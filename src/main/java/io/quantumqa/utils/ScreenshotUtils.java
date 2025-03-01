package io.quantumqa.utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.util.Base64;

public class ScreenshotUtils {

    public static String captureScreenshot(WebDriver driver, String screenshotName) {
        String screenshotPath = "target/screenshots/" + screenshotName + ".png";
        try {
            File sourceFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            File destinationFile = new File(screenshotPath);
            FileUtils.copyFile(sourceFile, destinationFile);
            return screenshotPath;
        } catch (IOException e) {
            throw new RuntimeException("Failed to capture screenshot: " + e.getMessage());
        }
    }

    public static String captureScreenshotAsBase64(WebDriver driver) {
        try {
            return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
        } catch (Exception e) {
            throw new RuntimeException("Failed to capture screenshot as Base64: " + e.getMessage());
        }
    }
}