package com.aspose.barcode.guide.recognition.basic_setup.choose_symbology;

import com.aspose.barcode.barcoderecognition.BarCodeReader;
import com.aspose.barcode.barcoderecognition.BarCodeResult;
import com.aspose.barcode.barcoderecognition.DecodeType;
import com.aspose.barcode.barcoderecognition.QualitySettings;
import com.aspose.barcode.guide.common.ExampleAssist;
import com.aspose.barcode.guide.common.LicenseAssist;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class ChooseRecognitionSymbology1
{

    private static final String IMAGES_FOLDER = ExampleAssist.getOrCreateResourceFolderPath("quick_start", "recognition", "prepared_images");

    @BeforeClass
    public void setUp() {
        LicenseAssist.setupLicense();
    }

    // ==================== 1D Barcodes ====================

    // --- Code 128 ---
    @Test
    public void read_Code128_NormalQuality() throws Exception {
        BarCodeReader reader = new BarCodeReader(IMAGES_FOLDER + "code128.png", DecodeType.CODE_128);
        assertRecognized(reader, "code128.png", 1);
    }


    // --- Code 39 ---
    @Test
    public void read_Code39() throws Exception {
        BarCodeReader reader = new BarCodeReader(IMAGES_FOLDER + "code39.png", DecodeType.CODE_39);
        assertRecognized(reader, "code39.png", 1);
    }
  @Test
    public void read_Code39FullASII() throws Exception {
        BarCodeReader reader = new BarCodeReader(IMAGES_FOLDER + "code39.png", DecodeType.CODE_39_FULL_ASCII);
        assertRecognized(reader, "code39_full_ascii.png", 1);
    }

    // --- EAN-13 ---
    @Test
    public void read_EAN13() throws Exception {
        BarCodeReader reader = new BarCodeReader(IMAGES_FOLDER + "ean13.png", DecodeType.EAN_13);
        assertRecognized(reader, "ean13.png", 1);
    }

    @Test
    public void read_EAN13_WithSupplement() throws Exception {
        BarCodeReader reader = new BarCodeReader(IMAGES_FOLDER + "ean13_supplement.png", DecodeType.EAN_13);
        reader.getBarcodeSettings().setDetectEncoding(true);
        assertRecognized(reader, "ean13_supplement.png", 1);
    }

    // --- EAN-8 ---
    @Test
    public void read_EAN8() throws Exception {
        BarCodeReader reader = new BarCodeReader(IMAGES_FOLDER + "ean8.png", DecodeType.EAN_8);
        assertRecognized(reader, "ean8.png", 1);
    }

    // --- UPC-A ---
    @Test
    public void read_UPCA() throws Exception {
        BarCodeReader reader = new BarCodeReader(IMAGES_FOLDER + "upca.png", DecodeType.UPCA);
        assertRecognized(reader, "upca.png", 1);
    }

    // --- UPC-E ---
    @Test
    public void read_UPCE() throws Exception {
        BarCodeReader reader = new BarCodeReader(IMAGES_FOLDER + "upce.png", DecodeType.UPCE);
        assertRecognized(reader, "upce.png", 1);
    }

    // --- Codabar ---
    @Test
    public void read_Codabar() throws Exception {
        BarCodeReader reader = new BarCodeReader(IMAGES_FOLDER + "codabar.png", DecodeType.CODABAR);
        assertRecognized(reader, "codabar.png", 1);
    }

    // --- ITF-14 ---
    @Test
    public void read_ITF14() throws Exception {
        BarCodeReader reader = new BarCodeReader(IMAGES_FOLDER + "itf14.png", DecodeType.ITF_14);
        assertRecognized(reader, "itf14.png", 1);
    }

    // ==================== 2D Barcodes ====================

    // --- QR Code ---
    @Test
    public void read_QRCode_Standard() throws Exception {
        BarCodeReader reader = new BarCodeReader(IMAGES_FOLDER + "qrcode.png", DecodeType.QR);
        assertRecognized(reader, "qrcode.png", 1);
    }

    @Test
    public void read_QRCode_Micro() throws Exception {
        BarCodeReader reader = new BarCodeReader(IMAGES_FOLDER + "microqr.png", DecodeType.MICRO_QR);
        assertRecognized(reader, "microqr.png", 1);
    }

    @Test
    public void read_QRCode_Damaged() throws Exception {
        BarCodeReader reader = new BarCodeReader(IMAGES_FOLDER + "qrcode_damaged.png", DecodeType.QR);
        reader.setQualitySettings(QualitySettings.getHighQuality());
        assertRecognized(reader, "qrcode_damaged.png", 1);
    }

    // --- Data Matrix ---
    @Test
    public void read_DataMatrix_Standard() throws Exception {
        BarCodeReader reader = new BarCodeReader(IMAGES_FOLDER + "datamatrix.png", DecodeType.DATA_MATRIX);
        assertRecognized(reader, "datamatrix.png", 1);
    }

    @Test
    public void read_DataMatrix_GS1() throws Exception {
        BarCodeReader reader = new BarCodeReader(IMAGES_FOLDER + "datamatrix_gs1.png", DecodeType.GS_1_DATA_MATRIX);
        assertRecognized(reader, "datamatrix_gs1.png", 1);
    }

    // --- PDF417 ---
    @Test
    public void read_PDF417_Standard() throws Exception {
        BarCodeReader reader = new BarCodeReader(IMAGES_FOLDER + "pdf417.png", DecodeType.PDF_417);
        assertRecognized(reader, "pdf417.png", 1);
    }

    @Test
    public void read_PDF417_Compact() throws Exception {
        BarCodeReader reader = new BarCodeReader(IMAGES_FOLDER + "pdf417_compact.png", DecodeType.COMPACT_PDF_417);
        assertRecognized(reader, "pdf417_compact.png", 1);
    }

    @Test
    public void read_PDF417_Macro() throws Exception {
        BarCodeReader reader = new BarCodeReader(IMAGES_FOLDER + "pdf417_macro.png", DecodeType.MACRO_PDF_417);
        assertRecognized(reader, "pdf417_macro.png", 1);
    }

    // --- Aztec ---
    @Test
    public void read_Aztec() throws Exception {
        BarCodeReader reader = new BarCodeReader(IMAGES_FOLDER + "aztec.png", DecodeType.AZTEC);
        assertRecognized(reader, "aztec.png", 1);
    }

    // ==================== Postal Barcodes ====================

    @Test
    public void read_Postnet() throws Exception {
        BarCodeReader reader = new BarCodeReader(IMAGES_FOLDER + "postnet.png", DecodeType.POSTNET);
        assertRecognized(reader, "postnet.png", 1);
    }

    @Test
    public void read_Planet() throws Exception {
        BarCodeReader reader = new BarCodeReader(IMAGES_FOLDER + "planet.png", DecodeType.PLANET);
        assertRecognized(reader, "planet.png", 1);
    }

    @Test
    public void read_AustraliaPost() throws Exception {
        BarCodeReader reader = new BarCodeReader(IMAGES_FOLDER + "australia_post.png", DecodeType.AUSTRALIA_POST);
        assertRecognized(reader, "australia_post.png", 1);
    }

    // ==================== Multiple Types ====================

    @Test
    public void read_AllTypes() throws Exception {
        BarCodeReader reader = new BarCodeReader(IMAGES_FOLDER + "mixed_barcodes.png", DecodeType.ALL_SUPPORTED_TYPES);
        assertRecognized(reader, "mixed_barcodes.png", 1);
    }

    @Test
    public void read_1D_Types() throws Exception {
        BarCodeReader reader = new BarCodeReader(IMAGES_FOLDER + "types_1D_barcodes.png", DecodeType.TYPES_1D);
        assertRecognized(reader, "types_1D_barcodes.png", 1);
    }

     @Test
    public void read_2D_Types() throws Exception {
        BarCodeReader reader = new BarCodeReader(IMAGES_FOLDER + "types_2D_barcodes.png", DecodeType.TYPES_2D);
        assertRecognized(reader, "types_2D_barcodes.png", 1);
    }

    @Test
    public void read_SpecificTypes() throws Exception {
        BarCodeReader reader = new BarCodeReader(
                IMAGES_FOLDER + "mixed_barcodes.png",
                DecodeType.CODE_128, DecodeType.QR, DecodeType.DATA_MATRIX
        );
        assertRecognized(reader, "mixed_barcodes.png", 1);
    }

    private void assertRecognized(BarCodeReader barCodeReader, String imageName, int count) throws Exception {
        BarCodeResult[] results = barCodeReader.readBarCodes();

        // Log results
        System.out.println("Results for " + imageName + ":");
        for (BarCodeResult result : results) {
            System.out.println("  Code Type: " + result.getCodeTypeName() +
                    " | Code Text: " + result.getCodeText() +
                    " | Quality: " + result.getReadingQuality());
        }

        Assert.assertTrue(
                results.length >= count,
                "Expected at least " + count + " result(s) in " + imageName + ", but got " + results.length
        );
    }

}