package com.contegris.intelliinbox.pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AttachmentModalPage {

    private AndroidDriver driver;
    private WebDriverWait wait;

    // ============================
    // 🔹 Locators Initialized Outside
    // ============================
    private final By plusButton = AppiumBy.xpath(
            "//android.widget.FrameLayout[@resource-id='android:id/content']" +
                    "/android.widget.FrameLayout/android.widget.FrameLayout/android.view.View" +
                    "/android.view.View/android.view.View/android.view.View/android.view.View/android.widget.ImageView[3]"
    );

    private final By attachmentsMenu = AppiumBy.accessibilityId("Attachments");
    private final By galleryOption = AppiumBy.accessibilityId("Gallery");
    private final By videoOption = AppiumBy.accessibilityId("Video");
    private final By audioOption = AppiumBy.accessibilityId("Audio");
    private final By fileOption = AppiumBy.accessibilityId("File");
    private final By showRoots = AppiumBy.accessibilityId("Show roots");

    private final By downloadsFolder = AppiumBy.xpath("//android.widget.TextView[@resource-id='android:id/title' and @text='Downloads']");
    private final By imagesFolder = AppiumBy.xpath("//android.widget.TextView[@resource-id='android:id/title' and @text='Images']");
    private final By videosFolder = AppiumBy.xpath("//android.widget.TextView[@resource-id='android:id/title' and @text='Videos']");
    private final By audiosFolder = AppiumBy.xpath("//android.widget.TextView[@resource-id='android:id/title' and @text='Audios']");
    private final By documentsFolder = AppiumBy.xpath("//android.widget.TextView[@resource-id='android:id/title' and @text='Documents']");

    private final By thirdImage = AppiumBy.xpath("(//android.widget.ImageView[@resource-id='com.google.android.documentsui:id/icon_thumb'])[3]");
    private final By thirdVideo = AppiumBy.androidUIAutomator("new UiSelector().resourceId(\"com.google.android.documentsui:id/icon_thumb\").instance(3)");
    private final By secondAudio = AppiumBy.androidUIAutomator("new UiSelector().resourceId(\"com.google.android.documentsui:id/icon_thumb\").instance(2)");
    private final By secondDocument = AppiumBy.androidUIAutomator("new UiSelector().resourceId(\"com.google.android.documentsui:id/icon_thumb\").instance(2)");

    // ============================
    // 🔹 Constructor
    // ============================
    public AttachmentModalPage(AndroidDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    // ============================
    // 🔹 Actions
    // ============================
    public void clickPlusButton() {
        waitAndClick(plusButton);
        System.out.println("✅ Clicked on '+' button to open menu");
    }

    public void uploadAttachment(String type) throws InterruptedException {
        switch (type.toLowerCase()) {
            case "image" -> uploadImage();
            case "video" -> uploadVideo();
            case "audio" -> uploadAudio();
            case "file", "document" -> uploadDocument();
            default -> throw new IllegalArgumentException("Invalid attachment type: " + type);
        }
    }

    // ========== Upload Actions ==========

    private void uploadImage() {
        waitAndClick(attachmentsMenu);
        waitAndClick(galleryOption);
        waitAndClick(showRoots);
        waitAndClick(downloadsFolder);
        waitAndClick(imagesFolder);
        waitAndClick(thirdImage);
        System.out.println("✅ Picked an image");
    }

    private void uploadVideo() {
        reopenAttachmentMenu();
        waitAndClick(videoOption);
        waitAndClick(showRoots);
        waitAndClick(downloadsFolder);
        waitAndClick(videosFolder);
        waitAndClick(thirdVideo);
        System.out.println("✅ Picked a video");
    }

    private void uploadAudio() {
        reopenAttachmentMenu();
        waitAndClick(audioOption);
        waitAndClick(showRoots);
        waitAndClick(downloadsFolder);
        waitAndClick(audiosFolder);
        waitAndClick(secondAudio);
        System.out.println("✅ Picked an audio");
    }

    private void uploadDocument() {
        reopenAttachmentMenu();
        waitAndClick(fileOption);
        waitAndClick(showRoots);
        waitAndClick(downloadsFolder);
        waitAndClick(documentsFolder);
        waitAndClick(secondDocument);
        System.out.println("✅ Picked a document");
    }

    // ========== Helper ==========

    private void reopenAttachmentMenu() {
        waitAndClick(plusButton);
        waitAndClick(attachmentsMenu);
    }

    private void waitAndClick(By locator) {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
        element.click();
    }
}
