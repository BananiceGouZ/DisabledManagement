package cn.bananice.system.mapper;

import cn.bananice.system.domain.Role;
import cn.bananice.basic.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author bananice
 * @since 2023-01-13
 */
public interface RoleMapper extends BaseMapper<Role> {

    void deleteRoleMenuByRoleId(Long roleId);

    void deleteRolePermissionByRoleId(Long roleId);

    void saveRoleMenuByRoleId(@Param("roleId") Long roleId, @Param("menus") List<Long> menus);

    void saveRolePermissionByRoleId(@Param("roleId") Long roleId, @Param("permissions") List<Long> permissions);
}
