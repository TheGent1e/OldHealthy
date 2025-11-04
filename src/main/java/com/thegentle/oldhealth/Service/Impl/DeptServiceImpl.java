package com.thegentle.oldhealth.Service.Impl;

import com.thegentle.oldhealth.Mapper.DeptMapper;
import com.thegentle.oldhealth.Service.DeptService;
import com.thegentle.oldhealth.pojo.Department.Dept;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public void add(Dept dept) {

    }

    @Override
    public void update(Dept dept) {

    }

    @Override
    public void delete(List<Integer> ids) {

    }

    @Override
    public Dept findById(Integer id) {
        return null;
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
