package com.aspose.barcode.guide.recognition.basic_setup;

import com.aspose.barcode.barcoderecognition.BarCodeReader;
import com.aspose.barcode.barcoderecognition.BarCodeResult;
import com.aspose.barcode.barcoderecognition.DecodeType;
import com.aspose.barcode.guide.common.ExampleAssist;
import com.aspose.barcode.guide.common.LicenseAssist;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.nio.file.Paths;

/**
 * ChooseRecognitionSymbology
 *
 * TestNG suite demonstrating how to choose recognition symbologies:
 * - Single symbology
 * - Multiple explicit symbologies
 * - 1D and 2D groups
 * - All supported types (auto-detect)
 */
public class ChooseRecognitionSymbology {

    private static final String IMAGES_FOLDER =
            ExampleAssist.getOrCreateResourceFolderPath("quick_start", "recognition", "prepared_images");

    @BeforeClass
    public void setUp() {
        LicenseAssist.setupLicense();
    }

    // --- Single symbology: Code 128 ---
    @Test
    public void read_Code128_SingleType() throws Exception {
        BarCodeReader reader = new BarCodeReader(
                path("code128.png"),
                DecodeType.CODE_128
        );
        assertRecognized(reader, "code128.png", 1);
    }

    // --- Single symbology: QR ---
    @Test
    public void read_QR_SingleType() throws Exception {
        BarCodeReader reader = new BarCodeReader(
                path("qrcode.png"),
                DecodeType.QR
        );
        assertRecognized(reader, "qrcode.png", 1);
    }

    // --- Single symbology: EAN-13 ---
    @Test
    public void read_EAN13_SingleType() throws Exception {
        BarCodeReader reader = new BarCodeReader(
                path("ean13.png"),
                DecodeType.EAN_13
        );
        assertRecognized(reader, "ean13.png", 1);
    }

    // --- Multiple explicit symbologies: Code128 or QR ---
    @Test
    public void read_Code128_or_QR() throws Exception {
        BarCodeReader reader = new BarCodeReader(
                path("mixed.png"),
                DecodeType.CODE_128,
                DecodeType.QR
        );
        assertRecognized(reader, "mixed.png", 1);
    }

    // --- 1D group (TYPES_1D): detect any linear type like Code128, EANs, ITF, etc. ---
    @Test
    public void read_Any1D_Group() throws Exception {
        BarCodeReader reader = new BarCodeReader(
                path("ean13.png"),
                DecodeType.TYPES_1D
        );
        assertRecognized(reader, "ean13.png", 1);
    }

    // --- 2D group (TYPES_2D): detect Data Matrix, QR, PDF417, Aztec, etc. ---
    @Test
    public void read_Any2D_Group() throws Exception {
        BarCodeReader reader = new BarCodeReader(
                path("datamatrix.png"),
                DecodeType.TYPES_2D
        );
        assertRecognized(reader, "datamatrix.png", 1);
    }

    // --- GS1-128 only (structured AI data) ---
    @Test
    public void read_GS1_128_Only() throws Exception {
        BarCodeReader reader = new BarCodeReader(
                path("gs1_128.png"),
                DecodeType.GS_1_CODE_128
        );
        assertRecognized(reader, "gs1_128.png", 1);
    }

    // --- All supported types (auto-detect everything) ---
    @Test
    public void read_AllSupportedTypes() throws Exception {
        BarCodeReader reader = new BarCodeReader(
                path("mixed.png"),
                DecodeType.ALL_SUPPORTED_TYPES
        );
        assertRecognized(reader, "mixed.png", 1);
    }

    // --- Multiple barcodes in one image (expect >= 2) ---
    @Test
    public void read_MultipleInOneImage_AllSupported() throws Exception {
        BarCodeReader reader = new BarCodeReader(
                path("multi.png"),
                DecodeType.ALL_SUPPORTED_TYPES
        );
        assertRecognized(reader, "multi.png", 2);
    }

    // --- Helpers ---

    private static String path(String fileName) {
        return Paths.get(IMAGES_FOLDER, fileName).toString();
    }

    private void assertRecognized(BarCodeReader reader, String imageName, int minCount) throws Exception {
        BarCodeResult[] results = reader.readBarCodes();
        for (BarCodeResult r : results) {
            System.out.println("Code Type: " + r.getCodeTypeName() + " | Code Text: " + r.getCodeText());
        }
        Assert.assertTrue(
                results.length >= minCount,
                "Expected at least " + minCount + " result(s) in " + imageName + ", but got " + results.length
        );
    }
}
