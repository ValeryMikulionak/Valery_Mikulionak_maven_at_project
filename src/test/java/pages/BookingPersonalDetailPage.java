package pages;

import driver.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import settings.ConfigProperties;
public class BookingPersonalDetailPage {
    WebDriver driver = Driver.getDriver();

    public void openPage() {
        driver.get(ConfigProperties.property.getProperty("bookingPersonalDetailsUrl"));
    }

    public Boolean checkVerify() {
        try {
            driver.findElement(By.xpath("//span[contains(text(), 'Verified')]"));
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }
}