package com.aspose.barcode.guide.recognition.barcode_properties;

import com.aspose.barcode.barcoderecognition.*;
import com.aspose.barcode.generation.BarCodeImageFormat;
import com.aspose.barcode.generation.BarcodeGenerator;
import com.aspose.barcode.generation.EncodeTypes;
import com.aspose.barcode.generation.QRErrorLevel;
import com.aspose.barcode.guide.common.ExampleAssist;
import com.aspose.barcode.guide.common.LicenseAssist;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.awt.*;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * Demonstrates how to extract and use metadata from recognized barcodes.
 * Focus points:
 *  - DecodeType, CodeText, Confidence
 *  - Region geometry (Rectangle/Quadrangle)
 *  - OneD checksum (for 1D)
 *  - QR extended parameters (version, error correction, structured append)
 *  - Macro PDF417 extended parameters (file id, segments, etc.)
 *  - Binary payload via getCodeBytes()
 */
public class ReadingMetadataExample {

    private static final String FOLDER =
            ExampleAssist.getOrCreateResourceFolderPath("recognition", "barcode_properties", "metadata");

    private static final String FILE_GS1_128      = "gs1_code128.png";
    private static final String FILE_QR_HIGH_EC   = "qr_high_ec.png";
    private static final String FILE_PDF417_MACRO = "pdf417_macro.png";
    private static final String FILE_QR_BINARY    = "qr_binary_data.png";

    @BeforeClass
    public void setUp() throws Exception {
        LicenseAssist.setupLicense();
        generateFixtures();
    }

    // --- Basic metadata on GS1-128 (type, text, confidence, region, 1D checksum) ---
    @Test
    public void readBasicMetadata() throws Exception {
        String path = ExampleAssist.pathCombine(FOLDER, FILE_GS1_128);
        BarCodeReader barCodeReader = new BarCodeReader(path, DecodeType.GS_1_CODE_128);
        BarCodeResult[] results = barCodeReader.readBarCodes();
        Assert.assertEquals(results.length, 1, "Expected exactly 1 barcode");

        BarCodeResult barCodeResult = results[0];
        Assert.assertEquals(barCodeResult.getCodeType(), DecodeType.GS_1_CODE_128);

        System.out.println("Type=" + barCodeResult.getCodeTypeName()
                + " Text=" + barCodeResult.getCodeText());
        int confidence = barCodeResult.getConfidence();
        System.out.println("Confidence=" + confidence);
        // Soft check: confidence must be within 0..100
        Assert.assertTrue(confidence >= 0 && confidence <= 100, "Confidence out of range");

        BarCodeRegionParameters barCodeResultRegion = barCodeResult.getRegion();
        System.out.println("Rect=" + barCodeResultRegion.getRectangle());
        Quadrangle quadrangle = barCodeResultRegion.getQuadrangle();
        if (quadrangle != null) {
            System.out.println("Quad LT=" + quadrangle.getLeftTop()
                    + " RT=" + quadrangle.getRightTop()
                    + " RB=" + quadrangle.getRightBottom()
                    + " LB=" + quadrangle.getLeftBottom());
        }
        Point[] points = barCodeResultRegion.getPoints();
//        Arrays.stream(points).forEach(System.out::println);
//        Arrays.stream(points).forEach(point -> {
//            System.out.println("Point: x=" + point.getX() + " y=" + point.getY());
//        });
        IntStream.range(0, points.length).forEach(i -> {
            Point point = points[i];
            System.out.println("Point " + i + ": x=" + point.getX() + " y=" + point.getY());
        });

        BarCodeExtendedParameters barCodeExtendedParameters = barCodeResult.getExtended();
        if (barCodeExtendedParameters != null && barCodeExtendedParameters.getOneD() != null) {
            System.out.println("OneD checksum=" + barCodeExtendedParameters.getOneD().getCheckSum());
        }
    }

    // --- QR extended parameters (error level, versions, structured append) ---
    @Test
    public void readQRMetadata() throws Exception {
        String path = ExampleAssist.pathCombine(FOLDER, FILE_QR_HIGH_EC);
        BarCodeReader barCodeReader = new BarCodeReader(path, DecodeType.QR);
        barCodeReader.setQualitySettings(QualitySettings.getHighQuality());

        // Use utility: expect at least one result
        ExampleAssist.assertHasAnyResult(barCodeReader, "readQRMetadata");
        BarCodeResult[] results = barCodeReader.readBarCodes();
        BarCodeResult barCodeResult = results[0];

        // Soft check for confidence range
        int confidence = barCodeResult.getConfidence();
        Assert.assertTrue(confidence >= 0 && confidence <= 100, "Confidence out of range");

        ExampleAssist.printResultMetadata(barCodeResult, "QR");

        QRExtendedParameters qrExtendedParameters = barCodeResult.getExtended().getQR();
        Assert.assertNotNull(qrExtendedParameters, "QR extended parameters must be present");
        Assert.assertEquals(qrExtendedParameters.getQRErrorLevel(), QRErrorLevel.LEVEL_H);
        // Sanity getters
        Assert.assertNotNull(qrExtendedParameters.getQRErrorLevel());
        Assert.assertNotNull(qrExtendedParameters.getQRVersion());
    }

    // --- Macro PDF417 extended parameters (file id, segments, etc.) ---
    @Test
    public void readPdf417MacroMetadata() throws Exception {
        String path = ExampleAssist.pathCombine(FOLDER, FILE_PDF417_MACRO);
        BarCodeReader barCodeReader = new BarCodeReader(path, DecodeType.MACRO_PDF_417);

        // Use utility: expect at least one result
        ExampleAssist.assertHasAnyResult(barCodeReader, "readPdf417MacroMetadata");
        BarCodeResult[] results = barCodeReader.readBarCodes();
        BarCodeResult barCodeResult = results[0];

        // Soft check for confidence range
        int confidence = barCodeResult.getConfidence();
        Assert.assertTrue(confidence >= 0 && confidence <= 100, "Confidence out of range");

        ExampleAssist.printResultMetadata(barCodeResult, "PDF417");

        Pdf417ExtendedParameters pdf417ExtendedParameters = barCodeResult.getExtended().getPdf417();
        Assert.assertNotNull(pdf417ExtendedParameters, "Pdf417 extended parameters must be present");

        // Soft checks: values depend on generator settings in fixtures
        Assert.assertTrue(pdf417ExtendedParameters.getMacroPdf417SegmentsCount() >= -1);
        Assert.assertTrue(pdf417ExtendedParameters.getMacroPdf417SegmentID() >= -1);
        Assert.assertNotNull(pdf417ExtendedParameters.getMacroPdf417FileID());
    }

    // --- Binary data via getCodeBytes() with round-trip check ---
    @Test
    public void readBinaryData() throws Exception {
        String path = ExampleAssist.pathCombine(FOLDER, FILE_QR_BINARY);
        BarCodeReader barCodeReader = new BarCodeReader(path, DecodeType.QR);

        // Use utility: expect at least one result
        ExampleAssist.assertHasAnyResult(barCodeReader, "readBinaryData");
        BarCodeResult[] results = barCodeReader.readBarCodes();
        BarCodeResult barCodeResult = results[0];

        // Soft check for confidence range
        int confidence = barCodeResult.getConfidence();
        Assert.assertTrue(confidence >= 0 && confidence <= 100, "Confidence out of range");

        byte[] rawBytes = barCodeResult.getCodeBytes();
        Assert.assertNotNull(rawBytes);
        System.out.println("Binary data length: " + rawBytes.length + " bytes");

        // Round-trip: encoded bytes must match source bytes used in fixtures
        byte[] expected = "Hello, Metadata!".getBytes(StandardCharsets.UTF_8);
        Assert.assertEquals(rawBytes, expected, "QR binary payload mismatch");
    }

    // ---------------- fixtures ----------------
    private void generateFixtures() throws Exception {
        // 1) GS1-128 simple content
        ExampleAssist.checkOrCreateImage(FOLDER, FILE_GS1_128, (String full) -> {
            BarcodeGenerator generator = new BarcodeGenerator(EncodeTypes.GS_1_CODE_128,
                    "(01)03453120000011(10)ABC123(17)251231");
            generator.save(full, BarCodeImageFormat.PNG);
        });

        // 2) QR with high error correction (Level H) – affects recognition metadata (error level)
        ExampleAssist.checkOrCreateImage(FOLDER, FILE_QR_HIGH_EC, (String full) -> {
            BarcodeGenerator generator = new BarcodeGenerator(EncodeTypes.QR, "Aspose.Barcode Metadata Test");
            generator.getParameters().getBarcode().getQR().setQrErrorLevel(QRErrorLevel.LEVEL_H);
            generator.save(full, BarCodeImageFormat.PNG);
        });

        // 3) Macro PDF417 – set macro fields so they appear in Pdf417ExtendedParameters
        ExampleAssist.checkOrCreateImage(FOLDER, FILE_PDF417_MACRO, (String full) -> {
            BarcodeGenerator generator = new BarcodeGenerator(EncodeTypes.MACRO_PDF_417, "Structured PDF417 Data");
            generator.getParameters().getBarcode().getPdf417().setPdf417MacroFileID(15900);
            generator.getParameters().getBarcode().getPdf417().setPdf417MacroSegmentsCount(3);
            generator.getParameters().getBarcode().getPdf417().setPdf417MacroSegmentID(1);
            generator.save(full, BarCodeImageFormat.PNG);
        });

        // 4) QR with binary payload
        ExampleAssist.checkOrCreateImage(FOLDER, FILE_QR_BINARY, (String full) -> {
            byte[] binaryData = "Hello, Metadata!".getBytes(StandardCharsets.UTF_8);
            BarcodeGenerator generator = new BarcodeGenerator(EncodeTypes.QR);
            generator.setCodeText(binaryData);
            generator.save(full, BarCodeImageFormat.PNG);
        });
    }
}
