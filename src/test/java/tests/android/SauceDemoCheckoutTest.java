package tests.android;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.android.SauceDemoCartPage;
import pages.android.SauceDemoCheckoutPage;
import pages.android.SauceDemoLoginPage;
import pages.android.SauceDemoProductsPage;

/**
 * Android UI Test 5 — Checkout flow.
 * Verifies that a user can proceed from the cart through to the payment screen.
 */
public class SauceDemoCheckoutTest extends AndroidTestBase {

    private SauceDemoProductsPage productsPage;
    private SauceDemoCartPage cartPage;
    private SauceDemoCheckoutPage checkoutPage;

    @BeforeClass
    public void loginAndAddItem() {
        SauceDemoLoginPage loginPage = new SauceDemoLoginPage(driver);
        loginPage.login(sauceUsername, saucePassword);

        productsPage = new SauceDemoProductsPage(driver);
        productsPage.addFirstProductToCart();
        productsPage.openCart();

        cartPage = new SauceDemoCartPage(driver);
        checkoutPage = new SauceDemoCheckoutPage(driver);
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
