package com.springboot.dao;

import com.springboot.domain.bo.UserRoleBo;
import com.springboot.domain.pojo.UserRole;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author qizenan
 * @date 2020/11/11 16:21
 */
public interface UserRoleMapper {
    /**
     * 创建用户角色
     *
     * @param userRole 实体
     * @return int
     */
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert(" INSERT INTO user_roles ( user_id ,role_id ,created_at ,active) " +
            " VALUES ( #{userId} ,#{roleId} ,NOW() ,1) ")
    int createUserRole(UserRole userRole);

    /**
     * 更新用户角色
     *
     * @param userRole 实体
     * @return int
     */
    @Update(" UPDATE user_roles SET user_id=#{userId},role_id=#{roleId} WHERE id=#{id} ")
    int updateUserRole(UserRole userRole);

    /**
     * 删除用户角色
     *
     * @param id 唯一标识id
     * @return int
     */
    @Update("UPDATE user_roles SET active=0 WHERE id=#{id}")
    int deleteUserRole(@Param("id") long id);

    /**
     * 查找所有用户角色
     *
     * @return UserRole 集合
     */
    @Select("SELECT id,user_id,role_id,created_at,active FROM user_roles WHERE active=1 ORDER BY id DESC ")
    List<UserRole> getUserRoleList();
}
