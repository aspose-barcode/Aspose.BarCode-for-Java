package com.aspose.barcode.guide.recognition.performance;// Demonstrates how to use QualitySettings.setMinimalXDimension/getMinimalXDimension
// together with XDimension mode that respects the minimal module size.
// Uses existing helpers: ExampleAssist.pathCombine(...)
// and checkOrCreateImage(folder, name, this::generator).
// No try-with-resources; explicit reader.close(); prints results; comments in English.

import com.aspose.barcode.*;
import com.aspose.barcode.barcoderecognition.*;
import com.aspose.barcode.generation.*;
import com.aspose.barcode.guide.common.ExampleAssist;
import org.testng.annotations.Test;

import java.io.IOException;

import static com.aspose.barcode.guide.common.ExampleAssist.checkOrCreateImage;

public class UseMinimalXDimensionExample
{

    private static final String FOLDER =
            ExampleAssist.getOrCreateResourceFolderPath("recognition", "performance", "minimal_xdimension");

    // Code 128 with X = 1 px (edge case; very thin bars)
    private void generate_Code128_X1(String fullPath) throws IOException
    {
        BarcodeGenerator gen = new BarcodeGenerator(EncodeTypes.CODE_128, "MIN-XD-128-X1");
        gen.getParameters().getBarcode().getXDimension().setPixels(1);
        gen.getParameters().getBarcode().getBarHeight().setPixels(60);

        // Quiet zones help tiny modules
        gen.getParameters().getBarcode().getPadding().getLeft().setPixels(16);
        gen.getParameters().getBarcode().getPadding().getRight().setPixels(16);
        gen.getParameters().getBarcode().getPadding().getTop().setPixels(8);
        gen.getParameters().getBarcode().getPadding().getBottom().setPixels(8);

        gen.save(fullPath, BarCodeImageFormat.PNG);
    }

    // Code 128 with X = 2 px (practical minimal)
    private void generate_Code128_X2(String fullPath) throws IOException
    {
        BarcodeGenerator gen = new BarcodeGenerator(EncodeTypes.CODE_128, "MIN-XD-128-X2");
        gen.getParameters().getBarcode().getXDimension().setPixels(2);
        gen.getParameters().getBarcode().getBarHeight().setPixels(60);

        gen.getParameters().getBarcode().getPadding().getLeft().setPixels(20);
        gen.getParameters().getBarcode().getPadding().getRight().setPixels(20);
        gen.getParameters().getBarcode().getPadding().getTop().setPixels(10);
        gen.getParameters().getBarcode().getPadding().getBottom().setPixels(10);

        gen.save(fullPath, BarCodeImageFormat.PNG);
    }

    // ------------------------- Tests -------------------------
    // NOTE: If your enum name differs, replace XDimensionMode.USE_MINIMAL_X_DIMENSION
    // with the actual constant that enables "UseMinimalXDimension".

    @Test
    public void read_Code128_X1_with_MinimalX_1px() throws Exception
    {
        // Image with X=1 px
        String fileName = "code128_x1.png";
        checkOrCreateImage(FOLDER, fileName, this::generate_Code128_X1);

        String path = ExampleAssist.pathCombine(FOLDER, fileName);

        BarCodeReader reader = new BarCodeReader(path, DecodeType.CODE_128);

        // Start from a robust preset, then switch to "UseMinimalXDimension"
        QualitySettings qs = QualitySettings.getHighQuality();
        qs.setXDimension(XDimensionMode.USE_MINIMAL_X_DIMENSION); // enable minimal-x handling
        qs.setMinimalXDimension(1.0f);                            // accept modules as small as 1 px
        reader.setQualitySettings(qs);
        ExampleAssist.assertRecognized(reader, "read_Code128_X1_with_MinimalX_1px", 1, DecodeType.CODE_128);
    }

    @Test
    public void read_Code128_X1_with_MinimalX_2px_should_be_stricter() throws Exception
    {
        // Same image X=1 px, but we demand MinimalXDimension=2 px → recognition may skip too-thin bars
        String fileName = "code128_x1.png";
        checkOrCreateImage(FOLDER, fileName, this::generate_Code128_X1);

        String path = ExampleAssist.pathCombine(FOLDER, fileName);

        BarCodeReader reader = new BarCodeReader(path, DecodeType.CODE_128);

        QualitySettings qs = QualitySettings.getHighQuality();
        qs.setXDimension(XDimensionMode.USE_MINIMAL_X_DIMENSION);
        qs.setMinimalXDimension(2.0f); // require at least 2 px bars
        reader.setQualitySettings(qs);
        ExampleAssist.assertRecognized(reader, "read_Code128_X1_with_MinimalX_2px_should_be_stricter", 1, DecodeType.CODE_128);
    }

    @Test
    public void read_Code128_X2_with_MinimalX_2px() throws Exception
    {
        // Image with X=2 px and MinimalXDimension=2 px → should be recognized
        String file = "code128_x2.png";
        checkOrCreateImage(FOLDER, file, this::generate_Code128_X2);

        String path = ExampleAssist.pathCombine(FOLDER, file);

        BarCodeReader reader = new BarCodeReader(path, DecodeType.CODE_128);

        QualitySettings qs = QualitySettings.getHighQuality();
        qs.setXDimension(XDimensionMode.USE_MINIMAL_X_DIMENSION);
        qs.setMinimalXDimension(2.0f);
        reader.setQualitySettings(qs);
        ExampleAssist.assertRecognized(reader, "read_Code128_X2_with_MinimalX_2px", 1, DecodeType.CODE_128);

    }
}