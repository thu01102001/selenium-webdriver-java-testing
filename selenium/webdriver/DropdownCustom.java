package webdriver;

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

public class DropdownCustom {
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
    public void TC_01_Jquery() throws InterruptedException {
        driver.get("http://jqueryui.com/resources/demos/selectmenu/default.html");
        selectItemInDropdown("span#speed-button", "ul#speed-menu div", "Medium");
        selectItemInDropdown("span#speed-button", "ul#speed-menu div", "Slower");
        selectItemInDropdown("span#speed-button", "ul#speed-menu div", "Faster");
    }

    @Test
    public void TC_02_ReactJS() throws InterruptedException {
        driver.get("https://react.semantic-ui.com/maximize/dropdown-example-selection/");
        selectItemInDropdown("div.selection.dropdown", "div.visible.menu.transition div", "Jenny Hess");
        selectItemInDropdown("div.selection.dropdown", "div.visible.menu.transition div", "Christian");
    }

    @Test
    public void TC_03_VueJS() throws InterruptedException {
        driver.get("https://mikerodham.github.io/vue-dropdowns/");
        selectItemInDropdown("li.dropdown-toggle", "ul.dropdown-menu a", "First Option");
        selectItemInDropdown("li.dropdown-toggle", "ul.dropdown-menu a", "Second Option");
        selectItemInDropdown("li.dropdown-toggle", "ul.dropdown-menu a", "Third Option");
    }

    @Test
    public void TC_031_React() throws InterruptedException {
        driver.get("https://react.semantic-ui.com/maximize/dropdown-example-search-selection/");
        selectItemInDropdown("div.ui.fluid.search.selection.dropdown", "div.visible.menu.transition div", "Andorra");
        selectItemInDropdown("div.ui.fluid.search.selection.dropdown", "div.visible.menu.transition div", "Albania");
        selectItemInDropdown("div.ui.fluid.search.selection.dropdown", "div.visible.menu.transition div", "Aland Islands");
    }

    @Test
    public void TC_04_Editable() throws InterruptedException {
        driver.get("https://indrimuska.github.io/jquery-editable-select/");
        selectItemInDropdown("div#default-place input", "div#default-place ul li", "BMW");
        selectItemInDropdown("div#slide-place input", "div#slide-place ul li", "Audi");
        selectItemInDropdown("div#fade-place input", "div#fade-place ul li", "Fiat");
    }
    private void selectItemInDropdown(String dropdown, String items, String nameItem) throws InterruptedException {
        driver.findElement(By.cssSelector(dropdown)).click();
        explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(items)));
        Thread.sleep(3000);
        List<WebElement> allItem = driver.findElements(By.cssSelector(items));
        for(WebElement item : allItem) {
            if(item.getText().equals(nameItem)) {
                item.click();
                break;
            }
        }
        Thread.sleep(3000);
        driver.findElement(By.xpath("div#_desktop_logo a")).click();
        Thread.sleep(2000);
        Assert.assertTrue(driver.findElement(By.xpath("//h2[contains(text(), 'Populaarsed tooted')]")).isDisplayed());
    }
    //3. clean: delete data test / account / close browser
    @AfterClass
    public void cleanBrowser() {
        driver.quit(); //dong trinh duyet
    }

}
