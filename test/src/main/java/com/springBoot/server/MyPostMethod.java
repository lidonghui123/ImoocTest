package com.springBoot.server;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

//告诉入口这是要扫描的类
@RestController
@Api(value = "/",tags="这是我全部的post请求")
@RequestMapping("v1")
public class MyPostMethod {

    //这是存储cookie信息的变量
    private  static Cookie cookie;
    //模拟一个用户登录成功获取到cookies，然后在访问其他接口列表

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    //swagger的api注释
    @ApiOperation(value = "登录接口，成功后获取cookeis信息",/*告诉是什么方法*/httpMethod = "POST")
    public String login(/*对象是添加cookies信息*/HttpServletResponse response,
                        /* 参数验证(value是对应前段调用时候的显示的名字，（required=true是必须）强调参数是否必须，)*/
                                          @RequestParam(value = "userName",required = true) String userName,
                                          @RequestParam(value = "passWord",required = true) String passWord){
        //验证用户名是否合法
        if (userName.equals("zhangsan") && passWord.equals("132456")){
            cookie = new Cookie("login","true");
            //添加上cookie
            response.addCookie(cookie);
            return "恭喜你登录成功了";
        }
        return "用户名或者密码错误！";
    }
}
