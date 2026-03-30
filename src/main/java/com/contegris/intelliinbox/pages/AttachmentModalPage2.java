package com.contegris.intelliinbox.pages;

import com.contegris.intelliinbox.base.BasePage;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class AttachmentModalPage2 extends BasePage {

    // ============================================================
    // 🔹 LOCATORS - Main Menu
    // ============================================================
    private final By plusButton = AppiumBy.xpath(
            "//android.widget.FrameLayout[@resource-id='android:id/content']" +
                    "/android.widget.FrameLayout/android.widget.FrameLayout/android.view.View" +
                    "/android.view.View/android.view.View/android.view.View/android.view.View/android.widget.ImageView[3]"
    );
    private final By attachmentsMenu = AppiumBy.accessibilityId("Attachments");
    private final By sendButton = AppiumBy.xpath(
            "//android.view.View[@content-desc=\"1 file selected\"]/android.widget.ImageView"
    );

    // ============================================================
    // 🔹 LOCATORS - Attachment Type Options
    // ============================================================
    private final By imageOption = AppiumBy.accessibilityId("Image");
    private final By videoOption = AppiumBy.accessibilityId("Video");
    private final By audioOption = AppiumBy.accessibilityId("Audio");
    private final By fileOption = AppiumBy.accessibilityId("File");

    // ============================================================
    // 🔹 LOCATORS - File Browser Navigation
    // ============================================================
    private final By moreOption = AppiumBy.accessibilityId("More options");
    private final By browseOption = AppiumBy.androidUIAutomator("new UiSelector().text(\"Browse…\")");
    private final By showRoots = AppiumBy.accessibilityId("Show roots");
    private final By downloadsFolder = AppiumBy.xpath(
            "//android.widget.TextView[@resource-id='android:id/title' and @text='Downloads']"
    );
    private final By testMediaFolder = AppiumBy.xpath(
            "//android.widget.TextView[@resource-id='android:id/title' and @text='TestMedia']"
    );

    // ============================================================
    // 🔹 CONSTRUCTOR
    // ============================================================

    public AttachmentModalPage2(AndroidDriver driver) {
        super(driver);
    }

    // ============================================================
    // 🔹 PUBLIC UPLOAD METHODS
    // ============================================================

    /**
     * Upload an image by file name.
     */
    public void uploadImage(String fileName) {
        openAttachmentMenu();
        waitAndClick(imageOption);
        navigateToTestMediaFolder();
        selectFileByName(fileName);
        waitForVisible(sendButton);
        clickSend();
        System.out.println("Sent image: " + fileName);
    }

    /**
     * Upload a video by file name.
     */
    public void uploadVideo(String fileName) {
        openAttachmentMenu();
        waitAndClick(videoOption);
        navigateToTestMediaFolder();
        selectFileByName(fileName);
        waitForVisible(sendButton);
        clickSend();
        System.out.println("Sent video: " + fileName);
    }

    /**
     * Upload an audio by file name.
     */
    public void uploadAudio(String fileName) {
        openAttachmentMenu();
        waitAndClick(audioOption);
        waitAndClick(showRoots);
        waitAndClick(downloadsFolder);
        waitAndClick(testMediaFolder);
        selectFileByName(fileName);
        waitForVisible(sendButton);
        clickSend();
        System.out.println("Sent audio: " + fileName);
    }

    /**
     * Upload a document by file name.
     */
    public void uploadDocument(String fileName) {
        openAttachmentMenu();
        waitAndClick(fileOption);
        waitAndClick(showRoots);
        waitAndClick(downloadsFolder);
        waitAndClick(testMediaFolder);
        selectFileByName(fileName);
        waitForVisible(sendButton);
        clickSend();
        System.out.println("Sent document: " + fileName);
    }

    // ============================================================
    // 🔹 PRIVATE HELPERS
    // ============================================================

    private void openAttachmentMenu() {
        waitAndClick(plusButton);
        waitAndClick(attachmentsMenu);
    }

    private void navigateToTestMediaFolder() {
        waitAndClick(moreOption);
        waitAndClick(browseOption);
        waitAndClick(showRoots);
        waitAndClick(downloadsFolder);
        waitAndClick(testMediaFolder);
    }

    private void selectFileByName(String fileName) {
        WebElement file = wait.until(ExpectedConditions.elementToBeClickable(
                AppiumBy.androidUIAutomator(
                        "new UiScrollable(new UiSelector().scrollable(true))" +
                                ".scrollIntoView(new UiSelector().textContains(\"" + fileName + "\"))"
                )
        ));
        file.click();
        System.out.println("📄 Selected: " + fileName);
    }

    private void clickSend() {
        waitUntilClickable(sendButton, 15);
        waitAndClick(sendButton);
    }
}