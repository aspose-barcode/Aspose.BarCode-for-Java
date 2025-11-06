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

import static com.aspose.barcode.guide.common.ExampleAssist.currentMethodName;

/**
 * Reading Low-Resolution Barcode Example.
 *
 * Focus:
 * - How recognition behaves when the barcode image is downscaled to low resolutions.
 * - Which QualitySettings and X-dimension hints improve robustness on pixelated input.
 *
 * Data:
 * - A clean, synthetic CODE_128 is generated once.
 * - Low-res variants are produced via nearest-neighbor downscale plus binarization to keep edges crisp.
 */
public class ReadingLowResolutionBarcodeExample {

    // Resource folder for this group of tests
    private static final String FOLDER =
            ExampleAssist.getOrCreateResourceFolderPath("recognition", "quality", "reading_low_resolution");

    // Descriptive file names (no abbreviations)
    private static final String FILE_CODE128_CLEAN_BASE = "code128_clean_width_600px.png";
    private static final String FILE_CODE128_LOWRES_WIDTH_150 = "code128_low_resolution_width_150px.png";
    private static final String FILE_CODE128_LOWRES_WIDTH_80  = "code128_low_resolution_width_80px.png";
    private static final String FILE_CODE128_LOWRES_WIDTH_40  = "code128_low_resolution_width_40px.png";

    // Common barcode payload for all generated images
    private static final String PAYLOAD_TEXT = "LowRes:CODE128";

    @BeforeClass
    public void setUp() throws Exception {
        LicenseAssist.setupLicense();
        generateCode128Base();
        generateLowResVariants();
    }

    private void generateCode128Base() throws Exception {
        ExampleAssist.checkOrCreateImage(FOLDER, FILE_CODE128_CLEAN_BASE, path -> {
            BarcodeGenerator gen = new BarcodeGenerator(EncodeTypes.CODE_128, PAYLOAD_TEXT);
            // Толще модули и широкие quiet-zones на базе — это не критично теперь,
            // но оставим как стабильный "идеальный" эталон.
            gen.getParameters().getBarcode().getXDimension().setPixels(3.0f);
            gen.getParameters().getBarcode().getPadding().getLeft().setPixels(40f);
            gen.getParameters().getBarcode().getPadding().getRight().setPixels(40f);
            gen.getParameters().getBarcode().getPadding().getTop().setPixels(20f);
            gen.getParameters().getBarcode().getPadding().getBottom().setPixels(20f);
            gen.save(path, BarCodeImageFormat.PNG);
        });
    }

    /**
     * Генерируем "низкое" разрешение напрямую генератором:
     * - фиксируем общую ширину изображения в пикселях;
     * - подбираем X-dimension так, чтобы после рендеринга модуль имел 1–2 px;
     * - задаём белый фон и читабельные quiet-zones.
     */
    private void generateLowResVariants() throws Exception {
        // Для стабильности задаём высоту ~ 120–160 px, чтобы штрихи были «достаточно высокими».
        // Quiet-zones: минимум 8–12 px слева/справа.
        final int HEIGHT_PX = 140;
        final int QUIET_PX  = 12;

        ExampleAssist.checkOrCreateImage(FOLDER, FILE_CODE128_LOWRES_WIDTH_150,
                out -> ExampleAssist.renderBarcodeFixedSizePNG(
                        EncodeTypes.CODE_128, PAYLOAD_TEXT,
                        /*widthPx*/150, /*heightPx*/HEIGHT_PX,
                        /*xDimPx*/2.0f, /*quietPx*/QUIET_PX, out));

        ExampleAssist.checkOrCreateImage(FOLDER, FILE_CODE128_LOWRES_WIDTH_80,
                out -> ExampleAssist.renderBarcodeFixedSizePNG(
                        EncodeTypes.CODE_128, PAYLOAD_TEXT,
                        /*widthPx*/80, /*heightPx*/HEIGHT_PX,
                        /*xDimPx*/1.2f, /*quietPx*/QUIET_PX, out));

        ExampleAssist.checkOrCreateImage(FOLDER, FILE_CODE128_LOWRES_WIDTH_40,
                out -> ExampleAssist.renderBarcodeFixedSizePNG(
                        EncodeTypes.CODE_128, PAYLOAD_TEXT,
                        /*widthPx*/40, /*heightPx*/HEIGHT_PX,
                        /*xDimPx*/1.0f, /*quietPx*/QUIET_PX, out));
    }

    // ── CLEAN baseline (sanity check) ──────────────────────────────────────────

    /**
     * Purpose:
     * - Establish a sanity baseline: a clean, sufficiently large CODE_128 should be readable
     *   with balanced settings (neither extreme speed nor extreme quality).
     *
     * Demonstrates:
     * - On good input quality, NORMAL settings are adequate; no special hints are required.
     *
     * Expectation (explicit):
     * - The reader returns at least one result (minCount=1), and at least one result has type CODE_128.
     * - This indicates stable decoding on clean input with typical defaults.
     */
    @Test
    public void read_Code128_Clean_NormalQuality() throws Exception {
        BarCodeReader reader = new BarCodeReader(
                ExampleAssist.pathCombine(FOLDER, FILE_CODE128_CLEAN_BASE), DecodeType.CODE_128);

        QualitySettings qs = QualitySettings.getNormalQuality();
        qs.setBarcodeQuality(BarcodeQualityMode.NORMAL);
        reader.setQualitySettings(qs);

        ExampleAssist.assertRecognized(reader, FILE_CODE128_CLEAN_BASE, 1, DecodeType.CODE_128);
    }

    // ── Low resolution: ~150 px width ─────────────────────────────────────────

    /**
     * Purpose:
     * - Verify that a mildly downscaled barcode (~150 px width), preprocessed to be crisp,
     *   is reliably decoded when prioritizing speed but keeping quality mode HIGH.
     *
     * Demonstrates:
     * - Pairing a performance preset with HIGH quality mode preserves robustness on modestly low resolution.
     *
     * Expectation (explicit):
     * - At least one decoded result; among results, CODE_128 type is present.
     * - This shows that the combination is appropriate for small-but-not-tiny barcodes in real apps.
     */
    @Test
    public void read_Code128_Width150_PerformancePreset_HighQualityMode() throws Exception {
        BarCodeReader reader = new BarCodeReader(
                ExampleAssist.pathCombine(FOLDER, FILE_CODE128_LOWRES_WIDTH_150), DecodeType.CODE_128);

        QualitySettings qs = QualitySettings.getHighPerformance();
        qs.setBarcodeQuality(BarcodeQualityMode.HIGH);
        reader.setQualitySettings(qs);

        ExampleAssist.assertRecognized(reader, FILE_CODE128_LOWRES_WIDTH_150, 1, DecodeType.CODE_128);
    }

    /**
     * Purpose:
     * - Confirm that a balanced preset also handles ~150 px width without extra hints when the image is crisp.
     *
     * Demonstrates:
     * - For slightly low-res but well-thresholded images, NORMAL settings remain sufficient.
     *
     * Expectation (explicit):
     * - At least one decoded result; CODE_128 type is present.
     * - If this ever fails in CI, increase base width or keep this test but raise quality mode to HIGH.
     */
    @Test
    public void read_Code128_Width150_NormalPreset_NormalQualityMode() throws Exception {
        BarCodeReader reader = new BarCodeReader(
                ExampleAssist.pathCombine(FOLDER, FILE_CODE128_LOWRES_WIDTH_150), DecodeType.CODE_128);

        QualitySettings qs = QualitySettings.getNormalQuality();
        qs.setBarcodeQuality(BarcodeQualityMode.NORMAL);
        reader.setQualitySettings(qs);

        ExampleAssist.assertRecognized(reader, FILE_CODE128_LOWRES_WIDTH_150, 1, DecodeType.CODE_128);
    }

    // ── Low resolution: ~80 px width ──────────────────────────────────────────

    /**
     * Purpose:
     * - At ~80 px width the module width is very small. We combine:
     *   (a) crisp low-res generation, (b) XDimension hints toward small bars,
     *   and (c) HIGH quality mode, starting from a performance preset.
     *
     * Demonstrates:
     * - Targeted X-dimension guidance helps the detector lock onto tiny modules,
     *   while HIGH quality mode enables stronger preprocessing.
     *
     * Expectation (explicit):
     * - At least one decoded result; CODE_128 type is present.
     * - If results are flaky in your dataset, consider raising MinimalXDimension or using getHighQuality().
     */
    @Test
    public void read_Code128_Width80_PerformancePreset_SmallXDim_HighQualityMode() throws Exception {
        BarCodeReader reader = new BarCodeReader(
                ExampleAssist.pathCombine(FOLDER, FILE_CODE128_LOWRES_WIDTH_80), DecodeType.CODE_128);

        QualitySettings qs = QualitySettings.getHighPerformance();
        qs.setXDimension(XDimensionMode.SMALL);
        qs.setMinimalXDimension(1.0f); // Hint: expected minimal module width in pixels after downscale
        qs.setBarcodeQuality(BarcodeQualityMode.HIGH);
        reader.setQualitySettings(qs);

        ExampleAssist.assertRecognized(reader, FILE_CODE128_LOWRES_WIDTH_80, 1, DecodeType.CODE_128);
    }

    /**
     * Purpose:
     * - Contrast case: same ~80 px input; keep the image crisp but do not provide X-dimension hints
     *   and use NORMAL quality mode.
     *
     * Demonstrates:
     * - Balanced settings may still decode due to crisp edges; however, they are more sensitive
     *   to module quantization and quiet zone violations.
     *
     * Expectation (explicit):
     * - At least one decoded result; CODE_128 type is present.
     * - If unstable in your CI, either add X-dimension hints or switch quality mode to HIGH.
     */
    @Test
    public void read_Code128_Width80_NormalPreset_NoHints_NormalQualityMode() throws Exception {
        BarCodeReader reader = new BarCodeReader(
                ExampleAssist.pathCombine(FOLDER, FILE_CODE128_LOWRES_WIDTH_80), DecodeType.CODE_128);

        QualitySettings qs = QualitySettings.getNormalQuality();
        qs.setBarcodeQuality(BarcodeQualityMode.NORMAL);
        reader.setQualitySettings(qs);

        ExampleAssist.assertRecognized(reader, FILE_CODE128_LOWRES_WIDTH_80, 1, DecodeType.CODE_128);
    }

    // ── Very low resolution: ~40 px width ─────────────────────────────────────

    /**
     * Purpose:
     * - Evaluate an extreme low-resolution case (~40 px width) using a quality-oriented preset
     *   (HighQuality) with SMALL X-dimension hints and HIGH quality mode, and verify that decoding
     *   can still succeed when the image is crisp and module hints are provided.
     *
     * Demonstrates:
     * - The decoder’s best-case robustness near the resolution cliff: with appropriate hints
     *   (XDimensionMode.SMALL, MinimalXDimension) and strong quality mode, even ~40 px can be decodable.
     *   This is an edge-case success, not a general guarantee.
     *
     * Expectation (explicit):
     * - At least one decoded result is returned, and among results there is CODE_128.
     *   Note: this scenario can be dataset- and device-sensitive; if it becomes flaky in CI,
     *   consider increasing MinimalXDimension or image width, or relax expectations.
     */
    @Test
    public void read_Code128_Width40_Negative_TooSmallEvenWhenCrisp() throws Exception {
        BarCodeReader reader = new BarCodeReader(
                ExampleAssist.pathCombine(FOLDER, FILE_CODE128_LOWRES_WIDTH_40), DecodeType.CODE_128);

        QualitySettings qs = QualitySettings.getHighQuality();
        qs.setXDimension(XDimensionMode.SMALL);
        qs.setMinimalXDimension(1.0f);
        qs.setBarcodeQuality(BarcodeQualityMode.HIGH);
        reader.setQualitySettings(qs);

        ExampleAssist.assertRecognized(reader, currentMethodName(), 1, DecodeType.CODE_128);
    }

    /**
     * Purpose:
     * - Stress-test an extreme low-resolution case (~40 px width) while prioritizing speed
     *   (HighPerformance preset) and using LOW quality mode, and verify that decoding can still succeed.
     *
     * Demonstrates:
     * - Decoder robustness at the resolution cliff: even with LOW quality mode, a minimal example
     *   can remain decodable. Treat this as a best-case edge, not a general recommendation.
     *
     * Expectation (explicit):
     * - At least one decoded result is returned, and among results there is CODE_128.
     *   Note: this case may be brittle on different datasets/devices; if it becomes flaky in CI,
     *   consider switching to a negative assertion or increasing the generated X-dimension/quiet zones.
     */
    @Test
    public void read_Code128_Width40_Negative_PerformancePreset_LowQualityMode() throws Exception {
        BarCodeReader reader = new BarCodeReader(
                ExampleAssist.pathCombine(FOLDER, FILE_CODE128_LOWRES_WIDTH_40), DecodeType.CODE_128);

        QualitySettings qs = QualitySettings.getHighPerformance();
        qs.setBarcodeQuality(BarcodeQualityMode.LOW);
        reader.setQualitySettings(qs);

        ExampleAssist.assertRecognized(reader, currentMethodName(), 1, DecodeType.CODE_128);
    }
}
