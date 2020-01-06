package com.robinsinghdev.app;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public final class BrowserSetup {
	public static WebDriver setup() throws IOException {
		Properties prop = new Properties();
		String propFileLocation = "C:\\work\\seleniumPoC\\src\\test\\java\\com\\robinsinghdev\\app\\data.properties";
		FileInputStream fis = new FileInputStream(propFileLocation);
		prop.load(fis);
		fis.close();
		
		WebDriver driver = null;
		
		if (prop.getProperty("browser").equals("chrome")) {
			System.setProperty("webdriver.chrome.driver", prop.getProperty("chromeDriverLocation"));
			driver = new ChromeDriver();
		}
		else if (prop.getProperty("browser").equals("firefox")) {
			System.setProperty("webdriver.gecko.driver", prop.getProperty("geckoDriverLocation"));
			driver = new FirefoxDriver();
		}
		
		return driver;
	}
}
