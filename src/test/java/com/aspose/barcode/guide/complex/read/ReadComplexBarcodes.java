package com.aspose.barcode.guide.complex.read;

import com.aspose.barcode.barcoderecognition.BarCodeReader;
import com.aspose.barcode.barcoderecognition.BarCodeResult;
import com.aspose.barcode.barcoderecognition.DecodeType;
import com.aspose.barcode.complexbarcode.Address;
import com.aspose.barcode.complexbarcode.ComplexBarcodeGenerator;
import com.aspose.barcode.complexbarcode.ComplexCodetextReader;
import com.aspose.barcode.complexbarcode.HIBCLICCombinedCodetext;
import com.aspose.barcode.complexbarcode.HIBCLICComplexCodetext;
import com.aspose.barcode.complexbarcode.HIBCLICDateFormat;
import com.aspose.barcode.complexbarcode.PrimaryData;
import com.aspose.barcode.complexbarcode.QrBillStandardVersion;
import com.aspose.barcode.complexbarcode.SecondaryAndAdditionalData;
import com.aspose.barcode.complexbarcode.SwissQRCodetext;
import com.aspose.barcode.generation.BarCodeImageFormat;
import com.aspose.barcode.generation.EncodeTypes;
import com.aspose.barcode.guide.common.ExampleAssist;
import com.aspose.barcode.guide.common.LicenseAssist;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.LocalDateTime;

/**
 * Demonstrates how to recognize carrier symbologies and decode complex barcode
 * payloads with Aspose.BarCode for Java.
 *
 * <p>The examples separate image recognition from complex codetext parsing,
 * show how to obtain a typed business object from recognized text, demonstrate
 * access to decoded fields, and explain how unsupported or malformed payloads
 * are handled safely.</p>
 */
public class ReadComplexBarcodes {

    private static final String FOLDER =
            ExampleAssist.getOrCreateResourceFolderPath(
                    "complex", "read-complex-barcodes");

    /**
     * Initializes the Aspose.BarCode license before running the recognition and
     * decoding examples in this class.
     */
    @BeforeClass
    public void setUp() {
        LicenseAssist.setupLicense();
    }

    /**
     * Demonstrates how the physical carrier symbology is recognized before the
     * structured payload is decoded.
     *
     * <p>A Swiss QR payment is stored in a regular QR symbol. The reader reports
     * the QR symbology and returns the raw standardized payload for further
     * processing by the complex codetext API.</p>
     */
    @Test
    public void recognizeUnderlyingBarcode() throws Exception {
        SwissQRCodetext codetext = createSwissQRCodetext();
        String outputPath = ExampleAssist.pathCombine(FOLDER, "recognized_swiss_qr.png");
        ComplexBarcodeGenerator generator = new ComplexBarcodeGenerator(codetext);
        generator.save(outputPath, BarCodeImageFormat.PNG);

        BarCodeReader reader = new BarCodeReader(outputPath, DecodeType.QR);
        BarCodeResult[] results = reader.readBarCodes();

        Assert.assertEquals(results.length, 1);
        Assert.assertEquals(results[0].getCodeType(), DecodeType.QR);
        Assert.assertEquals(results[0].getCodeText(), codetext.getConstructedCodetext());
    }

    /**
     * Demonstrates how recognized HIBC LIC text is converted into a typed complex
     * codetext object.
     *
     * <p>The reader first extracts the HIBC QR payload. The payload is then passed
     * to {@link ComplexCodetextReader#tryDecodeHIBCLIC(String)}, which restores the
     * combined primary and secondary HIBC data model.</p>
     */
    @Test
    public void decodeCodetextIntoStructuredData() throws Exception {
        HIBCLICCombinedCodetext sourceCodetext = createHIBCLICCodetext();
        String outputPath = ExampleAssist.pathCombine(FOLDER, "decoded_hibc_lic.png");
        ComplexBarcodeGenerator generator = new ComplexBarcodeGenerator(sourceCodetext);
        generator.save(outputPath, BarCodeImageFormat.PNG);

        BarCodeReader reader = new BarCodeReader(outputPath, DecodeType.HIBCQRLIC);
        BarCodeResult[] results = reader.readBarCodes();
        Assert.assertEquals(results.length, 1);

        HIBCLICComplexCodetext decodedBase =
                ComplexCodetextReader.tryDecodeHIBCLIC(results[0].getCodeText());

        Assert.assertTrue(decodedBase instanceof HIBCLICCombinedCodetext);
        HIBCLICCombinedCodetext decodedCodetext = (HIBCLICCombinedCodetext) decodedBase;
        Assert.assertEquals(decodedCodetext.getPrimaryData().getProductOrCatalogNumber(), "12345");
        Assert.assertEquals(decodedCodetext.getSecondaryAndAdditionalData().getLotNumber(), "LOT123");
    }

    /**
     * Demonstrates how application code accesses business fields after a complex
     * payload has been decoded.
     *
     * <p>The example reads a Swiss QR image and retrieves the account, amount,
     * currency, creditor, and debtor directly from the restored payment object.</p>
     */
    @Test
    public void accessDecodedBusinessFields() throws Exception {
        SwissQRCodetext sourceCodetext = createSwissQRCodetext();
        String outputPath = ExampleAssist.pathCombine(FOLDER, "swiss_qr_business_fields.png");
        ComplexBarcodeGenerator generator = new ComplexBarcodeGenerator(sourceCodetext);
        generator.save(outputPath, BarCodeImageFormat.PNG);

        BarCodeReader reader = new BarCodeReader(outputPath, DecodeType.QR);
        BarCodeResult[] results = reader.readBarCodes();
        SwissQRCodetext decodedCodetext =
                ComplexCodetextReader.tryDecodeSwissQR(results[0].getCodeText());

        Assert.assertNotNull(decodedCodetext);
        Assert.assertEquals(decodedCodetext.getBill().getAccount(), "CH4431999123000889012");
        Assert.assertEquals(decodedCodetext.getBill().getAmount(), 100.25);
        Assert.assertEquals(decodedCodetext.getBill().getCurrency(), "CHF");
        Assert.assertEquals(decodedCodetext.getBill().getCreditor().getName(), "Robert Schneider AG");
        Assert.assertEquals(decodedCodetext.getBill().getDebtor().getName(), "Max Mustermann");
    }

    /**
     * Demonstrates safe handling of text that does not conform to a supported
     * complex barcode standard.
     *
     * <p>The try-decode methods return {@code null} when the supplied text cannot
     * be interpreted as the requested complex barcode format, allowing callers to
     * try another decoder or report unsupported data without failing the workflow.</p>
     */
    @Test
    public void handleInvalidOrUnsupportedData() {
        String unsupportedCodetext = "NOT-A-SUPPORTED-COMPLEX-BARCODE";

        Assert.assertNull(ComplexCodetextReader.tryDecodeSwissQR(unsupportedCodetext));
        Assert.assertNull(ComplexCodetextReader.tryDecodeHIBCLIC(unsupportedCodetext));
        Assert.assertNull(ComplexCodetextReader.tryDecodeHIBCPAS(unsupportedCodetext));
        Assert.assertNull(ComplexCodetextReader.tryDecodeMailmark(unsupportedCodetext));
        Assert.assertNull(ComplexCodetextReader.tryDecodeMailmark2D(unsupportedCodetext));
    }

    /**
     * Creates the Swiss QR model used by the recognition examples.
     */
    private SwissQRCodetext createSwissQRCodetext() {
        SwissQRCodetext codetext = new SwissQRCodetext();
        codetext.getBill().setVersion(QrBillStandardVersion.V2_0);
        codetext.getBill().setAccount("CH4431999123000889012");
        codetext.getBill().setAmount(100.25);
        codetext.getBill().setCurrency("CHF");
        codetext.getBill().setCreditor(createAddress(
                "Robert Schneider AG", "Rue du Lac", "1268", "2501", "Biel"));
        codetext.getBill().setDebtor(createAddress(
                "Max Mustermann", "Musterstrasse", "1", "8000", "Zurich"));
        codetext.getBill().setReference("210000000003139471430009017");
        codetext.getBill().setUnstructuredMessage("Invoice 2026-001");
        return codetext;
    }

    /**
     * Creates a structured Swiss address for a Swiss QR payment model.
     */
    private Address createAddress(
            String name,
            String street,
            String houseNumber,
            String postalCode,
            String town) {
        Address address = new Address();
        address.setName(name);
        address.setStreet(street);
        address.setHouseNo(houseNumber);
        address.setPostalCode(postalCode);
        address.setTown(town);
        address.setCountryCode("CH");
        return address;
    }

    /**
     * Creates the HIBC LIC model used by the decoding example.
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
