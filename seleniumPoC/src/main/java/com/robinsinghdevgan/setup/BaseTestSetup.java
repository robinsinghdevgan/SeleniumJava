package com.robinsinghdevgan.setup;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
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


public class BaseTestSetup implements ITestListener{

	private Properties prop = null;
	private String propertiesFileName = null;
	private WebDriver driver = null;
	
	protected Properties getPropertiesObject() {
		return prop;
	}
	
	protected String getPropertiesFileName() {
		return propertiesFileName;
	}
	
	protected WebDriver getDriver() {
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
	
	public void onFinish(ITestContext arg0) {
		// TODO Auto-generated method stub
		//hey i am done
	}

	public void onStart(ITestContext arg0) {
		// TODO Auto-generated method stub
		//
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {
		// TODO Auto-generated method stub
		
	}

	public void onTestFailure(ITestResult result) {
		//screenshot
		getScreenshot(result.getName());
	}

	private void getScreenshot(String result) {
		// TODO Auto-generated method stub
		File src=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(src, new File("C://test//"+result+"screenshot.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void onTestSkipped(ITestResult arg0) {
		// TODO Auto-generated method stub
		
	}

	public void onTestStart(ITestResult arg0) {
		// TODO Auto-generated method stub
		
	}

	public void onTestSuccess(ITestResult arg0) {
		// TODO Auto-generated method stub
		
	}
}
