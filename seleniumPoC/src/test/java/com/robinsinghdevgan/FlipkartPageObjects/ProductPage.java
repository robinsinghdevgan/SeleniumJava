package com.robinsinghdevgan.FlipkartPageObjects;

import static com.codeborne.selenide.Selenide.*;

import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.*;

public class ProductPage {
	
	public void enterPINCode(String PIN) {
		$("#pincodeInputId").should(visible);
		$("#pincodeInputId").val(PIN).pressEnter();
	}
	
	public void addtoCart() {
		$(By.xpath("(//*[contains(text(),'% off')])[1]")).shouldBe(visible);
		$(By.xpath("(//ul[@class='row']//button)[1]")).click();
	}
	
	public void backToSearchResPage() {
		//switchTo().window(0);
	}
}
