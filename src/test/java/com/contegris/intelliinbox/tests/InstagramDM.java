package com.contegris.intelliinbox.tests;

import com.contegris.intelliinbox.base.BaseTest;
import com.contegris.intelliinbox.pages.*;
import com.contegris.intelliinbox.utils.InputData;
import org.testng.annotations.Test;
import com.contegris.intelliinbox.utils.MediaFiles;
import com.contegris.intelliinbox.enums.Channel;

public class InstagramDM extends BaseTest { // Extends BaseTest, not BasePage

    @Test
    public void testInboundInstaDMflow() throws InterruptedException {

        // Pass only driver to each page - they initialize their own wait
        InteractionAcceptancePage inbox = new InteractionAcceptancePage(driver);
        InteractionFlowPage chat = new InteractionFlowPage(driver);
        AttachmentModalPage2 attach = new AttachmentModalPage2(driver);
        InteractionClosurePage closure = new InteractionClosurePage(driver);


        inbox.openTeamInbox();
        inbox.selectChannel(Channel.INSTAGRAM_DM);
        inbox.clickFirstCard();
        inbox.clickAssignOrJoinIfAvailable();

        chat.sendCannedMessage(InputData.cannedMessage);
        chat.sendMessageWithEmoji(InputData.textMessage);
        // chat.recordAndSendVoiceNote();

        attach.uploadImage(MediaFiles.Image.jpg_Supported);
        attach.uploadVideo(MediaFiles.Video.mp4_Supported);
        attach.uploadAudio(MediaFiles.Audio.m4a_Supported);

        closure.addNote();
        closure.dismissBottomSheet();
        closure.openPlusMenu();
        closure.addWorkCode(InputData.workCode);
        closure.closeBottomSheet();
        closure.endInteraction(InputData.endingWorkcode);

        inbox.openMyInbox();
    }

    // No @BeforeClass or @AfterClass is needed - BaseTest handles it with @BeforeMethod/@AfterMethod
}