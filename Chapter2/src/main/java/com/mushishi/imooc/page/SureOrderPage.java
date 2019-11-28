package com.mushishi.imooc.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SureOrderPage extends BasePage {

    //调用父类
    public SureOrderPage(WebDriver driver) {
        super(driver);
    }
    /**
     * 获取提交订单按钮
     */
    public WebElement getSubOrderElement(){
        return  GetElement("sureOrder");
    }
}
