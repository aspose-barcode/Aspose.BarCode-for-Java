package com.aspose.barcode.guide.complex.supported_types.usa_driver_id;

import com.aspose.barcode.barcoderecognition.BarCodeReader;
import com.aspose.barcode.barcoderecognition.BarCodeResult;
import com.aspose.barcode.barcoderecognition.DecodeType;
import com.aspose.barcode.complexbarcode.ComplexBarcodeGenerator;
import com.aspose.barcode.complexbarcode.ComplexCodetextReader;
import com.aspose.barcode.complexbarcode.USADriveIdCodetext;
import com.aspose.barcode.complexbarcode.USADriveIdCountry;
import com.aspose.barcode.complexbarcode.USADriveIdEyeColor;
import com.aspose.barcode.complexbarcode.USADriveIdHairColor;
import com.aspose.barcode.complexbarcode.USADriveIdSex;
import com.aspose.barcode.generation.BarCodeImageFormat;
import com.aspose.barcode.guide.common.ExampleAssist;
import com.aspose.barcode.guide.common.LicenseAssist;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.LocalDate;
import java.util.List;

/**
 * Demonstrates how to generate and read AAMVA USA Driver ID data stored in a
 * PDF417 barcode with Aspose.BarCode for Java.
 *
 * <p>The examples populate the AAMVA header, mandatory driver-license fields,
 * and selected optional fields. They generate the PDF417 carrier image and then
 * decode the recognized payload back into typed mandatory and optional data.</p>
 */
public class USADriverIDBarcodes {

    private static final String FOLDER =
            ExampleAssist.getOrCreateResourceFolderPath("complex", "usa-driver-id");

    /**
     * Initializes the Aspose.BarCode license before running the USA Driver ID
     * examples.
     */
    @BeforeClass
    public void setUp() {
        LicenseAssist.setupLicense();
    }

    /**
     * Demonstrates how to create an AAMVA USA Driver ID model and generate its
     * PDF417 barcode image.
     *
     * <p>The test fills the issuer and version fields, assigns the mandatory card
     * data, adds optional hair-color and organ-donor information, and verifies
     * that a PDF417 image is produced.</p>
     */
    @Test
    public void generateDriverIDPDF417Barcode() throws Exception {
        USADriveIdCodetext codetext = createUSADriverIDCodetext();
        String outputPath = ExampleAssist.pathCombine(FOLDER, "usa_driver_id.png");

        ComplexBarcodeGenerator generator = new ComplexBarcodeGenerator(codetext);
        generator.save(outputPath, BarCodeImageFormat.PNG);

        ExampleAssist.assertFileCreated(outputPath);
        BarCodeReader reader = new BarCodeReader(outputPath, DecodeType.PDF_417);
        BarCodeResult[] results = reader.readBarCodes();
        Assert.assertEquals(results.length, 1);
        Assert.assertEquals(results[0].getCodeType(), DecodeType.PDF_417);
    }

    /**
     * Demonstrates how to recognize and decode AAMVA driver-license data from a
     * PDF417 barcode.
     *
     * <p>The test restores the typed USA Driver ID object and verifies issuer,
     * version, identity, document, address, physical-description, and optional
     * cardholder fields.</p>
     */
    @Test
    public void readAAMVADriverLicenseData() throws Exception {
        USADriveIdCodetext sourceCodetext = createUSADriverIDCodetext();
        String outputPath = ExampleAssist.pathCombine(FOLDER, "usa_driver_id_for_reading.png");
        ComplexBarcodeGenerator generator = new ComplexBarcodeGenerator(sourceCodetext);
        generator.save(outputPath, BarCodeImageFormat.PNG);

        BarCodeReader reader = new BarCodeReader(outputPath, DecodeType.PDF_417);
        BarCodeResult[] results = reader.readBarCodes();
        Assert.assertEquals(results.length, 1);

        USADriveIdCodetext decodedCodetext =
                ComplexCodetextReader.tryDecodeUSADriveId(results[0].getCodeText());

        Assert.assertNotNull(decodedCodetext);
        Assert.assertEquals(decodedCodetext.getIssuerIdentificationNumber(), "636026");
        Assert.assertEquals(decodedCodetext.getAAMVAVersionNumber(), "08");
        Assert.assertEquals(decodedCodetext.getMandatoryElements().getFamilyName(), "DOE");
        Assert.assertEquals(decodedCodetext.getMandatoryElements().getFirstName(), "JOHN");
        Assert.assertEquals(decodedCodetext.getMandatoryElements().getCustomerIDNumber(), "D1234567");
        Assert.assertEquals(decodedCodetext.getMandatoryElements().getAddressState(), "CA");
        Assert.assertEquals(decodedCodetext.getMandatoryElements().getEyeColor(), USADriveIdEyeColor.BROWN);
        Assert.assertEquals(decodedCodetext.getOptionalElements().getHairColor(), USADriveIdHairColor.BROWN);
        Assert.assertEquals(decodedCodetext.getOptionalElements().getOrganDonorIndicator(), "1");
    }

    /**
     * Creates the AAMVA USA Driver ID model used by both tests.
     */
    private USADriveIdCodetext createUSADriverIDCodetext() {
        USADriveIdCodetext codetext = new USADriveIdCodetext();
        codetext.setIssuerIdentificationNumber("636026");
        codetext.setAAMVAVersionNumber("08");
        codetext.setJurisdictionVersionNumber("00");
        codetext.setNumberOfEntries(1);
        codetext.setSubfileDesignator(List.of(
                new USADriveIdCodetext.SubfileProperties("DL")));

        USADriveIdCodetext.MandatoryFields mandatoryFields =
                new USADriveIdCodetext.MandatoryFields();
        mandatoryFields.setVehicleClass("C");
        mandatoryFields.setRestrictionCodes("NONE");
        mandatoryFields.setEndorsementCodes("NONE");
        mandatoryFields.setExpirationDate(LocalDate.of(2030, 12, 31));
        mandatoryFields.setFamilyName("DOE");
        mandatoryFields.setFirstName("JOHN");
        mandatoryFields.setMiddleName("Q");
        mandatoryFields.setIssueDate(LocalDate.of(2026, 1, 1));
        mandatoryFields.setDateOfBirth(LocalDate.of(1990, 5, 20));
        mandatoryFields.setSex(USADriveIdSex.MALE);
        mandatoryFields.setEyeColor(USADriveIdEyeColor.BROWN);
        mandatoryFields.setHeight("070 in");
        mandatoryFields.setAddressStreet1("123 MAIN STREET");
        mandatoryFields.setAddressCity("ANYTOWN");
        mandatoryFields.setAddressState("CA");
        mandatoryFields.setAddressPostalCode("90210");
        mandatoryFields.setCustomerIDNumber("D1234567");
        mandatoryFields.setDocumentDiscriminator("DOC123456789");
        mandatoryFields.setCountryIdentification(USADriveIdCountry.US);
        mandatoryFields.setFamilyNameTruncation("N");
        mandatoryFields.setFirstNameTruncation("N");
        mandatoryFields.setMiddleNameTruncation("N");
        codetext.setMandatoryElements(mandatoryFields);

        USADriveIdCodetext.OptionalFields optionalFields =
                new USADriveIdCodetext.OptionalFields();
        optionalFields.setHairColor(USADriveIdHairColor.BROWN);
        optionalFields.setOrganDonorIndicator("1");
        codetext.setOptionalElements(optionalFields);
        return codetext;
    }
}
