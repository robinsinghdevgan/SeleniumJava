package com.robinsinghdevgan.setup;

import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import junit.framework.Assert;

public class Practice {
	private static WebDriver driver = null;
	public static void main(String[] args) throws InterruptedException {
		
		System.setProperty("webdriver.gecko.driver", "C:\\Users\\robin\\Downloads\\geckodriver-v0.26.0-win32\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		assignment2();
		assignment3();
		assignment4();
		assignment5();
		assignment6();
		assignment7();
		assignment8();
		Thread.sleep(1000);
		driver.close();
	}

	private static void assignment8() throws InterruptedException {
		/*
		driver.get("https://www.rahulshettyacademy.com/AutomationPractice/");
		WebElement autoCompleteDropDown = driver.findElement(By.cssSelector("#autocomplete"));
		autoCompleteDropDown.sendKeys("ind");
		Thread.sleep(1000);
		autoCompleteDropDown.sendKeys(Keys.ARROW_DOWN);
		autoCompleteDropDown.sendKeys(Keys.ARROW_DOWN);
		autoCompleteDropDown.sendKeys(Keys.RETURN);
		Thread.sleep(1000);
		System.out.println(autoCompleteDropDown.getAttribute("value"));
		*/
		driver.get("https://www.rahulshettyacademy.com/AutomationPractice/");
		
		driver.findElement(By.id("autocomplete")).click();
        driver.findElement(By.id("autocomplete")).sendKeys("in");
        String interestedTest = "India";
        Thread.sleep(2000);

        driver.findElement(By.id("autocomplete")).sendKeys(Keys.DOWN);
        //Unable to get using myDriver element getText().
        JavascriptExecutor dExecutor = (JavascriptExecutor) driver;
        String script = "return document.getElementById('autocomplete').value";
        String selectedText = (String) dExecutor.executeScript(script);

        int count = 0;
        while (!selectedText.equalsIgnoreCase(interestedTest)) {
            driver.findElement(By.id("autocomplete")).sendKeys(Keys.DOWN);
            selectedText = (String) dExecutor.executeScript(script);
            count++;
            if (count > 50) {
                System.out.println("Couldn't find a match!");
                break;
            }

        }
        driver.findElement(By.id("autocomplete")).sendKeys(Keys.ENTER);

        Thread.sleep(5000);
	}

	private static void assignment7() {
		driver.get("https://www.rahulshettyacademy.com/AutomationPractice/");
		//print row count
		List<WebElement> tableRows = driver.findElements(By.xpath("//table[@id='product']//tr"));
		System.out.println(tableRows.size());
		//print col count
		List<WebElement> tableCols = driver.findElements(By.xpath("//table[@id='product']//tr//th"));
		System.out.println(tableCols.size());
		//print 3rd row, or 2nd data row
		List<WebElement> row = tableRows.get(2).findElements(By.xpath(".//td"));
		for(WebElement td : row) {
			System.out.println(td.getText());
		}
		
	}

	private static void assignment6() {
		driver.get("https://www.rahulshettyacademy.com/AutomationPractice/");
		driver.findElement(By.xpath("//input[@id='checkBoxOption1']")).click();
		String checkBoxText = driver.findElement(By.xpath("//input[@id='checkBoxOption1']//parent::*")).getText();
		WebElement dropDownSelect = driver.findElement(By.xpath("//select[@id='dropdown-class-example']"));
		Select s = new Select(dropDownSelect);
		s.selectByVisibleText(checkBoxText);

		String defaultWindow = driver.getWindowHandle();
		
		driver.findElement(By.xpath("//input[@id='name']")).sendKeys(checkBoxText);
		driver.findElement(By.cssSelector("#alertbtn")).click();
		org.junit.Assert.assertTrue(driver.switchTo().alert().getText().contains(checkBoxText));
		driver.switchTo().alert().accept();
		driver.switchTo().window(defaultWindow);
	}

	private static void assignment5() {
		driver.get("https://the-internet.herokuapp.com/");
		driver.findElement(By.xpath("//a[contains(text(),'Nested Frames')]")).click();
		new WebDriverWait(driver, 5).until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//frame[@name='frame-top']")));
		driver.switchTo().frame(driver.findElement(By.xpath("//frame[@name='frame-middle']")));
		System.out.println(driver.findElement(By.xpath("//div[@id='content']")).getText());
	}

	private static void assignment4() {
		driver.get("https://the-internet.herokuapp.com/");
		driver.findElement(By.xpath("//a[contains(text(),'Multiple Windows')]")).click();
		String mainWindow = driver.getWindowHandle();
		driver.findElement(By.xpath("//a[contains(text(),'Click Here')]")).click();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Set<String> w = driver.getWindowHandles();
		Iterator<String> i = w.iterator();
		String latestPage = "";
		while(i.hasNext())
			latestPage = i.next();
		driver.switchTo().window(latestPage);
		System.out.println(driver.findElement(By.xpath("//h3[contains(text(),'New Window')]")).getText());
		driver.switchTo().window(mainWindow);
		System.out.println(driver.findElement(By.xpath("//h3[contains(text(),'Opening a new window')]")).getText());
	}

	private static void assignment3() {
		driver.get("http://www.itgeared.com/demo/1506-ajax-loading.html");
		driver.findElement(By.xpath("//a[contains(text(),'Click to load get data via Ajax!')]")).click();
		new WebDriverWait(driver, 5).until(
				ExpectedConditions.invisibilityOf(
						driver.findElement(By.xpath("//div[@id='modal']"))));
		System.out.println(driver.findElement(By.xpath("//div[@id='results']")).getText());
	}

	private static void assignment2() {
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
