package com.aspose.barcode.guide.complex.generate;

import com.aspose.barcode.barcoderecognition.DecodeType;
import com.aspose.barcode.complexbarcode.Address;
import com.aspose.barcode.complexbarcode.ComplexBarcodeGenerator;
import com.aspose.barcode.complexbarcode.HIBCLICCombinedCodetext;
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

import java.awt.Color;
import java.io.ByteArrayOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.List;

import static com.aspose.barcode.guide.common.ExampleAssist.assertImageHasBarcodes;
import static com.aspose.barcode.guide.common.ExampleAssist.expected;

/**
 * Demonstrates the general generation workflow for complex barcodes with
 * Aspose.BarCode for Java.
 *
 * <p>The examples show how to create structured business data, pass it to
 * {@link ComplexBarcodeGenerator}, generate an image, configure common visual
 * properties, and save the result either to a file or to an output stream.</p>
 */
public class GenerateComplexBarcodes {

    private static final String FOLDER =
            ExampleAssist.getOrCreateResourceFolderPath(
                    "complex", "generate-complex-barcodes");

    /**
     * Initializes the Aspose.BarCode license before running the generation
     * examples in this class.
     */
    @BeforeClass
    public void setUp() {
        LicenseAssist.setupLicense();
    }

    /**
     * Demonstrates how to populate a typed HIBC LIC object before generating an
     * image.
     *
     * <p>The test separates primary product identification from secondary batch,
     * serial, quantity, manufacture date, and expiry data. The API then constructs
     * the standards-compliant HIBC payload automatically.</p>
     */
    @Test
    public void createStructuredBarcodeData() {
        HIBCLICCombinedCodetext codetext = createHIBCLICCodetext();
        String constructedCodetext = codetext.getConstructedCodetext();

        Assert.assertTrue(constructedCodetext.startsWith("+A999123451"));
        Assert.assertTrue(constructedCodetext.contains("LOT123"));
        Assert.assertTrue(constructedCodetext.contains("SERIAL123"));
        Assert.assertEquals(codetext.getPrimaryData().getUnitOfMeasureID(), 1);
    }

    /**
     * Demonstrates how to create a complex barcode image and save it as PNG.
     *
     * <p>The test generates a Swiss QR payment barcode from a typed payment model,
     * confirms that the output file was created, and verifies that the image
     * contains the expected QR payload.</p>
     */
    @Test
    public void generateBarcodeImage() throws Exception {
        SwissQRCodetext codetext = createSwissQRCodetext();
        String outputPath = ExampleAssist.pathCombine(FOLDER, "generated_swiss_qr.png");

        ComplexBarcodeGenerator generator = new ComplexBarcodeGenerator(codetext);
        generator.save(outputPath, BarCodeImageFormat.PNG);

        ExampleAssist.assertFileCreated(outputPath);
        assertImageHasBarcodes(
                outputPath,
                1,
                List.of(expected(DecodeType.QR, codetext.getConstructedCodetext())));
    }

    /**
     * Demonstrates how common appearance settings are applied to a complex
     * barcode through the generator parameters.
     *
     * <p>The example configures the background, bar color, border, X-dimension,
     * and padding without changing the structured Swiss QR business data.</p>
     */
    @Test
    public void configureBarcodeAppearance() throws Exception {
        SwissQRCodetext codetext = createSwissQRCodetext();
        String outputPath = ExampleAssist.pathCombine(FOLDER, "styled_swiss_qr.png");

        ComplexBarcodeGenerator generator = new ComplexBarcodeGenerator(codetext);
        generator.getParameters().setBackColor(new Color(245, 248, 252));
        generator.getParameters().getBarcode().setBarColor(new Color(20, 45, 90));
        generator.getParameters().getBarcode().getXDimension().setPixels(3);
        generator.getParameters().getBarcode().getPadding().getLeft().setPixels(12);
        generator.getParameters().getBarcode().getPadding().getRight().setPixels(12);
        generator.getParameters().getBarcode().getPadding().getTop().setPixels(12);
        generator.getParameters().getBarcode().getPadding().getBottom().setPixels(12);
        generator.getParameters().getBorder().setVisible(true);
        generator.getParameters().getBorder().setColor(Color.GRAY);
        generator.getParameters().getBorder().getWidth().setPixels(2);
        generator.save(outputPath, BarCodeImageFormat.PNG);

        ExampleAssist.assertFileCreated(outputPath);
        assertImageHasBarcodes(
                outputPath,
                1,
                List.of(expected(DecodeType.QR, codetext.getConstructedCodetext())));
    }

    /**
     * Demonstrates how the same complex barcode can be written directly to a
     * file and to an in-memory output stream.
     *
     * <p>The stream workflow is useful for web responses, document composition,
     * cloud storage, and other scenarios where an intermediate file is not
     * required.</p>
     */
    @Test
    public void saveBarcodeToFileAndStream() throws Exception {
        HIBCLICCombinedCodetext codetext = createHIBCLICCodetext();
        String outputPath = ExampleAssist.pathCombine(FOLDER, "hibc_lic_from_stream.png");

        ComplexBarcodeGenerator generator = new ComplexBarcodeGenerator(codetext);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        generator.save(outputStream, BarCodeImageFormat.PNG);

        byte[] imageBytes = outputStream.toByteArray();
        Assert.assertTrue(imageBytes.length > 0);
        Files.write(Path.of(outputPath), imageBytes);

        ExampleAssist.assertFileCreated(outputPath);
        assertImageHasBarcodes(
                outputPath,
                1,
                List.of(expected(DecodeType.HIBCQRLIC, codetext.getConstructedCodetext())));
    }

    /**
     * Creates the HIBC LIC model used by the generation examples.
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
        secondaryData.setDateOfManufacture(LocalDateTime.of(2026, 1, 15, 0, 0));
        codetext.setSecondaryAndAdditionalData(secondaryData);
        return codetext;
    }

    /**
     * Creates the Swiss QR model used by the image generation examples.
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
