package com.robinsinghdev.app;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public final class BrowserSetup {
	public static WebDriver setup() throws IOException {

		String propFileLocation = "C:\\Users\\robin\\git\\SeleniumJava\\seleniumPoC\\src\\test\\java\\com\\robinsinghdev\\app\\p.properties";

		WebDriver driver = null;

		try (InputStream fis = new FileInputStream(propFileLocation)) {
			Properties prop = new Properties();
			prop.load(fis);
			fis.close();
			prop.forEach((key, value) -> System.out.println("Key : " + key + ", Value : " + value));
			if (prop.getProperty("browser").equals("chrome")) {
				System.setProperty("webdriver.chrome.driver", prop.getProperty("chromeDriverLocation"));
				driver = new ChromeDriver();
			}

			else if (prop.getProperty("browser").equals("firefox")) {
				System.setProperty("webdriver.gecko.driver", prop.getProperty("geckoDriverLocation"));
				driver = new FirefoxDriver();
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		return driver;
	}
}
