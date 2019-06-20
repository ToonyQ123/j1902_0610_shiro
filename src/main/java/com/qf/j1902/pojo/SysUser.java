package com.qf.j1902.pojo;

import lombok.Data;

import java.util.Date;

/**
 * 封装用户信息
 */
@Data
public class SysUser {
    private  int userid;//用户id
  //  private String username;//用户实名
    private String password;//
    private String loginName;//登录名
    private Date createTime;
    private String realname;
    private int state; //状态
}
