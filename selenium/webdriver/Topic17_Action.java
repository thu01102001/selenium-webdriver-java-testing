package webdriver;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class Topic17_Action {
    //1. setup: OS/ Browser / Web / Page / Data /Variable/ Objiect (đủ điều kiện để thực thi testcase)
    WebDriver driver; //khai báo bến
    JavascriptExecutor jsExecutor;
    Actions actions;
    @BeforeClass
    public void InitialBrowser() {
        driver = new FirefoxDriver();
        jsExecutor = (JavascriptExecutor) driver;
        actions = new Actions(driver);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }
//
    @Test
    public void TC_01_() {
        driver.get("https://automationfc.github.io/jquery-tooltip/");
        WebElement tooltip = driver.findElement(By.xpath("//a[text()='ThemeRoller']"));
        actions.moveToElement(tooltip).perform();
        Assert.assertEquals(driver.findElement(By.cssSelector("div.ui-tooltip-content")).getText(), "ThemeRoller: jQuery UI's theme builder application");
    }
    @Test
    public void TC_02_() throws InterruptedException {
        driver.get("https://myntra.com/");
        actions.moveToElement(driver.findElement(By.xpath("//div[@class='desktop-navLink']/a[text()='Kids']"))).perform();
        Thread.sleep(3000);
        driver.findElement(By.xpath("//a[@class='desktop-categoryLink' and text()='Kurta Sets']")).click();
        Thread.sleep(3000);
        Assert.assertEquals(driver.findElement(By.cssSelector("span.breadcrumbs-crumb")).getText(), "Kids Wear Online Store");
    }

    @Test
    public void TC_03_Click_And_Hold_Block() throws InterruptedException {
        driver.get("https://automationfc.github.io/jquery-selectable/");
        List<WebElement> allNumber = driver.findElements(By.cssSelector("ol#selectable li"));
        actions.clickAndHold(allNumber.get(0)).moveToElement(allNumber.get(3)).release().perform();
        Thread.sleep(3000);
        List<WebElement> allNumberSelected = driver.findElements(By.cssSelector("ol#selectable li.ui-selected"));
        Assert.assertEquals(allNumberSelected.size(), 4);
    }
    @Test
    public void TC_04_Click_And_Hold_Random() throws InterruptedException {
        driver.get("https://automationfc.github.io/jquery-selectable/");
        List<WebElement> allNumber = driver.findElements(By.cssSelector("ol#selectable li"));
        //Nhấn Ctrl xuống (chưa nhả ra)
        actions.keyDown(Keys.CONTROL).perform();
        actions.click(allNumber.get(0)).click(allNumber.get(3)).click(allNumber.get(7)).click(allNumber.get(10)).click(allNumber.get(13)).click(allNumber.get(17)).perform();

        Thread.sleep(3000);
        List<WebElement> allNumberSelected = driver.findElements(By.cssSelector("ol#selectable li.ui-selected"));
        Assert.assertEquals(allNumberSelected.size(), 6);
    }

    @Test
    public void TC_05_DoubleClick() {
        driver.get("https://automationfc.github.io/basic-form/index.html");
        jsExecutor.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//button[text()='Double click me']")));
        actions.doubleClick(driver.findElement(By.xpath("//button[text()='Double click me']"))).perform();
        Assert.assertEquals(driver.findElement(By.cssSelector("p#demo")).getText(), "Hello Automation Guys!");
    }

    @Test
    public void TC_06_DropAndDrag() {

    }
    //3. clean: delete data test / account / close browser
    @AfterClass
    public void cleanBrowser() {
        driver.quit(); //dong trinh duyet
    }

}
