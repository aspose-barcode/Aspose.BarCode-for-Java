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

/**
 * Demonstrates how the {@code StripFNC} parameter affects barcode recognition.
 * <p>
 * FNC (Function Code) symbols are special non-printable control characters used in Code128 and GS1-128 barcodes.
 * They can define application identifiers (AI), initiate structured append sequences, or represent specific separators.
 * <p>
 * The {@code StripFNC} setting controls whether these symbols are stripped out from the recognized code text
 * (for cleaner output) or preserved in the returned string (for advanced use cases such as GS1 data parsing).
 */
public class StripFNCExample {

    private static final String FOLDER =
            ExampleAssist.getOrCreateResourceFolderPath("recognition", "special_parameters", "strip_FNC");

    @BeforeClass
    public void setUp() {
        LicenseAssist.setupLicense();
    }

    /**
     * Demonstrates default behavior when {@code StripFNC = true}.
     * <p>
     * In this mode (default), the recognition engine removes all FNC control symbols from the recognized text.
     * This is typically preferred when the user needs clean, human-readable data without embedded control markers.
     */
    @Test
    public void read_Code128_StripFNC_Enabled() throws Exception {
        String fileName = "code128_stripfnc_enabled.png";

        ExampleAssist.checkOrCreateImage(FOLDER, fileName, path -> {
            BarcodeGenerator generator = new BarcodeGenerator(EncodeTypes.CODE_128, "FNC1TEST123");
            generator.save(path, BarCodeImageFormat.PNG);
        });

        BarCodeReader reader = new BarCodeReader(ExampleAssist.pathCombine(FOLDER, fileName), DecodeType.CODE_128);
        reader.getBarcodeSettings().setStripFNC(true);

        ExampleAssist.assertRecognized(reader, fileName, 1, DecodeType.CODE_128);
    }

    /**
     * Demonstrates recognition when {@code StripFNC = false}.
     * <p>
     * In this case, FNC symbols are preserved inside the recognized text.
     * This is useful for debugging, GS1-128 processing, or applications that require the exact encoded content.
     */
    @Test
    public void read_Code128_StripFNC_Disabled() throws Exception {
        String fileName = "code128_stripfnc_disabled.png";

        ExampleAssist.checkOrCreateImage(FOLDER, fileName, path -> {
            BarcodeGenerator generator = new BarcodeGenerator(EncodeTypes.CODE_128, "FNC1TEST123");
            generator.save(path, BarCodeImageFormat.PNG);
        });

        BarCodeReader reader = new BarCodeReader(ExampleAssist.pathCombine(FOLDER, fileName), DecodeType.CODE_128);
        reader.getBarcodeSettings().setStripFNC(false);

        ExampleAssist.assertRecognized(reader, fileName, 1, DecodeType.CODE_128);
    }

    /**
     * Shows the difference in output text when {@code StripFNC} is disabled.
     * <p>
     * This example prints the raw recognized text to console, allowing the user to visually inspect
     * that the FNC control characters (such as FNC1) are present in the recognized result.
     */
    @Test
    public void read_Code128_StripFNC_CheckSymbolPresence() throws Exception {
        String fileName = "code128_fncpreserved.png";

        ExampleAssist.checkOrCreateImage(FOLDER, fileName, path -> {
            BarcodeGenerator generator = new BarcodeGenerator(EncodeTypes.CODE_128, "FNC1DATA999");
            generator.save(path, BarCodeImageFormat.PNG);
        });

        BarCodeReader reader = new BarCodeReader(ExampleAssist.pathCombine(FOLDER, fileName), DecodeType.CODE_128);
        reader.getBarcodeSettings().setStripFNC(false);

        BarCodeResult[] results = reader.readBarCodes();
        for (BarCodeResult result : results) {
            System.out.println("Code Type : " + result.getCodeTypeName());
            System.out.println("Code Text : " + result.getCodeText());
        }

        ExampleAssist.assertRecognized(reader, fileName, 1, DecodeType.CODE_128);
    }

    /**
     * Compares the recognized text with {@code StripFNC = true} and {@code StripFNC = false} for the same image.
     * <p>
     * This demonstrates that both settings recognize the barcode correctly,
     * but produce slightly different text results depending on whether the control symbols are removed.
     */
    @Test
    public void compare_StripFNC_Results() throws Exception {
        String fileName = "code128_compare.png";

        ExampleAssist.checkOrCreateImage(FOLDER, fileName, path -> {
            BarcodeGenerator generator = new BarcodeGenerator(EncodeTypes.CODE_128, "FNC1DATA555");
            generator.save(path, BarCodeImageFormat.PNG);
        });

        // StripFNC = true
        BarCodeReader reader1 = new BarCodeReader(ExampleAssist.pathCombine(FOLDER, fileName), DecodeType.CODE_128);
        reader1.getBarcodeSettings().setStripFNC(true);
        var results1 = reader1.readBarCodes();

        // StripFNC = false
        BarCodeReader reader2 = new BarCodeReader(ExampleAssist.pathCombine(FOLDER, fileName), DecodeType.CODE_128);
        reader2.getBarcodeSettings().setStripFNC(false);
        var results2 = reader2.readBarCodes();

        System.out.println("With StripFNC (true): " + results1[0].getCodeText());
        System.out.println("Without StripFNC (false): " + results2[0].getCodeText());

        ExampleAssist.assertRecognized(reader1, fileName, 1, DecodeType.CODE_128);
        ExampleAssist.assertRecognized(reader2, fileName, 1, DecodeType.CODE_128);
    }

    /**
     * Demonstrates that {@code StripFNC} affects both Code128 and GS1-128 decoding in the same way.
     * <p>
     * Even if the barcode was encoded as GS1-128 (with FNC1 inserted automatically),
     * reading it through {@code DecodeType.CODE_128} still respects the StripFNC flag.
     * This ensures consistent recognition behavior regardless of the chosen decode type.
     */
    @Test
    public void read_Gs1_Encoded_As_Code128() throws Exception {
        String fileName = "gs1encoded_as_code128.png";

        ExampleAssist.checkOrCreateImage(FOLDER, fileName, path -> {
            // GS1-128 automatically adds FNC1 when encoding (AI syntax triggers it)
            BarcodeGenerator generator = new BarcodeGenerator(EncodeTypes.GS_1_CODE_128, "(01)09501101530008(10)ABC123");
            generator.save(path, BarCodeImageFormat.PNG);
        });

        // Decode as regular Code128 with StripFNC enabled (default)
        BarCodeReader reader1 = new BarCodeReader(ExampleAssist.pathCombine(FOLDER, fileName), DecodeType.CODE_128);
        reader1.getBarcodeSettings().setStripFNC(true);
        var results1 = reader1.readBarCodes();

        // Decode as regular Code128 with StripFNC disabled (preserve FNC)
        BarCodeReader reader2 = new BarCodeReader(ExampleAssist.pathCombine(FOLDER, fileName), DecodeType.CODE_128);
        reader2.getBarcodeSettings().setStripFNC(false);
        var results2 = reader2.readBarCodes();

        System.out.println("Decoded as CODE_128 with StripFNC(true): " + results1[0].getCodeText());
        System.out.println("Decoded as CODE_128 with StripFNC(false): " + results2[0].getCodeText());

        ExampleAssist.assertRecognized(reader1, fileName, 1, DecodeType.CODE_128);
        ExampleAssist.assertRecognized(reader2, fileName, 1, DecodeType.CODE_128);
    }
}
