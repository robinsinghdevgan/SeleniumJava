package com.robinsinghdev.app;

import java.awt.AWTException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.robinsinghdevgan.pageobjects.PageObjects;
import com.robinsinghdevgan.setup.InitialSetup;
import com.robinsinghdevgan.setup.SpreadsheetReader;

public class NewTest {

	private WebDriver driver = null;
	private PageObjects po = null;
	Actions actions = null;

	@DataProvider
	public Iterator<Object[]> getTestData() {
		SpreadsheetReader reader = null;
		ArrayList<Object[]> testData = new ArrayList<Object[]>();

		reader = new SpreadsheetReader(InitialSetup.dataSheetLocation);

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
		driver = InitialSetup.setup();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();

		po = new PageObjects(driver);
		driver.get("https://flipkart.com/");
		actions = new Actions(driver);
		po.weCrossButtonWelcomeForm.click();
	}

	@Test(dataProvider = "getTestData", enabled = true)
	public void f(String searchValues, String sortBy, String Brand)
			throws NoSuchElementException, InterruptedException, AWTException {

		// System.out.printf(searchValues, sortBy, Brand);
		System.out.println(searchValues);
		System.out.println(sortBy);
		System.out.println(Brand);

		po.weWebsiteSearch.click();
		po.weWebsiteSearch.clear();
		po.weWebsiteSearch.sendKeys(searchValues);
		po.weWebsiteSearch.sendKeys(Keys.RETURN);
		new WebDriverWait(driver, 3).until(ExpectedConditions.elementToBeClickable(po.wePriceSectionMaxDropDown));
		// Thread.sleep(1000);

		// Click on middle element of 'to' price range
		/*
		 * po.wePriceSectionMaxDropDown.click(); Thread.sleep(400); List<WebElement>
		 * options = po.wePriceSectionMaxDropDownOptions; int selectThisOption =
		 * options.size() - 3; options.get(selectThisOption).click();
		 */

		// Sort Price
		new WebDriverWait(driver, 5).until(ExpectedConditions.elementToBeClickable(po.weSortH2L));
		switch (sortBy) {
		case "L2H":
			po.weSortL2H.click();
			break;
		case "H2L":
			po.weSortH2L.click();
			break;
		case "REL":
			po.weSortREL.click();
			break;
		case "POP":
			po.weSortPOP.click();
			break;
		case "NEW":
			po.weSortNEW.click();
			break;
		default:
			System.out.println("err");
			break;
		}
		// new WebDriverWait(driver, 3);
		// Select Brand

		new WebDriverWait(driver, 5).until(ExpectedConditions.elementToBeClickable(po.weBrandSection));
		po.weBrandSection.getLocation();
		//actions.moveToElement(po.weBrandSection).build().perform();
		/*
		 * // driver.findElement(By.xpath(xBrand)).click();
		 * 
		 * driver.findElement(By.xpath(brandCheckBox1)).click();
		 */
		// get first element listed
		driver.findElement(By.xpath("(//*[contains(text(),'% off')])[1]")).click();

	}

	@AfterTest
	public void close() {
		driver.quit();
	}

}
