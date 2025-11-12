package com.thegentle.oldhealth.Controller;

import com.thegentle.oldhealth.Service.UserService;
import com.thegentle.oldhealth.pojo.Result;
import com.thegentle.oldhealth.pojo.User.User;
import com.thegentle.oldhealth.pojo.User.UserBasicInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;


@Slf4j
@RestController
public class UserController {
    @Autowired
    private UserService userService;
    //登录接口
    @PostMapping("/login")
    public Result login(@RequestBody User user) {
        log.info("login:{}",user);
        //调用Service层
        UserBasicInfo userBasicInfo = userService.login(user);
        if(userBasicInfo!=null){
            return Result.success(userBasicInfo);
        }
        return Result.error("用户名或密码错误");
    }

    //注册接口
    @GetMapping("/register")
    public Result register(@RequestBody User user) {
        log.info("register:{}",user);
        //调用Service层
        return userService.register(user) == 1
                ? Result.success("注册成功")
                : Result.error("用户已存在");
    }

    //获取用户基本信息
    @GetMapping("/user/getUserBasicInfo")
    public Result getUserBasicInfo(@RequestBody User user) {
        log.info("getUserBasicInfo:{}",user);
        return Result.success(userService.getUserBasicInfo(user));
    }
    //分页获取用户列表
    @PostMapping("/user/getUserList")
    public Result getUserList(String username,
                              String phone,
                              String email,
                              Integer role,
                              String status,
                              @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDateTime begin,
                              @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDateTime end,
                              @RequestParam(defaultValue = "1") Integer page,
                              @RequestParam (defaultValue = "10") Integer pageSize) {
        log.info("getUserList:{},{},{},{},{}, {},{},{},{},pagesize:{}", page, pageSize, username, phone, email,role,status,begin,end,pageSize);
        return Result.success(userService.getUserList(username,phone,email,role,status,begin,end,page,pageSize));
    }
    //添加 用户
    @GetMapping("/user/addUser")
    public Result addUser(@RequestBody User user) {
        log.info("addUser:{}",user);
        userService.addUser(user);
        return Result.success();
    }
    //修改用户
    @PostMapping("/user/updateUser")
    public Result updateUser(@RequestBody User user) {
        log.info("updateUser:{}",user);
        userService.updateUser(user);
        return Result.success();
    }
    //删除用户
    @PostMapping("/user/deleteUser/{ids}")
    public Result deleteUser(@PathVariable List<Integer> ids) {
        log.info("deleteUser:{}",ids);
        userService.deleteUser(ids);
        return Result.success();
    }
}
