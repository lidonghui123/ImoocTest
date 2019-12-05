package com.imooc.groups;

import org.testng.annotations.Test;

@Test(groups = "stu")
public class GroupsOnClass2 {
    //创建学生类2
    public void stu3(){
        System.out.println("GroupsOnClass2的stu11111运行啦");
    }

    public void stu4(){
        System.out.println("GroupsOnClass2的stu222222运行啦");
    }
}
