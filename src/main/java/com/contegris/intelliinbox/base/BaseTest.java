package com.contegris.intelliinbox.base;

import com.contegris.intelliinbox.utils.ConfigReader;
import com.contegris.intelliinbox.utils.FileHelper;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.net.URL;
import java.time.Duration;

// BaseTest: Handles driver initialization for all Test Classes
// Setup basically prepares everything before the test: starts app, initializes the driver, creates waits, and sets session ready
// A driver session is basically a “connection” between your automation script and the mobile app (or browser) you want to test

public class BaseTest {
    protected AndroidDriver driver;

    @BeforeMethod
    public void setUp() throws Exception {
        UiAutomator2Options options = new UiAutomator2Options();
        options.setPlatformName(ConfigReader.get("platformName"));
        options.setDeviceName(ConfigReader.get("deviceName"));
        options.setUdid(ConfigReader.get("udid"));
        options.setPlatformVersion(ConfigReader.get("platformVersion"));
        options.setAutomationName(ConfigReader.get("automationName"));
        // options.setApp(ConfigReader.get("appPath"));
        options.setAppPackage(ConfigReader.get("appPackage"));
        options.setAppActivity(ConfigReader.get("appActivity"));
        options.setCapability("enableNotificationListener", true);

        options.setCapability("keepDeviceAwake", true);
        options.setCapability("newCommandTimeout", 600);
        options.setCapability("adbExecTimeout", 120000);
        options.setCapability("uiautomator2ServerLaunchTimeout", 120000);
        options.setCapability("uiautomator2ServerInstallTimeout", 120000);
        options.setCapability("mjpegServerPort", 9100); // avoids port conflicts between tests

        options.setNoReset(true);
        options.setFullReset(false);
        options.setCapability("newCommandTimeout", 300);

        driver = new AndroidDriver(new URL(ConfigReader.get("appiumServerURL")), options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

        System.out.println("Appium driver is initialized!");

        // Push files to the device
        FileHelper.pushFilesToDevice(driver);
    }

    // This tearDown() method basically closes the app / browser, Ends the session, Disconnects Appium from the device, Frees memory

    @AfterMethod
    public void tearDown()
    {
        if (driver != null) {
            driver.quit();
            System.out.println("Driver quit successfully!");
        }
    }
    // Commenting this out is fine during development (to keep the Inspector session alive),
    // this must be properly handled before suite runs — otherwise orphaned sessions pile up.
}