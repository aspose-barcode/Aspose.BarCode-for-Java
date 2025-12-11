package com.aspose.barcode.guide.recognition.quality_settings;

import com.aspose.barcode.barcoderecognition.*;
import com.aspose.barcode.generation.*;
import com.aspose.barcode.guide.common.ExampleAssist;
import com.aspose.barcode.guide.common.LicenseAssist;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

import static com.aspose.barcode.guide.common.ExampleAssist.checkOrCreateImage;

/**
 * Demonstrates the use of QualitySettings presets and fine-tuning options
 * to optimize Aspose.BarCode recognition for different barcode types and
 * image conditions.
 *
 * Each test shows how recognition quality and performance can be adjusted
 * for specific symbologies or image properties.
 */
public class QualitySettingsExample {

    private static final String IMAGES_FOLDER =
            ExampleAssist.getOrCreateResourceFolderPath("recognition", "quality_settings");

    @BeforeClass
    public void setUp() throws Exception {
        LicenseAssist.setupLicense();
        Files.createDirectories(Paths.get(IMAGES_FOLDER));
    }

    /**
     * Example 1:
     * Recognizes a standard Code128 barcode using the default NormalQuality preset.
     *
     * This preset offers a good balance between speed and accuracy and is
     * suitable for most clean, high-contrast images.
     */
    @Test
    public void readCode128NormalQuality() throws Exception {
        checkOrCreateImage(IMAGES_FOLDER, "code128.png", this::generateCode128);
        QualitySettings qs = QualitySettings.getNormalQuality();
        BarCodeReader reader = new BarCodeReader(getPath("code128.png"), DecodeType.CODE_128);
        reader.setQualitySettings(qs);
        ExampleAssist.assertRecognized(reader, "code128.png", 1, DecodeType.CODE_128);
    }

    /**
     * Example 2:
     * Recognizes a QR Code using the HighPerformance preset.
     *
     * HighPerformance increases decoding speed by using fewer preprocessing
     * filters and simplifications, making it suitable for large batches of
     * clean QR images where throughput is more important than precision.
     */
    @Test
    public void readQrHighPerformance() throws Exception {
        checkOrCreateImage(IMAGES_FOLDER, "qrcode.png", this::generateQr);
        QualitySettings qs = QualitySettings.getHighPerformance();
        BarCodeReader reader = new BarCodeReader(getPath("qrcode.png"), DecodeType.QR);
        reader.setQualitySettings(qs);
        ExampleAssist.assertRecognized(reader, "qrcode.png", 1, DecodeType.QR);
    }

    /**
     * Example 3:
     * Recognizes a DataMatrix barcode using the HighQuality preset.
     *
     * This preset applies advanced noise reduction and morphological filters,
     * improving accuracy on low-quality or slightly distorted images.
     */
    @Test
    public void readDataMatrixHighQuality() throws Exception {
        checkOrCreateImage(IMAGES_FOLDER, "datamatrix.png", this::generateDataMatrix);
        QualitySettings qs = QualitySettings.getHighQuality();
        BarCodeReader reader = new BarCodeReader(getPath("datamatrix.png"), DecodeType.DATA_MATRIX);
        reader.setQualitySettings(qs);
        ExampleAssist.assertRecognized(reader, "datamatrix.png", 1, DecodeType.DATA_MATRIX);
    }

    /**
     * Example 4:
     * Recognizes a PDF417 barcode using the MaxQuality preset.
     *
     * MaxQuality applies all available filters and scanning algorithms.
     * It provides the highest recognition accuracy at the cost of speed,
     * useful for degraded, skewed, or noisy images.
     */
    @Test
    public void readPdf417MaxQuality() throws Exception {
        checkOrCreateImage(IMAGES_FOLDER, "pdf417.png", this::generatePdf417);
        QualitySettings qs = QualitySettings.getMaxQuality();
        BarCodeReader reader = new BarCodeReader(getPath("pdf417.png"), DecodeType.PDF_417);
        reader.setQualitySettings(qs);
        ExampleAssist.assertRecognized(reader, "pdf417.png", 1, DecodeType.PDF_417);
    }

    /**
     * Example 5:
     * Recognizes a 1D EAN-13 barcode using the NormalQuality preset and
     * DecodeType.TYPES_1D to detect any 1D symbology automatically.
     *
     * This demonstrates how to handle mixed 1D barcode sets with a single reader.
     */
    @Test
    public void readEan13All1dNormalQuality() throws Exception {
        checkOrCreateImage(IMAGES_FOLDER, "ean13.png", this::generateEan13);
        QualitySettings qs = QualitySettings.getNormalQuality();
        BarCodeReader reader = new BarCodeReader(getPath("ean13.png"), DecodeType.TYPES_1D);
        reader.setQualitySettings(qs);
        ExampleAssist.assertRecognized(reader, "ean13.png", 1, DecodeType.EAN_13);
    }

    /**
     * Example 6:
     * Recognizes an Aztec barcode using the NormalQuality preset and
     * DecodeType.TYPES_2D, which allows detection of any 2D symbology.
     *
     * This approach is useful when the input may contain different 2D formats.
     */
    @Test
    public void readAztecAll2dNormalQuality() throws Exception {
        checkOrCreateImage(IMAGES_FOLDER, "aztec.png", this::generateAztec);
        QualitySettings qs = QualitySettings.getNormalQuality();
        BarCodeReader reader = new BarCodeReader(getPath("aztec.png"), DecodeType.TYPES_2D);
        reader.setQualitySettings(qs);
        ExampleAssist.assertRecognized(reader, "aztec.png", 1, DecodeType.AZTEC);
    }

    /**
     * Example 7:
     * Recognizes an inverted (light-on-dark) QR Code image by enabling
     * InverseImageMode in QualitySettings.
     *
     * This option allows recognition of barcodes printed with reversed contrast
     * or photographed under strong light reflections.
     */
    @Test
    public void readQrNormalQualityInverseEnabled() throws Exception {
        checkOrCreateImage(IMAGES_FOLDER, "qrcode_inverted.png", this::generateQrInverted);
        QualitySettings qs = QualitySettings.getNormalQuality();
        qs.setInverseImage(InverseImageMode.ENABLED);
        BarCodeReader reader = new BarCodeReader(getPath("qrcode_inverted.png"), DecodeType.QR);
        reader.setQualitySettings(qs);
        ExampleAssist.assertRecognized(reader, "qrcode_inverted.png", 1, DecodeType.QR);
    }

    /**
     * Example 8:
     * Recognizes a very small Code128 barcode using the HighPerformance preset,
     * while reducing minimal XDimension.
     *
     * Lowering XDimension allows the reader to capture narrow bars and
     * small-scale codes, which is helpful for high-density barcodes printed
     * on small surfaces.
     */
    @Test
    public void readCode128HighPerformanceTinyX() throws Exception {
        checkOrCreateImage(IMAGES_FOLDER, "code128_small.png", this::generateCode128Small);
        QualitySettings qs = QualitySettings.getHighPerformance();
        qs.setXDimension(XDimensionMode.USE_MINIMAL_X_DIMENSION);
        qs.setMinimalXDimension(1);
        BarCodeReader reader = new BarCodeReader(getPath("code128_small.png"), DecodeType.CODE_128);
        reader.setQualitySettings(qs);
        ExampleAssist.assertRecognized(reader, "code128_small.png", 1, DecodeType.CODE_128);
    }

    /**
     * Example 9:
     * Demonstrates how to recognize low-quality barcodes by setting
     * BarcodeQualityMode.LOW manually.
     *
     * Semantics:
     * - LOW does not mean “maximum effort” by itself. It tells the engine that
     *   barcodes are expected to be low quality (blurred, noisy, weak contrast),
     *   so the internal recognition path should be more tolerant to such inputs.
     *
     * Usage:
     * - Combined with a suitable preset (for example, HighPerformance here for
     *   speed or HighQuality for more robust scenarios), this hint helps the engine
     *   choose algorithms appropriate for degraded images.
     */
    @Test
    public void readCode128LowQualityMode() throws Exception {
        checkOrCreateImage(IMAGES_FOLDER, "code128_blurred.png", this::generateCode128);
        QualitySettings qs = QualitySettings.getHighPerformance();
        qs.setBarcodeQuality(BarcodeQualityMode.LOW);
        BarCodeReader reader = new BarCodeReader(getPath("code128_blurred.png"), DecodeType.CODE_128);
        reader.setQualitySettings(qs);
        ExampleAssist.assertRecognized(reader, "code128_blurred.png", 1, DecodeType.CODE_128);
    }

    /**
     * Example 10:
     * Demonstrates recognition on images with complex backgrounds (e.g., color or texture)
     * by enabling ComplexBackgroundMode. This helps when the barcode is printed on a
     * non-uniform or noisy surface.
     */
    @Test
    public void readQrWithComplexBackground() throws Exception {
        checkOrCreateImage(IMAGES_FOLDER, "qrcode_colored.png", this::generateQr);
        QualitySettings qs = QualitySettings.getMaxQuality();
        qs.setComplexBackground(ComplexBackgroundMode.ENABLED);
        BarCodeReader reader = new BarCodeReader(getPath("qrcode_colored.png"), DecodeType.QR);
        reader.setQualitySettings(qs);
        ExampleAssist.assertRecognized(reader, "qrcode_colored.png", 1, DecodeType.QR);
    }

    /**
     * Example 11:
     * Demonstrates how to recognize damaged or incorrect barcodes by enabling
     * AllowIncorrectBarcodes. This can extract partially corrupted data.
     */
    @Test
    public void readDamagedCodeAllowIncorrect() throws Exception {
        checkOrCreateImage(IMAGES_FOLDER, "code128_damaged.png", this::generateCode128);
        QualitySettings qs = QualitySettings.getMaxQuality();
        qs.setAllowIncorrectBarcodes(true);
        BarCodeReader reader = new BarCodeReader(getPath("code128_damaged.png"), DecodeType.CODE_128);
        reader.setQualitySettings(qs);
        ExampleAssist.assertRecognized(reader, "code128_damaged.png", 1, DecodeType.CODE_128);
    }

    /**
     * Example 12:
     * Demonstrates how to verify default parameter values for built-in QualitySettings presets
     * and how to partially override them for fine-tuning recognition behavior.
     * `getNormalQuality()` — «Suitable for the most of barcodes».
     * `getHighQuality()` — «developed for low quality barcodes».
     * `getMaxQuality()` — «recognize all possible barcodes, even incorrect».
     */
    @Test
    public void verifyPresetsAndPartialOverride() throws Exception {
        // --- 1. Verify built-in preset values ---
        QualitySettings normal = QualitySettings.getNormalQuality();
        Assert.assertEquals(normal.getXDimension(), XDimensionMode.NORMAL, "NormalQuality: XDimension");
        Assert.assertEquals(normal.getMinimalXDimension(), 1.0f, "NormalQuality: MinimalXDimension");
        Assert.assertEquals(normal.getBarcodeQuality(), BarcodeQualityMode.NORMAL, "NormalQuality: BarcodeQuality");
        Assert.assertEquals(normal.getDeconvolution(), DeconvolutionMode.NORMAL, "NormalQuality: Deconvolution");
        Assert.assertEquals(normal.getInverseImage(), InverseImageMode.DISABLED, "NormalQuality: InverseImage");
        Assert.assertEquals(normal.getComplexBackground(), ComplexBackgroundMode.DISABLED, "NormalQuality: ComplexBackground");
        Assert.assertFalse(normal.getAllowIncorrectBarcodes(), "NormalQuality: AllowIncorrectBarcodes");

        QualitySettings highPerformance = QualitySettings.getHighPerformance();
        Assert.assertEquals(highPerformance.getDeconvolution(), DeconvolutionMode.FAST, "HighPerformance: Deconvolution");
        Assert.assertEquals(highPerformance.getBarcodeQuality(), BarcodeQualityMode.HIGH, "HighPerformance: BarcodeQuality");

        QualitySettings highQuality = QualitySettings.getHighQuality();
        Assert.assertEquals(highQuality.getInverseImage(), InverseImageMode.ENABLED, "HighQuality: InverseImage");
        Assert.assertEquals(highQuality.getDeconvolution(), DeconvolutionMode.SLOW, "HighQuality: Deconvolution");

        QualitySettings maxQuality = QualitySettings.getMaxQuality();
        Assert.assertEquals(maxQuality.getComplexBackground(), ComplexBackgroundMode.ENABLED, "MaxQuality: ComplexBackground");

        // --- 2. Demonstrate partial override ---
        QualitySettings custom = QualitySettings.getHighPerformance();
        custom.setInverseImage(InverseImageMode.ENABLED);   // support inverse images
        custom.setAllowIncorrectBarcodes(true);             // allow partially damaged codes
        custom.setXDimension(XDimensionMode.USE_MINIMAL_X_DIMENSION); // enable MinimalXDimension hint
        custom.setMinimalXDimension(2.5f);                  // adjust minimal bar width

        // --- 3. Apply to a real barcode and verify recognition still works ---
        checkOrCreateImage(IMAGES_FOLDER, "code128_custom.png", this::generateCode128);
        BarCodeReader reader = new BarCodeReader(getPath("code128_custom.png"), DecodeType.CODE_128);
        reader.setQualitySettings(custom);

        ExampleAssist.assertRecognized(reader, "code128_custom.png", 1, DecodeType.CODE_128);

        // --- 4. Check that overridden values are retained inside the reader ---
        QualitySettings appliedQualitySettings = reader.getQualitySettings();
        Assert.assertEquals(appliedQualitySettings.getInverseImage(), InverseImageMode.ENABLED, "Custom: InverseImage");
        Assert.assertTrue(appliedQualitySettings.getAllowIncorrectBarcodes(), "Custom: AllowIncorrectBarcodes");
        Assert.assertEquals(appliedQualitySettings.getMinimalXDimension(), 2.5f, "Custom: MinimalXDimension");
    }


    // ====================== Helpers ======================

    private String getPath(String fileName) {
        return Paths.get(IMAGES_FOLDER, fileName).toString();
    }

    // ====================== Image Generators ======================

    private void generateCode128(String path) throws IOException {
        BarcodeGenerator gen = new BarcodeGenerator(EncodeTypes.CODE_128, "CODE128-QUALITY");
        gen.getParameters().getBarcode().getXDimension().setPixels(2);
        gen.getParameters().getBarcode().getBarHeight().setPixels(60);
        gen.save(path, BarCodeImageFormat.PNG);
    }

    private void generateQr(String path) throws IOException {
        BarcodeGenerator gen = new BarcodeGenerator(EncodeTypes.QR, "QR-QUALITY-DEMO");
        gen.getParameters().getBarcode().getXDimension().setPixels(4);
        gen.save(path, BarCodeImageFormat.PNG);
    }

    private void generateQrInverted(String path) throws IOException {
        String tmp = path + "_tmp.png";
        BarcodeGenerator gen = new BarcodeGenerator(EncodeTypes.QR, "QR-INVERTED");
        gen.getParameters().getBarcode().getXDimension().setPixels(4);
        gen.save(tmp, BarCodeImageFormat.PNG);

        BufferedImage src = ImageIO.read(Paths.get(tmp).toFile());
        BufferedImage inv = new BufferedImage(src.getWidth(), src.getHeight(), BufferedImage.TYPE_INT_ARGB);
        for (int y = 0; y < src.getHeight(); y++) {
            for (int x = 0; x < src.getWidth(); x++) {
                int rgba = src.getRGB(x, y);
                inv.setRGB(x, y, (~rgba) | 0xFF000000);
            }
        }
        ImageIO.write(inv, "PNG", Paths.get(path).toFile());
        Files.deleteIfExists(Paths.get(tmp));
    }

    private void generateDataMatrix(String path) throws IOException {
        BarcodeGenerator gen = new BarcodeGenerator(EncodeTypes.DATA_MATRIX, "DM-QUALITY-1234");
        gen.getParameters().getBarcode().getXDimension().setPixels(3);
        gen.save(path, BarCodeImageFormat.PNG);
    }

    private void generatePdf417(String path) throws IOException {
        BarcodeGenerator gen = new BarcodeGenerator(EncodeTypes.PDF_417, "PDF417-QUALITY");
        gen.getParameters().getBarcode().getXDimension().setPixels(2);
        gen.getParameters().getBarcode().getBarHeight().setPixels(60);
        gen.save(path, BarCodeImageFormat.PNG);
    }

    private void generateEan13(String path) throws IOException {
        BarcodeGenerator gen = new BarcodeGenerator(EncodeTypes.EAN_13, "5901234123457");
        gen.getParameters().getBarcode().getXDimension().setPixels(2);
        gen.save(path, BarCodeImageFormat.PNG);
    }

    private void generateAztec(String path) throws IOException {
        BarcodeGenerator gen = new BarcodeGenerator(EncodeTypes.AZTEC, "AZTEC-QUALITY-DEMO");
        gen.getParameters().getBarcode().getXDimension().setPixels(4);
        gen.save(path, BarCodeImageFormat.PNG);
    }

    private void generateCode128Small(String path) throws IOException {
        BarcodeGenerator gen = new BarcodeGenerator(EncodeTypes.CODE_128, "C128-SMALL");
        gen.getParameters().getBarcode().getXDimension().setPixels(1);
        gen.getParameters().getBarcode().getBarHeight().setPixels(40);
        gen.save(path, BarCodeImageFormat.PNG);
    }
}
