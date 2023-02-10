package cn.bananice.system.controller;

import cn.bananice.system.service.IMenuService;
import cn.bananice.system.domain.Menu;
import cn.bananice.system.query.MenuQuery;
import cn.bananice.basic.util.AjaxResult;
import cn.bananice.basic.util.PageList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/menu")
public class MenuController {
    @Autowired
    public IMenuService menuService;


    /**
     * 保存和修改公用的
     *
     * @param menu 传递的实体
     * @return Ajaxresult转换结果
     */
    @PutMapping
    public AjaxResult addOrUpdate(@RequestBody Menu menu) {
        try {
            if (menu.getId() != null)
                menuService.update(menu);
            else
                menuService.add(menu);
            return AjaxResult.getAjaxResult();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.getAjaxResult().setSuccess(false).setMessage("保存对象失败！" + e.getMessage());
        }
    }

    /**
     * 删除对象信息
     *
     * @param id
     * @return
     */
    @DeleteMapping(value = "/{id}")
    public AjaxResult delete(@PathVariable("id") Long id) {
        try {
            menuService.remove(id);
            return AjaxResult.getAjaxResult();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.getAjaxResult().setSuccess(false).setMessage("删除对象失败！" + e.getMessage());
        }
    }

    //获取用户
    @GetMapping("/{id}")
    public Menu get(@PathVariable("id") Long id) {
        return menuService.queryById(id);
    }


    /**
     * 查看所有的员工信息
     *
     * @return
     */
    @GetMapping()
    public List<Menu> list() {

        return menuService.queryAll();
    }


    /**
     * 分页查询数据
     *
     * @param query 查询对象
     * @return PageList 分页对象
     */
    @PostMapping("/list")
    public PageList<Menu> queryPage(@RequestBody MenuQuery query) {
        return menuService.queryPage(query);
    }

    @GetMapping("/tree")
    public List<Menu> getTree() {
        return menuService.getTree();
    }

    @GetMapping("/first")
    public List<Menu> getFirst() {
        return menuService.getFirst();
    }
}
