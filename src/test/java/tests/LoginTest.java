package tests;

import org.testng.annotations.Test;
import pages.LoginPage;

public class LoginTest extends TestBase {
	LoginPage loginPage;

	@Test(priority = 1)
	public void login() throws InterruptedException {
		loginPage = new LoginPage(driver);
		loginPage.login();
	}
}
