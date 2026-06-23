package com.aspose.barcode.guide.generation.unit;

import com.aspose.barcode.barcoderecognition.DecodeType;
import com.aspose.barcode.generation.BarCodeImageFormat;
import com.aspose.barcode.generation.BarcodeGenerator;
import com.aspose.barcode.generation.EncodeTypes;
import com.aspose.barcode.generation.Unit;
import com.aspose.barcode.guide.common.ExampleAssist;
import com.aspose.barcode.guide.common.LicenseAssist;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

import static com.aspose.barcode.guide.common.ExampleAssist.assertImageHasBarcodes;
import static com.aspose.barcode.guide.common.ExampleAssist.expected;

/**
 * Demonstrates the Unit class with pixels, millimeters, inches, and points.
 */
public class UnitClassExample {

    private static final String FOLDER =
            ExampleAssist.getOrCreateResourceFolderPath("generation", "unit-class");

    @BeforeClass
    public void setUp() {
        LicenseAssist.setupLicense();
    }

    /**
     * Defines physical barcode dimensions in millimeters at 300 DPI.
     */
    @Test
    public void configureDimensionsInMillimeters() throws Exception {
        String codeText = "UNIT-MM";
        String outputPath = ExampleAssist.pathCombine(FOLDER, "unit_millimeters.png");

        BarcodeGenerator generator = new BarcodeGenerator(EncodeTypes.CODE_128, codeText);
        generator.getParameters().setResolution(300.0f);
        generator.getParameters().getBarcode().getXDimension().setMillimeters(0.33f);
        generator.getParameters().getBarcode().getBarHeight().setMillimeters(15.0f);
        generator.getParameters().getBarcode().getPadding().getLeft().setMillimeters(3.5f);
        generator.getParameters().getBarcode().getPadding().getRight().setMillimeters(3.5f);
        generator.save(outputPath, BarCodeImageFormat.PNG);

        ExampleAssist.assertFileCreated(outputPath);
        assertImageHasBarcodes(outputPath, 1, List.of(expected(DecodeType.CODE_128, codeText)));
    }

    /**
     * Shows how a Unit converts a physical measurement to pixels for a selected DPI.
     */
    @Test
    public void convertPointAndInchValues() {
        BarcodeGenerator generator = new BarcodeGenerator(EncodeTypes.CODE_128, "UNIT-CONVERSION");

        Unit pointUnit = generator.getParameters().getBarcode().getPadding().getTop();
        pointUnit.updateResolution(300.0f);
        pointUnit.setPoint(12.0f);

        Unit inchUnit = generator.getParameters().getBarcode().getPadding().getLeft();
        inchUnit.updateResolution(300.0f);
        inchUnit.setInches(0.25f);

        Assert.assertTrue(pointUnit.getPixels() > 0);
        Assert.assertEquals(inchUnit.getPixels(), 75.0f, 0.01f);
    }
}
