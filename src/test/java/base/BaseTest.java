package base;

import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.asserts.SoftAssert;

import utils.ConfigReader;
import utils.DriverFactory;

public class BaseTest {

    protected WebDriver driver;
    protected SoftAssert softAssert;

    protected static final Logger log =
            LoggerFactory.getLogger(BaseTest.class);

    @Parameters("browser")
    @BeforeMethod
    public void setUp(@Optional("chrome") String browser) {

        log.info("========== Test Setup Started ==========");

        softAssert = new SoftAssert();

        DriverFactory.initDriver(browser);

        driver = DriverFactory.getDriver();

        String url = ConfigReader.get("url");

        driver.get(url);

        log.info("Navigating to URL: {}", url);



        log.info("========== Test Setup Completed ==========");
    }

    @AfterMethod
    public void tearDown() {

        log.info("========== Test Teardown Started ==========");

        try {
            //this is mandatory
            softAssert.assertAll();
            log.debug("SoftAssert assertions executed");
        } catch (AssertionError e) {
            log.error("Assertion failure detected", e);
            throw e;
        } finally {
            DriverFactory.quitDriver();
            log.info("WebDriver closed successfully");
        }

        log.info("========== Test Teardown Completed ==========");
    }
}
