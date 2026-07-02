package com.aspose.barcode.guide.generation.visual_parameters;

import com.aspose.barcode.barcoderecognition.DecodeType;
import com.aspose.barcode.generation.*;
import com.aspose.barcode.guide.common.ExampleAssist;
import com.aspose.barcode.guide.common.LicenseAssist;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.awt.*;
import java.util.List;

import static com.aspose.barcode.guide.common.ExampleAssist.assertImageHasBarcodes;
import static com.aspose.barcode.guide.common.ExampleAssist.expected;

/**
 * Border customization examples:
 * <ul>
 *   <li>Global frame border: visibility, width as {@link Unit}, color, optional dash style</li>
 *   <li>Contrast against white/black backgrounds so the frame is clearly visible</li>
 *   <li>ITF-14 bearer bar types and thickness in millimeters with DPI conversion</li>
 * </ul>
 *
 * Conventions:
 * <ul>
 *   <li>No try-with-resources; no explicit close()/dispose()</li>
 *   <li>Deterministic outputs under {@code src/test/resources}</li>
 *   <li>Use {@link ExampleAssist} helpers</li>
 * </ul>
 */
public class BordersExample {

    private static final String FOLDER =
            ExampleAssist.getOrCreateResourceFolderPath("generation", "visual_parameters", "borders");

    // Output files
    private static final String FILE_C128_BORDER_WHITE_BG       = "c128_border_red_2pt_on_white.png";
    private static final String FILE_QR_BORDER_BLACK_BG_DASHED  = "qr_border_white_2pt_on_black_dashed.png";
    private static final String FILE_EAN13_BORDER_MM_300DPI     = "ean13_border_1mm_300dpi_blue.png";
    private static final String FILE_ITF14_FRAME_2_5MM_300DPI   = "itf14_frame_2_5mm_300dpi_gray.png";
    private static final String FILE_ITF14_BAR_OUT_2_0MM_DARKBG = "itf14_bar_out_2_0mm_300dpi_ivory_on_dark.png";

    @BeforeClass
    public void setUp() throws Exception {
        LicenseAssist.setupLicense();
    }

    /**
     * # CODE 128 on WHITE background with a RED solid frame (2pt)
     *
     * Shows:
     * <ul>
     *   <li>Global border enable via {@code getParameters().getBorder().setVisible(true)}</li>
     *   <li>Border width in points via {@link Unit#setPoint(float)}</li>
     *   <li>High-contrast red frame on white background via {@code getParameters().getBackColor().setColor(...)} and {@code border.setColor(...)} </li>
     * </ul>
     *
     * Expected: one CODE_128 reading "BORDER-RED-2PT", red frame clearly visible on white.
     */
    @Test
    public void code128_whiteBackground_redSolidBorder_2pt() throws Exception {
        final String codeText = "BORDER-RED-2PT";
        BarcodeGenerator generator = new BarcodeGenerator(EncodeTypes.CODE_128, codeText);

        // White background for maximum contrast with a red frame
        generator.getParameters().setBackColor(Color.WHITE);

        BorderParameters border = generator.getParameters().getBorder();
        border.setVisible(true);
        border.getWidth().setPoint(2.0f);                 // 2 pt frame
        border.setColor(new Color(0xE5, 0x2B, 0x2B));     // vivid red

        // Keep bars safely away from the frame
        generator.getParameters().getBarcode().getPadding().getLeft().setPixels(14);
        generator.getParameters().getBarcode().getPadding().getRight().setPixels(14);
        generator.getParameters().getBarcode().getPadding().getTop().setPixels(10);
        generator.getParameters().getBarcode().getPadding().getBottom().setPixels(10);

        generator.getParameters().getBarcode().getXDimension().setPixels(2.0f);
        generator.getParameters().getBarcode().getBarHeight().setPixels(110);

        generator.getParameters().getImageWidth().setPixels(520);
        generator.getParameters().getImageHeight().setPixels(220);

        String fullPath = ExampleAssist.pathCombine(FOLDER, FILE_C128_BORDER_WHITE_BG);
        generator.save(fullPath, BarCodeImageFormat.PNG);
        ExampleAssist.assertFileCreated(fullPath);

        assertImageHasBarcodes(fullPath, 1, List.of(expected(DecodeType.CODE_128, codeText)));
    }

    /**
     * Generates a QR Code with white modules and a white solid frame
     * on a black background.
     *
     * <p>Inverted QR Codes may not be recognized by all readers, so this
     * example verifies the generated image properties without requiring
     * successful barcode recognition.</p>
     */
    @Test
    public void qrOnBlackBackgroundWithWhiteBorder() throws Exception {
        String outputPath = ExampleAssist.pathCombine(
                FOLDER,
                "qr_white_border_on_black.png"
        );

        BarcodeGenerator generator = new BarcodeGenerator(
                EncodeTypes.QR,
                "QR-WHITE-BORDER"
        );

        generator.getParameters()
                .setBackColor(Color.BLACK);

        generator.getParameters()
                .getBarcode()
                .setBarColor(Color.WHITE);

        BorderParameters border =
                generator.getParameters().getBorder();

        border.setVisible(true);
        border.getWidth().setPoint(2.0f);
        border.setColor(Color.WHITE);

        generator.getParameters()
                .getBarcode()
                .getPadding()
                .getLeft()
                .setPixels(16);

        generator.getParameters()
                .getBarcode()
                .getPadding()
                .getRight()
                .setPixels(16);

        generator.getParameters()
                .getBarcode()
                .getPadding()
                .getTop()
                .setPixels(16);

        generator.getParameters()
                .getBarcode()
                .getPadding()
                .getBottom()
                .setPixels(16);

        generator.getParameters()
                .getImageWidth()
                .setPixels(260);

        generator.getParameters()
                .getImageHeight()
                .setPixels(260);

        generator.save(
                outputPath,
                BarCodeImageFormat.PNG
        );

        ExampleAssist.assertFileCreated(outputPath);
    }


    /**
     * # EAN-13 on WHITE background with a BLUE frame (1.0 mm @ 300 dpi)
     *
     * Shows:
     * <ul>
     *   <li>Physical thickness for the global frame: {@code setMillimeters(1.0f)} + {@code updateResolution(300f)}</li>
     *   <li>Blue frame on white to make the border obvious</li>
     * </ul>
     *
     * Expected: one EAN_13 "5901234123457", blue frame ~1.0 mm at 300 dpi.
     */
    @Test
    public void ean13_whiteBackground_blueBorder_1mm_at300dpi() throws Exception {
        final String codeText = "5901234123457";
        BarcodeGenerator generator = new BarcodeGenerator(EncodeTypes.EAN_13, codeText);

        generator.getParameters().setBackColor(Color.WHITE);

        BorderParameters border = generator.getParameters().getBorder();
        border.setVisible(true);
        Unit width = border.getWidth();
        width.updateResolution(300f);
        width.setMillimeters(1.0f);
        border.setColor(new Color(0x0B, 0x57, 0xD0)); // azure/blue

        generator.getParameters().getBarcode().getPadding().getLeft().setPixels(12);
        generator.getParameters().getBarcode().getPadding().getRight().setPixels(12);
        generator.getParameters().getBarcode().getPadding().getTop().setPixels(8);
        generator.getParameters().getBarcode().getPadding().getBottom().setPixels(8);

        generator.getParameters().getImageWidth().setPixels(360);
        generator.getParameters().getImageHeight().setPixels(200);

        String fullPath = ExampleAssist.pathCombine(FOLDER, FILE_EAN13_BORDER_MM_300DPI);
        generator.save(fullPath, BarCodeImageFormat.PNG);
        ExampleAssist.assertFileCreated(fullPath);

        assertImageHasBarcodes(fullPath, 1, List.of(expected(DecodeType.EAN_13, codeText)));
    }

    /**
     * # ITF-14 FRAME bearer on WHITE background, thickness 2.5 mm @ 300 dpi
     *
     * Shows:
     * <ul>
     *   <li>ITF-14 bearer bar type selection: {@code ITF14BorderType.FRAME}</li>
     *   <li>Bearer thickness in millimeters via {@link Unit} with DPI set to 300</li>
     *   <li>Quiet zone coefficient in multiples of XDimension (>=10 recommended)</li>
     * </ul>
     *
     * Expected: one ITF_14 with visible rectangular frame (~2.5 mm) on white background.
     */
    @Test
    public void itf14_frame_onWhite_2_5mm_at300dpi() throws Exception {
        final String codeText = "10012345000017";
        BarcodeGenerator generator = new BarcodeGenerator(EncodeTypes.ITF_14, codeText);

        generator.getParameters().setBackColor(Color.WHITE);

        ITFParameters itf = generator.getParameters().getBarcode().getITF();
        itf.setItfBorderType(ITF14BorderType.FRAME);

        Unit bearer = itf.getItfBorderThickness();
        bearer.updateResolution(300f);
        bearer.setMillimeters(2.5f);

        // Give decoder more margin
        itf.setQuietZoneCoef(12);

        generator.getParameters().getBarcode().getXDimension().setPixels(2.0f);
        generator.getParameters().getBarcode().getBarHeight().setPixels(110);

        // Global border can be used together if you also want a frame around the whole image,
        // but here we demonstrate the symbology-specific bearer only.
        generator.getParameters().getImageWidth().setPixels(520);
        generator.getParameters().getImageHeight().setPixels(260);

        String fullPath = ExampleAssist.pathCombine(FOLDER, FILE_ITF14_FRAME_2_5MM_300DPI);
        generator.save(fullPath, BarCodeImageFormat.PNG);
        ExampleAssist.assertFileCreated(fullPath);

        assertImageHasBarcodes(fullPath, 1, List.of(expected(DecodeType.ITF_14, codeText)));
    }

    /**
     * # ITF-14 BAR_OUT bearer on DARK background, thickness 2.0 mm @ 300 dpi
     *
     * Shows:
     * <ul>
     *   <li>Alternative bearer style: {@code ITF14BorderType.BAR_OUT}</li>
     *   <li>Thinner bearer (2.0 mm) and dark background to demonstrate contrast strategy</li>
     *   <li>Global frame border can also be enabled with a light color (optional); here we focus on the bearer</li>
     * </ul>
     *
     * Expected: one ITF_14 with external bearers BAR_OUT (~2.0 mm) clearly visible on a near-black background.
     */
    @Test
    public void itf14_barOut_onDark_2_0mm_at300dpi() throws Exception {
        final String codeText = "10012345000017";
        BarcodeGenerator generator = new BarcodeGenerator(EncodeTypes.ITF_14, codeText);

        // Very dark background so the light bearer stands out
        generator.getParameters().setBackColor(new Color(0x0A, 0x0A, 0x0A));

        ITFParameters itf = generator.getParameters().getBarcode().getITF();
        itf.setItfBorderType(ITF14BorderType.BAR_OUT);

        Unit bearer = itf.getItfBorderThickness();
        bearer.updateResolution(300f);
        bearer.setMillimeters(2.0f);

        itf.setQuietZoneCoef(12);

        generator.getParameters().getBarcode().getXDimension().setPixels(2.0f);
        generator.getParameters().getBarcode().getBarHeight().setPixels(110);

        generator.getParameters().getImageWidth().setPixels(520);
        generator.getParameters().getImageHeight().setPixels(260);

        String fullPath = ExampleAssist.pathCombine(FOLDER, FILE_ITF14_BAR_OUT_2_0MM_DARKBG);
        generator.save(fullPath, BarCodeImageFormat.PNG);
        ExampleAssist.assertFileCreated(fullPath);

        assertImageHasBarcodes(fullPath, 1, List.of(expected(DecodeType.ITF_14, codeText)));
    }
}
