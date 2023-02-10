package cn.bananice.system.service.impl;

import cn.bananice.system.domain.Menu;
import cn.bananice.system.mapper.MenuMapper;
import cn.bananice.system.service.IMenuService;
import cn.bananice.basic.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author bananice
 * @since 2023-01-13
 */
@Service
public class MenuServiceImpl extends BaseServiceImpl<Menu> implements IMenuService {

    @Autowired
    private MenuMapper menuMapper;

    @Override
    public List<Menu> getTree() {
        List<Menu> tree = new ArrayList<>();

        List<Menu> menus = menuMapper.loadAll();
        Map<Long, Menu> map = new HashMap<>();
        for (Menu menu : menus) {
            map.put(menu.getId(), menu);
        }
        for (Menu menu : menus) {
            if (menu.getParentId() == null) {
                tree.add(menu);
            } else {
                map.get(menu.getParentId()).getChildren().add(menu);
            }
        }

        return tree;
    }

    @Override
    public List<Menu> queryByLogininfoId(Long LogininfoId) {
        return menuMapper.loadByLogininfoId(LogininfoId);
    }

    @Override
    public List<Menu> getFirst() {
        return menuMapper.loadParent();
    }
}
