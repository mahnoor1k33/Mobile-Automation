package com.contegris.intelliinbox.pages;

import com.contegris.intelliinbox.base.BasePage;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class OutboundModalPage extends BasePage {

    // -------------------- Locators --------------------
    private final By plusButton = AppiumBy.xpath("//android.widget.Button");
    private final By phoneButton = AppiumBy.accessibilityId("Phone");
    private final By whatsAppButton = AppiumBy.accessibilityId("Whatsapp");
    private final By emailButton = AppiumBy.accessibilityId("Email");
    private final By queueDropdown = AppiumBy.className("android.widget.Button");
    private final By defaultQueue = AppiumBy.accessibilityId("defaultQueue");
    private final By callDialButton = AppiumBy.xpath("//android.widget.Button[2]");
    private final By whatsappDialButton = AppiumBy.xpath("//android.widget.FrameLayout[@resource-id=\"android:id/content\"]/android.widget.FrameLayout/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View/android.widget.ImageView");
    private final By muteButton = AppiumBy.accessibilityId("Mute");
    private final By holdButton = AppiumBy.accessibilityId("Hold");
    private final By speakerButton = AppiumBy.accessibilityId("Speaker");
    private final By endButton = AppiumBy.accessibilityId("End");
    private final By infoButton = AppiumBy.accessibilityId("Info");
    private final By closeIcon = AppiumBy.xpath("//android.widget.ImageView[1]");
    private final By workCodeButton = AppiumBy.accessibilityId("Work code");
    private final By complaintCategory = AppiumBy.xpath("//android.view.View[@content-desc=\"Complaint\nZ Category\"]");
    private final By closeButton = AppiumBy.accessibilityId("Close");
    private final By endInteraction = AppiumBy.accessibilityId("End Interaction");
    private final By presenceIcon = By.xpath("//android.widget.ImageView[@clickable='true' and @content-desc]");

    public OutboundModalPage(AndroidDriver driver) {
        super(driver);
    }

    // -------------------- Actions --------------------

    public void makeOutboundCall(String number) throws InterruptedException {
        waitAndClick(plusButton);
        waitAndClick(phoneButton);
        waitAndClick(queueDropdown);
        waitAndClick(defaultQueue);

        // Dial number digits one by one
        for (char digit : number.toCharArray()) {
            String accessibilityId = mapDigitToAccessibilityId(digit);
            waitAndClick(AppiumBy.accessibilityId(accessibilityId));
            Thread.sleep(100);
        }

        waitAndClick(callDialButton);
        Thread.sleep(10000);
    }

    public void muteUnmuteHoldSpeakerFlow() throws InterruptedException {
        waitAndClick(muteButton);
        waitAndClick(holdButton);
        waitAndClick(speakerButton);
    }

    public void endCallFlow() throws InterruptedException {
        waitAndClick(endButton);
        waitAndClick(infoButton);
        Thread.sleep(2000);
        waitAndClick(closeIcon);

        waitAndClick(workCodeButton);
        waitAndClick(complaintCategory);
        waitAndClick(closeButton);
        waitAndClick(endInteraction);
    }
    public void makeWhatsappOutbound(String number) throws InterruptedException {
        waitAndClick(plusButton);
        waitAndClick(whatsAppButton);
        waitAndClick(queueDropdown);
        waitAndClick(defaultQueue);

        // Dial number digits one by one
        for (char digit : number.toCharArray()) {
            String accessibilityId = mapDigitToAccessibilityId(digit);
            waitAndClick(AppiumBy.accessibilityId(accessibilityId));
            Thread.sleep(100);
        }

        waitAndClick(whatsappDialButton);
        Thread.sleep(10000);
    }

    private String mapDigitToAccessibilityId(char digit) {
        return switch (digit) {
            case '0' -> "0";
            case '1' -> "1";
            case '2' -> "2\nABC";
            case '3' -> "3\nDEF";
            case '4' -> "4\nGHI";
            case '5' -> "5\nJKL";
            case '6' -> "6\nMNO";
            case '7' -> "7\nPQRS";
            case '8' -> "8\nTUV";
            case '9' -> "9\nWXYZ";
            default -> throw new IllegalArgumentException("Invalid digit: " + digit);
        };
    }
}
