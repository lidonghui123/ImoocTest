package com.xxgc.runcase;

import com.xxgc.handle.LoginHandle;
import com.xxgc.util.TestNGListenerScreen;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners({TestNGListenerScreen.class})
public class LoginCase extends BaseCase {
    //实例化log4j
    static Logger logger=Logger.getLogger(LoginCase.class);
    //全局变量
    public WebDriver driver;
    LoginHandle loginHandle;
    @BeforeClass
    public void beforeClass() {
        //应该引入log4j的目录以及文件
        PropertyConfigurator.configure("log4j.properties");
        logger.debug("初始化浏览器");
        //配置浏览器参数
        System.setProperty("webdriver.chrome.driver", "D:\\web_selenium\\chromedriver.exe");
        driver = new ChromeDriver();
        logger.debug("打开浏览器");
        driver.get("https://www.imooc.com");
        //Dimension dimension = new Dimension(1355,884);
        //driver.manage().window().setSize(dimension);
        //浏览器窗口最大化
        driver.manage().window().maximize();
        //driver.navigate().refresh();
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // driver.findElement(By.className("redrain-closeBtn")).click();
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
    @Test
    public void TestLoginSuccess() throws InterruptedException {
        logger.debug("开始第一个执行case");
        loginHandle.SendEmail("13231354644!");
        loginHandle.SendPassword("dewdweedewd");
        loginHandle.ClickLogin();
        Thread.sleep(2000);
        //引入登录后名字
        String  username = loginHandle.GetUserText();
        //登录后的用户名判断
        Assert.assertEquals(username,"dong辉");
    }
    @Test
    public void TestLoginError() throws InterruptedException {
        logger.debug("开始第二个执行case");
        loginHandle.SendEmail("1323134804@qq.com");
        loginHandle.SendPassword("libowen88");
        loginHandle.ClickLogin();
        Thread.sleep(2000);
        //引入登录后名字
        String  username = loginHandle.GetUserText();
        //登录后的用户名判断
        Assert.assertEquals(username,"dong辉");
    }
}