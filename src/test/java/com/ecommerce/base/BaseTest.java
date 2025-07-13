package com.ecommerce.base;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
//import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;

import com.aventstack.chaintest.plugins.ChainTestListener;
import com.ecommerce.constants.NavigationComponent;
import com.ecommerce.factory.DriverFactory;
import com.ecommerce.pages.CheckoutPage;
import com.ecommerce.pages.HomePage;
import com.ecommerce.pages.LoginPage;


//@Listeners(ChainTestListener.class)
public class BaseTest {
	WebDriver driver;
	DriverFactory df;
	protected Properties prop;
	protected LoginPage loginpage;
	protected HomePage homepage;
	protected NavigationComponent navigatepage;
	protected CheckoutPage checkoutpage;
	
	
	@BeforeTest
	@Parameters({"browser"})
	public void setup(String browserName)
	{
		df = new DriverFactory();
		prop = df.initProp();
		
		
		//Browser is pass form XML
		if(browserName!=null)
		{
			prop.setProperty("browser", browserName);
		}
		
		
		driver = df.initDriver(prop);
		loginpage = new LoginPage(driver);
	}
	
	
	@AfterMethod //will be running after each @test method
	public void attachScreenshot(ITestResult result) {
		if(!result.isSuccess()) {//only for failure test cases -- true
			ChainTestListener.embed(DriverFactory.getScreenshotFile(), "image/png");
		}
		
		//ChainTestListener.embed(DriverFactory.getScreenshotFile(), "image/png");


	}
	
	
//	@AfterTest
//	public void tearDown()
//	{
//		driver.quit();
//	}
}
