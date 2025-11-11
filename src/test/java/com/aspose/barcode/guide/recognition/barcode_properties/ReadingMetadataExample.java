package com.aspose.barcode.guide.recognition.barcode_properties;

import com.aspose.barcode.barcoderecognition.*;
import com.aspose.barcode.generation.BarCodeImageFormat;
import com.aspose.barcode.generation.BarcodeGenerator;
import com.aspose.barcode.generation.BaseEncodeType;
import com.aspose.barcode.generation.EncodeTypes;
import com.aspose.barcode.guide.common.ExampleAssist;
import com.aspose.barcode.guide.common.ImageSupplier;
import com.aspose.barcode.guide.common.LicenseAssist;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.awt.Rectangle;

import static com.aspose.barcode.guide.common.ExampleAssist.*;

/**
 * Demonstrates how to read and interpret recognition metadata:
 *  - Code type / type name and code text
 *  - Confidence (per result)
 *  - Region geometry (Rectangle vs Quadrangle, raw points)
 *  - Symbology-specific extended parameters (1D checksum, QR/DataMatrix info)
 */
public class ReadingMetadataExample {

    private static final String FOLDER =
            ExampleAssist.getOrCreateResourceFolderPath("recognition", "barcode_properties", "reading_metadata");

    private static final String FILE_C128      = "meta_code128.png";
    private static final String FILE_QR        = "meta_qr.png";
    private static final String FILE_DM        = "meta_dm.png";
    private static final String FILE_MULTI     = "meta_multi.png";

    @BeforeClass
    public void setUp() throws Exception {
        LicenseAssist.setupLicense();
        generateFixtures();
    }

    /**
     * Reads Code128 and prints generic/extended metadata:
     * - code type/name, code text
     * - confidence
     * - region rectangle and quadrangle
     * - 1D checksum (if available)
     */
    @Test
    public void readCode128Metadata() throws Exception {
        String path = ExampleAssist.pathCombine(FOLDER, FILE_C128);
        BarCodeReader reader = new BarCodeReader(path, DecodeType.CODE_128);
        BarCodeResult[] results = reader.readBarCodes();
        Assert.assertTrue(results.length >= 1, "Expected at least 1 result");

        BarCodeResult r = results[0];
        System.out.println("[C128] Type=" + r.getCodeTypeName() + " Text=" + r.getCodeText());
        System.out.println("[C128] Confidence=" + r.getConfidence());

        BarCodeRegionParameters region = r.getRegion();
        Rectangle rect = region.getRectangle();
        Quadrangle quad = region.getQuadrangle();
        System.out.println("[C128] Rect=" + rect);
        System.out.println("[C128] Quad LT=" + quad.getLeftTop()
                + " RT=" + quad.getRightTop()
                + " RB=" + quad.getRightBottom()
                + " LB=" + quad.getLeftBottom());

        BarCodeExtendedParameters ext = r.getExtended();
        if (ext != null && ext.getOneD() != null) {
            System.out.println("[C128] OneD checksum=" + ext.getOneD().getCheckSum());
        }

        ExampleAssist.assertRecognized(reader, "readCode128Metadata", 1, DecodeType.CODE_128);
    }

    /**
     * Reads QR and prints QR-specific extended parameters:
     * - Error level, version, mask, MicroQR flag (when provided)
     */
    @Test
    public void readQRMetadata() throws Exception {
        String path = ExampleAssist.pathCombine(FOLDER, FILE_QR);
        BarCodeReader barCodeReader = new BarCodeReader(path, DecodeType.QR);
        barCodeReader.setQualitySettings(QualitySettings.getHighQuality());
        BarCodeResult[] results = barCodeReader.readBarCodes();
        Assert.assertTrue(results.length >= 1, "Expected at least 1 result");

        BarCodeResult result = results[0];
        ExampleAssist.printResultMetadata(result, "QR");

        QRExtendedParameters qrExtendedParameters = result.getExtended().getQR();
        Assert.assertNotNull(qrExtendedParameters, "QR extended parameters must be present");
        Assert.assertNotNull(qrExtendedParameters.getQRErrorLevel());
    }

    /**
     * Reads DataMatrix and prints DataMatrix-specific extended parameters.
     */
    @Test
    public void readDataMatrixMetadata() throws Exception {
        String path = ExampleAssist.pathCombine(FOLDER, FILE_DM);
        BarCodeReader barCodeReader = new BarCodeReader(path, DecodeType.DATA_MATRIX);
        BarCodeResult[] results = barCodeReader.readBarCodes();
        Assert.assertTrue(results.length >= 1, "Expected at least 1 result");

        BarCodeResult result = results[0];
        ExampleAssist.printResultMetadata(result, "DM");

        DataMatrixExtendedParameters dataMatrixExtendedParameters = result.getExtended().getDataMatrix();
        Assert.assertNotNull(dataMatrixExtendedParameters, "DataMatrix extended parameters must be present");

        // Примеры читабельных проверок (без жёстких констант, т.к. значения зависят от конкретного кодирования):
        Assert.assertTrue(dataMatrixExtendedParameters.getStructuredAppendBarcodesCount() >= -1);
        Assert.assertTrue(dataMatrixExtendedParameters.getStructuredAppendBarcodeId() >= -1);
        Assert.assertTrue(dataMatrixExtendedParameters.getStructuredAppendFileId() >= -1);
    }

    @Test
    public void readPdf417Metadata() throws Exception {
        String fileName = "meta_pdf417.png";
        ExampleAssist.checkOrCreateImage(FOLDER, fileName, (String full) -> {
            BarcodeGenerator generator = new BarcodeGenerator(EncodeTypes.MACRO_PDF_417, "META-PDF417");
            // Настраиваем MacroPdf417 параметры на генераторе, если нужно получить заполненные extended-поля:
            generator.getParameters().getBarcode().getPdf417().setPdf417MacroFileID("FILE123");
            generator.getParameters().getBarcode().getPdf417().setPdf417MacroSegmentsCount(3);
            generator.getParameters().getBarcode().getPdf417().setPdf417MacroSegmentID(1);
            generator.save(full, BarCodeImageFormat.PNG);
        });

        String path = ExampleAssist.pathCombine(FOLDER, fileName);
        BarCodeReader barCodeReader = new BarCodeReader(path, DecodeType.MACRO_PDF_417);
        BarCodeResult[] results = barCodeReader.readBarCodes();
        Assert.assertTrue(results.length >= 1, "Expected at least 1 result");

        BarCodeResult result = results[0];
        ExampleAssist.printResultMetadata(result, "PDF417");

        Pdf417ExtendedParameters pdf417ExtendedParameters = result.getExtended().getPdf417();
        Assert.assertNotNull(pdf417ExtendedParameters, "Pdf417 extended parameters must be present");
        // Пример мягких проверок:
        Assert.assertTrue(pdf417ExtendedParameters.getMacroPdf417SegmentsCount() >= -1);
        Assert.assertTrue(pdf417ExtendedParameters.getMacroPdf417SegmentID() >= -1);
        Assert.assertNotNull(pdf417ExtendedParameters.getMacroPdf417FileID());
    }

    /**
     * Generates an image with multiple symbologies and shows per-result metadata differences.
     */
    @Test
    public void readMultipleSymbologiesMetadata() throws Exception {
        String path = ExampleAssist.pathCombine(FOLDER, FILE_MULTI);
        BarCodeReader reader = new BarCodeReader(path, DecodeType.CODE_128, DecodeType.QR, DecodeType.DATA_MATRIX);
        BarCodeResult[] results = reader.readBarCodes();

        System.out.println("[MULTI] Count=" + results.length);
        for (BarCodeResult r : results) {
            System.out.println("----");
            System.out.println("Type=" + r.getCodeTypeName() + " Text=" + r.getCodeText());
            System.out.println("Confidence=" + r.getConfidence());
            BarCodeRegionParameters region = r.getRegion();
            System.out.println("Rect=" + region.getRectangle());

            BarCodeExtendedParameters barCodeExtendedParameters = r.getExtended();
            if (barCodeExtendedParameters != null) {
                if (barCodeExtendedParameters.getOneD() != null) {
                    System.out.println("OneD checksum=" + barCodeExtendedParameters.getOneD().getCheckSum());
                }
                if (barCodeExtendedParameters.getQR() != null) {
                    QRExtendedParameters qrExtendedParameters = barCodeExtendedParameters.getQR();
                    System.out.println("QR EC=" + qrExtendedParameters.getErrorLevel()
                            + " Version=" + qrExtendedParameters.getVersion()
                            + " Mask=" + qrExtendedParameters.getMask());
                }
                if (barCodeExtendedParameters.getDataMatrix() != null) {
                    DataMatrixExtendedParameters dataMatrixExtendedParameters = barCodeExtendedParameters.getDataMatrix();
                    System.out.println("DM Version=" + dataMatrixExtendedParameters.getDataMatrixVersion()
                            + " SA=" + dataMatrixExtendedParameters.isStructuredAppend());
                }
            }
        }

        // Expect exactly 3 different symbologies present
        Assert.assertTrue(results.length >= 3, "Expected at least 3 results");
        Assert.assertTrue(ExampleAssist.hasDecodeType(results, DecodeType.CODE_128));
        Assert.assertTrue(ExampleAssist.hasDecodeType(results, DecodeType.QR));
        Assert.assertTrue(ExampleAssist.hasDecodeType(results, DecodeType.DATA_MATRIX));
    }

    // ---------------- fixtures ----------------

    private void generateFixtures() throws Exception {
        // Code128
        ExampleAssist.checkOrCreateImage(FOLDER, FILE_C128, (ImageSupplier) (String full) -> {
            BarcodeGenerator g = new BarcodeGenerator(EncodeTypes.CODE_128, "META-C128");
            g.save(full, BarCodeImageFormat.PNG);
        });

        // QR
        ExampleAssist.checkOrCreateImage(FOLDER, FILE_QR, (ImageSupplier) (String full) -> {
            BarcodeGenerator g = new BarcodeGenerator(EncodeTypes.QR, "META-QR:EC=H;VER=?");
            g.save(full, BarCodeImageFormat.PNG);
        });

        // DataMatrix
        ExampleAssist.checkOrCreateImage(FOLDER, FILE_DM, (ImageSupplier) (String full) -> {
            BarcodeGenerator g = new BarcodeGenerator(EncodeTypes.DATA_MATRIX, "META-DM");
            g.save(full, BarCodeImageFormat.PNG);
        });

        // Multiple (Code128 + QR + DataMatrix): render three images and tile them side-by-side.
        ExampleAssist.checkOrCreateImage(FOLDER, FILE_MULTI, (ImageSupplier) (String full) -> {
            String c128 = ExampleAssist.generateTestBarcode("BATCH-128", FOLDER, "tmp_c128.png", EncodeTypes.CODE_128);
            String qr   = ExampleAssist.generateTestBarcode("BATCH-QR",  FOLDER, "tmp_qr.png",   EncodeTypes.QR);
            String dm   = ExampleAssist.generateTestBarcode("BATCH-DM",  FOLDER, "tmp_dm.png",   EncodeTypes.DATA_MATRIX);

            java.awt.image.BufferedImage i1 = javax.imageio.ImageIO.read(new java.io.File(c128));
            java.awt.image.BufferedImage i2 = javax.imageio.ImageIO.read(new java.io.File(qr));
            java.awt.image.BufferedImage i3 = javax.imageio.ImageIO.read(new java.io.File(dm));

            int w = i1.getWidth() + i2.getWidth() + i3.getWidth();
            int h = Math.max(i1.getHeight(), Math.max(i2.getHeight(), i3.getHeight()));
            java.awt.image.BufferedImage out = new java.awt.image.BufferedImage(w, h, java.awt.image.BufferedImage.TYPE_INT_RGB);
            java.awt.Graphics2D g = out.createGraphics();
            try {
                g.setColor(java.awt.Color.WHITE);
                g.fillRect(0, 0, w, h);
                int x = 0;
                g.drawImage(i1, x, 0, null); x += i1.getWidth();
                g.drawImage(i2, x, 0, null); x += i2.getWidth();
                g.drawImage(i3, x, 0, null);
            } finally {
                g.dispose();
            }
            javax.imageio.ImageIO.write(out, "PNG", new java.io.File(full));

            new java.io.File(c128).delete();
            new java.io.File(qr).delete();
            new java.io.File(dm).delete();
        });
    }

}
