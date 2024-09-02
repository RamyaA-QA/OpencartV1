package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;

/*
 * Data is valid - login success - test pass - logout
 * Data is valid - login failed - test fail
 * 
 * Data is invalid - login success - test fail - logout
 * Data is invalid - login failed - test pass
 */

public class TC03_LoginDDT extends BaseClass{
	
	@Test(dataProvider = "LoginData",dataProviderClass = DataProviders.class,groups = "Datadriven")// getting data provider from different class
	public void verify_loginDDT(String email, String pwd, String exp)
	{
	
	logger.info("***** Starting TC03_LoginDDT *****");
	try
	{
	// HomePage
	HomePage hp=new HomePage(driver);
	hp.clickMyAccount();
	hp.clickLogin();
	
	//Login
	LoginPage lp=new LoginPage(driver);
	//lp.setEmail(p.getProperty("email"));
	//lp.setPassword(p.getProperty("password"));
	lp.setEmail(email);
	lp.setPassword(pwd);
	lp.clickLogin();
	
	//MyAccount
	MyAccountPage macc=new MyAccountPage(driver);
	boolean targetPage=macc.isMyAccountPageExists();
	
	/*
	 * Data is valid -  login success - test pass - logout
	 * 					login failed - test fail
	 * 
	 * Data is invalid - login success - test fail - logout
	 * 					 login failed - test pass
	 */
	
	if(exp.equalsIgnoreCase("Valid"))
	{
		if(targetPage==true)
		{
			Assert.assertTrue(true);
			macc.clickLogout();
		}
		else
		{
			Assert.assertTrue(false);
		}
	}
	
	if(exp.equalsIgnoreCase("Invalid"))
	{
		if(targetPage==true)
		{
			macc.clickLogout();
			Assert.assertTrue(false);
		}
		else
		{
			Assert.assertTrue(true);
		}
	}
	}
	catch (Exception e) {
		Assert.fail();
	}
	logger.info("***** Finished TC03_LoginDDT *****");
	}	
}
