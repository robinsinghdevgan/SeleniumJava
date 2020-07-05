package com.robinsinghdevgan.tests.adactinhotelapp;

import java.io.IOException;
import java.lang.invoke.MethodHandles;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Iterator;
import java.util.Locale;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;
import com.robinsinghdevgan.pageobjects.adactinhotelapp.BookHotel;
import com.robinsinghdevgan.pageobjects.adactinhotelapp.BookedItinerary;
import com.robinsinghdevgan.pageobjects.adactinhotelapp.BookingConfirm;
import com.robinsinghdevgan.pageobjects.adactinhotelapp.LoginPage;
import com.robinsinghdevgan.pageobjects.adactinhotelapp.SearchHotel;
import com.robinsinghdevgan.pageobjects.adactinhotelapp.SelectHotel;
import com.robinsinghdevgan.setup.BaseTestSetup;
import com.robinsinghdevgan.setup.SelectWebBrowser;
import com.robinsinghdevgan.utils.ExtentTestManager;

public class E2ETest extends BaseTestSetup{
	static {
		System.setProperty("currentPackage", MethodHandles.lookup().lookupClass().getPackageName().replaceAll("\\.", "-"));
		System.setProperty("currentClass", MethodHandles.lookup().lookupClass().getSimpleName());
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd_HH-mm");
		System.setProperty("currentDateTimeStamp", sdf.format(cal.getTime()));
		setLogger(MethodHandles.lookup().lookupClass());
	}


	@Test(dataProvider = "createRandomData")
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
	
	@BeforeSuite
	public void beforeSuite() throws IOException {
		setProperties();
		
		setDriver(SelectWebBrowser.setup(getPropertiesObject()));
		
		getDriver().manage().deleteAllCookies();
		getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		getDriver().manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
		getDriver().manage().window().maximize();
	}

	@AfterSuite
	public void afterSuite() throws IOException {
		getDriver().quit();
		setDriver(null);
	}
	

	protected static Iterator<Object[]> createData() {
		ArrayList<String> data = createSearchPageTextFieldsData();
		data.addAll(createBookingPageTextFieldData());
		Object[] o = data.toArray(new String[0]);
		ArrayList<Object[]> dataSet = new ArrayList<Object[]>();
		dataSet.add(o);
		return dataSet.iterator();
	}
	
	private static ArrayList<String> createBookingPageTextFieldData() {
		Faker f = new Faker();
		String firstName = f.name().firstName();
		String lastName = f.name().lastName();
		String address = f.address().fullAddress();
		String ccnum = f.business().creditCardNumber().replaceAll("[\\-]", "");
		Random random = new Random();
		String cvv = Integer.toString(random.nextInt(10)) + random.nextInt(10) + random.nextInt(10);
		String cardExpiryYear = "2022";
		ArrayList<String> data = new ArrayList<String>(Arrays.asList(firstName, lastName, address, ccnum, cvv, cardExpiryYear));
		return data;
	}
	
	private static ArrayList<String> createSearchPageTextFieldsData() {
		LocalDateTime ldt = LocalDateTime.now();
		String todaysDate = DateTimeFormatter.ofPattern("dd/MM/yyyy", Locale.ENGLISH).format(ldt);
		ldt = ldt.plusDays(5);
		String date5DaysAhead = DateTimeFormatter.ofPattern("dd/MM/yyyy", Locale.ENGLISH).format(ldt);
		ArrayList<String> data = new ArrayList<String>(Arrays.asList(todaysDate, date5DaysAhead));
		return data;
	}


}
