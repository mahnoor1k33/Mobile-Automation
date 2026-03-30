package com.contegris.intelliinbox.tests;

import com.contegris.intelliinbox.base.BaseTest;
import com.contegris.intelliinbox.pages.SideBarMenuPage;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Logout extends BaseTest { // Extends BaseTest, not BasePage

    private final By loginButton = AppiumBy.accessibilityId("Login");

    @Test(description = "Verify that the user can successfully log out and see the login screen.")
    public void verifyLogoutFunctionality() {
        SideBarMenuPage sideMenu = new SideBarMenuPage(driver); // Pass only driver

        try {
            System.out.println("Starting Logout Test...");

            // Step 1: Open side menu
            sideMenu.openSideMenu();
            System.out.println("Side menu opened successfully.");

            // Step 2: Click Logout
            sideMenu.clickLogout();
            System.out.println("Logout option clicked.");

            // Step 3: Wait and verify that Login screen is displayed
            boolean isLoginVisible = isElementPresent(loginButton);

            Assert.assertTrue(isLoginVisible, "Logout failed — Login button not visible after logout!");
            System.out.println("Logout successful. Login screen is visible.");

        } catch (AssertionError ae) {
            System.err.println("Assertion failed: " + ae.getMessage());
            throw ae; // rethrow for TestNG reporting
        } catch (Exception e) {
            System.err.println("Unexpected error during logout test: " + e.getMessage());
            Assert.fail("Test failed due to unexpected exception: " + e.getMessage());
        }
    }

    // Helper method - moved from inline usage
    private boolean isElementPresent(By locator) {
        try {
            driver.findElement(locator);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}