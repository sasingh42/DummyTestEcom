package com.ecommerce.factory;

import java.util.Properties;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;

public class OptionManager {
    
	private Properties prop;
	public OptionManager(Properties prop)
	{
		this.prop = prop;
	}
	
	public ChromeOptions getChromeOptions()
	{
		ChromeOptions co = new ChromeOptions();
		
		if(Boolean.parseBoolean(prop.getProperty("headless")))
		{
			System.out.println("--Running in headless--");
			co.addArguments("--headless");
		}
		
		if(Boolean.parseBoolean(prop.getProperty("incognito")))
		{
			System.out.println("--Running in incognito--");
			co.addArguments("--incognito");
		}
		return co;
	}
	
	public EdgeOptions getEdgeOptions() {
		EdgeOptions eo = new EdgeOptions();
		if (Boolean.parseBoolean(prop.getProperty("headless"))) {
			System.out.println("---Running in headless mode----");
			eo.addArguments("--headless");
		}
		if (Boolean.parseBoolean(prop.getProperty("incognito"))) {
			System.out.println("---Running in incognito mode----");
			eo.addArguments("--inprivate");
		}
		if(Boolean.parseBoolean(prop.getProperty("remote"))) {
			eo.setCapability("browserName", "edge");
		}

		return eo;
	}
	
}
