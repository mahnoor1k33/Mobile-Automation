package com.contegris.intelliinbox.tests;

import com.contegris.intelliinbox.base.BaseTest;
import com.contegris.intelliinbox.pages.AttachmentModalPage2;
import com.contegris.intelliinbox.pages.LoginPage;
import com.contegris.intelliinbox.utils.MediaFiles;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AttachmentTest extends BaseTest {

    private AttachmentModalPage2 attachmentModal;
    private LoginPage loginPage;

    @BeforeMethod
    public void initPages() {
        attachmentModal = new AttachmentModalPage2(driver);
        loginPage = new LoginPage(driver);
    }

    // ============================================================
    // 🔹 IMAGE TESTS
    // ============================================================

    @Test
    public void testUploadPngImage() {
        // Navigate to a conversation first (add your navigation code)
        attachmentModal.uploadImage(MediaFiles.Image.png_Supported);
    }

    @Test
    public void testUploadJpgImage() {
        attachmentModal.uploadImage(MediaFiles.Image.jpg_Supported);
    }

    // ============================================================
    // 🔹 VIDEO TESTS
    // ============================================================

    @Test
    public void testUploadMp4Video() {
        attachmentModal.uploadVideo(MediaFiles.Video.mp4_Supported);
    }

    // ============================================================
    // 🔹 AUDIO TESTS
    // ============================================================

    @Test
    public void testUploadM4aAudio() {
        attachmentModal.uploadAudio(MediaFiles.Audio.m4a_Supported);
    }

    // ============================================================
    // 🔹 DOCUMENT TESTS
    // ============================================================

    @Test
    public void testUploadPdfDocument() {
        attachmentModal.uploadDocument(MediaFiles.Document.pdf_Supported);
    }
}
