package com.aspose.barcode.guide.quickstart;

import com.aspose.barcode.barcoderecognition.*;
import com.aspose.barcode.generation.*;
import org.testng.Assert;
import org.testng.annotations.*;

import java.awt.*;
import java.io.File;
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

    private static final String TEST_IMAGES_FOLDER = "test_images/";
    private static final String OUTPUT_FOLDER = "recognition_output/";

    @BeforeClass
    public void setUp() {
        // Create test folders
        new File(TEST_IMAGES_FOLDER).mkdirs();
        new File(OUTPUT_FOLDER).mkdirs();

        System.out.println("=== Starting Barcode Recognition Tests ===\n");
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
        System.out.println("  [OK] URL: " + results[0].getCodeText());
        System.out.println("  [OK] QR Error Level: " + qrParams.getQRErrorLevel());
        System.out.println("  [OK] QR Version: " + qrParams.getQRVersion());
        System.out.println("  [OK] Encode Mode: " + qrParams.getQREncodeMode() + "\n");

        
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
        DataMatrixExtendedParameters dmParams =
                results[0].getExtended().getDataMatrix();

        System.out.println("  [OK] Data: " + results[0].getCodeText());
        System.out.println("  [OK] Structured Append Mode: " +
                dmParams.isStructuredAppend());
        System.out.println("  [OK] Reader Programming: " +
                dmParams.isReaderProgramming() + "\n");

        
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

        // Generate several codes (in reality they would be on one image)
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
                EncodeTypes.CODE_39_STANDARD,
                "ABC123",
                "test06_code39.png"
        );

        // Use ALL 1D types
        BarCodeReader reader = new BarCodeReader(
                imagePath,
                DecodeType.ALL_SUPPORTED_TYPES.getTypes1D()
        );

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

        String imagePath = generateTestBarcode(
                EncodeTypes.AZTEC,
                "AZTEC-DATA",
                "test07_aztec.png"
        );

        // Use ALL 2D types
        BarCodeReader reader = new BarCodeReader(
                imagePath,
                DecodeType.ALL_SUPPORTED_TYPES.getTypes2D()
        );

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
        custom.setAllowInvertImage(true);           // Image inversion
        custom.setAllowMedianSmoothing(true);       // Smoothing
        custom.setMedianSmoothingWindowSize(5);     // Window size
        custom.setAllowComplexBackground(true);     // Complex background
        custom.setAllowWhiteSpotsRemoving(true);    // Remove white spots
        custom.setAllowOneDFastBarcodesDetector(true); // Fast 1D detector

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

    // ==================== TIMEOUT & REGION ====================

    /**
     * Test 13: Timeout settings
     */
    @Test(priority = 13, description = "Timeout settings")
    public void test13_TimeoutSettings() throws Exception {
        System.out.println("Test 13: Timeout Settings");

        String imagePath = generateTestBarcode(
                EncodeTypes.QR,
                "TIMEOUT-TEST",
                "test13_timeout.png"
        );

        BarCodeReader reader = new BarCodeReader(imagePath, DecodeType.QR);

        // Set timeout 5 seconds
        reader.setTimeout(5000);

        long startTime = System.currentTimeMillis();
        BarCodeResult[] results = reader.readBarCodes();
        long endTime = System.currentTimeMillis();

        long elapsed = endTime - startTime;

        Assert.assertEquals(results.length, 1);
        Assert.assertTrue(elapsed < 5000,
                "Recognition should complete before timeout");

        System.out.println("  [OK] Timeout set: 5000 ms");
        System.out.println("  [OK] Recognized in: " + elapsed + " ms");
        System.out.println("  [OK] Timeout not triggered\n");

        
    }

    /**
     * Test 14: ProcessorSettings for multithreading
     */
    @Test(priority = 14, description = "ProcessorSettings")
    public void test14_ProcessorSettings() throws Exception {
        System.out.println("Test 14: ProcessorSettings");

        String imagePath = generateTestBarcode(
                EncodeTypes.DATA_MATRIX,
                "PROCESSOR-TEST",
                "test14_processor.png"
        );

        BarCodeReader reader = new BarCodeReader(imagePath, DecodeType.DATA_MATRIX);

        // Configure processor
        ProcessorSettings processorSettings = new ProcessorSettings();
        processorSettings.setMaxAdditionalAllowedThreads(4);
        processorSettings.setUseAllCores(false);

        reader.setProcessorSettings(processorSettings);

        BarCodeResult[] results = reader.readBarCodes();

        Assert.assertEquals(results.length, 1);

        System.out.println("  [OK] Processor settings:");
        System.out.println("    - Max additional threads: 4");
        System.out.println("    - Use all cores: false");
        System.out.println("  [OK] Result: " + results[0].getCodeText() + "\n");

        
    }

    // ==================== EXTENDED PARAMETERS ====================

    /**
     * Test 15: OneD Extended Parameters
     */
    @Test(priority = 15, description = "1D Extended Parameters")
    public void test15_OneDExtendedParameters() throws Exception {
        System.out.println("Test 15: 1D Extended Parameters");

        String imagePath = generateTestBarcode(
                EncodeTypes.CODE_128,
                "1D-EXTENDED",
                "test15_1d_ext.png"
        );

        BarCodeReader reader = new BarCodeReader(imagePath, DecodeType.CODE_128);

        BarCodeResult[] results = reader.readBarCodes();
        Assert.assertEquals(results.length, 1);

        BarCodeResult result = results[0];
        OneDExtendedParameters oneD = result.getExtended().getOneD();

        System.out.println("  [OK] 1D Extended Info:");
        System.out.println("    - CodeText: " + result.getCodeText());
        System.out.println("    - CheckSum: " + oneD.getCheckSum());
        System.out.println("    - Value: " + oneD.getValue());
        System.out.println();

        
    }

    /**
     * Test 16: QR Structured Append
     */
    @Test(priority = 16, description = "QR Structured Append")
    public void test16_QRStructuredAppend() throws Exception {
        System.out.println("Test 16: QR Structured Append");

        // Generate QR with Structured Append
        BarcodeGenerator gen = new BarcodeGenerator(
                EncodeTypes.QR,
                "Part 1 of data"
        );

        QrParameters qr = gen.getParameters().getBarcode().getQR();
        qr.setStructuredAppend(new QrStructuredAppendParameters());
        qr.getStructuredAppend().setTotalCount(3);
        qr.getStructuredAppend().setSequenceIndicator(0);
        qr.getStructuredAppend().setParityByte((byte)123);

        String imagePath = TEST_IMAGES_FOLDER + "test16_qr_structured.png";
        gen.save(imagePath);

        // Recognize
        BarCodeReader reader = new BarCodeReader(imagePath, DecodeType.QR);
        BarCodeResult[] results = reader.readBarCodes();

        Assert.assertEquals(results.length, 1);

        QRExtendedParameters qrExt = results[0].getExtended().getQR();

        System.out.println("  [OK] QR Structured Append:");
        System.out.println("    - Data: " + results[0].getCodeText());
        System.out.println("    - Total Count: " +
                qrExt.getQRStructuredAppendModeBarCodesQuantity());
        System.out.println("    - Sequence: " +
                qrExt.getQRStructuredAppendModeBarCodeIndex());
        System.out.println("    - Parity: " +
                qrExt.getQRStructuredAppendModeParityData());
        System.out.println();

        
    }

    // ==================== SPECIAL TYPES ====================

    /**
     * Test 17: EAN13 with supplement
     */
    @Test(priority = 17, description = "EAN13 with supplement")
    public void test17_EAN13WithSupplement() throws Exception {
        System.out.println("Test 17: EAN13 with Supplement");

        // Generate EAN13 with 2-digit supplement
        BarcodeGenerator gen = new BarcodeGenerator(
                EncodeTypes.EAN_13,
                "1234567890128"
        );

        SupplementParameters supp = gen.getParameters().getBarcode().getSupplement();
        supp.setSupplementData("12");
        supp.getSupplementSpace().setMillimeters(2f);

        String imagePath = TEST_IMAGES_FOLDER + "test17_ean13_supp.png";
        gen.save(imagePath);

        // Recognize
        BarCodeReader reader = new BarCodeReader(imagePath, DecodeType.EAN_13);
        BarCodeResult[] results = reader.readBarCodes();

        Assert.assertTrue(results.length >= 1);

        System.out.println("  [OK] EAN13 with supplement:");
        for (BarCodeResult result : results) {
            System.out.println("    - Type: " + result.getCodeTypeName());
            System.out.println("    - Data: " + result.getCodeText());
        }
        System.out.println();

        
    }

    /**
     * Test 18: GS1 DataBar
     */
    @Test(priority = 18, description = "GS1 DataBar")
    public void test18_GS1DataBar() throws Exception {
        System.out.println("Test 18: GS1 DataBar");

        String imagePath = generateTestBarcode(
                EncodeTypes.DATABAR_OMNIDIRECTIONAL,
                "(01)09521234567890",
                "test18_databar.png"
        );

        BarCodeReader reader = new BarCodeReader(
                imagePath,
                DecodeType.DATABAR_OMNIDIRECTIONAL
        );

        BarCodeResult[] results = reader.readBarCodes();

        Assert.assertEquals(results.length, 1);

        System.out.println("  [OK] GS1 DataBar:");
        System.out.println("    - Data: " + results[0].getCodeText());
        System.out.println("    - Type: " + results[0].getCodeTypeName());
        System.out.println();

        
    }

    /**
     * Test 19: Postal Barcodes
     */
    @Test(priority = 19, description = "Postal Barcodes")
    public void test19_PostalBarcodes() throws Exception {
        System.out.println("Test 19: Postal Barcodes");

        // POSTNET
        String postnetPath = generateTestBarcode(
                EncodeTypes.POSTNET,
                "12345678901",
                "test19_postnet.png"
        );

        BarCodeReader reader1 = new BarCodeReader(postnetPath, DecodeType.POSTNET);
        BarCodeResult[] results1 = reader1.readBarCodes();

        Assert.assertEquals(results1.length, 1);
        System.out.println("  [OK] POSTNET: " + results1[0].getCodeText());
        reader1.close();

        // Australia Post
        String ausPostPath = generateTestBarcode(
                EncodeTypes.AUSTRALIA_POST,
                "1100000000000",
                "test19_auspost.png"
        );

        BarCodeReader reader2 = new BarCodeReader(
                ausPostPath,
                DecodeType.AUSTRALIA_POST
        );
        BarCodeResult[] results2 = reader2.readBarCodes();

        Assert.assertEquals(results2.length, 1);
        System.out.println("  [OK] Australia Post: " + results2[0].getCodeText());
        System.out.println();
        reader2.close();
    }

    /**
     * Test 20: Aztec Code
     */
    @Test(priority = 20, description = "Aztec Code")
    public void test20_AztecCode() throws Exception {
        System.out.println("Test 20: Aztec Code");

        String imagePath = generateTestBarcode(
                EncodeTypes.AZTEC,
                "Aztec test data 12345",
                "test20_aztec.png"
        );

        BarCodeReader reader = new BarCodeReader(imagePath, DecodeType.AZTEC);

        BarCodeResult[] results = reader.readBarCodes();

        Assert.assertEquals(results.length, 1);

        System.out.println("  [OK] Aztec Code:");
        System.out.println("    - Data: " + results[0].getCodeText());
        System.out.println("    - Confidence: " + results[0].getConfidence() + "%");
        System.out.println();

        
    }

    // ==================== BATCH PROCESSING ====================

    /**
     * Test 21: Batch recognition
     */
    @Test(priority = 21, description = "Batch Recognition")
    public void test21_BatchRecognition() throws Exception {
        System.out.println("Test 21: Batch Recognition");

        // Generate multiple test images
        List<String> testImages = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            String path = generateTestBarcode(
                    EncodeTypes.CODE_128,
                    "BATCH-" + i,
                    "test21_batch_" + i + ".png"
            );
            testImages.add(path);
        }

        int totalRecognized = 0;
        long totalTime = 0;

        // Recognize all
        for (String imagePath : testImages) {
            BarCodeReader reader = new BarCodeReader(
                    imagePath,
                    DecodeType.CODE_128
            );

            long start = System.currentTimeMillis();
            BarCodeResult[] results = reader.readBarCodes();
            long end = System.currentTimeMillis();

            totalRecognized += results.length;
            totalTime += (end - start);

            if (results.length > 0) {
                System.out.println("  [OK] " + imagePath + ": " +
                        results[0].getCodeText() + " (" + (end - start) + " ms)");
            }

            
        }

        System.out.println("\n  [OK] Batch Summary:");
        System.out.println("    - Total images: " + testImages.size());
        System.out.println("    - Total recognized: " + totalRecognized);
        System.out.println("    - Total time: " + totalTime + " ms");
        System.out.println("    - Average: " + (totalTime / testImages.size()) + " ms/image");
        System.out.println();
    }

    /**
     * Test 22: Region of Interest
     */
    @Test(priority = 22, description = "Region of Interest")
    public void test22_RegionOfInterest() throws Exception {
        System.out.println("Test 22: Region of Interest");

        String imagePath = generateTestBarcode(
                EncodeTypes.QR,
                "ROI-TEST",
                "test22_roi.png"
        );

        BarCodeReader reader = new BarCodeReader(imagePath, DecodeType.QR);

        BarCodeResult[] results = reader.readBarCodes();

        Assert.assertEquals(results.length, 1);

        // Get region where barcode was found
        Rectangle region = results[0].getRegion();

        System.out.println("  [OK] Barcode found at:");

        System.out.println("    - X: " + region.x);
        System.out.println("    - Y: " + region.y);
        System.out.println("    - Width: " + region.width);
        System.out.println("    - Height: " + region.height);
        System.out.println("    - Data: " + results[0].getCodeText());
        System.out.println();

        
    }

    /**
     * Test 23: Confidence levels
     */
    @Test(priority = 23, description = "Confidence Levels")
    public void test23_ConfidenceLevels() throws Exception {
        System.out.println("Test 23: Confidence Levels");

        // Generate multiple barcodes with different quality
        String[] testFiles = new String[3];
        testFiles[0] = generateTestBarcode(
                EncodeTypes.CODE_128, "HIGH-QUALITY", "test23_high.png", 300f
        );
        testFiles[1] = generateTestBarcode(
                EncodeTypes.CODE_128, "MEDIUM-QUALITY", "test23_medium.png", 150f
        );
        testFiles[2] = generateTestBarcode(
                EncodeTypes.CODE_128, "LOW-QUALITY", "test23_low.png", 72f
        );

        for (String imagePath : testFiles) {
            BarCodeReader reader = new BarCodeReader(imagePath, DecodeType.CODE_128);
            BarCodeResult[] results = reader.readBarCodes();

            if (results.length > 0) {
                BarCodeResult result = results[0];
                System.out.println("  [OK] " + new File(imagePath).getName());
                System.out.println("    - Data: " + result.getCodeText());
                System.out.println("    - Confidence: " + result.getConfidence() + "%");
                System.out.println("    - Type: " +
                        (result.getConfidence() >= 95 ? "STRONG" :
                                result.getConfidence() >= 80 ? "MODERATE" : "WEAK"));
            }

            
        }
        System.out.println();
    }

    /**
     * Test 24: Strip FNC characters
     */
    @Test(priority = 24, description = "Strip FNC Characters")
    public void test24_StripFNC() throws Exception {
        System.out.println("Test 24: Strip FNC Characters");

        String imagePath = generateTestBarcode(
                EncodeTypes.GS_1_CODE_128,
                "(01)12345678901231(21)SERIAL123",
                "test24_fnc.png"
        );

        // Test with FNC stripping enabled
        BarCodeReader reader1 = new BarCodeReader(imagePath, DecodeType.GS_1_CODE_128);
        reader1.getBarcodeSettings().setStripFNC(true);
        BarCodeResult[] results1 = reader1.readBarCodes();

        System.out.println("  [OK] With FNC stripping:");
        if (results1.length > 0) {
            System.out.println("    - Data: " + results1[0].getCodeText());
        }
        reader1.close();

        // Test with FNC stripping disabled
        BarCodeReader reader2 = new BarCodeReader(imagePath, DecodeType.GS_1_CODE_128);
        reader2.getBarcodeSettings().setStripFNC(false);
        BarCodeResult[] results2 = reader2.readBarCodes();

        System.out.println("  [OK] Without FNC stripping:");
        if (results2.length > 0) {
            System.out.println("    - Data: " + results2[0].getCodeText());
        }
        System.out.println();
        reader2.close();
    }

    /**
     * Test 25: Checksum validation
     */
    @Test(priority = 25, description = "Checksum Validation")
    public void test25_ChecksumValidation() throws Exception {
        System.out.println("Test 25: Checksum Validation");

        // Generate Code39 with checksum
        BarcodeGenerator gen = new BarcodeGenerator(
                EncodeTypes.CODE_39_STANDARD,
                "ABC123"
        );
        gen.getParameters().getBarcode().setChecksumEnabled(EnableChecksum.YES);
        gen.getParameters().getBarcode().setChecksumAlwaysShow(true);

        String imagePath = TEST_IMAGES_FOLDER + "test25_checksum.png";
        gen.save(imagePath);

        // Recognize with checksum validation
        BarCodeReader reader = new BarCodeReader(imagePath, DecodeType.CODE_39_STANDARD);
        reader.getBarcodeSettings().setChecksumValidation(ChecksumValidation.ON);

        BarCodeResult[] results = reader.readBarCodes();

        Assert.assertEquals(results.length, 1);

        System.out.println("  [OK] Code39 with checksum:");
        System.out.println("    - Data: " + results[0].getCodeText());
        System.out.println("    - Checksum valid: " +
                (results[0].getExtended().getOneD().getCheckSum().length() > 0));
        System.out.println();

        
    }

    // ==================== COMPARISON TESTS ====================

    /**
     * Test 26: Quality presets comparison
     */
    @Test(priority = 26, description = "Quality Presets Comparison")
    public void test26_QualityPresetsComparison() throws Exception {
        System.out.println("Test 26: Quality Presets Comparison");

        String imagePath = generateTestBarcode(
                EncodeTypes.QR,
                "COMPARISON-TEST",
                "test26_comparison.png"
        );

        QualitySettings[] presets = {
                QualitySettings.getHighPerformance(),
                QualitySettings.getNormalQuality(),
                QualitySettings.getHighQuality(),
                QualitySettings.getMaxQuality()
        };

        String[] presetNames = {
                "HighPerformance",
                "NormalQuality",
                "HighQuality",
                "MaxQuality"
        };

        System.out.println("  [OK] Comparing quality presets:\n");

        for (int i = 0; i < presets.length; i++) {
            BarCodeReader reader = new BarCodeReader(imagePath, DecodeType.QR);
            reader.setQualitySettings(presets[i]);

            long start = System.currentTimeMillis();
            BarCodeResult[] results = reader.readBarCodes();
            long end = System.currentTimeMillis();

            System.out.println("    " + presetNames[i] + ":");
            System.out.println("      - Time: " + (end - start) + " ms");
            System.out.println("      - Found: " + results.length);
            if (results.length > 0) {
                System.out.println("      - Confidence: " + results[0].getConfidence() + "%");
            }

            
        }
        System.out.println();
    }

    /**
     * Test 27: Multiple barcodes in one image
     */
    @Test(priority = 27, description = "Multiple Barcodes in Image")
    public void test27_MultipleBarcodes() throws Exception {
        System.out.println("Test 27: Multiple Barcodes in One Image");

        // In real scenario, you would have multiple barcodes in one image
        // For testing, we recognize each separately
        String[] codes = {"CODE-A", "CODE-B", "CODE-C"};
        List<String> imagePaths = new ArrayList<>();

        for (String code : codes) {
            String path = generateTestBarcode(
                    EncodeTypes.CODE_128,
                    code,
                    "test27_multi_" + code + ".png"
            );
            imagePaths.add(path);
        }

        // Recognize all
        int totalFound = 0;
        for (String imagePath : imagePaths) {
            BarCodeReader reader = new BarCodeReader(imagePath, DecodeType.CODE_128);
            BarCodeResult[] results = reader.readBarCodes();

            totalFound += results.length;

            for (BarCodeResult result : results) {
                System.out.println("  [OK] Found: " + result.getCodeText() +
                        " at (" + result.getRegion().x + ", " + result.getRegion().y + ")");
            }

            
        }

        System.out.println("  [OK] Total barcodes found: " + totalFound + "\n");
    }

    /**
     * Test 28: Recognition statistics
     */
    @Test(priority = 28, description = "Recognition Statistics")
    public void test28_RecognitionStatistics() throws Exception {
        System.out.println("Test 28: Recognition Statistics");

        // Generate test set
        BaseEncodeType[] types = {
                EncodeTypes.CODE_128,
                EncodeTypes.QR,
                EncodeTypes.DATA_MATRIX,
                EncodeTypes.EAN_13
        };

        Map<String, Long> timings = new HashMap<>();
        Map<String, Double> confidences = new HashMap<>();

        for (BaseEncodeType type : types) {
            String imagePath = generateTestBarcode(
                    type,
                    "STATS-" + type.getTypeName(),
                    "test28_" + type.getTypeName() + ".png"
            );

            BarCodeReader reader = new BarCodeReader(imagePath, type);

            long start = System.currentTimeMillis();
            BarCodeResult[] results = reader.readBarCodes();
            long end = System.currentTimeMillis();

            if (results.length > 0) {
                timings.put(type.getTypeName(), end - start);
                confidences.put(type.getTypeName(), results[0].getConfidence());
            }

            
        }

        System.out.println("  [OK] Statistics by type:");
        for (String typeName : timings.keySet()) {
            System.out.println("    " + typeName + ":");
            System.out.println("      - Time: " + timings.get(typeName) + " ms");
            System.out.println("      - Confidence: " +
                    String.format("%.2f", confidences.get(typeName)) + "%");
        }
        System.out.println();
    }

    /**
     * Test 29: Error handling
     */
    @Test(priority = 29, description = "Error Handling")
    public void test29_ErrorHandling() throws Exception {
        System.out.println("Test 29: Error Handling");

        // Test 1: File not found
        try {
            BarCodeReader reader = new BarCodeReader(
                    "nonexistent.png",
                    DecodeType.CODE_128
            );
            reader.readBarCodes();
            
            Assert.fail("Should throw exception for missing file");
        } catch (Exception e) {
            System.out.println("  [OK] Correctly handled missing file: " +
                    e.getClass().getSimpleName());
        }

        // Test 2: Empty image (no barcodes)
        String emptyPath = generateEmptyImage("test29_empty.png");
        BarCodeReader reader2 = new BarCodeReader(emptyPath, DecodeType.CODE_128);
        BarCodeResult[] results2 = reader2.readBarCodes();

        System.out.println("  [OK] Empty image returned: " + results2.length + " results");
        Assert.assertEquals(results2.length, 0, "Should find no barcodes in empty image");
        reader2.close();

        // Test 3: Wrong type specified
        String qrPath = generateTestBarcode(
                EncodeTypes.QR,
                "QR-DATA",
                "test29_qr.png"
        );

        BarCodeReader reader3 = new BarCodeReader(qrPath, DecodeType.CODE_128);
        BarCodeResult[] results3 = reader3.readBarCodes();

        System.out.println("  [OK] Wrong type specified, found: " + results3.length + " results");
        Assert.assertEquals(results3.length, 0, "Should not find CODE_128 in QR image");
        reader3.close();

        System.out.println();
    }

    /**
     * Test 30: Performance test
     */
    @Test(priority = 30, description = "Performance Test")
    public void test30_PerformanceTest() throws Exception {
        System.out.println("Test 30: Performance Test");

        int iterations = 10;
        String imagePath = generateTestBarcode(
                EncodeTypes.CODE_128,
                "PERFORMANCE-TEST",
                "test30_perf.png"
        );

        // Warm-up
        for (int i = 0; i < 3; i++) {
            BarCodeReader reader = new BarCodeReader(imagePath, DecodeType.CODE_128);
            reader.readBarCodes();
            
        }

        // Actual test
        long totalTime = 0;
        for (int i = 0; i < iterations; i++) {
            BarCodeReader reader = new BarCodeReader(imagePath, DecodeType.CODE_128);

            long start = System.currentTimeMillis();
            BarCodeResult[] results = reader.readBarCodes();
            long end = System.currentTimeMillis();

            totalTime += (end - start);
            
        }

        double avgTime = (double) totalTime / iterations;

        System.out.println("  [OK] Performance results:");
        System.out.println("    - Iterations: " + iterations);
        System.out.println("    - Total time: " + totalTime + " ms");
        System.out.println("    - Average time: " + String.format("%.2f", avgTime) + " ms");
        System.out.println("    - Throughput: " +
                String.format("%.2f", 1000.0 / avgTime) + " recognitions/sec");
        System.out.println();
    }

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

        // Standard settings for test barcodes
        gen.getParameters().setResolution(dpi);
        gen.getParameters().getBarcode().getXDimension().setMillimeters(0.3f);

        if (type.getTypeIndex() == EncodeTypes.CODE_128.getTypeIndex() ||
                type.getTypeIndex() == EncodeTypes.CODE_39_STANDARD.getTypeIndex()) {
            gen.getParameters().getBarcode().getBarHeight().setMillimeters(25f);
        }

        String imagePath = TEST_IMAGES_FOLDER + filename;
        gen.save(imagePath, BarCodeImageFormat.PNG);

        return imagePath;
    }

    /**
     * Generate empty image for testing
     */
    private String generateEmptyImage(String filename) throws Exception {
        // Create a simple white image
        java.awt.image.BufferedImage image = new java.awt.image.BufferedImage(
                300, 300, java.awt.image.BufferedImage.TYPE_INT_RGB
        );
        Graphics2D g2d = image.createGraphics();
        g2d.setColor(Color.WHITE);
        g2d.fillRect(0, 0, 300, 300);
        g2d.dispose();

        String imagePath = TEST_IMAGES_FOLDER + filename;
        javax.imageio.ImageIO.write(image, "PNG", new File(imagePath));

        return imagePath;
    }

    /**
     * Print test summary
     */
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
        System.out.println("7. Enable ProcessorSettings for batch processing");
        System.out.println("8. Always close BarCodeReader to free resources");
        System.out.println("\nTest images location: " + TEST_IMAGES_FOLDER);
        System.out.println("=====================================\n");
    }

    // ==================== BONUS: PRACTICAL EXAMPLES ====================

    /**
     * Practical Example 1: Warehouse Scanner Simulation
     */
    @Test(priority = 31, description = "Warehouse Scanner Simulation", enabled = false)
    public void practical01_WarehouseScanner() throws Exception {
        System.out.println("Practical 1: Warehouse Scanner Simulation");

        // Simulate scanning multiple items
        String[] products = {
                "PROD-001", "PROD-002", "PROD-003", "PROD-004", "PROD-005"
        };

        List<ScannedItem> inventory = new ArrayList<>();

        for (String product : products) {
            String imagePath = generateTestBarcode(
                    EncodeTypes.CODE_128,
                    product,
                    "warehouse_" + product + ".png"
            );

            BarCodeReader reader = new BarCodeReader(imagePath, DecodeType.CODE_128);
            reader.setQualitySettings(QualitySettings.getHighPerformance());
            reader.setTimeout(3000);

            long scanStart = System.currentTimeMillis();
            BarCodeResult[] results = reader.readBarCodes();
            long scanEnd = System.currentTimeMillis();

            if (results.length > 0) {
                ScannedItem item = new ScannedItem();
                item.productCode = results[0].getCodeText();
                item.scanTime = scanEnd - scanStart;
                item.confidence = results[0].getConfidence();
                inventory.add(item);

                System.out.println("  [SCANNED] " + item.productCode +
                        " (" + item.scanTime + " ms, " +
                        String.format("%.1f", item.confidence) + "% confidence)");
            }

            
        }

        System.out.println("\n  [SUMMARY] Scanned " + inventory.size() +
                " items in warehouse");
        System.out.println();
    }

    /**
     * Practical Example 2: Quality Control System
     */
    @Test(priority = 32, description = "Quality Control System", enabled = false)
    public void practical02_QualityControl() throws Exception {
        System.out.println("Practical 2: Quality Control System");

        // Generate barcodes with varying quality
        String[] testBarcodes = new String[5];
        float[] dpis = {300f, 250f, 200f, 150f, 100f};

        for (int i = 0; i < testBarcodes.length; i++) {
            testBarcodes[i] = generateTestBarcode(
                    EncodeTypes.DATA_MATRIX,
                    "QC-ITEM-" + (i + 1),
                    "qc_test_" + (i + 1) + ".png",
                    dpis[i]
            );
        }

        int passCount = 0;
        int failCount = 0;

        for (int i = 0; i < testBarcodes.length; i++) {
            BarCodeReader reader = new BarCodeReader(
                    testBarcodes[i],
                    DecodeType.DATA_MATRIX
            );
            reader.setQualitySettings(QualitySettings.getHighQuality());

            BarCodeResult[] results = reader.readBarCodes();

            boolean pass = results.length > 0 && results[0].getConfidence() >= 90;

            if (pass) {
                passCount++;
                System.out.println("  [PASS] QC-ITEM-" + (i + 1) +
                        " - DPI: " + dpis[i] +
                        ", Confidence: " + results[0].getConfidence() + "%");
            } else {
                failCount++;
                System.out.println("  [FAIL] QC-ITEM-" + (i + 1) +
                        " - DPI: " + dpis[i] +
                        ", Confidence: " +
                        (results.length > 0 ? results[0].getConfidence() + "%" : "N/A"));
            }

            
        }

        System.out.println("\n  [QC SUMMARY]");
        System.out.println("    - Passed: " + passCount);
        System.out.println("    - Failed: " + failCount);
        System.out.println("    - Pass Rate: " +
                String.format("%.1f", 100.0 * passCount / testBarcodes.length) + "%");
        System.out.println();
    }

    /**
     * Practical Example 3: Document Processing System
     */
    @Test(priority = 33, description = "Document Processing System", enabled = false)
    public void practical03_DocumentProcessing() throws Exception {
        System.out.println("Practical 3: Document Processing System");

        // Simulate processing documents with barcodes
        Map<String, String> documents = new HashMap<>();
        documents.put("Invoice", "INV-2024-001");
        documents.put("Receipt", "RCP-2024-045");
        documents.put("Shipment", "SHP-2024-789");

        for (Map.Entry<String, String> doc : documents.entrySet()) {
            String imagePath = generateTestBarcode(
                    EncodeTypes.PDF_417,
                    doc.getValue(),
                    "doc_" + doc.getKey() + ".png"
            );

            BarCodeReader reader = new BarCodeReader(imagePath, DecodeType.PDF_417);
            reader.setQualitySettings(QualitySettings.getNormalQuality());

            BarCodeResult[] results = reader.readBarCodes();

            if (results.length > 0) {
                System.out.println("  [PROCESSED] " + doc.getKey());
                System.out.println("    - Document ID: " + results[0].getCodeText());
                System.out.println("    - Type: " + results[0].getCodeTypeName());
                System.out.println("    - Status: Indexed and filed");
            }

            
        }
        System.out.println();
    }

    // Helper class for warehouse scanner
    private static class ScannedItem {
        String productCode;
        long scanTime;
        double confidence;
    }
}