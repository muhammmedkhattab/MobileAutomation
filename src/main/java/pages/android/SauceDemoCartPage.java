package pages.android;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;
import pages.PageBase;

import java.util.List;

public class SauceDemoCartPage extends PageBase {

    public SauceDemoCartPage(AppiumDriver driver) {
        super(driver);
    }

    public boolean isOnCartPage() {
        try {
            return driver.findElement(AppiumBy.accessibilityId("cart screen")).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public int getCartItemCount() {
        return driver.findElements(AppiumBy.accessibilityId("cart item")).size();
    }

    public void proceedToCheckout() {
        WebElement btn = driver.findElement(AppiumBy.accessibilityId("Proceed To Checkout button"));
        waitForElement(btn);
        clickButton(btn);
    }

    public void removeFirstItem() {
        List<WebElement> removeButtons = driver.findElements(
            AppiumBy.accessibilityId("Remove Item button")
        );
        if (!removeButtons.isEmpty()) {
            clickButton(removeButtons.get(0));
        }
    }
}
