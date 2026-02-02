package com.contegris.intelliinbox.base;

import com.contegris.intelliinbox.utils.ConfigReader;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.net.URL;

import static java.time.Duration.ofSeconds;

public class BasePage {
    protected AndroidDriver driver;
    protected WebDriverWait wait;


    @BeforeMethod
    public void setUp() throws Exception {
        UiAutomator2Options options = new UiAutomator2Options();
        options.setPlatformName(ConfigReader.get("platformName"));
        options.setDeviceName(ConfigReader.get("deviceName"));
        options.setPlatformVersion(ConfigReader.get("platformVersion"));
        options.setAutomationName(ConfigReader.get("automationName"));

        options.setApp(ConfigReader.get("appPath"));

        options.setAppPackage(ConfigReader.get("appPackage"));
        options.setAppActivity(ConfigReader.get("appActivity"));

        options.setNoReset(Boolean.parseBoolean(ConfigReader.get("noReset")));
        options.setCapability("newCommandTimeout", 300);

        driver = new AndroidDriver(new URL(ConfigReader.get("appiumServerURL")), options);
        driver.manage().timeouts().implicitlyWait(ofSeconds(20));

        // ✅ Initialize explicit wait here
        wait = new WebDriverWait(driver, ofSeconds(20));

        System.out.println("✅ Appium driver initialized!");
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            System.out.println("✅ Driver quit successfully!");
        }
    }

    public AndroidDriver getDriver() {
        return driver;
    }

    public WebDriverWait getWait() {
        return wait;
    }

    // Helper Methods
    protected void waitAndClick(By locator) {

        wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
    }

    protected void waitAndSendKeys(By locator, String text) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, ofSeconds(20));
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            element.sendKeys(text);
            System.out.println("✅ Sent keys to: " + locator);
        } catch (Exception e) {
            System.out.println("❌ Failed to send keys: " + locator + " - " + e.getMessage());
        }
    }

    protected void waitUntilClickable(By locator, int timeoutSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, ofSeconds(timeoutSeconds));
        wait.until(ExpectedConditions.elementToBeClickable(locator));
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


}
