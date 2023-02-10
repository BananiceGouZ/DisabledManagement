package cn.bananice.user.controller;

import cn.bananice.user.service.ILogininfoService;
import cn.bananice.user.domain.Logininfo;
import cn.bananice.user.query.LogininfoQuery;
import cn.bananice.basic.util.AjaxResult;
import cn.bananice.basic.util.PageList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/logininfo")
public class LogininfoController {
    @Autowired
    public ILogininfoService logininfoService;


    /**
     * 保存和修改公用的
     * @param logininfo  传递的实体
     * @return Ajaxresult转换结果
     */
    @PutMapping
    public AjaxResult addOrUpdate(@RequestBody Logininfo logininfo){
        try {
            if( logininfo.getId()!=null)
                logininfoService.update(logininfo);
            else
                logininfoService.add(logininfo);
            return AjaxResult.getAjaxResult();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.getAjaxResult().setSuccess(false).setMessage("保存对象失败！"+e.getMessage());
        }
    }
    /**
    * 删除对象信息
    * @param id
    * @return
    */
    @DeleteMapping(value="/{id}")
    public AjaxResult delete(@PathVariable("id") Long id){
        try {
            logininfoService.remove(id);
            return AjaxResult.getAjaxResult();
        } catch (Exception e) {
        e.printStackTrace();
            return AjaxResult.getAjaxResult().setSuccess(false).setMessage("删除对象失败！"+e.getMessage());
        }
    }
	
    //获取用户
    @GetMapping("/{id}")
    public Logininfo get(@PathVariable("id")Long id)
    {
        return logininfoService.queryById(id);
    }


    /**
    * 查看所有的员工信息
    * @return
    */
    @GetMapping()
    public List<Logininfo> list(){

        return logininfoService.queryAll();
    }


    /**
    * 分页查询数据
    *
    * @param query 查询对象
    * @return PageList 分页对象
    */
    @PostMapping("/list")
    public PageList<Logininfo> queryPage(@RequestBody LogininfoQuery query)
    {
        return logininfoService.queryPage(query);
    }
}
