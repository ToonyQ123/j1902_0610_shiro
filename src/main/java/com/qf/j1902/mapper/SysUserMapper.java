package com.qf.j1902.mapper;

import com.qf.j1902.pojo.SysUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 提供用户信息对外访问接口
 */
@Mapper
public interface SysUserMapper {
//    根据登录名查询用户信息
    public SysUser findUserByLoginName(@Param("loginName") String loginName);
}
