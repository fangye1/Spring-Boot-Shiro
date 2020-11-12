package com.springboot.shiro;

import com.springboot.dao.UserMapper;
import com.springboot.domain.bo.UserPermissionBo;
import com.springboot.domain.bo.UserRoleBo;
import com.springboot.domain.pojo.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ShiroRealm extends AuthorizingRealm {
    @Resource
    private UserMapper userMapper;

    /**
     * 获取用户角色和权限
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principal) {
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        String userName = user.getName();

        System.out.println("用户" + userName + "获取权限-----ShiroRealm.doGetAuthorizationInfo");
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();

        // 获取用户角色集
        List<UserRoleBo> roleList = userMapper.getUserRoleBosByUserName(userName);
        Set<String> roleSet = new HashSet<String>();
        for (UserRoleBo userRoleBo : roleList) {
            roleSet.add(userRoleBo.getRoleCode());
        }
        simpleAuthorizationInfo.setRoles(roleSet);

        // 获取用户权限集
        List<UserPermissionBo> permissionList = userMapper.getUserPermissionBosByUserName(userName);
        Set<String> permissionSet = new HashSet<String>();
        for (UserPermissionBo permission : permissionList) {
            permissionSet.add(permission.getPermissionName());
        }
        simpleAuthorizationInfo.setStringPermissions(permissionSet);
        return simpleAuthorizationInfo;
    }

    /**
     * 登录认证
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String userName = (String) token.getPrincipal();
        String password = new String((char[]) token.getCredentials());

        System.out.println("用户" + userName + "认证-----ShiroRealm.doGetAuthenticationInfo");
        User user = userMapper.getUserByName(userName);

        if (user == null) {
            throw new UnknownAccountException("用户名或密码错误！");
        }
        if (!password.equals(user.getPassword())) {
            throw new IncorrectCredentialsException("用户名或密码错误！");
        }
        if (user.getEnabledStatus() == 1) {
            throw new LockedAccountException("账号已被锁定,请联系管理员！");
        }
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, password, getName());
        return info;
    }

}
