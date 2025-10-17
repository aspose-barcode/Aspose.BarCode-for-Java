package com.aspose.barcode.guide.common;

import java.io.IOException;

@FunctionalInterface
public interface Generator {
    void generate(String fullPath) throws IOException;
}