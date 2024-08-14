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
import java.util.List;

public class Topic_26_Wait_FindElement {
    //1. setup: OS/ Browser / Web / Page / Data /Variable/ Objiect (đủ điều kiện để thực thi testcase)
    WebDriver driver; //khai báo bến
    @BeforeClass
    public void InitialBrowser() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(13));
    }
//
    @Test
    public void TC_01_FindElement() {
        driver.get("https://demo.nopcommerce.com/register");
        //neu tim thay duy nhat 1 element
        //tra ve dung element do
        //ko can cho het time cua implicit
        //driver.findElement(By.cssSelector("input#FirstName"));

        //neu tim thay nhieu hon 1 element
        //chi thao tac voi element dau tien
        //luu y la khi lay locator nen check truoc
        //driver.findElement(By.cssSelector("input[type=text]"));

        //ko tìm thay element
        //moi dau vao findElement nhung ko thay:
        // tim lai ma thay element thi ko can cho het tong time con lai
        // tim lai va ko thay het toong time 13s thi danh fail
        //show loi: NoSuchElementException
        //driver.findElement(By.cssSelector("input#RememberMe"));
        driver.findElement(By.cssSelector("input#selenium"));
    }
    @Test
    public void TC_02_FindElements() {
        driver.get("https://demo.nopcommerce.com/register");
        //neu tim thay duy nhat 1 element

        List<WebElement> elements = null;
        elements = driver.findElements(By.cssSelector("input#FirstName"));
        System.out.println(elements.size());
        //neu tim thay nhieu hon 1 element
        //tra ve toan bo cac element matching
        elements = driver.findElements(By.cssSelector("input[type='text']"));
        System.out.println(elements.size());
        //ko tìm thay element
        //tra ve list = 0
        // ko danh fail testcase
        elements = driver.findElements(By.cssSelector("input#selenium"));
        System.out.println(elements.size());
        Assert.assertEquals(elements.size(), 0);

    }
    //3. clean: delete data test / account / close browser
    @AfterClass
    public void cleanBrowser() {

        //driver.quit(); //dong trinh duyet
    }

}
