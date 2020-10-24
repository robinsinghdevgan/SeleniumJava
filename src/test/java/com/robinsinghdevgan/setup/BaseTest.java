package com.robinsinghdevgan.setup;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Base64;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import com.relevantcodes.extentreports.LogStatus;
import com.robinsinghdevgan.utils.ExtentTestManager;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.*;

public class BaseTest {

    //Declare ThreadLocal Driver (ThreadLocalMap) for ThreadSafe Tests
    protected static ThreadLocal<RemoteWebDriver> driver;
    //protected static RemoteWebDriver driver = null;
    public CapabilityFactory capabilityFactory = new CapabilityFactory();

    private Properties prop = null;
    private String propertiesFileName = null;
    //private static Logger logger = null;

    public BaseTest(){
        driver = new ThreadLocal<RemoteWebDriver>();
        if(prop == null)
            setProperties();
    }
    public class ProcessOutputPrinter implements Runnable {

        private Process process;
        private String processIdentifier;

        public ProcessOutputPrinter(Process process, String processIdentifier) {
            this.process = process;
            this.processIdentifier = "[" + processIdentifier + "] -> ";
        }
        public void run() {
            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
                String line = "";
                while ((line = reader.readLine()) != null) {
                    System.out.println(processIdentifier + line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    @BeforeSuite
    public void dockerComposeUp() throws IOException, InterruptedException {
        String projectRoot = System.getProperty("user.dir");
        String dockerCommand = "cd "+ projectRoot + "; docker-compose -f docker-compose.yml up";
        String fullCommand[] = new String[]{"bash", "-c", dockerCommand};
        System.out.println("Executing command: " + fullCommand);
        Process process = Runtime.getRuntime().exec(fullCommand);
        Thread t = new Thread(new ProcessOutputPrinter(process, "dockerComposeUp"));
        t.start();
        Thread.sleep(25000); //sleep for 25 seconds
    }

    @AfterSuite
    public void dockerComposeDown() throws IOException, InterruptedException {
        String projectRoot = System.getProperty("user.dir");
        String dockerCommand = "cd "+ projectRoot + "; docker-compose down";
        String fullCommand[] = new String[]{"bash", "-c", dockerCommand};
        Process process = Runtime.getRuntime().exec(fullCommand);
        Thread t = new Thread(new ProcessOutputPrinter(process, "dockerComposeDown"));
        t.start();
        Thread.sleep(5000); //sleep for 5 seconds
    }

    @BeforeClass
    @Parameters(value={"browser"})
    public void setup (String browser) throws MalformedURLException {
        //Set Browser to ThreadLocalMap
        driver.set((new RemoteWebDriver
                (new URL("http://localhost:4444/wd/hub"),
                        capabilityFactory.getCapabilities(browser))));
        driver.get().manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        try{
            if(driver.get() == null){
                throw new NullPointerException();
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        System.out.println("driver setup complete");
    }

    @AfterClass
    public void quitBrowser(){
        driver.get().quit();
    }

    public static RemoteWebDriver getDriver() {
        //Get driver from ThreadLocalMap
        if(driver.get() == null)
            System.out.println("Driver is null");
        return driver.get();
    }

    //@AfterClass void terminate () {
        //Remove the ThreadLocalMap element
        //driver.remove();
    //}

    protected Properties getPropertiesObject() {
        return prop;
    }

    protected String getPropertiesFileName() {
        return propertiesFileName;
    }

    protected void setProperties() {
        this.propertiesFileName = "adactinHotelApp.properties";
        String propertiesFilePath = ArtifactLocations.createAndGetPropertyFilePath("adactinHotelApp.properties");
        try (InputStream fis = new FileInputStream(propertiesFilePath)) {
            prop = new Properties();
            prop.load(fis);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}