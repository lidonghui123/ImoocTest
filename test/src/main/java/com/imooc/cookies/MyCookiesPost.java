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
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;
import java.util.ResourceBundle;

public class MyCookiesPost {

    //定义变量
    private String  url;
    private ResourceBundle bundle;
    //存储cookies变量
    private CookieStore store;
    @BeforeTest
    public void beforeTest(){
       bundle= ResourceBundle.getBundle("application");
       url = bundle.getString("test.url");
    }

    @Test
    public void testGetCookies() throws IOException {
        //
        String result;
        //配置文件中拼接测试的url
        String uri = bundle.getString("getCookies.uri");
        String testUrl=this.url+uri;
        //获取cookies信息
        this.store =  new BasicCookieStore();
        //声明一个client对象，用来进行方法的执行,并设置cookies信息
        CloseableHttpClient client = HttpClients.custom().setDefaultCookieStore(this.store).build();
        //测试逻辑
        HttpGet httpGet = new HttpGet(testUrl);
        CloseableHttpResponse response = client.execute(httpGet);
        //打印结果
        result = EntityUtils.toString(response.getEntity(),"utf-8");
        System.out.println(result);

        //读取cookies信息
        List<Cookie> cookieList = store.getCookies();
        for (Cookie cookie:cookieList){
            String name = cookie.getName();
            String value = cookie.getValue();
            System.out.println("name="+name);
            System.out.println("name="+value);
        }
    }

    @Test(dependsOnMethods = {"testGetCookies"})
    public void testPostCookies() throws IOException {
        String uri = bundle.getString("postWithCookies.uri");
        //拼接最终的测试地址
        String testUrl = this.url+uri;

        //声明一个post方法
        HttpPost post = new HttpPost(testUrl);

        //添加参数
        JSONObject param = new JSONObject();
        param.put("name","huhansan");
        param.put("age","18");

        //设置请求头信息，设置header
        post.setHeader("content-type","application/json");

        //将参数信息添加到方法中
        StringEntity entity = new StringEntity(param.toString(),"utf-8");
        post.setEntity(entity);
        //声明一个client ,用来进行方法的执行，并设置cookies信息
        CloseableHttpClient httpClient = HttpClients.custom().setDefaultCookieStore(this.store).build();
        //执行post方法并得到相应结果
        CloseableHttpResponse response = httpClient.execute(post);


        //就是判断返回结果是否符合预期
        int statusCode = response.getStatusLine().getStatusCode();
        System.out.println("statusCode="+statusCode);
        //
        String result = EntityUtils.toString(response.getEntity(),"utf-8");
        if(statusCode==200){
            System.out.println(result);
        }else{
            System.out.println("访问/get/with/cookies接口失败！");
        }
        //将返回的响应结果字符串转化为json对象
        JSONObject resultjson = new JSONObject(result);

        //获取到结果值
        String success = (String) resultjson.get("huhansan");
        System.out.println(success);
        Assert.assertEquals("success",success);
    }
}
