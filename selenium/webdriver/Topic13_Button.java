package webdriver;

import org.checkerframework.checker.units.qual.C;
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
import java.util.List;

public class Topic13_Button {
    //1. setup: OS/ Browser / Web / Page / Data /Variable/ Objiect (đủ điều kiện để thực thi testcase)
    WebDriver driver; //khai báo bến
    WebDriverWait explicitWait;
    @BeforeClass
    public void InitialBrowser() {
        driver = new FirefoxDriver();
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(15));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }
//
    @Test
    public void TC_01_Fahasa() {
        driver.get("https://www.fahasa.com/customer/account/create");
        driver.findElement(By.cssSelector("li.popup-login-tab-login")).click();

        //verify button disable
        By loginButton = By.cssSelector("button.fhs-btn-login");
        Assert.assertFalse(driver.findElement(loginButton).isEnabled());
        Assert.assertEquals(Color.fromString(driver.findElement(loginButton).getCssValue("background-color")).asHex().toLowerCase(), "#000000");

        //verify button enable
        driver.findElement(By.id("login_username")).sendKeys("thu@gmail.com");
        driver.findElement(By.id("login_password")).sendKeys("123456");
        Assert.assertTrue(driver.findElement(loginButton).isEnabled());
        Assert.assertEquals(Color.fromString(driver.findElement(loginButton).getCssValue("background-color")).asHex().toLowerCase(), "#c92127");

    }
    @Test
    public void TC_02_Goconsensus() throws InterruptedException {
        driver.get("https://play.goconsensus.com/u5d5156df");
        Assert.assertFalse(driver.findElement(By.xpath("//footer //button[text()='Continue']")).isEnabled());
        String background = driver.findElement(By.xpath("//footer //button[text()='Continue']")).getCssValue("background-color");
        Color con = Color.fromString(background);
        Assert.assertEquals(con.asHex().toLowerCase(), "#673ab7");
        System.out.println(con.asHex().toLowerCase());

        //nhập thông tin
        driver.findElement(By.id("firstName")).sendKeys("Thu");
        driver.findElement(By.id("lastName")).sendKeys("Nguyễn");
        driver.findElement(By.id("email")).sendKeys("thu@gmail.com");
        driver.findElement(By.id("phoneNumber")).sendKeys("0364698180");
        driver.findElement(By.id("organization")).sendKeys("prox");
        driver.findElement(By.xpath("//div[@class='src-shared-ui-dropdown-select-ui-control--root-hk8zm']//parent::div[@class='src-shared-ui-field--input-wrapper-X5EYu']")).click();
        explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//div[@id='downshift-0-menu']//div[@data-testid='select menu item']")));
        List<WebElement> allCountry = driver.findElements(By.xpath("//div[@id='downshift-0-menu']//div[@data-testid='select menu item']"));
        for(WebElement itemCountry:allCountry) {
            if(itemCountry.getText().equals("MM")) {
                itemCountry.click();
                break;
            }
        }
        Thread.sleep(3000);
        driver.findElement(By.xpath("//label[text()='State']//following-sibling::div")).click();
        explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//label[text()='State']//following-sibling::div//div[@data-testid='select menu item']")));
        List<WebElement> allState = driver.findElements(By.xpath("//label[text()='State']//following-sibling::div//div[@data-testid='select menu item']"));
        for(WebElement itemState:allState) {
            if(itemState.getText().equals("15")) {
                itemState.click();
                break;
            }
        }
        driver.findElement(By.cssSelector("textarea#comments")).sendKeys("Nguyễn Thị Lệ THu");
        Assert.assertEquals(Color.fromString(driver.findElement(By.xpath("//footer //button[text()='Continue']")).getCssValue("background-color")).asHex().toLowerCase(), "#673ab7");

    }
    //3. clean: delete data test / account / close browser
    @AfterClass
    public void cleanBrowser() {
//        driver.quit(); //dong trinh duyet
    }

}
