package com.imooc.cookies;

import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.Cookie;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;
import java.util.ResourceBundle;

public class MyCookiesPost1 {
    //定义变量
    private String url;
    private ResourceBundle bundle;
    //存储cookies变量
    private CookieStore store;

    @BeforeTest
    public void beforeTest() {
        //读取配置文件
        bundle = ResourceBundle.getBundle("application");
        url = bundle.getString("test.url");
    }

    @Test
    public void testGetCookies() throws IOException {
        //定义返回变量
        String result;
        //从配置文件中拼接测试的url
        String uri = bundle.getString("getCookies.uri");
        String testUrl = this.url + uri;

        //声明一个get方法
        HttpGet get = new HttpGet(testUrl);
        //获取cookies信息
        this.store = new BasicCookieStore();
        //声明一个client对象
        CloseableHttpClient httpClient = HttpClients.custom().setDefaultCookieStore(this.store).build();
        //测试逻辑执行get请求
        CloseableHttpResponse response = httpClient.execute(get);
        //打印结果
        result = EntityUtils.toString(response.getEntity(), "utf-8");
        System.out.println(result);

        //打印cookies
        List<Cookie> cookieList = store.getCookies();
        for (Cookie cookie : cookieList) {
            String name = cookie.getName();
            String value = cookie.getValue();
            System.out.println("name=" + name);
            System.out.println("value=" + value);
        }
    }

    @Test(dependsOnMethods = {"testGetCookies"})
    public void testPostCookies() throws IOException {
        String uri = bundle.getString("postWithCookies.uri");
        //拼接
        String testUrl = this.url + uri;
        //声明post方法
        HttpPost post = new HttpPost(testUrl);

        //添加参数
        JSONObject param = new JSONObject();
        param.put("name", "huhansan");
        param.put("age", "18");

        //设置请求头
         post.setHeader("content-type","application/json");
        //将参数信息添加到方法中吧
        StringEntity entity = new StringEntity(param.toString(), "utf-8");
        post.setEntity(entity);

        //声明一个Clinet,用来执行post方法，设置cookies信息

        CloseableHttpClient client = HttpClients.custom().setDefaultCookieStore(this.store).build();
        //执行post方法
        CloseableHttpResponse response = client.execute(post);
        //转成string返回
        String result = EntityUtils.toString(response.getEntity(), "utf-8");
        System.out.println(result);
    }
}
