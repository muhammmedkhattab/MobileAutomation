package tests;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import cucumber.api.testng.AbstractTestNGCucumberTests;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import utilities.Helper;

public class TestBase extends AbstractTestNGCucumberTests
{
	public AndroidDriver driver;
	
	@BeforeSuite
	@Parameters({"browser"})
	public void startDriver(@Optional("Android") String browserName) throws MalformedURLException 
	{
		if (browserName.equalsIgnoreCase("Android")) {
			desiredCapSetUp();
		}
	} 

	@AfterSuite
	public void stopDriver() 
	{
		driver.quit();
	}

	// take screenshot when test case fail and add it in the Screenshot folder
	@AfterMethod
	public void screenshotOnFailure(ITestResult result) 
	{
		if (result.getStatus() == ITestResult.FAILURE)
		{
			System.out.println("Failed!");
			System.out.println("Taking Screenshot....");
			Helper.captureScreenshot(driver, result.getName());
		}
	}
	
	public void desiredCapSetUp() throws MalformedURLException {
		
		File path = new File(System.getProperty("user.dir") + "/Apk/TruDoc.apk");
		// Created object of DesiredCapabilities class.
		DesiredCapabilities capabilities = new DesiredCapabilities();
		// Don't install the app for each time script executed.
		capabilities.setCapability("no-reset", "true");
		capabilities.setCapability("full-reset", "False");
		// Set android deviceName desired capability. Set your device name.
		capabilities.setCapability("deviceName", "0123456789ABCDEF");

		// Set BROWSER_NAME desired capability. It's Android in our case here.
		//capabilities.setCapability(CapabilityType.BROWSER_NAME, "Android");

		// Set android VERSION desired capability. Set your mobile device's OS
		// version.
		capabilities.setCapability(CapabilityType.VERSION, "6.0");

		// Set android platformName desired capability. It's Android in our case
		// here.
		capabilities.setCapability("platformName", "Android");

		// Set android appPackage desired capability.
		capabilities.setCapability("appPackage", "com.youclick.wecare");

		// Set android appActivity desired capability. It is
		capabilities.setCapability("appActivity", "com.trudoc.signup.splash.SplashActivity");

		// Set android appActivity desired capability. It is
		capabilities.setCapability("app", path.getAbsolutePath());
		// Created object of RemoteWebDriver will all set capabilities.
		// Set appium server address and port number in URL string.
		// It will launch calculator app in android device.
		driver = new AndroidDriver(new URL("http://0.0.0.0:4723/wd/hub"), capabilities);
		//appiumDriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		System.out.println("SetUp method");
		
		
	}

}
