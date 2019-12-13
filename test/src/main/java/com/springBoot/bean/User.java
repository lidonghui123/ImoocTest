package com.springBoot.bean;


import lombok.Data;

@Data
public class User {

    private String UserName;
    private String Password;
    private String Name;
    private String age;
    private String sex;

//    public String getName() {
//        return Name;
//    }
//
//    public void setName(String name) {
//        Name = name;
//    }
//
//    public String getAge() {
//        return age;
//    }
//
//    public void setAge(String age) {
//        this.age = age;
//    }
//
//    public String getSex() {
//        return sex;
//    }
//
//    public void setSex(String sex) {
//        this.sex = sex;
//    }
//
//    @Override
//    public String toString() {
//        return "User{" +
//                "Name='" + Name + '\'' +
//                ", age='" + age + '\'' +
//                ", sex='" + sex + '\'' +
//                '}';
//    }
}
