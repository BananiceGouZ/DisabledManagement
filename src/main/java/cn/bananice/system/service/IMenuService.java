package cn.bananice.system.service;

import cn.bananice.basic.service.IBaseService;
import cn.bananice.system.domain.Menu;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author bananice
 * @since 2023-01-13
 */
public interface IMenuService extends IBaseService<Menu> {

    List<Menu> getTree();

    List<Menu> queryByLogininfoId(Long LogininfoId);

    List<Menu> getFirst();
}
