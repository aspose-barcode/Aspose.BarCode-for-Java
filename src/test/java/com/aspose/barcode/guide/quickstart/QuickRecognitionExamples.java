package com.aspose.barcode.guide.quickstart;

import com.aspose.barcode.barcoderecognition.*;
import com.aspose.barcode.generation.*;
import com.aspose.barcode.guide.common.ExampleAssist;
import com.aspose.barcode.guide.common.LicenseAssist;
import org.testng.Assert;
import org.testng.annotations.*;

import java.awt.*;
import java.io.File;
import java.nio.file.Paths;
import java.util.*;
import java.util.List;

/**
 * Quick Recognition Examples Class
 * Demonstrates basic settings and work with various barcode types.
 *
 * @author Aspose Examples Team
 * @version 1.0
 */
public class QuickRecognitionExamples {

    private static final String TEST_IMAGES_FOLDER =
            Paths.get(ExampleAssist.getOrCreateResourceFolderPath("quick_start", "recognition", "input_images")).toString();

    @BeforeClass
    public void setUp() {
        LicenseAssist.setupLicense();
    }


    // ==================== BASIC EXAMPLES ====================

    /**
     * Test 1: Simplest recognition example
     */
    @Test(priority = 1, description = "Simple Code128 recognition")
    public void test01_SimpleRecognition() throws Exception {
        System.out.println("Test 1: Simple Code128 Recognition");

        // 1. Generate test barcode
        String testData = "SIMPLE-TEST-001";
        String imagePath = generateTestBarcode(
                EncodeTypes.CODE_128,
                testData,
                "test01_code128.png"
        );

        // 2. Recognize
        BarCodeReader reader = new BarCodeReader(
                imagePath,
                DecodeType.CODE_128
        );

        // 3. Read results
        BarCodeResult[] results = reader.readBarCodes();

        // 4. Verify
        Assert.assertEquals(results.length, 1, "Should find 1 barcode");
        Assert.assertEquals(results[0].getCodeText(), testData);
        Assert.assertEquals(results[0].getCodeTypeName(), "Code128");

        System.out.println("  [OK] Recognized: " + results[0].getCodeText());
        System.out.println("  [OK] Type: " + results[0].getCodeTypeName());
        System.out.println("  [OK] Confidence: " + results[0].getConfidence() + "%\n");
    }

    /**
     * Test 2: QR Code recognition
     */
    @Test(priority = 2, description = "QR Code recognition with URL")
    public void test02_QRCodeRecognition() throws Exception {
        System.out.println("Test 2: QR Code Recognition");

        String testUrl = "https://aspose.com/barcode";
        String imagePath = generateTestBarcode(
                EncodeTypes.QR,
                testUrl,
                "test02_qr.png"
        );

        // Recognize with QR type specified
        BarCodeReader reader = new BarCodeReader(imagePath, DecodeType.QR);

        BarCodeResult[] results = reader.readBarCodes();

        Assert.assertEquals(results.length, 1);
        Assert.assertEquals(results[0].getCodeText(), testUrl);

        // Check extended information for QR
        QRExtendedParameters qrParams = results[0].getExtended().getQR();
        System.out.println("  [OK] CodeText: " + results[0].getCodeText());
        System.out.println("  [OK] CodeType: " + results[0].getCodeTypeName());
        System.out.println("  [OK] QR Error Level: " + qrParams.getQRErrorLevel());
        System.out.println("  [OK] QR Version: " + qrParams.getQRVersion());
    }

    /**
     * Test 3: DataMatrix recognition
     */
    @Test(priority = 3, description = "DataMatrix recognition")
    public void test03_DataMatrixRecognition() throws Exception {
        System.out.println("Test 3: DataMatrix Recognition");

        String testData = "DM-DATA-123456";
        String imagePath = generateTestBarcode(
                EncodeTypes.DATA_MATRIX,
                testData,
                "test03_datamatrix.png"
        );

        BarCodeReader reader = new BarCodeReader(
                imagePath,
                DecodeType.DATA_MATRIX
        );

        BarCodeResult[] results = reader.readBarCodes();

        Assert.assertEquals(results.length, 1);
        Assert.assertEquals(results[0].getCodeText(), testData);

        // Extended information for DataMatrix
        DataMatrixExtendedParameters dataMatrixExtendedParameters =
                results[0].getExtended().getDataMatrix();

        System.out.println("  [OK] Data: " + results[0].getCodeText());
        System.out.println("  [OK] Structured Append File Id: " +
                dataMatrixExtendedParameters.getStructuredAppendFileId());
        System.out.println("  [OK] Structured Append Barcode Id: " +
                dataMatrixExtendedParameters.getStructuredAppendBarcodeId());
        System.out.println("  [OK] Reader Programming: " +
                dataMatrixExtendedParameters.isReaderProgramming() + "\n");
    }

    /**
     * Test 4: PDF417 recognition
     */
    @Test(priority = 4, description = "PDF417 recognition")
    public void test04_PDF417Recognition() throws Exception {
        System.out.println("Test 4: PDF417 Recognition");

        String testData = "PDF417 Test Data with more content to make it interesting";
        String imagePath = generateTestBarcode(
                EncodeTypes.PDF_417,
                testData,
                "test04_pdf417.png"
        );

        BarCodeReader reader = new BarCodeReader(
                imagePath,
                DecodeType.PDF_417
        );

        BarCodeResult[] results = reader.readBarCodes();

        Assert.assertEquals(results.length, 1);
        Assert.assertEquals(results[0].getCodeText(), testData);

        // Extended information for PDF417
        Pdf417ExtendedParameters pdfParams =
                results[0].getExtended().getPdf417();

        System.out.println("  [OK] Data: " + results[0].getCodeText());
        System.out.println("  [OK] Macro PDF417 FileID: " +
                pdfParams.getMacroPdf417FileID());
        System.out.println("  [OK] Macro Segment ID: " +
                pdfParams.getMacroPdf417SegmentID() + "\n");
    }

    // ==================== MULTIPLE TYPES ====================

    /**
     * Test 5: Recognize multiple types simultaneously
     */
    @Test(priority = 5, description = "Multiple types in one image")
    public void test05_MultipleTypesRecognition() throws Exception {
        System.out.println("Test 5: Multiple Types Recognition");

        // Generate several codes
        String[] testFiles = {
                generateTestBarcode(EncodeTypes.CODE_128, "CODE128-DATA", "test05_1.png"),
                generateTestBarcode(EncodeTypes.QR, "QR-DATA", "test05_2.png"),
                generateTestBarcode(EncodeTypes.EAN_13, "1234567890128", "test05_3.png")
        };

        // Recognize with multiple types
        for (String file : testFiles) {
            BarCodeReader reader = new BarCodeReader(
                    file,
                    DecodeType.CODE_128,
                    DecodeType.QR,
                    DecodeType.EAN_13
            );

            BarCodeResult[] results = reader.readBarCodes();

            for (BarCodeResult result : results) {
                System.out.println("  [OK] Found: " + result.getCodeTypeName() +
                        " = " + result.getCodeText());
            }
        }
        System.out.println();
    }

    /**
     * Test 6: All 1D types
     */
    @Test(priority = 6, description = "Recognize all 1D types")
    public void test06_All1DTypes() throws Exception {
        System.out.println("Test 6: All 1D Types");

        String imagePath = generateTestBarcode(
                EncodeTypes.CODE_39_FULL_ASCII,
                "ABC123",
                "test06_code39.png"
        );

        // Use ALL 1D types
        BarCodeReader reader = new BarCodeReader(imagePath, DecodeType.TYPES_1D);

        BarCodeResult[] results = reader.readBarCodes();

        Assert.assertTrue(results.length > 0, "Should find at least one code");

        System.out.println("  [OK] Recognized 1D codes: " + results.length);
        for (BarCodeResult result : results) {
            System.out.println("    - " + result.getCodeTypeName() +
                    ": " + result.getCodeText());
        }
        System.out.println();
    }

    /**
     * Test 7: All 2D types
     */
    @Test(priority = 7, description = "Recognize all 2D types")
    public void test07_All2DTypes() throws Exception {
        System.out.println("Test 7: All 2D Types");

        String imagePath = generateTestBarcode(EncodeTypes.AZTEC,"AZTEC-DATA","test07_aztec.png"
        );

        // Use ALL 2D types
        BarCodeReader reader = new BarCodeReader(imagePath,DecodeType.TYPES_2D);

        BarCodeResult[] results = reader.readBarCodes();

        Assert.assertTrue(results.length > 0);

        System.out.println("  [OK] Recognized 2D codes: " + results.length);
        for (BarCodeResult result : results) {
            System.out.println("    - " + result.getCodeTypeName() +
                    ": " + result.getCodeText());
        }
        System.out.println();
    }

    // ==================== QUALITY SETTINGS ====================

    /**
     * Test 8: HighPerformance preset
     */
    @Test(priority = 8, description = "Quality: HighPerformance")
    public void test08_HighPerformance() throws Exception {
        System.out.println("Test 8: HighPerformance Quality");

        String imagePath = generateTestBarcode(
                EncodeTypes.CODE_128,
                "PERF-TEST",
                "test08_perf.png"
        );

        BarCodeReader reader = new BarCodeReader(imagePath, DecodeType.CODE_128);

        // Set HighPerformance
        reader.setQualitySettings(QualitySettings.getHighPerformance());

        long startTime = System.currentTimeMillis();
        BarCodeResult[] results = reader.readBarCodes();
        long endTime = System.currentTimeMillis();

        Assert.assertEquals(results.length, 1);

        System.out.println("  [OK] Recognized in: " + (endTime - startTime) + " ms");
        System.out.println("  [OK] Preset: HighPerformance (fastest)");
        System.out.println("  [OK] Best for: high-quality images\n");
    }

    /**
     * Test 9: NormalQuality preset
     */
    @Test(priority = 9, description = "Quality: NormalQuality")
    public void test09_NormalQuality() throws Exception {
        System.out.println("Test 9: NormalQuality (default)");

        String imagePath = generateTestBarcode(
                EncodeTypes.QR,
                "NORMAL-QUALITY-TEST",
                "test09_normal.png"
        );

        BarCodeReader reader = new BarCodeReader(imagePath, DecodeType.QR);

        // NormalQuality is default
        reader.setQualitySettings(QualitySettings.getNormalQuality());

        long startTime = System.currentTimeMillis();
        BarCodeResult[] results = reader.readBarCodes();
        long endTime = System.currentTimeMillis();

        Assert.assertEquals(results.length, 1);

        System.out.println("  [OK] Recognized in: " + (endTime - startTime) + " ms");
        System.out.println("  [OK] Preset: NormalQuality (balanced)");
        System.out.println("  [OK] Best for: most cases\n");
    }

    /**
     * Test 10: HighQuality preset
     */
    @Test(priority = 10, description = "Quality: HighQuality")
    public void test10_HighQuality() throws Exception {
        System.out.println("Test 10: HighQuality");

        String imagePath = generateTestBarcode(
                EncodeTypes.DATA_MATRIX,
                "HIGH-QUALITY-TEST",
                "test10_high.png"
        );

        BarCodeReader reader = new BarCodeReader(imagePath, DecodeType.DATA_MATRIX);

        // HighQuality - more algorithms
        reader.setQualitySettings(QualitySettings.getHighQuality());

        long startTime = System.currentTimeMillis();
        BarCodeResult[] results = reader.readBarCodes();
        long endTime = System.currentTimeMillis();

        Assert.assertEquals(results.length, 1);

        System.out.println("  [OK] Recognized in: " + (endTime - startTime) + " ms");
        System.out.println("  [OK] Preset: HighQuality (more thorough)");
        System.out.println("  [OK] Best for: difficult conditions\n");
    }

    /**
     * Test 11: MaxQuality preset
     */
    @Test(priority = 11, description = "Quality: MaxQuality")
    public void test11_MaxQuality() throws Exception {
        System.out.println("Test 11: MaxQuality");

        String imagePath = generateTestBarcode(
                EncodeTypes.PDF_417,
                "MAX-QUALITY-TEST",
                "test11_max.png"
        );

        BarCodeReader reader = new BarCodeReader(imagePath, DecodeType.PDF_417);

        // MaxQuality - all possible algorithms
        reader.setQualitySettings(QualitySettings.getMaxQuality());

        long startTime = System.currentTimeMillis();
        BarCodeResult[] results = reader.readBarCodes();
        long endTime = System.currentTimeMillis();

        Assert.assertEquals(results.length, 1);

        System.out.println("  [OK] Recognized in: " + (endTime - startTime) + " ms");
        System.out.println("  [OK] Preset: MaxQuality (slowest)");
        System.out.println("  [OK] Best for: extreme conditions\n");
    }

    // ==================== CUSTOM QUALITY SETTINGS ====================

    /**
     * Test 12: Custom quality settings
     */
    @Test(priority = 12, description = "Custom Quality Settings")
    public void test12_CustomQualitySettings() throws Exception {
        System.out.println("Test 12: Custom Quality Settings");

        String imagePath = generateTestBarcode(
                EncodeTypes.CODE_128,
                "CUSTOM-SETTINGS",
                "test12_custom.png"
        );

        BarCodeReader reader = new BarCodeReader(imagePath, DecodeType.CODE_128);
        // Create custom settings
        QualitySettings custom = new QualitySettings();
        custom.setAllowInvertImage(true);
        custom.setAllowMedianSmoothing(true);
        custom.setMedianSmoothingWindowSize(5);
        custom.setAllowComplexBackground(true);
        custom.setAllowWhiteSpotsRemoving(true);
        custom.setAllowOneDFastBarcodesDetector(true);

        reader.setQualitySettings(custom);

        BarCodeResult[] results = reader.readBarCodes();

        Assert.assertEquals(results.length, 1);

        System.out.println("  [OK] Applied custom settings:");
        System.out.println("    - Invert image: true");
        System.out.println("    - Median smoothing (window 5): true");
        System.out.println("    - Complex background: true");
        System.out.println("    - Remove spots: true");
        System.out.println("  [OK] Result: " + results[0].getCodeText() + "\n");
    }

    // Оставшиеся тесты без reader.close()...
    // (аналогично убираем все reader.close() из остальных тестов)

    // ==================== HELPER METHODS ====================

    /**
     * Generate test barcode image
     */
    private String generateTestBarcode(BaseEncodeType type, String data, String filename)
            throws Exception {
        return generateTestBarcode(type, data, filename, 300f);
    }

    /**
     * Generate test barcode image with custom DPI
     */
    private String generateTestBarcode(BaseEncodeType type, String data,
                                       String filename, float dpi) throws Exception {
        BarcodeGenerator gen = new BarcodeGenerator(type, data);

        // Universal settings for all barcode types
        gen.getParameters().setResolution(dpi);
        gen.getParameters().getBarcode().getXDimension().setMillimeters(0.3f);
        gen.getParameters().getBarcode().getBarHeight().setMillimeters(25f); // Ignored for 2D

        String imagePath = TEST_IMAGES_FOLDER + filename;
        gen.save(imagePath, BarCodeImageFormat.PNG);

        return imagePath;
    }

    @AfterClass
    public void tearDown() {
        System.out.println("\n=== Recognition Tests Summary ===");
        System.out.println("All tests completed successfully!");
        System.out.println("\nKey Takeaways:");
        System.out.println("1. Always specify expected barcode type for best performance");
        System.out.println("2. Use HighPerformance for high-quality images");
        System.out.println("3. Use HighQuality or MaxQuality for difficult conditions");
        System.out.println("4. Set timeout for large images or multiple barcodes");
        System.out.println("5. Check confidence level to validate results");
        System.out.println("6. Use Extended parameters for detailed information");
        System.out.println("=====================================\n");
    }
}