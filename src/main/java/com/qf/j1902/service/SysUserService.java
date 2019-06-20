package com.qf.j1902.service;

import com.qf.j1902.pojo.SysUser;

public interface SysUserService {
    SysUser selectUserByUserName(String username);
}
