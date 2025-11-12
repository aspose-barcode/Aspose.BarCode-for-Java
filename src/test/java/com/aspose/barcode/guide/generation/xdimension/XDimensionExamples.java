package com.aspose.barcode.guide.generation.xdimension;

import com.aspose.barcode.generation.AutoSizeMode;
import com.aspose.barcode.generation.BarCodeImageFormat;
import com.aspose.barcode.generation.BarcodeGenerator;
import com.aspose.barcode.generation.EncodeTypes;

import com.aspose.barcode.guide.common.ExampleAssist;
import com.aspose.barcode.guide.common.LicenseAssist;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * X-Dimension focused tests for Aspose.BarCode for Java.
 *
 * What is X-Dimension:
 * - For 1D barcodes: width of the narrowest bar/space.
 * - For 2D barcodes: module (cell) size.
 *
 * Why it matters:
 * - Controls density (how compact the symbol is) and final image size.
 * - Directly influences print/scanner reliability: too small = blur/merge, too large = big labels.
 *
 * Tips:
 * - Start around 0.30–0.40 mm for print labels.
 * - For 2D codes, 0.40–0.60 mm modules are usually safe.
 * - Offset/inkjet may widen bars; combine with BarWidthReduction.
 */
public class XDimensionExamples {

    // Where test images will be saved (e.g., /.../x_dimension/)
    private static final String folder = ExampleAssist.getResourceFolderPath("generation","x_dimension");

    @BeforeClass
    public void setUp() {
        // Apply license if available; safe to call in CI as well
        LicenseAssist.setupLicense();
    }

    // ---------------------------------------------------------------------
    // 1) BASIC: Set X-Dimension in millimeters for a linear (1D) symbology
    // ---------------------------------------------------------------------
    @Test
    public void xdim_Code128_in_millimeters() throws Exception {
        // Code 128 with human-friendly payload
        BarcodeGenerator gen = new BarcodeGenerator(EncodeTypes.CODE_128, "ASPOSE-12345");

        // Set X-Dimension to 0.30 mm:
        // - This configures the narrowest bar width
        // - A typical, safe starting point for thermal/laser printers
        gen.getParameters().getBarcode().getXDimension().setMillimeters(0.30f);

        // Bar height (1D only) in mm — affects the vertical size of bars
        gen.getParameters().getBarcode().getBarHeight().setMillimeters(12.0f);

        saveAndAssert(gen, "code128_xdim_0_30mm.png");
    }

    // ---------------------------------------------------------------------
    // 2) BASIC: Set X-Dimension in pixels for a 2D symbology (DataMatrix)
    // ---------------------------------------------------------------------
    @Test
    public void xdim_DataMatrix_in_pixels() throws Exception {
        // 2D symbols (DataMatrix, QR, etc.) use module (cell) size as X-Dimension
        BarcodeGenerator gen = new BarcodeGenerator(EncodeTypes.DATA_MATRIX, "ASPOSE");

        // Set module size to 8 pixels
        // For screen/demo outputs pixels are convenient; for print prefer millimeters
        gen.getParameters().getBarcode().getXDimension().setPixels(8);

        saveAndAssert(gen, "datamatrix_xdim_8px.png");
    }

    // ---------------------------------------------------------------------
    // 3) EFFECT: Compare density — small vs large X-Dimension (1D)
    //     - Smaller X → tighter symbol → more compact but may be harder to scan
    //     - Larger X → bigger symbol → easier to scan but takes more space
    // ---------------------------------------------------------------------
    @Test
    public void xdim_Code128_density_comparison() throws Exception {
        BarcodeGenerator gen = new BarcodeGenerator(EncodeTypes.CODE_128, "DENSITY-CHECK-1234567890");

        // Case A: compact (0.25 mm)
        gen.getParameters().getBarcode().getXDimension().setMillimeters(0.25f);
        gen.getParameters().getBarcode().getBarHeight().setMillimeters(12.0f);
        saveAndAssert(gen, "code128_xdim_0_25mm.png");

        // Case B: larger (0.50 mm)
        gen.getParameters().getBarcode().getXDimension().setMillimeters(0.50f);
        // Keep same bar height for a fair visual comparison
        gen.getParameters().getBarcode().getBarHeight().setMillimeters(12.0f);
        saveAndAssert(gen, "code128_xdim_0_50mm.png");
    }

    // ---------------------------------------------------------------------
    // 4) INTERPLAY: AutoSizeMode vs X-Dimension
    //     - NONE: size derives from geometry (X-Dimension, paddings, etc.)
    //     - INTERPOLATION: engine strictly fits ImageWidth/Height (may distort)
    //     - NEAREST: fits size while trying to keep proportions (less distortion)
    // ---------------------------------------------------------------------
    @Test
    public void xdim_AutoSizeModes_effects() throws Exception {
        BarcodeGenerator gen = new BarcodeGenerator(EncodeTypes.DATA_MATRIX, "ASPOSE");

        // NONE: ImageWidth/Height are ignored; size comes from X-Dimension & layout
        gen.getParameters().setAutoSizeMode(AutoSizeMode.NONE);
        gen.getParameters().getImageWidth().setPixels(300);   // ignored in NONE
        gen.getParameters().getImageHeight().setPixels(300);  // ignored in NONE
        gen.getParameters().getBarcode().getXDimension().setPixels(6);
        saveAndAssert(gen, "dm_autosize_NONE_xdim6.png");

        // INTERPOLATION: engine fits exactly the requested bitmap size,
        // potentially distorting proportions (OK for high DPI bitmaps)
        gen.getParameters().setAutoSizeMode(AutoSizeMode.INTERPOLATION);
        gen.getParameters().getImageWidth().setPixels(300);
        gen.getParameters().getImageHeight().setPixels(300);
        gen.getParameters().getBarcode().getXDimension().setPixels(6); // mostly ignored
        saveAndAssert(gen, "dm_autosize_INTERPOLATION_300x300.png");

        // NEAREST: tries to fit size with minimal distortion (prefers valid module geometry)
        gen.getParameters().setAutoSizeMode(AutoSizeMode.NEAREST);
        gen.getParameters().getImageWidth().setPixels(300);
        gen.getParameters().getImageHeight().setPixels(300);
        gen.getParameters().getBarcode().getXDimension().setPixels(6);
        saveAndAssert(gen, "dm_autosize_NEAREST_300x300.png");
    }

    // ---------------------------------------------------------------------
    // 5) PRINTING: Combine X-Dimension with BarWidthReduction (BWR)
    //     - BWR narrows bars/modules to compensate ink spread (offset/inkjet)
    //     - Has no effect on laser printers (no ink spreading)
    // ---------------------------------------------------------------------
    @Test
    public void xdim_with_BarWidthReduction_Code128_and_DataMatrix() throws Exception {
        // ---- Code 128 with and without BWR
        BarcodeGenerator gen1D = new BarcodeGenerator(EncodeTypes.CODE_128, "ASPOSE");
        gen1D.getParameters().getBarcode().getXDimension().setMillimeters(0.30f); // base geometry
        gen1D.getParameters().getBarcode().getBarHeight().setMillimeters(12.0f);

        // No reduction
        gen1D.getParameters().getBarcode().getBarWidthReduction().setPixels(0);
        saveAndAssert(gen1D, "code128_bwr_0.png");

        // Reduce bars by 4 pixels (visible on low-DPI demo images; in print choose by printer's BWR table)
        gen1D.getParameters().getBarcode().getBarWidthReduction().setPixels(4);
        saveAndAssert(gen1D, "code128_bwr_4.png");

        // ---- DataMatrix with and without BWR
        BarcodeGenerator gen2D = new BarcodeGenerator(EncodeTypes.DATA_MATRIX, "ASPOSE");
        gen2D.getParameters().getBarcode().getXDimension().setPixels(10); // module size

        // No reduction
        gen2D.getParameters().getBarcode().getBarWidthReduction().setPixels(0);
        saveAndAssert(gen2D, "datamatrix_bwr_0.png");

        // Reduce modules by 4 pixels (shrinks black modules slightly)
        gen2D.getParameters().getBarcode().getBarWidthReduction().setPixels(4);
        saveAndAssert(gen2D, "datamatrix_bwr_4.png");
    }

    // ---------------------------------------------------------------------
    // Helpers
    // ---------------------------------------------------------------------
    private void saveAndAssert(BarcodeGenerator gen, String fileName) throws Exception {
        Path out = Paths.get(folder).resolve(fileName);
        Files.createDirectories(out.getParent());

        gen.save(out.toString(), BarCodeImageFormat.PNG);

        Assert.assertTrue(Files.exists(out), "Output file must exist: " + out);
        Assert.assertTrue(Files.size(out) > 0, "Output file must be non-empty: " + out);
    }
}
