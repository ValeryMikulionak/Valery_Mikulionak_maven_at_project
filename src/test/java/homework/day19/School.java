package homework.day19;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.util.concurrent.TimeUnit;

public class School {

    private static WebDriver driver;

    @Before
    public void browserOpen() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        driver.manage().timeouts().setScriptTimeout(20, TimeUnit.SECONDS);
        driver.get("https://www.w3schools.com/java/");
    }

    @Test
    public void checkingSearchResults() throws InterruptedException {

        Actions make = new Actions(driver);
        make.doubleClick(driver.findElement(By.xpath("//div[@id='main']/h1/span")))
                .keyDown(Keys.LEFT_CONTROL)
                .sendKeys("c")
                .keyUp(Keys.LEFT_CONTROL)
                .build().perform();

        driver.get("https://www.google.com/");
        WebElement search = driver.findElement(By.name("q"));
        search.click();
        make.keyDown(Keys.LEFT_CONTROL)
                .sendKeys("v")
                .keyUp(Keys.LEFT_CONTROL)
                .build().perform();
        make.sendKeys(Keys.ENTER)
                .build().perform();

    }
}