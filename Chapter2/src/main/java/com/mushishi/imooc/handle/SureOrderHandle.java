package com.mushishi.imooc.handle;

import com.mushishi.imooc.page.SureOrderPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SureOrderHandle {
    //引入页面对象
    public SureOrderPage sureOrderPage;
    public WebDriver driver;
    //构造方法
    public SureOrderHandle(WebDriver driver){
        //driver都需要传递
        this.driver = driver;
        //页面层的driver要传递进去
        sureOrderPage = new SureOrderPage(driver);
    }
    /**
     * 点击确认订单按钮
     */
    public void clickSubOrderElment(){
        sureOrderPage.click(sureOrderPage.getSubOrderElement());
    }

}
