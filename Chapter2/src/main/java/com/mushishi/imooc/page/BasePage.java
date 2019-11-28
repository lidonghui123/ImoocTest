package com.mushishi.imooc.page;

import com.mushishi.imooc.util.ProUtil;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class BasePage {
    //实例化log4j
    static Logger logger=Logger.getLogger(BasePage.class);
    //声明driver
    public WebDriver driver;
    //构造方法,传入driver
    public BasePage(WebDriver driver){
        //因为driver1是没有数值，需要给它进行赋值
        this.driver=driver;
        //driver1 = new CoursePage(driver);
    }

    /**
     * Element封装方法
     * 为好识别，为按照驼峰原则命名
     */
    public WebElement GetElement(String key){
        //测试的方法待确定！
        logger.debug("封装方法"+key);
        //循环查找十次元素
        boolean flag= false;
        //捕获异常
        try {
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        int i =0;
        //element设置null值
        WebElement Element=null;
        //第一次进来flag为true
        while(flag) {
            try {
                Element = driver.findElement(this.GetByLocal(key));
                flag=false;

            }
            catch(Exception e) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
                i=i+1;
                //循环判断超过10次的，flag置为flase
                if(i ==5) {
                    flag=false;
                }
            }
        }
        return Element;
    }
    /**
     * 层级定位，通过父节点定位到子节点
     * 需要传入父节点和子节点by
     */
    public WebElement nodeElement(String key,String nodekey){
        logger.debug("层级定位"+nodekey);
        WebElement element = driver.findElement(this.GetByLocal(key));
        return element.findElement(this.GetByLocal(nodekey));
    }

    /**
     * 层级定位，通过父节点定位到子节点
     * 需要传入父节点和子节点by
     */
//    public WebElement nodeElement(By by,By nodeby){
//        WebElement el = this.element(by);
//        return  el.findElement(nodeby);
//    }
    /**
     * 封装一个click点击
     */
    public void click(WebElement element){
        logger.debug(element+"按钮点击");
        //判断传入的是否存在
        if(element != null){
            element.click();
        }else{
            System.out.println("元素没有定位到，点击失败！"+element);
        }
    }
    /**
     * 获取文本信息
     */
    public String gettext(WebElement element){
        return element.getText();
    }


    /**
     * 元素定位类型封装
     */
    public By GetByLocal(String key) {
        ProUtil pro = new ProUtil("element1.properties");
        //定位信息输出debug（key）值
        logger.debug("你的定位信息的key为:"+key);
        //key比对，
        String Locator = pro.GetPro(key);
        // value值进行拆分
        String LocatorBy = Locator.split("<")[0];
        String LocatorValue = Locator.split("<")[1];
        logger.debug("你的定位方式为"+LocatorBy);
        logger.debug("你的定位值为"+LocatorValue);
        // 判断对应BY类型
        if (LocatorBy.equals("id")) {
            return By.id(LocatorValue);
        } else if (LocatorBy.equals("name")) {
            return By.name(LocatorValue);
        } else if (LocatorBy.equals("className")) {
            return By.className(LocatorValue);
        } else if(LocatorBy.equals("linkText")){
            return By.linkText(LocatorValue);
        }else{
            return By.xpath(LocatorValue);
        }
    }
    //公司代码上传

    /**
     * 鼠标悬停
     * @param
     */
    public void MoveToElement(WebElement ToElement){
        //new action对象
        Actions actions = new Actions(driver);
        //鼠标悬停在图片Elemet上面
        actions.moveToElement(ToElement).perform();
    }
}