package io.quantumqa.reporting;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;

public class ReportLogger {
    public static void logPass(String message) {
        ExtentTestManager.getTest().log(Status.PASS, MarkupHelper.createLabel(message, ExtentColor.GREEN));
    }

    public static void logFail(String message) {
        ExtentTestManager.getTest().log(Status.FAIL, MarkupHelper.createLabel(message, ExtentColor.RED));
    }

    public static void logInfo(String message) {
        ExtentTestManager.getTest().log(Status.INFO, message);
    }

    public static void logWarning(String message) {
        ExtentTestManager.getTest().log(Status.WARNING, MarkupHelper.createLabel(message, ExtentColor.ORANGE));
    }
}