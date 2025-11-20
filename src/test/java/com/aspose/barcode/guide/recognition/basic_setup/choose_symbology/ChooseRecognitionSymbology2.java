package com.aspose.barcode.guide.recognition.basic_setup.choose_symbology;

import com.aspose.barcode.barcoderecognition.BarCodeReader;
import com.aspose.barcode.barcoderecognition.BarCodeResult;
import com.aspose.barcode.barcoderecognition.DecodeType;
import com.aspose.barcode.generation.BarCodeImageFormat;
import com.aspose.barcode.generation.BarcodeGenerator;
import com.aspose.barcode.generation.EncodeTypes;
import com.aspose.barcode.guide.common.ExampleAssist;
import com.aspose.barcode.guide.common.LicenseAssist;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static com.aspose.barcode.guide.common.ExampleAssist.checkOrCreateImage;

public class ChooseRecognitionSymbology2
{

    private static final String IMAGES_FOLDER =
            ExampleAssist.getOrCreateResourceFolderPath("recognition", "basic_setup", "recognition_symbology");

    @BeforeClass
    public void setUp() throws Exception {
        LicenseAssist.setupLicense();
    }

    // --- Single symbology: Code 128 ---
    @Test
    public void readCode128SingleType() throws Exception {
        ensureOrCreateCode128Png();
        BarCodeReader reader = new BarCodeReader(getFullPath("code128.png"), DecodeType.CODE_128);
        assertRecognized(reader, "code128.png", 1);
    }

    // --- Single symbology: QR ---
    @Test
    public void readQrSingleType() throws Exception {
        ensureOrCreateQrPng();
        BarCodeReader reader = new BarCodeReader(getFullPath("qrcode.png"), DecodeType.QR);
        assertRecognized(reader, "qrcode.png", 1);
    }

    // --- Single symbology: EAN-13 ---
    @Test
    public void readEan13SingleType() throws Exception {
        ensureOrCreateEan13Png();
        BarCodeReader reader = new BarCodeReader(getFullPath("ean13.png"), DecodeType.EAN_13);
        assertRecognized(reader, "ean13.png", 1);
    }

    // --- Multiple explicit symbologies: Code128 or QR ---
    @Test
    public void readCode128OrQr() throws Exception {
        ensureOrCreateMixedPng(); // contains a QR code; test expects >= 1 with either type
        BarCodeReader reader = new BarCodeReader(getFullPath("mixed.png"), DecodeType.CODE_128, DecodeType.QR);
        assertRecognized(reader, "mixed.png", 1);
    }

    // --- 1D group (TYPES_1D): detect any linear type like Code128, EANs, ITF, etc. ---
    @Test
    public void readAny1dGroup() throws Exception {
        ensureOrCreateEan13Png();
        BarCodeReader reader = new BarCodeReader(getFullPath("ean13.png"), DecodeType.TYPES_1D);
        assertRecognized(reader, "ean13.png", 1);
    }

    // --- 2D group (TYPES_2D): detect Data Matrix, QR, PDF417, Aztec, etc. ---
    @Test
    public void readAny2dGroup() throws Exception {
        ensureOrCreateDatamatrixPng();
        BarCodeReader reader = new BarCodeReader(getFullPath("datamatrix.png"), DecodeType.TYPES_2D);
        assertRecognized(reader, "datamatrix.png", 1);
    }



    // --- Multiple barcodes in one image (expect >= 2) ---
    @Test
    public void readMultipleInOneImageAllSupported() throws Exception {
        createMultiImage(); // collage with two barcodes
        BarCodeReader reader = new BarCodeReader(getFullPath("multi.png"), DecodeType.ALL_SUPPORTED_TYPES);
        assertRecognized(reader, "multi.png", 2);
    }

    // ---------------- Helpers ----------------

    private static String getFullPath(String fileName) {
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

    // ---------------- Fixture generators (private) ----------------

    private void ensureOrCreateCode128Png() throws Exception {
        checkOrCreateImage(IMAGES_FOLDER, "code128.png", (fullPath) -> {
            BarcodeGenerator g = new BarcodeGenerator(EncodeTypes.CODE_128, "ASPOSE-CODE128");
            g.getParameters().getBarcode().getXDimension().setPixels(2);
            g.getParameters().getBarcode().getBarHeight().setPixels(60);
            g.save(fullPath, BarCodeImageFormat.PNG);
        });
    }

    private void ensureOrCreateQrPng() throws Exception {
        checkOrCreateImage(IMAGES_FOLDER, "qrcode.png", (fullPath) -> {
            BarcodeGenerator g = new BarcodeGenerator(EncodeTypes.QR, "https://aspose.com");
            g.getParameters().getBarcode().getXDimension().setPixels(4);
            g.save(fullPath, BarCodeImageFormat.PNG);
        });
    }

    private void ensureOrCreateEan13Png() throws Exception {
        checkOrCreateImage(IMAGES_FOLDER, "ean13.png", (fullPath) -> {
            BarcodeGenerator g = new BarcodeGenerator(EncodeTypes.EAN_13, "5901234123457");
            g.getParameters().getBarcode().getXDimension().setPixels(2);
            g.save(fullPath, BarCodeImageFormat.PNG);
        });
    }

    private void ensureOrCreateDatamatrixPng() throws Exception {
        checkOrCreateImage(IMAGES_FOLDER, "datamatrix.png", (fullPath) -> {
            BarcodeGenerator g = new BarcodeGenerator(EncodeTypes.DATA_MATRIX, "DMX-DEMO-001");
            g.getParameters().getBarcode().getXDimension().setPixels(4);
            g.save(fullPath, BarCodeImageFormat.PNG);
        });
    }

    private void ensureOrCreateGs1_128Png() throws Exception {
        checkOrCreateImage(IMAGES_FOLDER, "gs1_128.png", (fullPath) -> {
            String gs1 = "(01)09501101530008(17)251231(10)BATCH-42";
            BarcodeGenerator g = new BarcodeGenerator(EncodeTypes.GS_1_CODE_128, gs1);
            g.getParameters().getBarcode().getXDimension().setPixels(2);
            g.getParameters().getBarcode().getBarHeight().setPixels(60);
            g.save(fullPath, BarCodeImageFormat.PNG);
        });
    }

    private void createMultiImage() throws Exception {
        String tmp1 = getFullPath("tmp_multi_code128.png");
        String tmp2 = getFullPath("tmp_multi_qr.png");

        Files.createDirectories(Paths.get(IMAGES_FOLDER));

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

            ImageIO.write(canvas, "PNG", Paths.get(getFullPath("multi.png")).toFile());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try { Files.deleteIfExists(Paths.get(tmp1)); } catch (Exception ignored) {}
            try { Files.deleteIfExists(Paths.get(tmp2)); } catch (Exception ignored) {}
        }
    }

    private void ensureOrCreateMixedPng() throws Exception {
        checkOrCreateImage(IMAGES_FOLDER, "mixed.png", (fullPath) -> {
            BarcodeGenerator g = new BarcodeGenerator(EncodeTypes.QR, "MIXED-SAMPLE");
            g.getParameters().getBarcode().getXDimension().setPixels(4);
            g.save(fullPath, BarCodeImageFormat.PNG);
        });
    }
}
