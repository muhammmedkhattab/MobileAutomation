package tests.android;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.android.SauceDemoLoginPage;
import pages.android.SauceDemoProductsPage;

/**
 * Android UI Test 2 — Products catalogue.
 * Verifies that the product list loads and individual product details open.
 */
public class SauceDemoProductsTest extends AndroidTestBase {

    private SauceDemoProductsPage productsPage;

    @BeforeClass
    public void loginFirst() {
        SauceDemoLoginPage loginPage = new SauceDemoLoginPage(driver);
        loginPage.login(sauceUsername, saucePassword);
        productsPage = new SauceDemoProductsPage(driver);
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

        // The detail screen should not show the products list any more
        Assert.assertFalse(productsPage.isOnProductsPage(),
            "Detail screen should replace the products list");
    }
}
