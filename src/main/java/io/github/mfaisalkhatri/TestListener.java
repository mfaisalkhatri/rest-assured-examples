package io.github.mfaisalkhatri;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static org.apache.commons.lang3.StringUtils.repeat;

/**
 * Created By Faisal Khatri on 19-11-2021
 */

public class TestListener implements org.testng.ITestListener {

    Logger log = LogManager.getLogger (TestListener.class);

    private void logMessage (final String message) {
        this.log.info ("\n");
        this.log.info (repeat ("=", 75));
        this.log.info (message);
        this.log.info (repeat ("=", 75));
        this.log.info ("\n");
    }

    @Override
    public void onTestStart (final org.testng.ITestResult result) {
        // TODO Auto-generated method stub
        logMessage ("Test Execution Started...." + result.getName ());
    }

    @Override
    public void onTestSuccess (final org.testng.ITestResult result) {
        logMessage ("Test Passed Successfully." + result.getName ());

    }

    @Override
    public void onTestFailure (final org.testng.ITestResult result) {
        logMessage ("Test Failed!!!!" + result.getName ());
    }

    @Override
    public void onFinish (final org.testng.ITestContext context) {
        logMessage ("Test Execution Completed Successfully for all tests!!" + context.getSuite ()
            .getAllMethods ());

    }

}