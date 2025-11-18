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
 * Checksum demos for symbologies where checksum is MANDATORY (enforced by standard/engine).
 * Attempting to disable checksum has no effect; the engine still generates and validates it.
 *
 * Covered symbologies here:
 *  - Code 93
 *  - Code 128
 *  - GS1 Code 128
 *  - EAN-13
 *  - EAN-8
 *  - UPC-A
 *  - UPC-E
 *  - ISBN
 *  - SSCC-18
 *  - EAN-14 (a.k.a. GTIN-14)
 *  - SCC-14
 *  - (Optionally) VIN — often enforced; keep if valid samples are available
 */
public class EnforcedChecksumExamples {

    private static final String FOLDER =
            ExampleAssist.getOrCreateResourceFolderPath("generation", "checksum", "enforced");

    @BeforeClass
    public void setUp() throws Exception {
        LicenseAssist.setupLicense();
    }

    // Utility: small helper to set a typical raster
    private static void apply1DLayout(BarcodeGenerator generator, int w, int h, float x) {
        generator.getParameters().getBarcode().getXDimension().setPixels(x);
        generator.getParameters().getBarcode().getBarHeight().setPixels(Math.max(90, h / 2));
        generator.getParameters().getImageWidth().setPixels(w);
        generator.getParameters().getImageHeight().setPixels(h);
    }

    /**
     * Code 93 — mandatory checksum. Disabling has no effect.
     */
    @Test
    public void code93_checksum_enforced() throws Exception {
        String payload = "C93DATA";
        BarcodeGenerator generator = new BarcodeGenerator(EncodeTypes.CODE_93, payload);
        generator.getParameters().getBarcode().setChecksumEnabled(EnableChecksum.NO); // ignored

        apply1DLayout(generator, 460, 180, 3.0f);

        String out = ExampleAssist.pathCombine(FOLDER, "code93_enforced.png");
        generator.save(out, BarCodeImageFormat.PNG);
        ExampleAssist.assertFileCreated(out);

        assertImageHasBarcodes(out, 1, List.of(expected(DecodeType.CODE_93, payload)));
    }

    /**
     * Code 128 — mandatory checksum (weighted sum with modulo).
     */
    @Test
    public void code128_checksum_enforced() throws Exception {
        String payload = "C128DATA";
        BarcodeGenerator generator = new BarcodeGenerator(EncodeTypes.CODE_128, payload);
        generator.getParameters().getBarcode().setChecksumEnabled(EnableChecksum.NO); // ignored

        apply1DLayout(generator, 520, 200, 2.0f);

        String out = ExampleAssist.pathCombine(FOLDER, "code128_enforced.png");
        generator.save(out, BarCodeImageFormat.PNG);
        ExampleAssist.assertFileCreated(out);

        assertImageHasBarcodes(out, 1, List.of(expected(DecodeType.CODE_128, payload)));
    }

    /**
     * GS1 Code 128 — mandatory checksum and GS1 formatting rules.
     */
    @Test
    public void gs1_code128_checksum_enforced() throws Exception {
        String payload = "(01)09501101530003(17)240101"; // example AIs
        BarcodeGenerator generator = new BarcodeGenerator(EncodeTypes.GS_1_CODE_128, payload);
        generator.getParameters().getBarcode().setChecksumEnabled(EnableChecksum.NO); // ignored

        apply1DLayout(generator, 560, 200, 2.0f);

        String out = ExampleAssist.pathCombine(FOLDER, "gs1_code128_enforced.png");
        generator.save(out, BarCodeImageFormat.PNG);
        ExampleAssist.assertFileCreated(out);

        assertImageHasBarcodes(out, 1, List.of(expected(DecodeType.GS_1_CODE_128, payload)));
    }

    /**
     * EAN-13 — mandatory checksum.
     */
    @Test
    public void ean13_checksum_enforced() throws Exception {
        String ean13 = "5901234123457";
        BarcodeGenerator generator = new BarcodeGenerator(EncodeTypes.EAN_13, ean13);
        generator.getParameters().getBarcode().setChecksumEnabled(EnableChecksum.NO); // ignored

        apply1DLayout(generator, 360, 160, 2.0f);

        String out = ExampleAssist.pathCombine(FOLDER, "ean13_enforced.png");
        generator.save(out, BarCodeImageFormat.PNG);
        ExampleAssist.assertFileCreated(out);

        assertImageHasBarcodes(out, 1, List.of(expected(DecodeType.EAN_13, ean13)));
    }

    /**
     * EAN-8 — mandatory checksum.
     */
    @Test
    public void ean8_checksum_enforced() throws Exception {
        String ean8 = "96385074";
        BarcodeGenerator generator = new BarcodeGenerator(EncodeTypes.EAN_8, ean8);
        generator.getParameters().getBarcode().setChecksumEnabled(EnableChecksum.NO); // ignored

        apply1DLayout(generator, 300, 160, 2.0f);

        String out = ExampleAssist.pathCombine(FOLDER, "ean8_enforced.png");
        generator.save(out, BarCodeImageFormat.PNG);
        ExampleAssist.assertFileCreated(out);

        assertImageHasBarcodes(out, 1, List.of(expected(DecodeType.EAN_8, ean8)));
    }

    /**
     * UPC-A — mandatory checksum.
     */
    @Test
    public void upca_checksum_enforced() throws Exception {
        String upca = "042100005264";
        BarcodeGenerator generator = new BarcodeGenerator(EncodeTypes.UPCA, upca);
        generator.getParameters().getBarcode().setChecksumEnabled(EnableChecksum.NO); // ignored

        apply1DLayout(generator, 320, 160, 2.0f);

        String out = ExampleAssist.pathCombine(FOLDER, "upca_enforced.png");
        generator.save(out, BarCodeImageFormat.PNG);
        ExampleAssist.assertFileCreated(out);

        assertImageHasBarcodes(out, 1, List.of(expected(DecodeType.UPCA, upca)));
    }

    /**
     * UPC-E — mandatory checksum.
     */
    @Test
    public void upce_checksum_enforced() throws Exception {
        String upce = "1234567"; // UPC-E 7-digit payload (engine renders with check digit)
        BarcodeGenerator generator = new BarcodeGenerator(EncodeTypes.UPCE, upce);
        generator.getParameters().getBarcode().setChecksumEnabled(EnableChecksum.NO); // ignored

        apply1DLayout(generator, 280, 160, 2.0f);

        String out = ExampleAssist.pathCombine(FOLDER, "upce_enforced.png");
        generator.save(out, BarCodeImageFormat.PNG);
        ExampleAssist.assertFileCreated(out);

        // Assert by prefix: renderer expands/encodes and appends check digit
        assertImageHasBarcodes(out, 1, List.of(ExampleAssist.expectedPrefix(DecodeType.UPCE, upce)));
    }

    /**
     * ISBN — mandatory checksum for the standard.
     */
    @Test
    public void isbn_checksum_enforced() throws Exception {
        String isbn = "9780306406157"; // ISBN-13 form
        BarcodeGenerator generator = new BarcodeGenerator(EncodeTypes.ISBN, isbn);
        generator.getParameters().getBarcode().setChecksumEnabled(EnableChecksum.NO); // ignored

        apply1DLayout(generator, 420, 180, 2.0f);

        String out = ExampleAssist.pathCombine(FOLDER, "isbn_enforced.png");
        generator.save(out, BarCodeImageFormat.PNG);
        ExampleAssist.assertFileCreated(out);

        assertImageHasBarcodes(out, 1, List.of(expected(DecodeType.ISBN, isbn)));
    }

    /**
     * SSCC-18 — mandatory check digit.
     */
    @Test
    public void sscc18_checksum_enforced() throws Exception {
        String sscc18 = "000123456000000018"; // include valid check digit
        BarcodeGenerator generator = new BarcodeGenerator(EncodeTypes.SSCC_18, sscc18);
        generator.getParameters().getBarcode().setChecksumEnabled(EnableChecksum.NO); // ignored

        apply1DLayout(generator, 640, 220, 2.0f);

        String out = ExampleAssist.pathCombine(FOLDER, "sscc18_enforced.png");
        generator.save(out, BarCodeImageFormat.PNG);
        ExampleAssist.assertFileCreated(out);

        assertImageHasBarcodes(out, 1, List.of(expected(DecodeType.SSCC_18, sscc18)));
    }

    /**
     * EAN-14 (GTIN-14) — mandatory check digit.
     */
    @Test
    public void ean14_checksum_enforced() throws Exception {
        String ean14 = "12345678901231"; // example with check
        BarcodeGenerator generator = new BarcodeGenerator(EncodeTypes.EAN_14, ean14);
        generator.getParameters().getBarcode().setChecksumEnabled(EnableChecksum.NO); // ignored

        apply1DLayout(generator, 560, 200, 2.0f);

        String out = ExampleAssist.pathCombine(FOLDER, "ean14_enforced.png");
        generator.save(out, BarCodeImageFormat.PNG);
        ExampleAssist.assertFileCreated(out);

        assertImageHasBarcodes(out, 1, List.of(expected(DecodeType.EAN_14, ean14)));
    }

    /**
     * SCC-14 — mandatory check digit.
     */
    @Test
    public void scc14_checksum_enforced() throws Exception {
        String scc14 = "12345678901231"; // same numeric length as GTIN-14 (check digit at the end)
        BarcodeGenerator generator = new BarcodeGenerator(EncodeTypes.SCC_14, scc14);
        generator.getParameters().getBarcode().setChecksumEnabled(EnableChecksum.NO); // ignored

        apply1DLayout(generator, 560, 200, 2.0f);

        String out = ExampleAssist.pathCombine(FOLDER, "scc14_enforced.png");
        generator.save(out, BarCodeImageFormat.PNG);
        ExampleAssist.assertFileCreated(out);

        assertImageHasBarcodes(out, 1, List.of(expected(DecodeType.SCC_14, scc14)));
    }
}
