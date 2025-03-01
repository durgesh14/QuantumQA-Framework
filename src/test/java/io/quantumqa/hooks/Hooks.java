package io.quantumqa.hooks;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.quantumqa.core.DriverFactory;
import io.quantumqa.reporting.ExtentManager;
import io.quantumqa.reporting.ExtentTestManager;
import io.quantumqa.utils.ScreenRecordingUtil;
import io.quantumqa.utils.ScreenshotUtils;

import java.awt.*;
import java.io.IOException;

public class Hooks {

    @Before
    public void setup(Scenario scenario) throws IOException, AWTException {

        DriverFactory.getDriver().manage().window().maximize();

        // Initialize ExtentTest for the scenario
        ExtentTestManager.setTest(ExtentManager.getInstance().createTest(scenario.getName()));
        // Start screen recording
        ScreenRecordingUtil.startRecording(scenario.getName());
    }

    @After
    public void tearDown(Scenario scenario) throws IOException {
        try {
            // Log scenario status in ExtentReports
            if (scenario.isFailed()) {
                String screenshotBase64 = ScreenshotUtils.captureScreenshotAsBase64(DriverFactory.getDriver());
                ExtentTestManager.getTest().log(Status.FAIL, "Scenario failed: " + scenario.getName(),
                        MediaEntityBuilder.createScreenCaptureFromBase64String(screenshotBase64).build());
            } else {
                ExtentTestManager.getTest().log(Status.PASS, "Scenario passed: " + scenario.getName());
            }
        } finally {
            // Stop screen recording
            ScreenRecordingUtil.stopRecording();

            // Quit WebDriver
            DriverFactory.quitDriver();

            // End the ExtentTest
            ExtentTestManager.unload();

            // Flush ExtentReports to write the report to the file
            ExtentManager.getInstance().flush();
        }
    }
}