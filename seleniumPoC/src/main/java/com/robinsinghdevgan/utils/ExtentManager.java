package com.robinsinghdevgan.utils;

import java.io.File;

import com.relevantcodes.extentreports.ExtentReports;

//OB: ExtentReports extent instance created here. That instance can be reachable by getReporter() method.
public class ExtentManager {
/*	
	static {
		File srcDir = new File("C:\\Users\\robin\\git\\SeleniumJava\\seleniumPoC\\test-output\\ExtentReports\\");
		File destDir = new File("C:\\Users\\robin\\git\\SeleniumJava\\seleniumPoC\\test-output\\ExtentReports\\Archive\\");
		try {
			FileUtils.moveDirectory(srcDir, destDir);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
*/
	private static ExtentReports extent;

	public synchronized static ExtentReports getReporter() {
		if (extent == null) {
			// Set HTML reporting file location
			final String dirSeparator = File.separator;
			final String testOutputFolder = "test-output";
			//Calendar cal = Calendar.getInstance();
			//SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd_HH-mm");

			//final String currentDateAndTime = sdf.format(cal.getTime());
			final String workingDir = System.getProperty("user.dir");

			String path = workingDir + dirSeparator + testOutputFolder + dirSeparator + "ExtentReports" + dirSeparator + "report.html";

			extent = new ExtentReports(path, false);
		}
		return extent;
	}
}