package com.robinsinghdevgan.adactinhotelapp.pageobjects;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	@FindBy(css = "#username")
	private WebElement username;

	@FindBy(css = "#password")
	private WebElement password;

	@FindBy(css = "#login")
	private WebElement login;

	private Properties prop = null;
	private WebDriver driver = null;

	public LoginPage(WebDriver driver, Properties prop) {
		this.prop = prop;
		this.driver = driver;
		// This initElements method will create all WebElements
		PageFactory.initElements(driver, this);
		driver.get(prop.getProperty("baseURL"));
	}

	public SearchHotel login() {
		// new WebDriverWait(driver, timeOutInSeconds, sleepInMillis)
		username.sendKeys(prop.getProperty("username"));
		password.sendKeys(prop.getProperty("password"));
		login.click();
		return new SearchHotel(driver);
	}
}
