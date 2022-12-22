package tests;

import driver.Config;
import driver.Driver;
import driver.DriverManager;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertFalse;

public class BaseTest {

    WebDriver driver = Driver.getDriver();

    @BeforeClass
    public static void initDriver() {
        Driver.initDriver(Config.valueOf(System.getenv("BROWSER1")));
        Driver.getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        Driver.getDriver().manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        Driver.getDriver().manage().timeouts().setScriptTimeout(10, TimeUnit.SECONDS);

    }

    @Test
    public void checkAvailableHotel() {
        driver.get("https://booking.com/");
        WebElement el = driver.findElement(By.xpath("//input[@name='ss']"));
        el.click();
        el.sendKeys("Milano");
        driver.findElement(By.xpath("//*[contains(@action,'searchresults')]/div/div[4]")).click();
        assertFalse("Отелей на выбранные даты нет",
                driver.findElements(By.xpath("//div[@data-component='arp-header']/div/div/div/h1")).isEmpty());
    }
//        Assert.assertTrue("Нет отелей на введенные даты", driver.findElement(By.cssSelector("h1[class]")).isDisplayed());
//    }

        @AfterClass
        public static void closeDriver () {
            Driver.closeDriver();
        }
    }