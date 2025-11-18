package com.aspose.barcode.guide.generation.checksum;

import com.aspose.barcode.barcoderecognition.DecodeType;
import com.aspose.barcode.generation.*;
import com.aspose.barcode.guide.common.ExampleAssist;
import com.aspose.barcode.guide.common.LicenseAssist;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

import static com.aspose.barcode.guide.common.ExampleAssist.assertImageHasBarcodes;
import static com.aspose.barcode.guide.common.ExampleAssist.expected;

/**
 * Checksum demos for symbologies where checksum is OPTIONAL:
 * turning it ON vs. OFF changes the rendered/decoded CodeText.
 *
 * Covered symbologies here:
 *  - Code 39 (Standard)
 *  - Codabar
 *  - Code 11
 *  - Standard 2 of 5
 *  - Interleaved 2 of 5
 *  - Matrix 2 of 5
 *  - IATA 2 of 5
 *  - MSI
 *  - ITF-14 (checksum exists but is not enforced by the engine in many builds)
 *
 * Notes:
 *  - API names can vary slightly between builds. If your SDK exposes different properties
 *    for enabling/disabling checksum, adjust the lines with EnableChecksum accordingly.
 */
public class OptionalChecksumExamples {

    private static final String FOLDER =
            ExampleAssist.getOrCreateResourceFolderPath("generation", "checksum", "optional");

    @BeforeClass
    public void setUp() throws Exception {
        LicenseAssist.setupLicense();
    }

    // ---------- Code 39 (Standard) ----------

    /**
     * Code 39: checksum ON — a Mod43 check digit is appended.
     * We assert by prefix, because the last character is calculated by the engine.
     */
    @Test
    public void code39_checksum_on() throws Exception {
        String payload = "C39DATA";
        BarcodeGenerator generator = new BarcodeGenerator(EncodeTypes.CODE_39, payload);
        generator.getParameters().getBarcode().getChecksum().setEnableChecksum(EnableChecksum.YES);

        generator.getParameters().getBarcode().getXDimension().setPixels(3.0f);
        generator.getParameters().getBarcode().getBarHeight().setPixels(80);
        generator.getParameters().getImageWidth().setPixels(420);
        generator.getParameters().getImageHeight().setPixels(180);

        String out = ExampleAssist.pathCombine(FOLDER, "code39_on.png");
        generator.save(out, BarCodeImageFormat.PNG);
        ExampleAssist.assertFileCreated(out);

        assertImageHasBarcodes(out, 1, List.of(ExampleAssist.expectedPrefix(DecodeType.CODE_39, payload)));
    }

    /**
     * Code 39: checksum OFF — CodeText equals the input payload.
     */
    @Test
    public void code39_checksum_off() throws Exception {
        String payload = "C39DATA";
        BarcodeGenerator generator = new BarcodeGenerator(EncodeTypes.CODE_39, payload);
        generator.getParameters().getBarcode().getChecksum().setEnableChecksum(EnableChecksum.NO);

        generator.getParameters().getBarcode().getXDimension().setPixels(3.0f);
        generator.getParameters().getBarcode().getBarHeight().setPixels(80);
        generator.getParameters().getImageWidth().setPixels(420);
        generator.getParameters().getImageHeight().setPixels(180);

        String out = ExampleAssist.pathCombine(FOLDER, "code39_off.png");
        generator.save(out, BarCodeImageFormat.PNG);
        ExampleAssist.assertFileCreated(out);

        assertImageHasBarcodes(out, 1, List.of(expected(DecodeType.CODE_39, payload)));
    }

    // ---------- Codabar ----------

    /**
     * Codabar: checksum ON — engine appends a check digit (depending on implementation).
     */
    @Test
    public void codabar_checksum_on() throws Exception {
        String payload = "A123456A"; // Codabar typically needs start/stop chars (A/B/C/D)
        BarcodeGenerator generator = new BarcodeGenerator(EncodeTypes.CODABAR, payload);
        generator.getParameters().getBarcode().getChecksum().setEnableChecksum(EnableChecksum.YES);

        generator.getParameters().getBarcode().getXDimension().setPixels(3.0f);
        generator.getParameters().getBarcode().getBarHeight().setPixels(80);
        generator.getParameters().getImageWidth().setPixels(420);
        generator.getParameters().getImageHeight().setPixels(180);

        String out = ExampleAssist.pathCombine(FOLDER, "codabar_on.png");
        generator.save(out, BarCodeImageFormat.PNG);
        ExampleAssist.assertFileCreated(out);

        assertImageHasBarcodes(out, 1, List.of(ExampleAssist.expectedPrefix(DecodeType.CODABAR, "A123456")));
    }

    /**
     * Codabar: checksum OFF — CodeText equals input, start/stop as provided.
     */
    @Test
    public void codabar_checksum_off() throws Exception {
        String payload = "A123456A";
        BarcodeGenerator generator = new BarcodeGenerator(EncodeTypes.CODABAR, payload);
        generator.getParameters().getBarcode().getChecksum().setEnableChecksum(EnableChecksum.NO);

        generator.getParameters().getBarcode().getXDimension().setPixels(3.0f);
        generator.getParameters().getBarcode().getBarHeight().setPixels(80);
        generator.getParameters().getImageWidth().setPixels(420);
        generator.getParameters().getImageHeight().setPixels(180);

        String out = ExampleAssist.pathCombine(FOLDER, "codabar_off.png");
        generator.save(out, BarCodeImageFormat.PNG);
        ExampleAssist.assertFileCreated(out);

        assertImageHasBarcodes(out, 1, List.of(expected(DecodeType.CODABAR, payload)));
    }

    // ---------- Code 11 ----------

    /**
     * Code 11: checksum ON — appends check digit(s). We assert by prefix.
     */
    @Test
    public void code11_checksum_on() throws Exception {
        String payload = "12345";
        BarcodeGenerator generator = new BarcodeGenerator(EncodeTypes.CODE_11, payload);
        generator.getParameters().getBarcode().getChecksum().setEnableChecksum(EnableChecksum.YES);

        generator.getParameters().getBarcode().getXDimension().setPixels(3.0f);
        generator.getParameters().getBarcode().getBarHeight().setPixels(80);
        generator.getParameters().getImageWidth().setPixels(420);
        generator.getParameters().getImageHeight().setPixels(180);

        String out = ExampleAssist.pathCombine(FOLDER, "code11_on.png");
        generator.save(out, BarCodeImageFormat.PNG);
        ExampleAssist.assertFileCreated(out);

        assertImageHasBarcodes(out, 1, List.of(ExampleAssist.expectedPrefix(DecodeType.CODE_11, payload)));
    }

    /**
     * Code 11: checksum OFF — CodeText equals input payload.
     */
    @Test
    public void code11_checksum_off() throws Exception {
        String payload = "12345";
        BarcodeGenerator generator = new BarcodeGenerator(EncodeTypes.CODE_11, payload);
        generator.getParameters().getBarcode().getChecksum().setEnableChecksum(EnableChecksum.NO);

        generator.getParameters().getBarcode().getXDimension().setPixels(3.0f);
        generator.getParameters().getBarcode().getBarHeight().setPixels(80);
        generator.getParameters().getImageWidth().setPixels(420);
        generator.getParameters().getImageHeight().setPixels(180);

        String out = ExampleAssist.pathCombine(FOLDER, "code11_off.png");
        generator.save(out, BarCodeImageFormat.PNG);
        ExampleAssist.assertFileCreated(out);

        assertImageHasBarcodes(out, 1, List.of(expected(DecodeType.CODE_11, payload)));
    }

    // ---------- Standard 2 of 5 ----------

    @Test
    public void standard2of5_checksum_on() throws Exception {
        String payload = "123456";
        BarcodeGenerator generator = new BarcodeGenerator(EncodeTypes.STANDARD_2_OF_5, payload);
        generator.getParameters().getBarcode().getChecksum().setEnableChecksum(EnableChecksum.YES);

        generator.getParameters().getBarcode().getXDimension().setPixels(3.0f);
        generator.getParameters().getBarcode().getBarHeight().setPixels(90);
        generator.getParameters().getImageWidth().setPixels(420);
        generator.getParameters().getImageHeight().setPixels(180);

        String out = ExampleAssist.pathCombine(FOLDER, "std25_on.png");
        generator.save(out, BarCodeImageFormat.PNG);
        ExampleAssist.assertFileCreated(out);

        assertImageHasBarcodes(out, 1, List.of(ExampleAssist.expectedPrefix(DecodeType.STANDARD_2_OF_5, payload)));
    }

    @Test
    public void standard2of5_checksum_off() throws Exception {
        String payload = "123456";
        BarcodeGenerator generator = new BarcodeGenerator(EncodeTypes.STANDARD_2_OF_5, payload);
        generator.getParameters().getBarcode().getChecksum().setEnableChecksum(EnableChecksum.NO);

        generator.getParameters().getBarcode().getXDimension().setPixels(3.0f);
        generator.getParameters().getBarcode().getBarHeight().setPixels(90);
        generator.getParameters().getImageWidth().setPixels(420);
        generator.getParameters().getImageHeight().setPixels(180);

        String out = ExampleAssist.pathCombine(FOLDER, "std25_off.png");
        generator.save(out, BarCodeImageFormat.PNG);
        ExampleAssist.assertFileCreated(out);

        assertImageHasBarcodes(out, 1, List.of(expected(DecodeType.STANDARD_2_OF_5, payload)));
    }

    // ---------- Interleaved 2 of 5 (дополнительно к вашим примерам) ----------

    @Test
    public void interleaved2of5_checksum_on() throws Exception {
        String payload = "123456"; // even
        BarcodeGenerator generator = new BarcodeGenerator(EncodeTypes.INTERLEAVED_2_OF_5, payload);
        generator.getParameters().getBarcode().getChecksum().setEnableChecksum(EnableChecksum.YES);

        generator.getParameters().getBarcode().getXDimension().setPixels(3.0f);
        generator.getParameters().getBarcode().getBarHeight().setPixels(90);
        generator.getParameters().getImageWidth().setPixels(420);
        generator.getParameters().getImageHeight().setPixels(180);

        String out = ExampleAssist.pathCombine(FOLDER, "itf_on.png");
        generator.save(out, BarCodeImageFormat.PNG);
        ExampleAssist.assertFileCreated(out);

        assertImageHasBarcodes(out, 1, List.of(ExampleAssist.expectedPrefix(DecodeType.INTERLEAVED_2_OF_5, payload)));
    }

    @Test
    public void interleaved2of5_checksum_off() throws Exception {
        String payload = "123456";
        BarcodeGenerator generator = new BarcodeGenerator(EncodeTypes.INTERLEAVED_2_OF_5, payload);
        generator.getParameters().getBarcode().getChecksum().setEnableChecksum(EnableChecksum.NO);

        generator.getParameters().getBarcode().getXDimension().setPixels(3.0f);
        generator.getParameters().getBarcode().getBarHeight().setPixels(90);
        generator.getParameters().getImageWidth().setPixels(420);
        generator.getParameters().getImageHeight().setPixels(180);

        String out = ExampleAssist.pathCombine(FOLDER, "itf_off.png");
        generator.save(out, BarCodeImageFormat.PNG);
        ExampleAssist.assertFileCreated(out);

        assertImageHasBarcodes(out, 1, List.of(expected(DecodeType.INTERLEAVED_2_OF_5, payload)));
    }

    // ---------- Matrix 2 of 5 ----------

    @Test
    public void matrix2of5_checksum_on() throws Exception {
        String payload = "123456";
        BarcodeGenerator generator = new BarcodeGenerator(EncodeTypes.MATRIX_2_OF_5, payload);
        generator.getParameters().getBarcode().getChecksum().setEnableChecksum(EnableChecksum.YES);

        generator.getParameters().getBarcode().getXDimension().setPixels(3.0f);
        generator.getParameters().getBarcode().getBarHeight().setPixels(90);
        generator.getParameters().getImageWidth().setPixels(420);
        generator.getParameters().getImageHeight().setPixels(180);

        String out = ExampleAssist.pathCombine(FOLDER, "m25_on.png");
        generator.save(out, BarCodeImageFormat.PNG);
        ExampleAssist.assertFileCreated(out);

        assertImageHasBarcodes(out, 1, List.of(ExampleAssist.expectedPrefix(DecodeType.MATRIX_2_OF_5, payload)));
    }

    @Test
    public void matrix2of5_checksum_off() throws Exception {
        String payload = "123456";
        BarcodeGenerator generator = new BarcodeGenerator(EncodeTypes.MATRIX_2_OF_5, payload);
        generator.getParameters().getBarcode().getChecksum().setEnableChecksum(EnableChecksum.NO);

        generator.getParameters().getBarcode().getXDimension().setPixels(3.0f);
        generator.getParameters().getBarcode().getBarHeight().setPixels(90);
        generator.getParameters().getImageWidth().setPixels(420);
        generator.getParameters().getImageHeight().setPixels(180);

        String out = ExampleAssist.pathCombine(FOLDER, "m25_off.png");
        generator.save(out, BarCodeImageFormat.PNG);
        ExampleAssist.assertFileCreated(out);

        assertImageHasBarcodes(out, 1, List.of(expected(DecodeType.MATRIX_2_OF_5, payload)));
    }

    // ---------- IATA 2 of 5 ----------

    @Test
    public void iata2of5_checksum_on() throws Exception {
        String payload = "123456";
        BarcodeGenerator generator = new BarcodeGenerator(EncodeTypes.IATA_2_OF_5, payload);
        generator.getParameters().getBarcode().getChecksum().setEnableChecksum(EnableChecksum.YES);

        generator.getParameters().getBarcode().getXDimension().setPixels(3.0f);
        generator.getParameters().getBarcode().getBarHeight().setPixels(90);
        generator.getParameters().getImageWidth().setPixels(420);
        generator.getParameters().getImageHeight().setPixels(180);

        String out = ExampleAssist.pathCombine(FOLDER, "iata25_on.png");
        generator.save(out, BarCodeImageFormat.PNG);
        ExampleAssist.assertFileCreated(out);

        assertImageHasBarcodes(out, 1, List.of(ExampleAssist.expectedPrefix(DecodeType.IATA_2_OF_5, payload)));
    }

    @Test
    public void iata2of5_checksum_off() throws Exception {
        String payload = "123456";
        BarcodeGenerator generator = new BarcodeGenerator(EncodeTypes.IATA_2_OF_5, payload);
        generator.getParameters().getBarcode().getChecksum().setEnableChecksum(EnableChecksum.NO);

        generator.getParameters().getBarcode().getXDimension().setPixels(3.0f);
        generator.getParameters().getBarcode().getBarHeight().setPixels(90);
        generator.getParameters().getImageWidth().setPixels(420);
        generator.getParameters().getImageHeight().setPixels(180);

        String out = ExampleAssist.pathCombine(FOLDER, "iata25_off.png");
        generator.save(out, BarCodeImageFormat.PNG);
        ExampleAssist.assertFileCreated(out);

        assertImageHasBarcodes(out, 1, List.of(expected(DecodeType.IATA_2_OF_5, payload)));
    }

    // ---------- MSI (пара ON/OFF у вас уже есть, добавляем в этот класс для полноты) ----------

    @Test
    public void msi_checksum_on() throws Exception {
        String payload = "123456";
        BarcodeGenerator generator = new BarcodeGenerator(EncodeTypes.MSI, payload);
        generator.getParameters().getBarcode().getChecksum().setEnableChecksum(EnableChecksum.YES);

        generator.getParameters().getBarcode().getXDimension().setPixels(3.0f);
        generator.getParameters().getBarcode().getBarHeight().setPixels(80);
        generator.getParameters().getImageWidth().setPixels(420);
        generator.getParameters().getImageHeight().setPixels(180);

        String out = ExampleAssist.pathCombine(FOLDER, "msi_on.png");
        generator.save(out, BarCodeImageFormat.PNG);
        ExampleAssist.assertFileCreated(out);

        assertImageHasBarcodes(out, 1, List.of(ExampleAssist.expectedPrefix(DecodeType.MSI, payload)));
    }

    @Test
    public void msi_checksum_off() throws Exception {
        String payload = "123456";
        BarcodeGenerator generator = new BarcodeGenerator(EncodeTypes.MSI, payload);
        generator.getParameters().getBarcode().getChecksum().setEnableChecksum(EnableChecksum.NO);

        generator.getParameters().getBarcode().getXDimension().setPixels(3.0f);
        generator.getParameters().getBarcode().getBarHeight().setPixels(80);
        generator.getParameters().getImageWidth().setPixels(420);
        generator.getParameters().getImageHeight().setPixels(180);

        String out = ExampleAssist.pathCombine(FOLDER, "msi_off.png");
        generator.save(out, BarCodeImageFormat.PNG);
        ExampleAssist.assertFileCreated(out);

        assertImageHasBarcodes(out, 1, List.of(expected(DecodeType.MSI, payload)));
    }

    // ---------- ITF-14 (many builds treat checksum as optional) ----------

    @Test
    public void itf14_checksum_on() throws Exception {
        String payload = "1001234500001"; // engine will compute check digit if ON
        BarcodeGenerator generator = new BarcodeGenerator(EncodeTypes.ITF_14, payload);
        generator.getParameters().getBarcode().getChecksum().setEnableChecksum(EnableChecksum.YES);

        generator.getParameters().getBarcode().getXDimension().setPixels(2.0f);
        generator.getParameters().getBarcode().getBarHeight().setPixels(100);
        generator.getParameters().getImageWidth().setPixels(520);
        generator.getParameters().getImageHeight().setPixels(220);

        String out = ExampleAssist.pathCombine(FOLDER, "itf14_on.png");
        generator.save(out, BarCodeImageFormat.PNG);
        ExampleAssist.assertFileCreated(out);

        // Check by prefix — final digit is calculated
        assertImageHasBarcodes(out, 1, List.of(ExampleAssist.expectedPrefix(DecodeType.ITF_14, payload)));
    }

    @Test
    public void itf14_checksum_off() throws Exception {
        String full14 = "10012345000017"; // already with a proper check digit
        BarcodeGenerator generator = new BarcodeGenerator(EncodeTypes.ITF_14, full14);
        generator.getParameters().getBarcode().getChecksum().setEnableChecksum(EnableChecksum.NO);

        generator.getParameters().getBarcode().getXDimension().setPixels(2.0f);
        generator.getParameters().getBarcode().getBarHeight().setPixels(100);
        generator.getParameters().getImageWidth().setPixels(520);
        generator.getParameters().getImageHeight().setPixels(220);

        String out = ExampleAssist.pathCombine(FOLDER, "itf14_off.png");
        generator.save(out, BarCodeImageFormat.PNG);
        ExampleAssist.assertFileCreated(out);

        assertImageHasBarcodes(out, 1, List.of(expected(DecodeType.ITF_14, full14)));
    }
}
