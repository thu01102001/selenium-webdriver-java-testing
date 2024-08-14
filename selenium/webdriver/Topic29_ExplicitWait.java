package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.regex.Pattern;

public class Topic29_ExplicitWait {
    //1. setup: OS/ Browser / Web / Page / Data /Variable/ Objiect (đủ điều kiện để thực thi testcase)
    WebDriver driver; //khai báo bến
    WebDriverWait explicitWait;
    @BeforeClass
    public void InitialBrowser() {
        driver = new FirefoxDriver();
        explicitWait = new WebDriverWait(driver,Duration.ofSeconds(10));
    }
    @Test
    public void TC_01_Less_Than() throws InterruptedException {
        //wait cho element ko hien thi , ko con trong html bien mat (truoc do la co ton tai)
        explicitWait.until(ExpectedConditions.stalenessOf(driver.findElement(By.cssSelector(""))));
        //wait cho element ko hien thi (con / ko con trong html)
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("")));
        //wait cho element duoc hien thi (phai co trong html / co tren UI)
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("")));
        //wait cho elemnt duoc phep click (button/ link/ radio)
        explicitWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("")));
        //wait cho url cua page tuyet doi
        explicitWait.until(ExpectedConditions.urlToBe(""));
        //wait cho url cua page tuong doi
        explicitWait.until(ExpectedConditions.urlContains(""));
        //wait cho url cua page thoa man bieu thuc (Regex)
        explicitWait.until(ExpectedConditions.urlMatches("**$^..."));
        //wait cho tra ve du lieu
        explicitWait.until(ExpectedConditions.jsReturnsValue(""));
        //wait cho alert co xuat hien trong html
        explicitWait.until(ExpectedConditions.alertIsPresent())
        //wait cho title cua page tuyet doi
        explicitWait.until(ExpectedConditions.titleIs(""));
        //wait cho thoa man 2 dieu kien
        explicitWait.until(ExpectedConditions.and(ExpectedConditions.urlContains(""), ExpectedConditions.urlContains("")));
        //wait cho 1 element co xuat hien trong html
        explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("")));
        //wait cho 1 element chua 1 thuoc tinh nao do
        explicitWait.until(ExpectedConditions.attributeContains(By.cssSelector(""), "class", "email"));
        //wait cho 1 element
        explicitWait.until(ExpectedConditions.attributeToBe(By.cssSelector(""), "class", "email"));
        //wait cho 1 element co thuoc tinh rong / null
        explicitWait.until(ExpectedConditions.attributeToBeNotEmpty(driver.findElement(By.cssSelector("")), "class"));

        explicitWait.until(ExpectedConditions.domAttributeToBe(driver.findElement(By.cssSelector("")), "innerText", "Start"));

        //wait cho element da duoc chon thanh cong
        explicitWait.until(ExpectedConditions.elementSelectionStateToBe(By.cssSelector(""), true));
        //wait cho element chua duoc chon thanh cong
        explicitWait.until(ExpectedConditions.elementSelectionStateToBe(By.cssSelector(""), false));
        //wait cho frame / iframe xuat hien va switch vao
        explicitWait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.cssSelector("")));
        //wait cho 1 doan lenh JS duoc thuc thi ko tra ve bat ky exception nao
        explicitWait.until(ExpectedConditions.javaScriptThrowsNoExceptions(""));
        explicitWait.until(ExpectedConditions.not(ExpectedConditions.javaScriptThrowsNoExceptions("")));

        explicitWait.until(ExpectedConditions.numberOfElementsToBe(By.cssSelector(""), 10));
        //wait cho so luong window/tab bang bao nhieu
        explicitWait.until(ExpectedConditions.numberOfWindowsToBe(3));
        //wait cho 1 doan text bang tuyet doi
        explicitWait.until(ExpectedConditions.textToBe(By.cssSelector(""), "abc"));
        //wait cho 1 doan text tuong doi
        explicitWait.until(ExpectedConditions.textMatches(By.cssSelector(""), Pattern.compile("a*b")));
        //wait cho 1 element hay bij change / refresh lai / update lair
        explicitWait.until(ExpectedConditions.refreshed(ExpectedConditions.numberOfWindowsToBe(3)));



    }

    @Test
    public void TC_02_Equal() throws InterruptedException {
        driver.get("https://automationfc.github.io/dynamic-loading/");
        driver.findElement(By.cssSelector("div#start button")).click();
        Thread.sleep(5000);
        Assert.assertEquals(driver.findElement(By.cssSelector("div#finish h4")).getText(), "Hello World!");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));

    }

    @Test
    public void TC_03_Greater_Than() throws InterruptedException {
        driver.get("https://automationfc.github.io/dynamic-loading/");
        driver.findElement(By.cssSelector("div#start button")).click();
        Thread.sleep(10000);
        Assert.assertEquals(driver.findElement(By.cssSelector("div#finish h4")).getText(), "Hello World!");
    }
    //3. clean: delete data test / account / close browser
    @AfterClass
    public void cleanBrowser() {
        driver.quit(); //dong trinh duyet
    }

}
