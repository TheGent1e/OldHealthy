package com.thegentle.oldhealth.Service.Impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.thegentle.oldhealth.Mapper.EmpMapper;
import com.thegentle.oldhealth.Service.EmpService;
import com.thegentle.oldhealth.pojo.Emp.Emp;
import com.thegentle.oldhealth.pojo.PageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class EmpServiceImpl implements EmpService {
    @Autowired
    private EmpMapper empMapper;

    @Override
    public PageResponse<Emp> findAll(Integer page, Integer pageSize, String name, Integer gender, String departmentName, String position, LocalDateTime begin, LocalDateTime end) { // 查询所有员工
        //1、设置papgehelper
        PageHelper.startPage(page,pageSize);
        //2、查询  员工姓名、性别、职位、部门名称、创建时间、更新时间、手机号、状态
        List<Emp> list=empMapper.findAll(name,gender,position,departmentName,begin,end);
        Page<Emp> p=(Page<Emp>)  list;
        return new PageResponse(p.getResult(),p.getTotal());
    }

    @Override
    public void addEmp(Emp emp) {// 添加员工

    }

    @Override
    public void deleteEmp(List<Integer> id) {// 删除员工

    }

    @Override
    public void updateEmp(Emp emp) { // 修改员工

    }

    @Override
    public Emp findEmpbyId(Integer id) { // 根据id查询员工
        return null;
    }
}
