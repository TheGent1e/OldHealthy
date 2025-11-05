package com.thegentle.oldhealth.Service.Impl;

import com.thegentle.oldhealth.Mapper.DeptMapper;
import com.thegentle.oldhealth.Service.DeptService;
import com.thegentle.oldhealth.pojo.Department.Dept;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DeptServiceImpl implements DeptService {
    @Autowired
    private DeptMapper deptMapper;;
    @Override
    public List<Dept> list() {
        //1、查询部门列表
       return deptMapper.list();
    }

    @Override
    public String add(Dept dept) {
        // 检查部门名称是否已存在（排除当前部门）
        Dept existingDept = deptMapper.findByName(dept.getDepartmentName());
        if (existingDept != null && !existingDept.getId().equals(dept.getId())) {
            return "部门名称已经存在";
        }
        //设置创建时间、更新时间
        dept.setCreatedAt(LocalDateTime.now());
        dept.setUpdatedAt(LocalDateTime.now());
//        //设置部门id
//        dept.setId(deptMapper.count() + 1);
        deptMapper.add(dept);
        return null;
    }

    @Override
    public String update(Dept dept) {
        // 检查部门名称是否已存在（排除当前部门）
        Dept existingDept = deptMapper.findByName(dept.getDepartmentName());
        if (existingDept != null && !existingDept.getId().equals(dept.getId())) {
            return "部门名称已经存在";
        }
        //修改时间
        dept.setUpdatedAt(LocalDateTime.now());
        deptMapper.update(dept);
        return null;
    }

    @Override
    public void delete(List<Integer> ids) {
        deptMapper.delete(ids);
    }

    @Override
    public Dept findById(Integer id) {
        return deptMapper.findById(id);
    }

    @Override // 根据部门名称查询部门
    public Map<Integer,String> findDeptIdAndName() {
        //1、查询部门列表
        List<Dept> list=deptMapper.findDeptIdAndName();
        //2、分装成 Map<Integer,String>
        Map<Integer,String> map=new HashMap<>();
        for (Dept dept:list){
            map.put(dept.getId(),dept.getDepartmentName());
        }
        //3、返回
        return map;
    }
}
