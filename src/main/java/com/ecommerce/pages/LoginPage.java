package com.ecommerce.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static com.ecommerce.constants.AppConstants.*;   //Important

import com.ecommerce.constants.NavigationComponent;
import com.ecommerce.utils.ElementUtil;
public class LoginPage {
	private WebDriver driver;
	private ElementUtil eleUtil;
	private NavigationComponent navigatepage;
//Private By Locator
	private final By email = By.id("userEmail");
	private final By password = By.id("userPassword");
	private final By loginBtn = By.id("login");
	private final By toastMessage = By.xpath("//div[@id='toast-container']//div[@aria-label='Login Successfully']");
	private final By forgetPwdLink = By.linkText("Forgot password?");

//public class Constructor
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
		navigatepage = new NavigationComponent(driver);
	}

//pubic Action:Methods

	

	public String getLoginPageTitle()
	{
		String title = eleUtil.waitForTitleIs(LOGIN_PAGE_TITLE,DEFAULT_TIMEOUT);
		System.out.println("Login Page Title : "+title);
		return title;
		
	}
	
	public String getLoginPageUrl()
	{
		String url = eleUtil.waitForURLContains(LOGIN_PAGE_FRACTION_URL, DEFAULT_TIMEOUT);
		System.out.println("Login Page Url : "+url);
		return url;
	}
	
	public boolean isForgetPwdLinkExist()
	{
		boolean flag=eleUtil.isElementDisplayed(forgetPwdLink);	
		return flag;
	}
	
	public HomePage doLogin(String userId, String userPwd )
	{
		System.out.println("User Credentails : "+userId+":"+userPwd);
		eleUtil.waitForElementVisible(email, DEFAULT_TIMEOUT).sendKeys(userId);
		eleUtil.doSendKeys(password, userPwd);
		eleUtil.doClick(loginBtn);
		eleUtil.waitForElementInvisible(toastMessage, DEFAULT_TIMEOUT);
		//driver.findElement(By.xpath("//div[@class='ng-start-inserted' and @arial-label='Login Successfully']")).getText();
		return new HomePage(driver);
	}
	
	
}
