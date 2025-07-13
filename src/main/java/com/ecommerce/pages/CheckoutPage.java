package com.ecommerce.pages;

import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static com.ecommerce.constants.AppConstants.*;
import com.ecommerce.constants.NavigationComponent;
import com.ecommerce.utils.ElementUtil;

public class CheckoutPage {
	private WebDriver driver;
	private ElementUtil eleUtil;
	private NavigationComponent navigatepage;
	private By checkOutBtn = By.xpath("//button[text()='Checkout']");
	//("//button[normalize-space()='Checkout']")
	//private By checkOutBtn =  By.xpath("//button[normalize-space()='Checkout']");
	
	private By selectCountryFld = By.xpath("//input[@placeholder='Select Country']");
	private By placeOrderBtn = By.xpath("//a[text()='Place Order ']");
	private By countryList = By.cssSelector("section.ta-results button");
	private final By toastMessage = By.cssSelector("#toast-container");
	private final By orderID = By.xpath("//tbody/tr[@class='ng-star-inserted']"); 
	public CheckoutPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
		navigatepage = new NavigationComponent(driver);
	}
	
	public String proceedWithCheckout(String countryName)
	{
		eleUtil.waitForElementVisible(checkOutBtn, DEFAULT_TIMEOUT);
		eleUtil.doClick(checkOutBtn);
	//	navigatepage.goToCartSection();
		eleUtil.doSendKeys(selectCountryFld, countryName);
		List <WebElement> listOfCountry = eleUtil.getElements(countryList);
		for(WebElement e : listOfCountry)
		{
			String country  = e.getText();
			if(country.equalsIgnoreCase(countryName))
			{
				e.click();
				break;
			}
		}
		eleUtil.doClick(placeOrderBtn);	
		String message = eleUtil.waitForElementVisible(toastMessage, DEFAULT_TIMEOUT).getText();
		System.out.println("Success Message : "+message);
		return message;
		
	}
	
	
	public String getCheckoutPageUrl()
	{
		String text = eleUtil.waitForURLContains("client/dashboard/cart", DEFAULT_TIMEOUT);
		System.out.println("CheckoutPage Url : "+text);
		return text;
		
	}
	
	public String processOrderID(String country)
	{
		proceedWithCheckout(country);
		String orderDetails = eleUtil.getElement(orderID).getText();
		System.out.println("Order Details : "+orderDetails);
		String[] split = orderDetails.split("\\|");
		System.out.println("Order ID : "+split[1].trim());
		String orderDetail = split[1].trim();
		return orderDetail;
		//split[1].trim();
	}
	
	public boolean goToOrderHistory(String orderId)
	{
		navigatepage.goToOrderHistorySection();

        List <WebElement> previousOrder =  navigatepage.fetchOrderIdHistory();
        System.out.println("===previousOrder==="+previousOrder.toString());
        for(WebElement e : previousOrder)
		{
			String order  = e.getText();
			if(order.equalsIgnoreCase(orderId))
			{
				System.out.println("Order Verified in Records "+order);
				return true;
			}
			
		}
		return false;
	}
	
	public void goToHomePage() {
	    navigatepage.goToHomeSection();
	}
}
