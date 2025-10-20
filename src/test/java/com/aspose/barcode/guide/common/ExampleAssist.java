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

public class ExampleAssist
{
    /**
     * Gets path to resource folder with trailing separator
     */
    public static String getResourceFolderPath(String... pathParts)
    {
        Path basePath = Paths.get("src", "test", "resources");

        for (String part : pathParts)
        {
            basePath = basePath.resolve(part);
        }

        return basePath.toString() + File.separator;
    }

    public static String getOrCreateResourceFolderPath(String... pathParts)
    {
        Path basePath = Paths.get("src", "test", "resources");

        for (String part : pathParts)
        {
            basePath = basePath.resolve(part);
        }

        try
        {
            Files.createDirectories(basePath);
        }
        catch (FileAlreadyExistsException e)
        {
            throw new IllegalStateException("Path exists but is not a directory: " + basePath, e);
        }
        catch (IOException e)
        {
            throw new UncheckedIOException("Failed to create directory: " + basePath, e);
        }

        return basePath + File.separator;
    }

    /**
     * Gets path to resource file or nested folders
     */
    public static String getResourceFilePath(String... pathParts)
    {
        Path basePath = Paths.get("src", "test", "resources");

        for (String part : pathParts)
        {
            basePath = basePath.resolve(part);
        }

        return basePath.toString();
    }

    /**
     * Gets input stream for reading resource files
     */
    public static InputStream getResourceAsStream(String resourcePath)
    {
        InputStream stream = ExampleAssist.class.getClassLoader()
                .getResourceAsStream(resourcePath);

        if (stream == null) {
            throw new IllegalArgumentException("Resource not found: " + resourcePath);
        }

        return stream;
    }

    private ExampleAssist()
    {
        throw new UnsupportedOperationException("Utility class");
    }

    public static void checkOrCreateImage(String imagesFolder, String fileName, Generator generator) throws Exception {
        Path p = Paths.get(imagesFolder, fileName);
        Files.createDirectories(p.getParent());
        if (!Files.exists(p)) {
            generator.generate(p.toString());
            Assert.assertTrue(Files.exists(p), "Failed to create fixture: " + p);
            Assert.assertTrue(Files.size(p) > 0, "Fixture is empty: " + p);
        }
    }


}