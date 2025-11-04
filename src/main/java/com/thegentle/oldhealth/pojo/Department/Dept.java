package com.thegentle.oldhealth.pojo.Department;

import lombok.Data;
import java.util.Date;

/**
 * 部门信息
 */
@Data
public class Dept {
    private Integer id;             // 部门ID
    private String departmentName;  // 部门名称
    private String description;     // 部门描述
    private Integer managerId;      // 部门经理ID
    private String managerName;     // 部门经理姓名（用于展示）
    private Integer employeeCount; // 新增：部门人数（在职）
    private Date createdAt;         // 创建时间
    private Date updatedAt;         // 更新时间
}