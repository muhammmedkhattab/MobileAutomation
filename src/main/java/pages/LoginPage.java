package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import io.appium.java_client.AppiumDriver;

public class LoginPage extends PageBase {

    public LoginPage(AppiumDriver driver) {
        super(driver);
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

    public void login(String phone, String password) {
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
            setTextElement(editTxtPhoneNumber, phone);
            hideKeyboard();
            clickButton(btn_Countinue);
            waitForElement(et_password);
            setTextElement(et_password, password);
            hideKeyboard();
            clickButton(btn_login);
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
