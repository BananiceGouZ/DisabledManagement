package cn.bananice.basic.service;

import cn.bananice.basic.dto.PhoneRegisterDto;

public interface IVerifyCodeService {
    String getVerifyCode(String imageCodeKey);

    void sendSmsCode(PhoneRegisterDto phoneRegisterDto);
}
