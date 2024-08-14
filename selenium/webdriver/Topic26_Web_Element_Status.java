package webdriver;

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

public class Topic26_Web_Element_Status {
    //1. setup: OS/ Browser / Web / Page / Data /Variable/ Objiect (đủ điều kiện để thực thi testcase)
    WebDriver driver; //khai báo bến
    WebDriverWait explicitWait;
    @BeforeClass
    public void InitialBrowser() {
        driver = new FirefoxDriver();
        driver.manage().window().maximize();

        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }
//
    @Test
    public void TC_01_Visible() {
        driver.get("https://www.facebook.com/");

        //1. element co tren UI va co trong HTML
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input#email")));
        // 15s la tong thoi gian de cho
//       // 3 truong hop xay ra
        // 1. Element thoa man dieu kien cua wait ngay lap tuc (ko can cho het 15s)
        // 2. Vao wait nhung chua thoa man :
        // 2.1. Cho tiep va lap lai dieu kien va tim thay thi quay lai giong 1 (check den khi het 15s)
        // 2.2. Cho tiep va lap lai khong thay den het timeout luon
        // 3. Het time out va ko thay element => Fail

    }
    @Test
    public void TC_02_Invisible() {
        driver.get("https://www.facebook.com/");
        //2. Element ko co tren UI, co trong HTML
        driver.findElement(By.xpath("//a[@data-testid='open-registration-form-button']")).click();

        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='reg_email__']")));

        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//input[@aria-label='Re-enter email address']")));

        //3. Element ko co tren UI, ko co trong HTML
        driver.findElement(By.xpath("//div[text()='Sign Up']/parent::div/preceding-sibling::img")).click();

        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//input[@aria-label='Re-enter email address']")));
    }
    @Test
    public void TC_02_Presence() {
        driver.get("https://www.facebook.com/");
        driver.findElement(By.xpath("//a[@data-testid='open-registration-form-button']")).click();

        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='reg_email__']")));

        driver.findElement(By.xpath("//input[@name='reg_email__']")).sendKeys("thu@gmail.com");

        //1
        explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@aria-label='Re-enter email address']")));

        //2
        driver.findElement(By.xpath("//input[@name='reg_email__']")).clear();
        explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@aria-label='Re-enter email address']")));

    }
    @Test
    public void TC_03_Staleness() {
        driver.get("https://www.facebook.com/");
        driver.findElement(By.xpath("//a[@data-testid='open-registration-form-button']")).click();
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='reg_email__']")));

        //tai thoi diem nay confirm email dang co trong html
        WebElement confirmEmail = driver.findElement(By.xpath("//input[@aria-label='Re-enter email address']"));
        //click dong popup
        driver.findElement(By.xpath("//div[text()='Sign Up']/parent::div/preceding-sibling::img")).click();

        explicitWait.until(ExpectedConditions.stalenessOf(confirmEmail));

    }
    //3. clean: delete data test / account / close browser
    @AfterClass
    public void cleanBrowser() {
        driver.quit(); //dong trinh duyet
    }

}
