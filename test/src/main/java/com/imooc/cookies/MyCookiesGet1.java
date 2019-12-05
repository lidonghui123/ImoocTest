package com.imooc.cookies;

import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class MyCookiesGet1 {
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
    public void  getCookies() throws IOException {
        //存放结果
        String result;
        //配置文件，拼接测试的url
        String uri = bundle.getString("getCookies.uri");
        String testUrl = this.url+uri;
        //获取cookies
        this.store = new BasicCookieStore();
        //创建一个HttpClient
        CloseableHttpClient httpClient = HttpClients.custom().setDefaultCookieStore(this.store).build();
        //测试逻辑：建立Get请求
        HttpGet get = new HttpGet(testUrl);
        //发送get请求
        CloseableHttpResponse response = httpClient.execute(get);
        //打印返回值
       result = EntityUtils.toString(response.getEntity(),"utf-8");
       //读取cookies信息
        List<Cookie> cookieList = store.getCookies();
        for (Cookie cookie:cookieList){
            String name =  cookie.getName();
            String value = cookie.getValue();
            System.out.println("cookie name =" + name);
            System.out.println("Cookie value=" + value);
        }
    }

    @Test(dependsOnMethods={"getCookies"})
    public void getWithCookies() throws IOException {
        //存放结果
        String result1;
        //配置文件，拼接测试的url
        String uri = bundle.getString("getWithCookies.uri");
        String testUrl = this.url+uri;
        //测试逻辑
        HttpGet httpGet = new HttpGet(testUrl);
        //声明一个client对象，用来进行方法的执行，并设置cookie信息
        CloseableHttpClient httpClient = HttpClients.custom().setDefaultCookieStore(this.store).build();
        ////执行get的方法并得到响应结果
        CloseableHttpResponse response = httpClient.execute(httpGet);
        //result1 接收返回结果
       result1= EntityUtils.toString(response.getEntity(),"utf-8");
        System.out.println(result1);
    }
}
