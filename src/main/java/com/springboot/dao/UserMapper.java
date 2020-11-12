package com.springboot.dao;

import com.springboot.domain.bo.UserPermissionBo;
import com.springboot.domain.bo.UserRoleBo;
import com.springboot.domain.pojo.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author qizenan
 * @date 2020/11/11 11:42
 */
@Mapper
public interface UserMapper {
    /**
     * 创建用户信息
     *
     * @param user 实体
     * @return int
     */
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert(" INSERT INTO users ( real_name ,name ,password ,age ,enabled_status ,created_at ,active) " +
            " VALUES ( #{realName} ,#{name} ,#{password} ,#{age} ,#{enabledStatus} ,NOW() ,1) ")
    int createUser(User user);

    /**
     * 更新用户信息
     *
     * @param user 实体
     * @return int
     */
    @Update(" UPDATE users SET real_name=#{realName},name=#{name},password=#{password},age=#{age},enabled_status=#{enabledStatus} WHERE id=#{id} ")
    int updateUser(User user);

    /**
     * 删除用户信息
     *
     * @param id 唯一标识id
     * @return int
     */
    @Update("UPDATE users SET active=0 WHERE id=#{id}")
    int deleteUser(@Param("id") long id);

    /**
     * 按 账号 查找用户信息
     *
     * @param name 账号
     * @return User
     */
    @Select("SELECT id,real_name,name,password,age,enabled_status,created_at,active FROM users WHERE name=#{name} LIMIT 1 ")
    User getUserByName(@Param("name") String name);

    /**
     * 按 用户名称 查找角色权限
     *
     * @param userName 用户名称
     * @return RolePermission
     */
    @Select("select a.id user_id,d.name permission_name from users a inner join user_roles b on a.id=b.user_id " +
            "inner join role_permissions c on b.role_id=c.role_id inner join permissions d on c.permission_id=d.id " +
            "where a.name=#{userName} ")
    List<UserPermissionBo> getUserPermissionBosByUserName(@Param("userName") String userName);

    /**
     * 按 用户账号 查找用户角色
     *
     * @param userName 用户账号
     * @return UserRole
     */
    @Select("select a.user_id,a.role_id,b.code role_code from user_roles a inner join roles b on a.role_id=b.id " +
            "inner join users c on a.user_id=c.id where c.name=#{userName} ")
    List<UserRoleBo> getUserRoleBosByUserName(@Param("userName") String userName);
}
