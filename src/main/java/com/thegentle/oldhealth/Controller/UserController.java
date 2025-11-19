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

/**
 * 用户控制器
 * 提供用户管理相关的API接口
 */
@Slf4j
@RestController
@RequestMapping("/")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 用户登录接口
     * 
     * @param user 包含用户名和密码的用户对象
     * @return 登录结果，成功返回用户基本信息和token，失败返回错误信息
     */
    @PostMapping("/login")
    public Result login(@RequestBody User user) {
        log.info("用户登录: {}", user);
        // 调用Service层进行登录验证
        UserBasicInfo userBasicInfo = userService.login(user);
        if (userBasicInfo != null) {
            return Result.success(userBasicInfo);
        }
        return Result.error("用户名或密码错误");
    }

    /**
     * 用户注册接口
     * 
     * @param user 包含用户注册信息的对象
     * @return 注册结果
     */
    @PostMapping("/register")
    public Result register(@RequestBody User user) {
        log.info("用户注册: {}", user);
        // 调用Service层进行注册处理
        return userService.register(user) == 1
                ? Result.success("注册成功")
                : Result.error("用户已存在");
    }

    /**
     * 获取用户基本信息
     * 
     * @param id 用户ID
     * @return 用户基本信息
     */
    @GetMapping("/user/getUserBasicInfo/{id}")
    public Result getUserBasicInfo(@PathVariable Integer id) {
        log.info("获取用户基本信息: 用户ID = {}", id);
        User user = new User();
        user.setId(id);
        return Result.success(userService.getUserBasicInfo(user));
    }

    /**
     * 分页获取用户列表
     * 
     * @param username 用户名（可选，模糊查询）
     * @param phone    手机号（可选，模糊查询）
     * @param email    邮箱（可选，模糊查询）
     * @param role     角色（可选）
     * @param status   状态（可选）
     * @param begin    开始时间（可选）
     * @param end      结束时间（可选）
     * @param page     当前页码（默认1）
     * @param pageSize 每页条数（默认10）
     * @return 用户列表和总数
     */
    @GetMapping("/user/getUserList")
    public Result getUserList(
            @RequestParam(required = false) String username,
            @RequestParam(required = false) String phone,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) Integer role,
            @RequestParam(required = false) String status,
            @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDateTime begin,
            @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDateTime end,
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer pageSize) {

        log.info("分页获取用户列表: 页码={}, 每页条数={}, 筛选条件=[用户名: {}, 手机号: {}, 邮箱: {}, 角色: {}, 状态: {}, 开始时间: {}, 结束时间: {}]",
                page, pageSize, username, phone, email, role, status, begin, end);

        return Result.success(
                userService.getUserList(username, phone, email, role, status, begin, end, page, pageSize));
    }

    /**
     * 添加用户
     * 
     * @param user 用户信息对象
     * @return 添加结果
     */
    @PostMapping("/user/addUser")
    public Result addUser(@RequestBody User user) {
        log.info("添加用户: {}", user);
        return userService.addUser(user) == 1 ? Result.success() : Result.error("添加失败");
    }

    /**
     * 修改用户
     * 
     * @param user 包含更新信息的用户对象
     * @return 更新结果
     */
    @PostMapping("/user/updateUser")
    public Result updateUser(@RequestBody User user) {
        log.info("修改用户: {}", user);
        userService.updateUser(user);
        return Result.success();
    }

    /**
     * 删除用户（批量）
     * 
     * @param ids 用户ID列表
     * @return 删除结果
     */
    @PostMapping("/user/deleteUser/{ids}")
    public Result deleteUser(@PathVariable List<Integer> ids) {
        log.info("删除用户: 用户ID列表 = {}", ids);
        userService.deleteUser(ids);
        return Result.success();
    }
}
