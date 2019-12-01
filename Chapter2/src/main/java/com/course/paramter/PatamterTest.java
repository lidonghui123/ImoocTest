package com.course.paramter;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class PatamterTest {
     //参数化测试
    //@Test
    @Parameters({"name","age"})
    public void paramterTest1(String name,String age){
        System.out.println("姓名："+name+"；"+"年龄："+age);
    }
    @Test
    public void paramterTest2(){
        System.out.println("dwefwefw ");
    }
}
