package com.mushishi.imooc.business;

import com.mushishi.imooc.handle.SureOrderHandle;
import org.openqa.selenium.WebDriver;

/**
 * @author libw-c
 * 确认订单业务层级
 */
public class SureOrderPagePro {
    public SureOrderHandle sureOrderHandle;
    public WebDriver driver;
    //构造函数
    public SureOrderPagePro (WebDriver driver){
        this.driver = driver;
        sureOrderHandle = new SureOrderHandle(driver);
    }
    /**
     *确认订单按钮
     */
    public void sureOrder(){
        sureOrderHandle.clickSubOrderElment();
    }
}
