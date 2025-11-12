package com.aspose.barcode.guide.generation.visual_parameters;

import com.aspose.barcode.barcoderecognition.BarCodeReader;
import com.aspose.barcode.barcoderecognition.BarCodeResult;
import com.aspose.barcode.barcoderecognition.DecodeType;
import com.aspose.barcode.generation.*;
import com.aspose.barcode.guide.common.ExampleAssist;
import com.aspose.barcode.guide.common.LicenseAssist;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

import static com.aspose.barcode.guide.common.ExampleAssist.assertImageHasBarcodes;
import static com.aspose.barcode.guide.common.ExampleAssist.expected;

/**
 * Examples focused on customizing barcode physical/visual size:
 * <ul>
 *   <li>Controlling {@code XDimension} (module/line width)</li>
 *   <li>Controlling {@code BarHeight} for 1D codes</li>
 *   <li>Auto size modes behavior</li>
 *   <li>Symbology-specific size parameter: Australian Post short bar height</li>
 * </ul>
 *
 * <p>Conventions:</p>
 * <ul>
 *   <li>No try-with-resources; do not close/Dispose readers/generators explicitly.</li>
 *   <li>Deterministic output paths under {@code src/test/resources}.</li>
 *   <li>Use {@link ExampleAssist} helpers (path combining, assertions).</li>
 * </ul>
 */
public class CustomizingSizeExample {

    private static final String FOLDER =
            ExampleAssist.getOrCreateResourceFolderPath("generation", "visual_parameters","customizing_size");

    private static final String FILE_C128_XDIM_PX      = "c128_xdim_px.png";
    private static final String FILE_C128_BAR_MM_300DPI= "c128_bar_mm_300dpi.png";
    private static final String FILE_EAN13_AUTOSIZE_NONE= "ean13_autosize_none.png";
    private static final String FILE_EAN13_AUTOSIZE_NEAR= "ean13_autosize_nearest.png";
    private static final String FILE_AUSPOST_SHORTBAR   = "auspost_shortbar.png";

    @BeforeClass
    public void setUp() throws Exception {
        LicenseAssist.setupLicense();
    }

    /**
     * Demonstrates controlling {@code XDimension} directly in pixels (no DPI involved).
     *
     * <p><b>Goal:</b> Show that using {@code setPixels(...)} sets the smallest bar/module width
     * to an exact pixel value and is independent of DPI conversion.</p>
     *
     * <p><b>What we do:</b></p>
     * <ul>
     *   <li>CODE_128 with text "XDIM-PX"</li>
     *   <li>{@code XDimension = 3 px}</li>
     *   <li>Reasonable canvas and padding to avoid clipping</li>
     * </ul>
     *
     * <p><b>Expected:</b> 1 barcode of type CODE_128 with the specified code text.</p>
     */
    @Test
    public void xdimensionPixels_forCode128() throws Exception {
        BarcodeGenerator gen = new BarcodeGenerator(EncodeTypes.CODE_128, "XDIM-PX");

        // Canvas (optional but keeps images consistent)
        gen.getParameters().getImageWidth().setPixels(500);
        gen.getParameters().getImageHeight().setPixels(180);

        // XDimension in pixels: exact raster control (DPI is not used here)
        Unit xdim = gen.getParameters().getBarcode().getXDimension();
        xdim.setPixels(3.0f);
        Assert.assertEquals((int) xdim.getPixels(), 3, "XDimension must be exactly 3 px");

        // Bar height (for visibility) and padding
        gen.getParameters().getBarcode().getBarHeight().setPixels(100);
        gen.getParameters().getBarcode().getPadding().getLeft().setPixels(12);
        gen.getParameters().getBarcode().getPadding().getRight().setPixels(12);
        gen.getParameters().getBarcode().getPadding().getTop().setPixels(8);
        gen.getParameters().getBarcode().getPadding().getBottom().setPixels(8);

        String fullPath = ExampleAssist.pathCombine(FOLDER, FILE_C128_XDIM_PX);
        gen.save(fullPath, BarCodeImageFormat.PNG);
        ExampleAssist.assertFileCreated(fullPath);

        assertImageHasBarcodes(
                fullPath,
                1,
                List.of(expected(DecodeType.CODE_128, "XDIM-PX"))
        );
    }

    /**
     * Demonstrates controlling 1D bar height in millimeters with explicit DPI to get predictable pixels.
     *
     * <p><b>Goal:</b> Set physical bar height and verify approximate pixel conversion via DPI.</p>
     *
     * <p><b>What we do:</b></p>
     * <ul>
     *   <li>CODE_128 with text "BAR-MM"</li>
     *   <li>{@code BarHeight = 12 mm} at {@code 300 dpi} → ≈ 142 px</li>
     *   <li>{@code XDimension = 0.5 mm} at {@code 300 dpi} → ≈ 6 px</li>
     * </ul>
     *
     * <p><b>Expected:</b> 1 barcode of type CODE_128 with the specified code text, and
     * pixel values in a tolerant range.</p>
     */
    @Test
    public void barHeightMillimeters_at300dpi() throws Exception {
        BarcodeGenerator gen = new BarcodeGenerator(EncodeTypes.CODE_128, "BAR-MM");

        Unit barH = gen.getParameters().getBarcode().getBarHeight();
        Unit xdim = gen.getParameters().getBarcode().getXDimension();

        // Use 300 dpi for physical → pixel conversion on these units
        barH.updateResolution(300f);
        xdim.updateResolution(300f);

        barH.setMillimeters(12.0f);  // ~142 px at 300 dpi
        xdim.setMillimeters(0.5f);   // ~6 px at 300 dpi

        float barPx  = barH.getPixels();
        float xdimPx = xdim.getPixels();
        Assert.assertTrue(Math.abs(barPx - 142f) <= 3f, "Bar height px ≈ 142 ± 3, got " + barPx);
        Assert.assertTrue(Math.abs(xdimPx - 6f)  <= 1f, "XDimension px ≈ 6 ± 1, got " + xdimPx);

        // Generous canvas/padding to avoid clipping
        gen.getParameters().getImageWidth().setPixels(500);
        gen.getParameters().getImageHeight().setPixels(220);
        gen.getParameters().getBarcode().getPadding().getLeft().setPixels(16);
        gen.getParameters().getBarcode().getPadding().getRight().setPixels(16);
        gen.getParameters().getBarcode().getPadding().getTop().setPixels(10);
        gen.getParameters().getBarcode().getPadding().getBottom().setPixels(10);

        String fullPath = ExampleAssist.pathCombine(FOLDER, FILE_C128_BAR_MM_300DPI);
        gen.save(fullPath, BarCodeImageFormat.PNG);
        ExampleAssist.assertFileCreated(fullPath);

        assertImageHasBarcodes(
                fullPath,
                1,
                List.of(expected(DecodeType.CODE_128, "BAR-MM"))
        );
    }

    /**
     * Shows the difference between {@code AutoSizeMode.NONE} (no automatic resampling)
     * and {@code AutoSizeMode.NEAREST} (engine may pick nearest pixel sizes to fit canvas).
     *
     * <p><b>Goal:</b> Demonstrate that auto-size mode can influence effective layout and rasterization,
     * while keeping payload and decode stable.</p>
     *
     * <p><b>What we do:</b></p>
     * <ul>
     *   <li>Two EAN-13 with the same text</li>
     *   <li>Small fixed canvas with tight padding to force differences in sizing strategy</li>
     *   <li>Generate with {@code NONE} and with {@code NEAREST}</li>
     * </ul>
     *
     * <p><b>Expected:</b> Both are decodable as EAN_13 with the same code text.</p>
     */
    @Test
    public void autoSizeModes_none_vs_nearest() throws Exception {
        final String code = "5901234123457";

        // --- NONE ---
        BarcodeGenerator genNone = new BarcodeGenerator(EncodeTypes.EAN_13, code);
        genNone.getParameters().setAutoSizeMode(AutoSizeMode.NONE);
        genNone.getParameters().getImageWidth().setPixels(220);
        genNone.getParameters().getImageHeight().setPixels(120);
        genNone.getParameters().getBarcode().getXDimension().setPixels(2.0f);
        genNone.getParameters().getBarcode().getPadding().getLeft().setPixels(8);
        genNone.getParameters().getBarcode().getPadding().getRight().setPixels(8);
        genNone.getParameters().getBarcode().getPadding().getTop().setPixels(6);
        genNone.getParameters().getBarcode().getPadding().getBottom().setPixels(6);

        String nonePath = ExampleAssist.pathCombine(FOLDER, FILE_EAN13_AUTOSIZE_NONE);
        genNone.save(nonePath, BarCodeImageFormat.PNG);
        ExampleAssist.assertFileCreated(nonePath);

        assertImageHasBarcodes(
                nonePath,
                1,
                List.of(expected(DecodeType.EAN_13, code))
        );

        // --- NEAREST ---
        BarcodeGenerator genNear = new BarcodeGenerator(EncodeTypes.EAN_13, code);
        genNear.getParameters().setAutoSizeMode(AutoSizeMode.NEAREST);
        genNear.getParameters().getImageWidth().setPixels(220);
        genNear.getParameters().getImageHeight().setPixels(120);
        genNear.getParameters().getBarcode().getXDimension().setPixels(2.0f);
        genNear.getParameters().getBarcode().getPadding().getLeft().setPixels(8);
        genNear.getParameters().getBarcode().getPadding().getRight().setPixels(8);
        genNear.getParameters().getBarcode().getPadding().getTop().setPixels(6);
        genNear.getParameters().getBarcode().getPadding().getBottom().setPixels(6);

        String nearPath = ExampleAssist.pathCombine(FOLDER, FILE_EAN13_AUTOSIZE_NEAR);
        genNear.save(nearPath, BarCodeImageFormat.PNG);
        ExampleAssist.assertFileCreated(nearPath);

        assertImageHasBarcodes(
                nearPath,
                1,
                List.of(expected(DecodeType.EAN_13, code))
        );

        // Optional: ensure both decode the same string (smoke check with the reader)
        BarCodeReader r1 = new BarCodeReader(nonePath, DecodeType.EAN_13);
        BarCodeResult[] a = r1.readBarCodes();
        Assert.assertTrue(a.length >= 1 && code.equals(a[0].getCodeText()));

        BarCodeReader r2 = new BarCodeReader(nearPath, DecodeType.EAN_13);
        BarCodeResult[] b = r2.readBarCodes();
        Assert.assertTrue(b.length >= 1 && code.equals(b[0].getCodeText()));
    }

    /**
     * Symbology-specific size parameter: Australian Post short bar height.
     *<p>Uses a valid Australia Post payload: first two digits are the Format Control Code (FCC).
     *  Valid FCC values: 11, 45, 59, 62, 87, 92. Here we use 59, followed by an 8-digit DPID.</p>
     * <p><b>Goal:</b> Show where to set the short bar height (a specific visual parameter for Australian Post),
     * using {@link Unit} in millimeters with DPI for predictable pixels.</p>
     *
     * <p><b>Notes:</b> Property name can differ between SDK versions. In many builds it is exposed
     * via {@code getParameters().getBarcode().getAustralianPost().getAustralianPostShortBarHeight()} (a {@link Unit}).
     *
     * <p><b>What we do:</b></p>
     * <ul>
     *   <li>Encode an Australian Post payload (example text)</li>
     *   <li>Set short bar height = 3.0 mm at 300 dpi</li>
     *   <li>Keep reasonable padding and canvas</li>
     * </ul>
     *
     * <p><b>Expected:</b> 1 barcode of type AUSTRALIA_POST with the specified code text.</p>
     */
    /**
     * Symbology-specific size parameter: Australian Post short bar height
     * with robust raster settings so the reader can detect it reliably.
     *
     * <p>Key points:</p>
     * <ul>
     *   <li>Valid FCC in the payload (here: "59")</li>
     *   <li>Explicit XDimension (2 px) and overall bar height (80 px)</li>
     *   <li>Short bar height set in millimeters with DPI for predictable pixels</li>
     *   <li>Generous quiet zones and fixed canvas</li>
     * </ul>
     */
    @Test
    public void australianPostShortBarHeight_300dpi() throws Exception {
        // Valid Australia Post example: FCC=59 + 8-digit DPID
        String payload = "5912345678";

        BarcodeGenerator gen = new BarcodeGenerator(EncodeTypes.AUSTRALIA_POST, payload);

        // 1) Make modules detectable: X-dimension ~2 px, overall bar height ~80 px
        gen.getParameters().getBarcode().getXDimension().setPixels(2.0f);
        gen.getParameters().getBarcode().getBarHeight().setPixels(80);

        // 2) Symbology-specific: short bar height = 3.0 mm at 300 dpi (~35 px)
        Unit shortBar = gen.getParameters().getBarcode()
                .getAustralianPost()
                .getAustralianPostShortBarHeight();
        shortBar.updateResolution(300f);
        shortBar.setMillimeters(3.0f);

        // 3) Quiet zones: at least ~16–20 px on each side
        gen.getParameters().getBarcode().getPadding().getLeft().setPixels(20);
        gen.getParameters().getBarcode().getPadding().getRight().setPixels(20);
        gen.getParameters().getBarcode().getPadding().getTop().setPixels(12);
        gen.getParameters().getBarcode().getPadding().getBottom().setPixels(12);

        // 4) Fixed canvas to avoid too-tight auto layout
        gen.getParameters().getImageWidth().setPixels(520);
        gen.getParameters().getImageHeight().setPixels(200);

        String fullPath = ExampleAssist.pathCombine(FOLDER, FILE_AUSPOST_SHORTBAR);
        gen.save(fullPath, BarCodeImageFormat.PNG);
        ExampleAssist.assertFileCreated(fullPath);

        // Expect exactly one Australia Post barcode with the same payload
        assertImageHasBarcodes(
                fullPath,
                1,
                List.of(expected(DecodeType.AUSTRALIA_POST, payload))
        );
    }

}
