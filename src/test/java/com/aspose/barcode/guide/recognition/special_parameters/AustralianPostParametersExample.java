package com.aspose.barcode.guide.recognition.special_parameters;

import com.aspose.barcode.barcoderecognition.*;
import com.aspose.barcode.generation.*;
import com.aspose.barcode.guide.common.ExampleAssist;
import com.aspose.barcode.guide.common.LicenseAssist;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.*;

import static com.aspose.barcode.guide.common.ExampleAssist.checkOrCreateImage;

/**
 * Demonstrates how to generate and recognize AustralianPost barcodes
 * using different Customer Information Interpreting Types:
 * C_TABLE, N_TABLE, and OTHER.
 * <p>
 * The Customer Information field defines how encoded data in
 * AustralianPost barcodes should be interpreted — as alphanumeric,
 * numeric-only, or raw binary (OTHER).
 */
public class AustralianPostParametersExample {

    private static final String IMAGES_FOLDER =
            ExampleAssist.getOrCreateResourceFolderPath("recognition", "special_parameters", "australian_post");

    @BeforeClass
    public void setUp() throws Exception {
        LicenseAssist.setupLicense();
        Files.createDirectories(Paths.get(IMAGES_FOLDER));
    }

    // ===============================================================
    // --- Example 1: C_TABLE interpretation (alphanumeric) ---
    // ===============================================================
    /**
     * Demonstrates reading an AustralianPost barcode encoded using
     * the C_TABLE interpretation type. This mode allows letters,
     * digits, spaces, and the '#' symbol.
     */
    @Test
    public void readAustralianPostCTable() throws Exception {
        checkOrCreateImage(IMAGES_FOLDER, "australian_post_ctable.png", this::generateCTable);

        BarCodeReader reader = new BarCodeReader(getFullPath("australian_post_ctable.png"), DecodeType.AUSTRALIA_POST);
        reader.getBarcodeSettings()
                .getAustraliaPost()
                .setCustomerInformationInterpretingType(CustomerInformationInterpretingType.C_TABLE);

        ExampleAssist.assertRecognized(reader, "australian_post_ctable.png", 1, DecodeType.AUSTRALIA_POST);
    }

    // ===============================================================
    // --- Example 2: N_TABLE interpretation (numeric) ---
    // ===============================================================
    /**
     * Demonstrates reading an AustralianPost barcode encoded using
     * the N_TABLE interpretation type. This mode supports digits only.
     */
    @Test
    public void readAustralianPostNTable() throws Exception {
        checkOrCreateImage(IMAGES_FOLDER, "australian_post_ntable.png", this::generateNTable);

        BarCodeReader reader = new BarCodeReader(getFullPath("australian_post_ntable.png"), DecodeType.AUSTRALIA_POST);
        reader.getBarcodeSettings()
                .getAustraliaPost()
                .setCustomerInformationInterpretingType(CustomerInformationInterpretingType.N_TABLE);

        ExampleAssist.assertRecognized(reader, "australian_post_ntable.png", 1, DecodeType.AUSTRALIA_POST);
    }

    // ===============================================================
    // --- Example 3: OTHER interpretation (raw data) ---
    // ===============================================================
    /**
     * Demonstrates reading an AustralianPost barcode encoded using
     * the OTHER interpretation type. This mode disables interpretation
     * and allows 0–3 symbolic characters for raw use cases.
     */
    @Test
    public void readAustralianPostOther() throws Exception {
        checkOrCreateImage(IMAGES_FOLDER, "australian_post_other.png", this::generateOther);

        BarCodeReader reader = new BarCodeReader(getFullPath("australian_post_other.png"), DecodeType.AUSTRALIA_POST);
        reader.getBarcodeSettings()
                .getAustraliaPost()
                .setCustomerInformationInterpretingType(CustomerInformationInterpretingType.OTHER);

        ExampleAssist.assertRecognized(reader, "australian_post_other.png", 1, DecodeType.AUSTRALIA_POST);
    }

    // ===============================================================
    // --- Helpers ---
    // ===============================================================

    private String getFullPath(String fileName) {
        return Paths.get(IMAGES_FOLDER, fileName).toString();
    }

    // ===============================================================
    // --- Image Generators ---
    // ===============================================================

    private void generateCTable(String path) throws IOException {
        BarcodeGenerator generator = new BarcodeGenerator(EncodeTypes.AUSTRALIA_POST, "5912345678ABCde");
        generator.getParameters()
                .getBarcode()
                .getAustralianPost()
                .setAustralianPostEncodingTable(CustomerInformationInterpretingType.C_TABLE);
        generator.save(path, BarCodeImageFormat.PNG);
    }

    private void generateNTable(String path) throws IOException {
        BarcodeGenerator generator = new BarcodeGenerator(EncodeTypes.AUSTRALIA_POST, "59123456781234567");
        generator.getParameters()
                .getBarcode()
                .getAustralianPost()
                .setAustralianPostEncodingTable(CustomerInformationInterpretingType.N_TABLE);
        generator.save(path, BarCodeImageFormat.PNG);
    }

    private void generateOther(String path) throws IOException {
        BarcodeGenerator generator = new BarcodeGenerator(EncodeTypes.AUSTRALIA_POST, "59123456780123012301230123");
        generator.getParameters()
                .getBarcode()
                .getAustralianPost()
                .setAustralianPostEncodingTable(CustomerInformationInterpretingType.OTHER);
        generator.save(path, BarCodeImageFormat.PNG);
    }
}
