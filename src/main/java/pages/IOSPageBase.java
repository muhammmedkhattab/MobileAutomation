package pages;

import io.appium.java_client.AppiumDriver;

/**
 * Base class for iOS page objects.
 * Extends PageBase — both platforms now share AppiumDriver as the driver type.
 */
public class IOSPageBase extends PageBase {
    public IOSPageBase(AppiumDriver driver) {
        super(driver);
    }
}
