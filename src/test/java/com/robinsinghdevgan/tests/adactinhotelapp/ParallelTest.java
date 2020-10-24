package com.robinsinghdevgan.tests.adactinhotelapp;

import com.github.javafaker.Faker;
import com.robinsinghdevgan.pageobjects.adactinhotelapp.*;
import com.robinsinghdevgan.setup.BaseTest;
import com.robinsinghdevgan.setup.SelectWebBrowser;
import com.robinsinghdevgan.utils.ExtentTestManager;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.IOException;
import java.lang.invoke.MethodHandles;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.TimeUnit;

import static com.robinsinghdevgan.setup.BaseTestSetup.setLogger;

public class ParallelTest extends BaseTest {

    static {
        System.setProperty("currentPackage", MethodHandles.lookup().lookupClass().getPackageName().replaceAll("\\.", "-"));
        System.setProperty("currentClass", MethodHandles.lookup().lookupClass().getSimpleName());
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd_HH-mm");
        System.setProperty("currentDateTimeStamp", sdf.format(cal.getTime()));
        setLogger(MethodHandles.lookup().lookupClass());
    }
    String unamePropertyString, pwdPropertyString;
    @BeforeTest
    @Parameters(value = {"username", "password"})
    public void getAndSetUsernameAndPassword(String username, String password){
        unamePropertyString = username;
        pwdPropertyString = password;
    }

    @Test(dataProvider = "createRandomData")
    public void searchAndBookHotel(String checkInDate, String checkOutDate, String firstName, String lastName, String address,
                                   String ccnum, String cvv, String cardExpiryYear) throws InterruptedException {

        getDriver().get("https://google.com");
        Thread.sleep(10000);
/*
        ExtentTestManager.startTest("searchAndBookHotel","Search, Select and Pay to book a hotel");

        System.out.println("Starting test");

        LoginPage loginPage = new LoginPage(getDriver(), getPropertiesObject());
        System.out.println("Login Page Object created, Landing page opened");

        SearchHotel searchHotel = loginPage.login(unamePropertyString, pwdPropertyString);
        System.out.println("Logged in successfully, driver now pointing at search hotel page");
        //BookedItinerary bookedItinerary = searchHotel.checkBooking();

        searchHotel.fillData(checkInDate, checkOutDate);
        System.out.println("Searching and selecting hotel");

        SelectHotel selectHotel = searchHotel.submitForm();
        BookHotel bookHotel = selectHotel.selectHotelAndContinue();
        System.out.println("Booking Hotel");

        bookHotel.fillData(firstName, lastName, address, ccnum, cvv, cardExpiryYear);
        System.out.println(firstName + ","  + lastName+ ","  +
                address + ","  + ccnum + ","  + cardExpiryYear);
        BookingConfirm bookingConfirm = bookHotel.book();
        System.out.println("Hotel Booking Confirmed");

        BookedItinerary bookedItinerary = bookingConfirm.viewItinerary();

        System.out.println("Printing booking details");
        bookedItinerary.printBookedItemDetails();

        System.out.println("Logging out");
        bookedItinerary.logOut();*/
    }

    @DataProvider
    private Iterator<Object[]> createRandomData() {
        return createData();
    }
    protected static Iterator<Object[]> createData() {
        ArrayList<String> data = createSearchPageTextFieldsData();
        data.addAll(createBookingPageTextFieldData());
        Object[] o = data.toArray(new String[0]);
        ArrayList<Object[]> dataSet = new ArrayList<Object[]>();
        dataSet.add(o);
        return dataSet.iterator();
    }

    private static ArrayList<String> createBookingPageTextFieldData() {
        Faker f = new Faker();
        String firstName = f.name().firstName();
        String lastName = f.name().lastName();
        String address = "Address Test " + DateTimeFormatter.ofPattern("ssmmhhddMMyyyy",
                Locale.ENGLISH).format(LocalDateTime.now());
        //String address = f.address().fullAddress();
        String ccnum = f.business().creditCardNumber().replaceAll("[\\-]", "");
        Random random = new Random();
        String cvv = Integer.toString(random.nextInt(10)) + random.nextInt(10) + random.nextInt(10);
        String cardExpiryYear = "2022";
        ArrayList<String> data = new ArrayList<String>(Arrays.asList(firstName, lastName, address, ccnum, cvv, cardExpiryYear));
        return data;
    }

    private static ArrayList<String> createSearchPageTextFieldsData() {
        LocalDateTime ldt = LocalDateTime.now();
        ldt = ldt.plusDays(2);
        String todaysDate = DateTimeFormatter.ofPattern("dd/MM/yyyy", Locale.ENGLISH).format(ldt);
        ldt = ldt.plusDays(6);
        String date5DaysAhead = DateTimeFormatter.ofPattern("dd/MM/yyyy", Locale.ENGLISH).format(ldt);
        ArrayList<String> data = new ArrayList<String>(Arrays.asList(todaysDate, date5DaysAhead));
        return data;
    }
}
