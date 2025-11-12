package com.aspose.barcode.guide.generation;

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

public class SymbologyAndCodeTextExample
{

    private static final String FOLDER =
            ExampleAssist.getOrCreateResourceFolderPath("generation", "parameters", "set_text");

    private static final String FILE_C128_SIMPLE       = "c128_simple.png";
    private static final String FILE_QR_UTF8_BOM       = "qr_utf8_bom.png";
    private static final String FILE_QR_BYTES          = "qr_bytes.png";
    private static final String FILE_C128_SIZED_COLORED= "c128_sized_colored.png";
    private static final String FILE_EAN13_ROTATED     = "ean13_rotated.png";
    private static final String FILE_QR_PARAMS         = "qr_params.png";
    private static final String FILE_DM_PARAMS         = "dm_params.png";
    private static final String FILE_PDF417_MACRO      = "pdf417_macro.png";
    private static final String FILE_GS1_128           = "gs1_128.png";

    @BeforeClass
    public void setUp() throws Exception {
        LicenseAssist.setupLicense();
        ExampleAssist.getOrCreateResourceFolderPath("generation", "parameters_and_text");
    }

    // --- 1) setCodeText(String) for Code128 ---
    @Test
    public void generate_Code128_withSimpleText() throws Exception {
        BarcodeGenerator generator = new BarcodeGenerator(EncodeTypes.CODE_128, "C128-SIMPLE");
        Assert.assertEquals(generator.getCodeText(), "C128-SIMPLE");
        String full = ExampleAssist.pathCombine(FOLDER, FILE_C128_SIMPLE);
        generator.save(full, BarCodeImageFormat.PNG);
        assertFileCreated(full);

        assertImageHasBarcodes(
                full,
                1,
                List.of(exp(DecodeType.CODE_128, "C128-SIMPLE"))
        );
    }

    // --- 2) setCodeText(String, Charset, insertBOM=true) for QR (UTF-8 with BOM) ---
    @Test
    public void generate_QR_withUtf8BOM() throws Exception {
        BarcodeGenerator generator = new BarcodeGenerator(EncodeTypes.QR);
        generator.setCodeText("車種名", StandardCharsets.UTF_8, true);
        Assert.assertTrue(generator.getCodeText() != null && !generator.getCodeText().isEmpty());
        generator.getParameters().getBarcode().getQR().setQrECIEncoding(ECIEncodings.UTF8);

        String full = ExampleAssist.pathCombine(FOLDER, FILE_QR_UTF8_BOM);
        generator.save(full, BarCodeImageFormat.PNG);
        assertFileCreated(full);

        assertImageHasBarcodes(
                full,
                1,
                List.of(exp(DecodeType.QR, "車種名"))
        );
    }

    // --- 3) setCodeText(byte[]) for QR (binary payload) ---
    @Test
    public void generate_QR_withRawBytes() throws Exception {
        // Prepare UTF-8 payload with an emoji
        byte[] payload = "Hello, \uD83D\uDE80 bytes!".getBytes(StandardCharsets.UTF_8);

        // Generate a QR with raw bytes
        BarcodeGenerator generator = new BarcodeGenerator(EncodeTypes.QR);
        generator.setCodeText(payload);

        // Critical: mark ECI as UTF-8 so readers interpret bytes correctly
        generator.getParameters().getBarcode().getQR().setQrECIEncoding(ECIEncodings.UTF8);
        generator.getParameters().getBarcode().getQR().setQrErrorLevel(QRErrorLevel.LEVEL_M);

        String full = ExampleAssist.pathCombine(FOLDER, FILE_QR_BYTES);
        generator.save(full, BarCodeImageFormat.PNG);
        assertFileCreated(full);

        // Expected human-readable text decoded from our bytes
        String expectedTextUtf8 = new String(payload, StandardCharsets.UTF_8);

        // Validate that the generated image contains exactly one QR with the expected text
        assertImageHasBarcodes(full,1, List.of(exp(DecodeType.QR, expectedTextUtf8))
        );

        // Round-trip check: decoded raw bytes must match exactly the original payload
        BarCodeReader reader = new BarCodeReader(full, DecodeType.QR);
        BarCodeResult[] results = reader.readBarCodes();
        Assert.assertTrue(results.length >= 1, "Expected at least 1 QR");
        byte[] readBytes = results[0].getCodeBytes();
        Assert.assertEquals(readBytes, payload, "QR payload bytes must round-trip exactly");
    }

    // --- 6) Symbology-specific: QR parameters (error level + version) ---
    @Test
    public void generate_QR_withParameters() throws Exception {
        BarcodeGenerator generator = new BarcodeGenerator(EncodeTypes.QR, "QR-PARAMS");
        generator.getParameters().getBarcode().getQR().setQrErrorLevel(QRErrorLevel.LEVEL_H);
        generator.getParameters().getBarcode().getQR().setQrVersion(QRVersion.VERSION_05);
        Assert.assertEquals(generator.getParameters().getBarcode().getQR().getQrErrorLevel(), QRErrorLevel.LEVEL_H);
        Assert.assertEquals(generator.getParameters().getBarcode().getQR().getQrVersion(), QRVersion.VERSION_05);

        String full = ExampleAssist.pathCombine(FOLDER, FILE_QR_PARAMS);
        generator.save(full, BarCodeImageFormat.PNG);
        assertFileCreated(full);

        assertImageHasBarcodes(
                full,
                1,
                List.of(exp(DecodeType.QR, "QR-PARAMS"))
        );
    }

    // --- 7) Symbology-specific: DataMatrix parameters (ECC / encode mode) ---
    @Test
    public void generate_DataMatrix_withParameters() throws Exception {
        BarcodeGenerator generator = new BarcodeGenerator(EncodeTypes.DATA_MATRIX, "DM-PARAMS");
        generator.getParameters().getBarcode().getDataMatrix().setDataMatrixEcc(DataMatrixEccType.ECC_200);
        generator.getParameters().getBarcode().getDataMatrix().setDataMatrixEncodeMode(DataMatrixEncodeMode.AUTO);
        generator.getParameters().getBarcode().getDataMatrix().setReaderProgramming(false);
        Assert.assertEquals(generator.getParameters().getBarcode().getDataMatrix().getDataMatrixEcc(), DataMatrixEccType.ECC_200);

        String full = ExampleAssist.pathCombine(FOLDER, FILE_DM_PARAMS);
        generator.save(full, BarCodeImageFormat.PNG);
        assertFileCreated(full);

        assertImageHasBarcodes(
                full,
                1,
                List.of(exp(DecodeType.DATA_MATRIX, "DM-PARAMS"))
        );
    }

    // --- 8) Symbology-specific: PDF417 structure + Macro fields ---
    @Test
    public void generate_Pdf417_withRowsColumnsAndMacro() throws Exception {
        BarcodeGenerator generator = new BarcodeGenerator(EncodeTypes.MACRO_PDF_417, "PDF417-MACRO");
        generator.getParameters().getBarcode().getPdf417().setRows(6);
        generator.getParameters().getBarcode().getPdf417().setColumns(5);
        generator.getParameters().getBarcode().getPdf417().setPdf417MacroFileID(15900);
        generator.getParameters().getBarcode().getPdf417().setPdf417MacroSegmentsCount(3);
        generator.getParameters().getBarcode().getPdf417().setPdf417MacroSegmentID(1);
        Assert.assertEquals(generator.getParameters().getBarcode().getPdf417().getRows(), 6);
        Assert.assertEquals(generator.getParameters().getBarcode().getPdf417().getColumns(), 5);

        String full = ExampleAssist.pathCombine(FOLDER, FILE_PDF417_MACRO);
        generator.save(full, BarCodeImageFormat.PNG);
        assertFileCreated(full);

        assertImageHasBarcodes(
                full,
                1,
                List.of(exp(DecodeType.MACRO_PDF_417, "PDF417-MACRO"))
        );
    }

    // --- 9) GS1 Code 128 (FNC1) with AI syntax ---
    @Test
    public void generate_GS1_Code128_withAIs() throws Exception {
        BarcodeGenerator generator = new BarcodeGenerator(
                EncodeTypes.GS_1_CODE_128,
                "(01)03453120000011(10)ABC123(17)251231");

        String full = ExampleAssist.pathCombine(FOLDER, FILE_GS1_128);
        generator.save(full, BarCodeImageFormat.PNG);
        assertFileCreated(full);

        assertImageHasBarcodes(
                full,
                1,
                List.of(exp(DecodeType.GS_1_CODE_128, "(01)03453120000011(10)ABC123(17)251231"))
        );
    }

}
