package testCase;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pageObjects.LoginPage;

public class TC001_LoginTest extends BaseClass{

	 @Test(priority=1,groups={"sanity","regression"})
	void LoginTest()
	{
		try
		{
			logger.info("**** Starting the TC001_LoginTest ****");
			LoginPage lg=new LoginPage(driver);
			
			lg.setUsername(p.getProperty("username"));
			logger.info("Username entered Successfull");
			
			lg.setPassword(p.getProperty("password"));
			logger.info("Passsowrd text entered Successfull");
			
			lg.clickOnLogin();
			logger.info("Login Successfull");
		
			logger.info("*** Vaildation ***");
			Assert.assertEquals(driver.getTitle(), "OrangeHRM");
			logger.info("Vaildation successfull");
			
		}
		catch(Exception e) {
		    logger.error("Test case failed due to exception: " + e.getMessage());
		    Assert.fail(e.getMessage());
		}

		logger.info("***** Finish of TC001_LoginTest *****");
	}
}