package com.listeners;

import com.aventstack.extentreports.ExtentTest;
import com.utilities.ExtentReporterUtil;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.openqa.selenium.chrome.ChromeDriver;
import java.io.File;
import java.io.IOException;

public class TestListener implements ITestListener {
    
    private ExtentTest test;

    @Override
    public void onTestFailure(ITestResult result) {
        test = ExtentReporterUtil.createTest(result.getMethod().getMethodName());
        
        // Capture screenshot
        WebDriver driver = (WebDriver) result.getTestContext().getAttribute("driver");
        TakesScreenshot screenshot = (TakesScreenshot) driver;
        File srcFile = screenshot.getScreenshotAs(OutputType.FILE);
        String filePath = System.getProperty("user.dir") + "/test-output/screenshots/" + result.getMethod().getMethodName() + ".png";
        File destFile = new File(filePath);
        
        try {
            FileUtils.copyFile(srcFile, destFile);
            test.fail("Test Failed: Screenshot attached").addScreenCaptureFromPath(filePath);
        } catch (IOException e) {
            test.fail("Failed to capture screenshot: " + e.getMessage());
        }

        // Also log the failure in the extent report
        test.fail(result.getThrowable());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        test = ExtentReporterUtil.createTest(result.getMethod().getMethodName());
        test.pass("Test Passed");
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        test = ExtentReporterUtil.createTest(result.getMethod().getMethodName());
        test.skip("Test Skipped");
    }

    @Override
    public void onStart(org.testng.ITestContext context) {}

    @Override
    public void onFinish(org.testng.ITestContext context) {}
}

