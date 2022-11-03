package za.co.pps.utility;

import com.aventstack.extentreports.MediaEntityBuilder;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import za.co.pps.page.TestBase;

import java.io.IOException;
import java.util.logging.Logger;

public class ExtentTestNGITestListener extends TestBase implements ITestListener {

    public static final Logger log4j = Logger.getLogger(ExtentTestNGITestListener.class.getName());

    @Override
    public void onTestStart(ITestResult iTestResult) {
        log4j.info("Starting tests");
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        try {
            Utilities.extentTest.pass("The passed : ", MediaEntityBuilder.createScreenCaptureFromPath( Utilities.getScreenShot(this.driver)).build());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        try {
            Utilities.extentTest.fail("The failed : ", MediaEntityBuilder.createScreenCaptureFromPath( Utilities.getScreenShot(this.driver)).build());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        try {
            Utilities.extentTest.skip("The failed : ", MediaEntityBuilder.createScreenCaptureFromPath( Utilities.getScreenShot(this.driver)).build());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
        log4j.info("Test with a certain percentage");
    }

    @Override
    public void onStart(ITestContext iTestContext) {
        log4j.info("Starting test");
    }

    @Override
    public void onFinish(ITestContext iTestContext) {
        log4j.info("Finising the test");
    }
}
