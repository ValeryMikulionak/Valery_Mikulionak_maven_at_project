package homework.day19;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.concurrent.TimeUnit;

public class Spisok {

    private static WebDriver driver;

    @Before
    public void browserOpen() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        driver.manage().timeouts().setScriptTimeout(20, TimeUnit.SECONDS);
        driver.get("https://demoqa.com/select-menu");
    }

    public static void main(String[] args) throws InterruptedException {

        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        driver.manage().timeouts().setScriptTimeout(20, TimeUnit.SECONDS);
        driver.get("https://demoqa.com/select-menu");
        Thread.sleep(1000);
        WebElement el = driver.findElement(By.id("withOptGroup"));
        Select sel = new Select(el);
        sel.selectByValue("A root option");

        WebElement el1 = driver.findElement(By.id("selectOne"));
        Select sel1 = new Select(el1);
        sel1.selectByValue("Mr.");

        WebElement el2 = driver.findElement(By.id("oldSelectMenu"));
        Select sel2 = new Select(el2);
        sel2.selectByValue("Purple");

        WebElement el3 = driver.findElement(By.xpath("//*[text()='Select...']"));
        Select sel3 = new Select(el3);
        sel3.selectByValue("Green");

    }

}
