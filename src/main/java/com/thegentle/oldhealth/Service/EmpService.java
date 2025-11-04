package com.thegentle.oldhealth.Service;

import com.thegentle.oldhealth.pojo.Emp.Emp;
import com.thegentle.oldhealth.pojo.PageResponse;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface EmpService {
    // 查询所有员工
    public PageResponse<Emp> findAll(Integer page, Integer pageSize, String name, Integer gender, String departmentName, String position, LocalDateTime begin, LocalDateTime end);
    // 添加员工
    public void addEmp(Emp emp);
    // 删除员工
    public void deleteEmp(List<Integer> id);
    // 修改员工
    public void updateEmp(Emp emp);
    // 查询员工
    public Emp findEmpbyId(Integer id);

    //条件查询员工
}
