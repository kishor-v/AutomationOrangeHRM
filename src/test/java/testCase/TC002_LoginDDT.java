package testCase;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import utilites.DataProviders;

public class TC002_LoginDDT extends BaseClass{


	@Test(priority=1, dataProvider="LoginData", dataProviderClass=DataProviders.class,groups= {"master"})
	public void LoginTestDDT(String uname, String upwd, String exp) 
	{
		logger.info("***** Starting of the TC003_LoginDDT *****");
		try 
		{
			
			LoginPage lg=new LoginPage(driver);
			
			Thread.sleep(5000);
			lg.setUsername(uname);
			logger.info("Successfull entered username");
			Thread.sleep(5000);
			lg.setPassword(upwd);
			logger.info("Successfull entered password");
			Thread.sleep(5000);
			lg.clickOnLogin();
			logger.info("Successfull entered Login");
			Thread.sleep(5000);
			
			HomePage hp=new HomePage(driver);
			boolean targetPage=hp.dashboard();
			
			logger.info("**** Vailedation of DDT ****");
			if(exp.equalsIgnoreCase("valid"))
			{
				if(targetPage==true)
				{
					Thread.sleep(5000);
					hp.clickOnDrop();
					Thread.sleep(5000);
					hp.clickOnLogout();
					Assert.assertTrue(true);
				}
				else
				{
					Assert.assertTrue(false);
				}
			}
			
			if(exp.equalsIgnoreCase("invalid"))
			{
				if(targetPage==true)
				{
					Thread.sleep(5000);
					hp.clickOnDrop();
					Thread.sleep(5000);
					hp.clickOnLogout();
					Assert.assertTrue(false);
				}
				else
				{
					Assert.assertTrue(true);
				}
			
		}
		logger.info("**** Vaildation is susccessfull ****");
	}
	catch(Exception e) 
	{
		logger.error(" Test Failed due to Exception: " + e.getMessage());
		Assert.fail();
	}
 }
}
