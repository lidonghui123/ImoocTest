package com.imooc.groups;

import org.testng.annotations.AfterGroups;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.Test;

public class GroupsOnMethod {
    //创建几个方法
    @Test(groups = "server")
     public void test1(){
        System.out.println("这是服务端组的测试方法111111");
     }
    @Test(groups = "server")
    public void test2(){
        System.out.println("这是服务端组的测试方法22222");
    }

    @Test(groups = "client")
    public void test3(){
        System.out.println("这是服务端组的测试方法33333");
    }

    @Test(groups = "client")
    public void test4(){
        System.out.println("这是服务端组的测试方法44444");
    }
    //beforegroups
    //afterGroups

    @BeforeGroups("server")
    public void beforeGroupsOnSever(){
        System.out.println("这是服务端组运行之前运行的方法！！！！");
    }
    @AfterGroups("server")
    public void afterGroupsOnSever(){
        System.out.println("这是服务端组运行之后运行的方法！！！！");
    }

    @BeforeGroups("client")
    public void beforeGroupsOnClient(){
        System.out.println("这是客户端组运行之前运行的方法！！！！");
    }
    @AfterGroups("client")
    public void afterGroupsOnClient(){
        System.out.println("这是客户端组运行之后运行的方法！！！！");
    }

}
