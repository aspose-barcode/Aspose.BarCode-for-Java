package com.aspose.barcode.guide.generation;

import com.aspose.barcode.barcoderecognition.*;
import com.aspose.barcode.generation.*;
import com.aspose.barcode.guide.common.ExampleAssist;
import com.aspose.barcode.guide.common.LicenseAssist;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.awt.*;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class ApplyParametersAndTextExample
{

    private static final String FOLDER =
            ExampleAssist.getOrCreateResourceFolderPath("generation", "parameters_and_text");

    private static final String FILE_C128_SIMPLE = "c128_simple.png";
    private static final String FILE_QR_UTF8_BOM = "qr_utf8_bom.png";
    private static final String FILE_QR_BYTES = "qr_bytes.png";
    private static final String FILE_C128_SIZED_COLORED = "c128_sized_colored.png";
    private static final String FILE_EAN13_ROTATED = "ean13_rotated.png";
    private static final String FILE_QR_PARAMS = "qr_params.png";
    private static final String FILE_DM_PARAMS = "dm_params.png";
    private static final String FILE_PDF417_STRUCT = "pdf417_struct.png";
    private static final String FILE_GS1_128 = "gs1_128.png";

    @BeforeClass
    public void setUp() throws Exception
    {
        LicenseAssist.setupLicense();
        generateFixtures();
    }

    // --- 1) Simple setCodeText(String) for Code128 ---
    @Test
    public void generate_Code128_withSimpleText() throws Exception
    {
        String fullPath = ExampleAssist.pathCombine(FOLDER, FILE_C128_SIMPLE);
        BarCodeReader barCodeReader = new BarCodeReader(fullPath, DecodeType.CODE_128);

        // Use helper to assert exactly 1 result and expected text
        ExampleAssist.assertRecognizedWithText(barCodeReader, "C128 simple", 1, "C128-SIMPLE");

        // Confidence is a soft check: expected to be in [0..100]
        BarCodeResult result = barCodeReader.readBarCodes()[0];
        int confidence = result.getConfidence();
        Assert.assertTrue(confidence >= 0 && confidence <= 100, "Confidence out of range: " + confidence);
    }

    // --- 2) setCodeText(String, Charset, insertBOM=true) for QR (UTF-8 with BOM) ---
    @Test
    public void generate_QR_withUtf8BOM() throws Exception
    {
        String fullPath = ExampleAssist.pathCombine(FOLDER, FILE_QR_UTF8_BOM);
        BarCodeReader barCodeReader = new BarCodeReader(fullPath, DecodeType.QR);

        ExampleAssist.assertRecognizedWithText(barCodeReader, "QR UTF8 BOM", 1, "車種名");

        BarCodeResult result = barCodeReader.readBarCodes()[0];
        int confidence = result.getConfidence();
        Assert.assertTrue(confidence >= 0 && confidence <= 100, "Confidence out of range: " + confidence);

        // Extended parameters sanity (not strict)
        QRExtendedParameters qrExtendedParameters = result.getExtended().getQR();
        Assert.assertNotNull(qrExtendedParameters);
    }

    // --- 3) setCodeText(byte[]) for QR + round-trip of code bytes ---
    @Test
    public void generate_QR_withRawBytes_roundTrip() throws Exception
    {
        String fullPath = ExampleAssist.pathCombine(FOLDER, FILE_QR_BYTES);
        BarCodeReader barCodeReader = new BarCodeReader(fullPath, DecodeType.QR);

        // At least one result and correct type
        ExampleAssist.assertRecognized(barCodeReader, "QR bytes", 1, DecodeType.QR);

        BarCodeResult result = barCodeReader.readBarCodes()[0];
        byte[] decoded = result.getCodeBytes();

        // Round-trip: compare with original payload
        byte[] original = "Hello, \uD83D\uDE80 bytes!".getBytes(StandardCharsets.UTF_8);
        Assert.assertTrue(Arrays.equals(decoded, original),
                "Decoded bytes must match the original payload");

        // Also check text equality via charset (reader returns text string)
        String decodedText = result.getCodeText();
        Assert.assertEquals(decodedText, new String(original, StandardCharsets.UTF_8));

        int confidence = result.getConfidence();
        Assert.assertTrue(confidence >= 0 && confidence <= 100, "Confidence out of range: " + confidence);
    }

    // --- 4) Global image & barcode metrics: size, X-dimension, bar height, colors & padding ---
    @Test
    public void generate_Code128_withSizeColorAndPadding() throws Exception
    {
        String fullPath = ExampleAssist.pathCombine(FOLDER, FILE_C128_SIZED_COLORED);
        BarCodeReader barCodeReader = new BarCodeReader(fullPath, DecodeType.CODE_128);

        ExampleAssist.assertRecognizedWithText(barCodeReader, "C128 sized/colored", 1, "SIZE-COLOR-PADDING");

        BarCodeResult result = barCodeReader.readBarCodes()[0];
        Assert.assertTrue(result.getConfidence() >= 0 && result.getConfidence() <= 100);
    }

    // --- 5) Rotation & quiet zones example on EAN-13 ---
    @Test
    public void generate_EAN13_rotated() throws Exception
    {
        String fullPath = ExampleAssist.pathCombine(FOLDER, FILE_EAN13_ROTATED);
        BarCodeReader barCodeReader = new BarCodeReader(fullPath, DecodeType.EAN_13);

        ExampleAssist.assertRecognizedWithText(barCodeReader, "EAN13 rotated", 1, "5901234123457");

        BarCodeResult result = barCodeReader.readBarCodes()[0];
        Assert.assertTrue(result.getConfidence() >= 0 && result.getConfidence() <= 100);
    }

    // --- 6) Symbology-specific: QR parameters (Error Level, Version) ---
    @Test
    public void generate_QR_withParameters() throws Exception
    {
        String fullPath = ExampleAssist.pathCombine(FOLDER, FILE_QR_PARAMS);
        BarCodeReader barCodeReader = new BarCodeReader(fullPath, DecodeType.QR);

        ExampleAssist.assertRecognizedWithText(barCodeReader, "QR params", 1, "QR-PARAMS");

        BarCodeResult result = barCodeReader.readBarCodes()[0];
        QRExtendedParameters qr = result.getExtended().getQR();
        Assert.assertNotNull(qr);
        Assert.assertTrue(result.getConfidence() >= 0 && result.getConfidence() <= 100);
    }

    // --- 7) Symbology-specific: DataMatrix parameters (ECC, encode mode) ---
    @Test
    public void generate_DataMatrix_withParameters() throws Exception
    {
        String fullPath = ExampleAssist.pathCombine(FOLDER, FILE_DM_PARAMS);
        BarCodeReader barCodeReader = new BarCodeReader(fullPath, DecodeType.DATA_MATRIX);

        ExampleAssist.assertRecognizedWithText(barCodeReader, "DM params", 1, "DM-PARAMS");

        BarCodeResult result = barCodeReader.readBarCodes()[0];
        Assert.assertTrue(result.getConfidence() >= 0 && result.getConfidence() <= 100);
    }

    // --- 8) Symbology-specific: PDF417 structure (rows/columns) ---
    @Test
    public void generate_Pdf417_withRowsAndColumns() throws Exception
    {
        String fullPath = ExampleAssist.pathCombine(FOLDER, FILE_PDF417_STRUCT);
        BarCodeReader barCodeReader = new BarCodeReader(fullPath, DecodeType.PDF_417);

        ExampleAssist.assertRecognizedWithText(barCodeReader, "PDF417 struct", 1, "PDF417-ROWSxCOLS");

        BarCodeResult result = barCodeReader.readBarCodes()[0];
        Assert.assertTrue(result.getConfidence() >= 0 && result.getConfidence() <= 100);
    }

    // --- 9) GS1 Code 128 (FNC1) with AI syntax ---
    @Test
    public void generate_GS1_Code128_withAIs() throws Exception
    {
        String fullPath = ExampleAssist.pathCombine(FOLDER, FILE_GS1_128);
        BarCodeReader barCodeReader = new BarCodeReader(fullPath, DecodeType.GS_1_CODE_128);

        ExampleAssist.assertRecognizedWithText(
                barCodeReader,
                "GS1-128",
                1,
                "(01)03453120000011(10)ABC123(17)251231"
        );

        BarCodeResult result = barCodeReader.readBarCodes()[0];
        Assert.assertTrue(result.getConfidence() >= 0 && result.getConfidence() <= 100);
    }

    // ------------------------------------------------------------
    // Fixtures
    // ------------------------------------------------------------
    private void generateFixtures() throws Exception
    {

        // (1) Code128 simple text
        ExampleAssist.checkOrCreateImage(FOLDER, FILE_C128_SIMPLE, (String full) -> {
            BarcodeGenerator generator = new BarcodeGenerator(EncodeTypes.CODE_128, "C128-SIMPLE");
            generator.save(full, BarCodeImageFormat.PNG);
        });

        // (2) QR UTF-8 with BOM
        ExampleAssist.checkOrCreateImage(FOLDER, FILE_QR_UTF8_BOM, (String full) -> {
            BarcodeGenerator generator = new BarcodeGenerator(EncodeTypes.QR);
            generator.setCodeText("車種名", StandardCharsets.UTF_8, true);
            generator.save(full, BarCodeImageFormat.PNG);
        });

        // (3) QR bytes (round-trip)
        ExampleAssist.checkOrCreateImage(FOLDER, FILE_QR_BYTES, (String full) -> {
            byte[] payload = "Hello, \uD83D\uDE80 bytes!".getBytes(StandardCharsets.UTF_8);
            BarcodeGenerator generator = new BarcodeGenerator(EncodeTypes.QR);
            generator.setCodeText(payload); // uses byte[] overload
            generator.save(full, BarCodeImageFormat.PNG);
        });

        // (4) Code128 with size/color/padding
        ExampleAssist.checkOrCreateImage(FOLDER, FILE_C128_SIZED_COLORED, (String full) -> {
            BarcodeGenerator generator = new BarcodeGenerator(EncodeTypes.CODE_128, "SIZE-COLOR-PADDING");

            // Image size in pixels
            generator.getParameters().getImageWidth().setPixels(600);
            generator.getParameters().getImageHeight().setPixels(200);

            // Module width & bar height in pixels
            generator.getParameters().getBarcode().getXDimension().setPixels(2.0f);
            generator.getParameters().getBarcode().getBarHeight().setPixels(120);

            // Colors and quiet zones
            generator.getParameters().setBackColor(Color.WHITE);
            generator.getParameters().getBarcode().setBarColor(Color.BLACK);
            generator.getParameters().getBarcode().getPadding().getLeft().setPixels(20);
            generator.getParameters().getBarcode().getPadding().getRight().setPixels(20);
            generator.getParameters().getBarcode().getPadding().getTop().setPixels(10);
            generator.getParameters().getBarcode().getPadding().getBottom().setPixels(10);

            generator.save(full, BarCodeImageFormat.PNG);
        });

        // (5) EAN-13 rotated with explicit quiet zones
        ExampleAssist.checkOrCreateImage(FOLDER, FILE_EAN13_ROTATED, (String full) -> {
            BarcodeGenerator generator = new BarcodeGenerator(EncodeTypes.EAN_13, "5901234123457");
            generator.getParameters().setRotationAngle(90); // degrees
            generator.getParameters().getBarcode().getPadding().getLeft().setPixels(12);
            generator.getParameters().getBarcode().getPadding().getRight().setPixels(12);
            generator.save(full, BarCodeImageFormat.PNG);
        });

        // (6) QR parameters (error level + version)
        ExampleAssist.checkOrCreateImage(FOLDER, FILE_QR_PARAMS, (String full) -> {
            BarcodeGenerator generator = new BarcodeGenerator(EncodeTypes.QR, "QR-PARAMS");
            generator.getParameters().getBarcode().getQR().setQrErrorLevel(QRErrorLevel.LEVEL_M);
            generator.getParameters().getBarcode().getQR().setQrVersion(QRVersion.VERSION_05);
            generator.save(full, BarCodeImageFormat.PNG);
        });

        // (7) DataMatrix parameters (ECC / encode mode)
        ExampleAssist.checkOrCreateImage(FOLDER, FILE_DM_PARAMS, (String full) -> {
            BarcodeGenerator generator = new BarcodeGenerator(EncodeTypes.DATA_MATRIX, "DM-PARAMS");
            generator.getParameters().getBarcode().getDataMatrix().setDataMatrixEcc(DataMatrixEccType.ECC_200);
            generator.getParameters().getBarcode().getDataMatrix().setDataMatrixEncodeMode(DataMatrixEncodeMode.AUTO);
            generator.save(full, BarCodeImageFormat.PNG);
        });

        // (8) PDF417 rows/columns structure
        ExampleAssist.checkOrCreateImage(FOLDER, FILE_PDF417_STRUCT, (String full) -> {
            BarcodeGenerator generator = new BarcodeGenerator(EncodeTypes.PDF_417, "PDF417-ROWSxCOLS");
            generator.getParameters().getBarcode().getPdf417().setRows(6);
            generator.getParameters().getBarcode().getPdf417().setColumns(5);
            generator.save(full, BarCodeImageFormat.PNG);
        });

        // (9) GS1-128 (FNC1) with Application Identifiers
        ExampleAssist.checkOrCreateImage(FOLDER, FILE_GS1_128, (String full) -> {
            BarcodeGenerator generator = new BarcodeGenerator(
                    EncodeTypes.GS_1_CODE_128,
                    "(01)03453120000011(10)ABC123(17)251231");
            generator.save(full, BarCodeImageFormat.PNG);
        });
    }
}
