package com.thegentle.oldhealth.pojo.Emp;

import lombok.Data;


import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * 员工信息
 */
@Data
public class Emp {
    private Integer id;             // 员工ID
    private String name;            // 员工姓名
    private String position;        // 职位
    private Integer departmentId;   // 部门ID
    private String departmentName;  // 部门名称（用于展示）
    private String phone;           // 联系电话
    private String status;          // 状态（active/inactive）
    private LocalDateTime createdAt;         // 创建时间
    private LocalDateTime updatedAt;         // 更新时间
}
