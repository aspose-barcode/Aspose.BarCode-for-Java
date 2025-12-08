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
 * under two conditions: (1) a clean, synthetic CODE_128 image and (2) the same image
 * degraded with additive Gaussian noise. Noise is produced by ExampleAssist.addGaussianNoise(...).
 *
 * Key ideas:
 * - Clean inputs should be robust to most quality modes.
 * - Noisy inputs typically benefit from higher quality modes, which may enable
 *   heavier preprocessing/filters at the cost of performance.
 * - Presets (getHighPerformance / getNormalQuality / getHighQuality) can be combined
 *   with targeted overrides (e.g., X-dimension and BarcodeQualityMode) to fine-tune behavior.
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
     * under "ideal" conditions. We expect all modes to recognize it reliably.
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
     * (stdDev ≈ 12). This simulates low SNR and stresses the recognizer,
     * revealing differences between quality modes and presets.
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

    // --- CLEAN image tests ---

    /**
     * Purpose:
     * - Validate that on a clean CODE_128, a fast preset (getHighPerformance) with
     *   BarcodeQualityMode.HIGH still recognizes reliably.
     *
     * Demonstrates:
     * - Even performance-oriented presets can be paired with HIGH quality mode
     *   to keep recognition robust on clean data.
     *
     * Expectation:
     * - At least 1 result of type CODE_128; stable, quick recognition.
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
     * - Show that a balanced preset (getNormalQuality) with NORMAL quality mode
     *   is sufficient for clean images.
     *
     * Demonstrates:
     * - Middle-ground configuration (latency vs quality) still yields correct reads
     *   when input quality is not a problem.
     *
     * Expectation:
     * - At least 1 CODE_128 result; comparable accuracy to other modes on clean input.
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
     * - Confirm that even with LOW quality mode, a clean CODE_128 remains readable.
     *
     * Demonstrates:
     * - Clean inputs provide a wide operating margin; lower quality settings are
     *   still capable of successful recognition and may reduce compute cost.
     *
     * Expectation:
     * - At least 1 CODE_128 result; this test sets a lower bound for "how low" we can go on clean data.
     */
    @Test
    public void read_Code128_Clean_BarcodeQuality_LOW() throws Exception {
        String file = "code128_clean.png";
        BarCodeReader reader =
                new BarCodeReader(ExampleAssist.pathCombine(FOLDER, file), DecodeType.CODE_128);

        QualitySettings qs = QualitySettings.getHighQuality(); // preset base
        qs.setBarcodeQuality(BarcodeQualityMode.LOW);          // explicit low-quality override
        reader.setQualitySettings(qs);

        ExampleAssist.assertRecognized(reader, file, 1, DecodeType.CODE_128);
    }

    // --- NOISY image tests ---

    /**
     * Purpose:
     * - Evaluate recognition on a noisy image when using a speed-oriented preset
     *   (getHighPerformance) but forcing HIGH quality mode.
     *
     * Demonstrates:
     * - On degraded inputs, raising the quality mode can compensate for a
     *   performance-biased preset by enabling stronger denoising/filters.
     *
     * Expectation:
     * - At least 1 CODE_128 result; proves that HIGH quality mode helps survive noise.
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
     * - Stress the recognizer by pairing a quality-oriented preset (getHighQuality)
     *   with a LOW quality mode on a noisy image.
     *
     * Demonstrates:
     * - Lowering the quality mode may harm robustness on degraded data. This test
     *   sets a contrast to the previous one and can expose sensitivity to the quality mode.
     *
     * Expectation:
     * - Still attempt to read at least 1 CODE_128 result. If your environment is stricter,
     *   you can convert this into a "negative" test (expect zero results) to show the cliff.
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

    // --- Preset + targeted overrides ---

    /**
     * Purpose:
     * - Showcase that presets can be fine-tuned via targeted overrides for
     *   specific scenarios (e.g., small module width).
     *
     * Demonstrates:
     * - Starting from a performance preset and explicitly setting:
     *   * XDimensionMode.SMALL + MinimalXDimension(1.0f) to bias the detector
     *     toward small bar widths (useful for tiny prints/screenshots).
     *   * BarcodeQualityMode.LOW to simulate pushing speed further while still
     *     retaining enough sensitivity due to proper X-dimension hints.
     *
     * Expectation:
     * - At least 1 CODE_128 result on the clean image, proving that accurate
     *   geometric hints (X-dimension) can offset a lower quality mode when the
     *   signal is otherwise clean.
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
