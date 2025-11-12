package com.thegentle.oldhealth.Service;

import com.thegentle.oldhealth.pojo.PageResponse;
import com.thegentle.oldhealth.pojo.User.User;
import com.thegentle.oldhealth.pojo.User.UserBasicInfo;

import java.time.LocalDateTime;
import java.util.List;

public interface UserService {
    //登录
    UserBasicInfo login(User user);
    //注册
    Integer register(User user);
    //获取用户基本信息
    User getUserBasicInfo(User user);
    //添加 用户
    Integer addUser(User user);
    //修改用户信息
    void updateUser(User user);
    //删除用户
    void deleteUser(List<Integer> ids);
    //分页获取用户信息
    PageResponse<User> getUserList(String username, String phone, String email, Integer role, String status, LocalDateTime begin, LocalDateTime end,Integer page,Integer pageSize);

}
