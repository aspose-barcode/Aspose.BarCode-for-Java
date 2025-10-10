package com.aspose.barcode.guide.quickstart;

import com.aspose.barcode.License;
import com.aspose.barcode.CodeTextAlignment;
import com.aspose.barcode.generation.EncodeTypes;
import com.aspose.barcode.generation.BarcodeGenerator;
import com.aspose.barcode.generation.BarCodeImageFormat;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.*;

/**
 * Quick Generation Examples as TestNG tests.
 * Conforms to user's API classes:
 *  - com.aspose.barcode.generation.EncodeTypes
 *  - com.aspose.barcode.generation.BarcodeGenerator
 *  - setMillimeters(float) uses 'f' suffix
 *  - No use of QRErrorLevel/Pdf417ErrorLevel as per provided sources
 */
public class QuickGenerationExamplesTestNG {

    private Path OUTPUT_DIR;

    @BeforeClass
    public void setUpClass() throws IOException {
        // Create output directory
        OUTPUT_DIR = Paths.get("target", "test-output", "quick-generation");
        Files.createDirectories(OUTPUT_DIR);

        // Optional: apply license if available
        try {
            License lic = new License();
            // lic.setLicense("Aspose.Total.Java.lic");
        } catch (Throwable t) {
            // Proceed without a license (evaluation mode)
        }
    }

    // -------- Linear Barcodes --------

    @Test
    public void generate_Code128() throws Exception {
        BarcodeGenerator gen = new BarcodeGenerator(EncodeTypes.CODE_128, "ABC-12345");
        gen.getParameters().getBarcode().getXDimension().setMillimeters(0.3f);
        gen.getParameters().getBarcode().getBarHeight().setMillimeters(12.0f);
        gen.getParameters().getBarcode().setCodeTextAlignment(CodeTextAlignment.BELOW);
        saveAndAssert(gen, "code128.png");
    }

    @Test
    public void generate_EAN13() throws Exception {
        // 12 or 13 digits; checksum auto-calculated
        BarcodeGenerator gen = new BarcodeGenerator(EncodeTypes.EAN_13, "5901234123457");
        gen.getParameters().getBarcode().getXDimension().setMillimeters(0.33f);
        gen.getParameters().getBarcode().setCodeTextAlignment(CodeTextAlignment.BELOW);
        saveAndAssert(gen, "ean13.png");
    }

    @Test
    public void generate_UPCA() throws Exception {
        // 11 or 12 digits; checksum auto-calculated
        BarcodeGenerator gen = new BarcodeGenerator(EncodeTypes.UPCA, "036000291452");
        gen.getParameters().getBarcode().getXDimension().setMillimeters(0.33f);
        saveAndAssert(gen, "upca.png");
    }

    @Test
    public void generate_ITF14() throws Exception {
        BarcodeGenerator gen = new BarcodeGenerator(EncodeTypes.ITF_14, "1234567890123");
        gen.getParameters().getBarcode().getXDimension().setMillimeters(0.495f);
        saveAndAssert(gen, "itf14.png");
    }

    @Test
    public void generate_Code39() throws Exception {
        BarcodeGenerator gen = new BarcodeGenerator(EncodeTypes.CODE_39, "ASPOSE-123");
        gen.getParameters().getBarcode().getXDimension().setMillimeters(0.4f);
        saveAndAssert(gen, "code39.png");
    }

    // -------- Two-Dimensional Barcodes --------

    @Test
    public void generate_QR() throws Exception {
        BarcodeGenerator gen = new BarcodeGenerator(EncodeTypes.QR, "https://aspose.com/");
        gen.getParameters().getBarcode().getXDimension().setMillimeters(0.5f);
        // Keep default encode/error levels to avoid enum mismatches. EncodeMode AUTO when available:
        try {
            gen.getParameters().getBarcode().getQR().setQrEncodeMode(
                    com.aspose.barcode.generation.QREncodeMode.AUTO);
        } catch (Throwable ignored) { }
        saveAndAssert(gen, "qrcode.png");
    }

    @Test
    public void generate_DataMatrix() throws Exception {
        BarcodeGenerator gen = new BarcodeGenerator(EncodeTypes.DATA_MATRIX, "DMX-INV-000042");
        gen.getParameters().getBarcode().getXDimension().setMillimeters(0.4f);
        try {
            gen.getParameters().getBarcode().getDataMatrix().setDataMatrixEncodeMode(
                    com.aspose.barcode.generation.DataMatrixEncodeMode.AUTO);
        } catch (Throwable ignored) { }
        saveAndAssert(gen, "datamatrix.png");
    }

    @Test
    public void generate_PDF417() throws Exception {
        BarcodeGenerator gen = new BarcodeGenerator(EncodeTypes.PDF_417, "Shipment 42: 10x Boxes");
        gen.getParameters().getBarcode().getXDimension().setMillimeters(0.6f);
        try {
            gen.getParameters().getBarcode().getPdf417().setPdf417CompactionMode(
                    com.aspose.barcode.generation.Pdf417CompactionMode.AUTO);
        } catch (Throwable ignored) { }
        saveAndAssert(gen, "pdf417.png");
    }

    @Test
    public void generate_Aztec() throws Exception {
        BarcodeGenerator gen = new BarcodeGenerator(EncodeTypes.AZTEC, "AZTEC-Order#123");
        gen.getParameters().getBarcode().getXDimension().setMillimeters(0.5f);
        try {
            gen.getParameters().getBarcode().getAztec().setAztecErrorLevel(50); // percent (0..99)
        } catch (Throwable ignored) { }
        saveAndAssert(gen, "aztec.png");
    }

    // -------- Postal Barcodes --------

    @Test
    public void generate_Postnet() throws Exception {
        BarcodeGenerator gen = new BarcodeGenerator(EncodeTypes.POSTNET, "20500-1234");
        gen.getParameters().getBarcode().getXDimension().setMillimeters(0.5f);
        saveAndAssert(gen, "postnet.png");
    }

    @Test
    public void generate_Planet() throws Exception {
        BarcodeGenerator gen = new BarcodeGenerator(EncodeTypes.PLANET, "205001234");
        gen.getParameters().getBarcode().getXDimension().setMillimeters(0.5f);
        saveAndAssert(gen, "planet.png");
    }

    @Test
    public void generate_IntelligentMail() throws Exception {
        BarcodeGenerator gen = new BarcodeGenerator(EncodeTypes.ONE_CODE, "01234567094987654321-01234567891-01234");
        gen.getParameters().getBarcode().getXDimension().setMillimeters(0.5f);
        saveAndAssert(gen, "intelligent-mail.png");
    }

    // -------- GS1 / Complex Barcodes --------

    @Test
    public void generate_GS1_128_with_AIs() throws Exception {
        BarcodeGenerator gen = new BarcodeGenerator(EncodeTypes.GS_1_CODE_128, "(01)09501101530008(17)251231(10)BATCH-42");
        gen.getParameters().getBarcode().getXDimension().setMillimeters(0.33f);
        saveAndAssert(gen, "gs1-128.png");
    }

    // Swiss QR: add when correct EncodeType exists in your build

    // -------- Styling demo --------

    @Test
    public void generate_WithStyling() throws Exception {
        BarcodeGenerator gen = new BarcodeGenerator(EncodeTypes.CODE_128, "Style-Demo");
        gen.getParameters().getBarcode().getXDimension().setMillimeters(0.3f);
        gen.getParameters().getBarcode().setCodeTextAlignment(CodeTextAlignment.BELOW);
        gen.getParameters().getCaptionAbove().setText("Caption Above");
        gen.getParameters().getCaptionAbove().getFont().setSize(10f);
        gen.getParameters().getCaptionAbove().setVisible(true);
        gen.getParameters().setRotationAngle(90.0f);
        saveAndAssert(gen, "styled.png");
    }

    // ---------- Helpers ----------

    private void saveAndAssert(BarcodeGenerator gen, String fileName) throws Exception {
        Path out = OUTPUT_DIR.resolve(fileName);
        gen.save(out.toString(), BarCodeImageFormat.PNG);

        Assert.assertTrue(Files.exists(out), "Output file must exist: " + out);
        Assert.assertTrue(Files.size(out) > 0, "Output file must be non-empty: " + out);
    }
}