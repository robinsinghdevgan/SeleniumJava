package com.robinsinghdevgan.tests.flipkart;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import com.robinsinghdevgan.pageobjects.flipkart.ProductPage;
import com.robinsinghdevgan.pageobjects.flipkart.SearchPage;
import com.robinsinghdevgan.setup.BaseTest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.codeborne.selenide.WebDriverRunner;
import com.robinsinghdevgan.pageobjects.flipkart.LandingPage;
import com.robinsinghdevgan.setup.ArtifactLocations;
import com.robinsinghdevgan.setup.SelectWebBrowser;
import com.robinsinghdevgan.setup.SpreadsheetReader;

import static com.codeborne.selenide.Selenide.*;

public class SearchAddtoCartTest extends BaseTest {

    private Properties prop = null;
    private WebDriver driver = null;
    protected static final Logger log = LogManager.getLogger(SearchAddtoCartTest.class.getName());


    @BeforeTest
    private void getProperties() {
        log.info("Starting.... flipkart");

        String propertiesFileName = "flipkart.properties";
        try (InputStream fis = new FileInputStream(ArtifactLocations.createAndGetPropertyFilePath(propertiesFileName))) {
            prop = new Properties();
            prop.load(fis);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @DataProvider
    public Iterator<Object[]> getTestData() {
        ArrayList<Object[]> testData = new ArrayList<>();
        String workbookFileName = ArtifactLocations.createAndGetWorkBookFilePath(prop.getProperty("Data_Sheet"));
        System.out.println(workbookFileName);
        SpreadsheetReader reader = new SpreadsheetReader(workbookFileName);

        // for (int rowNum = 2; rowNum <= reader.getRowCount("search"); rowNum++) {
        for (int rowNum = 2; rowNum <= 4; rowNum++) {
            String searchValue = reader.getCellData("search", "Search Item Value", rowNum);
            String sortValue = reader.getCellData("search", "Sort", rowNum);
            String brandValue = reader.getCellData("search", "Brand", rowNum);
            System.out.println(searchValue);
            Object[] ob = {searchValue, sortValue, brandValue};
            testData.add(ob);
        }
        return testData.iterator();
    }


    @BeforeTest
    public void setup() {
        WebDriverRunner.setWebDriver(getDriver());
    }

    @Test(dataProvider = "getTestData")
    public void f(String searchValues, String sortBy, String brand)
            throws NoSuchElementException, InterruptedException {

        // System.out.printf(searchValues, sortBy, Brand);
        System.out.println(searchValues);
        System.out.println(sortBy);
        System.out.println(brand);

        open("https://flipkart.com/");
        LandingPage lp = new LandingPage();
        String defaultWindowHandle = driver.getWindowHandle();
        Thread.sleep(2000);
        SearchPage sp = lp.search(searchValues);
        sp.searchBrand(brand);
        sp.selectSortType(sortBy);

        ProductPage pp = sp.clickOnFirstRes();
        switchTo().window(1);
        // use window handle to switch back to default and close tab
        Thread.sleep(2000);
        pp.enterPINCode("110085");
        pp.addtoCart();
        pp.backToSearchResPage(defaultWindowHandle);
        Thread.sleep(1000);
        // cart value
        // $(By.xpath("(//span[contains(text(),'Sort')]/parent::*)//div[contains(text(),
        // 'Popular')]"));
        $(By.xpath("//span[contains(text(),'Cart')]")).click();

    }

}
