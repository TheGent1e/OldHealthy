package com.thegentle.oldhealth.Controller;

import com.thegentle.oldhealth.Service.DeptService;
import com.thegentle.oldhealth.pojo.Department.Dept;
import com.thegentle.oldhealth.pojo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/dept")
@Slf4j
public class DeptController {
    @Autowired
    private DeptService deptService;

    // 查询部门列表
    @GetMapping("/list")
    public Result list(){
        //1、查询部门列表
        List<Dept> list=deptService.list();
        return Result.success(list);
    }
    // 只查询部门id和部门名称
    @GetMapping("/findDeptIdAndName")
    public Result findDeptIdAndName(){
        return Result.success(deptService.findDeptIdAndName());
    }
    // 添加部门
    @PostMapping("/add")
    public Result add(@RequestBody Dept dept){
        log.info("添加部门：{}", dept);
        return deptService.add(dept)==null?Result.success():Result.error("部门名称已经存在");
    }
    // 修改部门
    @PostMapping("/update")
    public Result update(@RequestBody Dept dept){

        log.info("修改部门：{}", dept);
        return deptService.update(dept)==null?Result.success():Result.error("部门名称已经存在");
    }
    //回显部门
    @GetMapping("/findById/{id}")
    public Result findById(@PathVariable Integer id){
        log.info("回显部门：{}", id);
        Dept dept=deptService.findById(id);
        return Result.success(dept);
    }
    // 删除部门
    @DeleteMapping("/delete")
    public Result delete(@RequestParam List<Integer> ids){
        log.info("删除部门：{}", ids);
        deptService.delete(ids);
        return Result.success();
    }

}
