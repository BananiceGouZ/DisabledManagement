package cn.bananice.user.mapper;

import cn.bananice.user.domain.Wxuser;
import cn.bananice.basic.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author bananice
 * @since 2023-01-11
 */
public interface WxuserMapper extends BaseMapper<Wxuser> {

    Wxuser laodByOpenid(String openid);
}
