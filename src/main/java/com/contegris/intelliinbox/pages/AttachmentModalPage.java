package com.contegris.intelliinbox.pages;

import com.contegris.intelliinbox.pages.system.MediaPickerPage;
import com.contegris.intelliinbox.base.BasePage;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class AttachmentModalPage extends BasePage {

    // === Locators ===
    private final By plusButton = AppiumBy.xpath(
            "//android.widget.FrameLayout[@resource-id='android:id/content']" +
                    "/android.widget.FrameLayout/android.widget.FrameLayout/android.view.View" +
                    "/android.view.View/android.view.View/android.view.View/android.view.View/android.widget.ImageView[3]"
    );

    // === Locators for Attachment Menu ===
    private final By attachmentsMenu = AppiumBy.accessibilityId("Attachments");
    private final By imageOption = AppiumBy.accessibilityId("Image");
    private final By videoOption = AppiumBy.accessibilityId("Video");
    private final By audioOption = AppiumBy.accessibilityId("Audio");
    private final By fileOption = AppiumBy.accessibilityId("File");

    // === Locators for File Browse Navigation ===
    private final By moreOption = AppiumBy.accessibilityId("More options");
    private final By browseOption = AppiumBy.androidUIAutomator("new UiSelector().text(\"Browse…\")");
    private final By showRoots = AppiumBy.accessibilityId("Show roots");
    private final By downloadsFolder = AppiumBy.xpath("//android.widget.TextView[@resource-id='android:id/title' and @text='Downloads']");
    private final By imagesFolder = AppiumBy.xpath("//android.widget.TextView[@resource-id='android:id/title' and @text='Images']");
    private final By videosFolder = AppiumBy.xpath("//android.widget.TextView[@resource-id='android:id/title' and @text='Videos']");
    private final By audiosFolder = AppiumBy.xpath("//android.widget.TextView[@resource-id='android:id/title' and @text='Audio']");
    private final By UnknownFolder = AppiumBy.androidUIAutomator("new UiSelector().className(\"android.widget.LinearLayout\").instance(7)");
    private final By documentsFolder = AppiumBy.xpath("//android.widget.TextView[@resource-id='android:id/title' and @text='Documents']");

    // === Locators for the File Type Folders ===

    // === Images ===
    private final By jpgFolder = AppiumBy.xpath("//android.widget.TextView[@resource-id=\"android:id/title\" and @text=\"JPG\"]");
    private final By jpegFolder = AppiumBy.xpath("//android.widget.TextView[@resource-id=\"android:id/title\" and @text=\"JPEG\"]");
    private final By pngFolder = AppiumBy.xpath("//android.widget.TextView[@resource-id=\"android:id/title\" and @text=\"PNG\"]");

    // === Videos ===
    private final By mp4Folder = AppiumBy.xpath("//android.widget.TextView[@resource-id=\"android:id/title\" and @text=\"mp4Folder\"]");
    private final By threegppFolder = AppiumBy.xpath("//android.widget.TextView[@resource-id=\"android:id/title\" and @text=\"3gppFolder\"]");
    private final By mpegFolder = AppiumBy.xpath("//android.widget.TextView[@resource-id=\"android:id/title\" and @text=\"mpegFolder\"]");

    // === Audios ===
    private final By m4aFolder = AppiumBy.xpath("//android.widget.TextView[@resource-id=\"android:id/title\" and @text=\"m4aAudio\"]");
    private final By aacFolder = AppiumBy.xpath("//android.widget.TextView[@resource-id=\"android:id/title\" and @text=\"aacAudio\"]");
    private final By wavFolder = AppiumBy.xpath("//android.widget.TextView[@resource-id=\"android:id/title\" and @text=\"wavAudio\"]");
    private final By mp3Folder = AppiumBy.xpath("//android.widget.TextView[@resource-id=\"android:id/title\" and @text=\"mp3Audio\"]");
    private final By oggFolder = AppiumBy.xpath("//android.widget.TextView[@resource-id=\"android:id/title\" and @text=\"oggAudio\"]");

    // === Documents ===
    private final By xlsFolder = AppiumBy.xpath("//android.widget.TextView[@resource-id=\"android:id/title\" and @text=\"xlsFolder\"]");
    private final By pptFolder = AppiumBy.xpath("//android.widget.TextView[@resource-id=\"android:id/title\" and @text=\"pptFolder\"]");
    private final By docFolder = AppiumBy.xpath("//android.widget.TextView[@resource-id=\"android:id/title\" and @text=\"docFolder\"]");
    private final By pdfFolder = AppiumBy.xpath("//android.widget.TextView[@resource-id=\"android:id/title\" and @text=\"pdfFolder\"]");
    private final By txtFolder = AppiumBy.xpath("//android.widget.TextView[@resource-id=\"android:id/title\" and @text=\"txtFolder\"]");
    private final By otherFolder = AppiumBy.xpath("//android.widget.TextView[@resource-id=\"android:id/title\" and @text=\"otherFolder\"]");

    // === Locators for File Selection ===
    private final By secondImageJpeg = AppiumBy.xpath("(//android.widget.ImageView[@resource-id=\"com.google.android.documentsui:id/icon_thumb\"])[2]");
    private final By firstVideoMp4 = AppiumBy.androidUIAutomator("new UiSelector().className(\"android.widget.LinearLayout\").instance(9)");
    private final By firstAudioM4a = AppiumBy.androidUIAutomator("new UiSelector().resourceId(\"com.google.android.documentsui:id/icon_thumb\")");
    private final By firstDocumentXls = AppiumBy.androidUIAutomator("new UiSelector().text(\"sample-1.xlsx\")");
    private final By sendButton = AppiumBy.xpath("//android.view.View[@content-desc=\"1 file selected\"]/android.widget.ImageView");

    // ========== Constructor ==========
    public AttachmentModalPage(AndroidDriver driver) {
        super(driver); // ✅ Pass driver to BasePage
        // driver and wait are now inherited from BasePage
    }

    // ========== Actions ==========
    public void clickPlusButton() {
        waitAndClick(plusButton);
        System.out.println("✅ Clicked on '+' button to open menu");
    }

    public void uploadAttachment(String type) {
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
        waitAndClick(imageOption);
        waitAndClick(moreOption);
        waitAndClick(browseOption);
        waitAndClick(showRoots);
        waitAndClick(imagesFolder);
        waitAndClick(jpegFolder);
        waitAndClick(secondImageJpeg);
        waitUntilClickable(sendButton, 15);
        waitAndClick(sendButton);
        System.out.println("✅ Sent an image");
    }

    private void uploadVideo() {
        reopenAttachmentMenu();
        // waitAndClick(attachmentsMenu);
        waitAndClick(videoOption);
        waitAndClick(moreOption);
        waitAndClick(browseOption);
        waitAndClick(showRoots);
        waitAndClick(videosFolder);
        waitAndClick(mp4Folder);
        waitAndClick(firstVideoMp4);
        waitUntilClickable(sendButton, 15);
        waitAndClick(sendButton);
        System.out.println("✅ Sent a video");
    }

    private void uploadAudio() {
        reopenAttachmentMenu();
        // waitAndClick(attachmentsMenu);
        waitAndClick(audioOption);
        // waitAndClick(moreOption);
        // waitAndClick(browseOption);
        waitAndClick(showRoots);
        waitAndClick(audiosFolder);
        waitAndClick(UnknownFolder);
        waitAndClick(m4aFolder);
        waitAndClick(firstAudioM4a);
        waitUntilClickable(sendButton, 15);
        waitAndClick(sendButton);
        System.out.println("✅ Sent an audio");
    }

    private void uploadDocument() {
        reopenAttachmentMenu();
        // waitAndClick(attachmentsMenu);
        waitAndClick(fileOption);
        // waitAndClick(moreOption);
        // waitAndClick(browseOption);
        waitAndClick(showRoots);
        waitAndClick(documentsFolder);
        waitAndClick(xlsFolder);
        waitAndClick(firstDocumentXls);
        waitUntilClickable(sendButton, 15);
        waitAndClick(sendButton);
        System.out.println("✅ Sent a document");
    }

    // ========== Helper ==========
    private void reopenAttachmentMenu() {
        waitAndClick(plusButton);
        waitAndClick(attachmentsMenu);
    }

    // Note: waitAndClick() is inherited from BasePage, so no need to redefine it here
}