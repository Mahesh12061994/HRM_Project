package com.orangehrm.base;

import java.util.concurrent.TimeUnit;
import java.util.logging.LogManager;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class Base_Class {
	WebDriver driver;
	private static final Logger logger=org.apache.log4j.LogManager.getLogger(Base_Class.class); 
		
<<<<<<< HEAD
		WebDriverManager.chromedriver().setup();
		driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(40,TimeUnit.SECONDS);
		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
		System.out.println("broswer lanched");
		System.out.println(driver.getTitle());
		System.out.println("Page title is "+driver.getTitle());
		Thread.sleep(3000);
=======
	
	@BeforeSuite
	public  void setup() {
		
		logger.info("Exicuting setup");
>>>>>>> branch 'master' of https://github.com/Mahesh12061994/HRM_Project.git
		
	}
	@Test
	public void Crossbrowser_testing() throws InterruptedException {
String browser = System.getProperty("browser", "firefox"); // Use system property for cross-browser testing (default: chrome)
        
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
	public  void closebroswer() {
		driver.quit();
		logger.info("browser closed");

	}
	
	}
	


