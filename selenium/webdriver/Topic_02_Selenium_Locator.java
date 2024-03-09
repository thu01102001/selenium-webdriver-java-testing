package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

public class Topic_02_Selenium_Locator {
    WebDriver driver; //khai báo biến
    String fullName = "Automation";
    @BeforeClass
    public void InitialBrowser() {
        //mo browser: khoi tao
        driver = new FirefoxDriver();
        //mo app len den man hinh login
        driver.get("https://demo.nopcommerce.com/login");
    }
    @Test
    public void TC_01_() {
        //tuong tac len Email Address textbox
        //<input class="email" autofocus="" type="email" data-val="true" data-val-email="Wrong email" data-val-required="Please enter your email" id="Email" name="Email">

        //HTML Source Code
        //The - thuoc tinh - gia tri thoc tinh
        //Tagname - Attribute - Value

        //XPath: //tagname[@attribute='value']
        //CSS: tagname[attribute='value']

        //Tuong tac len Email address textbox
        // 8 loai locator de tim Email address nay

        //tim element
        driver.findElement(By.id(""));
        //1 thao tac len luon (dung 1 lan)
        //sau dau . goi ham/bien cua thu vien do ra
        driver.findElement(By.id("")).click();
        //2 luu du lieu lai (dung nhieu lan)
        WebElement emailTextBox = driver.findElement(By.id(""));
        emailTextBox.clear();
        emailTextBox.isDisplayed();
        //tim nhieu element
        List<WebElement> textboxes = driver.findElements(By.cssSelector(""));
    }
    //
    @Test
    public void TC_02_() {

    }
    @AfterClass
    public void cleanBrowser() {
        driver.quit(); //dong trinh duyet
    }

}
