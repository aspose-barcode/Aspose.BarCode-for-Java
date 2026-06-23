package com.aspose.barcode.guide.generation.overview;

import com.aspose.barcode.barcoderecognition.DecodeType;
import com.aspose.barcode.generation.BarCodeImageFormat;
import com.aspose.barcode.generation.BarcodeGenerator;
import com.aspose.barcode.generation.EncodeTypes;
import com.aspose.barcode.guide.common.ExampleAssist;
import com.aspose.barcode.guide.common.LicenseAssist;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

import static com.aspose.barcode.guide.common.ExampleAssist.assertImageHasBarcodes;
import static com.aspose.barcode.guide.common.ExampleAssist.expected;

/**
 * Provides a minimal end-to-end barcode generation example.
 */
public class BarcodeGenerationOverview {

    private static final String FOLDER =
            ExampleAssist.getOrCreateResourceFolderPath("generation", "overview");

    @BeforeClass
    public void setUp() {
        LicenseAssist.setupLicense();
    }

    /**
     * Generates a Code 128 barcode, saves it as PNG, and verifies the result by recognition.
     */
    @Test
    public void generateAndVerifyBarcode() throws Exception {
        String codeText = "BARCODE-GENERATION";
        String outputPath = ExampleAssist.pathCombine(FOLDER, "barcode_generation_overview.png");

        BarcodeGenerator generator = new BarcodeGenerator(EncodeTypes.CODE_128, codeText);
        generator.save(outputPath, BarCodeImageFormat.PNG);

        ExampleAssist.assertFileCreated(outputPath);
        assertImageHasBarcodes(
                outputPath,
                1,
                List.of(expected(DecodeType.CODE_128, codeText))
        );
    }
}
