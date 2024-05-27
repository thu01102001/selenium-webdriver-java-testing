package webdriver;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.Color;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class Topic_14_Checkbox_Radio {
    //1. setup: OS/ Browser / Web / Page / Data /Variable/ Objiect (đủ điều kiện để thực thi testcase)
    WebDriver driver; //khai báo bến
    JavascriptExecutor jsExecutor;
    @BeforeClass
    public void InitialBrowser() {
        driver = new FirefoxDriver();
        jsExecutor = (JavascriptExecutor) driver;
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }
//
    @Test
    public void TC_01_() {
        driver.get("https://demos.telerik.com/kendo-ui/checkbox/index");
        //verify checkbox/radio is enable / disable
        Assert.assertFalse(driver.findElement(By.xpath("//label[text()='Leather trim']/preceding-sibling::span/input")).isEnabled());
        Assert.assertTrue(driver.findElement(By.xpath("//label[text()='Leather trim']/preceding-sibling::span/input")).isSelected());
        if(!driver.findElement(By.xpath("//label[text()='Leather trim']/Dual-zone air conditioning::span/input")).isSelected()) {
            driver.findElement(By.xpath("//label[text()='Leather trim']/Dual-zone air conditioning::span/input")).click();
        }
        Assert.assertTrue(driver.findElement(By.xpath("//label[text()='Leather trim']/Dual-zone air conditioning::span/input")).isSelected());
        driver.findElement(By.xpath("//label[text()='Leather trim']/Dual-zone air conditioning::span/input")).click();
        Assert.assertFalse(driver.findElement(By.xpath("//label[text()='Leather trim']/Dual-zone air conditioning::span/input")).isSelected());
    }
    @Test
    public void TC_02_Mul() {
        driver.get("https://automationfc.github.io/multiple-fields/");
        List<WebElement> checkboxes = driver.findElements(By.cssSelector("input.form-checkbox"));
        //click toafn bộ
        for (WebElement checkbox : checkboxes) {
            if(!checkbox.isSelected()) {
                checkbox.click();
                Assert.assertTrue(checkbox.isSelected());
            }
        }
        for (WebElement checkbox : checkboxes) {
            if(checkbox.isSelected()) {
                checkbox.click();
                Assert.assertFalse(checkbox.isSelected());
            }
        }

        driver.findElement(By.xpath("//input[@value='Epilepsy Seizures']")).click();
        driver.findElement(By.xpath("//input[@value='Digestive Problems']")).click();
        Assert.assertTrue(driver.findElement(By.xpath("//input[@value='Epilepsy Seizures']")).isSelected());
        Assert.assertTrue(driver.findElement(By.xpath("//input[@value='Digestive Problems']")).isSelected());

        for (WebElement checkbox : checkboxes) {
            if(!checkbox.isSelected() && checkbox.getAttribute("value").equals("Anemia")) {
                checkbox.click();
                Assert.assertTrue(checkbox.isSelected());
            }
        }
    }

    @Test
    public void TC_03_ubuntu() throws InterruptedException {
        driver.get("https://login.ubuntu.com/");
        //Thẻ input dùng để click
        // Dùng để verify .isSelected()
        By newUserRadio = By.cssSelector("input#id_new_user");
//        By newUserLabel = By.cssSelector("label.new-user");

        // 1 - Dùng thẻ input để click => Fail
        // 2 - Dùng thẻ input để verify => Pass
//        driver.findElement(newUserLabel).click();

        jsExecutor.executeScript("arguments[0].click();", driver.findElement(newUserRadio));
        Assert.assertTrue(driver.findElement(newUserRadio).isSelected());

//        By checkboxLabel = By.xpath("//label[@for='id_accept_tos']");
        By checkboxInput = By.cssSelector("input#id_accept_tos");
        jsExecutor.executeScript("arguments[0].click();", driver.findElement(checkboxInput));
        Assert.assertTrue(driver.findElement(checkboxInput).isSelected());
//        driver.findElement(checkboxLabel).click();
//        Thread.sleep(3000);
//        Assert.assertTrue(driver.findElement(checkboxInput).isSelected());
    }

    @Test
    public void TC_04_Docs() throws InterruptedException {
        driver.get("https://docs.google.com/forms/d/e/1FAIpQLSfiypnd69zhuDkjKgqvpID9kwO29UCzeCVrGGtbNPZXQok0jA/viewform");
        By hcmRadio = By.xpath("//div[@aria-label='Hà Nội']");
        By miQuang = By.xpath("//div[@aria-label='Mì Quảng']");
        Thread.sleep(3000);
        driver.findElement(hcmRadio).click();
        driver.findElement(miQuang).click();
//        Assert.assertTrue(driver.findElement(By.xpath("//div[@aria-label='Hà Nội'] and @aria-checked='true'")).isDisplayed());
        Assert.assertEquals(driver.findElement(hcmRadio).getAttribute("aria-checked"), "true");
        Assert.assertEquals(driver.findElement(miQuang).getAttribute("aria-checked"), "true");

    }

    //3. clean: delete data test / account / close browser
    @AfterClass
    public void cleanBrowser() {
        driver.quit(); //dong trinh duyet
    }

}
