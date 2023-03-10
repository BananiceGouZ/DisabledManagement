package cn.bananice.basic.service.impl;

import cn.bananice.basic.constants.BaseConstants;
import cn.bananice.basic.dto.LoginDto;
import cn.bananice.basic.dto.PhoneRegisterDto;
import cn.bananice.basic.exception.BusinessException;
import cn.bananice.basic.jwt.JwtRsaHolder;
import cn.bananice.basic.jwt.JwtUtils;
import cn.bananice.basic.jwt.PayloadData;
import cn.bananice.basic.jwt.RsaUtils;
import cn.bananice.basic.service.ILoginService;
import cn.bananice.basic.util.AjaxResult;
import cn.bananice.basic.util.HttpClientUtils;
import cn.bananice.basic.util.MD5Utils;
import cn.bananice.system.domain.Menu;
import cn.bananice.system.service.IMenuService;
import cn.bananice.system.service.IPermissionService;
import cn.bananice.user.domain.Logininfo;
import cn.bananice.user.domain.User;
import cn.bananice.user.domain.Wxuser;
import cn.bananice.user.mapper.LogininfoMapper;
import cn.bananice.user.service.IUserService;
import cn.bananice.user.service.IWxuserService;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.security.PrivateKey;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class LoginServiceImpl implements ILoginService {

    @Autowired
    private LogininfoMapper logininfoMapper;

    @Autowired
    private IWxuserService wxuserService;

    @Autowired
    private IUserService userService;

    @Autowired
    private IPermissionService permissionService;

    @Autowired
    private IMenuService menuService;

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public Map<String, Object> account(LoginDto loginDto) {
        String account = loginDto.getAccount();
        String password = loginDto.getPassword();
        String type = loginDto.getType();

        if (StringUtils.isAllBlank(account, password, type)) {
            throw new BusinessException("????????????");
        }

        Logininfo logininfo = logininfoMapper.loadByAccountAndType(account, type);
        if (logininfo == null || !logininfo.getPassword().equals(MD5Utils.encrypByMd5(logininfo.getSalt() + "%%" + password + "%%"))) {
            throw new BusinessException("????????????????????????");
        }

        if(!logininfo.getState()){
            throw new BusinessException("??????????????????????????????");
        }

        return getResultMap(logininfo);
    }

    @Override
    public AjaxResult wechat(Map<String, String> map) {
        String code = map.get("code");

        if (StringUtils.isBlank(code)) {
            throw new BusinessException("????????????");
        }

        String url = BaseConstants.WxConstants.TOKEN_URL
                .replace("APPID", BaseConstants.WxConstants.APPID)
                .replace("SECRET", BaseConstants.WxConstants.SECRET)
                .replace("CODE", code);
        String openidAndAccesstoken = HttpClientUtils.httpGet(url);
        JSONObject jsonObject = JSONObject.parseObject(openidAndAccesstoken);
        String openid = jsonObject.getString("openid");
        String access_token = jsonObject.getString("access_token");

        Wxuser wxuser = wxuserService.laodByOpenid(openid);
        if (wxuser != null) {  //???????????????????????????????????????????????????
            Logininfo logininfo = logininfoMapper.loadByUserId(wxuser.getUserId());

            Map<String, Object> resultMap = getResultMap(logininfo);
            return AjaxResult.getAjaxResult().setResultObj(resultMap);
        }

        //???????????????????????????
        String resultUrl = "?openId=" + openid + "&accessToken=" + access_token;
        return AjaxResult.getAjaxResult().setSuccess(false).setMessage("binder").setResultObj(resultUrl);

    }

    @Override
    public Map<String, Object> binder(PhoneRegisterDto phoneRegisterDto) {
        String phone = phoneRegisterDto.getPhone();
        String smsCode = phoneRegisterDto.getSmsCode();
        String accessToken = phoneRegisterDto.getAccessToken();
        String openId = phoneRegisterDto.getOpenId();
        String type = phoneRegisterDto.getType();

        if (StringUtils.isAllBlank(phone, smsCode, accessToken, openId, type)) {
            throw new BusinessException("????????????");
        }

        Object smsCodeAndTime = redisTemplate.opsForValue().get(BaseConstants.VerifyConstants.USER_PHONE_BINDER_CODE_PREFIX + phone);
        if (smsCodeAndTime == null) {
            throw new BusinessException("????????????????????????");
        }

        String[] smsCodeAndTimArr = smsCodeAndTime.toString().split(":");
        if (!phoneRegisterDto.getSmsCode().equals(smsCodeAndTimArr[0])) {
            throw new BusinessException("????????????????????????");
        }

        String url = BaseConstants.WxConstants.USERURL.replace("OPENID", openId).replace("ACCESS_TOKEN", accessToken);
        String wxuserJson = HttpClientUtils.httpGet(url);
        Wxuser wxuser = JSONObject.parseObject(wxuserJson, Wxuser.class);

        User user;
        Long userId;
        if ((user = userService.loadByPhone(phone)) != null) {    //?????????????????????user?????????userId
            userId = user.getId();
        } else {   //???????????????
            //????????????
            phoneRegisterDto.setPassword("123456");
            phoneRegisterDto.setPasswordRepeat("123456");
            userId = userService.phoneRegister(phoneRegisterDto);
        }

        //???user?????????wxuser???
        wxuser.setUserId(userId);
        wxuserService.add(wxuser);

        Logininfo logininfo = logininfoMapper.loadByUserId(userId);
        return getResultMap(logininfo);
    }

    private Map<String, Object> getResultMap(Logininfo logininfo) {
        Map<String, Object> map = new HashMap<>();

        try {
            PayloadData payloadData = new PayloadData();
            PrivateKey privateKey = RsaUtils.getPrivateKey(JwtRsaHolder.INSTANCE.getJwtRsaPriData());//????????????

            payloadData.setLogininfo(logininfo);
            if (!logininfo.getType()) {    //true??????  false?????????
                List<String> permissions = permissionService.queryByLogininfoId(logininfo.getId());
                List<Menu> menus = menuService.queryByLogininfoId(logininfo.getId());

                payloadData.setPermissions(permissions);
                payloadData.setMenus(menus);

                map.put("permissions",permissions);
                map.put("menus",menus);
            }

            String token = JwtUtils.generateTokenExpireInMinutes(payloadData, privateKey, 30);

            logininfo.setPassword(null);
            logininfo.setSalt(null);
            map.put("token", token);
            map.put("logininfo", logininfo);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return map;
    }

    /*private String getToken(Logininfo logininfo) {
        String token = null;
        try {
            PayloadData payloadData = new PayloadData();
            PrivateKey privateKey = RsaUtils.getPrivateKey(JwtRsaHolder.INSTANCE.getJwtRsaPriData());//????????????

            payloadData.setLogininfo(logininfo);
            if (!logininfo.getType()) {    //true??????  false?????????
                payloadData.setPermissions(permissionService.queryByLogininfoId(logininfo.getId()));
                payloadData.setMenus(menuService.queryByLogininfoId(logininfo.getId()));
            }

            token = JwtUtils.generateTokenExpireInMinutes(payloadData, privateKey, 30);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return token;
    }*/
}
