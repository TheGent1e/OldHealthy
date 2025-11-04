package com.thegentle.oldhealth.pojo.Emp;

import lombok.Data;

/**
 * 员工更新请求
 */
@Data
public class EmpUpdateRequest {
    private Integer id;             // 员工ID
    private String name;            // 员工姓名
    private String position;        // 职位
    private Integer departmentId;   // 部门ID
    private String phone;           // 联系电话
    private String status;          // 状态（active/inactive）
}
