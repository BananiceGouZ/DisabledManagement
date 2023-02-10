package cn.bananice.org.controller;

import cn.bananice.org.service.IEmployeeService;
import cn.bananice.org.domain.Employee;
import cn.bananice.org.query.EmployeeQuery;
import cn.bananice.basic.util.AjaxResult;
import cn.bananice.basic.util.PageList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    public IEmployeeService employeeService;


    /**
     * 保存和修改公用的
     * @param employee  传递的实体
     * @return Ajaxresult转换结果
     */
    @PutMapping
    public AjaxResult addOrUpdate(@RequestBody Employee employee){
        try {
            if( employee.getId()!=null)
                employeeService.update(employee);
            else
                employeeService.add(employee);
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
            employeeService.remove(id);
            return AjaxResult.getAjaxResult();
        } catch (Exception e) {
        e.printStackTrace();
            return AjaxResult.getAjaxResult().setSuccess(false).setMessage("删除对象失败！"+e.getMessage());
        }
    }
	
    //获取用户
    @GetMapping("/{id}")
    public Employee get(@PathVariable("id")Long id)
    {
        return employeeService.queryById(id);
    }


    /**
    * 查看所有的员工信息
    * @return
    */
    @GetMapping()
    public List<Employee> list(){

        return employeeService.queryAll();
    }


    /**
    * 分页查询数据
    *
    * @param query 查询对象
    * @return PageList 分页对象
    */
    @PostMapping("/list")
    public PageList<Employee> queryPage(@RequestBody EmployeeQuery query)
    {
        return employeeService.queryPage(query);
    }
}
