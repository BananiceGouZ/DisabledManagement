package cn.bananice.user.service;

import cn.bananice.basic.dto.EmailRegisterDto;
import cn.bananice.basic.dto.PhoneRegisterDto;
import cn.bananice.basic.service.IBaseService;
import cn.bananice.user.domain.User;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author bananice
 * @since 2023-01-08
 */
public interface IUserService extends IBaseService<User> {

    Long phoneRegister(PhoneRegisterDto phoneRegisterDto);

    User loadByPhone(String phone);

    void emailRegister(EmailRegisterDto emailRegisterDto);

    void activeEmail(String token);

    User queryByLogininfoId(Long logininfoId);
}