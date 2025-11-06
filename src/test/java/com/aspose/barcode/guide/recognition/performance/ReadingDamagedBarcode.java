package com.aspose.barcode.guide.recognition.performance;

import com.aspose.barcode.barcoderecognition.BarCodeReader;
import com.aspose.barcode.barcoderecognition.DecodeType;
import com.aspose.barcode.barcoderecognition.QualitySettings;
import com.aspose.barcode.barcoderecognition.BarcodeQualityMode;
import com.aspose.barcode.barcoderecognition.DeconvolutionMode;
import com.aspose.barcode.barcoderecognition.XDimensionMode;
import com.aspose.barcode.generation.BarCodeImageFormat;
import com.aspose.barcode.generation.BarcodeGenerator;
import com.aspose.barcode.generation.EncodeTypes;
import com.aspose.barcode.guide.common.ExampleAssist;
import com.aspose.barcode.guide.common.LicenseAssist;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * Reading Damaged Barcode Example
 * <p>
 * What this class demonstrates:
 * 1) How to prepare damaged inputs (noise / blur) using ExampleAssist utilities.
 * 2) How to tune QualitySettings for robust reading:
 * - BarcodeQualityMode.LOW   → heavy methods for damaged/low-contrast bars
 * - DeconvolutionMode.SLOW   → strongest restoration for blurred images
 * - XDimension hints         → help engine search tiny modules
 * <p>
 * Test images are generated idempotently in @BeforeClass.
 */
public class ReadingDamagedBarcode
{

    private static final String FOLDER =
            ExampleAssist.getOrCreateResourceFolderPath("recognition", "quality", "reading_damaged");

    @BeforeClass
    public void setUp() throws Exception
    {
        LicenseAssist.setupLicense();
        generateBaseImages();
        generateDamagedImages();
    }

    // ==================== Test data generation ====================

    /**
     * Creates clean baseline images used in this suite:
     * - code128_clean.png  : clean Code 128
     * - qr_clean.png       : clean QR
     * Reason:
     * - Keep tests self-contained and deterministic for local/CI runs.
     */
    private void generateBaseImages() throws Exception
    {
        ExampleAssist.checkOrCreateImage(FOLDER, "code128_clean.png", path -> {
            BarcodeGenerator g = new BarcodeGenerator(EncodeTypes.CODE_128, "Damaged/Noise Demo");
            g.save(path, BarCodeImageFormat.PNG);
        });

        ExampleAssist.checkOrCreateImage(FOLDER, "qr_clean.png", path -> {
            BarcodeGenerator g = new BarcodeGenerator(EncodeTypes.QR, "Damaged/Blur Demo");
            // Slightly bigger modules help keep the blurred sample recoverable
            g.getParameters().getBarcode().getXDimension().setPixels(3);
            g.save(path, BarCodeImageFormat.PNG);
        });
    }

    /**
     * Creates damaged variants from the clean images:
     * - code128_noisy.png  : adds zero-mean Gaussian noise (stdDev ≈ 10)
     * - qr_blurred.png     : applies mild Gaussian blur (sigma ≈ 1.0)
     * Notes:
     * - Noise → simulates poor printing or scanning.
     * - Blur  → simulates camera motion/out-of-focus.
     * - The chosen parameters aim for a realistic "hard but recoverable" level.
     */
    private void generateDamagedImages() throws Exception
    {
        ExampleAssist.checkOrCreateImage(FOLDER, "code128_noisy.png", outPath -> {
            String in = ExampleAssist.pathCombine(FOLDER, "code128_clean.png");
            ExampleAssist.addGaussianNoise(in, outPath, 10.0);     // tune if CI needs milder noise
        });

        ExampleAssist.checkOrCreateImage(FOLDER, "qr_blurred.png", outPath -> {
            String in = ExampleAssist.pathCombine(FOLDER, "qr_clean.png");
            ExampleAssist.blur(in, outPath, 1.0f);                 // sigma ≈ 1.0 (mild, recoverable)
        });
    }

    // ==================== Damaged: NOISE (Code 128) ====================

    /**
     * Damaged (noisy) Code 128 → Robust recognition with BarcodeQuality=LOW.
     * <p>
     * Purpose:
     * - Show that enabling heavy recognition methods helps on low-contrast / noisy bars.
     * What it does:
     * - Starts from HighQuality preset and switches BarcodeQuality to LOW
     * (the most tolerant profile for damaged elements).
     * Expected:
     * - Successful recognition of the noisy Code 128.
     */
    @Test
    public void read_Code128_Noisy_WithBarcodeQuality_LOW() throws Exception
    {
        String file = "code128_noisy.png";
        BarCodeReader reader = new BarCodeReader(ExampleAssist.pathCombine(FOLDER, file), DecodeType.CODE_128);

        QualitySettings qs = QualitySettings.getHighQuality();
        qs.setBarcodeQuality(BarcodeQualityMode.LOW); // enable heavy methods for poor-quality bars
        reader.setQualitySettings(qs);

        ExampleAssist.assertRecognized(reader, file, 1, DecodeType.CODE_128);
    }

    /**
     * Damaged (noisy) Code 128 → Tuning for tiny/weak modules.
     * <p>
     * Purpose:
     * - Demonstrate targeted hints combined with LOW profile for tough 1D cases.
     * What it does:
     * - Starts from HighPerformance (speed-first) then applies minimal overrides:
     * XDimension = SMALL          → search for small bar width
     * MinimalXDimension = 1 px    → minimal expected module size
     * BarcodeQuality = LOW        → heavy recognition methods
     * Expected:
     * - Successful recognition with better tolerance than plain HighPerformance.
     */
    @Test
    public void read_Code128_Noisy_TinyModules_Tuned() throws Exception
    {
        String file = "code128_noisy.png";
        BarCodeReader reader = new BarCodeReader(ExampleAssist.pathCombine(FOLDER, file), DecodeType.CODE_128);

        QualitySettings qs = QualitySettings.getHighPerformance();
        qs.setXDimension(XDimensionMode.SMALL);
        qs.setMinimalXDimension(1.0f);
        qs.setBarcodeQuality(BarcodeQualityMode.LOW);
        reader.setQualitySettings(qs);

        ExampleAssist.assertRecognized(reader, file, 1, DecodeType.CODE_128);
    }

    // ==================== Damaged: BLUR (QR) ====================

    /**
     * Damaged (blurred) QR → Strong deconvolution (SLOW) for recovery.
     * <p>
     * Purpose:
     * - Show that the strongest restoration pipeline is appropriate for camera blur.
     * What it does:
     * - Starts from HighQuality and sets:
     * Deconvolution = SLOW  → robust restoration for strong blur
     * XDimension = SMALL    → hint engine to consider small modules after blur
     * MinimalXDimension = 1 → minimal module size hint
     * Expected:
     * - Successful recognition of the blurred QR.
     */
    @Test
    public void read_QR_Blurred_WithDeconvolution_SLOW() throws Exception
    {
        String file = "qr_blurred.png";
        BarCodeReader reader = new BarCodeReader(ExampleAssist.pathCombine(FOLDER, file), DecodeType.QR);

        QualitySettings qs = QualitySettings.getHighQuality();
        qs.setDeconvolution(DeconvolutionMode.SLOW);
        qs.setXDimension(XDimensionMode.SMALL);
        qs.setMinimalXDimension(1.0f);
        reader.setQualitySettings(qs);

        ExampleAssist.assertRecognized(reader, file, 1, DecodeType.QR);
    }

    /**
     * (Optional) Damaged (blurred) QR → FAST deconvolution as contrast (negative).
     * <p>
     * Purpose:
     * - Illustrate that the light-weight restoration path may be insufficient for this blur level.
     * - Serves as a didactic counterexample to the SLOW test above.
     * Expected:
     * - No recognition with FAST on the same blurred sample.
     * Note:
     * - If this becomes flaky on your setup, either reduce blur to make FAST pass
     * or keep this test disabled/commented out.
     */
    @Test//TODO: Find input parameters that cause the image to be unrecognized
    public void read_QR_Blurred_WithDeconvolution_FAST_Negative() throws Exception
    {
        String file = "qr_blurred.png";
        BarCodeReader reader = new BarCodeReader(ExampleAssist.pathCombine(FOLDER, file), DecodeType.QR);

        QualitySettings qs = QualitySettings.getHighPerformance();
        qs.setDeconvolution(DeconvolutionMode.FAST);
        reader.setQualitySettings(qs);

        ExampleAssist.assertNotRecognized(reader, file);
    }
}
