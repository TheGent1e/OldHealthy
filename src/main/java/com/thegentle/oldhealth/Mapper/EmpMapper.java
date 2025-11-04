package com.thegentle.oldhealth.Mapper;

import com.thegentle.oldhealth.pojo.Emp.Emp;
import com.thegentle.oldhealth.pojo.PageResponse;
import org.apache.ibatis.annotations.Mapper;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface EmpMapper {

    // 条件查询所有员工
    public List<Emp> findAll(String name, Integer gender, String departmentName, String position, LocalDateTime begin, LocalDateTime end);

    // 删除员工
    void deleteEmp(List<Integer> ids);

    // 添加员工
    void addEmp(Emp emp);

    // 修改员工
    void updateEmp(Emp emp);

    Emp findEmpbyId(Integer id);




}
