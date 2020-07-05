package com.robinsinghdevgan.pageobjects.adactinhotelapp;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.robinsinghdevgan.helper.HelperMethods;

public class BookHotel {
	@FindBy(css = "#first_name")
	private WebElement firstNameTextField;

	@FindBy(css = "#last_name")
	private WebElement lastNameTextField;

	@FindBy(css = "#address")
	private WebElement addressTextField;

	@FindBy(css = "#cc_num")
	private WebElement ccNum;

	@FindBy(css = "#cc_type")
	private WebElement ccType;

	@FindBy(css = "#cc_exp_month")
	private WebElement ccExpMonth;

	@FindBy(css = "#cc_exp_year")
	private WebElement ccExpYear;

	@FindBy(css = "#cc_cvv")
	private WebElement ccCVV;

	@FindBy(css = "#book_now")
	private WebElement bookNow;

	@FindBy(css = "#cancel")
	private WebElement cancel;

	private WebDriver driver;

	public BookHotel(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void fillData(String firstName, String lastName, String address, String ccnum, String cvv, String cardExpiryYear) {
		firstNameTextField.sendKeys(firstName);
		lastNameTextField.sendKeys(lastName);
		addressTextField.sendKeys(address);
		ccNum.sendKeys(ccnum);
		ccCVV.sendKeys(cvv);
		
		int timeOutInSeconds = 1;
		int sleepInMillis = 90;
		WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds, sleepInMillis);
		ccExpYear.click();
		wait.until(ExpectedConditions.visibilityOf(ccExpYear.findElement(By.xpath("//option[contains (text(),'" + cardExpiryYear + "')]")))).click();
		ccExpYear.findElement(By.xpath("//*[contains(text(),'" + cardExpiryYear +"')]")).click();
		//drop-downs
		HelperMethods.selectRandomOption(ccType);
		HelperMethods.selectRandomOption(ccExpMonth);
	}

	public BookingConfirm book() {
		bookNow.click();
		return new BookingConfirm(driver);
	}
}
