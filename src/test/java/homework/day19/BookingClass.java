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

public class BookingClass {

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
    public void checkingPrice() throws InterruptedException {

        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_MONTH, 3);
        Date day = cal.getTime();
        SimpleDateFormat formatDate = new SimpleDateFormat("d MMMM y");
        String pat = formatDate.format(day);
        cal.add(Calendar.DAY_OF_MONTH, 7);
        Date day2 = cal.getTime();
        String pat2 = formatDate.format(day2);

        driver.findElement(By.xpath("//*[contains(@action,'searchresults')]/div/div[2]")).click();
        driver.findElement(By.xpath("//*[@aria-label='" + pat + "']")).click();
        driver.findElement(By.xpath("//*[@aria-label='" + pat2 + "']")).click();

        WebElement el = driver.findElement(By.xpath("//input[@name='ss']"));
        el.click();
        el.sendKeys("Paris");
//        driver.findElement(By.xpath("//*[contains(@action,'searchresults')]/div/div[1]//ul/li[1]")).click();
        driver.findElement(By.xpath("//*[contains(@action,'searchresults')]/div/div[3]")).click();
        WebElement el1 = driver.findElement(By.xpath("//*[@for='group_adults']//..//../div[2]/button[2]"));
        el1.click();
        el1.click();
        WebElement el2 = driver.findElement(By.xpath("//*[@for='no_rooms']//..//../div[2]/button[2]"));
        el2.click();
        driver.findElement(By.xpath("//*[contains(@action,'searchresults')]/div/div[4]")).click();
        WebElement el3 = driver.findElement(By.xpath("//div[@data-filters-item='pri:pri=5']/label/span[2]"));
        el3.click();
        driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(30))
                .pollingEvery(Duration.ofMillis(5))
                .ignoring(NoSuchElementException.class)
                .until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@data-testid='overlay-spinner']")));

        String cost = driver.findElement
                        (By.xpath("//div[@data-filters-item='pri:pri=5']/label/span[3]/div/div/div"))
                .getText();
        String[] str0 = cost.split(" ");
        int priceDay = Integer.parseInt(str0[1].trim());
        System.out.println(priceDay);

        driver.findElement(By.xpath("//button[@data-testid='sorters-dropdown-trigger']")).click();
        driver.findElement(By.xpath("//div[@data-testid='sorters-dropdown']//ul/li[3]")).click();
        driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(30))
                .pollingEvery(Duration.ofMillis(5))
                .ignoring(NoSuchElementException.class)
                .until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@data-testid='overlay-spinner']")));

        String att = driver.findElement
                        (By.xpath("//div[@data-testid='availability-rate-information'][1]/span/div/span[2]"))
                .getText();
        String att1 = att.replaceAll(",", "");
        String[] str = att1.split(" ");
        int priceWeek = Integer.parseInt(str[1].trim());
        System.out.println(priceWeek);

        assertTrue("Стоимость ниже выбранной", priceWeek / 7 >= priceDay);
    }

    @After
    public void browserClose() {
        driver.close();
        driver.quit();
    }
}