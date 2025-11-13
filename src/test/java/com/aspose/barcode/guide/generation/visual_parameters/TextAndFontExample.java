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
 * Human-Readable Text (HRI) & Fonts examples.
 *
 * What these tests cover:
 * - Placing the human-readable code text ABOVE/BELOW bars or hiding it (NONE).
 * - Controlling HRI font size (points), color, and alignment.
 * - Adjusting spacing (gap) between bars and HRI (via CodeTextParameters.Space).
 * - UTF-8/ECI for QR when you expect non-ASCII text (payload remains fully decodable).
 *
 * Conventions:
 * - No try-with-resources; do not close/Dispose readers/generators explicitly.
 * - Deterministic output paths under src/test/resources.
 * - Uses ExampleAssist helpers (path combining, assertions).
 */
public class TextAndFontExample {

    private static final String FOLDER =
            ExampleAssist.getOrCreateResourceFolderPath("generation", "visual_parameters", "text_and_fonts");

    // File names
    private static final String FILE_C128_BELOW_DEFAULT      = "c128_hri_below_default.png";
    private static final String FILE_C128_ABOVE_14PT_BLUE    = "c128_hri_above_14pt_blue.png";
    private static final String FILE_EAN13_BELOW_SPACE_PT    = "ean13_hri_below_space_pt.png";
    private static final String FILE_QR_ABOVE_UTF8_12PT      = "qr_hri_above_utf8_12pt.png";
    private static final String FILE_UPCA_HRI_NONE           = "upca_hri_none.png";
    private static final String FILE_C128_ALIGN_RIGHT        = "c128_hri_align_right.png";
    private static final String FILE_C128_FONT_8PT_VS_16PT_A = "c128_hri_8pt.png";
    private static final String FILE_C128_FONT_8PT_VS_16PT_B = "c128_hri_16pt.png";

    @BeforeClass
    public void setUp() throws Exception {
        LicenseAssist.setupLicense();
    }

    /**
     * # CODE_128: HRI BELOW with default font
     *
     * Shows:
     * - Rendering the human-readable code text under the bars via {@code CodeLocation.BELOW}.
     * - Keeping defaults for font/color (engine's standard rendering).
     * Expected: one CODE_128 with payload "HRI-BELOW"; HRI visible below the bars.
     */
    @Test
    public void code128_hriBelow_defaultFont() throws Exception {
        final String payload = "HRI-BELOW";
        BarcodeGenerator generator = new BarcodeGenerator(EncodeTypes.CODE_128, payload);

        // Place code text under the bars
        generator.getParameters().getBarcode().getCodeTextParameters().setLocation(CodeLocation.BELOW);

        // Modest canvas to make the effect visible
        generator.getParameters().getImageWidth().setPixels(360);
        generator.getParameters().getImageHeight().setPixels(180);

        String fullPath = ExampleAssist.pathCombine(FOLDER, FILE_C128_BELOW_DEFAULT);
        generator.save(fullPath, BarCodeImageFormat.PNG);
        ExampleAssist.assertFileCreated(fullPath);

        assertImageHasBarcodes(fullPath, 1, List.of(expected(DecodeType.CODE_128, payload)));
    }

    /**
     * # CODE_128: HRI ABOVE with custom font size (14pt) and color (blue)
     *
     * Shows:
     * - Placing HRI above the bars via {@code CodeLocation.ABOVE}.
     * - Setting font size in points through {@code CodeTextParameters.getFont().getSize().setPoint(..)}.
     * - Setting HRI text color via {@code CodeTextParameters.setColor(..)}.
     * Expected: one CODE_128 with payload "ABOVE-14PT-BLUE"; HRI above, larger and blue.
     */
    @Test
    public void code128_hriAbove_14pt_blue() throws Exception {
        final String payload = "ABOVE-14PT-BLUE";
        BarcodeGenerator generator = new BarcodeGenerator(EncodeTypes.CODE_128, payload);

        CodetextParameters codeText = generator.getParameters().getBarcode().getCodeTextParameters();
        codeText.setLocation(CodeLocation.ABOVE);
        codeText.getFont().getSize().setPoint(14); // 14pt text
        codeText.setColor(Color.BLUE);             // blue HRI

        generator.getParameters().getImageWidth().setPixels(420);
        generator.getParameters().getImageHeight().setPixels(200);

        String fullPath = ExampleAssist.pathCombine(FOLDER, FILE_C128_ABOVE_14PT_BLUE);
        generator.save(fullPath, BarCodeImageFormat.PNG);
        ExampleAssist.assertFileCreated(fullPath);

        assertImageHasBarcodes(fullPath, 1, List.of(expected(DecodeType.CODE_128, payload)));
    }

    /**
     * # EAN-13: HRI BELOW with extra spacing in typographic points (pt)
     *
     * Shows:
     * - HRI BELOW with an additional gap controlled via {@code CodeTextParameters.Space}.
     * - The gap is set in points (pt), internally converted to pixels using the {@link Unit}'s DPI.
     * Expected: one EAN_13 "5901234123457"; HRI rendered below with a visible extra gap from bars.
     */
    @Test
    public void ean13_hriBelow_withPointSpace() throws Exception {
        final String code = "5901234123457";
        BarcodeGenerator generator = new BarcodeGenerator(EncodeTypes.EAN_13, code);

        CodetextParameters codeText = generator.getParameters().getBarcode().getCodeTextParameters();
        codeText.setLocation(CodeLocation.BELOW);

        // Additional gap (if CodeTextParameters.Space is available in your SDK)
        // If your build doesn't expose Space, switch to increasing overall barcode bottom padding instead.
        Unit hriGap = codeText.getSpace();
        hriGap.setPoint(10f); // 10pt extra gap between bars and HRI

        generator.getParameters().getImageWidth().setPixels(360);
        generator.getParameters().getImageHeight().setPixels(200);

        String fullPath = ExampleAssist.pathCombine(FOLDER, FILE_EAN13_BELOW_SPACE_PT);
        generator.save(fullPath, BarCodeImageFormat.PNG);
        ExampleAssist.assertFileCreated(fullPath);

        assertImageHasBarcodes(fullPath, 1, List.of(expected(DecodeType.EAN_13, code)));
    }

    /**
     * # QR: HRI ABOVE with UTF-8/ECI and 12pt font
     *
     * Shows:
     * - QR payload with explicit UTF-8 ECI (robust non-ASCII path if needed).
     * - HRI ABOVE with 12pt font.
     * Expected: one QR "QR-UTF8"; HRI above; decode returns the same payload text.
     */
    @Test
    public void qr_hriAbove_utf8_12pt() throws Exception {
        final String payload = "QR-UTF8";
        BarcodeGenerator generator = new BarcodeGenerator(EncodeTypes.QR, payload);

        generator.getParameters().getBarcode().getQR().setQrECIEncoding(ECIEncodings.UTF8);

        CodetextParameters codeText = generator.getParameters().getBarcode().getCodeTextParameters();
        codeText.setLocation(CodeLocation.ABOVE);
        codeText.getFont().getSize().setPoint(12); // 12pt

        generator.getParameters().getImageWidth().setPixels(240);
        generator.getParameters().getImageHeight().setPixels(240);

        String fullPath = ExampleAssist.pathCombine(FOLDER, FILE_QR_ABOVE_UTF8_12PT);
        generator.save(fullPath, BarCodeImageFormat.PNG);
        ExampleAssist.assertFileCreated(fullPath);

        assertImageHasBarcodes(fullPath, 1, List.of(expected(DecodeType.QR, payload)));
    }

    /**
     * # UPC-A: HRI hidden (NONE)
     *
     * Shows:
     * - Turning HRI off with {@code CodeLocation.NONE}; bars only.
     * - Useful when you plan to add captions manually or render text elsewhere.
     * Expected: one UPCA "042100005264"; no human-readable text is drawn by the engine.
     */
    @Test
    public void upca_hri_none() throws Exception {
        final String code = "042100005264";
        BarcodeGenerator generator = new BarcodeGenerator(EncodeTypes.UPCA, code);

        generator.getParameters().getBarcode().getCodeTextParameters().setLocation(CodeLocation.NONE);

        generator.getParameters().getImageWidth().setPixels(320);
        generator.getParameters().getImageHeight().setPixels(160);

        String fullPath = ExampleAssist.pathCombine(FOLDER, FILE_UPCA_HRI_NONE);
        generator.save(fullPath, BarCodeImageFormat.PNG);
        ExampleAssist.assertFileCreated(fullPath);

        assertImageHasBarcodes(fullPath, 1, List.of(expected(DecodeType.UPCA, code)));
    }

    /**
     * # CODE_128: HRI alignment = RIGHT
     *
     * Shows:
     * - Horizontal alignment of the HRI line via {@code CodeTextParameters.setAlignment(..)}.
     * - Here we push the HRI to the right side under the bars.
     * Expected: one CODE_128 "ALIGN-R"; HRI below and right-aligned.
     */
    @Test
    public void code128_hri_align_right() throws Exception {
        final String payload = "ALIGN-R";
        BarcodeGenerator generator = new BarcodeGenerator(EncodeTypes.CODE_128, payload);

        CodetextParameters codeText = generator.getParameters().getBarcode().getCodeTextParameters();
        codeText.setLocation(CodeLocation.BELOW);
        codeText.setAlignment(TextAlignment.RIGHT);

        generator.getParameters().getImageWidth().setPixels(380);
        generator.getParameters().getImageHeight().setPixels(180);

        String fullPath = ExampleAssist.pathCombine(FOLDER, FILE_C128_ALIGN_RIGHT);
        generator.save(fullPath, BarCodeImageFormat.PNG);
        ExampleAssist.assertFileCreated(fullPath);

        assertImageHasBarcodes(fullPath, 1, List.of(expected(DecodeType.CODE_128, payload)));
    }

    /**
     * # CODE_128: Compare HRI readability at 8pt vs 16pt
     *
     * Shows:
     * - Two renders with the same payload while changing only the HRI font size (8pt vs 16pt).
     * - Useful as a visual guideline for minimum readable font sizes in your output medium.
     * Expected: both images decode as CODE_128 "FONT-COMPARE"; HRI visibly different in size.
     */
    @Test
    public void code128_hri_fontSize_8pt_vs_16pt() throws Exception {
        final String payload = "FONT-COMPARE";

        // 8pt
        BarcodeGenerator generatorSmall = new BarcodeGenerator(EncodeTypes.CODE_128, payload);
        CodetextParameters smallText = generatorSmall.getParameters().getBarcode().getCodeTextParameters();
        smallText.setLocation(CodeLocation.BELOW);
        smallText.getFont().getSize().setPoint(8);

        generatorSmall.getParameters().getImageWidth().setPixels(360);
        generatorSmall.getParameters().getImageHeight().setPixels(180);

        String pathSmall = ExampleAssist.pathCombine(FOLDER, FILE_C128_FONT_8PT_VS_16PT_A);
        generatorSmall.save(pathSmall, BarCodeImageFormat.PNG);
        ExampleAssist.assertFileCreated(pathSmall);
        assertImageHasBarcodes(pathSmall, 1, List.of(expected(DecodeType.CODE_128, payload)));

        // 16pt
        BarcodeGenerator generatorBig = new BarcodeGenerator(EncodeTypes.CODE_128, payload);
        CodetextParameters bigText = generatorBig.getParameters().getBarcode().getCodeTextParameters();
        bigText.setLocation(CodeLocation.BELOW);
        bigText.getFont().getSize().setPoint(16);

        generatorBig.getParameters().getImageWidth().setPixels(360);
        generatorBig.getParameters().getImageHeight().setPixels(180);

        String pathBig = ExampleAssist.pathCombine(FOLDER, FILE_C128_FONT_8PT_VS_16PT_B);
        generatorBig.save(pathBig, BarCodeImageFormat.PNG);
        ExampleAssist.assertFileCreated(pathBig);
        assertImageHasBarcodes(pathBig, 1, List.of(expected(DecodeType.CODE_128, payload)));
    }
}
