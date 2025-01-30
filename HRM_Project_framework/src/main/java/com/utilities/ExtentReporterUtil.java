package com.utilities;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class ExtentReporterUtil {
    private static ExtentReports extent;
    private static ExtentTest test;

    public static ExtentReports initializeExtentReport() {
        if (extent == null) {
            ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") + "/test-output/ExtentReport.html");
            htmlReporter.config().setReportName("Automation Test Report");
            extent = new ExtentReports();
            extent.attachReporter(htmlReporter);
        }
        return extent;
    }

    public static ExtentTest createTest(String testName) {
        test = extent.createTest(testName);
        return test;
    }

    public static void flushReport() {
        extent.flush();
    }

    public static ExtentTest getTest() {
        return test;
    }
}
