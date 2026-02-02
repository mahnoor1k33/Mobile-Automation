package com.contegris.intelliinbox.tests;

import com.contegris.intelliinbox.base.BasePage;
import com.contegris.intelliinbox.pages.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Instagram extends BasePage {

    @BeforeClass
    public void setupTest() throws Exception {
        setUp();
    }

    @Test
    public void testInboundInstagramFlow() throws InterruptedException {
        InteractionAcceptancePage inbox = new InteractionAcceptancePage(driver, wait);
        InteractionFlowPage chat = new InteractionFlowPage(driver, wait);
        AttachmentModalPage attach = new AttachmentModalPage(driver, wait);
        InteractionClosurePage closure = new InteractionClosurePage(getDriver());

        inbox.openTeamInbox();
        inbox.clickFirstCard("Mahnoor Shoukat");
        inbox.clickAssignOrJoin();
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

    @AfterClass
    public void tearDownTest() {
        tearDown();
    }
}
