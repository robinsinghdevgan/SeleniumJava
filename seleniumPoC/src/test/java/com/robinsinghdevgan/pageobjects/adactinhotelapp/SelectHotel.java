package com.robinsinghdevgan.pageobjects.adactinhotelapp;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SelectHotel {
	@FindBy(css = "#continue")
	private WebElement continueButton;
	
	@FindBy(css = "#cancel")
	private WebElement cancelButton;
	
	@FindBy(xpath = "//input[@type='radio']")
	private WebElement firstRadioButton;

	private WebDriver driver;
	
	public SelectHotel(WebDriver driver) {
		this.driver  = driver;
		PageFactory.initElements(driver, this);
	}
	
	public BookHotel selectHotelAndContinue() {
		WebDriverWait wait = new WebDriverWait(driver, 5);
		wait.until(ExpectedConditions.visibilityOf(firstRadioButton));
		firstRadioButton.click();
		continueButton.click();
		return new BookHotel(driver);
	}
	
}
