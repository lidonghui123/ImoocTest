package com.mushishi.imooc.runcase;

import com.mushishi.imooc.business.CoursePagePro;
import com.mushishi.imooc.business.OrderPayPagepro;
import com.mushishi.imooc.business.SureOrderPagePro;
import com.mushishi.imooc.handle.CoursePageHandle;
import com.mushishi.imooc.util.SendEmail;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

    public class Login   {
        public WebDriver driver;
        public CoursePagePro cpp;
        public CoursePageHandle c1;
        public SureOrderPagePro sopp;
        public OrderPayPagepro opp;
        @BeforeClass
        public void courseList1() {
            //预加载log4j
            PropertyConfigurator.configure("log4j.properties");

            //cpp = new CoursePagePro(driver);
            // 加载配置文件
            System.setProperty("webdriver.chrome.driver", "E:\\selenium\\chromedriver.exe");
            // 实例化driver对象
            driver = new ChromeDriver();
            driver.get("https://www.imooc.com/");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // driver.findElement(By.className("redrain-closeBtn")).click();
        }
        @Test
        public void checkBox() {
            driver.manage().window().maximize();
            // ID定位
            driver.findElement(By.id("js-signin-btn")).click();
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // name定位
            driver.findElement(By.name("email")).sendKeys("1323134804@qq.com");
            // className定位
            driver.findElement(By.className("js-loginPassword")).sendKeys("libowen8866");
            //登录按钮
            driver.findElement(By.className("moco-btn-red")).click();
            //等待页面加载
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                //e.printStackTrace();
            }
            driver.get("https://coding.imooc.com/class/330.html");
            cpp = new CoursePagePro(driver);
            sopp = new SureOrderPagePro(driver);
            opp = new OrderPayPagepro(driver);
        }
        /**
         * 立即购买
         */
        @Test
        public void  TestAddCart() throws InterruptedException {
            //点击购买
            cpp.buyNow();
        }
        /**
         * 提交订单
         */
        @Test
        public void TestSureOrder() throws InterruptedException {
            sopp.sureOrder();
            /*出现弹窗有数据就会有影响*/
            /*Thread.sleep(2000);
            driver.switchTo().activeElement();
            //WebElement a =driver.findElement(By.className("moco-modal-inner"));
            driver.findElement(By.xpath("/html/body/div[9]/div/div/div/div/div/div/a/span")).click();

           /* try{
                driver.switchTo().activeElement();
                WebElement a =driver.findElement(By.className("moco-modal-inner"));
                String content = a.findElement(By.className("moco-modal-content")).getText();
                if(content.equals("此课程已存在未支付订单，请移步订单中心完成支付或取消未支付订单！")){
                    driver.findElement(By.xpath("/html/body/div[9]/div/div/div/div/div/div/a/span")).click();
                }
            }catch (Exception e){
                System.out.println("未执行！");
            }*/

        }

        /**
         * 跳转支付页面
         */
        @Test//(dependsOnMethods ={"checkBox","TestAddCart","TestSureOrder"})
        public void TestGoPay(){
            opp.orderPayPro();
            SendEmail.sendToEmail("用例执行完了！请看报告去！");
        }

//    public void buyCourse() throws InterruptedException {
//        driver.get("https://coding.imooc.com/class/330.html");
//        String cousrseDetail = this.buyCourseNow();
//        this.sureOrder();
//        if (this.getOder() != null) {
//            Thread.sleep(1000);
//            Assert.assertEquals(cousrseDetail,this.getOrderCourse(), "购买的商品信息不一样");
//        }
//
//    }





    }