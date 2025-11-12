package com.thegentle.oldhealth.Mapper;

import com.thegentle.oldhealth.pojo.User.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface UserMapper {
    // 1.用户登录
    @Select("select id,username,name,role,gender,age,phone,email,health_record_number from users where username = #{username} and password = #{password}")
    User SelectByUsernameAndPassword(User user);
    // 2.用户注册
    void register(User user);
    //3.获取用户基本信息
    @Select("select id,username,name,role from users where username = #{id}")
    User getUserBasicInfo(User user);
    //4. 新增：根据ID更新健康档案号
    @Update("UPDATE users SET health_record_number = #{healthRecordNumber}, updated_at = #{updatedAt} WHERE id = #{id}")
    void updateHealthRecordNumber(User user);

    //5.原有的根据用户名查询方法（注意：原代码用了SelectByUsernameAndPassword，但判断用户名是否存在只需查用户名，建议优化）
    @Select("SELECT id FROM users WHERE username = #{username}")
    User selectByUsername(String username);
    //6.删除用户
    void deleteUser(List<Integer> ids);
    //7.修改用户
    void updateUser(User user);
    //8.添加用户
    void addUser(User user);
    //9.分页获取用户列表
    List<User> getUserList(String username, String phone, String email, Integer role, String status, LocalDateTime begin, LocalDateTime end);
}
