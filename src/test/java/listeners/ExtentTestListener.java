package listeners;

import com.aventstack.extentreports.ExtentTest;
import org.testng.*;
import utils.DriverFactory;
import utils.ExtentManager;
import utils.ExtentTestManager;
import utils.ScreenshotUtils;

public class ExtentTestListener implements ITestListener {

    /**
     * This method is triggered by TestNG
     * BEFORE each @Test method execution.
     *
     * 🔥 STEP-LEVEL LOGGING IS ENABLED HERE
     * -----------------------------------
     * 1. We create an ExtentTest object for the current test.
     * 2. We store this ExtentTest in ExtentTestManager (ThreadLocal).
     *
     * Because ExtentTest is now stored in ThreadLocal,
     * Page Objects and utility classes can access it
     * and log individual steps during execution.
     */
    @Override
    public void onTestStart(ITestResult result) {

        // Create ExtentTest for current test case
        ExtentTest test = ExtentManager.getExtent()
                .createTest(result.getMethod().getMethodName());

        // 🔥 CRITICAL LINE:
        // Stores ExtentTest in ThreadLocal so that
        // step-level logging from Page Objects becomes possible
        ExtentTestManager.setTest(test);
    }

    /**
     * Called when test passes.
     * Logs final test status (not step-level).
     */
    @Override
    public void onTestSuccess(ITestResult result) {
        ExtentTestManager.getTest().pass("Test Passed");
    }

    /**
     * Called when test fails.
     * Logs exception details in Extent Report.
     */
    @Override
    public void onTestFailure(ITestResult result) {
        ExtentTestManager.getTest().fail(result.getThrowable());


        String screenshotPath = ScreenshotUtils.captureScreenshot(
                DriverFactory.getDriver(),
                result.getMethod().getMethodName()
        );


        ExtentTestManager.getTest().addScreenCaptureFromPath(screenshotPath);


    }

    /**
     * Called when test is skipped.
     */
    @Override
    public void onTestSkipped(ITestResult result) {
        ExtentTestManager.getTest().skip("Test Skipped");
    }

    /**
     * Called once after all tests are executed.
     *
     * Flush writes all logged test steps and results
     * into the final HTML report.
     */
    @Override
    public void onFinish(ITestContext context) {
        ExtentManager.getExtent().flush();
    }
}
