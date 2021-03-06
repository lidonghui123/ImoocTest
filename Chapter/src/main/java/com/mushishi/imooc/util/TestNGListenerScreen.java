package com.mushishi.imooc.util;

import com.mushishi.imooc.runcase.Login;
import com.mushishi.imooc.runcase.LoginCase;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TestNGListenerScreen extends TestListenerAdapter {

    private  final String OUTPUT_FOLDER = "screen/";
    public WebDriver driver;
    @Override
    public void onTestFailure(ITestResult tr) {
        //System.err.println("第一case失败");
        String a = tr.getInstanceName();
        System.out.println("------------"+tr.getInstanceName());
        System.out.println("------------"+tr.getInstanceName());
        if(a.contains("LoginCase")){
            //实例化testng
            LoginCase s = (LoginCase)tr.getInstance();
            //转换成webdriver
            this.driver= s.driver;
        }else if(a.contains("Login")){
            //实例化testng
            Login s = (Login)tr.getInstance();
            //转换成webdriver
            this.driver= s.driver;
        }else{
            System.out.println("无对应的类名！");
        }
       /* if(tr.equals(Login.class)){
            //实例化testng
            Login s = (Login)tr.getInstance();
            //转换成webdriver
             this.driver= s.driver;
        }else if(tr.equals(LoginCase.class)){
            //实例化testng
            LoginCase s = (LoginCase)tr.getInstance();
            //转换成webdriver
            this.driver= s.driver;
        }*/
//        //实例化testng
//        LoginCase s = (LoginCase)tr.getInstance();
//        //转换成webdriver
//        this.driver= s.driver;
        //调用截图方法
        this.TakeSecreenShot(driver);
        //调用父类方法
        super.onTestFailure(tr);
    }

    public void TakeSecreenShot(WebDriver driver) {

        // 获取当前时间
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
        String curTime = sdf.format(new Date());
        // 获取当前类名
        String curClassName = getClass().getName();
        String pngPath = curClassName + "_" + curTime + ".png";

        // 获取当前路径
        String curPath = System.getProperty("user.dir");
        // 截屏、图片存储
        File scrFile = ((RemoteWebDriver)driver).getScreenshotAs(OutputType.FILE);
        // 图片存放的位置
        try {
            com.google.common.io.Files.copy(scrFile, new File(curPath + "\\"+OUTPUT_FOLDER+"\\"+ pngPath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
