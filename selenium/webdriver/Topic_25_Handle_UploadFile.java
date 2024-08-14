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

import java.io.File;
import java.time.Duration;
import java.util.List;

public class Topic_25_Handle_UploadFile {
    //1. setup: OS/ Browser / Web / Page / Data /Variable/ Objiect (đủ điều kiện để thực thi testcase)
    WebDriver driver; //khai báo bến
    String uploadFolderPath = System.getProperty("user.dir") + File.separator + "uploadFile" + File.separator;
    String anh1 = "5fcc80e2a77a4ce69467ea7f08e49fea.jpg";
    String anh2 = "hinh-nen-full-hd-1920x1080-cho-may-tinh-012.jpg";
    String anh3 = "images.jpg";
    String anh4 = "images (1).jpg";

    String anh1Path = uploadFolderPath + anh1;
    String anh2Path = uploadFolderPath + anh2;
    String anh3Path = uploadFolderPath + anh3;
    String anh4Path = uploadFolderPath + anh4;
    @BeforeClass
    public void InitialBrowser() {
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }
//
    @Test
    public void TC_01_Simple_File() throws InterruptedException {
        driver.get("https://blueimp.github.io/jQuery-File-Upload/");
        By uploadFileElement = By.xpath("//input[@type='file']");
        //load file len - 1 lan nhieu file
        driver.findElement(uploadFileElement).sendKeys(anh1Path + "\n" +anh2Path+ "\n" +anh3Path+ "\n" +anh4Path);
        Thread.sleep(2000);

        //verify file duoc load len
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" +anh1+ "']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" +anh2+ "']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" +anh3+ "']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" +anh4+ "']")).isDisplayed());

        //click upload cho tung file
        List<WebElement> startBtn = driver.findElements(By.xpath("//table//button[@class='btn btn-primary start']"));
        for (WebElement start : startBtn) {
            start.click();
            Thread.sleep(2000);
        }
        //verify cac file duoc upload thanh cong
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[@title='" +anh1+ "']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[@title='" +anh2+ "']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[@title='" +anh3+ "']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[@title='" +anh4+ "']")).isDisplayed());
    }
    @Test
    public void TC_02_Multiple_File() throws InterruptedException {
        driver.get("https://blueimp.github.io/jQuery-File-Upload/");
        By uploadFileElement = By.xpath("//input[@type='file']");
        driver.findElement(uploadFileElement).sendKeys(anh1Path);
        Thread.sleep(2000);
        driver.findElement(uploadFileElement).sendKeys(anh2Path);
        Thread.sleep(2000);
        driver.findElement(uploadFileElement).sendKeys(anh3Path);
        Thread.sleep(2000);
        driver.findElement(uploadFileElement).sendKeys(anh4Path);
        Thread.sleep(2000);

        //verify file duoc load len
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" +anh1+ "']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" +anh2+ "']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" +anh3+ "']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" +anh4+ "']")).isDisplayed());

        //click upload cho tung file
        List<WebElement> startBtn = driver.findElements(By.xpath("//table//button[@class='btn btn-primary start']"));
        for (WebElement start : startBtn) {
            start.click();
            Thread.sleep(2000);
        }
        //verify cac file duoc upload thanh cong
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[@title='" +anh1+ "']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[@title='" +anh2+ "']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[@title='" +anh3+ "']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[@title='" +anh4+ "']")).isDisplayed());
    }
    //3. clean: delete data test / account / close browser
    @AfterClass
    public void cleanBrowser() {
        driver.quit(); //dong trinh duyet
    }

}
