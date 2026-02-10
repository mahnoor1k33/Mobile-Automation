package com.contegris.intelliinbox.pages;

import com.contegris.intelliinbox.base.BasePage;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;

import java.time.Duration;
import java.util.List;

public class SideBarMenuPage extends BasePage {

    // 🔹 Locators
    private final By sideMenuButton = AppiumBy.xpath(
            "//android.widget.FrameLayout[@resource-id='android:id/content']"
                    + "/android.widget.FrameLayout/android.widget.FrameLayout/android.view.View"
                    + "/android.view.View/android.view.View/android.view.View/android.view.View"
                    + "/android.view.View[1]/android.widget.ImageView");
    private final By presenceIcon = AppiumBy.accessibilityId("Presence Info");
    private final By readyOption = AppiumBy.accessibilityId("Ready");
    private final By logoutButton = AppiumBy.accessibilityId("Logout");

    // ✅ Constructor
    public SideBarMenuPage(AndroidDriver driver) {
        super(driver); // Pass driver to BasePage
    }

    // ======================== ACTION METHODS ========================

    // 🔸 Open side menu
    public void openSideMenu() {
        waitAndClick(sideMenuButton); // Use inherited helper method
        System.out.println("✅ Side menu opened");
    }

    // 🔸 Reset presence to Ready if needed
    public void resetPresenceIfNeeded() {
        WebElement statusElement = wait.until(ExpectedConditions.presenceOfElementLocated(presenceIcon));
        String currentStatus = statusElement.getAttribute("contentDescription");

        if (!"Ready".equalsIgnoreCase(currentStatus)) {
            statusElement.click();
            waitAndClick(readyOption); // Use inherited helper method
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
        waitAndClick(logoutButton); // Use inherited helper method
        System.out.println("🚪 Logged out successfully");
    }
}