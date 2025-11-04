package com.thegentle.oldhealth.Controller;

import com.thegentle.oldhealth.Service.UserService;
import com.thegentle.oldhealth.pojo.Result;
import com.thegentle.oldhealth.pojo.User.User;
import com.thegentle.oldhealth.pojo.User.UserBasicInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


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
    @PostMapping("/register")
    public Result register(@RequestBody User user) {
        log.info("register:{}",user);
        //

        return Result.success("注册成功");
    }

    //获取用户基本信息
    @PostMapping("/getUserBasicInfo")
    public Result getUserBasicInfo(@RequestBody User user) {
        log.info("getUserBasicInfo:{}",user);
        return Result.success(userService.getUserBasicInfo(user));
    }
}
