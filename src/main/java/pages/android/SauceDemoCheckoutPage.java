package pages.android;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;
import pages.PageBase;

public class SauceDemoCheckoutPage extends PageBase {

    public SauceDemoCheckoutPage(AppiumDriver driver) {
        super(driver);
    }

    public boolean isOnCheckoutPage() {
        try {
            return driver.findElement(AppiumBy.accessibilityId("checkout screen")).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void fillShippingInfo(String fullName, String address,
                                  String city, String state, String zip) {
        fillField("Full Name* input field", fullName);
        fillField("Address Line 1* input field", address);
        fillField("City* input field", city);
        fillField("State/Region input field", state);
        fillField("Zip Code* input field", zip);
    }

    public void tapContinueToPayment() {
        WebElement btn = driver.findElement(AppiumBy.accessibilityId("To Payment button"));
        waitForElement(btn);
        clickButton(btn);
    }

    public boolean isOnPaymentPage() {
        try {
            return driver.findElement(AppiumBy.accessibilityId("payment screen")).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    private void fillField(String accessibilityId, String value) {
        WebElement field = driver.findElement(AppiumBy.accessibilityId(accessibilityId));
        waitForElement(field);
        clearText(field);
        setTextElement(field, value);
    }
}
