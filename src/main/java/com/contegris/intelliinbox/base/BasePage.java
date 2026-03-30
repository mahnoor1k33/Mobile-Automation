
package com.contegris.intelliinbox.base;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import java.time.Duration;
import java.util.List;
import java.util.Map;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;

// BasePage: Provides reusable helper methods for all Page Objects

public class BasePage {
    protected AndroidDriver driver;
    protected WebDriverWait wait;

    // Constructor - Every page must receive a driver
    public BasePage(AndroidDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    // ========== HELPER METHODS ==========
    // These are the "reusable utilities" pages need


    // Waits for an element to be clickable and clicks it.
    protected void waitAndClick(By locator) {
        wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
    }

    // Waits for an element to be visible, clears it, and types the given text
    protected void waitAndSendKeys(By locator, String text) {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        element.clear();
        element.click();
        driver.executeScript("mobile: type", Map.of("text", text));
    }

    // Waits for an element to be visible and returns it
    protected WebElement waitForVisible(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    protected WebElement waitForToast(By locator) {
        return new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    // Returns true if the element is present, otherwise false
    protected boolean isElementPresent(By locator) {
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(locator));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // Quickly checks if an element is present within a custom timeout
    protected boolean isElementPresentQuick(By locator, int timeoutSeconds) {
        try {
            new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds))
                    .until(ExpectedConditions.presenceOfElementLocated(locator));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    // Waits until the element is clickable within the given timeout
    protected void waitUntilClickable(By locator, int timeoutSeconds) {
        WebDriverWait customWait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));
        customWait.until(ExpectedConditions.elementToBeClickable(locator));
    }

   // Scrolls to the element by content-description and clicks.
   protected void scrollToAndClickByDescription(String contentDesc) {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(
                AppiumBy.androidUIAutomator(
                        "new UiScrollable(new UiSelector().scrollable(true))" +
                                ".scrollIntoView(new UiSelector().descriptionContains(\"" + contentDesc + "\"))"
                )
        ));
        element.click();
    }

   // Scrolls to the element by text and clicks.
    protected void scrollToAndClickByText(String text) {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(
                AppiumBy.androidUIAutomator(
                        "new UiScrollable(new UiSelector().scrollable(true))" +
                                ".scrollIntoView(new UiSelector().textContains(\"" + text + "\"))"
                )
        ));
        element.click();
    }

    // Scrolls to the element by resource-id and clicks.
    protected void scrollToAndClickByResourceId(String resourceId) {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(
                AppiumBy.androidUIAutomator(
                        "new UiScrollable(new UiSelector().scrollable(true))" +
                                ".scrollIntoView(new UiSelector().resourceId(\"" + resourceId + "\"))"
                )
        ));
        element.click();
    }

    // Scroll to Top
    public void scrollIntoView(String contentDesc) {
        try {
            driver.findElement(AppiumBy.androidUIAutomator(
                    "new UiScrollable(new UiSelector().scrollable(true))" +
                            ".scrollIntoView(new UiSelector().description(\"" + contentDesc + "\"))"
            ));
        } catch (Exception e) {
            // Element already visible or not found — safe to ignore
        }
    }

    // Tap anywhere via Coordinates
    public void tapByCoordinates(int x, int y) {
        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence tap = new Sequence(finger, 1)
                .addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), x, y))
                .addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
                .addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        driver.perform(List.of(tap));
    }
}