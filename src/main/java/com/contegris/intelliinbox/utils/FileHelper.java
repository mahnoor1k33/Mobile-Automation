package com.contegris.intelliinbox.utils;

import io.appium.java_client.android.AndroidDriver;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;

public class FileHelper {

    private static String getDeviceId() {
        return ConfigReader.get("udid");
    }

    public static void pushFilesToDevice(AndroidDriver driver) {

        if (areFilesOnDevice()) {
            System.out.println("✅ Files already on device. Skipping.");
            return;
        }

        System.out.println("📁 Pushing test files to device...");

        createDeviceFolder();

        File folder = new File(MediaFiles.Path.LOCAL);
        File[] files = folder.listFiles();

        if (files == null || files.length == 0) {
            System.out.println("⚠️ No files found in: " + MediaFiles.Path.LOCAL);
            return;
        }

        int successCount = 0;
        for (File file : files) {
            if (file.isFile()) {
                System.out.println("   📄 " + file.getName() + " (" + file.length() + " bytes)");
                boolean success = pushFileWithAdb(file);
                if (success) successCount++;
            }
        }

        refreshMediaScanner();
        System.out.println("✅ Pushed " + successCount + "/" + files.length + " files!");
    }

    /**
     * Push file using ADB (NOT driver.pushFile - that corrupts files!)
     */
    private static boolean pushFileWithAdb(File file) {
        try {
            String localPath = file.getAbsolutePath();
            String devicePath = MediaFiles.Path.DEVICE + file.getName();

            String command = "adb -s " + getDeviceId() + " push \"" + localPath + "\" \"" + devicePath + "\"";

            Process process = Runtime.getRuntime().exec(command);
            process.waitFor();

            if (process.exitValue() == 0) {
                System.out.println("   ✓ " + file.getName());
                return true;
            } else {
                System.out.println("   ✗ " + file.getName());
                return false;
            }

        } catch (Exception e) {
            System.out.println("   ✗ " + file.getName() + " - " + e.getMessage());
            return false;
        }
    }

    private static boolean areFilesOnDevice() {
        try {
            String command = "adb -s " + getDeviceId() + " shell ls " + MediaFiles.Path.DEVICE + "IMG_png_1MB.png";
            Process process = Runtime.getRuntime().exec(command);
            process.waitFor();

            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(process.getInputStream())
            );
            String result = reader.readLine();

            boolean exists = result != null
                    && !result.contains("No such file")
                    && !result.contains("error");

            if (exists) {
                System.out.println("📂 Found existing files on device");
            }

            return exists;

        } catch (Exception e) {
            return false;
        }
    }

    private static void createDeviceFolder() {
        try {
            String command = "adb -s " + getDeviceId() + " shell mkdir -p " + MediaFiles.Path.DEVICE;
            Runtime.getRuntime().exec(command).waitFor();
            System.out.println("📂 Created folder: " + MediaFiles.Path.DEVICE);
        } catch (Exception e) {
            System.out.println("⚠️ Could not create folder");
        }
    }

    private static void refreshMediaScanner() {
        try {
            String command = "adb -s " + getDeviceId() +
                    " shell am broadcast -a android.intent.action.MEDIA_SCANNER_SCAN_FILE -d file://" +
                    MediaFiles.Path.DEVICE;
            Runtime.getRuntime().exec(command).waitFor();
            System.out.println("🔄 Media scanner refreshed");
        } catch (Exception e) {
            // ignore
        }
    }

    public static void forceRePush(AndroidDriver driver) {
        try {
            String command = "adb -s " + getDeviceId() + " shell rm -rf " + MediaFiles.Path.DEVICE;
            Runtime.getRuntime().exec(command).waitFor();
            System.out.println("🗑️ Deleted old files");
        } catch (Exception e) {
            // ignore
        }
        pushFilesToDevice(driver);
    }
}