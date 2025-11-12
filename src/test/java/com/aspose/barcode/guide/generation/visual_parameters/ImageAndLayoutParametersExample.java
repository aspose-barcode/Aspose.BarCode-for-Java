package com.aspose.barcode.guide.generation.visual_parameters;

import com.aspose.barcode.barcoderecognition.BarCodeReader;
import com.aspose.barcode.barcoderecognition.BarCodeResult;
import com.aspose.barcode.barcoderecognition.DecodeType;
import com.aspose.barcode.generation.*;
import com.aspose.barcode.guide.common.ExampleAssist;
import com.aspose.barcode.guide.common.LicenseAssist;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.awt.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static com.aspose.barcode.guide.common.ExampleAssist.*;

public class ImageAndLayoutParametersExample
{

    private static final String FOLDER =
            ExampleAssist.getOrCreateResourceFolderPath("generation", "parameters", "image_and_layout_parameters");

    private static final String FILE_C128_SIZED_COLORED= "c128_sized_colored.png";
    private static final String FILE_EAN13_ROTATED     = "ean13_rotated.png";

    @BeforeClass
    public void setUp() throws Exception {
        LicenseAssist.setupLicense();
        ExampleAssist.getOrCreateResourceFolderPath("generation", "image_and_layout");
    }

    // --- 4) Global image & barcode metrics: size, X-dimension, bar height, colors & padding ---
    @Test
    public void generate_Code128_withSizeColorAndPadding() throws Exception {
        BarcodeGenerator generator = new BarcodeGenerator(EncodeTypes.CODE_128, "SIZE-COLOR-PADDING");

        generator.getParameters().getImageWidth().setPixels(600);
        generator.getParameters().getImageHeight().setPixels(200);
        Assert.assertEquals((int) generator.getParameters().getImageWidth().getPixels(), 600);
        Assert.assertEquals((int) generator.getParameters().getImageHeight().getPixels(), 200);

        generator.getParameters().getBarcode().getXDimension().setPixels(2.0f);
        generator.getParameters().getBarcode().getBarHeight().setPixels(120);
        Assert.assertEquals((int) generator.getParameters().getBarcode().getBarHeight().getPixels(), 120);

        generator.getParameters().setBackColor(Color.WHITE);
        generator.getParameters().getBarcode().setBarColor(Color.BLACK);
        generator.getParameters().getBarcode().getPadding().getLeft().setPixels(20);
        generator.getParameters().getBarcode().getPadding().getRight().setPixels(20);
        generator.getParameters().getBarcode().getPadding().getTop().setPixels(10);
        generator.getParameters().getBarcode().getPadding().getBottom().setPixels(10);

        String full = ExampleAssist.pathCombine(FOLDER, FILE_C128_SIZED_COLORED);
        generator.save(full, BarCodeImageFormat.PNG);
        assertFileCreated(full);

        assertImageHasBarcodes(
                full,
                1,
                List.of(exp(DecodeType.CODE_128, "SIZE-COLOR-PADDING"))
        );
    }

    // --- 5) Rotation & quiet zones example on EAN-13 ---
    @Test
    public void generate_EAN13_rotated() throws Exception {
        BarcodeGenerator generator = new BarcodeGenerator(EncodeTypes.EAN_13, "5901234123457");
        generator.getParameters().setRotationAngle(90);
        generator.getParameters().getBarcode().getPadding().getLeft().setPixels(12);
        generator.getParameters().getBarcode().getPadding().getRight().setPixels(12);
        Assert.assertEquals(generator.getParameters().getRotationAngle(), 90);

        String full = ExampleAssist.pathCombine(FOLDER, FILE_EAN13_ROTATED);
        generator.save(full, BarCodeImageFormat.PNG);
        assertFileCreated(full);

        assertImageHasBarcodes(
                full,
                1,
                List.of(exp(DecodeType.EAN_13, "5901234123457"))
        );
    }
}
