package com.ecommerce.constants;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.ecommerce.utils.ElementUtil;
import static com.ecommerce.constants.AppConstants.*;

import java.util.List;  
public class NavigationComponent {
	private WebDriver driver;
	private ElementUtil eleUtil;
	private final By orderSec = By.xpath("//ul//button[contains(text(),'ORDERS')]");
	private final By cartSec = By.xpath("//ul//button[contains(text(),'Cart')]");
	private final By orderHistoryList = By.xpath("//tbody//th");
	private final By homeSec = By.xpath("//button[contains(text(),' HOME ')]");
	
	public NavigationComponent(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}
	
	public void goToCartSection()
	{
		
		eleUtil.doClick(cartSec);
		System.out.println("User Landed to Cart Section");
	}
	
	public void goToOrderHistorySection()
	{
		
		eleUtil.doClick(orderSec);
		System.out.println("User Landed to Order History Page");
		
	}
	
	public List<WebElement> fetchOrderIdHistory()
	{
		 List <WebElement> orderHistory = eleUtil.waitForAllElementsVisible(orderHistoryList, DEFAULT_TIMEOUT);
		 return orderHistory;
	}
	
	public void goToHomeSection()
	{
		
		eleUtil.doClick(homeSec);
		System.out.println("User Landed to Home Section");
	}
	
}
