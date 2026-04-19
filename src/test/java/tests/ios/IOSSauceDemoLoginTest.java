package tests.ios;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.ios.IOSSauceDemoLoginPage;
import pages.ios.IOSSauceDemoProductsPage;

/**
 * iOS UI Test 1 — Login scenarios.
 * Verifies successful login and login with invalid credentials.
 */
public class IOSSauceDemoLoginTest extends IOSTestBase {

    @Test(priority = 1, description = "Valid credentials should navigate to the Products screen")
    public void loginWithValidCredentials() {
        IOSSauceDemoLoginPage loginPage = new IOSSauceDemoLoginPage(driver);
        IOSSauceDemoProductsPage productsPage = new IOSSauceDemoProductsPage(driver);

        Assert.assertTrue(loginPage.isOnLoginPage(), "Login page should be visible on launch");

        loginPage.login(sauceUsername, saucePassword);

        Assert.assertTrue(productsPage.isOnProductsPage(),
            "Products page should be visible after successful login");
    }

    @Test(priority = 2, description = "Wrong password should display an error message")
    public void loginWithInvalidPassword() {
        IOSSauceDemoLoginPage loginPage = new IOSSauceDemoLoginPage(driver);

        loginPage.login(sauceUsername, "wrong_password_12345");

        Assert.assertTrue(loginPage.isLoginErrorDisplayed(),
            "An error message should appear for invalid credentials");
        Assert.assertTrue(loginPage.isOnLoginPage(),
            "User should remain on the Login page after failed login");
    }
}
