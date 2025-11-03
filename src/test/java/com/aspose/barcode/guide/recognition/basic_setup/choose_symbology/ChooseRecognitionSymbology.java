package com.aspose.barcode.guide.recognition.basic_setup.choose_symbology;

import com.aspose.barcode.barcoderecognition.BarCodeReader;
import com.aspose.barcode.barcoderecognition.BarCodeResult;
import com.aspose.barcode.barcoderecognition.DecodeType;
import com.aspose.barcode.barcoderecognition.QualitySettings;
import com.aspose.barcode.generation.BarCodeImageFormat;
import com.aspose.barcode.generation.BarcodeGenerator;
import com.aspose.barcode.generation.EncodeTypes;
import com.aspose.barcode.guide.common.ExampleAssist;
import com.aspose.barcode.guide.common.LicenseAssist;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class ChooseRecognitionSymbology {

    private static final String FOLDER =
            ExampleAssist.getOrCreateResourceFolderPath("recognition", "basic_setup", "choose_symbology");

    @BeforeClass
    public void setUp() {
        LicenseAssist.setupLicense();
    }

    // ==================== 1D Barcodes ====================

    // --- Code 128 ---
    @Test
    public void read_Code128_NormalQuality() throws Exception {
        String fileName = "code128.png";
        ExampleAssist.checkOrCreateImage(FOLDER, fileName, path -> {
            BarcodeGenerator generator = new BarcodeGenerator(EncodeTypes.CODE_128, "123456789");
            generator.save(path, BarCodeImageFormat.PNG);
        });
        BarCodeReader reader = new BarCodeReader(ExampleAssist.pathCombine(FOLDER, fileName), DecodeType.CODE_128);
        ExampleAssist.assertRecognized(reader, fileName, 1, DecodeType.CODE_128);
    }

    // --- Code 39 ---
    @Test
    public void read_Code39() throws Exception {
        String fileName = "code39.png";
        BarCodeReader reader = new BarCodeReader(ExampleAssist.pathCombine(FOLDER, fileName), DecodeType.CODE_39);
        ExampleAssist.assertRecognized(reader, fileName, 1, DecodeType.CODE_39);
    }

    @Test
    public void read_Code39FullASCII() throws Exception {
        String fileName = "code39_full_ascii.png";
        BarCodeReader reader = new BarCodeReader(ExampleAssist.pathCombine(FOLDER, fileName), DecodeType.CODE_39_FULL_ASCII);
        ExampleAssist.assertRecognized(reader, fileName, 1, DecodeType.CODE_39_FULL_ASCII);
    }

    // --- EAN-13 ---
    @Test
    public void read_EAN13() throws Exception {
        String fileName = "ean13.png";
        BarCodeReader reader = new BarCodeReader(ExampleAssist.pathCombine(FOLDER, fileName), DecodeType.EAN_13);
        ExampleAssist.assertRecognized(reader, fileName, 1, DecodeType.EAN_13);
    }

    @Test
    public void read_EAN13_WithSupplement() throws Exception {
        String fileName = "ean13_supplement.png";
        BarCodeReader reader = new BarCodeReader(ExampleAssist.pathCombine(FOLDER, fileName), DecodeType.EAN_13);
        reader.getBarcodeSettings().setDetectEncoding(true);
        ExampleAssist.assertRecognized(reader, fileName, 1, DecodeType.EAN_13);
    }

    // --- EAN-8 ---
    @Test
    public void read_EAN8() throws Exception {
        String fileName = "ean8.png";
        BarCodeReader reader = new BarCodeReader(ExampleAssist.pathCombine(FOLDER, fileName), DecodeType.EAN_8);
        ExampleAssist.assertRecognized(reader, fileName, 1, DecodeType.EAN_8);
    }

    // --- UPC-A ---
    @Test
    public void read_UPCA() throws Exception {
        String fileName = "upca.png";
        BarCodeReader reader = new BarCodeReader(ExampleAssist.pathCombine(FOLDER, fileName), DecodeType.UPCA);
        ExampleAssist.assertRecognized(reader, fileName, 1, DecodeType.UPCA);
    }

    // --- UPC-E ---
    @Test
    public void read_UPCE() throws Exception {
        String fileName = "upce.png";
        BarCodeReader reader = new BarCodeReader(ExampleAssist.pathCombine(FOLDER, fileName), DecodeType.UPCE);
        ExampleAssist.assertRecognized(reader, fileName, 1, DecodeType.UPCE);
    }

    // --- Codabar ---
    @Test
    public void read_Codabar() throws Exception {
        String fileName = "codabar.png";
        BarCodeReader reader = new BarCodeReader(ExampleAssist.pathCombine(FOLDER, fileName), DecodeType.CODABAR);
        ExampleAssist.assertRecognized(reader, fileName, 1, DecodeType.CODABAR);
    }

    // --- ITF-14 ---
    @Test
    public void read_ITF14() throws Exception {
        String fileName = "itf14.png";
        BarCodeReader reader = new BarCodeReader(ExampleAssist.pathCombine(FOLDER, fileName), DecodeType.ITF_14);
        ExampleAssist.assertRecognized(reader, fileName, 1, DecodeType.ITF_14);
    }

    // ==================== 2D Barcodes ====================

    // --- QR Code ---
    @Test
    public void read_QRCode_Standard() throws Exception {
        String fileName = "qrcode.png";
        BarCodeReader reader = new BarCodeReader(ExampleAssist.pathCombine(FOLDER, fileName), DecodeType.QR);
        ExampleAssist.assertRecognized(reader, fileName, 1, DecodeType.QR);
    }

    @Test
    public void read_QRCode_Micro() throws Exception {
        String fileName = "microqr.png";
        BarCodeReader reader = new BarCodeReader(ExampleAssist.pathCombine(FOLDER, fileName), DecodeType.MICRO_QR);
        ExampleAssist.assertRecognized(reader, fileName, 1, DecodeType.MICRO_QR);
    }

    @Test
    public void read_QRCode_Damaged() throws Exception {
        String fileName = "qrcode_damaged.png";
        BarCodeReader reader = new BarCodeReader(ExampleAssist.pathCombine(FOLDER, fileName), DecodeType.QR);
        reader.setQualitySettings(QualitySettings.getHighQuality());
        ExampleAssist.assertRecognized(reader, fileName, 1, DecodeType.QR);
    }

    // --- Data Matrix ---
    @Test
    public void read_DataMatrix_Standard() throws Exception {
        String fileName = "datamatrix.png";
        BarCodeReader reader = new BarCodeReader(ExampleAssist.pathCombine(FOLDER, fileName), DecodeType.DATA_MATRIX);
        ExampleAssist.assertRecognized(reader, fileName, 1, DecodeType.DATA_MATRIX);
    }

    @Test
    public void read_DataMatrix_GS1() throws Exception {
        String fileName = "datamatrix_gs1.png";
        BarCodeReader reader = new BarCodeReader(ExampleAssist.pathCombine(FOLDER, fileName), DecodeType.GS_1_DATA_MATRIX);
        ExampleAssist.assertRecognized(reader, fileName, 1, DecodeType.GS_1_DATA_MATRIX);
    }

    // --- PDF417 ---
    @Test
    public void read_PDF417_Standard() throws Exception {
        String fileName = "pdf417.png";
        BarCodeReader reader = new BarCodeReader(ExampleAssist.pathCombine(FOLDER, fileName), DecodeType.PDF_417);
        ExampleAssist.assertRecognized(reader, fileName, 1, DecodeType.PDF_417);
    }

    @Test
    public void read_PDF417_Compact() throws Exception {
        String fileName = "pdf417_compact.png";
        BarCodeReader reader = new BarCodeReader(ExampleAssist.pathCombine(FOLDER, fileName), DecodeType.COMPACT_PDF_417);
        ExampleAssist.assertRecognized(reader, fileName, 1, DecodeType.COMPACT_PDF_417);
    }

    @Test
    public void read_PDF417_Macro() throws Exception {
        String fileName = "pdf417_macro.png";
        BarCodeReader reader = new BarCodeReader(ExampleAssist.pathCombine(FOLDER, fileName), DecodeType.MACRO_PDF_417);
        ExampleAssist.assertRecognized(reader, fileName, 1, DecodeType.MACRO_PDF_417);
    }

    // --- Aztec ---
    @Test
    public void read_Aztec() throws Exception {
        String fileName = "aztec.png";
        BarCodeReader reader = new BarCodeReader(ExampleAssist.pathCombine(FOLDER, fileName), DecodeType.AZTEC);
        ExampleAssist.assertRecognized(reader, fileName, 1, DecodeType.AZTEC);
    }

    // ==================== Postal Barcodes ====================

    @Test
    public void read_Postnet() throws Exception {
        String fileName = "postnet.png";
        BarCodeReader reader = new BarCodeReader(ExampleAssist.pathCombine(FOLDER, fileName), DecodeType.POSTNET);
        ExampleAssist.assertRecognized(reader, fileName, 1, DecodeType.POSTNET);
    }

    @Test
    public void read_Planet() throws Exception {
        String fileName = "planet.png";
        BarCodeReader reader = new BarCodeReader(ExampleAssist.pathCombine(FOLDER, fileName), DecodeType.PLANET);
        ExampleAssist.assertRecognized(reader, fileName, 1, DecodeType.PLANET);
    }

    @Test
    public void read_AustraliaPost() throws Exception {
        String fileName = "australia_post.png";
        BarCodeReader reader = new BarCodeReader(ExampleAssist.pathCombine(FOLDER, fileName), DecodeType.AUSTRALIA_POST);
        ExampleAssist.assertRecognized(reader, fileName, 1, DecodeType.AUSTRALIA_POST);
    }

    // ==================== Multiple Types ====================

    @Test
    public void read_AllTypes() throws Exception {
        String fileName = "mixed_barcodes.png";
        BarCodeReader reader = new BarCodeReader(ExampleAssist.pathCombine(FOLDER, fileName), DecodeType.ALL_SUPPORTED_TYPES);
        // For a mixed image, we only check the quantity
        ExampleAssist.assertRecognized(reader, fileName, 1, DecodeType.ALL_SUPPORTED_TYPES);
    }

    @Test
    public void read_1D_Types() throws Exception {
        String fileName = "types_1D_barcodes.png";
        BarCodeReader reader = new BarCodeReader(ExampleAssist.pathCombine(FOLDER, fileName), DecodeType.TYPES_1D);
        ExampleAssist.assertRecognized(reader, fileName, 1, DecodeType.TYPES_1D);
    }

    @Test
    public void read_2D_Types() throws Exception {
        String fileName = "types_2D_barcodes.png";
        BarCodeReader reader = new BarCodeReader(ExampleAssist.pathCombine(FOLDER, fileName), DecodeType.TYPES_2D);
        ExampleAssist.assertRecognized(reader, fileName, 1, DecodeType.TYPES_2D);

    }

    @Test
    public void read_SpecificTypes() throws Exception {
        String fileName = "mixed_barcodes.png";
        BarCodeReader reader = new BarCodeReader(ExampleAssist.pathCombine(FOLDER, fileName), DecodeType.ALL_SUPPORTED_TYPES);
        reader.setBarCodeReadType(DecodeType.CODE_128, DecodeType.QR, DecodeType.DATA_MATRIX);

        ExampleAssist.assertRecognized(reader, fileName, 1, DecodeType.DATA_MATRIX);
    }


}
