package utils;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public final class DriverFactory {

    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    private DriverFactory() {}

    /**
     * Initialize WebDriver based on browser name (hardcoded paths)
     */
    public static void initDriver(String browser) {

        if (driver.get() == null) {

            switch (browser.toLowerCase()) {

                case "chrome":
                    // Hardcoded ChromeDriver path
                    System.setProperty("webdriver.chrome.driver",
                            "D:\\SDET\\chromedriver-win64\\chromedriver.exe");
                    //set() set WebDriver for current thread
                    driver.set(new ChromeDriver());
                    break;

                case "firefox":
                    // Hardcoded GeckoDriver path
                    System.setProperty("webdriver.gecko.driver",
                            "D:\\SDET\\geckodriver_win32\\geckodriver.exe");
                    driver.set(new FirefoxDriver());
                    break;

                case "edge":
                    // Hardcoded EdgeDriver path
                    System.setProperty("webdriver.edge.driver",
                            "D:\\SDET\\edgedriver_win32\\msedgedriver.exe");
                    driver.set(new EdgeDriver());
                    break;

                default:
                    throw new IllegalArgumentException(
                            "Browser not supported: " + browser);
            }

            applyCommonSettings();
        }
    }

    /**
     * Apply common browser settings
     */
    private static void applyCommonSettings() {
        getDriver().manage().window().maximize();
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        getDriver().manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
    }

    /**
     * Get WebDriver for current thread
     */
    public static WebDriver getDriver() {
        return driver.get();
    }

    /**
     * Quit and clean WebDriver
     */
    public static void quitDriver() {
        if (driver.get() != null) {
            driver.get().quit();

            //Removes the current thread's value for this thread-local variable
            driver.remove();
        }
    }
}
