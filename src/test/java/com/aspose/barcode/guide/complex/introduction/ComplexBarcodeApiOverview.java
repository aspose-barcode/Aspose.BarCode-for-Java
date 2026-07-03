package com.aspose.barcode.guide.complex.introduction;

import com.aspose.barcode.barcoderecognition.BarCodeReader;
import com.aspose.barcode.barcoderecognition.BarCodeResult;
import com.aspose.barcode.barcoderecognition.DecodeType;
import com.aspose.barcode.complexbarcode.Address;
import com.aspose.barcode.complexbarcode.ComplexBarcodeGenerator;
import com.aspose.barcode.complexbarcode.ComplexCodetextReader;
import com.aspose.barcode.complexbarcode.IComplexCodetext;
import com.aspose.barcode.complexbarcode.QrBillStandardVersion;
import com.aspose.barcode.complexbarcode.SwissQRCodetext;
import com.aspose.barcode.generation.BarCodeImageFormat;
import com.aspose.barcode.generation.BarcodeGenerator;
import com.aspose.barcode.generation.EncodeTypes;
import com.aspose.barcode.guide.common.ExampleAssist;
import com.aspose.barcode.guide.common.LicenseAssist;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

import static com.aspose.barcode.guide.common.ExampleAssist.assertImageHasBarcodes;
import static com.aspose.barcode.guide.common.ExampleAssist.expected;

/**
 * Provides an API overview for working with complex barcodes in Aspose.BarCode for Java.
 *
 * <p>The examples show the responsibilities of the main API types:</p>
 * <ul>
 *     <li>{@link IComplexCodetext} stores structured business data.</li>
 *     <li>{@link ComplexBarcodeGenerator} generates a barcode from a structured model.</li>
 *     <li>{@link BarcodeGenerator} generates a barcode from already prepared plain text.</li>
 *     <li>{@link BarCodeReader} recognizes the physical barcode symbology from an image.</li>
 *     <li>{@link ComplexCodetextReader} decodes recognized text back into a typed model.</li>
 * </ul>
 */
public class ComplexBarcodeApiOverview {

    private static final String FOLDER =
            ExampleAssist.getOrCreateResourceFolderPath("complex", "api-overview");

    /**
     * Initializes the Aspose.BarCode license before running generation and recognition examples.
     */
    @BeforeClass
    public void setUp() {
        LicenseAssist.setupLicense();
    }

    /**
     * Shows that a complex barcode starts from a typed codetext object.
     *
     * <p>The Swiss QR model stores payment fields and constructs the standardized payload
     * internally. The model also knows which barcode symbology is used to encode this data.</p>
     */
    @Test
    public void inspectComplexCodetextModel() {
        SwissQRCodetext codetext = createSwissQRCodetext();

        Assert.assertTrue(codetext instanceof IComplexCodetext);
        Assert.assertEquals(codetext.getBarcodeType(), EncodeTypes.QR);
        Assert.assertTrue(codetext.getConstructedCodetext().startsWith("SPC"));
        Assert.assertEquals(codetext.getBill().getAccount(), "CH4431999123000889012");
        Assert.assertEquals(codetext.getBill().getAmount(), 100.25);
        Assert.assertEquals(codetext.getBill().getCurrency(), "CHF");
    }

    /**
     * Compares the responsibilities of BarcodeGenerator and ComplexBarcodeGenerator.
     *
     * <p>BarcodeGenerator receives the exact text that must be encoded. ComplexBarcodeGenerator
     * receives a structured object and uses its standard-specific constructed codetext.</p>
     */
    @Test
    public void compareBarcodeGeneratorAndComplexBarcodeGenerator() throws Exception {
        SwissQRCodetext codetext = createSwissQRCodetext();
        String constructedCodetext = codetext.getConstructedCodetext();
        String regularPath = ExampleAssist.pathCombine(FOLDER, "regular_qr_from_text.png");
        String complexPath = ExampleAssist.pathCombine(FOLDER, "complex_swiss_qr_from_model.png");

        BarcodeGenerator regularGenerator = new BarcodeGenerator(EncodeTypes.QR, constructedCodetext);
        regularGenerator.save(regularPath, BarCodeImageFormat.PNG);

        ComplexBarcodeGenerator complexGenerator = new ComplexBarcodeGenerator(codetext);
        complexGenerator.save(complexPath, BarCodeImageFormat.PNG);

        assertImageHasBarcodes(regularPath, 1, List.of(expected(DecodeType.QR, constructedCodetext)));
        assertImageHasBarcodes(complexPath, 1, List.of(expected(DecodeType.QR, constructedCodetext)));
    }

    /**
     * Shows the two-stage reading workflow: recognition first, complex decoding second.
     *
     * <p>BarCodeReader detects the physical QR Code and returns BarCodeResult objects.
     * ComplexCodetextReader then interprets the recognized text as a Swiss QR payload.</p>
     */
    @Test
    public void recognizeCarrierThenDecodeComplexCodetext() throws Exception {
        SwissQRCodetext sourceCodetext = createSwissQRCodetext();
        String imagePath = ExampleAssist.pathCombine(FOLDER, "swiss_qr_api_workflow.png");

        ComplexBarcodeGenerator generator = new ComplexBarcodeGenerator(sourceCodetext);
        generator.save(imagePath, BarCodeImageFormat.PNG);

        BarCodeReader reader = new BarCodeReader(imagePath, DecodeType.QR);
        BarCodeResult[] results = reader.readBarCodes();

        Assert.assertEquals(results.length, 1);
        Assert.assertEquals(results[0].getCodeType(), DecodeType.QR);

        SwissQRCodetext decodedCodetext =
                ComplexCodetextReader.tryDecodeSwissQR(results[0].getCodeText());

        Assert.assertNotNull(decodedCodetext);
        Assert.assertEquals(decodedCodetext.getBill().getAccount(), sourceCodetext.getBill().getAccount());
        Assert.assertEquals(decodedCodetext.getBill().getAmount(), sourceCodetext.getBill().getAmount());
        Assert.assertEquals(decodedCodetext.getBill().getCreditor().getName(), "Robert Schneider AG");
    }

    /**
     * Shows that complex decoding is standard-specific.
     *
     * <p>The QR Code is recognized successfully as a physical barcode, but the text is converted
     * to a typed SwissQRCodetext only when the matching complex decoder is used.</p>
     */
    @Test
    public void decodeOnlyWithMatchingComplexStandard() throws Exception {
        String plainQrPath = ExampleAssist.pathCombine(FOLDER, "plain_qr_not_swiss_qr.png");

        BarcodeGenerator generator = new BarcodeGenerator(EncodeTypes.QR, "Plain QR text");
        generator.save(plainQrPath, BarCodeImageFormat.PNG);

        BarCodeReader reader = new BarCodeReader(plainQrPath, DecodeType.QR);
        BarCodeResult[] results = reader.readBarCodes();

        Assert.assertEquals(results.length, 1);
        Assert.assertEquals(results[0].getCodeText(), "Plain QR text");
        Assert.assertNull(ComplexCodetextReader.tryDecodeSwissQR(results[0].getCodeText()));
    }

    /**
     * Creates a valid Swiss QR payment model used by all API overview examples.
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
     * Creates a structured Swiss address for the Swiss QR payment model.
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
