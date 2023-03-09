package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

	WebDriver driver = null;

	public LoginPage(WebDriver driver) {
		
		this.driver = driver;
		PageFactory.initElements(driver,this);
		
	}
	
	@FindBy(id="input-email")
	private WebElement emailAddressTextField;
	
	@FindBy(id="input-password")
	private WebElement passwordField;

	@FindBy(css="input[value='Login']")
	private WebElement loginButton;
	
	@FindBy(css=".alert.alert-danger.alert-dismissible")
	private WebElement errorMessage;
	
	public void enterIntoEmailAddressField(String emailAddress) {
		
		emailAddressTextField.sendKeys(emailAddress);
		
	}
	
	public void enterIntoPasswordField(String password) {
		
		passwordField.sendKeys(password);
		
	}
	
	public void clickOnLoginButton() {
		
		loginButton.click();
		
	}
	
	public String getMessage() {
		
		return errorMessage.getText();
		
	}
	
}
