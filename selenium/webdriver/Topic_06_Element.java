package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_06_Element {
    WebDriver driver; //khai báo bến
    @BeforeClass
    public void InitialBrowser() {
        driver = new FirefoxDriver();
        driver.get("https://login.mailchimp.com/signup/");
    }
//
    @Test
    public void TC_01_Display() throws InterruptedException {
        WebElement email = driver.findElement(By.xpath("//label[@for='mail']"));
        Assert.assertTrue(email.isDisplayed());
        driver.findElement(By.xpath("//input[@id='mail']")).sendKeys("Automation Testing");
        WebElement edu = driver.findElement(By.xpath("//label[@for='edu']"));
        Assert.assertTrue(edu.isDisplayed());
        driver.findElement(By.xpath("//textarea[@id='edu']")).sendKeys("Automation Testing");
        WebElement age = driver.findElement(By.xpath("//label[@for='under_18']"));
        Assert.assertTrue(age.isDisplayed());
        driver.findElement(By.xpath("//input[@id='under_18']")).click();
        WebElement user = driver.findElement(By.xpath("//h5[text()='Name: User5']"));
        Assert.assertFalse(user.isDisplayed());
        if(user.isDisplayed()) {
            System.out.println("Name: User5 is displayed");
        }
        else {
            System.out.println("Name: User5 is not displayed");
        }
        if(email.isDisplayed()) {
            System.out.println("Email is displayed");
        }
        else {
            System.out.println("Email is not displayed");
        }
        if(edu.isDisplayed()) {
            System.out.println("Education is displayed");
        }
        else {
            System.out.println("Education is not displayed");
        }
        if(age.isDisplayed()) {
            System.out.println("Age is displayed");
        }
        else {
            System.out.println("Age is not displayed");
        }
    }
    @Test
    public void TC_02_EnableDisable() {
        driver.get("https://automationfc.github.io/basic-form/index.html");
        WebElement inputEmail = driver.findElement(By.xpath("//input[@id='mail']"));
        Assert.assertTrue(inputEmail.isEnabled());
        WebElement under18 = driver.findElement(By.xpath("//input[@id='under_18']"));
        Assert.assertTrue(under18.isEnabled());
        WebElement education = driver.findElement(By.xpath("//textarea[@id='edu']"));
        Assert.assertTrue(education.isEnabled());
        WebElement job1 = driver.findElement(By.xpath("//select[@id='job1']"));
        Assert.assertTrue(job1.isEnabled());
        WebElement job2 = driver.findElement(By.xpath("//select[@id='job2']"));
        Assert.assertTrue(job2.isEnabled());
        WebElement slider1 = driver.findElement(By.xpath("//input[@id='slider-1']"));
        Assert.assertTrue(slider1.isEnabled());
        WebElement password = driver.findElement(By.xpath("//input[@id='disable_password']"));
        Assert.assertFalse(password.isEnabled());
        WebElement radio = driver.findElement(By.xpath("//input[@id='radio-disabled']"));
        Assert.assertFalse(radio.isEnabled());
        WebElement biography = driver.findElement(By.xpath("//textarea[@id='bio']"));
        Assert.assertFalse(biography.isEnabled());
        WebElement job3 = driver.findElement(By.xpath("//select[@id='job3']"));
        Assert.assertFalse(job3.isEnabled());
        WebElement checkbox = driver.findElement(By.xpath("//input[@id='check-disbaled']"));
        Assert.assertFalse(checkbox.isEnabled());
        WebElement slider2 = driver.findElement(By.xpath("//input[@id='slider-2']"));
        Assert.assertFalse(slider2.isEnabled());
        if(inputEmail.isEnabled() && under18.isEnabled() && education.isEnabled() && job1.isEnabled() && job2.isEnabled() && slider1.isEnabled()) {
            System.out.println("Email, under18, education, job1, job2, slider1 is enabled");
        }
        else {
            System.out.println("Email, under18, education, job1, job2, slider1 is not enabled");
        }
        if (password.isEnabled() && radio.isEnabled() && biography.isEnabled() && job3.isEnabled() && checkbox.isEnabled() && slider2.isEnabled()) {
            System.out.println("Password, radio, biography, job3, checkbox is enabled");
        }
        else {
            System.out.println("Password, radio, biography, job3, checkbox is not enabled");
        }
    }
    @Test
    public void TC_03_Selected() {
        driver.get("https://automationfc.github.io/basic-form/index.html");
        WebElement radioUnder18 = driver.findElement(By.xpath("//input[@id='under_18']"));
        radioUnder18.click();
        Assert.assertTrue(radioUnder18.isSelected());
        WebElement checkboxJava = driver.findElement(By.xpath("//input[@id='java']"));
        checkboxJava.click();
        Assert.assertTrue(checkboxJava.isSelected());
        checkboxJava.click();
        Assert.assertFalse(checkboxJava.isSelected());
    }
    @Test
    public void TC_04_Register() throws InterruptedException {
        driver.get("https://login.mailchimp.com/signup/");
        driver.findElement(By.xpath("//input[@id='email']")).sendKeys("nguyenthilethu2001vn@gmail.com");
        //Case1: Number
        driver.findElement(By.cssSelector("input#new_password")).sendKeys("12345");
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='lowercase-char not-completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='uppercase-char not-completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='number-char completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='special-char not-completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char not-completed']")).isDisplayed());

        //Case2: LowerCase
        driver.findElement(By.cssSelector("input#new_password")).clear();
        driver.findElement(By.cssSelector("input#new_password")).sendKeys("auto");
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='lowercase-char completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='uppercase-char not-completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='number-char not-completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='special-char not-completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char not-completed']")).isDisplayed());

        //Case3: UpperCase
        driver.findElement(By.cssSelector("input#new_password")).clear();
        driver.findElement(By.cssSelector("input#new_password")).sendKeys("AUTO");
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='lowercase-char not-completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='uppercase-char completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='number-char not-completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='special-char not-completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char not-completed']")).isDisplayed());

        //Case4: special
        driver.findElement(By.cssSelector("input#new_password")).clear();
        driver.findElement(By.cssSelector("input#new_password")).sendKeys("@#$$");
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='lowercase-char not-completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='uppercase-char not-completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='number-char not-completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='special-char completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char not-completed']")).isDisplayed());

        //Case5: 8char số
        driver.findElement(By.cssSelector("input#new_password")).clear();
        driver.findElement(By.cssSelector("input#new_password")).sendKeys("123456789");
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='lowercase-char not-completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='uppercase-char not-completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='number-char completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='special-char not-completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char completed']")).isDisplayed());

        //Case6: Valid
        driver.findElement(By.cssSelector("input#new_password")).clear();
        driver.findElement(By.cssSelector("input#new_password")).sendKeys("Abc123@$");
        Assert.assertFalse(driver.findElement(By.xpath("//li[@class='lowercase-char completed']")).isDisplayed());
        Assert.assertFalse(driver.findElement(By.xpath("//li[@class='uppercase-char completed']")).isDisplayed());
        Assert.assertFalse(driver.findElement(By.xpath("//li[@class='number-char completed']")).isDisplayed());
        Assert.assertFalse(driver.findElement(By.xpath("//li[@class='special-char completed']")).isDisplayed());
        Assert.assertFalse(driver.findElement(By.xpath("//li[@class='8-char completed']")).isDisplayed());

        //Case7: Empty
        driver.findElement(By.cssSelector("input#new_password")).clear();
        driver.findElement(By.xpath("//button[@id='create-account-enabled']")).click();
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='lowercase-char not-completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='uppercase-char not-completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='number-char not-completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='special-char not-completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char not-completed']")).isDisplayed());
    }
    @AfterClass
    public void cleanBrowser() {
//        driver.quit(); //dong trinh duyet
    }

}
