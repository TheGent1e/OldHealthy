package com.thegentle.oldhealth.Mapper;

import com.thegentle.oldhealth.pojo.User.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface UserMapper {
    // 用户登录
    @Select("select id,username,name,role,gender,age,phone,email,health_record_number from users where username = #{username} and password = #{password}")
    User SelectByUsernameAndPassword(User user);

    void register(User user);
    //获取用户基本信息
    @Select("select id,username,name,role from users where username = #{id}")
    User getUserBasicInfo(User user);

    void deleteUser(User user);

    void updateUser(User user);

    void addUser(User user);

    List<User> getUserList(String username, String phone, String email, Integer role, String status, LocalDateTime begin, LocalDateTime end);
}
