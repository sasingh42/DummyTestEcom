package com.ecommerce.tests;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static com.ecommerce.constants.AppConstants.*;

import com.aventstack.chaintest.plugins.ChainTestListener;
import com.ecommerce.base.BaseTest;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
//import com.ecommerce.constants.AppConstants;
import io.qameta.allure.Story;




@Feature("F 50: Ecom - Login Feature")
@Epic("Epic 100: design pages for ecom application")
@Story("US 101: implement login page for ecom application")
public class LoginPageTest extends BaseTest{
 
	
	
	@Description("checking ecom login page title...")
	@Severity(SeverityLevel.MINOR)
	@Owner("Naveen")
	@Test(description = "Checking Login Page Tite ......")
	public void loginPageTitleTest()
	{
		String actTitle = loginpage.getLoginPageTitle();
		ChainTestListener.log("Actual Title :"+actTitle);
		Assert.assertEquals(actTitle, LOGIN_PAGE_TITLE);
	}
	
	@Test(priority = 1)
	public void loginPageUrlTest()
	{
		String actUrl= loginpage.getLoginPageUrl();
		Assert.assertTrue(actUrl.contains(LOGIN_PAGE_FRACTION_URL));
		
	}
	
	@Test
	public void forgetPwdLinkExistTest()
	{
		boolean match = loginpage.isForgetPwdLinkExist();
		Assert.assertTrue(match);
	}
	
	@Description("check user is able to login with valid user credentials...")
	@Severity(SeverityLevel.BLOCKER)
	@Owner("Naveen")
	@Test(priority = Short.MAX_VALUE)
	public void loginTest()
	{
		homepage =  loginpage.doLogin(prop.getProperty("userName"), prop.getProperty("userPwd"));
		String url=homepage.getHomePageUrl();
		Assert.assertTrue(url.contains(HOME_PAGE_FRACTION_URL));
	
	}
	
	
	

}
