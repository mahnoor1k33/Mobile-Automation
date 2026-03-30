package com.contegris.intelliinbox.tests;

import com.contegris.intelliinbox.base.BaseTest;
import com.contegris.intelliinbox.pages.*;
import org.testng.annotations.Test;
import com.contegris.intelliinbox.enums.Channel;

public class Instapost extends BaseTest
{

    @Test
    public void testInboundInstagramPostFlow() throws InterruptedException {
        // driver is already initialized by @BeforeMethod in BaseTest

        // Pass only driver to each page
        InteractionAcceptancePage inbox = new InteractionAcceptancePage(driver);
        InteractionFlowPage chat = new InteractionFlowPage(driver);
        AttachmentModalPage attach = new AttachmentModalPage(driver);
        InteractionClosurePage closure = new InteractionClosurePage(driver);

        inbox.openTeamInbox();
        inbox.selectChannel(Channel.INSTAGRAM_Post);
        inbox.clickFirstCard();
        inbox.clickAssignOrJoinIfAvailable();

        chat.clickReplyButton();
        chat.sendCannedMessage("Thanks");
        chat.clickReplyButton();
        chat.sendMessageWithEmoji("Hi, how may I help you today?");


        chat.clickReplyButton();
        closure.addNote();
        closure.dismissBottomSheet();
        closure.openPlusMenu();
        closure.addWorkCode("Hot Lead");
        closure.closeBottomSheet();
        closure.endInteraction("Contact Center");

        inbox.openMyInbox();
    }

    // No need for @BeforeClass and @AfterClass - BaseTest handles it
}