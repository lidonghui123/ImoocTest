package com.imooc.cookies1;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.client.CookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;
import java.util.ResourceBundle;

public class MyCookiesGet {
    //
    private String url;
    //读取配置文件
    private ResourceBundle bundle;
    //用来存储cookies信息的变量
    private CookieStore store;

    @BeforeTest
    public void beforeTest(){
       bundle =  ResourceBundle.getBundle("application");
       url = bundle.getString("test.url");
    }
    @Test
    public void testGetCookies() throws IOException {
        //存放结果
        String result;
        //从配置文件，拼接测试url
        String uri = bundle.getString("getCookies.uri");
        String test_url= this.url+uri;
        //获取cookies
        this.store= new BasicCookieStore();
        CloseableHttpClient httpClient = HttpClients.custom().setDefaultCookieStore(this.store).build();
        //测试逻辑建立httpget
        HttpGet get = new HttpGet(test_url);
        //发送get请求
        CloseableHttpResponse response = httpClient.execute(get);
        //打印返回值，
        result = EntityUtils.toString(response.getEntity(),"utf-8");
        System.out.println(result);
        //读取cookies信息
        List<Cookie> cookieList = store.getCookies();
        for(Cookie cookie:cookieList){
            String name= cookie.getName();
            String value = cookie.getValue();
            System.out.println("name="+name);
            System.out.println("value="+value);
        }
    }

    @Test(dependsOnMethods = {"testGetCookies"})
    public void testGetWithCookies() throws IOException {
        //存放结果
        String result;
        //从配置文件，拼接测试的url
        String uri = bundle.getString("getWithCookies.uri");
        String test_url = this.url+uri;

        //测试逻辑，建立get请求
        HttpGet get = new HttpGet(test_url);
        //声明一个client对象，用来进行方法的执行，并设置cookies信息
        CloseableHttpClient httpClient = HttpClients.custom().setDefaultCookieStore(this.store).build();
        //执行post请求得到响应结果
        CloseableHttpResponse response = httpClient.execute(get);
        //打印返回值
        result = EntityUtils.toString(response.getEntity(),"utf-8");
        System.out.println(result);
    }

}
