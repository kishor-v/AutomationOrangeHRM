package pageObjects;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage{
	
	public LoginPage(WebDriver driver){
		super(driver);
	}
	
	@FindBy(xpath="//input[@placeholder='Username']")
	WebElement username;
	
	@FindBy(xpath="//input[@placeholder='Password']")
	WebElement password;
	
	@FindBy(xpath="//button[normalize-space()='Login']")
	WebElement login;

	
	public void setUsername(String un) {
		username.clear();
		username.sendKeys(un);
	}
	
	public void setPassword(String pw) {
		password.clear();
		password.sendKeys(pw);
	}
	
	public void clickOnLogin() {
		login.click();
	}

}
