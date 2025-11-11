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
        BarcodeGenerator barcodeGenerator = new BarcodeGenerator(EncodeTypes.CODE_128, "ABC-12345");

        // Standard settings
        barcodeGenerator.getParameters().getBarcode().getXDimension().setMillimeters(0.3f);
        barcodeGenerator.getParameters().getBarcode().getBarHeight().setMillimeters(10.0f);
        barcodeGenerator.getParameters().getBarcode().getBarWidthReduction().setMillimeters(0.1f);
                saveAndAssert(barcodeGenerator, "code128.png");
    }

    @Test
    public void generate_EAN13() throws Exception {
        // Valid EAN-13 with checksum
        BarcodeGenerator barcodeGenerator = new BarcodeGenerator(EncodeTypes.EAN_13, "5901234123457");

        // Standard EAN-13 X-dimension
        barcodeGenerator.getParameters().getBarcode().getXDimension().setMillimeters(0.33f);
        barcodeGenerator.getParameters().getBarcode().getBarHeight().setMillimeters(25.0f);

        saveAndAssert(barcodeGenerator, "ean13.png");
    }

    @Test
    public void generate_UPCA() throws Exception {
        // Valid UPC-A (12 digits)
        BarcodeGenerator barcodeGenerator = new BarcodeGenerator(EncodeTypes.UPCA, "036000291452");

        barcodeGenerator.getParameters().getBarcode().getXDimension().setMillimeters(0.33f);
        barcodeGenerator.getParameters().getBarcode().getBarHeight().setMillimeters(25.0f);

        saveAndAssert(barcodeGenerator, "upca.png");
    }

    @Test
    public void generate_Code39() throws Exception {
        BarcodeGenerator barcodeGenerator = new BarcodeGenerator(EncodeTypes.CODE_39, "ASPOSE-123");

        barcodeGenerator.getParameters().getBarcode().getXDimension().setMillimeters(0.4f);
        barcodeGenerator.getParameters().getBarcode().getBarHeight().setMillimeters(12.0f);

        // Show code text above
        barcodeGenerator.getParameters().getBarcode().getCodeTextParameters()
                .setLocation(CodeLocation.ABOVE);

        saveAndAssert(barcodeGenerator, "code39.png");
    }

    @Test
    public void generate_ITF14() throws Exception {
        // ITF-14 requires 14 digits
        BarcodeGenerator barcodeGenerator = new BarcodeGenerator(EncodeTypes.ITF_14, "12345678901231");

        // ITF-14 standard X-dimension
        barcodeGenerator.getParameters().getBarcode().getXDimension().setMillimeters(0.495f);
        barcodeGenerator.getParameters().getBarcode().getBarHeight().setMillimeters(32.0f);

        saveAndAssert(barcodeGenerator, "itf14.png");
    }

    // ========== Two-Dimensional Barcodes ==========

    @Test
    public void generate_QR() throws Exception {
        BarcodeGenerator barcodeGenerator = new BarcodeGenerator(EncodeTypes.QR, "https://www.aspose.com");

        barcodeGenerator.getParameters().getBarcode().getXDimension().setMillimeters(0.5f);
        barcodeGenerator.getParameters().getBarcode().getQR().setQrEncodeMode(QREncodeMode.AUTO);
        barcodeGenerator.getParameters().getBarcode().getQR().setQrErrorLevel(QRErrorLevel.LEVEL_M);

        saveAndAssert(barcodeGenerator, "qrcode.png");
    }

    @Test
    public void generate_QR_withUnicode() throws Exception {
        // QR with Unicode data (Japanese)
        BarcodeGenerator barcodeGenerator = new BarcodeGenerator(EncodeTypes.QR, "データ");

        barcodeGenerator.getParameters().getBarcode().getXDimension().setMillimeters(0.5f);
        barcodeGenerator.getParameters().getBarcode().getQR().setQrEncodeMode(QREncodeMode.ECI);
        barcodeGenerator.getParameters().getBarcode().getQR().setQrECIEncoding(ECIEncodings.UTF8);

        saveAndAssert(barcodeGenerator, "qrcode_unicode.png");
    }

    @Test
    public void generate_DataMatrix() throws Exception {
        BarcodeGenerator barcodeGenerator = new BarcodeGenerator(EncodeTypes.DATA_MATRIX, "DMX-INV-000042");

        barcodeGenerator.getParameters().getBarcode().getXDimension().setMillimeters(0.4f);
        barcodeGenerator.getParameters().getBarcode().getDataMatrix()
                .setDataMatrixEncodeMode(DataMatrixEncodeMode.AUTO);

        // Optional: enable anti-aliasing for better quality
        barcodeGenerator.getParameters().setUseAntiAlias(true);
        barcodeGenerator.getParameters().setRotationAngle(15f);
        barcodeGenerator.getParameters().getBarcode().getCodeTextParameters().setLocation(CodeLocation.BELOW);
        barcodeGenerator.getParameters().getBarcode().getDataMatrix().setDataMatrixEncodeMode(
                com.aspose.barcode.generation.DataMatrixEncodeMode.AUTO);
        saveAndAssert(barcodeGenerator, "datamatrix.png");
    }

    @Test
    public void generate_PDF417() throws Exception {
        BarcodeGenerator barcodeGenerator = new BarcodeGenerator(
                EncodeTypes.PDF_417,
                "Shipment 42: 10x Boxes"
        );

        barcodeGenerator.getParameters().getBarcode().getXDimension().setMillimeters(0.6f);
        barcodeGenerator.getParameters().getBarcode().getPdf417().setColumns(3);
        barcodeGenerator.getParameters().getBarcode().getPdf417().setRows(6);

        // Add padding
        barcodeGenerator.getParameters().getBarcode().getPadding().getLeft().setMillimeters(2.0f);
        barcodeGenerator.getParameters().getBarcode().getPadding().getRight().setMillimeters(2.0f);

        saveAndAssert(barcodeGenerator, "pdf417.png");
    }

    @Test
    public void generate_PDF417_truncated() throws Exception {
        BarcodeGenerator barcodeGenerator = new BarcodeGenerator(
                EncodeTypes.PDF_417,
                "Compact PDF417"
        );

        barcodeGenerator.getParameters().getBarcode().getXDimension().setMillimeters(0.6f);
        barcodeGenerator.getParameters().getBarcode().getPdf417().setColumns(3);
        barcodeGenerator.getParameters().getBarcode().getPdf417().setPdf417Truncate(true);

        saveAndAssert(barcodeGenerator, "pdf417_truncated.png");
    }

    @Test
    public void generate_Aztec() throws Exception {
        BarcodeGenerator barcodeGenerator = new BarcodeGenerator(EncodeTypes.AZTEC, "AZTEC-Order#123");

        barcodeGenerator.getParameters().getBarcode().getXDimension().setMillimeters(0.5f);
        barcodeGenerator.getParameters().getBarcode().getAztec().setAztecErrorLevel(50);

        saveAndAssert(barcodeGenerator, "aztec.png");
    }

    // ========== Postal Barcodes ==========

    @Test
    public void generate_Postnet() throws Exception {
        // USPS Postnet: ZIP+4 (9 digits) or ZIP (5 digits)
        BarcodeGenerator barcodeGenerator = new BarcodeGenerator(EncodeTypes.POSTNET, "205001234");

        barcodeGenerator.getParameters().getBarcode().getXDimension().setMillimeters(0.5f);
        barcodeGenerator.getParameters().getBarcode().getBarHeight().setMillimeters(3.0f);

        saveAndAssert(barcodeGenerator, "postnet.png");
    }

    @Test
    public void generate_Planet() throws Exception {
        // USPS Planet
        BarcodeGenerator barcodeGenerator = new BarcodeGenerator(EncodeTypes.PLANET, "205001234");

        barcodeGenerator.getParameters().getBarcode().getXDimension().setMillimeters(0.5f);
        barcodeGenerator.getParameters().getBarcode().getBarHeight().setMillimeters(3.0f);

        saveAndAssert(barcodeGenerator, "planet.png");
    }

    @Test
    public void generate_IntelligentMail() throws Exception {
        // USPS Intelligent Mail (OneCode)
        // Format: 20 digits (tracking) + 11 digits (routing) = 31 digits
        String imb = "01234567094987654321" + "01234567891";

        BarcodeGenerator barcodeGenerator = new BarcodeGenerator(EncodeTypes.ONE_CODE, imb);
        barcodeGenerator.getParameters().getBarcode().getXDimension().setMillimeters(0.5f);
        barcodeGenerator.getParameters().getBarcode().getBarHeight().setMillimeters(3.0f);

        saveAndAssert(barcodeGenerator, "intelligent_mail.png");
    }

    // ========== GS1 Barcodes ==========

    @Test
    public void generate_GS1_128_basic() throws Exception {
        // GS1-128 with Application Identifiers
        BarcodeGenerator barcodeGenerator = new BarcodeGenerator(
                EncodeTypes.GS_1_CODE_128,
                "(01)09501101530008(17)251231(10)BATCH-42"
        );

        barcodeGenerator.getParameters().getBarcode().getXDimension().setMillimeters(0.33f);
        barcodeGenerator.getParameters().getBarcode().getBarHeight().setMillimeters(15.0f);
        barcodeGenerator.getParameters().setResolution(300.0f);

        saveAndAssert(barcodeGenerator, "gs1_128.png");
    }

    @Test
    public void generate_GS1_128_complete() throws Exception {
        // Complex GS1 data with multiple AIs
        String gs1Data = "(01)09512345678900" +  // GTIN
                "(17)260630" +           // Expiration date
                "(10)LOT2025A" +         // Batch number
                "(21)SERIAL123456";      // Serial number

        BarcodeGenerator barcodeGenerator = new BarcodeGenerator(EncodeTypes.GS_1_CODE_128, gs1Data);

        barcodeGenerator.getParameters().getBarcode().getXDimension().setMillimeters(0.33f);
        barcodeGenerator.getParameters().getBarcode().getBarHeight().setMillimeters(16.0f);
        barcodeGenerator.getParameters().setResolution(300.0f);

        // Quiet zones
        barcodeGenerator.getParameters().getBarcode().getPadding().getLeft().setMillimeters(3.3f);
        barcodeGenerator.getParameters().getBarcode().getPadding().getRight().setMillimeters(3.3f);

        // Code text
        barcodeGenerator.getParameters().getBarcode().getCodeTextParameters().setLocation(CodeLocation.BELOW);
        barcodeGenerator.getParameters().getBarcode().getCodeTextParameters().getFont().setFamilyName("Arial");
        barcodeGenerator.getParameters().getBarcode().getCodeTextParameters().getFont().getSize().setPoint(9);

        saveAndAssert(barcodeGenerator, "gs1_128_complete.png");
    }

    @Test
    public void generate_GS1_DataMatrix() throws Exception {
        String gs1Data = "(01)12345678901231(21)SERIAL123";

        BarcodeGenerator barcodeGenerator = new BarcodeGenerator(EncodeTypes.GS_1_DATA_MATRIX, gs1Data);
        barcodeGenerator.getParameters().getBarcode().getXDimension().setMillimeters(0.4f);

        saveAndAssert(barcodeGenerator, "gs1_datamatrix.png");
    }

    @Test
    public void generate_GS1_QR() throws Exception {
        String gs1Data = "(01)12345678901231(10)ABC123";

        BarcodeGenerator barcodeGenerator = new BarcodeGenerator(EncodeTypes.GS_1_QR, gs1Data);
        barcodeGenerator.getParameters().getBarcode().getXDimension().setMillimeters(0.5f);

        saveAndAssert(barcodeGenerator, "gs1_qr.png");
    }

    // ========== Customization Examples ==========

    @Test
    public void customization_colorsAndFonts() throws Exception {
        BarcodeGenerator barcodeGenerator = new BarcodeGenerator(EncodeTypes.CODE_128, "CUSTOM-STYLE");

        // Colors
        barcodeGenerator.getParameters().setBackColor(Color.WHITE);
        barcodeGenerator.getParameters().getBarcode().setBarColor(Color.BLUE);

        // Dimensions
        barcodeGenerator.getParameters().getBarcode().getXDimension().setMillimeters(0.4f);
        barcodeGenerator.getParameters().getBarcode().getBarHeight().setMillimeters(15.0f);

        // Code text styling
        barcodeGenerator.getParameters().getBarcode().getCodeTextParameters().setLocation(CodeLocation.BELOW);
        barcodeGenerator.getParameters().getBarcode().getCodeTextParameters().setColor(Color.RED);
        barcodeGenerator.getParameters().getBarcode().getCodeTextParameters().getFont().setFamilyName("Arial");
        barcodeGenerator.getParameters().getBarcode().getCodeTextParameters().getFont().getSize().setPoint(12);
        barcodeGenerator.getParameters().getBarcode().getCodeTextParameters().getFont().setStyle(FontStyle.BOLD);

        // Padding
        barcodeGenerator.getParameters().getBarcode().getPadding().getLeft().setMillimeters(3.0f);
        barcodeGenerator.getParameters().getBarcode().getPadding().getRight().setMillimeters(3.0f);
        barcodeGenerator.getParameters().getBarcode().getPadding().getTop().setMillimeters(2.0f);
        barcodeGenerator.getParameters().getBarcode().getPadding().getBottom().setMillimeters(2.0f);

        saveAndAssert(barcodeGenerator, "custom_style.png");
    }

    @Test
    public void customization_multipleFormats() throws Exception {
        BarcodeGenerator barcodeGenerator = new BarcodeGenerator(EncodeTypes.CODE_128, "MULTI-FORMAT");

        barcodeGenerator.getParameters().getBarcode().getXDimension().setMillimeters(0.3f);
        barcodeGenerator.getParameters().getBarcode().getBarHeight().setMillimeters(12.0f);
        barcodeGenerator.getParameters().setResolution(300.0f);

        // Save in multiple formats
        Path base = Paths.get(folder);
        barcodeGenerator.save(base.resolve("format_test.png").toString(), BarCodeImageFormat.PNG);
        barcodeGenerator.save(base.resolve("format_test.svg").toString(), BarCodeImageFormat.SVG);
        barcodeGenerator.save(base.resolve("format_test.bmp").toString(), BarCodeImageFormat.BMP);
        barcodeGenerator.save(base.resolve("format_test.gif").toString(), BarCodeImageFormat.GIF);
        barcodeGenerator.save(base.resolve("format_test.tiff").toString(), BarCodeImageFormat.TIFF);

        // Verify PNG exists
        Assert.assertTrue(Files.exists(base.resolve("format_test.png")));
    }

    @Test
    public void customization_rotation() throws Exception {
        BarcodeGenerator barcodeGenerator = new BarcodeGenerator(EncodeTypes.CODE_128, "ROTATED");

        barcodeGenerator.getParameters().getBarcode().getXDimension().setMillimeters(0.3f);
        barcodeGenerator.getParameters().getBarcode().getBarHeight().setMillimeters(10.0f);

        // Test different rotation angles
        float[] angles = {0, 90, 180, 270};
        Path base = Paths.get(folder);

        for (float angle : angles) {
            barcodeGenerator.getParameters().setRotationAngle(angle);
            String fileName = "rotated_" + (int)angle + ".png";
            barcodeGenerator.save(base.resolve(fileName).toString(), BarCodeImageFormat.PNG);
            Assert.assertTrue(Files.exists(base.resolve(fileName)));
        }
    }

    @Test
    public void customization_resolution() throws Exception {
        BarcodeGenerator barcodeGenerator = new BarcodeGenerator(EncodeTypes.CODE_128, "HIGH-RES");

        barcodeGenerator.getParameters().getBarcode().getXDimension().setMillimeters(0.3f);
        barcodeGenerator.getParameters().getBarcode().getBarHeight().setMillimeters(12.0f);

        // High resolution for printing
        barcodeGenerator.getParameters().setResolution(300.0f);

        // Control image size
        barcodeGenerator.getParameters().getImageWidth().setPixels(500);
        barcodeGenerator.getParameters().getImageHeight().setPixels(200);
        barcodeGenerator.getParameters().setAutoSizeMode(AutoSizeMode.NEAREST);

        saveAndAssert(barcodeGenerator, "high_resolution.png");
    }

    // ========== Performance: Batch Generation ==========

    @Test
    public void performance_batchGeneration() throws Exception {
        String[] codes = {"PROD001", "PROD002", "PROD003", "PROD004", "PROD005"};

        // Create generator once and reuse
        BarcodeGenerator barcodeGenerator = new BarcodeGenerator(EncodeTypes.CODE_128, codes[0]);

        // Set common parameters once
        barcodeGenerator.getParameters().getBarcode().getXDimension().setMillimeters(0.3f);
        barcodeGenerator.getParameters().getBarcode().getBarHeight().setMillimeters(10.0f);
        barcodeGenerator.getParameters().setResolution(300.0f);

        Path base = Paths.get(folder);

        // Generate batch
        for (String code : codes) {
            barcodeGenerator.setCodeText(code);  // Only change the code text
            String fileName = "batch_" + code + ".png";
            barcodeGenerator.save(base.resolve(fileName).toString(), BarCodeImageFormat.PNG);
            Assert.assertTrue(Files.exists(base.resolve(fileName)));
        }
    }

    // ========== Complete Example: Product Label ==========

    @Test
    public void complete_productLabel() throws Exception {
        String ean13Code = "5901234123457";
        String productName = "Premium Coffee Beans";

        BarcodeGenerator barcodeGenerator = new BarcodeGenerator(EncodeTypes.EAN_13, ean13Code);

        // Professional label dimensions
        barcodeGenerator.getParameters().getBarcode().getXDimension().setMillimeters(0.33f);
        barcodeGenerator.getParameters().getBarcode().getBarHeight().setMillimeters(25.0f);

        // Print resolution
        barcodeGenerator.getParameters().setResolution(300.0f);

        // Code text styling
        barcodeGenerator.getParameters().getBarcode().getCodeTextParameters().setLocation(CodeLocation.BELOW);
        barcodeGenerator.getParameters().getBarcode().getCodeTextParameters().setAlignment(TextAlignment.CENTER);
        barcodeGenerator.getParameters().getBarcode().getCodeTextParameters().getFont().setFamilyName("Arial");
        barcodeGenerator.getParameters().getBarcode().getCodeTextParameters().getFont().getSize().setPoint(10);

        // Quiet zones
        barcodeGenerator.getParameters().getBarcode().getPadding().getLeft().setMillimeters(3.3f);
        barcodeGenerator.getParameters().getBarcode().getPadding().getRight().setMillimeters(3.3f);
        barcodeGenerator.getParameters().getBarcode().getPadding().getTop().setMillimeters(2.0f);
        barcodeGenerator.getParameters().getBarcode().getPadding().getBottom().setMillimeters(2.0f);

        // Product name caption
        barcodeGenerator.getParameters().getCaptionAbove().setVisible(true);
        barcodeGenerator.getParameters().getCaptionAbove().setText(productName);
        barcodeGenerator.getParameters().getCaptionAbove().setAlignment(TextAlignment.CENTER);
        barcodeGenerator.getParameters().getCaptionAbove().getFont().setFamilyName("Arial");
        barcodeGenerator.getParameters().getCaptionAbove().getFont().getSize().setPoint(12);
        barcodeGenerator.getParameters().getCaptionAbove().getFont().setStyle(FontStyle.BOLD);
        barcodeGenerator.getParameters().getCaptionAbove().getPadding().getLeft().setMillimeters(5);
        barcodeGenerator.getParameters().getCaptionAbove().getPadding().getRight().setMillimeters(5);

        // Clean appearance
        barcodeGenerator.getParameters().setBackColor(Color.WHITE);
        barcodeGenerator.getParameters().getBarcode().setBarColor(Color.BLACK);

        // Auto-size
        barcodeGenerator.getParameters().setAutoSizeMode(AutoSizeMode.NEAREST);

        saveAndAssert(barcodeGenerator, "product_label.png");
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

    private void saveAndAssert(BarcodeGenerator barcodeGenerator, String fileName) throws Exception {
        Path out = Paths.get(folder).resolve(fileName);
        Files.createDirectories(out.getParent());
        barcodeGenerator.save(out.toString(), BarCodeImageFormat.PNG);

        Assert.assertTrue(Files.exists(out), "Output file must exist: " + out);
        Assert.assertTrue(Files.size(out) > 0, "Output file must be non-empty: " + out);
    }
}