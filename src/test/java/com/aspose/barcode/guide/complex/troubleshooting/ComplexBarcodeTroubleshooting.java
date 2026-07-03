package com.aspose.barcode.guide.complex.troubleshooting;

import com.aspose.barcode.BarCodeException;
import com.aspose.barcode.barcoderecognition.BarCodeReader;
import com.aspose.barcode.barcoderecognition.BarCodeResult;
import com.aspose.barcode.barcoderecognition.DecodeType;
import com.aspose.barcode.complexbarcode.ComplexBarcodeGenerator;
import com.aspose.barcode.complexbarcode.ComplexCodetextReader;
import com.aspose.barcode.complexbarcode.HIBCLICCombinedCodetext;
import com.aspose.barcode.complexbarcode.HIBCLICComplexCodetext;
import com.aspose.barcode.complexbarcode.HIBCLICDateFormat;
import com.aspose.barcode.complexbarcode.MailmarkCodetext;
import com.aspose.barcode.complexbarcode.PrimaryData;
import com.aspose.barcode.complexbarcode.SecondaryAndAdditionalData;
import com.aspose.barcode.complexbarcode.SwissQRCodetext;
import com.aspose.barcode.generation.BarCodeImageFormat;
import com.aspose.barcode.generation.BarcodeGenerator;
import com.aspose.barcode.generation.EncodeTypes;
import com.aspose.barcode.guide.common.ExampleAssist;
import com.aspose.barcode.guide.common.LicenseAssist;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.LocalDateTime;

/**
 * Demonstrates troubleshooting scenarios specific to complex barcodes with
 * Aspose.BarCode for Java.
 *
 * <p>The examples focus on issues that happen after a barcode has been
 * recognized or before a complex barcode image is generated: checking the
 * recognized symbology, selecting the matching complex decoder, validating
 * required business fields, and enforcing standard-specific limits.</p>
 */
public class ComplexBarcodeTroubleshooting {

    private static final String FOLDER =
            ExampleAssist.getOrCreateResourceFolderPath("complex", "troubleshooting");

    /**
     * Initializes the Aspose.BarCode license before running the troubleshooting
     * examples.
     */
    @BeforeClass
    public void setUp() {
        LicenseAssist.setupLicense();
    }

    /**
     * Demonstrates that successful barcode recognition does not necessarily mean
     * successful complex barcode decoding.
     *
     * <p>The generated symbol is a valid QR Code, but its text is not a Swiss QR
     * payment payload. The reader recognizes the QR Code correctly, while the
     * Swiss QR decoder returns {@code null}.</p>
     */
    @Test
    public void verifyRecognizedBarcodeTypeBeforeComplexDecoding() throws Exception {
        BarcodeGenerator generator = new BarcodeGenerator(EncodeTypes.QR, "NOT-A-SWISS-QR");
        String outputPath = ExampleAssist.pathCombine(FOLDER, "plain_qr_not_swiss.png");
        generator.save(outputPath, BarCodeImageFormat.PNG);

        ExampleAssist.assertFileCreated(outputPath);
        BarCodeReader reader = new BarCodeReader(outputPath, DecodeType.ALL_SUPPORTED_TYPES);
        BarCodeResult[] results = reader.readBarCodes();

        Assert.assertEquals(results.length, 1);
        Assert.assertEquals(results[0].getCodeType(), DecodeType.QR);
        Assert.assertNull(ComplexCodetextReader.tryDecodeSwissQR(results[0].getCodeText()));
    }

    /**
     * Demonstrates that a valid complex barcode payload must be decoded with the
     * matching complex decoder.
     *
     * <p>The HIBC LIC payload is valid complex barcode data, but it is not Swiss
     * QR data. The Swiss QR decoder returns {@code null}, while the HIBC LIC
     * decoder restores the typed codetext object.</p>
     */
    @Test
    public void decodeUsingMatchingComplexDecoder() throws Exception {
        HIBCLICCombinedCodetext sourceCodetext = createHIBCLICCodetext();
        String outputPath = ExampleAssist.pathCombine(FOLDER, "hibc_lic_for_decoder_check.png");

        ComplexBarcodeGenerator generator = new ComplexBarcodeGenerator(sourceCodetext);
        generator.save(outputPath, BarCodeImageFormat.PNG);

        ExampleAssist.assertFileCreated(outputPath);
        BarCodeReader reader = new BarCodeReader(outputPath, DecodeType.HIBCQRLIC);
        BarCodeResult[] results = reader.readBarCodes();
        Assert.assertEquals(results.length, 1);

        String recognizedText = results[0].getCodeText();
        Assert.assertNull(ComplexCodetextReader.tryDecodeSwissQR(recognizedText));

        HIBCLICComplexCodetext decodedCodetext =
                ComplexCodetextReader.tryDecodeHIBCLIC(recognizedText);
        Assert.assertTrue(decodedCodetext instanceof HIBCLICCombinedCodetext);
    }

    /**
     * Demonstrates how missing required business fields are reported before image
     * generation.
     *
     * <p>An empty Swiss QR object does not contain the required valid IBAN.
     * Calling {@code getConstructedCodetext()} therefore raises
     * {@link BarCodeException}, allowing the application to correct the business
     * data before rendering.</p>
     */
    @Test
    public void handleRequiredFieldsMissing() {
        SwissQRCodetext incompleteCodetext = new SwissQRCodetext();

        try {
            incompleteCodetext.getConstructedCodetext();
            Assert.fail("Expected BarCodeException for missing required Swiss QR fields.");
        } catch (BarCodeException exception) {
            Assert.assertTrue(exception.getMessage().contains("IBAN"));
        }
    }

    /**
     * Demonstrates how a field value that exceeds a Mailmark standard limit is
     * rejected during codetext construction.
     *
     * <p>The Mailmark item identifier must not exceed 99,999,999. The example
     * supplies a larger value and verifies that the API raises
     * {@link BarCodeException} before a noncompliant payload can be generated.</p>
     */
    @Test
    public void handleGeneratedDataDoesNotMatchStandard() {
        MailmarkCodetext invalidCodetext = new MailmarkCodetext();
        invalidCodetext.setFormat(1);
        invalidCodetext.setVersionID(1);
        invalidCodetext.setClass("1");
        invalidCodetext.setSupplychainID(99);
        invalidCodetext.setItemID(100_000_000);
        invalidCodetext.setDestinationPostCodePlusDPS("XY11     ");

        try {
            invalidCodetext.getConstructedCodetext();
            Assert.fail("Expected BarCodeException for an out-of-range Mailmark item ID.");
        } catch (BarCodeException exception) {
            Assert.assertTrue(exception.getMessage().contains("99999999"));
        }
    }

    /**
     * Creates a combined HIBC LIC model used to demonstrate decoder selection.
     */
    private HIBCLICCombinedCodetext createHIBCLICCodetext() {
        HIBCLICCombinedCodetext codetext = new HIBCLICCombinedCodetext();
        codetext.setBarcodeType(EncodeTypes.HIBCQRLIC);

        PrimaryData primaryData = new PrimaryData();
        primaryData.setLabelerIdentificationCode("A999");
        primaryData.setProductOrCatalogNumber("12345");
        primaryData.setUnitOfMeasureID(1);
        codetext.setPrimaryData(primaryData);

        SecondaryAndAdditionalData secondaryData = new SecondaryAndAdditionalData();
        secondaryData.setExpiryDateFormat(HIBCLICDateFormat.YYYYMMDD);
        secondaryData.setExpiryDate(LocalDateTime.of(2027, 12, 31, 0, 0));
        secondaryData.setQuantity(30);
        secondaryData.setLotNumber("LOT123");
        secondaryData.setSerialNumber("SERIAL123");
        codetext.setSecondaryAndAdditionalData(secondaryData);
        return codetext;
    }
}
