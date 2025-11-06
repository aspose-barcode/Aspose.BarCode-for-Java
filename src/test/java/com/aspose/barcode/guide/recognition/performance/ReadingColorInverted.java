package com.aspose.barcode.guide.recognition.conditions;

import com.aspose.barcode.barcoderecognition.BarCodeReader;
import com.aspose.barcode.barcoderecognition.DecodeType;
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
 *
 * Notes:
 * 1) No try-with-resources is used because BarCodeReader is not AutoCloseable.
 * 2) Requires ExampleAssist.invertColors(srcPath, outPath) — see helper method below.
 */
public class ReadingColorInvertedExample {

    private static final String FOLDER =
            ExampleAssist.getOrCreateResourceFolderPath("recognition", "color", "inverted");

    @BeforeClass
    public void setUp() throws Exception {
        LicenseAssist.setupLicense();
        generateFixtures();
    }

    /**
     * Create small fixtures if absent:
     *  - code128_normal.png → code128_inverted.png
     *  - qr_normal.png      → qr_inverted.png
     */
    private void generateFixtures() throws Exception {
        // CODE_128 base
        ExampleAssist.checkOrCreateImage(FOLDER, "code128_normal.png", out -> {
            BarcodeGenerator gen = new BarcodeGenerator(EncodeTypes.CODE_128, "Invert OFF/ON demo");
            gen.save(out, BarCodeImageFormat.PNG);
        });
        // CODE_128 inverted from base
        ExampleAssist.checkOrCreateImage(FOLDER, "code128_inverted.png", out -> {
            String src = ExampleAssist.pathCombine(FOLDER, "code128_normal.png");
            ExampleAssist.invertColors(src, out);
        });

        // QR base
        ExampleAssist.checkOrCreateImage(FOLDER, "qr_normal.png", out -> {
            BarcodeGenerator gen = new BarcodeGenerator(EncodeTypes.QR, "Invert QR Demo");
            gen.save(out, BarCodeImageFormat.PNG);
        });
        // QR inverted from base
        ExampleAssist.checkOrCreateImage(FOLDER, "qr_inverted.png", out -> {
            String src = ExampleAssist.pathCombine(FOLDER, "qr_normal.png");
            ExampleAssist.invertColors(src, out);
        });
    }

    /**
     * Purpose:
     *  - Show that an inverted CODE_128 may fail when inversion is disabled.
     * Expectation:
     *  - Zero results when AllowInvertImage == false, then success when true.
     */
    @Test
    public void read_Code128_Inverted_AllowInvert_OFF_then_ON() throws Exception {
        String path = ExampleAssist.pathCombine(FOLDER, "code128_inverted.png");

        // OFF
        BarCodeReader reader = new BarCodeReader(path, DecodeType.CODE_128);
        QualitySettings qs = QualitySettings.getHighPerformance();
        reader.setQualitySettings(qs);
        reader.getBarcodeSettings().setAllowInvertImage(false);
        ExampleAssist.assertNotRecognized(reader, "code128_inverted.png");

        // ON
        reader = new BarCodeReader(path, DecodeType.CODE_128);
        qs = QualitySettings.getNormalQuality();
        reader.setQualitySettings(qs);
        reader.getBarcodeSettings().setAllowInvertImage(true);
        ExampleAssist.assertRecognized(reader, "code128_inverted.png", 1, DecodeType.CODE_128);
    }

    /**
     * Purpose:
     *  - Read an inverted QR image using a common 2D-type set with inversion ON.
     */
    @Test
    public void read_QR_Inverted_Multi2D_WithInvert_ON() throws Exception {
        String path = ExampleAssist.pathCombine(FOLDER, "qr_inverted.png");

        BarCodeReader reader = new BarCodeReader(
                path,
                new int[]{DecodeType.QR, DecodeType.MICRO_QR, DecodeType.DATA_MATRIX, DecodeType.AZTEC}
        );

        QualitySettings qs = QualitySettings.getHighQuality();
        reader.setQualitySettings(qs);
        reader.getBarcodeSettings().setAllowInvertImage(true);

        ExampleAssist.assertRecognized(reader, "qr_inverted.png", 1, DecodeType.QR);
    }

    /**
     * Purpose:
     *  - Batch process a folder with mixed normal and inverted images.
     */
    @Test
    public void read_Batch_Mixed_NormalAndInverted_Invert_ON() throws Exception {
        String[] files = ExampleAssist.listFilesByGlob(FOLDER, "*.png");
        if (files == null || files.length == 0) {
            ExampleAssist.logWarn("No PNG files in: " + FOLDER);
            return;
        }

        for (String path : files) {
            BarCodeReader reader = new BarCodeReader(path, DecodeType.ALL_SUPPORTED_TYPES);
            QualitySettings qs = QualitySettings.getHighPerformance();
            reader.setQualitySettings(qs);
            reader.getBarcodeSettings().setAllowInvertImage(true);

            ExampleAssist.logInfo("Reading: " + path);
            ExampleAssist.assertHasAnyResult(reader, ExampleAssist.getFileName(path));
        }
    }
}
