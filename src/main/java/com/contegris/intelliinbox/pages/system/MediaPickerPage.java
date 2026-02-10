package com.contegris.intelliinbox.pages.system;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MediaPickerPage {

    private AndroidDriver driver;
    private WebDriverWait wait;

    public MediaPickerPage(AndroidDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Scrolls to find and select the file by name.
    public void selectMediaByName(String fileName) {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(
                AppiumBy.androidUIAutomator(
                        "new UiScrollable(new UiSelector().scrollable(true))" +
                                ".scrollIntoView(new UiSelector().textContains(\"" + fileName + "\"))"
                )
        ));
        element.click();
        System.out.println("📁 Selected: " + fileName);
    }
}