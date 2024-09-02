package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC01_AccountRegistrationTest extends BaseClass{

	
	@Test(groups = {"Regression","Master"})
	public void verify_account_registration()
	{
		logger.info("********* Starting TC01_AccountRegistrationTest ***********");
		
		try 
		{
		HomePage hp=new HomePage(driver);
		
		hp.clickMyAccount();
		logger.info("We clicked on Myaccount link");
		
		hp.clickRegister();
		logger.info("We clicked on Register link");
				
		AccountRegistrationPage regPage=new AccountRegistrationPage(driver);
		
		//regPage.setFirstName(randomeString().toUpperCase());
		
		//regPage.setFirstName("Ramya");
		//regPage.setLastName("Abburi");
		
		logger.info("Providing customer details");
		regPage.setFirstName(randomeString().toUpperCase());
		regPage.setLastName(randomeString().toUpperCase());	
		//regPage.setEmail("ramyaabburi@gmail.com");
		regPage.setEmail(randomeString()+"@gmail.com");// Randomly generated the email
		//regPage.setTelephone("1234567890");
		regPage.setTelephone(randomNumber());
		String password=randomeAlphaNumeric();
		//regPage.setPassword("xyz123");
		regPage.setPassword(password);
		//regPage.setConfirmPassword("xyz123");
		regPage.setConfirmPassword(password);
		
		regPage.setPrivacyPolicy();
		regPage.clickContinue();
		
		logger.info("Validating expected message..");
		String confmsg=regPage.getConfirmationMsg();
		
		if(confmsg.equals("Your Account Has Been Created!"))
		{
			Assert.assertTrue(true);
		}
		
		else
		{
			logger.error("Test Failed...");
			logger.debug("Debug logs...");
			Assert.assertTrue(false);
		}
		
		//Assert.assertEquals(confmsg, "Your Account Has Been Created!!");
		}
		catch (Exception e) {
			Assert.fail();
		}
		logger.info("********* Finished TC01_AccountRegistrationTest ***********");
	}
	
	
}
