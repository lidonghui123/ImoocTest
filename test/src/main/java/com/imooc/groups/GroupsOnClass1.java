package com.imooc.groups;

import org.testng.annotations.Test;

@Test(groups =  "stu")
public class GroupsOnClass1 {
    //创建学生类1
    public void stu1(){
        System.out.println();
        System.out.println("GroupsOnClass1的stu11111运行啦");
    }

    public void stu2(){
        System.out.println("GroupsOnClass1的stu222222运行啦");
    }
}
