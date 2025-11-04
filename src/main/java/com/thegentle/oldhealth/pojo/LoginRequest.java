package com.thegentle.oldhealth.pojo;

import lombok.Data;

/**
 * 用户登录请求
 */
@Data
public class LoginRequest {
    private String username;    // 用户名/账号
    private String password;    // 密码
    private Integer role;        // 角色（admin/user） 0表示admin 1表示 user
}