package classwork.day23;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class Mobilka {

    public static void main(String[] args) throws MalformedURLException, InterruptedException {

        DesiredCapabilities caps = new DesiredCapabilities();

        caps.setCapability("deviceName", "Pixel 3 API 29");
        caps.setCapability("platformName", "android");
        caps.setCapability("browserName", "chrome");

        WebDriver driver = new RemoteWebDriver(new URL("http://0.0.0.0:4723/wd/hub"), caps);

        driver.get("https://mail.ru/");
        System.out.println("Job done");
        Thread.sleep(5000);
        driver.quit();

    }
}
