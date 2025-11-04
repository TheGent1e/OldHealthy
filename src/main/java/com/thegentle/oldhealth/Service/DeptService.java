package com.thegentle.oldhealth.Service;

import com.thegentle.oldhealth.pojo.Department.Dept;

import java.util.List;
import java.util.Map;

// 部门服务
public interface DeptService {

    // 查询部门列表
    public List<Dept> list();
    // 添加部门
    public void add(Dept dept);
    // 修改部门
    public void update(Dept dept);
    // 删除部门
    public void delete(List<Integer> ids);
    // 根据id查询部门
    public Dept findById(Integer id);

    //中查询部门id 和name
    public Map<Integer,String> findDeptIdAndName();
}
