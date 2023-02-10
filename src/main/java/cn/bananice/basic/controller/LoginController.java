package cn.bananice.basic.controller;

import cn.bananice.basic.dto.LoginDto;
import cn.bananice.basic.dto.PhoneRegisterDto;
import cn.bananice.basic.exception.BusinessException;
import cn.bananice.basic.service.ILoginService;
import cn.bananice.basic.util.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/login")
public class LoginController {
    @Autowired
    private ILoginService loginService;

    @PostMapping("/account")
    public AjaxResult account(@RequestBody LoginDto loginDto) {
        try {
            Map<String,Object> map = loginService.account(loginDto);
            return AjaxResult.getAjaxResult().setResultObj(map);
        } catch (BusinessException e) {
            e.printStackTrace();
            return AjaxResult.getAjaxResult().setSuccess(false).setMessage("登陆失败！" + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.getAjaxResult().setSuccess(false).setMessage("登陆异常！");
        }
    }

    @PostMapping("/wechat")
    public AjaxResult wechat(@RequestBody Map<String,String> map) {
        try {
            return loginService.wechat(map);
        } catch (BusinessException e) {
            e.printStackTrace();
            return AjaxResult.getAjaxResult().setSuccess(false).setMessage("登陆失败！" + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.getAjaxResult().setSuccess(false).setMessage("登陆异常！");
        }
    }

    @PostMapping("/wechat/binder")
    public AjaxResult binder(@RequestBody PhoneRegisterDto phoneRegisterDto) {
        try {
            Map<String, Object> map = loginService.binder(phoneRegisterDto);
            return AjaxResult.getAjaxResult().setResultObj(map);
        } catch (BusinessException e) {
            e.printStackTrace();
            return AjaxResult.getAjaxResult().setSuccess(false).setMessage("绑定失败！" + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.getAjaxResult().setSuccess(false).setMessage("绑定异常！");
        }
    }
}
