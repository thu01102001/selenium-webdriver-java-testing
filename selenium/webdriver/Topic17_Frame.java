package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic17_Frame {
    //1. setup: OS/ Browser / Web / Page / Data /Variable/ Objiect (đủ điều kiện để thực thi testcase)
    WebDriver driver; //khai báo bến
    JavascriptExecutor jsExecutor;
    @BeforeClass
    public void InitialBrowser() {
        driver = new ChromeDriver();
        jsExecutor = (JavascriptExecutor) driver;
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }
//
    @Test
    public void TC_01_IFrame() throws InterruptedException {
        driver.get("https://www.formsite.com/templates/education/campus-safety-survey/");
        driver.findElement(By.cssSelector("img[alt='Campus Safety Survey']")).click();
        Thread.sleep(3000);
//        driver.switchTo().frame(0);
//        driver.switchTo().frame("frame-one85593366");
        driver.switchTo().frame(driver.findElement(By.id("frame-one85593366")));
        new Select(driver.findElement(By.cssSelector("select#RESULT_RadioButton-2"))).selectByVisibleText("Junior");
        Thread.sleep(3000);
        new Select(driver.findElement(By.id("RESULT_RadioButton-3"))).selectByVisibleText("East Dorm");
        jsExecutor.executeScript("arguments[0].click();", driver.findElement(By.cssSelector("input#RESULT_RadioButton-4_0")));
        Thread.sleep(3000);

        driver.switchTo().defaultContent();
        Thread.sleep(3000);
        driver.findElement(By.cssSelector("a.fs-btn.fs-btn--transparent-white.pull-right.menu-item-login")).click();
        Thread.sleep(3000);
        driver.findElement(By.cssSelector("button#login")).click();
        Assert.assertEquals(driver.findElement(By.cssSelector("div#message-error")).getText(), "Username and password are both required.");
    }
    @Test
    public void TC_02_() {
        driver.get("https://toidicodedao.com/");
        driver.switchTo().frame(driver.findElement(By.cssSelector("iframe[name='f4162a1c7c7f43b45']")));
        Assert.assertEquals(driver.findElement(By.xpath("//a[text()='Tôi đi code dạo']/parent::div/following-sibling::div[text()]")).getText(), "406,741 followers");
    }

    @Test
    public void TC_03() throws InterruptedException {
        driver.get("https://netbanking.hdfcbank.com/netbanking/");
        driver.switchTo().frame(driver.findElement(By.xpath("//frame[@name='login_page']")));
        driver.findElement(By.xpath("//input[@name='fldLoginUserId']")).sendKeys("automation");
        driver.findElement(By.cssSelector("a.login-btn")).click();
        Thread.sleep(3000);
        driver.findElement(By.cssSelector("input#keyboard")).sendKeys("123456789");
        driver.findElement(By.cssSelector("a#loginBtn")).click();
        Assert.assertEquals(driver.findElement(By.cssSelector("p.error-msg")).getText(), "Customer ID/IPIN (Password) is invalid. Please try again.");

    }
    //3. clean: delete data test / account / close browser
    @AfterClass
    public void cleanBrowser() {
        driver.quit(); //dong trinh duyet
    }

}
