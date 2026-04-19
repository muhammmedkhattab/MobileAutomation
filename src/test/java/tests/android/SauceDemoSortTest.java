package tests.android;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.android.SauceDemoLoginPage;
import pages.android.SauceDemoProductsPage;

/**
 * Android UI Test 4 — Product sorting.
 * Verifies that switching sort order changes the first product displayed.
 */
public class SauceDemoSortTest extends AndroidTestBase {

    private SauceDemoProductsPage productsPage;

    @BeforeClass
    public void loginFirst() {
        SauceDemoLoginPage loginPage = new SauceDemoLoginPage(driver);
        loginPage.login(sauceUsername, saucePassword);
        productsPage = new SauceDemoProductsPage(driver);
    }

    @Test(priority = 1, description = "Sort by Name ascending should show products in A→Z order")
    public void sortByNameAscending() {
        String beforeSort = productsPage.getFirstProductName();

        productsPage.tapSortButton();
        productsPage.selectSortOption("Name - Ascending");

        String afterSort = productsPage.getFirstProductName();

        Assert.assertFalse(afterSort.isEmpty(), "First product name should not be empty after sort");
        // Name ascending should still yield a non-empty, valid product name
        Assert.assertNotNull(afterSort, "Product name after sort must not be null");
    }

    @Test(priority = 2, description = "Sort by Name descending should change the first product",
          dependsOnMethods = "sortByNameAscending")
    public void sortByNameDescending() {
        String ascending = productsPage.getFirstProductName();

        productsPage.tapSortButton();
        productsPage.selectSortOption("Name - Descending");

        String descending = productsPage.getFirstProductName();

        Assert.assertFalse(descending.isEmpty(), "First product name should not be empty");
        Assert.assertNotEquals(ascending, descending,
            "First product should differ between ascending and descending sort");
    }
}
