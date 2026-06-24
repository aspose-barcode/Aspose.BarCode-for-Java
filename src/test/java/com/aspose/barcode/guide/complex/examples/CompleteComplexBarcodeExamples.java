package com.aspose.barcode.guide.complex.examples;

import com.aspose.barcode.barcoderecognition.BarCodeReader;
import com.aspose.barcode.barcoderecognition.BarCodeResult;
import com.aspose.barcode.barcoderecognition.DecodeType;
import com.aspose.barcode.complexbarcode.Address;
import com.aspose.barcode.complexbarcode.ComplexBarcodeGenerator;
import com.aspose.barcode.complexbarcode.ComplexCodetextReader;
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

import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.time.LocalDateTime;

/**
 * Provides complete end-to-end examples for complex barcode processing with
 * Aspose.BarCode for Java.
 *
 * <p>The class demonstrates a full generate-read-decode workflow, processing
 * several complex barcodes from one image, and verifying that generated payloads
 * preserve their original business data after recognition and decoding.</p>
 */
public class CompleteComplexBarcodeExamples {

    private static final String FOLDER =
            ExampleAssist.getOrCreateResourceFolderPath(
                    "complex", "complete-examples");

    /**
     * Initializes the Aspose.BarCode license before running the complete examples.
     */
    @BeforeClass
    public void setUp() {
        LicenseAssist.setupLicense();
    }

    /**
     * Demonstrates a complete Swiss QR workflow from business data to a decoded
     * payment object.
     *
     * <p>The example constructs the payment model, generates a PNG image, recognizes
     * its QR carrier, decodes the standardized text, and validates the restored
     * account, amount, and creditor fields.</p>
     */
    @Test
    public void generateAndReadComplexBarcode() throws Exception {
        SwissQRCodetext sourceCodetext = createSwissQRCodetext();
        String outputPath = ExampleAssist.pathCombine(FOLDER, "complete_swiss_qr.png");

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
     * Demonstrates how to process multiple complex barcodes contained in one image.
     *
     * <p>The example places a Swiss QR symbol and an HIBC QR LIC symbol into a
     * single composite image, reads all supported barcode types, identifies each
     * recognized carrier, and applies the appropriate complex codetext decoder.</p>
     */
    @Test
    public void processMultipleComplexBarcodes() throws Exception {
        SwissQRCodetext swissCodetext = createSwissQRCodetext();
        HIBCLICCombinedCodetext hibcCodetext = createHIBCLICCodetext();

        BufferedImage swissImage = new ComplexBarcodeGenerator(swissCodetext).generateBarCodeImage();
        BufferedImage hibcImage = new ComplexBarcodeGenerator(hibcCodetext).generateBarCodeImage();
        int spacing = 30;
        int width = Math.max(swissImage.getWidth(), hibcImage.getWidth());
        int height = swissImage.getHeight() + spacing + hibcImage.getHeight();
        BufferedImage combinedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        Graphics2D graphics = combinedImage.createGraphics();
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, width, height);
        graphics.drawImage(swissImage, 0, 0, null);
        graphics.drawImage(hibcImage, 0, swissImage.getHeight() + spacing, null);

        String outputPath = ExampleAssist.pathCombine(FOLDER, "multiple_complex_barcodes.png");
        ImageIO.write(combinedImage, "png", new File(outputPath));
        ExampleAssist.assertFileCreated(outputPath);

        BarCodeReader reader = new BarCodeReader(outputPath, DecodeType.ALL_SUPPORTED_TYPES);
        BarCodeResult[] results = reader.readBarCodes();
        Assert.assertEquals(results.length, 2);

        boolean swissDecoded = false;
        boolean hibcDecoded = false;
        for (BarCodeResult result : results) {
            if (result.getCodeType().equals(DecodeType.QR)) {
                swissDecoded = ComplexCodetextReader.tryDecodeSwissQR(result.getCodeText()) != null;
            } else if (result.getCodeType().equals(DecodeType.HIBCQRLIC)) {
                hibcDecoded = ComplexCodetextReader.tryDecodeHIBCLIC(result.getCodeText()) != null;
            }
        }

        Assert.assertTrue(swissDecoded);
        Assert.assertTrue(hibcDecoded);
    }

    /**
     * Demonstrates how to verify generated complex barcode data with a round-trip
     * comparison.
     *
     * <p>The test generates and recognizes an HIBC LIC symbol, decodes the result,
     * and compares the original and restored primary and secondary business fields.
     * This pattern can be used in automated regression tests for document output.</p>
     */
    @Test
    public void verifyGeneratedComplexBarcodeData() throws Exception {
        HIBCLICCombinedCodetext sourceCodetext = createHIBCLICCodetext();
        String outputPath = ExampleAssist.pathCombine(FOLDER, "verified_hibc_lic.png");
        ComplexBarcodeGenerator generator = new ComplexBarcodeGenerator(sourceCodetext);
        generator.save(outputPath, BarCodeImageFormat.PNG);

        BarCodeReader reader = new BarCodeReader(outputPath, DecodeType.HIBCQRLIC);
        BarCodeResult[] results = reader.readBarCodes();
        HIBCLICCombinedCodetext decodedCodetext =
                (HIBCLICCombinedCodetext) ComplexCodetextReader.tryDecodeHIBCLIC(
                        results[0].getCodeText());

        Assert.assertNotNull(decodedCodetext);
        Assert.assertEquals(decodedCodetext.getPrimaryData(), sourceCodetext.getPrimaryData());
        Assert.assertEquals(
                decodedCodetext.getSecondaryAndAdditionalData(),
                sourceCodetext.getSecondaryAndAdditionalData());
    }

    /**
     * Creates the Swiss QR model used by the complete examples.
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
     * Creates a structured Swiss address for the complete examples.
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
     * Creates the HIBC LIC model used by the complete examples.
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
}
