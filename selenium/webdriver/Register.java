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

import java.awt.*;
import java.time.Duration;
import java.util.Random;

public class Register {
    //1. setup: OS/ Browser / Web / Page / Data /Variable/ Objiect (đủ điều kiện để thực thi testcase)
    WebDriver driver; //khai báo bến
    Random rand;
    Random rand1;
    @BeforeClass
    public void InitialBrowser() {
        driver = new ChromeDriver();
    }
//
    @Test
    public void TC_01_TextboxTextarea() throws InterruptedException {
        driver.get("http://live.techpanda.org/");
        rand = new Random();
        String firstName, lastName, emailAddress, password, fullName;
        firstName = "Thu";
        lastName = "Nguyen";
        fullName = firstName + " " + lastName;
        emailAddress = "thunguyen" + rand.nextInt(99999) + "@gmail.com";
        password = "123456789";
        driver.findElement(By.xpath("//div[@class='footer-container']//a[@title='My Account']")).click();
        driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
        driver.findElement(By.cssSelector("input#firstname")).sendKeys(firstName);
        driver.findElement(By.cssSelector("input#lastname")).sendKeys(lastName);
        driver.findElement(By.cssSelector("input#email_address")).sendKeys(emailAddress);
        driver.findElement(By.cssSelector("input#password")).sendKeys(password);
        driver.findElement(By.cssSelector("input#confirmation")).sendKeys(password);
        driver.findElement(By.xpath("//button[@title='Register']")).click();
        //Tuyệt đối
        Assert.assertEquals(driver.findElement(By.xpath("//li[@class='success-msg']//span")).getText(), "Thank you for registering with Main Website Store.");
        String contactInformationText = driver.findElement(By.xpath("//h3[text()='Contact Information']/parent::div/following-sibling::div/p")).getText();
        System.out.printf(contactInformationText);
        //Tương đối
        Assert.assertTrue(contactInformationText.contains(fullName)); //Full name
        driver.findElement(By.xpath("//h3[text()='Contact Information']/following-sibling::a")).click();
        Assert.assertEquals(driver.findElement(By.cssSelector("input#firstname")).getAttribute("value"), firstName);
        Assert.assertEquals(driver.findElement(By.cssSelector("input#lastname")).getAttribute("value"), lastName);
        Assert.assertEquals(driver.findElement(By.cssSelector("input#email")).getAttribute("value"), emailAddress);
        driver.findElement(By.xpath("//li[@class='level0 nav-1 first']/a")).click();
        driver.findElement(By.xpath("//h2/a[@title='Samsung Galaxy']")).click();
        driver.findElement(By.xpath("//a[text()='Add Your Review']")).click();
        driver.findElement(By.xpath("//input[@id='Quality 1_5']")).click();
        driver.findElement(By.xpath("//textarea[@id='review_field']")).sendKeys("Good application\n" + "Pretty easy to navigate.");
        driver.findElement(By.xpath("//input[@id='summary_field']")).sendKeys("Best phone");
        driver.findElement(By.xpath("//input[@id='nickname_field']")).clear();
        driver.findElement(By.xpath("//input[@id='nickname_field']")).sendKeys(firstName);
        driver.findElement(By.xpath("//button[@title='Submit Review']")).click();
        Assert.assertEquals(driver.findElement(By.xpath("//li[@class='success-msg']//span")).getText(), "Your review has been accepted for moderation.");

        //logout
        driver.findElement(By.xpath("//a[@class='skip-link skip-account']")).click();
        driver.findElement(By.xpath("//a[@title='Log Out']")).click();
        Thread.sleep(6000);
        Assert.assertEquals(driver.getCurrentUrl(), "http://live.techpanda.org/index.php/");

    }
    @Test
    public void TC_02_TextboxTextArea() throws InterruptedException {
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        driver.findElement(By.xpath("//input[@name='username']")).sendKeys("Admin");
        driver.findElement(By.xpath("//input[@name='password']")).sendKeys("admin123");
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        driver.findElement(By.xpath("//ul[@class='oxd-main-menu']/li[2]")).click();
        driver.findElement(By.xpath("//a[text()='Add Employee']/parent::li")).click();
        //nhập dữ liệu
        String firstName, lastName;
        firstName = "Thu";
        lastName = "Nguyễn";

        driver.findElement(By.xpath("//input[@name='firstName']")).sendKeys(firstName);
        driver.findElement(By.xpath("//input[@name='lastName']")).sendKeys(lastName);
        WebElement employeeid = driver.findElement(By.xpath("//label[text()='Employee Id']/parent::div/following-sibling::div/input"));
        String id = employeeid.getAttribute("value");
        System.out.printf(id);
        String username, password;
        rand1 = new Random();
        username = "nguyenthilethu" + rand1.nextInt(99999);
        password = "123456789aa";
        driver.findElement(By.xpath("//input[@type='checkbox']//following-sibling::span")).click();
        driver.findElement(By.xpath("//div[@class='oxd-form-row']//input[@autocomplete='off']")).sendKeys(username);
        driver.findElement(By.xpath("//label[text()='Password']//parent::div//following-sibling::div/input")).sendKeys(password);
        driver.findElement(By.xpath("//label[text()='Confirm Password']//parent::div//following-sibling::div/input")).sendKeys(password);
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        //verìy dữ liệu
        Thread.sleep(15000);
        Assert.assertEquals(driver.findElement(By.xpath("//input[@name='firstName']")).getAttribute("value"), firstName);
        Assert.assertEquals(driver.findElement(By.xpath("//input[@name='lastName']")).getAttribute("value"), lastName);
        Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Employee Id']/parent::div/following-sibling::div/input")).getAttribute("value"), id);
        //step8
        driver.findElement(By.xpath("//a[text()='Immigration']")).click();
        driver.findElement(By.xpath("//h6[text()='Assigned Immigration Records']/following-sibling::button")).click();
        String number, cmt;
        number = "0364697188";
        cmt = "Nguyễn\n" +
                "Thị\n" +
                "Lệ\n" +
                "Thu";
        driver.findElement(By.xpath("//label[text()='Number']//parent::div//following-sibling::div/input")).sendKeys(number);
        driver.findElement(By.xpath("//label[text()='Comments']//parent::div//following-sibling::div/textarea")).sendKeys(cmt);
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        Thread.sleep(6000);
        driver.findElement(By.xpath("//div[@class='oxd-table-cell-actions']//button[2]")).click();
        Thread.sleep(6000);
        //verify
        Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Number']//parent::div//following-sibling::div/input")).getAttribute("value"), number);
        Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Comments']//parent::div//following-sibling::div/textarea")).getAttribute("value"), cmt);
        Thread.sleep(6000);

        driver.findElement(By.xpath("//span[@class='oxd-userdropdown-tab']")).click();
        driver.findElement(By.xpath("//a[text()='Logout']")).click();
        Thread.sleep(3000);
        driver.findElement(By.xpath("//input[@name='username']")).sendKeys(username);
        driver.findElement(By.xpath("//input[@name='password']")).sendKeys(password);
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        driver.findElement(By.xpath("//span[text()='My Info']//parent::a")).click();
        Thread.sleep(6000);
        Assert.assertEquals(driver.findElement(By.xpath("//input[@name='firstName']")).getAttribute("value"), firstName);
        Assert.assertEquals(driver.findElement(By.xpath("//input[@name='lastName']")).getAttribute("value"), lastName);
        Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Employee Id']/parent::div/following-sibling::div/input")).getAttribute("value"), id);
        driver.findElement(By.xpath("//a[text()='Immigration']")).click();
        driver.findElement(By.xpath("//div[@class='oxd-table-cell-actions']//button[2]")).click();
        Thread.sleep(6000);
        Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Number']//parent::div//following-sibling::div/input")).getAttribute("value"), number);
        Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Comments']//parent::div//following-sibling::div/textarea")).getAttribute("value"), cmt);

    }
    //3. clean: delete data test / account / close browser
    @AfterClass
    public void cleanBrowser() {
        driver.quit(); //dong trinh duyet
    }

}
