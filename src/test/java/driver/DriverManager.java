package driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.util.concurrent.TimeUnit;

public class DriverManager {

        public static WebDriver getDriver(Config config) {

            switch (config) {
                case CHROME:
                    return getChromeDriver();
                case FF:
                    return getFirefoxDriver();
                default:
                    throw null;
            }
        }

        public static WebDriver getChromeDriver() {
            ChromeOptions opt = new ChromeOptions();
            opt.addArguments("start-maximized");
            WebDriver driver = new ChromeDriver(opt);
            Driver.getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            Driver.getDriver().manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
            Driver.getDriver().manage().timeouts().setScriptTimeout(10, TimeUnit.SECONDS);
            return driver;
        }

        public static WebDriver getFirefoxDriver() {
            FirefoxOptions opt = new FirefoxOptions();
            opt.addArguments("--width=1920");
            opt.addArguments("--height=1080");
            WebDriver driver = new FirefoxDriver(opt);
            Driver.getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            Driver.getDriver().manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
            Driver.getDriver().manage().timeouts().setScriptTimeout(10, TimeUnit.SECONDS);
            return driver;
        }
    }