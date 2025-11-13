package com.aspose.barcode.guide.generation.visual_parameters;

import com.aspose.barcode.barcoderecognition.BarCodeReader;
import com.aspose.barcode.barcoderecognition.BarCodeResult;
import com.aspose.barcode.barcoderecognition.DecodeType;
import com.aspose.barcode.generation.*;
import com.aspose.barcode.guide.common.ExampleAssist;
import com.aspose.barcode.guide.common.LicenseAssist;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.awt.Color;
import java.util.List;

import static com.aspose.barcode.guide.common.ExampleAssist.assertImageHasBarcodes;
import static com.aspose.barcode.guide.common.ExampleAssist.expected;

/**
 * Caption customization examples (top/bottom captions independent from the barcode’s own CodeText).
 * Conventions:
 * - No try-with-resources; do not close/Dispose readers/generators explicitly.
 * - Deterministic output paths under src/test/resources.
 * - Uses ExampleAssist helpers (path combining, assertions).
 */
public class CustomizingCaptionsExample {

    private static final String FOLDER =
            ExampleAssist.getOrCreateResourceFolderPath("generation", "visual_parameters", "captions");

    // File names
    private static final String FILE_QR_TITLE_ONLY                  = "qr_title_only.png";
    private static final String FILE_CODE128_ABOVE_AND_BELOW        = "code128_above_and_below.png";
    private static final String FILE_CODE128_ALIGN_LEFT_CENTER_RIGHT= "code128_align_lcr.png";
    private static final String FILE_CODE128_FONT_SIZE_PT           = "code128_font_size_pt.png";
    private static final String FILE_CODE128_COLOR_AND_PADDING_MIX  = "code128_color_and_padding_mix.png";
    private static final String FILE_QR_DPI_SENSITIVE_TOP_GAP       = "qr_dpi_sensitive_top_gap.png";
    private static final String FILE_CODE128_MULTILINE_WRAP         = "code128_multiline_wrap.png";
    private static final String FILE_EAN13_TITLE_PLUS_HRI           = "ean13_title_plus_hri.png";
    private static final String FILE_CODE128_TINY_LABEL             = "code128_tiny_label.png";
    private static final String FILE_CODE128_MATCH_BAR_COLOR        = "code128_match_bar_color.png";
    private static final String FILE_QR_INCH_GAP_POSTER             = "qr_inch_gap_poster.png";
    private static final String FILE_ABOVE_VS_BELOW_SPACING         = "code128_above_vs_below_spacing.png";

    @BeforeClass
    public void setUp() throws Exception {
        LicenseAssist.setupLicense();
    }

    /**
     * QR with caption used as the ONLY human-readable text (CodeText hidden).
     *
     * Shows:
     * - CodeTextParameters.Location = NONE (do not render the internal code text).
     * - CaptionAbove visible and used as a title block.
     * - Caption size in typographic points (pt) using FontUnit -> Size -> setPoint(..).
     * Expected: one QR with payload "QR-TITLE" and a visible title above.
     */
    @Test
    public void qr_captionTitle_only() throws Exception {
        final String payload = "QR-TITLE";
        BarcodeGenerator generator = new BarcodeGenerator(EncodeTypes.QR, payload);
        generator.getParameters().getBarcode().getCodeTextParameters().setLocation(CodeLocation.NONE);

        CaptionParameters captionTop = generator.getParameters().getCaptionAbove();
        captionTop.setVisible(true);
        captionTop.setText("SCAN ME");
        captionTop.getFont().getSize().setPoint(14);
        captionTop.setAlignment(TextAlignment.CENTER);
        captionTop.getPadding().getBottom().setPoint(8); // gap from caption to bars

        generator.getParameters().getImageWidth().setPixels(220);
        generator.getParameters().getImageHeight().setPixels(220);

        String fullPath = ExampleAssist.pathCombine(FOLDER, FILE_QR_TITLE_ONLY);
        generator.save(fullPath, BarCodeImageFormat.PNG);
        ExampleAssist.assertFileCreated(fullPath);

        assertImageHasBarcodes(fullPath, 1, List.of(expected(DecodeType.QR, payload)));
    }

    /**
     * CODE_128 with both captions: title ABOVE + note BELOW, CodeText hidden.
     *
     * Shows:
     * - Both captions are independent and can be shown simultaneously.
     * - Individual padding control for each caption (pt).
     * Expected: one CODE_128 "ABOVE-BELOW" with visible captions above and below.
     */
    @Test
    public void code128_captionAbove_and_captionBelow() throws Exception {
        final String payload = "ABOVE-BELOW";
        BarcodeGenerator generator = new BarcodeGenerator(EncodeTypes.CODE_128, payload);
        generator.getParameters().getBarcode().getCodeTextParameters().setLocation(CodeLocation.NONE);

        CaptionParameters capTop = generator.getParameters().getCaptionAbove();
        capTop.setVisible(true);
        capTop.setText("INVENTORY LABEL");
        capTop.getFont().getSize().setPoint(12);
        capTop.setAlignment(TextAlignment.CENTER);
        capTop.getPadding().getBottom().setPoint(6);

        CaptionParameters capBottom = generator.getParameters().getCaptionBelow();
        capBottom.setVisible(true);
        capBottom.setText("SKU-001-RED");
        capBottom.getFont().getSize().setPoint(10);
        capBottom.setAlignment(TextAlignment.CENTER);
        capBottom.getPadding().getTop().setPoint(8);

        generator.getParameters().getImageWidth().setPixels(420);
        generator.getParameters().getImageHeight().setPixels(220);

        String fullPath = ExampleAssist.pathCombine(FOLDER, FILE_CODE128_ABOVE_AND_BELOW);
        generator.save(fullPath, BarCodeImageFormat.PNG);
        ExampleAssist.assertFileCreated(fullPath);

        assertImageHasBarcodes(fullPath, 1, List.of(expected(DecodeType.CODE_128, payload)));
    }

    /**
     * Caption horizontal alignment comparison (LEFT vs CENTER vs RIGHT).
     *
     * Shows:
     * - TextAlignment control on CaptionBelow while CodeText is hidden.
     * Expected: one CODE_128 "ALIGN-LCR"; caption rendered at the requested alignment.
     */
    @Test
    public void code128_caption_alignment_left_center_right() throws Exception {
        final String payload = "ALIGN-LCR";
        BarcodeGenerator generator = new BarcodeGenerator(EncodeTypes.CODE_128, payload);
        generator.getParameters().getBarcode().getCodeTextParameters().setLocation(CodeLocation.NONE);

        CaptionParameters caption = generator.getParameters().getCaptionBelow();
        caption.setVisible(true);
        caption.setText("LEFT  •  CENTER  •  RIGHT");
        caption.getFont().getSize().setPoint(10);

        // Try different runs by changing alignment; default here: CENTER
        caption.setAlignment(TextAlignment.CENTER);
        caption.getPadding().getTop().setPoint(6);

        generator.getParameters().getImageWidth().setPixels(500);
        generator.getParameters().getImageHeight().setPixels(180);

        String fullPath = ExampleAssist.pathCombine(FOLDER, FILE_CODE128_ALIGN_LEFT_CENTER_RIGHT);
        generator.save(fullPath, BarCodeImageFormat.PNG);
        ExampleAssist.assertFileCreated(fullPath);

        assertImageHasBarcodes(fullPath, 1, List.of(expected(DecodeType.CODE_128, payload)));
    }

    /**
     * Caption font size in points (pt), DPI-agnostic typographic sizing.
     *
     * Shows:
     * - How FontUnit size in pt results in consistent typographic size (conversion inside the engine).
     * Expected: one CODE_128 with big caption below.
     */
    @Test
    public void code128_caption_font_size_in_points() throws Exception {
        final String payload = "FONT-PT";
        BarcodeGenerator generator = new BarcodeGenerator(EncodeTypes.CODE_128, payload);
        generator.getParameters().getBarcode().getCodeTextParameters().setLocation(CodeLocation.NONE);

        CaptionParameters caption = generator.getParameters().getCaptionBelow();
        caption.setVisible(true);
        caption.setText("CAPTION 14pt");
        caption.getFont().getSize().setPoint(14);
        caption.setAlignment(TextAlignment.CENTER);
        caption.getPadding().getTop().setPoint(8);

        generator.getParameters().getImageWidth().setPixels(420);
        generator.getParameters().getImageHeight().setPixels(200);

        String fullPath = ExampleAssist.pathCombine(FOLDER, FILE_CODE128_FONT_SIZE_PT);
        generator.save(fullPath, BarCodeImageFormat.PNG);
        ExampleAssist.assertFileCreated(fullPath);

        assertImageHasBarcodes(fullPath, 1, List.of(expected(DecodeType.CODE_128, payload)));
    }

    /**
     * Caption color + mixed-units padding (pt, mm, px).
     *
     * Shows:
     * - RGB caption color.
     * - Padding as Unit with different measurement systems.
     * Expected: one CODE_128 with teal caption below and clearly visible gaps.
     */
    @Test
    public void code128_caption_color_and_mixed_padding_units() throws Exception {
        final String payload = "COLOR-MIX";
        BarcodeGenerator generator = new BarcodeGenerator(EncodeTypes.CODE_128, payload);
        generator.getParameters().getBarcode().getCodeTextParameters().setLocation(CodeLocation.NONE);

        CaptionParameters caption = generator.getParameters().getCaptionBelow();
        caption.setVisible(true);
        caption.setText("BRAND CAPTION");
        caption.setTextColor(new Color(0, 128, 128));
        caption.getFont().getSize().setPoint(12);
        caption.setAlignment(TextAlignment.CENTER);
        caption.getPadding().getTop().setPoint(6);
        caption.getPadding().getLeft().setMillimeters(2.0f);
        caption.getPadding().getRight().setPixels(10);

        generator.getParameters().getImageWidth().setPixels(460);
        generator.getParameters().getImageHeight().setPixels(200);

        String fullPath = ExampleAssist.pathCombine(FOLDER, FILE_CODE128_COLOR_AND_PADDING_MIX);
        generator.save(fullPath, BarCodeImageFormat.PNG);
        ExampleAssist.assertFileCreated(fullPath);

        assertImageHasBarcodes(fullPath, 1, List.of(expected(DecodeType.CODE_128, payload)));
    }

    /**
     * DPI-sensitive spacing demo: same 10pt top gap at 203 vs 300 dpi.
     *
     * Shows:
     * - CaptionAbove bottom padding in points with DPI changed on the Unit,
     *   which affects px conversion (visual pixel gap differs).
     * Expected: one QR with payload "DPI-GAP" and a title; visually different px gap at another dpi setting.
     */
    @Test
    public void qr_caption_top_gap_dpi_sensitive() throws Exception {
        final String payload = "DPI-GAP";
        BarcodeGenerator generator = new BarcodeGenerator(EncodeTypes.QR, payload);
        generator.getParameters().getBarcode().getCodeTextParameters().setLocation(CodeLocation.NONE);

        CaptionParameters captionTop = generator.getParameters().getCaptionAbove();
        captionTop.setVisible(true);
        captionTop.setText("TOP GAP 10pt");
        captionTop.getFont().getSize().setPoint(12);
        captionTop.setAlignment(TextAlignment.CENTER);

        // Bottom padding in points; updateResolution will affect how many pixels 10pt becomes
        Unit bottomPad = captionTop.getPadding().getBottom();
        bottomPad.updateResolution(203f); // thermal printer dpi example
        bottomPad.setPoint(10f);

        generator.getParameters().getImageWidth().setPixels(220);
        generator.getParameters().getImageHeight().setPixels(220);

        String fullPath = ExampleAssist.pathCombine(FOLDER, FILE_QR_DPI_SENSITIVE_TOP_GAP);
        generator.save(fullPath, BarCodeImageFormat.PNG);
        ExampleAssist.assertFileCreated(fullPath);

        assertImageHasBarcodes(fullPath, 1, List.of(expected(DecodeType.QR, payload)));
    }

    /**
     * Multiline caption with wrapping control.
     *
     * Shows:
     * - Explicit line breaks with \n.
     * - NoWrap flag influence (false = allow wrapping).
     * Expected: one CODE_128 with two-line caption below the bars.
     */
    @Test
    public void code128_caption_multiline_with_wrap() throws Exception {
        final String payload = "MULTILINE";
        BarcodeGenerator generator = new BarcodeGenerator(EncodeTypes.CODE_128, payload);
        generator.getParameters().getBarcode().getCodeTextParameters().setLocation(CodeLocation.NONE);

        CaptionParameters caption = generator.getParameters().getCaptionBelow();
        caption.setVisible(true);
        caption.setText("PICK FACE\nAISLE 12");
        caption.setNoWrap(false); // allow wrapping if canvas gets tight
        caption.getFont().getSize().setPoint(11);
        caption.setAlignment(TextAlignment.CENTER);
        caption.getPadding().getTop().setPoint(8);

        generator.getParameters().getImageWidth().setPixels(360);
        generator.getParameters().getImageHeight().setPixels(220);

        String fullPath = ExampleAssist.pathCombine(FOLDER, FILE_CODE128_MULTILINE_WRAP);
        generator.save(fullPath, BarCodeImageFormat.PNG);
        ExampleAssist.assertFileCreated(fullPath);

        assertImageHasBarcodes(fullPath, 1, List.of(expected(DecodeType.CODE_128, payload)));
    }

    /**
     * EAN-13 with classic layout: title ABOVE + human-readable CodeText BELOW.
     *
     * Shows:
     * - CodeTextParameters.Location = BELOW (HRI).
     * - An additional caption at the top used as a title.
     * Expected: one EAN_13 "5901234123457" with both title and HRI rendered.
     */
    @Test
    public void ean13_title_above_plus_hri_below() throws Exception {
        final String code = "5901234123457";
        BarcodeGenerator generator = new BarcodeGenerator(EncodeTypes.EAN_13, code);
        generator.getParameters().getBarcode().getCodeTextParameters().setLocation(CodeLocation.BELOW);

        CaptionParameters captionTop = generator.getParameters().getCaptionAbove();
        captionTop.setVisible(true);
        captionTop.setText("PRODUCT LABEL");
        captionTop.getFont().getSize().setPoint(12);
        captionTop.setAlignment(TextAlignment.CENTER);
        captionTop.getPadding().getBottom().setPoint(6);

        generator.getParameters().getImageWidth().setPixels(380);
        generator.getParameters().getImageHeight().setPixels(220);

        String fullPath = ExampleAssist.pathCombine(FOLDER, FILE_EAN13_TITLE_PLUS_HRI);
        generator.save(fullPath, BarCodeImageFormat.PNG);
        ExampleAssist.assertFileCreated(fullPath);

        assertImageHasBarcodes(fullPath, 1, List.of(expected(DecodeType.EAN_13, code)));
    }

    /**
     * Tiny label: small caption with an exact pixel gap.
     *
     * Shows:
     * - Caption font size in pt with a very small gap specified in PIXELS (exact raster control).
     * Expected: one CODE_128 "TINY" with compact caption spacing.
     */
    @Test
    public void code128_tiny_caption_exact_pixel_gap() throws Exception {
        final String payload = "TINY";
        BarcodeGenerator generator = new BarcodeGenerator(EncodeTypes.CODE_128, payload);
        generator.getParameters().getBarcode().getCodeTextParameters().setLocation(CodeLocation.NONE);

        CaptionParameters caption = generator.getParameters().getCaptionBelow();
        caption.setVisible(true);
        caption.setText("LOT 24");
        caption.getFont().getSize().setPoint(8);
        caption.getPadding().getTop().setPixels(4);
        caption.setAlignment(TextAlignment.CENTER);

        generator.getParameters().getImageWidth().setPixels(320);
        generator.getParameters().getImageHeight().setPixels(180);

        String fullPath = ExampleAssist.pathCombine(FOLDER, FILE_CODE128_TINY_LABEL);
        generator.save(fullPath, BarCodeImageFormat.PNG);
        ExampleAssist.assertFileCreated(fullPath);

        assertImageHasBarcodes(fullPath, 1, List.of(expected(DecodeType.CODE_128, payload)));
    }

    /**
     * Caption color matches the bar color (brand-coherent look).
     *
     * Shows:
     * - Setting bar color and caption text color to the same RGB.
     * Expected: one CODE_128 with dark-gray bars and caption in the same color.
     */
    @Test
    public void code128_caption_color_matches_bar_color() throws Exception {
        final String payload = "MATCH-COLOR";
        BarcodeGenerator generator = new BarcodeGenerator(EncodeTypes.CODE_128, payload);
        generator.getParameters().getBarcode().getCodeTextParameters().setLocation(CodeLocation.NONE);

        Color brand = new Color(30, 30, 30);
        generator.getParameters().getBarcode().setBarColor(brand);

        CaptionParameters caption = generator.getParameters().getCaptionBelow();
        caption.setVisible(true);
        caption.setText("BRAND COLOR");
        caption.setTextColor(brand);
        caption.getFont().getSize().setPoint(12);
        caption.setAlignment(TextAlignment.CENTER);
        caption.getPadding().getTop().setPoint(8);

        generator.getParameters().getImageWidth().setPixels(420);
        generator.getParameters().getImageHeight().setPixels(200);

        String fullPath = ExampleAssist.pathCombine(FOLDER, FILE_CODE128_MATCH_BAR_COLOR);
        generator.save(fullPath, BarCodeImageFormat.PNG);
        ExampleAssist.assertFileCreated(fullPath);

        assertImageHasBarcodes(fullPath, 1, List.of(expected(DecodeType.CODE_128, payload)));
    }

    /**
     * Poster-style QR: quiet zones and caption gap specified in INCHES (print-ready).
     *
     * Shows:
     * - Using inches for caption padding, with explicit dpi on the Unit to get predictable pixels.
     * Expected: one QR "INCH-GAP" with a large, consistent physical gap above/below on print.
     */
    @Test
    public void qr_caption_gap_in_inches_print_ready() throws Exception {
        final String payload = "INCH-GAP";
        BarcodeGenerator generator = new BarcodeGenerator(EncodeTypes.QR, payload);
        generator.getParameters().getBarcode().getCodeTextParameters().setLocation(CodeLocation.NONE);

        CaptionParameters caption = generator.getParameters().getCaptionBelow();
        caption.setVisible(true);
        caption.setText("SCAN FOR MENU");
        caption.getFont().getSize().setPoint(16);
        caption.setAlignment(TextAlignment.CENTER);

        // 0.15" gap from bars to caption at 300 dpi
        Unit topGap = caption.getPadding().getTop();
        topGap.updateResolution(300f);
        topGap.setInches(0.15f);

        generator.getParameters().getImageWidth().setPixels(360);
        generator.getParameters().getImageHeight().setPixels(360);

        String fullPath = ExampleAssist.pathCombine(FOLDER, FILE_QR_INCH_GAP_POSTER);
        generator.save(fullPath, BarCodeImageFormat.PNG);
        ExampleAssist.assertFileCreated(fullPath);

        assertImageHasBarcodes(fullPath, 1, List.of(expected(DecodeType.QR, payload)));
    }

    /**
     * Compare spacing semantics: CaptionAbove.bottom vs CaptionBelow.top.
     *
     * Shows:
     * - Bottom padding under the TOP caption (space toward bars).
     * - Top padding above the BOTTOM caption (space toward bars).
     * Expected: one CODE_128 "ABOVE-BELOW-SPACING" with clearly separated captions.
     */
    @Test
    public void code128_above_vs_below_spacing_controls() throws Exception {
        final String payload = "ABOVE-BELOW-SPACING";
        BarcodeGenerator generator = new BarcodeGenerator(EncodeTypes.CODE_128, payload);
        generator.getParameters().getBarcode().getCodeTextParameters().setLocation(CodeLocation.NONE);

        CaptionParameters top = generator.getParameters().getCaptionAbove();
        top.setVisible(true);
        top.setText("TOP");
        top.getFont().getSize().setPoint(12);
        top.setAlignment(TextAlignment.CENTER);
        top.getPadding().getBottom().setPoint(6); // space under caption, toward bars

        CaptionParameters bottom = generator.getParameters().getCaptionBelow();
        bottom.setVisible(true);
        bottom.setText("BOTTOM");
        bottom.getFont().getSize().setPoint(12);
        bottom.setAlignment(TextAlignment.CENTER);
        bottom.getPadding().getTop().setPoint(10); // space above caption, toward bars

        generator.getParameters().getImageWidth().setPixels(460);
        generator.getParameters().getImageHeight().setPixels(240);

        String fullPath = ExampleAssist.pathCombine(FOLDER, FILE_ABOVE_VS_BELOW_SPACING);
        generator.save(fullPath, BarCodeImageFormat.PNG);
        ExampleAssist.assertFileCreated(fullPath);

        assertImageHasBarcodes(fullPath, 1, List.of(expected(DecodeType.CODE_128, payload)));
    }
}
