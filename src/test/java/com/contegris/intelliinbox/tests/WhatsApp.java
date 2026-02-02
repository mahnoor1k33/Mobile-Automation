package com.contegris.intelliinbox.tests;

import com.contegris.intelliinbox.base.BasePage;
import com.contegris.intelliinbox.pages.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class WhatsApp extends BasePage {

    @BeforeClass
    public void setupTest() throws Exception {
        setUp();
    }

    @Test
    public void testInboundWhatsappFlow() throws InterruptedException {
        InteractionAcceptancePage inbox = new InteractionAcceptancePage(driver, wait);
        InteractionFlowPage chat = new InteractionFlowPage(driver, wait);
        AttachmentModalPage attach = new AttachmentModalPage(driver, wait);
        InteractionClosurePage closure = new InteractionClosurePage(getDriver());

        inbox.openTeamInbox();
        inbox.clickFirstCard("Mahnoor");
        inbox.clickAssignOrJoin();
        chat.sendCannedMessage("Thanks");
        chat.sendWhatsAppTemplate("we_are_back_contegris_support");
        chat.sendMessageWithEmoji("Hi, how may I help you today?");
        // chat.recordAndSendVoiceNote();

        attach.clickPlusButton();
        attach.uploadAttachment("image");
        closure.addNote();
        closure.openPlusMenu();
        closure.addWorkCode("Hot Lead");
        closure.closeBottomSheet();
        closure.endInteraction("Contact Center");
    }

    @Test
    public void testOutboundWhatsappFlow() throws InterruptedException {
        OutboundModalPage out = new OutboundModalPage(driver);
        InteractionAcceptancePage inbox = new InteractionAcceptancePage(driver, wait);
        InteractionFlowPage chat = new InteractionFlowPage(driver, wait);
        AttachmentModalPage attach = new AttachmentModalPage(driver, wait);
        InteractionClosurePage closure = new InteractionClosurePage(getDriver());

        out.makeWhatsappOutbound("923057204466");
        // inbox.clickAssignOrJoin();
//        chat.sendCannedMessage("Thanks");
//        chat.sendWhatsAppTemplate("we_are_back_contegris_support");
//        chat.sendMessageWithEmoji("Hi, how may I help you today?");
        // chat.recordAndSendVoiceNote();

//        attach.clickPlusButton();
//        attach.uploadAttachment("image");
        closure.addNote();
        closure.addWorkCode("Hot Lead");
        closure.closeBottomSheet();
        closure.endInteraction("Contact Center");
    }

    @AfterClass
    public void tearDownTest() {
        tearDown();
    }
}
