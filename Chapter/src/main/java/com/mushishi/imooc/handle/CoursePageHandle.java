package com.mushishi.imooc.handle;

import com.mushishi.imooc.page.CoursePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * 课程页面操作类
 */
public class CoursePageHandle {
    public WebDriver driver;
    //初始化coursePage对象
    public CoursePage coursePage;

    //构造方法
    public CoursePageHandle(WebDriver driver) {
        this.driver = driver;
        coursePage = new CoursePage(driver);
    }

    /**
     * 点击立即购买按钮
     */
    public void clickBuyNow() {
        coursePage.click(coursePage.getBuytriggerElement());
    }

    /**
     * 点击添加购物车
     */
    public void clickaddCart() {
        coursePage.click(coursePage.getAddCartElement());
    }

    /**
     * 点击右上角购物车
     */
    public void clickshopcart() {
        coursePage.click(coursePage.getShopCartElement());
    }
    /**
     * 获取购物车数量
     */
    public String getshopcartNum(){
        WebElement element= coursePage.getShopCartNumElement();
        return coursePage.gettext(element);
    }
    /**
     * 获取课程名称
     */
    public String getCourseName(){
        WebElement element = coursePage.getCouserNameElement();
        return  coursePage.gettext(element);
    }
    /**
     * 点击已购买的课程弹窗
     */
    public void clickReadyBuy(){
        coursePage.click(coursePage.getReadyBuy());
    }
}