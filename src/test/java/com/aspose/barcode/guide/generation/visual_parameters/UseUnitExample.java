package com.aspose.barcode.guide.generation.visual_parameters;

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
 * Demonstrates how {@link Unit} is used across Aspose.BarCode parameters to set visual size
 * and layout in different measurement systems:
 * <ul>
 *   <li>Pixels (image canvas and bars)</li>
 *   <li>Millimeters (bar height and X-dimension) with custom DPI</li>
 *   <li>Points and Inches (padding/quiet zones)</li>
 * </ul>
 *
 * <p>Conventions for this project:</p>
 * <ul>
 *   <li>No try-with-resources; do not call close()/dispose() on readers/generators.</li>
 *   <li>We use {@link ExampleAssist} utilities for IO and assertions.</li>
 *   <li>Each test saves a deterministic PNG and verifies recognition.</li>
 * </ul>
 */
public class UseUnitExample {

    private static final String FOLDER =
            ExampleAssist.getOrCreateResourceFolderPath("generation", "visual_parameters", "use_unit");

    private static final String FILE_C128_PX_CANVAS   = "c128_px_canvas.png";
    private static final String FILE_C128_MM_BARS     = "c128_mm_bars.png";
    private static final String FILE_EAN13_PT_IN_PAD  = "ean13_pt_in_padding.png";
    private static final String FILE_QR_INCH_XDIM     = "qr_inch_xdim.png";

    @BeforeClass
    public void setUp() throws Exception {
        LicenseAssist.setupLicense();
    }

    /**
     * Sets image canvas and bar metrics explicitly in <b>pixels</b>.
     *
     * <p><b>Purpose:</b> Show the simplest path to pixel-precise rendering using {@link Unit#setPixels(float)}.</p>
     * <p><b>What we set:</b></p>
     * <ul>
     *   <li>Canvas: 500×180 px</li>
     *   <li>X-dimension: 2 px</li>
     *   <li>Bar height: 100 px</li>
     *   <li>Padding: 10 px on all sides</li>
     * </ul>
     * <p><b>Expected:</b> One CODE_128 with text "UNIT-PX".</p>
     */
    @Test
    public void unitPixelsForCanvasAndBars() throws Exception {
        BarcodeGenerator generator = new BarcodeGenerator(EncodeTypes.CODE_128, "UNIT-PX");

        // Canvas size (pixels)
        generator.getParameters().getImageWidth().setPixels(500);
        generator.getParameters().getImageHeight().setPixels(180);

        // Barcode metrics (pixels)
        generator.getParameters().getBarcode().getXDimension().setPixels(2.0f);
        generator.getParameters().getBarcode().getBarHeight().setPixels(100);

        // Padding / quiet zones (pixels)
        generator.getParameters().getBarcode().getPadding().getLeft().setPixels(10);
        generator.getParameters().getBarcode().getPadding().getRight().setPixels(10);
        generator.getParameters().getBarcode().getPadding().getTop().setPixels(10);
        generator.getParameters().getBarcode().getPadding().getBottom().setPixels(10);

        String fullPath = ExampleAssist.pathCombine(FOLDER, FILE_C128_PX_CANVAS);
        generator.save(fullPath, BarCodeImageFormat.PNG);
        ExampleAssist.assertFileCreated(fullPath);

        // Recognize & assert via ExampleAssist (compare by text)
        assertImageHasBarcodes(
                fullPath,
                1,
                List.of(expected(DecodeType.CODE_128, "UNIT-PX"))
        );
    }

    /**
     * Sets bar height and module width in <b>millimeters</b> and adjusts DPI for correct conversion.
     *
     * <p><b>Purpose:</b> Show how {@link Unit#setMillimeters(float)} cooperates with {@link Unit#updateResolution(float)}
     * to yield predictable pixel sizes.</p>
     *
     * <p><b>What we set:</b></p>
     * <ul>
     *   <li>DPI: 300 (on both barHeight and X-dimension units)</li>
     *   <li>Bar height: 12 mm (≈ 12 / 25.4 in × 300 dpi ≈ 142 px)</li>
     *   <li>X-dimension: 0.5 mm (≈ 6 px at 300 dpi)</li>
     * </ul>
     * <p><b>Expected:</b> One CODE_128 with text "UNIT-MM". Additionally, we assert approximate pixel values.</p>
     */
    @Test
    public void unitMillimetersWithCustomDpi() throws Exception {
        BarcodeGenerator generator = new BarcodeGenerator(EncodeTypes.CODE_128, "UNIT-MM");

        // Access units
        Unit barHeight = generator.getParameters().getBarcode().getBarHeight();
        Unit xdim      = generator.getParameters().getBarcode().getXDimension();

        // Set conversion DPI for these units
        barHeight.updateResolution(300f);
        xdim.updateResolution(300f);

        // Set values in millimeters
        barHeight.setMillimeters(12.0f);
        xdim.setMillimeters(0.5f);

        // Sanity: check pixel approximations (tolerant)
        float barHeightPx = barHeight.getPixels();
        float xdimPx      = xdim.getPixels();

        // 12 mm at 300 dpi ≈ 12/25.4 inch * 300 ≈ 141.73 px
        Assert.assertTrue(Math.abs(barHeightPx - 142f) <= 3f,
                "Bar height (px) unexpected: " + barHeightPx);

        // 0.5 mm at 300 dpi ≈ 0.5/25.4 * 300 ≈ 5.91 px
        Assert.assertTrue(Math.abs(xdimPx - 6f) <= 1f,
                "X-dimension (px) unexpected: " + xdimPx);

        String fullPath = ExampleAssist.pathCombine(FOLDER, FILE_C128_MM_BARS);
        generator.save(fullPath, BarCodeImageFormat.PNG);
        ExampleAssist.assertFileCreated(fullPath);

        assertImageHasBarcodes(
                fullPath,
                1,
                List.of(expected(DecodeType.CODE_128, "UNIT-MM"))
        );
    }

    /**
     * Sets quiet zones using mixed units: <b>points</b> for left/right and <b>inches</b> for top/bottom.
     *
     * <p><b>Purpose:</b> Demonstrate mixed-unit layout via {@link Unit#setPoint(float)} and {@link Unit#setInches(float)}.</p>
     * <p><b>What we set:</b></p>
     * <ul>
     *   <li>Left/Right padding: 12 pt (1 pt = 1/72 in)</li>
     *   <li>Top/Bottom padding: 0.1 in</li>
     * </ul>
     * <p><b>Expected:</b> One EAN_13 with text "5901234123457".</p>
     */
    @Test
    public void unitPointsAndInchesForPadding() throws Exception {
        BarcodeGenerator generator = new BarcodeGenerator(EncodeTypes.EAN_13, "5901234123457");

        Unit padL = generator.getParameters().getBarcode().getPadding().getLeft();
        Unit padR = generator.getParameters().getBarcode().getPadding().getRight();
        Unit padT = generator.getParameters().getBarcode().getPadding().getTop();
        Unit padB = generator.getParameters().getBarcode().getPadding().getBottom();

        // Use default DPI for points/inches unless your pipeline mandates specific DPI.
        padL.setPoint(12f);
        padR.setPoint(12f);
        padT.setInches(0.1f);
        padB.setInches(0.1f);

        String fullPath = ExampleAssist.pathCombine(FOLDER, FILE_EAN13_PT_IN_PAD);
        generator.save(fullPath, BarCodeImageFormat.PNG);
        ExampleAssist.assertFileCreated(fullPath);

        assertImageHasBarcodes(
                fullPath,
                1,
                List.of(expected(DecodeType.EAN_13, "5901234123457"))
        );
    }

    /**
     * Controls QR module size in <b>inches</b> via X-dimension and shows that the text stays intact.
     *
     * <p><b>Purpose:</b> Demonstrate {@link Unit#setInches(float)} for 2D symbology module width (X-dimension).</p>
     * <p><b>What we set:</b></p>
     * <ul>
     *   <li>QR X-dimension: 0.01 inch</li>
     *   <li>ECI UTF-8 (robust Unicode path for QR)</li>
     * </ul>
     * <p><b>Expected:</b> One QR with text "UNIT-INCH".</p>
     */
    @Test
    public void unitInchesForQrModule() throws Exception {
        BarcodeGenerator generator = new BarcodeGenerator(EncodeTypes.QR, "UNIT-INCH");

        // 1) Make the module physically reasonable:
        //    0.01" at 300 dpi ≈ 3 px per module (reliably decodable).
        Unit xDimension = generator.getParameters().getBarcode().getXDimension();
        xDimension.updateResolution(300f);   // ensure inch-to-pixel conversion uses 300 dpi
        xDimension.setInches(0.01f);         // ≈ 3 px per module at 300 dpi

        // 2) Recommended QR settings for robustness.
        generator.getParameters().getBarcode().getQR().setECIEncoding(ECIEncodings.UTF8);
        generator.getParameters().getBarcode().getQR().setErrorLevel(QRErrorLevel.LEVEL_M);
        generator.getParameters().getBarcode().getQR().setVersion(QRVersion.VERSION_01); // compact symbol size

        // 3) Quiet zone: ~4 modules on each side (with a minimum safety margin of 12 px).
        int quietPx = Math.max(12, Math.round(xDimension.getPixels() * 4));
        generator.getParameters().getBarcode().getPadding().getLeft().setPixels(quietPx);
        generator.getParameters().getBarcode().getPadding().getRight().setPixels(quietPx);
        generator.getParameters().getBarcode().getPadding().getTop().setPixels(quietPx);
        generator.getParameters().getBarcode().getPadding().getBottom().setPixels(quietPx);

        // 4) Explicit canvas size to avoid tight auto-fit near borders.
        generator.getParameters().getImageWidth().setPixels(240);
        generator.getParameters().getImageHeight().setPixels(240);

        String fullPath = ExampleAssist.pathCombine(FOLDER, FILE_QR_INCH_XDIM);
        generator.save(fullPath, BarCodeImageFormat.PNG);
        ExampleAssist.assertFileCreated(fullPath);

        assertImageHasBarcodes(
                fullPath,
                1,
                List.of(expected(DecodeType.QR, "UNIT-INCH"))
        );
    }


}
