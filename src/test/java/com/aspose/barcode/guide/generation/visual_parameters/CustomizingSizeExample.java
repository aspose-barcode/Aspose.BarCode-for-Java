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
    private static final String FILE_QR_XDIMENSION   = "qr_xdim_mm_203dpi.png";
    private static final String FILE_EAN13_QUIET_ZONE   = "ean13_quiet_mm_300dpi.png";
    private static final String FILE_UPCA_INTERPOLATION   = "upca_interpolation.png";
    private static final String FILE_ROWS_COLUMNS_RATIO   = "pdf417_rows_cols_ratio.png";
    private static final String FILE_DM_VERSION_XDIM   = "dm_version_xdim_px.png";
    private static final String FILE_ITF14_BEARER   = "itf14_bearer_mm_300dpi.png";

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
    public void australianPostShortBarHeight300dpi() throws Exception {
        String payload = "5912345678"; // FCC(59) + DPID

        BarcodeGenerator gen = new BarcodeGenerator(EncodeTypes.AUSTRALIA_POST, payload);
        gen.getParameters().getBarcode().getCodeTextParameters().setLocation(CodeLocation.NONE);

        gen.getParameters().setAutoSizeMode(AutoSizeMode.NONE);
        gen.getParameters().getBarcode().getXDimension().setPixels(3.0f);
        gen.getParameters().getBarcode().getBarHeight().setPixels(100);

        Unit shortBar = gen.getParameters().getBarcode().getAustralianPost().getAustralianPostShortBarHeight();
        shortBar.updateResolution(300f);
        shortBar.setMillimeters(3.0f);

        gen.getParameters().getBarcode().getPadding().getLeft().setPixels(24);
        gen.getParameters().getBarcode().getPadding().getRight().setPixels(24);
        gen.getParameters().getBarcode().getPadding().getTop().setPixels(16);
        gen.getParameters().getBarcode().getPadding().getBottom().setPixels(16);

        gen.getParameters().getImageWidth().setPixels(640);
        gen.getParameters().getImageHeight().setPixels(240);

        String full = ExampleAssist.pathCombine(FOLDER, FILE_AUSPOST_SHORTBAR);
        gen.save(full, BarCodeImageFormat.PNG);
        ExampleAssist.assertFileCreated(full);

        // Compare the prefix: only FCC+DPID is needed
        assertImageHasBarcodes(
                full,
                1,
                java.util.List.of(ExampleAssist.expectedPrefix(DecodeType.AUSTRALIA_POST, payload))
        );
    }

    /**
     * Set QR module size in millimeters for 203 dpi thermal printers.
     */
    @Test
    public void qrXdimensionMillimeters_at203dpi() throws Exception {
        BarcodeGenerator gen = new BarcodeGenerator(EncodeTypes.QR, "QR-203DPI");
        Unit xdim = gen.getParameters().getBarcode().getXDimension();
        xdim.updateResolution(203f);
        xdim.setMillimeters(0.50f);
        gen.getParameters().getBarcode().getQR().setQrECIEncoding(ECIEncodings.UTF8);

        String full = ExampleAssist.pathCombine(FOLDER, FILE_QR_XDIMENSION);
        gen.save(full, BarCodeImageFormat.PNG);
        ExampleAssist.assertFileCreated(full);

        assertImageHasBarcodes(full, 1, List.of(expected(DecodeType.QR, "QR-203DPI")));
    }

    /**
     * Enforce EAN-13 quiet zones in millimeters at 300 dpi.
     */
    @Test
    public void ean13QuietZoneMillimeters_at300dpi() throws Exception {
        String code = "5901234123457";
        BarcodeGenerator gen = new BarcodeGenerator(EncodeTypes.EAN_13, code);

        Unit left = gen.getParameters().getBarcode().getPadding().getLeft();
        Unit right = gen.getParameters().getBarcode().getPadding().getRight();
        left.updateResolution(300f);
        right.updateResolution(300f);
        left.setMillimeters(3.7f);
        right.setMillimeters(3.7f);

        gen.getParameters().getImageWidth().setPixels(320);
        gen.getParameters().getImageHeight().setPixels(160);

        String full = ExampleAssist.pathCombine(FOLDER, FILE_EAN13_QUIET_ZONE);
        gen.save(full, BarCodeImageFormat.PNG);
        ExampleAssist.assertFileCreated(full);

        assertImageHasBarcodes(full, 1, List.of(expected(DecodeType.EAN_13, code)));
    }

    /**
     * Show how AutoSizeMode.INTERPOLATION affects rasterization compared to NONE.
     */
    @Test
    public void upcA_autoSizeInterpolation_vsNone() throws Exception {
        String code = "042100005264";
        // AutoSizeMode.NONE
        BarcodeGenerator none = new BarcodeGenerator(EncodeTypes.UPCA, code);
        none.getParameters().setAutoSizeMode(AutoSizeMode.NONE);
        none.getParameters().getImageWidth().setPixels(260);
        none.getParameters().getImageHeight().setPixels(140);
        none.getParameters().getBarcode().getXDimension().setPixels(2.0f);
        String pNone = ExampleAssist.pathCombine(FOLDER, "upca_none.png");
        none.save(pNone, BarCodeImageFormat.PNG);
        ExampleAssist.assertFileCreated(pNone);
        assertImageHasBarcodes(pNone, 1, List.of(expected(DecodeType.UPCA, code)));

        // AutoSizeMode.INTERPOLATION
        BarcodeGenerator interp = new BarcodeGenerator(EncodeTypes.UPCA, code);
        interp.getParameters().setAutoSizeMode(AutoSizeMode.INTERPOLATION);
        interp.getParameters().getImageWidth().setPixels(260);
        interp.getParameters().getImageHeight().setPixels(140);
        interp.getParameters().getBarcode().getXDimension().setPixels(2.0f);
        String pInt = ExampleAssist.pathCombine(FOLDER, FILE_UPCA_INTERPOLATION);
        interp.save(pInt, BarCodeImageFormat.PNG);
        ExampleAssist.assertFileCreated(pInt);
        assertImageHasBarcodes(pInt, 1, List.of(expected(DecodeType.UPCA, code)));
    }

    /**
     * ITF-14: set bearer bar thickness in millimeters.
     * NOTE: Adjust property names if your SDK exposes them differently.
     */
    @Test
    public void itf14BearerBarThicknessMmAt300dpi() throws Exception {
        // ITF-14 payload (14 digits incl. check)
        String code = "10012345000017";

        BarcodeGenerator gen = new BarcodeGenerator(EncodeTypes.ITF_14, code);

        // --- Symbology-specific: border (bearer bar) type and thickness ---
        ITFParameters itf = gen.getParameters().getBarcode().getITF();

        // Choose one of: NONE, FRAME, BAR, FRAME_OUT, BAR_OUT
        itf.setItfBorderType(ITF14BorderType.FRAME);

        // Thickness in millimeters at 300 dpi
        Unit thickness = itf.getItfBorderThickness();
        thickness.updateResolution(300f);
        thickness.setMillimeters(2.5f);

        // Quiet zone in multiples of XDimension (>= 10). 12 gives a bit more safety.
        itf.setQuietZoneCoef(12);

        // Raster controls
        gen.getParameters().getBarcode().getXDimension().setPixels(2.0f);
        gen.getParameters().getBarcode().getBarHeight().setPixels(100);

        // Canvas (big enough to avoid clipping with bearer bar + quiet zones)
        gen.getParameters().getImageWidth().setPixels(520);
        gen.getParameters().getImageHeight().setPixels(260);

        String full = ExampleAssist.pathCombine(FOLDER, FILE_ITF14_BEARER);
        gen.save(full, BarCodeImageFormat.PNG);
        ExampleAssist.assertFileCreated(full);

        // ITF-14 is a distinct decode type in your enum
        assertImageHasBarcodes(full, 1, List.of(expected(DecodeType.ITF_14, code)));
    }


    /**
     * PDF417 size geometry: rows/columns and module aspect ratio.
     * NOTE: Use setAspectRatio or setXToYRatio depending on your SDK.
     */
    @Test
    public void pdf417RowsColumns_withAspectRatio() throws Exception {
        BarcodeGenerator gen = new BarcodeGenerator(EncodeTypes.PDF_417, "PDF417-SIZE");

        gen.getParameters().getBarcode().getPdf417().setRows(8);
        gen.getParameters().getBarcode().getPdf417().setColumns(5);
        // Either:
        // gen.getParameters().getBarcode().getPdf417().setAspectRatio(3.0f);
        // or:
        // gen.getParameters().getBarcode().getPdf417().setXToYRatio(3.0f);

        gen.getParameters().getImageWidth().setPixels(480);
        gen.getParameters().getImageHeight().setPixels(240);

        String full = ExampleAssist.pathCombine(FOLDER, FILE_ROWS_COLUMNS_RATIO);
        gen.save(full, BarCodeImageFormat.PNG);
        ExampleAssist.assertFileCreated(full);

        assertImageHasBarcodes(full, 1, List.of(expected(DecodeType.PDF_417, "PDF417-SIZE")));
    }

    /**
     * DataMatrix: fix symbol size (version) and module width in pixels.
     */
    @Test
    public void dataMatrixFixedVersionWithXdimensionPx() throws Exception {
        // Fixed-size DataMatrix (ECC 200), force a 24x24 symbol and set module width in pixels.
        BarcodeGenerator gen = new BarcodeGenerator(EncodeTypes.DATA_MATRIX, "DM-SIZE");

        // ECC 200 is required for the ECC200_* versions
        gen.getParameters().getBarcode().getDataMatrix().setDataMatrixEcc(DataMatrixEccType.ECC_200);

        // Pick the correct enum member from your DataMatrixVersion: ECC200_24x24 (not DM_24x24)
        gen.getParameters().getBarcode().getDataMatrix().setDataMatrixVersion(DataMatrixVersion.ECC200_24x24);

        // Control module width at the raster level (independent of DPI)
        gen.getParameters().getBarcode().getXDimension().setPixels(3.0f);

        // Make decoding robust: add quiet zones and a comfortable canvas
        gen.getParameters().getBarcode().getPadding().getLeft().setPixels(12);
        gen.getParameters().getBarcode().getPadding().getRight().setPixels(12);
        gen.getParameters().getBarcode().getPadding().getTop().setPixels(12);
        gen.getParameters().getBarcode().getPadding().getBottom().setPixels(12);
        gen.getParameters().getImageWidth().setPixels(220);
        gen.getParameters().getImageHeight().setPixels(220);

        String full = ExampleAssist.pathCombine(FOLDER, FILE_DM_VERSION_XDIM);
        gen.save(full, BarCodeImageFormat.PNG);
        ExampleAssist.assertFileCreated(full);

        assertImageHasBarcodes(full, 1, List.of(expected(DecodeType.DATA_MATRIX, "DM-SIZE")));
    }


    /**
     * Choose XDimension to hit an approximate target total width.
     */
    @Test
    public void code128SolveXdimensionForTargetWidth() throws Exception {
        String text = "TARGET-WIDTH";
        BarcodeGenerator gen = new BarcodeGenerator(EncodeTypes.CODE_128, text);

        int canvas = 600, quiet = 20;
        gen.getParameters().getImageWidth().setPixels(canvas);
        gen.getParameters().getImageHeight().setPixels(200);
        gen.getParameters().getBarcode().getPadding().getLeft().setPixels(quiet);
        gen.getParameters().getBarcode().getPadding().getRight().setPixels(quiet);

        // Very rough estimate: Code 128 ~ 11 modules/char avg (demo only).
        int estModules = Math.max(60, text.length() * 11);
        float xdimPx = Math.max(2f, (canvas - 2f * quiet) / estModules);
        gen.getParameters().getBarcode().getXDimension().setPixels(xdimPx);
        gen.getParameters().getBarcode().getBarHeight().setPixels(120);

        String full = ExampleAssist.pathCombine(FOLDER, "c128_target_width.png");
        gen.save(full, BarCodeImageFormat.PNG);
        ExampleAssist.assertFileCreated(full);

        assertImageHasBarcodes(full, 1, List.of(expected(DecodeType.CODE_128, text)));
    }

    /**
     * Small QR with fixed version and quiet zones in typographic points.
     */
    @Test
    public void qrFixedVersion_quietZoneInPoints() throws Exception {
        BarcodeGenerator gen = new BarcodeGenerator(EncodeTypes.QR, "QR-PT");

        gen.getParameters().getBarcode().getQR().setQrVersion(QRVersion.VERSION_02);
        Unit l = gen.getParameters().getBarcode().getPadding().getLeft();
        Unit r = gen.getParameters().getBarcode().getPadding().getRight();
        Unit t = gen.getParameters().getBarcode().getPadding().getTop();
        Unit b = gen.getParameters().getBarcode().getPadding().getBottom();
        l.setPoint(12f); r.setPoint(12f); t.setPoint(12f); b.setPoint(12f);

        gen.getParameters().getImageWidth().setPixels(220);
        gen.getParameters().getImageHeight().setPixels(220);

        String full = ExampleAssist.pathCombine(FOLDER, "qr_ver2_quiet_pt.png");
        gen.save(full, BarCodeImageFormat.PNG);
        ExampleAssist.assertFileCreated(full);

        assertImageHasBarcodes(full, 1, List.of(expected(DecodeType.QR, "QR-PT")));
    }





}
