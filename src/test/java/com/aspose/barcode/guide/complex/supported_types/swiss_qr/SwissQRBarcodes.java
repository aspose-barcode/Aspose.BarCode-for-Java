package com.aspose.barcode.guide.complex.supported_types.swiss_qr;

import com.aspose.barcode.barcoderecognition.BarCodeReader;
import com.aspose.barcode.barcoderecognition.BarCodeResult;
import com.aspose.barcode.barcoderecognition.DecodeType;
import com.aspose.barcode.complexbarcode.Address;
import com.aspose.barcode.complexbarcode.ComplexBarcodeGenerator;
import com.aspose.barcode.complexbarcode.ComplexCodetextReader;
import com.aspose.barcode.complexbarcode.QrBillStandardVersion;
import com.aspose.barcode.complexbarcode.SwissQRCodetext;
import com.aspose.barcode.generation.BarCodeImageFormat;
import com.aspose.barcode.guide.common.ExampleAssist;
import com.aspose.barcode.guide.common.LicenseAssist;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * Demonstrates how to generate and read Swiss QR payment barcodes with
 * Aspose.BarCode for Java.
 *
 * <p>The examples create a standards-based Swiss QR Bill with account, amount,
 * currency, creditor, debtor, payment reference, and message data. They then
 * generate a QR image and restore the payment fields from the recognized payload.</p>
 */
public class SwissQRBarcodes {

    private static final String FOLDER =
            ExampleAssist.getOrCreateResourceFolderPath("complex", "swiss-qr");

    /**
     * Initializes the Aspose.BarCode license before running the Swiss QR examples.
     */
    @BeforeClass
    public void setUp() {
        LicenseAssist.setupLicense();
    }

    /**
     * Demonstrates how to create a Swiss QR Bill and generate its QR image.
     *
     * <p>The test fills the required payment and address fields through the typed
     * Swiss QR model, generates the barcode as PNG, and verifies that the image
     * contains the constructed Swiss QR payload.</p>
     */
    @Test
    public void generateSwissQRBill() throws Exception {
        SwissQRCodetext codetext = createSwissQRCodetext();
        String outputPath = ExampleAssist.pathCombine(FOLDER, "swiss_qr_bill.png");

        ComplexBarcodeGenerator generator = new ComplexBarcodeGenerator(codetext);
        generator.save(outputPath, BarCodeImageFormat.PNG);

        ExampleAssist.assertFileCreated(outputPath);
        BarCodeReader reader = new BarCodeReader(outputPath, DecodeType.QR);
        BarCodeResult[] results = reader.readBarCodes();
        Assert.assertEquals(results.length, 1);
        Assert.assertEquals(results[0].getCodeText(), codetext.getConstructedCodetext());
    }

    /**
     * Demonstrates how to recognize a Swiss QR image and decode its payment data.
     *
     * <p>The test reads the QR carrier symbol, converts the recognized text back
     * into {@link SwissQRCodetext}, and verifies the account, amount, currency,
     * payment reference, creditor, and debtor values.</p>
     */
    @Test
    public void readSwissQRData() throws Exception {
        SwissQRCodetext sourceCodetext = createSwissQRCodetext();
        String outputPath = ExampleAssist.pathCombine(FOLDER, "swiss_qr_for_reading.png");
        ComplexBarcodeGenerator generator = new ComplexBarcodeGenerator(sourceCodetext);
        generator.save(outputPath, BarCodeImageFormat.PNG);

        BarCodeReader reader = new BarCodeReader(outputPath, DecodeType.QR);
        BarCodeResult[] results = reader.readBarCodes();
        Assert.assertEquals(results.length, 1);

        SwissQRCodetext decodedCodetext =
                ComplexCodetextReader.tryDecodeSwissQR(results[0].getCodeText());

        Assert.assertNotNull(decodedCodetext);
        Assert.assertEquals(decodedCodetext.getBill().getAccount(), "CH4431999123000889012");
        Assert.assertEquals(decodedCodetext.getBill().getAmount(), 100.25);
        Assert.assertEquals(decodedCodetext.getBill().getCurrency(), "CHF");
        Assert.assertEquals(decodedCodetext.getBill().getReference(), "210000000003139471430009017");
        Assert.assertEquals(decodedCodetext.getBill().getCreditor().getName(), "Robert Schneider AG");
        Assert.assertEquals(decodedCodetext.getBill().getDebtor().getName(), "Max Mustermann");
    }

    /**
     * Creates the complete Swiss QR Bill used by the generation and reading tests.
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
        codetext.getBill().setBillInformation("//S1/10/2026001");
        return codetext;
    }

    /**
     * Creates a structured Swiss address for the Swiss QR Bill.
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
}
