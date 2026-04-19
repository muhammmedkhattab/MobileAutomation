package pages.ios;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;
import pages.IOSPageBase;

public class IOSSauceDemoLoginPage extends IOSPageBase {

    public IOSSauceDemoLoginPage(AppiumDriver driver) {
        super(driver);
    }

    public void login(String username, String password) {
        WebElement usernameField = driver.findElement(AppiumBy.accessibilityId("Username input field"));
        WebElement passwordField = driver.findElement(AppiumBy.accessibilityId("Password input field"));
        WebElement loginButton   = driver.findElement(AppiumBy.accessibilityId("Login button"));

        waitForElement(usernameField);
        clearText(usernameField);
        setTextElement(usernameField, username);

        clearText(passwordField);
        setTextElement(passwordField, password);

        clickButton(loginButton);
    }

    public boolean isLoginErrorDisplayed() {
        try {
            return driver.findElement(AppiumBy.accessibilityId("generic-error-message")).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isOnLoginPage() {
        try {
            return driver.findElement(AppiumBy.accessibilityId("Login button")).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
