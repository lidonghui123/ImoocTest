package com.mushishi.imooc.business;

import com.mushishi.imooc.handle.OrderPayPageHandle;
import org.openqa.selenium.WebDriver;

public class OrderPayPagepro {
    //引入页面对象
    public WebDriver driver;
    public OrderPayPageHandle orderPayPageHandle;
    //构造方法
    public OrderPayPagepro(WebDriver driver){
        this.driver = driver;
        orderPayPageHandle = new OrderPayPageHandle(driver);
    }
    /**
     * 根据课程、订单判断跳转到支付页面
     */
    public void orderPayPro(){
        //获取订单
        String orderName = orderPayPageHandle.getOrderName();
        //获取
        String courseName = orderPayPageHandle.getOrderCourseName();
        //判断订单和课程名称不为空
        if(orderName != null && courseName != null){
            //点击支付宝
            orderPayPageHandle.clickAlipay();
            //点击立即支付
            orderPayPageHandle.clickPayElement();
        }
    }
    /**
     * 模态窗口
     */
    public void swithMoto(){
        driver.switchTo().activeElement();
    }
}
