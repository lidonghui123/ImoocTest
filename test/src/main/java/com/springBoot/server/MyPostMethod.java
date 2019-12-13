package com.springBoot.server;


import com.springBoot.bean.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//告诉入口这是要扫描的类
@RestController
@Api(value = "/",tags="this is my post method")
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
        if (userName.equals("zhangsan") && passWord.equals("123456")){
            cookie = new Cookie("login","true");
            //添加上cookie
            response.addCookie(cookie);
            return "恭喜你登录成功了";
        }
        return "用户名或者密码错误！";
    }


    @RequestMapping(value = "/libowen",method = RequestMethod.POST)
    //wagger api  注释
    @ApiOperation(value = "登录接口.",httpMethod = "POST")
    public  String  login1(HttpServletResponse response,
                           @RequestParam(value = "userName",required = true) String userName,
                           @RequestParam(value = "passWord",required = true) String passWord){

        //验证用户名是是否合法
        if(userName.equals("libowen") && passWord.equals("8866")){
            cookie = new Cookie("login","true");
            //定义好，就得添加上了吧。
            response.addCookie(cookie);
            return "亲，恭喜您登录成功了！";
        }
        return  "用户名和密码错误！";
    }

    @RequestMapping(value = "/getUserList",method = RequestMethod.POST)
    @ApiOperation(value = "获取用户列表",httpMethod = "POST")
    public String getUserList(HttpServletRequest request,
                              @RequestBody User u){
        //创建返回user对象
        User user;
        //获取cookies信息
        Cookie[] cookies = request.getCookies();
        //验证cookies是否合法
        for (Cookie c : cookies) {
            if (c.getName().equals( "login")
                    && c.getValue().equals("true")
                    && u.getUserName().equals("zhangsan")
                    && u.getPassword().equals("123456")
            ) {
                user = new User();
                user.setName("libowen");
                user.setAge("18");
                user.setSex("man");

                return user.toString();
            }
        }
        return "参数不合法";
    }
}
