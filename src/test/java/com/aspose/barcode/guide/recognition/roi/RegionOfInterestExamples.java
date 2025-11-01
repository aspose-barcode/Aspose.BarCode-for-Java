package com.aspose.barcode.guide.recognition.roi;

import com.aspose.barcode.barcoderecognition.BarCodeReader;
import com.aspose.barcode.barcoderecognition.BarCodeResult;
import com.aspose.barcode.barcoderecognition.DecodeType;
import com.aspose.barcode.generation.BarCodeImageFormat;
import com.aspose.barcode.generation.BarcodeGenerator;
import com.aspose.barcode.generation.EncodeTypes;
import com.aspose.barcode.guide.common.ExampleAssist;
import com.aspose.barcode.guide.common.ImageSupplier;
import com.aspose.barcode.guide.common.LicenseAssist;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import javax.imageio.ImageIO;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.*;

import static com.aspose.barcode.guide.common.ExampleAssist.assertRecognized;

public class RegionOfInterestExamples {

    private static final String IMAGES_FOLDER =
            ExampleAssist.getOrCreateResourceFolderPath("quick_start", "recognition", "roi");

    @BeforeClass
    public void setUp() throws Exception {
        LicenseAssist.setupLicense();
        Files.createDirectories(Paths.get(IMAGES_FOLDER));
    }

    // ---- Single ROI for Code 128 on a composite canvas ----
    @Test
    public void roiCode128OnlyArea() throws Exception {
        ensureRoiCanvasPng();
        Rectangle roi = new Rectangle(20, 20, 440, 140);
        BarCodeReader reader = new BarCodeReader(getFullPath("roi_canvas.png"), roi, DecodeType.CODE_128);
        assertRecognized(reader, "roiCode128OnlyArea", 1, DecodeType.CODE_128);
    }

    // ---- Single ROI for QR on a composite canvas (using TYPES_2D) ----
    @Test
    public void roiQrAreaTypes2D() throws Exception {
        ensureRoiCanvasPng();
        Rectangle roi = new Rectangle(470, 10, 250, 250);
        BarCodeReader reader = new BarCodeReader(getFullPath("roi_canvas.png"), roi, DecodeType.TYPES_2D);
        assertRecognized(reader, "roiQrAreaTypes2D", 1, DecodeType.QR);
    }

    // ---- Multiple ROIs: scan top-left (Code128) and bottom-right (EAN-13) in one pass for 1D ----
    @Test
    public void roiTwoAreas1DPass() throws Exception {
        ensureRoiCanvasPng();
        Rectangle[] areas = new Rectangle[] {
                new Rectangle(20, 20, 440, 140),    // Code 128
                new Rectangle(470, 240, 450, 160)   // EAN-13
        };
        BarCodeReader reader = new BarCodeReader(getFullPath("roi_canvas.png"), areas, DecodeType.TYPES_1D);
        BarCodeResult[] results = reader.readBarCodes();
        boolean hasCode128 = false, hasEan13 = false;
        for (BarCodeResult r : results) {
            System.out.println("[roiTwoAreas1DPass] " + r.getCodeTypeName() + " | " + r.getCodeText());
            if (r.getCodeType() == DecodeType.CODE_128) hasCode128 = true;
            if (r.getCodeType() == DecodeType.EAN_13) hasEan13 = true;
        }
        Assert.assertTrue(hasCode128, "Expected CODE_128 in ROI results");
        Assert.assertTrue(hasEan13, "Expected EAN_13 in ROI results");
    }

    // ---- ROI vs full image comparison: full must find many, ROI must find the targeted symbol ----
    @Test
    public void roiVsFullImage() throws Exception {
        ensureRoiCanvasPng();

        BarCodeReader full = new BarCodeReader(getFullPath("roi_canvas.png"), DecodeType.ALL_SUPPORTED_TYPES);
        BarCodeResult[] fullResults = full.readBarCodes();
        Assert.assertTrue(fullResults.length >= 3, "Expected several symbols on the full canvas");

        Rectangle dmArea = new Rectangle(10, 190, 230, 230);
        BarCodeReader roiOnly = new BarCodeReader(getFullPath("roi_canvas.png"), dmArea, DecodeType.DATA_MATRIX);
        assertRecognized(roiOnly, "roi_canvas.png@roiDataMatrix", 1, DecodeType.DATA_MATRIX);
    }

    // ---- ROI with a single-image fixture where barcode is not at origin ----
    @Test
    public void roiSingleImageSubRegion() throws Exception {
        ensureCode128OffsetPng();
        Rectangle roi = new Rectangle(150, 80, 480, 160);
        BarCodeReader reader = new BarCodeReader(getFullPath("code128_offset.png"), roi, DecodeType.CODE_128);
        assertRecognized(reader, "", 1, DecodeType.CODE_128);
    }

    // ========================= Helpers =========================

    private static String getFullPath(String fileName) {
        return Paths.get(IMAGES_FOLDER, fileName).toString();
    }



    // ---------------- Fixture generators using your Generator interface ----------------

    private void ensureRoiCanvasPng() throws Exception {
        ensureImage("roi_canvas.png", (fullPath) -> {
            String tmpCode128 = getFullPath("tmp_roi_code128.png");
            String tmpQR     = getFullPath("tmp_roi_qr.png");
            String tmpDM     = getFullPath("tmp_roi_dm.png");
            String tmpEAN    = getFullPath("tmp_roi_ean13.png");

            BarcodeGenerator c128 = new BarcodeGenerator(EncodeTypes.CODE_128, "ROI-CODE128-12345");
            c128.getParameters().getBarcode().getXDimension().setPixels(2);
            c128.getParameters().getBarcode().getBarHeight().setPixels(60);
            c128.save(tmpCode128, BarCodeImageFormat.PNG);

            BarcodeGenerator qr = new BarcodeGenerator(EncodeTypes.QR, "ROI-QR-DEMO");
            qr.getParameters().getBarcode().getXDimension().setPixels(4);
            qr.save(tmpQR, BarCodeImageFormat.PNG);

            BarcodeGenerator dm = new BarcodeGenerator(EncodeTypes.DATA_MATRIX, "ROI-DM-0001");
            dm.getParameters().getBarcode().getXDimension().setPixels(4);
            dm.save(tmpDM, BarCodeImageFormat.PNG);

            BarcodeGenerator ean = new BarcodeGenerator(EncodeTypes.EAN_13, "5901234123457");
            ean.getParameters().getBarcode().getXDimension().setPixels(2);
            ean.save(tmpEAN, BarCodeImageFormat.PNG);

            try {
                BufferedImage code128Img = ImageIO.read(Paths.get(tmpCode128).toFile());
                BufferedImage qrImg      = ImageIO.read(Paths.get(tmpQR).toFile());
                BufferedImage dmImg      = ImageIO.read(Paths.get(tmpDM).toFile());
                BufferedImage eanImg     = ImageIO.read(Paths.get(tmpEAN).toFile());

                int canvasW = 920;
                int canvasH = 420;
                BufferedImage canvas = new BufferedImage(canvasW, canvasH, BufferedImage.TYPE_INT_ARGB);
                Graphics2D g = canvas.createGraphics();

                g.drawImage(code128Img, 20, 20, null);
                g.drawImage(qrImg,      480, 20, null);
                g.drawImage(dmImg,       20, 200, null);
                g.drawImage(eanImg,     480, 260, null);

                g.dispose();
                ImageIO.write(canvas, "PNG", Paths.get(fullPath).toFile());
            } catch (IOException e) {
                throw new RuntimeException(e);
            } finally {
                safeDelete(tmpCode128);
                safeDelete(tmpQR);
                safeDelete(tmpDM);
                safeDelete(tmpEAN);
            }
        });
    }

    private void ensureCode128OffsetPng() throws Exception {
        ensureImage("code128_offset.png", (fullPath) -> {
            String tmp = getFullPath("tmp_offset_code128.png");

            BarcodeGenerator gen = new BarcodeGenerator(EncodeTypes.CODE_128, "OFFSET-C128");
            gen.getParameters().getBarcode().getXDimension().setPixels(2);
            gen.getParameters().getBarcode().getBarHeight().setPixels(60);
            gen.save(tmp, BarCodeImageFormat.PNG);

            try {
                BufferedImage symbol = ImageIO.read(Paths.get(tmp).toFile());
                int canvasW = symbol.getWidth() + 200;
                int canvasH = symbol.getHeight() + 200;

                BufferedImage canvas = new BufferedImage(canvasW, canvasH, BufferedImage.TYPE_INT_ARGB);
                Graphics2D g = canvas.createGraphics();
                g.drawImage(symbol, 150, 80, null);
                g.dispose();

                ImageIO.write(canvas, "PNG", Paths.get(fullPath).toFile());
            } catch (IOException e) {
                throw new RuntimeException(e);
            } finally {
                safeDelete(tmp);
            }
        });
    }

    private void ensureImage(String fileName, ImageSupplier generator) throws Exception {
        Path p = Paths.get(IMAGES_FOLDER, fileName);
        Files.createDirectories(p.getParent());
        if (!Files.exists(p)) {
            generator.supply(p.toString());
            Assert.assertTrue(Files.exists(p), "Failed to create fixture: " + p);
            Assert.assertTrue(Files.size(p) > 0, "Fixture is empty: " + p);
        }
    }

    private void safeDelete(String filePath) {
        try { Files.deleteIfExists(Paths.get(filePath)); } catch (Exception ignored) {}
    }
}
