package com.aspose.barcode.guide.quickstart;

import com.aspose.barcode.generation.*;
import com.aspose.barcode.guide.common.ExampleAssist;
import com.aspose.barcode.guide.common.LicenseAssist;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.awt.Color;
import java.nio.file.*;

/**
 * Quick Generation Examples.
 * All tests write PNGs into the 'quick_start' resource folder.
 *
 * Guidelines:
 * - Use consistent units: millimeters for physical dimensions, pixels only when needed
 * - Follow best practices from documentation
 * - Keep tests simple and focused
 */
public class QuickGenerationExamples {

    private static final String folder = ExampleAssist.getOrCreateResourceFolderPath("quick_start");

    @BeforeClass
    public void setUp() {
        LicenseAssist.setupLicense();
    }

    // ========== Linear Barcodes ==========

    @Test
    public void generate_Code128() throws Exception {
        BarcodeGenerator gen = new BarcodeGenerator(EncodeTypes.CODE_128, "ABC-12345");

        // Standard settings
        gen.getParameters().getBarcode().getXDimension().setMillimeters(0.3f);
        gen.getParameters().getBarcode().getBarHeight().setMillimeters(10.0f);
        gen.getParameters().getBarcode().getBarWidthReduction().setMillimeters(0.1f);
                saveAndAssert(gen, "code128.png");
    }

    @Test
    public void generate_EAN13() throws Exception {
        // Valid EAN-13 with checksum
        BarcodeGenerator gen = new BarcodeGenerator(EncodeTypes.EAN_13, "5901234123457");

        // Standard EAN-13 X-dimension
        gen.getParameters().getBarcode().getXDimension().setMillimeters(0.33f);
        gen.getParameters().getBarcode().getBarHeight().setMillimeters(25.0f);

        saveAndAssert(gen, "ean13.png");
    }

    @Test
    public void generate_UPCA() throws Exception {
        // Valid UPC-A (12 digits)
        BarcodeGenerator gen = new BarcodeGenerator(EncodeTypes.UPCA, "036000291452");

        gen.getParameters().getBarcode().getXDimension().setMillimeters(0.33f);
        gen.getParameters().getBarcode().getBarHeight().setMillimeters(25.0f);

        saveAndAssert(gen, "upca.png");
    }

    @Test
    public void generate_Code39() throws Exception {
        BarcodeGenerator gen = new BarcodeGenerator(EncodeTypes.CODE_39, "ASPOSE-123");

        gen.getParameters().getBarcode().getXDimension().setMillimeters(0.4f);
        gen.getParameters().getBarcode().getBarHeight().setMillimeters(12.0f);

        // Show code text above
        gen.getParameters().getBarcode().getCodeTextParameters()
                .setLocation(CodeLocation.ABOVE);

        saveAndAssert(gen, "code39.png");
    }

    @Test
    public void generate_ITF14() throws Exception {
        // ITF-14 requires 14 digits
        BarcodeGenerator gen = new BarcodeGenerator(EncodeTypes.ITF_14, "12345678901231");

        // ITF-14 standard X-dimension
        gen.getParameters().getBarcode().getXDimension().setMillimeters(0.495f);
        gen.getParameters().getBarcode().getBarHeight().setMillimeters(32.0f);

        saveAndAssert(gen, "itf14.png");
    }

    // ========== Two-Dimensional Barcodes ==========

    @Test
    public void generate_QR() throws Exception {
        BarcodeGenerator gen = new BarcodeGenerator(EncodeTypes.QR, "https://www.aspose.com");

        gen.getParameters().getBarcode().getXDimension().setMillimeters(0.5f);
        gen.getParameters().getBarcode().getQR().setQrEncodeMode(QREncodeMode.AUTO);
        gen.getParameters().getBarcode().getQR().setQrErrorLevel(QRErrorLevel.LEVEL_M);

        saveAndAssert(gen, "qrcode.png");
    }

    @Test
    public void generate_QR_withUnicode() throws Exception {
        // QR with Unicode data (Japanese)
        BarcodeGenerator gen = new BarcodeGenerator(EncodeTypes.QR, "データ");

        gen.getParameters().getBarcode().getXDimension().setMillimeters(0.5f);
        gen.getParameters().getBarcode().getQR().setQrEncodeMode(QREncodeMode.ECI);
        gen.getParameters().getBarcode().getQR().setQrECIEncoding(ECIEncodings.UTF8);

        saveAndAssert(gen, "qrcode_unicode.png");
    }

    @Test
    public void generate_DataMatrix() throws Exception {
        BarcodeGenerator gen = new BarcodeGenerator(EncodeTypes.DATA_MATRIX, "DMX-INV-000042");

        gen.getParameters().getBarcode().getXDimension().setMillimeters(0.4f);
        gen.getParameters().getBarcode().getDataMatrix()
                .setDataMatrixEncodeMode(DataMatrixEncodeMode.AUTO);

        // Optional: enable anti-aliasing for better quality
        gen.getParameters().setUseAntiAlias(true);
        gen.getParameters().setRotationAngle(15f);
        gen.getParameters().getBarcode().getCodeTextParameters().setLocation(CodeLocation.BELOW);
        gen.getParameters().getBarcode().getDataMatrix().setDataMatrixEncodeMode(
                com.aspose.barcode.generation.DataMatrixEncodeMode.AUTO);
        saveAndAssert(gen, "datamatrix.png");
    }

    @Test
    public void generate_PDF417() throws Exception {
        BarcodeGenerator gen = new BarcodeGenerator(
                EncodeTypes.PDF_417,
                "Shipment 42: 10x Boxes"
        );

        gen.getParameters().getBarcode().getXDimension().setMillimeters(0.6f);
        gen.getParameters().getBarcode().getPdf417().setColumns(3);
        gen.getParameters().getBarcode().getPdf417().setRows(6);

        // Add padding
        gen.getParameters().getBarcode().getPadding().getLeft().setMillimeters(2.0f);
        gen.getParameters().getBarcode().getPadding().getRight().setMillimeters(2.0f);

        saveAndAssert(gen, "pdf417.png");
    }

    @Test
    public void generate_PDF417_truncated() throws Exception {
        BarcodeGenerator gen = new BarcodeGenerator(
                EncodeTypes.PDF_417,
                "Compact PDF417"
        );

        gen.getParameters().getBarcode().getXDimension().setMillimeters(0.6f);
        gen.getParameters().getBarcode().getPdf417().setColumns(3);
        gen.getParameters().getBarcode().getPdf417().setPdf417Truncate(true);

        saveAndAssert(gen, "pdf417_truncated.png");
    }

    @Test
    public void generate_Aztec() throws Exception {
        BarcodeGenerator gen = new BarcodeGenerator(EncodeTypes.AZTEC, "AZTEC-Order#123");

        gen.getParameters().getBarcode().getXDimension().setMillimeters(0.5f);
        gen.getParameters().getBarcode().getAztec().setAztecErrorLevel(50);

        saveAndAssert(gen, "aztec.png");
    }

    // ========== Postal Barcodes ==========

    @Test
    public void generate_Postnet() throws Exception {
        // USPS Postnet: ZIP+4 (9 digits) or ZIP (5 digits)
        BarcodeGenerator gen = new BarcodeGenerator(EncodeTypes.POSTNET, "205001234");

        gen.getParameters().getBarcode().getXDimension().setMillimeters(0.5f);
        gen.getParameters().getBarcode().getBarHeight().setMillimeters(3.0f);

        saveAndAssert(gen, "postnet.png");
    }

    @Test
    public void generate_Planet() throws Exception {
        // USPS Planet
        BarcodeGenerator gen = new BarcodeGenerator(EncodeTypes.PLANET, "205001234");

        gen.getParameters().getBarcode().getXDimension().setMillimeters(0.5f);
        gen.getParameters().getBarcode().getBarHeight().setMillimeters(3.0f);

        saveAndAssert(gen, "planet.png");
    }

    @Test
    public void generate_IntelligentMail() throws Exception {
        // USPS Intelligent Mail (OneCode)
        // Format: 20 digits (tracking) + 11 digits (routing) = 31 digits
        String imb = "01234567094987654321" + "01234567891";

        BarcodeGenerator gen = new BarcodeGenerator(EncodeTypes.ONE_CODE, imb);
        gen.getParameters().getBarcode().getXDimension().setMillimeters(0.5f);
        gen.getParameters().getBarcode().getBarHeight().setMillimeters(3.0f);

        saveAndAssert(gen, "intelligent_mail.png");
    }

    // ========== GS1 Barcodes ==========

    @Test
    public void generate_GS1_128_basic() throws Exception {
        // GS1-128 with Application Identifiers
        BarcodeGenerator gen = new BarcodeGenerator(
                EncodeTypes.GS_1_CODE_128,
                "(01)09501101530008(17)251231(10)BATCH-42"
        );

        gen.getParameters().getBarcode().getXDimension().setMillimeters(0.33f);
        gen.getParameters().getBarcode().getBarHeight().setMillimeters(15.0f);
        gen.getParameters().setResolution(300.0f);

        saveAndAssert(gen, "gs1_128.png");
    }

    @Test
    public void generate_GS1_128_complete() throws Exception {
        // Complex GS1 data with multiple AIs
        String gs1Data = "(01)09512345678900" +  // GTIN
                "(17)260630" +           // Expiration date
                "(10)LOT2025A" +         // Batch number
                "(21)SERIAL123456";      // Serial number

        BarcodeGenerator gen = new BarcodeGenerator(EncodeTypes.GS_1_CODE_128, gs1Data);

        gen.getParameters().getBarcode().getXDimension().setMillimeters(0.33f);
        gen.getParameters().getBarcode().getBarHeight().setMillimeters(16.0f);
        gen.getParameters().setResolution(300.0f);

        // Quiet zones
        gen.getParameters().getBarcode().getPadding().getLeft().setMillimeters(3.3f);
        gen.getParameters().getBarcode().getPadding().getRight().setMillimeters(3.3f);

        // Code text
        gen.getParameters().getBarcode().getCodeTextParameters().setLocation(CodeLocation.BELOW);
        gen.getParameters().getBarcode().getCodeTextParameters().getFont().setFamilyName("Arial");
        gen.getParameters().getBarcode().getCodeTextParameters().getFont().getSize().setPoint(9);

        saveAndAssert(gen, "gs1_128_complete.png");
    }

    @Test
    public void generate_GS1_DataMatrix() throws Exception {
        String gs1Data = "(01)12345678901231(21)SERIAL123";

        BarcodeGenerator gen = new BarcodeGenerator(EncodeTypes.GS_1_DATA_MATRIX, gs1Data);
        gen.getParameters().getBarcode().getXDimension().setMillimeters(0.4f);

        saveAndAssert(gen, "gs1_datamatrix.png");
    }

    @Test
    public void generate_GS1_QR() throws Exception {
        String gs1Data = "(01)12345678901231(10)ABC123";

        BarcodeGenerator gen = new BarcodeGenerator(EncodeTypes.GS_1_QR, gs1Data);
        gen.getParameters().getBarcode().getXDimension().setMillimeters(0.5f);

        saveAndAssert(gen, "gs1_qr.png");
    }

    // ========== Customization Examples ==========

    @Test
    public void customization_colorsAndFonts() throws Exception {
        BarcodeGenerator gen = new BarcodeGenerator(EncodeTypes.CODE_128, "CUSTOM-STYLE");

        // Colors
        gen.getParameters().setBackColor(Color.WHITE);
        gen.getParameters().getBarcode().setBarColor(Color.BLUE);

        // Dimensions
        gen.getParameters().getBarcode().getXDimension().setMillimeters(0.4f);
        gen.getParameters().getBarcode().getBarHeight().setMillimeters(15.0f);

        // Code text styling
        gen.getParameters().getBarcode().getCodeTextParameters().setLocation(CodeLocation.BELOW);
        gen.getParameters().getBarcode().getCodeTextParameters().setColor(Color.RED);
        gen.getParameters().getBarcode().getCodeTextParameters().getFont().setFamilyName("Arial");
        gen.getParameters().getBarcode().getCodeTextParameters().getFont().getSize().setPoint(12);
        gen.getParameters().getBarcode().getCodeTextParameters().getFont().setStyle(FontStyle.BOLD);

        // Padding
        gen.getParameters().getBarcode().getPadding().getLeft().setMillimeters(3.0f);
        gen.getParameters().getBarcode().getPadding().getRight().setMillimeters(3.0f);
        gen.getParameters().getBarcode().getPadding().getTop().setMillimeters(2.0f);
        gen.getParameters().getBarcode().getPadding().getBottom().setMillimeters(2.0f);

        saveAndAssert(gen, "custom_style.png");
    }

    @Test
    public void customization_multipleFormats() throws Exception {
        BarcodeGenerator gen = new BarcodeGenerator(EncodeTypes.CODE_128, "MULTI-FORMAT");

        gen.getParameters().getBarcode().getXDimension().setMillimeters(0.3f);
        gen.getParameters().getBarcode().getBarHeight().setMillimeters(12.0f);
        gen.getParameters().setResolution(300.0f);

        // Save in multiple formats
        Path base = Paths.get(folder);
        gen.save(base.resolve("format_test.png").toString(), BarCodeImageFormat.PNG);
        gen.save(base.resolve("format_test.svg").toString(), BarCodeImageFormat.SVG);
        gen.save(base.resolve("format_test.bmp").toString(), BarCodeImageFormat.BMP);
        gen.save(base.resolve("format_test.gif").toString(), BarCodeImageFormat.GIF);
        gen.save(base.resolve("format_test.tiff").toString(), BarCodeImageFormat.TIFF);

        // Verify PNG exists
        Assert.assertTrue(Files.exists(base.resolve("format_test.png")));
    }

    @Test
    public void customization_rotation() throws Exception {
        BarcodeGenerator gen = new BarcodeGenerator(EncodeTypes.CODE_128, "ROTATED");

        gen.getParameters().getBarcode().getXDimension().setMillimeters(0.3f);
        gen.getParameters().getBarcode().getBarHeight().setMillimeters(10.0f);

        // Test different rotation angles
        float[] angles = {0, 90, 180, 270};
        Path base = Paths.get(folder);

        for (float angle : angles) {
            gen.getParameters().setRotationAngle(angle);
            String fileName = "rotated_" + (int)angle + ".png";
            gen.save(base.resolve(fileName).toString(), BarCodeImageFormat.PNG);
            Assert.assertTrue(Files.exists(base.resolve(fileName)));
        }
    }

    @Test
    public void customization_resolution() throws Exception {
        BarcodeGenerator gen = new BarcodeGenerator(EncodeTypes.CODE_128, "HIGH-RES");

        gen.getParameters().getBarcode().getXDimension().setMillimeters(0.3f);
        gen.getParameters().getBarcode().getBarHeight().setMillimeters(12.0f);

        // High resolution for printing
        gen.getParameters().setResolution(300.0f);

        // Control image size
        gen.getParameters().getImageWidth().setPixels(500);
        gen.getParameters().getImageHeight().setPixels(200);
        gen.getParameters().setAutoSizeMode(AutoSizeMode.NEAREST);

        saveAndAssert(gen, "high_resolution.png");
    }

    // ========== Performance: Batch Generation ==========

    @Test
    public void performance_batchGeneration() throws Exception {
        String[] codes = {"PROD001", "PROD002", "PROD003", "PROD004", "PROD005"};

        // Create generator once and reuse
        BarcodeGenerator gen = new BarcodeGenerator(EncodeTypes.CODE_128, codes[0]);

        // Set common parameters once
        gen.getParameters().getBarcode().getXDimension().setMillimeters(0.3f);
        gen.getParameters().getBarcode().getBarHeight().setMillimeters(10.0f);
        gen.getParameters().setResolution(300.0f);

        Path base = Paths.get(folder);

        // Generate batch
        for (String code : codes) {
            gen.setCodeText(code);  // Only change the code text
            String fileName = "batch_" + code + ".png";
            gen.save(base.resolve(fileName).toString(), BarCodeImageFormat.PNG);
            Assert.assertTrue(Files.exists(base.resolve(fileName)));
        }
    }

    // ========== Complete Example: Product Label ==========

    @Test
    public void complete_productLabel() throws Exception {
        String ean13Code = "5901234123457";
        String productName = "Premium Coffee Beans";

        BarcodeGenerator gen = new BarcodeGenerator(EncodeTypes.EAN_13, ean13Code);

        // Professional label dimensions
        gen.getParameters().getBarcode().getXDimension().setMillimeters(0.33f);
        gen.getParameters().getBarcode().getBarHeight().setMillimeters(25.0f);

        // Print resolution
        gen.getParameters().setResolution(300.0f);

        // Code text styling
        gen.getParameters().getBarcode().getCodeTextParameters().setLocation(CodeLocation.BELOW);
        gen.getParameters().getBarcode().getCodeTextParameters().setAlignment(TextAlignment.CENTER);
        gen.getParameters().getBarcode().getCodeTextParameters().getFont().setFamilyName("Arial");
        gen.getParameters().getBarcode().getCodeTextParameters().getFont().getSize().setPoint(10);

        // Quiet zones
        gen.getParameters().getBarcode().getPadding().getLeft().setMillimeters(3.3f);
        gen.getParameters().getBarcode().getPadding().getRight().setMillimeters(3.3f);
        gen.getParameters().getBarcode().getPadding().getTop().setMillimeters(2.0f);
        gen.getParameters().getBarcode().getPadding().getBottom().setMillimeters(2.0f);

        // Product name caption
        gen.getParameters().getCaptionAbove().setVisible(true);
        gen.getParameters().getCaptionAbove().setText(productName);
        gen.getParameters().getCaptionAbove().setAlignment(TextAlignment.CENTER);
        gen.getParameters().getCaptionAbove().getFont().setFamilyName("Arial");
        gen.getParameters().getCaptionAbove().getFont().getSize().setPoint(12);
        gen.getParameters().getCaptionAbove().getFont().setStyle(FontStyle.BOLD);
        gen.getParameters().getCaptionAbove().getPadding().getLeft().setMillimeters(5);
        gen.getParameters().getCaptionAbove().getPadding().getRight().setMillimeters(5);

        // Clean appearance
        gen.getParameters().setBackColor(Color.WHITE);
        gen.getParameters().getBarcode().setBarColor(Color.BLACK);

        // Auto-size
        gen.getParameters().setAutoSizeMode(AutoSizeMode.NEAREST);

        saveAndAssert(gen, "product_label.png");
    }

    // ========== Linear Barcodes Batch ==========

    @Test
    public void batch_linearBarcodes() throws Exception {
        Path base = Paths.get(folder);

        // UPC-A
        BarcodeGenerator upcGen = new BarcodeGenerator(EncodeTypes.UPCA, "036000291452");
        upcGen.getParameters().getBarcode().getXDimension().setMillimeters(0.33f);
        upcGen.save(base.resolve("linear_upca.png").toString(), BarCodeImageFormat.PNG);

        // EAN-13
        BarcodeGenerator eanGen = new BarcodeGenerator(EncodeTypes.EAN_13, "5901234123457");
        eanGen.getParameters().getBarcode().getXDimension().setMillimeters(0.33f);
        eanGen.save(base.resolve("linear_ean13.png").toString(), BarCodeImageFormat.PNG);

        // Code 39
        BarcodeGenerator code39Gen = new BarcodeGenerator(EncodeTypes.CODE_39, "ASPOSE-123");
        code39Gen.getParameters().getBarcode().getXDimension().setMillimeters(0.4f);
        code39Gen.save(base.resolve("linear_code39.png").toString(), BarCodeImageFormat.PNG);

        // Code 128
        BarcodeGenerator code128Gen = new BarcodeGenerator(EncodeTypes.CODE_128, "ABC-12345");
        code128Gen.getParameters().getBarcode().getXDimension().setMillimeters(0.3f);
        code128Gen.save(base.resolve("linear_code128.png").toString(), BarCodeImageFormat.PNG);

        // Verify all exist
        Assert.assertTrue(Files.exists(base.resolve("linear_upca.png")));
        Assert.assertTrue(Files.exists(base.resolve("linear_ean13.png")));
        Assert.assertTrue(Files.exists(base.resolve("linear_code39.png")));
        Assert.assertTrue(Files.exists(base.resolve("linear_code128.png")));
    }

    // ========== 2D Barcodes Batch ==========

    @Test
    public void batch_2DBarcodes() throws Exception {
        Path base = Paths.get(folder);

        // QR Code
        BarcodeGenerator qrGen = new BarcodeGenerator(EncodeTypes.QR, "QR Code Data");
        qrGen.getParameters().getBarcode().getXDimension().setMillimeters(0.5f);
        qrGen.save(base.resolve("2d_qr.png").toString(), BarCodeImageFormat.PNG);

        // Aztec
        BarcodeGenerator aztecGen = new BarcodeGenerator(EncodeTypes.AZTEC, "Aztec Data");
        aztecGen.getParameters().getBarcode().getXDimension().setMillimeters(0.5f);
        aztecGen.save(base.resolve("2d_aztec.png").toString(), BarCodeImageFormat.PNG);

        // DataMatrix
        BarcodeGenerator dmGen = new BarcodeGenerator(EncodeTypes.DATA_MATRIX, "DataMatrix");
        dmGen.getParameters().getBarcode().getXDimension().setMillimeters(0.4f);
        dmGen.save(base.resolve("2d_datamatrix.png").toString(), BarCodeImageFormat.PNG);

        // PDF417
        BarcodeGenerator pdf417Gen = new BarcodeGenerator(EncodeTypes.PDF_417, "PDF417 Data");
        pdf417Gen.getParameters().getBarcode().getXDimension().setMillimeters(0.6f);
        pdf417Gen.save(base.resolve("2d_pdf417.png").toString(), BarCodeImageFormat.PNG);

        // Verify all exist
        Assert.assertTrue(Files.exists(base.resolve("2d_qr.png")));
        Assert.assertTrue(Files.exists(base.resolve("2d_aztec.png")));
        Assert.assertTrue(Files.exists(base.resolve("2d_datamatrix.png")));
        Assert.assertTrue(Files.exists(base.resolve("2d_pdf417.png")));
    }

    // ========== Helper Methods ==========

    private void saveAndAssert(BarcodeGenerator gen, String fileName) throws Exception {
        Path out = Paths.get(folder).resolve(fileName);
        Files.createDirectories(out.getParent());
        gen.save(out.toString(), BarCodeImageFormat.PNG);

        Assert.assertTrue(Files.exists(out), "Output file must exist: " + out);
        Assert.assertTrue(Files.size(out) > 0, "Output file must be non-empty: " + out);
    }
}