package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public class LoginPage extends PageBase {

	AndroidDriver driver;

	public LoginPage(AndroidDriver driver) {
		super(driver);
		this.driver = driver;
	}

	@FindBy(xpath = "//android.widget.Button[@resource-id='com.android.packageinstaller:id/permission_allow_button']")
	WebElement btnAllow;

	@FindBy(xpath = "//android.widget.Button[@resource-id='com.android.packageinstaller:id/permission_allow_button']")
	WebElement btnallowLocation;
	             
	@FindBy(xpath = "//android.widget.Button[@resource-id='com.youclick.wecare:id/btn_english']")
	WebElement btnLang_English;

	@FindBy(xpath = "//*[@resource-id='com.youclick.wecare:id/img_flag']")
	WebElement btnCountry;

	@FindBy(xpath = "//*[@resource-id='com.youclick.wecare:id/et_country_search']")
	WebElement editTxtCountry;

	@FindBy(xpath = "//*[@resource-id='com.youclick.wecare:id/tv_country']")
	WebElement tvCountry;

	@FindBy(xpath = "//*[@resource-id='com.youclick.wecare:id/et_phone_number']")
	WebElement editTxtPhoneNumber;

	@FindBy(xpath = "//*[@resource-id='com.youclick.wecare:id/btn_continue']")
	WebElement btn_Countinue;

	@FindBy(xpath = "//*[@resource-id='com.youclick.wecare:id/et_password']")
	WebElement et_password;

	@FindBy(xpath = "//*[@resource-id='com.youclick.wecare:id/btn_login']")
	WebElement btn_login;

	public void login() {
		try {
			clickButton(btnAllow);
			clickButton(btnallowLocation);
			
			waitForElement(btnLang_English);
			clickButton(btnLang_English);
			waitForElement(btnCountry);
			clickButton(btnCountry);
			waitForElement(editTxtCountry);
			setTextElement(editTxtCountry, "egypt");
			waitForElement(tvCountry);
			clickButton(tvCountry);
			waitForElement(editTxtPhoneNumber);
			setTextElement(editTxtPhoneNumber, "01006147962");
			hideKeyboard();
			clickButton(btn_Countinue);
			waitForElement(et_password);
			setTextElement(et_password, "mmgh0123");
			hideKeyboard();
			clickButton(btn_login);
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
