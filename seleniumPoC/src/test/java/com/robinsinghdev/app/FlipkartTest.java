package com.robinsinghdev.app;

import java.awt.AWTException;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class FlipkartTest {
	
	private WebDriver driver;
	
	@BeforeTest
	public void setup() throws IOException {
		WebDriver driver = BrowserSetup.setup();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(35, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}
	
	@Test
	public void f() throws NoSuchElementException, InterruptedException, AWTException {
		
		driver.get("https://flipkart.com/");
		
		
		//if no cookies, close the login to flipkart form by clicking the cross button
		WebElement crossButtonLoginWelcomeForm;
		try {
			crossButtonLoginWelcomeForm = driver.findElement(By.cssSelector("button[class='_2AkmmA _29YdH8']"));
			crossButtonLoginWelcomeForm.click();
		}
		catch(NoSuchElementException e) {
			
		}
			

		//else continue if no login form appears

		//WebElement electronicsButton
		driver.findElement(By.xpath("//span[contains(text(),'Electronics')]")).click();
		Thread.sleep(2000);
		//Laptops
		driver.findElement(By.cssSelector("a[title=Laptops]")).click();
		Thread.sleep(5000);
		//Click on middle element of 'to' price range
		WebElement thePriceSectionMaxDropDown = driver.findElement(By.xpath("(//span[contains(text(),'Price')]/ancestor::section//select)[2]"));
		thePriceSectionMaxDropDown.click();
		
		Thread.sleep(200);
		List<WebElement> options = thePriceSectionMaxDropDown.findElements(By.xpath("//option"));
		
		int selectThisOption = options.size() - 3;
		System.out.println(selectThisOption);
		options.get(selectThisOption).click();
		
		Thread.sleep(2000);
		
		Thread.sleep(5000);
		//Sort Price High to Low
		driver.findElement(By.xpath("//div[contains(text(),'Price -- High to Low')]")).click();
		Thread.sleep(5000);
		//get first element listed
		driver.findElement(By.xpath("(//*[contains(text(),'% off')])[1]")).click();
		//(//span[contains(text(),'Sort')]/parent::*)//div[contains(text(), 'Dis')]
		
		Thread.sleep(5000);
		//switch context to new window opened
		Set<String> ids = driver.getWindowHandles();
		Iterator<String> it = ids.iterator();
		
		String parentID = it.next();
		String childID = it.next();
		System.out.println(driver.getTitle());
		driver.switchTo().window(childID);
		System.out.println(driver.getTitle());
		//enter pin code
		driver.findElement(By.xpath("//input[@id='pincodeInputId']")).sendKeys("110006");
		driver.findElement(By.xpath("//input[@id='pincodeInputId']")).sendKeys(Keys.ENTER);

		//add to cart
		
		driver.findElement(By.xpath("(//ul[@class='row']//button)[1]")).click();

		//assert if item is enlisted in the cart*/
		
		Thread.sleep(5000);
	}
	
	@AfterTest
	public void close() {
		driver.quit();
	}
}
