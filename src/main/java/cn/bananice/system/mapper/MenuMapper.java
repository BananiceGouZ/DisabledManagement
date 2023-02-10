package cn.bananice.system.mapper;

import cn.bananice.system.domain.Menu;
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
public interface MenuMapper extends BaseMapper<Menu> {

    List<Menu> loadByLogininfoId(Long logininfoId);

    List<Menu> loadParent();
}
