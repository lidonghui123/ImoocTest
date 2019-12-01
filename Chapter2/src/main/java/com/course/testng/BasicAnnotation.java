package com.course.testng;

import org.testng.annotations.*;

public class BasicAnnotation {
    //最基本的注解
    @Test
    public void test() {
        System.out.println("这是测试用例1");
    }

    @Test
    public void test1() {
        System.out.println("这是测试用例2");
    }

    //每一个方法运行之前运行。
    @BeforeMethod
    public void beforeMethod() {
        System.out.println("这是在测试之前运行的！");
    }

    //每一方法运行之后运行。
    @AfterMethod
    public void afterMethod() {
        System.out.println("这是在测试之后运行的！");
    }

    //类运行之前和之后运行、、
    @BeforeClass
    public void beforeClass() {
        System.out.println("这是在类运行之前运行的方法");
    }

    @AfterClass
    public void afterClass() {
        System.out.println("这是在类运行之后运行的方法");
    }

    @BeforeSuite
    public void beforeSuite() {
        System.out.println("beforeSuite测试套件！");
    }

    @AfterSuite
    public void afterSuite() {
        System.out.println("afterSuite测试套件！");
    }
}
