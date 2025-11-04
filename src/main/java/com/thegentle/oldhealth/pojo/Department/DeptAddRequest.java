package com.thegentle.oldhealth.pojo.Department;

import lombok.Data;

/**
 * 部门添加请求
 */
@Data
public class DeptAddRequest {
    private String departmentName;  // 部门名称
    private String description;     // 部门描述
    private Integer managerId;      // 部门经理ID（可选）
}