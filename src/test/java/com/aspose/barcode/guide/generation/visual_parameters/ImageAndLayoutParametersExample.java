package com.aspose.barcode.guide.generation.visual_parameters;

import com.aspose.barcode.barcoderecognition.DecodeType;
import com.aspose.barcode.generation.*;
import com.aspose.barcode.guide.common.ExampleAssist;
import com.aspose.barcode.guide.common.LicenseAssist;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.awt.*;
import java.util.List;

import static com.aspose.barcode.guide.common.ExampleAssist.*;

/**
 * This class demonstrates visual and layout-oriented parameters that affect
 * the rendered barcode image (not the encoded data itself):
 * - Global image size (width/height)
 * - Barcode metrics (X-dimension, bar height)
 * - Colors and paddings (quiet zones)
 * - Rotation
 */
public class ImageAndLayoutParametersExample {

    // Use a clear, dedicated resource folder for this group of tests
    private static final String FOLDER =
            ExampleAssist.getOrCreateResourceFolderPath("generation", "image_and_layout");

    private static final String FILE_C128_SIZED_COLORED = "c128_sized_colored.png";
    private static final String FILE_EAN13_ROTATED      = "ean13_rotated.png";

    @BeforeClass
    public void setUp() throws Exception {
        // Ensure the license is applied for consistent behavior across environments
        LicenseAssist.setupLicense();

        // Also ensure the folder exists (the constant FOLDER above already creates it)
        ExampleAssist.getOrCreateResourceFolderPath("generation", "image_and_layout");
    }

    // --- Global image & barcode metrics: size, X-dimension, bar height, colors & padding ---
    @Test
    public void generate_Code128_withSizeColorAndPadding() throws Exception {
        // Choose Code 128 to demonstrate layout parameters
        BarcodeGenerator generator = new BarcodeGenerator(EncodeTypes.CODE_128, "SIZE-COLOR-PADDING");

        // Set target image size in pixels (overall bitmap dimensions)
        generator.getParameters().getImageWidth().setPixels(600);
        generator.getParameters().getImageHeight().setPixels(200);
        Assert.assertEquals((int) generator.getParameters().getImageWidth().getPixels(), 600);
        Assert.assertEquals((int) generator.getParameters().getImageHeight().getPixels(), 200);

        // Set barcode module (X-dimension) and bar height in pixels
        generator.getParameters().getBarcode().getXDimension().setPixels(2.0f);
        generator.getParameters().getBarcode().getBarHeight().setPixels(120);
        Assert.assertEquals((int) generator.getParameters().getBarcode().getBarHeight().getPixels(), 120);

        // Set background/foreground colors and paddings (quiet zones)
        generator.getParameters().setBackColor(Color.WHITE);
        generator.getParameters().getBarcode().setBarColor(Color.BLACK);
        generator.getParameters().getBarcode().getPadding().getLeft().setPixels(20);
        generator.getParameters().getBarcode().getPadding().getRight().setPixels(20);
        generator.getParameters().getBarcode().getPadding().getTop().setPixels(10);
        generator.getParameters().getBarcode().getPadding().getBottom().setPixels(10);

        // Save & verify
        String full = pathCombine(FOLDER, FILE_C128_SIZED_COLORED);
        generator.save(full, BarCodeImageFormat.PNG);
        assertFileCreated(full);

        // Recognition should still succeed with the same code text
        assertImageHasBarcodes(
                full,
                1,
                List.of(exp(DecodeType.CODE_128, "SIZE-COLOR-PADDING"))
        );
    }

    // --- Rotation & quiet zones example on EAN-13 ---
    @Test
    public void generate_EAN13_rotated() throws Exception {
        // EAN-13 with rotation and explicit left/right paddings (quiet zones)
        BarcodeGenerator generator = new BarcodeGenerator(EncodeTypes.EAN_13, "5901234123457");

        // Rotate 90 degrees clockwise
        generator.getParameters().setRotationAngle(90);

        // Explicit quiet zones (left/right) can help ensure scanners have room to detect start/stop patterns
        generator.getParameters().getBarcode().getPadding().getLeft().setPixels(12);
        generator.getParameters().getBarcode().getPadding().getRight().setPixels(12);
        Assert.assertEquals(generator.getParameters().getRotationAngle(), 90);

        // Save & verify
        String full = pathCombine(FOLDER, FILE_EAN13_ROTATED);
        generator.save(full, BarCodeImageFormat.PNG);
        assertFileCreated(full);

        // Recognition should work regardless of rotation if quiet zones are respected
        assertImageHasBarcodes(
                full,
                1,
                List.of(exp(DecodeType.EAN_13, "5901234123457"))
        );
    }
}
