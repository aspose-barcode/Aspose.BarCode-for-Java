package com.aspose.barcode.guide.recognition.conditions;

import com.aspose.barcode.barcoderecognition.BarCodeReader;
import com.aspose.barcode.barcoderecognition.DecodeType;
import com.aspose.barcode.barcoderecognition.InverseImageMode;
import com.aspose.barcode.barcoderecognition.QualitySettings;
import com.aspose.barcode.generation.BarCodeImageFormat;
import com.aspose.barcode.generation.BarcodeGenerator;
import com.aspose.barcode.generation.EncodeTypes;
import com.aspose.barcode.guide.common.ExampleAssist;
import com.aspose.barcode.guide.common.LicenseAssist;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * Reading color-inverted barcodes (Java, TestNG).
 * <p>
 * Notes:
 * 1) No try-with-resources is used because BarCodeReader is not AutoCloseable.
 * 2) Requires ExampleAssist.invertColors(srcPath, outPath) — see helper method below.
 */
public class ReadingColorInvertedExample
{

    private static final String FOLDER =
            ExampleAssist.getOrCreateResourceFolderPath("recognition", "color", "color_inverted");

    @BeforeClass
    public void setUp() throws Exception
    {
        LicenseAssist.setupLicense();
        generateFixtures();
    }

    /**
     * Creates fixtures for inverted-image tests with the text "INVERT IMAGE TEST".
     * Produces normal and inverted pairs for Code128, QR, PDF417, DataMatrix.
     */
    private void generateFixtures() throws Exception
    {
        // CODE_128
        ExampleAssist.checkOrCreateImage(FOLDER, "Code128.png", out -> {
            BarcodeGenerator gen = new BarcodeGenerator(EncodeTypes.CODE_128, "INVERT IMAGE TEST");
            gen.save(out, BarCodeImageFormat.PNG);
        });
        ExampleAssist.checkOrCreateImage(FOLDER, "Code128Invert.png", out -> {
            String src = ExampleAssist.pathCombine(FOLDER, "Code128.png");
            ExampleAssist.invertColors(src, out);
        });

        // QR
        ExampleAssist.checkOrCreateImage(FOLDER, "QR.png", out -> {
            BarcodeGenerator gen = new BarcodeGenerator(EncodeTypes.QR, "INVERT IMAGE TEST");
            gen.save(out, BarCodeImageFormat.PNG);
        });
        ExampleAssist.checkOrCreateImage(FOLDER, "QRInvert.png", out -> {
            String src = ExampleAssist.pathCombine(FOLDER, "QR.png");
            ExampleAssist.invertColors(src, out);
        });

        // PDF417
        ExampleAssist.checkOrCreateImage(FOLDER, "PDF417.png", out -> {
            BarcodeGenerator gen = new BarcodeGenerator(EncodeTypes.PDF_417, "INVERT IMAGE TEST");
            gen.save(out, BarCodeImageFormat.PNG);
        });
        ExampleAssist.checkOrCreateImage(FOLDER, "PDF417Invert.png", out -> {
            String src = ExampleAssist.pathCombine(FOLDER, "PDF417.png");
            ExampleAssist.invertColors(src, out);
        });

        // DataMatrix
        ExampleAssist.checkOrCreateImage(FOLDER, "DM.png", out -> {
            BarcodeGenerator gen = new BarcodeGenerator(EncodeTypes.DATA_MATRIX, "INVERT IMAGE TEST");
            gen.save(out, BarCodeImageFormat.PNG);
        });
        ExampleAssist.checkOrCreateImage(FOLDER, "DMInvert.png", out -> {
            String src = ExampleAssist.pathCombine(FOLDER, "DM.png");
            ExampleAssist.invertColors(src, out);
        });
    }

    // --- Tests based on provided examples, using InverseImageMode ---

    @Test
    public void Code128Invert()
    {
        String filename = ExampleAssist.pathCombine(FOLDER, "Code128Invert.png");
        BarCodeReader reader = new BarCodeReader(filename, DecodeType.CODE_128);
        reader.getQualitySettings().setInverseImage(InverseImageMode.ENABLED);
        ExampleAssist.assertRecognizedWithText(reader, filename, 1, "INVERT IMAGE TEST");
    }

    @Test
    public void QRInvert()
    {
        String filename = ExampleAssist.pathCombine(FOLDER, "QRInvert.png");
        BarCodeReader reader = new BarCodeReader(filename, DecodeType.QR);
        reader.getQualitySettings().setInverseImage(InverseImageMode.ENABLED);
        ExampleAssist.assertRecognizedWithText(reader, filename, 1, "INVERT IMAGE TEST");
    }

    @Test
    public void PDF417Invert()
    {
        String filename = ExampleAssist.pathCombine(FOLDER, "PDF417Invert.png");
        BarCodeReader reader = new BarCodeReader(filename, DecodeType.PDF_417);
        reader.getQualitySettings().setInverseImage(InverseImageMode.ENABLED);
        ExampleAssist.assertRecognizedWithText(reader, filename, 1, "INVERT IMAGE TEST");
    }

    @Test
    public void DMInvert()
    {
        String filename = ExampleAssist.pathCombine(FOLDER, "DMInvert.png");
        BarCodeReader reader = new BarCodeReader(filename, DecodeType.DATA_MATRIX);
        reader.getQualitySettings().setInverseImage(InverseImageMode.ENABLED);
        ExampleAssist.assertRecognizedWithText(reader, filename, 1, "INVERT IMAGE TEST");
    }

    @Test
    public void UserImage_DM_Email()
    {
        String filename = ExampleAssist.pathCombine(FOLDER, "datamatrix-generator.png");
        if (!ExampleAssist.fileExists(filename))
        {
            ExampleAssist.logWarn("Skip UserImage_DM_Email: not found " + filename);
            return;
        }
        BarCodeReader reader = new BarCodeReader(filename, DecodeType.DATA_MATRIX);
        reader.getQualitySettings().setInverseImage(InverseImageMode.ENABLED);
        ExampleAssist.assertRecognizedWithText(reader, filename, 1, "bartoli.giacomo@email.it");
    }

    // Optional extra samples from the provided list (run if BMP fixtures exist)
    @Test
    public void DMInvert4bpp_BMP_ifExists()
    {
        String filename = ExampleAssist.pathCombine(FOLDER, "DMInvert4bpp.bmp");
        if (!ExampleAssist.fileExists(filename))
        {
            ExampleAssist.logWarn("Skip DMInvert4bpp_BMP_ifExists: not found " + filename);
            return;
        }
        BarCodeReader reader = new BarCodeReader(filename, DecodeType.DATA_MATRIX);
        reader.getQualitySettings().setInverseImage(InverseImageMode.ENABLED);
        ExampleAssist.assertRecognizedWithText(reader, filename, 1, "INVERT IMAGE TEST");
    }

    @Test
    public void QRInvert1bpp_BMP_ifExists()
    {
        String filename = ExampleAssist.pathCombine(FOLDER, "QRInvert1bpp.bmp");
        if (!ExampleAssist.fileExists(filename))
        {
            ExampleAssist.logWarn("Skip QRInvert1bpp_BMP_ifExists: not found " + filename);
            return;
        }
        BarCodeReader reader = new BarCodeReader(filename, DecodeType.QR);
        reader.getQualitySettings().setInverseImage(InverseImageMode.ENABLED);
        ExampleAssist.assertRecognizedWithText(reader, filename, 1, "INVERT IMAGE TEST");
    }
}

