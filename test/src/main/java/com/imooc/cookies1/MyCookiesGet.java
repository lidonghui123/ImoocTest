package com.imooc.cookies1;

import org.apache.http.client.CookieStore;

import java.util.ResourceBundle;

public class MyCookiesGet {
    //定义变量
    private String url;
    //读取配置文件
    private ResourceBundle bundle;
    //存储cookies
    private CookieStore store;
    /**
     * 初始化方法
     */
    public void beforeTest(){
        //读取配置文件
        bundle = ResourceBundle.getBundle("application");

    }

}
