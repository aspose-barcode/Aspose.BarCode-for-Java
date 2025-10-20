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
 *  1. File path
 *  2. File object
 *  3. InputStream
 *  4. BufferedImage
 *  5. Byte array
 *  6. Base64 string (via stream)
 *  7. In-memory generated barcode
 *  8. Processed image
 *  9. Error handling for invalid inputs
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

    // -----------------------------------------------------------------------------------------
    // 1. Recognition from File Path
    // -----------------------------------------------------------------------------------------
    /**
     * Demonstrates recognition from a simple file path (String).
     * This is the most direct and typical way to read barcodes from disk.
     */
    @Test(priority = 1, description = "Recognition from file path")
    public void readFromFilePath() throws Exception {
        BarCodeReader reader = new BarCodeReader(testImagePath, DecodeType.CODE_128);
        ExampleAssist.assertRecognized(reader, "readFromFilePath", 1, DecodeType.CODE_128);
    }

    // -----------------------------------------------------------------------------------------
    // 2. Recognition from File Object
    // -----------------------------------------------------------------------------------------
    /**
     * Demonstrates recognition when a {@link java.io.File} object is used.
     * Useful when working with files already managed by your application logic.
     */
    @Test(priority = 2, description = "Recognition from File object")
    public void readFromFileObject() throws Exception {
        File file = new File(testImagePath);
        BarCodeReader reader = new BarCodeReader(file.getAbsolutePath());
        ExampleAssist.assertRecognized(reader, "readFromFileObject", 1, DecodeType.CODE_128);
    }

    // -----------------------------------------------------------------------------------------
    // 3. Recognition from InputStream
    // -----------------------------------------------------------------------------------------
    /**
     * Demonstrates how to recognize barcodes from an {@link InputStream}.
     * This method is suitable for scenarios such as reading from:
     * - uploaded files,
     * - network responses,
     * - archives or resource bundles.
     */
    @Test(priority = 3, description = "Recognition from InputStream")
    public void readFromInputStream() throws Exception {
        try (FileInputStream stream = new FileInputStream(testImagePath)) {
            BarCodeReader reader = new BarCodeReader(stream);
            ExampleAssist.assertRecognized(reader, "readFromInputStream", 1, DecodeType.CODE_128);
        }
    }

    // -----------------------------------------------------------------------------------------
    // 4. Recognition from BufferedImage
    // -----------------------------------------------------------------------------------------
    /**
     * Demonstrates recognition directly from an in-memory {@link BufferedImage}.
     * Useful when you already have the image loaded or processed by Java 2D or other APIs.
     */
    @Test(priority = 4, description = "Recognition from BufferedImage")
    public void readFromBufferedImage() throws Exception {
        BufferedImage image = ImageIO.read(new File(testImagePath));
        BarCodeReader reader = new BarCodeReader(image);
        ExampleAssist.assertRecognized(reader, "readFromBufferedImage", 1, DecodeType.CODE_128);
    }

    // -----------------------------------------------------------------------------------------
    // 5. Recognition from Byte Array
    // -----------------------------------------------------------------------------------------
    /**
     * Demonstrates how to recognize barcodes when image data is stored in memory as a byte array.
     * Common use case: images stored in a database or received via REST API.
     */
    @Test(priority = 5, description = "Recognition from byte array")
    public void readFromByteArray() throws Exception {
        byte[] bytes = Files.readAllBytes(Paths.get(testImagePath));
        try (ByteArrayInputStream stream = new ByteArrayInputStream(bytes)) {
            BarCodeReader reader = new BarCodeReader(stream);
            ExampleAssist.assertRecognized(reader, "readFromByteArray", 1, DecodeType.CODE_128);
        }
    }

    // -----------------------------------------------------------------------------------------
    // 6. Recognition from Base64 String (realistic API scenario)
    // -----------------------------------------------------------------------------------------
    /**
     * Demonstrates how to recognize barcodes from Base64-encoded image data.
     * This pattern is often used in web APIs that accept images as Base64 strings (e.g. JSON payloads).
     *
     * <p>Example use case:</p>
     * <pre>
     * {
     *   "image": "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAA..."
     * }
     * </pre>
     *
     * Since {@link BarCodeReader} has no constructor for Base64 directly,
     * we manually decode the string into bytes and feed it via {@link ByteArrayInputStream}.
     */
    @Test(priority = 6, description = "Recognition from Base64 string")
    public void readFromBase64String() throws Exception {
        // 1. Read barcode image into bytes
        byte[] imageBytes = Files.readAllBytes(Paths.get(testImagePath));

        // 2. Encode to Base64 string (simulating JSON/web API payload)
        String base64 = java.util.Base64.getEncoder().encodeToString(imageBytes);
        System.out.println("  [INFO] Base64 length: " + base64.length() + " characters");

        // 3. Strip optional data URI prefix if it exists (e.g. "data:image/png;base64,")
        String cleanBase64 = base64.contains(",")
                ? base64.substring(base64.indexOf(",") + 1)
                : base64;

        // 4. Decode back into raw image bytes
        byte[] decodedBytes = java.util.Base64.getDecoder().decode(cleanBase64);

        // 5. Feed into BarCodeReader through memory stream
        try (ByteArrayInputStream stream = new ByteArrayInputStream(decodedBytes)) {
            BarCodeReader reader = new BarCodeReader(stream);
            ExampleAssist.assertRecognized(reader, "readFromBase64String", 1, DecodeType.CODE_128);
        }
    }

    // -----------------------------------------------------------------------------------------
    // 7. Recognition from In-Memory Generated Barcode
    // -----------------------------------------------------------------------------------------
    /**
     * Demonstrates barcode recognition from a dynamically generated image kept entirely in memory.
     * No temporary files are created — this is useful for pipelines or microservices.
     */
    @Test(priority = 7, description = "Recognition from in-memory generated barcode")
    public void readFromMemoryStream() throws Exception {
        // Generate barcode in memory
        BarcodeGenerator generator = new BarcodeGenerator(EncodeTypes.QR, "MEMORY-STREAM-TEST");
        ByteArrayOutputStream memory = new ByteArrayOutputStream();
        generator.save(memory, BarCodeImageFormat.PNG);

        // Recognize it directly from the same memory buffer
        try (ByteArrayInputStream stream = new ByteArrayInputStream(memory.toByteArray())) {
            BarCodeReader reader = new BarCodeReader(stream, DecodeType.QR);
            ExampleAssist.assertRecognized(reader, "readFromMemoryStream", 1, DecodeType.QR);
        }
    }

    // -----------------------------------------------------------------------------------------
    // 8. Recognition from Processed Image
    // -----------------------------------------------------------------------------------------
    /**
     * Demonstrates recognition after performing preprocessing (e.g. copying or filtering).
     * This shows that {@link BarCodeReader} can work with any {@link BufferedImage},
     * even if modified or re-rendered.
     */
    @Test(priority = 8, description = "Recognition from processed image")
    public void readFromProcessedImage() throws Exception {
        BufferedImage source = ImageIO.read(new File(testImagePath));
        BufferedImage copy = new BufferedImage(source.getWidth(), source.getHeight(), BufferedImage.TYPE_INT_RGB);

        java.awt.Graphics2D g2 = copy.createGraphics();
        g2.drawImage(source, 0, 0, null);
        g2.dispose();

        BarCodeReader reader = new BarCodeReader(copy);
        ExampleAssist.assertRecognized(reader, "readFromProcessedImage", 1, DecodeType.CODE_128);
    }

    // -----------------------------------------------------------------------------------------
    // 9. Error Handling for Invalid Inputs
    // -----------------------------------------------------------------------------------------
    /**
     * Demonstrates how {@link BarCodeReader} handles invalid or corrupted inputs:
     * 1. Non-existent file
     * 2. Fake .png file (invalid binary)
     * 3. Empty byte array
     *
     * Each case should be handled gracefully without crashing the application.
     */
    @Test(priority = 9, description = "Error handling for invalid inputs")
    public void handleInvalidInputs() throws Exception {
        // (1) Non-existent file
        try {
            BarCodeReader reader = new BarCodeReader("non_existent.png");
            reader.readBarCodes();
        } catch (Exception e) {
            System.out.println("[OK] Non-existent file handled: " + e.getClass().getSimpleName());
        }

        // (2) Invalid image format (fake .png)
        String invalidFile = IMAGES_FOLDER + File.separator + "invalid_fake.png";
        Files.write(Paths.get(invalidFile), "This is not a real image file".getBytes());
        try {
            BarCodeReader reader = new BarCodeReader(invalidFile);
            reader.readBarCodes();
            System.out.println("[WARN] Fake image processed unexpectedly without exception");
        } catch (Exception e) {
            System.out.println("[OK] Fake image handled safely: " + e.getClass().getSimpleName());
        } finally {
            Files.deleteIfExists(Paths.get(invalidFile));
        }

        // (3) Empty byte array
        try (ByteArrayInputStream empty = new ByteArrayInputStream(new byte[0])) {
            BarCodeReader reader = new BarCodeReader(empty);
            reader.readBarCodes();
        } catch (Exception e) {
            System.out.println("[OK] Empty byte array handled: " + e.getClass().getSimpleName());
        }
    }
}
