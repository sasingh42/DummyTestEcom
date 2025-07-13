package com.ecommerce.tests;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import static com.ecommerce.constants.AppConstants.*;

import java.util.List;

import com.ecommerce.base.BaseTest;
import com.ecommerce.utils.ExcelUtil;

public class HomePageTest extends BaseTest {
	
	@BeforeClass
	public void homePageSetup()
	{
	   homepage =  loginpage.doLogin(prop.getProperty("userName"), prop.getProperty("userPwd"));
	 
	}
	
	
	
	
	
	@Test(priority = 1)
	public void homepageVisibleTest()
	{
		String text  = homepage.isHomePageDisplayed();
		Assert.assertEquals(text, HOME_PAGE_TEXT);
	}
	
	
	@DataProvider
	public Object[][] getData() {
		return new Object[][] {
			
			{"IPHONE 13 PRO"},
			//{"ZARA COAT 3"},
			
			{"ADIDAS ORIGINAL"}
			
		};
	}

	@DataProvider
	public Object[][] getDataFromExcel()
	{
		return ExcelUtil.getTestData("Product");
	}
	
	
	
	
	@Test(dataProvider = "getDataFromExcel"  ,priority = 2)
	public void searchProductTest(String productName)
	{
      
    WebElement product =  homepage.searchProduct(productName);
	Assert.assertNotNull(productName, "Not Found"+productName);		
		
	}
	
	@Test(priority = 3)
	public void addProductToCartTest() {
	String prodAdded =homepage.addSearchProductToCart("IPHONE 13 PRO");
	Assert.assertTrue(prodAdded.contains("Product Added To Cart"));
    
	}
	
	@Test(priority = 4)
	public void goToCartSectionTest() {
	homepage.goToCart("ADIDAS ORIGINAL");
	
	
	}
	
 
}
