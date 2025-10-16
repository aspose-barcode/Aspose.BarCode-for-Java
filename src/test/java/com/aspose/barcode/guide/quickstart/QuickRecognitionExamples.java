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

/**
 * Quick Recognition Examples Class
 * Demonstrates basic settings and work with various barcode types.
 */
public class QuickRecognitionExamples
{

    private static final String TEST_IMAGES_FOLDER = Paths.get(ExampleAssist.getOrCreateResourceFolderPath("quick_start", "recognition", "input_images")).toString();

    @BeforeClass
    public void setUp()
    {
        LicenseAssist.setupLicense();
    }

    // ==================== BASIC EXAMPLES ====================

    /**
     * Test 1: Simplest recognition example
     */
    @Test(priority = 1, description = "Simple Code128 recognition")
    public void test01_SimpleRecognition() throws Exception
    {
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
    public void test02_QRCodeRecognition() throws Exception
    {
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

        System.out.println("  [OK] URL: " + results[0].getCodeText());
        System.out.println("  [OK] QR Error Level: " + qrParams.getQRErrorLevel());
        System.out.println("  [OK] QR Version: " + qrParams.getQRVersion());

        // Structured Append info
        if (qrParams.getQRStructuredAppendModeBarCodesQuantity() > 0)
        {
            System.out.println("  [OK] Structured Append:");
            System.out.println("    - Total Count: " +
                    qrParams.getQRStructuredAppendModeBarCodesQuantity());
            System.out.println("    - Index: " +
                    qrParams.getQRStructuredAppendModeBarCodeIndex());
            System.out.println("    - Parity: " +
                    qrParams.getQRStructuredAppendModeParityData());
        }

        System.out.println();
    }

    /**
     * Test 3: DataMatrix recognition
     */
    @Test(priority = 3, description = "DataMatrix recognition")
    public void test03_DataMatrixRecognition() throws Exception
    {
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
    public void test04_PDF417Recognition() throws Exception
    {
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
    public void test05_MultipleTypesRecognition() throws Exception
    {
        System.out.println("Test 5: Multiple Types Recognition");

        // Generate several codes
        String[] testFiles = {
                generateTestBarcode(EncodeTypes.CODE_128, "CODE128-DATA", "test05_1.png"),
                generateTestBarcode(EncodeTypes.QR, "QR-DATA", "test05_2.png"),
                generateTestBarcode(EncodeTypes.EAN_13, "1234567890128", "test05_3.png")
        };

        // Recognize with multiple types
        for (String file : testFiles)
        {
            BarCodeReader reader = new BarCodeReader(
                    file,
                    DecodeType.CODE_128,
                    DecodeType.QR,
                    DecodeType.EAN_13
            );

            BarCodeResult[] results = reader.readBarCodes();

            for (BarCodeResult result : results)
            {
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
    public void test06_All1DTypes() throws Exception
    {
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
        for (BarCodeResult result : results)
        {
            System.out.println("    - " + result.getCodeTypeName() +
                    ": " + result.getCodeText());
        }
        System.out.println();
    }

    /**
     * Test 7: All 2D types
     */
    @Test(priority = 7, description = "Recognize all 2D types")
    public void test07_All2DTypes() throws Exception
    {
        System.out.println("Test 7: All 2D Types");

        String imagePath = generateTestBarcode(EncodeTypes.AZTEC, "AZTEC-DATA", "test07_aztec.png"
        );

        // Use ALL 2D types
        BarCodeReader reader = new BarCodeReader(imagePath, DecodeType.TYPES_2D);

        BarCodeResult[] results = reader.readBarCodes();

        Assert.assertTrue(results.length > 0);

        System.out.println("  [OK] Recognized 2D codes: " + results.length);
        for (BarCodeResult result : results)
        {
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
    public void test08_HighPerformance() throws Exception
    {
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
    public void test09_NormalQuality() throws Exception
    {
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
    public void test10_HighQuality() throws Exception
    {
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
    public void test11_MaxQuality() throws Exception
    {
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
     * Test: Quality settings for different scenarios
     */
    @Test(priority = 12, description = "Quality Settings Scenarios")
    public void test12_QualitySettingsScenarios() throws Exception
    {
        System.out.println("Test 12: Quality Settings Scenarios\n");

        // Scenario 1: High-quality image - use HighPerformance
        String goodImage = generateTestBarcode(
                EncodeTypes.CODE_128, "GOOD-IMAGE", "test12_good.png", 300f
        );
        testWithPreset(goodImage, "HighPerformance",
                QualitySettings.getHighPerformance());

        // Scenario 2: Normal photo - use NormalQuality
        String normalImage = generateTestBarcode(
                EncodeTypes.QR, "NORMAL-IMAGE", "test12_normal.png", 150f
        );
        testWithPreset(normalImage, "NormalQuality",
                QualitySettings.getNormalQuality());

        // Scenario 3: Low quality - use HighQuality
        String lowImage = generateTestBarcode(
                EncodeTypes.DATA_MATRIX, "LOW-IMAGE", "test12_low.png", 96f
        );
        testWithPreset(lowImage, "HighQuality",
                QualitySettings.getHighQuality());

        // Scenario 4: Very difficult - use MaxQuality
        testWithPreset(lowImage, "MaxQuality",
                QualitySettings.getMaxQuality());
    }


    /**
     * // HighPerformance
     * // ✅ Use when: High-quality scanned images, good lighting, clear barcodes
     * // ⚡ Speed: Fastest
     * // 📊 Accuracy: Good for clean images
     * reader.setQualitySettings(QualitySettings.getHighPerformance());
     * <p>
     * // NormalQuality (default)
     * // ✅ Use when: Standard smartphone photos, normal conditions
     * // ⚡ Speed: Fast
     * // 📊 Accuracy: Good for most cases
     * reader.setQualitySettings(QualitySettings.getNormalQuality());
     * <p>
     * // HighQuality
     * // ✅ Use when: Blurry images, noise, slight rotation, poor lighting
     * // ⚡ Speed: Moderate
     * // 📊 Accuracy: Better for difficult conditions
     * reader.setQualitySettings(QualitySettings.getHighQuality());
     * <p>
     * // MaxQuality
     * // ✅ Use when: Very poor quality, damaged barcodes, extreme conditions
     * // ⚡ Speed: Slow
     * // 📊 Accuracy: Maximum (uses all algorithms)
     * reader.setQualitySettings(QualitySettings.getMaxQuality());
     * <p>
     * // MaxBarCodes
     * // ✅ Use when: Need to find ALL barcodes in image (multiple barcodes)
     * // ⚡ Speed: Very slow
     * // 📊 Accuracy: Finds maximum number of barcodes
     * reader.setQualitySettings(QualitySettings.getMaxBarCodes());
     **/
    private void testWithPreset(String imagePath, String presetName, QualitySettings preset)
    {
        BarCodeReader reader = new BarCodeReader(imagePath, DecodeType.CODE_128, DecodeType.QR, DecodeType.DATA_MATRIX
        );
        reader.setQualitySettings(preset);

        long start = System.currentTimeMillis();
        BarCodeResult[] results = reader.readBarCodes();
        long end = System.currentTimeMillis();

        System.out.println("  [" + presetName + "]");
        System.out.println("    - Time: " + (end - start) + " ms");
        System.out.println("    - Found: " + results.length + " barcode(s)");
        if (results.length > 0)
        {
            System.out.println("    - Data: " + results[0].getCodeText());
            System.out.println("    - Confidence: " + results[0].getConfidence() + "%");
        }
        System.out.println();
    }

    // ==================== MULTIPLE FILES ====================
    /**
     * Test 8: Batch recognition from multiple files
     * Process multiple images in a loop.
     */
    @Test(priority = 13, description = "Batch recognition from multiple files")
    public void test13_BatchRecognition() throws Exception
    {
        System.out.println("Test 8: Batch Recognition from Multiple Files");

        // Generate multiple test images
        String[] testImages = new String[5];
        for (int i = 0; i < testImages.length; i++)
        {
            testImages[i] = generateTestBarcode(EncodeTypes.CODE_128, "BATCH-" + (i + 1), "batch_test_" + i + ".png");
        }

        System.out.println("  [INFO] Processing " + testImages.length + " images...\n");

        int successCount = 0;
        long totalTime = 0;

        // Process each image
        for (int i = 0; i < testImages.length; i++)
        {
            long start = System.currentTimeMillis();

            BarCodeReader reader = new BarCodeReader(
                    testImages[i],
                    DecodeType.CODE_128
            );
            BarCodeResult[] results = reader.readBarCodes();

            long end = System.currentTimeMillis();
            totalTime += (end - start);

            if (results.length > 0)
            {
                successCount++;
                System.out.println("  [OK] Image " + (i + 1) + ": " +
                        results[0].getCodeText() + " (" + (end - start) + " ms)");
            }
            else
            {
                System.out.println("  [FAIL] Image " + (i + 1) + ": No barcode found");
            }
        }

        System.out.println("\n  [SUMMARY] Batch Recognition:");
        System.out.println("    - Total images: " + testImages.length);
        System.out.println("    - Successfully recognized: " + successCount);
        System.out.println("    - Failed: " + (testImages.length - successCount));
        System.out.println("    - Total time: " + totalTime + " ms");
        System.out.println("    - Average time: " + (totalTime / testImages.length) + " ms/image");

        Assert.assertEquals(successCount, testImages.length,
                "All images should be recognized");

        System.out.println();
    }

    // ==================== HELPER METHODS ====================

    /**
     * Generate test barcode image
     */
    private String generateTestBarcode(BaseEncodeType type, String data, String filename)
            throws Exception
    {
        return generateTestBarcode(type, data, filename, 300f);
    }

    /**
     * Generate test barcode image with custom DPI
     */
    private String generateTestBarcode(BaseEncodeType type, String data,
                                       String filename, float dpi) throws Exception
    {
        BarcodeGenerator gen = new BarcodeGenerator(type, data);

        // Universal settings for all barcode types
        gen.getParameters().setResolution(dpi);
        gen.getParameters().getBarcode().getXDimension().setMillimeters(0.3f);
        gen.getParameters().getBarcode().getBarHeight().setMillimeters(25f); // Ignored for 2D

        String imagePath = TEST_IMAGES_FOLDER + File.separator + filename;
        gen.save(imagePath, BarCodeImageFormat.PNG);

        return imagePath;
    }

    @AfterClass
    public void tearDown()
    {
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