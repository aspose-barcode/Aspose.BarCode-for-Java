package com.aspose.barcode.guide.generation.checksum;

import com.aspose.barcode.barcoderecognition.ChecksumValidation;
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
 * Checksum demos for symbologies where checksum is MANDATORY (enforced by standard/engine).
 * For each symbology we show:
 *  (1) Positive case: DEFAULT (or YES) works and barcode is readable.
 *  (2) Negative case: explicitly setting EnableChecksum.NO throws an exception.
 *
 * Covered:
 *  - Code 93
 *  - Code 128
 *  - GS1 Code 128
 *  - EAN-13
 *  - EAN-8
 *  - UPC-A
 *  - UPC-E
 *  - ISBN
 *  - SSCC-18
 *  - EAN-14 (GTIN-14)
 *  - SCC-14
 */
public class EnforcedChecksumExamples {

    private static final String FOLDER =
            ExampleAssist.getOrCreateResourceFolderPath("generation", "checksum", "enforced");

    @BeforeClass
    public void setUp() throws Exception {
        LicenseAssist.setupLicense();
    }

    // Small helper to get consistent raster
    private static void apply1DLayout(BarcodeGenerator generator, int widthPx, int heightPx, float xDimPx) {
        generator.getParameters().getBarcode().getXDimension().setPixels(xDimPx);
        generator.getParameters().getBarcode().getBarHeight().setPixels(Math.max(90, heightPx / 2));
        generator.getParameters().getImageWidth().setPixels(widthPx);
        generator.getParameters().getImageHeight().setPixels(heightPx);
    }

    // -------------------- Code 93 --------------------

    /** Code 93: checksum is enforced — DEFAULT succeeds. */
    @Test
    public void code93_checksum_enforced_default() throws Exception {
        String payload = "C93DATA";
        BarcodeGenerator generator = new BarcodeGenerator(EncodeTypes.CODE_93, payload);
        generator.getParameters().getBarcode().setChecksumEnabled(EnableChecksum.DEFAULT);

        apply1DLayout(generator, 460, 180, 3.0f);
        String outputPath = ExampleAssist.pathCombine(FOLDER, "code93_enforced_ok.png");
        generator.save(outputPath, BarCodeImageFormat.PNG);
        ExampleAssist.assertFileCreated(outputPath);

        assertImageHasBarcodes(outputPath, 1, List.of(expected(DecodeType.CODE_93, payload)), ChecksumValidation.DEFAULT);
    }

    /** Code 93: explicitly disabling checksum must throw. */
    @Test(expectedExceptions = com.aspose.barcode.BarCodeException.class)
    public void code93_checksum_cannotDisable() throws Exception {
        String payload = "C93DATA";
        BarcodeGenerator generator = new BarcodeGenerator(EncodeTypes.CODE_93, payload);
        generator.getParameters().getBarcode().setChecksumEnabled(EnableChecksum.NO);

        apply1DLayout(generator, 460, 180, 3.0f);
        String outputPath = ExampleAssist.pathCombine(FOLDER, "code93_enforced_fail.png");
        generator.save(outputPath, BarCodeImageFormat.PNG);
    }

    // -------------------- Code 128 --------------------

    /** Code 128: checksum is enforced — DEFAULT succeeds. */
    @Test
    public void code128_checksum_enforced_default() throws Exception {
        String payload = "C128DATA";
        BarcodeGenerator generator = new BarcodeGenerator(EncodeTypes.CODE_128, payload);
        generator.getParameters().getBarcode().setChecksumEnabled(EnableChecksum.DEFAULT);

        apply1DLayout(generator, 520, 200, 2.0f);
        String outputPath = ExampleAssist.pathCombine(FOLDER, "code128_enforced_ok.png");
        generator.save(outputPath, BarCodeImageFormat.PNG);
        ExampleAssist.assertFileCreated(outputPath);

        assertImageHasBarcodes(outputPath, 1, List.of(expected(DecodeType.CODE_128, payload)));
    }

    /** Code 128: explicitly disabling checksum must throw. */
    @Test(expectedExceptions = com.aspose.barcode.BarCodeException.class)
    public void code128_checksum_cannotDisable() throws Exception {
        String payload = "C128DATA";
        BarcodeGenerator generator = new BarcodeGenerator(EncodeTypes.CODE_128, payload);
        generator.getParameters().getBarcode().setChecksumEnabled(EnableChecksum.NO);

        apply1DLayout(generator, 520, 200, 2.0f);
        String outputPath = ExampleAssist.pathCombine(FOLDER, "code128_enforced_fail.png");
        generator.save(outputPath, BarCodeImageFormat.PNG);
    }

    // -------------------- GS1 Code 128 --------------------

    /** GS1 Code 128: checksum is enforced — DEFAULT succeeds (also requires valid AI syntax). */
    @Test
    public void gs1_code128_checksum_enforced_default() throws Exception {
        String payload = "(01)09501101530003(17)240101";
        BarcodeGenerator generator = new BarcodeGenerator(EncodeTypes.GS_1_CODE_128, payload);
        generator.getParameters().getBarcode().setChecksumEnabled(EnableChecksum.DEFAULT);

        apply1DLayout(generator, 560, 200, 2.0f);
        String outputPath = ExampleAssist.pathCombine(FOLDER, "gs1_code128_enforced_ok.png");
        generator.save(outputPath, BarCodeImageFormat.PNG);
        ExampleAssist.assertFileCreated(outputPath);

        assertImageHasBarcodes(outputPath, 1, List.of(expected(DecodeType.GS_1_CODE_128, payload)));
    }

    /** GS1 Code 128: explicitly disabling checksum must throw. */
    @Test(expectedExceptions = com.aspose.barcode.BarCodeException.class)
    public void gs1_code128_checksum_cannotDisable() throws Exception {
        String payload = "(01)09501101530003(17)240101";
        BarcodeGenerator generator = new BarcodeGenerator(EncodeTypes.GS_1_CODE_128, payload);
        generator.getParameters().getBarcode().setChecksumEnabled(EnableChecksum.NO);

        apply1DLayout(generator, 560, 200, 2.0f);
        String outputPath = ExampleAssist.pathCombine(FOLDER, "gs1_code128_enforced_fail.png");
        generator.save(outputPath, BarCodeImageFormat.PNG);
    }

    // -------------------- EAN-13 --------------------

    /** EAN-13: checksum is enforced — DEFAULT succeeds. */
    @Test
    public void ean13_checksum_enforced_default() throws Exception {
        String payload = "5901234123457";
        BarcodeGenerator generator = new BarcodeGenerator(EncodeTypes.EAN_13, payload);
        generator.getParameters().getBarcode().setChecksumEnabled(EnableChecksum.DEFAULT);

        apply1DLayout(generator, 360, 160, 2.0f);
        String outputPath = ExampleAssist.pathCombine(FOLDER, "ean13_enforced_ok.png");
        generator.save(outputPath, BarCodeImageFormat.PNG);
        ExampleAssist.assertFileCreated(outputPath);

        assertImageHasBarcodes(outputPath, 1, List.of(expected(DecodeType.EAN_13, payload)));
    }

    /** EAN-13: explicitly disabling checksum must throw. */
    @Test(expectedExceptions = com.aspose.barcode.BarCodeException.class)
    public void ean13_checksum_cannotDisable() throws Exception {
        String payload = "5901234123457";
        BarcodeGenerator generator = new BarcodeGenerator(EncodeTypes.EAN_13, payload);
        generator.getParameters().getBarcode().setChecksumEnabled(EnableChecksum.NO);

        apply1DLayout(generator, 360, 160, 2.0f);
        String outputPath = ExampleAssist.pathCombine(FOLDER, "ean13_enforced_fail.png");
        generator.save(outputPath, BarCodeImageFormat.PNG);
    }

    // -------------------- EAN-8 --------------------

    /** EAN-8: checksum is enforced — DEFAULT succeeds. */
    @Test
    public void ean8_checksum_enforced_default() throws Exception {
        String payload = "96385074";
        BarcodeGenerator generator = new BarcodeGenerator(EncodeTypes.EAN_8, payload);
        generator.getParameters().getBarcode().setChecksumEnabled(EnableChecksum.DEFAULT);

        apply1DLayout(generator, 300, 160, 2.0f);
        String outputPath = ExampleAssist.pathCombine(FOLDER, "ean8_enforced_ok.png");
        generator.save(outputPath, BarCodeImageFormat.PNG);
        ExampleAssist.assertFileCreated(outputPath);

        assertImageHasBarcodes(outputPath, 1, List.of(expected(DecodeType.EAN_8, payload)));
    }

    /** EAN-8: explicitly disabling checksum must throw. */
    @Test(expectedExceptions = com.aspose.barcode.BarCodeException.class)
    public void ean8_checksum_cannotDisable() throws Exception {
        String payload = "96385074";
        BarcodeGenerator generator = new BarcodeGenerator(EncodeTypes.EAN_8, payload);
        generator.getParameters().getBarcode().setChecksumEnabled(EnableChecksum.NO);

        apply1DLayout(generator, 300, 160, 2.0f);
        String outputPath = ExampleAssist.pathCombine(FOLDER, "ean8_enforced_fail.png");
        generator.save(outputPath, BarCodeImageFormat.PNG);
    }

    // -------------------- UPC-A --------------------

    /** UPC-A: checksum is enforced — DEFAULT succeeds. */
    @Test
    public void upca_checksum_enforced_default() throws Exception {
        String payload = "042100005264";
        BarcodeGenerator generator = new BarcodeGenerator(EncodeTypes.UPCA, payload);
        generator.getParameters().getBarcode().setChecksumEnabled(EnableChecksum.DEFAULT);

        apply1DLayout(generator, 320, 160, 2.0f);
        String outputPath = ExampleAssist.pathCombine(FOLDER, "upca_enforced_ok.png");
        generator.save(outputPath, BarCodeImageFormat.PNG);
        ExampleAssist.assertFileCreated(outputPath);

        assertImageHasBarcodes(outputPath, 1, List.of(expected(DecodeType.UPCA, payload)));
    }

    /** UPC-A: explicitly disabling checksum must throw. */
    @Test(expectedExceptions = com.aspose.barcode.BarCodeException.class)
    public void upca_checksum_cannotDisable() throws Exception {
        String payload = "042100005264";
        BarcodeGenerator generator = new BarcodeGenerator(EncodeTypes.UPCA, payload);
        generator.getParameters().getBarcode().setChecksumEnabled(EnableChecksum.NO);

        apply1DLayout(generator, 320, 160, 2.0f);
        String outputPath = ExampleAssist.pathCombine(FOLDER, "upca_enforced_fail.png");
        generator.save(outputPath, BarCodeImageFormat.PNG);
    }

    // -------------------- UPC-E --------------------

    /** UPC-E: checksum is enforced — DEFAULT succeeds. */
    @Test
    public void upce_checksum_enforced_default() throws Exception {
        String payload = "1234567"; // UPC-E core (engine will apply check)
        BarcodeGenerator generator = new BarcodeGenerator(EncodeTypes.UPCE, payload);
        generator.getParameters().getBarcode().setChecksumEnabled(EnableChecksum.DEFAULT);

        apply1DLayout(generator, 280, 160, 2.0f);
        String outputPath = ExampleAssist.pathCombine(FOLDER, "upce_enforced_ok.png");
        generator.save(outputPath, BarCodeImageFormat.PNG);
        ExampleAssist.assertFileCreated(outputPath);

        // Recognizers often expand UPC-E; use prefix match
        assertImageHasBarcodes(outputPath, 1, List.of(ExampleAssist.expectedPrefix(DecodeType.UPCE, payload)));
    }

    /** UPC-E: explicitly disabling checksum must throw. */
    @Test(expectedExceptions = com.aspose.barcode.BarCodeException.class)
    public void upce_checksum_cannotDisable() throws Exception {
        String payload = "1234567";
        BarcodeGenerator generator = new BarcodeGenerator(EncodeTypes.UPCE, payload);
        generator.getParameters().getBarcode().setChecksumEnabled(EnableChecksum.NO);

        apply1DLayout(generator, 280, 160, 2.0f);
        String outputPath = ExampleAssist.pathCombine(FOLDER, "upce_enforced_fail.png");
        generator.save(outputPath, BarCodeImageFormat.PNG);
    }

    // -------------------- ISBN --------------------

    /** ISBN (ISBN-13 form here): checksum is enforced — DEFAULT succeeds. */
    @Test
    public void isbn_checksum_enforced_default() throws Exception {
        String payload = "9780306406157"; // valid ISBN-13
        BarcodeGenerator generator = new BarcodeGenerator(EncodeTypes.ISBN, payload);
        generator.getParameters().getBarcode().setChecksumEnabled(EnableChecksum.DEFAULT);

        apply1DLayout(generator, 420, 180, 2.0f);
        String outputPath = ExampleAssist.pathCombine(FOLDER, "isbn_enforced_ok.png");
        generator.save(outputPath, BarCodeImageFormat.PNG);
        ExampleAssist.assertFileCreated(outputPath);

        assertImageHasBarcodes(outputPath, 1, List.of(expected(DecodeType.ISBN, payload)));
    }

    /** ISBN: explicitly disabling checksum must throw. */
    @Test(expectedExceptions = com.aspose.barcode.BarCodeException.class)
    public void isbn_checksum_cannotDisable() throws Exception {
        String payload = "9780306406157";
        BarcodeGenerator generator = new BarcodeGenerator(EncodeTypes.ISBN, payload);
        generator.getParameters().getBarcode().setChecksumEnabled(EnableChecksum.NO);

        apply1DLayout(generator, 420, 180, 2.0f);
        String outputPath = ExampleAssist.pathCombine(FOLDER, "isbn_enforced_fail.png");
        generator.save(outputPath, BarCodeImageFormat.PNG);
    }

    // -------------------- SSCC-18 --------------------

    /** SSCC-18: checksum is enforced — DEFAULT succeeds. */
    @Test
    public void sscc18_checksum_enforced_default() throws Exception {
        // Correct SSCC-18 including check digit (computed -> '2')
        String payload = "000123456000000012";

        BarcodeGenerator generator = new BarcodeGenerator(EncodeTypes.SSCC_18, payload);
        generator.getParameters().getBarcode().setChecksumEnabled(EnableChecksum.DEFAULT);

        apply1DLayout(generator, 640, 220, 2.0f);

        String outputPath = ExampleAssist.pathCombine(FOLDER, "sscc18_enforced_ok.png");
        generator.save(outputPath, BarCodeImageFormat.PNG);
        ExampleAssist.assertFileCreated(outputPath);

        // Decoder returns AI(00) + 18 digits
        assertImageHasBarcodes(outputPath,1,java.util.List.of(expected(DecodeType.SSCC_18, "(00)" + payload))
        );
    }


    /** SSCC-18: explicitly disabling checksum must throw. */
    @Test(expectedExceptions = com.aspose.barcode.BarCodeException.class)
    public void sscc18_checksum_cannotDisable() throws Exception {
        String payload = "000123456000000018";
        BarcodeGenerator generator = new BarcodeGenerator(EncodeTypes.SSCC_18, payload);
        generator.getParameters().getBarcode().setChecksumEnabled(EnableChecksum.NO);

        apply1DLayout(generator, 640, 220, 2.0f);
        String outputPath = ExampleAssist.pathCombine(FOLDER, "sscc18_enforced_fail.png");
        generator.save(outputPath, BarCodeImageFormat.PNG);
    }

    // -------------------- EAN-14 (GTIN-14) --------------------

    /** EAN-14 (GTIN-14): checksum is enforced — DEFAULT succeeds. */
    @Test
    public void ean14_checksum_enforced_default() throws Exception {
        String payload = "12345678901231";
        BarcodeGenerator generator = new BarcodeGenerator(EncodeTypes.EAN_14, payload);
        generator.getParameters().getBarcode().setChecksumEnabled(EnableChecksum.DEFAULT);

        apply1DLayout(generator, 560, 200, 2.0f);
        String outputPath = ExampleAssist.pathCombine(FOLDER, "ean14_enforced_ok.png");
        generator.save(outputPath, BarCodeImageFormat.PNG);
        ExampleAssist.assertFileCreated(outputPath);

        assertImageHasBarcodes(
                outputPath,
                1,
                List.of(expected(DecodeType.EAN_14, "(01)" + payload))
        );
    }

    /** EAN-14: explicitly disabling checksum must throw. */
    @Test(expectedExceptions = com.aspose.barcode.BarCodeException.class)
    public void ean14_checksum_cannotDisable() throws Exception {
        String payload = "12345678901231";
        BarcodeGenerator generator = new BarcodeGenerator(EncodeTypes.EAN_14, payload);
        generator.getParameters().getBarcode().setChecksumEnabled(EnableChecksum.NO);

        apply1DLayout(generator, 560, 200, 2.0f);
        String outputPath = ExampleAssist.pathCombine(FOLDER, "ean14_enforced_fail.png");
        generator.save(outputPath, BarCodeImageFormat.PNG);
    }

    // -------------------- SCC-14 --------------------

    /** SCC-14: checksum is enforced — DEFAULT succeeds. */
    @Test
    public void scc14_checksum_enforced_default() throws Exception {
        String payload = "12345678901231";
        BarcodeGenerator generator = new BarcodeGenerator(EncodeTypes.SCC_14, payload);
        generator.getParameters().getBarcode().setChecksumEnabled(EnableChecksum.DEFAULT);

        apply1DLayout(generator, 560, 200, 2.0f);
        String outputPath = ExampleAssist.pathCombine(FOLDER, "scc14_enforced_ok.png");
        generator.save(outputPath, BarCodeImageFormat.PNG);
        ExampleAssist.assertFileCreated(outputPath);

        assertImageHasBarcodes(outputPath, 1, List.of(expected(DecodeType.SCC_14, "(01)" + payload)));
    }

    /** SCC-14: explicitly disabling checksum must throw. */
    @Test(expectedExceptions = com.aspose.barcode.BarCodeException.class)
    public void scc14_checksum_cannotDisable() throws Exception {
        String payload = "12345678901231";
        BarcodeGenerator generator = new BarcodeGenerator(EncodeTypes.SCC_14, payload);
        generator.getParameters().getBarcode().setChecksumEnabled(EnableChecksum.NO);

        apply1DLayout(generator, 560, 200, 2.0f);
        String outputPath = ExampleAssist.pathCombine(FOLDER, "scc14_enforced_fail.png");
        generator.save(outputPath, BarCodeImageFormat.PNG);
    }
}
