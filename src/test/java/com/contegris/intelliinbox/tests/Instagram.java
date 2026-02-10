package com.contegris.intelliinbox.tests;

import com.contegris.intelliinbox.base.BaseTest;
import com.contegris.intelliinbox.pages.*;
import org.testng.annotations.Test;

public class Instagram extends BaseTest { // ✅ Extends BaseTest, not BasePage

    @Test
    public void testInboundInstagramFlow() throws InterruptedException {
        // driver is already initialized by @BeforeMethod in BaseTest

        // ✅ Pass only driver to each page
        InteractionAcceptancePage inbox = new InteractionAcceptancePage(driver);
        InteractionFlowPage chat = new InteractionFlowPage(driver);
        AttachmentModalPage attach = new AttachmentModalPage(driver);
        InteractionClosurePage closure = new InteractionClosurePage(driver);

        inbox.openTeamInbox();
        inbox.clickFirstCard("Mahnoor Shoukat");
        inbox.clickAssignOrJoinIfAvailable();

        chat.clickReplyButton();
        chat.sendCannedMessage("Thanks");
        chat.clickReplyButton();
        chat.sendMessageWithEmoji("Hi, how may I help you today?");

        chat.clickReplyButton();
        attach.clickPlusButton();
        attach.uploadAttachment("image");

        chat.clickReplyButton();
        closure.addNote();
        closure.addWorkCode("Hot Lead");
        closure.closeBottomSheet();
        closure.endInteraction("Contact Center");
    }

    // No need for @BeforeClass and @AfterClass - BaseTest handles it
}