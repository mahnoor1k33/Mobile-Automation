package com.contegris.intelliinbox.tests;

import com.contegris.intelliinbox.base.BaseTest;
import com.contegris.intelliinbox.pages.*;
import org.testng.annotations.Test;

public class WhatsApp extends BaseTest { // Extends BaseTest, not BasePage

    @Test(priority = 2, description = "Test inbound WhatsApp interaction flow")
    public void testInboundWhatsappFlow() throws InterruptedException {
        // Pass only driver to each page - they initialize their own wait
        InteractionAcceptancePage inbox = new InteractionAcceptancePage(driver);
        InteractionFlowPage chat = new InteractionFlowPage(driver);
        AttachmentModalPage attach = new AttachmentModalPage(driver);
        InteractionClosurePage closure = new InteractionClosurePage(driver);

        inbox.openTeamInbox();
        inbox.clickFirstCard("Hey");
        inbox.clickAssignOrJoinIfAvailable();

        chat.sendCannedMessage("Thanks");
        chat.sendWhatsAppTemplate("we_are_back_contegris_support");
        chat.sendMessageWithEmoji("Hi, how may I help you today?");
        // chat.recordAndSendVoiceNote();

        attach.clickPlusButton();
        attach.uploadAttachment("image");
        attach.uploadAttachment("audio");
        attach.uploadAttachment("video");
        attach.uploadAttachment("document");

        closure.addNote();
        closure.openPlusMenu();
        closure.addWorkCode("Hot Lead");
        closure.closeBottomSheet();
        closure.endInteraction("Contact Center");
    }

    @Test(priority = 1, description = "Test outbound WhatsApp interaction flow")
    public void testOutboundWhatsappFlow() throws InterruptedException {
        // Pass only driver to each page
        OutboundModalPage out = new OutboundModalPage(driver);
        InteractionAcceptancePage inbox = new InteractionAcceptancePage(driver);
        InteractionFlowPage chat = new InteractionFlowPage(driver);
        AttachmentModalPage attach = new AttachmentModalPage(driver);
        InteractionClosurePage closure = new InteractionClosurePage(driver);

        out.makeWhatsappOutbound("923057204466");
        inbox.clickAssignOrJoinIfAvailable();

        chat.sendCannedMessage("Thanks");
        chat.sendWhatsAppTemplate("we_are_back_contegris_support");
        chat.sendMessageWithEmoji("Hi, how may I help you today?");
        // chat.recordAndSendVoiceNote();

        attach.clickPlusButton();
        attach.uploadAttachment("file");

        closure.addNote();
        closure.addWorkCode("Hot Lead");
        closure.closeBottomSheet();
        closure.endInteraction("Contact Center");
    }

    // No @BeforeClass or @AfterClass is needed - BaseTest handles it with @BeforeMethod/@AfterMethod
}