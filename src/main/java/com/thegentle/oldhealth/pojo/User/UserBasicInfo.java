package com.thegentle.oldhealth.pojo.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户基本信息
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserBasicInfo {
    private Integer id;          // 用户ID
    private String username;
    private String name;
    private Integer role;               // 角色（默认1，表示user）
    private String gender;              // 性别
    private Integer age;                // 年龄
    private String phone;               // 手机号
    private String email;               // 邮箱
    private String healthRecordNumber;  // 健康档案号
    //token
    private String token;  // token
}