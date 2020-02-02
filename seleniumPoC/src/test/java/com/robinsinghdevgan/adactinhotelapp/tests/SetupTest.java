package com.robinsinghdevgan.adactinhotelapp.tests;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Locale;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.github.javafaker.Faker;
import com.robinsinghdevgan.setup.ArtifactLocations;
import com.robinsinghdevgan.setup.SelectWebBrowser;

public class SetupTest {

	protected static Properties prop = null;
	private String propertiesFileName = "adactinHotelApp.properties";
	protected static WebDriver driver = null;
	
	@BeforeSuite
	public void beforeSuite() {
		setProperties();
		createDriver();
	}

	@AfterSuite
	public void afterSuite() {
		//driver.quit();
	}

	private void setProperties() {
		try (InputStream fis = new FileInputStream(ArtifactLocations.getPropertyFilePath(propertiesFileName))) {
			prop = new Properties();
			prop.load(fis);
			fis.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static Properties getPropertiesObject() {
		return prop;
	}

	public void createDriver() {
		try {
			driver = SelectWebBrowser.setup(prop);
		} catch (IOException e) {
			// TODO: User Log4j
			e.printStackTrace();
		}
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	public static WebDriver getDriver() {
		return driver;
	}

	public static Iterator<Object[]> createData() {
		ArrayList<String> data = createSearchPageTextFieldsData();
		data.addAll(createBookingPageTextFieldData());
		Object[] o = data.toArray(new String[data.size()]);
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
		String cvv = Integer.toString(random.nextInt(10)) + Integer.toString(random.nextInt(10)) + Integer.toString(random.nextInt(10));
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
