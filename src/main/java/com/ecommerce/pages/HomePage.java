package com.ecommerce.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static com.ecommerce.constants.AppConstants.*;

import java.lang.reflect.Array;
import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

import com.ecommerce.constants.NavigationComponent;
import com.ecommerce.utils.ElementUtil;

public class HomePage {
	private WebDriver driver;
	private ElementUtil eleUtil;
	private NavigationComponent navigatepage;
	private By homePageText = By.xpath("//section/p");
	private By searchFld = By.xpath("(//form//input[@name='search'])[last()]");
	private By loading = By.cssSelector(".ng-animating");
	private final By defaultProduct = By.cssSelector(".container");
	private final By productsResult = By.cssSelector(".container div.ng-star-inserted");
	private final By toastMessage = By.cssSelector("#toast-container");
	//private final By addToCartBtn = By.xpath("//div[@class='card-body']//button[@class='btn w-10 rounded']");

	public HomePage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
		navigatepage = new NavigationComponent(driver);
	}

	public String getHomePageUrl() {
		String url = eleUtil.waitForURLContains(HOME_PAGE_FRACTION_URL, DEFAULT_TIMEOUT);
		System.out.println("Home Page Title : " + url);
		return url;
	}

	public String isHomePageDisplayed() {

		eleUtil.waitForAllElementsVisible(homePageText, DEFAULT_TIMEOUT);
		String text = eleUtil.getElement(homePageText).getText();
		System.out.println("Home Page Text : " + text);
		return text;
	}

	public CheckoutPage goToCart(String productName) {
		String msg = addSearchProductToCart(productName);
	    System.out.println("Cart Toast Message: " + msg);
	    navigatepage.goToCartSection();
	    return new CheckoutPage(driver);
		
	}

	public WebElement searchProduct(String productName) {
		System.out.println(productName);
		eleUtil.waitForElementPresence(defaultProduct, DEFAULT_TIMEOUT);
		eleUtil.waitForElementVisible(searchFld, DEFAULT_TIMEOUT);
		// eleUtil.doSendKeys(searchFld, "ZARA");
		eleUtil.doSendKeys(searchFld, productName, Keys.ENTER);
		try {
			Thread.sleep(5);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String xpath = "//div[@class='card-body']//b[contains(text(),'" + productName + "')]"
				+ "/ancestor::div[@class='card-body']//button[contains(@class,'w-10')]";
		try {
			WebElement productNameElement = eleUtil.waitForElementPresence(By.xpath(xpath), DEFAULT_TIMEOUT);
			System.out.println(productNameElement);
			System.out.println("✅ Product found: " + productName);
			return productNameElement;
		} catch (Exception e) {
			System.out.println("❌ Product not found: " + productName);
			return null;
		}

	}

	public String addSearchProductToCart(String productName) {
		searchProduct(productName);
		String xpath = "//div[@class='card-body']//b[contains(text(),'" + productName + "')]"
				+ "/ancestor::div[@class='card-body']//button[contains(@class,'w-10')]";
		eleUtil.doClick(By.xpath(xpath));
		String productAdded = eleUtil.waitForElementVisible(toastMessage, DEFAULT_TIMEOUT).getText();
		eleUtil.waitForElementInvisible(loading, DEFAULT_TIMEOUT);
	    return productAdded;

	}

}
