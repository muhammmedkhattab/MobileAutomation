package tests.ios;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.ios.IOSSauceDemoCartPage;
import pages.ios.IOSSauceDemoCheckoutPage;
import pages.ios.IOSSauceDemoLoginPage;
import pages.ios.IOSSauceDemoProductsPage;

/**
 * iOS UI Test 5 — Checkout flow.
 * Verifies that a user can proceed from the cart through to the payment screen.
 */
public class IOSSauceDemoCheckoutTest extends IOSTestBase {

    private IOSSauceDemoCartPage cartPage;
    private IOSSauceDemoCheckoutPage checkoutPage;

    @BeforeClass
    public void loginAndAddItem() {
        IOSSauceDemoLoginPage loginPage = new IOSSauceDemoLoginPage(driver);
        loginPage.login(sauceUsername, saucePassword);

        IOSSauceDemoProductsPage productsPage = new IOSSauceDemoProductsPage(driver);
        productsPage.addFirstProductToCart();
        productsPage.openCart();

        cartPage = new IOSSauceDemoCartPage(driver);
        checkoutPage = new IOSSauceDemoCheckoutPage(driver);
    }

    @Test(priority = 1, description = "Checkout page should be reachable from the cart")
    public void navigateToCheckout() {
        cartPage.proceedToCheckout();
        Assert.assertTrue(checkoutPage.isOnCheckoutPage(),
            "Checkout screen should be visible after tapping 'Proceed To Checkout'");
    }

    @Test(priority = 2, description = "Filling shipping info and continuing should reach payment",
          dependsOnMethods = "navigateToCheckout")
    public void fillShippingAndProceedToPayment() {
        checkoutPage.fillShippingInfo(
            "Test User",
            "123 Main Street",
            "Springfield",
            "IL",
            "62701"
        );
        checkoutPage.tapContinueToPayment();

        Assert.assertTrue(checkoutPage.isOnPaymentPage(),
            "Payment screen should be visible after completing shipping info");
    }
}
