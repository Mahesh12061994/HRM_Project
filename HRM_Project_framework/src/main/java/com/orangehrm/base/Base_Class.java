package com.orangehrm.base;

import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import com.utilities.ExtentReporterUtil;

import com.utilities.Log;

public class Base_Class {

    // Correct Logger initialization using class name as a string
    private static final Logger logger = LogManager.getLogger(Base_Class.class.getName());

    WebDriver driver;

    @BeforeSuite
    public void setup() {
        // Initialize Extent Report
        ExtentReporterUtil.initializeExtentReport();
        logger.info("Initializing Extent Report...");
    }

    @Test
    public void Crossbrowser_testing() throws InterruptedException {
        String browser = System.getProperty("browser", "chrome");
        logger.info("Starting browser launch process...");

        if (browser.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
            logger.info("Launched Chrome Browser.");
        } else if (browser.equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
            logger.info("Launched Firefox Browser.");
        } else if (browser.equalsIgnoreCase("edge")) {
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver();
            logger.info("Launched Edge Browser.");
        } else {
            logger.error("Unsupported browser: " + browser);
            Assert.fail("Unsupported browser: " + browser);
        }

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        logger.info("Browser Launched: " + driver.getTitle());

        // Simulating waiting for page load
        Thread.sleep(3000);
        logger.info("Waiting 3 seconds before proceeding to the next steps.");
    }

    @AfterSuite
    public void closeBrowser() {
        if (driver != null) {
            driver.quit();
            logger.info("Browser closed.");
        }
        // Flush the extent report
        ExtentReporterUtil.flushReport();
    }
}
