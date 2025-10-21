package com.aspose.barcode.guide.recognition.quality_settings;

import com.aspose.barcode.barcoderecognition.*;
import com.aspose.barcode.generation.*;
import com.aspose.barcode.guide.common.ExampleAssist;
import com.aspose.barcode.guide.common.Generator;
import com.aspose.barcode.guide.common.LicenseAssist;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

import static com.aspose.barcode.guide.common.ExampleAssist.checkOrCreateImage;

/**
 * Demonstrates various QualitySettings presets and custom configurations
 * for Aspose.BarCode recognition.
 */
public class QualitySettingsExample {

    private static final String IMAGES_FOLDER =
            ExampleAssist.getOrCreateResourceFolderPath("recognition", "quality_settings");

    @BeforeClass
    public void setUp() throws Exception {
        LicenseAssist.setupLicense();
        Files.createDirectories(Paths.get(IMAGES_FOLDER));
    }

    // --- Code128 with NormalQuality ---
    @Test
    public void readCode128NormalQuality() throws Exception {
        checkOrCreateImage(IMAGES_FOLDER, "code128.png", this::generateCode128);
        QualitySettings qs = QualitySettings.getNormalQuality();
        BarCodeReader reader = new BarCodeReader(getPath("code128.png"), DecodeType.CODE_128);
        reader.setQualitySettings(qs);
        ExampleAssist.assertRecognized(reader, "code128.png", 1, DecodeType.CODE_128);
    }

    // --- QR with HighPerformance ---
    @Test
    public void readQrHighPerformance() throws Exception {
        checkOrCreateImage(IMAGES_FOLDER, "qrcode.png", this::generateQr);
        QualitySettings qs = QualitySettings.getHighPerformance();
        BarCodeReader reader = new BarCodeReader(getPath("qrcode.png"), DecodeType.QR);
        reader.setQualitySettings(qs);
        ExampleAssist.assertRecognized(reader, "qrcode.png", 1, DecodeType.QR);
    }

    // --- DataMatrix with HighQuality ---
    @Test
    public void readDataMatrixHighQuality() throws Exception {
        checkOrCreateImage(IMAGES_FOLDER, "datamatrix.png", this::generateDataMatrix);
        QualitySettings qs = QualitySettings.getHighQuality();
        BarCodeReader reader = new BarCodeReader(getPath("datamatrix.png"), DecodeType.DATA_MATRIX);
        reader.setQualitySettings(qs);
        ExampleAssist.assertRecognized(reader, "datamatrix.png", 1, DecodeType.DATA_MATRIX);
    }

    // --- PDF417 with MaxQuality ---
    @Test
    public void readPdf417MaxQuality() throws Exception {
        checkOrCreateImage(IMAGES_FOLDER, "pdf417.png", this::generatePdf417);
        QualitySettings qs = QualitySettings.getMaxQuality();
        BarCodeReader reader = new BarCodeReader(getPath("pdf417.png"), DecodeType.PDF_417);
        reader.setQualitySettings(qs);
        ExampleAssist.assertRecognized(reader, "pdf417.png", 1, DecodeType.PDF_417);
    }

    // --- EAN-13 via TYPES_1D ---
    @Test
    public void readEan13All1dNormalQuality() throws Exception {
        checkOrCreateImage(IMAGES_FOLDER, "ean13.png", this::generateEan13);
        QualitySettings qs = QualitySettings.getNormalQuality();
        BarCodeReader reader = new BarCodeReader(getPath("ean13.png"), DecodeType.TYPES_1D);
        reader.setQualitySettings(qs);
        ExampleAssist.assertRecognized(reader, "ean13.png", 1, DecodeType.EAN_13);
    }

    // --- Aztec via TYPES_2D ---
    @Test
    public void readAztecAll2dNormalQuality() throws Exception {
        checkOrCreateImage(IMAGES_FOLDER, "aztec.png", this::generateAztec);
        QualitySettings qs = QualitySettings.getNormalQuality();
        BarCodeReader reader = new BarCodeReader(getPath("aztec.png"), DecodeType.TYPES_2D);
        reader.setQualitySettings(qs);
        ExampleAssist.assertRecognized(reader, "aztec.png", 1, DecodeType.AZTEC);
    }

    // --- Custom: NormalQuality + InverseImage (for inverted QR) ---
    @Test
    public void readQrNormalQualityInverseEnabled() throws Exception {
        checkOrCreateImage(IMAGES_FOLDER, "qrcode_inverted.png", this::generateQrInverted);
        QualitySettings qs = QualitySettings.getNormalQuality();
        qs.setInverseImage(InverseImageMode.ENABLED);
        BarCodeReader reader = new BarCodeReader(getPath("qrcode_inverted.png"), DecodeType.QR);
        reader.setQualitySettings(qs);
        ExampleAssist.assertRecognized(reader, "qrcode_inverted.png", 1, DecodeType.QR);
    }

    // --- Custom: HighPerformance + Small XDimension for tiny barcodes ---
    @Test
    public void readCode128HighPerformanceTinyX() throws Exception {
        checkOrCreateImage(IMAGES_FOLDER, "code128_small.png", this::generateCode128Small);
        QualitySettings qs = QualitySettings.getHighPerformance();
        qs.setXDimension(XDimensionMode.SMALL);
        qs.setMinimalXDimension(1);
        BarCodeReader reader = new BarCodeReader(getPath("code128_small.png"), DecodeType.CODE_128);
        reader.setQualitySettings(qs);
        ExampleAssist.assertRecognized(reader, "code128_small.png", 1, DecodeType.CODE_128);
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
