package com.contegris.intelliinbox.utils;

public class MediaFiles {

    // ==================== IMAGES ====================
    public static class Image {
        public static final String jpg_Supported = "IMG_jpg_1MB.jpg";
        public static final String jpg_size_exceeds_5MB = "IMG_jpg_5MB.jpg";
        public static final String jpeg_Supported = "IMG_jpeg_2MB.jpeg";
        public static final String png_Supported = "IMG_png_1MB.png";
        public static final String gif_Supported = "gif_40KB.gif";
    }

    // ==================== VIDEOS ====================
    public static class Video {
        public static final String mp4_Supported = "VID_mp4_1MB.mp4";
        public static final String mp4_size_exceeds_5MB = "VID_mp4_5MB.mp4";
        public static final String Threegpp_Supported = "VID_3gpp_1MB.3gp";
        public static final String avi_Supported = "VID_avi_KB.avi";
    }

    // ==================== AUDIO ====================
    public static class Audio {
        public static final String mp3_Supported = "AUD_mp3_KB.mp3";
        public static final String m4a_Supported = "AUD_m4a_KB.m4a";
        public static final String aac_Supported = "AUD_aac_KB.aac";
        public static final String wav_Supported = "AUD_wav_KB.wav";
        public static final String ogg_Supported = "AUD_ogg_KB.ogg";
    }

    // ==================== DOCUMENTS ====================
    public static class Document {
        public static final String pdf_Supported = "DOC_pdf_5MB.pdf";
        public static final String xlsx_Supported = "DOC_xlsx_KB.xlsx";
        public static final String txt_Supported = "DOC_txt_KB.txt";
    }

    // ==================== PATHS ====================
    public static class Path {
        // Local path (in your project)
        public static final String LOCAL = "src/test/resources/mediaFiles/";

        // Device path (where files will be pushed)
        public static final String DEVICE = "/sdcard/Download/TestMedia/";
    }
}