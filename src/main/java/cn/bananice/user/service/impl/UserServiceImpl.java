package cn.bananice.user.service.impl;

import cn.bananice.basic.constants.BaseConstants;
import cn.bananice.basic.dto.EmailRegisterDto;
import cn.bananice.basic.dto.PhoneRegisterDto;
import cn.bananice.basic.exception.BusinessException;
import cn.bananice.basic.jwt.JwtUtils;
import cn.bananice.basic.jwt.RsaUtils;
import cn.bananice.basic.service.impl.BaseServiceImpl;
import cn.bananice.basic.util.MD5Utils;
import cn.bananice.basic.util.StrUtils;
import cn.bananice.user.domain.Logininfo;
import cn.bananice.user.domain.User;
import cn.bananice.user.mapper.LogininfoMapper;
import cn.bananice.user.mapper.UserMapper;
import cn.bananice.user.service.IUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author bananice
 * @since 2023-01-08
 */
@Service
public class UserServiceImpl extends BaseServiceImpl<User> implements IUserService {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    UserMapper userMapper;

    @Autowired
    LogininfoMapper logininfoMapper;

    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public void add(User user) {
        user.setSalt(StrUtils.getComplexRandomString(32));
        user.setPassword(MD5Utils.encrypByMd5(user.getSalt() + "%%" + user.getPassword() + "%%"));

        Logininfo logininfo = userToLogininfo(user);

        logininfoMapper.save(logininfo);
        user.setLogininfoId(logininfo.getId());
        userMapper.save(user);
    }

    @Override
    public Long phoneRegister(PhoneRegisterDto phoneRegisterDto) {

        if (StringUtils.isBlank(phoneRegisterDto.getType()) ||
                StringUtils.isBlank(phoneRegisterDto.getPhone()) ||
                StringUtils.isBlank(phoneRegisterDto.getSmsCode()) ||
                StringUtils.isBlank(phoneRegisterDto.getPassword()) ||
                StringUtils.isBlank(phoneRegisterDto.getPasswordRepeat())) {
            throw new BusinessException("参数为空!");
        }

        if ("register".equals(phoneRegisterDto.getType())) {
            if (!phoneRegisterDto.getPassword().equals(phoneRegisterDto.getPasswordRepeat())) {
                throw new BusinessException("确认密码不一致！");
            }

            Object smsCodeAndTime = redisTemplate.opsForValue().get(BaseConstants.VerifyConstants.USER_PHONE_REGISTER_CODE_PREFIX + phoneRegisterDto.getPhone());
            if (smsCodeAndTime == null) {
                throw new BusinessException("手机验证码过期！");
            }

            String[] smsCodeAndTimArr = smsCodeAndTime.toString().split(":");
            if (!phoneRegisterDto.getSmsCode().equals(smsCodeAndTimArr[0])) {
                throw new BusinessException("手机验证码错误！");
            }

            //检验手机号是否注册
            if (userMapper.queryByPhone(phoneRegisterDto.getPhone()) != null) {
                throw new BusinessException("该手机号已被注册");
            }
        } else if (!"binder".equals(phoneRegisterDto.getType())) {
            throw new BusinessException("参数异常");
        }

        User user = phoneRegisterDtoToUser(phoneRegisterDto);
        add(user);

        return user.getId();
    }

    @Override
    public User loadByPhone(String phone) {
        return userMapper.queryByPhone(phone);
    }

    @Override
    public void emailRegister(EmailRegisterDto emailRegisterDto) {

        String email = emailRegisterDto.getEmail();
        String password = emailRegisterDto.getPassword();
        String passwordRepeat = emailRegisterDto.getPasswordRepeat();

        if (StringUtils.isBlank(email) || StringUtils.isBlank(password) || StringUtils.isBlank(passwordRepeat)) {
            throw new BusinessException("参数为空");
        }

        if (!password.equals(passwordRepeat)) {
            throw new BusinessException("确认密码不一致");
        }

        if (redisTemplate.opsForValue().get(BaseConstants.VerifyConstants.USER_PHONE_REGISTER_CODE_PREFIX + email) != null) {
            throw new BusinessException("请勿频繁发送");
        }

        if (userMapper.loadByEmail(email) != null) {
            throw new BusinessException("该邮箱已被注册");
        }

        //加密
        String token = null;
        try {
            PrivateKey privateKey = RsaUtils.getPrivateKey(JwtUtils.class.getClassLoader().getResource("hrm_auth_rsa").getFile());
            token = JwtUtils.generateTokenExpireInMinutes(emailRegisterDto, privateKey, 5);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BusinessException("邮件发送失败");
        }

        redisTemplate.opsForValue().set(BaseConstants.VerifyConstants.USER_PHONE_REGISTER_CODE_PREFIX + email, token, 5, TimeUnit.MINUTES);

        //发送审核结果邮件
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom("1640986862@qq.com");
        mailMessage.setTo(email);//发送给客户
//        mailMessage.setTo("1640986862@qq.com");//测试，发给自己
        mailMessage.setSubject("[宠物之家]注册验证");
        mailMessage.setText("请点击以下链接实现验证：http://localhost:8080/user/active/email/" + token);
        javaMailSender.send(mailMessage);

    }

    @Override
    public void activeEmail(String token) {
        EmailRegisterDto emailRegisterDto;

        try {
            PublicKey publicKey = RsaUtils.getPublicKey(JwtUtils.class.getClassLoader().getResource("hrm_auth_rsa.pub").getFile());
            emailRegisterDto = JwtUtils.getInfoFromToken(token, publicKey, EmailRegisterDto.class).getUserInfo();
        } catch (Exception e) {
            e.printStackTrace();
            throw new BusinessException("验证过期");
        }

        if (!token.equals(redisTemplate.opsForValue().get(BaseConstants.VerifyConstants.USER_PHONE_REGISTER_CODE_PREFIX + emailRegisterDto.getEmail()))) {
            throw new BusinessException("重复验证");
        }

        User user = new User();
        user.setEmail(emailRegisterDto.getEmail());
        user.setPassword(emailRegisterDto.getPassword());
        user.setUsername(StrUtils.getComplexRandomString(6) + "_" + StrUtils.getRandomString(4));
        user.setState(true);

        add(user);

        redisTemplate.delete(BaseConstants.VerifyConstants.USER_PHONE_REGISTER_CODE_PREFIX + emailRegisterDto.getEmail());
    }

    @Override
    public User queryByLogininfoId(Long logininfoId) {
        return userMapper.loadByLogininfoId(logininfoId);
    }

    private Logininfo userToLogininfo(User user) {
        Logininfo logininfo = new Logininfo();
        BeanUtils.copyProperties(user, logininfo);

        logininfo.setType(true);
        logininfo.setState(user.getState());
        return logininfo;
    }

    private User phoneRegisterDtoToUser(PhoneRegisterDto phoneRegisterDto) {
        User user = new User();
        user.setUsername(StrUtils.getComplexRandomString(6) + "_" + phoneRegisterDto.getPhone().substring(phoneRegisterDto.getPhone().length() - 4));
        user.setPhone(phoneRegisterDto.getPhone());
        user.setState(true);

        return user;
    }
}
