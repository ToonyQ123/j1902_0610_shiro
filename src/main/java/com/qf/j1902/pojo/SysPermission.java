package com.qf.j1902.pojo;

import lombok.Data;

/**
 * 对权限信息和资源信息的封装
 */
@Data
public class SysPermission {
    private int permId; //权限ID
    private String permName;//权限名称
    private String permUrl;//权限操作地址（路径）
    private String menuName;//菜单名
    private String menuLevel;//菜单级别（11：一级；12：二级。。。）
    private String menuCode;//菜单编码（每级两位数字）
    private int ifValid; //是否有效
    private String parentCode;//父编码
}
