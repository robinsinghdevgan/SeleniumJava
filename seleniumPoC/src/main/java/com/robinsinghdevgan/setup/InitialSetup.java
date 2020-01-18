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
	
	private final static String projectRootDir = System.getProperty("user.dir");
	private final static String propertiesFileDir = "Properties File";
	private final static String webDriverDir = "WebDriver";
	private final static String dataSheetFileDir = "Data Sheet";
	private final static String dirSeparators = File.separator;
	private static String propFileLocation = projectRootDir + dirSeparators + propertiesFileDir + dirSeparators + "p.properties";
	private static String geckoDriverLocation = projectRootDir + dirSeparators + webDriverDir + dirSeparators + "geckodriver";
	private static String chromeDriverLocation = projectRootDir + dirSeparators + webDriverDir + dirSeparators + "geckodriver";
	public static String dataSheetLocation = projectRootDir + dirSeparators + dataSheetFileDir + dirSeparators + "data.xlsx";
	
	public static WebDriver setup() throws IOException {
		WebDriver driver = null;

		try (InputStream fis = new FileInputStream(propFileLocation)) {
			Properties prop = new Properties();
			prop.load(fis);
			fis.close();
			prop.forEach((key, value) -> System.out.println("Key : " + key + ", Value : " + value));
			if (prop.getProperty("browser").equals("chrome")) {
				System.setProperty("webdriver.chrome.driver", chromeDriverLocation);
				driver = new ChromeDriver();
			}

			else if (prop.getProperty("browser").equals("firefox")) {
				System.setProperty("webdriver.gecko.driver", geckoDriverLocation);
				driver = new FirefoxDriver();
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		return driver;
	}
}
