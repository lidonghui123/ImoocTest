package com.springBoot.server;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;

@RestController
public class MyGetMethod {

    @RequestMapping(value = "/getCookies",method = RequestMethod.GET)
    public String getCookies(HttpServletResponse response){
        //HttpServerrequest
        //HttpServerresponse
        //设置cookies
        Cookie cookie = new Cookie("login","true");
        //添加相应cookie
        response.addCookie(cookie);
        return "恭喜你获得cookies成功啦！";
    }
}
