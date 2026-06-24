package com.aspose.barcode.guide.complex.introduction;

import com.aspose.barcode.barcoderecognition.BarCodeReader;
import com.aspose.barcode.barcoderecognition.BarCodeResult;
import com.aspose.barcode.barcoderecognition.DecodeType;
import com.aspose.barcode.complexbarcode.Address;
import com.aspose.barcode.complexbarcode.ComplexBarcodeGenerator;
import com.aspose.barcode.complexbarcode.ComplexCodetextReader;
import com.aspose.barcode.complexbarcode.HIBCLICCombinedCodetext;
import com.aspose.barcode.complexbarcode.HIBCPASCodetext;
import com.aspose.barcode.complexbarcode.IComplexCodetext;
import com.aspose.barcode.complexbarcode.Mailmark2DCodetext;
import com.aspose.barcode.complexbarcode.MailmarkCodetext;
import com.aspose.barcode.complexbarcode.MaxiCodeCodetextMode2;
import com.aspose.barcode.complexbarcode.PrimaryData;
import com.aspose.barcode.complexbarcode.QrBillStandardVersion;
import com.aspose.barcode.complexbarcode.SecondaryAndAdditionalData;
import com.aspose.barcode.complexbarcode.SwissQRCodetext;
import com.aspose.barcode.complexbarcode.USADriveIdCodetext;
import com.aspose.barcode.generation.BarCodeImageFormat;
import com.aspose.barcode.generation.BarcodeGenerator;
import com.aspose.barcode.generation.EncodeTypes;
import com.aspose.barcode.guide.common.ExampleAssist;
import com.aspose.barcode.guide.common.LicenseAssist;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.LocalDateTime;
import java.util.List;

import static com.aspose.barcode.guide.common.ExampleAssist.assertImageHasBarcodes;
import static com.aspose.barcode.guide.common.ExampleAssist.expected;

/**
 * Introduces the complex barcode object model and the common processing workflow
 * provided by Aspose.BarCode for Java.
 *
 * <p>The examples explain how a complex barcode differs from a regular barcode,
 * how business data is represented by {@link IComplexCodetext}, which complex
 * barcode standards are supported, and how typed data is generated, recognized,
 * and decoded back into a domain object.</p>
 */
public class ComplexBarcodeIntroduction {

    private static final String FOLDER =
            ExampleAssist.getOrCreateResourceFolderPath(
                    "complex", "introduction-to-complex-barcodes");

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
     * Demonstrates that a complex barcode is created from a typed business model
     * rather than from an unstructured string.
     *
     * <p>The example builds a Swiss QR payment object, verifies that the model
     * selects QR as its carrier symbology, and constructs the standardized text
     * that will be encoded into the barcode image.</p>
     */
    @Test
    public void createTypedComplexCodetext() {
        SwissQRCodetext codetext = createSwissQRCodetext();

        Assert.assertEquals(codetext.getBarcodeType(), EncodeTypes.QR);
        Assert.assertTrue(codetext.getConstructedCodetext().startsWith("SPC"));
        Assert.assertEquals(codetext.getBill().getAmount(), 100.25);
        Assert.assertEquals(codetext.getBill().getCurrency(), "CHF");
    }

    /**
     * Demonstrates the difference between regular and complex barcode generation.
     *
     * <p>A regular {@link BarcodeGenerator} receives the final encoded text
     * directly, while {@link ComplexBarcodeGenerator} receives a typed complex
     * codetext object and constructs the standardized payload automatically.
     * Both approaches produce a QR image containing the same payload.</p>
     */
    @Test
    public void compareRegularAndComplexBarcodeGeneration() throws Exception {
        SwissQRCodetext codetext = createSwissQRCodetext();
        String constructedCodetext = codetext.getConstructedCodetext();
        String complexPath = ExampleAssist.pathCombine(FOLDER, "complex_swiss_qr.png");
        String regularPath = ExampleAssist.pathCombine(FOLDER, "regular_qr.png");

        ComplexBarcodeGenerator complexGenerator = new ComplexBarcodeGenerator(codetext);
        complexGenerator.save(complexPath, BarCodeImageFormat.PNG);

        BarcodeGenerator regularGenerator = new BarcodeGenerator(EncodeTypes.QR, constructedCodetext);
        regularGenerator.save(regularPath, BarCodeImageFormat.PNG);

        ExampleAssist.assertFileCreated(complexPath);
        ExampleAssist.assertFileCreated(regularPath);
        assertImageHasBarcodes(
                complexPath,
                1,
                List.of(expected(DecodeType.QR, constructedCodetext)));
        assertImageHasBarcodes(
                regularPath,
                1,
                List.of(expected(DecodeType.QR, constructedCodetext)));
    }

    /**
     * Demonstrates the main typed codetext classes available for supported complex
     * barcode standards.
     *
     * <p>The test creates representative objects for Swiss QR, HIBC LIC, HIBC PAS,
     * Royal Mail Mailmark, MaxiCode, and USA Driver ID. It verifies that each
     * standard is represented by a dedicated Java type instead of requiring
     * callers to build a proprietary data string manually.</p>
     */
    @Test
    public void inspectSupportedComplexBarcodeStandards() {
        List<IComplexCodetext> supportedCodetexts = List.of(
                createSwissQRCodetext(),
                createHIBCLICCodetext(),
                createHIBCPASCodetext(),
                new MailmarkCodetext(),
                new Mailmark2DCodetext(),
                new MaxiCodeCodetextMode2(),
                new USADriveIdCodetext());

        Assert.assertEquals(supportedCodetexts.size(), 7);
        for (IComplexCodetext codetext : supportedCodetexts) {
            Assert.assertNotNull(codetext);
        }
    }

    /**
     * Demonstrates the complete processing workflow for a complex barcode.
     *
     * <p>The example generates a Swiss QR image from a typed model, recognizes the
     * QR carrier symbology with {@link BarCodeReader}, decodes the recognized text
     * with {@link ComplexCodetextReader}, and accesses the restored payment fields.</p>
     */
    @Test
    public void executeGenerationRecognitionAndDecodingWorkflow() throws Exception {
        SwissQRCodetext sourceCodetext = createSwissQRCodetext();
        String outputPath = ExampleAssist.pathCombine(FOLDER, "swiss_qr_workflow.png");

        ComplexBarcodeGenerator generator = new ComplexBarcodeGenerator(sourceCodetext);
        generator.save(outputPath, BarCodeImageFormat.PNG);

        BarCodeReader reader = new BarCodeReader(outputPath, DecodeType.QR);
        BarCodeResult[] results = reader.readBarCodes();
        Assert.assertEquals(results.length, 1);

        SwissQRCodetext decodedCodetext =
                ComplexCodetextReader.tryDecodeSwissQR(results[0].getCodeText());

        Assert.assertNotNull(decodedCodetext);
        Assert.assertEquals(decodedCodetext.getBill().getAccount(), sourceCodetext.getBill().getAccount());
        Assert.assertEquals(decodedCodetext.getBill().getAmount(), sourceCodetext.getBill().getAmount());
        Assert.assertEquals(decodedCodetext.getBill().getCreditor().getName(), "Robert Schneider AG");
    }

    /**
     * Creates a valid Swiss QR payment model used by the introductory examples.
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
     * Creates a valid HIBC LIC model for the supported standards overview.
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
        secondaryData.setExpiryDate(LocalDateTime.of(2027, 12, 31, 0, 0));
        secondaryData.setLotNumber("LOT123");
        codetext.setSecondaryAndAdditionalData(secondaryData);
        return codetext;
    }

    /**
     * Creates a valid HIBC PAS model for the supported standards overview.
     */
    private HIBCPASCodetext createHIBCPASCodetext() {
        HIBCPASCodetext codetext = new HIBCPASCodetext();
        codetext.setBarcodeType(EncodeTypes.HIBC_DATA_MATRIX_PAS);
        return codetext;
    }
}
