package cn.bananice.addr.controller;

import cn.bananice.addr.service.IAddressStreetService;
import cn.bananice.addr.domain.AddressStreet;
import cn.bananice.addr.query.AddressStreetQuery;
import cn.bananice.basic.util.AjaxResult;
import cn.bananice.basic.util.PageList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/addressStreet")
public class AddressStreetController {
    @Autowired
    public IAddressStreetService addressStreetService;


    /**
     * 保存和修改公用的
     * @param addressStreet  传递的实体
     * @return Ajaxresult转换结果
     */
    @PutMapping
    public AjaxResult addOrUpdate(@RequestBody AddressStreet addressStreet){
        try {
            if( addressStreet.getId()!=null)
                addressStreetService.update(addressStreet);
            else
                addressStreetService.add(addressStreet);
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
            addressStreetService.remove(id);
            return AjaxResult.getAjaxResult();
        } catch (Exception e) {
        e.printStackTrace();
            return AjaxResult.getAjaxResult().setSuccess(false).setMessage("删除对象失败！"+e.getMessage());
        }
    }
	
    //获取用户
    @GetMapping("/{id}")
    public AddressStreet get(@PathVariable("id")Long id)
    {
        return addressStreetService.queryById(id);
    }


    /**
    * 查看所有的员工信息
    * @return
    */
    @GetMapping()
    public List<AddressStreet> list(){

        return addressStreetService.queryAll();
    }


    /**
    * 分页查询数据
    *
    * @param query 查询对象
    * @return PageList 分页对象
    */
    @PostMapping("/list")
    public PageList<AddressStreet> queryPage(@RequestBody AddressStreetQuery query)
    {
        return addressStreetService.queryPage(query);
    }
}
