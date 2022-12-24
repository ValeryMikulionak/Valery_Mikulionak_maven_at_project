package tests.junit;

import driver.Config;
import driver.Driver;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import settings.ConfigProperties;
import java.util.concurrent.TimeUnit;

public class BaseTest {

    @BeforeClass
    public static void initDriver() {
        ConfigProperties.initPropertyFile();
        Driver.initDriver(Config.valueOf(ConfigProperties.property.getProperty("BROWSER1")));
        Driver.getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        Driver.getDriver().manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        Driver.getDriver().manage().timeouts().setScriptTimeout(10, TimeUnit.SECONDS);

    }

        @AfterClass
        public static void closeDriver () {
            Driver.closeDriver();
        }
   }