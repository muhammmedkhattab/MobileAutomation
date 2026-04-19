package tests.ios;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.ios.options.XCUITestOptions;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import utilities.ConfigReader;
import utilities.Helper;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

public class IOSTestBase {

    protected AppiumDriver driver;
    protected String sauceUsername;
    protected String saucePassword;

    @BeforeSuite
    public void setUp() throws MalformedURLException {
        sauceUsername = ConfigReader.getCredential("SAUCEDEMO_USERNAME");
        saucePassword = ConfigReader.getCredential("SAUCEDEMO_PASSWORD");

        File appFile = new File(
            System.getProperty("user.dir"),
            ConfigReader.get("saucedemo.ios.appPath")
        );

        XCUITestOptions options = new XCUITestOptions();
        options.setDeviceName(ConfigReader.get("saucedemo.ios.deviceName"));
        options.setPlatformVersion(ConfigReader.get("saucedemo.ios.platformVersion"));
        options.setBundleId(ConfigReader.get("saucedemo.ios.bundleId"));
        options.setApp(appFile.getAbsolutePath());
        options.setNoReset(true);

        driver = new AppiumDriver(new URL(ConfigReader.get("saucedemo.ios.appiumUrl")), options);
    }

    @AfterSuite
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
