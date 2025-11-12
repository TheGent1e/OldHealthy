package com.thegentle.oldhealth.pojo.User;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * 用户创建请求
 */
@Data
public class User {
    private Integer id; //ID,主键
    private String username;            // 用户名
    private String password;            // 密码
    private String name;                // 姓名
    private String gender;              // 性别
    private Integer age;                // 年龄
    private String phone;               // 手机号
    private String email;               // 邮箱
    private String healthRecordNumber;  // 健康档案号
    private Integer role;               // 角色（默认1，表示user）
    private String status;              // 状态（默认active）
    private LocalDateTime createdAt;         // 创建时间
    private LocalDateTime updatedAt;         // 更新时间
    private LocalDateTime registerTime;     // 注册时间
    private LocalDateTime lastLoginTime;    // 最后登录时间
}