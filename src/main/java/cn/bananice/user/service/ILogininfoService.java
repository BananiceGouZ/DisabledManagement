package cn.bananice.user.service;

import cn.bananice.user.domain.Logininfo;
import cn.bananice.basic.service.IBaseService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author bananice
 * @since 2023-01-08
 */
public interface ILogininfoService extends IBaseService<Logininfo> {

    void active(Long logininfoId, boolean state);
}
