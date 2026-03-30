package com.contegris.intelliinbox.tests;


import com.contegris.intelliinbox.base.BaseTest;
import com.contegris.intelliinbox.pages.*;
import com.contegris.intelliinbox.utils.MediaFiles;
import org.testng.annotations.Test;
import com.contegris.intelliinbox.utils.InputData;
import com.contegris.intelliinbox.utils.MediaFiles;
import com.contegris.intelliinbox.enums.Channel;

public class FBpost extends BaseTest
{

    @Test
    public void testInboundFacebookFlow() throws InterruptedException {
        // driver is already initialized by @BeforeMethod in BaseTest

        // Pass only driver to each page
        InteractionAcceptancePage inbox = new InteractionAcceptancePage(driver);
        InteractionFlowPage chat = new InteractionFlowPage(driver);
        AttachmentModalPage2 attach = new AttachmentModalPage2(driver);
        InteractionClosurePage closure = new InteractionClosurePage(driver);

        inbox.openTeamInbox();
        inbox.selectChannel(Channel.FACEBOOK_Post);
        inbox.clickFirstCard();
        inbox.clickAssignOrJoinIfAvailable();

        chat.clickReplyButton();
        chat.sendCannedMessage(InputData.cannedMessage);
        chat.clickReplyButton();
        chat.sendMessageWithEmoji(InputData.textMessage);

        chat.clickReplyButton();
        attach.uploadImage(MediaFiles.Image.jpg_Supported);

        chat.clickReplyButton();
        closure.addNote();
        closure.dismissBottomSheet();
        closure.openPlusMenu();
        closure.addWorkCode(InputData.workCode);
        closure.closeBottomSheet();
        closure.endInteraction(InputData.endingWorkcode);

        inbox.openMyInbox();
    }

    // No need for @BeforeClass and @AfterClass - BaseTest handles it
}