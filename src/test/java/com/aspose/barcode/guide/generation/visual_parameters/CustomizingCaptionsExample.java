package com.aspose.barcode.guide.generation.visual_parameters;

import com.aspose.barcode.barcoderecognition.DecodeType;
import com.aspose.barcode.generation.*;
import com.aspose.barcode.guide.common.ExampleAssist;
import com.aspose.barcode.guide.common.LicenseAssist;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

import static com.aspose.barcode.guide.common.ExampleAssist.assertImageHasBarcodes;
import static com.aspose.barcode.guide.common.ExampleAssist.expected;

/**
 * Examples focused on customizing human-readable captions (code text):
 * <ul>
 *   <li>Controlling caption location (Above/Below/None)</li>
 *   <li>Setting caption spacing in Points and Millimeters (with DPI)</li>
 *   <li>Hiding the caption for 2D symbols (e.g., QR)</li>
 * </ul>
 *
 * <p>Conventions:</p>
 * <ul>
 *   <li>No try-with-resources; do not close/Dispose readers/generators explicitly.</li>
 *   <li>Deterministic output paths under {@code src/test/resources}.</li>
 *   <li>Use {@link ExampleAssist} helpers (path combining, assertions).</li>
 * </ul>
 */
public class CustomizingCaptionsExample {

    private static final String FOLDER =
            ExampleAssist.getOrCreateResourceFolderPath("generation", "visual_parameters", "customizing_captions");

    private static final String FILE_EAN13_BELOW_PT_SPACE   = "ean13_below_pt_space.png";
    private static final String FILE_CODE128_ABOVE_MM_SPACE = "code128_above_mm_300dpi.png";
    private static final String FILE_QR_NO_CAPTION          = "qr_no_caption.png";

    @BeforeClass
    public void setUp() throws Exception {
        LicenseAssist.setupLicense();
    }

    /**
     * # EAN-13: caption **Below** with extra spacing in **typographic points (pt)**
     *
     * <b>Shows:</b>
     * <ul>
     *   <li>How to place the human-readable text under the bars via {@code CodeLocation.BELOW}.</li>
     *   <li>How to push the caption away from the bars using {@code CodeTextParameters.Space} in points.</li>
     *   <li>Points are converted to pixels using the {@link Unit}'s DPI (default if not changed).</li>
     * </ul>
     *
     * <b>Expected:</b> one {@code EAN_13} with the given code; caption rendered below with visible extra gap.
     */
    @Test
    public void ean13_captionBelow_withPointSpace() throws Exception {
        final String code = "5901234123457";
        BarcodeGenerator generator = new BarcodeGenerator(EncodeTypes.EAN_13, code);
        generator.getParameters().getBarcode().getCodeTextParameters().setLocation(CodeLocation.NONE);
        CaptionParameters captionParameters = generator.getParameters().getCaptionBelow();
        captionParameters.setVisible(true);
        captionParameters.setText("CAP-BELOW");
        captionParameters.getFont().getSize().setPoint(12);
        captionParameters.setAlignment(TextAlignment.CENTER);
        captionParameters.getPadding().getBottom().setPoint(10f);
        generator.getParameters().getBarcode().getPadding().getBottom().setPixels(16);
        generator.getParameters().getImageWidth().setPixels(360);
        generator.getParameters().getImageHeight().setPixels(200);
        String fullPath = ExampleAssist.pathCombine(FOLDER, FILE_EAN13_BELOW_PT_SPACE);
        generator.save(fullPath, BarCodeImageFormat.PNG);
        ExampleAssist.assertFileCreated(fullPath);

        assertImageHasBarcodes(fullPath, 1, List.of(expected(DecodeType.EAN_13, code)));
    }


    /**
     * # Code 128: caption **Above** with spacing in **millimeters at 300 dpi**
     *
     * <b>Shows:</b>
     * <ul>
     *   <li>How to place the caption above bars via {@code CodeLocation.ABOVE}.</li>
     *   <li>How to set a physical gap (in mm) between caption and bars using {@code Unit.setMillimeters} +
     *       {@code updateResolution(300)} for predictable pixel conversion.</li>
     * </ul>
     *
     * <b>Expected:</b> one {@code CODE_128} with the given text; caption rendered above with a ~3 mm gap.
     *
     * <b>Gotchas:</b> forgetting {@code updateResolution(300)} makes mm→px use default DPI and the gap will differ.
     */
    @Test
    public void code128_captionAbove_mmSpace_at300dpi() throws Exception {
        final String text = "CAP-ABOVE";
        BarcodeGenerator gen = new BarcodeGenerator(EncodeTypes.CODE_128, text);

        // Caption above the bars
        gen.getParameters().getBarcode().getCodeTextParameters().setLocation(CodeLocation.ABOVE);

        // Physical gap: 3.0 mm @ 300 dpi
        Unit space = gen.getParameters().getBarcode().getCodeTextParameters().getSpace();
        space.updateResolution(300f);
        space.setMillimeters(3.0f);

        // Make bars visible and keep layout stable
        gen.getParameters().getBarcode().getXDimension().setPixels(3.0f);
        gen.getParameters().getBarcode().getBarHeight().setPixels(120);

        gen.getParameters().getImageWidth().setPixels(560);
        gen.getParameters().getImageHeight().setPixels(240);

        String full = ExampleAssist.pathCombine(FOLDER, FILE_CODE128_ABOVE_MM_SPACE);
        gen.save(full, BarCodeImageFormat.PNG);
        ExampleAssist.assertFileCreated(full);

        assertImageHasBarcodes(full, 1, List.of(expected(DecodeType.CODE_128, text)));
    }

    /**
     * # QR: **hide** caption entirely (no printed code text)
     *
     * <b>Shows:</b>
     * <ul>
     *   <li>How to suppress any human-readable text using {@code CodeLocation.NONE}, which is common for 2D symbols.</li>
     *   <li>Why hiding captions can help keep dense QR modules clear and compact.</li>
     * </ul>
     *
     * <b>Expected:</b> one {@code QR} with the given payload; no printed caption on the image.
     */
    @Test
    public void qr_hideCaption_locationNone() throws Exception {
        final String payload = "QR-NO-CAPTION";
        BarcodeGenerator gen = new BarcodeGenerator(EncodeTypes.QR, payload);

        // Do not render any code text (caption)
        gen.getParameters().getBarcode().getCodeTextParameters().setLocation(CodeLocation.NONE);

        // Reasonable raster so modules are detectable
        gen.getParameters().getBarcode().getXDimension().setPixels(4.0f);
        gen.getParameters().getImageWidth().setPixels(240);
        gen.getParameters().getImageHeight().setPixels(240);

        String full = ExampleAssist.pathCombine(FOLDER, FILE_QR_NO_CAPTION);
        gen.save(full, BarCodeImageFormat.PNG);
        ExampleAssist.assertFileCreated(full);

        assertImageHasBarcodes(full, 1, List.of(expected(DecodeType.QR, payload)));
    }
}
