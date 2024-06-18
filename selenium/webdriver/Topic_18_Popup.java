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

public class Topic_18_Popup {
    //1. setup: OS/ Browser / Web / Page / Data /Variable/ Objiect (đủ điều kiện để thực thi testcase)
    WebDriver driver; //khai báo bến
    @BeforeClass
    public void InitialBrowser() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }
//
    @Test
    public void TC_01_NgoaiNgu24h() throws InterruptedException {
        driver.get("https://ngoaingu24h.vn/");
        Thread.sleep(3000);
        driver.findElement(By.cssSelector("button.login_.icon-before")).click();
        Thread.sleep(3000);
        By loginPopup = By.cssSelector("div#modal-login-v1 div.modal-content");
        Assert.assertTrue(driver.findElement(loginPopup).isDisplayed());
        driver.findElement(By.cssSelector("input#account-input")).sendKeys("automationFC");
        driver.findElement(By.cssSelector("input#password-input")).sendKeys("automationFC");
        driver.findElement(By.cssSelector("button.btn-login-v1")).click();
        Thread.sleep(3000);
        Assert.assertEquals(driver.findElement(By.cssSelector("div#modal-login-v1 div.error-login-panel")).getText(), "Tài khoản không tồn tại!");
        driver.findElement(By.cssSelector("div#modal-login-v1 button.close")).click();
        Thread.sleep(3000);
        Assert.assertFalse(driver.findElement(loginPopup).isDisplayed());

    }
    @Test
    public void TC_02_() throws InterruptedException {
        driver.get("https://skills.kynaenglish.vn/dang-nhap");
        By loginPopup = By.cssSelector("div#k-popup-account-login-mb div.modal-content");
        Assert.assertTrue(driver.findElement(loginPopup).isDisplayed());
        driver.findElement(By.cssSelector("input#user-login")).sendKeys("automationFC");
        driver.findElement(By.cssSelector("input#user-password")).sendKeys("automationFC");
        driver.findElement(By.cssSelector("button#btn-submit-login")).click();
        Thread.sleep(3000);
        Assert.assertEquals(driver.findElement(By.cssSelector("div#password-form-login-message")).getText(), "Sai tên đăng nhập hoặc mật khẩu");

    }

    @Test
    public void TC_03() throws InterruptedException {
        driver.get("https://tiki.vn/");
        //POpup co dinh hien thi khi vua mo site ra
        // khi dong lai thi ko hien trong html nua
        Assert.assertTrue(driver.findElement(By.cssSelector("div#VIP_BUNDLE")).isDisplayed());
        driver.findElement(By.cssSelector("img[alt='close-icon']")).click();
        driver.findElement(By.xpath("//div[@data-view-id='header_header_account_container']")).click();
        Assert.assertTrue(driver.findElement(By.cssSelector("div.ReactModal__Content--after-open")).isDisplayed());
        driver.findElement(By.cssSelector("p.login-with-email")).click();
        driver.findElement(By.xpath("//button[text()='Đăng nhập']")).click();
        Assert.assertEquals(driver.findElement(By.xpath("//input[@name='email']/parent::div/following-sibling::span[1]")).getText(), "Email không được để trống");
        Assert.assertEquals(driver.findElement(By.xpath("//input[@name='email']/parent::div/following-sibling::span[2]")).getText(), "Mật khẩu không được để trống");
        Thread.sleep(2000);
        driver.findElement(By.cssSelector("input[name='email']")).sendKeys("automationFC@gmail.com");
        driver.findElement(By.cssSelector("input[type='password']")).sendKeys("automationFC");
        driver.findElement(By.xpath("//button[text()='Đăng nhập']")).click();
        Assert.assertEquals(driver.findElement(By.xpath("//form/span")).getText(), "Thông tin đăng nhập không đúng");
        Thread.sleep(2000);
        driver.findElement(By.cssSelector("img.close-img")).click();
        Thread.sleep(2000);
        Assert.assertEquals(driver.findElements(By.cssSelector("div.ReactModal__Content--after-open")).size(), 0);

    }

    @Test
    public void TC_04_Facebook() throws InterruptedException {
        driver.get("https://www.facebook.com/");
        driver.findElement(By.xpath("//a[text()='Create new account']")).click();
        Thread.sleep(2000);
        Assert.assertTrue(driver.findElement(By.cssSelector("div._n3")).isDisplayed());
        driver.findElement(By.cssSelector("img._8idr.img")).click();
        Assert.assertEquals(driver.findElements(By.cssSelector("div._n3")).size(), 0);
    }
    //3. clean: delete data test / account / close browser
    @AfterClass
    public void cleanBrowser() {
        driver.quit(); //dong trinh duyet
    }

}
