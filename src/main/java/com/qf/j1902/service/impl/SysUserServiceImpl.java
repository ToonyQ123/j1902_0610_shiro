package com.qf.j1902.service.impl;

import com.qf.j1902.mapper.SysUserMapper;
import com.qf.j1902.pojo.SysUser;
import com.qf.j1902.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SysUserServiceImpl implements SysUserService {
    @Autowired
    private SysUserMapper sysUserMapper;
    @Override
    public SysUser selectUserByUserName(String username) {
        return sysUserMapper.findUserByLoginName(username);
    }
}
