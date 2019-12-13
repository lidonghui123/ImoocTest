package com.springBoot.server;


import com.springBoot.bean.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// 告诉入这是要扫描的类
@RestController
@Api(value = "/",tags="this is my post api")
@RequestMapping(value = "/v2")
public class MyPostMethod1 {

    //这是存储cookies信息的变量
    private  static Cookie cookie;
    //模拟一个用户登录成功获取到cookies，然后访问其他接口列表

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @ApiOperation(value = "登录接口",httpMethod = "POST")
    public String login(HttpServletResponse response,
                        @RequestParam(value = "userName",required = true) String userName,
                        @RequestParam(value = "passWord",required = true) String passWord){
        //验证用户名是否合法
        if(userName.equals("libowen")&& passWord.equals("123456")){
            cookie = new Cookie("login","true");
            response.addCookie(cookie);
            return "恭喜登录成功了！";
        }
        return "用户名和密码错误！";
    }

    //根据上一个请求返回cookies，传入下一个请求里面
    @RequestMapping(value = "/getUserList",method = RequestMethod.POST)
    @ApiOperation(value ="获取亲的用户",httpMethod = "POST")
    public String getUserList(HttpServletRequest request,
                              @RequestBody User u){
        //创建一个user对象
        User user;
        //获取cookies 信息
        Cookie[] cookies = request.getCookies();
        //验cookies是否合法
        for(Cookie cookie:cookies){
            //判断
            if(cookie.getName().equals("login")
            && cookie.getValue().equals("true")
            && u.getUserName().equals("libowen")
            && u.getPassword().equals("123456")){
                user = new User();
                user.setName("libowen");
                user.setAge("18");
                user.setSex("man");
                user.setUserName("帅哥");
                user.setPassword("***");
                return user.toString();
            }
        }
        return "参数不合法！" ;
    }

}
