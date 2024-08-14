package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Random;

public class Topic_24_Javascript_Executor {
    public Object executeForBrowser(String javaScript) {
        return jsExecutor.executeScript(javaScript);
    }

    public String getInnerText() {
        return (String) jsExecutor.executeScript("return document.documentElement.innerText;");
    }

    public boolean isExpectedTextInInnerText(String textExpected) {
        String textActual = (String) jsExecutor.executeScript("return document.documentElement.innerText.match('" + textExpected + "')[0];");
        return textActual.equals(textExpected);
    }

    public void scrollToBottomPage() {
        jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
    }

    public void sleepInSecond(int timeout) {
        try {
            Thread.sleep(timeout * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void navigateToUrlByJS(String url) {
        jsExecutor.executeScript("window.location = '" + url + "'");
        sleepInSecond(3);
    }

    public void hightlightElement(String locator) {
        WebElement element = getElement(locator);
        String originalStyle = element.getAttribute("style");
        jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1])", element, "border: 2px solid red; border-style: dashed;");
        sleepInSecond(2);
        jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1])", element, originalStyle);
    }

    public void clickToElementByJS(String locator) {
        jsExecutor.executeScript("arguments[0].click();", getElement(locator));
        sleepInSecond(3);
    }

    public void scrollToElementOnTop(String locator) {
        jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getElement(locator));
    }

    public void scrollToElementOnDown(String locator) {
        jsExecutor.executeScript("arguments[0].scrollIntoView(false);", getElement(locator));
    }

    public void setAttributeInDOM(String locator, String attributeName, String attributeValue) {
        jsExecutor.executeScript("arguments[0].setAttribute('" + attributeName + "', '" + attributeValue +"');", getElement(locator));
    }

    public void removeAttributeInDOM(String locator, String attributeRemove) {
        jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getElement(locator));
    }

    public void sendkeyToElementByJS(String locator, String value) {
        jsExecutor.executeScript("arguments[0].setAttribute('value', '" + value + "')", getElement(locator));
    }

    public String getAttributeInDOM(String locator, String attributeName) {
        return (String) jsExecutor.executeScript("return arguments[0].getAttribute('" + attributeName + "');", getElement(locator));
    }

    public String getElementValidationMessage(String locator) {
        return (String) jsExecutor.executeScript("return arguments[0].validationMessage;", getElement(locator));
    }

    public boolean isImageLoaded(String locator) {
        boolean status = (boolean) jsExecutor.executeScript(
                "return arguments[0].complete && typeof arguments[0].naturalWidth != 'undefined' && arguments[0].naturalWidth > 0", getElement(locator));
        return status;
    }

    public WebElement getElement(String locator) {
        return driver.findElement(By.xpath(locator));
    }
    //1. setup: OS/ Browser / Web / Page / Data /Variable/ Objiect (đủ điều kiện để thực thi testcase)
    WebDriver driver; //khai báo bến
    JavascriptExecutor jsExecutor;
    WebDriverWait explicitWait;
    String email;
    String random;
    @BeforeClass
    public void InitialBrowser() {
        driver = new ChromeDriver();
        jsExecutor = (JavascriptExecutor) driver;
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(15));
        email = "automation" + new Random().nextInt(999) + "@gmail.com";
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }
//
    @Test
    public void TC_01_() throws InterruptedException {
        jsExecutor.executeScript("window.location = 'http://live.techpanda.org/'");
        Thread.sleep(3000);
        String getDomain = (String) jsExecutor.executeScript("return document.domain;");
        Assert.assertEquals(getDomain, "live.techpanda.org");

        String getURL = (String) jsExecutor.executeScript("return document.URL;");
        Assert.assertEquals(getURL, "http://live.techpanda.org/");

        jsExecutor.executeScript("arguments[0].click();", driver.findElement(By.xpath("//a[text()='Mobile']")));
        Thread.sleep(3000);
        jsExecutor.executeScript("arguments[0].click();", driver.findElement(By.xpath("//a[text()='Samsung Galaxy']/ancestor::div[@class='product-info']//span[text()='Add to Cart']")));
        Thread.sleep(3000);

        String text = (String) jsExecutor.executeScript("return document.documentElement.innerText;");
        Assert.assertTrue(text.contains("Samsung Galaxy was added to your shopping cart."));

        jsExecutor.executeScript("arguments[0].click();", driver.findElement(By.xpath("//a[text()='Customer Service']")));
        Thread.sleep(3000);

        String pageTitle = (String) jsExecutor.executeScript("return document.title;");
        Assert.assertEquals(pageTitle, "Customer Service");

        jsExecutor.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//input[@title='Sign up for our newsletter']")));
        jsExecutor.executeScript("arguments[0].setAttribute('value', arguments[1]);", driver.findElement(By.xpath("//input[@title='Sign up for our newsletter']")), email);

        jsExecutor.executeScript("arguments[0].click();", driver.findElement(By.xpath("//span[text()='Subscribe']")));
        Thread.sleep(3000);

        String text1 = (String) jsExecutor.executeScript("return document.documentElement.innerText;");
//        Assert.assertTrue(text1.contains("Thank you for your subscription."));
        Thread.sleep(3000);
        jsExecutor.executeScript("window.location = 'https://www.youtube.com/'");

        Thread.sleep(3000);

    }
    @Test
    public void TC_02_Rode_html5() throws InterruptedException {
        driver.get("https://warranty.rode.com/login");
        WebElement loginBtn = driver.findElement(By.xpath("//button[@type='submit']"));
        //empty
        loginBtn.click();
        String emptyEmailMessage = getElementValidationMessage("//input[@id='email']");
        Assert.assertEquals(emptyEmailMessage, "Please fill out this field.");

        //email invalid
        String invalidEmailData = "aaa";
        driver.findElement(By.xpath("//input[@id='email']")).sendKeys(invalidEmailData);
        loginBtn.click();
        String invalidEmail = getElementValidationMessage("//input[@id='email']");
        //Please include an '@' in the email address. 'aa' is missing an '@'.
        //Please enter an email address.
        if(driver.toString().contains("ChromeDriver")) {
            Assert.assertEquals(invalidEmail, "Please include an '@' in the email address. '" +invalidEmailData + "' is missing an '@'.");
        } else {
            Assert.assertEquals(invalidEmail, "Please enter an email address.");
        }

        Thread.sleep(3000);

        invalidEmailData = "aaa@";
        driver.findElement(By.xpath("//input[@id='email']")).clear();
        driver.findElement(By.xpath("//input[@id='email']")).sendKeys(invalidEmailData);
        loginBtn.click();
        invalidEmail = getElementValidationMessage("//input[@id='email']");
        //Please include an '@' in the email address. 'aa' is missing an '@'.
        //Please enter an email address.
        if(driver.toString().contains("ChromeDriver")) {
            Assert.assertEquals(invalidEmail, "Please enter a part following '@'. '" +invalidEmailData+ "' is incomplete.");
        } else {
            Assert.assertEquals(invalidEmail, "Please enter an email address.");
        }
        Thread.sleep(3000);

        invalidEmailData = "aaa@abc.";
        driver.findElement(By.xpath("//input[@id='email']")).clear();
        driver.findElement(By.xpath("//input[@id='email']")).sendKeys(invalidEmailData);
        loginBtn.click();
        invalidEmail = getElementValidationMessage("//input[@id='email']");
        //Please include an '@' in the email address. 'aa' is missing an '@'.
        //Please enter an email address.
        if(driver.toString().contains("ChromeDriver")) {
            Assert.assertEquals(invalidEmail, "'.' is used at a wrong position in '" +invalidEmailData.split("@")[1] + "'.");
        } else {
            Assert.assertEquals(invalidEmail, "Please enter an email address.");
        }
        Thread.sleep(3000);
        
        //email valid
        driver.findElement(By.xpath("//input[@id='email']")).clear();
        driver.findElement(By.xpath("//input[@id='email']")).sendKeys(email);
        loginBtn.click();
        Thread.sleep(3000);

    }

    @Test
    public void TC_03_html5_validate() throws InterruptedException {
        driver.get("https://automationfc.github.io/html5/index.html");
        WebElement buttonSubmit = driver.findElement(By.xpath("//input[@type='submit']"));
        //de trong name
        buttonSubmit.click();
        String inputValid = getElementValidationMessage("//input[@id='fname']");
        Assert.assertEquals(inputValid, "Please fill out this field.");
        Thread.sleep(3000);

        //nhap name
        String name = "thu";
        driver.findElement(By.xpath("//input[@id='fname']")).sendKeys(name);
        Assert.assertEquals(Color.fromString(driver.findElement(By.xpath("//input[@id='fname']")).getCssValue("background-color")).asHex().toLowerCase(), "#3c5cb4");
            //nhan submit
        buttonSubmit.click();
        inputValid = getElementValidationMessage("//input[@id='pass']");
        Assert.assertEquals(inputValid, "Please fill out this field.");
        Thread.sleep(3000);

        //nhap pass
        String pass = "12345678";
        driver.findElement(By.xpath("//input[@id='pass']")).sendKeys(pass);
        Assert.assertEquals(Color.fromString(driver.findElement(By.xpath("//input[@id='pass']")).getCssValue("background-color")).asHex().toLowerCase(), "#3c5cb4");
            //nhan submit
        buttonSubmit.click();
        inputValid = getElementValidationMessage("//input[@type='email']");
        Assert.assertEquals(inputValid, "Please fill out this field.");
        Thread.sleep(3000);

        //nhap email invalid
        String email = "aaa";
        driver.findElement(By.xpath("//input[@type='email']")).sendKeys(email);
        buttonSubmit.click();
        inputValid = getElementValidationMessage("//input[@type='email']");
        if(driver.toString().contains("ChromeDriver")) {
            Assert.assertEquals(inputValid, "Please include an '@' in the email address. '" +email+ "' is missing an '@'.");
        }
        else {
            Assert.assertEquals(inputValid, "Please enter an email address.");
        }
        Thread.sleep(3000);

        email = "aaa@";
        driver.findElement(By.xpath("//input[@type='email']")).clear();
        driver.findElement(By.xpath("//input[@type='email']")).sendKeys(email);
        buttonSubmit.click();
        inputValid = getElementValidationMessage("//input[@type='email']");
        if(driver.toString().contains("ChromeDriver")) {
            Assert.assertEquals(inputValid, "Please enter a part following '@'. '" +email+ "' is incomplete.");
        }
        else {
            Assert.assertEquals(inputValid, "Please enter an email address.");
        }
        Thread.sleep(3000);

        //nhap email valid
        email = "thu@gmail.com";
        driver.findElement(By.xpath("//input[@type='email']")).clear();
        driver.findElement(By.xpath("//input[@type='email']")).sendKeys(email);
        buttonSubmit.click();
        inputValid = getElementValidationMessage("//select");
        Assert.assertEquals(inputValid, "Please select an item in the list.");
        Thread.sleep(3000);

        //chon Address
        new Select(driver.findElement(By.cssSelector("select"))).selectByVisibleText("DA NANG");
        Assert.assertEquals(new Select(driver.findElement(By.xpath("//select"))).getFirstSelectedOption().getText(), "DA NANG");
        Assert.assertEquals(new Select(driver.findElement(By.xpath("//select"))).getOptions().size(), 6);
        Assert.assertFalse(new Select(driver.findElement(By.xpath("//select"))).isMultiple());
        buttonSubmit.click();
        Thread.sleep(3000);
    }

    @Test
    public void TC_04_Ubuntu() throws InterruptedException {
        driver.get("https://login.ubuntu.com/");
        Thread.sleep(3000);
        WebElement buttonLogin = driver.findElement(By.xpath("//div[@class='login-form']//button[@type='submit']"));
        //empty
        buttonLogin.click();
        String invalid = getElementValidationMessage("//div[@class='login-form']//input[@id='id_email']");
        Assert.assertEquals(invalid, "Please fill out this field.");
        Thread.sleep(3000);

        //nhap aaa
        String email = "aaa";
        driver.findElement(By.xpath("//div[@class='login-form']//input[@id='id_email']")).sendKeys(email);
        buttonLogin.click();
        invalid = getElementValidationMessage("//div[@class='login-form']//input[@id='id_email']");
        Assert.assertEquals(invalid, "Please include an '@' in the email address. '" +email+ "' is missing an '@'.");
        Thread.sleep(3000);

        //nhap aaa@
        email = "aaa@";
        driver.findElement(By.xpath("//div[@class='login-form']//input[@id='id_email']")).clear();
        driver.findElement(By.xpath("//div[@class='login-form']//input[@id='id_email']")).sendKeys(email);
        buttonLogin.click();
        invalid = getElementValidationMessage("//div[@class='login-form']//input[@id='id_email']");
        Assert.assertEquals(invalid, "Please enter a part following '@'. '" +email+ "' is incomplete.");
        Thread.sleep(3000);

        //nhap aaa@
        email = "aaa@abc";
        driver.findElement(By.xpath("//div[@class='login-form']//input[@id='id_email']")).clear();
        driver.findElement(By.xpath("//div[@class='login-form']//input[@id='id_email']")).sendKeys(email);
        buttonLogin.click();
        invalid = getElementValidationMessage("//div[@class='login-form']//input[@id='id_password']");
        Assert.assertEquals(invalid, "Please fill out this field.");
        Thread.sleep(3000);

        //nhap pass
        String pass = "12345678";
        driver.findElement(By.xpath("//div[@class='login-form']//input[@id='id_password']")).sendKeys(pass);
        buttonLogin.click();
        Assert.assertEquals(driver.findElement(By.cssSelector("span.p-form-help-text")).getText(), "Invalid email");
        Thread.sleep(3000);
        //nhap valid
        email = "thu@gmail.com";
        pass = "123456";
        driver.findElement(By.xpath("//div[@class='login-form']//input[@id='id_email']")).clear();
        driver.findElement(By.xpath("//div[@class='login-form']//input[@id='id_email']")).sendKeys(email);
        driver.findElement(By.xpath("//div[@class='login-form']//input[@id='id_password']")).sendKeys(pass);
        Thread.sleep(3000);
        buttonLogin = driver.findElement(By.xpath("//div[@class='login-form']//button[@type='submit']"));
        buttonLogin.click();
        Assert.assertEquals(driver.findElement(By.cssSelector("p.p-notification__response")).getText(), "There were some problems with the information you gave us. Please check below and try again.");
        Thread.sleep(3000);
    }

    @Test
    public void TC_05_MayMocThietBI() throws InterruptedException {
        driver.get("https://sieuthimaymocthietbi.com/account/register");
        Thread.sleep(3000);
        WebElement buttonSignUp = driver.findElement(By.xpath("//button[@value='Đăng ký']"));
        //empty ho
        buttonSignUp.click();
        String invalid = getElementValidationMessage("//input[@id='lastName']");
        Assert.assertEquals(invalid, "Please fill out this field.");
        Thread.sleep(3000);
//        Assert.assertEquals(driver.findElement(By.xpath("//span[@class='help-block form-error']")).getText(), "Không được để trống");
//        Thread.sleep(3000);
        //nhap ho
        String ho = "nguyen";
        driver.findElement(By.xpath("//input[@id='lastName']")).sendKeys(ho);
        buttonSignUp.click();
        invalid = getElementValidationMessage("//input[@id='firstName']");
        Assert.assertEquals(invalid, "Please fill out this field.");
        Thread.sleep(3000);

        //nhap ten
        String ten = "thu";
        driver.findElement(By.xpath("//input[@id='firstName']")).sendKeys(ten);
        buttonSignUp.click();
        invalid = getElementValidationMessage("//input[@id='email']");
        Assert.assertEquals(invalid, "Please fill out this field.");
        Thread.sleep(3000);

        //nhap email = aaa
        String email = "aaa";
        driver.findElement(By.xpath("//input[@id='email']")).sendKeys(email);
        buttonSignUp.click();
        invalid = getElementValidationMessage("//input[@id='email']");
        Assert.assertEquals(invalid, "Please include an '@' in the email address. '" +email+ "' is missing an '@'.");
        Thread.sleep(3000);

        //nhap email = aaa@
        email = "aaa@";
        driver.findElement(By.xpath("//input[@id='email']")).clear();
        driver.findElement(By.xpath("//input[@id='email']")).sendKeys(email);
        buttonSignUp.click();
        invalid = getElementValidationMessage("//input[@id='email']");
        Assert.assertEquals(invalid, "Please enter a part following '@'. '" +email+ "' is incomplete.");
        Thread.sleep(3000);

        //nhap email = aaa@aaa
        email = "aaa@aaa";
        buttonSignUp.click();
        driver.findElement(By.xpath("//input[@id='email']")).clear();
        driver.findElement(By.xpath("//input[@id='email']")).sendKeys(email);
        invalid = getElementValidationMessage("//input[@id='password']");
        Assert.assertEquals(invalid, "Please fill out this field.");
        Thread.sleep(3000);
        Assert.assertEquals(driver.findElement(By.xpath("//input[@id='email']/following-sibling::span")).getText(), "Email sai định dạng");
        Thread.sleep(3000);
//        buttonSignUp.click();
//        Assert.assertEquals(driver.findElement(By.xpath("//input[@id='password']/following-sibling::span")).getText(), "Không được để trống");
//        Thread.sleep(3000);

        //nhap pass
        String pass = "123";
        driver.findElement(By.xpath("//input[@id='password']")).sendKeys(pass);
        buttonSignUp.click();
//        Assert.assertEquals(Color.fromString(driver.findElement(By.xpath("//input[@id='email']")).getAttribute("color")).asHex().toLowerCase(), "#ff0000");
        Thread.sleep(3000);

        //nhap email hop le
        email = "thu@gmail.com";
        driver.findElement(By.xpath("//input[@id='email']")).clear();
        driver.findElement(By.xpath("//input[@id='email']")).sendKeys(email);
        buttonSignUp.click();
        Thread.sleep(3000);
        Assert.assertEquals(driver.findElement(By.cssSelector("div.errors li")).getText(), "Mật khẩu dài từ 6 đến 50 ký tự");

        //nhap pass 6 - 50 ky tuu
        pass = "12345678";
        driver.findElement(By.xpath("//input[@id='lastName']")).sendKeys(ho);
        driver.findElement(By.xpath("//input[@id='firstName']")).sendKeys(ten);
        driver.findElement(By.xpath("//input[@id='email']")).clear();
        driver.findElement(By.xpath("//input[@id='password']")).sendKeys(pass);
        buttonSignUp = driver.findElement(By.xpath("//button[@value='Đăng ký']"));
        buttonSignUp.click();
        Thread.sleep(3000);
        Assert.assertEquals(driver.findElement(By.cssSelector("div.errors li")).getText(), "mã captcha được xác định là máy");
        Thread.sleep(3000);
    }

    @Test
    public void TC_06_RemoveAttribute() throws InterruptedException {
        driver.get("http://demo.guru99.com/v4");
        driver.findElement(By.xpath("//input[@name='uid']")).sendKeys("mngr581835");
        driver.findElement(By.xpath("//input[@name='password']")).sendKeys("tanAhUq");
        driver.findElement(By.xpath("//input[@name='btnLogin']")).click();
        driver.findElement(By.xpath("//a[text()='New Customer']")).click();
        driver.findElement(By.xpath("//input[@name='name']")).sendKeys("thu");
        driver.findElement(By.xpath("//input[@value='f']")).click();
        jsExecutor.executeScript("arguments[0].removeAttribute('type');", driver.findElement(By.xpath("//input[@id='dob']")));
        driver.findElement(By.xpath("//input[@id='dob']")).sendKeys("2024-07-18");
        driver.findElement(By.xpath("//textarea[@name='addr']")).sendKeys("hanoi");
        driver.findElement(By.xpath("//input[@name='city']")).sendKeys("namdinh");
        driver.findElement(By.xpath("//input[@name='state']")).sendKeys("lethu");
        driver.findElement(By.xpath("//input[@name='pinno']")).sendKeys("011001");
        driver.findElement(By.xpath("//input[@name='telephoneno']")).sendKeys("0364697188");
        driver.findElement(By.xpath("//input[@name='emailid']")).sendKeys("thunguyen@gmail.com");
        driver.findElement(By.xpath("//input[@name='password']")).sendKeys("123456789");
        driver.findElement(By.xpath("//input[@type='submit']")).click();
        Thread.sleep(3000);
        Assert.assertEquals(driver.findElement(By.cssSelector("p.heading3")).getText(), "Customer Registered Successfully!!!");
    }

    @Test
    public void TC_06_Guru() throws InterruptedException {
        jsExecutor.executeScript("window.location = 'http://live.techpanda.org/'");
        jsExecutor.executeScript("arguments[0].click();", driver.findElement(By.xpath("//div[@class='account-cart-wrapper']//span[text()='Account']")));
        jsExecutor.executeScript("arguments[0].click();", driver.findElement(By.cssSelector("div#header-account a[title='My Account']")));
        Thread.sleep(3000);
        jsExecutor.executeScript("arguments[0].click();", driver.findElement(By.xpath("//a[@title='Create an Account']")));
        jsExecutor.executeScript("arguments[0].value='thu';", driver.findElement(By.xpath("//input[@id='firstname']")));
        jsExecutor.executeScript("arguments[0].value='le';", driver.findElement(By.xpath("//input[@id='middlename']")));
        jsExecutor.executeScript("arguments[0].value='nguyen';", driver.findElement(By.xpath("//input[@id='lastname']")));
        jsExecutor.executeScript("arguments[0].value='" +email+ "';", driver.findElement(By.xpath("//input[@id='email_address']")));
        jsExecutor.executeScript("arguments[0].value='123456789';", driver.findElement(By.xpath("//input[@id='password']")));
        jsExecutor.executeScript("arguments[0].value='123456789';", driver.findElement(By.xpath("//input[@id='confirmation']")));
        jsExecutor.executeScript("arguments[0].click();", driver.findElement(By.xpath("//button[@title='Register']")));
        Thread.sleep(3000);
        String msg = (String) jsExecutor.executeScript("return document.documentElement.innerText;", driver.findElement(By.xpath("//li[@class='success-msg']//span")));
        Assert.assertTrue(msg.contains("Thank you for registering with Main Website Store."));
        jsExecutor.executeScript("arguments[0].click();", driver.findElement(By.xpath("//div[@class='account-cart-wrapper']//span[text()='Account']")));
        jsExecutor.executeScript("arguments[0].click();", driver.findElement(By.cssSelector("div#header-account a[title='Log Out']")));
        Thread.sleep(3000);

    }
    //3. clean: delete data test / account / close browser
    @AfterClass
    public void cleanBrowser() {
        driver.quit(); //dong trinh duyet
    }

}
