package com.aspose.barcode.guide.recognition.barcode_properties;

import com.aspose.barcode.barcoderecognition.BarCodeReader;
import com.aspose.barcode.barcoderecognition.BarCodeResult;
import com.aspose.barcode.barcoderecognition.DecodeType;
import com.aspose.barcode.barcoderecognition.QualitySettings;
import com.aspose.barcode.generation.BarCodeImageFormat;
import com.aspose.barcode.generation.BarcodeGenerator;
import com.aspose.barcode.generation.EncodeTypes;
import com.aspose.barcode.guide.common.ExampleAssist;
import com.aspose.barcode.guide.common.LicenseAssist;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.nio.charset.StandardCharsets;

/**
 * Reading CodeText examples:
 *
 * Focus:
 *  1) Default CodeText (string) vs raw bytes.
 *  2) Encodings / DetectEncoding: reading Unicode payloads (e.g., UTF-8).
 *  3) GS1: human-readable CodeText (AI with parentheses) vs raw stream with FNC1 (0x1D).
 *
 * Notes:
 *  - We intentionally print both string CodeText and raw bytes (as hex) to show the difference.
 *  - If your Aspose.BarCode version exposes raw via another accessor, replace getCodeBytes() accordingly.
 */
public class ReadingCodeTextExample {

    private static final String FOLDER =
            ExampleAssist.getOrCreateResourceFolderPath("recognition", "barcode_properties", "reading_codetext");

    @BeforeClass
    public void setUp() throws Exception {
        LicenseAssist.setupLicense();
        generateFixtures();
    }

    // ---------------------------------------------------------------------
    // Test data generation
    // ---------------------------------------------------------------------

    private void generateFixtures() throws Exception {
        // 1) Plain ASCII for Code 128 (simple baseline)
        ExampleAssist.checkOrCreateImage(FOLDER, "code128_ascii.png", path -> {
            BarcodeGenerator g = new BarcodeGenerator(EncodeTypes.CODE_128, "ASCII-1234-OK");
            g.save(path, BarCodeImageFormat.PNG);
        });

        // 2) QR with UTF-8 payload (Cyrillic + ASCII) to demonstrate DetectEncoding on read
        ExampleAssist.checkOrCreateImage(FOLDER, "qr_utf8.png", path -> {
            String payload = "Привет QR / Hello";
            BarcodeGenerator g = new BarcodeGenerator(EncodeTypes.QR, payload);
            // Many engines will auto-emit ECI/UTF-8 for non-ASCII text. If your version
            // requires explicit encoding, set it via parameters (CodeTextEncoding) here.
            g.save(path, BarCodeImageFormat.PNG);
        });

        // 3) GS1 DataMatrix (human-readable with AIs, internally encoded with FNC1 separators)
        ExampleAssist.checkOrCreateImage(FOLDER, "gs1_dm.png", path -> {
            // Typical GS1 example: (01)GTIN14 + (10)Batch + (17)Expiry
            String gs1 = "(01)12345678901231(10)BATCH42(17)251231";
            BarcodeGenerator g = new BarcodeGenerator(EncodeTypes.GS_1_DATA_MATRIX, gs1);
            g.save(path, BarCodeImageFormat.PNG);
        });

        // 4) GS1 Code128 (same AIs, different symbology)
        ExampleAssist.checkOrCreateImage(FOLDER, "gs1_code128.png", path -> {
            String gs1 = "(01)12345678901231(10)BATCH42(17)251231";
            BarcodeGenerator g = new BarcodeGenerator(EncodeTypes.GS_1_CODE_128, gs1);
            g.save(path, BarCodeImageFormat.PNG);
        });
    }

    // ---------------------------------------------------------------------
    // 1) Default CodeText: Code 128, ASCII payload
    // ---------------------------------------------------------------------

    /**
     * Purpose:
     *  - Demonstrates reading a plain ASCII Code 128 and shows that CodeText (string)
     *    matches the original payload.
     * Shows:
     *  - result.getCodeText() for human-readable text
     *  - result.getCodeBytes() for raw byte stream (should be ASCII here)
     */
    @Test
    public void read_Code128_ASCII_DefaultCodeText() throws Exception {
        String file = "code128_ascii.png";
        BarCodeReader reader = new BarCodeReader(ExampleAssist.pathCombine(FOLDER, file), DecodeType.CODE_128);

        BarCodeResult[] results = reader.readBarCodes();
        ExampleAssist.assertRecognized(reader, file, 1, DecodeType.CODE_128);

        // Print human-readable and raw to show they are aligned for ASCII
        for (BarCodeResult r : results) {
            String text = r.getCodeText();
            byte[] raw = r.getCodeBytes();
            System.out.println("CodeText: " + text);
            System.out.println("RawHex : " + bytesToHex(raw));
            Assert.assertEquals(new String(raw, StandardCharsets.US_ASCII), text);
        }
    }

    // ---------------------------------------------------------------------
    // 2) Encodings / DetectEncoding: QR with UTF-8 payload
    // ---------------------------------------------------------------------

    /**
     * Purpose:
     *  - Demonstrates reading a QR that carries Unicode text (Cyrillic).
     *  - With DetectEncoding enabled, engine returns correct Java String in CodeText.
     * Shows:
     *  - BarcodeSettings.setDetectEncoding(true)
     *  - CodeText contains Unicode as expected; raw bytes are UTF-8 (usually with ECI marker inside symbol).
     */
    @Test
    public void read_QR_UTF8_DetectEncoding() throws Exception {
        String file = "qr_utf8.png";
        BarCodeReader reader = new BarCodeReader(ExampleAssist.pathCombine(FOLDER, file), DecodeType.QR);

        // Enable automatic encoding detection (important for non-ASCII payloads)
        reader.getBarcodeSettings().setDetectEncoding(true);

        BarCodeResult[] results = reader.readBarCodes();
        ExampleAssist.assertRecognized(reader, file, 1, DecodeType.QR);

        boolean seenCyrillic = false;
        for (BarCodeResult r : results) {
            String codeText = r.getCodeText();
            byte[] raw = r.getCodeBytes(); // raw stream as read from symbol (often UTF-8 for Unicode)
            System.out.println("CodeText (decoded): " + codeText);
            System.out.println("RawHex            : " + bytesToHex(raw));
            // Should contain Cyrillic substring "Привет"
            if (codeText.contains("Привет")) {
                seenCyrillic = true;
            }
        }
        Assert.assertTrue(seenCyrillic, "Expected Unicode (Cyrillic) substring in CodeText");
    }

    // ---------------------------------------------------------------------
    // 3) GS1: human-readable vs raw (FNC1 / GS separator 0x1D)
    // ---------------------------------------------------------------------

    /**
     * Purpose:
     *  - Demonstrates how GS1 DataMatrix CodeText looks human-readable (with parentheses),
     *    while raw byte stream contains FNC1 separator (Group Separator, 0x1D) between variable-length AIs.
     * Shows:
     *  - CodeText — human-friendly format like "(01)123...(10)...(17)..."
     *  - Raw bytes — same data where variable-length fields are separated by 0x1D.
     *
     * Tip:
     *  - To detect GS1, you can check extended parameters (varies by version). Here we just compare forms.
     */
    @Test
    public void read_GS1_DataMatrix_CodeText_vs_Raw() throws Exception {
        String file = "gs1_dm.png";
        BarCodeReader reader = new BarCodeReader(ExampleAssist.pathCombine(FOLDER, file), DecodeType.GS_1_DATA_MATRIX);

        // GS1 often benefits from Normal/HighQuality; enable encoding detection just in case
        reader.getBarcodeSettings().setDetectEncoding(true);
        reader.setQualitySettings(QualitySettings.getNormalQuality());

        BarCodeResult[] results = reader.readBarCodes();
        ExampleAssist.assertRecognized(reader, file, 1, DecodeType.GS_1_DATA_MATRIX);

        for (BarCodeResult r : results) {
            String human = r.getCodeText();
            byte[] raw = r.getCodeBytes();

            System.out.println("GS1 DM human: " + human);
            System.out.println("GS1 DM raw  : " + bytesToHex(raw));

            // Human readable must contain parentheses with AIs
            Assert.assertTrue(human.contains("(01)") && human.contains("(10)") && human.contains("(17)"),
                    "Expected GS1 AIs in human-readable CodeText");

            // Raw should contain FNC1 (Group Separator, 0x1D) between variable-length data parts.
            Assert.assertTrue(containsByte(raw, (byte) 0x1D),
                    "Expected FNC1 (0x1D) separator in raw byte stream");
        }
    }

    /**
     * Purpose:
     *  - Same as previous test but with GS1 Code 128 symbology.
     * Shows:
     *  - Consistent story: human-readable with AIs vs raw with FNC1 separators.
     */
    @Test
    public void read_GS1_Code128_CodeText_vs_Raw() throws Exception {
        String file = "gs1_code128.png";
        BarCodeReader reader = new BarCodeReader(ExampleAssist.pathCombine(FOLDER, file), DecodeType.GS_1_CODE_128);

        reader.getBarcodeSettings().setDetectEncoding(true);
        reader.setQualitySettings(QualitySettings.getNormalQuality());

        BarCodeResult[] results = reader.readBarCodes();
        ExampleAssist.assertRecognized(reader, file, 1, DecodeType.GS_1_CODE_128);

        for (BarCodeResult r : results) {
            String human = r.getCodeText();
            byte[] raw = r.getCodeBytes();

            System.out.println("GS1 C128 human: " + human);
            System.out.println("GS1 C128 raw  : " + bytesToHex(raw));

            Assert.assertTrue(human.contains("(01)") && human.contains("(10)") && human.contains("(17)"),
                    "Expected GS1 AIs in human-readable CodeText");

            Assert.assertTrue(containsByte(raw, (byte) 0x1D),
                    "Expected FNC1 (0x1D) separator in raw byte stream");
        }
    }

    // ---------------------------------------------------------------------
    // Helpers (local to this test class)
    // ---------------------------------------------------------------------

    // Hex dump for raw bytes (compact).
    private static String bytesToHex(byte[] data) {
        if (data == null) return "(null)";
        StringBuilder sb = new StringBuilder(3 * data.length);
        for (byte b : data) {
            sb.append(String.format("%02X ", b));
        }
        return sb.toString().trim();
    }

    private static boolean containsByte(byte[] arr, byte val) {
        if (arr == null) return false;
        for (byte b : arr) if (b == val) return true;
        return false;
    }
}
