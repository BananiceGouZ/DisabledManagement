package cn.bananice.basic.service.impl;

import cn.bananice.basic.constants.BaseConstants;
import cn.bananice.basic.dto.PhoneRegisterDto;
import cn.bananice.basic.exception.BusinessException;
import cn.bananice.basic.service.IVerifyCodeService;
import cn.bananice.basic.util.StrUtils;
import cn.bananice.basic.util.VerifyCodeUtils;
import cn.bananice.user.mapper.UserMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class VerifyCodeServiceImpl implements IVerifyCodeService {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private UserMapper userMapper;

    @Override
    public String getVerifyCode(String imageCodeKey) {

        if (StringUtils.isBlank(imageCodeKey)) {
            throw new BusinessException("参数为空");
        }

        String imageCodeValue = StrUtils.getComplexRandomString(4);

        redisTemplate.opsForValue().set(imageCodeKey, imageCodeValue, 3, TimeUnit.MINUTES);

        String verifyCode = VerifyCodeUtils.VerifyCode(100, 40, imageCodeValue);

        return verifyCode;
    }

    @Override
    public void sendSmsCode(PhoneRegisterDto phoneRegisterDto) {

        String phone = phoneRegisterDto.getPhone();
        String type = phoneRegisterDto.getType();

        if (StringUtils.isBlank(phone) || StringUtils.isBlank(type)) {
            throw new BusinessException("参数为空！");
        }

        String redisKeyPrefix;
        if ("register".equals(type)){
            registerCheck(phoneRegisterDto);
            redisKeyPrefix = BaseConstants.VerifyConstants.USER_PHONE_REGISTER_CODE_PREFIX;
        } else if ("binder".equals(type)) {
            redisKeyPrefix = BaseConstants.VerifyConstants.USER_PHONE_BINDER_CODE_PREFIX;
        }else {
            throw new BusinessException("参数错误");
        }

        String smsCode = initSmsCode(phoneRegisterDto,redisKeyPrefix);

        //发送短信
//        SmsUtil.sendSms(phone,"亲，您的验证码是：" + smsCode +",请在三分钟内使用");  //TODO 使用时要打开
        System.out.println("亲，您的验证码是：" + smsCode + ",请在三分钟内使用");

    }

    private void registerCheck(PhoneRegisterDto phoneRegisterDto) {
        String imageCodeKey = phoneRegisterDto.getImageCodeKey();
        String imageCodeValue = phoneRegisterDto.getImageCodeValue();

        //参数校验
        if (StringUtils.isAllBlank(imageCodeKey, imageCodeValue)) {
            throw new BusinessException("参数为空！");
        }

        //验证码校验
        Object imageCode = redisTemplate.opsForValue().get(imageCodeKey);
        if (imageCode == null || !imageCodeValue.equalsIgnoreCase(imageCode.toString())) {
            throw new BusinessException("验证码错误！");
        }

    }

    private String initSmsCode(PhoneRegisterDto phoneRegisterDto, String redisKeyPrefix) {
        String phone = phoneRegisterDto.getPhone();

        //获取验证码 <register:phone,验证码:时间戳>
        String redisKey = redisKeyPrefix + phone;
        String smsCode;
        Long currentTime = System.currentTimeMillis();

        Object objRedisValue = redisTemplate.opsForValue().get(redisKey);
        if (objRedisValue != null) {
            //验证是否间隔大于1min
            String[] smsCodeAndTimeArr = objRedisValue.toString().split(":");
            if (currentTime - Long.valueOf(smsCodeAndTimeArr[1]) < 60 * 1000) {
                throw new BusinessException("请稍等片刻再发送");
            }

            smsCode = smsCodeAndTimeArr[0];
        } else {
            //生成验证码
            smsCode = StrUtils.getRandomString(6);
        }

        //存入Redis <register:phone,验证码:时间戳>
        redisTemplate.opsForValue().set(redisKey, smsCode + ":" + currentTime, 3, TimeUnit.MINUTES);
        
        return smsCode;
    }
}
