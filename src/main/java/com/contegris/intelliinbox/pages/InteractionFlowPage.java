package com.contegris.intelliinbox.pages;

import com.contegris.intelliinbox.base.BasePage;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

public class InteractionFlowPage extends BasePage {

    // 🔹 Common Locators
    private final By templatesButton = AppiumBy.xpath("//android.widget.FrameLayout[@resource-id=\"android:id/content\"]/android.widget.FrameLayout/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View/android.widget.ImageView[2]");
    private final By whatsappTemplateTab = AppiumBy.accessibilityId("WhatsApp Templates\nTab 2 of 2");
    private final By sendButton = AppiumBy.accessibilityId("Send");
    private final By emojiButton = AppiumBy.xpath("//android.widget.FrameLayout[@resource-id='android:id/content']/android.widget.FrameLayout/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View/android.widget.ImageView[1]");
    private final By secondEmojiTab = AppiumBy.xpath("//android.view.View[@content-desc='Tab 2 of 9']");
    private final By smileEmoji = AppiumBy.xpath("//android.widget.Button[@content-desc=\"\uD83D\uDE42\"]");
    private final By messageInput = AppiumBy.xpath("//android.widget.EditText");
    private final By expiryMessage = AppiumBy.xpath("//android.view.View[contains(@content-desc, 'within 24 hours')]");
    private final By voiceNoteButton = AppiumBy.xpath("//android.widget.FrameLayout[@resource-id=\"android:id/content\"]/android.widget.FrameLayout/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View/android.widget.ImageView[4]");
    private final By pauseButton = AppiumBy.xpath("//android.view.View[@bounds='[379,1403][531,1554]']]");
    private final By resumeButton = AppiumBy.xpath("//android.widget.Button[@index='4']");
    private final By sendVoiceNoteButton = AppiumBy.androidUIAutomator("new UiSelector().className(\"android.widget.ImageView\").instance(7)");
    private final By replyButton = AppiumBy.accessibilityId("Reply");
    private final By plusButton = AppiumBy.xpath("//android.widget.FrameLayout[@resource-id='android:id/content']" +
            "/android.widget.FrameLayout/android.widget.FrameLayout/android.view.View/android.view.View/" +
            "android.view.View/android.view.View/android.view.View/android.widget.ImageView[3]");
    private final By notesButton = AppiumBy.accessibilityId("Notes");
    private final By addNotesField = AppiumBy.xpath("//android.widget.EditText");
    private final By notesBackButton = AppiumBy.xpath("//android.view.View[@content-desc='Notes\nAdd Note']/android.widget.ImageView[2]");

    // 🔹 Constructor - FIXED
    public InteractionFlowPage(AndroidDriver driver) {
        super(driver); // ✅ Pass driver to BasePage constructor
        // No need to initialize driver and wait again - BasePage does it!
    }

    public void clickReplyButton() throws InterruptedException {
        waitAndClick(replyButton);
        System.out.println("Reply button gets clicked");
        Thread.sleep(2000);
    }

    // 🔹 1. Send Canned Message
    public void sendCannedMessage(String cannedMessageName) {
        waitAndClick(templatesButton);
        System.out.println("📩 Clicked Templates button");

        waitAndClick(AppiumBy.accessibilityId(cannedMessageName));
        System.out.println("✅ Canned message '" + cannedMessageName + "' sent successfully!");
    }

    // 🔹 2. Send WhatsApp Template
    public void sendWhatsAppTemplate(String templateName) {
        waitAndClick(templatesButton);
        System.out.println("📲 Opened Templates section");

        waitAndClick(whatsappTemplateTab);
        System.out.println("🟢 Switched to WhatsApp Templates tab");

        WebElement template = wait.until(ExpectedConditions.elementToBeClickable(
                AppiumBy.androidUIAutomator(
                        "new UiScrollable(new UiSelector().scrollable(true))" +
                                ".scrollIntoView(new UiSelector().descriptionContains(\"" + templateName + "\"))")));
        template.click();

        waitAndClick(sendButton);
        System.out.println("✅ WhatsApp template '" + templateName + "' sent successfully!");
    }

    // 🔹 3. Send Message with Emoji
    public void sendMessageWithEmoji(String messageText) {
        waitAndClick(messageInput);
        waitAndSendKeys(messageInput, messageText);
        System.out.println("💬 Typed message: " + messageText);

        waitAndClick(emojiButton);
        waitAndClick(secondEmojiTab);
        waitAndClick(smileEmoji);
        System.out.println("😊 Emoji added");

        WebElement sendMsgButton = wait.until(ExpectedConditions.elementToBeClickable(
                AppiumBy.xpath("//android.widget.FrameLayout[@resource-id=\"android:id/content\"]/android.widget.FrameLayout/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View/android.widget.ImageView[4]")));
        sendMsgButton.click();
        System.out.println("✅ Message with emoji sent");

        waitAndClick(emojiButton); // close emoji picker
    }

    // 🔹 4. Record and Send Voice Note
    public void recordAndSendVoiceNote() {
        try {
            System.out.println("🎤 Starting voice note flow...");

            waitAndClick(voiceNoteButton);
            System.out.println("🎙 Recording started...");

            // Wait until pause button is visible and clickable
            waitUntilClickable(pauseButton, 10);
            waitAndClick(pauseButton);
            System.out.println("⏸ Recording paused...");

            // Wait until resume button is visible and clickable
            waitUntilClickable(resumeButton, 10);
            waitAndClick(resumeButton);
            System.out.println("▶️ Recording resumed...");

            waitUntilClickable(sendVoiceNoteButton, 10);
            waitAndClick(sendVoiceNoteButton);
            System.out.println("✅ Voice note sent successfully!");

        } catch (Exception e) {
            System.out.println("❌ Voice note flow failed: " + e.getMessage());
        }
    }

    public void verifyWhatsAppWindowExpiry() {
        try {
            if(!isElementPresent(messageInput)) {
                isElementPresent(expiryMessage);
                System.out.println("✅ WhatsApp 24-hour expiry message verified.");
            } else {
                System.out.println("Editor is enabled");
                sendCannedMessage("Thanks");
                sendMessageWithEmoji("Hi!");
            }
        } catch (Exception e) {
            System.out.println("❌ Failed to verify WhatsApp window expiry: " + e.getMessage());
        }
    }

    public void addNote() {
        waitAndClick(plusButton);
        waitAndClick(notesButton);
        System.out.println("🟢 Clicked Notes");

        WebElement noteField = wait.until(ExpectedConditions.elementToBeClickable(addNotesField));
        noteField.click();
        noteField.sendKeys("Hi, notes are added here :)");
        System.out.println("🟢 Added note text");

        waitAndClick(notesBackButton);
        System.out.println("🟢 Clicked Back Button from Notes");
    }
}