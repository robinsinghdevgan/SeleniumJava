package com.robinsinghdevgan.setup;

import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public final class SelectWebBrowser {

	public static WebDriver setup(Properties prop) {
		WebDriver driver = null;
		// prop.forEach((key, value) -> System.out.println("Key : " + key + ", Value : "
		// + value));
		if (prop.getProperty("browser").equals("chrome")) {
			System.setProperty("webdriver.chrome.driver", ArtifactLocations.getChromeDriverPath());
			driver = new ChromeDriver();
		}

		else if (prop.getProperty("browser").equals("firefox")) {
			System.setProperty("webdriver.gecko.driver", ArtifactLocations.getGeckoDriverPath());
			driver = new FirefoxDriver();
		}
		return driver;
	}
}
