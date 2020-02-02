package com.robinsinghdevgan.adactinhotelapp.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.robinsinghdevgan.helper.HelperMethods;

public class SearchHotel {

	private WebDriver driver = null;
	
	//Drop-down menus
	@FindBy(css = "#location")
	private WebElement locations;

	@FindBy(css = "#hotels")
	private WebElement hotels;

	@FindBy(css = "#room_type")
	private WebElement roomTypes;

	@FindBy(css = "#room_nos")
	private WebElement roomNos;

	@FindBy(css = "#adult_room")
	private WebElement adultPerRoom;

	@FindBy(css = "#child_room")
	private WebElement childrenPerRoom;
	
	//Text fields
	@FindBy(css = "#datepick_in")
	private WebElement datePickIn;

	@FindBy(css = "#datepick_out")
	private WebElement datePickOut;
	
	//Buttons
	@FindBy(css = "#Submit")
	private WebElement submit;

	@FindBy(css = "#Reset")
	private WebElement reset;
	
	//links
	@FindBy(xpath = "//a[contains(text(),'Logout')]")
	private WebElement logOut;

	@FindBy(xpath = "//a[contains(text(),'Booked Itinerary')]")
	private WebElement bookedItinerary;

	@FindBy(xpath = "//a[contains(text(),'Change Password')]")
	private WebElement changePassword;

	public SearchHotel(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void fillData(String checkInDate, String checkOutDate) throws InterruptedException {
		// select location
		HelperMethods.selectRandomOption(locations);
		// select hotel
		HelperMethods.selectRandomOption(hotels);
		// select type of room
		HelperMethods.selectRandomOption(roomTypes);
		// select number of rooms
		HelperMethods.selectRandomOption(roomNos);
		
		// enter check-in date
		datePickIn.clear();
		datePickIn.sendKeys(checkInDate);

		// enter check-out date
		datePickOut.clear();
		datePickOut.sendKeys(checkOutDate);

		// select number of adults per room
		HelperMethods.selectRandomOption(adultPerRoom);
		// select number of children per room
		HelperMethods.selectRandomOption(childrenPerRoom);
	}

	public SelectHotel submitForm() {
		submit.click();
		return new SelectHotel(driver);
	}

	public BookedItinerary checkBooking() {
		bookedItinerary.click();
		return new BookedItinerary(driver);
	}
}
