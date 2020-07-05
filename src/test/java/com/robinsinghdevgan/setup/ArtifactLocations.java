package com.robinsinghdevgan.setup;

import java.io.File;

public class ArtifactLocations {

    private final static String dirSeparator = File.separator;
    private final static String projectRootDir = System.getProperty("user.dir");
    private final static String propertiesFileDir = projectRootDir + dirSeparator + "Properties File";
    private final static String webDriverDir = projectRootDir + dirSeparator + "WebDriver";
    private final static String dataSheetFileDir = projectRootDir + dirSeparator + "Data Sheet";

    public static String createAndGetPropertyFilePath(final String fileNameWithExtension) {
        return propertiesFileDir + dirSeparator + fileNameWithExtension;
    }

    public static String createAndGetWorkBookFilePath(final String fileNameWithExtension) {
        return dataSheetFileDir + dirSeparator + fileNameWithExtension;
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
