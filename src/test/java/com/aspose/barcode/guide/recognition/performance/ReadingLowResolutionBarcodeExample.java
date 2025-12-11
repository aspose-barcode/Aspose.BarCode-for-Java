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
 * Reading low-resolution barcode example.
 *
 * Focus:
 * - How recognition behaves when the barcode image is rendered directly at low target widths (no post-resampling).
 * - How QualitySettings presets and X-dimension hints affect robustness on tiny, pixel-aligned modules.
 * - How BarcodeQualityMode (HIGH / NORMAL / LOW) is used to describe the expected image quality,
 *   independently from the chosen preset.
 *
 * Data:
 * - A clean synthetic CODE_128 is generated once (wide quiet zones).
 * - Low-res variants are rendered directly at fixed pixel widths (150 / 80 / 40 px) with explicit X-dimension.
 *
 * BarcodeQualityMode semantics:
 * - HIGH   → the engine assumes that barcodes are rendered with good quality (crisp edges, low noise).
 * - NORMAL → medium/typical quality.
 * - LOW    → barcodes may be low quality (noise, blur, weak contrast) and the engine should be more tolerant.
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
            gen.getParameters().getBarcode().getXDimension().setPixels(3.0f);
            gen.getParameters().getBarcode().getPadding().getLeft().setPixels(40f);
            gen.getParameters().getBarcode().getPadding().getRight().setPixels(40f);
            gen.getParameters().getBarcode().getPadding().getTop().setPixels(20f);
            gen.getParameters().getBarcode().getPadding().getBottom().setPixels(20f);
            gen.save(path, BarCodeImageFormat.PNG);
        });
    }

    /**
     * Render low-resolution variants directly via the generator (no resampling):
     * - we fix target image width in pixels,
     * - choose X-dimension so that modules remain ~1–2 px,
     * - keep a white background and sufficient quiet zones.
     */
    private void generateLowResVariants() throws Exception {
        // For stability, we set the height to ~120–160 px so that the strokes are "tall enough."
        // Quiet zones: at least 8–12 px left/right.
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

    /**
     * Purpose:
     * - Establish a sanity baseline: a clean, sufficiently large CODE_128 should be readable
     *   with balanced settings (neither extreme speed nor extreme quality).
     *
     * Demonstrates:
     * - On good input quality, NORMAL settings are adequate; no special hints are required.
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
     * - Verify that a mildly downscaled barcode (~150 px width), rendered crisp and with proper quiet zones,
     *   is reliably decoded when prioritizing speed and marking barcodes as high-quality.
     *
     * Demonstrates:
     * - A performance preset (getHighPerformance) can be safely combined with BarcodeQualityMode.HIGH
     *   when low-resolution images are still well rendered (good contrast, no noticeable noise).
     *
     * Interpretation:
     * - Here HIGH does not mean "heavier processing". It tells the engine that symbols are expected
     *   to be high quality, so a lighter recognition path is appropriate.
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
     * - NormalQuality preset + BarcodeQualityMode.NORMAL as a typical "default" configuration for
     *   slightly low-resolution but otherwise clean barcodes.
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
     *   and (c) BarcodeQualityMode.HIGH, starting from a performance preset.
     *
     * Demonstrates:
     * - Targeted X-dimension guidance helps the detector lock onto tiny modules,
     *   while HIGH indicates that barcodes themselves are still expected to be
     *   high quality (synthetic, noise-free), despite the small size.
     *
     * Notes:
     * - Here the limiting factor is resolution, not damage or noise. That is why
     *   we still treat barcodes as "high-quality" from the engine's point of view.
     */
    @Test
    public void read_Code128_Width80_PerformancePreset_SmallXDim_HighQualityMode() throws Exception {
        BarCodeReader reader = new BarCodeReader(
                ExampleAssist.pathCombine(FOLDER, FILE_CODE128_LOWRES_WIDTH_80), DecodeType.CODE_128);

        QualitySettings qs = QualitySettings.getHighPerformance();
        qs.setXDimension(XDimensionMode.USE_MINIMAL_X_DIMENSION);
        qs.setMinimalXDimension(1.0f); // Hint: expected minimal module width in pixels at this rendered size
        qs.setBarcodeQuality(BarcodeQualityMode.HIGH);
        reader.setQualitySettings(qs);

        ExampleAssist.assertRecognized(reader, FILE_CODE128_LOWRES_WIDTH_80, 1, DecodeType.CODE_128);
    }

    /**
     * Purpose:
     * - Contrast case: same ~80 px input; keep the image crisp and use NormalQuality
     *   preset with BarcodeQualityMode.NORMAL, without additional X-dimension hints.
     *
     * Demonstrates:
     * - Balanced settings that assume medium/typical quality may still decode tiny barcodes
     *   if they are rendered cleanly, but they are more sensitive to quantization and
     *   minor violations of quiet zones.
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
     * - Evaluate an extreme low-resolution case (~40 px width) using a robustness-oriented preset
     *   (HighQuality) with SMALL X-dimension hints and BarcodeQualityMode.HIGH, and verify that
     *   decoding can still succeed when the image is synthetically crisp.
     *
     * Demonstrates:
     * - Best-case robustness near the resolution cliff: with accurate geometry hints
     *   (XDimensionMode.USE_MINIMAL_X_DIMENSION, MinimalXDimension) and a preset tuned for difficult barcodes,
     *   even ~40 px wide symbols can be decoded when they are cleanly rendered.
     *
     * Interpretation:
     * - BarcodeQualityMode.HIGH here expresses that symbols come from a controlled generator
     *   (no extra noise or damage), even though they are very small.
     */
    @Test
    public void read_Code128_Width40_Negative_TooSmallEvenWhenCrisp() throws Exception {
        BarCodeReader reader = new BarCodeReader(
                ExampleAssist.pathCombine(FOLDER, FILE_CODE128_LOWRES_WIDTH_40), DecodeType.CODE_128);

        QualitySettings qs = QualitySettings.getHighQuality();
        qs.setXDimension(XDimensionMode.USE_MINIMAL_X_DIMENSION);
        qs.setMinimalXDimension(1.0f);
        qs.setBarcodeQuality(BarcodeQualityMode.HIGH);
        reader.setQualitySettings(qs);

        ExampleAssist.assertRecognized(reader, currentMethodName(), 1, DecodeType.CODE_128);
    }

    /**
     * Purpose:
     * - Stress-test an extreme low-resolution case (~40 px width) while prioritizing speed
     *   (HighPerformance preset) and explicitly marking barcodes as low quality
     *   (BarcodeQualityMode.LOW).
     *
     * Demonstrates:
     * - Decoder robustness at the resolution cliff: even when the engine assumes low-quality
     *   symbols and works in a speed-oriented preset, a carefully generated example
     *   can still be decodable.
     *
     * Recommendation:
     * - Treat this as a best-case edge. In production, consider using HighQuality preset
     *   for similar inputs if you expect real noise or damage.
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
