package pageObjects;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage extends BasePage{

	public HomePage(WebDriver driver) throws InterruptedException {
		super(driver);
		Thread.sleep(5000);
	}

	@FindBy(xpath="//h6[normalize-space()='Dashboard']")
	WebElement dash;
	
	@FindBy(xpath="//span[@class='oxd-userdropdown-tab']")
	WebElement userdrop;
	
	@FindBy(xpath="//a[normalize-space()='Logout']")
	WebElement logout;
	
	public boolean dashboard() throws InterruptedException {
		Thread.sleep(3000);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(dash));

		boolean d = dash.isDisplayed();
		return d;
	}
	
	public void clickOnDrop() throws InterruptedException {
		Thread.sleep(3000);
		userdrop.click();
	}
	
	public void clickOnLogout() throws InterruptedException {
		Thread.sleep(3000);
		logout.click();
	}
	
}
