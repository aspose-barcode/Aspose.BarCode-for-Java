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
public class BarcodeQualityDeconvolutionModeExample
{
    private static final String FOLDER =
            ExampleAssist.getOrCreateResourceFolderPath("recognition", "quality", "barcode_quality_deconvolution");

    /**
     * Initializes license and ensures demo images exist before tests run.
     * Images are created once (idempotently) to keep tests deterministic and self-contained.
     */
    @BeforeClass
    public void setUp() throws Exception
    {
        LicenseAssist.setupLicense();
        generateCode128AndQR();
    }

    // ==================== Test data generation ====================

    /**
     * Generates two images used by tests:
     *  - qset_code128.png: Code 128 for BarcodeQualityMode experiments
     *  - qset_qr.png: QR Code for DeconvolutionMode experiments
     *
     * Note: ExampleAssist.checkOrCreateImage(...) will reuse existing files if present.
     */
    private void generateCode128AndQR() throws Exception
    {
        // Code128 for BarcodeQuality tests
        String code128 = "qset_code128.png";
        ExampleAssist.checkOrCreateImage(FOLDER, code128, path -> {
            BarcodeGenerator gen = new BarcodeGenerator(EncodeTypes.CODE_128, "QualitySettings:BarcodeQuality");
            gen.save(path, BarCodeImageFormat.PNG);
        });

        // QR for Deconvolution tests
        String qr = "qset_qr.png";
        ExampleAssist.checkOrCreateImage(FOLDER, qr, path -> {
            BarcodeGenerator gen = new BarcodeGenerator(EncodeTypes.QR, "QualitySettings:Deconvolution");
            gen.save(path, BarCodeImageFormat.PNG);
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
    public void read_Code128_BarcodeQuality_HIGH() throws Exception
    {
        String fileName = "qset_code128.png";
        BarCodeReader reader = new BarCodeReader(ExampleAssist.pathCombine(FOLDER, fileName), DecodeType.CODE_128);

        QualitySettings qs = QualitySettings.getHighPerformance();
        qs.setBarcodeQuality(BarcodeQualityMode.HIGH);
        reader.setQualitySettings(qs);

        ExampleAssist.assertRecognized(reader, fileName, 1, DecodeType.CODE_128);
    }

    /**
     * Demonstrates BarcodeQualityMode.NORMAL on a clean Code 128:
     *  - Starts from NormalQuality preset (balanced speed/accuracy).
     *  - Forces NORMAL quality profile: standard methods enabled.
     *  - Expected behavior: robust for most regular-quality inputs.
     */
    @Test
    public void read_Code128_BarcodeQuality_NORMAL() throws Exception
    {
        String fileName = "qset_code128.png";
        BarCodeReader reader = new BarCodeReader(ExampleAssist.pathCombine(FOLDER, fileName), DecodeType.CODE_128);

        QualitySettings qs = QualitySettings.getNormalQuality();
        qs.setBarcodeQuality(BarcodeQualityMode.NORMAL);
        reader.setQualitySettings(qs);

        ExampleAssist.assertRecognized(reader, fileName, 1, DecodeType.CODE_128);
    }

    /**
     * Demonstrates BarcodeQualityMode.LOW on a clean Code 128:
     *  - Starts from HighQuality preset (heavier processing).
     *  - Forces LOW quality profile: enables extra/hard methods for damaged or low-contrast bars.
     *  - Expected behavior: the slowest but most tolerant path when quality is poor.
     */
    @Test
    public void read_Code128_BarcodeQuality_LOW() throws Exception
    {
        String fileName = "qset_code128.png";
        BarCodeReader reader = new BarCodeReader(ExampleAssist.pathCombine(FOLDER, fileName), DecodeType.CODE_128);

        QualitySettings qs = QualitySettings.getHighQuality();
        qs.setBarcodeQuality(BarcodeQualityMode.LOW);
        reader.setQualitySettings(qs);

        ExampleAssist.assertRecognized(reader, fileName, 1, DecodeType.CODE_128);
    }

    // ==================== DeconvolutionMode ====================

    /**
     * Demonstrates DeconvolutionMode.FAST on a clean QR code:
     *  - Uses HighQuality preset but forces FAST deconvolution (light-weight restoration).
     *  - Intended for images with minimal blur/shake.
     *  - Expected behavior: fastest restoration stage, enough for high-quality captures.
     */
    @Test
    public void read_QR_Deconvolution_FAST() throws Exception
    {
        String fileName = "qset_qr.png";
        BarCodeReader reader = new BarCodeReader(ExampleAssist.pathCombine(FOLDER, fileName), DecodeType.QR);

        QualitySettings qs = QualitySettings.getHighQuality();
        qs.setDeconvolution(DeconvolutionMode.FAST);
        reader.setQualitySettings(qs);

        ExampleAssist.assertRecognized(reader, fileName, 1, DecodeType.QR);
    }

    /**
     * Demonstrates DeconvolutionMode.NORMAL on a clean QR code:
     *  - Uses NormalQuality preset with NORMAL deconvolution.
     *  - Balanced option when some mild blur/noise may exist.
     *  - Expected behavior: moderate cost, good default for typical mobile photos.
     */
    @Test
    public void read_QR_Deconvolution_NORMAL() throws Exception
    {
        String fileName = "qset_qr.png";
        BarCodeReader reader = new BarCodeReader(ExampleAssist.pathCombine(FOLDER, fileName), DecodeType.QR);

        QualitySettings qs = QualitySettings.getNormalQuality();
        qs.setDeconvolution(DeconvolutionMode.NORMAL);
        reader.setQualitySettings(qs);

        ExampleAssist.assertRecognized(reader, fileName, 1, DecodeType.QR);
    }

    /**
     * Demonstrates DeconvolutionMode.SLOW on a clean QR code:
     *  - Uses HighQuality preset with the strongest (SLOW) restoration methods.
     *  - Intended for heavily blurred/low-quality inputs; slowest but most powerful.
     *  - Expected behavior: the most robust deconvolution pipeline.
     */
    @Test
    public void read_QR_Deconvolution_SLOW() throws Exception
    {
        String fileName = "qset_qr.png";
        BarCodeReader reader = new BarCodeReader(ExampleAssist.pathCombine(FOLDER, fileName), DecodeType.QR);

        QualitySettings qs = QualitySettings.getHighQuality();
        qs.setDeconvolution(DeconvolutionMode.SLOW);
        reader.setQualitySettings(qs);

        ExampleAssist.assertRecognized(reader, fileName, 1, DecodeType.QR);
    }

    // ==================== Preset + targeted overrides ====================

    /**
     * Demonstrates starting from a fast preset and then applying targeted overrides:
     *  - Base: HighPerformance (aims for speed on clean inputs).
     *  - Overrides:
     *      XDimension = SMALL           -> look for smaller bars/cells (tiny modules)
     *      MinimalXDimension = 1.0 px   -> minimal expected module size in pixels
     *      BarcodeQuality = LOW         -> enable heavy methods for low-quality / damaged bars
     *      Deconvolution = SLOW         -> strongest restoration for blur/degradation
     *  - Intended use: when you expect very small and/or degraded 1D symbols.
     *  - Trade-off: slower but more tolerant than pure HighPerformance.
     */
    @Test
    public void read_Code128_PresetWithOverrides_forSmallAndLowQuality() throws Exception
    {
        String fileName = "qset_code128.png";
        BarCodeReader reader = new BarCodeReader(ExampleAssist.pathCombine(FOLDER, fileName), DecodeType.CODE_128);

        QualitySettings qs = QualitySettings.getHighPerformance();
        qs.setXDimension(XDimensionMode.SMALL);
        qs.setMinimalXDimension(1.0f);
        qs.setBarcodeQuality(BarcodeQualityMode.LOW);
        qs.setDeconvolution(DeconvolutionMode.SLOW);
        reader.setQualitySettings(qs);

        ExampleAssist.assertRecognized(reader, fileName, 1, DecodeType.CODE_128);
    }
}
