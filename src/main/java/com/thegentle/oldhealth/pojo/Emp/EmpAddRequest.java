package com.thegentle.oldhealth.pojo.Emp;

import lombok.Data;

/**
 * 员工添加请求
 */
@Data
public class EmpAddRequest {
    private String name;            // 员工姓名
    private String position;        // 职位
    private Integer departmentId;   // 部门ID
    private String phone;           // 联系电话
}