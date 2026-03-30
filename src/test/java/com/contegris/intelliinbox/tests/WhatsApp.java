package com.contegris.intelliinbox.tests;

import com.contegris.intelliinbox.base.BaseTest;
import com.contegris.intelliinbox.enums.Channel;
import com.contegris.intelliinbox.pages.*;
import com.contegris.intelliinbox.utils.InputData;
import org.testng.annotations.Test;
import com.contegris.intelliinbox.utils.MediaFiles;

public class WhatsApp extends BaseTest
{

    @Test(priority = 1, description = "Test inbound WhatsApp interaction flow")
    public void testInboundWhatsappFlow() throws InterruptedException {
        // Pass only driver to each page - they initialize their own wait
        InteractionAcceptancePage inbox = new InteractionAcceptancePage(driver);
        InteractionFlowPage chat = new InteractionFlowPage(driver);
        AttachmentModalPage2 attach = new AttachmentModalPage2(driver);
        InteractionClosurePage closure = new InteractionClosurePage(driver);

        inbox.openTeamInbox();
        inbox.selectChannel(Channel.WHATSAPP);
        inbox.clickFirstCard();
        inbox.clickAssignOrJoinIfAvailable();

        chat.sendMessageWithEmoji(InputData.textMessage);
        chat.sendCannedMessage(InputData.cannedMessage);
        chat.sendWhatsAppTemplate(InputData.templateMessage);
        // chat.recordAndSendVoiceNote();

        attach.uploadImage(MediaFiles.Image.jpg_Supported);
        attach.uploadVideo(MediaFiles.Video.mp4_Supported);
        attach.uploadAudio(MediaFiles.Audio.m4a_Supported);
        attach.uploadDocument(MediaFiles.Document.pdf_Supported);

        closure.addNote();
        closure.dismissBottomSheet();
        closure.openPlusMenu();
        closure.addWorkCode(InputData.workCode);
        closure.closeBottomSheet();
        closure.endInteraction(InputData.endingWorkcode);

        inbox.openMyInbox();
    }

    @Test(priority = 2, enabled = false, description = "Test outbound WhatsApp interaction flow")
    public void testOutboundWhatsappFlow() throws InterruptedException {
        // Pass only driver to each page
        OutboundModalPage out = new OutboundModalPage(driver);
        InteractionAcceptancePage inbox = new InteractionAcceptancePage(driver);
        InteractionFlowPage chat = new InteractionFlowPage(driver);
        AttachmentModalPage2 attach = new AttachmentModalPage2(driver);
        InteractionClosurePage closure = new InteractionClosurePage(driver);

        out.makeWhatsappOutbound(InputData.whatsappNumber);
        inbox.clickAssignOrJoinIfAvailable();

        chat.sendMessageWithEmoji(InputData.textMessage);
        chat.sendCannedMessage(InputData.cannedMessage);
        chat.sendWhatsAppTemplate(InputData.templateMessage);
        // chat.recordAndSendVoiceNote();

        attach.uploadImage(MediaFiles.Image.jpg_Supported);
        attach.uploadVideo(MediaFiles.Video.mp4_Supported);
        attach.uploadAudio(MediaFiles.Audio.m4a_Supported);
        attach.uploadAudio(MediaFiles.Document.pdf_Supported);

        closure.addNote();
        closure.dismissBottomSheet();
        closure.openPlusMenu();
        closure.addWorkCode(InputData.workCode);
        closure.closeBottomSheet();
        closure.endInteraction(InputData.endingWorkcode);
    }

    // No @BeforeClass or @AfterClass is needed - BaseTest handles it with @BeforeMethod/@AfterMethod
}