package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public final class WaitUtils {

    private static final int DEFAULT_EXPLICIT_WAIT = 10;

    private WaitUtils() {}

    // -------------------- IMPLICIT WAIT --------------------

    /**
     * Apply implicit wait globally
     */
    public static void setImplicitWait(WebDriver driver, int timeInSeconds) {
        driver.manage().timeouts()
                .implicitlyWait(Duration.ofSeconds(timeInSeconds));
    }

    // -------------------- EXPLICIT WAIT --------------------

    public static WebElement waitForElementToBeClickable(
            WebDriver driver, By locator) {

        WebDriverWait wait =
                new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_EXPLICIT_WAIT));

        return wait.until(ExpectedConditions.refreshed(ExpectedConditions.elementToBeClickable(locator)));
    }

    public static WebElement waitForElementToBeVisible(
            WebDriver driver, By locator) {

        WebDriverWait wait =
                new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_EXPLICIT_WAIT));

        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public static boolean waitForTitleContains(
            WebDriver driver, String title) {

        WebDriverWait wait =
                new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_EXPLICIT_WAIT));

        return wait.until(ExpectedConditions.titleContains(title));
    }
}
