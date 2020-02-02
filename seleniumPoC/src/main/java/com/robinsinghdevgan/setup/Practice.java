package com.robinsinghdevgan.setup;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Practice {
	public static void main(String[] args) {
		
		System.setProperty("webdriver.gecko.driver", "C:\\Users\\robin\\Downloads\\geckodriver-v0.26.0-win32\\geckodriver.exe");
		WebDriver driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("https://www.cleartrip.com/");
		driver.findElement(By.cssSelector("#DepartDate")).click();
		new WebDriverWait(driver, 3).until(
				ExpectedConditions.visibilityOf(
						driver.findElement(By.xpath("//div[@id='ui-datepicker-div']"))));
		driver.findElement(By.xpath("//a[contains(@class,'ui-state-default ui-state-highlight ui-state-active')]")).click();
		
		Select s = new Select(driver.findElement(By.xpath("//select[@id='Adults']")));
		s.selectByIndex(2);
		
		s = new Select(driver.findElement(By.xpath("//select[@id='Childrens']")));
		s.selectByIndex(2);
		
		
		s = new Select(driver.findElement(By.xpath("//select[@id='Infants']")));
		s.selectByIndex(2);
		
		driver.findElement(By.cssSelector("#SearchBtn")).click();
		
		new WebDriverWait(driver, 3).until(
				ExpectedConditions.visibilityOf(
						driver.findElement(By.xpath("//div[@id='homeErrorMessage']"))));
		
		System.out.println(driver.findElement(By.xpath("//div[@id='homeErrorMessage']")).getText());
		
	}
}
