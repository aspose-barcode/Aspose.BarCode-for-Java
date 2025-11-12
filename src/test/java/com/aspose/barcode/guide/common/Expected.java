package com.aspose.barcode.guide.common;

import com.aspose.barcode.barcoderecognition.BaseDecodeType;

import java.util.Objects;

public class Expected {
    public final BaseDecodeType type;
    public final CompareMode mode;
    public final String text;   // used when mode == TEXT
    public final byte[] bytes;  // used when mode == BYTES

    public Expected(BaseDecodeType type, CompareMode mode, String text, byte[] bytes) {
        this.type = Objects.requireNonNull(type, "type");
        this.mode = Objects.requireNonNull(mode, "mode");
        this.text = text;
        this.bytes = bytes;
    }
}
