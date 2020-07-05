package com.robinsinghdevgan.pageobjects.flipkart;

import static com.codeborne.selenide.Selenide.*;

import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.*;

public class ProductPage {
	
	private boolean PINCodeEnteredOnce = false;
	public void enterPINCode(String PIN) {
		if (!PINCodeEnteredOnce && $("#pincodeInputId").exists()) {
			$("#pincodeInputId").clear();
			$("#pincodeInputId").click();
			$("#pincodeInputId").val(PIN).pressEnter();
			PINCodeEnteredOnce = true;
		}
	}
	
	public void addtoCart() {
		$(By.xpath("(//*[contains(text(),'% off')])[1]")).shouldBe(visible);
		$(By.xpath("(//ul[@class='row']//button)[1]")).click();
	}
	
	public void backToSearchResPage(String defaultWindowHandle) {
		closeWindow();
		switchTo().window(defaultWindowHandle);
	}
}
