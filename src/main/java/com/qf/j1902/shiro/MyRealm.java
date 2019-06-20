package com.qf.j1902.shiro;

import com.qf.j1902.pojo.SysPermission;
import com.qf.j1902.pojo.SysUser;
import com.qf.j1902.service.SysPermissionService;
import com.qf.j1902.service.SysUserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Set;

public class MyRealm extends AuthorizingRealm {
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private SysPermissionService permissionService;
//    授权（权限验证）
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        Object principal = principalCollection.getPrimaryPrincipal();
        String username =(String)principal;
        Set<SysPermission> permissions = permissionService.selectPermsByUserName(username);
        Set<String> perms = new HashSet<>();
        if (permissions!=null){
            for (SysPermission perm : permissions){
                perms.add(perm.getPermName());//获取权限名称组成权限集合
            }
        }
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();

        authorizationInfo.setStringPermissions(perms);//设置权限
        return authorizationInfo;
    }
//    认证（处理登录）
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
//        获取用户名
        String principal = (String) authenticationToken.getPrincipal();
//        根据用户名获取用户信息
        SysUser sysUser = sysUserService.selectUserByUserName(principal);
        SimpleAuthenticationInfo authenticationInfo = null;
        if (sysUser!=null){
            authenticationInfo = new SimpleAuthenticationInfo(principal,sysUser.getPassword(),null,this.getName());
        }
        return authenticationInfo;
    }
}
