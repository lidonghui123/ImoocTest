package com.imooc.httpclient;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.testng.annotations.Test;

import java.io.IOException;

public class MyHttpClient {
    //测试方法

    public void test() throws IOException {
        //用来存放结果
        String result;
        //new 一个get对象，填入网址
        HttpGet get = new HttpGet("http://127.0.0.1:8808/getCookies");
        //
        CloseableHttpClient httpClient = HttpClients.createDefault();
        //执行get对象
        CloseableHttpResponse response = httpClient.execute(get);
        //用Entity,toString转换response.getEntity.赋值给result
        result = EntityUtils.toString(response.getEntity(),"utf-8");
        //dayin
        System.out.println(result);
    }

    @Test
    public void test1() throws IOException {
        //用来存放结果
        String result;
        //new 一个get对象，传入网址
        HttpGet get = new HttpGet("http://www.baidu.com");
        //实例化一个client方法
        CloseableHttpClient client = HttpClients.createDefault();
        //执行get
        CloseableHttpResponse response = client.execute(get);
        //用Entity,toString转换response.getEntity.赋值给result
       result= EntityUtils.toString(response.getEntity(),"utf-8");
        System.out.println(result);
    }

}
