package com.robinsinghdevgan.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import com.relevantcodes.extentreports.ExtentReports;

//OB: ExtentReports extent instance created here. That instance can be reachable by getReporter() method.
public class ExtentManager {

	private static ExtentReports extent;

	public synchronized static ExtentReports getReporter() {
      if (extent == null) {
          //Set HTML reporting file location
    	  Calendar cal = Calendar.getInstance();
    	  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd_HH-mm");
    	  
    	  String currentDateAndTime = sdf.format(cal.getTime());
          String workingDir = System.getProperty("user.dir");
          
          String currentPackage = System.getProperty("currentPackage");
          String currentTestClassName = System.getProperty("currentClass");
          
          String windows = workingDir + "\\ExtentReports\\"+currentPackage+"\\"+currentTestClassName+currentDateAndTime+".html";
          String otherOS = workingDir + "/ExtentReports/"+currentPackage+"/"+currentTestClassName+currentDateAndTime+".html";
          if (System.getProperty("os.name").toLowerCase().contains("win")) {
              extent = new ExtentReports(windows, false);
          }
          else {
              extent = new ExtentReports(otherOS, false);
          }
      }
      return extent;
  }
}