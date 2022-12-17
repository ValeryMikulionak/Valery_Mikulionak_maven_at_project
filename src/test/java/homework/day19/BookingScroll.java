package homework.day19;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertTrue;

public class BookingScroll {

    private static WebDriver driver;

    @Before
    public void browserOpen() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        driver.manage().timeouts().setScriptTimeout(20, TimeUnit.SECONDS);
    }

    @Test
    public void ColorMatching() throws InterruptedException {

        driver.get("https://booking.com/");
        WebElement el = driver.findElement(By.xpath("//input[@name='ss']"));
        el.click();
        el.sendKeys("Paris");
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[contains(@action,'searchresults')]/div/div[1]//ul/li[1]")).click();
        driver.findElement(By.xpath("//*[contains(@action,'searchresults')]/div/div[4]")).click();

        WebElement el1 = driver.findElement(By.xpath("//div[@data-testid='property-card'][10]"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true)", el1);
        ((JavascriptExecutor) driver).executeScript("arguments[0].style.backgroundColor = 'green'", el1);
        WebElement el2 = driver.findElement(By.xpath("//div[@data-testid='property-card'][10]/div/div[2]//a/div[1]"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].style.color = 'red'", el2);

        String color = driver.findElement
                        (By.xpath("//div[@data-testid='property-card'][10]/div/div[2]//a/div[1]"))
                .getAttribute("style");
        System.out.println(color);

        assertTrue("Цвет названия не красный", color.contains("red"));
    }

    @After
    public void browserClose() {
        driver.close();
        driver.quit();
    }
}