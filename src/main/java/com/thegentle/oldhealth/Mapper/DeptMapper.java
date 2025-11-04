package com.thegentle.oldhealth.Mapper;

import com.thegentle.oldhealth.pojo.Department.Dept;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DeptMapper {

    // 查询部门列表
    List<Dept> list();
    //只查询部门id和部门名称
    List<Dept> findDeptIdAndName();
}
