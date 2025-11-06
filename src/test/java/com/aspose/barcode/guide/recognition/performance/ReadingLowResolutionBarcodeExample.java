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

    /**
     * Creates a clean baseline CODE_128 image at comfortable resolution (~600 px wide).
     * Rationale:
     * - A high-quality source isolates the variable under test (downscale) and avoids generation artifacts.
     * Success criteria:
     * - The image itself is not asserted here; subsequent tests validate recognition.
     */
    private void generateCode128Base() throws Exception {
        ExampleAssist.checkOrCreateImage(FOLDER, FILE_CODE128_CLEAN_BASE, path -> {
            BarcodeGenerator gen = new BarcodeGenerator(EncodeTypes.CODE_128, PAYLOAD_TEXT);
            // Default generator settings are fine; saved as PNG (lossless).
            gen.save(path, BarCodeImageFormat.PNG);
        });
    }

    /**
     * Produces low-resolution variants using nearest-neighbor downscale followed by Otsu binarization.
     * Why:
     * - Nearest-neighbor preserves module boundaries; Otsu binarization re-asserts hard black/white edges
     *   after resampling, which improves decode reliability on small images.
     */
    private void generateLowResVariants() throws Exception {
        final String src = ExampleAssist.pathCombine(FOLDER, FILE_CODE128_CLEAN_BASE);

        ExampleAssist.checkOrCreateImage(FOLDER, FILE_CODE128_LOWRES_WIDTH_150,
                out -> ExampleAssist.downscaleNearestCrisp(src, out, /*targetWidthPx*/150));

        ExampleAssist.checkOrCreateImage(FOLDER, FILE_CODE128_LOWRES_WIDTH_80,
                out -> ExampleAssist.downscaleNearestCrisp(src, out, /*targetWidthPx*/80));

        ExampleAssist.checkOrCreateImage(FOLDER, FILE_CODE128_LOWRES_WIDTH_40,
                out -> ExampleAssist.downscaleNearestCrisp(src, out, /*targetWidthPx*/40));
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
     * - Document the "failure cliff": at ~40 px width bars may collapse to 1–2 px,
     *   making decoding unreliable even with strong quality settings and crisping.
     *
     * Demonstrates:
     * - A practical lower bound in resolution; negative test clarifies expectations for tiny inputs.
     *
     * Expectation (explicit):
     * - No decoded results (minCount=0). If you need a positive at 40 px, generate from larger base
     *   with a thicker X-dimension, or avoid such low widths in production.
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

        ExampleAssist.assertNotRecognized(reader, FILE_CODE128_LOWRES_WIDTH_40);
    }

    /**
     * Purpose:
     * - Show that prioritizing speed with LOW quality mode on a tiny input further reduces robustness.
     *
     * Demonstrates:
     * - Performance preset + LOW quality mode is not suitable for extreme low-res cases.
     *
     * Expectation (explicit):
     * - No decoded results (negative test).
     */
    @Test
    public void read_Code128_Width40_Negative_PerformancePreset_LowQualityMode() throws Exception {
        BarCodeReader reader = new BarCodeReader(
                ExampleAssist.pathCombine(FOLDER, FILE_CODE128_LOWRES_WIDTH_40), DecodeType.CODE_128);

        QualitySettings qs = QualitySettings.getHighPerformance();
        qs.setBarcodeQuality(BarcodeQualityMode.LOW);
        reader.setQualitySettings(qs);

        ExampleAssist.assertNotRecognized(reader, FILE_CODE128_LOWRES_WIDTH_40);
    }
}
