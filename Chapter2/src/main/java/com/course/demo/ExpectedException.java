package com.course.demo;

import org.testng.annotations.Test;

import java.util.Scanner;

public class ExpectedException {
    /**
     * 什么时候会用到异常测试？
     * 在我们期望结果为某一个异常时候
     * 比如：我们传入了某些不合法的异常。
     */
    //这是一个测试结果会失败的异常测试
    //括号里面添加属性
    @Test(expectedExceptions = RuntimeException.class)
    public void runTimeExcepionFailed(){
        System.out.println("这是异常测试失败的！");
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void runTimeExceptionSuccess(){
        System.out.println("这是一个成功的异常测试！");
         throw new NullPointerException();
    }
}
