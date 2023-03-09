package tests;

import java.io.IOException;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pageobjects.AccountPage;
import pageobjects.LandingPage;
import pageobjects.LoginPage;
import resources.Base;

public class LoginTest extends Base {
	
	public WebDriver driver = null;
	
	@Test(priority=1)
	public void loginWithValidCredentails() throws IOException {
		
		LandingPage landingPage = new LandingPage(driver);
		landingPage.selectMyAccountDropMenu();
		landingPage.clickLoginOption();
		
		LoginPage loginPage = new LoginPage(driver);
		loginPage.enterIntoEmailAddressField(prop.getProperty("validEmail"));
		loginPage.enterIntoPasswordField(prop.getProperty("validPassword"));
		loginPage.clickOnLoginButton();
		
		AccountPage accountPage = new AccountPage(driver);
		
		Assert.assertTrue(accountPage.availabilityOfeditYourAccountInformation());
		
	}
	
	@Test(priority=2)
	public void loginWithInvalidEmailAddressAndValidPassword() {
		
		LandingPage landingPage = new LandingPage(driver);
		landingPage.selectMyAccountDropMenu();
		landingPage.clickLoginOption();
		
		LoginPage loginPage = new LoginPage(driver);
		loginPage.enterIntoEmailAddressField("amotooricap"+timestamp()+"@gmail.com");
		loginPage.enterIntoPasswordField(prop.getProperty("validPassword"));
		loginPage.clickOnLoginButton();
		
		Assert.assertTrue(loginPage.getMessage().contains("Warning: No match for E-Mail Address and/or Password."));
		
	}
	
	@Test(priority=3)
	public void loginWithValidEmailAddressAndInvalidValidPassword() {
		
		LandingPage landingPage = new LandingPage(driver);
		landingPage.selectMyAccountDropMenu();
		landingPage.clickLoginOption();
		
		LoginPage loginPage = new LoginPage(driver);
		loginPage.enterIntoEmailAddressField((prop.getProperty("validEmail")));
		loginPage.enterIntoPasswordField(prop.getProperty("invalidPassword"));
		loginPage.clickOnLoginButton();
		
		Assert.assertTrue(loginPage.getMessage().contains("Warning: No match for E-Mail Address and/or Password."));
		
	}
	
	@AfterMethod
	public void tearDown() {
		
		if(driver!=null)
			driver.quit();
		
	}
	
	@BeforeMethod
	public void setup() throws IOException {
		
		driver = initializeBrowser();
		driver.get(prop.getProperty("url"));
		
	}

}
