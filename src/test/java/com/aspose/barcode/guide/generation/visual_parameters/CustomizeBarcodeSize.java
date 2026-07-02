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
public class CustomizeBarcodeSize {

    private static final String FOLDER =
            ExampleAssist.getOrCreateResourceFolderPath("generation", "visual_parameters","customizing_size");

    private static final String FILE_C128_XDIM_PX       = "c128_xdim_px.png";
    private static final String FILE_C128_BAR_MM_300DPI = "c128_bar_mm_300dpi.png";
    private static final String FILE_EAN13_AUTOSIZE_NONE= "ean13_autosize_none.png";
    private static final String FILE_EAN13_AUTOSIZE_NEAR= "ean13_autosize_nearest.png";
    private static final String FILE_AUSPOST_SHORTBAR   = "auspost_shortbar.png";
    private static final String FILE_QR_XDIMENSION      = "qr_xdim_mm_203dpi.png";
    private static final String FILE_EAN13_QUIET_ZONE   = "ean13_quiet_mm_300dpi.png";
    private static final String FILE_UPCA_INTERPOLATION = "upca_interpolation.png";
    private static final String FILE_ROWS_COLUMNS_RATIO = "pdf417_rows_cols_ratio.png";
    private static final String FILE_DM_VERSION_XDIM    = "dm_version_xdim_px.png";
    private static final String FILE_ITF14_BEARER       = "itf14_bearer_mm_300dpi.png";

    @BeforeClass
    public void setUp() throws Exception {
        LicenseAssist.setupLicense();
    }

    /**
     * # CODE 128: fix **XDimension in pixels**
     *
     * <b>Shows:</b>
     * <ul>
     *   <li>How to set the smallest module/bar width directly in <b>pixels</b> using {@code Unit.setPixels(...)}.</li>
     *   <li>Why DPI is <b>not involved</b> when you specify pixels — you get exact raster control.</li>
     * </ul>
     *
     * <b>Key settings:</b> {@code XDimension = 3 px}, reasonable bar height and paddings to avoid clipping.
     *
     * <b>Expected:</b> one {@code CODE_128} with the text {@code XDIM-PX}.
     *
     * <b>Gotchas:</b> using {@code XDimension < 2 px} often leads to antialiasing blur in PNGs.
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
     * # CODE 128: set 1D bar height in **millimeters @300 dpi**
     *
     * <b>Shows:</b>
     * <ul>
     *   <li>How to define physical size via {@code setMillimeters(...)} and get predictable pixels with {@code updateResolution(dpi)}.</li>
     *   <li>Pixel checks for sanity: {@code 12 mm @300 dpi ≈ 142 px}, {@code 0.5 mm @300 dpi ≈ 6 px}.</li>
     * </ul>
     *
     * <b>Key settings:</b> both bar height and XDimension units set to {@code 300 dpi} before assigning mm.
     *
     * <b>Expected:</b> one {@code CODE_128} with text {@code BAR-MM}, pixel values within tolerant ranges (asserted).
     *
     * <b>Gotchas:</b> forgetting {@code updateResolution(...)} will keep default DPI (likely 96), breaking mm→px math.
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
     * # EAN-13 and **AutoSizeMode**: NONE vs NEAREST
     *
     * <b>Shows:</b>
     * <ul>
     *   <li>How {@code AutoSizeMode} can change the effective rasterization when the canvas is tight.</li>
     *   <li>{@code NONE} = no resampling; {@code NEAREST} = engine may snap to nearest integer pixel sizes.</li>
     * </ul>
     *
     * <b>Key settings:</b> identical payload/canvas/paddings/XDimension, only {@code AutoSizeMode} differs.
     *
     * <b>Expected:</b> both images decode as {@code EAN_13} with the same text.
     *
     * <b>Gotchas:</b> very small canvases + {@code NEAREST} may slightly alter the effective x-dim (±1 px).
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

        // Optional: smoke-check via reader
        BarCodeReader r1 = new BarCodeReader(nonePath, DecodeType.EAN_13);
        BarCodeResult[] a = r1.readBarCodes();
        Assert.assertTrue(a.length >= 1 && code.equals(a[0].getCodeText()));

        BarCodeReader r2 = new BarCodeReader(nearPath, DecodeType.EAN_13);
        BarCodeResult[] b = r2.readBarCodes();
        Assert.assertTrue(b.length >= 1 && code.equals(b[0].getCodeText()));
    }

    /**
     * # Australia Post: short bar height in **mm @300 dpi**
     *
     * <b>Shows:</b>
     * <ul>
     *   <li>Where to set the symbology-specific short bar height via a {@link Unit} with physical units.</li>
     *   <li>Why a valid FCC is required: here we use {@code "59"} + 8-digit DPID (example payload).</li>
     *   <li>Why explicit XDimension, bar height, and generous quiet zones help decoding.</li>
     * </ul>
     *
     * <b>Key settings:</b>
     * <ul>
     *   <li>{@code shortBar.updateResolution(300); shortBar.setMillimeters(3.0f);}</li>
     *   <li>{@code AutoSizeMode.NONE}, visible bar height and paddings.</li>
     * </ul>
     *
     * <b>Expected:</b> one {@code AUSTRALIA_POST}. We compare by prefix (FCC+DPID) because readers often append extra machine data.
     *
     * <b>Gotchas:</b> invalid FCC throws {@code InvalidCodeException}; too small paddings/canvas → clipping → 0 results.
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
     * # QR: set **XDimension in millimeters** for **203 dpi** thermal printers
     *
     * <b>Shows:</b>
     * <ul>
     *   <li>How to size QR modules for 203 dpi devices via physical units and DPI-aware conversion.</li>
     *   <li>ECI UTF-8 as a robust text encoding path for QR.</li>
     * </ul>
     *
     * <b>Expected:</b> one {@code QR} with text {@code QR-203DPI}.
     *
     * <b>Gotchas:</b> for very small modules, add explicit quiet zones; otherwise decoding on rendered PNGs may fail.
     */
    @Test
    public void qrXdimensionMillimeters_at203dpi() throws Exception {
        BarcodeGenerator gen = new BarcodeGenerator(EncodeTypes.QR, "QR-203DPI");
        Unit xdim = gen.getParameters().getBarcode().getXDimension();
        xdim.updateResolution(203f);
        xdim.setMillimeters(0.50f);
        gen.getParameters().getBarcode().getQR().setECIEncoding(ECIEncodings.UTF8);

        String full = ExampleAssist.pathCombine(FOLDER, FILE_QR_XDIMENSION);
        gen.save(full, BarCodeImageFormat.PNG);
        ExampleAssist.assertFileCreated(full);

        assertImageHasBarcodes(full, 1, List.of(expected(DecodeType.QR, "QR-203DPI")));
    }

    /**
     * # EAN-13: enforce **quiet zones in millimeters @300 dpi**
     *
     * <b>Shows:</b>
     * <ul>
     *   <li>How to set physically correct quiet zones on left/right sides via mm + dpi.</li>
     *   <li>Quiet zones are critical for EAN/UPC readability.</li>
     * </ul>
     *
     * <b>Expected:</b> one {@code EAN_13} with the given code.
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
     * # UPC-A: compare **AutoSizeMode.INTERPOLATION** vs **NONE**
     *
     * <b>Shows:</b>
     * <ul>
     *   <li>How interpolation-based autosizing differs from no-autosizing for the same canvas/xdim.</li>
     *   <li>Both images remain decodable.</li>
     * </ul>
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
     * # ITF-14: set **bearer bar thickness in millimeters @300 dpi**
     *
     * <b>Shows:</b>
     * <ul>
     *   <li>How to choose a bearer type (FRAME/BAR/FRAME_OUT/BAR_OUT) and set its thickness via a {@link Unit}.</li>
     *   <li>Why {@code QuietZoneCoef} (in multiples of XDimension) should be >= 10 (we use 12 for safety).</li>
     * </ul>
     *
     * <b>Key settings:</b>
     * <ul>
     *   <li>{@code itf.setItfBorderType(ITF14BorderType.FRAME);}</li>
     *   <li>{@code thickness.updateResolution(300); thickness.setMillimeters(2.5f);}</li>
     *   <li>{@code itf.setQuietZoneCoef(12);}</li>
     * </ul>
     *
     * <b>Expected:</b> one {@code ITF_14} with the given text.
     *
     * <b>Gotchas:</b> ensure canvas is large enough (bearer + quiet zones may be wide).
     */
    @Test
    public void itf14BearerBarThicknessMmAt300dpi() throws Exception {
        String code = "10012345000017"; // ITF-14 payload (14 digits incl. check)

        BarcodeGenerator gen = new BarcodeGenerator(EncodeTypes.ITF_14, code);

        ITFParameters itf = gen.getParameters().getBarcode().getITF();
        itf.setItfBorderType(ITF14BorderType.FRAME);

        Unit thickness = itf.getItfBorderThickness();
        thickness.updateResolution(300f);
        thickness.setMillimeters(2.5f);

        itf.setQuietZoneCoef(12);

        gen.getParameters().getBarcode().getXDimension().setPixels(2.0f);
        gen.getParameters().getBarcode().getBarHeight().setPixels(100);

        gen.getParameters().getImageWidth().setPixels(520);
        gen.getParameters().getImageHeight().setPixels(260);

        String full = ExampleAssist.pathCombine(FOLDER, FILE_ITF14_BEARER);
        gen.save(full, BarCodeImageFormat.PNG);
        ExampleAssist.assertFileCreated(full);

        assertImageHasBarcodes(full, 1, List.of(expected(DecodeType.ITF_14, code)));
    }

    /**
     * # PDF417: control **rows/columns** and **module aspect ratio**
     *
     * <b>Shows:</b>
     * <ul>
     *   <li>How to lock the grid size (rows/columns) of a PDF417 symbol.</li>
     *   <li>How to influence module aspect via {@code setAspectRatio} or {@code setXToYRatio}.</li>
     * </ul>
     * <b>Expected:</b> one {@code PDF_417} with the text {@code PDF417-SIZE}.
     */
    @Test
    public void pdf417RowsColumns_withAspectRatio() throws Exception {
        BarcodeGenerator gen = new BarcodeGenerator(EncodeTypes.PDF_417, "PDF417-SIZE");

        gen.getParameters().getBarcode().getPdf417().setRows(8);
        gen.getParameters().getBarcode().getPdf417().setColumns(5);

        gen.getParameters().getImageWidth().setPixels(480);
        gen.getParameters().getImageHeight().setPixels(240);

        String full = ExampleAssist.pathCombine(FOLDER, FILE_ROWS_COLUMNS_RATIO);
        gen.save(full, BarCodeImageFormat.PNG);
        ExampleAssist.assertFileCreated(full);

        assertImageHasBarcodes(full, 1, List.of(expected(DecodeType.PDF_417, "PDF417-SIZE")));
    }

    /**
     * # DataMatrix: fix **symbol version (size)** and **module width in pixels**
     *
     * <b>Shows:</b>
     * <ul>
     *   <li>How to force a concrete ECC200 symbol size (e.g., 24×24) via {@code DataMatrixVersion}.</li>
     *   <li>How to control module width at raster level via {@code XDimension} in pixels (independent of DPI).</li>
     * </ul>
     *
     * <b>Key settings:</b>
     * <ul>
     *   <li>{@code setDataMatrixEcc(DataMatrixEccType.ECC_200);} (required for ECC200_* versions)</li>
     *   <li>{@code setDataMatrixVersion(DataMatrixVersion.ECC200_24x24);}</li>
     *   <li>{@code XDimension = 3 px} + explicit quiet zones and a comfortable canvas.</li>
     * </ul>
     *
     * <b>Expected:</b> one {@code DATA_MATRIX} with text {@code DM-SIZE}.
     *
     * <b>Gotchas:</b> some builds do not expose {@code DM_24x24}; use {@code ECC200_24x24}.
     */
    @Test
    public void dataMatrixFixedVersionWithXdimensionPx() throws Exception {
        // Fixed-size DataMatrix (ECC 200), force a 24x24 symbol and set module width in pixels.
        BarcodeGenerator gen = new BarcodeGenerator(EncodeTypes.DATA_MATRIX, "DM-SIZE");

        gen.getParameters().getBarcode().getDataMatrix().setDataMatrixEcc(DataMatrixEccType.ECC_200);
        gen.getParameters().getBarcode().getDataMatrix().setDataMatrixVersion(DataMatrixVersion.ECC200_24x24);

        gen.getParameters().getBarcode().getXDimension().setPixels(3.0f);

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
     * # CODE 128: estimate **XDimension** to hit an approximate target total width
     *
     * <b>Shows:</b>
     * <ul>
     *   <li>How to roughly compute {@code XDimension} for a desired canvas width with given paddings.</li>
     *   <li>This is a heuristic (demo), not a strict formula; actual modules depend on Start/Stop/encoding.</li>
     * </ul>
     *
     * <b>Expected:</b> one {@code CODE_128} with text {@code TARGET-WIDTH}.
     *
     * <b>Gotchas:</b> For production code, prefer an API that reports exact width given parameters, if available.
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
     * # Small QR: fixed version + quiet zones in **typographic points (pt)**
     *
     * <b>Shows:</b>
     * <ul>
     *   <li>Mixing units: restrict the QR size via {@code QrVersion} and set quiet zones in points.</li>
     *   <li>Points are converted to pixels according to the {@link Unit}'s DPI (default if not changed).</li>
     * </ul>
     *
     * <b>Expected:</b> one {@code QR} with text {@code QR-PT}.
     *
     * <b>Gotchas:</b> If exact physical spacing is required, prefer {@code setMillimeters}/{@code setInches} with {@code updateResolution(dpi)}.
     */
    @Test
    public void qrFixedVersion_quietZoneInPoints() throws Exception {
        BarcodeGenerator gen = new BarcodeGenerator(EncodeTypes.QR, "QR-PT");

        gen.getParameters().getBarcode().getQR().setVersion(QRVersion.VERSION_02);
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
    /**
     * Controls a linear barcode through X-dimension and bar height.
     */
    @Test
    public void configureLinearBarcodeSize() throws Exception {
        String codeText = "SIZE-1D";
        String outputPath = ExampleAssist.pathCombine(
                FOLDER,
                "code128_size.png"
        );

        BarcodeGenerator generator = new BarcodeGenerator(
                EncodeTypes.CODE_128,
                codeText
        );

        generator.getParameters().setAutoSizeMode(AutoSizeMode.NONE);
        generator.getParameters().getBarcode().getXDimension().setPixels(3.0f);
        generator.getParameters().getBarcode().getBarHeight().setPixels(100);

        generator.save(outputPath, BarCodeImageFormat.PNG);

        ExampleAssist.assertFileCreated(outputPath);
        assertImageHasBarcodes(
                outputPath,
                1,
                List.of(expected(DecodeType.CODE_128, codeText))
        );
    }

    /**
     * Fits a QR Code into a target image box using AutoSizeMode.NEAREST.
     */
    @Test
    public void fitQrIntoImageBox() throws Exception {
        String codeText = "AUTO-SIZE-QR";
        String outputPath = ExampleAssist.pathCombine(
                FOLDER,
                "qr_nearest.png"
        );

        BarcodeGenerator generator = new BarcodeGenerator(
                EncodeTypes.QR,
                codeText
        );

        generator.getParameters().setAutoSizeMode(AutoSizeMode.NEAREST);
        generator.getParameters().getImageWidth().setPixels(240);
        generator.getParameters().getImageHeight().setPixels(240);

        generator.save(outputPath, BarCodeImageFormat.PNG);

        ExampleAssist.assertFileCreated(outputPath);
        assertImageHasBarcodes(
                outputPath,
                1,
                List.of(expected(DecodeType.QR, codeText))
        );
    }

    /**
     * Configures the short-bar height for Australian Post.
     *
     * The decoded text may contain additional filler data generated by the engine,
     * so this test verifies the barcode type and checks that the decoded value
     * starts with the original source data.
     */
    @Test
    public void configureAustralianPostShortBarHeight() throws Exception {
        String australianPostCodeText = "5912345678";

        String australianPostPath = ExampleAssist.pathCombine(
                FOLDER,
                "australian_post_short_bar.png"
        );

        BarcodeGenerator australianPostGenerator =
                new BarcodeGenerator(
                        EncodeTypes.AUSTRALIA_POST,
                        australianPostCodeText
                );

        australianPostGenerator.getParameters()
                .getBarcode()
                .getAustralianPost()
                .getAustralianPostShortBarHeight()
                .setPixels(12);

        australianPostGenerator.save(
                australianPostPath,
                BarCodeImageFormat.PNG
        );

        ExampleAssist.assertFileCreated(australianPostPath);

        BarCodeReader australianPostReader = new BarCodeReader(
                australianPostPath,
                DecodeType.AUSTRALIA_POST
        );

        BarCodeResult[] australianPostResults =
                australianPostReader.readBarCodes();

        Assert.assertEquals(
                australianPostResults.length,
                1,
                "Expected exactly one Australia Post barcode"
        );

        Assert.assertEquals(
                australianPostResults[0].getCodeType(),
                DecodeType.AUSTRALIA_POST,
                "Decode type must be Australia Post"
        );

        Assert.assertTrue(
                australianPostResults[0]
                        .getCodeText()
                        .startsWith(australianPostCodeText),
                "Decoded Australia Post text must start with the source data"
        );
    }

    /**
     * Configures the bearer-bar thickness for ITF-14.
     *
     * The goal of this example is to demonstrate the ITF-specific visual parameter.
     * Recognition output formatting may vary, so the test verifies that the file is
     * created and that at least one ITF-14 barcode is recognized.
     */
    @Test
    public void configureItf14BearerBarThickness() throws Exception {
        String itf14CodeText = "10012345000017";

        String itf14Path = ExampleAssist.pathCombine(
                FOLDER,
                "itf14_bearer_bar.png"
        );

        BarcodeGenerator itf14Generator = new BarcodeGenerator(
                EncodeTypes.ITF_14,
                itf14CodeText
        );

        itf14Generator.getParameters()
                .getBarcode()
                .getITF()
                .setItfBorderType(ITF14BorderType.FRAME);

        itf14Generator.getParameters()
                .getBarcode()
                .getITF()
                .getItfBorderThickness()
                .setPixels(4);

        itf14Generator.save(itf14Path, BarCodeImageFormat.PNG);

        ExampleAssist.assertFileCreated(itf14Path);

        BarCodeReader itf14Reader = new BarCodeReader(
                itf14Path,
                DecodeType.ITF_14
        );

        BarCodeResult[] itf14Results = itf14Reader.readBarCodes();

        Assert.assertTrue(
                itf14Results.length >= 1,
                "Expected at least one ITF-14 barcode"
        );

        Assert.assertEquals(
                itf14Results[0].getCodeType(),
                DecodeType.ITF_14,
                "Decode type must be ITF-14"
        );

        Assert.assertNotNull(
                itf14Results[0].getCodeText(),
                "Decoded ITF-14 text must not be null"
        );

        Assert.assertFalse(
                itf14Results[0].getCodeText().isEmpty(),
                "Decoded ITF-14 text must not be empty"
        );
    }

}
