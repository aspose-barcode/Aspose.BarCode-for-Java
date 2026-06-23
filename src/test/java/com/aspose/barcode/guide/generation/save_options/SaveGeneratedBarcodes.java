package com.aspose.barcode.guide.generation.save_options;

import com.aspose.barcode.barcoderecognition.BarCodeReader;
import com.aspose.barcode.barcoderecognition.BarCodeResult;
import com.aspose.barcode.barcoderecognition.DecodeType;
import com.aspose.barcode.generation.BarCodeImageFormat;
import com.aspose.barcode.generation.BarcodeGenerator;
import com.aspose.barcode.generation.EncodeTypes;
import com.aspose.barcode.guide.common.ExampleAssist;
import com.aspose.barcode.guide.common.LicenseAssist;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import static com.aspose.barcode.guide.common.ExampleAssist.assertImageHasBarcodes;
import static com.aspose.barcode.guide.common.ExampleAssist.expected;

/**
 * Demonstrates raster, vector, and stream output.
 */
public class SaveGeneratedBarcodes {

    private static final String FOLDER =
            ExampleAssist.getOrCreateResourceFolderPath("generation", "save-generated-barcodes");

    @BeforeClass
    public void setUp() {
        LicenseAssist.setupLicense();
    }

    /**
     * Saves the same barcode to PNG, JPEG, BMP, and TIFF.
     */
    @Test
    public void saveRasterFormats() throws Exception {
        String codeText = "SAVE-RASTER";
        BarcodeGenerator generator = new BarcodeGenerator(EncodeTypes.CODE_128, codeText);

        saveAndVerifyRaster(generator, "barcode.png", BarCodeImageFormat.PNG, codeText);
        saveAndVerifyRaster(generator, "barcode.jpg", BarCodeImageFormat.JPEG, codeText);
        saveAndVerifyRaster(generator, "barcode.bmp", BarCodeImageFormat.BMP, codeText);
        saveAndVerifyRaster(generator, "barcode.tif", BarCodeImageFormat.TIFF, codeText);
    }

    /**
     * Saves a QR Code to SVG vector format.
     */
    @Test
    public void saveVectorFormat() throws Exception {
        String outputPath = ExampleAssist.pathCombine(FOLDER, "qrcode.svg");
        BarcodeGenerator generator = new BarcodeGenerator(EncodeTypes.QR, "SAVE-SVG");
        generator.save(outputPath, BarCodeImageFormat.SVG);
        ExampleAssist.assertFileCreated(outputPath);
    }

    /**
     * Saves a barcode to an in-memory stream and recognizes it from that stream.
     */
    @Test
    public void saveToStream() throws IOException {
        String codeText = "SAVE-STREAM";
        BarcodeGenerator generator = new BarcodeGenerator(EncodeTypes.CODE_128, codeText);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        generator.save(outputStream, BarCodeImageFormat.PNG);
        byte[] imageBytes = outputStream.toByteArray();
        Assert.assertTrue(imageBytes.length > 0);

        ByteArrayInputStream inputStream = new ByteArrayInputStream(imageBytes);
        BarCodeReader reader = new BarCodeReader(inputStream, DecodeType.CODE_128);
        BarCodeResult[] results = reader.readBarCodes();

        Assert.assertEquals(results.length, 1);
        Assert.assertEquals(results[0].getCodeType(), DecodeType.CODE_128);
        Assert.assertEquals(results[0].getCodeText(), codeText);
    }

    private static void saveAndVerifyRaster(
            BarcodeGenerator generator,
            String fileName,
            BarCodeImageFormat format,
            String expectedText) throws Exception {
        String outputPath = ExampleAssist.pathCombine(FOLDER, fileName);
        generator.save(outputPath, format);
        ExampleAssist.assertFileCreated(outputPath);
        assertImageHasBarcodes(
                outputPath,
                1,
                List.of(expected(DecodeType.CODE_128, expectedText))
        );
    }
}
