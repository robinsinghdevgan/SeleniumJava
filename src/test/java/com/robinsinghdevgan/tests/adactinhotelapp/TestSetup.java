package com.robinsinghdevgan.tests.adactinhotelapp;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Locale;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.github.javafaker.Faker;
import com.robinsinghdevgan.setup.BaseTestSetup;
import com.robinsinghdevgan.setup.SelectWebBrowser;

public class TestSetup extends BaseTestSetup{
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
