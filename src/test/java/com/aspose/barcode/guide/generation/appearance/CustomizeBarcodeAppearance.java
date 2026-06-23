package com.aspose.barcode.guide.generation.appearance;

import com.aspose.barcode.barcoderecognition.DecodeType;
import com.aspose.barcode.generation.BarCodeImageFormat;
import com.aspose.barcode.generation.BarcodeGenerator;
import com.aspose.barcode.generation.EncodeTypes;
import com.aspose.barcode.guide.common.ExampleAssist;
import com.aspose.barcode.guide.common.LicenseAssist;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.awt.Color;
import java.util.List;

import static com.aspose.barcode.guide.common.ExampleAssist.assertImageHasBarcodes;
import static com.aspose.barcode.guide.common.ExampleAssist.expected;

/**
 * Demonstrates rotation, colors, borders, and backgrounds.
 */
public class CustomizeBarcodeAppearance {

    private static final String FOLDER =
            ExampleAssist.getOrCreateResourceFolderPath("generation", "customize-barcode-appearance");

    @BeforeClass
    public void setUp() {
        LicenseAssist.setupLicense();
    }

    /**
     * Applies rotation, foreground and background colors, and a visible border.
     */
    @Test
    public void configureRotationColorsBorderAndBackground() throws Exception {
        String codeText = "APPEARANCE-QR";
        String outputPath = ExampleAssist.pathCombine(FOLDER, "qr_appearance.png");

        BarcodeGenerator generator = new BarcodeGenerator(EncodeTypes.QR, codeText);
        generator.getParameters().setRotationAngle(90.0f);
        generator.getParameters().setBackColor(new Color(245, 248, 252));
        generator.getParameters().getBarcode().setBarColor(new Color(20, 45, 90));
        generator.getParameters().getBorder().setVisible(true);
        generator.getParameters().getBorder().setColor(Color.GRAY);
        generator.getParameters().getBorder().getWidth().setPixels(2);
        generator.getParameters().getBarcode().getPadding().getLeft().setPixels(16);
        generator.getParameters().getBarcode().getPadding().getRight().setPixels(16);
        generator.getParameters().getBarcode().getPadding().getTop().setPixels(16);
        generator.getParameters().getBarcode().getPadding().getBottom().setPixels(16);
        generator.save(outputPath, BarCodeImageFormat.PNG);

        ExampleAssist.assertFileCreated(outputPath);
        assertImageHasBarcodes(outputPath, 1, List.of(expected(DecodeType.QR, codeText)));
    }

    /**
     * Uses an ARGB background with transparency.
     */
    @Test
    public void configureTransparentBackground() throws Exception {
        String codeText = "TRANSPARENT-QR";
        String outputPath = ExampleAssist.pathCombine(FOLDER, "qr_transparent.png");

        BarcodeGenerator generator = new BarcodeGenerator(EncodeTypes.QR, codeText);
        generator.getParameters().setBackColor(new Color(255, 255, 255, 0));
        generator.getParameters().getBarcode().setBarColor(Color.BLACK);
        generator.save(outputPath, BarCodeImageFormat.PNG);

        ExampleAssist.assertFileCreated(outputPath);
        assertImageHasBarcodes(outputPath, 1, List.of(expected(DecodeType.QR, codeText)));
    }
}
