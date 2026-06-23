package com.aspose.barcode.guide.generation.visual_parameters;

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
 * Examples focused on coloring different visual elements of a barcode image:
 * <ul>
 *   <li>Bars (foreground) via {@code BarcodeParameters.setBarColor(Color)}</li>
 *   <li>Background via {@code BaseGenerationParameters.setBackColor(Color)}</li>
 *   <li>Caption colors via {@code CaptionParameters.setTextColor(Color)}</li>
 *   <li>Transparent and semi-transparent backgrounds (PNG alpha)</li>
 * </ul>
 *
 * Notes:
 * <ul>
 *   <li>Keep strong contrast: dark bars on light background.</li>
 *   <li>With transparency, ensure the final composite surface is light enough around the code.</li>
 *   <li>No try-with-resources; do not call close()/dispose() per project policy.</li>
 * </ul>
 */
public class ColorsExample {

    private static final String FOLDER =
            ExampleAssist.getOrCreateResourceFolderPath("generation", "visual_parameters", "colors");

    private static final String FILE_C128_BRAND_PALETTE      = "c128_brand_palette.png";
    private static final String FILE_QR_CUSTOM_COLORS        = "qr_custom_colors.png";
    private static final String FILE_EAN13_CAPTION_ABOVE     = "ean13_caption_above_color.png";
    private static final String FILE_EAN13_CAPTION_DARKBG    = "ean13_caption_below_on_dark_bg.png";
    private static final String FILE_PDF417_COLORS           = "pdf417_colors.png";
    private static final String FILE_UPCA_BACKGROUND_ONLY    = "upca_background_only.png";
    private static final String FILE_QR_TRANSPARENT_BG       = "qr_transparent_bg.png";
    // NEW:
    private static final String FILE_C128_BRAND_TUNABLE      = "c128_brand_tunable.png";
    private static final String FILE_QR_SEMI_TRANSPARENT_BG  = "qr_semi_transparent_bg.png";

    // ---- Brand palette placeholders (tune to your brand guide) ----
    private static final Color THEME_PRIMARY_DARK = new Color(0, 64, 128);    // bars or accents
    private static final Color THEME_IVORY_LIGHT = new Color(250, 245, 230); // background

    @BeforeClass
    public void setUp() throws Exception {
        LicenseAssist.setupLicense();
    }

    /**
     * # CODE 128: brand-like palette (dark blue bars on warm light background)
     *
     * Shows:
     * - How to set bars (foreground) color.
     * - How to set image background color.
     * - Why generous quiet zones are important when changing colors.
     *
     * Expected: one CODE_128 with the given text, fully decodable.
     */
    @Test
    public void code128_brandPalette_colors() throws Exception {
        String codeText = "COLOR-C128";
        BarcodeGenerator generator = new BarcodeGenerator(EncodeTypes.CODE_128, codeText);

        generator.getParameters().getBarcode().setBarColor(new Color(0, 64, 128));     // dark blue
        generator.getParameters().setBackColor(new Color(250, 245, 230));              // warm ivory

        generator.getParameters().getBarcode().getXDimension().setPixels(3.0f);
        generator.getParameters().getBarcode().getBarHeight().setPixels(110);
        generator.getParameters().getBarcode().getPadding().getLeft().setPixels(16);
        generator.getParameters().getBarcode().getPadding().getRight().setPixels(16);
        generator.getParameters().getBarcode().getPadding().getTop().setPixels(10);
        generator.getParameters().getBarcode().getPadding().getBottom().setPixels(10);

        generator.getParameters().getImageWidth().setPixels(540);
        generator.getParameters().getImageHeight().setPixels(220);

        String fullPath = ExampleAssist.pathCombine(FOLDER, FILE_C128_BRAND_PALETTE);
        generator.save(fullPath, BarCodeImageFormat.PNG);
        ExampleAssist.assertFileCreated(fullPath);

        assertImageHasBarcodes(fullPath, 1, List.of(expected(DecodeType.CODE_128, codeText)));
    }

    /**
     * # QR: custom colors with safe contrast
     *
     * Shows:
     * - Coloring a 2D symbol (QR) with dark foreground and light background.
     * - Keeping quiet zones and a comfortable canvas for robust decoding.
     *
     * Expected: one QR with the given text.
     */
    @Test
    public void qr_customColors_safeContrast() throws Exception {
        String codeText = "QR-COLORS";
        BarcodeGenerator generator = new BarcodeGenerator(EncodeTypes.QR, codeText);

        generator.getParameters().getBarcode().setBarColor(new Color(20, 20, 20));
        generator.getParameters().setBackColor(new Color(245, 248, 255));

        generator.getParameters().getBarcode().getXDimension().setPixels(3.0f);
        generator.getParameters().getBarcode().getQR().setErrorLevel(QRErrorLevel.LEVEL_M);

        generator.getParameters().getBarcode().getPadding().getLeft().setPixels(16);
        generator.getParameters().getBarcode().getPadding().getRight().setPixels(16);
        generator.getParameters().getBarcode().getPadding().getTop().setPixels(16);
        generator.getParameters().getBarcode().getPadding().getBottom().setPixels(16);
        generator.getParameters().getImageWidth().setPixels(240);
        generator.getParameters().getImageHeight().setPixels(240);

        String fullPath = ExampleAssist.pathCombine(FOLDER, FILE_QR_CUSTOM_COLORS);
        generator.save(fullPath, BarCodeImageFormat.PNG);
        ExampleAssist.assertFileCreated(fullPath);

        assertImageHasBarcodes(fullPath, 1, List.of(expected(DecodeType.QR, codeText)));
    }

    /**
     * # EAN-13: Caption Above with custom text color (bars stay black on white)
     *
     * Shows:
     * - Caption Above via getCaptionAbove().
     * - Caption text color independent from bar/background colors.
     * - Font size in points and caption padding.
     *
     * Expected: one EAN_13; caption rendered above in the chosen color.
     */
    @Test
    public void ean13_captionAbove_colored() throws Exception {
        String codeText = "5901234123457";
        BarcodeGenerator generator = new BarcodeGenerator(EncodeTypes.EAN_13, codeText);

        generator.getParameters().getBarcode().getCodeTextParameters().setLocation(CodeLocation.NONE);

        CaptionParameters caption = generator.getParameters().getCaptionAbove();
        caption.setVisible(true);
        caption.setText("CAPTION-ABOVE");
        caption.getFont().getSize().setPoint(12f);
        caption.setTextColor(new Color(160, 40, 40));
        caption.getPadding().getTop().setPoint(6f);
        caption.getPadding().getBottom().setPoint(6f);

        generator.getParameters().getBarcode().getXDimension().setPixels(2.0f);
        generator.getParameters().getBarcode().getBarHeight().setPixels(110);
        generator.getParameters().getImageWidth().setPixels(420);
        generator.getParameters().getImageHeight().setPixels(240);

        String fullPath = ExampleAssist.pathCombine(FOLDER, FILE_EAN13_CAPTION_ABOVE);
        generator.save(fullPath, BarCodeImageFormat.PNG);
        ExampleAssist.assertFileCreated(fullPath);

        assertImageHasBarcodes(fullPath, 1, List.of(expected(DecodeType.EAN_13, codeText)));
    }

    /**
     * # EAN-13: Caption Below on a dark background (white caption)
     *
     * Shows:
     * - Caption Below on a non-white background.
     * - Picking caption color for readability (white on dark).
     * - Keeping bars darker than background for decodability.
     *
     * Expected: one EAN_13; caption below in white, readable over dark background.
     */
    @Test
    public void ean13_captionBelow_onDarkBackground_whiteText() throws Exception {
        String codeText = "5901234123457";
        BarcodeGenerator generator = new BarcodeGenerator(EncodeTypes.EAN_13, codeText);

        generator.getParameters().setBackColor(new Color(42, 53, 64));                 // dark slate background
        generator.getParameters().getBarcode().setBarColor(new Color(10, 10, 10));     // very dark bars

        generator.getParameters().getBarcode().getCodeTextParameters().setLocation(CodeLocation.NONE);

        CaptionParameters caption = generator.getParameters().getCaptionBelow();
        caption.setVisible(true);
        caption.setText("CAPTION-BELOW");
        caption.getFont().getSize().setPoint(12f);
        caption.setTextColor(Color.WHITE);
        caption.getPadding().getTop().setPoint(6f);
        caption.getPadding().getBottom().setPoint(6f);

        generator.getParameters().getBarcode().getXDimension().setPixels(2.0f);
        generator.getParameters().getBarcode().getBarHeight().setPixels(110);
        generator.getParameters().getImageWidth().setPixels(420);
        generator.getParameters().getImageHeight().setPixels(240);

        String fullPath = ExampleAssist.pathCombine(FOLDER, FILE_EAN13_CAPTION_DARKBG);
        generator.save(fullPath, BarCodeImageFormat.PNG);
        ExampleAssist.assertFileCreated(fullPath);

        assertImageHasBarcodes(fullPath, 1, List.of(expected(DecodeType.EAN_13, codeText)));
    }

    /**
     * # PDF417: custom bar/background + caption color
     *
     * Shows:
     * - Coloring a stacked symbology (PDF417) and adding a colored caption.
     * - Coordinating palette while preserving contrast.
     *
     * Expected: one PDF_417 with the given text.
     */
    @Test
    public void pdf417_customColors_withCaption() throws Exception {
        String codeText = "PDF417-COLORS";
        BarcodeGenerator generator = new BarcodeGenerator(EncodeTypes.PDF_417, codeText);

        generator.getParameters().getBarcode().setBarColor(new Color(90, 20, 20));     // dark burgundy bars
        generator.getParameters().setBackColor(new Color(255, 252, 240));              // light ivory background

        generator.getParameters().getBarcode().getCodeTextParameters().setLocation(CodeLocation.NONE);
        CaptionParameters caption = generator.getParameters().getCaptionAbove();
        caption.setVisible(true);
        caption.setText("PDF417 DEMO");
        caption.getFont().getSize().setPoint(12f);
        caption.setTextColor(new Color(120, 40, 40));
        caption.getPadding().getBottom().setPoint(6f);

        generator.getParameters().getBarcode().getXDimension().setPixels(3.0f);
        generator.getParameters().getBarcode().getPdf417().setRows(8);
        generator.getParameters().getBarcode().getPdf417().setColumns(4);

        generator.getParameters().getImageWidth().setPixels(520);
        generator.getParameters().getImageHeight().setPixels(260);

        String fullPath = ExampleAssist.pathCombine(FOLDER, FILE_PDF417_COLORS);
        generator.save(fullPath, BarCodeImageFormat.PNG);
        ExampleAssist.assertFileCreated(fullPath);

        assertImageHasBarcodes(fullPath, 1, List.of(expected(DecodeType.PDF_417, codeText)));
    }

    /**
     * # UPC-A: background-only change (bars remain black)
     *
     * Shows:
     * - How to change only the background color while keeping default black bars.
     * - That decodability remains intact if contrast is sufficient.
     *
     * Expected: one UPCA with the given text.
     */
    @Test
    public void upca_backgroundOnly_pastel() throws Exception {
        String codeText = "042100005264";
        BarcodeGenerator generator = new BarcodeGenerator(EncodeTypes.UPCA, codeText);

        generator.getParameters().setBackColor(new Color(255, 248, 230)); // soft pastel

        generator.getParameters().getBarcode().getXDimension().setPixels(2.0f);
        generator.getParameters().getBarcode().getBarHeight().setPixels(110);
        generator.getParameters().getBarcode().getPadding().getLeft().setPixels(14);
        generator.getParameters().getBarcode().getPadding().getRight().setPixels(14);
        generator.getParameters().getImageWidth().setPixels(360);
        generator.getParameters().getImageHeight().setPixels(200);

        String fullPath = ExampleAssist.pathCombine(FOLDER, FILE_UPCA_BACKGROUND_ONLY);
        generator.save(fullPath, BarCodeImageFormat.PNG);
        ExampleAssist.assertFileCreated(fullPath);

        assertImageHasBarcodes(fullPath, 1, List.of(expected(DecodeType.UPCA, codeText)));
    }

    /**
     * # QR: transparent background (ARGB) for overlay usage
     *
     * Shows:
     * - Using an ARGB Color with alpha=0 for the background to produce a PNG with transparency.
     * - Keeping bars dark and adding sufficient quiet zone, since the final composite background is unknown.
     *
     * Caveats:
     * - Ensure the final surface where the PNG is placed is light enough around the code area.
     * - Some pipelines flatten transparency; verify in your export/print flow.
     *
     * Expected: one QR with the given text.
     */
    @Test
    public void qr_transparentBackground_argb() throws Exception {
        String codeText = "QR-ALPHA";
        BarcodeGenerator generator = new BarcodeGenerator(EncodeTypes.QR, codeText);

        generator.getParameters().setBackColor(new Color(255, 255, 255, 0));   // fully transparent
        generator.getParameters().getBarcode().setBarColor(new Color(15, 15, 15));

        generator.getParameters().getBarcode().getXDimension().setPixels(3.5f);
        generator.getParameters().getBarcode().getPadding().getLeft().setPixels(16);
        generator.getParameters().getBarcode().getPadding().getRight().setPixels(16);
        generator.getParameters().getBarcode().getPadding().getTop().setPixels(16);
        generator.getParameters().getBarcode().getPadding().getBottom().setPixels(16);
        generator.getParameters().getImageWidth().setPixels(260);
        generator.getParameters().getImageHeight().setPixels(260);

        String fullPath = ExampleAssist.pathCombine(FOLDER, FILE_QR_TRANSPARENT_BG);
        generator.save(fullPath, BarCodeImageFormat.PNG); // PNG supports alpha
        ExampleAssist.assertFileCreated(fullPath);

        assertImageHasBarcodes(fullPath, 1, List.of(expected(DecodeType.QR, codeText)));
    }

    // ===================== NEW CASES =====================

    /**
     * # CODE 128: tunable brand palette (centralized constants)
     *
     * Shows:
     * - How to centralize "brand" colors in constants and reuse them across tests.
     * - One place to adjust when the company palette changes.
     *
     * Expected: one CODE_128; bars use BRAND_PRIMARY_DARK, background uses BRAND_IVORY_LIGHT.
     */
    @Test
    public void code128_brandPalette_tunableConstants() throws Exception {
        String codeText = "BRAND-TUNABLE";
        BarcodeGenerator generator = new BarcodeGenerator(EncodeTypes.CODE_128, codeText);

        generator.getParameters().getBarcode().setBarColor(THEME_PRIMARY_DARK);
        generator.getParameters().setBackColor(THEME_IVORY_LIGHT);

        generator.getParameters().getBarcode().getXDimension().setPixels(3.0f);
        generator.getParameters().getBarcode().getBarHeight().setPixels(110);
        generator.getParameters().getBarcode().getPadding().getLeft().setPixels(16);
        generator.getParameters().getBarcode().getPadding().getRight().setPixels(16);
        generator.getParameters().getImageWidth().setPixels(540);
        generator.getParameters().getImageHeight().setPixels(220);

        String fullPath = ExampleAssist.pathCombine(FOLDER, FILE_C128_BRAND_TUNABLE);
        generator.save(fullPath, BarCodeImageFormat.PNG);
        ExampleAssist.assertFileCreated(fullPath);

        assertImageHasBarcodes(fullPath, 1, List.of(expected(DecodeType.CODE_128, codeText)));
    }

    /**
     * # QR: semi-transparent background (alpha ~ 40%)
     *
     * Shows:
     * - Using a partially transparent background (ARGB with alpha ~ 102/255 ≈ 40%).
     * - Keeping quiet zone larger because final composite may be mid-tone.
     *
     * Hint:
     * - Adjust alpha if your compositor tends to darken/lighten the final image after export.
     *
     * Expected: one QR with the given text.
     */
    @Test
    public void qr_semiTransparentBackground_alpha40() throws Exception {
        String codeText = "QR-ALPHA40";
        BarcodeGenerator generator = new BarcodeGenerator(EncodeTypes.QR, codeText);

        // ~40% opacity white (alpha 102 of 255)
        generator.getParameters().setBackColor(new Color(255, 255, 255, 102));
        generator.getParameters().getBarcode().setBarColor(new Color(18, 18, 18));

        generator.getParameters().getBarcode().getXDimension().setPixels(3.5f);
        generator.getParameters().getBarcode().getQR().setErrorLevel(QRErrorLevel.LEVEL_M);
        generator.getParameters().getBarcode().getPadding().getLeft().setPixels(20);
        generator.getParameters().getBarcode().getPadding().getRight().setPixels(20);
        generator.getParameters().getBarcode().getPadding().getTop().setPixels(20);
        generator.getParameters().getBarcode().getPadding().getBottom().setPixels(20);
        generator.getParameters().getImageWidth().setPixels(280);
        generator.getParameters().getImageHeight().setPixels(280);

        String fullPath = ExampleAssist.pathCombine(FOLDER, FILE_QR_SEMI_TRANSPARENT_BG);
        generator.save(fullPath, BarCodeImageFormat.PNG); // keep PNG for alpha channel
        ExampleAssist.assertFileCreated(fullPath);

        assertImageHasBarcodes(fullPath, 1, List.of(expected(DecodeType.QR, codeText)));
    }
}
