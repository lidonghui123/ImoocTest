package com.mushishi.imooc.runcase;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class BaseCase {
    //设置浏览器

    //创建一个方法，传入参数是为了甄别，传入的是哪个浏览器。
    public static WebDriver  GetDriver(String browser){
        WebDriver driver;
        //判断传入的值,别用纯equals判断，可能会忽略大小写。
        if(browser.equalsIgnoreCase("chrome")){
            //driver文件配置
            System.setProperty("webdriver.chrome.driver", "E:\\selenium\\chromedriver.exe");
            driver = new ChromeDriver();
            driver.manage().window().maximize();
        }else {
            System.setProperty("webdriver.chrome.driver", "E:\\selenium\\chromedriver.exe");
            ChromeOptions options = new ChromeOptions();
            options.setBinary("C:\\Users\\libw-c\\AppData\\Roaming\\360se6\\Application\\360se.exe");
             driver = new ChromeDriver(options);
            //driver.manage().window().maximize();
            //driver.get("https://wwww.baidu.com");
            //driver.quit();
        }
        return driver;
    }

//    public static void main(String[] args) {
//        GetDriver("dd");
//    }
    //Dimension dimension = new Dimension(1355,884);
    //driver.manage().window().setSize(dimension);
    //driver.navigate().refresh();
}
