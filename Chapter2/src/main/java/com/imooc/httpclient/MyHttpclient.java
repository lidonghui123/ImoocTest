package com.imooc.httpclient;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.charset.Charset;

public class MyHttpclient {
    //测试方法,引入testng框架
    @Test
    public void test1() throws IOException {
        //用来存放结果
        String result;
        //new一个get对象，填入网址
        HttpGet get = new HttpGet("https://www.baidu.com");
        //defaulthttpClint替换HttpClientBuilder.create().build();用来执行get方法
        HttpClient client = HttpClientBuilder.create().build();
        //接住返回的参数
        HttpResponse response = client.execute(get);
        //用Entity,toString转换response.getEntity,并且复制给result.
        result = EntityUtils.toString(response.getEntity(),"utf-8");
        //打印result信息
        System.out.println(result);
    }

    @Test
    public void test2() throws IOException {
        //定义String变量
        String result;
        //new一个对象输入网址
        HttpGet get = new HttpGet("https://www.imooc.com");
        //获取DefaultHttpClient请求
        HttpClient client = HttpClientBuilder.create().build();
        //执行get,返回接收返回对象
        HttpResponse response = client.execute(get);
        //返回对象转成String 存入result
        result = EntityUtils.toString(response.getEntity(), "utf-8");
        //result打印
        System.out.println(result);
    }

}
