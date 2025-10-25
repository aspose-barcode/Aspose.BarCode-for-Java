package com.aspose.barcode.guide.recognition.special_parameters;

import com.aspose.barcode.barcoderecognition.BarCodeReader;
import com.aspose.barcode.barcoderecognition.BarCodeResult;
import com.aspose.barcode.barcoderecognition.DecodeType;
import com.aspose.barcode.generation.BarCodeImageFormat;
import com.aspose.barcode.generation.BarcodeGenerator;
import com.aspose.barcode.generation.EncodeTypes;
import com.aspose.barcode.guide.common.ExampleAssist;
import com.aspose.barcode.guide.common.LicenseAssist;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.nio.charset.Charset;

/**
 * Demonstrates how the {@code DetectEncoding} parameter affects barcode recognition.
 * <p>
 * Some barcodes (especially 2D types like QR or DataMatrix) can contain text encoded
 * in different character sets — UTF-8, Shift-JIS, ISO-8859-5, etc.
 * <p>
 * The {@code DetectEncoding} flag controls whether the recognition engine should
 * automatically detect and decode text encoding, or just return raw byte-to-char output.
 */
public class DetectEncodingExample {

    private static final String FOLDER =
            ExampleAssist.getOrCreateResourceFolderPath("recognition", "special_parameters", "detect_encoding");

    @BeforeClass
    public void setUp() {
        LicenseAssist.setupLicense();
    }

    /**
     * Demonstrates reading a UTF-8 encoded QR code with {@code DetectEncoding = true}.
     * <p>
     * In this mode (default), Aspose.BarCode automatically detects UTF-8 encoding
     * and correctly returns readable Unicode text.
     */
    @Test
    public void read_QR_UTF8_DetectEncoding_Enabled() throws Exception {
        String fileName = "qr_utf8_detectencoding_enabled.png";

        ExampleAssist.checkOrCreateImage(FOLDER, fileName, path -> {
            String utf8Text = "Привет, 世界!"; // Mixed Cyrillic and Chinese
            BarcodeGenerator generator = new BarcodeGenerator(EncodeTypes.QR, utf8Text);
            generator.save(path, BarCodeImageFormat.PNG);
        });

        BarCodeReader reader = new BarCodeReader(ExampleAssist.pathCombine(FOLDER, fileName), DecodeType.QR);
        reader.getBarcodeSettings().setDetectEncoding(true);

        BarCodeResult[] results = reader.readBarCodes();
        for (BarCodeResult r : results) {
            System.out.println("Detected text: " + r.getCodeText());
        }

        ExampleAssist.assertRecognized(reader, fileName, 1, DecodeType.QR);
    }

    /**
     * Demonstrates reading a UTF-8 encoded QR code with {@code DetectEncoding = false}.
     * <p>
     * When auto-detection is disabled, the engine does not attempt to interpret byte data,
     * and the resulting string may contain incorrect characters (mojibake).
     */
    @Test
    public void read_QR_UTF8_DetectEncoding_Disabled() throws Exception {
        String fileName = "qr_utf8_detectencoding_disabled.png";

        ExampleAssist.checkOrCreateImage(FOLDER, fileName, path -> {
            String utf8Text = "Привет, 世界!"; // Same text as above
            BarcodeGenerator generator = new BarcodeGenerator(EncodeTypes.QR, utf8Text);
            generator.save(path, BarCodeImageFormat.PNG);
        });

        BarCodeReader reader = new BarCodeReader(ExampleAssist.pathCombine(FOLDER, fileName), DecodeType.QR);
        reader.getBarcodeSettings().setDetectEncoding(false);

        BarCodeResult[] results = reader.readBarCodes();
        for (BarCodeResult r : results) {
            System.out.println("Raw text (DetectEncoding=false): " + r.getCodeText());
        }

        ExampleAssist.assertRecognized(reader, fileName, 1, DecodeType.QR);
    }

    /**
     * Demonstrates reading a barcode encoded in Windows-1251 (Cyrillic legacy codepage).
     * <p>
     * With {@code DetectEncoding = true}, text is automatically interpreted as Cyrillic.
     * With {@code DetectEncoding = false}, text appears garbled (incorrect symbols).
     */
    @Test
    public void read_QR_Windows1251_Comparison() throws Exception {
        String fileName = "qr_windows1251_compare.png";
        String text1251 = "Проверка кодировки";

        ExampleAssist.checkOrCreateImage(FOLDER, fileName, path -> {
            // Manually encode string into Windows-1251 bytes and re-decode as ISO-8859-1 for storage
            byte[] bytes = text1251.getBytes(Charset.forName("windows-1251"));
            String encoded = new String(bytes, Charset.forName("ISO-8859-1"));
            BarcodeGenerator generator = new BarcodeGenerator(EncodeTypes.QR, encoded);
            generator.save(path, BarCodeImageFormat.PNG);
        });

        // With DetectEncoding = true
        BarCodeReader reader1 = new BarCodeReader(ExampleAssist.pathCombine(FOLDER, fileName), DecodeType.QR);
        reader1.getBarcodeSettings().setDetectEncoding(true);
        var results1 = reader1.readBarCodes();

        // With DetectEncoding = false
        BarCodeReader reader2 = new BarCodeReader(ExampleAssist.pathCombine(FOLDER, fileName), DecodeType.QR);
        reader2.getBarcodeSettings().setDetectEncoding(false);
        var results2 = reader2.readBarCodes();

        System.out.println("DetectEncoding(true): " + results1[0].getCodeText());
        System.out.println("DetectEncoding(false): " + results2[0].getCodeText());

        ExampleAssist.assertRecognized(reader1, fileName, 1, DecodeType.QR);
        ExampleAssist.assertRecognized(reader2, fileName, 1, DecodeType.QR);
    }

    /**
     * Demonstrates that {@code DetectEncoding} works consistently for multiple barcode types.
     * <p>
     * Here, both QR and DataMatrix contain UTF-8 data, and both are correctly decoded
     * when {@code DetectEncoding = true}.
     */
    @Test
    public void read_QR_and_DataMatrix_UTF8_DetectEncoding_Enabled() throws Exception {
        String qrFile = "qr_utf8_multi.png";
        String dmFile = "datamatrix_utf8_multi.png";
        String text = "こんにちは世界"; // Japanese "Hello World"

        ExampleAssist.checkOrCreateImage(FOLDER, qrFile, path -> {
            BarcodeGenerator generator = new BarcodeGenerator(EncodeTypes.QR, text);
            generator.save(path, BarCodeImageFormat.PNG);
        });

        ExampleAssist.checkOrCreateImage(FOLDER, dmFile, path -> {
            BarcodeGenerator generator = new BarcodeGenerator(EncodeTypes.DATA_MATRIX, text);
            generator.save(path, BarCodeImageFormat.PNG);
        });

        // QR
        BarCodeReader qrReader = new BarCodeReader(ExampleAssist.pathCombine(FOLDER, qrFile), DecodeType.QR);
        qrReader.getBarcodeSettings().setDetectEncoding(true);
        BarCodeResult[] qrResults = qrReader.readBarCodes();

        // DataMatrix
        BarCodeReader dmReader = new BarCodeReader(ExampleAssist.pathCombine(FOLDER, dmFile), DecodeType.DATA_MATRIX);
        dmReader.getBarcodeSettings().setDetectEncoding(true);
        BarCodeResult[] dmResults = dmReader.readBarCodes();

        System.out.println("QR decoded text: " + qrResults[0].getCodeText());
        System.out.println("DataMatrix decoded text: " + dmResults[0].getCodeText());

        ExampleAssist.assertRecognized(qrReader, qrFile, 1, DecodeType.QR);
        ExampleAssist.assertRecognized(dmReader, dmFile, 1, DecodeType.DATA_MATRIX);
    }
}
