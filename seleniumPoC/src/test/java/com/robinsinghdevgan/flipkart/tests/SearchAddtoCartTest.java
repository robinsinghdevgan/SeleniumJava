package com.robinsinghdevgan.flipkart.tests;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.switchTo;

import java.awt.AWTException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.codeborne.selenide.WebDriverRunner;
import com.robinsinghdevgan.flipkart.pageobjects.LandingPage;
import com.robinsinghdevgan.flipkart.pageobjects.ProductPage;
import com.robinsinghdevgan.flipkart.pageobjects.SearchPage;
import com.robinsinghdevgan.setup.ArtifactLocations;
import com.robinsinghdevgan.setup.SelectWebBrowser;
import com.robinsinghdevgan.setup.SpreadsheetReader;

public class SearchAddtoCartTest {
	
	private Properties prop = null;
	private String propertiesFileName = "flipkart.properties";
	private LandingPage lp = null;
	private WebDriver driver = null;
	private String defaultWindowHandle = "";
	protected static Logger log = LogManager.getLogger(SearchAddtoCartTest.class.getName());
	
	
	@BeforeTest
	private void getProperties() {
		log.info("Starting.... flipkart");
		
		ArtifactLocations.setPropertyFileName(propertiesFileName);
		try (InputStream fis = new FileInputStream(ArtifactLocations.getPropertyFilePath())) {
			prop = new Properties();
			prop.load(fis);
			fis.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@DataProvider
	public Iterator<Object[]> getTestData() {
		ArrayList<Object[]> testData = new ArrayList<Object[]>();

		SpreadsheetReader reader = new SpreadsheetReader(ArtifactLocations.getWorkBookFilePath(prop.getProperty("Data_Sheet")));
		
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

	

	@BeforeTest
	public void setup() throws IOException {
		driver = SelectWebBrowser.setup(prop);
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();

		WebDriverRunner.setWebDriver(driver);
		open("https://flipkart.com/");
		lp = new LandingPage();
		defaultWindowHandle = driver.getWindowHandle();
	}

	@Test(dataProvider = "getTestData", enabled = true)
	public void f(String searchValues, String sortBy, String brand)
			throws NoSuchElementException, InterruptedException, AWTException {

		// System.out.printf(searchValues, sortBy, Brand);
		System.out.println(searchValues);
		System.out.println(sortBy);
		System.out.println(brand);

		Thread.sleep(2000);
		SearchPage sp = lp.search(searchValues);
		sp.searchBrand(brand);
		sp.selectSortType(sortBy);

		ProductPage pp = sp.clickOnFirstRes();
		switchTo().window(1);
		// use window handle to switch back to default and close tab
		Thread.sleep(2000);
		pp.enterPINCode("110085");
		pp.addtoCart();
		pp.backToSearchResPage(defaultWindowHandle);
		Thread.sleep(1000);
		// cart value
		// $(By.xpath("(//span[contains(text(),'Sort')]/parent::*)//div[contains(text(),
		// 'Popular')]"));
	}

	@AfterTest
	public void checkCart() {
		$(By.xpath("//span[contains(text(),'Cart')]")).click();
	}
}
