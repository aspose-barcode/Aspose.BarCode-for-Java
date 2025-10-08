package com.aspose.barcode.guide.common;


import java.io.InputStream;

public class ExampleAssist
{
    public static String getResourcePath(String resourcePath)
    {
        return ExampleAssist.class.getClassLoader()
                .getResource(resourcePath)
                .getPath();
    }

    public static InputStream getResourceAsStream(String resourcePath)
    {
        return ExampleAssist.class.getClassLoader()
                .getResourceAsStream(resourcePath);
    }
}
