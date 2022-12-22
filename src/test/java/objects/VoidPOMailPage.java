package objects;

import driver.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class VoidPOMailPage {

    WebDriver driver = Driver.getDriver();

    public void loginMail() {

        driver.findElement(By.id("login")).sendKeys();
        driver.findElement(By.xpath("//button[@class='md']"));

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

    }
}