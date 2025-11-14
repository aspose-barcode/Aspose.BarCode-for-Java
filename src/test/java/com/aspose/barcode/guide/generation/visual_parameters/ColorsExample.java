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
 * Examples focused on coloring different visual elements of a barcode image:
 * <ul>
 *   <li>Bars (foreground) color via {@code BarcodeParameters.setBarColor(Color)}</li>
 *   <li>Background color via {@code BaseGenerationParameters.setBackColor(Color)}</li>
 *   <li>Caption colors (Above/Below) via {@code CaptionParameters.setTextColor(Color)}</li>
 * </ul>
 *
 * <p>Notes:</p>
 * <ul>
 *   <li>Keep sufficient contrast (dark bars on a light background) to preserve decodability.</li>
 *   <li>Canvas/padding are adjusted to avoid clipping after style changes.</li>
 *   <li>No try-with-resources; do not call close()/dispose() per project policy.</li>
 * </ul>
 */
public class ColorsExample {

    private static final String FOLDER =
            ExampleAssist.getOrCreateResourceFolderPath("generation", "visual_parameters", "colors");

    private static final String FILE_C128_BRAND_PALETTE   = "c128_brand_palette.png";
    private static final String FILE_QR_CUSTOM_COLORS     = "qr_custom_colors.png";
    private static final String FILE_EAN13_CAPTION_ABOVE  = "ean13_caption_above_color.png";
    private static final String FILE_EAN13_CAPTION_DARKBG = "ean13_caption_below_on_dark_bg.png";
    private static final String FILE_PDF417_COLORS        = "pdf417_colors.png";

    @BeforeClass
    public void setUp() throws Exception {
        LicenseAssist.setupLicense();
    }

    /**
     * # CODE 128: brand-like palette (dark blue bars on warm light background)
     *
     * <b>Shows:</b>
     * <ul>
     *   <li>How to set bars (foreground) color with {@code setBarColor(Color)}.</li>
     *   <li>How to set image background color with {@code setBackColor(Color)}.</li>
     *   <li>How to keep a generous quiet zone to maintain contrast at edges.</li>
     * </ul>
     *
     * <b>Expected:</b> one {@code CODE_128} with the given text, fully decodable.
     */
    @Test
    public void code128_brandPalette_colors() throws Exception {
        String codeText = "COLOR-C128";
        BarcodeGenerator generator = new BarcodeGenerator(EncodeTypes.CODE_128, codeText);

        // Bars (foreground) and background colors (keep high contrast)
        generator.getParameters().getBarcode().setBarColor(new Color(0, 64, 128));     // dark blue
        generator.getParameters().setBackColor(new Color(250, 245, 230));              // warm ivory

        // Raster safety: x-dimension, bar height, padding, canvas
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
     * <b>Shows:</b>
     * <ul>
     *   <li>How to color a 2D symbol (QR) using dark foreground and light background.</li>
     *   <li>Why keeping quiet zones and a comfortable canvas helps recognition.</li>
     * </ul>
     *
     * <b>Expected:</b> one {@code QR} with the given text, fully decodable.
     */
    @Test
    public void qr_customColors_safeContrast() throws Exception {
        String codeText = "QR-COLORS";
        BarcodeGenerator generator = new BarcodeGenerator(EncodeTypes.QR, codeText);

        // Foreground/background
        generator.getParameters().getBarcode().setBarColor(new Color(20, 20, 20));     // near-black
        generator.getParameters().setBackColor(new Color(245, 248, 255));              // very light blueish

        // Typical QR robustness
        generator.getParameters().getBarcode().getXDimension().setPixels(3.0f);
        generator.getParameters().getBarcode().getQR().setQrErrorLevel(QRErrorLevel.LEVEL_M);

        // Quiet zones + canvas
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
     * <b>Shows:</b>
     * <ul>
     *   <li>How to show a custom caption above the bars using {@code getCaptionAbove()}.</li>
     *   <li>How to color the caption text independently via {@code setTextColor(Color)}.</li>
     *   <li>How to tune font size (in points) and padding around the caption.</li>
     * </ul>
     *
     * <b>Expected:</b> one {@code EAN_13}; caption rendered above in the chosen color.
     */
    @Test
    public void ean13_captionAbove_colored() throws Exception {
        String codeText = "5901234123457";
        BarcodeGenerator generator = new BarcodeGenerator(EncodeTypes.EAN_13, codeText);

        // Make the engine draw only the caption (no built-in code text)
        generator.getParameters().getBarcode().getCodeTextParameters().setLocation(CodeLocation.NONE);

        // Caption Above
        CaptionParameters caption = generator.getParameters().getCaptionAbove();
        caption.setVisible(true);
        caption.setText("CAPTION-ABOVE");
        caption.getFont().getSize().setPoint(12f);
        caption.setTextColor(new Color(160, 40, 40)); // dark red
        caption.getPadding().getTop().setPoint(6f);   // space above caption
        caption.getPadding().getBottom().setPoint(6f);// gap between caption and bars

        // Keep default black-on-white bars, set raster and canvas
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
     * <b>Shows:</b>
     * <ul>
     *   <li>How to place caption below the bars via {@code getCaptionBelow()}.</li>
     *   <li>How to pick a caption text color that stays legible on a dark background.</li>
     *   <li>How background color affects overall contrast and why bars must remain dark.</li>
     * </ul>
     *
     * <b>Expected:</b> one {@code EAN_13}; caption rendered below in white, readable over dark background.
     */
    @Test
    public void ean13_captionBelow_onDarkBackground_whiteText() throws Exception {
        String codeText = "5901234123457";
        BarcodeGenerator generator = new BarcodeGenerator(EncodeTypes.EAN_13, codeText);

        // Background darker (but still ensure bars are darker than background)
        generator.getParameters().setBackColor(new Color(42, 53, 64));                 // dark slate
        generator.getParameters().getBarcode().setBarColor(new Color(10, 10, 10));     // very dark bars

        // Hide built-in code text; use a custom caption instead
        generator.getParameters().getBarcode().getCodeTextParameters().setLocation(CodeLocation.NONE);

        // Caption below in white
        CaptionParameters caption = generator.getParameters().getCaptionBelow();
        caption.setVisible(true);
        caption.setText("CAPTION-BELOW");
        caption.getFont().getSize().setPoint(12f);
        caption.setTextColor(Color.WHITE);
        caption.getPadding().getTop().setPoint(6f);   // gap between bars and caption
        caption.getPadding().getBottom().setPoint(6f);

        // Raster + canvas
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
     * <b>Shows:</b>
     * <ul>
     *   <li>Coloring stacked symbology (PDF417) bars and background.</li>
     *   <li>Adding a colored caption Above for branding/titles.</li>
     * </ul>
     *
     * <b>Expected:</b> one {@code PDF_417} with the given text.
     */
    @Test
    public void pdf417_customColors_withCaption() throws Exception {
        String codeText = "PDF417-COLORS";
        BarcodeGenerator generator = new BarcodeGenerator(EncodeTypes.PDF_417, codeText);

        // Bars/background
        generator.getParameters().getBarcode().setBarColor(new Color(90, 20, 20));     // dark burgundy
        generator.getParameters().setBackColor(new Color(255, 252, 240));              // light ivory

        // Caption above in matching accent
        generator.getParameters().getBarcode().getCodeTextParameters().setLocation(CodeLocation.NONE);
        CaptionParameters caption = generator.getParameters().getCaptionAbove();
        caption.setVisible(true);
        caption.setText("PDF417 DEMO");
        caption.getFont().getSize().setPoint(12f);
        caption.setTextColor(new Color(120, 40, 40));
        caption.getPadding().getBottom().setPoint(6f);

        // Raster + canvas
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
}
