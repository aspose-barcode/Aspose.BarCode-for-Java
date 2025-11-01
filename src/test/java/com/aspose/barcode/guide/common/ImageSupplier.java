package com.aspose.barcode.guide.common;

import java.io.IOException;

@FunctionalInterface
public interface ImageSupplier
{
    void supply(String fullPath) throws IOException;
}