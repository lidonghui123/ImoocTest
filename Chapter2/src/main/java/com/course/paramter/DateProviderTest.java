package com.course.paramter;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.lang.reflect.Method;


public class DateProviderTest {

    //数据源的name和test应该一致
   // @Test(dataProvider = "data" )
    public void testDataProvider(String name,int age){
        System.out.println("name="+name+"; age="+age);
    }

    //数据来源,注解是DataProvider
    //@DataProvider(name="data")
    public Object[][] ProviderData(){
        //定义一个o,返回object对象的里面的数据
        Object[][] o = new Object[][]{
                {"zhangsan",10},
                {"lisi",20},
                {"wangwu",22}
        };
        //返回o的对象。
        return o;
    }

    //test1
    @Test(dataProvider = "methdaData")
    public void test1(String name,int age){
        System.out.println("Test1+name="+name+"; age="+age);
    }
    @Test(dataProvider = "methdaData")
    public void test2(String name,int age){
        System.out.println("Test2+name="+name+"; age="+age);

    }

    //注意一点是根据方法执行的参数的，大括号一定要加Method method 对象
    //填写method目的是自动把方法名传递进来
    @DataProvider(name="methdaData")
    public Object[][] methdDataProviter(Method method){
        //
        Object[][] result=null;
        //判断方法名称进行传递参数
        if(method.getName().equals("test1")){
            result = new Object[][]{
                    {"zhangsan",20}
            };
        }else if(method.getName().equals("test2")){
            result = new Object[][]{
                    {"lisi",30}
            };
        }
        return result;
    }
}
