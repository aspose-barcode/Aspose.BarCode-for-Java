package com.aspose.barcode.guide.common;

import com.aspose.barcode.barcoderecognition.*;
import com.aspose.barcode.generation.BarCodeImageFormat;
import com.aspose.barcode.generation.BarcodeGenerator;
import com.aspose.barcode.generation.BaseEncodeType;
import com.aspose.barcode.generation.EncodeTypes;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Utility helper for example tests and resource management.
 */
public class ExampleAssist {

    /**
     * Gets the path to a resource folder with a trailing separator.
     */
    public static String getResourceFolderPath(String... pathParts) {
        Path basePath = Paths.get("src", "test", "resources");

        for (String part : pathParts) {
            basePath = basePath.resolve(part);
        }

        return basePath + File.separator;
    }

    /**
     * Gets or creates a resource folder path and prints created structure.
     */
    public static String getOrCreateResourceFolderPath(String... pathParts) {
        Path basePath;

        // Detect if the first part already includes src/test/resources to avoid duplication
        if (pathParts.length > 0 && pathParts[0].contains("src" + File.separator + "test" + File.separator + "resources")) {
            basePath = Paths.get(pathParts[0]);
            for (int i = 1; i < pathParts.length; i++) {
                basePath = basePath.resolve(pathParts[i]);
            }
        } else {
            basePath = Paths.get("src", "test", "resources");
            for (String part : pathParts) {
                basePath = basePath.resolve(part);
            }
        }

        try {
            Files.createDirectories(basePath);

            System.out.println("[ExampleAssist] Created or verified folder structure:");
            Path current = Paths.get("src", "test", "resources");
            for (String part : pathParts) {
                current = current.resolve(part);
                if (Files.exists(current)) {
                    System.out.println("  - " + current.toAbsolutePath());
                }
            }

            System.out.println("[ExampleAssist] Final resource path: " + basePath.toAbsolutePath());

        } catch (FileAlreadyExistsException e) {
            throw new IllegalStateException("Path exists but is not a directory: " + basePath, e);
        } catch (IOException e) {
            throw new UncheckedIOException("Failed to create directory: " + basePath, e);
        }

        return basePath + File.separator;
    }

    /**
     * Gets path to a resource file or nested folders.
     */
    public static String getResourceFilePath(String... pathParts) {
        Path basePath = Paths.get("src", "test", "resources");

        for (String part : pathParts) {
            basePath = basePath.resolve(part);
        }

        return basePath.toString();
    }

    /**
     * Gets input stream for reading resource files from classpath.
     */
    public static InputStream getResourceAsStream(String resourcePath) {
        InputStream stream = ExampleAssist.class.getClassLoader()
                .getResourceAsStream(resourcePath);

        if (stream == null) {
            throw new IllegalArgumentException("Resource not found: " + resourcePath);
        }

        return stream;
    }

    /**
     * Checks if the given image exists, or creates it using the provided generator.
     */
    public static void checkOrCreateImage(String imagesFolder, String fileName, ImageSupplier generator) throws IOException
    {
        Path path = Paths.get(imagesFolder, fileName);
        Files.createDirectories(path.getParent());

        if (!Files.exists(path)) {
            String fullPath = path.toString();
            generator.supply(fullPath);
            Assert.assertTrue(Files.exists(path), "Failed to create fixture: " + path);
            Assert.assertTrue(Files.size(path) > 0, "Fixture is empty: " + path);
        }
    }

    public static void checkOrCreateImage(String imagesFolder, String fileName, BarcodeGenerator barcodeGenerator) throws IOException
    {
        Path path = Paths.get(imagesFolder, fileName);
        Files.createDirectories(path.getParent());

        if (!Files.exists(path)) {
            barcodeGenerator.save(path.toString());
            Assert.assertTrue(Files.exists(path), "Failed to create fixture: " + path);
            Assert.assertTrue(Files.size(path) > 0, "Fixture is empty: " + path);
        }
    }

    public static void assertRecognized(BarCodeReader reader, String tag, int minCount, BaseDecodeType expectedType) throws Exception {

        // Auto-detect test name if tag not provided
        if (tag == null || tag.isEmpty()) {
            tag = Thread.currentThread().getStackTrace()[2].getMethodName();
        }

        BarCodeResult[] results = reader.readBarCodes();

        System.out.println("=== [" + tag + "] ===");
        for (BarCodeResult result : results) {
            System.out.println(" Code Type: " + result.getCodeTypeName() + " - Code Text: " + result.getCodeText());
        }

        Assert.assertTrue(results.length >= minCount,"Expected at least " + minCount + " result(s) in test '" + tag + "', but got " + results.length);

        if (expectedType != null && results.length > 0) {
            boolean hasExpectedType = false;
            for (BarCodeResult result : results) {
                if (result.getCodeType().equals(expectedType)) {
                    hasExpectedType = true;
                    break;
                }
            }

            Assert.assertTrue(
                    hasExpectedType,
                    "Expected to find type " + expectedType.toString() + " in test '" + tag + "'"
            );
        }
    }

    public static String generateTestBarcode(String data, String imagesFolder, String fileName, BaseEncodeType type) throws IOException {
        BarcodeGenerator gen = new BarcodeGenerator(type, data);
        gen.getParameters().setResolution(300f);
        gen.getParameters().getBarcode().getXDimension().setMillimeters(0.3f);
        gen.getParameters().getBarcode().getBarHeight().setMillimeters(25f);

        String fullPath = imagesFolder + File.separator + fileName;
        gen.save(fullPath, BarCodeImageFormat.PNG);
        return fullPath;
    }

    public static void generateAndRead(String folder, String fileName, String codeText, BaseEncodeType encodeType, BaseDecodeType decodeType) throws Exception
    {
        BarcodeGenerator g = new BarcodeGenerator(encodeType, codeText);
        String path = folder + "/" +  fileName;
        g.save(path, BarCodeImageFormat.PNG);
        BarCodeReader reader = new BarCodeReader(path, decodeType);
        reader.setQualitySettings(QualitySettings.getHighPerformance());
        BarCodeResult[] results = reader.readBarCodes();
        for (BarCodeResult result : results)
        {
            System.out.println(" Code Type: " + result.getCodeTypeName() + " - Code Text: " + result.getCodeText());
        }
    }


/**
    @Test
    public void read_QR_HighPerformance1() throws Exception
    {
        // Purpose: Recognize a clean QR using High Performance preset.
        String fileName = "qr_hp123.png";
        BarcodeGenerator g = new BarcodeGenerator(EncodeTypes.QR, "HP-QR");
        String path = FOLDER + "/" +  fileName;
        g.save(path, BarCodeImageFormat.PNG);
        BarCodeReader reader = new BarCodeReader(ExampleAssist.pathCombine(FOLDER, fileName), DecodeType.QR);
        BarCodeResult[] results = reader.readBarCodes();
        for (BarCodeResult result : results)
        {
            System.out.println(" Code Type: " + result.getCodeTypeName() + " - Code Text: " + result.getCodeText());
        }
    }
**/
    /**
     * Private constructor to prevent instantiation.
     */
    private ExampleAssist() {
        throw new UnsupportedOperationException("Utility class");
    }

    public static String pathCombine(String folder, String image)
    {
        return folder + "/" + image;
    }

    public static String getCurrentMethodName() {
        return Thread.currentThread().getStackTrace()[2].getMethodName();
    }
}
