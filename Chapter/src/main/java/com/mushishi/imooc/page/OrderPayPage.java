package com.mushishi.imooc.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class OrderPayPage extends BasePage {
    //构造函数
    public OrderPayPage(WebDriver driver) {
        super(driver);
    }
    /**
     * 获取订单号
     */
    public WebElement getOrderNumElement(){
        return  GetElement("order");
    }

    /**
     * 获取课程名称
     */
    public WebElement getOrderCourserNameElement(){
        return GetElement("orderCourse");
    }
    /**
     *返回支付宝element
     */
    public WebElement getAlipayElement(){
        return GetElement("alipay");
    }
    /**
     * 获取点击立即支付按钮elemnt
     */
    public WebElement getOrderPayElement(){
        return GetElement("orderPay");
    }
    /**
     * 已经提交订单的移步按钮
     */
    public WebElement getPayOrderElement(){
        return  nodeElement("payorder","payorderskip");
    }
}
