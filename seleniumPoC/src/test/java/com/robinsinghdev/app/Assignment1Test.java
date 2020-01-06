package com.robinsinghdev.app;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class Assignment1Test {
	@Test
	public void f() throws IOException {
		
		WebDriver driver = BrowserSetup.setup();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get("https://rahulshettyacademy.com/AutomationPractice/");
		
		//Task 1: Check the first  Checkbox and verify if it is successfully checked and Uncheck it again to verify if it is successfully Unchecked
		
		WebElement checkBox1 = driver.findElement(By.cssSelector("input[id='checkBoxOption1']"));
		checkBox1.click();
		assertTrue(checkBox1.isSelected());
		
		checkBox1.click();
		assertFalse(checkBox1.isSelected());
		
		//Task 2: How to get the Count of number of check boxes present in the page
		List<WebElement> allCheckBoxes = driver.findElements(By.xpath("input[@type='checkbox']"));
		System.out.println(allCheckBoxes.size());
		
		driver.quit();
	}
}
