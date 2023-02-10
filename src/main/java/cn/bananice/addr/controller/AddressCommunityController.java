package cn.bananice.addr.controller;

import cn.bananice.addr.service.IAddressCommunityService;
import cn.bananice.addr.domain.AddressCommunity;
import cn.bananice.addr.query.AddressCommunityQuery;
import cn.bananice.basic.util.AjaxResult;
import cn.bananice.basic.util.PageList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/addressCommunity")
public class AddressCommunityController {
    @Autowired
    public IAddressCommunityService addressCommunityService;


    /**
     * 保存和修改公用的
     * @param addressCommunity  传递的实体
     * @return Ajaxresult转换结果
     */
    @PutMapping
    public AjaxResult addOrUpdate(@RequestBody AddressCommunity addressCommunity){
        try {
            if( addressCommunity.getId()!=null)
                addressCommunityService.update(addressCommunity);
            else
                addressCommunityService.add(addressCommunity);
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
            addressCommunityService.remove(id);
            return AjaxResult.getAjaxResult();
        } catch (Exception e) {
        e.printStackTrace();
            return AjaxResult.getAjaxResult().setSuccess(false).setMessage("删除对象失败！"+e.getMessage());
        }
    }
	
    //获取用户
    @GetMapping("/{id}")
    public AddressCommunity get(@PathVariable("id")Long id)
    {
        return addressCommunityService.queryById(id);
    }


    /**
    * 查看所有的员工信息
    * @return
    */
    @GetMapping()
    public List<AddressCommunity> list(){

        return addressCommunityService.queryAll();
    }


    /**
    * 分页查询数据
    *
    * @param query 查询对象
    * @return PageList 分页对象
    */
    @PostMapping("/list")
    public PageList<AddressCommunity> queryPage(@RequestBody AddressCommunityQuery query)
    {
        return addressCommunityService.queryPage(query);
    }
}
