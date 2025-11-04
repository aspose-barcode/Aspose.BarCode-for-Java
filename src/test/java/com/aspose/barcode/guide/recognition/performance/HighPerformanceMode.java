package com.aspose.barcode.guide.recognition.performance;

import com.aspose.barcode.barcoderecognition.BarCodeReader;
import com.aspose.barcode.barcoderecognition.BarCodeResult;
import com.aspose.barcode.barcoderecognition.DecodeType;
import com.aspose.barcode.barcoderecognition.QualitySettings;
import com.aspose.barcode.generation.BarCodeImageFormat;
import com.aspose.barcode.generation.BarcodeGenerator;
import com.aspose.barcode.generation.EncodeTypes;
import com.aspose.barcode.guide.common.ExampleAssist;
import com.aspose.barcode.guide.common.LicenseAssist;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;

/**
 * Demonstrates how to enable and use High Performance mode during recognition.
 * The tests keep recognition simple (>=1 result) to focus on API usage and the setup pattern.
 */
public class HighPerformanceMode
{

    private static final String FOLDER =
            ExampleAssist.getOrCreateResourceFolderPath("recognition", "performance", "high_performance_mode");

    @BeforeClass
    public void setUp()
    {
        LicenseAssist.setupLicense();
    }

    // --- QR + HighPerformance ---
    @Test
    public void read_QR_HighPerformance() throws Exception {
        // Purpose: Recognize a clean QR using High Performance preset.
        String fileName = "qr_hp.png";
        String path = ExampleAssist.pathCombine(FOLDER, fileName);

        // Force regeneration: delete stale or corrupted file if it exists
        java.nio.file.Files.deleteIfExists(java.nio.file.Paths.get(path));

        ExampleAssist.checkOrCreateImage(FOLDER, fileName, p -> {
            BarcodeGenerator g = new BarcodeGenerator(EncodeTypes.QR, "HP-QR");
            // Make modules a bit larger to be robust under HighPerformance
            g.getParameters().getBarcode().getXDimension().setPixels(4);
            g.save(p, BarCodeImageFormat.PNG);
        });

        BarCodeReader reader = new BarCodeReader(path, DecodeType.QR);
        reader.setQualitySettings(QualitySettings.getHighPerformance());

        ExampleAssist.assertRecognized(reader, fileName, 1, DecodeType.QR);
    }


    // --- Code128 + HighPerformance ---
    @Test
    public void read_Code128_HighPerformance() throws Exception
    {
        // Purpose: Linear code (1D) decoding with High Performance, typical for fast scanning.
        String fileName = "code128_hp.png";
        ExampleAssist.checkOrCreateImage(FOLDER, fileName, path -> {
            BarcodeGenerator g = new BarcodeGenerator(EncodeTypes.CODE_128, "FAST-128");
            g.save(path, BarCodeImageFormat.PNG);
        });

        BarCodeReader reader = new BarCodeReader(ExampleAssist.pathCombine(FOLDER, fileName), DecodeType.CODE_128);
        reader.setQualitySettings(QualitySettings.getHighPerformance());

        ExampleAssist.assertRecognized(reader, fileName, 1, DecodeType.CODE_128);
    }

    // --- Restrict types for extra speed (ALL + narrowed ReadType) ---
    @Test
    public void read_LimitedTypes_AllSupported_WithHighPerformance() throws Exception
    {
        // Purpose: Show that limiting the set of candidate symbologies speeds up recognition.
        // We generate Data Matrix and restrict the reader to CODE_128/QR/DATA_MATRIX.
        String fileName = "limited_types_hp.png";
        ExampleAssist.checkOrCreateImage(FOLDER, fileName, path -> {
            BarcodeGenerator g = new BarcodeGenerator(EncodeTypes.DATA_MATRIX, "HP-DM");
            g.save(path, BarCodeImageFormat.PNG);
        });

        BarCodeReader reader = new BarCodeReader(ExampleAssist.pathCombine(FOLDER, fileName), DecodeType.ALL_SUPPORTED_TYPES);
        reader.setQualitySettings(QualitySettings.getHighPerformance());
        reader.setBarCodeReadType(DecodeType.CODE_128, DecodeType.QR, DecodeType.DATA_MATRIX);

        ExampleAssist.assertRecognized(reader, fileName, 1, DecodeType.DATA_MATRIX);
    }

    // --- Group: 1D set + HighPerformance ---
//    Purpose: Recognize any 1D barcode using TYPES_1D with High Performance preset.
    @Test
    public void read_Any1DGroup_HighPerformance() throws Exception
    {
        String fileName = "types_1d_hp.png";
        ExampleAssist.checkOrCreateImage(FOLDER, fileName, path -> {
            // Any 1D symbology; EAN-13 is a common choice.
            BarcodeGenerator g = new BarcodeGenerator(EncodeTypes.EAN_13, "5901234123457");
            g.save(path, BarCodeImageFormat.PNG);
        });

        BarCodeReader reader = new BarCodeReader(ExampleAssist.pathCombine(FOLDER, fileName), DecodeType.TYPES_1D);
        reader.setQualitySettings(QualitySettings.getHighPerformance());

        ExampleAssist.assertRecognized(reader, fileName, 1, DecodeType.EAN_13);
    }

    // --- Group: 2D set + HighPerformance ---
    // Purpose: Recognize any 2D barcode using TYPES_2D with High Performance preset.
    @Test(enabled = false)  //TODO BARCODEJAVA-2164
    public void read_Any2DGroup_HighPerformance() throws Exception
    {
        String fileName = "types_2d_hp.png";
        BarcodeGenerator g = new BarcodeGenerator(EncodeTypes.QR, "HP-QR");
        String path = FOLDER + "/" +  fileName;
        g.save(path, BarCodeImageFormat.PNG);
        BarCodeReader reader = new BarCodeReader(ExampleAssist.pathCombine(FOLDER, fileName), DecodeType.QR);
        reader.setQualitySettings(QualitySettings.getHighPerformance());
        BarCodeResult[] results = reader.readBarCodes();
        Assert.assertEquals(results.length, 1);
        for (BarCodeResult result : results)
        {
            System.out.println(" Code Type: " + result.getCodeTypeName() + " - Code Text: " + result.getCodeText());
        }
    }

    // --- Tougher image handled in HighPerformance (still OK for clean cases) ---
    // Purpose: Use High Performance on a QR where we also set reader properties typical for speed-first scenarios.
    // (We keep generation clean; the focus is the "speed" preset usage, not error-recovery.)
    @Test(enabled = false)  //TODO BARCODEJAVA-2164
    public void read_QR_HighPerformance_TougherCase() throws Exception
    {

        String fileName = "qr_hp_tough.png";
        ExampleAssist.checkOrCreateImage(FOLDER, fileName, path -> {
            BarcodeGenerator g = new BarcodeGenerator(EncodeTypes.QR, "HP-TOUGH");
            g.save(path, BarCodeImageFormat.PNG);
        });

        BarCodeReader reader = new BarCodeReader(ExampleAssist.pathCombine(FOLDER, fileName), DecodeType.QR);
        reader.setQualitySettings(QualitySettings.getHighPerformance());
        // Optional: additionally restrict the scan window or similar knobs if exposed in your build.

        ExampleAssist.assertRecognized(reader, fileName, 1, DecodeType.QR);
    }
}
