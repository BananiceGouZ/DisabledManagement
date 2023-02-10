package cn.bananice.system.service.impl;

import cn.bananice.system.domain.Role;
import cn.bananice.system.mapper.RoleMapper;
import cn.bananice.system.service.IRoleService;
import cn.bananice.basic.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author bananice
 * @since 2023-01-13
 */
@Service
public class RoleServiceImpl extends BaseServiceImpl<Role> implements IRoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public void add(Role role) {
        super.add(role);
        handleRelationTable(role);
    }

    @Override
    public void update(Role role) {
        super.update(role);
        handleRelationTable(role);
    }

    private void handleRelationTable(Role role) {
        roleMapper.deleteRoleMenuByRoleId(role.getId());
        roleMapper.deleteRolePermissionByRoleId(role.getId());

        roleMapper.saveRoleMenuByRoleId(role.getId(), role.getMenus());
        roleMapper.saveRolePermissionByRoleId(role.getId(), role.getPermissions());
    }
}
