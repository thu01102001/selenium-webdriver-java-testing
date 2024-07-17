package webdriver;

import org.openqa.selenium.Alert;
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
import java.util.Set;

public class Topic_23_Window_Tab {
    //1. setup: OS/ Browser / Web / Page / Data /Variable/ Objiect (đủ điều kiện để thực thi testcase)
    WebDriver driver; //khai báo bến
    @BeforeClass
    public void InitialBrowser() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }
//
    @Test
    public void TC_01_() throws InterruptedException {
        driver.get("https://automationfc.github.io/basic-form/index.html");
        //lay ra ID cua tab / window mà driver dang dung trong do
        String githubID = driver.getWindowHandle();
        driver.findElement(By.xpath("//a[text()='GOOGLE']")).click();
        Thread.sleep(3000);
        //click vao GG link => no se bat len 1 tab moi va tu nhay qua
        // nhung driver ko tu nhay => no van o tab cu
        switchToWindowByID(githubID);
        Thread.sleep(3000);
//        System.out.println("-------");
//        System.out.println("ID: " +driver.getWindowHandle());
//        System.out.println("Title: " +driver.getTitle());
//        System.out.println("URL: " +driver.getCurrentUrl());
        driver.findElement(By.xpath("//textarea[@title='Tìm kiếm']")).sendKeys("automation FC");
        Thread.sleep(3000);
        //switch ve lai
        String googleID = driver.getWindowHandle();
        switchToWindowByID(googleID);
        System.out.println("ID: " +driver.getWindowHandle());
        System.out.println("Title: " +driver.getTitle());
        System.out.println("URL: " +driver.getCurrentUrl());
        //click vafo fb
        Thread.sleep(3000);
        driver.findElement(By.xpath("//a[text()='FACEBOOK']")).click();
        Thread.sleep(3000);
        //lay het toan bo cac id cua window/tab
        switchToWindowByTitle("Facebook – log in or sign up");
        System.out.println("Page: " +driver.getTitle());
        switchToWindowByTitle("Selenium WebDriver");
        Thread.sleep(3000);
        driver.findElement(By.xpath("//a[text()='LAZADA']")).click();
        switchToWindowByTitle("Lazada - Mua Sắm Hàng Chất Giá Tốt Online");
        Thread.sleep(3000);
        Set<String> allWindowsIDs = driver.getWindowHandles();
        for (String id : allWindowsIDs) {
            if(!id.equals(githubID)) {
                driver.switchTo().window(id);
                Thread.sleep(2000);
                driver.close();
            }
        }
    }

    private void switchToWindowByTitle(String pageTitle) {
        Set<String> allWindowsIDs = driver.getWindowHandles();
        for (String id : allWindowsIDs) {
            //moi lan duyet se cho no switch vao truoc
            driver.switchTo().window(id);
            //get ra title cua window hien tai
            if(pageTitle.equals(pageTitle)) {
                break;
            }
        }
    }

    private void switchToWindowByID(String githubID) {
        Set<String> allWindow =  driver.getWindowHandles();
        for (String id : allWindow) {
            if (!id.equals(githubID)) {
                driver.switchTo().window(id);
            }
        }
    }

    @Test
    public void TC_02_() throws InterruptedException {
        driver.get("http://live.techpanda.org/");
        driver.findElement(By.xpath("//a[text()='Mobile']")).click();
        driver.findElement(By.xpath("//a[text()='Sony Xperia']/ancestor::div[@class='product-info']//a[text()='Add to Compare']")).click();
        Assert.assertEquals(driver.findElement(By.cssSelector("li.success-msg span")).getText(), "The product Sony Xperia has been added to comparison list.");
        driver.findElement(By.xpath("//a[text()='Samsung Galaxy']/ancestor::div[@class='product-info']//a[text()='Add to Compare']")).click();
        Assert.assertEquals(driver.findElement(By.cssSelector("li.success-msg span")).getText(), "The product Samsung Galaxy has been added to comparison list.");
        driver.findElement(By.xpath("//button[@title='Compare']")).click();
        Thread.sleep(3000);
        Set<String> windowsNew = driver.getWindowHandles();
        for (String windows : windowsNew) {
            if(!windows.equals(driver.getWindowHandle())) {
                driver.switchTo().window(windows);
            }
        }
        Assert.assertEquals(driver.getTitle(), "Products Comparison List - Magento Commerce");
        Thread.sleep(2000);
        driver.findElement(By.xpath("//button[@title='Close Window']")).click();
        Set<String> titleWindows = driver.getWindowHandles();
        for(String title : titleWindows) {
            driver.switchTo().window(title);
            if(!title.equals("Mobile")) {
                break;
            }
        }
        Thread.sleep(3000);
        driver.findElement(By.xpath("//a[text()='Clear All']")).click();
        Thread.sleep(2000);
        Alert alert = driver.switchTo().alert();
        alert.accept();
        Thread.sleep(3000);
        Assert.assertEquals(driver.findElement(By.cssSelector("li.success-msg span")).getText(), "The comparison list was cleared.");
        Thread.sleep(3000);

    }
    @Test
    public void TC_03() throws InterruptedException {
        driver.get("https://dictionary.cambridge.org/vi/");
        driver.findElement(By.xpath("//div[@class='hdn hdib-s']//span[text()='Đăng nhập']")).click();
        Set<String> newWindows = driver.getWindowHandles();
        for (String windows : newWindows) {
            if(!windows.equals(driver.getWindowHandle())) {
                driver.switchTo().window(windows);
            }
        }
        Thread.sleep(3000);
        driver.findElement(By.xpath("//input[@value='Log in']")).click();
        Assert.assertEquals(driver.findElement(By.xpath("//input[@placeholder='Email *']/parent::div/span")).getText(), "This field is required");
        Assert.assertEquals(driver.findElement(By.xpath("//input[@placeholder='Password *']/parent::div/span")).getText(), "This field is required");
        driver.close();
        Set<String> titleWindows = driver.getWindowHandles();
        for(String title : titleWindows) {
            driver.switchTo().window(title);
            if(!title.equals("Cambridge Dictionary | Từ điển tiếng Anh, Bản dịch & Từ điển từ đồng nghĩa")) {
                break;
            }
        }
        Thread.sleep(3000);
        driver.findElement(By.cssSelector("input#searchword")).sendKeys("automationFC");
        driver.findElement(By.xpath("//button[@title='Tìm kiếm']")).click();
        Assert.assertEquals(driver.findElement(By.cssSelector("h1.lpb-10.lbb span")).getText(), "automationFC");
        Thread.sleep(3000);
    }
    @Test
    public void TC_04() throws InterruptedException {
        driver.get("https://courses.dce.harvard.edu/");
        driver.findElement(By.xpath("//a[@data-action='login']")).click();
        Set<String> newWindows = driver.getWindowHandles();
        for (String windows : newWindows) {
            if(!windows.equals(driver.getWindowHandle())) {
                driver.switchTo().window(windows);
            }
        }
        Thread.sleep(3000);
        Assert.assertEquals(driver.findElement(By.xpath("//h1[@class='c85308b88 c6689a483']")).getText(), "DCE Login Portal");
        driver.close();
        Thread.sleep(2000);
        Set<String> titleWindows = driver.getWindowHandles();
        for(String title : titleWindows) {
            driver.switchTo().window(title);
            if(!title.equals("DCE Course Search")) {
                break;
            }
        }
        Thread.sleep(2000);
        Assert.assertTrue(driver.findElement(By.cssSelector("div#sam-wait")).isDisplayed());
        Thread.sleep(3000);
    }
    //3. clean: delete data test / account / close browser
    @AfterClass
    public void cleanBrowser() {
        driver.quit(); //dong trinh duyet
    }

}
