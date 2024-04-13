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

public class Topic_00_Template {
    //1. setup: OS/ Browser / Web / Page / Data /Variable/ Objiect (đủ điều kiện để thực thi testcase)
    WebDriver driver; //khai báo bến
    @BeforeClass
    public void InitialBrowser() {
        driver = new FirefoxDriver();
        driver.get("https://demo.nopcommerce.com");
    }
//
    @Test
    public void TC_01_() {
        WebElement element = driver.findElement(By.xpath("//button[@type='submit']"));
        String color = element.getCssValue("font-size");
        Assert.assertEquals(color, "14px");
        //hex
        String color1 = element.getCssValue("color");
        String color2 = Color.fromString(color1).asHex();
        Assert.assertEquals(color2, "#fff");
        WebElement placeholder = driver.findElement(By.xpath("//input[@id='small-searchterms']"));
        String placeholder1 = placeholder.getAttribute("placeholder");
        Assert.assertEquals(placeholder1, "Search store");
    }
    @Test
    public void TC_02_() {

    }
    //3. clean: delete data test / account / close browser
    @AfterClass
    public void cleanBrowser() {
        driver.quit(); //dong trinh duyet
    }

}
