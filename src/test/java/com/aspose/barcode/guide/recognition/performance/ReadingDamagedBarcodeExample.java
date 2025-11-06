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

public class ReadingDamagedBarcodeExample
{

    private static final String FOLDER =
            ExampleAssist.getOrCreateResourceFolderPath("recognition", "quality", "reading_damaged");

    // ==== File names (centralized) ====
    private static final String FILE_CODE128_CLEAN        = "code128_clean.png";
    private static final String FILE_QR_CLEAN             = "qr_clean.png";
    private static final String FILE_CODE128_NOISY        = "code128_noisy.png";
    private static final String FILE_QR_BLUR_MILD         = "qr_blurred_mild.png";   // for SLOW positive
    private static final String FILE_QR_BLUR_HEAVY        = "qr_blurred_heavy.png";  // for FAST negative

    @BeforeClass
    public void setUp() throws Exception {
        LicenseAssist.setupLicense();
        generateBaseImages();
        generateDamagedImages();
    }

    // ==================== Test data generation ====================

    /**
     * Creates clean baseline images used in this suite:
     * - code128_clean.png  : clean Code 128
     * - qr_clean.png       : clean QR (slightly thicker modules for robustness in blur tests)
     */
    private void generateBaseImages() throws Exception {
        ExampleAssist.checkOrCreateImage(FOLDER, FILE_CODE128_CLEAN, path -> {
            BarcodeGenerator g = new BarcodeGenerator(EncodeTypes.CODE_128, "Damaged/Noise Demo");
            g.save(path, BarCodeImageFormat.PNG);
        });

        ExampleAssist.checkOrCreateImage(FOLDER, FILE_QR_CLEAN, path -> {
            BarcodeGenerator g = new BarcodeGenerator(EncodeTypes.QR, "Damaged/Blur Demo");
            g.getParameters().getBarcode().getXDimension().setPixels(3); // slightly thicker modules
            g.save(path, BarCodeImageFormat.PNG);
        });
    }

    /**
     * Creates damaged variants:
     * - code128_noisy.png   : adds zero-mean Gaussian noise (stdDev ≈ 10)
     * - qr_blurred_mild.png : single Gaussian blur (sigma ≈ 1.0) → should be recoverable with SLOW
     * - qr_blurred_heavy.png: strong degradation (blur σ=2.0 applied twice + light noise) → FAST should fail
     */
    private void generateDamagedImages() throws Exception {
        // Noisy Code128
        ExampleAssist.checkOrCreateImage(FOLDER, FILE_CODE128_NOISY, out -> {
            String in = ExampleAssist.pathCombine(FOLDER, FILE_CODE128_CLEAN);
            ExampleAssist.addGaussianNoise(in, out, 10.0);
        });

        // Mildly blurred QR (positive with SLOW)
        ExampleAssist.checkOrCreateImage(FOLDER, FILE_QR_BLUR_MILD, out -> {
            String in = ExampleAssist.pathCombine(FOLDER, FILE_QR_CLEAN);
            ExampleAssist.blur(in, out, 1.0f); // mild, recoverable
        });

        // Heavily blurred QR (negative with FAST)
        ExampleAssist.checkOrCreateImage(FOLDER, FILE_QR_BLUR_HEAVY, out -> {
            String in = ExampleAssist.pathCombine(FOLDER, FILE_QR_CLEAN);
            String step1 = ExampleAssist.pathCombine(FOLDER, "qr_blur_step1.png");
            String step2 = ExampleAssist.pathCombine(FOLDER, "qr_blur_step2.png");
            // Two strong blurs + a bit of noise to reduce contrast
            ExampleAssist.blur(in, step1, 2.0f);
            ExampleAssist.blur(step1, step2, 2.0f);
            ExampleAssist.addGaussianNoise(step2, out, 6.0);
        });
    }

    // ==================== Damaged: NOISE (Code 128) ====================

    /**
     * Noisy Code 128 → robust recognition with BarcodeQuality=LOW.
     * Starts from HighQuality and switches BarcodeQuality to LOW (heavier methods).
     * Expectation: at least one result; CODE_128 present.
     */
    @Test
    public void read_Code128_Noisy_WithBarcodeQuality_LOW() throws Exception {
        BarCodeReader reader =
                new BarCodeReader(ExampleAssist.pathCombine(FOLDER, FILE_CODE128_NOISY), DecodeType.CODE_128);

        QualitySettings qs = QualitySettings.getHighQuality();
        qs.setBarcodeQuality(BarcodeQualityMode.LOW);
        reader.setQualitySettings(qs);

        ExampleAssist.assertRecognized(reader, FILE_CODE128_NOISY, 1, DecodeType.CODE_128);
    }

    /**
     * Noisy Code 128 → tuning for tiny/weak modules (XDimension hints + LOW).
     * Expectation: at least one result; CODE_128 present.
     */
    @Test
    public void read_Code128_Noisy_TinyModules_Tuned() throws Exception {
        BarCodeReader reader =
                new BarCodeReader(ExampleAssist.pathCombine(FOLDER, FILE_CODE128_NOISY), DecodeType.CODE_128);

        QualitySettings qs = QualitySettings.getHighPerformance();
        qs.setXDimension(XDimensionMode.SMALL);
        qs.setMinimalXDimension(1.0f);
        qs.setBarcodeQuality(BarcodeQualityMode.LOW);
        reader.setQualitySettings(qs);

        ExampleAssist.assertRecognized(reader, FILE_CODE128_NOISY, 1, DecodeType.CODE_128);
    }

    // ==================== Damaged: BLUR (QR) ====================

    /**
     * Blurred QR (mild) → SLOW deconvolution for recovery.
     * Settings: HighQuality + Deconvolution=SLOW + XDimension=SMALL + MinimalXDimension=1.
     * Expectation: at least one result; QR present.
     */
    @Test
    public void read_QR_BlurredMild_WithDeconvolution_SLOW() throws Exception {
        BarCodeReader reader =
                new BarCodeReader(ExampleAssist.pathCombine(FOLDER, FILE_QR_BLUR_MILD), DecodeType.QR);

        QualitySettings qs = QualitySettings.getHighQuality();
        qs.setDeconvolution(DeconvolutionMode.SLOW);
        qs.setXDimension(XDimensionMode.SMALL);
        qs.setMinimalXDimension(1.0f);
        reader.setQualitySettings(qs);

        ExampleAssist.assertRecognized(reader, FILE_QR_BLUR_MILD, 1, DecodeType.QR);
    }

    /**
     * Blurred QR (heavy) → FAST deconvolution (contrast, negative).
     * Purpose: illustrate that a lightweight restoration path is insufficient at this blur level.
     * Expectation: zero results.
     */
    @Test
    public void read_QR_BlurredHeavy_WithDeconvolution_FAST_Negative() throws Exception {
        BarCodeReader reader =
                new BarCodeReader(ExampleAssist.pathCombine(FOLDER, FILE_QR_BLUR_HEAVY), DecodeType.QR);

        QualitySettings qs = QualitySettings.getHighPerformance();
        qs.setDeconvolution(DeconvolutionMode.FAST);
        reader.setQualitySettings(qs);

        ExampleAssist.assertNotRecognized(reader, FILE_QR_BLUR_HEAVY);
    }
}
