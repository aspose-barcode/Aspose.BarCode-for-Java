package com.aspose.barcode.guide.recognition.performance;

import com.aspose.barcode.*;
import com.aspose.barcode.barcoderecognition.BarCodeReader;
import com.aspose.barcode.barcoderecognition.BarCodeResult;
import com.aspose.barcode.barcoderecognition.DecodeType;
import com.aspose.barcode.barcoderecognition.QualitySettings;
import com.aspose.barcode.generation.BarCodeImageFormat;
import com.aspose.barcode.generation.BarcodeGenerator;
import com.aspose.barcode.generation.EncodeTypes;
import com.aspose.barcode.guide.common.ExampleAssist;
import com.aspose.barcode.guide.common.LicenseAssist;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


import java.io.IOException;
import java.nio.file.*;

import static com.aspose.barcode.guide.common.ExampleAssist.checkOrCreateImage;

public class UseMinimalXDimensionExample
{

    private static final String FOLDER =
            ExampleAssist.getOrCreateResourceFolderPath("recognition", "basic_setup", "choose_symbology");

    @BeforeClass
    public void setUp()
    {
        LicenseAssist.setupLicense();
    }

    // Code 128 with X=1 px (edge case; fragile)
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

    // Code 128 with X=2 px (recommended “practical minimal”)
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

    // QR with X=2 px (tiny modules + proper quiet zone)
    private void generate_QR_X2(String fullPath) throws IOException
    {
        BarcodeGenerator gen = new BarcodeGenerator(EncodeTypes.QR, "MIN-XD-QR-X2");
        gen.getParameters().getBarcode().getXDimension().setPixels(2);

        gen.getParameters().getBarcode().getPadding().getLeft().setPixels(16);
        gen.getParameters().getBarcode().getPadding().getRight().setPixels(16);
        gen.getParameters().getBarcode().getPadding().getTop().setPixels(16);
        gen.getParameters().getBarcode().getPadding().getBottom().setPixels(16);

        gen.save(fullPath, BarCodeImageFormat.PNG);
    }

    @Test
    public void read_Code128_MinimalX_1px() throws Exception
    {
        String file = "code128_min_x1.png";
        // Reuse your existing helper:
        checkOrCreateImage(FOLDER, file, this::generate_Code128_X1);

        String path = ExampleAssist.pathCombine(FOLDER, file);
        BarCodeReader reader = new BarCodeReader(path, DecodeType.CODE_128);
        reader.getQualitySettings().setMinimalXDimension();
        reader.getBarcodeSettings()
        BarCodeResult[] results = reader.readBarCodes();

        System.out.println("== Code 128, X=1 px ==");
        for (BarCodeResult r : results)
        {
            System.out.println("Code Type: " + r.getCodeTypeName() + " - Code Text: " + r.getCodeText());
        }

    }

}