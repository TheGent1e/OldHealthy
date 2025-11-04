package com.thegentle.oldhealth.Controller;


import com.thegentle.oldhealth.Service.Impl.EmpServiceImpl;
import com.thegentle.oldhealth.pojo.Emp.Emp;
import com.thegentle.oldhealth.pojo.PageResponse;
import com.thegentle.oldhealth.pojo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import retrofit2.Response;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/admin/emp")
public class EmpController {
    @Autowired
    private EmpServiceImpl empService;
    /**
     * 跳转到员工列表页面
     * @return
     */
    @GetMapping("/list")  // 员工列表
    public Result list(String name,
                       Integer gender,
                       String position,
                       String departmentName,
                       @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDateTime begin,
                       @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDateTime end,
                       @RequestParam(defaultValue = "1") Integer page,
                       @RequestParam (defaultValue = "10") Integer pageSize){
        log.info("查询请求参数： {},{},{},{},{}, {},{},{}", page, pageSize, name, gender,position, departmentName,begin, end);
        return Result.success(empService.findAll(page, pageSize, name, gender,position,departmentName ,begin, end));
    }
    /**
     * 添加员工
     * @param emp
     * @return
     */
    @PostMapping("/add")
    public Result add(@RequestBody Emp emp){
        log.info("添加员工：{}", emp);
        empService.addEmp(emp);
        return Result.success();
    }
    /**
     * 删除员工
     * @param ids
     * @return
     */
    @PostMapping("/delete")
    public Result delete(@RequestBody List<Integer> ids){
        log.info("删除员工：{}", ids);
        empService.deleteEmp(ids);
        return Result.success();
    }
    /**
     * 修改员工
     * @param emp
     * @return
     */
    @PostMapping("/update")
    public Result update(@RequestBody Emp emp){
        log.info("修改员工：{}", emp);
        empService.updateEmp(emp);
        return Result.success();
    }
}
