package tests;

import org.testng.annotations.Test;
import pages.LoginPage;
import utilities.ConfigReader;

public class LoginTest extends TestBase {

    @Test(priority = 1)
    public void login() throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(
            ConfigReader.getCredential("TRUDOC_PHONE"),
            ConfigReader.getCredential("TRUDOC_PASSWORD")
        );
    }
}
