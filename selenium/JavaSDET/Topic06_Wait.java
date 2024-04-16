package JavaSDET;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic06_Wait {
    WebDriver driver; //khai báo bến
    WebDriverWait explicitWait;
    FluentWait<WebDriver> fluentWait;
    @BeforeClass
    public void InitialBrowser() {
        driver = new FirefoxDriver();
        //explicit Wait
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(15));
        //implicit Wait
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        //Fluent Wait
        fluentWait = new FluentWait<WebDriver>(driver);
    }
    //
    @Test
    public void TC_01_() {

    }
}
