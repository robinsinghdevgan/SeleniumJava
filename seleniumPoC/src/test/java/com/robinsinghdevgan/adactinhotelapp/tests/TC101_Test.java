package com.robinsinghdevgan.adactinhotelapp.tests;

import java.util.Iterator;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.robinsinghdevgan.adactinhotelapp.pageobjects.BookHotel;
import com.robinsinghdevgan.adactinhotelapp.pageobjects.BookedItinerary;
import com.robinsinghdevgan.adactinhotelapp.pageobjects.BookingConfirm;
import com.robinsinghdevgan.adactinhotelapp.pageobjects.LoginPage;
import com.robinsinghdevgan.adactinhotelapp.pageobjects.SearchHotel;
import com.robinsinghdevgan.adactinhotelapp.pageobjects.SelectHotel;

public class TC101_Test {
	private Properties prop = null;
	private WebDriver driver = null;

	@BeforeTest
	private void beforeTest() {
		driver = SetupTest.getDriver();
		prop = SetupTest.getPropertiesObject();
		driver.get(prop.getProperty("baseURL"));
	}

	@Test(dataProvider = "createRandomData")
	public void f(String checkInDate, String checkOutDate, String firstName, String lastName, String address,
			String ccnum, String cvv, String cardExpiryYear) throws InterruptedException {
		LoginPage loginPage = new LoginPage(driver, prop);
		
		SearchHotel searchHotel = loginPage.login();
		//BookedItinerary bookedItinerary = searchHotel.checkBooking();
		
		searchHotel.fillData(checkInDate, checkOutDate);
		
		SelectHotel selectHotel = searchHotel.submitForm();
		BookHotel bookHotel = selectHotel.selectHotelAndContinue();
		
		bookHotel.fillData(firstName, lastName, address, ccnum, cvv, cardExpiryYear);
		BookingConfirm bookingConfirm = bookHotel.book();
		
		BookedItinerary bookedItinerary = bookingConfirm.viewItinerary();
		
		bookedItinerary.printBookedItemDetails();
		bookedItinerary.logOut();
	}

	@DataProvider
	private Iterator<Object[]> createRandomData() {
		return SetupTest.createData();
	}
}
