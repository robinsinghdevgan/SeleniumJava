package com.robinsinghdevgan.adactinhotelapp;

import java.lang.invoke.MethodHandles;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Iterator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class E2ETest extends TestSetup{
	static {
		System.setProperty("currentPackage", MethodHandles.lookup().lookupClass().getPackageName().replaceAll("\\.", "-"));
		System.setProperty("currentClass", MethodHandles.lookup().lookupClass().getSimpleName());
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd_HH-mm");
		System.setProperty("currentDateTimeStamp", sdf.format(cal.getTime()));
	}
	
	private static Logger logger = LogManager.getLogger(MethodHandles.lookup().lookupClass());
	
	@BeforeTest
	private void beforeTest(){
		logger.trace("Starting test");
		logger.info("Starting test");
		logger.debug("Starting test");
		logger.warn("Starting test");
		logger.error("Starting test");
	}

	@Test(dataProvider = "createRandomData")
	public void f(String checkInDate, String checkOutDate, String firstName, String lastName, String address,
			String ccnum, String cvv, String cardExpiryYear) throws InterruptedException {
		logger.info("Starting test");
		/*LoginPage loginPage = new LoginPage(getDriver(), getPropertiesObject());
		logger.info("Login Page Object created, Landing page opened");
		
		SearchHotel searchHotel = loginPage.login();
		logger.info("Logged in successfully, driver now pointing at search hotel page");
		//BookedItinerary bookedItinerary = searchHotel.checkBooking();
		
		searchHotel.fillData(checkInDate, checkOutDate);
		
		SelectHotel selectHotel = searchHotel.submitForm();
		BookHotel bookHotel = selectHotel.selectHotelAndContinue();
		
		bookHotel.fillData(firstName, lastName, address, ccnum, cvv, cardExpiryYear);
		BookingConfirm bookingConfirm = bookHotel.book();
		
		BookedItinerary bookedItinerary = bookingConfirm.viewItinerary();
		
		bookedItinerary.printBookedItemDetails();
		bookedItinerary.logOut();*/
	}

	@DataProvider
	private Iterator<Object[]> createRandomData() {
		return createData();
	}
}
