package com.aspose.barcode.guide.generation;

import com.aspose.barcode.generation.*;
import com.aspose.barcode.guide.common.ExampleAssist;
import com.aspose.barcode.guide.common.LicenseAssist;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.awt.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * TestNG suite for Code 128 generation examples used in the Developer Guide.
 * All tests write PNGs into the 'code128' resource folder.
 */
public class GenerateCode128 {

    private static final String folder = ExampleAssist.getResourceFolderPath("code128");

    @BeforeClass
    public void setUp() {
        LicenseAssist.setupLicense();
    }

    // --- Quick Start ---
    @Test
    public void quickStart() throws Exception {
        BarcodeGenerator gen = new BarcodeGenerator(EncodeTypes.CODE_128, "ASPOSE COMPANY");
        saveAndAssert(gen, "code128_quick.png");
    }

    // --- Size control (X-Dimension & Height) ---
    @Test
    public void sizeControl_Pixels_and_Millimeters() throws Exception {
        BarcodeGenerator gen = new BarcodeGenerator(EncodeTypes.CODE_128, "ABC123");

        // X-Dimension
        gen.getParameters().getBarcode().getXDimension().setPixels(2);
        gen.getParameters().getBarcode().getXDimension().setMillimeters(0.30f);

        // Height
        gen.getParameters().getBarcode().getBarHeight().setPixels(60);
        gen.getParameters().getBarcode().getBarHeight().setMillimeters(15.0f);

        saveAndAssert(gen, "code128_sized.png");
    }

    // --- Human-readable text (CodeText) ---
    @Test
    public void codetext_Below_with_Font_and_Spacing() throws Exception {
        BarcodeGenerator gen = new BarcodeGenerator(EncodeTypes.CODE_128, "PRODUCT-789");

        // Location/Font/Spacing (if available in your build)
        gen.getParameters().getBarcode().getCodeTextParameters().setLocation(CodeLocation.BELOW);
        gen.getParameters().getBarcode().getCodeTextParameters().getFont().setFamilyName("Arial");
        gen.getParameters().getBarcode().getCodeTextParameters().getFont().setStyle(Font.PLAIN);
        gen.getParameters().getBarcode().getCodeTextParameters().getFont().getSize().setPoint(30);
        gen.getParameters().getBarcode().getCodeTextParameters().setColor(Color.RED);
        gen.getParameters().getBarcode().getCodeTextParameters().getSpace().setPoint(2);

        saveAndAssert(gen, "code128_with_text.png");
    }

    // --- Resolution (print quality) ---
    @Test
    public void printQuality_300dpi() throws Exception {
        BarcodeGenerator gen = new BarcodeGenerator(EncodeTypes.CODE_128, "PRINT-READY");
        gen.getParameters().setResolution(300.0f);
        saveAndAssert(gen, "code128_300dpi.png");
    }

    // --- Colors / Padding / Border ---
    @Test
    public void styling_Colors_Padding_Border() throws Exception {
        BarcodeGenerator gen = new BarcodeGenerator(EncodeTypes.CODE_128, "STYLE-01");

        gen.getParameters().setBackColor(Color.WHITE);
        gen.getParameters().getBarcode().setBarColor(Color.BLACK);

        gen.getParameters().getBarcode().getPadding().getLeft().setPixels(12);
        gen.getParameters().getBarcode().getPadding().getRight().setPixels(12);
        gen.getParameters().getBarcode().getPadding().getTop().setPixels(6);
        gen.getParameters().getBarcode().getPadding().getBottom().setPixels(6);

        gen.getParameters().getBorder().setVisible(true);
        gen.getParameters().getBorder().getWidth().setPixels(2);

        saveAndAssert(gen, "code128_style.png");
    }

    // --- Rotation ---
    @Test
    public void rotation_90_degrees() throws Exception {
        BarcodeGenerator gen = new BarcodeGenerator(EncodeTypes.CODE_128, "ROTATE");
        gen.getParameters().setRotationAngle(90.0f);
        saveAndAssert(gen, "code128_rotate_90.png");
    }

    // --- GS1-128 (EAN-128) ---
    @Test
    public void gs1_128_AI() throws Exception {
        BarcodeGenerator gen = new BarcodeGenerator(
                EncodeTypes.GS_1_CODE_128,
                "(01)09501101530008(17)251231(10)BATCH-42"
        );
        saveAndAssert(gen, "gs1_128.png");
    }

    // --- Subset behavior smoke tests ---
    @Test
    public void subset_numeric_uses_C() throws Exception {
        BarcodeGenerator gen = new BarcodeGenerator(EncodeTypes.CODE_128, "123456789012");
        saveAndAssert(gen, "code128_subsetC_numeric.png");
    }

    @Test
    public void subset_mixed_auto_switch() throws Exception {
        BarcodeGenerator gen = new BarcodeGenerator(EncodeTypes.CODE_128, "ABC123def456");
        saveAndAssert(gen, "code128_subset_auto.png");
    }

    // ----------------- Helpers -----------------
    private void saveAndAssert(BarcodeGenerator gen, String fileName) throws Exception {
        Path out = Paths.get(folder).resolve(fileName);
        Files.createDirectories(out.getParent());
        gen.save(out.toString(), BarCodeImageFormat.PNG);
        Assert.assertTrue(Files.exists(out), "Output file must exist: " + out);
        Assert.assertTrue(Files.size(out) > 0, "Output file must be non-empty: " + out);
    }
}
