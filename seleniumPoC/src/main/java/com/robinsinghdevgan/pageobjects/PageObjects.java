package com.robinsinghdevgan.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public final class PageObjects {
	
	WebDriver driver = null;
	public PageObjects(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css = "button[class='_2AkmmA _29YdH8']")
	public WebElement weCrossButtonWelcomeForm;
	
	@FindBy(xpath = "//input[@placeholder='Search for products, brands and more']")
	public WebElement weWebsiteSearch;
	
	@FindBy(xpath = "(//span[contains(text(),'Price')]/ancestor::section//select)[2]")
	public WebElement wePriceSectionMaxDropDown;
	
	@FindBy(xpath = "(//span[contains(text(),'Price')]/ancestor::section//select)[2]//options")
	public List<WebElement> wePriceSectionMaxDropDownOptions;
	
	@FindBy(xpath = "(//span[contains(text(),'Sort')]/parent::*)//div[contains(text(), 'Low to')]")
	public WebElement weSortL2H;
	
	@FindBy(xpath = "(//span[contains(text(),'Sort')]/parent::*)//div[contains(text(), 'High to')]")
	public WebElement weSortH2L;
	
	@FindBy(xpath = "(//span[contains(text(),'Sort')]/parent::*)//div[contains(text(), 'Rele')]")
	public WebElement weSortREL;
	
	@FindBy(xpath = "(//span[contains(text(),'Sort')]/parent::*)//div[contains(text(), 'Popular')]")
	public WebElement weSortPOP;
	
	@FindBy(xpath = "(//span[contains(text(),'Sort')]/parent::*)//div[contains(text(), 'New')]")
	public WebElement weSortNEW;
	
	@FindBy(xpath = "//div[contains(text(),'Brand')]")
	public WebElement weBrandSection;
	
	@FindBy(xpath = "(//div[contains(text(),'Brand')]/parent::*/parent::*)//*[contains(text(),'MORE')]")
	public WebElement weBrandSectionMoreButton;
	
	@FindBy(xpath = "(//div[contains(text(),'Brand')]/parent::*/parent::*)//input[@placeholder='Search Brand']")
	public WebElement weBrandSearchBar;
	
}
