package com.springBoot.server;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

//告诉它是被扫描的类
@RestController
@Api(value = "/",tags = "这是全部的get方法")
public class MyGetMethod {

    //此标签指定访问路径是什么？
    @RequestMapping(value = "/getCookies",method = RequestMethod.GET)
    @ApiOperation(value="通过这个方法可以获取到Cookies",httpMethod = "GET")
    public String getCookies(HttpServletResponse response){
        //HttpServerletResuest 装请求信息的类
        //HttpServerletResponse 装相应信息的类
        Cookie cookie = new Cookie("login","true");
        // cookie信息添加进去
        response.addCookie(cookie);
        return  "恭喜你获得cookies成功！";
    }
    /**
     * 要求客户端携带cookies访问
     * 这是一个需要携带cookies信息访问接口
     */
    @RequestMapping(value = "/get/with/cookies",method = RequestMethod.GET)
    @ApiOperation(value = "要求客户端携带cookies访问",httpMethod = "GET")
    public String getWithCookeis(HttpServletRequest request){
        //request.getcookies,返回一个cookie[]数组
        Cookie[] cookies =  request.getCookies();
        //使用objects.isnull判断是否为空。
        if(Objects.isNull(cookies)){
            return "你必须携带cookies信息访问！";
        }
        //循环cookies数组值
        for (Cookie cookie:cookies){
            //判断cookie的key和value与预期是否一致
            if(cookie.getName().equals("login") && cookie.getValue().equals("true")){
                return "这是一个需要携带cookies信息访问接口";
            }
        }
        return "你必须携带cookies信息访问！";
    }

    /**
     * 开发一个需要携带参数才能访问的get请求。
     * 第一种实现方式 url:key=value&key=value
     * 我们来模拟获取商品列表的接口，获取商品肯定有开始和结束的位置。
     * 第二种
     */

    @RequestMapping(value = "/get/with/param",method= RequestMethod.GET)
    @ApiOperation(value = "需要携带参数才能访问的get请求",httpMethod = "GET")
    public Map<String,Integer> getList(@RequestParam Integer start,
                                       @RequestParam Integer end){
        //new map集合
        Map<String,Integer> myList = new HashMap<>();
        //添加参数
        myList.put("鞋",400);
        myList.put("干脆面",1);
        myList.put("衬衫",300);
        //返回结果
        return myList;
    }
    /**
     * 第二种需要携带参数访问的get请求
     * url:ip:prot/get/with/param/10/20
     * 使用的注解是@pathvariable Integer start 和 end
     */
    @RequestMapping(value = "/get/with/param/{start}/{end}",method = RequestMethod.GET)
    @ApiOperation(value = "需要携带参数才能访问的get请求第二种实现",httpMethod = "GET")
    public Map getMyList(@PathVariable Integer start,
                         @PathVariable Integer end){

        Map<String,Integer> myList = new HashMap<>();
        myList.put("汽车",50000);
        myList.put("豪宅",800000);
        myList.put("名牌",620000);
        return myList;
    }

}

