package com.aspose.barcode.guide.common;

import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Paths;

public class ExampleAssist
{
    private ExampleAssist()
    {
        // Utility class, не должна создаваться
        throw new UnsupportedOperationException("Utility class");
    }

    public static String getResourcePath(String resourcePath)
    {
        URL resource = ExampleAssist.class.getClassLoader()
                .getResource(resourcePath);

        if (resource == null) {
            throw new IllegalArgumentException("Resource not found: " + resourcePath);
        }

        return resource.getPath();
    }

    public static InputStream getResourceAsStream(String resourcePath)
    {
        InputStream stream = ExampleAssist.class.getClassLoader()
                .getResourceAsStream(resourcePath);

        if (stream == null) {
            throw new IllegalArgumentException("Resource not found: " + resourcePath);
        }

        return stream;
    }

    public static File getResourceFile(String resourcePath) throws Exception
    {
        URL resource = ExampleAssist.class.getClassLoader()
                .getResource(resourcePath);

        if (resource == null) {
            throw new IllegalArgumentException("Resource not found: " + resourcePath);
        }

        return Paths.get(resource.toURI()).toFile();
    }
}