package com.bjsxt.realm;

import com.bjsxt.pojo.User;
import com.bjsxt.service.UserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MyRealm extends AuthorizingRealm {
    //声明业务层属性
    @Autowired
    private UserService userService;
    /**
     * 自定义的授权方法，当shiro触发授权认证时，会自动调用该方法从数据库中
     * 获取用户的权限信息。
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //1. 获取用户的身份
        String principal = principalCollection.getPrimaryPrincipal().toString();

        //4. 将数据封装到AuthorizationInfo中并返回
        SimpleAuthorizationInfo info=new SimpleAuthorizationInfo();
        return info;
    }

    /**
     * 自定义登录认证的方法，shiro底层会自动的调用该方法从数据库中获取
     * 要认证的数据
     * @param token
     * @return
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        System.out.println("自定义的MyRealm的登录认证方法触发执行..............");
        //1. 获取要认证的用户的身份
        String username = token.getPrincipal().toString();
        //2. 调用业务层从数据库中获取用户的信息
        User user = userService.selUserInfoService(username);
        //3. 将数据存储到info对象中返回
        if(user!=null){
            //声明盐
            String salt="bjsxt";
            //创建info对象存储数据并返回
            AuthenticationInfo info=new SimpleAuthenticationInfo(token.getPrincipal(),user.getPwd(), ByteSource.Util.bytes(salt),token.getPrincipal().toString());
            return info;
        }
        return null;
    }
}
