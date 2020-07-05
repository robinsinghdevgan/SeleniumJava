package com.robinsinghdevgan.pageobjects.flipkart;

import com.codeborne.selenide.Selenide;
import org.openqa.selenium.By;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;

public class LandingPage {
	public LandingPage() {
		if ($(By.cssSelector("button[class='_2AkmmA _29YdH8']")).exists()) {
			$(By.cssSelector("button[class='_2AkmmA _29YdH8']")).click();
			$(By.cssSelector("button[class='_2AkmmA _29YdH8']")).should(disappear);
		}
	}

	public SearchPage search(String searchString) {
		SelenideElement searchBar = $(By.xpath("//input[@placeholder='Search for products, brands and more']"));
		searchBar.click();
		searchBar.val(searchString).pressEnter();
		return Selenide.page(SearchPage.class);
	}
}
