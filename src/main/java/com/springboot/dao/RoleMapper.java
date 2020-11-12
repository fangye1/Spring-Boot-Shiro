package com.springboot.dao;

import com.springboot.domain.pojo.Role;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author qizenan
 * @date 2020/11/11 17:47
 */
public interface RoleMapper {
    /**
     * 创建角色
     *
     * @param role 实体
     * @return int
     */
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert(" INSERT INTO roles ( name ,code ,created_at ,active) " +
            " VALUES ( #{name} ,#{code} ,NOW() ,1) ")
    int createRole(Role role);

    /**
     * 更新角色
     *
     * @param role 实体
     * @return int
     */
    @Update(" UPDATE roles SET name=#{name},code=#{code} WHERE id=#{id} ")
    int updateRole(Role role);

    /**
     * 删除角色
     *
     * @param id 唯一标识id
     * @return int
     */
    @Update("UPDATE roles SET active=0 WHERE id=#{id}")
    int deleteRole(@Param("id") long id);

    /**
     * 按id查找角色
     *
     * @param id 唯一标识id
     * @return Role
     */
    @Select("SELECT id,name,code,created_at,active FROM roles WHERE id=#{id} LIMIT 1 ")
    Role getRoleById(@Param("id") long id);

    /**
     * 查找所有角色
     *
     * @return Role 集合
     */
    @Select("SELECT id,name,code,created_at,active FROM roles WHERE active=1 ORDER BY id DESC ")
    List<Role> getRoleList();
}
