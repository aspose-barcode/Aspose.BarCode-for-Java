package com.aspose.barcode.guide.common;

import org.testng.Assert;

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

        return basePath.toString() + File.separator;
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
    public static void checkOrCreateImage(String imagesFolder, String fileName, Generator generator) throws Exception {
        Path path = Paths.get(imagesFolder, fileName);
        Files.createDirectories(path.getParent());

        if (!Files.exists(path)) {
            generator.generate(path.toString());

            Assert.assertTrue(Files.exists(path), "Failed to create fixture: " + path);
            Assert.assertTrue(Files.size(path) > 0, "Fixture is empty: " + path);
        }
    }

    /**
     * Private constructor to prevent instantiation.
     */
    private ExampleAssist() {
        throw new UnsupportedOperationException("Utility class");
    }

    /**
     * Quick manual test for debugging.
     */
    public static void main(String[] args) {
        getOrCreateResourceFolderPath("quick_start", "recognition", "Recognition_Symbology");
    }
}
