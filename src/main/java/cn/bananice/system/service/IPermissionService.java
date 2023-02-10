package cn.bananice.system.service;

import cn.bananice.basic.service.IBaseService;
import cn.bananice.system.domain.Permission;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author bananice
 * @since 2023-01-13
 */
public interface IPermissionService extends IBaseService<Permission> {
    List<String> queryByLogininfoId(Long logininfoId);
}
