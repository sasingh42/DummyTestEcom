package com.ecommerce.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

import com.ecommerce.exceptions.BrowserExceptions;

//import company.qa.opencartapp.factory.DriverFactory;
import io.qameta.allure.Step;

public class DriverFactory {

	WebDriver driver;
	Properties prop;
	OptionManager optionsManger;
	public static String highlight;
	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();
	
	public static final Logger log = LogManager.getLogger(DriverFactory.class); // Logger for every class use this line
	/**
	 * This method is used to init driver
	 * 
	 * @param browserName
	 * @return
	 */

	// mvn clean install -Denv="qa"
	@Step("init driver with properties: {0}")
	public WebDriver initDriver(Properties prop) {
		log.info("Properties : "+prop);
		
		
		String browserName = prop.getProperty("browser");
		log.info("Browser Name : "+browserName);
		//System.out.println("Browser Name : " + browserName);
		optionsManger = new OptionManager(prop);
		highlight = prop.getProperty("highlight");
		switch (browserName.toLowerCase().trim()) {
		case "chrome":
			tlDriver.set(new ChromeDriver(optionsManger.getChromeOptions()));
			break;
		case "edge":
			tlDriver.set(new EdgeDriver(optionsManger.getEdgeOptions()));
			break;

		default:
			log.error("Plz Pass Valid Browser");
			System.out.println("---Pass Valid Browser---" + browserName);
			throw new BrowserExceptions("===Invalid Browser Passsed===");

		}
		getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		getDriver().get(prop.getProperty("url"));
		getDriver().manage().window().maximize();
		getDriver().manage().deleteAllCookies();
		return getDriver();
	}
	
	
	/**
	 * getDriver: get the local thready copy of the driver
	 */

	public static WebDriver getDriver() {
		return tlDriver.get();
	}


	public Properties initProp() {
		String envName = System.getProperty("env");
		Properties prop = new Properties();
		FileInputStream ip = null;
		try {
			if (envName == null) {
				log.warn("Env is null, hence running in a QA env.....");
			//	System.out.println("Env is null, hence running in a QA env.....");
				ip = new FileInputStream("./src/test/resource/config/config.properties");
			} else {
				System.out.println("Running Test On Env..."+envName);
				switch (envName.toLowerCase().trim()) {			
				case "qa":
					ip = new FileInputStream("./src/test/resource/config/config.properties");
					break;
				case "stage":
					ip = new FileInputStream("./src/test/resource/config/stage.config.properties");
					break;
				default:
					System.out.println("Please Pass Valid Env Name......."+envName);
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			prop.load(ip);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return prop;
	}
	
	/**
	 * takescreenshot
	 */

	public static File getScreenshotFile() {
		File srcFile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);// temp dir
		return srcFile;
	}

	public static byte[] getScreenshotByte() {
		return ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.BYTES);// temp dir

	}

	public static String getScreenshotBase64() {
		return ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.BASE64);// temp dir

	}

}
