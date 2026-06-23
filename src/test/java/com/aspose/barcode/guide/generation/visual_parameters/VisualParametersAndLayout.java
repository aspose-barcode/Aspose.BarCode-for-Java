package com.aspose.barcode.guide.generation.visual_parameters;

import com.aspose.barcode.barcoderecognition.DecodeType;
import com.aspose.barcode.generation.BarCodeImageFormat;
import com.aspose.barcode.generation.BarcodeGenerator;
import com.aspose.barcode.generation.CodeLocation;
import com.aspose.barcode.generation.EncodeTypes;
import com.aspose.barcode.guide.common.ExampleAssist;
import com.aspose.barcode.guide.common.LicenseAssist;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

import static com.aspose.barcode.guide.common.ExampleAssist.assertImageHasBarcodes;
import static com.aspose.barcode.guide.common.ExampleAssist.expected;

/**
 * Demonstrates how common visual and layout parameters work together.
 */
public class VisualParametersAndLayout {

    private static final String FOLDER =
            ExampleAssist.getOrCreateResourceFolderPath("generation", "visual-parameters-and-layout");

    @BeforeClass
    public void setUp() {
        LicenseAssist.setupLicense();
    }

    /**
     * Configures the module width, bar height, padding, image size, and code text location.
     */
    @Test
    public void configureCommonLayoutParameters() throws Exception {
        String codeText = "LAYOUT-EXAMPLE";
        String outputPath = ExampleAssist.pathCombine(FOLDER, "visual_layout.png");

        BarcodeGenerator generator = new BarcodeGenerator(EncodeTypes.CODE_128, codeText);
        generator.getParameters().getBarcode().getXDimension().setPixels(2.0f);
        generator.getParameters().getBarcode().getBarHeight().setPixels(90);
        generator.getParameters().getBarcode().getPadding().getLeft().setPixels(24);
        generator.getParameters().getBarcode().getPadding().getRight().setPixels(24);
        generator.getParameters().getBarcode().getPadding().getTop().setPixels(12);
        generator.getParameters().getBarcode().getPadding().getBottom().setPixels(12);
        generator.getParameters().getBarcode().getCodeTextParameters().setLocation(CodeLocation.BELOW);
        generator.getParameters().getImageWidth().setPixels(520);
        generator.getParameters().getImageHeight().setPixels(180);
        generator.save(outputPath, BarCodeImageFormat.PNG);

        ExampleAssist.assertFileCreated(outputPath);
        assertImageHasBarcodes(outputPath, 1, List.of(expected(DecodeType.CODE_128, codeText)));
    }
}
