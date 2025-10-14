package com.aspose.barcode.guide.generation;

import com.aspose.barcode.generation.*;
import com.aspose.barcode.guide.common.ExampleAssist;
import com.aspose.barcode.guide.common.LicenseAssist;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * TestNG suite for GS1-128 (UCC/EAN-128) generation examples used in the Developer Guide.
 * All tests write PNGs into the 'gs1-128' resource folder.
 *
 * Notes:
 * - Use EncodeTypes.GS_1_CODE_128 for GS1-128.
 * - When you pass AIs in parentheses (e.g., "(01)...(17)..."), FNC1 is inserted automatically where required.
 */
public class GenerateGS1_128 {

    private static final String folder = ExampleAssist.getOrCreateResourceFolderPath("gs1-128");

    @BeforeClass
    public void setUp() {
        LicenseAssist.setupLicense();
    }

    // --- Quick Start ---
    @Test
    public void quickStart_basic() throws Exception {
        BarcodeGenerator gen = new BarcodeGenerator(
                EncodeTypes.GS_1_CODE_128,
                "(01)09501101530008(17)251231(10)BATCH-42"
        );

        // Print defaults
        gen.getParameters().getBarcode().getXDimension().setMillimeters(0.33f);
        gen.getParameters().getBarcode().getBarHeight().setMillimeters(15.0f);
        gen.getParameters().setResolution(300.0f);

        saveAndAssert(gen, "gs1_128.png");
    }

    // --- Common AIs (GTIN + EXP + LOT + SERIAL) ---
    @Test
    public void gs1_commonAIs_complete() throws Exception {
        String gs1 = "(01)09512345678900"  // GTIN (14)
                + "(17)260630"          // Expiration YYMMDD
                + "(10)LOT2025A"        // Lot (variable)
                + "(21)SERIAL123456";   // Serial (variable)
        BarcodeGenerator gen = new BarcodeGenerator(EncodeTypes.GS_1_CODE_128, gs1);

        gen.getParameters().getBarcode().getXDimension().setMillimeters(0.33f);
        gen.getParameters().getBarcode().getBarHeight().setMillimeters(16.0f);
        gen.getParameters().setResolution(300.0f);

        // Quiet zones (≈10× X-Dim)
        gen.getParameters().getBarcode().getPadding().getLeft().setMillimeters(3.3f);
        gen.getParameters().getBarcode().getPadding().getRight().setMillimeters(3.3f);

        // Show human-readable text below
        gen.getParameters().getBarcode().getCodeTextParameters().setLocation(CodeLocation.BELOW);
        gen.getParameters().getBarcode().getCodeTextParameters().getFont().setFamilyName("Arial");
        gen.getParameters().getBarcode().getCodeTextParameters().getFont().getSize().setPoint(9);

        saveAndAssert(gen, "gs1_128_complete.png");
    }

    // --- Variable-length AI followed by another AI (FNC1 auto-handled) ---
    @Test
    public void gs1_variableLength_followedBy_nextAI() throws Exception {
        // (10) is variable-length; when followed by another AI, FNC1 is needed.
        // The bracketed format causes the engine to insert FNC1 automatically.
        String gs1 = "(01)09501101530008"
                + "(10)LOT-ABC-999"   // variable length
                + "(21)SN00004567";   // next AI -> FNC1 auto
        BarcodeGenerator gen = new BarcodeGenerator(EncodeTypes.GS_1_CODE_128, gs1);

        gen.getParameters().getBarcode().getXDimension().setMillimeters(0.33f);
        gen.getParameters().getBarcode().getBarHeight().setMillimeters(15.0f);
        gen.getParameters().setResolution(300.0f);

        saveAndAssert(gen, "gs1_128_varlen.png");
    }

    // --- Print layout / box-driven sizing example ---
    @Test
    public void gs1_printLayout_boxDriven() throws Exception {
        String gs1 = "(01)09501101530008(17)251231(10)BATCH-42";
        BarcodeGenerator gen = new BarcodeGenerator(EncodeTypes.GS_1_CODE_128, gs1);

        // Geometry
        gen.getParameters().getBarcode().getXDimension().setMillimeters(0.33f);
        gen.getParameters().getBarcode().getBarHeight().setMillimeters(15.0f);

        // Target bitmap box — enough to avoid "not enough space"
        gen.getParameters().getImageWidth().setPixels(500);
        gen.getParameters().getImageHeight().setPixels(200);
        gen.getParameters().setAutoSizeMode(AutoSizeMode.NEAREST);

        gen.getParameters().setResolution(300.0f);
        saveAndAssert(gen, "gs1_128_box.png");
    }

    // --- Minimalist layout (no code text, tight vertical padding) ---
    @Test
    public void gs1_minimal_noText() throws Exception {
        String gs1 = "(01)09501101530008(17)251231(10)BATCH-42";
        BarcodeGenerator gen = new BarcodeGenerator(EncodeTypes.GS_1_CODE_128, gs1);

        gen.getParameters().getBarcode().getXDimension().setMillimeters(0.33f);
        gen.getParameters().getBarcode().getBarHeight().setMillimeters(12.0f); // минимально допустимо для печати
        gen.getParameters().setResolution(300.0f);

        // Отключаем код-текст, чтобы сэкономить высоту
        gen.getParameters().getBarcode().getCodeTextParameters().setLocation(CodeLocation.NONE);

        // Урезаем верх/низ тихие зоны
        gen.getParameters().getBarcode().getPadding().getTop().setMillimeters(1.0f);
        gen.getParameters().getBarcode().getPadding().getBottom().setMillimeters(1.0f);

        saveAndAssert(gen, "gs1_128_minimal.png");
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
