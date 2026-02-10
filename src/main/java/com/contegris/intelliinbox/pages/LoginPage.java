package com.contegris.intelliinbox.pages;

import com.contegris.intelliinbox.base.BasePage;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.Map;

public class LoginPage extends BasePage {

    // Constructor - MUST accept driver
    public LoginPage(AndroidDriver driver) {
        super(driver); // Pass to BasePage constructor
    }

    // Locators
    private final By nextButton = AppiumBy.accessibilityId("Next");
    private final By domainField = By.xpath("//android.widget.EditText");
    private final By usernameField = AppiumBy.xpath("//android.view.View[@content-desc='Enter your username or email']/android.widget.EditText");
    private final By passwordField = AppiumBy.xpath("//android.view.View[@content-desc=\"Enter your password\"]/android.widget.EditText");
    private final By signInButton = AppiumBy.xpath("//android.widget.Button[@content-desc=\"Sign In\"]");
    private final By allowButton = AppiumBy.accessibilityId("com.android.permissioncontroller:id/permission_allow_button");
    private final By myInboxHeading = AppiumBy.accessibilityId("My Inbox");

    // Page Methods - Use helper methods from BasePage
    public void launchApp() {
        System.out.println("Intellicon App launched successfully!");
    }

    public void enterDomainIfVisible(String domain) {
        if (isElementPresent(allowButton)) { // ← Using BasePage method
            waitAndClick(allowButton);       // ← Using BasePage method
            System.out.println("Permission allowed");
        }

        if (isElementPresent(domainField)) {
            waitAndClick(domainField);
            waitAndSendKeys(domainField, domain); // ← Using BasePage method

            if (isElementPresent(nextButton)) {
                waitAndClick(nextButton);
                System.out.println("Domain entered and Next clicked");
            }
        } else {
            System.out.println("Domain screen not displayed, skipping to login");
        }
    }

    public void login(String username, String password) {
        waitAndClick(usernameField);
        driver.executeScript("mobile: type", Map.of("text", username));

        waitAndClick(passwordField);
        driver.executeScript("mobile: type", Map.of("text", password));

        waitAndClick(signInButton);
        System.out.println("Logged in successfully!");
    }

    public void verifyMyInboxScreen() {
        WebElement heading = waitForVisible(myInboxHeading); // ← Using BasePage method
        Assert.assertEquals(heading.getText(), "My Inbox", "❌ My Inbox heading not displayed");
        System.out.println("My Inbox screen verified");
    }
}