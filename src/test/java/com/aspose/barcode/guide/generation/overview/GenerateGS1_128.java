package com.aspose.barcode.guide.generation.overview;

import com.aspose.barcode.barcoderecognition.DecodeType;
import com.aspose.barcode.generation.*;
import com.aspose.barcode.guide.common.ExampleAssist;
import com.aspose.barcode.guide.common.LicenseAssist;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * TestNG suite for GS1-128 generation examples used in the Developer Guide.
 * All tests write PNG files into the {@code gs1-128} resource folder and verify
 * that the generated barcode can be recognized as GS1 Code 128 with the expected data.
 *
 * <p>Application Identifiers must be supplied in parenthesized form. The generator
 * inserts the required GS1 field separator after a non-final variable-length field.</p>
 */
public class GenerateGS1_128 {

    private static final String FOLDER =
            ExampleAssist.getOrCreateResourceFolderPath(
                    "generation",
                    "generate-gs1-128"
            );

    @BeforeClass
    public void setUp() {
        LicenseAssist.setupLicense();
    }

    /**
     * Generates a basic GS1-128 barcode containing GTIN, expiration date, and lot data.
     * The dimensions are examples only and must be checked against the applicable GS1 specification.
     */
    @Test
    public void quickStart_basic() throws Exception {
        String gs1CodeText = "(01)09501101530003(17)251231(10)BATCH-42";
        BarcodeGenerator generator = new BarcodeGenerator(EncodeTypes.GS_1_CODE_128, gs1CodeText);

        // Example dimensions; verify them against the applicable GS1 specification.
        generator.getParameters().getBarcode().getXDimension().setMillimeters(0.33f);
        generator.getParameters().getBarcode().getBarHeight().setMillimeters(15.0f);
        generator.getParameters().setResolution(300.0f);

        // Reserve horizontal quiet-zone space with a small margin above 10X.
        generator.getParameters().getBarcode().getPadding().getLeft().setMillimeters(3.5f);
        generator.getParameters().getBarcode().getPadding().getRight().setMillimeters(3.5f);

        saveAndAssert(generator, "gs1_128.png", gs1CodeText);
    }

    /**
     * Generates a GS1-128 symbol with common fixed-length and variable-length
     * Application Identifiers and configures human-readable text manually.
     */
    @Test
    public void gs1_commonAIs_complete() throws Exception {
        String gs1CodeText = "(01)09512345678901"
                + "(17)260630"
                + "(10)LOT2025A"
                + "(21)SERIAL123456";

        BarcodeGenerator generator = new BarcodeGenerator(EncodeTypes.GS_1_CODE_128, gs1CodeText);

        // Example dimensions; verify them against the applicable GS1 specification.
        generator.getParameters().getBarcode().getXDimension().setMillimeters(0.33f);
        generator.getParameters().getBarcode().getBarHeight().setMillimeters(16.0f);
        generator.getParameters().setResolution(300.0f);

        // Reserve horizontal quiet-zone space with a small margin above 10X.
        generator.getParameters().getBarcode().getPadding().getLeft().setMillimeters(3.5f);
        generator.getParameters().getBarcode().getPadding().getRight().setMillimeters(3.5f);

        CodetextParameters codeTextParameters = generator.getParameters()
                .getBarcode()
                .getCodeTextParameters();

        codeTextParameters.setLocation(CodeLocation.BELOW);
        codeTextParameters.setFontMode(FontMode.MANUAL);
        codeTextParameters.getFont().setFamilyName("Arial");
        codeTextParameters.getFont().getSize().setPoint(9);

        saveAndAssert(generator, "gs1_128_complete.png", gs1CodeText);
    }

    /**
     * Demonstrates automatic insertion of the required GS1 field separator after
     * a non-final variable-length AI (10) when another AI follows.
     */
    @Test
    public void gs1_variableLength_followedBy_nextAI() throws Exception {
        String gs1CodeText = "(01)09501101530003"
                + "(10)LOT-ABC-999"
                + "(21)SN00004567";

        BarcodeGenerator generator = new BarcodeGenerator(EncodeTypes.GS_1_CODE_128, gs1CodeText);

        // Example dimensions; verify them against the applicable GS1 specification.
        generator.getParameters().getBarcode().getXDimension().setMillimeters(0.33f);
        generator.getParameters().getBarcode().getBarHeight().setMillimeters(15.0f);
        generator.getParameters().setResolution(300.0f);

        // Preserve horizontal quiet-zone space.
        generator.getParameters().getBarcode().getPadding().getLeft().setMillimeters(3.5f);
        generator.getParameters().getBarcode().getPadding().getRight().setMillimeters(3.5f);

        saveAndAssert(generator, "gs1_128_varlen.png", gs1CodeText);
    }

    /**
     * Fits a GS1-128 symbol into a fixed bitmap area. In {@link AutoSizeMode#NEAREST},
     * the effective X-dimension and bar height are calculated from the target box.
     */
    @Test
    public void gs1_printLayout_boxDriven() throws Exception {
        String gs1CodeText = "(01)09501101530003(17)251231(10)BATCH-42";
        BarcodeGenerator generator = new BarcodeGenerator(EncodeTypes.GS_1_CODE_128, gs1CodeText);

        generator.getParameters().setResolution(300.0f);
        generator.getParameters().getImageWidth().setPixels(500);
        generator.getParameters().getImageHeight().setPixels(200);
        generator.getParameters().setAutoSizeMode(AutoSizeMode.NEAREST);

        Path outputPath = saveAndAssert(generator, "gs1_128_box.png", gs1CodeText);
        assertImageDimensions(outputPath, 500, 200);
    }

    /**
     * Creates a compact GS1-128 image by hiding human-readable text and reducing
     * vertical padding while preserving horizontal quiet-zone space.
     */
    @Test
    public void gs1_minimal_noText() throws Exception {
        String gs1CodeText = "(01)09501101530003(17)251231(10)BATCH-42";
        BarcodeGenerator generator = new BarcodeGenerator(EncodeTypes.GS_1_CODE_128, gs1CodeText);

        // Example dimensions; verify them against the applicable GS1 specification.
        generator.getParameters().getBarcode().getXDimension().setMillimeters(0.33f);
        generator.getParameters().getBarcode().getBarHeight().setMillimeters(12.0f);
        generator.getParameters().setResolution(300.0f);

        // Disable human-readable text to reduce image height.
        generator.getParameters().getBarcode().getCodeTextParameters().setLocation(CodeLocation.NONE);

        // Reduce vertical padding.
        generator.getParameters().getBarcode().getPadding().getTop().setMillimeters(1.0f);
        generator.getParameters().getBarcode().getPadding().getBottom().setMillimeters(1.0f);

        // Preserve horizontal quiet-zone space.
        generator.getParameters().getBarcode().getPadding().getLeft().setMillimeters(3.5f);
        generator.getParameters().getBarcode().getPadding().getRight().setMillimeters(3.5f);

        saveAndAssert(generator, "gs1_128_minimal.png", gs1CodeText);
    }

    /**
     * Saves a generated PNG, verifies that the file is non-empty, and confirms that
     * it can be recognized as GS1 Code 128 with the expected structured code text.
     *
     * @return path to the generated image
     */
    private Path saveAndAssert(BarcodeGenerator generator,
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
                List.of(ExampleAssist.expected(DecodeType.GS_1_CODE_128, expectedCodeText))
        );

        return outputPath;
    }

    /**
     * Verifies the exact raster dimensions of a generated image.
     */
    private void assertImageDimensions(Path imagePath,
                                       int expectedWidth,
                                       int expectedHeight) throws Exception {
        BufferedImage image = ImageIO.read(imagePath.toFile());
        Assert.assertNotNull(image, "Generated image must be readable: " + imagePath);
        Assert.assertEquals(image.getWidth(), expectedWidth, "Unexpected image width: " + imagePath);
        Assert.assertEquals(image.getHeight(), expectedHeight, "Unexpected image height: " + imagePath);
    }
}
