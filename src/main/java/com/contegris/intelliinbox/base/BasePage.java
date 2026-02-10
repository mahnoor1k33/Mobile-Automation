package com.contegris.intelliinbox.base;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * BasePage: Provides reusable helper methods for all Page Objects
 * Pages extend this to access common actions like waitAndClick()
 */

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

    protected void waitAndClick(By locator) {
        wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
        System.out.println("✅ Clicked: " + locator);
    }

    protected void waitAndSendKeys(By locator, String text) {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        element.clear();
        element.sendKeys(text);
        System.out.println("✅ Sent keys to: " + locator);
    }

    protected WebElement waitForVisible(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    protected boolean isElementPresent(By locator) {
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(locator));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

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
}