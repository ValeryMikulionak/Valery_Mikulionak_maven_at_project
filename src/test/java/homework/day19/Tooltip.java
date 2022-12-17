package homework.day19;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.util.concurrent.TimeUnit;

public class Tooltip {

    private WebDriver driver;

    @Before
    public void browserOpen() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        driver.manage().timeouts().setScriptTimeout(20, TimeUnit.SECONDS);
    }

    @Test
    public void checkingCurrency() throws InterruptedException {

        driver.get("https://booking.com/");
        WebElement cur = driver.findElement(By.xpath("//div[@class='bui-group__item'][1]/button"));
        Actions act = new Actions(driver);
        act.moveToElement(cur);
        act.perform();
        String str = cur.getAttribute("data-tooltip-text");
        Assert.assertEquals("Текст подсказки для валюты неверный", "Choose your currency", "" + str + "");

        WebElement lang = driver.findElement(By.xpath("//div[@class='bui-group__item'][2]/button"));
        Actions actions = new Actions(driver);
        actions.moveToElement(lang);
        actions.perform();
        String str1 = lang.getAttribute("data-tooltip-text");
        Assert.assertEquals("Текст подсказки для языка неверный", "Choose your language", "" + str1 + "");
    }

        @After
        public void browserClose() {
            driver.close();
            driver.quit();
    }
}