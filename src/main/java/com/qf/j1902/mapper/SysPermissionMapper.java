package com.qf.j1902.mapper;

import com.qf.j1902.pojo.SysPermission;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 提供对外访问用户权限的接口
 */
@Mapper
public interface SysPermissionMapper {
//    根据用户名查询用户权限
    public List<SysPermission> findPermsByLoginName(@Param("loginName")String loginName);
}
