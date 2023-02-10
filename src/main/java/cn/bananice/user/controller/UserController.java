package cn.bananice.user.controller;

import cn.bananice.basic.dto.EmailRegisterDto;
import cn.bananice.basic.dto.PhoneRegisterDto;
import cn.bananice.basic.exception.BusinessException;
import cn.bananice.user.service.IUserService;
import cn.bananice.user.domain.User;
import cn.bananice.user.query.UserQuery;
import cn.bananice.basic.util.AjaxResult;
import cn.bananice.basic.util.PageList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    public IUserService userService;

    //    /user/register/phone
    @PostMapping("/register/phone")
    public AjaxResult phoneRegister(@RequestBody PhoneRegisterDto phoneRegisterDto) {
        try {
            userService.phoneRegister(phoneRegisterDto);
            return AjaxResult.getAjaxResult();
        } catch (BusinessException e) {
            e.printStackTrace();
            return AjaxResult.getAjaxResult().setSuccess(false).setMessage("注册失败！" + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.getAjaxResult().setSuccess(false).setMessage("注册失败！");
        }
    }

    @PostMapping("/register/email")
    public AjaxResult emailRegister(@RequestBody EmailRegisterDto emailRegisterDto) {
        try {
            userService.emailRegister(emailRegisterDto);
            return AjaxResult.getAjaxResult();
        } catch (BusinessException e) {
            e.printStackTrace();
            return AjaxResult.getAjaxResult().setSuccess(false).setMessage("注册失败！" + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.getAjaxResult().setSuccess(false).setMessage("注册失败！");
        }
    }

    @GetMapping("/active/email/{token}")
    public String active(@PathVariable("token") String token) {
        try {
            userService.activeEmail(token);
            return "<h2 style=\"color:green\">验证成功！</h2>";
        } catch (BusinessException e) {
            e.printStackTrace();
            return e.getMessage();
        } catch (Exception e) {
            e.printStackTrace();
            return "验证失败！请联系客服：029-88119677";
        }
    }

    /**
     * 保存和修改公用的
     *
     * @param user 传递的实体
     * @return Ajaxresult转换结果
     */
    @PutMapping
    public AjaxResult addOrUpdate(@RequestBody User user) {
        try {
            if (user.getId() != null) userService.update(user);
            else userService.add(user);
            return AjaxResult.getAjaxResult();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.getAjaxResult().setSuccess(false).setMessage("保存对象失败！" + e.getMessage());
        }
    }

    /**
     * 删除对象信息
     *
     * @param id
     * @return
     */
    @DeleteMapping(value = "/{id}")
    public AjaxResult delete(@PathVariable("id") Long id) {
        try {
            userService.remove(id);
            return AjaxResult.getAjaxResult();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.getAjaxResult().setSuccess(false).setMessage("删除对象失败！" + e.getMessage());
        }
    }

    //获取用户
    @GetMapping("/{id}")
    public User get(@PathVariable("id") Long id) {
        return userService.queryById(id);
    }


    /**
     * 查看所有的员工信息
     *
     * @return
     */
    @GetMapping()
    public List<User> list() {

        return userService.queryAll();
    }


    /**
     * 分页查询数据
     *
     * @param query 查询对象
     * @return PageList 分页对象
     */
    @PostMapping("/list")
    public PageList<User> queryPage(@RequestBody UserQuery query) {
        return userService.queryPage(query);
    }
}