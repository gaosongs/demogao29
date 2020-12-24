package com.bjsxt.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import com.bjsxt.realm.MyRealm;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.spring.web.config.DefaultShiroFilterChainDefinition;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ShiroConfig {
    //声明自定义realm属性
    @Autowired
    private MyRealm myRealm;

    //声明bean方法: 自定义配置SecurityManager对象
    @Bean
    public DefaultWebSecurityManager defaultWebSecurityManager(){
        //创建DefaultWebSecurityManager对象
        DefaultWebSecurityManager  defaultWebSecurityManager=new DefaultWebSecurityManager();
        //设置自定义的realm
        defaultWebSecurityManager.setRealm(myRealm);
        //返回
        return defaultWebSecurityManager;
    }
    //配置shiro的过滤器放行
    @Bean
    public DefaultShiroFilterChainDefinition shiroFilterChainDefinition(){
        //创建shiro的过滤器管理对象
        DefaultShiroFilterChainDefinition definition=new DefaultShiroFilterChainDefinition();
        //设置拦截和放行
        definition.addPathDefinition("/userController/login", "anon");
        definition.addPathDefinition("/userController/userLogin", "anon");
        definition.addPathDefinition("/js/**", "anon");

        definition.addPathDefinition("/**", "authc");
        return definition;

    }

}