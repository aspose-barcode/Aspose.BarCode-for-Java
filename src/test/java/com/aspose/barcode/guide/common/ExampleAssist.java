package com.aspose.barcode.guide.common;

import java.io.File;
import java.io.InputStream;
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
}