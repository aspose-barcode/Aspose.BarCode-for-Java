package com.aspose.barcode.guide.recognition.performance;

import com.aspose.barcode.barcoderecognition.*;
import com.aspose.barcode.generation.*;
import com.aspose.barcode.guide.common.ExampleAssist;
import com.aspose.barcode.guide.common.LicenseAssist;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Random;

import static com.aspose.barcode.guide.common.ExampleAssist.checkOrCreateImage;

/**
 * Demonstrates how different QualitySettings presets trade off speed vs robustness.
 * Each test uses the same style of helpers as other examples in this section.
 */
public class RecognitionPresets {

    private static final String IMAGES_FOLDER =
            ExampleAssist.getOrCreateResourceFolderPath("recognition", "performance", "recognition_presets");

    @BeforeClass
    public void setUp() throws Exception {
        LicenseAssist.setupLicense();
        Files.createDirectories(Paths.get(IMAGES_FOLDER));
    }

    /**
     * Uses HighPerformance preset on a clean Code 128 image.
     * <p>
     * Intent: show the "fast path" preset that favors speed. On high-quality, unproblematic images
     * this preset recognizes quickly while keeping resource usage low.
     */
    @Test
    public void readCode128HighPerformanceOnClean() throws Exception {
        checkOrCreateImage(IMAGES_FOLDER, "code128_clean.png", this::generateCode128Clean);

        QualitySettings qs = QualitySettings.getHighPerformance();
        BarCodeReader reader = new BarCodeReader(path("code128_clean.png"), DecodeType.CODE_128);
        reader.setQualitySettings(qs);

        ExampleAssist.assertRecognized(reader, "code128_clean.png", 1, DecodeType.CODE_128);
    }

    /**
     * Uses MaxQuality preset on the same clean image.
     * <p>
     * Intent: show the "thorough" preset that enables more exhaustive processing (potentially slower)
     * but should still recognize easy images. This contrasts with HighPerformance on identical input.
     */
    @Test
    public void readCode128MaxQualityOnClean() throws Exception {
        String fileName = "code128_max_quality.png";
        checkOrCreateImage(IMAGES_FOLDER, fileName, this::generateCode128Clean);

        QualitySettings qs = QualitySettings.getMaxQuality();
        BarCodeReader reader = new BarCodeReader(path(fileName), DecodeType.CODE_128);
        reader.setQualitySettings(qs);

        ExampleAssist.assertRecognized(reader, fileName, 1, DecodeType.CODE_128);
    }

    /**
     * Uses HighQuality preset on a deliberately blurred/noisy Code 128 image.
     * <p>
     * Intent: demonstrate that quality-oriented presets (HighQuality) improve robustness on degraded inputs
     * by enabling slower deconvolution and additional recovery steps.
     */
    @Test
    public void readCode128HighQualityOnBlurredNoisy() throws Exception {
        String fileName = "code128_blur_noise.png";
        checkOrCreateImage(IMAGES_FOLDER, fileName, this::generateCode128BlurredNoisy);

        QualitySettings qs = QualitySettings.getHighQuality(); // enables SMALL XDimension, SLOW deconvolution, inverse, etc.
        BarCodeReader reader = new BarCodeReader(path(fileName), DecodeType.CODE_128);
        reader.setQualitySettings(qs);

        ExampleAssist.assertRecognized(reader, fileName, 1, DecodeType.CODE_128);
    }

    /**
     * Compares elapsed recognition time between HighPerformance and MaxQuality on the same image.
     * <p>
     * Intent: illustrate the speed vs quality tradeoff in practice. Both should recognize;
     * timings printed to console show relative cost. No strict timing assertions are made to keep
     * the test stable across environments.
     */
    @Test
    public void compareTimingHighPerformanceVsMaxQuality() throws Exception {
        String fileName = "compare_timing_high_quality.png";
        checkOrCreateImage(IMAGES_FOLDER, fileName, this::generateCode128Clean);

        // HighPerformance timing
        BarCodeReader fastReader = new BarCodeReader(path(fileName), DecodeType.CODE_128);
        fastReader.setQualitySettings(QualitySettings.getHighPerformance());
        long t1 = System.nanoTime();
        ExampleAssist.assertRecognized(fastReader, "HighPerformance timing", 1, DecodeType.CODE_128);
        long fastMs = (System.nanoTime() - t1) / 1_000_000;

        // MaxQuality timing
        BarCodeReader thoroughReader = new BarCodeReader(path(fileName), DecodeType.CODE_128);
        thoroughReader.setQualitySettings(QualitySettings.getMaxQuality());
        long t2 = System.nanoTime();
        ExampleAssist.assertRecognized(thoroughReader, "MaxQuality timing", 1, DecodeType.CODE_128);
        long thoroughMs = (System.nanoTime() - t2) / 1_000_000;

        System.out.println("[Speed vs Quality] HighPerformance: " + fastMs + " ms; MaxQuality: " + thoroughMs + " ms");
    }

    // -------------------------------------------------
    // Helpers
    // -------------------------------------------------

    private String path(String fileName) {
        return Paths.get(IMAGES_FOLDER, fileName).toString();
    }

    // Clean, easy Code 128 (fast path friendly)
    private void generateCode128Clean(String fullPath) throws IOException {
        BarcodeGenerator gen = new BarcodeGenerator(EncodeTypes.CODE_128, "SPEED-QUALITY-DEMO");
        gen.getParameters().getBarcode().getXDimension().setPixels(2);
        gen.getParameters().getBarcode().getBarHeight().setPixels(60);
        gen.save(fullPath, BarCodeImageFormat.PNG);
    }

    // Create a degraded Code 128 (blur + light noise) to motivate HighQuality/MaxQuality
    private void generateCode128BlurredNoisy(String fullPath) throws IOException {
        // Render larger to preserve bar geometry, then downscale for soft edges
        final int scale = 2; // 2x render

        BarcodeGenerator gen = new BarcodeGenerator(EncodeTypes.CODE_128, "ROBUSTNESS-CHECK-123");
        gen.getParameters().getBarcode().getXDimension().setPixels(3 * scale);  // effectively ~1.5 px after downscale
        gen.getParameters().getBarcode().getBarHeight().setPixels(70 * scale);

        int qx = 10 * (3 * scale); // quiet zone ≈ 10X
        gen.getParameters().getBarcode().getPadding().getLeft().setPixels(qx);
        gen.getParameters().getBarcode().getPadding().getRight().setPixels(qx);
        gen.getParameters().getBarcode().getPadding().getTop().setPixels(5 * (3 * scale));
        gen.getParameters().getBarcode().getPadding().getBottom().setPixels(5 * (3 * scale));

        String hi = fullPath + ".hi.png";
        gen.save(hi, BarCodeImageFormat.PNG);

        BufferedImage src = ImageIO.read(Paths.get(hi).toFile());
        BufferedImage blurred = boxBlur(src);             // light 3x3 blur
        addSaltPepperNoise(blurred, 0.004);               // ~0.4%

        // Downscale back by 2x (bilinear) to get soft but readable bars
        int w = blurred.getWidth() / scale, h = blurred.getHeight() / scale;
        BufferedImage out = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = out.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.drawImage(blurred, 0, 0, w, h, null);
        g2.dispose();

        ImageIO.write(out, "PNG", Paths.get(fullPath).toFile());
        Files.deleteIfExists(Paths.get(hi));
    }



    // Naive 3x3 box blur for demo purposes
    private static BufferedImage boxBlur(BufferedImage img) {
        int w = img.getWidth(), h = img.getHeight();
        BufferedImage out = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        int[] dx = {-1, 0, 1};
        int[] dy = {-1, 0, 1};
        for (int y = 0; y < h; y++) {
            for (int x = 0; x < w; x++) {
                long a = 0, r = 0, g = 0, b = 0, n = 0;
                for (int yy : dy) {
                    int py = y + yy;
                    if (py < 0 || py >= h) continue;
                    for (int xx : dx) {
                        int px = x + xx;
                        if (px < 0 || px >= w) continue;
                        int rgba = img.getRGB(px, py);
                        a += (rgba >>> 24) & 0xFF;
                        r += (rgba >>> 16) & 0xFF;
                        g += (rgba >>> 8) & 0xFF;
                        b += rgba & 0xFF;
                        n++;
                    }
                }
                int A = (int)(a / n), R = (int)(r / n), G = (int)(g / n), B = (int)(b / n);
                out.setRGB(x, y, (A << 24) | (R << 16) | (G << 8) | B);
            }
        }
        return out;
    }

    // Simple salt & pepper noise injector (ratio in [0..1])
    private static void addSaltPepperNoise(BufferedImage img, double ratio) {
        Random rnd = new Random(12345);
        int w = img.getWidth(), h = img.getHeight();
        int total = (int)(w * h * ratio);
        for (int i = 0; i < total; i++) {
            int x = rnd.nextInt(w);
            int y = rnd.nextInt(h);
            // toggle to black or white randomly
            int rgb = rnd.nextBoolean() ? 0xFFFFFFFF : 0xFF000000;
            img.setRGB(x, y, rgb);
        }
    }
}
