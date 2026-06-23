package com.aspose.barcode.guide.generation;

import com.aspose.barcode.barcoderecognition.DecodeType;
import com.aspose.barcode.generation.*;
import com.aspose.barcode.guide.common.ExampleAssist;
import com.aspose.barcode.guide.common.LicenseAssist;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.awt.Color;
import java.awt.Font;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * TestNG suite for Code 128 generation examples used in the Developer Guide.
 * All tests write PNG files into the {@code code128} resource folder and verify
 * that the generated barcode can be recognized as Code 128 with the expected text.
 *
 * <p>GS1-128 examples are maintained in a separate test class.</p>
 */
public class GenerateCode128 {

    private static final String FOLDER = ExampleAssist.getOrCreateResourceFolderPath("generation","code128");

    @BeforeClass
    public void setUp() {
        LicenseAssist.setupLicense();
    }

    /**
     * Generates a basic Code 128 barcode with default parameters.
     */
    @Test
    public void quickStart() throws Exception {
        String payload = "ASPOSE COMPANY";
        BarcodeGenerator generator = new BarcodeGenerator(EncodeTypes.CODE_128, payload);

        saveAndAssert(generator, "code128_quick.png", payload);
    }

    /**
     * Places human-readable text below the bars and configures its font and spacing.
     * Manual font mode is required for the explicitly configured font size to be used.
     */
    @Test
    public void codetext_Below_with_Font_and_Spacing() throws Exception {
        String payload = "PRODUCT-789";
        BarcodeGenerator generator = new BarcodeGenerator(EncodeTypes.CODE_128, payload);

        CodetextParameters codeTextParameters = generator.getParameters()
                .getBarcode()
                .getCodeTextParameters();

        codeTextParameters.setLocation(CodeLocation.BELOW);
        codeTextParameters.setFontMode(FontMode.MANUAL);
        codeTextParameters.getFont().setFamilyName("Arial");
        codeTextParameters.getFont().setStyle(Font.PLAIN);
        codeTextParameters.getFont().getSize().setPoint(12);
        codeTextParameters.getSpace().setPoint(2);

        saveAndAssert(generator, "code128_with_text.png", payload);
    }

    /**
     * Generates a Code 128 barcode with 300 DPI metadata and fits it into a fixed
     * image bounding box by using {@link AutoSizeMode#NEAREST}.
     */
    @Test
    public void printQuality_300dpi() throws Exception {
        String payload = "PRINT-READY-001";
        BarcodeGenerator generator = new BarcodeGenerator(EncodeTypes.CODE_128, payload);

        // Resolution is used when physical units are converted to pixels.
        generator.getParameters().setResolution(300.0f);

        // NEAREST fits the barcode into the target box while preserving valid proportions.
        generator.getParameters().setAutoSizeMode(AutoSizeMode.NEAREST);
        generator.getParameters().getImageWidth().setPixels(400);
        generator.getParameters().getImageHeight().setPixels(150);

        saveAndAssert(generator, "code128_300dpi.png", payload);
    }

    /**
     * Configures colors, blank margins, and a visible border around a Code 128 barcode.
     */
    @Test
    public void styling_Colors_Padding_Border() throws Exception {
        String payload = "STYLE-2025";
        BarcodeGenerator generator = new BarcodeGenerator(EncodeTypes.CODE_128, payload);

        generator.getParameters().setBackColor(Color.WHITE);
        generator.getParameters().getBarcode().setBarColor(Color.BLACK);

        // Reserve blank space that can be used to satisfy quiet-zone requirements.
        generator.getParameters().getBarcode().getPadding().getLeft().setPixels(12);
        generator.getParameters().getBarcode().getPadding().getRight().setPixels(12);
        generator.getParameters().getBarcode().getPadding().getTop().setPixels(6);
        generator.getParameters().getBarcode().getPadding().getBottom().setPixels(6);

        generator.getParameters().getBorder().setVisible(true);
        generator.getParameters().getBorder().setColor(Color.GRAY);
        generator.getParameters().getBorder().getWidth().setPixels(2);

        saveAndAssert(generator, "code128_styled.png", payload);
    }

    /**
     * Rotates the complete generated barcode image by 90 degrees.
     */
    @Test
    public void rotation_90_degrees() throws Exception {
        String payload = "ROTATE-ME";
        BarcodeGenerator generator = new BarcodeGenerator(EncodeTypes.CODE_128, payload);

        // Right-angle rotations are generally easier for scanners to read.
        generator.getParameters().setRotationAngle(90.0f);

        saveAndAssert(generator, "code128_rotate_90.png", payload);
    }

    /**
     * Generates a numeric Code 128 barcode that can be encoded efficiently with code set C.
     */
    @Test
    public void codeSet_numeric_uses_C() throws Exception {
        String payload = "123456789012";
        BarcodeGenerator generator = new BarcodeGenerator(EncodeTypes.CODE_128, payload);

        saveAndAssert(generator, "code128_numeric.png", payload);
    }

    /**
     * Generates mixed alphabetic and numeric content so automatic encoding can switch
     * between Code 128 code sets when beneficial.
     */
    @Test
    public void codeSet_mixed_auto_switch() throws Exception {
        String payload = "ABC1234567890def";
        BarcodeGenerator generator = new BarcodeGenerator(EncodeTypes.CODE_128, payload);

        saveAndAssert(generator, "code128_mixed.png", payload);
    }

    /**
     * Generates lowercase content, which is represented through Code 128 code set B.
     */
    @Test
    public void codeSet_lowercase_uses_B() throws Exception {
        String payload = "product-code";
        BarcodeGenerator generator = new BarcodeGenerator(EncodeTypes.CODE_128, payload);

        saveAndAssert(generator, "code128_lowercase.png", payload);
    }

    /**
     * Creates a compact Code 128 image by hiding human-readable text, reducing vertical
     * padding, and fitting the symbol into a fixed image bounding box.
     */
    @Test
    public void sizeControl() throws Exception {
        String payload = "SIZE-DEMO-123";
        BarcodeGenerator generator = new BarcodeGenerator(EncodeTypes.CODE_128, payload);

        generator.getParameters().getBarcode().getCodeTextParameters().setLocation(CodeLocation.NONE);
        generator.getParameters().getBarcode().getPadding().getTop().setPixels(1);
        generator.getParameters().getBarcode().getPadding().getBottom().setPixels(1);

        // In NEAREST mode, image dimensions define the bounding box. Explicit
        // XDimension and BarHeight values are therefore not configured here.
        generator.getParameters().setAutoSizeMode(AutoSizeMode.NEAREST);
        generator.getParameters().getImageWidth().setPixels(300);
        generator.getParameters().getImageHeight().setPixels(60);

        saveAndAssert(generator, "code128_tiny_no_text.png", payload);
    }

    /**
     * Saves a generated PNG, verifies that the file is non-empty, and confirms that
     * it can be recognized as Code 128 with the expected code text.
     */
    private void saveAndAssert(BarcodeGenerator generator,
                               String fileName,
                               String expectedCodeText) throws Exception {
        Path outputPath = Paths.get(FOLDER).resolve(fileName);
        Files.createDirectories(outputPath.getParent());

        generator.save(outputPath.toString(), BarCodeImageFormat.PNG);

        Assert.assertTrue(Files.exists(outputPath), "Output file must exist: " + outputPath);
        Assert.assertTrue(Files.size(outputPath) > 0, "Output file must be non-empty: " + outputPath);

        ExampleAssist.assertImageHasBarcodes(
                outputPath.toString(),
                1,
                List.of(ExampleAssist.expected(DecodeType.CODE_128, expectedCodeText))
        );
    }
}
