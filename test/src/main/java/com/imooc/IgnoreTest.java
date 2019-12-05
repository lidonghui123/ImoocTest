package com.imooc;

import org.testng.annotations.Test;

public class IgnoreTest {
    //忽略测试
    @Test(enabled = false)
    public void ignore1(){
        System.out.println(" ignore1 执行");
    }

    @Test(enabled = true)
    public void ignore2(){
        System.out.println();
        System.out.println(" ignore2 执行");
    }
}
