package driver;

import org.openqa.selenium.WebDriver;

public class Driver {

    private static WebDriver driver;

    public static void initDriver(Config config) {
        if (driver == null) {
            driver = DriverManager.getDriver(config);
        }
    }

    public static WebDriver getDriver() {
        return driver;
    }

    public static void setDriver(WebDriver driver) {
        Driver.driver = driver;
    }

    public static void closeDriver() {
        driver.quit();
    }
}