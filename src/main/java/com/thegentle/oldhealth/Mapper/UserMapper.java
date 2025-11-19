package com.thegentle.oldhealth.Mapper;

import com.thegentle.oldhealth.pojo.User.User;
import org.apache.ibatis.annotations.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 用户数据访问层接口
 * 提供用户相关的数据库操作方法
 */
@Mapper
public interface UserMapper {
    
    /**
     * 用户登录 - 根据用户名和密码查询用户信息
     * @param user 包含用户名和密码的用户对象
     * @return 用户信息对象
     */
    @Select("select id,username,name,role,gender,age,phone,email,health_record_number from users where username = #{username} and password = #{password}")
    User SelectByUsernameAndPassword(User user);
    
    /**
     * 用户注册 - 插入新用户信息
     * @param user 包含用户注册信息的对象
     */
    // Removed @Insert annotation to avoid duplication with XML mapping
    void register(User user);
    
    /**
     * 获取用户基本信息 - 根据用户名查询用户信息
     * @param user 包含用户ID的用户对象
     * @return 用户基本信息
     */
    @Select("select id,username,name,role,gender,age,phone,email,health_record_number from users where id = #{id}")
    User getUserBasicInfo(User user);
    
    /**
     * 根据ID更新健康档案号
     * @param user 包含用户ID和健康档案号的用户对象
     */
    @Update("UPDATE users SET health_record_number = #{healthRecordNumber}, updated_at = #{updatedAt} WHERE id = #{id}")
    void updateHealthRecordNumber(User user);

    /**
     * 根据用户名查询方法 - 判断用户名是否存在
     * @param username 用户名
     * @return 用户信息（如果存在）
     */
    @Select("SELECT id FROM users WHERE username = #{username}")
    User selectByUsername(String username);
    
    /**
     * 删除用户 - 批量删除用户
     * @param ids 用户ID列表
     */
    // Removed @Delete annotation to avoid duplication with XML mapping
    void deleteUser(List<Integer> ids);
    
    /**
     * 修改用户 - 更新用户信息
     * @param user 包含更新信息的用户对象
     */
    // Removed @Update annotation to avoid duplication with XML mapping
    void updateUser(User user);
    
    /**
     * 添加用户 - 插入新用户信息
     * @param user 包含用户信息的对象
     */
    // Removed @Insert annotation to avoid duplication with XML mapping
    void addUser(User user);
    
    /**
     * 分页获取用户列表 - 查询满足条件的用户
     * @param username 用户名（模糊查询）
     * @param phone 手机号（模糊查询）
     * @param email 邮箱（模糊查询）
     * @param role 角色
     * @param status 状态
     * @param begin 开始时间
     * @param end 结束时间
     * @return 用户列表
     */
    List<User> getUserList(String username, String phone, String email, Integer role, String status, LocalDateTime begin, LocalDateTime end);
}