package com.springboot.dao;

import com.springboot.domain.bo.UserPermissionBo;
import com.springboot.domain.pojo.RolePermission;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author qizenan
 * @date 2020/11/11 16:47
 */
public interface RolePermissionMapper {
    /**
     * 创建角色权限
     *
     * @param rolePermission 实体
     * @return int
     */
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert(" INSERT INTO role_permissions ( role_id ,permission_id ,created_at ,active) " +
            " VALUES ( #{roleId} ,#{permissionId} ,NOW() ,1) ")
    int createRolePermission(RolePermission rolePermission);

    /**
     * 更新角色权限
     *
     * @param rolePermission 实体
     * @return int
     */
    @Update(" UPDATE role_permissions SET role_id=#{roleId},permission_id=#{permissionId} WHERE id=#{id} ")
    int updateRolePermission(RolePermission rolePermission);

    /**
     * 删除角色权限
     *
     * @param id 唯一标识id
     * @return int
     */
    @Update("UPDATE role_permissions SET active=0 WHERE id=#{id}")
    int deleteRolePermission(@Param("id") long id);

    /**
     * 查找所有角色权限
     *
     * @return RolePermission 集合
     */
    @Select("SELECT id,role_id,permission_id,created_at,active FROM role_permissions WHERE active=1 ORDER BY id DESC ")
    List<RolePermission> getRolePermissionList();
}
