package com.xxgc.util;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

public class SendEmail {
    //静态的方法
    public static void sendToEmail(String content){
        //实例化对email对象
        SimpleEmail email =new SimpleEmail();
        //用什么邮箱发送
        email.setHostName("smtp.163.com");
        //登录邮箱身份验证
        email.setAuthentication("libowentester@163.com", "lidonghui123");

        try {
            //来自哪个人
            email.setFrom("libowentester@163.com");
            //发送给谁
            email.addTo("1323134804@qq.com");
            //标题
            email.setSubject("imooc_test");
            //正文用msg可能会乱码，建议用 setContent()
            email.setContent(content,"text/html;charset=utf-8");
            //发送提交
            email.send();
        } catch (EmailException e) {
            e.printStackTrace();
        }
    }
    //调用发送邮件
    public static void main(String[] args) {
        sendToEmail("我自己发着玩！");
    }
}
