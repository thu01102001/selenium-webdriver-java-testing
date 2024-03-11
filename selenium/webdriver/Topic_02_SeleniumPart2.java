package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.locators.RelativeLocator;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_02_SeleniumPart2 {
    WebDriver driver; //khai báo bến
    @BeforeClass
    public void InitialBrowser() {
        driver = new FirefoxDriver();
        driver.get("https://demo.nopcommerce.com/register");
    }

    @Test
    public void TC_01_ID() throws InterruptedException {
        //sendkey: nhap ky tu
        driver.findElement(By.id("small-searchterms")).sendKeys("Macbook");
        Thread.sleep(3000); //delay
        driver.findElement(By.id("FirstName")).sendKeys("Automation");
        Thread.sleep(3000); //delay
    }
    @Test
    public void TC_02_Class() throws InterruptedException {
    //ko lay toan bo gia tri neu co khoang trang
        driver.findElement(By.className("register-next-step-button")).click();
        Thread.sleep(3000);
    }
    @Test
    public void TC_03_Name() {
        driver.findElement(By.name("DateOfBirthDay"));
        driver.findElement(By.name("DateOfBirthMonth"));
        driver.findElement(By.name("DateOfBirthYear"));
    }
    @Test
    public void TC_04_LinkText() {
        //phai lay het toan bo text
        //chi lam viec voi element la link va co text (the a va co thuoc tinh href)
        driver.findElement(By.linkText("Register"));
    }
    @Test
    public void TC_05_Partial_Link_Text() {
        //co the lay toan bo text hoac 1 phan
        driver.findElement(By.linkText("Digital"));
        driver.findElement(By.linkText("downloads"));
    }
    @Test
    public void TC_06_TagName() {
    //ten the
        //khi muon tim tat ca cac element giong nhau
        //tat ca cac textbox / checkbox/ link/ button
        driver.findElement(By.tagName("button"));
        driver.findElement(By.tagName("input"));

    }
    @Test
    public void TC_07_Css() {
        //id
        driver.findElement(By.cssSelector("input#Company"));
        driver.findElement(By.cssSelector("#Company"));

        driver.findElement(By.cssSelector("input[id='Company']"));
        driver.findElement(By.cssSelector("button[id='register-next-step-button']"));

        driver.findElement(By.cssSelector("a[herf='/register?returnUrl=%2Fregister']"));
        driver.findElement(By.cssSelector("a[herf='/login?returnUrl=%2Fregister']"));

        driver.findElement(By.cssSelector("a[herf*='register?']"));
        driver.findElement(By.cssSelector("a[herf*='login?']"));

        driver.findElement(By.cssSelector("a"));
        driver.findElement(By.cssSelector("b"));
    }
//
    @Test
    public void TC_08_XPath() {
        driver.findElement(By.xpath("//input[@id='small-searchterms']"));
        driver.findElement(By.xpath("//button[@class='register-next-step-button']"));
        driver.findElement(By.xpath("//input[@name='DateOfBirthDay']"));
        driver.findElement(By.xpath("//a[text()='Register']"));
        driver.findElement(By.xpath("//a[contains(text(), 'Register')]"));
        driver.findElement(By.xpath("//a"));
    }

    @Test
    public void TC_09_Relative_Locator() {
        driver.get("https://demo.nopcommerce.com/login");
        //element A
        By passwordTextboxBy = By.id("Password");
        //element B
        By rememberMeCheckboxBy = By.id("RememberMe");
        //element C
        By buttonLoginBy = By.cssSelector("button.login-button");
        //element D
        By forgotPasswordBy = By.cssSelector("span.forgot-password");
        //element E
        WebElement rememberMeLabelText = driver.findElement(RelativeLocator.with(By.tagName("label")).below(passwordTextboxBy)
                .toLeftOf(forgotPasswordBy)
                .toRightOf(rememberMeCheckboxBy)
                .above(forgotPasswordBy)
        );
        //cau truc viet cua xpath
        //tagname[@attribute='value']
    }
    @AfterClass
    public void cleanBrowser() {
        driver.quit(); //dong trinh duyet
    }

}
