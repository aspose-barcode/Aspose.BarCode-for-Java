package com.aspose.barcode.guide.recognition.input_sources;

import com.aspose.barcode.barcoderecognition.*;
import com.aspose.barcode.generation.*;
import com.aspose.barcode.guide.common.ExampleAssist;
import com.aspose.barcode.guide.common.LicenseAssist;
import org.testng.annotations.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import javax.imageio.ImageIO;

import static com.aspose.barcode.guide.common.ExampleAssist.generateTestBarcode;

/**
 * Demonstrates how to use BarCodeReader with various input sources.
 * Covers:
 * - File path
 * - File object
 * - InputStream
 * - BufferedImage
 * - Byte array
 * - Base64 string
 * - In-memory stream
 * - Error handling examples
 */
public class InputSourcesExamples {

    private static final String IMAGES_FOLDER =
            ExampleAssist.getOrCreateResourceFolderPath("quick_start", "recognition", "input_sources");

    private static final String TEST_DATA = "INPUT-SOURCES-TEST";
    private static String testImagePath;

    @BeforeClass
    public void setUp() throws Exception {
        LicenseAssist.setupLicense();
        testImagePath = generateTestBarcode(TEST_DATA, IMAGES_FOLDER, "test_input_source.png", EncodeTypes.CODE_128);
        System.out.println("=== Starting Input Source Tests ===");
        System.out.println("Generated test image: " + testImagePath + "\n");
    }

    // ----------- From File Path -----------
    @Test(priority = 1, description = "Recognition from file path")
    public void readFromFilePath() throws Exception {
        BarCodeReader reader = new BarCodeReader(testImagePath, DecodeType.CODE_128);
        ExampleAssist.assertRecognized(reader, null, 1, DecodeType.CODE_128);
    }

    // ----------- From File Object -----------
    @Test(priority = 2, description = "Recognition from File object")
    public void readFromFileObject() throws Exception {
        File file = new File(testImagePath);
        BarCodeReader reader = new BarCodeReader(file.getAbsolutePath());
        ExampleAssist.assertRecognized(reader, null, 1, DecodeType.CODE_128);
    }

    // ----------- From InputStream -----------
    @Test(priority = 3, description = "Recognition from InputStream")
    public void readFromInputStream() throws Exception {
        try (FileInputStream stream = new FileInputStream(testImagePath)) {
            BarCodeReader reader = new BarCodeReader(stream);
            ExampleAssist.assertRecognized(reader, null, 1, DecodeType.CODE_128);
        }
    }

    // ----------- From BufferedImage -----------
    @Test(priority = 4, description = "Recognition from BufferedImage")
    public void readFromBufferedImage() throws Exception {
        BufferedImage image = ImageIO.read(new File(testImagePath));
        BarCodeReader reader = new BarCodeReader(image);
        ExampleAssist.assertRecognized(reader, null, 1, DecodeType.CODE_128);
    }

    // ----------- From Byte Array -----------
    @Test(priority = 5, description = "Recognition from byte array")
    public void readFromByteArray() throws Exception {
        byte[] bytes = Files.readAllBytes(Paths.get(testImagePath));
        try (ByteArrayInputStream stream = new ByteArrayInputStream(bytes)) {
            BarCodeReader reader = new BarCodeReader(stream);
            ExampleAssist.assertRecognized(reader, null, 1, DecodeType.CODE_128);
        }
    }

    // ----------- From Base64 String -----------
    @Test(priority = 6, description = "Recognition from Base64 string")
    public void readFromBase64String() throws Exception {
        byte[] bytes = Files.readAllBytes(Paths.get(testImagePath));
        String base64 = java.util.Base64.getEncoder().encodeToString(bytes);
        byte[] decoded = java.util.Base64.getDecoder().decode(base64);

        try (ByteArrayInputStream stream = new ByteArrayInputStream(decoded)) {
            BarCodeReader reader = new BarCodeReader(stream);
            ExampleAssist.assertRecognized(reader, null, 1, DecodeType.CODE_128);
        }
    }

    // ----------- From Memory Stream (Generated Barcode) -----------
    @Test(priority = 7, description = "Recognition from in-memory generated barcode")
    public void readFromMemoryStream() throws Exception {
        BarcodeGenerator generator = new BarcodeGenerator(EncodeTypes.QR, "MEMORY-STREAM-TEST");
        ByteArrayOutputStream memory = new ByteArrayOutputStream();
        generator.save(memory, BarCodeImageFormat.PNG);

        try (ByteArrayInputStream stream = new ByteArrayInputStream(memory.toByteArray())) {
            BarCodeReader reader = new BarCodeReader(stream, DecodeType.QR);
            ExampleAssist.assertRecognized(reader, null, 1, DecodeType.QR);
        }
    }

    // ----------- From Processed Image -----------
    @Test(priority = 8, description = "Recognition from processed image")
    public void readFromProcessedImage() throws Exception {
        BufferedImage source = ImageIO.read(new File(testImagePath));
        BufferedImage copy = new BufferedImage(source.getWidth(), source.getHeight(), BufferedImage.TYPE_INT_RGB);
        java.awt.Graphics2D g2 = copy.createGraphics();
        g2.drawImage(source, 0, 0, null);
        g2.dispose();

        BarCodeReader reader = new BarCodeReader(copy);
        ExampleAssist.assertRecognized(reader, null, 1, DecodeType.CODE_128);
    }

    // ----------- Error Handling -----------
    @Test(priority = 9, description = "Error handling for invalid inputs")
    public void handleInvalidInputs() throws Exception {
        // 1. Non-existent file
        try {
            BarCodeReader reader = new BarCodeReader("non_existent.png");
            reader.readBarCodes();
        } catch (Exception e) {
            System.out.println("[OK] Non-existent file handled: " + e.getClass().getSimpleName());
        }

        // 2. Invalid image format (create fake .png file for robustness test)
        String invalidFile = IMAGES_FOLDER + File.separator + "invalid_fake.png";
        Files.write(Paths.get(invalidFile), "This is not a real image file".getBytes());

        try {
            BarCodeReader reader = new BarCodeReader(invalidFile);
            reader.readBarCodes();
            System.out.println("[WARN] Fake image processed unexpectedly without exception");
        } catch (Exception e) {
            System.out.println("[OK] Fake image handled safely: " + e.getClass().getSimpleName());
        }


        // 3. Empty byte array
        try (ByteArrayInputStream empty = new ByteArrayInputStream(new byte[0])) {
            BarCodeReader reader = new BarCodeReader(empty);
            reader.readBarCodes();
        } catch (Exception e) {
            System.out.println("[OK] Empty byte array handled: " + e.getClass().getSimpleName());
        }
    }

    // ----------- Helper: Generate test barcode -----------

}
