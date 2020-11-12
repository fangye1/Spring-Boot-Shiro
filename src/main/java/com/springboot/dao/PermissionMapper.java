package com.springboot.dao;

import com.springboot.domain.pojo.Permission;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author qizenan
 * @date 2020/11/11 16:56
 */
public interface PermissionMapper {
    /**
     * 创建权限
     *
     * @param permission 实体
     * @return int
     */
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert(" INSERT INTO permissions ( url ,name ,created_at ,active) " +
            " VALUES ( #{url} ,#{name} ,NOW() ,1) ")
    int createPermission(Permission permission);

    /**
     * 更新权限
     *
     * @param permission 实体
     * @return int
     */
    @Update(" UPDATE permissions SET url=#{url},name=#{name} WHERE id=#{id} ")
    int updatePermission(Permission permission);

    /**
     * 删除权限
     *
     * @param id 唯一标识id
     * @return int
     */
    @Update("UPDATE permissions SET active=0 WHERE id=#{id}")
    int deletePermission(@Param("id") long id);

    /**
     * 按id查找权限
     *
     * @param id 唯一标识id
     * @return Permission
     */
    @Select("SELECT id,url,name,created_at,active FROM permissions WHERE id=#{id} LIMIT 1 ")
    Permission getPermissionById(@Param("id") long id);

    /**
     * 查找所有权限
     *
     * @return Permission 集合
     */
    @Select("SELECT id,url,name,created_at,active FROM permissions WHERE active=1 ORDER BY id DESC ")
    List<Permission> getPermissionList();
}
