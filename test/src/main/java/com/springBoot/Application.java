package com.springBoot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

//1、标签进行托管了2、指定扫描的包，报错都没啥影响
@SpringBootApplication
@ComponentScan("com.springBoot.config")
@ComponentScan("com.springBoot.server")
public class Application {
    public static void main(String[] args) {
        //固定写法
        SpringApplication.run(Application.class,args);
    }
}