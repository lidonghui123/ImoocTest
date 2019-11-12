package com.mushishi.imooc.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * @author 李博文
 */
public class CoursePage extends BasePage{

    public CoursePage(WebDriver driver) {
        super(driver);
    }
    /**
     * 获取立即购买按钮
     */
    public WebElement getBuytriggerElement(){
        return  GetElement("BuyNow");
    }
    /**
     * 获取添加购物车element
     */
    public WebElement getAddCartElement(){
        return GetElement("addCart");
    }
    /**
     * 获取右上角购物车element
     */
    public WebElement getShopCartElement(){
        return  GetElement("shopcart");
    }
    /**
     * 获取购物车数量
     */
    public WebElement getShopCartNumElement(){
        return GetElement("shopcartNum");
    }
}
