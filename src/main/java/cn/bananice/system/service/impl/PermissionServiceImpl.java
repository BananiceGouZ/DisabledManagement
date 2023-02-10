package cn.bananice.system.service.impl;

import cn.bananice.system.domain.Permission;
import cn.bananice.system.mapper.PermissionMapper;
import cn.bananice.system.service.IPermissionService;
import cn.bananice.basic.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author bananice
 * @since 2023-01-13
 */
@Service
public class PermissionServiceImpl extends BaseServiceImpl<Permission> implements IPermissionService {

    @Autowired
    private PermissionMapper permissionMapper;

    @Override
    public List<String> queryByLogininfoId(Long logininfoId) {
        return permissionMapper.loadByLogininfoId(logininfoId);
    }
}
