package com.robinsinghdevgan.FlipkartTests;

import java.awt.AWTException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.codeborne.selenide.WebDriverRunner;
import com.robinsinghdevgan.FlipkartPageObjects.LandingPage;
import com.robinsinghdevgan.FlipkartPageObjects.ProductPage;
import com.robinsinghdevgan.FlipkartPageObjects.SearchPage;
import com.robinsinghdevgan.setup.ArtifactLocations;
import com.robinsinghdevgan.setup.InitialSetup;
import com.robinsinghdevgan.setup.SpreadsheetReader;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;

public class SearchAddtoCartTest {

	@DataProvider
	public Iterator<Object[]> getTestData() {
		SpreadsheetReader reader = null;
		ArrayList<Object[]> testData = new ArrayList<Object[]>();

		reader = new SpreadsheetReader(ArtifactLocations.getSpreadsheetFilePath("data.xlsx"));

		// for (int rowNum = 2; rowNum <= reader.getRowCount("search"); rowNum++) {
		for (int rowNum = 2; rowNum <= 2; rowNum++) {
			String searchValue = reader.getCellData("search", "Search Item Value", rowNum);
			String sortValue = reader.getCellData("search", "Sort", rowNum);
			String brandValue = reader.getCellData("search", "Brand", rowNum);
			Object ob[] = { searchValue, sortValue, brandValue };
			testData.add(ob);
		}
		return testData.iterator();
	}
	
	LandingPage lp = null;
	WebDriver driver = null;
	
	@BeforeTest
	public void setup() throws IOException {
		driver = InitialSetup.setup();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();

		WebDriverRunner.setWebDriver(driver);
		open("https://flipkart.com/");
		lp = new LandingPage();
	}

	@Test(dataProvider = "getTestData", enabled = true)
	public void f(String searchValues, String sortBy, String brand)
			throws NoSuchElementException, InterruptedException, AWTException {

		// System.out.printf(searchValues, sortBy, Brand);
		System.out.println(searchValues);
		System.out.println(sortBy);
		System.out.println(brand);
		
		SearchPage sp = lp.search(searchValues);
		sp.searchBrand(brand);
		sp.selectSortType(sortBy);
		
		
		ProductPage pp = sp.clickOnFirstRes();
		switchTo().window(1);
		System.out.println(driver.getWindowHandle());
		pp.enterPINCode("110085");
		pp.addtoCart();
		pp.backToSearchResPage();
	}

}
