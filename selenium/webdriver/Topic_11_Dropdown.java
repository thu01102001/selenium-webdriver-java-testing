package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;
import java.util.Random;

public class Topic_11_Dropdown {
    //1. setup: OS/ Browser / Web / Page / Data /Variable/ Objiect (đủ điều kiện để thực thi testcase)
    WebDriver driver; //khai báo bến
    String firstName = "thu", lastName = "nguyen", email = getEmailAddress();
    String company = "prox", password = "123456789a";
    String day = "1", month = "October", year = "2001";
    @BeforeClass
    public void InitialBrowser() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.get("https://demo.nopcommerce.com");
    }
//
    @Test
    public void TC_01_Register() throws InterruptedException {
        driver.findElement(By.xpath("//a[@class='ico-register']")).click();
        driver.findElement(By.xpath("//input[@value='M']")).click();
        driver.findElement(By.id("FirstName")).sendKeys(firstName);
        driver.findElement(By.id("LastName")).sendKeys(lastName);

        Select day = new Select(driver.findElement(By.name("DateOfBirthDay")));
        day.selectByVisibleText(this.day);
        Assert.assertFalse(day.isMultiple());
        Assert.assertEquals(day.getOptions().size(), 32);

        Select month = new Select(driver.findElement(By.name("DateOfBirthMonth")));
        month.selectByVisibleText(this.month);
        Assert.assertFalse(month.isMultiple());
        Assert.assertEquals(month.getOptions().size(), 13);

        Select year = new Select(driver.findElement(By.name("DateOfBirthYear")));
        year.selectByVisibleText(this.year);
        Assert.assertFalse(year.isMultiple());
        Assert.assertEquals(year.getOptions().size(), 112);

        driver.findElement(By.id("Email")).sendKeys(email);
        driver.findElement(By.id("Company")).sendKeys(company);
        driver.findElement(By.id("Password")).sendKeys(password);
        driver.findElement(By.id("ConfirmPassword")).sendKeys(password);
        driver.findElement(By.id("register-button")).click();
        Thread.sleep(3000);
        Assert.assertEquals(driver.findElement(By.xpath("//div[@class='result']")).getText(), "Your registration completed");
    }
    @Test
    public void TC_02_Login() throws InterruptedException {
        driver.get("https://demo.nopcommerce.com");
        driver.findElement(By.cssSelector("a.ico-login")).click();
        driver.findElement(By.id("Email")).sendKeys(email);
        driver.findElement(By.id("Password")).sendKeys(password);
        driver.findElement(By.xpath("//button[text()='Log in']")).click();
        Thread.sleep(3000);
        driver.findElement(By.cssSelector("a.ico-account")).click();
        Assert.assertTrue(driver.findElement(By.xpath("//input[@value='M']")).isSelected());
        Assert.assertEquals(driver.findElement(By.id("FirstName")).getAttribute("value"), firstName);
        Assert.assertEquals(driver.findElement(By.id("LastName")).getAttribute("value"), lastName);
        Assert.assertEquals(new Select(driver.findElement(By.name("DateOfBirthDay"))).getFirstSelectedOption().getText(), day);
        Assert.assertEquals(new Select(driver.findElement(By.name("DateOfBirthMonth"))).getFirstSelectedOption().getText(), month);
        Assert.assertEquals(new Select(driver.findElement(By.name("DateOfBirthYear"))).getFirstSelectedOption().getText(), year);
        Assert.assertEquals(driver.findElement(By.id("Email")).getAttribute("value"), email);
        Assert.assertEquals(driver.findElement(By.id("Company")).getAttribute("value"), company);
    }

    @Test
    public void TC_03_Rode() {
        driver.get("https://www.rode.com/wheretobuy");
        new Select(driver.findElement(By.cssSelector("select#country"))).selectByVisibleText("Vietnam");
        driver.findElement(By.cssSelector("input#map_search_query")).sendKeys("Ho Chi Minh");
        driver.findElement(By.cssSelector("button.btn-default")).click();
        List<WebElement> dealers = driver.findElements(By.xpath("//h3[text()='Dealers']/following-sibling::div//h4"));
        Assert.assertEquals(dealers.size(), 16);
        for(WebElement element : dealers) {
            System.out.println(element.getText());
        }
    }
    public String getEmailAddress() {
        Random rand = new Random();
        return "thunguyen" + rand.nextInt() + "@gmail.com";
    }
    @AfterClass
    public void cleanBrowser() {
        driver.quit(); //dong trinh duyet
    }

}
