package com.thegentle.oldhealth.Mapper;

import com.thegentle.oldhealth.pojo.Department.Dept;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface DeptMapper {

    // 查询部门列表
    List<Dept> list();
    //只查询部门id和部门名称
    List<Dept> findDeptIdAndName();
    //添加部门
    void add(Dept dept);
    //修改部门
    void update(Dept dept);
    //删除部门
    void delete(List<Integer> ids);
    //根据id查询部门
    Dept findById(Integer id);

    @Select("SELECT id FROM departments WHERE department_name = #{departDepartName}")
    Dept findByName(String departDepartName);


}
