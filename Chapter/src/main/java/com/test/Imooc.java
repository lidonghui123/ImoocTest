package com.test;

import com.google.common.io.Files;
import com.test.file.ProUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Test;
import java.io.File;
import java.io.IOException;

public class Imooc {
    public WebDriver driver;

    /**
     * 驱动加载
     */
    public void InitDriver() {
        System.setProperty("webdriver.chrome.driver", "E:\\selenium\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://www.imooc.com");
        driver.manage().window().maximize();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.findElement(By.id("js-signin-btn")).click();
    }

    /**
     * 系统登陆、判断是否成功登陆
     */
    @Test
    public void UserLogin() throws InterruptedException {


        ProUtil pro = new ProUtil("user.properties");
        String user = null;
        String username;
        String password;
        //获取行数
        int Lines = pro.GetLines();
        for (int i = 0; i < Lines; i++) {
            //调用驱动加载
            InitDriver();
            user = pro.GetPro("user" + i);
            //用户名和密码//进行拆分
            username = user.split("<")[0];
            password = user.split("<")[1];
            //
            Thread.sleep(2000);
            WebElement EmailElement = GetElement("username");
            WebElement PasswordElement = GetElement("password");
            WebElement LoginElement = GetElement("loginbutton");

            EmailElement.sendKeys(username);
            PasswordElement.sendKeys(password);
            LoginElement.click();
            TakeSecreenShot();
            Thread.sleep(3000);
            //
            try {
                Actions as = new Actions(driver);
                WebElement Png = GetElement("headpng");
                as.moveToElement(Png).perform();
                String UserName = GetElement("userinfo").getText();
                //判断名称与预期的是否一致
                if (UserName.equals("dong辉")) {
                    System.out.println("登陸成功");
                } else {
                    System.out.println("用戶信息不匹配");
                }

            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("登陸失敗");

            }
            driver.close();
        }


    }

    /**
     * 元素数据加载
     */
    public By GetByLocal(String key) {
        ProUtil pro = new ProUtil("element.properties");
        String Locator = pro.GetPro(key);
        //进行拆分
        String LocatorBy = Locator.split("<")[0];
        String LocatorValue = Locator.split("<")[1];
        //判断对应BY类型
        if (LocatorBy.equals("id")) {
            return By.id(LocatorValue);
        } else if (LocatorBy.equals("name")) {
            return By.name(LocatorValue);
        } else if (LocatorBy.equals("className")) {
            return By.className(LocatorValue);
        } else {
            return By.xpath(LocatorValue);
        }
    }

    /**
     * Element方法封装
     */
    public WebElement GetElement(String key) {
        WebElement Element = driver.findElement(this.GetByLocal(key));
        return Element;
    }

    /**
     * 截图的方法
     */
    public void TakeSecreenShot(){
        //图片的名字
        //图片的存储路径
        //强制转化driver为remoteWebdriver
        File ScrFile = ((RemoteWebDriver)driver).getScreenshotAs(OutputType.FILE);
        try {
            Files.copy(ScrFile, new File("C:\\Users\\libw-c\\IdeaProjects\\Chapter\\l.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * 新的截图方法
     */
    public void TakeSecreenShot1(){
        //获取当前时间，并格式化时间

        //图片存储流内
         File ScrFile = ((RemoteWebDriver)driver).getScreenshotAs(OutputType.FILE);
         //存储路径

    }
    /**
     * 主方法调用
     */
   /* public static void main(String[] args) throws InterruptedException {
        Imooc sm = new Imooc();
        sm.UserLogin();
        //sm.GetByLocal("username");
    }*/
}
