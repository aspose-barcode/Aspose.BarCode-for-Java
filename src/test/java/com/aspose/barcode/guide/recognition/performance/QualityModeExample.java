package com.aspose.barcode.guide.recognition.performance;

import com.aspose.barcode.barcoderecognition.BarCodeReader;
import com.aspose.barcode.barcoderecognition.DecodeType;
import com.aspose.barcode.barcoderecognition.QualitySettings;
import com.aspose.barcode.barcoderecognition.BarcodeQualityMode;
import com.aspose.barcode.barcoderecognition.XDimensionMode;
import com.aspose.barcode.generation.BarCodeImageFormat;
import com.aspose.barcode.generation.BarcodeGenerator;
import com.aspose.barcode.generation.EncodeTypes;
import com.aspose.barcode.guide.common.ExampleAssist;
import com.aspose.barcode.guide.common.LicenseAssist;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * Demonstrates the effect of BarcodeQualityMode (HIGH / NORMAL / LOW) on recognition
 * under two conditions: (1) a clean synthetic CODE_128 image and (2) the same image
 * degraded with additive Gaussian noise (produced by ExampleAssist.addGaussianNoise(...)).
 *
 * Semantics:
 * - BarcodeQualityMode.HIGH    → the engine assumes that barcodes are high quality
 *                                and can follow a lighter / more optimistic recognition path.
 * - BarcodeQualityMode.NORMAL  → the engine assumes medium quality and uses a balanced path.
 * - BarcodeQualityMode.LOW     → the engine assumes low quality and may apply more intensive
 *                                processing for damaged or noisy barcodes.
 *
 * This enum describes the expected quality of input images. It is orthogonal to
 * QualitySettings presets:
 * - getHighPerformance() → speed-oriented preset
 * - getNormalQuality()   → balanced preset for most scenarios
 * - getHighQuality()     → preset for low-quality barcodes
 *
 * In practice you:
 * - choose a preset according to the overall difficulty/throughput of your task
 * - set BarcodeQualityMode according to the expected quality of the incoming images.
 */
public class QualityModeExample {

    // Test resource folder is created once and reused.
    private static final String FOLDER =
            ExampleAssist.getOrCreateResourceFolderPath("recognition", "quality", "barcode_quality_mode");

    @BeforeClass
    public void setUp() throws Exception {
        // Load license for reproducible recognition quality and behavior.
        LicenseAssist.setupLicense();

        // Generate test fixtures once if they don't exist:
        // 1) clean CODE_128 image
        // 2) the same image degraded with Gaussian noise
        generateCode128Base();
        generateCode128Noisy();
    }

    /**
     * Creates a clean baseline CODE_128 image to test how different quality modes behave
     * when the barcode is rendered under ideal conditions.
     *
     * Expected behavior:
     * - All BarcodeQualityMode values (HIGH / NORMAL / LOW) should be able to read this image.
     * - Differences are mostly about internal recognition strategy, not the final result.
     */
    private void generateCode128Base() throws Exception {
        String file = "code128_clean.png";
        ExampleAssist.checkOrCreateImage(FOLDER, file, path -> {
            BarcodeGenerator gen =
                    new BarcodeGenerator(EncodeTypes.CODE_128, "QualitySettings:BarcodeQuality");
            gen.save(path, BarCodeImageFormat.PNG);
        });
    }

    /**
     * Produces a noisy variant of the baseline image by adding Gaussian noise
     * (stdDev ≈ 12). This simulates low signal-to-noise ratio and stresses the recognizer.
     *
     * Expected behavior:
     * - BarcodeQualityMode.LOW is usually the safest choice for this kind of input.
     * - BarcodeQualityMode.HIGH assumes high-quality images and may operate closer
     *   to the robustness limit on degraded data.
     */
    private void generateCode128Noisy() throws Exception {
        String src = "code128_clean.png";
        String noisy = "code128_noisy.png";
        ExampleAssist.checkOrCreateImage(FOLDER, noisy, outPath -> {
            String inPath = ExampleAssist.pathCombine(FOLDER, src);
            // Adds Gaussian noise (stdDev ~12). Increase/decrease for your CI if needed.
            ExampleAssist.addGaussianNoise(inPath, outPath, 12.0);
        });
    }

    // --- CLEAN image tests ---------------------------------------------------

    /**
     * Purpose:
     * - Demonstrate the typical configuration for high-quality images:
     *   a speed-oriented preset (getHighPerformance) combined with
     *   BarcodeQualityMode.HIGH to reflect that the input is expected to be clean.
     *
     * Demonstrates:
     * - When the image quality is good, you can tell the engine that barcodes are
     *   high quality so it can follow an optimistic recognition path.
     *
     * Expectation:
     * - At least 1 result of type CODE_128; fast, stable recognition on clean input.
     */
    @Test
    public void read_Code128_Clean_BarcodeQuality_HIGH() throws Exception {
        String file = "code128_clean.png";
        BarCodeReader reader = new BarCodeReader(ExampleAssist.pathCombine(FOLDER, file), DecodeType.CODE_128);

        QualitySettings qs = QualitySettings.getHighPerformance();
        qs.setBarcodeQuality(BarcodeQualityMode.HIGH);
        reader.setQualitySettings(qs);

        ExampleAssist.assertRecognized(reader, file, 1, DecodeType.CODE_128);
    }

    /**
     * Purpose:
     * - Show that a balanced preset (getNormalQuality) with BarcodeQualityMode.NORMAL
     *   is sufficient for most clean images.
     *
     * Demonstrates:
     * - Middle-ground configuration (latency vs robustness) that matches
     *   a "typical" scan quality level.
     *
     * Expectation:
     * - At least 1 CODE_128 result; behavior comparable to HIGH mode on clean input.
     */
    @Test
    public void read_Code128_Clean_BarcodeQuality_NORMAL() throws Exception {
        String file = "code128_clean.png";
        BarCodeReader reader =
                new BarCodeReader(ExampleAssist.pathCombine(FOLDER, file), DecodeType.CODE_128);

        QualitySettings qs = QualitySettings.getNormalQuality();
        qs.setBarcodeQuality(BarcodeQualityMode.NORMAL);
        reader.setQualitySettings(qs);

        ExampleAssist.assertRecognized(reader, file, 1, DecodeType.CODE_128);
    }

    /**
     * Purpose:
     * - Confirm that even if you mark a clean CODE_128 as "low-quality"
     *   (BarcodeQualityMode.LOW), the barcode is still readable.
     *
     * Demonstrates:
     * - Clean inputs provide a wide safety margin; using a mode designed for
     *   low-quality images does not break recognition but may be an overkill.
     *
     * Expectation:
     * - At least 1 CODE_128 result; this test shows that LOW mode is safe even
     *   when the actual image quality is better than expected.
     */
    @Test
    public void read_Code128_Clean_BarcodeQuality_LOW() throws Exception {
        String file = "code128_clean.png";
        BarCodeReader reader =
                new BarCodeReader(ExampleAssist.pathCombine(FOLDER, file), DecodeType.CODE_128);

        QualitySettings qs = QualitySettings.getHighQuality(); // preset oriented to harder inputs
        qs.setBarcodeQuality(BarcodeQualityMode.LOW);          // assume barcodes may be low quality
        reader.setQualitySettings(qs);

        ExampleAssist.assertRecognized(reader, file, 1, DecodeType.CODE_128);
    }

    // --- NOISY image tests ---------------------------------------------------

    /**
     * Purpose:
     * - Evaluate recognition on a noisy image when the engine is still told
     *   that barcodes are of HIGH quality.
     *
     * Demonstrates:
     * - A "mismatched" configuration: using a performance-oriented preset and
     *   BarcodeQualityMode.HIGH on degraded data may work, but it operates closer
     *   to the robustness boundary than LOW mode.
     *
     * Expectation:
     * - At least 1 CODE_128 result; this shows that noisy inputs can sometimes
     *   be decoded even when the engine assumes high quality.
     */
    @Test
    public void read_Code128_Noisy_BarcodeQuality_HIGH() throws Exception {
        String file = "code128_noisy.png";
        BarCodeReader reader =
                new BarCodeReader(ExampleAssist.pathCombine(FOLDER, file), DecodeType.CODE_128);

        QualitySettings qs = QualitySettings.getHighPerformance();
        qs.setBarcodeQuality(BarcodeQualityMode.HIGH);
        reader.setQualitySettings(qs);

        ExampleAssist.assertRecognized(reader, file, 1, DecodeType.CODE_128);
    }

    /**
     * Purpose:
     * - Demonstrate the recommended configuration for degraded images:
     *   use a more robust preset (getHighQuality) and set BarcodeQualityMode.LOW
     *   to explicitly indicate that barcodes are low-quality.
     *
     * Demonstrates:
     * - For noisy or partially corrupted inputs, LOW mode usually provides
     *   the most tolerant behavior, at the cost of extra processing.
     *
     * Expectation:
     * - At least 1 CODE_128 result; this path should be more stable
     *   than HIGH mode on difficult images.
     */
    @Test
    public void read_Code128_Noisy_BarcodeQuality_LOW() throws Exception {
        String file = "code128_noisy.png";
        BarCodeReader reader =
                new BarCodeReader(ExampleAssist.pathCombine(FOLDER, file), DecodeType.CODE_128);

        QualitySettings qs = QualitySettings.getHighQuality();
        qs.setBarcodeQuality(BarcodeQualityMode.LOW);
        reader.setQualitySettings(qs);

        ExampleAssist.assertRecognized(reader, file, 1, DecodeType.CODE_128);
    }

    // --- Preset + targeted overrides ----------------------------------------

    /**
     * Purpose:
     * - Showcase that presets can be fine-tuned via targeted overrides for
     *   specific scenarios (for example, small module width and low-quality input).
     *
     * Demonstrates:
     * - Starting from a performance preset and explicitly setting:
     *   * XDimensionMode.SMALL + MinimalXDimension(1.0f) to bias the detector
     *     toward small bar widths (useful for tiny prints or screenshots).
     *   * BarcodeQualityMode.LOW to tell the engine that symbols may be low quality
     *     and that extra effort is acceptable.
     *
     * Expectation:
     * - At least 1 CODE_128 result on the clean image; the same configuration
     *   is a good candidate for small and potentially noisy barcodes in real projects.
     */
    @Test
    public void read_Code128_Clean_PresetWithOverrides_SmallLowQuality() throws Exception {
        String file = "code128_clean.png";
        BarCodeReader reader =
                new BarCodeReader(ExampleAssist.pathCombine(FOLDER, file), DecodeType.CODE_128);

        QualitySettings qs = QualitySettings.getHighPerformance();
        qs.setXDimension(XDimensionMode.SMALL);
        qs.setMinimalXDimension(1.0f);
        qs.setBarcodeQuality(BarcodeQualityMode.LOW);
        reader.setQualitySettings(qs);

        ExampleAssist.assertRecognized(reader, file, 1, DecodeType.CODE_128);
    }
}
