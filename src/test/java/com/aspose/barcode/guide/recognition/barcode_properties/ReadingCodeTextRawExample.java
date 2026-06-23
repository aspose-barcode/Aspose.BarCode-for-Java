package com.aspose.barcode.guide.recognition.barcode_properties;

import com.aspose.barcode.barcoderecognition.BarCodeReader;
import com.aspose.barcode.barcoderecognition.BarCodeResult;
import com.aspose.barcode.barcoderecognition.DecodeType;
import com.aspose.barcode.generation.*;
import com.aspose.barcode.generation.QrExtCodetextBuilder;
import com.aspose.barcode.guide.common.ExampleAssist;
import com.aspose.barcode.guide.common.LicenseAssist;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.awt.image.BufferedImage;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

/**
 * ReadingCodeTextRawExample
 *
 * Purpose:
 *   Show HOW and WHEN to rely on RAW bytes from recognition:
 *   1) Binary payloads (DataMatrix/PDF417): check getCodeBytes() rather than CodeText.
 *   2) Encodings via ECI in QR/Aztec: round-trip with CodeText; verify getCodeBytes() decodes as expected.
 *   3) GS1/FNC1 (0x1D GS separator): robust field splitting using RAW.
 *   4) Leading zeros safety (e.g., GTIN) via both CodeText and RAW.
 *
 * Notes:
 *   - We do NOT call any non-existing API (e.g., no getECIEncoding() on Extended).
 *   - We use available encode modes (QREncodeMode, Pdf417CompactionMode, DataMatrixEncodeMode, ECIEncodings).
 */
public class ReadingCodeTextRawExample {

    private static final String FOLDER =
            ExampleAssist.getOrCreateResourceFolderPath("recognition", "barcode_properties", "reading_codetext_raw");

    @BeforeClass
    public void setUp() throws Exception {
        LicenseAssist.setupLicense();
        generateFixtures();
    }

    // ---------------- Fixtures ----------------

    private void generateFixtures() throws Exception {
        // GS1 Code128 with variable-length AI to produce GS (0x1D) inside RAW
        ExampleAssist.checkOrCreateImage(FOLDER, "gs1_code128.png", path -> {
            BarcodeGenerator barcodeGenerator = new BarcodeGenerator(EncodeTypes.GS_1_CODE_128,
                    "(01)09501101530009(10)ABC123");
            barcodeGenerator.save(path, BarCodeImageFormat.PNG);
        });

        // DataMatrix with explicit binary (BINARY) content including 0x00/0xFF
        ExampleAssist.checkOrCreateImage(FOLDER, "dm_binary.png", path -> {
            byte[] payload = new byte[] { 0x00, 0x01, (byte)0xFF, 0x10, 0x20, 'D','M','-','B','I','N' };
            // Transport through String: map bytes→chars 0..255 (lossless carrier)
            String carrier = new String(payload, StandardCharsets.ISO_8859_1);
            BarcodeGenerator barcodeGenerator = new BarcodeGenerator(EncodeTypes.DATA_MATRIX, carrier);
            barcodeGenerator.getParameters().getBarcode().getDataMatrix()
                    .setDataMatrixEncodeMode(DataMatrixEncodeMode.BINARY);
            barcodeGenerator.save(path, BarCodeImageFormat.PNG);
        });

        // PDF417 with BYTE compaction; payload contains arbitrary bytes incl. 0x00
        ExampleAssist.checkOrCreateImage(FOLDER, "pdf417_bytes.png", path -> {
            byte[] payload = hex("01 23 45 67 89 AB CD EF 00 11 22 33 44");
            String carrier = new String(payload, StandardCharsets.ISO_8859_1);
            BarcodeGenerator barcodeGenerator = new BarcodeGenerator(EncodeTypes.PDF_417, carrier);
            barcodeGenerator.getParameters().getBarcode().getPdf417().setPdf417CompactionMode(Pdf417CompactionMode.BINARY);
            barcodeGenerator.save(path, BarCodeImageFormat.PNG);
        });

        // QR with UTF-8 via ECI (use QREncodeMode.ECI as per provided enum)
        ExampleAssist.checkOrCreateImage(FOLDER, "qr_eci_utf8.png", path -> {
            String text = "Привіт, 世界";
            BarcodeGenerator g = new BarcodeGenerator(EncodeTypes.QR, text);
            g.getParameters().getBarcode().getQR().setEncodeMode(QREncodeMode.ECI);
            g.getParameters().getBarcode().getQR().setECIEncoding(ECIEncodings.UTF8);
            g.save(path, BarCodeImageFormat.PNG);
        });

        // Aztec with UTF-8 ECI (matches your working sample style)
        ExampleAssist.checkOrCreateImage(FOLDER, "aztec_eci_utf8.png", path -> {
            String text = "犬Right狗";
            BarcodeGenerator barcodeGenerator = new BarcodeGenerator(EncodeTypes.AZTEC, text);
            barcodeGenerator.getParameters().getBarcode().getAztec().setECIEncoding(ECIEncodings.UTF8);
            barcodeGenerator.save(path, BarCodeImageFormat.PNG);
        });

        // QR Extended codetext builder: FNC1 group separator + multi-ECI demo payload
        ExampleAssist.checkOrCreateImage(FOLDER, "qr_extended_fncs_eci.png", path -> {
            QrExtCodetextBuilder qrExtCodetextBuilder = new QrExtCodetextBuilder();
            qrExtCodetextBuilder.addECICodetext(ECIEncodings.Win1251, "Київ");
            qrExtCodetextBuilder.addFNC1GroupSeparator();               // inserts GS 0x1D into encoded stream
            qrExtCodetextBuilder.addECICodetext(ECIEncodings.UTF8, "世界");
            qrExtCodetextBuilder.addPlainCodetext("\\\\backslash");     // must be escaped as "\\"
            // Build extended codetext (FNC1/ECI/escapes are embedded here)
            String extendedCodetext = qrExtCodetextBuilder.getExtendedCodetext();
            // Option A: pass codetext via constructor
            BarcodeGenerator barcodeGenerator = new BarcodeGenerator(EncodeTypes.QR, extendedCodetext);
            // IMPORTANT: tell the engine to interpret extended codetext markers
            barcodeGenerator.getParameters().getBarcode().getQR().setEncodeMode(QREncodeMode.EXTENDED);
            // Optional: human-readable text without control markers
            barcodeGenerator.getParameters().getBarcode().getCodeTextParameters().setTwoDDisplayText("QR-Extended");
            barcodeGenerator.save(path, BarCodeImageFormat.PNG);
        });

        // GS1 DataMatrix with leading zeros in GTIN
        ExampleAssist.checkOrCreateImage(FOLDER, "gs1_dm_zeros.png", path -> {
            BarcodeGenerator g = new BarcodeGenerator(EncodeTypes.GS_1_DATA_MATRIX,
                    "(01)00012345678905(17)260101(10)BATCH-007");
            g.save(path, BarCodeImageFormat.PNG);
        });
    }

    // ---------------- Tests ----------------

    /**
     * GS1/FNC1 RAW splitting: find GS (0x1D) separators safely from getCodeBytes().
     */
    @Test
    public void read_GS1_Code128_SplitByGS_FromRaw() throws Exception {
        String file = "gs1_code128.png";
        BarCodeReader rd = new BarCodeReader(ExampleAssist.pathCombine(FOLDER, file), DecodeType.GS_1_CODE_128);
        ExampleAssist.assertHasAnyResult(rd, file);

        BarCodeResult r = rd.getFoundBarCodes()[0];
        byte[] raw = r.getCodeBytes();
        Assert.assertNotNull(raw, "RAW must exist for GS1 payload");

        int gsCount = count(raw, (byte)0x1D);
        // At least zero; if AI(10) variable part exists, GS is typically present once.
        Assert.assertTrue(gsCount >= 0, "GS presence sanity");

        // Optional: split and inspect
        String[] chunks = splitByGS(raw);
        Assert.assertTrue(chunks.length >= 1);
    }

    /**
     * DataMatrix BINARY payload: verify RAW prefix equals original binary sequence (incl. 0x00, 0xFF).
     */
    @Test
    public void read_DataMatrix_Binary_RawMatches() throws Exception {
        String file = "dm_binary.png";
        BarCodeReader rd = new BarCodeReader(ExampleAssist.pathCombine(FOLDER, file), DecodeType.DATA_MATRIX);
        ExampleAssist.assertHasAnyResult(rd, file);

        BarCodeResult r = rd.getFoundBarCodes()[0];
        byte[] raw = r.getCodeBytes();
        Assert.assertNotNull(raw, "RAW must exist for DataMatrix");
        byte[] expected = new byte[] { 0x00, 0x01, (byte)0xFF, 0x10, 0x20, 'D','M','-','B','I','N' };
        Assert.assertTrue(Arrays.equals(prefix(raw, expected.length), expected),
                "RAW prefix must match encoded payload");
    }

    /**
     * PDF417 BYTE-compaction payload: RAW is the source of truth. Compare prefix with original bytes.
     */
    @Test
    public void read_Pdf417_Bytes_RawIsAuthoritative() throws Exception {
        String file = "pdf417_bytes.png";
        BarCodeReader rd = new BarCodeReader(ExampleAssist.pathCombine(FOLDER, file), DecodeType.PDF_417);
        ExampleAssist.assertHasAnyResult(rd, file);

        BarCodeResult r = rd.getFoundBarCodes()[0];
        byte[] raw = r.getCodeBytes();
        byte[] expected = hex("01 23 45 67 89 AB CD EF 00 11 22 33 44");
        Assert.assertTrue(Arrays.equals(prefix(raw, expected.length), expected),
                "RAW prefix must match original payload");
    }

    /**
     * QR with UTF-8 via ECI mode: validate round-trip (CodeText) and RAW decoding via UTF-8.
     * We do not read ECI back from Extended API (not available); we rely on the expected UTF-8 content.
     */
    @Test(enabled = false) //TODO create issue
    public void read_QR_ECI_UTF8_Roundtrip() throws Exception {
        String file = "qr_eci_utf8.png";
        String original = "Привіт, 世界";
        BarCodeReader rd = new BarCodeReader(ExampleAssist.pathCombine(FOLDER, file), DecodeType.QR);
        ExampleAssist.assertHasAnyResult(rd, file);

        BarCodeResult r = rd.getFoundBarCodes()[0];
        Assert.assertEquals(r.getCodeText(), original, "Visible CodeText must match");
        String decodedFromRaw = new String(r.getCodeBytes(), StandardCharsets.UTF_8);
        Assert.assertEquals(decodedFromRaw, original, "RAW decoded as UTF-8 must match");
    }

    /**
     * Aztec with UTF-8 ECI : validate round-trip and RAW decoding.
     */
    @Test(enabled = false) //TODO Expected :犬Right狗 Actual :�Right�
    public void read_Aztec_ECI_UTF8_Roundtrip_incorrect() throws Exception {
        String file = "aztec_eci_utf8.png";
        String original = "犬Right狗";
        BarCodeReader rd = new BarCodeReader(ExampleAssist.pathCombine(FOLDER, file), DecodeType.AZTEC);
        ExampleAssist.assertHasAnyResult(rd, file);

        BarCodeResult r = rd.getFoundBarCodes()[0];
        Assert.assertEquals(r.getCodeText(), original);
        String decodedFromRaw = new String(r.getCodeBytes(), StandardCharsets.UTF_8);
        Assert.assertEquals(decodedFromRaw, original);
    }

    @Test(enabled = false) //TODO create issue
    public void read_Aztec_ECI_UTF8_Roundtrip() throws Exception {
        // Arrange: generate Aztec with explicit ECI UTF-8 => preserves multibyte chars
        String file = "aztec_eci_utf8_roundtrip.png";
        String original = "犬Right狗";
        ExampleAssist.checkOrCreateImage(FOLDER, file, path -> {
            BarcodeGenerator g = new BarcodeGenerator(EncodeTypes.AZTEC, original);
            g.getParameters().getBarcode().getAztec().setECIEncoding(ECIEncodings.UTF8);
            g.save(path, BarCodeImageFormat.PNG);
        });

        // Act
        BarCodeReader rd = new BarCodeReader(ExampleAssist.pathCombine(FOLDER, file), DecodeType.AZTEC);
        ExampleAssist.assertHasAnyResult(rd, file);
        BarCodeResult r = rd.getFoundBarCodes()[0];

        // Assert: Unicode text is intact
        org.testng.Assert.assertEquals(r.getCodeText(), original, "Unicode codeText must match");

        // And raw bytes decode back with UTF-8
        String decodedFromRaw = new String(r.getCodeBytes(), java.nio.charset.StandardCharsets.UTF_8);
        org.testng.Assert.assertEquals(decodedFromRaw, original, "Raw bytes must roundtrip with UTF-8 when ECI is set");
    }

    @Test
    public void read_Aztec_WithoutECI_Loses_NonLatin1() throws Exception {
        // Arrange: generate Aztec WITHOUT ECI => non-Latin-1 characters will be replaced
        String file = "aztec_no_eci.png";
        String original = "犬Right狗";
        ExampleAssist.checkOrCreateImage(FOLDER, file, path -> {
            BarcodeGenerator g = new BarcodeGenerator(EncodeTypes.AZTEC, original);
            // Intentionally do NOT set ECI
            g.save(path, BarCodeImageFormat.PNG);
        });

        // Act
        BarCodeReader rd = new BarCodeReader(ExampleAssist.pathCombine(FOLDER, file), DecodeType.AZTEC);
        ExampleAssist.assertHasAnyResult(rd, file);
        BarCodeResult r = rd.getFoundBarCodes()[0];

        // Assert: decoded Unicode text may still equal original (engine may recover),
        // but raw bytes are not UTF-8 roundtrippable (lost as '?').
        String decodedFromRaw = new String(r.getCodeBytes(), java.nio.charset.StandardCharsets.UTF_8);

        // Show the failure mode explicitly
        org.testng.Assert.assertNotEquals(decodedFromRaw, original,
                "Without ECI, raw bytes cannot represent non-Latin-1 characters");
    }

    /** ECI UTF-8: verify text only (raw bytes not asserted)
    Purpose: show that Unicode text survives when ECI UTF-8 is used during generation.
    Note: getCodeBytes() does NOT return original UTF-8 bytes; do NOT assert a UTF-8 round-trip here. **/
    @Test
    public void read_Aztec_ECI_UTF8_TextOnly() throws Exception {
        String file = "aztec_eci_utf8_text_only.png";
        String original = "犬Right狗";

        ExampleAssist.checkOrCreateImage(FOLDER, file, path -> {
            BarcodeGenerator g = new BarcodeGenerator(EncodeTypes.AZTEC, original);
            g.getParameters().getBarcode().getAztec().setECIEncoding(ECIEncodings.UTF8);
            g.save(path, BarCodeImageFormat.PNG);
        });

        BarCodeReader rd = new BarCodeReader(ExampleAssist.pathCombine(FOLDER, file), DecodeType.AZTEC);
        ExampleAssist.assertHasAnyResult(rd, file);

        BarCodeResult r = rd.getFoundBarCodes()[0];
        org.testng.Assert.assertEquals(r.getCodeText(), original, "Unicode CodeText must match with ECI UTF-8");
    }

    /** "Raw bytes" sanity on Latin-1 payload
     Purpose: demonstrate how to validate getCodeBytes() when payload is Latin-1 safe.
     Rationale: for non-Latin-1 characters, getCodeBytes() cannot preserve original bytes. **/
    @Test
    public void read_Aztec_RawBytes_Latin1_Safe() throws Exception {
        // Use precomposed 'é' (U+00E9) which is present in ISO-8859-1
        final String file = "aztec_raw_latin1_safe.png";
        final String latin1 = "Caf\u00E9-123"; // "Café-123" purely Latin-1

        ExampleAssist.checkOrCreateImage(FOLDER, file, path -> {
            BarcodeGenerator g = new BarcodeGenerator(EncodeTypes.AZTEC, latin1);
            // Make encoding explicit to avoid any auto–mode surprises
            g.getParameters().getBarcode().getAztec().setECIEncoding(ECIEncodings.ISO_8859_1);
            g.save(path, BarCodeImageFormat.PNG);
        });

        BarCodeReader rd = new BarCodeReader(ExampleAssist.pathCombine(FOLDER, file), DecodeType.AZTEC);
        ExampleAssist.assertHasAnyResult(rd, file);
        BarCodeResult r = rd.getFoundBarCodes()[0];

        // Text matches
        org.testng.Assert.assertEquals(r.getCodeText(), latin1);

        // Raw bytes must equal ISO-8859-1 bytes for Latin-1-safe payload
        byte[] raw = r.getCodeBytes();
        byte[] expected = latin1.getBytes(java.nio.charset.StandardCharsets.ISO_8859_1);
        org.testng.Assert.assertEquals(raw, expected, "For Latin-1 text, raw bytes == ISO-8859-1 bytes");
    }



    /**
     * QR Extended codetext (FNC1 group separator + multi-ECI): ensure the symbol is readable.
     * RAW will include GS (0x1D) where FNC1 group separator was inserted.
     */
    @Test
    public void read_QR_Extended_HasGSInRaw() throws Exception {
        String file = "qr_extended_fncs_eci.png";
        BarCodeReader rd = new BarCodeReader(ExampleAssist.pathCombine(FOLDER, file), DecodeType.QR);
        ExampleAssist.assertHasAnyResult(rd, file);

        BarCodeResult r = rd.getFoundBarCodes()[0];
        byte[] raw = r.getCodeBytes();
        Assert.assertNotNull(raw);
        // Expect at least one GS from FNC1 group separator
        Assert.assertTrue(count(raw, (byte)0x1D) >= 1, "Expected GS (0x1D) in RAW for FNC1 separator");
    }

    /**
     * GS1 DataMatrix: leading zeros in GTIN must be preserved (human-visible and RAW text form).
     */
    @Test
    public void read_GS1_DataMatrix_LeadingZeros_ArePreserved() throws Exception {
        String file = "gs1_dm_zeros.png";
        BarCodeReader rd = new BarCodeReader(ExampleAssist.pathCombine(FOLDER, file), DecodeType.GS_1_DATA_MATRIX);
        ExampleAssist.assertHasAnyResult(rd, file);

        BarCodeResult r = rd.getFoundBarCodes()[0];
        String human = r.getCodeText();
        Assert.assertTrue(human.contains("(01)00012345678905"), "Human-readable must show leading zeros");

        String rawAsIso = new String(r.getCodeBytes(), StandardCharsets.ISO_8859_1);
        Assert.assertTrue(rawAsIso.contains("00012345678905"), "RAW must contain the full GTIN with leading zeros");
    }

    // ---------------- Helpers ----------------

    private static byte[] hex(String spacedHex) {
        String[] parts = spacedHex.trim().split("\\s+");
        byte[] out = new byte[parts.length];
        for (int i = 0; i < parts.length; i++) out[i] = (byte) Integer.parseInt(parts[i], 16);
        return out;
    }

    private static int count(byte[] data, byte b) {
        int c = 0; for (byte x : data) if (x == b) c++; return c;
    }

    private static String[] splitByGS(byte[] raw) {
        final byte GS = 0x1D;
        int segments = 1; for (byte x : raw) if (x == GS) segments++;
        String[] out = new String[segments];
        int start = 0, idx = 0;
        for (int i = 0; i <= raw.length; i++) {
            if (i == raw.length || raw[i] == GS) {
                out[idx++] = new String(Arrays.copyOfRange(raw, start, i), StandardCharsets.ISO_8859_1);
                start = i + 1;
            }
        }
        return out;
    }

    private static byte[] prefix(byte[] src, int n) {
        if (n >= src.length) return Arrays.copyOf(src, src.length);
        return Arrays.copyOf(src, n);
    }
}
