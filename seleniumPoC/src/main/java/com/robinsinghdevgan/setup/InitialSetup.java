package com.robinsinghdevgan.setup;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public final class InitialSetup {
	
	public static WebDriver setup() throws IOException {
		WebDriver driver = null;
		
		try (InputStream fis = new FileInputStream(ArtifactLocations.getPropertyFilePath("p.properties"))) {
			Properties prop = new Properties();
			prop.load(fis);
			fis.close();
			prop.forEach((key, value) -> System.out.println("Key : " + key + ", Value : " + value));
			if (prop.getProperty("browser").equals("chrome")) {
				System.setProperty("webdriver.chrome.driver", ArtifactLocations.getChromeDriverPath());
				driver = new ChromeDriver();
			}

			else if (prop.getProperty("browser").equals("firefox")) {
				System.setProperty("webdriver.gecko.driver", ArtifactLocations.getGeckoDriverPath());
				driver = new FirefoxDriver();
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		return driver;
	}
}
