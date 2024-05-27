package webdriver;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_15_Alert {
    //1. setup: OS/ Browser / Web / Page / Data /Variable/ Objiect (đủ điều kiện để thực thi testcase)
    WebDriver driver; //khai báo bến
    WebDriverWait explicitWait;
    @BeforeClass
    public void InitialBrowser() {
        driver = new FirefoxDriver();
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }
//
    @Test
    public void TC_01_Accept_Alert() throws InterruptedException {
        driver.get("https://automationfc.github.io/basic-form/index.html");
        driver.findElement(By.xpath("//button[text()='Click for JS Alert']")).click();
        Thread.sleep(3000);
        Alert alert = driver.switchTo().alert();
        Assert.assertEquals(alert.getText(), "I am a JS Alert");
        alert.accept();
        Assert.assertEquals(driver.findElement(By.cssSelector("p#result")).getText(), "You clicked an alert successfully");
        //Accept alert
//        driver.switchTo().alert().accept();
//        //Cancel alert
//        driver.switchTo().alert().dismiss();
//        //Get text bên trong Alert (Description)
//        String text = driver.switchTo().alert().getText();
//        //Enter text vào Alert
//        driver.switchTo().alert().sendKeys("");

    }
    @Test
    public void TC_02_Confirm_Alert() throws InterruptedException {
        driver.get("https://automationfc.github.io/basic-form/index.html");
//        driver.findElement(By.xpath("//button[text()='Click for JS Confirm']")).click();
        //vừa wait vừa switch
        Alert alert = explicitWait.until(ExpectedConditions.alertIsPresent());
        Assert.assertEquals(alert.getText(), "I am a JS Confirm");
        alert.dismiss();
        Assert.assertEquals(driver.findElement(By.cssSelector("p#result")).getText(), "You clicked: Cancel");
    }
    @Test
    public void TC_03_Prompt_Alert() {
        driver.get("https://automationfc.github.io/basic-form/index.html");
        driver.findElement(By.xpath("//button[text()='Click for JS Prompt']")).click();
        //vừa wait vừa switch
        Alert alert = explicitWait.until(ExpectedConditions.alertIsPresent());
        Assert.assertEquals(alert.getText(), "I am a JS prompt");
        String value = "Automation FC";
        alert.sendKeys(value);
        alert.accept();
        Assert.assertEquals(driver.findElement(By.cssSelector("p#result")).getText(), "You entered: " +value);

    }
    //3. clean: delete data test / account / close browser
    @AfterClass
    public void cleanBrowser() {
        driver.quit(); //dong trinh duyet
    }

}
