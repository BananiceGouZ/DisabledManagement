package cn.bananice.basic.controller;

import cn.bananice.basic.dto.PhoneRegisterDto;
import cn.bananice.basic.exception.BusinessException;
import cn.bananice.basic.service.IVerifyCodeService;
import cn.bananice.basic.util.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/verifyCode")
public class VerifycodeController {

    @Autowired
    IVerifyCodeService verifyCodeService;

    @GetMapping("/image/{imageCodeKey}")
    public AjaxResult image(@PathVariable("imageCodeKey") String imageCodeKey) {
        try {
            String VerifyCode = verifyCodeService.getVerifyCode(imageCodeKey);
            return AjaxResult.getAjaxResult().setResultObj(VerifyCode);
        } catch (BusinessException e) {
            e.printStackTrace();
            return AjaxResult.getAjaxResult().setSuccess(false).setMessage(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.getAjaxResult().setSuccess(false).setMessage("获取图形验证码失败！");
        }
    }

    @PostMapping("/sendSmsCode")
    public AjaxResult sendSmsCode(@RequestBody PhoneRegisterDto phoneRegisterDto) {
        try {
            verifyCodeService.sendSmsCode(phoneRegisterDto);
            return AjaxResult.getAjaxResult();
        } catch (BusinessException e) {
            e.printStackTrace();
            return AjaxResult.getAjaxResult().setSuccess(false).setMessage(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.getAjaxResult().setSuccess(false).setMessage("获取手机验证码失败！");
        }
    }
}
