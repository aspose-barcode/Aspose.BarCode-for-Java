package com.aspose.barcode.guide.recognition.input_sources;

import com.aspose.barcode.barcoderecognition.*;
import com.aspose.barcode.generation.*;
import com.aspose.barcode.guide.common.ExampleAssist;
import com.aspose.barcode.guide.common.LicenseAssist;
import org.testng.Assert;
import org.testng.annotations.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Input Sources Examples Class
 * Demonstrates how to use BarCodeReader with various input sources.
 *
 * Covers:
 * - File path (String)
 * - File object
 * - InputStream
 * - BufferedImage
 * - Byte array
 * - Base64 string
 * - Multiple files
 * - URL/Remote images
 */
public class InputSourcesExamples
{
    private static final String TEST_IMAGES_FOLDER = ExampleAssist.getOrCreateResourceFolderPath("quick_start", "input_sources", "test_images");

    private static String testImagePath;
    private static final String TEST_DATA = "INPUT-SOURCE-TEST";

    @BeforeClass
    public void setUp() throws Exception
    {
        LicenseAssist.setupLicense();

        // Generate test barcode for all examples
        testImagePath = generateTestBarcode(TEST_DATA, "test_input_source.png",EncodeTypes.CODE_128);

        System.out.println("=== Starting Input Sources Tests ===");
        System.out.println("Test image: " + testImagePath + "\n");
    }

    // ==================== FILE PATH ====================

    /**
     * Test 1: Recognition from file path (String)
     * This is the most common and simplest way.
     */
    @Test(priority = 1, description = "Recognition from file path")
    public void test01_FromFilePath()
    {
        System.out.println("Test 1: Recognition from File Path (String)");

        // Method 1: Direct path
        BarCodeReader reader1 = new BarCodeReader(testImagePath);
        BarCodeResult[] results1 = reader1.readBarCodes();

        Assert.assertTrue(results1.length > 0, "Should recognize barcode");
        Assert.assertEquals(results1[0].getCodeText(), TEST_DATA);

        System.out.println("  [OK] Direct path: " + results1[0].getCodeText());

        // Method 2: Path with specific type
        BarCodeReader reader2 = new BarCodeReader(testImagePath, DecodeType.CODE_128);
        BarCodeResult[] results2 = reader2.readBarCodes();

        Assert.assertEquals(results2.length, 1);
        Assert.assertEquals(results2[0].getCodeText(), TEST_DATA);

        System.out.println("  [OK] With type specified: " + results2[0].getCodeText());

        // Method 3: Path with multiple types
        BarCodeReader reader3 = new BarCodeReader(
                testImagePath,
                DecodeType.CODE_128,
                DecodeType.QR,
                DecodeType.DATA_MATRIX
        );
        BarCodeResult[] results3 = reader3.readBarCodes();

        Assert.assertTrue(results3.length > 0);
        System.out.println("  [OK] With multiple types: " + results3[0].getCodeText());

        System.out.println();
    }

    // ==================== FILE OBJECT ====================

    /**
     * Test 2: Recognition from File object
     * Useful when working with file system operations.
     */
    @Test(priority = 2, description = "Recognition from File object")
    public void test02_FromFileObject() throws Exception
    {
        System.out.println("Test 2: Recognition from File Object");

        // Create File object
        File imageFile = new File(testImagePath);

        // Check if file exists
        Assert.assertTrue(imageFile.exists(), "Test image should exist");
        System.out.println("  [INFO] File exists: " + imageFile.exists());
        System.out.println("  [INFO] File size: " + imageFile.length() + " bytes");

        // Method 1: From File object
        BarCodeReader reader = new BarCodeReader(imageFile.getAbsolutePath());
        BarCodeResult[] results = reader.readBarCodes();

        Assert.assertTrue(results.length > 0);
        Assert.assertEquals(results[0].getCodeText(), TEST_DATA);

        System.out.println("  [OK] Recognized from File: " + results[0].getCodeText());
        System.out.println();
    }

    // ==================== INPUT STREAM ====================

    /**
     * Test 3: Recognition from InputStream
     * Useful for reading from network, resources, or archives.
     */
    @Test(priority = 3, description = "Recognition from InputStream")
    public void test03_FromInputStream() throws Exception
    {
        System.out.println("Test 3: Recognition from InputStream");

        // Method 1: FileInputStream
        try (FileInputStream fis = new FileInputStream(testImagePath))
        {
            BarCodeReader reader = new BarCodeReader(fis);
            BarCodeResult[] results = reader.readBarCodes();

            Assert.assertTrue(results.length > 0);
            Assert.assertEquals(results[0].getCodeText(), TEST_DATA);

            System.out.println("  [OK] From FileInputStream: " + results[0].getCodeText());
        }

        // Method 2: BufferedInputStream (better performance)
        try (BufferedInputStream bis = new BufferedInputStream(
                new FileInputStream(testImagePath)))
        {
            BarCodeReader reader = new BarCodeReader(bis);
            BarCodeResult[] results = reader.readBarCodes();

            Assert.assertTrue(results.length > 0);
            System.out.println("  [OK] From BufferedInputStream: " +
                    results[0].getCodeText());
        }

        // Method 3: ByteArrayInputStream (from memory)
        byte[] imageBytes = Files.readAllBytes(Paths.get(testImagePath));
        try (ByteArrayInputStream bais = new ByteArrayInputStream(imageBytes))
        {
            BarCodeReader reader = new BarCodeReader(bais);
            BarCodeResult[] results = reader.readBarCodes();

            Assert.assertTrue(results.length > 0);
            System.out.println("  [OK] From ByteArrayInputStream: " +
                    results[0].getCodeText());
        }

        System.out.println();
    }

    // ==================== BUFFERED IMAGE ====================

    /**
     * Test 4: Recognition from BufferedImage
     * Useful when image is already loaded in memory or processed.
     */
    @Test(priority = 4, description = "Recognition from BufferedImage")
    public void test04_FromBufferedImage() throws Exception
    {
        System.out.println("Test 4: Recognition from BufferedImage");

        // Load image as BufferedImage
        BufferedImage image = ImageIO.read(new File(testImagePath));

        Assert.assertNotNull(image, "Image should be loaded");
        System.out.println("  [INFO] Image size: " + image.getWidth() +
                "x" + image.getHeight() + " pixels");

        // Recognize from BufferedImage
        BarCodeReader reader = new BarCodeReader(image);
        BarCodeResult[] results = reader.readBarCodes();

        Assert.assertTrue(results.length > 0);
        Assert.assertEquals(results[0].getCodeText(), TEST_DATA);

        System.out.println("  [OK] From BufferedImage: " + results[0].getCodeText());
        System.out.println();
    }

    /**
     * Test 5: Recognition from BufferedImage with specific type
     */
    @Test(priority = 5, description = "BufferedImage with barcode type")
    public void test05_FromBufferedImageWithType() throws Exception
    {
        System.out.println("Test 5: BufferedImage with Barcode Type");

        BufferedImage image = ImageIO.read(new File(testImagePath));

        // Method 1: Single type
        BarCodeReader reader1 = new BarCodeReader(image, DecodeType.CODE_128);
        BarCodeResult[] results1 = reader1.readBarCodes();

        Assert.assertEquals(results1.length, 1);
        System.out.println("  [OK] With single type: " + results1[0].getCodeText());

        // Method 2: Multiple types
        BarCodeReader reader2 = new BarCodeReader(
                image,
                DecodeType.CODE_128,
                DecodeType.QR
        );
        BarCodeResult[] results2 = reader2.readBarCodes();

        Assert.assertTrue(results2.length > 0);
        System.out.println("  [OK] With multiple types: " + results2[0].getCodeText());

        System.out.println();
    }

    // ==================== BYTE ARRAY ====================

    /**
     * Test 6: Recognition from byte array
     * Useful for images stored in database or received from API.
     */
    @Test(priority = 6, description = "Recognition from byte array")
    public void test06_FromByteArray() throws Exception
    {
        System.out.println("Test 6: Recognition from Byte Array");

        // Read image as byte array
        byte[] imageBytes = Files.readAllBytes(Paths.get(testImagePath));

        System.out.println("  [INFO] Image bytes length: " + imageBytes.length);

        // Convert to InputStream and recognize
        try (ByteArrayInputStream bais = new ByteArrayInputStream(imageBytes))
        {
            BarCodeReader reader = new BarCodeReader(bais);
            BarCodeResult[] results = reader.readBarCodes();

            Assert.assertTrue(results.length > 0);
            Assert.assertEquals(results[0].getCodeText(), TEST_DATA);

            System.out.println("  [OK] From byte array: " + results[0].getCodeText());
        }

        System.out.println();
    }

    // ==================== BASE64 STRING ====================

    /**
     * Test 7: Recognition from Base64 string
     * Useful for images transmitted as text (JSON, XML).
     */
    @Test(priority = 7, description = "Recognition from Base64 string")
    public void test07_FromBase64String() throws Exception
    {
        System.out.println("Test 7: Recognition from Base64 String");

        // Read image and convert to Base64
        byte[] imageBytes = Files.readAllBytes(Paths.get(testImagePath));
        String base64Image = java.util.Base64.getEncoder().encodeToString(imageBytes);

        System.out.println("  [INFO] Base64 length: " + base64Image.length() +
                " characters");
        System.out.println("  [INFO] Base64 preview: " +
                base64Image.substring(0, Math.min(50, base64Image.length())) + "...");

        // Decode Base64 and recognize
        byte[] decodedBytes = java.util.Base64.getDecoder().decode(base64Image);

        try (ByteArrayInputStream bais = new ByteArrayInputStream(decodedBytes))
        {
            BarCodeReader reader = new BarCodeReader(bais);
            BarCodeResult[] results = reader.readBarCodes();

            Assert.assertTrue(results.length > 0);
            Assert.assertEquals(results[0].getCodeText(), TEST_DATA);

            System.out.println("  [OK] From Base64: " + results[0].getCodeText());
        }

        System.out.println();
    }



    // ==================== MEMORY STREAM ====================

    /**
     * Test 9: Recognition from memory stream
     * Demonstrates working with in-memory image data.
     */
    @Test(priority = 9, description = "Recognition from memory stream")
    public void test9_FromMemoryStream() throws Exception
    {
        System.out.println("Test 10: Recognition from Memory Stream");

        // Generate barcode directly to memory
        BarcodeGenerator gen = new BarcodeGenerator(
                EncodeTypes.QR,
                "MEMORY-STREAM-TEST"
        );

        // Save to ByteArrayOutputStream
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        gen.save(baos, BarCodeImageFormat.PNG);

        System.out.println("  [INFO] Generated to memory: " +
                baos.size() + " bytes");

        // Recognize from memory
        byte[] imageData = baos.toByteArray();
        try (ByteArrayInputStream bais = new ByteArrayInputStream(imageData))
        {
            BarCodeReader reader = new BarCodeReader(bais, DecodeType.QR);
            BarCodeResult[] results = reader.readBarCodes();

            Assert.assertTrue(results.length > 0);
            Assert.assertEquals(results[0].getCodeText(), "MEMORY-STREAM-TEST");

            System.out.println("  [OK] From memory: " + results[0].getCodeText());
        }

        System.out.println();
    }

    // ==================== IMAGE PROCESSING ====================

    /**
     * Test 11: Recognition from processed image
     * Shows how to pre-process image before recognition.
     */
    @Test(priority = 11, description = "Recognition from processed image")
    public void test11_FromProcessedImage() throws Exception
    {
        System.out.println("Test 11: Recognition from Processed Image");

        // Load original image
        BufferedImage originalImage = ImageIO.read(new File(testImagePath));

        System.out.println("  [INFO] Original image: " +
                originalImage.getWidth() + "x" + originalImage.getHeight());

        // Example 1: Recognize original
        BarCodeReader reader1 = new BarCodeReader(originalImage);
        BarCodeResult[] results1 = reader1.readBarCodes();
        Assert.assertTrue(results1.length > 0);
        System.out.println("  [OK] Original image: " + results1[0].getCodeText());

        // Example 2: Create a copy and recognize
        BufferedImage copyImage = new BufferedImage(
                originalImage.getWidth(),
                originalImage.getHeight(),
                BufferedImage.TYPE_INT_RGB
        );

        java.awt.Graphics2D g2d = copyImage.createGraphics();
        g2d.drawImage(originalImage, 0, 0, null);
        g2d.dispose();

        BarCodeReader reader2 = new BarCodeReader(copyImage);
        BarCodeResult[] results2 = reader2.readBarCodes();
        Assert.assertTrue(results2.length > 0);
        System.out.println("  [OK] Copied image: " + results2[0].getCodeText());

        System.out.println();
    }

    // ==================== ERROR HANDLING ====================

    /**
     * Test 12: Error handling for invalid inputs
     */
    @Test(priority = 12, description = "Error handling for invalid inputs")
    public void test12_ErrorHandling() throws Exception
    {
        System.out.println("Test 12: Error Handling for Invalid Inputs");

        // Test 1: Non-existent file
        try
        {
            BarCodeReader reader = new BarCodeReader("non_existent_file.png");
            reader.readBarCodes();
            Assert.fail("Should throw exception for non-existent file");
        }
        catch (Exception e)
        {
            System.out.println("  [OK] Non-existent file handled: " +
                    e.getClass().getSimpleName());
        }

        // Test 2: Invalid file format (create text file with .png extension)
        String invalidFile = TEST_IMAGES_FOLDER + File.separator + "invalid.png";
        Files.write(Paths.get(invalidFile), "This is not an image".getBytes());

        try
        {
            BarCodeReader reader = new BarCodeReader(invalidFile);
            BarCodeResult[] results = reader.readBarCodes();
            System.out.println("  [INFO] Invalid format returned: " +
                    results.length + " results");
        }
        catch (Exception e)
        {
            System.out.println("  [OK] Invalid format handled: " +
                    e.getClass().getSimpleName());
        }

        // Test 3: Empty byte array
        try
        {
            byte[] emptyBytes = new byte[0];
            ByteArrayInputStream bais = new ByteArrayInputStream(emptyBytes);
            BarCodeReader reader = new BarCodeReader(bais);
            reader.readBarCodes();
            System.out.println("  [INFO] Empty array handled gracefully");
        }
        catch (Exception e)
        {
            System.out.println("  [OK] Empty array handled: " +
                    e.getClass().getSimpleName());
        }

        System.out.println();
    }

    // ==================== PRACTICAL EXAMPLES ====================

    /**
     * Practical 1: Recognition from HTTP response
     * Simulates receiving image from web service.
     */
    @Test(priority = 13, description = "Practical: HTTP response simulation",
            enabled = false)
    public void practical01_FromHttpResponse() throws Exception
    {
        System.out.println("Practical 1: From HTTP Response (Simulated)");

        // Simulate HTTP response with byte array
        byte[] httpResponseBody = Files.readAllBytes(Paths.get(testImagePath));

        System.out.println("  [INFO] Received " + httpResponseBody.length +
                " bytes from HTTP");

        // Process response
        try (ByteArrayInputStream bais = new ByteArrayInputStream(httpResponseBody))
        {
            BarCodeReader reader = new BarCodeReader(bais);
            BarCodeResult[] results = reader.readBarCodes();

            if (results.length > 0)
            {
                System.out.println("  [OK] HTTP Response: " + results[0].getCodeText());
            }
        }

        System.out.println();
    }

    /**
     * Practical 2: Recognition from database BLOB
     * Simulates reading image stored in database.
     */
    @Test(priority = 14, description = "Practical: Database BLOB simulation",
            enabled = false)
    public void practical02_FromDatabaseBlob() throws Exception
    {
        System.out.println("Practical 2: From Database BLOB (Simulated)");

        // Simulate database BLOB
        byte[] blobData = Files.readAllBytes(Paths.get(testImagePath));

        System.out.println("  [INFO] Retrieved BLOB: " + blobData.length + " bytes");

        // Process BLOB
        try (ByteArrayInputStream bais = new ByteArrayInputStream(blobData))
        {
            BarCodeReader reader = new BarCodeReader(bais);
            reader.setQualitySettings(QualitySettings.getHighPerformance());

            BarCodeResult[] results = reader.readBarCodes();

            if (results.length > 0)
            {
                System.out.println("  [OK] Database BLOB: " + results[0].getCodeText());

                // Simulate saving result back to database
                String recognizedText = results[0].getCodeText();
                System.out.println("  [INFO] Would UPDATE database SET " +
                        "barcode_text='" + recognizedText + "'");
            }
        }

        System.out.println();
    }

    /**
     * Practical 3: Recognition from uploaded file
     * Simulates web application file upload scenario.
     */
    @Test(priority = 15, description = "Practical: File upload simulation",
            enabled = false)
    public void practical03_FromUploadedFile() throws Exception
    {
        System.out.println("Practical 3: From Uploaded File (Simulated)");

        // Simulate file upload (multipart/form-data)
        File uploadedFile = new File(testImagePath);

        System.out.println("  [INFO] Uploaded file: " + uploadedFile.getName());
        System.out.println("  [INFO] Size: " + uploadedFile.length() + " bytes");
        System.out.println("  [INFO] Content-Type: image/png");

        // Validate file type
        String filename = uploadedFile.getName().toLowerCase();
        if (!filename.endsWith(".png") && !filename.endsWith(".jpg") &&
                !filename.endsWith(".jpeg"))
        {
            System.out.println("  [ERROR] Invalid file type");
            return;
        }

        // Process uploaded file
        try (FileInputStream fis = new FileInputStream(uploadedFile))
        {
            BarCodeReader reader = new BarCodeReader(fis);
            reader.setTimeout(5000); // 5 second timeout

            BarCodeResult[] results = reader.readBarCodes();

            if (results.length > 0)
            {
                System.out.println("  [OK] Uploaded file processed: " +
                        results[0].getCodeText());
                System.out.println("  [INFO] Would return JSON: " +
                        "{\"success\": true, \"barcode\": \"" +
                        results[0].getCodeText() + "\"}");
            }
            else
            {
                System.out.println("  [WARN] No barcode found in uploaded file");
                System.out.println("  [INFO] Would return JSON: " +
                        "{\"success\": false, \"error\": \"No barcode found\"}");
            }
        }

        System.out.println();
    }

    // ==================== HELPER METHODS ====================

    /**
     * Generate test barcode with custom data and filename
     */
    private String generateTestBarcode(String data, String filename, BaseEncodeType encodeType) throws Exception
    {
        BarcodeGenerator gen = new BarcodeGenerator(encodeType, data);

        gen.getParameters().setResolution(300f);
        gen.getParameters().getBarcode().getXDimension().setMillimeters(0.3f);
        gen.getParameters().getBarcode().getBarHeight().setMillimeters(25f);

        String imagePath = TEST_IMAGES_FOLDER + File.separator + filename;
        gen.save(imagePath, BarCodeImageFormat.PNG);

        return imagePath;
    }
}