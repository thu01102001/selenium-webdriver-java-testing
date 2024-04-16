package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic12_DropdownCustom {
    //1. setup: OS/ Browser / Web / Page / Data /Variable/ Objiect (đủ điều kiện để thực thi testcase)
    WebDriver driver; //khai báo bến
    WebDriverWait explicitWait;
    @BeforeClass
    public void InitialBrowser() {
        driver = new FirefoxDriver();
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(15));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

    }
//
    @Test
    public void TC_01_() {
        driver.get("https://jqueryui.com/resources/demos/selectmenu/default.html");
        //Hành vi (behavior) để thao tác lên dropdown
        //1. Chờ cho dropdown có thể thao tác lên được (clickable)
        //2. Click vào element nào để nó xổ cái dropdown ra
        //3. Chờ cho tất cả các item được load ra
        //4. Tìm item nào đúng với mong đợi
        //5. Click vào item đó


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
