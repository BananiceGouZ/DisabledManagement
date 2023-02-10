package cn.bananice.basic.dto;

import lombok.Data;

@Data
public class PhoneRegisterDto {
    private String phone;//用户填入的手机号
    private String imageCodeValue;//用户输入的图形验证码
    private String smsCode;
    private String password;
    private String passwordRepeat;
    private String imageCodeKey;//从localStorage中获取的图形验证码key

    private String accessToken;
    private String openId;

    private String type;    //register  binder
}
