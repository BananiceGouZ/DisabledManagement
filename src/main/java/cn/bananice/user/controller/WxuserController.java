package cn.bananice.user.controller;

import cn.bananice.user.service.IWxuserService;
import cn.bananice.user.domain.Wxuser;
import cn.bananice.user.query.WxuserQuery;
import cn.bananice.basic.util.AjaxResult;
import cn.bananice.basic.util.PageList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/wxuser")
public class WxuserController {
    @Autowired
    public IWxuserService wxuserService;


    /**
     * 保存和修改公用的
     * @param wxuser  传递的实体
     * @return Ajaxresult转换结果
     */
    @PutMapping
    public AjaxResult addOrUpdate(@RequestBody Wxuser wxuser){
        try {
            if( wxuser.getId()!=null)
                wxuserService.update(wxuser);
            else
                wxuserService.add(wxuser);
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
            wxuserService.remove(id);
            return AjaxResult.getAjaxResult();
        } catch (Exception e) {
        e.printStackTrace();
            return AjaxResult.getAjaxResult().setSuccess(false).setMessage("删除对象失败！"+e.getMessage());
        }
    }
	
    //获取用户
    @GetMapping("/{id}")
    public Wxuser get(@PathVariable("id")Long id)
    {
        return wxuserService.queryById(id);
    }


    /**
    * 查看所有的员工信息
    * @return
    */
    @GetMapping()
    public List<Wxuser> list(){

        return wxuserService.queryAll();
    }


    /**
    * 分页查询数据
    *
    * @param query 查询对象
    * @return PageList 分页对象
    */
    @PostMapping("/list")
    public PageList<Wxuser> queryPage(@RequestBody WxuserQuery query)
    {
        return wxuserService.queryPage(query);
    }
}
