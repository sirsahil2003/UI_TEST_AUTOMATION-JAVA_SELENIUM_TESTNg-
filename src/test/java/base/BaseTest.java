package base;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import utils.ConfigReader;
import utils.DriverFactory;

import java.util.Objects;

public class BaseTest {

    protected WebDriver driver;

    @BeforeMethod
    @Parameters("browser")
    public void setUp(String browser) {
        DriverFactory.initDriver(browser);  //initializing driver runtime polymorphism
        driver = DriverFactory.getDriver();  // getting driver object using Singleton design pattern
        driver.get(ConfigReader.get("url")); //navigating to the url provided in config file
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
