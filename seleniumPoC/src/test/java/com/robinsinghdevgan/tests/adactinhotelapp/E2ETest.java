package com.robinsinghdevgan.tests.adactinhotelapp;

import java.lang.invoke.MethodHandles;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Iterator;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.robinsinghdevgan.pageobjects.adactinhotelapp.BookHotel;
import com.robinsinghdevgan.pageobjects.adactinhotelapp.BookedItinerary;
import com.robinsinghdevgan.pageobjects.adactinhotelapp.BookingConfirm;
import com.robinsinghdevgan.pageobjects.adactinhotelapp.LoginPage;
import com.robinsinghdevgan.pageobjects.adactinhotelapp.SearchHotel;
import com.robinsinghdevgan.pageobjects.adactinhotelapp.SelectHotel;
import com.robinsinghdevgan.utils.ExtentTestManager;

public class E2ETest extends TestSetup{
	static {
		System.setProperty("currentPackage", MethodHandles.lookup().lookupClass().getPackageName().replaceAll("\\.", "-"));
		System.setProperty("currentClass", MethodHandles.lookup().lookupClass().getSimpleName());
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd_HH-mm");
		System.setProperty("currentDateTimeStamp", sdf.format(cal.getTime()));
		setLogger(MethodHandles.lookup().lookupClass());
	}
	
	
	@BeforeTest
	private void beforeTest(){
		/**/
	}
	
	@Test(dataProvider = "createRandomData", enabled = true)
	public void searchAndBookHotel(String checkInDate, String checkOutDate, String firstName, String lastName, String address,
			String ccnum, String cvv, String cardExpiryYear) throws InterruptedException {
		
		ExtentTestManager.startTest("searchAndBookHotel","Search, Select and Pay to book a hotel");
		
		logInfo("Starting test");
	
		LoginPage loginPage = new LoginPage(getDriver(), getPropertiesObject());
		logInfo("Login Page Object created, Landing page opened");
		
		SearchHotel searchHotel = loginPage.login();
		logInfo("Logged in successfully, driver now pointing at search hotel page");
		//BookedItinerary bookedItinerary = searchHotel.checkBooking();
		
		searchHotel.fillData(checkInDate, checkOutDate);
		logInfo("Searching and selecting hotel");
		
		SelectHotel selectHotel = searchHotel.submitForm();
		BookHotel bookHotel = selectHotel.selectHotelAndContinue();
		logInfo("Booking Hotel");
		
		bookHotel.fillData(firstName, lastName, address, ccnum, cvv, cardExpiryYear);
		BookingConfirm bookingConfirm = bookHotel.book();
		logInfo("Hotel Booking Confirmed");
		
		BookedItinerary bookedItinerary = bookingConfirm.viewItinerary();
		
		logInfo("Printing booking details");
		bookedItinerary.printBookedItemDetails();
		
		logInfo("Logging out");
		bookedItinerary.logOut();
	}

	@DataProvider
	private Iterator<Object[]> createRandomData() {
		return createData();
	}
}
