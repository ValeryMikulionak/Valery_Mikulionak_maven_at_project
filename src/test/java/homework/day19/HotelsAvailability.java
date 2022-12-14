package homework.day19;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertFalse;

public class HotelsAvailability {

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
    public void checkingAvailability() throws InterruptedException {

        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_MONTH, 5);
        Date day = cal.getTime();
        SimpleDateFormat formatDate = new SimpleDateFormat("d MMMM y");
        String pat = formatDate.format(day);
        cal.add(Calendar.DAY_OF_MONTH, 10);
        Date day2 = cal.getTime();
        String pat2 = formatDate.format(day2);

        WebElement el = driver.findElement(By.xpath("//input[@name='ss']"));
        el.click();
        el.sendKeys("Milano");
//        driver.findElement(By.xpath("//*[contains(@action,'searchresults')]/div/div[1]//ul/li[1]")).click();

        driver.findElement(By.xpath("//*[contains(@action,'searchresults')]/div/div[2]")).click();
        driver.findElement(By.xpath("//*[@aria-label='" + pat + "']")).click();
        driver.findElement(By.xpath("//*[@aria-label='" + pat2 + "']")).click();
        driver.findElement(By.xpath("//*[contains(@action,'searchresults')]/div/div[4]")).click();

        assertFalse("Отелей на выбранные даты нет",
                driver.findElements(By.xpath("//div[@data-component='arp-header']/div/div/div/h1")).isEmpty());
    }

    @After
    public void browserClose() {
        driver.close();
        driver.quit();
    }
}