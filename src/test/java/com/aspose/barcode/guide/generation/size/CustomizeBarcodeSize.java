package com.aspose.barcode.guide.generation.size;

import com.aspose.barcode.barcoderecognition.BarCodeReader;
import com.aspose.barcode.barcoderecognition.BarCodeResult;
import com.aspose.barcode.barcoderecognition.DecodeType;
import com.aspose.barcode.generation.AutoSizeMode;
import com.aspose.barcode.generation.BarCodeImageFormat;
import com.aspose.barcode.generation.BarcodeGenerator;
import com.aspose.barcode.generation.EncodeTypes;
import com.aspose.barcode.generation.ITF14BorderType;
import com.aspose.barcode.guide.common.ExampleAssist;
import com.aspose.barcode.guide.common.LicenseAssist;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

import static com.aspose.barcode.guide.common.ExampleAssist.assertImageHasBarcodes;
import static com.aspose.barcode.guide.common.ExampleAssist.expected;

/**
 * Demonstrates barcode sizing with X-dimension, bar height, auto-size modes,
 * and selected symbology-specific dimensions.
 */
public class CustomizeBarcodeSize {

    private static final String FOLDER =
            ExampleAssist.getOrCreateResourceFolderPath("generation", "customize-barcode-size");

    @BeforeClass
    public void setUp() {
        LicenseAssist.setupLicense();
    }

    /**
     * Controls a linear barcode through X-dimension and bar height.
     */
    @Test
    public void configureLinearBarcodeSize() throws Exception {
        String codeText = "SIZE-1D";
        String outputPath = ExampleAssist.pathCombine(FOLDER, "code128_size.png");

        BarcodeGenerator generator = new BarcodeGenerator(EncodeTypes.CODE_128, codeText);
        generator.getParameters().setAutoSizeMode(AutoSizeMode.NONE);
        generator.getParameters().getBarcode().getXDimension().setPixels(3.0f);
        generator.getParameters().getBarcode().getBarHeight().setPixels(100);
        generator.save(outputPath, BarCodeImageFormat.PNG);

        ExampleAssist.assertFileCreated(outputPath);
        assertImageHasBarcodes(outputPath, 1, List.of(expected(DecodeType.CODE_128, codeText)));
    }

    /**
     * Fits a QR Code into a target image box using AutoSizeMode.NEAREST.
     */
    @Test
    public void fitQrIntoImageBox() throws Exception {
        String codeText = "AUTO-SIZE-QR";
        String outputPath = ExampleAssist.pathCombine(FOLDER, "qr_nearest.png");

        BarcodeGenerator generator = new BarcodeGenerator(EncodeTypes.QR, codeText);
        generator.getParameters().setAutoSizeMode(AutoSizeMode.NEAREST);
        generator.getParameters().getImageWidth().setPixels(240);
        generator.getParameters().getImageHeight().setPixels(240);
        generator.save(outputPath, BarCodeImageFormat.PNG);

        ExampleAssist.assertFileCreated(outputPath);
        assertImageHasBarcodes(outputPath, 1, List.of(expected(DecodeType.QR, codeText)));
    }

    /**
     * Configures the short-bar height for Australian Post and the
     * bearer-bar thickness for ITF-14.
     */
    @Test
    public void configureSymbologySpecificDimensions() throws Exception {
        String australianPostCodeText = "5912345678";

        String australianPostPath = ExampleAssist.pathCombine(
                FOLDER,
                "australian_post_short_bar.png"
        );

        BarcodeGenerator australianPostGenerator =
                new BarcodeGenerator(
                        EncodeTypes.AUSTRALIA_POST,
                        australianPostCodeText
                );

        australianPostGenerator.getParameters()
                .getBarcode()
                .getAustralianPost()
                .getAustralianPostShortBarHeight()
                .setPixels(12);

        australianPostGenerator.save(
                australianPostPath,
                BarCodeImageFormat.PNG
        );

        ExampleAssist.assertFileCreated(
                australianPostPath
        );

        BarCodeReader australianPostReader = new BarCodeReader(
                australianPostPath,
                DecodeType.AUSTRALIA_POST
        );

        BarCodeResult[] australianPostResults =
                australianPostReader.readBarCodes();

        Assert.assertEquals(
                australianPostResults.length,
                1,
                "Expected exactly one Australia Post barcode"
        );

        Assert.assertEquals(
                australianPostResults[0].getCodeType(),
                DecodeType.AUSTRALIA_POST,
                "Decode type must be Australia Post"
        );

        Assert.assertTrue(
                australianPostResults[0]
                        .getCodeText()
                        .startsWith(australianPostCodeText),
                "Decoded Australia Post text must start with the source data"
        );
    }
}
