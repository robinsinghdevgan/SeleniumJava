package com.robinsinghdevgan.pageobjects.adactinhotelapp;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	@FindBy(css = "#username")
	private WebElement username;

	@FindBy(css = "#password")
	private WebElement password;

	@FindBy(css = "#login")
	private WebElement login;

	private Properties prop;
	private RemoteWebDriver driver;

	public LoginPage(RemoteWebDriver driver, Properties prop) {
		this.prop = prop;
		this.driver = driver;
		// This initElements method will create all WebElements
		PageFactory.initElements(driver, this);
		String url = prop.getProperty("baseURL");
		driver.get(url);
	}

	public SearchHotel login(String unamePropertyString, String pwdPropertyString) {
		// new WebDriverWait(driver, timeOutInSeconds, sleepInMillis)
		username.sendKeys(prop.getProperty(unamePropertyString));
		password.sendKeys(prop.getProperty(pwdPropertyString));
		login.click();
		return new SearchHotel(driver);
	}
}
