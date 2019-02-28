package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public class PageBase {
	public JavascriptExecutor jse;
	public Select select;
	public Actions action;
	public AndroidDriver driver;

	// create constructor
	public PageBase(AndroidDriver driver){
		PageFactory.initElements(driver, this);
		this.driver=driver;
		
	}

	protected  void clickButton(WebElement element) {
		element.click();
	}

	protected  void setTextElement(WebElement textElement, String value) {
		textElement.sendKeys(value);
	}

	public void scrollToBottom(String i){
		
		jse.executeScript("scrollBy(0,"+i+")");
	}

	public void clearText(WebElement element) {
		element.clear();
	}
	/*
	 * wait for element by passing xPath and driver
	 */
	public void waitForElement(WebElement element) {

		try {
			WebDriverWait wait = new WebDriverWait(driver, 20);
			wait.until(ExpectedConditions.visibilityOf(element));

		} catch (Exception e) {
			System.out.println(e);
		}

	}
	
	protected  void hideKeyboard() {
		driver.hideKeyboard();
	}
}
