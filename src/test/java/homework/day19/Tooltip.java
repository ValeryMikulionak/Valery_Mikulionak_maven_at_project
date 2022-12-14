package homework.day19;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

public class Tooltip {

    private static WebDriver driver;

    @Before
    public void browserOpen() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        driver.manage().timeouts().setScriptTimeout(20, TimeUnit.SECONDS);
        driver.get("https://booking.com/");
    }

    @Test
    public void checkingCurrency() throws InterruptedException {

        WebElement cur = driver.findElement(By.xpath("//div[@class='bui-group__item'][1]/button"));
        Actions act = new Actions(driver);
        act.moveToElement(cur);
        act.perform();
        String str = cur.getAttribute("title");
        System.out.println(str + 1);
        assertSame("Стоимость ниже выбранной", "Choose your currency", "" + str + "");
    }

    @Test
    public void checkingLanguage() throws InterruptedException {

        WebElement lang = driver.findElement(By.xpath("//div[@class='bui-group__item'][2]/button"));
        Actions actions = new Actions(driver);
        actions.moveToElement(lang);
        actions.perform();
        lang.getAttribute("title");

    }
}