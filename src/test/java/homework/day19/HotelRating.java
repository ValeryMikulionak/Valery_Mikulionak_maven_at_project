package homework.day19;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.Date;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertTrue;

public class HotelRating {

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
        cal.add(Calendar.DAY_OF_MONTH, 10);
        Date day = cal.getTime();
        SimpleDateFormat formatDate = new SimpleDateFormat("d MMMM y");
        String pat = formatDate.format(day);
        cal.add(Calendar.DAY_OF_MONTH, 15);
        Date day2 = cal.getTime();
        String pat2 = formatDate.format(day2);

//        String mainTab = driver.getWindowHandle();
        WebElement el = driver.findElement(By.xpath("//input[@name='ss']"));
        el.click();
        el.sendKeys("Izmir");
//        driver.findElement(By.xpath("//*[contains(@action,'searchresults')]/div/div[1]//ul/li[1]")).click();

        driver.findElement(By.xpath("//*[contains(@action,'searchresults')]/div/div[2]")).click();
        driver.findElement(By.xpath("//*[@aria-label='" + pat + "']")).click();
        driver.findElement(By.xpath("//*[@aria-label='" + pat2 + "']")).click();
        driver.findElement(By.xpath("//*[contains(@action,'searchresults')]/div/div[4]")).click();

        if (driver.findElements(By.xpath("//div[@data-component='arp-header']/div/div/div/h1")).isEmpty()) {
            System.out.println("Отелей нет");
        } else {
            driver.findElement(By.xpath("//button[@data-testid='sorters-dropdown-trigger']")).click();
            driver.findElement(By.xpath("//div[@data-testid='sorters-dropdown']//ul/li[4]")).click();
            driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
            new FluentWait<>(driver)
                    .withTimeout(Duration.ofSeconds(30))
                    .pollingEvery(Duration.ofMillis(5))
                    .ignoring(NoSuchElementException.class)
                    .until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@data-testid='overlay-spinner']")));

            driver.findElement(By.xpath("//div[@data-testid='property-card'][1]/div/div[1]")).click();
            driver.navigate().forward();

            for (String tab : driver.getWindowHandles()) {
                driver.switchTo().window(tab);
            }

            String att = driver.findElement
                            (By.xpath("//div[@data-testid='review-score-right-component']/div[1]"))
                    .getAttribute("aria-label");
            String[] str = att.split(" ");
            System.out.println(str[1]);
            float f = Float.parseFloat(str[1].trim());

            assertTrue("Рейтинг отеля ниже 9", f >= 9);

//            driver.switchTo().window(mainTab);
        }
    }

    @After
    public void browserClose() {
        driver.close();
        driver.quit();
    }
}