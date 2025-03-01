package io.quantumqa.utils;
import org.monte.media.Format;
import org.monte.media.Registry;
import org.monte.media.math.Rational;
import org.monte.screenrecorder.ScreenRecorder;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.monte.media.AudioFormatKeys.*;
import static org.monte.media.VideoFormatKeys.*;

public class ScreenRecordingUtil {

    private static final ThreadLocal<ScreenRecorder> screenRecorder = new ThreadLocal<>();

    public static void startRecording(String testName) throws IOException, AWTException {
        if (Boolean.parseBoolean(ConfigReader.getProperty("screen.recording.enabled"))) {
            File file = new File("target/screen-recordings/");
            if (!file.exists()) {
                file.mkdirs();
            }

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH.mm.ss");
            String timestamp = dateFormat.format(new Date());

            GraphicsConfiguration gc = GraphicsEnvironment
                    .getLocalGraphicsEnvironment()
                    .getDefaultScreenDevice()
                    .getDefaultConfiguration();

            ScreenRecorder recorder = new ScreenRecorder(
                    gc,
                    new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()),
                    new Format(MediaTypeKey, MediaType.FILE, MimeTypeKey, MIME_AVI),
                    new Format(MediaTypeKey, MediaType.VIDEO, EncodingKey, ENCODING_AVI_TECHSMITH_SCREEN_CAPTURE,
                            CompressorNameKey, ENCODING_AVI_TECHSMITH_SCREEN_CAPTURE,
                            DepthKey, 24, FrameRateKey, Rational.valueOf(15),
                            QualityKey, 1.0f,
                            KeyFrameIntervalKey, 15 * 60),
                    new Format(MediaTypeKey, MediaType.VIDEO, EncodingKey, "black", FrameRateKey, Rational.valueOf(30)),
                    null,
                    new File(file, testName + "_" + timestamp + ".avi")
            );

            recorder.start();
            screenRecorder.set(recorder);

        }
    }

    public static void stopRecording() throws IOException {
        ScreenRecorder recorder = screenRecorder.get();
        if (recorder != null) {
            recorder.stop();
            screenRecorder.remove(); // Clean up the ThreadLocal to prevent memory leaks
        }
    }
}