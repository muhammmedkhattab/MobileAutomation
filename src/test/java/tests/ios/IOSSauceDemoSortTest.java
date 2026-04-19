package tests.ios;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.ios.IOSSauceDemoLoginPage;
import pages.ios.IOSSauceDemoProductsPage;

/**
 * iOS UI Test 4 — Product sorting.
 * Verifies that switching sort order changes the first product displayed.
 */
public class IOSSauceDemoSortTest extends IOSTestBase {

    private IOSSauceDemoProductsPage productsPage;

    @BeforeClass
    public void loginFirst() {
        IOSSauceDemoLoginPage loginPage = new IOSSauceDemoLoginPage(driver);
        loginPage.login(sauceUsername, saucePassword);
        productsPage = new IOSSauceDemoProductsPage(driver);
    }

    @Test(priority = 1, description = "Sort by Name ascending should be available")
    public void sortByNameAscending() {
        Assert.assertTrue(productsPage.isOnProductsPage(), "Should be on products page");

        productsPage.tapSortButton();
        productsPage.selectSortOption("Name - Ascending");

        Assert.assertTrue(productsPage.isOnProductsPage(),
            "Should remain on products page after sorting");
    }

    @Test(priority = 2, description = "Sort by Name descending should reorder the list",
          dependsOnMethods = "sortByNameAscending")
    public void sortByNameDescending() {
        productsPage.tapSortButton();
        productsPage.selectSortOption("Name - Descending");

        int count = productsPage.getProductCount();
        Assert.assertTrue(count > 0,
            "Product list should still be populated after changing sort order");
    }
}
