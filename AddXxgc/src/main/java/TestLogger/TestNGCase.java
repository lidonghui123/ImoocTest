package TestLogger;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.ReporterConfig.Property;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners({TestNGListenerScreen.class})
public class TestNGCase {
    //实例化log4j
    private Logger logger=Logger.getLogger(TestNGCase.class);
    //private Logger logeer = Logger.getLogger(TestNGCase.class);
    //driver实例化对象
    public WebDriver driver;
    @BeforeClass
    public void beforeClass(){
        //应该引入log4j的目录以及文件
        //PropertyConfigurator.configure("C:\\Users\\libw-c\\IdeaProjects\\AddXxgc\\resources\\log4j.properties");
        //初始化webdriver目录，引入文件
        System.setProperty("webdriver.chrome.driver", "E:\\selenium\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://www.imooc.com");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.findElement(By.id("js-signin-btn")).click();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    @AfterClass
    public void afterClass(){
        driver.close();
        this.SendToEmail();
    }
    @Test
    public void test01(){
        logger.debug("debug邮箱信息");
        driver.findElement(By.name("email")).sendKeys("dwefwfwfew");
        System.out.println("第一个case");
    }
    @Test
    public void test02(){
        logger.error("error的邮箱信息");
        driver.findElement(By.id("email1")).sendKeys("dwefwfwfew");
        System.out.println("第二个case");
    }
    @Test
    public void test03(){
        driver.findElement(By.name("password")).sendKeys("qweewqe");
        System.out.println("第三个case");
    }
    public void SendToEmail(){
        /**
         * 用什么邮箱发送
         * 登录邮箱
         * 谁发给谁
         * 标题
         * 内容
         */
        //邮箱对象实例化
        SimpleEmail email = new SimpleEmail();
        //用什么邮箱发送
        email.setHostName("smtp.163.com");
        //发送邮箱的用户名和密码
        email.setAuthentication("libowentester@163.com","lidonghui123");

        try {
            //来自于哪个人
            email.setFrom("libowentester@163.com");
            //发给谁
            email.addTo("1323134804@qq.com");
            //发送的标题
            email.setSubject("selenium_test");
            //发送的内容
            email.setMsg("测试邮箱是否发送成功");
            //发送提交
            email.send();
            //debug断言
            logger.debug("发送成功！");
        } catch (EmailException e) {
            e.printStackTrace();
        }
        //发送给谁

    }
}

