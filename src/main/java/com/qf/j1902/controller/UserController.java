package com.qf.j1902.controller;

import com.qf.j1902.vo.UserVo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UserController {
//    显示登录视图
    @RequestMapping("/login")
    public String loginView(){
        return "login";
    }
//    登录处理
    @RequestMapping(value = "dealLogin",method = RequestMethod.POST)
    public String dealLogin(UserVo userVo){
        System.out.println(userVo);
        try {
//        获取subject
            Subject subject = SecurityUtils.getSubject();//从安全管理器获取主体对象
//            构建令牌
            UsernamePasswordToken token = new UsernamePasswordToken(userVo.getUsername(),userVo.getPassword());
//            登录
            subject.login(token);
            if (subject.isAuthenticated()){//判定是否登录成功
                return "main";//放回主页面
            }
        } catch (AuthenticationException e) {
            e.printStackTrace();
        }
        return "login";
    }
//    显示主功能视图
/*    @RequestMapping("/main")
    public String showMain(){
        return "main";
    }*/
//    显示one视图
    @RequiresPermissions(value = {"user_edit"})
    @RequestMapping("/one")
    public String showOneView(){
        return "one";
    }
//    显示two视图
    @RequiresPermissions(value = {"user_forbidden"})
    @RequestMapping("/two")
    public String showTwoView(){
        return "two";
    }
//    显示unauth视图
    @RequestMapping("/unauth")
    public String showUnauthView(){
        return "unauth";
    }
    @RequestMapping("/logout")
    public String logOut(){
        try {
            Subject subject = SecurityUtils.getSubject();
            subject.logout();//退出操作（清除session）
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:login";
    }
}
