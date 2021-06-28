package com.qa.opencart.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * 
 * @author jey
 *
 */

public class DriverFactory {

	public WebDriver driver;
	public Properties prop;
	public static String highlight;
	public OptionsManager optionsManager;

	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();

	/**
	 * This methos will return the driver
	 * 
	 * @param browsername
	 * @return
	 */

	public WebDriver init_driver(Properties prop) {
		String browsername = prop.getProperty("browser").trim();
		System.out.println("browser name is: " + browsername);

		highlight = prop.getProperty("highlight");
		optionsManager = new OptionsManager(prop);

		if (browsername.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			// driver = new ChromeDriver(optionsManager.getChromeOptions());

			if (Boolean.parseBoolean(prop.getProperty("remote"))) {
				init_remoteDriver("chrome");
				System.out.println("running on remote webdriver");
			} else {
				tlDriver.set(new ChromeDriver(optionsManager.getChromeOptions()));
			}

		} else if (browsername.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			// driver = new FirefoxDriver(optionsManager.getFirefoxOptions());
			
			if (Boolean.parseBoolean(prop.getProperty("remote"))) {
				init_remoteDriver("firefox");
				System.out.println("running on remote webdriver");
			} else {
				tlDriver.set(new FirefoxDriver(optionsManager.getFirefoxOptions()));
			}

		} else if (browsername.equalsIgnoreCase("safari")) {
			// driver = new SafariDriver();
			tlDriver.set(new SafariDriver());

		} else {
			System.out.println("plz pass correct browser....... " + browsername);
		}
		getDriver().manage().window().maximize();
		getDriver().manage().deleteAllCookies();
		getDriver().get(prop.getProperty("url").trim());
		return getDriver();
	}

	private void init_remoteDriver(String browserName) {
            if(browserName.equalsIgnoreCase("chrome"))	{
            	DesiredCapabilities cap = DesiredCapabilities.chrome();
            	cap.setCapability(ChromeOptions.CAPABILITY, optionsManager.getChromeOptions());
            	try {
					tlDriver.set(new RemoteWebDriver(new URL(prop.getProperty("huburl")), cap));
				} catch (MalformedURLException e) {
					e.printStackTrace();
				}
            }
            else if(browserName.equalsIgnoreCase("firefox"))	{
            	DesiredCapabilities cap = DesiredCapabilities.firefox();
            	cap.setCapability(FirefoxOptions.FIREFOX_OPTIONS, optionsManager.getFirefoxOptions());
            	try {
					tlDriver.set(new RemoteWebDriver(new URL(prop.getProperty("huburl")), cap));
				} catch (MalformedURLException e) {
					e.printStackTrace();
				}
            }
		
		
	}

	public static synchronized WebDriver getDriver() {
		return tlDriver.get();
	}

	/**
	 * This method is used to initlialize the properties
	 * 
	 * @return
	 */
	public Properties init_prop() {
		prop = new Properties();
		FileInputStream ip = null;

		String env = System.getProperty("env");
		if (env == null) {
			System.out.println("Running on environment: Production ");
			try {
				ip = new FileInputStream("./src/test/resources/config/config.properties");

			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("Running on environment: " + env);
			try {
				switch (env) {
				case "qa":
					ip = new FileInputStream("./src/test/resources/config/qa.config.properties");
					break;
				case "dev":
					ip = new FileInputStream("./src/test/resources/config/dev.config.properties");
					break;
				case "stage":
					ip = new FileInputStream("./src/test/resources/config/stage.config.properties");
					break;
				default:
					break;
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}

		}
		try {
			prop.load(ip);
		} catch (IOException e) {

			e.printStackTrace();
		}

		return prop;
	}

	public String getScreenshot() {

		File srcFile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
		String path = System.getProperty("user.dir") + "/screenshot/" + System.currentTimeMillis() + ".png";
		File destination = new File(path);

		try {
			FileUtils.copyFile(srcFile, destination);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return path;
	}

}

/**
 * 
 * How to install Java JDK Java Development Kit on mac. In Mac OS or later,
 * Apple recommends to set the $JAVA_HOME variable to /usr/libexec/java_home,
 * just export $JAVA_HOME in file
 * 
 * ~/. bash_profile or ~/.profile Find out where
 * 
 * is JDK 14.
 * 
 * $ /usr/libexec/java_home -v14
 * /Library/Java/JavaVirtualMachines/jdk-14.jdk/Contents/Home
 * 
 * $ vim .bash_profile export JAVA_HOME=$(/usr/libexec/java_home)
 * 
 * $ echo $JAVA_HOME /Library/Java/JavaVirtualMachines/jdk-14.jdk/Contents/Home
 * 
 * 
 * 
 */
