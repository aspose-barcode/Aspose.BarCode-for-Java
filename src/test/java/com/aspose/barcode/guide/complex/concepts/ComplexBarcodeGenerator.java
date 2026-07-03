package com.aspose.barcode.guide.complex.concepts;

import com.aspose.barcode.barcoderecognition.BarCodeReader;
import com.aspose.barcode.barcoderecognition.BarCodeResult;
import com.aspose.barcode.barcoderecognition.DecodeType;
import com.aspose.barcode.complexbarcode.Address;
import com.aspose.barcode.complexbarcode.ComplexCodetextReader;
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
 * Demonstrates when to use the general-purpose {@link BarcodeGenerator} API and
 * when to use {@link com.aspose.barcode.complexbarcode.ComplexBarcodeGenerator} for structured complex barcode data.
 *
 * <p>The examples use Swiss QR because it clearly shows the difference between
 * encoding an already prepared string and letting Aspose.BarCode construct the
 * standardized payload from a typed business object.</p>
 */
public class ComplexBarcodeGenerator {

    private static final String FOLDER =
            ExampleAssist.getOrCreateResourceFolderPath(
                    "complex", "barcode-generator-vs-complex-barcode-generator");

    /**
     * Initializes the Aspose.BarCode license before running the examples in this
     * class so that generation and recognition are performed without evaluation
     * limitations.
     */
    @BeforeClass
    public void setUp() {
        LicenseAssist.setupLicense();
    }

    /**
     * Demonstrates the regular barcode workflow.
     *
     * <p>{@link BarcodeGenerator} receives the exact text that must be encoded.
     * This is the right choice for plain barcodes, such as a QR Code that stores
     * an application-defined string. The generator does not know whether the text
     * follows any complex barcode standard.</p>
     */
    @Test
    public void generatePlainQRCodeWithBarcodeGenerator() throws Exception {
        String codeText = "Plain application text";
        String outputPath = ExampleAssist.pathCombine(FOLDER, "plain_qr.png");

        BarcodeGenerator generator = new BarcodeGenerator(EncodeTypes.QR, codeText);
        generator.save(outputPath, BarCodeImageFormat.PNG);

        assertImageHasBarcodes(
                outputPath,
                1,
                List.of(expected(DecodeType.QR, codeText)));
    }

    /**
     * Demonstrates the complex barcode workflow.
     *
     * <p>{@link com.aspose.barcode.complexbarcode.ComplexBarcodeGenerator} receives a typed complex codetext object.
     * The object represents business data, and Aspose.BarCode constructs the
     * standardized payload that must be encoded into the carrier symbology.</p>
     */
    @Test
    public void generateSwissQRWithComplexBarcodeGenerator() throws Exception {
        SwissQRCodetext sourceCodetext = createSwissQRCodetext();
        String outputPath = ExampleAssist.pathCombine(FOLDER, "complex_swiss_qr.png");

        com.aspose.barcode.complexbarcode.ComplexBarcodeGenerator generator = new com.aspose.barcode.complexbarcode.ComplexBarcodeGenerator(sourceCodetext);
        generator.save(outputPath, BarCodeImageFormat.PNG);

        BarCodeReader reader = new BarCodeReader(outputPath, DecodeType.QR);
        BarCodeResult[] results = reader.readBarCodes();
        Assert.assertEquals(results.length, 1);

        SwissQRCodetext decodedCodetext =
                ComplexCodetextReader.tryDecodeSwissQR(results[0].getCodeText());

        Assert.assertNotNull(decodedCodetext);
        Assert.assertEquals(decodedCodetext.getBill().getAccount(), sourceCodetext.getBill().getAccount());
        Assert.assertEquals(decodedCodetext.getBill().getAmount(), sourceCodetext.getBill().getAmount());
        Assert.assertEquals(decodedCodetext.getBill().getCurrency(), "CHF");
    }

    /**
     * Demonstrates that both APIs can create the same carrier barcode when the
     * standardized complex codetext is already available.
     *
     * <p>This test is useful for understanding the API boundary. The regular
     * generator can encode the final Swiss QR text as a QR Code, but it does not
     * build or validate the Swiss QR payment object. The complex generator is the
     * preferred API when the application works with structured complex barcode data.</p>
     */
    @Test
    public void compareGeneratedPayloads() throws Exception {
        SwissQRCodetext codetext = createSwissQRCodetext();
        String constructedCodetext = codetext.getConstructedCodetext();
        String regularPath = ExampleAssist.pathCombine(FOLDER, "regular_generator_swiss_qr_payload.png");
        String complexPath = ExampleAssist.pathCombine(FOLDER, "complex_generator_swiss_qr_payload.png");

        BarcodeGenerator regularGenerator = new BarcodeGenerator(EncodeTypes.QR, constructedCodetext);
        regularGenerator.save(regularPath, BarCodeImageFormat.PNG);

        com.aspose.barcode.complexbarcode.ComplexBarcodeGenerator complexGenerator = new com.aspose.barcode.complexbarcode.ComplexBarcodeGenerator(codetext);
        complexGenerator.save(complexPath, BarCodeImageFormat.PNG);

        assertImageHasBarcodes(
                regularPath,
                1,
                List.of(expected(DecodeType.QR, constructedCodetext)));
        assertImageHasBarcodes(
                complexPath,
                1,
                List.of(expected(DecodeType.QR, constructedCodetext)));
    }

    /**
     * Creates a valid Swiss QR payment model used by the examples in this class.
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
}
