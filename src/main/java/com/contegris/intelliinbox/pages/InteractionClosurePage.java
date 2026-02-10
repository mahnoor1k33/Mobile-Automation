package com.contegris.intelliinbox.pages;

import com.contegris.intelliinbox.base.BasePage;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class InteractionClosurePage extends BasePage {

    // ✅ Locators
    private final By plusButton = AppiumBy.xpath("//android.widget.FrameLayout[@resource-id='android:id/content']" +
            "/android.widget.FrameLayout/android.widget.FrameLayout/android.view.View/android.view.View/" +
            "android.view.View/android.view.View/android.view.View/android.widget.ImageView[3]");
    private final By notesButton = AppiumBy.accessibilityId("Notes");
    private final By addNotesField = AppiumBy.xpath("//android.widget.EditText");
    private final By notesBackButton = AppiumBy.xpath("//android.view.View[@content-desc='Notes\nAdd Note']/android.widget.ImageView[2]");
    private final By workCodesButton = AppiumBy.accessibilityId("Work Codes");
    private final By closeWorkCodeButton = AppiumBy.accessibilityId("Close");
    private final By closeAddModal = AppiumBy.accessibilityId("Dismiss");
    private final By moreButton = AppiumBy.xpath("//android.widget.FrameLayout[@resource-id=\"android:id/content\"]/android.widget.FrameLayout/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View[1]/android.widget.ImageView[4]");
    private final By interactionInfoButton = AppiumBy.accessibilityId("Interaction Info");
    private final By infoBackButton = AppiumBy.xpath("//android.widget.FrameLayout[@resource-id=\"android:id/content\"]/android.widget.FrameLayout/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View[1]/android.widget.ImageView[1]");
    private final By endInteractionButton = AppiumBy.accessibilityId("End Interaction");
    private final By leaveInteractionButton = AppiumBy.accessibilityId("Leave Interaction");
    private final By confirmLeaveButton = AppiumBy.accessibilityId("Yes, sure");
    private final By cancelButton = AppiumBy.accessibilityId("Cancel");

    // ✅ Constructor: gets driver from BaseTest
    public InteractionClosurePage(AndroidDriver driver) {
        super(driver);
    }

    // Adding Notes before ending the Interaction
    public void addNote() {
        waitAndClick(plusButton);
        waitAndClick(notesButton);
        System.out.println("🟢 Clicked Notes");

        waitAndClick(addNotesField);
        waitAndSendKeys(addNotesField,"Hi, notes are added here :)");
        System.out.println("🟢 Added note text");

        waitAndClick(notesBackButton);
        System.out.println("🟢 Clicked Back Button from Notes");
    }

    // Applying work codes
    public void openPlusMenu() {
        try {
            waitAndClick(plusButton);
            System.out.println("✅ Plus (+) menu opened successfully");
        } catch (Exception e) {
            System.out.println("❌ Failed to open Plus menu: " + e.getMessage());
        }
    }

    // ✅ Step: Add Work Code (combined flow)
    public void addWorkCode(String workCodeName) {
        try {
            // Open Work Codes modal
            waitAndClick(workCodesButton);
            System.out.println("✅ Work Codes option clicked");

            // Select specific Work Code
            WebElement workCode = wait.until(ExpectedConditions.elementToBeClickable(
                    AppiumBy.xpath("//android.view.View[contains(@content-desc, '" + workCodeName + "')]")));
            workCode.click();
            System.out.println("✅ Selected Work Code: " + workCodeName);

            // Close modal
            waitAndClick(closeWorkCodeButton);
            System.out.println("✅ Closed Work Code modal successfully");

        } catch (Exception e) {
            System.out.println("❌ Failed to add Work Code '" + workCodeName + "': " + e.getMessage());
        }
    }


    // ✅ Step 5: Close Bottom Sheet modal
    public void closeBottomSheet () {

        try {
            waitAndClick(closeAddModal);
            System.out.println("✅ Bottom Sheet gets Closed");
        } catch (Exception e) {
            System.out.println("❌ Failed to close the Bottom Sheet: " + e.getMessage());
        }
    }

    // ✅ Method to End or Leave Interaction safely
    public void endInteraction(String workCodeName) {
        try {
            waitAndClick(moreButton);
            System.out.println("More button clicked");

            waitAndClick(interactionInfoButton);
            System.out.println("Clicked Interaction Info button");

            waitAndClick(infoBackButton);
            System.out.println("Navigated back from Interaction Info");

            // Try to end or leave interaction
            if (isElementPresent(endInteractionButton))
            {
                waitAndClick(endInteractionButton);
                System.out.println("Clicked End Interaction");

                if(isElementPresent(endInteractionButton))
                {
                    waitAndClick(endInteractionButton);
                    System.out.println("Workcode is already applied");
                    System.out.println("Clicked End Interaction");
                }
                else
                {
                    WebElement workCode = wait.until(ExpectedConditions.elementToBeClickable(
                            AppiumBy.xpath("//android.view.View[contains(@content-desc, '" + workCodeName + "')]")));
                    workCode.click();
                    System.out.println("✅ Selected Work Code: " + workCodeName);
                    isElementPresent(endInteractionButton);
                    waitAndClick(endInteractionButton);
                }
            }
            else if (isElementPresent(leaveInteractionButton))
            {
                waitAndClick(leaveInteractionButton);
                System.out.println("End Interaction not found — clicked Leave Interaction instead");
                waitAndClick(confirmLeaveButton);
                System.out.println("Interaction is leaved successfully");
//                waitAndClick(cancelButton);
//                System.out.println("Clicked on Cancel button");
            } else {
                System.out.println("Neither End nor Leave Interaction button found");
            }

        } catch (Exception e) {
            System.out.println("Failed to end interaction: " + e.getMessage());
        }
    }

}
