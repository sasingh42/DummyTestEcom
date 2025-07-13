package com.ecommerce.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static com.ecommerce.constants.AppConstants.*;  
import com.ecommerce.base.BaseTest;
import com.ecommerce.constants.AppConstants;

public class CheckoutPageTest extends BaseTest{
	@BeforeClass
	public void homePageSetup()
	{
	   homepage =  loginpage.doLogin(prop.getProperty("userName"), prop.getProperty("userPwd"));
	  
	}
	

	@Test(priority = Short.MAX_VALUE)	
	public void getCheckoutPageUrlTest()
	{
		checkoutpage = homepage.goToCart("IPHONE 13 PRO");
		String url = checkoutpage.getCheckoutPageUrl();
		Assert.assertTrue(url.contains(CHECKOUT_PAGE_URL));
		checkoutpage.goToHomePage();
	}
	
	

	
	
	
	
//	@Test(priority = Short.MAX_VALUE)
//	public void productCheckoutTest()
//	{
//		checkoutpage = homepage.goToCart("IPHONE 13 PRO");
//		String successMsg = checkoutpage.proceedWithCheckout("India");
//		Assert.assertEquals(successMsg, "Order Placed Successfully");
//		checkoutpage.goToHomePage();
//	}
	
	@Test
	public void getOrderIdTest()
	{
		checkoutpage = homepage.goToCart("IPHONE 13 PRO");		
		String orderId = checkoutpage.processOrderID("India");
		Assert.assertEquals(orderId.length(),24);
		checkoutpage.goToHomePage();
	
	}
	
	@Test
	public void orderHistoryTest()
	{
		checkoutpage = homepage.goToCart("IPHONE 13 PRO");	
		String orderId = checkoutpage.processOrderID("India");
		boolean flag = checkoutpage.goToOrderHistory(orderId);
		Assert.assertTrue(flag);
		checkoutpage.goToHomePage();
		
	}
	

	
	
}
