package com.aspose.barcode.guide.recognition.barcode_properties;

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

import javax.imageio.ImageIO;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

import static com.aspose.barcode.guide.common.ExampleAssist.hasDecodeType;
//OAI
/**
 * SymbologyTypeExamples
 *
 * Focus: how to configure symbology types for recognition.
 *
 * Covered scenarios:
 *  1) Single, explicit symbology only (best for speed and fewer false positives).
 *  2) Most-common set (DecodeType.MOST_COMMON_TYPES) when you do not know the exact type.
 *  3) All supported types (DecodeType.ALL_SUPPORTED_TYPES) for broad discovery.
 *  4) Multiple selected types at once (e.g., QR + Code128).
 *  5) Filtering: restricting to wrong type returns zero results (reduces false positives).
 *  6) Mixed image with two barcodes (QR + Code128) and varying decode sets.
 */
public class ConfigureSymbologyTypeExample
{

    private static final String FOLDER =
            ExampleAssist.getOrCreateResourceFolderPath("recognition", "barcode_properties", "symbology_type");

    // Fixture filenames
    private static final String FILE_C128 = "c128.png";
    private static final String FILE_QR   = "qr.png";
    private static final String FILE_EAN13 = "ean13.png";
    private static final String FILE_DM   = "dm.png";
    private static final String FILE_MIX  = "mix_qr_c128.png";

    @BeforeClass
    public void setUp() throws Exception {
        LicenseAssist.setupLicense();
        generateFixtures();
    }

    // ---------------------------------------------------------------------
    // Tests
    // ---------------------------------------------------------------------

    /**
     * Explicit decode type: recognize Code128 image with DecodeType.CODE_128 only.
     * This is the fastest and safest configuration if you know what you expect.
     */
    @Test
    public void read_Code128_WithExplicitDecodeType() throws Exception {
        BarCodeReader reader = new BarCodeReader(getFullPath(FILE_C128), DecodeType.CODE_128);
        ExampleAssist.assertRecognized(reader, FILE_C128, 1, DecodeType.CODE_128);
    }

    /**
     * Use the "most common" set when the exact symbology is unknown.
     * The engine tries a curated subset of popular types.
     */
    @Test
    public void read_QR_WithMostCommonTypes() throws Exception {
        BarCodeReader reader = new BarCodeReader(getFullPath(FILE_QR), DecodeType.MOST_COMMON_TYPES);
        ExampleAssist.assertRecognized(reader, FILE_QR, 1, DecodeType.QR);
    }

    /**
     * Use "all supported types" when you need the broadest search.
     * This is slower and may return more candidates, but finds rare types too.
     */
    @Test
    public void read_EAN13_WithAllSupportedTypes() throws Exception {
        BarCodeReader reader = new BarCodeReader(getFullPath(FILE_EAN13), DecodeType.ALL_SUPPORTED_TYPES);
        ExampleAssist.assertRecognized(reader, FILE_EAN13, 1, DecodeType.EAN_13);
    }

    /**
     * Recognize a mixed image (QR + Code128) using a custom set (QR, Code128).
     * Demonstrates how to pass multiple explicit symbology types at once.
     */
    @Test
    public void read_Mixed_QR_and_Code128_WithSelectedTypes() throws Exception {
        BarCodeReader reader = new BarCodeReader(getFullPath(FILE_MIX), DecodeType.QR, DecodeType.CODE_128);
        BarCodeResult[] results = reader.readBarCodes();

        // Expect at least two results and both types present
        Assert.assertTrue(results.length >= 2, "Expected at least QR + Code128");
        boolean hasQR = hasDecodeType(results, DecodeType.QR);
        boolean hasC128 = hasDecodeType(results, DecodeType.CODE_128);
        Assert.assertTrue(hasQR && hasC128, "Mixed image must contain QR and Code128");
    }

    /**
     * Filtering demonstration: try to read a Code128 image while allowing only QR.
     * Since the wrong type is selected, recognition should return zero results.
     * This helps reduce false positives when the type is known.
     */
    @Test
    public void read_Code128_FilteredByWrongType_ReturnsZero() throws Exception {
        BarCodeReader reader = new BarCodeReader(getFullPath(FILE_C128), DecodeType.QR);
        ExampleAssist.assertNotRecognized(reader, FILE_C128);
    }

    /**
     * Mixed image again, but restrict recognition to a single type.
     * With Code128 only, the QR on the left is ignored and only Code128 is returned.
     */
    @Test
    public void read_Mixed_RestrictTo_Code128_Only() throws Exception {
        BarCodeReader reader = new BarCodeReader(getFullPath(FILE_MIX), DecodeType.CODE_128);
        BarCodeResult[] results = reader.readBarCodes();
        Assert.assertTrue(results.length >= 1, "Expected at least one Code128 result");
        Assert.assertTrue(hasDecodeType(results, DecodeType.CODE_128), "Expected Code128 in results");
        Assert.assertFalse(hasDecodeType(results, DecodeType.QR), "QR must be ignored when only Code128 is allowed");
    }

    /**
     * Mixed image with the "most common" set.
     * Typically, both QR and Code128 are included in this set, so we expect both results.
     */
    @Test
    public void read_Mixed_WithMostCommonTypes() throws Exception {
        BarCodeReader reader = new BarCodeReader(getFullPath(FILE_MIX), DecodeType.MOST_COMMON_TYPES);
        BarCodeResult[] results = reader.readBarCodes();
        Assert.assertTrue(results.length >= 2, "Expected at least two results on a mixed image");
        Assert.assertTrue(hasDecodeType(results, DecodeType.QR));
        Assert.assertTrue(hasDecodeType(results, DecodeType.CODE_128));
    }

    /**
     * Extra sample: read DataMatrix using an exact type to avoid confusion with other 2D codes.
     */
    @Test
    public void read_DataMatrix_WithExactType() throws Exception {
        BarCodeReader reader = new BarCodeReader(getFullPath(FILE_DM), DecodeType.DATA_MATRIX);
        ExampleAssist.assertRecognized(reader, FILE_DM, 1, DecodeType.DATA_MATRIX);
    }

    // ---------------------------------------------------------------------
    // Fixture generation
    // ---------------------------------------------------------------------

    private void generateFixtures() throws Exception {
        // Code128
        ExampleAssist.checkOrCreateImage(FOLDER, FILE_C128, (String full) -> {
            BarcodeGenerator generator = new BarcodeGenerator(EncodeTypes.CODE_128, "C128-DEMO");
            generator.save(full, BarCodeImageFormat.PNG);
        });

        // QR
        ExampleAssist.checkOrCreateImage(FOLDER, FILE_QR, (String full) -> {
            BarcodeGenerator generator = new BarcodeGenerator(EncodeTypes.QR, "QR-DEMO");
            generator.save(full, BarCodeImageFormat.PNG);
        });

        // EAN-13 (use 12 digits, checksum is calculated automatically by the engine)
        ExampleAssist.checkOrCreateImage(FOLDER, FILE_EAN13, (String full) -> {
            BarcodeGenerator generator = new BarcodeGenerator(EncodeTypes.EAN_13, "590123412345");
            generator.save(full, BarCodeImageFormat.PNG);
        });

        // DataMatrix
        ExampleAssist.checkOrCreateImage(FOLDER, FILE_DM, (String full) -> {
            BarcodeGenerator generator = new BarcodeGenerator(EncodeTypes.DATA_MATRIX, "DM-DEMO");
            generator.save(full, BarCodeImageFormat.PNG);
        });

        // Mixed image: place QR (left) + Code128 (right) onto one canvas
        ExampleAssist.checkOrCreateImage(FOLDER, FILE_MIX, this::generateMixedQrAndCode128);
    }

    /**
     * Builds a single PNG that contains two barcodes side-by-side (QR + Code128).
     * Useful to demonstrate recognition with multiple decode types.
     */
    private void generateMixedQrAndCode128(String outPath) throws IOException
    {
        // Make temp components
        String qrTemp = Paths.get(FOLDER, "_tmp_qr.png").toString();
        String c128Temp = Paths.get(FOLDER, "_tmp_c128.png").toString();

        BarcodeGenerator qrGen = new BarcodeGenerator(EncodeTypes.QR, "QR-MIX");
        qrGen.save(qrTemp, BarCodeImageFormat.PNG);

        BarcodeGenerator c128Gen = new BarcodeGenerator(EncodeTypes.CODE_128, "C128-MIX");
        c128Gen.save(c128Temp, BarCodeImageFormat.PNG);

        BufferedImage qr = ImageIO.read(new File(qrTemp));
        BufferedImage c128 = ImageIO.read(new File(c128Temp));

        int pad = 20;
        int w = qr.getWidth() + c128.getWidth() + pad * 3;
        int h = Math.max(qr.getHeight(), c128.getHeight()) + pad * 2;

        BufferedImage canvas = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = canvas.createGraphics();
        try {
            g.setColor(Color.WHITE);
            g.fillRect(0, 0, w, h);
            g.drawImage(qr, pad, pad, null);
            g.drawImage(c128, pad * 2 + qr.getWidth(), pad, null);
        } finally {
            g.dispose();
        }

        ImageIO.write(canvas, "PNG", new File(outPath));

        // Cleanup temps
        new File(qrTemp).delete();
        new File(c128Temp).delete();
    }

    // ---------------------------------------------------------------------
    // Helpers
    // ---------------------------------------------------------------------

    private static String getFullPath(String file) {
        return ExampleAssist.pathCombine(FOLDER, file);
    }


}
