package com.qf.j1902.mapper;

import com.qf.j1902.pojo.SysUser;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@Configuration("com.qf.j1902.mapper")
public class TestSysUserMapper {
    @Resource
    private SysUserMapper userMapper;
    @Test
    public void test1(){
        SysUser user = userMapper.findUserByLoginName("admin");
        System.out.println(user);
    }
    @Test
    public void test2(){
        Md5Hash md5Hash = new Md5Hash("admin",null, 1024);
        String md5str = md5Hash.toString();
        System.out.println(md5str);
    }
}
