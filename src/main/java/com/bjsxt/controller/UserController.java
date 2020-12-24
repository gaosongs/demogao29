package com.bjsxt.controller;

import com.bjsxt.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("userController")
public class UserController {

    //声明业务层属性
    @Autowired
    private UserService userService;

    //声明单元方法：跳转登录页面
    @RequestMapping("login")
    public String login(){
        System.out.println("我是单元方法，跳转登录页面");
        //返回值和要跳转的页面名称必须一致
        return "login";
    }

    //声明单元方法处理用户用户登录
    @RequestMapping("userLogin")
    @ResponseBody
    public Boolean userLogin(String uname,String pwd){
        //获取subject对象
        Subject subject = SecurityUtils.getSubject();
        //2、调用subject的login方法完成登录认证
            //创建Token对象存储要验证的用户数据
        AuthenticationToken token = new UsernamePasswordToken(uname, pwd);
        //登录认证
        try {
            subject.login(token);
            return true;
        }catch (AuthenticationException e){
            return false;
        }


    }

}
