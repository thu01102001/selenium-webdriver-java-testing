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

public class Topic_16_Authentication_Alert {
    //1. setup: OS/ Browser / Web / Page / Data /Variable/ Objiect (đủ điều kiện để thực thi testcase)
    WebDriver driver; //khai báo bến
    String username = "admin";
    String password = "admin";
    @BeforeClass
    public void InitialBrowser() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }
//
    @Test
    public void TC_01_Authentication_url() {
        driver.get("http://" +username+ ":" +password+ "@" + "the-internet.herokuapp.com/basic_auth");
        //By pass: ko phải nhập username, password
        Assert.assertEquals(driver.findElement(By.cssSelector("div.example p")).getText(), "Congratulations! You must have the proper credentials.");

    }
    @Test
    public void TC_02_Authentication_Navigate() {
        driver.get("https://the-internet.herokuapp.com/");
        String basicAuth = driver.findElement(By.xpath("//a[text()='Basic Auth']")).getAttribute("href");
        driver.get(getAuthentication(basicAuth, username, password));
        Assert.assertEquals(driver.findElement(By.cssSelector("div.example p")).getText(), "Congratulations! You must have the proper credentials.");
    }
    public String getAuthentication(String link, String username, String password) {
        String linkArray[] = link.split("//");
        return linkArray[0] + "//" + username + ":" + password + "@" + linkArray[1];
    }
    //3. clean: delete data test / account / close browser
    @AfterClass
    public void cleanBrowser() {
        driver.quit(); //dong trinh duyet
    }

}
