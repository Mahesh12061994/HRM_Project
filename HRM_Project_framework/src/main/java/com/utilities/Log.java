package com.utilities;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.aventstack.extentreports.ExtentTest;


public class Log {
    private static final Logger logger = LogManager.getLogger(Log.class);

    // Log info level message
    public static void info(String message) {
        logger.info(message);
        ExtentReporterUtil.getTest().info(message); // Log to both Log4j and Extent Report
    }

    // Log warn level message
    public static void warn(String message) {
        logger.warn(message);
        ExtentReporterUtil.getTest().warning(message);
    }

    // Log error level message
    public static void error(String message) {
        logger.error(message);
        ExtentReporterUtil.getTest().fail(message);
    }

    // Log fatal level message
    public static void fatal(String message) {
        logger.fatal(message);
        ExtentReporterUtil.getTest().fail(message);
    }
}

