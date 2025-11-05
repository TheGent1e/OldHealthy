package com.thegentle.oldhealth.Service.Impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.thegentle.oldhealth.Mapper.EmpMapper;
import com.thegentle.oldhealth.Service.EmpService;
import com.thegentle.oldhealth.pojo.Emp.Emp;
import com.thegentle.oldhealth.pojo.PageResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
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
        //1、设置创建时间、更新时间
        emp.setCreatedAt(LocalDateTime.now());
        emp.setUpdatedAt(LocalDateTime.now());
        empMapper.addEmp(emp);
    }

    @Override
    public void deleteEmp(List<Integer> ids) {// 删除员工
        empMapper.deleteEmp(ids);
        log.info("删除员工：{}", ids);

    }

    @Override
    public void updateEmp(Emp emp) { // 修改员工
        emp.setUpdatedAt(LocalDateTime.now());
        empMapper.updateEmp(emp);
        log.info("修改员工：{}", emp);

    }

    @Override
    public Emp findEmpbyId(Integer id) { // 根据id查询员工
        //1、查询员工
        return empMapper.findEmpbyId(id);
    }

    @Override
    public Map<String, Integer> dynamic(String name) {
        //调用mapper
        List<Emp> list= empMapper.dynamic(name);
        //转换成map
        Map<String,Integer> map=list.stream().collect(Collectors.toMap(Emp::getName, Emp::getId));
//        // 按姓名分组，Lambda 写完整逻辑
//        Map<String, Integer> map = list.stream()
//                .collect(Collectors.groupingBy(
//                        (Emp emp) -> emp.getName(),  // 完整 Lambda：从 emp 里拿 name
//                        Collectors.summingInt((Emp emp) -> emp.getId())  // 完整 Lambda：从 emp 里拿 id
//                ));
        return map;
    }
}
