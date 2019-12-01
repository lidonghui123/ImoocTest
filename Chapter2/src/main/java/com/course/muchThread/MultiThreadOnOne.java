package com.course.muchThread;

import org.testng.annotations.Test;

public class MultiThreadOnOne {

    //线程池，多
    @Test(invocationCount = 10,threadPoolSize = 3)
    public void test(){
        System.out.println(1);
        //打印当前线程的id
        System.out.printf("Thread id: %s%n",Thread.currentThread().getId());
    }
}
