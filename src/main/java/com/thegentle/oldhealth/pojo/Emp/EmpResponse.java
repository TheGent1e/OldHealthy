package com.thegentle.oldhealth.pojo.Emp;

import lombok.Data;
import java.util.Date;

/**
 * 员工响应
 */
@Data
public class EmpResponse {
    private Integer id;             // 员工ID
    private String name;            // 员工姓名
    private String position;        // 职位
    private Integer departmentId;   // 部门ID
    private String departmentName;  // 部门名称
    private String phone;           // 联系电话
    private String status;          // 状态
    private String statusText;      // 状态文本描述
    private Date createdAt;         // 创建时间
}