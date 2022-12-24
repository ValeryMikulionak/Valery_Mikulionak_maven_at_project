package tests.testng;

import org.testng.annotations.Test;
import pages.BookingLoginPage;
import pages.BookingPersonalDetailPage;
import pages.BookingRegisterPage;
import pages.MailPage;
import settings.ConfigProperties;
import static org.testng.AssertJUnit.*;

public class RegisterTest extends BaseTest{

    private MailPage emailPage = new MailPage();
    private BookingRegisterPage registerPage = new BookingRegisterPage();
    private BookingLoginPage loginPage = new BookingLoginPage();
    private BookingPersonalDetailPage personalDetailsPage = new BookingPersonalDetailPage();

    @Test
    public void bookingRegistration() throws InterruptedException {
        registerPage.openPage();
        registerPage.inputEmail(ConfigProperties.property.getProperty("emailName") +
                ConfigProperties.property.getProperty("emailDomain"));
        registerPage.clickSubmitButton();
        registerPage.inputPass(ConfigProperties.property.getProperty("userPass"));
        registerPage.clickSubmitButton();

        emailPage.openPage();
        emailPage.loginMail(ConfigProperties.property.getProperty("emailName"));
        Thread.sleep(5000);
        emailPage.refreshMail();
        emailPage.findMail();
        emailPage.confirmMail();

        loginPage.openPage();
        loginPage.inputEmail(ConfigProperties.property.getProperty("emailName") +
                ConfigProperties.property.getProperty("emailDomain"));
        loginPage.clickSubmitButton();
        loginPage.inputPass(ConfigProperties.property.getProperty("userPass"));
        loginPage.clickSubmitButton();
//        loginPage.closePopup();

        personalDetailsPage.openPage();
        assertTrue("Нет зеленой галочки Verify", personalDetailsPage.checkVerify());
    }
}