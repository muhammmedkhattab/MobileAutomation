package tests.android;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.android.SauceDemoCartPage;
import pages.android.SauceDemoLoginPage;
import pages.android.SauceDemoProductsPage;

/**
 * Android UI Test 3 — Shopping cart.
 * Verifies adding an item to the cart and removing it.
 */
public class SauceDemoCartTest extends AndroidTestBase {

    private SauceDemoProductsPage productsPage;
    private SauceDemoCartPage cartPage;

    @BeforeClass
    public void loginFirst() {
        SauceDemoLoginPage loginPage = new SauceDemoLoginPage(driver);
        loginPage.login(sauceUsername, saucePassword);
        productsPage = new SauceDemoProductsPage(driver);
        cartPage = new SauceDemoCartPage(driver);
    }

    @Test(priority = 1, description = "Adding a product should increase the cart item count")
    public void addProductToCart() {
        productsPage.addFirstProductToCart();
        productsPage.openCart();

        Assert.assertTrue(cartPage.isOnCartPage(), "Should be on the Cart page");
        int itemCount = cartPage.getCartItemCount();
        Assert.assertEquals(itemCount, 1, "Cart should contain exactly 1 item after adding one");
    }

    @Test(priority = 2, description = "Removing a product should empty the cart",
          dependsOnMethods = "addProductToCart")
    public void removeProductFromCart() {
        cartPage.removeFirstItem();

        int itemCount = cartPage.getCartItemCount();
        Assert.assertEquals(itemCount, 0, "Cart should be empty after removing the only item");
    }
}
