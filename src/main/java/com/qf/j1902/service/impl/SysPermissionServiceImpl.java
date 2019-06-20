package com.qf.j1902.service.impl;

import com.qf.j1902.mapper.SysPermissionMapper;
import com.qf.j1902.pojo.SysPermission;
import com.qf.j1902.service.SysPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
@Service
public class SysPermissionServiceImpl implements SysPermissionService {
    @Autowired
    private SysPermissionMapper sysPermissionMapper;
    @Override
    public Set<SysPermission> selectPermsByUserName(String username) {
        Set<SysPermission> perms = new HashSet<>();
        List<SysPermission> sysPermissionList = sysPermissionMapper.findPermsByLoginName(username);
        for (SysPermission sysPermission : sysPermissionList){
            perms.add(sysPermission);
        }

        return perms;
    }
}
