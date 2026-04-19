package pages.android;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.PageBase;

import java.time.Duration;

public class SauceDemoLoginPage extends PageBase {

    public SauceDemoLoginPage(AppiumDriver driver) {
        super(driver);
    }

    public void login(String username, String password) {
        WebElement usernameField = driver.findElement(AppiumBy.accessibilityId("Username input field"));
        WebElement passwordField = driver.findElement(AppiumBy.accessibilityId("Password input field"));
        WebElement loginButton   = driver.findElement(AppiumBy.accessibilityId("Login button"));

        waitForElement(usernameField);
        clearText(usernameField);
        setTextElement(usernameField, username);
        hideKeyboard();

        clearText(passwordField);
        setTextElement(passwordField, password);
        hideKeyboard();

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
            new WebDriverWait(driver, Duration.ofSeconds(15))
                .until(ExpectedConditions.presenceOfElementLocated(
                    AppiumBy.accessibilityId("Login button")));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
