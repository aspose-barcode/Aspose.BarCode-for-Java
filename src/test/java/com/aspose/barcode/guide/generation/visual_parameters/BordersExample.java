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

import java.awt.*;
import java.util.List;

import static com.aspose.barcode.guide.common.ExampleAssist.assertImageHasBarcodes;
import static com.aspose.barcode.guide.common.ExampleAssist.expected;

/**
 * Examples focused on configuring borders:
 * <ul>
 *   <li>Global frame border around the barcode (visibility, width as Unit, color, dash style)</li>
 *   <li>Interplay between border and barcode padding (to avoid touching bars)</li>
 *   <li>ITF-14 bearer bar (border) types and thickness in physical units (mm @ DPI)</li>
 * </ul>
 */
public class BordersExample {

    private static final String FOLDER =
            ExampleAssist.getOrCreateResourceFolderPath("generation", "visual_parameters", "borders");

    // Filenames
    private static final String FILE_C128_BORDER_PT     = "c128_border_2pt_solid.png";
    private static final String FILE_EAN13_BORDER_MM    = "ean13_border_1mm_300dpi.png";
    private static final String FILE_QR_BORDER_DASHED   = "qr_border_dashed.png";
    private static final String FILE_ITF14_FRAME_MM     = "itf14_frame_bearer_2_5mm_300dpi.png";
    private static final String FILE_ITF14_BAR_OUT_MM   = "itf14_bar_out_bearer_2_0mm_300dpi.png";
    private static final String FILE_PADDING_VS_BORDER  = "code128_padding_vs_border.png";

    @BeforeClass
    public void setUp() throws Exception {
        LicenseAssist.setupLicense();
    }

    /**
     * # CODE 128: global frame border, width in typographic points
     *
     * <b>Shows:</b>
     * <ul>
     *   <li>How to enable the global frame border via {@code getParameters().getBorder().setVisible(true)}.</li>
     *   <li>How to set border width in points using {@link Unit} (independent from DPI for {@code setPixels}, but DPI matters for {@code setPoint}).</li>
     *   <li>How to set a custom border color.</li>
     *   <li>How to keep enough barcode padding so the frame does not touch bars.</li>
     * </ul>
     *
     * <b>Expected:</b> one {@code CODE_128} reading "BORDER-2PT", a visible solid frame around the image with ~2pt thickness.
     */
    @Test
    public void code128_globalBorder_pointsWidth() throws Exception {
        final String codeText = "BORDER-2PT";
        BarcodeGenerator generator = new BarcodeGenerator(EncodeTypes.CODE_128, codeText);

        // Border: visible, 2pt, dark gray color
        BorderParameters border = generator.getParameters().getBorder();
        border.setVisible(true);
        border.getWidth().setPoint(2.0f);
        border.setColor(new Color(0x33, 0x33, 0x33)); // #333333

        // Keep some padding so bars do not "stick" to the border
        generator.getParameters().getBarcode().getPadding().getLeft().setPixels(12);
        generator.getParameters().getBarcode().getPadding().getRight().setPixels(12);
        generator.getParameters().getBarcode().getPadding().getTop().setPixels(10);
        generator.getParameters().getBarcode().getPadding().getBottom().setPixels(10);

        generator.getParameters().getBarcode().getXDimension().setPixels(2.0f);
        generator.getParameters().getBarcode().getBarHeight().setPixels(110);

        generator.getParameters().getImageWidth().setPixels(520);
        generator.getParameters().getImageHeight().setPixels(220);

        String fullPath = ExampleAssist.pathCombine(FOLDER, FILE_C128_BORDER_PT);
        generator.save(fullPath, BarCodeImageFormat.PNG);
        ExampleAssist.assertFileCreated(fullPath);

        assertImageHasBarcodes(fullPath, 1, List.of(expected(DecodeType.CODE_128, codeText)));
    }

    /**
     * # EAN-13: global frame border, width in millimeters at 300 DPI
     *
     * <b>Shows:</b>
     * <ul>
     *   <li>Setting physical border thickness via {@code Unit.setMillimeters()} and controlling conversion with {@code Unit.updateResolution(300)}.</li>
     *   <li>How physical units map to pixels for the final raster (useful for print workflows).</li>
     * </ul>
     *
     * <b>Expected:</b> one {@code EAN_13} with a visible frame of ~1.0 mm thickness at 300 dpi.
     */
    @Test
    public void ean13_globalBorder_millimeters_at300dpi() throws Exception {
        final String codeText = "5901234123457";
        BarcodeGenerator generator = new BarcodeGenerator(EncodeTypes.EAN_13, codeText);

        BorderParameters border = generator.getParameters().getBorder();
        border.setVisible(true);
        Unit borderWidth = border.getWidth();
        borderWidth.updateResolution(300f);
        borderWidth.setMillimeters(1.0f);
        border.setColor(new Color(0x00, 0x00, 0x00));

        // Reasonable layout
        generator.getParameters().getBarcode().getPadding().getLeft().setPixels(12);
        generator.getParameters().getBarcode().getPadding().getRight().setPixels(12);
        generator.getParameters().getBarcode().getPadding().getTop().setPixels(8);
        generator.getParameters().getBarcode().getPadding().getBottom().setPixels(8);

        generator.getParameters().getImageWidth().setPixels(360);
        generator.getParameters().getImageHeight().setPixels(200);

        String fullPath = ExampleAssist.pathCombine(FOLDER, FILE_EAN13_BORDER_MM);
        generator.save(fullPath, BarCodeImageFormat.PNG);
        ExampleAssist.assertFileCreated(fullPath);

        assertImageHasBarcodes(fullPath, 1, List.of(expected(DecodeType.EAN_13, codeText)));
    }

    /**
     * # QR: dashed (non-solid) global frame border
     *
     * <b>Shows:</b>
     * <ul>
     *   <li>How to switch border dash style (e.g., to dashed/dotted) if your SDK exposes {@code BorderDashStyle}.</li>
     *   <li>Using a colored border around a 2D symbol.</li>
     * </ul>
     *
     * <b>Notes:</b> If your build lacks {@code setDashStyle}, keep the call commented and the test still demonstrates color + visibility.
     *
     * <b>Expected:</b> one {@code QR} with a (dashed) visible colored frame.
     */
    @Test
    public void qr_globalBorder_dashedStyle() throws Exception {
        final String codeText = "QR-BORDER-DASH";
        BarcodeGenerator generator = new BarcodeGenerator(EncodeTypes.QR, codeText);

        BorderParameters border = generator.getParameters().getBorder();
        border.setVisible(true);
        border.getWidth().setPoint(2.0f);
        border.setColor(new Color(0x00, 0x66, 0xCC)); // blue-ish

        // If available in your SDK:
        // border.setDashStyle(BorderDashStyle.DASH);

        generator.getParameters().getBarcode().getPadding().getLeft().setPixels(10);
        generator.getParameters().getBarcode().getPadding().getRight().setPixels(10);
        generator.getParameters().getBarcode().getPadding().getTop().setPixels(10);
        generator.getParameters().getBarcode().getPadding().getBottom().setPixels(10);

        generator.getParameters().getImageWidth().setPixels(240);
        generator.getParameters().getImageHeight().setPixels(240);

        String fullPath = ExampleAssist.pathCombine(FOLDER, FILE_QR_BORDER_DASHED);
        generator.save(fullPath, BarCodeImageFormat.PNG);
        ExampleAssist.assertFileCreated(fullPath);

        assertImageHasBarcodes(fullPath, 1, List.of(expected(DecodeType.QR, codeText)));
    }

    /**
     * # ITF-14: bearer bar type = FRAME, thickness in millimeters at 300 DPI
     *
     * <b>Shows:</b>
     * <ul>
     *   <li>How to select ITF-14 bearer bar type (FRAME) via {@code ITFParameters.setItfBorderType(...)}.</li>
     *   <li>How to set bearer thickness in mm with DPI-controlled conversion through {@link Unit} on {@code getItfBorderThickness()}.</li>
     *   <li>How to increase quiet zones via {@code setQuietZoneCoef(...)} (in multiples of XDimension).</li>
     * </ul>
     *
     * <b>Expected:</b> one {@code ITF_14} with visible rectangular frame (bearer) of ~2.5 mm at 300 dpi.
     */
    @Test
    public void itf14_bearerFrame_millimeters_at300dpi() throws Exception {
        final String codeText = "10012345000017"; // 14 digits including check
        BarcodeGenerator generator = new BarcodeGenerator(EncodeTypes.ITF_14, codeText);

        ITFParameters itf = generator.getParameters().getBarcode().getITF();
        itf.setItfBorderType(ITF14BorderType.FRAME);

        Unit bearerThickness = itf.getItfBorderThickness();
        bearerThickness.updateResolution(300f);
        bearerThickness.setMillimeters(2.5f);

        // Quiet zone in multiples of XDimension (>=10 recommended)
        itf.setQuietZoneCoef(12);

        generator.getParameters().getBarcode().getXDimension().setPixels(2.0f);
        generator.getParameters().getBarcode().getBarHeight().setPixels(110);

        generator.getParameters().getImageWidth().setPixels(520);
        generator.getParameters().getImageHeight().setPixels(260);

        String fullPath = ExampleAssist.pathCombine(FOLDER, FILE_ITF14_FRAME_MM);
        generator.save(fullPath, BarCodeImageFormat.PNG);
        ExampleAssist.assertFileCreated(fullPath);

        assertImageHasBarcodes(fullPath, 1, List.of(expected(DecodeType.ITF_14, codeText)));
    }

    /**
     * # ITF-14: bearer bar type = BAR_OUT, thickness in millimeters at 300 DPI
     *
     * <b>Shows:</b>
     * <ul>
     *   <li>Alternative bearer style (BAR_OUT) for ITF-14.</li>
     *   <li>Thickness in mm + DPI conversion again, but slightly thinner.</li>
     * </ul>
     *
     * <b>Expected:</b> one {@code ITF_14} with external bearer bars (BAR_OUT), ~2.0 mm at 300 dpi.
     */
    @Test
    public void itf14_bearerBarOut_millimeters_at300dpi() throws Exception {
        final String codeText = "10012345000017";
        BarcodeGenerator generator = new BarcodeGenerator(EncodeTypes.ITF_14, codeText);

        ITFParameters itf = generator.getParameters().getBarcode().getITF();
        itf.setItfBorderType(ITF14BorderType.BAR_OUT);

        Unit bearerThickness = itf.getItfBorderThickness();
        bearerThickness.updateResolution(300f);
        bearerThickness.setMillimeters(2.0f);

        itf.setQuietZoneCoef(12);

        generator.getParameters().getBarcode().getXDimension().setPixels(2.0f);
        generator.getParameters().getBarcode().getBarHeight().setPixels(110);

        generator.getParameters().getImageWidth().setPixels(520);
        generator.getParameters().getImageHeight().setPixels(260);

        String fullPath = ExampleAssist.pathCombine(FOLDER, FILE_ITF14_BAR_OUT_MM);
        generator.save(fullPath, BarCodeImageFormat.PNG);
        ExampleAssist.assertFileCreated(fullPath);

        assertImageHasBarcodes(fullPath, 1, List.of(expected(DecodeType.ITF_14, codeText)));
    }

    /**
     * # Padding vs global border: why enough padding matters
     *
     * <b>Shows:</b>
     * <ul>
     *   <li>That the global frame border is drawn around the content area; if padding is too small, the frame can visually "touch" bars.</li>
     *   <li>How to increase barcode padding to maintain a clean gap between bars and the frame.</li>
     * </ul>
     *
     * <b>Expected:</b> one {@code CODE_128}; the bars should not collide with the frame due to adequate padding.
     */
    @Test
    public void code128_paddingVsBorder_gapDemonstration() throws Exception {
        final String codeText = "PADDING-VS-BORDER";
        BarcodeGenerator generator = new BarcodeGenerator(EncodeTypes.CODE_128, codeText);

        // Border on
        BorderParameters border = generator.getParameters().getBorder();
        border.setVisible(true);
        border.getWidth().setPoint(2.0f);
        border.setColor(new Color(0x55, 0x55, 0x55));

        // Adequate padding so the frame does not overlap bars
        generator.getParameters().getBarcode().getPadding().getLeft().setPixels(16);
        generator.getParameters().getBarcode().getPadding().getRight().setPixels(16);
        generator.getParameters().getBarcode().getPadding().getTop().setPixels(12);
        generator.getParameters().getBarcode().getPadding().getBottom().setPixels(12);

        generator.getParameters().getBarcode().getXDimension().setPixels(2.0f);
        generator.getParameters().getBarcode().getBarHeight().setPixels(115);

        generator.getParameters().getImageWidth().setPixels(520);
        generator.getParameters().getImageHeight().setPixels(220);

        String fullPath = ExampleAssist.pathCombine(FOLDER, FILE_PADDING_VS_BORDER);
        generator.save(fullPath, BarCodeImageFormat.PNG);
        ExampleAssist.assertFileCreated(fullPath);

        assertImageHasBarcodes(fullPath, 1, List.of(expected(DecodeType.CODE_128, codeText)));
    }
}
