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
 * Demonstrates how to control recognition performance and robustness using:
 *  - BarcodeQualityMode (HIGH / NORMAL / LOW)
 *  - DeconvolutionMode (FAST / NORMAL / SLOW)
 *  - Preset + targeted overrides (XDimensionMode, MinimalXDimension, etc.)
 *
 * Test data (Code 128 and QR) is generated once in @BeforeClass.
 */
public class DeconvolutionModeExample {

    private static final String FOLDER =
            ExampleAssist.getOrCreateResourceFolderPath("recognition", "quality", "barcode_quality_deconvolution");

    private static final String FILE_CODE128      = "qset_code128.png";
    private static final String FILE_QR_CLEAN     = "qset_qr.png";
    private static final String FILE_QR_BLURRED   = "qset_qr_blurred.png";

    /**
     * Initializes license and ensures demo images exist before tests run.
     * Images are created once (idempotently) to keep tests deterministic and self-contained.
     */
    @BeforeClass
    public void setUp() throws Exception {
        LicenseAssist.setupLicense();
        generateCode128AndQR();
    }

    // ==================== Test data generation ====================

    /**
     * Generates images used by tests:
     *  - qset_code128.png: Code 128 for BarcodeQualityMode experiments
     *  - qset_qr.png: clean QR Code for DeconvolutionMode experiments
     *  - qset_qr_blurred.png: blurred QR Code to demonstrate effect of SLOW deconvolution
     *
     * Note: ExampleAssist.checkOrCreateImage(...) will reuse existing files if present.
     */
    private void generateCode128AndQR() throws Exception {
        // Code128 for BarcodeQuality tests
        ExampleAssist.checkOrCreateImage(FOLDER, FILE_CODE128, path -> {
            BarcodeGenerator generator =
                    new BarcodeGenerator(EncodeTypes.CODE_128, "QualitySettings:BarcodeQuality");
            generator.save(path, BarCodeImageFormat.PNG);
        });

        // QR for Deconvolution tests (clean)
        ExampleAssist.checkOrCreateImage(FOLDER, FILE_QR_CLEAN, path -> {
            BarcodeGenerator generator =
                    new BarcodeGenerator(EncodeTypes.QR, "QualitySettings:Deconvolution");
            // Make modules larger so that blurred image is still recoverable
            generator.getParameters().getBarcode().getXDimension().setPixels(4);
            generator.save(path, BarCodeImageFormat.PNG);
        });

        // QR blurred: mild blur that FAST may struggle with, SLOW should still recognize
        ExampleAssist.checkOrCreateImage(FOLDER, FILE_QR_BLURRED, (String full) -> {
            String cleanPath = ExampleAssist.pathCombine(FOLDER, FILE_QR_CLEAN);
            // Tuned empirically so that DeconvolutionMode.SLOW passes reliably
            ExampleAssist.blur(cleanPath, full, 0.8f);
        });


    }

    // ==================== BarcodeQualityMode ====================

    /**
     * Demonstrates BarcodeQualityMode.HIGH on a clean Code 128:
     *  - Starts from HighPerformance preset.
     *  - Forces HIGH quality profile: fastest path for high-quality barcodes.
     *  - Expected behavior: quick recognition when the image is good.
     */
    @Test
    public void read_Code128_BarcodeQuality_HIGH() throws Exception {
        String fileName = FILE_CODE128;
        BarCodeReader reader =
                new BarCodeReader(ExampleAssist.pathCombine(FOLDER, fileName), DecodeType.CODE_128);

        QualitySettings qualitySettings = QualitySettings.getHighPerformance();
        qualitySettings.setBarcodeQuality(BarcodeQualityMode.HIGH);
        reader.setQualitySettings(qualitySettings);

        ExampleAssist.assertRecognized(reader, fileName, 1, DecodeType.CODE_128);
    }

    /**
     * Demonstrates BarcodeQualityMode.NORMAL on a clean Code 128:
     *  - Starts from NormalQuality preset (balanced speed/accuracy).
     *  - Forces NORMAL quality profile: standard methods enabled.
     *  - Expected behavior: robust for most regular-quality inputs.
     */
    @Test
    public void read_Code128_BarcodeQuality_NORMAL() throws Exception {
        String fileName = FILE_CODE128;
        BarCodeReader reader =
                new BarCodeReader(ExampleAssist.pathCombine(FOLDER, fileName), DecodeType.CODE_128);

        QualitySettings qualitySettings = QualitySettings.getNormalQuality();
        qualitySettings.setBarcodeQuality(BarcodeQualityMode.NORMAL);
        reader.setQualitySettings(qualitySettings);

        ExampleAssist.assertRecognized(reader, fileName, 1, DecodeType.CODE_128);
    }

    /**
     * Demonstrates BarcodeQualityMode.LOW on a clean Code 128:
     *  - Starts from HighQuality preset (heavier processing).
     *  - Forces LOW quality profile.
     *  - Expected behavior: slowest path with extra methods enabled for poor-quality bars.
     */
    @Test
    public void read_Code128_BarcodeQuality_LOW() throws Exception {
        String fileName = FILE_CODE128;
        BarCodeReader reader =
                new BarCodeReader(ExampleAssist.pathCombine(FOLDER, fileName), DecodeType.CODE_128);

        QualitySettings qualitySettings = QualitySettings.getHighQuality();
        qualitySettings.setBarcodeQuality(BarcodeQualityMode.LOW);
        reader.setQualitySettings(qualitySettings);

        ExampleAssist.assertRecognized(reader, fileName, 1, DecodeType.CODE_128);
    }

    // ==================== DeconvolutionMode (clean QR) ====================

    /**
     * Demonstrates DeconvolutionMode.FAST on a clean QR code:
     *  - Uses HighQuality preset but forces FAST deconvolution (light-weight restoration).
     *  - Intended for images with minimal blur/shake.
     *  - Expected behavior: fastest restoration stage, enough for high-quality captures.
     */
    @Test
    public void read_QR_Deconvolution_FAST() throws Exception {
        String fileName = FILE_QR_CLEAN;
        BarCodeReader reader =
                new BarCodeReader(ExampleAssist.pathCombine(FOLDER, fileName), DecodeType.QR);

        QualitySettings qualitySettings = QualitySettings.getHighQuality();
        qualitySettings.setDeconvolution(DeconvolutionMode.FAST);
        reader.setQualitySettings(qualitySettings);

        ExampleAssist.assertRecognized(reader, fileName, 1, DecodeType.QR);
    }

    /**
     * Demonstrates DeconvolutionMode.NORMAL on a clean QR code:
     *  - Uses NormalQuality preset with NORMAL deconvolution.
     *  - Balanced option when some mild blur/noise may exist.
     *  - Expected behavior: moderate cost, good default for typical mobile photos.
     */
    @Test
    public void read_QR_Deconvolution_NORMAL() throws Exception {
        String fileName = FILE_QR_CLEAN;
        BarCodeReader reader =
                new BarCodeReader(ExampleAssist.pathCombine(FOLDER, fileName), DecodeType.QR);

        QualitySettings qualitySettings = QualitySettings.getNormalQuality();
        qualitySettings.setDeconvolution(DeconvolutionMode.NORMAL);
        reader.setQualitySettings(qualitySettings);

        ExampleAssist.assertRecognized(reader, fileName, 1, DecodeType.QR);
    }

    /**
     * Demonstrates DeconvolutionMode.SLOW on a clean QR code:
     *  - Uses HighQuality preset with the strongest (SLOW) restoration methods.
     *  - Intended for heavily blurred/low-quality inputs; slowest but most powerful.
     *  - Expected behavior: the most robust deconvolution pipeline.
     */
    @Test
    public void read_QR_Deconvolution_SLOW() throws Exception {
        String fileName = FILE_QR_CLEAN;
        BarCodeReader reader =
                new BarCodeReader(ExampleAssist.pathCombine(FOLDER, fileName), DecodeType.QR);

        QualitySettings qualitySettings = QualitySettings.getHighQuality();
        qualitySettings.setDeconvolution(DeconvolutionMode.SLOW);
        reader.setQualitySettings(qualitySettings);

        ExampleAssist.assertRecognized(reader, fileName, 1, DecodeType.QR);
    }

    // ==================== DeconvolutionMode (blurred QR) ====================

    /**
     * Demonstrates DeconvolutionMode.SLOW on a blurred QR code:
     *  - Uses HighQuality preset with the strongest deconvolution pipeline.
     *  - Intended for motion blur or out-of-focus captures.
     *  - Expected behavior: QR is still recognized on the blurred image.
     */
    @Test
    public void read_QR_Blurred_Deconvolution_SLOW() throws Exception {
        String fileName = FILE_QR_BLURRED;
        BarCodeReader reader =
                new BarCodeReader(ExampleAssist.pathCombine(FOLDER, fileName), DecodeType.QR);

        QualitySettings qualitySettings = QualitySettings.getHighQuality();
        qualitySettings.setDeconvolution(DeconvolutionMode.SLOW);
        reader.setQualitySettings(qualitySettings);

        ExampleAssist.assertRecognized(reader, fileName, 1, DecodeType.QR);
    }

    // ==================== Preset + targeted overrides ====================

    /**
     * Demonstrates starting from a fast preset and then applying targeted overrides:
     *  - Base: HighPerformance (aims for speed on clean inputs).
     *  - Overrides:
     *      XDimension = SMALL           -> look for smaller bars/cells (tiny modules)
     *      MinimalXDimension = 1.0 px   -> minimal expected module size in pixels
     *      BarcodeQuality = LOW         -> heavier methods for low-quality / damaged bars
     *      Deconvolution = SLOW         -> strongest restoration for blur/degradation
     *  - Intended use: when you expect very small and/or degraded 1D symbols.
     *  - Trade-off: slower but more tolerant than pure HighPerformance.
     */
    @Test
    public void read_Code128_PresetWithOverrides_forSmallAndLowQuality() throws Exception {
        String fileName = FILE_CODE128;
        BarCodeReader reader =
                new BarCodeReader(ExampleAssist.pathCombine(FOLDER, fileName), DecodeType.CODE_128);

        QualitySettings qualitySettings = QualitySettings.getHighPerformance();
        qualitySettings.setXDimension(XDimensionMode.SMALL);
        qualitySettings.setMinimalXDimension(1.0f);
        qualitySettings.setBarcodeQuality(BarcodeQualityMode.LOW);
        qualitySettings.setDeconvolution(DeconvolutionMode.SLOW);
        reader.setQualitySettings(qualitySettings);

        ExampleAssist.assertRecognized(reader, fileName, 1, DecodeType.CODE_128);
    }
}
