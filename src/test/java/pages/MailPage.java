package pages;

import driver.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import settings.ConfigProperties;

import java.util.ArrayList;

public class MailPage {

    WebDriver driver = Driver.getDriver();

    public void openPage(){
        driver.get(ConfigProperties.property.getProperty("emailUrl"));
    }

    public void loginMail(String email) {

        driver.findElement(By.id("login")).clear();
        driver.findElement(By.id("login")).sendKeys(email);
        driver.findElement(By.xpath("//button[@class='md']")).click();

    }

    public void refreshMail() {

        driver.findElement(By.id("refresh")).click();

    }

    public void findMail() {

        driver.switchTo().frame(driver.findElement(By.id("ifinbox")));
        driver.findElement(By.xpath("//span[contains(text(),'Booking.com')]/../..")).click();
        driver.switchTo().defaultContent();

    }

    public void confirmMail() {

        driver.switchTo().frame(driver.findElement(By.id("ifmail")));
        driver.findElement(By.xpath("//a[contains(text(),'Confirm')]")).click();
        driver.switchTo().defaultContent();
        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));

    }
}