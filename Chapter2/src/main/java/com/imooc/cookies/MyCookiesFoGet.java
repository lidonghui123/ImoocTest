package com.imooc.cookies;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import sun.util.resources.zh.LocaleNames_zh;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;

public class MyCookiesFoGet {
    //优化方法
    private  String url;
    //读取配置文件
    private ResourceBundle bundle;

    //@
//
    @BeforeTest
    public void beforeTest() throws IOException {
        Properties properties = new Properties();
        FileInputStream fileInputStream = new FileInputStream("application.properties");
        BufferedInputStream In = new BufferedInputStream(fileInputStream);
        properties.load(In);
        System.out.println(properties);
        System.out.println(properties.getProperty("test.url"));
        //properties.getProperty("test.url");
    }
    @Test
    public void testGetCookies() throws IOException {
        //从配置文件中拼接测试文件url
        String result;
        //使用get请求
        HttpGet get = new HttpGet(this.url+bundle.getString("getCookies.uri"));
        //httpclient方法
        HttpClient client = HttpClientBuilder.create().build();
        //执行接住返回结果
        HttpResponse response = client.execute(get);
        //赋值给String类型文件
        result = EntityUtils.toString(response.getEntity(),"gbk");
        //打印返回值
        System.out.println(result);
    }

}
