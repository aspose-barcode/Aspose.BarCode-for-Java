package com.aspose.barcode.guide.generation;

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

/**
 * TestNG suite for Code 128 generation examples used in the Developer Guide.
 * All tests write PNGs into the 'code128' resource folder.
 *
 * NOTE:
 * - GS1-128 examples are moved to a separate test class/file.
 * - Font configured via getters (safe for different SDK builds).
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

    // --- Human-readable text (CodeText) ---
    @Test
    public void codetext_Below_with_Font_and_Spacing() throws Exception {
        BarcodeGenerator gen = new BarcodeGenerator(EncodeTypes.CODE_128, "PRODUCT-789");

        // Location below bars
        gen.getParameters().getBarcode().getCodeTextParameters().setLocation(CodeLocation.BELOW);

        // Safe font configuration via getters (no new FontUnit(...))
        gen.getParameters().getBarcode().getCodeTextParameters().getFont().setFamilyName("Arial");
        gen.getParameters().getBarcode().getCodeTextParameters().getFont().setStyle(Font.PLAIN);
        gen.getParameters().getBarcode().getCodeTextParameters().getFont().getSize().setPoint(12);

        // Spacing between bars and text
        gen.getParameters().getBarcode().getCodeTextParameters().getSpace().setPoint(2);

        // Optional text color (keep default black if you prefer)
        // gen.getParameters().getBarcode().getCodeTextParameters().setColor(Color.RED);

        // If your build has TextAlignment, you could use:
        // gen.getParameters().getBarcode().getCodeTextParameters().setAlignment(TextAlignment.CENTER);

        saveAndAssert(gen, "code128_with_text.png");
    }

    // --- Resolution (print quality) ---
    @Test
    public void printQuality_300dpi() throws Exception {
        BarcodeGenerator gen = new BarcodeGenerator(EncodeTypes.CODE_128, "PRINT-READY-001");

        // 300 DPI is standard for label printing
        gen.getParameters().setResolution(300.0f);

        // Optional image bounding box (NEAREST tries to fit geometry without distortion)
        gen.getParameters().getImageWidth().setPixels(400);
        gen.getParameters().getImageHeight().setPixels(150);
        gen.getParameters().setAutoSizeMode(AutoSizeMode.NEAREST);

        saveAndAssert(gen, "code128_300dpi.png");
    }

    // --- Colors / Padding / Border ---
    @Test
    public void styling_Colors_Padding_Border() throws Exception {
        BarcodeGenerator gen = new BarcodeGenerator(EncodeTypes.CODE_128, "STYLE-2025");

        // Colors
        gen.getParameters().setBackColor(Color.WHITE);
        gen.getParameters().getBarcode().setBarColor(Color.BLACK);

        // Quiet zones (blank margins)
        gen.getParameters().getBarcode().getPadding().getLeft().setPixels(12);
        gen.getParameters().getBarcode().getPadding().getRight().setPixels(12);
        gen.getParameters().getBarcode().getPadding().getTop().setPixels(6);
        gen.getParameters().getBarcode().getPadding().getBottom().setPixels(6);

        // Optional visible border
        gen.getParameters().getBorder().setVisible(true);
        gen.getParameters().getBorder().setColor(Color.GRAY);
        gen.getParameters().getBorder().getWidth().setPixels(2);
        // If BorderDashStyle is available:
        // gen.getParameters().getBorder().setDashStyle(BorderDashStyle.SOLID);

        saveAndAssert(gen, "code128_styled.png");
    }

    // --- Rotation ---
    @Test
    public void rotation_90_degrees() throws Exception {
        BarcodeGenerator gen = new BarcodeGenerator(EncodeTypes.CODE_128, "ROTATE-ME");
        gen.getParameters().setRotationAngle(90.0f); // 0, 90, 180, 270 supported
        saveAndAssert(gen, "code128_rotate_90.png");
    }

    // --- Subset behavior smoke tests ---
    @Test
    public void subset_numeric_uses_C() throws Exception {
        // Pure numeric → engine should switch to subset C automatically
        BarcodeGenerator gen = new BarcodeGenerator(EncodeTypes.CODE_128, "123456789012");
        saveAndAssert(gen, "code128_numeric.png");
    }

    @Test
    public void subset_mixed_auto_switch() throws Exception {
        // Mixed content → auto switching between A/B/C
        BarcodeGenerator gen = new BarcodeGenerator(EncodeTypes.CODE_128, "ABC123def456");
        saveAndAssert(gen, "code128_mixed.png");
    }

    @Test
    public void subset_lowercase_uses_B() throws Exception {
        // Lowercase and punctuation are best served by subset B
        BarcodeGenerator gen = new BarcodeGenerator(EncodeTypes.CODE_128, "product-code");
        saveAndAssert(gen, "code128_lowercase.png");
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
