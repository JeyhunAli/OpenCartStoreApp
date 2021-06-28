package com.qa.opencart.factory;

import java.util.Properties;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;

public class OptionsManager {
	
	private Properties prop;
	private ChromeOptions co;
	private FirefoxOptions fo;
	
	
	
	//constructor
	//options manager will read prop file thats why as param im passing properties 
	public OptionsManager (Properties prop) {
		this.prop = prop;
	}

	//method to have to mode of chromeoptions
	public ChromeOptions getChromeOptions() {
		co = new ChromeOptions();
		if(Boolean.parseBoolean(prop.getProperty("headless").trim()))co.addArguments("--headless");
		if(Boolean.parseBoolean(prop.getProperty("incognito").trim()))co.addArguments("--incognito");
        return co;
	}
	//method to have to mode of firefox options
	public FirefoxOptions getFirefoxOptions() {
		fo = new FirefoxOptions();
		if(Boolean.parseBoolean(prop.getProperty("headless").trim()))fo.addArguments("--headless");
		if(Boolean.parseBoolean(prop.getProperty("incognito").trim()))fo.addArguments("--incognito");
        return fo;
	}

	
	
	 
	
}



