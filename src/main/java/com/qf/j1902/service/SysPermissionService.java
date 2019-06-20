package com.qf.j1902.service;

import com.qf.j1902.pojo.SysPermission;

import java.util.Set;

public interface SysPermissionService {
//    根据用户名查询用户权限集合
    public Set<SysPermission> selectPermsByUserName(String username);
}
