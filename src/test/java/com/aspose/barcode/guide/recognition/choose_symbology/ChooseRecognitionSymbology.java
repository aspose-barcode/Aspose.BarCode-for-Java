package com.aspose.barcode.guide.recognition.choose_symbology;

import com.aspose.barcode.barcoderecognition.BarCodeReader;
import com.aspose.barcode.barcoderecognition.DecodeType;
import com.aspose.barcode.barcoderecognition.QualitySettings;
import com.aspose.barcode.generation.BarCodeImageFormat;
import com.aspose.barcode.generation.BarcodeGenerator;
import com.aspose.barcode.generation.EncodeTypes;
import com.aspose.barcode.guide.common.ExampleAssist;
import com.aspose.barcode.guide.common.LicenseAssist;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static com.aspose.barcode.guide.common.ExampleAssist.checkOrCreateImage;

public class ChooseRecognitionSymbology
{

    private static final String FOLDER =
            ExampleAssist.getOrCreateResourceFolderPath("recognition", "choose_symbology");

    @BeforeClass
    public void setUp()
    {
        LicenseAssist.setupLicense();
    }

    // ==================== 1D Barcodes ====================

    // --- Code 128 ---
    @Test
    public void read_Code128_NormalQuality() throws Exception
    {
        // Generate a valid Code 128 and verify recognition with DecodeType.CODE_128
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
    public void read_Code39() throws Exception
    {
        // Generate a standard Code 39 and verify recognition with DecodeType.CODE_39
        String fileName = "code39.png";
        ExampleAssist.checkOrCreateImage(FOLDER, fileName, path -> {
            BarcodeGenerator generator = new BarcodeGenerator(EncodeTypes.CODE_39, "CODE39-123");
            generator.save(path, BarCodeImageFormat.PNG);
        });
        BarCodeReader reader = new BarCodeReader(ExampleAssist.pathCombine(FOLDER, fileName), DecodeType.CODE_39);
        ExampleAssist.assertRecognized(reader, fileName, 1, DecodeType.CODE_39);
    }

    @Test
    public void read_Code39FullASCII() throws Exception
    {
        // Generate Code 39 Full ASCII (uses characters requiring Full ASCII mode) and verify
        String fileName = "code39_full_ascii.png";
        ExampleAssist.checkOrCreateImage(FOLDER, fileName, path -> {
            BarcodeGenerator generator = new BarcodeGenerator(EncodeTypes.CODE_39_FULL_ASCII, "Full+ASCII/%$-123");
            generator.save(path, BarCodeImageFormat.PNG);
        });
        BarCodeReader reader = new BarCodeReader(ExampleAssist.pathCombine(FOLDER, fileName), DecodeType.CODE_39_FULL_ASCII);
        ExampleAssist.assertRecognized(reader, fileName, 1, DecodeType.CODE_39_FULL_ASCII);
    }

    // --- EAN-13 ---
    @Test
    public void read_EAN13() throws Exception
    {
        // Generate a valid EAN-13 and verify recognition with DecodeType.EAN_13
        String fileName = "ean13.png";
        ExampleAssist.checkOrCreateImage(FOLDER, fileName, path -> {
            BarcodeGenerator generator = new BarcodeGenerator(EncodeTypes.EAN_13, "5901234123457");
            generator.save(path, BarCodeImageFormat.PNG);
        });
        BarCodeReader reader = new BarCodeReader(ExampleAssist.pathCombine(FOLDER, fileName), DecodeType.EAN_13);
        ExampleAssist.assertRecognized(reader, fileName, 1, DecodeType.EAN_13);
    }

    @Test
    public void read_EAN13_WithSupplement() throws Exception
    {
        // Generate EAN-13 with a 5-digit supplement and verify recognition with DecodeType.EAN_13
        String fileName = "ean13_supplement.png";
        ExampleAssist.checkOrCreateImage(FOLDER, fileName, path -> {
            BarcodeGenerator generator = new BarcodeGenerator(EncodeTypes.EAN_13, "5901234123457");
            generator.getParameters().getBarcode().getSupplement().setSupplementData("12345");
            generator.save(path, BarCodeImageFormat.PNG);
        });
        BarCodeReader reader = new BarCodeReader(ExampleAssist.pathCombine(FOLDER, fileName), DecodeType.EAN_13);
        reader.getBarcodeSettings().setDetectEncoding(true);
        ExampleAssist.assertRecognized(reader, fileName, 1, DecodeType.EAN_13);
    }

    // --- EAN-8 ---
    @Test
    public void read_EAN8() throws Exception
    {
        // Generate a valid EAN-8 and verify recognition with DecodeType.EAN_8
        String fileName = "ean8.png";
        ExampleAssist.checkOrCreateImage(FOLDER, fileName, path -> {
            BarcodeGenerator generator = new BarcodeGenerator(EncodeTypes.EAN_8, "12345670");
            generator.save(path, BarCodeImageFormat.PNG);
        });
        BarCodeReader reader = new BarCodeReader(ExampleAssist.pathCombine(FOLDER, fileName), DecodeType.EAN_8);
        ExampleAssist.assertRecognized(reader, fileName, 1, DecodeType.EAN_8);
    }

    // --- UPC-A ---
    @Test
    public void read_UPCA() throws Exception
    {
        // Generate a valid UPC-A (12 digits) and verify recognition with DecodeType.UPCA
        String fileName = "upca.png";
        ExampleAssist.checkOrCreateImage(FOLDER, fileName, path -> {
            BarcodeGenerator generator = new BarcodeGenerator(EncodeTypes.UPCA, "123456789012");
            generator.save(path, BarCodeImageFormat.PNG);
        });
        BarCodeReader reader = new BarCodeReader(ExampleAssist.pathCombine(FOLDER, fileName), DecodeType.UPCA);
        ExampleAssist.assertRecognized(reader, fileName, 1, DecodeType.UPCA);
    }

    // --- UPC-E ---
    @Test
    public void read_UPCE() throws Exception
    {
        // Generate a valid UPC-E and verify recognition with DecodeType.UPCE
        String fileName = "upce.png";
        ExampleAssist.checkOrCreateImage(FOLDER, fileName, path -> {
            // Common 6-digit UPC-E payload (checksum handled by generator)
            BarcodeGenerator generator = new BarcodeGenerator(EncodeTypes.UPCE, "123456");
            generator.save(path, BarCodeImageFormat.PNG);
        });
        BarCodeReader reader = new BarCodeReader(ExampleAssist.pathCombine(FOLDER, fileName), DecodeType.UPCE);
        ExampleAssist.assertRecognized(reader, fileName, 1, DecodeType.UPCE);
    }

    // --- Codabar ---
    @Test
    public void read_Codabar() throws Exception
    {
        // Generate Codabar with start/stop characters and verify recognition with DecodeType.CODABAR
        String fileName = "codabar.png";
        ExampleAssist.checkOrCreateImage(FOLDER, fileName, path -> {
            BarcodeGenerator generator = new BarcodeGenerator(EncodeTypes.CODABAR, "A123456A");
            generator.save(path, BarCodeImageFormat.PNG);
        });
        BarCodeReader reader = new BarCodeReader(ExampleAssist.pathCombine(FOLDER, fileName), DecodeType.CODABAR);
        ExampleAssist.assertRecognized(reader, fileName, 1, DecodeType.CODABAR);
    }

    // --- ITF-14 ---
    @Test
    public void read_ITF14() throws Exception
    {
        // Generate a valid ITF-14 and verify recognition with DecodeType.ITF_14
        String fileName = "itf14.png";
        ExampleAssist.checkOrCreateImage(FOLDER, fileName, path -> {
            BarcodeGenerator generator = new BarcodeGenerator(EncodeTypes.ITF_14, "12345678901231");
            generator.save(path, BarCodeImageFormat.PNG);
        });
        BarCodeReader reader = new BarCodeReader(ExampleAssist.pathCombine(FOLDER, fileName), DecodeType.ITF_14);
        ExampleAssist.assertRecognized(reader, fileName, 1, DecodeType.ITF_14);
    }

    // ==================== 2D Barcodes ====================

    // --- QR Code ---
    @Test
    public void read_QRCode_Standard() throws Exception
    {
        // Generate a standard QR and verify recognition with DecodeType.QR
        String fileName = "qrcode.png";
        ExampleAssist.checkOrCreateImage(FOLDER, fileName, path -> {
            BarcodeGenerator generator = new BarcodeGenerator(EncodeTypes.QR, "Hello QR");
            generator.save(path, BarCodeImageFormat.PNG);
        });
        BarCodeReader reader = new BarCodeReader(ExampleAssist.pathCombine(FOLDER, fileName), DecodeType.QR);
        ExampleAssist.assertRecognized(reader, fileName, 1, DecodeType.QR);
    }

    @Test
    public void read_QRCode_Micro() throws Exception
    {
        // Generate a Micro QR and verify recognition with DecodeType.MICRO_QR
        String fileName = "microqr.png";
        ExampleAssist.checkOrCreateImage(FOLDER, fileName, path -> {
            BarcodeGenerator generator = new BarcodeGenerator(EncodeTypes.MICRO_QR, "MQR");
            generator.save(path, BarCodeImageFormat.PNG);
        });
        BarCodeReader reader = new BarCodeReader(ExampleAssist.pathCombine(FOLDER, fileName), DecodeType.MICRO_QR);
        ExampleAssist.assertRecognized(reader, fileName, 1, DecodeType.MICRO_QR);
    }

    @Test
    public void read_QRCode_Damaged() throws Exception
    {
        // Generate a QR and read it using HighQuality settings to simulate tough conditions
        String fileName = "qrcode_damaged.png";
        ExampleAssist.checkOrCreateImage(FOLDER, fileName, path -> {
            BarcodeGenerator generator = new BarcodeGenerator(EncodeTypes.QR, "Damaged?");
            generator.save(path, BarCodeImageFormat.PNG);
        });
        BarCodeReader reader = new BarCodeReader(ExampleAssist.pathCombine(FOLDER, fileName), DecodeType.QR);
        reader.setQualitySettings(QualitySettings.getHighQuality());
        ExampleAssist.assertRecognized(reader, fileName, 1, DecodeType.QR);
    }

    // --- Data Matrix ---
    @Test
    public void read_DataMatrix_Standard() throws Exception
    {
        // Generate a Data Matrix and verify recognition with DecodeType.DATA_MATRIX
        String fileName = "datamatrix.png";
        ExampleAssist.checkOrCreateImage(FOLDER, fileName, path -> {
            BarcodeGenerator generator = new BarcodeGenerator(EncodeTypes.DATA_MATRIX, "DMATRIX-12345");
            generator.save(path, BarCodeImageFormat.PNG);
        });
        BarCodeReader reader = new BarCodeReader(ExampleAssist.pathCombine(FOLDER, fileName), DecodeType.DATA_MATRIX);
        ExampleAssist.assertRecognized(reader, fileName, 1, DecodeType.DATA_MATRIX);
    }

    @Test
    public void read_DataMatrix_GS1() throws Exception
    {
        // Generate a GS1 Data Matrix with AIs and verify recognition with DecodeType.GS_1_DATA_MATRIX
        String fileName = "datamatrix_gs1.png";
        ExampleAssist.checkOrCreateImage(FOLDER, fileName, path -> {
            BarcodeGenerator generator = new BarcodeGenerator(EncodeTypes.GS_1_DATA_MATRIX, "(01)12345678901231(10)ABCD1234");
            generator.save(path, BarCodeImageFormat.PNG);
        });
        BarCodeReader reader = new BarCodeReader(ExampleAssist.pathCombine(FOLDER, fileName), DecodeType.GS_1_DATA_MATRIX);
        ExampleAssist.assertRecognized(reader, fileName, 1, DecodeType.GS_1_DATA_MATRIX);
    }

    // --- PDF417 ---
    @Test
    public void read_PDF417_Standard() throws Exception
    {
        // Generate a standard PDF417 and verify recognition with DecodeType.PDF_417
        String fileName = "pdf417.png";
        ExampleAssist.checkOrCreateImage(FOLDER, fileName, path -> {
            BarcodeGenerator generator = new BarcodeGenerator(EncodeTypes.PDF_417, "PDF417 sample");
            generator.save(path, BarCodeImageFormat.PNG);
        });
        BarCodeReader reader = new BarCodeReader(ExampleAssist.pathCombine(FOLDER, fileName), DecodeType.PDF_417);
        ExampleAssist.assertRecognized(reader, fileName, 1, DecodeType.PDF_417);
    }

    @Test
    public void read_PDF417_Macro() throws Exception
    {
        // Generate a Macro PDF417 and verify recognition with DecodeType.MACRO_PDF_417
        String fileName = "pdf417_macro.png";
        ExampleAssist.checkOrCreateImage(FOLDER, fileName, path -> {
            BarcodeGenerator generator = new BarcodeGenerator(EncodeTypes.MACRO_PDF_417, "Macro segment");
            generator.save(path, BarCodeImageFormat.PNG);
        });
        BarCodeReader reader = new BarCodeReader(ExampleAssist.pathCombine(FOLDER, fileName), DecodeType.MACRO_PDF_417);
        ExampleAssist.assertRecognized(reader, fileName, 1, DecodeType.MACRO_PDF_417);
    }

    // --- Aztec ---
    @Test
    public void read_Aztec() throws Exception
    {
        // Generate an Aztec code and verify recognition with DecodeType.AZTEC
        String fileName = "aztec.png";
        ExampleAssist.checkOrCreateImage(FOLDER, fileName, path -> {
            BarcodeGenerator generator = new BarcodeGenerator(EncodeTypes.AZTEC, "AZTEC-OK");
            generator.save(path, BarCodeImageFormat.PNG);
        });
        BarCodeReader reader = new BarCodeReader(ExampleAssist.pathCombine(FOLDER, fileName), DecodeType.AZTEC);
        ExampleAssist.assertRecognized(reader, fileName, 1, DecodeType.AZTEC);
    }

    // ==================== Postal Barcodes ====================

    @Test
    public void read_Postnet() throws Exception
    {
        // Generate a POSTNET (5-digit) and verify recognition with DecodeType.POSTNET
        String fileName = "postnet.png";
        ExampleAssist.checkOrCreateImage(FOLDER, fileName, path -> {
            BarcodeGenerator generator = new BarcodeGenerator(EncodeTypes.POSTNET, "12345");
            generator.save(path, BarCodeImageFormat.PNG);
        });
        BarCodeReader reader = new BarCodeReader(ExampleAssist.pathCombine(FOLDER, fileName), DecodeType.POSTNET);
        ExampleAssist.assertRecognized(reader, fileName, 1, DecodeType.POSTNET);
    }

    @Test
    public void read_Planet() throws Exception
    {
        // Generate a PLANET (12-digit) and verify recognition with DecodeType.PLANET
        String fileName = "planet.png";
        ExampleAssist.checkOrCreateImage(FOLDER, fileName, path -> {
            BarcodeGenerator generator = new BarcodeGenerator(EncodeTypes.PLANET, "401234567890");
            generator.save(path, BarCodeImageFormat.PNG);
        });
        BarCodeReader reader = new BarCodeReader(ExampleAssist.pathCombine(FOLDER, fileName), DecodeType.PLANET);
        ExampleAssist.assertRecognized(reader, fileName, 1, DecodeType.PLANET);
    }

    @Test
    public void read_AustraliaPost() throws Exception
    {
        // Generate valid Australia Post: FCC(11) + 8-digit DPID
        String fileName = "australia_post.png";
        ExampleAssist.checkOrCreateImage(FOLDER, fileName, path -> {
            String codeText = "11" + "12345678"; // valid FCC + DPID
            BarcodeGenerator generator = new BarcodeGenerator(EncodeTypes.AUSTRALIA_POST, codeText);
            generator.save(path, BarCodeImageFormat.PNG);
        });

        BarCodeReader reader = new BarCodeReader(ExampleAssist.pathCombine(FOLDER, fileName), DecodeType.AUSTRALIA_POST);
        ExampleAssist.assertRecognized(reader, fileName, 1, DecodeType.AUSTRALIA_POST);
    }


    // ==================== Multiple Types ====================

    @Test
    public void read_AllTypes() throws Exception
    {
        // Generate a QR (any supported type works) and read with DecodeType.ALL_SUPPORTED_TYPES
        String fileName = "all_types.png";
        ExampleAssist.checkOrCreateImage(FOLDER, fileName, path -> {
            BarcodeGenerator generator = new BarcodeGenerator(EncodeTypes.QR, "MIXED");
            generator.save(path, BarCodeImageFormat.PNG);
        });
        BarCodeReader reader =
                new BarCodeReader(ExampleAssist.pathCombine(FOLDER, fileName), DecodeType.ALL_SUPPORTED_TYPES);
        // We still pass the expected type required by assertRecognized
        ExampleAssist.assertRecognized(reader, fileName, 1, DecodeType.QR);
    }

    @Test
    public void read_1D_Types() throws Exception
    {
        // Generate a 1D barcode (Code 128) and read with DecodeType.TYPES_1D
        String fileName = "types_1D_barcodes.png";
        ExampleAssist.checkOrCreateImage(FOLDER, fileName, path -> {
            BarcodeGenerator generator = new BarcodeGenerator(EncodeTypes.CODE_128, "1D-SET");
            generator.save(path, BarCodeImageFormat.PNG);
        });
        BarCodeReader reader =
                new BarCodeReader(ExampleAssist.pathCombine(FOLDER, fileName), DecodeType.TYPES_1D);
        ExampleAssist.assertRecognized(reader, fileName, 1, DecodeType.CODE_128);
    }

    @Test
    public void read_2D_Types() throws Exception
    {
        // Generate a 2D barcode (Data Matrix) and read with DecodeType.TYPES_2D
        String fileName = "types_2D_barcodes.png";
        ExampleAssist.checkOrCreateImage(FOLDER, fileName, path -> {
            BarcodeGenerator generator = new BarcodeGenerator(EncodeTypes.DATA_MATRIX, "2D-SET");
            generator.save(path, BarCodeImageFormat.PNG);
        });
        BarCodeReader reader =
                new BarCodeReader(ExampleAssist.pathCombine(FOLDER, fileName), DecodeType.TYPES_2D);
        ExampleAssist.assertRecognized(reader, fileName, 1, DecodeType.DATA_MATRIX);
    }

    @Test
    public void read_SpecificTypes() throws Exception
    {
        // Generate a Data Matrix, limit reader to CODE_128 + QR + DATA_MATRIX, expect at least one match of DATA_MATRIX
        String fileName = "specific_types.png";
        ExampleAssist.checkOrCreateImage(FOLDER, fileName, path -> {
            BarcodeGenerator generator = new BarcodeGenerator(EncodeTypes.DATA_MATRIX, "Specific types");
            generator.save(path, BarCodeImageFormat.PNG);
        });
        BarCodeReader reader = new BarCodeReader(ExampleAssist.pathCombine(FOLDER, fileName), DecodeType.ALL_SUPPORTED_TYPES);
        reader.setBarCodeReadType(DecodeType.CODE_128, DecodeType.QR, DecodeType.DATA_MATRIX);
        ExampleAssist.assertRecognized(reader, fileName, 1, DecodeType.DATA_MATRIX);
    }

    // --- GS1-128 only (structured AI data) ---
    @Test
    public void readGs1_128Only() throws Exception {
        String fileName = "gs1_128.png";
        checkOrCreateImage(FOLDER, fileName, (fullPath) -> {
            String gs1 = "(01)09501101530008(17)251231(10)BATCH-42";
            BarcodeGenerator g = new BarcodeGenerator(EncodeTypes.GS_1_CODE_128, gs1);
            g.getParameters().getBarcode().getXDimension().setPixels(2);
            g.getParameters().getBarcode().getBarHeight().setPixels(60);
            g.save(fullPath, BarCodeImageFormat.PNG);
        });
        BarCodeReader reader = new BarCodeReader(ExampleAssist.pathCombine(FOLDER, fileName), DecodeType.GS_1_CODE_128);
        ExampleAssist.assertRecognized(reader, fileName, 1, DecodeType.GS_1_CODE_128);
    }

    // --- Multiple barcodes in one image (expect >= 2) ---
    @Test
    public void readMultipleInOneImageAllSupported() throws Exception {
        String fileName = createMultiImage(); // collage with two barcodes
        BarCodeReader reader = new BarCodeReader(ExampleAssist.pathCombine(FOLDER,fileName), DecodeType.ALL_SUPPORTED_TYPES);
        ExampleAssist.assertRecognized(reader, fileName, 2, DecodeType.CODE_128, DecodeType.QR);
    }

    private String createMultiImage() throws Exception {
        String fileName = "multi.png";
        String tmp1 = ExampleAssist.pathCombine(FOLDER,"tmp_multi_code128.png");
        String tmp2 = ExampleAssist.pathCombine(FOLDER,"tmp_multi_qr.png");



        BarcodeGenerator g1 = new BarcodeGenerator(EncodeTypes.CODE_128, "MULTI-1-C128");
        g1.getParameters().getBarcode().getXDimension().setPixels(2);
        g1.getParameters().getBarcode().getBarHeight().setPixels(60);
        g1.save(tmp1, BarCodeImageFormat.PNG);

        BarcodeGenerator g2 = new BarcodeGenerator(EncodeTypes.QR, "MULTI-2-QR");
        g2.getParameters().getBarcode().getXDimension().setPixels(4);
        g2.save(tmp2, BarCodeImageFormat.PNG);

        try {
            BufferedImage left = ImageIO.read(Paths.get(tmp1).toFile());
            BufferedImage right = ImageIO.read(Paths.get(tmp2).toFile());

            int gap = 20;
            int w = left.getWidth() + gap + right.getWidth();
            int h = Math.max(left.getHeight(), right.getHeight());

            BufferedImage canvas = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g = canvas.createGraphics();
            g.drawImage(left, 0, 0, null);
            g.drawImage(right, left.getWidth() + gap, 0, null);
            g.dispose();

            ImageIO.write(canvas, "PNG", Paths.get(ExampleAssist.pathCombine(FOLDER, fileName)).toFile());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try { Files.deleteIfExists(Paths.get(tmp1)); } catch (Exception ignored) {}
            try { Files.deleteIfExists(Paths.get(tmp2)); } catch (Exception ignored) {}
        }
        return fileName;
    }

}
