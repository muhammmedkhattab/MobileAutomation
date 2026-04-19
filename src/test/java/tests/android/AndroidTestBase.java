package tests.android;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import utilities.ConfigReader;
import utilities.Helper;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

public class AndroidTestBase {

    protected AppiumDriver driver;
    protected String sauceUsername;
    protected String saucePassword;

    @BeforeClass
    public void setUp() throws MalformedURLException {
        sauceUsername = ConfigReader.getCredential("SAUCEDEMO_USERNAME");
        saucePassword = ConfigReader.getCredential("SAUCEDEMO_PASSWORD");

        File appFile = new File(
            System.getProperty("user.dir"),
            ConfigReader.get("saucedemo.android.appPath")
        );

        UiAutomator2Options options = new UiAutomator2Options();
        options.setDeviceName(ConfigReader.get("saucedemo.android.deviceName"));
        options.setPlatformVersion(ConfigReader.get("saucedemo.android.platformVersion"));
        options.setAppPackage(ConfigReader.get("saucedemo.android.appPackage"));
        options.setAppActivity(ConfigReader.get("saucedemo.android.appActivity"));
        options.setApp(appFile.getAbsolutePath());
        options.setNoReset(false);

        driver = new AppiumDriver(new URL(ConfigReader.get("saucedemo.android.appiumUrl")), options);
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @AfterMethod
    public void screenshotOnFailure(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            System.out.println("Test failed: " + result.getName() + " — capturing screenshot");
            Helper.captureScreenshot(driver, result.getName());
        }
    }
}
