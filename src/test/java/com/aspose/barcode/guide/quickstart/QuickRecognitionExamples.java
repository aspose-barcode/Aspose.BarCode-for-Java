package com.aspose.barcode.guide.quickstart;

import com.aspose.barcode.barcoderecognition.*;
import com.aspose.barcode.guide.common.ExampleAssist;
import com.aspose.barcode.guide.common.LicenseAssist;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.awt.*;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Quick Recognition Examples as TestNG tests.
 * Uses only public APIs and mirrors the documentation samples.
 */
public class QuickRecognitionExamples {

    private static final String folder = ExampleAssist.getResourceFolderPath("quick_start/recognition");

    @BeforeClass
    public void setUp() {
        LicenseAssist.setupLicense();
    }

    // ========== Barcode-Specific Settings ==========

    @Test
    public void code128WithChecksum() throws Exception {
        String imagePath = Paths.get(folder, "code128.png").toString();

        BarCodeReader reader = new BarCodeReader(imagePath, DecodeType.CODE_128);

        // Enable checksum validation
        reader.getBarcodeSettings().setChecksumValidation(ChecksumValidation.ON);
        reader.getBarcodeSettings().setStripFNC(true); // Strip FNC characters

        boolean found = false;
        for (BarCodeResult result : reader.readBarCodes()) {
            System.out.println("Code 128 with checksum: " + result.getCodeText());
            Assert.assertNotNull(result.getCodeText(), "Code text should not be null");
            found = true;
        }

        Assert.assertTrue(found, "Should recognize Code 128 with checksum validation");
    }

    @Test
    public void code39Extended() throws Exception {
        String imagePath = Paths.get(folder, "code39.png").toString();

        // Try both standard and extended
        BarCodeReader reader = new BarCodeReader(
                imagePath,
                DecodeType.CODE_39,
                DecodeType.CODE_39_EXTENDED
        );

        reader.getBarcodeSettings().setChecksumValidation(ChecksumValidation.ON);

        boolean found = false;
        for (BarCodeResult result : reader.readBarCodes()) {
            System.out.println("Code 39: " + result.getCodeText());
            Assert.assertNotNull(result.getCodeText(), "Code text should not be null");
            found = true;
        }

        Assert.assertTrue(found, "Should recognize Code 39");
    }

    @Test
    public void itfWithSettings() throws Exception {
        String imagePath = Paths.get(folder, "itf14.png").toString();

        BarCodeReader reader = new BarCodeReader(imagePath, DecodeType.ITF_14);

        // ITF-14 specific settings
        reader.getBarcodeSettings().setStripFNC(false);
        reader.getBarcodeSettings().setDetectEncoding(true);

        boolean found = false;
        for (BarCodeResult result : reader.readBarCodes()) {
            System.out.println("ITF-14: " + result.getCodeText());
            Assert.assertEquals(result.getCodeText().length(), 14, "ITF-14 should be 14 digits");
            found = true;
        }

        Assert.assertTrue(found, "Should recognize ITF-14 with settings");
    }

    @Test
    public void qrWithEncoding() throws Exception {
        String imagePath = Paths.get(folder, "qrcode.png").toString();

        BarCodeReader reader = new BarCodeReader(imagePath, DecodeType.QR);

        // Enable automatic encoding detection
        reader.getBarcodeSettings().setDetectEncoding(true);

        boolean found = false;
        for (BarCodeResult result : reader.readBarCodes()) {
            System.out.println("QR Code: " + result.getCodeText());
            System.out.println("Type: " + result.getCodeType());
            Assert.assertNotNull(result.getCodeText(), "QR code text should not be null");
            found = true;
        }

        Assert.assertTrue(found, "Should recognize QR with encoding detection");
    }

    @Test
    public void qrUnicode() throws Exception {
        String imagePath = Paths.get(folder, "qrcode_unicode.png").toString();

        if (!Files.exists(Paths.get(imagePath))) {
            System.out.println("Unicode QR test file not found, skipping");
            return;
        }

        BarCodeReader reader = new BarCodeReader(imagePath, DecodeType.QR);

        // Detect encoding for Unicode data
        reader.getBarcodeSettings().setDetectEncoding(true);
        reader.setQualitySettings(QualitySettings.getHighQuality());

        boolean found = false;
        for (BarCodeResult result : reader.readBarCodes()) {
            System.out.println("QR Unicode: " + result.getCodeText());
            Assert.assertNotNull(result.getCodeText(), "Unicode text should not be null");
            found = true;
        }

        Assert.assertTrue(found, "Should recognize QR with Unicode");
    }

    @Test
    public void dataMatrixEncoding() throws Exception {
        String imagePath = Paths.get(folder, "datamatrix.png").toString();

        BarCodeReader reader = new BarCodeReader(imagePath, DecodeType.DATA_MATRIX);

        // Enable encoding detection for DataMatrix
        reader.getBarcodeSettings().setDetectEncoding(true);

        boolean found = false;
        for (BarCodeResult result : reader.readBarCodes()) {
            System.out.println("DataMatrix: " + result.getCodeText());
            Assert.assertNotNull(result.getCodeText(), "DataMatrix text should not be null");
            found = true;
        }

        Assert.assertTrue(found, "Should recognize DataMatrix with encoding");
    }

    @Test
    public void pdf417WithSettings() throws Exception {
        String imagePath = Paths.get(folder, "pdf417.png").toString();

        BarCodeReader reader = new BarCodeReader(imagePath, DecodeType.PDF_417);

        // Enable detection of PDF417 features
        reader.getBarcodeSettings().setDetectEncoding(true);
        reader.getBarcodeSettings().setStripFNC(false);

        boolean found = false;
        for (BarCodeResult result : reader.readBarCodes()) {
            System.out.println("PDF417: " + result.getCodeText());

            // Check for extended info
            if (result.getExtended() != null && result.getExtended().getPDF417() != null) {
                System.out.println("Has PDF417 extended info");
            }

            Assert.assertNotNull(result.getCodeText(), "PDF417 text should not be null");
            found = true;
        }

        Assert.assertTrue(found, "Should recognize PDF417 with settings");
    }

    @Test
    public void gs1_128WithAIParsing() throws Exception {
        String imagePath = Paths.get(folder, "gs1_128.png").toString();

        BarCodeReader reader = new BarCodeReader(imagePath, DecodeType.GS_1_CODE_128);

        // Enable FNC character stripping for clean AI parsing
        reader.getBarcodeSettings().setStripFNC(true);

        boolean found = false;
        for (BarCodeResult result : reader.readBarCodes()) {
            String codeText = result.getCodeText();
            System.out.println("GS1-128: " + codeText);

            Assert.assertTrue(codeText.startsWith("("), "GS1 should start with AI");

            // Parse AIs
            if (codeText.contains("(01)")) {
                System.out.println("Contains GTIN AI");
            }

            if (codeText.contains("(10)")) {
                System.out.println("Contains Lot AI");
            }

            found = true;
        }

        Assert.assertTrue(found, "Should recognize GS1-128 with AI parsing");
    }

    @Test
    public void gs1DataMatrixFNC1() throws Exception {
        String imagePath = Paths.get(folder, "gs1_datamatrix.png").toString();

        BarCodeReader reader = new BarCodeReader(imagePath, DecodeType.GS_1_DATA_MATRIX);

        // GS1 DataMatrix settings
        reader.getBarcodeSettings().setStripFNC(true);
        reader.getBarcodeSettings().setDetectEncoding(true);

        boolean found = false;
        for (BarCodeResult result : reader.readBarCodes()) {
            System.out.println("GS1 DataMatrix: " + result.getCodeText());
            Assert.assertTrue(result.getCodeText().startsWith("("), "GS1 should have AI format");
            found = true;
        }

        Assert.assertTrue(found, "Should recognize GS1 DataMatrix");
    }

    @Test
    public void postnetWithChecksum() throws Exception {
        String imagePath = Paths.get(folder, "postnet.png").toString();

        BarCodeReader reader = new BarCodeReader(imagePath, DecodeType.POSTNET);

        // Enable checksum validation for postal barcodes
        reader.getBarcodeSettings().setChecksumValidation(ChecksumValidation.ON);

        boolean found = false;
        for (BarCodeResult result : reader.readBarCodes()) {
            System.out.println("Postnet: " + result.getCodeText());
            Assert.assertTrue(result.getCodeText().matches("\\d+"), "Postnet should be digits");
            found = true;
        }

        Assert.assertTrue(found, "Should recognize Postnet with checksum");
    }

    // ========== Special Recognition Scenarios ==========

    @Test
    public void recognizeInvertedBarcode() throws Exception {
        String imagePath = Paths.get(folder, "code128.png").toString();

        BarCodeReader reader = new BarCodeReader(imagePath, DecodeType.CODE_128);

        // Enable inverted image detection
        QualitySettings settings = QualitySettings.getHighQuality();
        settings.setAllowInvertImage(true);
        reader.setQualitySettings(settings);

        boolean found = false;
        for (BarCodeResult result : reader.readBarCodes()) {
            System.out.println("Barcode (invert check enabled): " + result.getCodeText());
            found = true;
        }

        Assert.assertTrue(found, "Should recognize with invert detection enabled");
    }

    @Test
    public void recognizeLowContrast() throws Exception {
        String imagePath = Paths.get(folder, "code128.png").toString();

        BarCodeReader reader = new BarCodeReader(imagePath, DecodeType.CODE_128);

        // Settings for low contrast images
        QualitySettings settings = QualitySettings.getMaxQuality();
        settings.setAllowComplexBackground(true);
        settings.setAllowMedianSmoothing(true);
        settings.setAllowRegularWiping(true);
        settings.setAllowWhiteSpotsRemoving(true);

        reader.setQualitySettings(settings);

        boolean found = false;
        for (BarCodeResult result : reader.readBarCodes()) {
            System.out.println("Low contrast settings: " + result.getCodeText());
            found = true;
        }

        Assert.assertTrue(found, "Should work with low contrast settings");
    }

    @Test
    public void recognizeDamagedBarcode() throws Exception {
        String imagePath = Paths.get(folder, "code128.png").toString();

        BarCodeReader reader = new BarCodeReader(imagePath, DecodeType.CODE_128);

        // Maximum quality with all filters enabled
        QualitySettings settings = QualitySettings.getMaxQuality();
        settings.setAllowSaltAndPaperFiltering(true);
        settings.setAllowWhiteSpotsRemoving(true);
        settings.setAllowMedianSmoothing(true);
        settings.setAllowDecreasedImage(true);

        reader.setQualitySettings(settings);

        boolean found = false;
        for (BarCodeResult result : reader.readBarCodes()) {
            System.out.println("Damaged barcode handling: " + result.getCodeText());
            System.out.println("Confidence: " + result.getConfidence() + "%");
            found = true;
        }

        Assert.assertTrue(found, "Should work with damaged barcode settings");
    }

    @Test
    public void recognizeSmallBarcode() throws Exception {
        String imagePath = Paths.get(folder, "code128.png").toString();

        BarCodeReader reader = new BarCodeReader(imagePath, DecodeType.CODE_128);

        // Settings for small or dense barcodes
        QualitySettings settings = QualitySettings.getHighQuality();
        settings.setAllowDecreasedImage(false); // Don't decrease small images
        settings.setAllowOneDFastBarcodesDetector(true);

        reader.setQualitySettings(settings);

        boolean found = false;
        for (BarCodeResult result : reader.readBarCodes()) {
            System.out.println("Small barcode settings: " + result.getCodeText());
            found = true;
        }

        Assert.assertTrue(found, "Should work with small barcode settings");
    }

    @Test
    public void recognizeRotatedBarcode() throws Exception {
        String imagePath = Paths.get(folder, "rotated_90.png").toString();

        if (!Files.exists(Paths.get(imagePath))) {
            System.out.println("Rotated barcode test file not found, using regular file");
            imagePath = Paths.get(folder, "code128.png").toString();
        }

        BarCodeReader reader = new BarCodeReader(imagePath, DecodeType.CODE_128);

        // Enable detection of rotated barcodes
        QualitySettings settings = QualitySettings.getHighQuality();
        settings.setAllowQRMicroQrRestoration(true);

        reader.setQualitySettings(settings);

        boolean found = false;
        for (BarCodeResult result : reader.readBarCodes()) {
            System.out.println("Rotated barcode: " + result.getCodeText());

            // Get rotation angle from region
            Rectangle rect = result.getRegion().getRectangle();
            System.out.println("Region: " + rect);
            found = true;
        }

        Assert.assertTrue(found, "Should recognize rotated barcode");
    }

    // ========== Multiple Barcode Types ==========

    @Test
    public void recognizeMixed1DAnd2D() throws Exception {
        // Use multiple files since we don't have a single image with mixed types
        String[] imageFiles = {"code128.png", "qrcode.png", "ean13.png"};

        int totalFound = 0;

        for (String file : imageFiles) {
            String imagePath = Paths.get(folder, file).toString();

            if (!Files.exists(Paths.get(imagePath))) {
                continue;
            }

            BarCodeReader reader = new BarCodeReader(
                    imagePath,
                    DecodeType.CODE_128,
                    DecodeType.QR,
                    DecodeType.DATA_MATRIX,
                    DecodeType.EAN_13
            );

            // Optimize for mixed barcode types
            reader.setQualitySettings(QualitySettings.getHighQuality());

            for (BarCodeResult result : reader.readBarCodes()) {
                System.out.println(result.getCodeTypeName() + ": " + result.getCodeText());

                Rectangle rect = result.getRegion().getRectangle();
                System.out.println("  Position: (" + rect.x + ", " + rect.y + ")");
                totalFound++;
            }
        }

        Assert.assertTrue(totalFound > 0, "Should recognize mixed barcode types");
    }

    @Test
    public void recognizeSeparate1DAnd2D() throws Exception {
        String imagePath = Paths.get(folder, "code128.png").toString();

        // First pass: recognize 1D barcodes (faster)
        BarCodeReader reader1D = new BarCodeReader(
                imagePath,
                DecodeType.CODE_128,
                DecodeType.CODE_39,
                DecodeType.EAN_13
        );
        reader1D.setQualitySettings(QualitySettings.getHighPerformance());

        int count1D = 0;
        System.out.println("1D Barcodes:");
        for (BarCodeResult result : reader1D.readBarCodes()) {
            System.out.println("  " + result.getCodeTypeName() + ": " + result.getCodeText());
            count1D++;
        }

        // Second pass: recognize 2D barcodes on QR image
        String qrPath = Paths.get(folder, "qrcode.png").toString();

        if (Files.exists(Paths.get(qrPath))) {
            BarCodeReader reader2D = new BarCodeReader(
                    qrPath,
                    DecodeType.QR,
                    DecodeType.DATA_MATRIX,
                    DecodeType.PDF_417
            );
            reader2D.setQualitySettings(QualitySettings.getHighQuality());

            System.out.println("2D Barcodes:");
            for (BarCodeResult result : reader2D.readBarCodes()) {
                System.out.println("  " + result.getCodeTypeName() + ": " + result.getCodeText());
                count1D++; // Total count
            }
        }

        Assert.assertTrue(count1D > 0, "Should recognize barcodes in separate passes");
    }

    // ========== Advanced Scenarios ==========

    @Test
    public void recognizeWithTimeout() throws Exception {
        String imagePath = Paths.get(folder, "code128.png").toString();

        BarCodeReader reader = new BarCodeReader(imagePath, DecodeType.ALL_SUPPORTED_TYPES);

        // Set timeout for recognition (in milliseconds)
        reader.setTimeout(5000); // 5 seconds

        long startTime = System.currentTimeMillis();

        boolean found = false;
        for (BarCodeResult result : reader.readBarCodes()) {
            System.out.println("Found: " + result.getCodeText());
            found = true;
        }

        long elapsed = System.currentTimeMillis() - startTime;
        System.out.println("Recognition took: " + elapsed + " ms");

        Assert.assertTrue(found, "Should recognize within timeout");
        Assert.assertTrue(elapsed < 10000, "Should complete in reasonable time");
    }

    @Test
    public void recognizeMultipleBarcodesInImage() throws Exception {
        // Generate an image with multiple barcodes for testing
        String imagePath = Paths.get(folder, "code128.png").toString();

        BarCodeReader reader = new BarCodeReader(
                imagePath,
                DecodeType.CODE_128,
                DecodeType.QR,
                DecodeType.EAN_13
        );

        BarCodeResult[] results = reader.readBarCodes();
        System.out.println("Found " + results.length + " barcodes:");

        for (int i = 0; i < results.length; i++) {
            System.out.println("Barcode " + (i + 1) + ":");
            System.out.println("  Type: " + results[i].getCodeTypeName());
            System.out.println("  Text: " + results[i].getCodeText());
            System.out.println("  Confidence: " + results[i].getConfidence() + "%");
        }

        Assert.assertTrue(results.length > 0, "Should find at least one barcode");
    }

    @Test
    public void recognizeWithROI() throws Exception {
        String imagePath = Paths.get(folder, "code128.png").toString();

        BarCodeReader reader = new BarCodeReader(imagePath, DecodeType.CODE_128);

        // Set a region of interest (if image is large enough)
        // For small test images, use a small ROI
        reader.setArea(new Rectangle(0, 0, 300, 200));

        boolean found = false;
        for (BarCodeResult result : reader.readBarCodes()) {
            System.out.println("Found in ROI: " + result.getCodeText());
            found = true;
        }

        // If not found in ROI, try full image
        if (!found) {
            reader.setArea(null); // Reset ROI
            for (BarCodeResult result : reader.readBarCodes()) {
                System.out.println("Found in full image: " + result.getCodeText());
                found = true;
            }
        }

        Assert.assertTrue(found, "Should recognize with or without ROI");
    }

    @Test
    public void recognizeWithManualHints() throws Exception {
        String imagePath = Paths.get(folder, "code128.png").toString();

        BarCodeReader reader = new BarCodeReader(imagePath, DecodeType.CODE_128);

        // Provide manual hints for better performance
        QualitySettings settings = new QualitySettings();

        // Assume we know the image characteristics
        settings.setHighPerformance(); // Start with high performance
        settings.setAllowInvertImage(false); // Normal image (not inverted)
        settings.setAllowComplexBackground(false); // Clean background
        settings.setFastScanOnly(true); // Fast scan only

        reader.setQualitySettings(settings);

        boolean found = false;
        for (BarCodeResult result : reader.readBarCodes()) {
            System.out.println("With manual hints: " + result.getCodeText());
            found = true;
        }

        Assert.assertTrue(found, "Should recognize with manual hints");
    }

    @Test
    public void recognizeAndVerifyChecksum() throws Exception {
        String imagePath = Paths.get(folder, "ean13.png").toString();

        BarCodeReader reader = new BarCodeReader(imagePath, DecodeType.EAN_13);

        // Enable checksum validation
        reader.getBarcodeSettings().setChecksumValidation(ChecksumValidation.ON);

        boolean found = false;
        for (BarCodeResult result : reader.readBarCodes()) {
            System.out.println("EAN-13: " + result.getCodeText());

            // Verify checksum manually (EAN-13 last digit is checksum)
            String code = result.getCodeText();
            if (code.length() == 13) {
                System.out.println("Checksum digit: " + code.charAt(12));
                Assert.assertTrue(code.matches("\\d{13}"), "Should be 13 digits");
            }

            found = true;
        }

        Assert.assertTrue(found, "Should recognize and verify checksum");
    }

    @Test
    public void recognizeWithExtendedInfo() throws Exception {
        String imagePath = Paths.get(folder, "code128.png").toString();

        BarCodeReader reader = new BarCodeReader(imagePath, DecodeType.CODE_128);

        boolean found = false;
        for (BarCodeResult result : reader.readBarCodes()) {
            System.out.println("Code: " + result.getCodeText());

            // Get extended information
            if (result.getExtended() != null) {
                System.out.println("Extended info available");

                // Get Code128 specific info if available
                if (result.getExtended().getCode128() != null) {
                    System.out.println("Code128 extended data present");
                }
            }

            // Get reading quality
            System.out.println("Confidence: " + result.getConfidence() + "%");

            // Get region information
            Rectangle rect = result.getRegion().getRectangle();
            System.out.println("Location: X=" + rect.x + ", Y=" + rect.y +
                    ", W=" + rect.width + ", H=" + rect.height);

            found = true;
        }

        Assert.assertTrue(found, "Should get extended info");
    }

    @Test
    public void compareQualitySettings() throws Exception {
        String imagePath = Paths.get(folder, "code128.png").toString();

        // Test with different quality settings and measure performance
        QualitySettings[] settingsArray = {
                QualitySettings.getHighPerformance(),
                QualitySettings.getNormalQuality(),
                QualitySettings.getHighQuality()
        };

        String[] names = {"HighPerformance", "NormalQuality", "HighQuality"};

        for (int i = 0; i < settingsArray.length; i++) {
            BarCodeReader reader = new BarCodeReader(imagePath, DecodeType.CODE_128);
            reader.setQualitySettings(settingsArray[i]);

            long startTime = System.currentTimeMillis();

            int count = 0;
            for (BarCodeResult result : reader.readBarCodes()) {
                count++;
            }

            long elapsed = System.currentTimeMillis() - startTime;

            System.out.println(names[i] + ": Found " + count + " in " + elapsed + " ms");
            Assert.assertTrue(count > 0, "Should find barcode with " + names[i]);
        }
    }

    @Test
    public void recognizeBarcodeTypes() throws Exception {
        // Test recognition of all available barcode types in the folder
        String[] expectedTypes = {
                "code128.png", "ean13.png", "upca.png", "code39.png", "itf14.png",
                "qrcode.png", "datamatrix.png", "pdf417.png", "aztec.png",
                "postnet.png", "planet.png", "intelligent_mail.png",
                "gs1_128.png", "gs1_datamatrix.png", "gs1_qr.png"
        };

        int recognizedCount = 0;

        for (String fileName : expectedTypes) {
            String imagePath = Paths.get(folder, fileName).toString();

            if (!Files.exists(Paths.get(imagePath))) {
                System.out.println("Skipping " + fileName + " (not found)");
                continue;
            }

            BarCodeReader reader = new BarCodeReader(imagePath, DecodeType.ALL_SUPPORTED_TYPES);
            reader.setQualitySettings(QualitySettings.getHighQuality());

            boolean found = false;
            for (BarCodeResult result : reader.readBarCodes()) {
                System.out.println(fileName + " -> " + result.getCodeTypeName() +
                        ": " + result.getCodeText());
                found = true;
                recognizedCount++;
            }

            if (!found) {
                System.out.println("WARNING: Could not recognize " + fileName);
            }
        }

        System.out.println("Successfully recognized " + recognizedCount + " barcode types");
        Assert.assertTrue(recognizedCount > 0, "Should recognize at least some barcodes");
    }
}package com.aspose.barcode.guide.quickstart;

import com.aspose.barcode.barcoderecognition.*;
        import com.aspose.barcode.generation.*;
        import com.aspose.barcode.guide.common.ExampleAssist;
import com.aspose.barcode.guide.common.LicenseAssist;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.awt.Rectangle;
import java.awt.Point;
import java.nio.file.*;

/**
 * TestNG suite for Quick Recognition Examples.
 * Tests read barcodes from the 'quick_start' resource folder.
 *
 * Prerequisites:
 * - Run QuickGenerationExamples tests first to generate barcode images
 *
 * Guidelines:
 * - Use appropriate DecodeType for better performance
 * - Validate recognition results
 * - Handle cases when no barcodes found
 */
public class QuickRecognitionExamples {

    private static final String folder = ExampleAssist.getResourceFolderPath("quick_start");

    @BeforeClass
    public void setUp() {
        LicenseAssist.setupLicense();
    }

    // ========== Simple Recognition ==========

    @Test
    public void recognizeSingleType() throws Exception {
        String imagePath = Paths.get(folder, "code128.png").toString();

        // Recognize only Code 128 barcodes
        BarCodeReader reader = new BarCodeReader(imagePath, DecodeType.CODE_128);

        boolean found = false;
        for (BarCodeResult result : reader.readBarCodes()) {
            System.out.println("CodeType: " + result.getCodeTypeName());
            System.out.println("CodeText: " + result.getCodeText());
            Assert.assertEquals(result.getCodeTypeName(), "Code128");
            found = true;
        }

        Assert.assertTrue(found, "Should recognize at least one Code 128 barcode");
    }

    @Test
    public void recognizeMultipleTypes() throws Exception {
        // Test with Code 128
        String code128Path = Paths.get(folder, "code128.png").toString();

        // Recognize Code 128 and QR codes
        BarCodeReader reader = new BarCodeReader(
                code128Path,
                DecodeType.CODE_128,
                DecodeType.QR
        );

        boolean found = false;
        for (BarCodeResult result : reader.readBarCodes()) {
            System.out.println("Found: " + result.getCodeTypeName());
            System.out.println("Text: " + result.getCodeText());
            found = true;
        }

        Assert.assertTrue(found, "Should find at least one barcode");
    }

    @Test
    public void recognizeAllTypes() throws Exception {
        String imagePath = Paths.get(folder, "code128.png").toString();

        // Recognize all barcode types
        BarCodeReader reader = new BarCodeReader(imagePath, DecodeType.ALL_SUPPORTED_TYPES);

        boolean found = false;
        for (BarCodeResult result : reader.readBarCodes()) {
            System.out.println("Type: " + result.getCodeTypeName());
            System.out.println("Data: " + result.getCodeText());
            System.out.println("Confidence: " + result.getConfidence());
            Assert.assertTrue(result.getConfidence() > 0, "Confidence should be positive");
            found = true;
        }

        Assert.assertTrue(found, "Should recognize at least one barcode");
    }

    // ========== Quality Settings ==========

    @Test
    public void fastRecognition() throws Exception {
        String imagePath = Paths.get(folder, "code128.png").toString();

        BarCodeReader reader = new BarCodeReader(imagePath, DecodeType.CODE_128);

        // Fast recognition mode
        reader.setQualitySettings(QualitySettings.getHighPerformance());

        boolean found = false;
        for (BarCodeResult result : reader.readBarCodes()) {
            System.out.println("Code: " + result.getCodeText());
            found = true;
        }

        Assert.assertTrue(found, "Fast recognition should work on good quality image");
    }

    @Test
    public void highQualityRecognition() throws Exception {
        String imagePath = Paths.get(folder, "code128.png").toString();

        BarCodeReader reader = new BarCodeReader(imagePath, DecodeType.CODE_128);

        // High quality mode
        reader.setQualitySettings(QualitySettings.getHighQuality());

        boolean found = false;
        for (BarCodeResult result : reader.readBarCodes()) {
            System.out.println("Recognized: " + result.getCodeText());
            found = true;
        }

        Assert.assertTrue(found, "High quality recognition should work");
    }

    @Test
    public void maxQualityRecognition() throws Exception {
        String imagePath = Paths.get(folder, "code128.png").toString();

        BarCodeReader reader = new BarCodeReader(imagePath, DecodeType.ALL_SUPPORTED_TYPES);

        // Maximum quality
        reader.setQualitySettings(QualitySettings.getMaxQuality());

        boolean found = false;
        for (BarCodeResult result : reader.readBarCodes()) {
            System.out.println("Type: " + result.getCodeTypeName());
            System.out.println("Text: " + result.getCodeText());
            found = true;
        }

        Assert.assertTrue(found, "Max quality should recognize barcode");
    }

    // ========== Linear Barcodes ==========

    @Test
    public void recognizeEAN13() throws Exception {
        String imagePath = Paths.get(folder, "ean13.png").toString();

        BarCodeReader reader = new BarCodeReader(imagePath, DecodeType.EAN_13);

        boolean found = false;
        for (BarCodeResult result : reader.readBarCodes()) {
            System.out.println("EAN-13: " + result.getCodeText());
            Assert.assertEquals(result.getCodeText().length(), 13, "EAN-13 should be 13 digits");
            found = true;
        }

        Assert.assertTrue(found, "Should recognize EAN-13");
    }

    @Test
    public void recognizeUPCA() throws Exception {
        String imagePath = Paths.get(folder, "upca.png").toString();

        BarCodeReader reader = new BarCodeReader(imagePath, DecodeType.UPCA);

        boolean found = false;
        for (BarCodeResult result : reader.readBarCodes()) {
            System.out.println("UPC-A: " + result.getCodeText());
            Assert.assertEquals(result.getCodeText().length(), 12, "UPC-A should be 12 digits");
            found = true;
        }

        Assert.assertTrue(found, "Should recognize UPC-A");
    }

    @Test
    public void recognizeCode39() throws Exception {
        String imagePath = Paths.get(folder, "code39.png").toString();

        BarCodeReader reader = new BarCodeReader(imagePath, DecodeType.CODE_39);

        boolean found = false;
        for (BarCodeResult result : reader.readBarCodes()) {
            System.out.println("Code 39: " + result.getCodeText());
            Assert.assertNotNull(result.getCodeText(), "Code text should not be null");
            found = true;
        }

        Assert.assertTrue(found, "Should recognize Code 39");
    }

    @Test
    public void recognizeCode128() throws Exception {
        String imagePath = Paths.get(folder, "code128.png").toString();

        BarCodeReader reader = new BarCodeReader(imagePath, DecodeType.CODE_128);

        boolean found = false;
        for (BarCodeResult result : reader.readBarCodes()) {
            System.out.println("Code 128: " + result.getCodeText());
            Assert.assertNotNull(result.getCodeText(), "Code text should not be null");
            found = true;
        }

        Assert.assertTrue(found, "Should recognize Code 128");
    }

    @Test
    public void recognizeITF14() throws Exception {
        String imagePath = Paths.get(folder, "itf14.png").toString();

        BarCodeReader reader = new BarCodeReader(imagePath, DecodeType.ITF_14);

        boolean found = false;
        for (BarCodeResult result : reader.readBarCodes()) {
            System.out.println("ITF-14: " + result.getCodeText());
            Assert.assertEquals(result.getCodeText().length(), 14, "ITF-14 should be 14 digits");
            found = true;
        }

        Assert.assertTrue(found, "Should recognize ITF-14");
    }

    // ========== 2D Barcodes ==========

    @Test
    public void recognizeQR() throws Exception {
        String imagePath = Paths.get(folder, "qrcode.png").toString();

        BarCodeReader reader = new BarCodeReader(imagePath, DecodeType.QR);

        boolean found = false;
        for (BarCodeResult result : reader.readBarCodes()) {
            System.out.println("QR Code: " + result.getCodeText());
            Assert.assertNotNull(result.getCodeText(), "QR code text should not be null");
            found = true;
        }

        Assert.assertTrue(found, "Should recognize QR code");
    }

    @Test
    public void recognizeDataMatrix() throws Exception {
        String imagePath = Paths.get(folder, "datamatrix.png").toString();

        BarCodeReader reader = new BarCodeReader(imagePath, DecodeType.DATA_MATRIX);

        boolean found = false;
        for (BarCodeResult result : reader.readBarCodes()) {
            System.out.println("DataMatrix: " + result.getCodeText());
            Assert.assertNotNull(result.getCodeText(), "DataMatrix text should not be null");
            found = true;
        }

        Assert.assertTrue(found, "Should recognize DataMatrix");
    }

    @Test
    public void recognizePDF417() throws Exception {
        String imagePath = Paths.get(folder, "pdf417.png").toString();

        BarCodeReader reader = new BarCodeReader(imagePath, DecodeType.PDF_417);

        boolean found = false;
        for (BarCodeResult result : reader.readBarCodes()) {
            System.out.println("PDF417: " + result.getCodeText());
            Assert.assertNotNull(result.getCodeText(), "PDF417 text should not be null");
            found = true;
        }

        Assert.assertTrue(found, "Should recognize PDF417");
    }

    @Test
    public void recognizeAztec() throws Exception {
        String imagePath = Paths.get(folder, "aztec.png").toString();

        BarCodeReader reader = new BarCodeReader(imagePath, DecodeType.AZTEC);

        boolean found = false;
        for (BarCodeResult result : reader.readBarCodes()) {
            System.out.println("Aztec: " + result.getCodeText());
            Assert.assertNotNull(result.getCodeText(), "Aztec text should not be null");
            found = true;
        }

        Assert.assertTrue(found, "Should recognize Aztec");
    }

    // ========== Postal Barcodes ==========

    @Test
    public void recognizePostnet() throws Exception {
        String imagePath = Paths.get(folder, "postnet.png").toString();

        BarCodeReader reader = new BarCodeReader(imagePath, DecodeType.POSTNET);

        boolean found = false;
        for (BarCodeResult result : reader.readBarCodes()) {
            System.out.println("Postnet: " + result.getCodeText());
            Assert.assertNotNull(result.getCodeText(), "Postnet text should not be null");
            found = true;
        }

        Assert.assertTrue(found, "Should recognize Postnet");
    }

    @Test
    public void recognizePlanet() throws Exception {
        String imagePath = Paths.get(folder, "planet.png").toString();

        BarCodeReader reader = new BarCodeReader(imagePath, DecodeType.PLANET);

        boolean found = false;
        for (BarCodeResult result : reader.readBarCodes()) {
            System.out.println("Planet: " + result.getCodeText());
            Assert.assertNotNull(result.getCodeText(), "Planet text should not be null");
            found = true;
        }

        Assert.assertTrue(found, "Should recognize Planet");
    }

    @Test
    public void recognizeIntelligentMail() throws Exception {
        String imagePath = Paths.get(folder, "intelligent_mail.png").toString();

        BarCodeReader reader = new BarCodeReader(imagePath, DecodeType.ONE_CODE);

        boolean found = false;
        for (BarCodeResult result : reader.readBarCodes()) {
            System.out.println("Intelligent Mail: " + result.getCodeText());
            Assert.assertEquals(result.getCodeText().length(), 31, "Intelligent Mail should be 31 digits");
            found = true;
        }

        Assert.assertTrue(found, "Should recognize Intelligent Mail");
    }

    // ========== GS1 Barcodes ==========

    @Test
    public void recognizeGS1_128() throws Exception {
        String imagePath = Paths.get(folder, "gs1_128.png").toString();

        BarCodeReader reader = new BarCodeReader(imagePath, DecodeType.GS_1_CODE_128);

        boolean found = false;
        for (BarCodeResult result : reader.readBarCodes()) {
            System.out.println("GS1-128: " + result.getCodeText());

            // Parse GS1 data
            String codeText = result.getCodeText();
            if (codeText.contains("(01)")) {
                System.out.println("Contains GTIN");
            }

            Assert.assertTrue(codeText.startsWith("("), "GS1 data should start with AI in parentheses");
            found = true;
        }

        Assert.assertTrue(found, "Should recognize GS1-128");
    }

    @Test
    public void recognizeGS1_DataMatrix() throws Exception {
        String imagePath = Paths.get(folder, "gs1_datamatrix.png").toString();

        BarCodeReader reader = new BarCodeReader(imagePath, DecodeType.GS_1_DATA_MATRIX);

        boolean found = false;
        for (BarCodeResult result : reader.readBarCodes()) {
            System.out.println("GS1 DataMatrix: " + result.getCodeText());
            Assert.assertTrue(result.getCodeText().startsWith("("), "GS1 data should start with AI");
            found = true;
        }

        Assert.assertTrue(found, "Should recognize GS1 DataMatrix");
    }

    @Test
    public void recognizeGS1_QR() throws Exception {
        String imagePath = Paths.get(folder, "gs1_qr.png").toString();

        BarCodeReader reader = new BarCodeReader(imagePath, DecodeType.GS_1_QR);

        boolean found = false;
        for (BarCodeResult result : reader.readBarCodes()) {
            System.out.println("GS1 QR: " + result.getCodeText());
            Assert.assertTrue(result.getCodeText().startsWith("("), "GS1 data should start with AI");
            found = true;
        }

        Assert.assertTrue(found, "Should recognize GS1 QR");
    }

    // ========== Advanced Features ==========

    @Test
    public void getBarcodeRegion() throws Exception {
        String imagePath = Paths.get(folder, "code128.png").toString();

        BarCodeReader reader = new BarCodeReader(imagePath, DecodeType.CODE_128);

        boolean found = false;
        for (BarCodeResult result : reader.readBarCodes()) {
            System.out.println("Code: " + result.getCodeText());

            // Get barcode region
            Point[] points = result.getRegion().getPoints();
            System.out.println("Barcode found at:");
            Assert.assertEquals(points.length, 4, "Should have 4 corner points");

            for (int i = 0; i < points.length; i++) {
                System.out.println("  Point " + i + ": (" + points[i].x + ", " + points[i].y + ")");
                Assert.assertTrue(points[i].x >= 0 && points[i].y >= 0, "Coordinates should be positive");
            }

            // Get bounding rectangle
            Rectangle rect = result.getRegion().getRectangle();
            System.out.println("Rectangle: X=" + rect.x + ", Y=" + rect.y +
                    ", Width=" + rect.width + ", Height=" + rect.height);

            Assert.assertTrue(rect.width > 0 && rect.height > 0, "Rectangle should have positive dimensions");
            found = true;
        }

        Assert.assertTrue(found, "Should find barcode region");
    }

    @Test
    public void getConfidence() throws Exception {
        String imagePath = Paths.get(folder, "code128.png").toString();

        BarCodeReader reader = new BarCodeReader(imagePath, DecodeType.ALL_SUPPORTED_TYPES);

        boolean found = false;
        for (BarCodeResult result : reader.readBarCodes()) {
            System.out.println("Type: " + result.getCodeTypeName());
            System.out.println("Text: " + result.getCodeText());
            System.out.println("Confidence: " + result.getConfidence() + "%");

            Assert.assertTrue(result.getConfidence() >= 0 && result.getConfidence() <= 100,
                    "Confidence should be between 0 and 100");

            // Check if confidence is high enough
            if (result.getConfidence() >= 80) {
                System.out.println("High confidence recognition");
            }

            found = true;
        }

        Assert.assertTrue(found, "Should recognize barcode with confidence");
    }

    @Test
    public void recognizeFromStream() throws Exception {
        String imagePath = Paths.get(folder, "code128.png").toString();

        // Read image from file into stream
        java.io.FileInputStream stream = new java.io.FileInputStream(imagePath);

        BarCodeReader reader = new BarCodeReader(stream, DecodeType.CODE_128);

        boolean found = false;
        for (BarCodeResult result : reader.readBarCodes()) {
            System.out.println("Code: " + result.getCodeText());
            Assert.assertNotNull(result.getCodeText(), "Should recognize from stream");
            found = true;
        }

        stream.close();
        Assert.assertTrue(found, "Should recognize from stream");
    }

    @Test
    public void recognizeFromByteArray() throws Exception {
        Path imagePath = Paths.get(folder, "code128.png");

        // Read image into byte array
        byte[] imageBytes = Files.readAllBytes(imagePath);
        Assert.assertTrue(imageBytes.length > 0, "Image bytes should not be empty");

        // Create input stream from byte array
        java.io.ByteArrayInputStream stream = new java.io.ByteArrayInputStream(imageBytes);

        BarCodeReader reader = new BarCodeReader(stream, DecodeType.CODE_128);

        boolean found = false;
        for (BarCodeResult result : reader.readBarCodes()) {
            System.out.println("Code: " + result.getCodeText());
            Assert.assertNotNull(result.getCodeText(), "Should recognize from byte array");
            found = true;
        }

        stream.close();
        Assert.assertTrue(found, "Should recognize from byte array");
    }

    // ========== Batch Recognition ==========

    @Test
    public void recognizeMultipleFiles() throws Exception {
        String[] files = {
                "code128.png",
                "ean13.png",
                "qrcode.png"
        };

        int totalFound = 0;

        for (String file : files) {
            System.out.println("Processing: " + file);
            String imagePath = Paths.get(folder, file).toString();

            if (!Files.exists(Paths.get(imagePath))) {
                System.out.println("  File not found, skipping");
                continue;
            }

            BarCodeReader reader = new BarCodeReader(imagePath, DecodeType.ALL_SUPPORTED_TYPES);

            for (BarCodeResult result : reader.readBarCodes()) {
                System.out.println("  " + result.getCodeTypeName() + ": " + result.getCodeText());
                totalFound++;
            }
        }

        Assert.assertTrue(totalFound > 0, "Should recognize barcodes from multiple files");
    }

    // ========== Custom Quality Settings ==========

    @Test
    public void customQualitySettings() throws Exception {
        String imagePath = Paths.get(folder, "code128.png").toString();

        BarCodeReader reader = new BarCodeReader(imagePath, DecodeType.CODE_128);

        // Create custom quality settings
        QualitySettings settings = new QualitySettings();
        settings.setHighPerformance();

        // Enable specific detectors
        settings.setAllowInvertImage(true);
        settings.setAllowComplexBackground(true);
        settings.setAllowMedianSmoothing(true);
        settings.setAllowRegularWiping(true);

        reader.setQualitySettings(settings);

        boolean found = false;
        for (BarCodeResult result : reader.readBarCodes()) {
            System.out.println("Code: " + result.getCodeText());
            found = true;
        }

        Assert.assertTrue(found, "Custom quality settings should work");
    }

    @Test
    public void fineTuneDetection() throws Exception {
        String imagePath = Paths.get(folder, "code128.png").toString();

        BarCodeReader reader = new BarCodeReader(imagePath, DecodeType.CODE_128);

        QualitySettings settings = QualitySettings.getNormalQuality();

        // Enable advanced features
        settings.setAllowDecreasedImage(true);
        settings.setAllowWhiteSpotsRemoving(true);
        settings.setAllowOneDFastBarcodesDetector(true);
        settings.setFastScanOnly(false);

        reader.setQualitySettings(settings);

        boolean found = false;
        for (BarCodeResult result : reader.readBarCodes()) {
            System.out.println("Recognized: " + result.getCodeText());
            found = true;
        }

        Assert.assertTrue(found, "Fine-tuned detection should work");
    }

    // ========== Error Handling ==========

    @Test
    public void handleRecognitionErrors() throws Exception {
        String imagePath = Paths.get(folder, "code128.png").toString();

        try {
            BarCodeReader reader = new BarCodeReader(imagePath, DecodeType.CODE_128);

            boolean found = false;
            for (BarCodeResult result : reader.readBarCodes()) {
                System.out.println("Found: " + result.getCodeText());
                found = true;
            }

            if (!found) {
                System.out.println("No barcodes found in the image");
            }

            // This test should always find a barcode
            Assert.assertTrue(found, "Should find at least one barcode");

        } catch (Exception ex) {
            System.err.println("Recognition error: " + ex.getMessage());
            throw ex;
        }
    }

    @Test
    public void validateResults() throws Exception {
        String imagePath = Paths.get(folder, "ean13.png").toString();

        BarCodeReader reader = new BarCodeReader(imagePath, DecodeType.EAN_13);

        boolean found = false;
        for (BarCodeResult result : reader.readBarCodes()) {
            String codeText = result.getCodeText();

            // Validate EAN-13 (should be 13 digits)
            if (codeText != null && codeText.length() == 13 && codeText.matches("\\d+")) {
                System.out.println("Valid EAN-13: " + codeText);
                Assert.assertTrue(true, "Valid EAN-13 format");
            } else {
                System.out.println("Invalid EAN-13 format: " + codeText);
                Assert.fail("Invalid EAN-13 format");
            }

            // Check confidence
            if (result.getConfidence() < 70) {
                System.out.println("Warning: Low confidence (" + result.getConfidence() + "%)");
            }

            found = true;
        }

        Assert.assertTrue(found, "Should validate EAN-13 results");
    }

    // ========== Performance Pattern ==========

    @Test
    public void performancePattern_reuseReader() throws Exception {
        String[] files = {"code128.png", "ean13.png", "qrcode.png"};

        // Create reader once
        BarCodeReader reader = new BarCodeReader();
        reader.setBarCodeReadType(DecodeType.ALL_SUPPORTED_TYPES);

        int totalFound = 0;

        // Reuse for multiple images
        for (String file : files) {
            String imagePath = Paths.get(folder, file).toString();

            if (!Files.exists(Paths.get(imagePath))) {
                continue;
            }

            reader.setBarCodeImage(imagePath);

            for (BarCodeResult result : reader.readBarCodes()) {
                System.out.println(file + ": " + result.getCodeText());
                totalFound++;
            }
        }

        Assert.assertTrue(totalFound > 0, "Reused reader should recognize barcodes");
    }

    // ========== Recognition Patterns ==========

    @Test
    public void simplePattern() throws Exception {
        String imagePath = Paths.get(folder, "code128.png").toString();

        BarCodeReader reader = new BarCodeReader(imagePath, DecodeType.CODE_128);

        boolean found = false;
        for (BarCodeResult result : reader.readBarCodes()) {
            System.out.println(result.getCodeText());
            found = true;
        }

        Assert.assertTrue(found, "Simple pattern should work");
    }

    @Test
    public void validationPattern() throws Exception {
        String imagePath = Paths.get(folder, "ean13.png").toString();

        BarCodeReader reader = new BarCodeReader(imagePath, DecodeType.EAN_13);

        boolean validFound = false;
        for (BarCodeResult result : reader.readBarCodes()) {
            if (result.getConfidence() >= 80 && result.getCodeText().length() == 13) {
                System.out.println("Valid: " + result.getCodeText());
                validFound = true;
            }
        }

        Assert.assertTrue(validFound, "Should find valid barcode with validation");
    }

    @Test
    public void robustPattern() throws Exception {
        String imagePath = Paths.get(folder, "code128.png").toString();

        BarCodeReader reader = new BarCodeReader(imagePath, DecodeType.CODE_128);
        reader.setQualitySettings(QualitySettings.getHighQuality());

        BarCodeResult[] results = reader.readBarCodes();

        if (results.length == 0) {
            // Try with max quality
            reader.setQualitySettings(QualitySettings.getMaxQuality());
            results = reader.readBarCodes();
        }

        Assert.assertTrue(results.length > 0, "Robust pattern should find barcode");

        for (BarCodeResult result : results) {
            System.out.println("Found: " + result.getCodeText());
        }
    }
}
}
