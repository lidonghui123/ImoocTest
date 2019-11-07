package addproject;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import TestLogger.TestNGListenerScreen;
import file.ProUtil;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;


import java.util.Date;


@Listeners({TestNGListenerScreen.class})
public class XxgcCreate {
    public String StartTime;
    public String EndTime;
    public String ProjectNameID;
    public static WebDriver driver;
    //实例化Log4j
    private Logger logger = Logger.getLogger(XxgcCreate.class);


    /**
     * 驱动加载
     */
    @BeforeClass
    public void InitDriver() {
        logger.debug("InitDriver-debug");
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd:HH:mm:ss");
        String a = sf.format(new Date());
        Calendar c = Calendar.getInstance();
        ProjectNameID = "测试xxgc自行发布" + a;
        //kaishishijian
        c.add(Calendar.DAY_OF_MONTH, 1);
        this.StartTime = sf.format(c.getTime());
        System.out.println(StartTime);
        c.add(Calendar.DAY_OF_MONTH, 4);
        this.EndTime = sf.format(c.getTime());
        System.setProperty("webdriver.chrome.driver", "E:\\selenium\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("http://10.111.155.163:8090/xxgc/gsp/login.do?systemId=297eff545b857f6a015b8580d4240003");
        driver.manage().window().maximize();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    @AfterClass
    public void afterClass() throws AWTException, InterruptedException {
        List<WebElement> button = driver.findElements(By.className("ui-button-text"));
        button.get(0).click();
        Thread.sleep(2000);
        //driver.close();
        //new一个StringSlction流，文件复制进去。
        StringSelection SelectPdf = new StringSelection("E:\\xxgc.pdf");
        //实现点击复制
        Clipboard Sysc = Toolkit.getDefaultToolkit().getSystemClipboard();
        //设置剪切板,复制内容
        Sysc.setContents(SelectPdf, null);
        //new键盘事件
        Robot robot = new Robot();
        // 键盘事件设置键盘事件
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);
        Thread.sleep(1000);
        robot.keyRelease(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        Thread.sleep(1000);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
        Thread.sleep(5000);

    }

    /**
     * 系统登陆、判断是否成功登陆
     */
    public void UserLogin() throws InterruptedException {
        logger.debug("UserLogin-debug");
        logger.error("UserLogin-error");
        ProUtil pro = new ProUtil("user.properties");
        String user = null;
        String username;
        String password;
        //获取行数
        int Lines = pro.GetLines();
        for (int i = 0; i < Lines; i++) {
            //调用驱动加载
            //InitDriver();
            user = pro.GetPro("user" + i);
            //用户名和密码//进行拆分
            username = user.split("<")[0];
            password = user.split("<")[1];
            //用户名密码登录
            Thread.sleep(2000);
            WebElement EmailElement = GetElement("username");
            WebElement PasswordElement = GetElement("password");
            WebElement LoginElement = GetElement("login");

            EmailElement.sendKeys(username);
            PasswordElement.sendKeys(password);
            LoginElement.click();
            Thread.sleep(2000);
            //
            try {
                String UserName = GetElement("userinfo").getText();
                //判断名称与预期的是否一致
                if (UserName.equals("北京市海淀区人民政府青龙桥街道办事处")) {
                    System.out.println(UserName);
                    System.out.println("登陸成功");
                } else {
                    System.out.println("用戶信息不匹配");
                }
            } catch (Exception e) {
                System.out.println("登陸失敗");

            }
            //driver.close();
        }


    }

    /**
     * 项目立项
     */
    @Test
    public void ProjectinInfo() throws InterruptedException, AWTException {
        logger.debug("ProjectinInfo-debug");
        logger.error("ProjectinInfo-error");
        UserLogin();
        //点击项目管理
        GetElement("setinfo").click();
        GetElement("serInfoBetton").click();
        //项目点击
        GetElement("serInfoon").click();
        //思考时间
        Thread.sleep(3000);
        //立项信息填写
        GetElement("ProjectNameElemnt").sendKeys(ProjectNameID);
        GetElement("sourcePropertyElement").sendKeys("资金性质");
        GetElement("budgetElement").sendKeys("100000");
        GetElement("budgetAuthorizeElment").sendKeys("100000");
        GetElement("designerElemnt").sendKeys("设计单位");
        //下拉框（工程类型）
        WebElement projectTypeElement = GetElement("projectTypeElement");
        Select selectList = new Select(projectTypeElement);
        selectList.selectByVisibleText("环境建设");
        //下拉框（组织形式）
        WebElement projectType1Elment = GetElement("projectType1Elment");
        Select selectList1 = new Select(projectType1Elment);
        selectList1.selectByVisibleText("自行发包");
        //开始和结束时间
        GetElement("startTimeElement").sendKeys(StartTime);
        GetElement("EndTimeElement").sendKeys(EndTime);
        GetElement("scheduleElment").click();
        GetElement("remarkElement").sendKeys("工程概况测试");
        //建设单位
        GetElement("buildPlaceElement").sendKeys("desdefwfewfw");

        //评审单位选择
        GetElement("addReviewerElement").click();
        Thread.sleep(1000);
        GetElement("enterpriseElement").sendKeys("北京八笛众和科技服务有限公司");
        GetElement("selectButton").click();
        Thread.sleep(2000);
        GetElement("arrowElement").click();
        Thread.sleep(1000);
        //建设单位选择
        GetElement("addSurveyorElement").click();
        Thread.sleep(1000);
        GetElement("enterpriseName1Elment").sendKeys("天");
        GetElement("Selectbutton1").click();
        Thread.sleep(1000);
        GetElement("arrowElement1").click();
        //建设单位输入框
        boolean jssr = GetElement("enterpriseNameElement").isSelected();
        System.out.println(jssr);
        GetElement("enterpriseAddressElment").sendKeys("北京市海淀区丹棱街10号新海大厦办公中心");
        GetElement("enterprisePersonElment").sendKeys("etst");
        GetElement("enterpriseTelElement").sendKeys("18766998855");
        GetElement("enterpriseRoomElment").sendKeys("keshi");
        GetElement("RoomPersonElement").sendKeys("科室负责人");
        GetElement("roomPersonTelElment").sendKeys("0102269988");
        //附件上传 ui-button-text



    }

    /**
     * 元素数据加载
     */
    public By GetByLocal(String key) {
        logger.debug("GetByLocal.debug");
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
        } else if (LocatorBy.equals("linkText")) {
            return By.linkText(LocatorValue);
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
     * 主方法调用
     */
   /* public static void main(String[] args) throws InterruptedException {
        XxgcCreate sm = new XxgcCreate();
        sm.UserLogin();
        //sm.GetByLocal("username");
    }*/


}
