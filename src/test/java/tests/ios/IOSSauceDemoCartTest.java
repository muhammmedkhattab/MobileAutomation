package tests.ios;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.ios.IOSSauceDemoCartPage;
import pages.ios.IOSSauceDemoLoginPage;
import pages.ios.IOSSauceDemoProductsPage;

/**
 * iOS UI Test 3 — Shopping cart.
 * Verifies adding an item to the cart and removing it.
 */
public class IOSSauceDemoCartTest extends IOSTestBase {

    private IOSSauceDemoProductsPage productsPage;
    private IOSSauceDemoCartPage cartPage;

    @BeforeClass
    public void loginFirst() {
        IOSSauceDemoLoginPage loginPage = new IOSSauceDemoLoginPage(driver);
        loginPage.login(sauceUsername, saucePassword);
        productsPage = new IOSSauceDemoProductsPage(driver);
        cartPage = new IOSSauceDemoCartPage(driver);
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
