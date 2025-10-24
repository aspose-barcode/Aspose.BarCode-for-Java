package com.aspose.barcode.guide.recognition.special_parameters;

import com.aspose.barcode.barcoderecognition.BarCodeReader;
import com.aspose.barcode.barcoderecognition.DecodeType;
import com.aspose.barcode.generation.BarCodeImageFormat;
import com.aspose.barcode.generation.BarcodeGenerator;
import com.aspose.barcode.generation.EncodeTypes;
import com.aspose.barcode.guide.common.ExampleAssist;
import com.aspose.barcode.guide.common.LicenseAssist;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * Demonstrates the usage of the StripFNC parameter in barcode recognition.
 */
public class StripFNCExample {

    private static final String FOLDER = ExampleAssist.getOrCreateResourceFolderPath("recognition", "special_parameters", "strip_FNC");

    @BeforeClass
    public void setUp() {
        LicenseAssist.setupLicense();
    }

    /**
     * Generates and reads a Code128 barcode with FNC symbols included (default: StripFNC = true).
     */
    @Test
    public void read_Code128_StripFNC_Enabled() throws Exception {
        String fileName = "code128_stripfnc_enabled.png";

        ExampleAssist.checkOrCreateImage(FOLDER, fileName, path -> {
            BarcodeGenerator generator = new BarcodeGenerator(EncodeTypes.CODE_128, "FNC1TEST123");
            generator.save(path, BarCodeImageFormat.PNG);
        });

        BarCodeReader reader = new BarCodeReader(ExampleAssist.pathCombine(FOLDER,fileName), DecodeType.CODE_128);
        reader.getBarcodeSettings().setStripFNC(true); // default behavior

        ExampleAssist.assertRecognized(reader, fileName, 1, DecodeType.CODE_128);
    }

    /**
     * Generates and reads a Code128 barcode with FNC symbols preserved (StripFNC = false).
     */
    @Test
    public void read_Code128_StripFNC_Disabled() throws Exception {
        String fileName = "code128_stripfnc_disabled.png";

        ExampleAssist.checkOrCreateImage(FOLDER, fileName, path -> {
            BarcodeGenerator generator = new BarcodeGenerator(EncodeTypes.CODE_128, "FNC1TEST123");
            generator.save(path, BarCodeImageFormat.PNG);
        });

        BarCodeReader reader = new BarCodeReader(ExampleAssist.pathCombine(FOLDER,fileName), DecodeType.CODE_128);
        reader.getBarcodeSettings().setStripFNC(false);

        ExampleAssist.assertRecognized(reader, fileName, 1, DecodeType.CODE_128);
    }

    /**
     * Reads Code128 with StripFNC disabled and prints FNC presence in the recognized code text.
     */
    @Test
    public void read_Code128_StripFNC_CheckSymbolPresence() throws Exception {
        String fileName = "code128_fncpreserved.png";

        ExampleAssist.checkOrCreateImage(FOLDER, fileName, path -> {
            BarcodeGenerator generator = new BarcodeGenerator(EncodeTypes.CODE_128, "FNC1DATA999");
            generator.save(path, BarCodeImageFormat.PNG);
        });

        BarCodeReader reader = new BarCodeReader(ExampleAssist.pathCombine(FOLDER,fileName), DecodeType.CODE_128);
        reader.getBarcodeSettings().setStripFNC(false);

        var results = reader.readBarCodes();
        for (var result : results) {
            System.out.println("Code Type : " + result.getCodeTypeName());
            System.out.println("Code Text : " + result.getCodeText());
        }

        ExampleAssist.assertRecognized(reader, fileName, 1, DecodeType.CODE_128);
    }

    /**
     * Compares StripFNC true vs false recognition results for the same image.
     */
    @Test
    public void compare_StripFNC_Results() throws Exception {
        String fileName = "code128_compare.png";

        ExampleAssist.checkOrCreateImage(FOLDER, fileName, path -> {
            BarcodeGenerator generator = new BarcodeGenerator(EncodeTypes.CODE_128, "FNC1DATA555");
            generator.save(path, BarCodeImageFormat.PNG);
        });

        // StripFNC = true
        BarCodeReader reader1 = new BarCodeReader(ExampleAssist.pathCombine(FOLDER,fileName), DecodeType.CODE_128);
        reader1.getBarcodeSettings().setStripFNC(true);
        var results1 = reader1.readBarCodes();

        // StripFNC = false
        BarCodeReader reader2 = new BarCodeReader(ExampleAssist.pathCombine(FOLDER,fileName), DecodeType.CODE_128);
        reader2.getBarcodeSettings().setStripFNC(false);
        var results2 = reader2.readBarCodes();

        System.out.println("With StripFNC (true): " + results1[0].getCodeText());
        System.out.println("Without StripFNC (false): " + results2[0].getCodeText());

        ExampleAssist.assertRecognized(reader1, fileName, 1, DecodeType.CODE_128);
        ExampleAssist.assertRecognized(reader2, fileName, 1, DecodeType.CODE_128);
    }
}
