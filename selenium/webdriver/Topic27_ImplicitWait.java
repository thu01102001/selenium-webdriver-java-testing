package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.Color;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic27_ImplicitWait {
    //1. setup: OS/ Browser / Web / Page / Data /Variable/ Objiect (đủ điều kiện để thực thi testcase)
    WebDriver driver; //khai báo bến
    @BeforeClass
    public void InitialBrowser() {
        driver = new FirefoxDriver();
        driver.get("https://automationfc.github.io/dynamic-loading/");
    }
//
//    @Test
//    public void TC_01_Dont_Set() {
//        driver.findElement(By.cssSelector("div#start button")).click();
//        Assert.assertEquals(driver.findElement(By.cssSelector("div#finish h4")).getText(), "Hello World!");
//    }
//    @Test
//    public void TC_02_Less_Than() {
//        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
//        driver.findElement(By.cssSelector("div#start button")).click();
//        Assert.assertEquals(driver.findElement(By.cssSelector("div#finish h4")).getText(), "Hello World!");
//
//    }
    @Test
    public void TC_03_Equal() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.findElement(By.cssSelector("div#start button")).click();
        Assert.assertEquals(driver.findElement(By.cssSelector("div#finish h4")).getText(), "Hello World!");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));


    }

    @Test
    public void TC_04_Greater_Than() {
        driver.findElement(By.cssSelector("div#start button")).click();
        Assert.assertEquals(driver.findElement(By.cssSelector("div#finish h4")).getText(), "Hello World!");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }
    //3. clean: delete data test / account / close browser
    @AfterClass
    public void cleanBrowser() {
        driver.quit(); //dong trinh duyet
    }

}
