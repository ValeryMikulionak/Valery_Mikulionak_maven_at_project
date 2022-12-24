package steps;

import driver.Config;
import driver.Driver;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import pages.BookingLoginPage;
import pages.BookingPersonalDetailPage;
import pages.BookingRegisterPage;
import pages.MailPage;
import settings.ConfigProperties;

import java.util.concurrent.TimeUnit;

public class BookingRegisterCucumber {

    private MailPage emailPage = new MailPage();
    private BookingRegisterPage registerPage = new BookingRegisterPage();
    private BookingLoginPage loginPage = new BookingLoginPage();
    private BookingPersonalDetailPage personalDetailsPage = new BookingPersonalDetailPage();

    @Before
    public static void initDriver() {
        ConfigProperties.initPropertyFile();
        Driver.initDriver(Config.valueOf(ConfigProperties.property.getProperty("BROWSER1")));
        Driver.getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        Driver.getDriver().manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        Driver.getDriver().manage().timeouts().setScriptTimeout(10, TimeUnit.SECONDS);
    }

    @Given("I register new user")
    public void registerUser() {
        registerPage.openPage();
        registerPage.inputEmail(ConfigProperties.property.getProperty("emailName") +
                ConfigProperties.property.getProperty("emailDomain"));
        registerPage.clickSubmitButton();
        registerPage.inputPass(ConfigProperties.property.getProperty("userPass"));
        registerPage.clickSubmitButton();
        throw new cucumber.api.PendingException();
    }

    @When("I confirm email")
    public void confirmEmail() throws InterruptedException {
        emailPage.openPage();
        emailPage.loginMail(ConfigProperties.property.getProperty("emailName"));
        Thread.sleep(6000);
        emailPage.refreshMail();
        emailPage.findMail();
        emailPage.confirmMail();
        throw new cucumber.api.PendingException();
    }

    @When("I login as user")
    public void loginUser() {
        loginPage.openPage();
        loginPage.inputEmail(ConfigProperties.property.getProperty("emailName") +
                ConfigProperties.property.getProperty("emailDomain"));
        loginPage.clickSubmitButton();
        loginPage.inputPass(ConfigProperties.property.getProperty("userPass"));
        loginPage.clickSubmitButton();
        loginPage.closePopup();
        throw new cucumber.api.PendingException();
    }

    @Then("I check my verify")
    public void doAssert() {
        personalDetailsPage.openPage();
        Assert.assertTrue("Нет иконки подтверждения email адреса", personalDetailsPage.checkVerify());
        throw new cucumber.api.PendingException();
    }

    @After
    public static void closeDriver() {
        Driver.closeDriver();
    }

}