package homework.day19;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class School {

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
    public void checkingSearchResults() throws InterruptedException {

        driver.get("https://www.w3schools.com/java/");
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

        List<WebElement> list = driver.findElements(By.xpath("//div[@data-header-feature='0']//h3"));

        List<String> titles = list.stream()
                .map(WebElement::getText)
                .map(String::toLowerCase)
                .collect(Collectors.toList());

        Assert.assertTrue("Не все результаты поиска содержат введенное слово",
                titles.stream().allMatch(i -> i.contains("tutorial")));
    }

    @After
    public void browserClose() {
        driver.close();
        driver.quit();
    }
}