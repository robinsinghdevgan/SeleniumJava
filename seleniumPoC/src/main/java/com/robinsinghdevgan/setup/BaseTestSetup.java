package com.robinsinghdevgan.setup;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.robinsinghdevgan.utils.ExtentTestManager;


public class BaseTestSetup {

	private Properties prop = null;
	private String propertiesFileName = null;
	private WebDriver driver = null;
	private static Logger logger = null;
	
	protected Properties getPropertiesObject() {
		return prop;
	}
	
	protected String getPropertiesFileName() {
		return propertiesFileName;
	}
	
	public WebDriver getDriver() {
		return driver;
	}
	
	
	protected void setProperties(String propertiesFileName) {
		this.propertiesFileName = propertiesFileName;
		ArtifactLocations.setPropertyFileName(propertiesFileName);
		try (InputStream fis = new FileInputStream(ArtifactLocations.getPropertyFilePath())) {
			prop = new Properties();
			prop.load(fis);
			fis.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	protected void setDriver(WebDriver driver) throws IOException {
		this.driver = driver;
	}

	public static Logger getLogger() {
		return logger;
	}

	public static void setLogger(Class<?> class1) {
		BaseTestSetup.logger = LogManager.getLogger(class1);
	}
	
	public void logInfo(String info) {
		getLogger().info(info);
		ExtentTestManager.getTest().log(LogStatus.INFO, screenshotToString()+info);
	}
	
	private String screenshotToString() {
		return ExtentTestManager.getTest().addBase64ScreenShot(getBase64Screenshot());
	}
	
	private String getBase64Screenshot() {
		String base64StringofScreenshot="";
	    File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
	    byte[] fileContent;
		try {
			fileContent = FileUtils.readFileToByteArray(src);
			base64StringofScreenshot = "data:image/png;base64,"+Base64.getEncoder().encodeToString(fileContent);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    return base64StringofScreenshot;
	}

	public void logWarning(String info) {
		getLogger().warn(info);
		ExtentTestManager.getTest().log(LogStatus.WARNING, screenshotToString()+info);
	}
	
	public void logError(String info) {
		getLogger().error(info);
		ExtentTestManager.getTest().log(LogStatus.ERROR, screenshotToString()+info);
	}
}
