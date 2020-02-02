package com.robinsinghdevgan.adactinhotelapp.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BookingConfirm {
	@FindBy(css = "#my_itinerary")
	private WebElement viewItinerary;

	private WebDriver driver = null;

	public BookingConfirm(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public BookedItinerary viewItinerary() {
		WebDriverWait wait = new WebDriverWait(driver, 5);
		wait.until(ExpectedConditions.visibilityOfAllElements(viewItinerary));
		viewItinerary.click();
		return new BookedItinerary(driver);
	}
}
