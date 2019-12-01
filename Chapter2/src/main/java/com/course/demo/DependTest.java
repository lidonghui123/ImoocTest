package com.course.demo;

import org.testng.annotations.Test;

public class DependTest {
    //依赖测试
    @Test//(dependsOnMethods = {"test2"})
    public void test1(){
        System.out.println("test1执行啦");
        throw new RuntimeException();
    }
    @Test(dependsOnMethods = {"test1"})
    public void test2(){
        System.out.println("test2执行啦");
    }
}
