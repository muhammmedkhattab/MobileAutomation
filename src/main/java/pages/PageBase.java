package pages;

import java.time.Duration;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.AppiumDriver;

public class PageBase {
    public AppiumDriver driver;

    public PageBase(AppiumDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    protected void clickButton(WebElement element) {
        element.click();
    }

    protected void setTextElement(WebElement element, String value) {
        element.sendKeys(value);
    }

    public void clearText(WebElement element) {
        element.clear();
    }

    public void waitForElement(WebElement element) {
        try {
            new WebDriverWait(driver, Duration.ofSeconds(20))
                .until(ExpectedConditions.visibilityOf(element));
        } catch (Exception e) {
            System.out.println("waitForElement exception: " + e.getMessage());
        }
    }

    protected void hideKeyboard() {
        driver.executeScript("mobile: hideKeyboard");
    }
}
