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
import com.aspose.barcode.guide.common.ImageSupplier;
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
 * - Low-res variants are produced via nearest-neighbor downscale to preserve "pixelation".
 */
public class ReadingLowResolutionExample {

    private static final String FOLDER =
            ExampleAssist.getOrCreateResourceFolderPath("recognition", "quality", "reading_low_resolution");

    @BeforeClass
    public void setUp() throws Exception {
        LicenseAssist.setupLicense();
        generateCode128Base();
        generateLowResVariants();
    }

    /**
     * Creates a clean baseline CODE_128 image at comfortable resolution.
     * Rationale: a "good" source makes it easier to attribute failures to downscale, not to generation artifacts.
     */
    private void generateCode128Base() throws Exception {
        final String file = "code128_clean_600.png";
        ExampleAssist.checkOrCreateImage(FOLDER, file, path -> {
            BarcodeGenerator gen = new BarcodeGenerator(EncodeTypes.CODE_128, "LowRes:CODE128");
            // Default generator settings are fine; saved at PNG for lossless baseline.
            gen.save(path, BarCodeImageFormat.PNG);
        });
    }

    /**
     * Produces low-resolution variants using nearest-neighbor downscale.
     * We keep multiple target widths to probe the "failure cliff".
     * Notes:
     * - NN downscale preserves hard pixel edges and minimizes blur (more realistic for tiny captures / thumbnails).
     * - If your ImageSupplier supports height-only or scale factor, adapt the calls accordingly.
     */
    private void generateLowResVariants() throws Exception {
        final String src = ExampleAssist.pathCombine(FOLDER, "code128_clean_600.png");

        ExampleAssist.checkOrCreateImage(FOLDER, "code128_lr_150.png",
                out -> ImageSupplier.downscaleNearest(src, out, /*targetWidthPx*/150));

        ExampleAssist.checkOrCreateImage(FOLDER, "code128_lr_80.png",
                out -> ImageSupplier.downscaleNearest(src, out, /*targetWidthPx*/80));

        ExampleAssist.checkOrCreateImage(FOLDER, "code128_lr_40.png",
                out -> ImageSupplier.downscaleNearest(src, out, /*targetWidthPx*/40));
    }

    // ── CLEAN baseline (sanity check) ──────────────────────────────────────────

    /**
     * Purpose:
     * - Sanity check on the clean baseline image with a balanced preset.
     *
     * Demonstrates:
     * - Normal-quality configuration reliably reads a sufficiently large barcode.
     *
     * Expectation:
     * - ≥1 CODE_128 result.
     */
    @Test
    public void read_Code128_Clean_NormalQuality() throws Exception {
        final String file = "code128_clean_600.png";
        BarCodeReader reader = new BarCodeReader(ExampleAssist.pathCombine(FOLDER, file), DecodeType.CODE_128);

        QualitySettings qs = QualitySettings.getNormalQuality();
        qs.setBarcodeQuality(BarcodeQualityMode.NORMAL);
        reader.setQualitySettings(qs);

        ExampleAssist.assertRecognized(reader, file, 1, DecodeType.CODE_128);
    }

    // ── Low resolution: ~150px width ──────────────────────────────────────────

    /**
     * Purpose:
     * - Show that mildly downscaled barcode (~150 px width) is still robust with a performance-biased preset
     *   if we keep quality high.
     *
     * Demonstrates:
     * - getHighPerformance + BarcodeQualityMode.HIGH remains reliable at modestly low resolution.
     *
     * Expectation:
     * - ≥1 CODE_128 result; stable read.
     */
    @Test
    public void read_Code128_LR150_PerfPlusHighQuality() throws Exception {
        final String file = "code128_lr_150.png";
        BarCodeReader reader = new BarCodeReader(ExampleAssist.pathCombine(FOLDER, file), DecodeType.CODE_128);

        QualitySettings qs = QualitySettings.getHighPerformance();
        qs.setBarcodeQuality(BarcodeQualityMode.HIGH);
        reader.setQualitySettings(qs);

        ExampleAssist.assertRecognized(reader, file, 1, DecodeType.CODE_128);
    }

    /**
     * Purpose:
     * - Validate that a balanced preset also handles ~150 px width without extra hints.
     *
     * Demonstrates:
     * - getNormalQuality + NORMAL is often sufficient for mild low-res.
     *
     * Expectation:
     * - ≥1 CODE_128 result.
     */
    @Test
    public void read_Code128_LR150_NormalQuality() throws Exception {
        final String file = "code128_lr_150.png";
        BarCodeReader reader = new BarCodeReader(ExampleAssist.pathCombine(FOLDER, file), DecodeType.CODE_128);

        QualitySettings qs = QualitySettings.getNormalQuality();
        qs.setBarcodeQuality(BarcodeQualityMode.NORMAL);
        reader.setQualitySettings(qs);

        ExampleAssist.assertRecognized(reader, file, 1, DecodeType.CODE_128);
    }

    // ── Low resolution: ~80px width ───────────────────────────────────────────

    /**
     * Purpose:
     * - At ~80 px width, module width becomes very small. Provide explicit X-dimension hints
     *   to bias the detector toward small bars, while keeping quality high.
     *
     * Demonstrates:
     * - Combining getHighPerformance with XDimensionMode.SMALL and MinimalXDimension can recover tiny barcodes.
     *
     * Expectation:
     * - ≥1 CODE_128 result; hints should avoid missing modules due to quantization.
     */
    @Test
    public void read_Code128_LR80_PerfWithSmallXDim_HighQuality() throws Exception {
        final String file = "code128_lr_80.png";
        BarCodeReader reader = new BarCodeReader(ExampleAssist.pathCombine(FOLDER, file), DecodeType.CODE_128);

        QualitySettings qs = QualitySettings.getHighPerformance();
        qs.setXDimension(XDimensionMode.SMALL);
        qs.setMinimalXDimension(1.0f);              // suggest minimal module width in pixels
        qs.setBarcodeQuality(BarcodeQualityMode.HIGH);
        reader.setQualitySettings(qs);

        ExampleAssist.assertRecognized(reader, file, 1, DecodeType.CODE_128);
    }

    /**
     * Purpose:
     * - Contrast: same ~80 px input but with NORMAL quality and no X-dimension hints.
     *
     * Demonstrates:
     * - Balanced settings might still pass, but are more sensitive to font/quiet zone/quantization.
     *
     * Expectation:
     * - ≥1 CODE_128 result (if your environment is stricter, lower minCount or switch to "negative" for this case).
     */
    @Test
    public void read_Code128_LR80_NormalQuality_NoHints() throws Exception {
        final String file = "code128_lr_80.png";
        BarCodeReader reader = new BarCodeReader(ExampleAssist.pathCombine(FOLDER, file), DecodeType.CODE_128);

        QualitySettings qs = QualitySettings.getNormalQuality();
        qs.setBarcodeQuality(BarcodeQualityMode.NORMAL);
        reader.setQualitySettings(qs);

        ExampleAssist.assertRecognized(reader, file, 1, DecodeType.CODE_128);
    }

    // ── Very low resolution: ~40px width ──────────────────────────────────────

    /**
     * Purpose:
     * - Probe the "failure cliff": at ~40 px width, bars may collapse to 1–2 px,
     *   making decoding unreliable even with strong quality settings.
     *
     * Demonstrates:
     * - There exists a practical lower bound in resolution. This negative test documents it.
     *
     * Expectation:
     * - 0 results (documenting a known limitation for tiny inputs).
     */
    @Test
    public void read_Code128_LR40_Negative_TooSmall() throws Exception {
        final String file = "code128_lr_40.png";
        BarCodeReader reader = new BarCodeReader(ExampleAssist.pathCombine(FOLDER, file), DecodeType.CODE_128);

        QualitySettings qs = QualitySettings.getHighQuality();  // even quality-biased preset…
        qs.setXDimension(XDimensionMode.SMALL);
        qs.setMinimalXDimension(1.0f);
        qs.setBarcodeQuality(BarcodeQualityMode.HIGH);          // …and max quality might still be insufficient
        reader.setQualitySettings(qs);

        // Negative expectation: nothing should be recognized at this extreme size.
        ExampleAssist.assertNotRecognized(reader, file);
    }

    /**
     * Purpose:
     * - (Optional) Show that pushing for speed on a tiny input makes it even more fragile.
     *
     * Demonstrates:
     * - Performance preset + LOW quality mode is not suitable for extreme low-res cases.
     *
     * Expectation:
     * - 0 results.
     */
    @Test
    public void read_Code128_LR40_Negative_PerfLowQuality() throws Exception {
        final String file = "code128_lr_40.png";
        BarCodeReader reader = new BarCodeReader(ExampleAssist.pathCombine(FOLDER, file), DecodeType.CODE_128);

        QualitySettings qs = QualitySettings.getHighPerformance();
        qs.setBarcodeQuality(BarcodeQualityMode.LOW);
        reader.setQualitySettings(qs);

        ExampleAssist.assertNotRecognized(reader, file);
    }
}
