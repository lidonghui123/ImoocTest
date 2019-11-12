package com.test.file;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.testng.annotations.Test;

public class TestCase {
    @Test
<<<<<<< HEAD
    public  void test() {
//        System.setProperty("webdriver.firefox.bin", "C:\\Users\\libw-c\\AppData\\Local\\Mozilla Firefox\\firefox.exe");
//        System.setProperty("webdriver.gecko.driver", "E:\\selenium\\geckodriver.exe");
//        WebDriver driver = new FirefoxDriver();
       /* System.setProperty("webdriver.chrome.driver", "E:\\selenium\\360\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.setBinary("C:\\Users\\libw-c\\AppData\\Roaming\\360se6\\Application\\360se.exe");
        WebDriver driver = new ChromeDriver(options);
        driver.get("http://www.baidu.com");*/
        System.setProperty("webdriver.ie.driver","E:\\selenium\\IEDriverServer.exe");
        WebDriver driver = new InternetExplorerDriver();
        driver.get("http://www.baidu.com");
=======
    public  void test(){
        System.out.println("wefw");
>>>>>>> origin/master
    }
}
