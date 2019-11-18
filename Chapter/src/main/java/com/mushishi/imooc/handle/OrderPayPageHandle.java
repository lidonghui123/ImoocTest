package com.mushishi.imooc.handle;

import com.mushishi.imooc.page.OrderPayPage;
import com.mushishi.imooc.runcase.BaseCase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class OrderPayPageHandle {
    //引入对象
    public OrderPayPage orderPayPage;
    public WebDriver driver;
    //构造函数
    public OrderPayPageHandle(WebDriver driver){
        this.driver = driver;
        orderPayPage = new OrderPayPage(driver);
    }

    /**
     *获取订单文字的
     * @return
     */
    public String getOrderName(){
        return orderPayPage.gettext(orderPayPage.getOrderNumElement());
    }
    /**
     * 获取课程名称
     */
    public String getOrderCourseName(){
        return orderPayPage.gettext(orderPayPage.getOrderCourserNameElement());
    }

    /**
     *点击支付宝支付
     */
    public void clickAlipay(){
        orderPayPage.click(orderPayPage.getAlipayElement());
    }
    /**
     *点击立即支付
     */
     public void clickPayElement(){
         orderPayPage.click(orderPayPage.getOrderPayElement());
     }
    /**
     * 点击已提交弹窗
     */
    public void clickPayOrderElement(){
        orderPayPage.click(orderPayPage.getPayOrderElement());

    }

}
