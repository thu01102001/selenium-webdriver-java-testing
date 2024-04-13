package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.bidi.log.Log;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.Logs;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class Topic_06_WebBrowser_Commands {
    WebDriver driver; //khai báo bến
    @BeforeClass
    public void InitialBrowser() {
        //Run with Browser
        driver = new FirefoxDriver();
        driver = new ChromeDriver();
        driver = new EdgeDriver();
        driver = new SafariDriver();
        driver = new InternetExplorerDriver();
    }
    @Test
    public void TC_01_() {
        //Dùng để mở ra một url bất kỳ (lưu ý: phải bắt đầu bằng http hoặc https)
        String homePage = "https://www.facebook.com/";
        driver.get(homePage);
        //Hoặc
        driver.get("https://www.facebook.com/");
        //Đóng browser
        driver.close();
        //đóng trình duyệt (tất cả các tab)
        driver.quit();
        //Lấy title của page hiện tại (phải lấy title ra được rồi mới kiểm tra)
        //1- Lưu dữ liệu lại rồi kiểm tra sau (khai báo 1 biến để lưu dữ liệu lại)
        String homePageTitle = driver.getTitle();
        Assert.assertEquals(homePageTitle, "Facebook");
        //2- kiểm tra trực tíiếp luôn
        Assert.assertEquals(driver.getTitle(), "Facebook");
        //==> khi dữ liệu chỉ dùng 1 lần thì ko nên khai báo biến

        //Lấy ra url của page hiện tại
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.facebook.com/");
        //lấy ra Page source code - verify tương đối
        driver.getPageSource();
        Assert.assertTrue(driver.getPageSource().contains("Facebook"));

        //Khi gọi hàm nào cần phải kiểm tra xem mình đang đứng ở đâu
        //lấy ra id của tab / window đang active
        driver.getWindowHandle();
        //lấy ra tất cả id của tất cả các tab/window đang có
        driver.getWindowHandles();

        //đi tìm element
        driver.findElement(By.xpath(""));
        //tìm n element
        driver.findElements(By.xpath(""));

        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        //Dùng để chờ cho việc tìm element (findElement)
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        //Dùng để chờ cho việc page được load xong
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(15));
        //Dùng để chờ cho 1 đoạn script được thực thi xong
        //JavascriptExecutor - js
        driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(15));

        driver.manage().window().maximize();
        driver.manage().window().minimize();
        driver.manage().window().fullscreen();
        driver.manage().window().getSize();
        driver.manage().window().getPosition();
        Set<Cookie> cookies = driver.manage().getCookies();
        driver.manage().getCookieNamed(".Nop.Antiforgery");
        driver.manage().deleteAllCookies();
        for (Cookie cookie: cookies) {
            driver.manage().deleteCookie(cookie);
        }
        driver.manage().deleteCookieNamed(".Nop.Antiforgery");

        Logs log = driver.manage().logs();
        LogEntries logEntries = log.get("BROWSER");
        for (LogEntry logEn: logEntries) {
            System.out.println(logEn);
        }
        
    }

    @Test
    public void TC_02_() {

    }
    @AfterClass
    public void cleanBrowser() {
        driver.quit(); //dong trinh duyet
    }

}
