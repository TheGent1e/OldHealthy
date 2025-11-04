package com.thegentle.oldhealth.Controller;


import com.thegentle.oldhealth.Service.AdminService;
import com.thegentle.oldhealth.pojo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;
    // 统计用户数 员工数 部门数量
    @PostMapping("/count")
    public Result count(){

        Map<String,Integer> countAll = adminService.countAll();
        log.info("Admin:count{}","count");
        // 统计用户数
        return Result.success(countAll);

    }
}
