package cn.bananice.user.mapper;

import cn.bananice.user.domain.User;
import cn.bananice.basic.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author bananice
 * @since 2023-01-08
 */
public interface UserMapper extends BaseMapper<User> {

    User queryByPhone(String phone);

    User loadByEmail(String email);

    User loadByLogininfoId(Long logininfoId);
}