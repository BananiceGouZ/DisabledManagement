package cn.bananice.basic.service;

import cn.bananice.basic.dto.LoginDto;
import cn.bananice.basic.dto.PhoneRegisterDto;
import cn.bananice.basic.util.AjaxResult;

import java.util.Map;

public interface ILoginService {
    Map<String,Object> account(LoginDto loginDto);

    AjaxResult wechat(Map<String, String> map);

    Map<String, Object> binder(PhoneRegisterDto phoneRegisterDto);
}
