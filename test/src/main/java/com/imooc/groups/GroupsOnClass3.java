package com.imooc.groups;

import org.testng.annotations.Test;

@Test(groups = "teacher")
public class GroupsOnClass3 {
    public void teacher1(){
        System.out.println("GroupsOnClass3的teacher11111运行啦");
    }
    public void teacher2(){
        System.out.println("GroupsOnClass3的teacher22222运行啦");
    }
}
