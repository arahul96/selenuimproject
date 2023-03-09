package tests;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pageobjects.AccountSuccessPage;
import pageobjects.LandingPage;
import pageobjects.RegisterAccountPage;
import resources.Base;

public class RegisterTest extends Base {
	
	public WebDriver driver = null;
	
	@Test(priority=1)
	public void registerNewAccountByProvidingAllDetails() throws InterruptedException {
		
		LandingPage landingPage = new LandingPage(driver);
		landingPage.selectMyAccountDropMenu();
		landingPage.clickRegisterOption();
		
		RegisterAccountPage registerAccountPage = new RegisterAccountPage(driver);
		registerAccountPage.enterFirstName(prop.getProperty("firstName"));
		registerAccountPage.enterLastName(prop.getProperty("lastName"));
		registerAccountPage.enterEmailAddress("amotooricap"+timestamp()+"@gmail.com");
		registerAccountPage.enterTelephoneNumber(prop.getProperty("telephone"));
		registerAccountPage.enterPassword(prop.getProperty("validPassword"));
		registerAccountPage.enterPasswordConfirm(prop.getProperty("validPassword"));
		registerAccountPage.selectYesRadioOption();
		registerAccountPage.selectAgreeCheckBoxOption();
		registerAccountPage.clickOnContinueButton();
		
		AccountSuccessPage accountSuccessPage = new AccountSuccessPage(driver);
		
		Assert.assertEquals(accountSuccessPage.retrievePageHeading(),"ABC Your Account Has Been Created!");
		
	}
	
	@Test(priority=2)
	public void registerNewAccountByProvidingMandatoryDetails() throws InterruptedException {
		
		LandingPage landingPage = new LandingPage(driver);
		landingPage.selectMyAccountDropMenu();
		landingPage.clickRegisterOption();
		
		RegisterAccountPage registerAccountPage = new RegisterAccountPage(driver);
		registerAccountPage.enterFirstName(prop.getProperty("firstName"));
		registerAccountPage.enterLastName(prop.getProperty("lastName"));
		registerAccountPage.enterEmailAddress("amotooricap"+timestamp()+"@gmail.com");
		registerAccountPage.enterTelephoneNumber(prop.getProperty("telephone"));
		registerAccountPage.enterPassword(prop.getProperty("validPassword"));
		registerAccountPage.enterPasswordConfirm(prop.getProperty("validPassword"));
		registerAccountPage.selectAgreeCheckBoxOption();
		registerAccountPage.clickOnContinueButton();
		
		AccountSuccessPage accountSuccessPage = new AccountSuccessPage(driver);
		
		Assert.assertEquals(accountSuccessPage.retrievePageHeading(),"Your Account Has Been Created!");
		
	}
	
	@BeforeMethod
	public void setup() throws IOException {
		
		driver = initializeBrowser();
		driver.get(prop.getProperty("url"));
		
	}
	
	@AfterMethod
	public void tearDown() {
		
		if(driver!=null)
			driver.quit();
		
	}

}
