package com.contegris.intelliinbox.pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;

import java.time.Duration;
import java.util.List;

public class SideBarMenuPage {

    private AndroidDriver driver;
    private WebDriverWait wait;

    // ✅ Constructor (for driver + wait initialization)
    public SideBarMenuPage(AndroidDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    // 🔹 Locators (declared outside methods)
    private final AppiumBy sideMenuButton = (AppiumBy) AppiumBy.xpath(
            "//android.widget.FrameLayout[@resource-id='android:id/content']"
                    + "/android.widget.FrameLayout/android.widget.FrameLayout/android.view.View"
                    + "/android.view.View/android.view.View/android.view.View/android.view.View"
                    + "/android.view.View[1]/android.widget.ImageView");
    private final AppiumBy presenceIcon = (AppiumBy) AppiumBy.accessibilityId("Presence Info");
    private final AppiumBy readyOption = (AppiumBy) AppiumBy.accessibilityId("Ready");
    private final AppiumBy logoutButton = (AppiumBy) AppiumBy.accessibilityId("Logout");

    // ======================== ACTION METHODS ========================

    // 🔸 Open side menu
    public void openSideMenu() {
        wait.until(ExpectedConditions.elementToBeClickable(sideMenuButton)).click();
        System.out.println("✅ Side menu opened");
    }

    // 🔸 Reset presence to Ready if needed
    public void resetPresenceIfNeeded() {
        WebElement statusElement = wait.until(ExpectedConditions.presenceOfElementLocated(presenceIcon));
        String currentStatus = statusElement.getAttribute("contentDescription");

        if (!"Ready".equalsIgnoreCase(currentStatus)) {
            statusElement.click();
            wait.until(ExpectedConditions.elementToBeClickable(readyOption)).click();
            System.out.println("🔄 Presence reset to Ready");
        } else {
            System.out.println("✅ Presence already Ready");
        }
    }

    // 🔸 Close side menu (tap outside using coordinates)
    public void closeSideMenu() {
        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence tap = new Sequence(finger, 1);
        tap.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), 902, 605));
        tap.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        tap.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        driver.perform(List.of(tap));
        System.out.println("✅ Side menu closed");
    }

    // 🔸 Logout from the app
    public void clickLogout() {
        wait.until(ExpectedConditions.elementToBeClickable(logoutButton)).click();
        System.out.println("🚪 Logged out successfully");
    }
}
