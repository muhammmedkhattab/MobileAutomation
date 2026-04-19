package tests;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import utilities.ConfigReader;
import utilities.Helper;

public class TestBase extends AbstractTestNGCucumberTests {

    public AppiumDriver driver;

    @BeforeSuite
    @Parameters({"browser"})
    public void startDriver(@Optional("Android") String browserName) throws MalformedURLException {
        if (browserName.equalsIgnoreCase("Android")) {
            desiredCapSetUp();
        }
    }

    @AfterSuite
    public void stopDriver() {
        if (driver != null) {
            driver.quit();
        }
    }

    @AfterMethod
    public void screenshotOnFailure(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            System.out.println("Failed! Taking screenshot for: " + result.getName());
            Helper.captureScreenshot(driver, result.getName());
        }
    }

    public void desiredCapSetUp() throws MalformedURLException {
        File appFile = new File(
            System.getProperty("user.dir"),
            ConfigReader.get("trudoc.appPath")
        );

        UiAutomator2Options options = new UiAutomator2Options();
        options.setNoReset(true);
        options.setDeviceName(ConfigReader.get("trudoc.deviceName"));
        options.setPlatformVersion(ConfigReader.get("trudoc.platformVersion"));
        options.setAppPackage(ConfigReader.get("trudoc.appPackage"));
        options.setAppActivity(ConfigReader.get("trudoc.appActivity"));
        options.setApp(appFile.getAbsolutePath());

        driver = new AppiumDriver(new URL(ConfigReader.get("trudoc.appiumUrl")), options);
        System.out.println("SetUp complete — driver initialised");
    }
}
