package com.robinsinghdevgan.utils;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import java.util.HashMap;
import java.util.Map;

import static java.lang.Thread.currentThread;

/**
 * OB: extentTestMap holds the information of thread ids and ExtentTest instances.
 * ExtentReports instance created by calling getReporter() method from ExtentManager.
 * At startTest() method, an instance of ExtentTest created and put into extentTestMap with current thread id.
 * At endTest() method, test ends and ExtentTest instance got from extentTestMap via current thread id.
 * At getTest() method, return ExtentTest instance in extentTestMap by using current thread id.
 */
public class ExtentTestManager {
    static final Map<Integer, ExtentTest> extentTestMap = new HashMap<>();
    static final ExtentReports extent = ExtentManager.getReporter();
 
    public static synchronized ExtentTest getTest() {
        return extentTestMap.get((int) currentThread().getId());
    }
 
    public static synchronized void endTest() {
        extent.endTest(extentTestMap.get((int) currentThread().getId()));
    }
 
    public static synchronized ExtentTest startTest(String testName, String desc) {
        ExtentTest test = extent.startTest(testName, desc);
        extentTestMap.put((int) currentThread().getId(), test);
        return test;
    }
}