package com.thegentle.oldhealth.Service.Impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.thegentle.oldhealth.Mapper.UserMapper;
import com.thegentle.oldhealth.Service.UserService;
import com.thegentle.oldhealth.pojo.PageResponse;
import com.thegentle.oldhealth.pojo.User.UserBasicInfo;
import com.thegentle.oldhealth.pojo.User.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.thegentle.oldhealth.utils.JWTutils;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    /**
     * 用户登录
     * @param user
     * @return
     */
    @Override
    public UserBasicInfo login(User user) {
        // 调用Mapper层
        User TempUser= userMapper.SelectByUsernameAndPassword(user);
        //生成 token
        Map<String,Object> date=new HashMap<>();
        date.put("username",TempUser.getUsername());
        date.put("password",TempUser.getPassword());
        String token= JWTutils.generateJwt(date);
        //判断是否有这个账户
        if(TempUser!=null){
            //将信息返回
            log.info("员工登录成功{}",user);
            return new UserBasicInfo(TempUser.getId(),
                    TempUser.getUsername(),
                    TempUser.getName(),
                    TempUser.getRole(),
                    TempUser.getGender(),
                    TempUser.getAge(),
                    TempUser.getPhone(),
                    TempUser.getEmail(),
                    TempUser.getHealthRecordNumber(),
                    token);
        }
        return null;
    }


    @Override
    public Integer register(User user) {
        //掉用Mapper层
        ///1.判断用户名是否已存在
        User TempUser= userMapper.SelectByUsernameAndPassword(user);
        if(TempUser==null){
            user.setStatus("active");
            user.setRole(1);
            //1.设置创建时间
            user.setCreatedAt(LocalDateTime.now());
            user.setUpdatedAt(LocalDateTime.now());
            userMapper.register(user);
            return 1;
        }
        return 0;
    }
    //获取用户基本信息
    @Override
    public User getUserBasicInfo(User user) {
        log.info("getUserBasicInfo:{}",user);
        //调用Mapper层
        User TempUser= userMapper.getUserBasicInfo(user);
        if(TempUser!=null){
            return TempUser;
        }
        return null;
    }
    //添加用户
    @Override
    public User addUser(User user) {
        log.info("addUser:{}",user);
        userMapper.addUser(user);
        return null;
    }
    //修改用户
    @Override
    public void updateUser(User user) {
        log.info("updateUser:{}",user);
        userMapper.updateUser(user);

    }
    //删除用户
    @Override
    public void deleteUser(User user) {
        log.info("deleteUser:{}",user);
        userMapper.deleteUser(user);
        return;

    }
    //分页获取用户列表
    @Override
    public PageResponse<User> getUserList(String username, String phone, String email, Integer role, String status, LocalDateTime begin, LocalDateTime end, Integer page, Integer pageSize) {

        //1、设置pagerHelper
        PageHelper.startPage(page,pageSize);
        List<User> users=userMapper.getUserList(username,phone,email,role,status,begin,end);
        //2、获取分页数据
        Page<User> p=(Page<User>) users;
        //3、返回
        return new PageResponse<>(p.getResult(),p.getTotal());
    }
}
