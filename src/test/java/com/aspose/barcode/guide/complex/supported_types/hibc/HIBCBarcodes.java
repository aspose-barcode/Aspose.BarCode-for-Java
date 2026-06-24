package com.aspose.barcode.guide.complex.supported_types.hibc;

import com.aspose.barcode.barcoderecognition.BarCodeReader;
import com.aspose.barcode.barcoderecognition.BarCodeResult;
import com.aspose.barcode.barcoderecognition.DecodeType;
import com.aspose.barcode.complexbarcode.ComplexBarcodeGenerator;
import com.aspose.barcode.complexbarcode.ComplexCodetextReader;
import com.aspose.barcode.complexbarcode.HIBCLICCombinedCodetext;
import com.aspose.barcode.complexbarcode.HIBCLICComplexCodetext;
import com.aspose.barcode.complexbarcode.HIBCLICDateFormat;
import com.aspose.barcode.complexbarcode.HIBCPASCodetext;
import com.aspose.barcode.complexbarcode.HIBCPASDataLocation;
import com.aspose.barcode.complexbarcode.HIBCPASDataType;
import com.aspose.barcode.complexbarcode.PrimaryData;
import com.aspose.barcode.complexbarcode.SecondaryAndAdditionalData;
import com.aspose.barcode.generation.BarCodeImageFormat;
import com.aspose.barcode.generation.EncodeTypes;
import com.aspose.barcode.guide.common.ExampleAssist;
import com.aspose.barcode.guide.common.LicenseAssist;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.LocalDateTime;

/**
 * Demonstrates HIBC LIC and HIBC PAS complex barcode workflows with
 * Aspose.BarCode for Java.
 *
 * <p>The LIC example combines product identification with secondary production
 * data such as lot, serial number, quantity, manufacture date, and expiry date.
 * The PAS example stores healthcare process records with typed data identifiers
 * and a defined data location. Both examples generate and decode their payloads.</p>
 */
public class HIBCBarcodes {

    private static final String FOLDER =
            ExampleAssist.getOrCreateResourceFolderPath("complex", "hibc");

    /**
     * Initializes the Aspose.BarCode license before running the HIBC examples.
     */
    @BeforeClass
    public void setUp() {
        LicenseAssist.setupLicense();
    }

    /**
     * Demonstrates how to generate and read a combined HIBC LIC barcode.
     *
     * <p>The test creates primary product data and secondary production data,
     * encodes them in an HIBC QR LIC symbol, recognizes the image, and restores
     * the typed fields with the HIBC LIC decoder.</p>
     */
    @Test
    public void generateAndReadHIBCLIC() throws Exception {
        HIBCLICCombinedCodetext sourceCodetext = createHIBCLICCodetext();
        String outputPath = ExampleAssist.pathCombine(FOLDER, "hibc_lic.png");

        ComplexBarcodeGenerator generator = new ComplexBarcodeGenerator(sourceCodetext);
        generator.save(outputPath, BarCodeImageFormat.PNG);

        ExampleAssist.assertFileCreated(outputPath);
        BarCodeReader reader = new BarCodeReader(outputPath, DecodeType.HIBCQRLIC);
        BarCodeResult[] results = reader.readBarCodes();
        Assert.assertEquals(results.length, 1);

        HIBCLICComplexCodetext decodedBase =
                ComplexCodetextReader.tryDecodeHIBCLIC(results[0].getCodeText());
        Assert.assertTrue(decodedBase instanceof HIBCLICCombinedCodetext);

        HIBCLICCombinedCodetext decodedCodetext = (HIBCLICCombinedCodetext) decodedBase;
        Assert.assertEquals(decodedCodetext.getPrimaryData().getLabelerIdentificationCode(), "A999");
        Assert.assertEquals(decodedCodetext.getPrimaryData().getProductOrCatalogNumber(), "12345");
        Assert.assertEquals(decodedCodetext.getSecondaryAndAdditionalData().getLotNumber(), "LOT123");
        Assert.assertEquals(decodedCodetext.getSecondaryAndAdditionalData().getSerialNumber(), "SERIAL123");
        Assert.assertEquals(decodedCodetext.getSecondaryAndAdditionalData().getQuantity(), 30);
    }

    /**
     * Demonstrates how to generate and read an HIBC PAS barcode.
     *
     * <p>The test assigns the patient data location, adds labeler and serial-number
     * records with HIBC PAS data type identifiers, encodes them in Data Matrix,
     * and verifies the restored record collection after recognition.</p>
     */
    @Test
    public void generateAndReadHIBCPAS() throws Exception {
        HIBCPASCodetext sourceCodetext = createHIBCPASCodetext();
        String outputPath = ExampleAssist.pathCombine(FOLDER, "hibc_pas.png");

        ComplexBarcodeGenerator generator = new ComplexBarcodeGenerator(sourceCodetext);
        generator.save(outputPath, BarCodeImageFormat.PNG);

        ExampleAssist.assertFileCreated(outputPath);
        BarCodeReader reader = new BarCodeReader(outputPath, DecodeType.HIBC_DATA_MATRIX_PAS);
        BarCodeResult[] results = reader.readBarCodes();
        Assert.assertEquals(results.length, 1);

        HIBCPASCodetext decodedCodetext =
                ComplexCodetextReader.tryDecodeHIBCPAS(results[0].getCodeText());

        Assert.assertNotNull(decodedCodetext);
        Assert.assertEquals(decodedCodetext.getDataLocation(), HIBCPASDataLocation.PATIENT);
        Assert.assertEquals(decodedCodetext.getRecords().size(), 2);
        Assert.assertEquals(decodedCodetext.getRecords().get(0).getData(), "A123");
        Assert.assertEquals(decodedCodetext.getRecords().get(1).getData(), "SERIAL123");
    }

    /**
     * Creates a combined HIBC LIC model with primary and secondary data.
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
     * Creates an HIBC PAS model with two typed healthcare records.
     */
    private HIBCPASCodetext createHIBCPASCodetext() {
        HIBCPASCodetext codetext = new HIBCPASCodetext();
        codetext.setDataLocation(HIBCPASDataLocation.PATIENT);
        codetext.addRecord(HIBCPASDataType.LABELER_IDENTIFICATION_CODE, "A123");
        codetext.addRecord(HIBCPASDataType.MANUFACTURER_SERIAL_NUMBER, "SERIAL123");
        codetext.setBarcodeType(EncodeTypes.HIBC_DATA_MATRIX_PAS);
        return codetext;
    }
}
