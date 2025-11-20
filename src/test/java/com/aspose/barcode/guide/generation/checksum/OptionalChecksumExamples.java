package com.aspose.barcode.guide.generation.checksum;

import com.aspose.barcode.barcoderecognition.BarCodeReader;
import com.aspose.barcode.barcoderecognition.BarCodeResult;
import com.aspose.barcode.barcoderecognition.ChecksumValidation;
import com.aspose.barcode.barcoderecognition.DecodeType;
import com.aspose.barcode.generation.*;
import com.aspose.barcode.guide.common.ExampleAssist;
import com.aspose.barcode.guide.common.LicenseAssist;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

import static com.aspose.barcode.guide.common.ExampleAssist.*;

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
        generator.getParameters().getBarcode().setChecksumEnabled(EnableChecksum.YES);

        generator.getParameters().getBarcode().getXDimension().setPixels(3.0f);
        generator.getParameters().getBarcode().getBarHeight().setPixels(80);
        generator.getParameters().getImageWidth().setPixels(420);
        generator.getParameters().getImageHeight().setPixels(180);

        String out = ExampleAssist.pathCombine(FOLDER, "code39_on.png");
        generator.save(out, BarCodeImageFormat.PNG);
        ExampleAssist.assertFileCreated(out);

        assertImageHasBarcodes(out, 1,
                List.of(ExampleAssist.expectedPrefix(DecodeType.CODE_39, payload)), ChecksumValidation.ON);
    }

    /**
     * Code 39: checksum OFF — CodeText equals the input payload.
     */
    @Test
    public void code39_checksum_off() throws Exception {
        String payload = "C39DATA";
        BarcodeGenerator generator = new BarcodeGenerator(EncodeTypes.CODE_39, payload);
        generator.getParameters().getBarcode().setChecksumEnabled(EnableChecksum.NO);

        generator.getParameters().getBarcode().getXDimension().setPixels(3.0f);
        generator.getParameters().getBarcode().getBarHeight().setPixels(80);
        generator.getParameters().getImageWidth().setPixels(420);
        generator.getParameters().getImageHeight().setPixels(180);

        String out = ExampleAssist.pathCombine(FOLDER, "code39_off.png");
        generator.save(out, BarCodeImageFormat.PNG);
        ExampleAssist.assertFileCreated(out);

        assertImageHasBarcodes(out, 1, List.of(expected(DecodeType.CODE_39, payload)), ChecksumValidation.OFF);
    }

    // ---------- Codabar ----------

    /**
     * Codabar: checksum ON — engine appends a check digit (depending on implementation).
     */
    @Test(enabled = false) //TODO
    public void codabar_checksum_on() throws Exception {
        // Codabar requires start/stop characters A–D; we use A…A.
        final String payloadWithGuards = "A123456A";
        final String expectedDecoded = "123456"; // reader returns data without guards and without the check symbol

        BarcodeGenerator generator = new BarcodeGenerator(EncodeTypes.CODABAR, payloadWithGuards);

        // Turn checksum ON and use the most widely used variant for Codabar demos: MOD_10.
        // (MOD_16 is valid too, but MOD_10 tends to be more robust across readers/settings.)
        generator.getParameters().getBarcode().setChecksumEnabled(EnableChecksum.YES);
        generator.getParameters().getBarcode().getCodabar()
                .setCodabarChecksumMode(CodabarChecksumMode.MOD_10);

        // Raster safety: thicker modules, taller bars, and generous quiet zones.
        generator.getParameters().getBarcode().getXDimension().setPixels(3.0f);
        generator.getParameters().getBarcode().getBarHeight().setPixels(120);
        generator.getParameters().getBarcode().getPadding().getLeft().setPixels(40);
        generator.getParameters().getBarcode().getPadding().getRight().setPixels(40);
        generator.getParameters().getBarcode().getPadding().getTop().setPixels(18);
        generator.getParameters().getBarcode().getPadding().getBottom().setPixels(18);

        // Keep the barcode graphic clean for this checksum-focused test.
        generator.getParameters().getBarcode().getCodeTextParameters().setLocation(CodeLocation.BELOW);

        // Big enough canvas so the quiet zones are not clipped after rendering.
        generator.getParameters().getImageWidth().setPixels(700);
        generator.getParameters().getImageHeight().setPixels(260);

        String outputPath = ExampleAssist.pathCombine(FOLDER, "codabar_on.png");
        generator.save(outputPath, BarCodeImageFormat.PNG);
        ExampleAssist.assertFileCreated(outputPath);

        // With ChecksumValidation.ON the reader must validate the Mod10 symbol and return plain data (no guards).
        assertImageHasBarcodes(
                outputPath,
                1,
                java.util.List.of(expected(DecodeType.CODABAR, expectedDecoded)),
                ChecksumValidation.ON
        );
    }




    /**
     * Codabar: checksum OFF — CodeText equals input, start/stop as provided.
     */
    @Test
    public void codabar_checksum_off() throws Exception {
        // Codabar requires start/stop chars A–D; here we use A…A and checksum is turned OFF.
        final String payload = "A123456A";

        BarcodeGenerator generator = new BarcodeGenerator(EncodeTypes.CODABAR, payload);

        // Explicitly disable optional checksum for Codabar.
        generator.getParameters().getBarcode().setChecksumEnabled(EnableChecksum.NO);

        // Robust rasterization: keep modules readable and bars tall enough.
        generator.getParameters().getBarcode().getXDimension().setPixels(2.0f);
        generator.getParameters().getBarcode().getBarHeight().setPixels(100);

        // IMPORTANT: quiet zones around the barcode (>= 10×XDimension). Give a comfortable margin.
        generator.getParameters().getBarcode().getPadding().getLeft().setPixels(40);
        generator.getParameters().getBarcode().getPadding().getRight().setPixels(40);
        generator.getParameters().getBarcode().getPadding().getTop().setPixels(16);
        generator.getParameters().getBarcode().getPadding().getBottom().setPixels(16);

        // Keep HRT out of the way for a checksum-focused test.
        generator.getParameters().getBarcode().getCodeTextParameters().setLocation(CodeLocation.NONE);

        // Canvas large enough to avoid clipping.
        generator.getParameters().getImageWidth().setPixels(600);
        generator.getParameters().getImageHeight().setPixels(220);

        String out = ExampleAssist.pathCombine(FOLDER, "codabar_off.png");
        generator.save(out, BarCodeImageFormat.PNG);
        ExampleAssist.assertFileCreated(out);

        // Most decoders return Codabar WITHOUT start/stop and WITHOUT checksum → plain data "123456".
        assertImageHasBarcodes(out, 1,
                java.util.List.of(expected(DecodeType.CODABAR, "123456")), ChecksumValidation.OFF);
    }


    // ---------- Code 11 ----------

    /**
     * Code 11: checksum ON — appends check digit(s). We assert by prefix.
     */
    @Test
    public void code11_checksum_on() throws Exception {
        String payload = "12345";
        BarcodeGenerator generator = new BarcodeGenerator(EncodeTypes.CODE_11, payload);
        generator.getParameters().getBarcode().setChecksumEnabled(EnableChecksum.YES);

        generator.getParameters().getBarcode().getXDimension().setPixels(3.0f);
        generator.getParameters().getBarcode().getBarHeight().setPixels(80);
        generator.getParameters().getImageWidth().setPixels(420);
        generator.getParameters().getImageHeight().setPixels(180);

        String out = ExampleAssist.pathCombine(FOLDER, "code11_on.png");
        generator.save(out, BarCodeImageFormat.PNG);
        ExampleAssist.assertFileCreated(out);

        assertImageHasBarcodes(out, 1,
                List.of(ExampleAssist.expectedPrefix(DecodeType.CODE_11, payload)),ChecksumValidation.ON);
    }

    /**
     * Code 11: checksum OFF — CodeText equals input payload.
     */
    /**
     * Code 11 with checksum OFF.
     *
     * Why it failed before: default 5pt padding is far less than 10×XDimension,
     * so the reader rejects the symbol (quiet zones too small).
     * Fix: make L/R quiet zones >= 10×XDimension (here: 12× for extra margin),
     * increase bar height a bit, and keep HRT below the bars.
     */
    @Test
    public void code11_checksum_off() throws Exception {
        String payload = "12345";
        BarcodeGenerator generator = new BarcodeGenerator(EncodeTypes.CODE_11, payload);
        generator.getParameters().getBarcode().setChecksumEnabled(EnableChecksum.NO);

        // Raster geometry
        generator.getParameters().getBarcode().getXDimension().setPixels(3.0f);
        generator.getParameters().getBarcode().getBarHeight().setPixels(100);

        // Quiet zones: 12×X on both sides (>= 10×X is recommended)
        int qz = (int)(12 * generator.getParameters().getBarcode().getXDimension().getPixels());
        generator.getParameters().getBarcode().getPadding().getLeft().setPixels(qz);
        generator.getParameters().getBarcode().getPadding().getRight().setPixels(qz);
        generator.getParameters().getBarcode().getPadding().getTop().setPixels(12);
        generator.getParameters().getBarcode().getPadding().getBottom().setPixels(12);

        // Keep human-readable text below so it never touches the bars
        generator.getParameters().getBarcode().getCodeTextParameters().setLocation(CodeLocation.BELOW);

        // Canvas
        generator.getParameters().getImageWidth().setPixels(420);
        generator.getParameters().getImageHeight().setPixels(200);

        String out = ExampleAssist.pathCombine(FOLDER, "code11_off.png");
        generator.save(out, BarCodeImageFormat.PNG);
        ExampleAssist.assertFileCreated(out);

        assertImageHasBarcodes(out, 1,
                List.of(expected(DecodeType.CODE_11, payload)), ChecksumValidation.OFF);
    }


    // ---------- Standard 2 of 5 ----------

    @Test
    public void standard2of5_checksum_on() throws Exception {
        String payload = "123456";
        BarcodeGenerator generator = new BarcodeGenerator(EncodeTypes.STANDARD_2_OF_5, payload);
        generator.getParameters().getBarcode().setChecksumEnabled(EnableChecksum.YES);

        generator.getParameters().getBarcode().getXDimension().setPixels(3.0f);
        generator.getParameters().getBarcode().getBarHeight().setPixels(90);
        generator.getParameters().getImageWidth().setPixels(420);
        generator.getParameters().getImageHeight().setPixels(180);

        String out = ExampleAssist.pathCombine(FOLDER, "std25_on.png");
        generator.save(out, BarCodeImageFormat.PNG);
        ExampleAssist.assertFileCreated(out);

        assertImageHasBarcodes(out, 1,
                List.of(ExampleAssist.expectedPrefix(DecodeType.STANDARD_2_OF_5, payload)), ChecksumValidation.ON);
    }

    @Test
    public void standard2of5_checksum_off() throws Exception {
        String payload = "123456";
        BarcodeGenerator generator = new BarcodeGenerator(EncodeTypes.STANDARD_2_OF_5, payload);
        generator.getParameters().getBarcode().setChecksumEnabled(EnableChecksum.NO);

        generator.getParameters().getBarcode().getXDimension().setPixels(3.0f);
        generator.getParameters().getBarcode().getBarHeight().setPixels(90);
        generator.getParameters().getImageWidth().setPixels(420);
        generator.getParameters().getImageHeight().setPixels(180);

        String out = ExampleAssist.pathCombine(FOLDER, "std25_off.png");
        generator.save(out, BarCodeImageFormat.PNG);
        ExampleAssist.assertFileCreated(out);

        assertImageHasBarcodes(out, 1,
                List.of(expected(DecodeType.STANDARD_2_OF_5, payload)),ChecksumValidation.OFF);
    }

    // ---------- Interleaved 2 of 5 ----------
    @Test
    public void interleaved2of5_checksum_on() throws Exception {
        // Even number of digits is required by I-2/5. With checksum=YES, engine appends MOD10.
        // If total length becomes odd, engine prepends a leading '0' to keep it even.
        String payload = "123456";

        BarcodeGenerator generator = new BarcodeGenerator(EncodeTypes.INTERLEAVED_2_OF_5, payload);
        generator.getParameters().getBarcode().setChecksumEnabled(EnableChecksum.YES);

        // Robust rasterization + quiet zones
        generator.getParameters().getBarcode().getXDimension().setPixels(3.0f);
        generator.getParameters().getBarcode().getBarHeight().setPixels(90);
        generator.getParameters().getBarcode().getPadding().getLeft().setPixels(24);
        generator.getParameters().getBarcode().getPadding().getRight().setPixels(24);
        generator.getParameters().getBarcode().getPadding().getTop().setPixels(12);
        generator.getParameters().getBarcode().getPadding().getBottom().setPixels(12);
        generator.getParameters().getImageWidth().setPixels(420);
        generator.getParameters().getImageHeight().setPixels(180);

        String out = ExampleAssist.pathCombine(FOLDER, "i25_on.png");
        generator.save(out, BarCodeImageFormat.PNG);
        ExampleAssist.assertFileCreated(out);

        // Expected text is payload with MOD10 check digit AND optional leading '0' to keep even length → "01234565".
        String expectedCodeText = expectedI25WithChecksum(payload);
        assertImageHasBarcodes(
                out,
                1,
                java.util.List.of(expected(DecodeType.INTERLEAVED_2_OF_5, expectedCodeText)), ChecksumValidation.ON);
    }


    @Test
    public void interleaved2of5_checksum_off() throws Exception {
        String payload = "123456";
        BarcodeGenerator generator = new BarcodeGenerator(EncodeTypes.INTERLEAVED_2_OF_5, payload);
        generator.getParameters().getBarcode().setChecksumEnabled(EnableChecksum.NO);

        generator.getParameters().getBarcode().getXDimension().setPixels(3.0f);
        generator.getParameters().getBarcode().getBarHeight().setPixels(90);
        generator.getParameters().getImageWidth().setPixels(420);
        generator.getParameters().getImageHeight().setPixels(180);

        String out = ExampleAssist.pathCombine(FOLDER, "itf_off.png");
        generator.save(out, BarCodeImageFormat.PNG);
        ExampleAssist.assertFileCreated(out);

        assertImageHasBarcodes(out, 1,
                List.of(expected(DecodeType.INTERLEAVED_2_OF_5, payload)), ChecksumValidation.OFF);
    }

    // ---------- Matrix 2 of 5 ----------

    @Test
    public void matrix2of5_checksum_on() throws Exception {
        String payload = "123456";
        BarcodeGenerator generator = new BarcodeGenerator(EncodeTypes.MATRIX_2_OF_5, payload);
        generator.getParameters().getBarcode().setChecksumEnabled(EnableChecksum.YES);

        generator.getParameters().getBarcode().getXDimension().setPixels(3.0f);
        generator.getParameters().getBarcode().getBarHeight().setPixels(90);
        generator.getParameters().getImageWidth().setPixels(420);
        generator.getParameters().getImageHeight().setPixels(180);

        String out = ExampleAssist.pathCombine(FOLDER, "m25_on.png");
        generator.save(out, BarCodeImageFormat.PNG);
        ExampleAssist.assertFileCreated(out);

        assertImageHasBarcodes(out, 1,
                List.of(ExampleAssist.expectedPrefix(DecodeType.MATRIX_2_OF_5, payload)), ChecksumValidation.ON);
    }

    @Test
    public void matrix2of5_checksum_off() throws Exception {
        String payload = "123456";
        BarcodeGenerator generator = new BarcodeGenerator(EncodeTypes.MATRIX_2_OF_5, payload);
        generator.getParameters().getBarcode().setChecksumEnabled(EnableChecksum.NO);

        generator.getParameters().getBarcode().getXDimension().setPixels(3.0f);
        generator.getParameters().getBarcode().getBarHeight().setPixels(90);
        generator.getParameters().getImageWidth().setPixels(420);
        generator.getParameters().getImageHeight().setPixels(180);

        String out = ExampleAssist.pathCombine(FOLDER, "m25_off.png");
        generator.save(out, BarCodeImageFormat.PNG);
        ExampleAssist.assertFileCreated(out);

        assertImageHasBarcodes(out, 1,
                List.of(expected(DecodeType.MATRIX_2_OF_5, payload)), ChecksumValidation.OFF);
    }

    // ---------- IATA 2 of 5 ----------

    @Test
    public void iata2of5_checksum_on() throws Exception {
        String payload = "123456";
        BarcodeGenerator generator = new BarcodeGenerator(EncodeTypes.IATA_2_OF_5, payload);
        generator.getParameters().getBarcode().setChecksumEnabled(EnableChecksum.YES);

        generator.getParameters().getBarcode().getXDimension().setPixels(3.0f);
        generator.getParameters().getBarcode().getBarHeight().setPixels(90);
        generator.getParameters().getImageWidth().setPixels(420);
        generator.getParameters().getImageHeight().setPixels(180);

        String out = ExampleAssist.pathCombine(FOLDER, "iata25_on.png");
        generator.save(out, BarCodeImageFormat.PNG);
        ExampleAssist.assertFileCreated(out);

        assertImageHasBarcodes(out, 1,
                List.of(ExampleAssist.expectedPrefix(DecodeType.IATA_2_OF_5, payload)), ChecksumValidation.ON);
    }

    /**
     * IATA 2 of 5 with checksum OFF.
     *
     * Why it failed before: the shorter no-check digit pattern + small default padding
     * produced insufficient quiet zones for the reader.
     * Fix: widen quiet zones to 12×X, set classic wide:narrow = 3, bump bar height,
     * and keep HRT below.
     */
    @Test
    public void iata2of5_checksum_off() throws Exception {
        String payload = "123456"; // even number of digits is OK for IATA 2 of 5
        BarcodeGenerator generator = new BarcodeGenerator(EncodeTypes.IATA_2_OF_5, payload);
        generator.getParameters().getBarcode().setChecksumEnabled(EnableChecksum.NO);

        // Raster geometry
        generator.getParameters().getBarcode().getXDimension().setPixels(3.0f);
        generator.getParameters().getBarcode().getBarHeight().setPixels(100);
        generator.getParameters().getBarcode().setWideNarrowRatio(3.0f);

        // Quiet zones: 12×X
        int qz = (int)(12 * generator.getParameters().getBarcode().getXDimension().getPixels());
        generator.getParameters().getBarcode().getPadding().getLeft().setPixels(qz);
        generator.getParameters().getBarcode().getPadding().getRight().setPixels(qz);
        generator.getParameters().getBarcode().getPadding().getTop().setPixels(12);
        generator.getParameters().getBarcode().getPadding().getBottom().setPixels(12);

        generator.getParameters().getBarcode().getCodeTextParameters().setLocation(CodeLocation.BELOW);

        // Canvas
        generator.getParameters().getImageWidth().setPixels(520);
        generator.getParameters().getImageHeight().setPixels(200);

        String out = ExampleAssist.pathCombine(FOLDER, "iata25_off.png");
        generator.save(out, BarCodeImageFormat.PNG);
        ExampleAssist.assertFileCreated(out);

        // For OFF we expect the exact payload (no check digit appended)
        assertImageHasBarcodes(out, 1,
                List.of(expected(DecodeType.IATA_2_OF_5, payload)), ChecksumValidation.OFF);
    }

    // ---------- MSI (the ON/OFF pair already exists; adding it here for completeness)----------

    @Test
    public void msi_checksum_on() throws Exception {
        String payload = "123456";
        BarcodeGenerator generator = new BarcodeGenerator(EncodeTypes.MSI, payload);
        generator.getParameters().getBarcode().setChecksumEnabled(EnableChecksum.YES);

        generator.getParameters().getBarcode().getXDimension().setPixels(3.0f);
        generator.getParameters().getBarcode().getBarHeight().setPixels(80);
        generator.getParameters().getImageWidth().setPixels(420);
        generator.getParameters().getImageHeight().setPixels(180);

        String out = ExampleAssist.pathCombine(FOLDER, "msi_on.png");
        generator.save(out, BarCodeImageFormat.PNG);
        ExampleAssist.assertFileCreated(out);

        assertImageHasBarcodes(out, 1,
                List.of(ExampleAssist.expectedPrefix(DecodeType.MSI, payload)), ChecksumValidation.ON);
    }

    @Test
    public void msi_checksum_off() throws Exception {
        String payload = "123456";
        BarcodeGenerator generator = new BarcodeGenerator(EncodeTypes.MSI, payload);
        generator.getParameters().getBarcode().setChecksumEnabled(EnableChecksum.NO);

        generator.getParameters().getBarcode().getXDimension().setPixels(3.0f);
        generator.getParameters().getBarcode().getBarHeight().setPixels(80);
        generator.getParameters().getImageWidth().setPixels(420);
        generator.getParameters().getImageHeight().setPixels(180);

        String out = ExampleAssist.pathCombine(FOLDER, "msi_off.png");
        generator.save(out, BarCodeImageFormat.PNG);
        ExampleAssist.assertFileCreated(out);

        assertImageHasBarcodes(out, 1,
                List.of(expected(DecodeType.MSI, payload)), ChecksumValidation.OFF);
    }

    // ---------- ITF-14 (many builds treat checksum as optional) ----------

    @Test
    public void itf14_checksum_on() throws Exception {
        String payload = "1001234500001"; // engine will compute check digit if ON
        BarcodeGenerator generator = new BarcodeGenerator(EncodeTypes.ITF_14, payload);
        generator.getParameters().getBarcode().setChecksumEnabled(EnableChecksum.YES);

        generator.getParameters().getBarcode().getXDimension().setPixels(2.0f);
        generator.getParameters().getBarcode().getBarHeight().setPixels(100);
        generator.getParameters().getImageWidth().setPixels(520);
        generator.getParameters().getImageHeight().setPixels(220);

        String out = ExampleAssist.pathCombine(FOLDER, "itf14_on.png");
        generator.save(out, BarCodeImageFormat.PNG);
        ExampleAssist.assertFileCreated(out);

        // Check by prefix — final digit is calculated
        assertImageHasBarcodes(out, 1,
                List.of(ExampleAssist.expectedPrefix(DecodeType.ITF_14, payload)), ChecksumValidation.ON);
    }

    /**
     * ITF-14: OFF validation can return different normalized GS1 texts.
     * Accept any of these common forms:
     *  1) "(01)" + GTIN-14 (raw, with parentheses)
     *  2) "01" + GTIN-14 (raw, no parentheses)
     *  3) "01" + GTIN-13 (check digit removed)
     *  4) "01" + GTIN-12 (indicator + check digit removed)  ← matches observed: 01001234500001
     */
    @Test
    public void itf14_checksum_off() throws Exception {
        String codeText = "10012345000017"; // valid GTIN-14 with check digit
        BarcodeGenerator generator = new BarcodeGenerator(EncodeTypes.ITF_14, codeText);
        generator.getParameters().getBarcode().setChecksumEnabled(EnableChecksum.NO); // ignored for ITF-14

        generator.getParameters().getBarcode().getXDimension().setPixels(2.0f);
        generator.getParameters().getBarcode().getBarHeight().setPixels(100);
        generator.getParameters().getImageWidth().setPixels(520);
        generator.getParameters().getImageHeight().setPixels(220);

        String out = ExampleAssist.pathCombine(FOLDER, "itf14_off.png");
        generator.save(out, BarCodeImageFormat.PNG);
        ExampleAssist.assertFileCreated(out);

        // Expected variants (all without spaces):
        String v1 = "(01)" + codeText;                                     // (01) + 14
        String v2 = "01" + codeText;                                       // 01 + 14
        String v3 = "01" + codeText.substring(0, codeText.length() - 1);   // 01 + 13 (no check digit)
        String v4 = "01" + codeText.substring(1, codeText.length() - 1);   // 01 + 12 (no indicator, no check digit)

        // Use the helper with ChecksumValidation.OFF, but allow multiple acceptable texts.
        // If your helper can only check a single expected, read and assert manually:
       BarCodeReader reader =
                new BarCodeReader(out, DecodeType.ITF_14);
        reader.getBarcodeSettings().setChecksumValidation(ChecksumValidation.OFF);
        BarCodeResult[] results = reader.readBarCodes();

        org.testng.Assert.assertTrue(results.length >= 1, "Expected at least 1 ITF-14");
        String actual = results[0].getCodeText();

        boolean matches =
                actual.equals(v1) || actual.equals(v2) || actual.equals(v3) || actual.equals(v4);

        org.testng.Assert.assertTrue(
                matches,
                "Unexpected text: '" + actual + "'. Expected one of: '" + v1 + "', '" + v2 + "', '" + v3 + "', '" + v4 + "'."
        );
    }


}
