package com.imooc.cookies;

import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.*;
import org.apache.http.util.EntityUtils;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class MyCookiesGet {
    //
    private String url;
    //读取配置文件对象
    private ResourceBundle bundle;
    //用来存储cookies信息的变量
    private CookieStore store;

    @BeforeTest
    public void beforTest(){
        bundle = ResourceBundle.getBundle("application", Locale.CHINA);
        this.url =bundle.getString("test.url");
    }

    @Test
    public void testGetCookies() throws IOException {
        //存放结果
        String result;
        //从配置文件，拼接测试的url
        String uri = bundle.getString("getCookies.uri");
        String testUrl = this.url+uri;

        // 获取cookies信息1.创建cookie store的本地实例 2.创建一个HttpClient
        this.store= new BasicCookieStore();
        CloseableHttpClient httpclient = HttpClients.custom().setDefaultCookieStore(this.store).build();

        //测试逻辑：建立Get请求
        HttpGet httpget=new HttpGet(testUrl);
        //发送Get请求
        CloseableHttpResponse response2 = httpclient.execute(httpget);

        //打印返回值
        result = EntityUtils.toString(response2.getEntity());
        System.out.println(result);

        //读取cookie信息
        List<Cookie> cookielist = store.getCookies();
        for(Cookie cookie: cookielist){
//            if(cookie.getName().equals("login")){
//                System.out.println(cookie.getName()+cookie.getValue());
//            }else{
//                System.out.println("cookie信息不存在！");
//            }
            String name=cookie.getName();
            String value=cookie.getValue();
//            System.out.println("cookie name =" + name);
//            System.out.println("Cookie value=" + value);
        }
   }

   @Test(dependsOnMethods = {"testGetCookies"})
   public void testGetWithCookies() throws IOException {
        //存放结果
       String result1;
       //从配置文件，拼接测试的url
       String uri = bundle.getString("getWithCookies.uri");
       String testUrl = this.url+uri;
       //测试逻辑：建立Get请求
       HttpGet httpget=new HttpGet(testUrl);

       //声明一个client对象，用来进行方法的执行,并设置cookies信息
       CloseableHttpClient httpclient = HttpClients.custom().setDefaultCookieStore(this.store).build();

       //执行post的方法并得到响应结果
       CloseableHttpResponse response3 = httpclient.execute(httpget);

       //
       result1 = EntityUtils.toString(response3.getEntity(),"utf-8");

       System.out.println(result1);

   }
}
