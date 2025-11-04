package com.thegentle.oldhealth.Controller;

import com.thegentle.oldhealth.Service.DeptService;
import com.thegentle.oldhealth.pojo.Department.Dept;
import com.thegentle.oldhealth.pojo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
}
