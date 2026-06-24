package com.aspose.barcode.guide.complex.supported_types.royal_mail_mailmark;

import com.aspose.barcode.barcoderecognition.BarCodeReader;
import com.aspose.barcode.barcoderecognition.BarCodeResult;
import com.aspose.barcode.barcoderecognition.DecodeType;
import com.aspose.barcode.complexbarcode.ComplexBarcodeGenerator;
import com.aspose.barcode.complexbarcode.ComplexCodetextReader;
import com.aspose.barcode.complexbarcode.Mailmark2DCodetext;
import com.aspose.barcode.complexbarcode.Mailmark2DType;
import com.aspose.barcode.complexbarcode.MailmarkCodetext;
import com.aspose.barcode.generation.BarCodeImageFormat;
import com.aspose.barcode.guide.common.ExampleAssist;
import com.aspose.barcode.guide.common.LicenseAssist;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * Demonstrates generation and recognition of Royal Mail Mailmark 1D and 2D
 * barcodes with Aspose.BarCode for Java.
 *
 * <p>The examples populate the postal routing and item identification fields for
 * both Mailmark formats, generate their carrier symbols, recognize the images,
 * and decode the standardized Mailmark payloads back into typed objects.</p>
 */
public class RoyalMailMailmarkBarcodes {

    private static final String FOLDER =
            ExampleAssist.getOrCreateResourceFolderPath(
                    "complex", "royal-mail-mailmark");

    /**
     * Initializes the Aspose.BarCode license before running the Mailmark examples.
     */
    @BeforeClass
    public void setUp() {
        LicenseAssist.setupLicense();
    }

    /**
     * Demonstrates how to generate and read a Royal Mail Mailmark 1D barcode.
     *
     * <p>The test defines the format, version, postal class, supply-chain ID,
     * item ID, and international destination code, generates the four-state
     * Mailmark image, and verifies the decoded postal fields.</p>
     */
    @Test
    public void generateAndReadMailmark1D() throws Exception {
        MailmarkCodetext sourceCodetext = createMailmark1DCodetext();
        String outputPath = ExampleAssist.pathCombine(FOLDER, "mailmark_1d.png");

        ComplexBarcodeGenerator generator = new ComplexBarcodeGenerator(sourceCodetext);
        generator.save(outputPath, BarCodeImageFormat.PNG);

        ExampleAssist.assertFileCreated(outputPath);
        BarCodeReader reader = new BarCodeReader(outputPath, DecodeType.MAILMARK);
        BarCodeResult[] results = reader.readBarCodes();
        Assert.assertEquals(results.length, 1);

        MailmarkCodetext decodedCodetext =
                ComplexCodetextReader.tryDecodeMailmark(results[0].getCodeText());

        Assert.assertNotNull(decodedCodetext);
        Assert.assertEquals(decodedCodetext.getFormat(), 1);
        Assert.assertEquals(decodedCodetext.getVersionID(), 1);
        Assert.assertEquals(decodedCodetext.getClass_(), "1");
        Assert.assertEquals(decodedCodetext.getSupplychainID(), 99);
        Assert.assertEquals(decodedCodetext.getItemID(), 12345678);
        Assert.assertEquals(decodedCodetext.getDestinationPostCodePlusDPS(), "XY11     ");
    }

    /**
     * Demonstrates how to generate and read a Royal Mail Mailmark 2D barcode.
     *
     * <p>The test creates a Type 7 Data Matrix payload with routing, return-to-
     * sender, supply-chain, item, and customer-content data. It then recognizes
     * the Data Matrix carrier and restores the typed Mailmark 2D fields.</p>
     */
    @Test
    public void generateAndReadMailmark2D() throws Exception {
        Mailmark2DCodetext sourceCodetext = createMailmark2DCodetext();
        String outputPath = ExampleAssist.pathCombine(FOLDER, "mailmark_2d.png");

        Assert.assertEquals(sourceCodetext.getDataMatrixType(), Mailmark2DType.TYPE_7);
        ComplexBarcodeGenerator generator = new ComplexBarcodeGenerator(sourceCodetext);
        generator.save(outputPath, BarCodeImageFormat.PNG);

        ExampleAssist.assertFileCreated(outputPath);
        BarCodeReader reader = new BarCodeReader(outputPath, DecodeType.DATA_MATRIX);
        BarCodeResult[] results = reader.readBarCodes();
        Assert.assertEquals(results.length, 1);

        Mailmark2DCodetext decodedCodetext =
                ComplexCodetextReader.tryDecodeMailmark2D(results[0].getCodeText());

        Assert.assertNotNull(decodedCodetext);
        Assert.assertEquals(decodedCodetext.getUPUCountryID(), "JGB ");
        Assert.assertEquals(decodedCodetext.getInformationTypeID(), "0");
        Assert.assertEquals(decodedCodetext.getSupplyChainID(), 1234567);
        Assert.assertEquals(decodedCodetext.getItemID(), 12345678);
        Assert.assertEquals(decodedCodetext.getCustomerContent(), "ABC123");
    }

    /**
     * Creates a valid Mailmark 1D payload with an international destination code.
     */
    private MailmarkCodetext createMailmark1DCodetext() {
        MailmarkCodetext codetext = new MailmarkCodetext();
        codetext.setFormat(1);
        codetext.setVersionID(1);
        codetext.setClass("1");
        codetext.setSupplychainID(99);
        codetext.setItemID(12345678);
        codetext.setDestinationPostCodePlusDPS("XY11     ");
        return codetext;
    }

    /**
     * Creates a valid Mailmark 2D Type 7 payload.
     */
    private Mailmark2DCodetext createMailmark2DCodetext() {
        Mailmark2DCodetext codetext = new Mailmark2DCodetext();
        codetext.setUPUCountryID("JGB ");
        codetext.setInformationTypeID("0");
        codetext.setVersionID("1");
        codetext.setclass("1");
        codetext.setSupplyChainID(1234567);
        codetext.setItemID(12345678);
        codetext.setDestinationPostCodeAndDPS("XY11     ");
        codetext.setRTSFlag("0");
        codetext.setReturnToSenderPostCode("B1 1AA");
        codetext.setCustomerContent("ABC123");
        codetext.setDataMatrixType(Mailmark2DType.TYPE_7);
        return codetext;
    }
}
