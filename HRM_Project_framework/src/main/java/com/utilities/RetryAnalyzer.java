package com.utilities;


import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer {
    
    private int count = 0;
    private int maxRetryCount = 1; // Retry once

    @Override
    public boolean retry(ITestResult iTestResult) {
        if (count < maxRetryCount) {
            count++;
            return true; // Retry the test
        }
        return false; // Don't retry if max retry count reached
    }
}

