package tests.android;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.android.SauceDemoLoginPage;
import pages.android.SauceDemoProductsPage;

/**
 * Android UI Test 1 — Login scenarios.
 * Verifies successful login and login with invalid credentials.
 */
public class SauceDemoLoginTest extends AndroidTestBase {

    @Test(priority = 2, description = "Valid credentials should navigate to the Products screen")
    public void loginWithValidCredentials() {
        SauceDemoLoginPage loginPage = new SauceDemoLoginPage(driver);
        SauceDemoProductsPage productsPage = new SauceDemoProductsPage(driver);

        Assert.assertTrue(loginPage.isOnLoginPage(), "Login page should be visible on launch");

        loginPage.login(sauceUsername, saucePassword);

        Assert.assertTrue(productsPage.isOnProductsPage(),
            "Products page should be visible after successful login");
    }

    @Test(priority = 1, description = "Wrong password should display an error message")
    public void loginWithInvalidPassword() {
        SauceDemoLoginPage loginPage = new SauceDemoLoginPage(driver);

        loginPage.login(sauceUsername, "wrong_password_12345");

        Assert.assertTrue(loginPage.isLoginErrorDisplayed(),
            "An error message should appear for invalid credentials");
        Assert.assertTrue(loginPage.isOnLoginPage(),
            "User should remain on the Login page after failed login");
    }
}
