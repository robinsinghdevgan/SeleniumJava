package com.robinsinghdevgan.pageobjects.flipkart;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.*;

import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.*;

public class SearchPage {
	
	private void searchResultsReadyWait() {
		$(By.xpath("(//span[contains(text(),'Sort')]/parent::*)//div[contains(text(), 'Low to')]")).should(enabled);
	}
	
	public void selectSortType(String sortBy) {
		searchResultsReadyWait();
		switch (sortBy) {
		case "L2H":
			$(By.xpath("(//span[contains(text(),'Sort')]/parent::*)//div[contains(text(), 'Low to')]")).click();
			break;
		case "H2L":
			$(By.xpath("(//span[contains(text(),'Sort')]/parent::*)//div[contains(text(), 'High to')]")).click();
			break;
		case "REL":
			$(By.xpath("(//span[contains(text(),'Sort')]/parent::*)//div[contains(text(), 'Relev')]")).click();
			break;
		case "POP":
			$(By.xpath("(//span[contains(text(),'Sort')]/parent::*)//div[contains(text(), 'Popular')]")).click();
			break;
		case "NEW":
			$(By.xpath("(//span[contains(text(),'Sort')]/parent::*)//div[contains(text(), 'New')]")).click();
			break;
		default:
			System.out.println("err");
			break;
		}
	}
	
	
	public void searchBrand(String brand) {
		searchResultsReadyWait();
		$(By.xpath("(//div[contains(text(),'Brand')]/parent::*/parent::*)")).scrollIntoView(true);
		//click on more button
		$(By.xpath("(//div[contains(text(),'Brand')]/parent::*/parent::*)//*[contains(text(),'MORE')]")).click();
		SelenideElement brandSearchBar = $(By.xpath("(//div[contains(text(),'Brand')]/parent::*/parent::*)//input[@placeholder='Search Brand']"));
		if (brandSearchBar.exists())
			brandSearchBar.val(brand);
		String firstCheckBox = "//div[contains(text(),'Brand')]/parent::*/following-sibling::*//div[contains(text(),'" + brand + "')]";
		$(By.xpath(firstCheckBox)).shouldBe(visible);
		$(By.xpath(firstCheckBox)).click();	
		//close by clicking cross button
		int cc = 2715;
		char ccc = (char) Integer.parseInt(String.valueOf(cc), 16);
		final String text = String.valueOf(ccc);
		$(By.xpath("//input[@placeholder = 'Search Brand']/parent::*//div[text()='" + text +"']")).click();
	}
	
	public ProductPage clickOnFirstRes() {
		searchResultsReadyWait();
		$(By.xpath("(//*[contains(text(),'% off')])[1]")).click();
		return page(ProductPage.class);
	}
	
	
}
