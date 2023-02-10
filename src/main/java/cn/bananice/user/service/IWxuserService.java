package cn.bananice.user.service;

import cn.bananice.user.domain.Wxuser;
import cn.bananice.basic.service.IBaseService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author bananice
 * @since 2023-01-11
 */
public interface IWxuserService extends IBaseService<Wxuser> {

    Wxuser laodByOpenid(String openid);
}
