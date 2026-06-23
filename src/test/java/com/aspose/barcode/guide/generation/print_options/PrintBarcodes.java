package com.aspose.barcode.guide.generation.print_options;

import com.aspose.barcode.barcoderecognition.DecodeType;
import com.aspose.barcode.generation.BarCodeImageFormat;
import com.aspose.barcode.generation.BarcodeGenerator;
import com.aspose.barcode.generation.EncodeTypes;
import com.aspose.barcode.guide.common.ExampleAssist;
import com.aspose.barcode.guide.common.LicenseAssist;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

import static com.aspose.barcode.guide.common.ExampleAssist.assertImageHasBarcodes;
import static com.aspose.barcode.guide.common.ExampleAssist.expected;

/**
 * Demonstrates printer-oriented generation settings.
 */
public class PrintBarcodes {

    private static final String FOLDER =
            ExampleAssist.getOrCreateResourceFolderPath("generation", "printing");

    @BeforeClass
    public void setUp() {
        LicenseAssist.setupLicense();
    }

    /**
     * Configures a physical X-dimension and bar height for a 300 DPI output image.
     */
    @Test
    public void configureResolutionAndPhysicalSize() throws Exception {
        String codeText = "PRINT-300-DPI";
        String outputPath = ExampleAssist.pathCombine(FOLDER, "code128_300dpi.png");

        BarcodeGenerator generator = new BarcodeGenerator(EncodeTypes.CODE_128, codeText);
        generator.getParameters().setResolution(300.0f);
        generator.getParameters().getBarcode().getXDimension().setMillimeters(0.33f);
        generator.getParameters().getBarcode().getBarHeight().setMillimeters(18.0f);
        generator.getParameters().getBarcode().getPadding().getLeft().setMillimeters(3.5f);
        generator.getParameters().getBarcode().getPadding().getRight().setMillimeters(3.5f);
        generator.save(outputPath, BarCodeImageFormat.PNG);

        ExampleAssist.assertFileCreated(outputPath);
        assertImageHasBarcodes(outputPath, 1, List.of(expected(DecodeType.CODE_128, codeText)));
    }

    /**
     * Configures the wide-to-narrow ratio for Code 39.
     */
    @Test
    public void configureWideToNarrowRatio() throws Exception {
        String codeText = "CODE39-RATIO";
        String outputPath = ExampleAssist.pathCombine(FOLDER, "code39_ratio.png");

        BarcodeGenerator generator = new BarcodeGenerator(EncodeTypes.CODE_39, codeText);
        generator.getParameters().getBarcode().setWideNarrowRatio(2.5f);
        generator.getParameters().getBarcode().getXDimension().setPixels(2.0f);
        generator.getParameters().getBarcode().getBarHeight().setPixels(100);
        generator.save(outputPath, BarCodeImageFormat.PNG);

        ExampleAssist.assertFileCreated(outputPath);
        assertImageHasBarcodes(outputPath, 1, List.of(expected(DecodeType.CODE_39, codeText)));
    }

    /**
     * Compares anti-aliasing disabled and enabled for QR raster output.
     */
    @Test
    public void configureAntiAliasing() throws Exception {
        String codeText = "PRINT-ANTIALIAS";

        BarcodeGenerator crispGenerator = new BarcodeGenerator(EncodeTypes.QR, codeText);
        crispGenerator.getParameters().setUseAntiAlias(false);
        crispGenerator.getParameters().getBarcode().getXDimension().setPixels(4);
        String crispPath = ExampleAssist.pathCombine(FOLDER, "qr_antialias_off.png");
        crispGenerator.save(crispPath, BarCodeImageFormat.PNG);
        ExampleAssist.assertFileCreated(crispPath);
        assertImageHasBarcodes(crispPath, 1, List.of(expected(DecodeType.QR, codeText)));

        BarcodeGenerator smoothGenerator = new BarcodeGenerator(EncodeTypes.QR, codeText);
        smoothGenerator.getParameters().setUseAntiAlias(true);
        smoothGenerator.getParameters().getBarcode().getXDimension().setPixels(4);
        String smoothPath = ExampleAssist.pathCombine(FOLDER, "qr_antialias_on.png");
        smoothGenerator.save(smoothPath, BarCodeImageFormat.PNG);
        ExampleAssist.assertFileCreated(smoothPath);
        assertImageHasBarcodes(smoothPath, 1, List.of(expected(DecodeType.QR, codeText)));
    }
}
