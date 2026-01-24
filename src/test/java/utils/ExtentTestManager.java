package utils;

import com.aventstack.extentreports.ExtentTest;

public class ExtentTestManager {

    /**
     * ThreadLocal is used to store one ExtentTest instance
     * per execution thread.
     *
     * 🔥 WHY THIS IS IMPORTANT:
     * ------------------------
     * - TestNG can run tests in parallel.
     * - Each test must log to its own ExtentTest instance.
     * - ThreadLocal ensures logs from parallel tests
     *   do NOT mix with each other.
     *
     * This is the backbone of step-level logging.
     */
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    /**
     * Returns the ExtentTest object associated with
     * the current execution thread.
     *
     * 🔥 Page Objects call this method to log steps like:
     * ExtentTestManager.getTest().log(Status.INFO, "Clicked Login button");
     *
     * Because of ThreadLocal, the correct test is always used.
     */
    public static ExtentTest getTest() {
        return test.get();
    }

    /**
     * Stores the ExtentTest object in ThreadLocal.
     *
     * 🔥 This method is called from the TestNG Listener
     * during onTestStart().
     *
     * Once set, this ExtentTest becomes globally accessible
     * (within the same thread) to:
     * - Page Objects
     * - Utility classes
     * - BasePage
     *
     * This single method ENABLES step-level logging.
     */
    public static void setTest(ExtentTest extentTest) {
        test.set(extentTest);
    }
}
