package com.robinsinghdevgan.setup;

import java.io.File;

public class ArtifactLocations {

	private final static String dirSeparator = File.separator;
	private final static String projectRootDir = System.getProperty("user.dir");
	private final static String propertiesFileDir = projectRootDir + dirSeparator + "Properties File";
	private final static String webDriverDir = projectRootDir + dirSeparator + "WebDriver";
	private final static String dataSheetFileDir = projectRootDir + dirSeparator + "Data Sheet";
	
	private static String propertiesFilePath = null;
	private static String workBookFilePath = null;
	
	public static void setPropertyFileName(final String fileNameWithExtension) {
		propertiesFilePath = propertiesFileDir + dirSeparator + fileNameWithExtension;		
	}

	public static void setWorkBookFileName(final String fileNameWithExtension) {
		workBookFilePath = dataSheetFileDir + dirSeparator + fileNameWithExtension;
	}
	
	public static String getPropertyFilePath() {
		return propertiesFilePath;
	}

	public static String getWorkBookFilePath(final String fileNameWithExtension) {
		return workBookFilePath;
	}

	public static String getGeckoDriverPath() {
		String filePath = webDriverDir + dirSeparator + "geckodriver";
		if (System.getProperty("os.name").contains("Windows"))
			filePath = filePath + ".exe";
		return filePath;
	}

	public static String getChromeDriverPath() {
		String filePath = webDriverDir + dirSeparator + "chromedriver";
		if (System.getProperty("os.name").contains("Windows"))
			filePath = filePath + ".exe";
		return filePath;
	}

}
