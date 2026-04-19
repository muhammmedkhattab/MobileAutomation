package pages.ios;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import pages.IOSPageBase;

import java.util.List;

public class IOSSauceDemoProductsPage extends IOSPageBase {

    public IOSSauceDemoProductsPage(AppiumDriver driver) {
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
            AppiumBy.accessibilityId("Add To Cart button")
        );
        if (!addButtons.isEmpty()) {
            clickButton(addButtons.get(0));
        }
    }

    public void tapFirstProduct() {
        WebElement first = driver.findElement(
            By.xpath("(//XCUIElementTypeOther[@name='store item'])[1]")
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
            By.xpath("//XCUIElementTypeStaticText[@name='" + optionText + "']")
        );
        waitForElement(option);
        clickButton(option);
    }

    public void openCart() {
        WebElement cart = driver.findElement(AppiumBy.accessibilityId("cart badge"));
        waitForElement(cart);
        clickButton(cart);
    }
}
