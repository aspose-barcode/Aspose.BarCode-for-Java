package com.aspose.barcode.guide.generation.symbology_codetext;

import com.aspose.barcode.barcoderecognition.BarCodeReader;
import com.aspose.barcode.barcoderecognition.BarCodeResult;
import com.aspose.barcode.barcoderecognition.DecodeType;
import com.aspose.barcode.generation.*;
import com.aspose.barcode.guide.common.ExampleAssist;
import com.aspose.barcode.guide.common.LicenseAssist;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.nio.charset.StandardCharsets;
import java.util.List;

import static com.aspose.barcode.guide.common.ExampleAssist.*;

/**
 * This class contains focused examples that demonstrate:
 * 1) Choosing a barcode symbology (EncodeTypes.*)
 * 2) Setting Code Text in different ways (String, String + Charset + BOM, raw byte[])
 * 3) When and why to use ECI for QR to ensure correct interpretation of bytes
 * 4) Basic symbology-specific parameters (QR/DataMatrix/PDF417) to show where they live
 *
 * Each test:
 * - Generates a barcode to a deterministic file path
 * - Uses ExampleAssist utilities: pathCombine(), assertFileCreated(), assertImageHasBarcodes(), exp()
 * - Verifies recognition using DecodeType + expected code text
 */
public class SymbologyAndCodeTextExample {

    // Use a clear, dedicated resource folder for this group of tests
    private static final String FOLDER =
            ExampleAssist.getOrCreateResourceFolderPath("generation", "symbology_and_code_text");

    private static final String FILE_C128_SIMPLE  = "c128_simple.png";
    private static final String FILE_QR_UTF8_BOM  = "qr_utf8_bom.png";
    private static final String FILE_QR_BYTES     = "qr_bytes.png";
    private static final String FILE_QR_PARAMS    = "qr_params.png";
    private static final String FILE_DM_PARAMS    = "dm_params.png";
    private static final String FILE_PDF417_MACRO = "pdf417_macro.png";
    private static final String FILE_GS1_128      = "gs1_128.png";

    @BeforeClass
    public void setUp() throws Exception {
        // Ensure the license is applied for consistent behavior across environments
        LicenseAssist.setupLicense();

        // Also ensure the folder exists (the constant FOLDER above already creates it)
        ExampleAssist.getOrCreateResourceFolderPath("generation", "symbology_and_code_text");
    }

    /**
     * Generates a simple Code 128 barcode with plain String Code Text.
     *
     * <p><b>Purpose:</b> Show the minimal path to create a barcode by specifying symbology and code text.</p>
     * <p><b>Key API:</b> {@link BarcodeGenerator(EncodeTypes, String)},
     * {@link BarcodeGenerator#save(String, BarCodeImageFormat)}.</p>
     * <p><b>Expected:</b> One CODE_128 barcode is detected with text "C128-SIMPLE".</p>
     */
    @Test
    public void generate_Code128_withSimpleText() throws Exception {
        // Choose the symbology and provide Code Text as a simple Java String
        BarcodeGenerator generator = new BarcodeGenerator(EncodeTypes.CODE_128, "C128-SIMPLE");

        // Basic assertion: generator holds the same code text we provided
        Assert.assertEquals(generator.getCodeText(), "C128-SIMPLE");

        // Save to a deterministic location
        String full = pathCombine(FOLDER, FILE_C128_SIMPLE);
        generator.save(full, BarCodeImageFormat.PNG);
        assertFileCreated(full);

        // Validate that the image has exactly one CODE_128 with the expected text
        assertImageHasBarcodes(
                full,
                1,
                List.of(expected(DecodeType.CODE_128, "C128-SIMPLE"))
        );
    }

    /**
     * Generates a QR code from a Unicode string using UTF-8 with BOM and sets ECI to UTF-8.
     *
     * <p><b>Purpose:</b> Demonstrate Code Text via String + Charset + BOM and why ECI helps readers interpret bytes.</p>
     * <p><b>Key API:</b> {@link BarcodeGenerator#setCodeText(String, java.nio.charset.Charset, boolean)}
     * <p><b>Expected:</b> One QR barcode is detected with text "車種名".</p>
     * <p><b>Notes:</b> Some legacy scanners rely on BOM; modern readers prefer ECI.</p>
     */
    @Test
    public void generate_QR_withUtf8BOM() throws Exception {
        // Use QR and pass a Unicode string with explicit UTF-8 and a BOM marker.
        BarcodeGenerator generator = new BarcodeGenerator(EncodeTypes.QR);
        generator.setCodeText("車種名", StandardCharsets.UTF_8, true);

        // Code text should be non-empty
        Assert.assertTrue(generator.getCodeText() != null && !generator.getCodeText().isEmpty());

        // Setting an ECI helps decoders interpret bytes as UTF-8 even if BOM is ignored
        generator.getParameters().getBarcode().getQR().setECIEncoding(ECIEncodings.UTF8);

        // Save & verify
        String full = pathCombine(FOLDER, FILE_QR_UTF8_BOM);
        generator.save(full, BarCodeImageFormat.PNG);
        assertFileCreated(full);

        // Expect: the decoded text exactly matches the original Unicode text
        assertImageHasBarcodes(
                full,
                1,
                List.of(expected(DecodeType.QR, "車種名"))
        );
    }

    /**
     * Generates a QR Code from a raw byte sequence and verifies that the
     * decoded binary payload is identical to the source bytes.
     *
     * <p><b>Purpose:</b> Demonstrate how to pass binary data through
     * {@link BarcodeGenerator#setCodeText(byte[])} without text transcoding.</p>
     *
     * <p><b>Key API:</b> {@link BarcodeGenerator#setCodeText(byte[])},
     * {@link QrParameters#setEncodeMode(QREncodeMode)},
     * {@link BarCodeResult#getCodeBytes()}.</p>
     *
     * <p><b>Expected:</b> One QR Code is detected and its decoded byte sequence
     * exactly matches the supplied binary payload.</p>
     */
    @Test
    public void generate_QR_withRawBytes() throws Exception {
        byte[] payload = {
                0x42, 0x49, 0x4E, 0x41, 0x52, 0x59,
                0x2D,
                0x01, 0x02, 0x03,
                0x7F
        };

        BarcodeGenerator generator =
                new BarcodeGenerator(EncodeTypes.QR);

        generator.setCodeText(payload);

        generator.getParameters()
                .getBarcode()
                .getQR()
                .setEncodeMode(QREncodeMode.BYTES);

        generator.getParameters()
                .getBarcode()
                .getQR()
                .setErrorLevel(QRErrorLevel.LEVEL_M);

        String fullPath = pathCombine(
                FOLDER,
                FILE_QR_BYTES
        );

        generator.save(
                fullPath,
                BarCodeImageFormat.PNG
        );

        assertFileCreated(fullPath);

        assertImageHasBarcodes(
                fullPath,
                1,
                List.of(
                        expected(
                                DecodeType.QR,
                                payload
                        )
                )
        );

        BarCodeReader reader = new BarCodeReader(
                fullPath,
                DecodeType.QR
        );

        BarCodeResult[] results =
                reader.readBarCodes();

        Assert.assertEquals(
                results.length,
                1,
                "Expected exactly one QR Code"
        );

        Assert.assertEquals(
                results[0].getCodeType(),
                DecodeType.QR,
                "Decode type must be QR"
        );

        Assert.assertEquals(
                results[0].getCodeBytes(),
                payload,
                "QR payload bytes must round-trip exactly"
        );
    }


    /**
     * Generates a QR Code from Unicode text using UTF-8 ECI.
     *
     * <p><b>Expected:</b> The decoded text matches the original Unicode string.</p>
     */
    @Test
    public void generate_QR_withUtf8Text() throws Exception {
        String payload = "Hello, 🚀 bytes!";

        BarcodeGenerator generator = new BarcodeGenerator(
                EncodeTypes.QR,
                payload
        );

        generator.getParameters()
                .getBarcode()
                .getQR()
                .setEncodeMode(QREncodeMode.ECI);

        generator.getParameters()
                .getBarcode()
                .getQR()
                .setECIEncoding(ECIEncodings.UTF8);

        String fullPath = pathCombine(
                FOLDER,
                "qr_utf8_text.png"
        );

        generator.save(
                fullPath,
                BarCodeImageFormat.PNG
        );

        assertFileCreated(fullPath);

        assertImageHasBarcodes(
                fullPath,
                1,
                List.of(
                        expected(
                                DecodeType.QR,
                                payload
                        )
                )
        );
    }



    /**
     * Demonstrates QR-specific parameters: error correction level and fixed version.
     *
     * <p><b>Purpose:</b> Show where to configure symbology-specific settings for QR.</p>
     * <p><b>Key API:</b> {@link QrParameters#setErrorLevel(QRErrorLevel)},
     * {@link QrParameters#setVersion(QRVersion)}.</p>
     * <p><b>Expected:</b> One QR is detected with text "QR-PARAMS".</p>
     */
    @Test
    public void generate_QR_withParameters() throws Exception {
        // Show where QR-specific parameters live: error correction level and fixed version
        BarcodeGenerator generator = new BarcodeGenerator(EncodeTypes.QR, "QR-PARAMS");
        generator.getParameters().getBarcode().getQR().setErrorLevel(QRErrorLevel.LEVEL_H);
        generator.getParameters().getBarcode().getQR().setVersion(QRVersion.VERSION_05);

        // Basic assertions
        Assert.assertEquals(
                generator.getParameters().getBarcode().getQR().getQrErrorLevel(),
                QRErrorLevel.LEVEL_H
        );
        Assert.assertEquals(
                generator.getParameters().getBarcode().getQR().getQrVersion(),
                QRVersion.VERSION_05
        );

        // Save & verify
        String full = pathCombine(FOLDER, FILE_QR_PARAMS);
        generator.save(full, BarCodeImageFormat.PNG);
        assertFileCreated(full);

        assertImageHasBarcodes(
                full,
                1,
                List.of(expected(DecodeType.QR, "QR-PARAMS"))
        );
    }

    /**
     * Demonstrates DataMatrix-specific parameters: ECC 200 and encode mode.
     *
     * <p><b>Purpose:</b> Show where to configure DataMatrix ECC and encode mode.</p>
     * <p><b>Key API:</b> {@link DataMatrixParameters#setDataMatrixEcc(DataMatrixEccType)},
     * {@link DataMatrixParameters#setDataMatrixEncodeMode(DataMatrixEncodeMode)}.</p>
     * <p><b>Expected:</b> One DATA_MATRIX is detected with text "DM-PARAMS".</p>
     */
    @Test
    public void generate_DataMatrix_withParameters() throws Exception {
        // Show DataMatrix ECC and encode mode locations
        BarcodeGenerator generator = new BarcodeGenerator(EncodeTypes.DATA_MATRIX, "DM-PARAMS");
        generator.getParameters().getBarcode().getDataMatrix().setDataMatrixEcc(DataMatrixEccType.ECC_200);
        generator.getParameters().getBarcode().getDataMatrix().setDataMatrixEncodeMode(DataMatrixEncodeMode.AUTO);
        generator.getParameters().getBarcode().getDataMatrix().setReaderProgramming(false);

        // Basic assertion
        Assert.assertEquals(
                generator.getParameters().getBarcode().getDataMatrix().getDataMatrixEcc(),
                DataMatrixEccType.ECC_200
        );

        // Save & verify
        String full = pathCombine(FOLDER, FILE_DM_PARAMS);
        generator.save(full, BarCodeImageFormat.PNG);
        assertFileCreated(full);

        assertImageHasBarcodes(
                full,
                1,
                List.of(expected(DecodeType.DATA_MATRIX, "DM-PARAMS"))
        );
    }

    /**
     * Demonstrates PDF417 grid parameters (rows/columns) and Macro PDF417 fields.
     *
     * <p><b>Purpose:</b> Show structure control (rows/columns) and macro-segmentation metadata.</p>
     * <p><b>Key API:</b> {@link Pdf417Parameters#setRows(int)}, {@link Pdf417Parameters#setColumns(int)},
     * {@link Pdf417Parameters#setPdf417MacroFileID(int)},
     * {@link Pdf417Parameters#setPdf417MacroSegmentsCount(int)},
     * {@link Pdf417Parameters#setPdf417MacroSegmentID(int)}.</p>
     * <p><b>Expected:</b> One MACRO_PDF_417 barcode is detected with text "PDF417-MACRO".</p>
     */
    @Test
    public void generate_Pdf417_withRowsColumnsAndMacro() throws Exception {
        // Demonstrate PDF417 grid params and Macro PDF417 fields
        BarcodeGenerator generator = new BarcodeGenerator(EncodeTypes.MACRO_PDF_417, "PDF417-MACRO");
        generator.getParameters().getBarcode().getPdf417().setRows(6);
        generator.getParameters().getBarcode().getPdf417().setColumns(5);

        // Macro fields to identify segments and file grouping
        generator.getParameters().getBarcode().getPdf417().setPdf417MacroFileID(15900);
        generator.getParameters().getBarcode().getPdf417().setPdf417MacroSegmentsCount(3);
        generator.getParameters().getBarcode().getPdf417().setPdf417MacroSegmentID(1);

        // Basic assertions
        Assert.assertEquals(generator.getParameters().getBarcode().getPdf417().getRows(), 6);
        Assert.assertEquals(generator.getParameters().getBarcode().getPdf417().getColumns(), 5);

        // Save & verify
        String full = pathCombine(FOLDER, FILE_PDF417_MACRO);
        generator.save(full, BarCodeImageFormat.PNG);
        assertFileCreated(full);

        assertImageHasBarcodes(
                full,
                1,
                List.of(expected(DecodeType.MACRO_PDF_417, "PDF417-MACRO"))
        );
    }

    /**
     * Generates a GS1 Code 128 (FNC1) barcode using AI syntax.
     *
     * <p><b>Purpose:</b> Show how to encode structured GS1 data (GTIN, Batch/Lot, Expiration) via AIs.</p>
     * <p><b>Key API:</b> {@link BarcodeGenerator(EncodeTypes, String)}
     * with {@link EncodeTypes#GS_1_CODE_128} and AI-formatted string.</p>
     * <p><b>Expected:</b> One GS_1_CODE_128 barcode is detected with the same AI string.</p>
     */
    @Test
    public void generate_GS1_Code128_withAIs() throws Exception {
        // GS1 Code 128 uses FNC1 and Application Identifiers (AI) to structure data.
        // This example sets a common set of AIs (01 - GTIN, 10 - Batch/Lot, 17 - Expiration)
        BarcodeGenerator generator = new BarcodeGenerator(
                EncodeTypes.GS_1_CODE_128,
                "(01)03453120000011(10)ABC123(17)251231"
        );

        // Save & verify
        String full = pathCombine(FOLDER, FILE_GS1_128);
        generator.save(full, BarCodeImageFormat.PNG);
        assertFileCreated(full);

        assertImageHasBarcodes(
                full,
                1,
                List.of(expected(DecodeType.GS_1_CODE_128, "(01)03453120000011(10)ABC123(17)251231"))
        );
    }
}
