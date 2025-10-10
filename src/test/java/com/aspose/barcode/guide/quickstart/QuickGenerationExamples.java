package com.aspose.barcode.guide.quickstart;

import com.aspose.barcode.License;
import com.aspose.barcode.generation.*;

import com.aspose.barcode.guide.common.ExampleAssist;
import com.aspose.barcode.guide.common.LicenseAssist;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.nio.file.*;

/**
 * Quick Generation Examples as TestNG tests.
 * Uses only APIs validated against provided sources.
 */
public class QuickGenerationExamples
{

    private static final String folder = ExampleAssist.getResourceFolderPath("quick_start");

    @BeforeClass
    public void setUp()
    {
        LicenseAssist.setupLicense();
    }

    // -------- Linear Barcodes --------

    @Test
    public void generate_Code128() throws Exception
    {
        BarcodeGenerator gen = new BarcodeGenerator(EncodeTypes.CODE_128, "ABC-12345");
        gen.getParameters().getBarcode().getXDimension().setMillimeters(0.3f);
        gen.getParameters().getBarcode().getBarHeight().setMillimeters(10f);
        gen.getParameters().getBarcode().getBarWidthReduction().setMillimeters(0.1f);
        saveAndAssert(gen, "code128.png");
    }

    @Test
    public void generate_EAN13() throws Exception
    {
        BarcodeGenerator gen = new BarcodeGenerator(EncodeTypes.EAN_13, "5901234123457");
        gen.getParameters().getBarcode().getXDimension().setMillimeters(0.33f);
        saveAndAssert(gen, "ean13.png");
    }

    @Test
    public void generate_UPCA() throws Exception
    {
        BarcodeGenerator gen = new BarcodeGenerator(EncodeTypes.UPCA, "036000291452");
        gen.getParameters().getBarcode().getXDimension().setMillimeters(0.33f);
        saveAndAssert(gen, "upca.png");
    }

    @Test
    public void generate_ITF14() throws Exception
    {
        BarcodeGenerator gen = new BarcodeGenerator(EncodeTypes.ITF_14, "1234567890123");
        gen.getParameters().getBarcode().getXDimension().setMillimeters(0.495f);
        saveAndAssert(gen, "itf14.png");
    }

    @Test
    public void generate_Code39() throws Exception
    {
        BarcodeGenerator gen = new BarcodeGenerator(EncodeTypes.CODE_39, "ASPOSE-123");
        gen.getParameters().getBarcode().getXDimension().setMillimeters(0.4f);
        gen.getParameters().setRotationAngle(10f);
        gen.getParameters().getBarcode().getCodeTextParameters().setLocation(CodeLocation.ABOVE);
        saveAndAssert(gen, "code39.png");
    }

    // -------- Two-Dimensional Barcodes --------

    @Test
    public void generate_QR() throws Exception
    {
        BarcodeGenerator gen = new BarcodeGenerator(EncodeTypes.QR, "データ");
        gen.getParameters().getBarcode().getQR().setQrEncodeMode(QREncodeMode.ECI);
        gen.getParameters().getBarcode().getQR().setQrECIEncoding(ECIEncodings.UTF8);
        gen.getParameters().getBarcode().getXDimension().setMillimeters(0.5f);
        saveAndAssert(gen, "qrcode.png");
    }

    @Test
    public void generate_DataMatrix() throws Exception
    {
        BarcodeGenerator gen = new BarcodeGenerator(EncodeTypes.DATA_MATRIX, "DMX-INV-000042");
        gen.getParameters().getBarcode().getXDimension().setMillimeters(0.4f);
        gen.getParameters().getBarcode().getXDimension().setPixels(4);
        gen.getParameters().setUseAntiAlias(true);
        gen.getParameters().setRotationAngle(15f);
        gen.getParameters().getBarcode().getCodeTextParameters().setLocation(CodeLocation.BELOW);
        gen.getParameters().getBarcode().getDataMatrix().setDataMatrixEncodeMode(
                com.aspose.barcode.generation.DataMatrixEncodeMode.AUTO);
        saveAndAssert(gen, "datamatrix.png");
    }

    @Test
    public void generate_PDF417() throws Exception
    {
        BarcodeGenerator gen = new BarcodeGenerator(EncodeTypes.PDF_417, "Shipment 42: 10x Boxes");
        gen.getParameters().getBarcode().getXDimension().setMillimeters(0.6f);
        gen.getParameters().getBarcode().getPdf417().setColumns(3);
        gen.getParameters().getBarcode().getPdf417().setPdf417Truncate(true);
        gen.getParameters().getBarcode().getXDimension().setPixels(2);
        gen.getParameters().getBarcode().getPadding().getLeft().setPixels(5);
        gen.getParameters().getBarcode().getPadding().getRight().setPixels(5);

        saveAndAssert(gen, "pdf417.png");
    }

    @Test
    public void generate_Aztec() throws Exception
    {
        BarcodeGenerator gen = new BarcodeGenerator(EncodeTypes.AZTEC, "AZTEC-Order#123");
        gen.getParameters().getBarcode().getXDimension().setMillimeters(0.5f);
        gen.getParameters().getBarcode().getAztec().setAztecErrorLevel(50);
        saveAndAssert(gen, "aztec.png");
    }

    // -------- Postal Barcodes --------

    @Test
    public void generate_Postnet() throws Exception
    {
        // ZIP+4 (9 digits)
        BarcodeGenerator gen = new BarcodeGenerator(EncodeTypes.POSTNET, "205001234");
        gen.getParameters().getBarcode().getXDimension().setMillimeters(0.5f);
        saveAndAssert(gen, "postnet.png");
    }


    @Test
    public void generate_Planet() throws Exception
    {
        BarcodeGenerator gen = new BarcodeGenerator(EncodeTypes.PLANET, "205001234");
        gen.getParameters().getBarcode().getXDimension().setMillimeters(0.5f);
        saveAndAssert(gen, "planet.png");
    }

    @Test
    public void generate_IntelligentMail() throws Exception
    {
        // 20 (tracking) + 11 (routing) = 31 digits — valid
        String imb = "01234567094987654321" + "01234567891"; // 31 digits
        BarcodeGenerator gen = new BarcodeGenerator(EncodeTypes.ONE_CODE, imb);
        gen.getParameters().getBarcode().getXDimension().setMillimeters(0.5f);
        saveAndAssert(gen, "intelligent-mail.png");
    }


    // -------- GS1 / Complex Barcodes --------

    @Test
    public void generate_GS1_128_with_AIs() throws Exception
    {
        BarcodeGenerator gen = new BarcodeGenerator(EncodeTypes.GS_1_CODE_128, "(01)09501101530008(17)251231(10)BATCH-42");
        gen.getParameters().getBarcode().getXDimension().setMillimeters(0.33f);
        saveAndAssert(gen, "gs1-128.png");
    }

    private void saveAndAssert(BarcodeGenerator gen, String fileName) throws Exception
    {
        Path out = Paths.get(folder).resolve(fileName);
        gen.save(out.toString(), BarCodeImageFormat.PNG);

        Assert.assertTrue(Files.exists(out), "Output file must exist: " + out);
        Assert.assertTrue(Files.size(out) > 0, "Output file must be non-empty: " + out);
    }
}