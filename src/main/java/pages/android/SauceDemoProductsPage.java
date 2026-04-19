package pages.android;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import pages.PageBase;

import java.util.List;

public class SauceDemoProductsPage extends PageBase {

    public SauceDemoProductsPage(AppiumDriver driver) {
        super(driver);
    }

    public boolean isOnProductsPage() {
        try {
            return driver.findElement(AppiumBy.accessibilityId("products screen")).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public int getProductCount() {
        return driver.findElements(AppiumBy.accessibilityId("store item")).size();
    }

    public void addFirstProductToCart() {
        List<WebElement> addButtons = driver.findElements(
            By.xpath("//android.view.ViewGroup[@content-desc='Add To Cart button']")
        );
        if (!addButtons.isEmpty()) {
            clickButton(addButtons.get(0));
        }
    }

    public void tapFirstProduct() {
        WebElement first = driver.findElement(
            By.xpath("(//android.view.ViewGroup[@content-desc='store item'])[1]")
        );
        waitForElement(first);
        clickButton(first);
    }

    public void tapSortButton() {
        WebElement btn = driver.findElement(AppiumBy.accessibilityId("sort button"));
        waitForElement(btn);
        clickButton(btn);
    }

    public void selectSortOption(String optionText) {
        WebElement option = driver.findElement(
            By.xpath("//android.widget.TextView[@text='" + optionText + "']")
        );
        waitForElement(option);
        clickButton(option);
    }

    public String getFirstProductName() {
        List<WebElement> names = driver.findElements(
            By.xpath("//android.widget.TextView[@content-desc='store item text']")
        );
        return names.isEmpty() ? "" : names.get(0).getText();
    }

    public void openCart() {
        WebElement cart = driver.findElement(AppiumBy.accessibilityId("cart badge"));
        waitForElement(cart);
        clickButton(cart);
    }
}
