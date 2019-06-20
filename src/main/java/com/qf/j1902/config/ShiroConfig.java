package com.qf.j1902.config;

import com.qf.j1902.shiro.MyRealm;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * 配置shiro相关组件
 */
@Configuration
public class ShiroConfig {
//    创建自定义的realm对象
    @Bean(name = "myRealm")
    public MyRealm getRealm(@Qualifier("hashedCredentialsMatcher") HashedCredentialsMatcher credentialsMatcher){
        MyRealm myRealm = new MyRealm();
        myRealm.setCredentialsMatcher(credentialsMatcher);//为realm配置凭证匹配器
        return myRealm;
    }
//    创建安全管理器
    @Bean(name = "defaultWebSecurityManager")
    public DefaultWebSecurityManager defaultWebSecurityManager(@Qualifier("myRealm") MyRealm myRealm){
//        创建securityManager对象
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
//        设置自定义的Realm
        defaultWebSecurityManager.setRealm(myRealm);
        return defaultWebSecurityManager;
    }
//    创建shrio权限过滤器
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(@Qualifier("defaultWebSecurityManager") DefaultWebSecurityManager securityManager){
        ShiroFilterFactoryBean filterFactoryBean = new ShiroFilterFactoryBean();
        filterFactoryBean.setSecurityManager(securityManager);
        filterFactoryBean.setLoginUrl("/login");
        filterFactoryBean.setUnauthorizedUrl("/unauth");
        Map<String,String> map = new HashMap<>();
        map.put("/main","authc");//登录后才可访问
/*        map.put("/one","perms[user_edit]");
        map.put("/two","perms[user_forbidden]");*/
        filterFactoryBean.setFilterChainDefinitionMap(map);//设置拦截权限
        return filterFactoryBean;
    }
//    开启注解验证模式
    @Bean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator(){
        DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        advisorAutoProxyCreator.setProxyTargetClass(true);
        return advisorAutoProxyCreator;
    }
//    开启AOP注解支持
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(DefaultWebSecurityManager defaultWebSecurityManager){
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(defaultWebSecurityManager);
        return authorizationAttributeSourceAdvisor;
    }
//    添加凭证匹配器
    @Bean(name = "hashedCredentialsMatcher")
    public HashedCredentialsMatcher hashedCredentialsMatcher(){
//        创建凭证匹配器
        HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher();
        credentialsMatcher.setHashAlgorithmName("MD5");//设置加密算法，还可以是SHA1
        credentialsMatcher.setHashIterations(1024);//设置密码算法的HASH频率
        credentialsMatcher.setStoredCredentialsHexEncoded(true);//十六进制字符串
        return credentialsMatcher;
    }
}
