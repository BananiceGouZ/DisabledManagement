package cn.bananice.system.mapper;

import cn.bananice.system.domain.Permission;
import cn.bananice.basic.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author bananice
 * @since 2023-01-13
 */
public interface PermissionMapper extends BaseMapper<Permission> {

    Permission loadBySn(String sn);

    List<String> loadByLogininfoId(Long logininfoId);
}
