package com.aspose.barcode.guide.recognition.barcode_properties;

import com.aspose.barcode.barcoderecognition.*;
import com.aspose.barcode.generation.BarCodeImageFormat;
import com.aspose.barcode.generation.BarcodeGenerator;
import com.aspose.barcode.generation.EncodeTypes;
import com.aspose.barcode.guide.common.ExampleAssist;
import com.aspose.barcode.guide.common.ImageSupplier;
import com.aspose.barcode.guide.common.LicenseAssist;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.awt.Rectangle;

import static com.aspose.barcode.guide.common.ExampleAssist.*;

public class ReadingMetadataExample {

    private static final String FOLDER =
            ExampleAssist.getOrCreateResourceFolderPath("recognition", "barcode_properties", "reading_metadata");

    private static final String FILE_C128  = "meta_code128.png";
    private static final String FILE_QR    = "meta_qr.png";
    private static final String FILE_DM    = "meta_dm.png";
    private static final String FILE_MULTI = "meta_multi.png";

    @BeforeClass
    public void setUp() throws Exception {
        LicenseAssist.setupLicense();
        generateFixtures();
    }

    @Test
    public void readCode128Metadata() throws Exception {
        String path = ExampleAssist.pathCombine(FOLDER, FILE_C128);
        BarCodeReader barCodeReader = new BarCodeReader(path, DecodeType.CODE_128);
        BarCodeResult[] results = barCodeReader.readBarCodes();
        Assert.assertTrue(results.length >= 1, "Expected at least 1 result");

        BarCodeResult barCodeResult = results[0];
        System.out.println("[C128] Type=" + barCodeResult.getCodeTypeName() + " Text=" + barCodeResult.getCodeText());
        System.out.println("[C128] Confidence=" + barCodeResult.getConfidence());

        BarCodeRegionParameters region = barCodeResult.getRegion();
        Rectangle rect = region.getRectangle();
        Quadrangle quad = region.getQuadrangle();
        System.out.println("[C128] Rect=" + rect);
        System.out.println("[C128] Quad LT=" + quad.getLeftTop()
                + " RT=" + quad.getRightTop()
                + " RB=" + quad.getRightBottom()
                + " LB=" + quad.getLeftBottom());

        BarCodeExtendedParameters barCodeExtendedParameters = barCodeResult.getExtended();
        if (barCodeExtendedParameters != null && barCodeExtendedParameters.getOneD() != null) {
            System.out.println("[C128] OneD checksum=" + barCodeExtendedParameters.getOneD().getCheckSum());
        }

        ExampleAssist.assertRecognized(barCodeReader, "readCode128Metadata", 1, DecodeType.CODE_128);
    }

    @Test
    public void readQRMetadata() throws Exception {
        String path = ExampleAssist.pathCombine(FOLDER, FILE_QR);
        BarCodeReader barCodeReader = new BarCodeReader(path, DecodeType.QR);
        barCodeReader.setQualitySettings(QualitySettings.getHighQuality());
        BarCodeResult[] results = barCodeReader.readBarCodes();
        Assert.assertTrue(results.length >= 1, "Expected at least 1 result");

        BarCodeResult barCodeResult = results[0];
        ExampleAssist.printResultMetadata(barCodeResult, "QR");

        QRExtendedParameters qrExtendedParameters = barCodeResult.getExtended().getQR();
        Assert.assertNotNull(qrExtendedParameters, "QR extended parameters must be present");
        Assert.assertNotNull(qrExtendedParameters.getQRErrorLevel());
    }

    @Test
    public void readDataMatrixMetadata() throws Exception {
        String path = ExampleAssist.pathCombine(FOLDER, FILE_DM);
        BarCodeReader barCodeReader = new BarCodeReader(path, DecodeType.DATA_MATRIX);
        BarCodeResult[] results = barCodeReader.readBarCodes();
        Assert.assertTrue(results.length >= 1, "Expected at least 1 result");

        BarCodeResult barCodeResult = results[0];
        ExampleAssist.printResultMetadata(barCodeResult, "DM");

        DataMatrixExtendedParameters dataMatrixExtendedParameters = barCodeResult.getExtended().getDataMatrix();
        Assert.assertNotNull(dataMatrixExtendedParameters, "DataMatrix extended parameters must be present");
        Assert.assertTrue(dataMatrixExtendedParameters.getStructuredAppendBarcodesCount() >= -1);
        Assert.assertTrue(dataMatrixExtendedParameters.getStructuredAppendBarcodeId() >= -1);
        Assert.assertTrue(dataMatrixExtendedParameters.getStructuredAppendFileId() >= -1);
    }

    @Test
    public void readPdf417Metadata() throws Exception {
        String fileName = "meta_pdf417.png";
        ExampleAssist.checkOrCreateImage(FOLDER, fileName, (String full) -> {
            BarcodeGenerator generator = new BarcodeGenerator(EncodeTypes.MACRO_PDF_417, "META-PDF417");
            generator.getParameters().getBarcode().getPdf417().setPdf417MacroFileID(15900);
            generator.getParameters().getBarcode().getPdf417().setPdf417MacroSegmentsCount(3);
            generator.getParameters().getBarcode().getPdf417().setPdf417MacroSegmentID(1);
            generator.save(full, BarCodeImageFormat.PNG);
        });

        String path = ExampleAssist.pathCombine(FOLDER, fileName);
        BarCodeReader barCodeReader = new BarCodeReader(path, DecodeType.MACRO_PDF_417);
        BarCodeResult[] results = barCodeReader.readBarCodes();
        Assert.assertTrue(results.length >= 1, "Expected at least 1 result");

        BarCodeResult barCodeResult = results[0];
        ExampleAssist.printResultMetadata(barCodeResult, "PDF417");

        Pdf417ExtendedParameters pdf417ExtendedParameters = barCodeResult.getExtended().getPdf417();
        Assert.assertNotNull(pdf417ExtendedParameters, "Pdf417 extended parameters must be present");
        Assert.assertTrue(pdf417ExtendedParameters.getMacroPdf417SegmentsCount() >= -1);
        Assert.assertTrue(pdf417ExtendedParameters.getMacroPdf417SegmentID() >= -1);
        Assert.assertNotNull(pdf417ExtendedParameters.getMacroPdf417FileID());
    }

    @Test
    public void readMultipleSymbologiesMetadata() throws Exception {
        String path = ExampleAssist.pathCombine(FOLDER, FILE_MULTI);
        BarCodeReader barCodeReader = new BarCodeReader(path, DecodeType.CODE_128, DecodeType.QR, DecodeType.DATA_MATRIX);
        BarCodeResult[] results = barCodeReader.readBarCodes();

        System.out.println("[MULTI] Count=" + results.length);
        for (BarCodeResult barCodeResult : results) {
            System.out.println("----");
            System.out.println("Type=" + barCodeResult.getCodeTypeName() + " Text=" + barCodeResult.getCodeText());
            System.out.println("Confidence=" + barCodeResult.getConfidence());
            BarCodeRegionParameters region = barCodeResult.getRegion();
            System.out.println("Rect=" + region.getRectangle());

            BarCodeExtendedParameters barCodeExtendedParameters = barCodeResult.getExtended();
            if (barCodeExtendedParameters != null) {
                if (barCodeExtendedParameters.getOneD() != null) {
                    System.out.println("OneD checksum=" + barCodeExtendedParameters.getOneD().getCheckSum());
                }
                if (barCodeExtendedParameters.getQR() != null) {
                    QRExtendedParameters qrExtendedParameters = barCodeExtendedParameters.getQR();
                    System.out.println("QR ErrorLevel=" + qrExtendedParameters.getQRErrorLevel()
                            + " QRVersion=" + qrExtendedParameters.getQRVersion()
                            + " MicroQRVersion=" + qrExtendedParameters.getMicroQRVersion()
                            + " RectMicroQRVersion=" + qrExtendedParameters.getRectMicroQRVersion()
                            + " SA.Quantity=" + qrExtendedParameters.getQRStructuredAppendModeBarCodesQuantity()
                            + " SA.Index=" + qrExtendedParameters.getQRStructuredAppendModeBarCodeIndex()
                            + " SA.Parity=" + qrExtendedParameters.getQRStructuredAppendModeParityData());
                }
                if (barCodeExtendedParameters.getDataMatrix() != null) {
                    DataMatrixExtendedParameters dataMatrixExtendedParameters = barCodeExtendedParameters.getDataMatrix();
                    System.out.println("DM SA.BarcodeId=" + dataMatrixExtendedParameters.getStructuredAppendBarcodeId()
                            + " SA.Count=" + dataMatrixExtendedParameters.getStructuredAppendBarcodesCount()
                            + " SA.FileId=" + dataMatrixExtendedParameters.getStructuredAppendFileId()
                            + " ReaderProgramming=" + dataMatrixExtendedParameters.isReaderProgramming());
                }
            }
        }

        Assert.assertTrue(results.length >= 3, "Expected at least 3 results");
        Assert.assertTrue(ExampleAssist.hasDecodeType(results, DecodeType.CODE_128));
        Assert.assertTrue(ExampleAssist.hasDecodeType(results, DecodeType.QR));
        Assert.assertTrue(ExampleAssist.hasDecodeType(results, DecodeType.DATA_MATRIX));
    }

    // ---------------- fixtures ----------------
    private void generateFixtures() throws Exception {
        // Code128
        ExampleAssist.checkOrCreateImage(FOLDER, FILE_C128, (ImageSupplier) (String full) -> {
            BarcodeGenerator generator = new BarcodeGenerator(EncodeTypes.CODE_128, "META-C128");
            generator.save(full, BarCodeImageFormat.PNG);
        });

        // QR
        ExampleAssist.checkOrCreateImage(FOLDER, FILE_QR, (ImageSupplier) (String full) -> {
            BarcodeGenerator generator = new BarcodeGenerator(EncodeTypes.QR, "META-QR:EC=H;VER=?");
            generator.save(full, BarCodeImageFormat.PNG);
        });

        // DataMatrix
        ExampleAssist.checkOrCreateImage(FOLDER, FILE_DM, (ImageSupplier) (String full) -> {
            BarcodeGenerator generator = new BarcodeGenerator(EncodeTypes.DATA_MATRIX, "META-DM");
            generator.save(full, BarCodeImageFormat.PNG);
        });

        // Multiple (Code128 + QR + DataMatrix)
        ExampleAssist.checkOrCreateImage(FOLDER, FILE_MULTI, (ImageSupplier) (String full) -> {
            String pathC128 = ExampleAssist.generateTestBarcode("BATCH-128", FOLDER, "tmp_c128.png", EncodeTypes.CODE_128);
            String pathQR   = ExampleAssist.generateTestBarcode("BATCH-QR",  FOLDER, "tmp_qr.png",   EncodeTypes.QR);
            String pathDM   = ExampleAssist.generateTestBarcode("BATCH-DM",  FOLDER, "tmp_dm.png",   EncodeTypes.DATA_MATRIX);

            java.awt.image.BufferedImage img1 = javax.imageio.ImageIO.read(new java.io.File(pathC128));
            java.awt.image.BufferedImage img2 = javax.imageio.ImageIO.read(new java.io.File(pathQR));
            java.awt.image.BufferedImage img3 = javax.imageio.ImageIO.read(new java.io.File(pathDM));

            int w = img1.getWidth() + img2.getWidth() + img3.getWidth();
            int h = Math.max(img1.getHeight(), Math.max(img2.getHeight(), img3.getHeight()));
            java.awt.image.BufferedImage out = new java.awt.image.BufferedImage(w, h, java.awt.image.BufferedImage.TYPE_INT_RGB);
            java.awt.Graphics2D g = out.createGraphics();
            try {
                g.setColor(java.awt.Color.WHITE);
                g.fillRect(0, 0, w, h);
                int x = 0;
                g.drawImage(img1, x, 0, null); x += img1.getWidth();
                g.drawImage(img2, x, 0, null); x += img2.getWidth();
                g.drawImage(img3, x, 0, null);
            } finally {
                g.dispose();
            }
            javax.imageio.ImageIO.write(out, "PNG", new java.io.File(full));

            new java.io.File(pathC128).delete();
            new java.io.File(pathQR).delete();
            new java.io.File(pathDM).delete();
        });
    }
}
