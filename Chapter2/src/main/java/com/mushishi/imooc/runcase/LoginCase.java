package com.mushishi.imooc.runcase;

import com.mushishi.imooc.handle.LoginHandle;
import com.mushishi.imooc.util.TestNGListenerScreen;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import org.apache.log4j.Logger;

@Listeners({TestNGListenerScreen.class})
public class LoginCase extends BaseCase{
    //实例化log4j
    static Logger logger=Logger.getLogger(LoginCase.class);
    //全局变量
    public WebDriver driver;
    LoginHandle loginHandle;
    @Parameters({"url","browser"})
    @BeforeClass(description = "登录初始化浏览器")
    //@BeforeGroups (groups = "error")
    public void beforeClass(String url,String browser) {
        //应该引入log4j的目录以及文件
        PropertyConfigurator.configure("log4j.properties");
        logger.debug("初始化浏览器");
        //配置浏览器参数
        driver = GetDriver(browser);
        logger.debug("打开浏览器");
        driver.get(url);
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //new 一个loginHandle的对象
        loginHandle = new LoginHandle(driver);
        //点击登录按钮
        loginHandle.ClickSigniButton();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
    @AfterClass
    public void afterClass(){
        driver.close();
    }

    /**
     * 测试登录成功用例
     */
    @Parameters({"username1","password"})
    @Test(description = "登录成功")
    public void TestLoginSuccess(String username1,String password) throws InterruptedException {
        logger.debug("开始第一个执行case");
        loginHandle.SendEmail(username1);
        loginHandle.SendPassword(password);
        loginHandle.ClickLogin();
        Thread.sleep(2000);
        //引入登录后名字
        String  username = loginHandle.GetUserText();
        //登录后的用户名判断
        Assert.assertEquals(username,"dong辉");
    }
    @Test(description = "登录失败")
    public void TestLoginError() throws InterruptedException {
        logger.debug("开始第二个执行case");
        loginHandle.SendEmail("1323134804@qq.com");
        loginHandle.SendPassword("libowen");
        loginHandle.ClickLogin();
        Thread.sleep(2000);
        //引入登录后名字
        String  username = loginHandle.GetUserText();
        //登录后的用户名判断
        Assert.assertEquals(username,"dong辉");
    }
}