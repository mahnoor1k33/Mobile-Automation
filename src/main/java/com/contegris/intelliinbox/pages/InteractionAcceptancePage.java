package com.contegris.intelliinbox.pages;

import com.contegris.intelliinbox.base.BasePage;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.contegris.intelliinbox.enums.Channel;

import java.time.Duration;

public class InteractionAcceptancePage extends BasePage {

    // ---------- LOCATORS ---------- //
//    private final By teamInboxTab = AppiumBy.xpath("//android.widget.FrameLayout[@resource-id=\"android:id/content\"]/android.widget.FrameLayout/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View/android.widget.ImageView[2]");
//    private final By myInboxTab = AppiumBy.xpath("//android.widget.FrameLayout[@resource-id=\"android:id/content\"]/android.widget.FrameLayout/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View/android.widget.ImageView[1]");
    private final By myInboxTab = AppiumBy.androidUIAutomator("new UiSelector().className(\"android.widget.ImageView\").instance(5)");
    private final By teamInboxTab = AppiumBy.androidUIAutomator("new UiSelector().className(\"android.widget.ImageView\").instance(6)");
    private final By joinButton = AppiumBy.accessibilityId("Join");
    private final By assignButton = AppiumBy.accessibilityId("Assign");
    private final By acceptButton = AppiumBy.accessibilityId("New Call request from \\nMahnoor QA\\nDefaultIVR\\ndefaultQueue\\nAccept");
    private final By rejectButton =  AppiumBy.accessibilityId("Reject");
    private final By incomingRequestCard = AppiumBy.androidUIAutomator("new UiSelector().descriptionContains(\"New\")");

    public InteractionAcceptancePage(AndroidDriver driver) {
        super(driver);
    }

    // ---------- TEAM INBOX SECTION ---------- //

    // Opens the Team Inbox tab
    // Used Bounds here for the time being until the developer adds the id for it!
    // Not using xpath, cause it crashes the app due to deep links
    public void openMyInbox() {
        tapByCoordinates(88, 1440);
        System.out.println("✓ Opened My Inbox");
    }

    public void openTeamInbox() {
        tapByCoordinates(222, 1441);
        System.out.println("✓ Opened Team Inbox");
    }

    // This will specifically click on the Channel Filters displaying at the Top
    public void selectChannel(Channel channel) throws InterruptedException {

        // Step 1: Reset — scrollable(true) correctly matches the channel tab bar
        driver.findElement(AppiumBy.androidUIAutomator(
                "new UiScrollable(new UiSelector().scrollable(true))" +
                        ".setAsHorizontalList().scrollToBeginning(5)"
        ));
        Thread.sleep(300);

        // Step 2: Scroll into view only
        driver.findElement(AppiumBy.androidUIAutomator(
                "new UiScrollable(new UiSelector().scrollable(true))" +
                        ".setAsHorizontalList().setMaxSearchSwipes(10)" +
                        ".scrollIntoView(new UiSelector().descriptionContains(\"" + channel.getLabel() + "\"))"
        ));
        Thread.sleep(300);

        // Step 3: Click independently using UIAutomator descriptionContains
        driver.findElement(AppiumBy.androidUIAutomator(
                "new UiSelector().descriptionContains(\"" + channel.getLabel() + "\")"
        )).click();

        System.out.println("✓ Selected channel: " + channel.getLabel());
    }

    // Clicks the first available card based on customer name
    public void clickFirstCard() {
        WebElement firstCard = wait.until(ExpectedConditions.elementToBeClickable(
                AppiumBy.xpath(
                        "(//android.view.View[@clickable='true' and contains(@content-desc, '/')])[1]"
                )
        ));
        firstCard.click();
        System.out.println("✓ Clicked on first card");
    }

    public void clickFirstCardWebChat() {
        WebElement firstCard = wait.until(ExpectedConditions.elementToBeClickable(
                AppiumBy.xpath(
                        "(//android.view.View[contains(@content-desc, 'Web Client')])[1]")
        ));
        firstCard.click();
        System.out.println("Clicked on the First Card");
        // Need to check this Last message Out!
    }

    // Clicks either 'Join' or 'Assign' button based on availability
    private boolean isElementDisplayed(By locator, WebDriverWait wait) {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void clickAssignOrJoinIfAvailable() {
        WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(3));

        if (isElementDisplayed(assignButton, shortWait)) {
            driver.findElement(assignButton).click();
            System.out.println("Assigned interaction successfully.");
            return;
        }

        if (isElementDisplayed(joinButton, shortWait)) {
            driver.findElement(joinButton).click();
            System.out.println("Joined interaction successfully.");
            return;
        }

        System.out.println("Interaction already assigned. Skipping.");
    }

    // ---------- INBOUND INTERACTION SECTION ---------- //

    //  Waits for and accepts an incoming interaction
    public void acceptIncomingInteraction() {
        if (isIncomingRequestVisible()) {
            try {
                wait.until(ExpectedConditions.elementToBeClickable(acceptButton)).click();
                System.out.println("Accepted the incoming interaction.");
            } catch (Exception e) {
                System.out.println("Accept button not found, trying fallback...");
                clickCardContaining("Accept");
            }
        } else {
            System.out.println("No incoming interaction visible.");
        }
    }

    // Waits for and rejects an incoming interaction
    public void rejectIncomingInteraction() {
        if (isIncomingRequestVisible()) {
            try {
                wait.until(ExpectedConditions.elementToBeClickable(rejectButton)).click();
                System.out.println("Rejected the incoming interaction.");
            } catch (Exception e) {
                System.out.println("Reject button not found, trying fallback...");
                clickCardContaining("Reject");
            }
        } else {
            System.out.println("No incoming interaction visible.");
        }
    }

    // Checks if any incoming interaction card is visible
    public boolean isIncomingRequestVisible() {
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(incomingRequestCard));
            System.out.println("Incoming request detected.");
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // Fallback: clicks a card containing the given keyword
    private void clickCardContaining(String keyword) {
        try {
            WebElement card = wait.until(ExpectedConditions.elementToBeClickable(
                    AppiumBy.androidUIAutomator(
                            "new UiSelector().descriptionContains(\"" + keyword + "\")")));
            card.click();
            System.out.println("Clicked card containing '" + keyword + "'.");
        } catch (Exception e) {
            System.out.println("Element still not clickable. Trying coordinate tap...");
            new TouchAction(driver)
                    .tap(PointOption.point(287, 564))
                    .perform();
        }
    }
}
