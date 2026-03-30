package com.contegris.intelliinbox.pages;

import com.contegris.intelliinbox.base.BasePage;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.Map;

public class LoginPage extends BasePage {

    public LoginPage(AndroidDriver driver) {
        super(driver);
    }

    // ======== Locators ========

    private final By domainField      = By.xpath("//android.widget.EditText");
    private final By nextButton       = AppiumBy.accessibilityId("Next");

    // Change the paths as it is Stable — works whether the field is empty or filled
    private final By usernameField    = AppiumBy.androidUIAutomator("new UiSelector().className(\"android.widget.EditText\").instance(0)");
    private final By passwordField    = AppiumBy.androidUIAutomator("new UiSelector().className(\"android.widget.EditText\").instance(1)");
    private final By signInButton     = AppiumBy.xpath("//android.widget.Button[@content-desc='Sign In']");
    private final By allowButton      = AppiumBy.accessibilityId("com.android.permissioncontroller:id/permission_allow_button");
    private final By switchDomain     = AppiumBy.accessibilityId("Switch Domain");
    private final By myInboxHeading   = AppiumBy.accessibilityId("My Inbox");

    // Toast locators
    private final By invalidDomainToast      = AppiumBy.androidUIAutomator("new UiSelector().descriptionContains(\"Invalid domain\")");
    private final By invalidCredentialsToast = AppiumBy.androidUIAutomator("new UiSelector().descriptionContains(\"Invalid username/password\")");

    // ======== Launching App ========

    public void launchApp() throws InterruptedException {
        driver.activateApp("com.contegris.intelli_inbox");
        Thread.sleep(2000);
        System.out.println("Intellicon app launched");
    }

    // ======== Checking States ========

    public boolean isAlreadyLoggedIn() {
        return isElementPresentQuick(myInboxHeading, 3);
    }

    // ======== Domain Screen ========
    public void enterDomain(String domain) {
        if (isElementPresentQuick(allowButton, 2))
        {
            waitAndClick(allowButton);
        }
        if (isElementPresentQuick(nextButton, 3))
        {
            waitAndClick(domainField);
            waitAndSendKeys(domainField, domain);
            waitAndClick(nextButton);
        }
        else {
            System.out.println("Domain screen not displayed, navigating to Login");
        }
    }

    // ======== Login Screen ========

    public void enterCredentials(String username, String password) {
        waitAndClick(usernameField);
        driver.executeScript("mobile: type", Map.of("text", username));

        waitAndClick(passwordField);
        driver.executeScript("mobile: type", Map.of("text", password));
        System.out.println("Entered credentials");
    }

    public void clickSignIn() {
        waitAndClick(signInButton);
        System.out.println("Clicked Sign In");
    }

    public void clickSwitchDomain() {
        waitAndClick(switchDomain);
        System.out.println("Clicked Switch Domain — navigated back to domain screen");
    }

    // ======== Combined Flows ========

    // Full happy path login
    public void login(String domain, String username, String password) {
        enterDomain(domain);
        enterCredentials(username, password);
        clickSignIn();
    }

    // ======== Verifications ========

    public void verifyMyInboxScreen() {
        WebElement heading = waitForVisible(myInboxHeading);
        Assert.assertEquals(heading.getAttribute("content-desc"), "My Inbox",
                "My Inbox heading not displayed");
        System.out.println("My Inbox screen verified");
    }

    public void verifyInvalidDomainToast() {
        WebElement toast = waitForToast(invalidDomainToast);
        Assert.assertTrue(toast.getAttribute("content-desc").contains("Invalid domain"),
                "Invalid domain toast not displayed");
        System.out.println("Invalid domain toast verified");
    }

    public void verifyInvalidCredentialsToast() {
        WebElement toast = waitForToast(invalidCredentialsToast);
        Assert.assertTrue(toast.getAttribute("content-desc").contains("Invalid username/password"),
                "Invalid credentials toast not displayed");
        System.out.println("Invalid credentials toast verified");
    }


    // ======== Clearing Fields Methods ========
    public void clearDomainField() {
        waitAndClick(domainField);
        driver.findElement(domainField).clear();
        System.out.println("Domain field cleared");
    }

    public void clearCredentials() {
        waitAndClick(usernameField);
        driver.findElement(usernameField).clear();
        waitAndClick(passwordField);
        driver.findElement(passwordField).clear();
        System.out.println("Credentials cleared");
    }
}