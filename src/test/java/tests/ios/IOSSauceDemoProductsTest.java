package tests.ios;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.ios.IOSSauceDemoLoginPage;
import pages.ios.IOSSauceDemoProductsPage;

/**
 * iOS UI Test 2 — Products catalogue.
 * Verifies that the product list loads and individual product details open.
 */
public class IOSSauceDemoProductsTest extends IOSTestBase {

    private IOSSauceDemoProductsPage productsPage;

    @BeforeClass
    public void loginFirst() {
        IOSSauceDemoLoginPage loginPage = new IOSSauceDemoLoginPage(driver);
        loginPage.login(sauceUsername, saucePassword);
        productsPage = new IOSSauceDemoProductsPage(driver);
    }

    @Test(priority = 1, description = "Products page should display at least one item")
    public void productsListIsPopulated() {
        Assert.assertTrue(productsPage.isOnProductsPage(), "Should be on the Products page");
        int count = productsPage.getProductCount();
        Assert.assertTrue(count > 0, "Product list should contain at least one item, found: " + count);
    }

    @Test(priority = 2, description = "Tapping a product should open its detail screen")
    public void tapProductOpensDetailScreen() {
        productsPage.tapFirstProduct();

        Assert.assertFalse(productsPage.isOnProductsPage(),
            "Detail screen should replace the products list after tapping a product");
    }
}
