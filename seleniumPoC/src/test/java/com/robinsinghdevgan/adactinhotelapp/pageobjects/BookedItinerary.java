package com.robinsinghdevgan.adactinhotelapp.pageobjects;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BookedItinerary {

	private WebDriver driver = null;

	// links
	@FindBy(xpath = "//a[contains(text(),'Logout')]")
	private WebElement logOut;

	@FindBy(xpath = "//tr//tr//tr[2]//td//input")
	private List<WebElement> oldestBookingRow;

	@FindBy(xpath = "(//tr//tr[2]//td[1]//table[1]//tr)")
	private List<WebElement> allBookingTableRows;

	@FindBy(xpath = "//tr//tr[2]//td[1]//table[1]//tbody[1]//tr[1]//td")
	private List<WebElement> tableHeaders;

	public BookedItinerary(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void logOut() {
		logOut.click();
	}

	public void printBookedItemDetails() {

		WebDriverWait wait = new WebDriverWait(driver, 5);
		wait.until(ExpectedConditions.visibilityOfAllElements(tableHeaders));
		Map<String, String> m = new LinkedHashMap<String, String>();
		// List<WebElement> allBookingTableRows =
		// driver.findElements(By.xpath("//tr//tr[2]//td[1]//table[1]//tr"));
		int latestBookingRowNumber = allBookingTableRows.size();
		List<WebElement> latestBookingRowCells = driver
				.findElements(By.xpath("(//tr//tr[2]//td[1]//table[1]//tr)[" + latestBookingRowNumber + "]" + "//td//input"));

		for (int i = 1; i < tableHeaders.size(); ++i) {
			m.putIfAbsent(tableHeaders.get(i).getText(), latestBookingRowCells.get(i).getAttribute("value"));
		}

		m.entrySet().forEach(entry -> {
			System.out.println(entry.getKey() + ":" + entry.getValue());
		});
		/*
		 * System.out.print(tdata.getText() + "	"); } for (WebElement tdata:
		 * tableRow2Cells) { System.out.print(tdata.getAttribute("value") + "	"); }
		 */

	}
}
