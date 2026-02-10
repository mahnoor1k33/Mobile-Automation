package com.contegris.intelliinbox.utils;

public class MediaFiles {

    // ==================== IMAGES ====================
    public static class Image {
        public static final String JPG_SUPPORTED = "IMG_jpg_0.5MB";
        public static final String JPG_SIZE_EXCEEDS = "IMG_jpg_5MB";
        public static final String JPEG_SUPPORTED = "IMG_jpeg_0.8MB";
        public static final String PNG_SUPPORTED = "IMG_png_1MB";
        public static final String UNSUPPORTED_GIF = "IMG_gif_1MB";
    }

    // ==================== VIDEOS ====================
    public static class Video {
        public static final String MP4_SUPPORTED = "VID_mp4_2MB";
        public static final String MP4_SIZE_EXCEEDS = "VID_mp4_50MB";
        public static final String THREEGPP_SUPPORTED = "VID_3gpp_1MB";
    }

    // ==================== AUDIO ====================
    public static class Audio {
        public static final String MP3_SUPPORTED = "AUD_mp3_1MB";
        public static final String MP3_SIZE_EXCEEDS = "AUD_mp3_20MB";
        public static final String M4A_SUPPORTED = "AUD_m4a_2MB";
    }

    // ==================== DOCUMENTS ====================
    public static class Document {
        public static final String PDF_SUPPORTED = "DOC_pdf_1MB";
        public static final String PDF_SIZE_EXCEEDS = "DOC_pdf_15MB";
        public static final String XLS_SUPPORTED = "DOC_xls_0.5MB";
    }
}