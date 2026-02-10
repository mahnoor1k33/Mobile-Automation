package com.contegris.intelliinbox.base;

import com.contegris.intelliinbox.utils.ConfigReader;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.net.URL;
import java.time.Duration;

/**
 * BaseTest: Handles driver initialization for all Test Classes
 * Tests extend this to get a fresh driver before each @Test
 */
public class BaseTest {
    protected AndroidDriver driver;

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
        options.setCapability("enableNotificationListener", true);

        options.setNoReset(true);
        options.setFullReset(false);
        options.setCapability("newCommandTimeout", 300);

        driver = new AndroidDriver(new URL(ConfigReader.get("appiumServerURL")), options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

        System.out.println("✅ Appium driver initialized!");
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            System.out.println("✅ Driver quit successfully!");
        }
    }
}