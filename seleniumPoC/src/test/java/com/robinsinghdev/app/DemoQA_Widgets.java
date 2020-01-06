package com.robinsinghdev.app;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.fail;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.lang.reflect.Method;
import java.util.Date;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.ITestContext;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.internal.TestNGMethod;


public class DemoQA_Widgets {
	private WebDriver driver;
	private StringBuffer verificationErrors = new StringBuffer();
	private boolean acceptNextAlert = true;

	@BeforeClass(alwaysRun = false)
	public void setUp() throws Exception {
		// System.setProperty("webdriver.gecko.driver", "/home/robin/SELENIUM
		// GECKO/geckodriver");
		// driver = new FirefoxDriver();
		String exePath = "C:\\Users\\robin\\Downloads\\chromedriver_win32\\chromedriver.exe";
		System.setProperty("webdriver.chrome.driver", exePath);
		driver = new ChromeDriver();
		/*
		 * String exePath = "C:\\Users\\rdevgan\\Downloads\\MicrosoftWebDriver.exe";
		 * System.setProperty("webdriver.edge.driver", exePath); driver = new
		 * EdgeDriver();
		 */
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get("https://demoqa.com/");
		// driver.manage().window().setSize(new Dimension(1936, 1066));
	}
	/*
	 * @BeforeMethod public Test beforeMethod(Method method) { Test test =
	 * method.getAnnotation(Test.class); return test; }
	 */

	@Test(priority = 0, enabled = true)
	public void testTooltip_and_DoubleClick() throws Exception {
		driver.findElement(By.linkText("Tooltip and Double click")).click();
		WebElement element = driver.findElement(By.xpath("//*[@id=\"tooltipDemo\"]"));
		Actions toolAct = new Actions(driver);
		toolAct.moveToElement(element).build().perform();
		WebElement toolTipElement = driver.findElement(By.className("tooltiptext"));
		String toolTipText = toolTipElement.getText();
		System.out.println(toolTipText);

		WebElement doubleClickThis = driver.findElement(By.xpath("//*[@id=\"doubleClickBtn\"]"));
		doubleClickThis.click();
		doubleClickThis.click();
		// todo right click
		WebElement rightClickThis = driver.findElement(By.xpath("//*[@id=\"rightClickBtn\"]"));
		Actions oAction = new Actions(driver);
		oAction.moveToElement(rightClickThis);
		oAction.contextClick(rightClickThis).build().perform(); /* this will perform right click */
		/*
		 * Robot robot = new Robot(); robot.keyPress(KeyEvent.VK_DOWN);
		 * robot.keyRelease(KeyEvent.VK_DOWN); robot.keyPress(KeyEvent.VK_ENTER);
		 * robot.keyRelease(KeyEvent.VK_ENTER);
		 */
		List<WebElement> rightContextMenu = driver.findElements(By.className("contextMenuItem"));
		Thread.sleep(1000);
		System.out.println(rightContextMenu.size());
		rightContextMenu.get(2).click();
		Thread.sleep(000);
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		Thread.sleep(2000);
	}

	@Test(priority = 1, enabled = false, testName = "TC02_Tooltip", description = "hey")
	public void testTooltip() throws Throwable {
		String testName, testDesc, docFileWithPath;
		testName = "TC02_Tooltip";
		testDesc = "Validate the Tooltip page";
		docFileWithPath = "C:\\Users\\rdevgan\\Pictures\\"+  new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()) + ".docx";
		
		driver.get("https://demoqa.com/");
		driver.findElement(By.linkText("Tooltip")).click();
		WebElement element = driver.findElement(By.xpath(".//*[@id='age']"));
		Actions toolAct = new Actions(driver);
		toolAct.moveToElement(element).build().perform();
		WebElement toolTipElement = driver.findElement(By.cssSelector(".ui-tooltip"));
		String toolTipText = toolTipElement.getText();
		System.out.println(toolTipText);

		
	}

	@Test(priority = 2, enabled = false)
	public void testTabs() throws Exception {
		driver.findElement(By.linkText("Tabs")).click();
		try {
			assertEquals(driver.findElement(By.id("ui-id-1")).getText(), "Nunc tincidunt");
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
		driver.findElement(
				By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Aenean lacinia'])[1]/following::p[1]"))
				.click();
		try {
			assertEquals(driver.findElement(By.xpath(
					"(.//*[normalize-space(text()) and normalize-space(.)='Aenean lacinia'])[1]/following::p[1]"))
					.getText(),
					"Proin elit arcu, rutrum commodo, vehicula tempus, commodo a, risus. Curabitur nec arcu. Donec sollicitudin mi sit amet mauris. Nam elementum quam ullamcorper ante. Etiam aliquet massa et lorem. Mauris dapibus lacus auctor risus. Aenean tempor ullamcorper leo. Vivamus sed magna quis ligula eleifend adipiscing. Duis orci. Aliquam sodales tortor vitae ipsum. Aliquam nulla. Duis aliquam molestie erat. Ut et mauris vel pede varius sollicitudin. Sed ut dolor nec orci tincidunt interdum. Phasellus ipsum. Nunc tristique tempus lectus.");
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
		driver.findElement(By.id("ui-id-2")).click();
		driver.findElement(By.id("ui-id-3")).click();
	}

	@Test(priority = 3, enabled = false)
	public void testSpinner() throws Exception {
		driver.get("https://demoqa.com/");
		driver.findElement(By.linkText("Spinner")).click();
		driver.findElement(By.id("destroy")).click();
		driver.findElement(By.id("disable")).click();
		driver.findElement(By.id("disable")).click();
		driver.findElement(By.id("destroy")).click();
		driver.findElement(By.id("disable")).click();
		driver.findElement(
				By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Spinner'])[2]/following::div[2]"))
				.click();
		driver.findElement(By.id("setvalue")).click();
		driver.findElement(By.id("destroy")).click();
		driver.findElement(By.id("disable")).click();
		driver.findElement(By.id("disable")).click();
		driver.findElement(By.id("destroy")).click();
		driver.findElement(By.xpath(
				"(.//*[normalize-space(text()) and normalize-space(.)='Select a value:'])[1]/following::span[2]"))
				.click();
		driver.findElement(By.xpath(
				"(.//*[normalize-space(text()) and normalize-space(.)='Select a value:'])[1]/following::span[2]"))
				.click();
		driver.findElement(By.xpath(
				"(.//*[normalize-space(text()) and normalize-space(.)='Select a value:'])[1]/following::span[2]"))
				.click();
		driver.findElement(By.xpath(
				"(.//*[normalize-space(text()) and normalize-space(.)='Select a value:'])[1]/following::span[2]"))
				.click();
		driver.findElement(By.xpath(
				"(.//*[normalize-space(text()) and normalize-space(.)='Select a value:'])[1]/following::span[2]"))
				.click();
		driver.findElement(By.xpath(
				"(.//*[normalize-space(text()) and normalize-space(.)='Select a value:'])[1]/following::span[2]"))
				.click();
		driver.findElement(By.id("getvalue")).click();
		assertEquals(closeAlertAndGetItsText(), "11");
		driver.findElement(By.linkText("Selectmenu")).click();
	}

	@Test(priority = 4, enabled = false)
	public void testSlider() throws Exception {
		driver.findElement(By.linkText("Slider")).click();
		WebElement slider = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[2]/div[2]/div/span"));
		// with keys
		for (int i = 0; i < 10; ++i) {
			slider.sendKeys(Keys.ARROW_RIGHT);
		}
		Thread.sleep(2000);

		Actions moveSlider = new Actions(driver);
		moveSlider.dragAndDropBy(slider, 100, 0).build().perform();
		Thread.sleep(1000);
		moveSlider.moveToElement(slider).clickAndHold().moveByOffset(100, 0).release().perform();
		Thread.sleep(1000);

		// Move to 100%
		Dimension sliderSize = slider.getSize();
		int sliderWidth = sliderSize.getWidth();

		int xCoord = slider.getLocation().getX();

		moveSlider.moveToElement(slider).click().dragAndDropBy(slider, xCoord + sliderWidth, 0).build().perform();

		Thread.sleep(2000);
	}

	@Test(priority = 5, enabled = false)
	public void testSelectMenu() throws Exception {
		driver.findElement(By.linkText("Selectmenu")).click();
		driver.findElement(By.xpath("//*[@id=\"speed-button\"]")).click();
		Thread.sleep(500);
		List<WebElement> speedMenu = driver.findElements(By.className("ui-menu-item"));
		speedMenu.get(2).click();

		WebElement filesButton = driver.findElement(By.xpath("//*[@id=\"files-button\"]"));
		filesButton.click();
		Thread.sleep(500);
		filesButton.sendKeys(Keys.ARROW_DOWN);
		filesButton.sendKeys(Keys.RETURN);

		driver.findElement(By.xpath("//*[@id=\"number-button\"]")).click();
		Thread.sleep(500);
		List<WebElement> numberMenu = driver.findElements(By.className("ui-menu-item"));
		numberMenu.get(numberMenu.size() - 1).click();

		driver.findElement(By.xpath("//*[@id=\"salutation-button\"]")).click();
		Thread.sleep(500);
		driver.findElement(By.xpath("//*[@id=\"ui-id-30\"]")).click();

		Thread.sleep(2000);
	}

	@Test(priority = 6, enabled = false)
	public void testProgressbar() throws Exception {
		driver.findElement(By.linkText("Progressbar")).click();
		WebElement progressBar = driver.findElement(By.id("progressbar"));
		/*
		 * JavascriptExecutor js = (JavascriptExecutor) driver; js.
		 * executeScript("var newprogress=80;$('#progressBar').width(newprogress + '%').attr('aria-valuenow', newprogress);"
		 * , progressBar);
		 */
		System.out.println(progressBar.getAttribute("aria-valuenow"));
		Thread.sleep(2000);
	}

	@Test(priority = 7, enabled = false)
	public void testMenu() throws Exception {
		driver.findElement(By.linkText("Menu")).click();
		List<WebElement> numberMenu = driver.findElements(By.className("ui-menu-item"));
		Actions action = new Actions(driver);
		for (WebElement w : numberMenu) {
			action.moveToElement(w).build().perform();
			Thread.sleep(1000);
		}
	}

	@Test(priority = 8, enabled = false)
	public void testDialog() throws Exception {
		driver.findElement(By.linkText("Dialog")).click();
		WebElement titlebar = driver.findElement(By.xpath("//*[@id=\"ui-id-1\"]"));
		try {
			assertEquals(titlebar.getText(), "Basic dialog");
		} catch (AssertionError e) {
			System.err.println("Title Bar name is not correct");
		}
		Actions a = new Actions(driver);
		a.dragAndDropBy(titlebar, 200, 100).build().perform();
		Thread.sleep(2000);
		WebElement closeDialogButton = driver.findElement(By.xpath("/html/body/div[6]/div[1]/button/span[1]"));
		closeDialogButton.click();
		Thread.sleep(100);
	}

	@Test(priority = 9, enabled = false)
	public void testDatepicker() throws Exception {
		Date date = new Date();
		String DATE_FORMAT = "MM/dd/yyyy";
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
		driver.get("https://demoqa.com/datepicker/");
		WebElement dateField = driver.findElement(By.xpath("//*[@id=\"datepicker\"]"));
		dateField.sendKeys(sdf.format(date));
		dateField.click();
		Thread.sleep(500);
	}

	@Test(priority = 10, enabled = false)
	public void testControlGroup() throws Exception {
		driver.get("https://demoqa.com/controlgroup/");
		driver.findElement(
				By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Rental Car'])[1]/following::span[2]"))
				.click();
		driver.findElement(By.id("ui-id-4")).click();
		driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Insurance'])[1]/span[1]"))
				.click();
		driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Standard'])[1]/span[1]"))
				.click();
		driver.findElement(
				By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Standard'])[1]/following::label[1]"))
				.click();
		driver.findElement(
				By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='# of cars'])[1]/following::span[2]"))
				.click();
		driver.findElement(
				By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='# of cars'])[1]/following::span[2]"))
				.click();
		driver.findElement(
				By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='# of cars'])[1]/following::span[2]"))
				.click();
		driver.findElement(
				By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='# of cars'])[1]/following::span[2]"))
				.click();
		driver.findElement(
				By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='# of cars'])[1]/following::span[2]"))
				.click();
		driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Standard'])[1]/span[2]"))
				.click();
		driver.findElement(
				By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='# of cars'])[1]/following::button[1]"))
				.click();
		driver.findElement(
				By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Rental Car'])[2]/following::span[3]"))
				.click();
		driver.findElement(By.id("ui-id-10")).click();
		driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Automatic'])[2]/span[1]"))
				.click();
		driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Insurance'])[2]/span[1]"))
				.click();
		driver.findElement(
				By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='# of cars'])[2]/following::a[1]"))
				.click();
		driver.findElement(
				By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='# of cars'])[2]/following::a[1]"))
				.click();
		driver.findElement(
				By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='# of cars'])[2]/following::a[1]"))
				.click();
		driver.findElement(
				By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='# of cars'])[2]/following::a[1]"))
				.click();
		driver.findElement(By.id("book")).click();
		Thread.sleep(100);
	}

	@Test(priority = 11, enabled = false)
	public void testCheckboxradio() throws Exception {
		driver.findElement(By.linkText("Widgets")).click();
		driver.findElement(
				By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Controlgroup'])[2]/following::a[1]"))
				.click();
		driver.findElement(By.xpath(
				"(.//*[normalize-space(text()) and normalize-space(.)='Select a Location:'])[1]/following::label[1]"))
				.click();
		driver.findElement(By.xpath(
				"(.//*[normalize-space(text()) and normalize-space(.)='Hotel Ratings:'])[1]/following::label[2]"))
				.click();
		driver.findElement(
				By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Bed Type:'])[1]/following::label[3]"))
				.click();
		driver.findElement(
				By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Bed Type:'])[1]/following::label[1]"))
				.click();
		driver.findElement(By.xpath(
				"(.//*[normalize-space(text()) and normalize-space(.)='Hotel Ratings:'])[1]/following::label[3]"))
				.click();
		driver.findElement(
				By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Paris'])[1]/following::label[1]"))
				.click();
		driver.findElement(By.xpath(
				"(.//*[normalize-space(text()) and normalize-space(.)='Hotel Ratings:'])[1]/following::label[1]"))
				.click();
		driver.findElement(
				By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Bed Type:'])[1]/following::label[4]"))
				.click();
		driver.findElement(
				By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='New York'])[1]/following::label[1]"))
				.click();
		driver.findElement(
				By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Bed Type:'])[1]/following::label[2]"))
				.click();
		driver.findElement(By
				.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Hotel Ratings:'])[1]/following::span[7]"))
				.click();
		driver.findElement(
				By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Bed Type:'])[1]/following::label[1]"))
				.click();
		driver.findElement(By.xpath(
				"(.//*[normalize-space(text()) and normalize-space(.)='Select a Location:'])[1]/following::label[1]"))
				.click();
		driver.findElement(By.xpath(
				"(.//*[normalize-space(text()) and normalize-space(.)='Hotel Ratings:'])[1]/following::label[2]"))
				.click();
		driver.findElement(
				By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Bed Type:'])[1]/following::label[4]"))
				.click();
		driver.findElement(By.xpath(
				"(.//*[normalize-space(text()) and normalize-space(.)='Hotel Ratings:'])[1]/following::label[1]"))
				.click();
		driver.findElement(By
				.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Hotel Ratings:'])[1]/following::span[5]"))
				.click();
		driver.findElement(
				By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Bed Type:'])[1]/following::label[3]"))
				.click();
	}

	@Test(priority = 12, enabled = false)
	public void testButton() throws Exception {
		driver.findElement(By.linkText("Widgets")).click();
		driver.findElement(By.cssSelector("ul:nth-child(1) > li:nth-child(13) > a")).click();
		driver.findElement(By.cssSelector("input:nth-child(3)")).click();
		driver.findElement(By.cssSelector(".ui-button:nth-child(8)")).click();
		driver.findElement(By.cssSelector("button:nth-child(2)")).click();
		driver.findElement(By.cssSelector(".ui-button:nth-child(7)")).click();
		driver.findElement(By.cssSelector(".ui-button:nth-child(9)")).click();
		driver.findElement(By.linkText("An anchor")).click();
		driver.findElement(By.cssSelector("button:nth-child(2)")).click();
		driver.findElement(By.cssSelector("input:nth-child(3)")).click();
		driver.findElement(By.cssSelector(".ui-button:nth-child(9)")).click();
	}

	@Test(priority = 13, enabled = false)
	public void testAutocomplete() throws Exception {
		driver.findElement(By.linkText("Autocomplete")).click();
		driver.findElement(
				By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Accordion'])[1]/following::h1[1]"))
				.click();
		try {
			assertEquals(driver
					.findElement(By.xpath(
							"(.//*[normalize-space(text()) and normalize-space(.)='Accordion'])[1]/following::h1[1]"))
					.getText(), "Autocomplete");
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
		WebElement tagTextField = driver.findElement(By.id("tags"));

		tagTextField.sendKeys("H");
		Thread.sleep(500);
		tagTextField.sendKeys(Keys.ARROW_DOWN);

		List<WebElement> autoCompleteMenu = driver.findElements(By.className("ui-menu-item"));

		try {
			assertEquals(autoCompleteMenu.get(1).getText(), "PHP");
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}

		// autoCompleteMenu.get(0).click();
		tagTextField.clear();
		tagTextField.sendKeys("A");
		Thread.sleep(500);
		tagTextField.sendKeys(Keys.ARROW_DOWN);
		autoCompleteMenu.clear();
		/*
		 * //ISSUE: once used list to click an element, next time same indexed ele would
		 * get clicked even though index selected was different autoCompleteMenu =
		 * driver.findElements(By.className("ui-menu-item"));
		 */
		List<WebElement> list2 = driver.findElements(By.className("ui-menu-item"));
		try {
			assertEquals(list2.get(1).getText(), "AppleScript");
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
		int i = 0;
		for (WebElement w : list2)
			System.out.println((i++) + " " + w.getText());
		// autoCompleteMenu.
		list2.get(9).click();
		Thread.sleep(400);
		System.out.println(tagTextField.getAttribute("value"));
		// assertEquals(tagTextField.getAttribute("value"), "Asp");

		tagTextField.clear();
		tagTextField.sendKeys("B");
		Thread.sleep(500);
		tagTextField.sendKeys(Keys.ARROW_DOWN);
		tagTextField.sendKeys(Keys.RETURN);
		assertEquals(tagTextField.getAttribute("value"), "BASIC");
	}

	@Test(priority = 14, enabled = false)
	public void testAccordian() throws Exception {
		driver.get("https://demoqa.com/");
		driver.findElement(By.linkText("Accordion")).click();
		try {
			assertEquals(driver.findElement(By.id("ui-id-1")).getText(), "Section 1");
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
		driver.findElement(
				By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Section 1'])[1]/following::p[1]"))
				.click();
		Thread.sleep(100);
		try {
			assertEquals(
					driver.findElement(By.xpath(
							"(.//*[normalize-space(text()) and normalize-space(.)='Section 1'])[1]/following::p[1]"))
							.getText(),
					"Mauris mauris ante, blandit et, ultrices a, suscipit eget, quam. Integer ut neque. Vivamus nisi metus, molestie vel, gravida in, condimentum sit amet, nunc. Nam a nibh. Donec suscipit eros. Nam mi. Proin viverra leo ut odio. Curabitur malesuada. Vestibulum a velit eu ante scelerisque vulputate.");
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
		driver.findElement(By.id("ui-id-3")).click();
		driver.findElement(
				By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Section 2'])[1]/following::p[1]"))
				.click();
		Thread.sleep(100);
		try {
			assertEquals(
					driver.findElement(By.xpath(
							"(.//*[normalize-space(text()) and normalize-space(.)='Section 2'])[1]/following::p[1]"))
							.getText(),
					"Sed non urna. Donec et ante. Phasellus eu ligula. Vestibulum sit amet purus. Vivamus hendrerit, dolor at aliquet laoreet, mauris turpis porttitor  velit, faucibus interdum tellus libero ac justo. Vivamus non quam. In suscipit faucibus urna.");
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
		driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Section 3'])[1]/span[1]"))
				.click();
		driver.findElement(By.id("ui-id-6")).click();
		Thread.sleep(100);
		try {
			assertEquals(
					driver.findElement(By.xpath(
							"(.//*[normalize-space(text()) and normalize-space(.)='Section 3'])[1]/following::p[1]"))
							.getText(),
					"Nam enim risus, molestie et, porta ac, aliquam ac, risus. Quisque lobortis. Phasellus pellentesque purus in massa. Aenean in pede. Phasellus ac libero ac tellus pellentesque semper. Sed ac felis. Sed commodo, magna quis lacinia ornare, quam ante aliquam nisi, eu iaculis leo purus venenatis dui.");
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
		driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Section 4'])[1]/span[1]"))
				.click();
		Thread.sleep(100);
		try {
			assertEquals(driver.findElement(By.id("ui-id-7")).getText(), "Section 4");
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
	}

	@AfterClass(alwaysRun = false)
	public void tearDown() throws Exception {
		driver.quit();
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			fail(verificationErrorString);
		}
	}

	private String closeAlertAndGetItsText() {
		try {
			Alert alert = driver.switchTo().alert();
			String alertText = alert.getText();
			if (acceptNextAlert) {
				alert.accept();
			} else {
				alert.dismiss();
			}
			return alertText;
		} finally {
			acceptNextAlert = true;
		}
	}
}
