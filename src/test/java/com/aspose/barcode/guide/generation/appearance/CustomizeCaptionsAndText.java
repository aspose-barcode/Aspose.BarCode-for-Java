package com.aspose.barcode.guide.generation.appearance;

import com.aspose.barcode.barcoderecognition.DecodeType;
import com.aspose.barcode.generation.BarCodeImageFormat;
import com.aspose.barcode.generation.BarcodeGenerator;
import com.aspose.barcode.generation.CaptionParameters;
import com.aspose.barcode.generation.CodeLocation;
import com.aspose.barcode.generation.CodetextParameters;
import com.aspose.barcode.generation.EncodeTypes;
import com.aspose.barcode.generation.FontMode;
import com.aspose.barcode.generation.FontStyle;
import com.aspose.barcode.generation.TextAlignment;
import com.aspose.barcode.guide.common.ExampleAssist;
import com.aspose.barcode.guide.common.LicenseAssist;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.awt.Color;
import java.util.List;

import static com.aspose.barcode.guide.common.ExampleAssist.assertImageHasBarcodes;
import static com.aspose.barcode.guide.common.ExampleAssist.expected;

/**
 * Demonstrates captions, human-readable code text, font settings, and spacing.
 */
public class CustomizeCaptionsAndText {

    private static final String FOLDER =
            ExampleAssist.getOrCreateResourceFolderPath("generation", "customize-captions-and-text");

    @BeforeClass
    public void setUp() {
        LicenseAssist.setupLicense();
    }

    /**
     * Configures the human-readable text below a Code 128 barcode.
     */
    @Test
    public void configureHumanReadableText() throws Exception {
        String codeText = "HRI-12345";
        String outputPath = ExampleAssist.pathCombine(FOLDER, "human_readable_text.png");

        BarcodeGenerator generator = new BarcodeGenerator(EncodeTypes.CODE_128, codeText);
        CodetextParameters codeTextParameters =
                generator.getParameters().getBarcode().getCodeTextParameters();
        codeTextParameters.setLocation(CodeLocation.BELOW);
        codeTextParameters.setFontMode(FontMode.MANUAL);
        codeTextParameters.getFont().setFamilyName("Arial");
        codeTextParameters.getFont().setStyle(FontStyle.BOLD);
        codeTextParameters.getFont().getSize().setPoint(11);
        codeTextParameters.setAlignment(TextAlignment.CENTER);
        codeTextParameters.setColor(Color.DARK_GRAY);
        codeTextParameters.getSpace().setPoint(2);
        generator.save(outputPath, BarCodeImageFormat.PNG);

        ExampleAssist.assertFileCreated(outputPath);
        assertImageHasBarcodes(outputPath, 1, List.of(expected(DecodeType.CODE_128, codeText)));
    }

    /**
     * Adds independent captions above and below the barcode while hiding the code text.
     */
    @Test
    public void configureCaptionsAboveAndBelow() throws Exception {
        String codeText = "CAPTION-EXAMPLE";
        String outputPath = ExampleAssist.pathCombine(FOLDER, "captions_above_below.png");

        BarcodeGenerator generator = new BarcodeGenerator(EncodeTypes.CODE_128, codeText);
        generator.getParameters().getBarcode().getCodeTextParameters().setLocation(CodeLocation.NONE);

        CaptionParameters captionAbove = generator.getParameters().getCaptionAbove();
        captionAbove.setVisible(true);
        captionAbove.setText("INVENTORY LABEL");
        captionAbove.setAlignment(TextAlignment.CENTER);
        captionAbove.getFont().getSize().setPoint(12);
        captionAbove.getPadding().getBottom().setPoint(5);

        CaptionParameters captionBelow = generator.getParameters().getCaptionBelow();
        captionBelow.setVisible(true);
        captionBelow.setText("Warehouse A");
        captionBelow.setAlignment(TextAlignment.CENTER);
        captionBelow.getFont().getSize().setPoint(10);
        captionBelow.getPadding().getTop().setPoint(5);

        generator.getParameters().getImageWidth().setPixels(500);
        generator.getParameters().getImageHeight().setPixels(220);
        generator.save(outputPath, BarCodeImageFormat.PNG);

        ExampleAssist.assertFileCreated(outputPath);
        assertImageHasBarcodes(outputPath, 1, List.of(expected(DecodeType.CODE_128, codeText)));
    }
}
