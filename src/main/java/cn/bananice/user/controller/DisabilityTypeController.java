package cn.bananice.user.controller;

import cn.bananice.user.service.IDisabilityTypeService;
import cn.bananice.user.domain.DisabilityType;
import cn.bananice.user.query.DisabilityTypeQuery;
import cn.bananice.basic.util.AjaxResult;
import cn.bananice.basic.util.PageList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/disabilityType")
public class DisabilityTypeController {
    @Autowired
    public IDisabilityTypeService disabilityTypeService;


    /**
     * 保存和修改公用的
     * @param disabilityType  传递的实体
     * @return Ajaxresult转换结果
     */
    @PutMapping
    public AjaxResult addOrUpdate(@RequestBody DisabilityType disabilityType){
        try {
            if( disabilityType.getId()!=null)
                disabilityTypeService.update(disabilityType);
            else
                disabilityTypeService.add(disabilityType);
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
            disabilityTypeService.remove(id);
            return AjaxResult.getAjaxResult();
        } catch (Exception e) {
        e.printStackTrace();
            return AjaxResult.getAjaxResult().setSuccess(false).setMessage("删除对象失败！"+e.getMessage());
        }
    }
	
    //获取用户
    @GetMapping("/{id}")
    public DisabilityType get(@PathVariable("id")Long id)
    {
        return disabilityTypeService.queryById(id);
    }


    /**
    * 查看所有的员工信息
    * @return
    */
    @GetMapping()
    public List<DisabilityType> list(){

        return disabilityTypeService.queryAll();
    }


    /**
    * 分页查询数据
    *
    * @param query 查询对象
    * @return PageList 分页对象
    */
    @PostMapping("/list")
    public PageList<DisabilityType> queryPage(@RequestBody DisabilityTypeQuery query)
    {
        return disabilityTypeService.queryPage(query);
    }
}
