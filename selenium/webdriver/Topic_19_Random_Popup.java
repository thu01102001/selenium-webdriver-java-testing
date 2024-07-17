package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.Color;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_19_Random_Popup {
    //1. setup: OS/ Browser / Web / Page / Data /Variable/ Objiect (đủ điều kiện để thực thi testcase)
    WebDriver driver; //khai báo bến
    @BeforeClass
    public void InitialBrowser() {
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }
//
    @Test
    public void TC_01_() throws InterruptedException {
        driver.get("https://www.javacodegeeks.com/");

        By newLetterPopupBy = By.xpath("//div[@data-title='Newsletter-Books Anime Brief' and not(contains(@style, 'display:none'))]");
        //Hien thi thi close di, ko hien thi thi action tiep
        if(driver.findElements(newLetterPopupBy).size() > 0 && driver.findElements(newLetterPopupBy).get(0).isDisplayed()) {
            System.out.println("=======if=========");
            driver.findElement(By.xpath("//a[text()='×']")).click();
            Thread.sleep(2000);
        }
        else {
            System.out.println("=========else=========");
            driver.findElement(By.cssSelector("input#search-input")).sendKeys("Agile");
            driver.findElement(By.xpath("//button[@id='search-submit']/span[@class='tie-icon-search tie-search-icon']")).click();
            Thread.sleep(3000);
            Assert.assertEquals(driver.findElement(By.cssSelector("header h1.page-title span")).getText(), "Agile");
        }
    }
    @Test
    public void TC_02_VNK() throws InterruptedException {
        driver.get("https://vnk.edu.vn/");
        Thread.sleep(30000);
        By popup = By.cssSelector("div#popmake-23751");
        if(driver.findElements(popup).size() > 0 && driver.findElements(popup).get(0).isDisplayed()) {
            System.out.println("====if===");
            driver.findElement(By.cssSelector("button.pum-close.popmake-close")).click();
            Thread.sleep(2000);
        }
        else {
            System.out.println("========else=====");
            driver.findElement(By.xpath("//div[@id='mega-menu-wrap-primary']//a[text()='Liên hệ']")).click();
            Thread.sleep(2000);
            Assert.assertEquals(driver.getCurrentUrl(), "https://vnk.edu.vn/lien-he/");
        }
    }
    @Test
    public void TC_03_Dehieu() throws InterruptedException {
        driver.get("https://dehieu.vn/");
        Thread.sleep(30000);
        By popup = By.cssSelector("div.modal-content.css-modal-bt");
        if(driver.findElements(popup).size() > 0 && driver.findElements(popup).get(0).isDisplayed()) {
            System.out.println("====if===");
            driver.findElement(By.cssSelector("div.modal-content.css-modal-bt span[aria-hidden='true']")).click();
            Thread.sleep(5000);
            Assert.assertFalse(driver.findElement(popup).isDisplayed());
//            Assert.assertEquals(driver.findElements(popup).size(), 0);
        }
        else {
            System.out.println("========else=====");
            driver.findElement(By.cssSelector("c_header__search-wrapper")).click();
            driver.findElement(By.cssSelector("input[name='key']")).sendKeys("Khóa học Đọc bản vẽ và đo bóc khối lượng điện");
            driver.findElement(By.cssSelector("button.header-search")).click();
            Assert.assertTrue(driver.findElement(By.cssSelector("main#main h4")).isDisplayed());
        }
    }
    //3. clean: delete data test / account / close browser
    @AfterClass
    public void cleanBrowser() {
        driver.quit(); //dong trinh duyet
    }

}
