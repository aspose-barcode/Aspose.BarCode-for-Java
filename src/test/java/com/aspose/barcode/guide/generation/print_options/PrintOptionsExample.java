package com.aspose.barcode.guide.generation.print_options;

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
 * Printing options focused tests:
 * <ul>
 *   <li>Controlling physical sizing by DPI (203 vs 300) through {@link Unit#updateResolution(float)}.</li>
 *   <li>Wide-to-narrow ratio for 1D symbologies (Code 39).</li>
 *   <li>Using anti-aliasing ON/OFF for raster output (visual, still decodable).</li>
 * </ul>
 *
 * Conventions:
 * <ul>
 *   <li>No try-with-resources; do not close/Dispose readers/generators explicitly.</li>
 *   <li>Deterministic output paths under {@code src/test/resources}.</li>
 *   <li>Use {@link ExampleAssist} helpers for IO and assertions.</li>
 * </ul>
 */
public class PrintOptionsExample {

    private static final String FOLDER =
            ExampleAssist.getOrCreateResourceFolderPath("generation", "print_options");

    private static final String FILE_C128_XDIM_MM_203DPI = "c128_xdim_0_33mm_203dpi.png";
    private static final String FILE_C128_XDIM_MM_300DPI = "c128_xdim_0_33mm_300dpi.png";
    private static final String FILE_CODE39_RATIO_25     = "code39_ratio_2_5.png";
    private static final String FILE_CODE39_RATIO_30     = "code39_ratio_3_0.png";
    private static final String FILE_QR_AA_OFF           = "qr_antialias_off.png";
    private static final String FILE_QR_AA_ON            = "qr_antialias_on.png";

    @BeforeClass
    public void setUp() throws Exception {
        LicenseAssist.setupLicense();
    }

    /**
     * # Physical sizing at printer DPI: 203 dpi vs 300 dpi
     *
     * <b>Shows:</b>
     * <ul>
     *   <li>How the same physical X-dimension (in millimeters) converts to different pixels at different DPI.</li>
     *   <li>Use {@link Unit#updateResolution(float)} to tell the {@link Unit} which DPI to use for conversions.</li>
     * </ul>
     *
     * <b>What we do:</b>
     * <ol>
     *   <li>Generate two CODE_128 barcodes with {@code XDimension = 0.33 mm}.</li>
     *   <li>First uses 203 dpi (typical thermal printer), second uses 300 dpi.</li>
     *   <li>We check that pixel widths differ accordingly, while both decode to the same text.</li>
     * </ol>
     */
    @Test
    public void code128_xdimensionMillimeters_at203vs300dpi() throws Exception {
        final String codeText = "XDIM-0.33MM";

        // --- 203 dpi ---
        BarcodeGenerator generator203 = new BarcodeGenerator(EncodeTypes.CODE_128, codeText);
        Unit xdim203 = generator203.getParameters().getBarcode().getXDimension();
        xdim203.updateResolution(203f);
        xdim203.setMillimeters(0.33f); // ~ 0.33/25.4*203 ≈ 2.64 px

        generator203.getParameters().getBarcode().getBarHeight().setPixels(90);
        generator203.getParameters().getBarcode().getPadding().getLeft().setPixels(16);
        generator203.getParameters().getBarcode().getPadding().getRight().setPixels(16);
        generator203.getParameters().getImageWidth().setPixels(420);
        generator203.getParameters().getImageHeight().setPixels(180);

        String p203 = ExampleAssist.pathCombine(FOLDER, FILE_C128_XDIM_MM_203DPI);
        generator203.save(p203, BarCodeImageFormat.PNG);
        ExampleAssist.assertFileCreated(p203);

        // --- 300 dpi ---
        BarcodeGenerator generator300 = new BarcodeGenerator(EncodeTypes.CODE_128, codeText);
        Unit xdim300 = generator300.getParameters().getBarcode().getXDimension();
        xdim300.updateResolution(300f);
        xdim300.setMillimeters(0.33f); // ~ 0.33/25.4*300 ≈ 3.90 px

        generator300.getParameters().getBarcode().getBarHeight().setPixels(90);
        generator300.getParameters().getBarcode().getPadding().getLeft().setPixels(16);
        generator300.getParameters().getBarcode().getPadding().getRight().setPixels(16);
        generator300.getParameters().getImageWidth().setPixels(420);
        generator300.getParameters().getImageHeight().setPixels(180);

        String p300 = ExampleAssist.pathCombine(FOLDER, FILE_C128_XDIM_MM_300DPI);
        generator300.save(p300, BarCodeImageFormat.PNG);
        ExampleAssist.assertFileCreated(p300);

        // Assert both decode and pixel values differ as expected (tolerant bounds).
        assertImageHasBarcodes(p203, 1, List.of(expected(DecodeType.CODE_128, codeText)));
        assertImageHasBarcodes(p300, 1, List.of(expected(DecodeType.CODE_128, codeText)));

        float px203 = xdim203.getPixels();
        float px300 = xdim300.getPixels();
        Assert.assertTrue(px203 > 2.0f && px203 < 3.3f, "203dpi XDim(px) must be around ~2.6px, got " + px203);
        Assert.assertTrue(px300 > 3.2f && px300 < 4.8f, "300dpi XDim(px) must be around ~3.9px, got " + px300);
        Assert.assertTrue(px300 > px203, "300dpi must produce larger pixel X-dimension than 203dpi");
    }

    /**
     * # Wide-to-Narrow Ratio for Code 39 (printability / scanner tolerance)
     *
     * <b>Shows:</b>
     * <ul>
     *   <li>How to set wide-to-narrow bar ratio for 1D symbologies (here Code 39).</li>
     *   <li>Different ratios yield different printed appearance but should still decode.</li>
     * </ul>
     *
     */
    @Test
    public void code39_wideToNarrowRatio_variants() throws Exception {
        final String codeText = "CODE39-RATIO";

        // --- Ratio = 2.5 ---
        BarcodeGenerator generator25 = new BarcodeGenerator(EncodeTypes.CODE_39, codeText);
        generator25.getParameters().getBarcode().setWideNarrowRatio(2.5f);
        generator25.getParameters().getBarcode().getXDimension().setPixels(2.0f);
        generator25.getParameters().getBarcode().getBarHeight().setPixels(100);
        generator25.getParameters().getImageWidth().setPixels(520);
        generator25.getParameters().getImageHeight().setPixels(220);
        String p25 = ExampleAssist.pathCombine(FOLDER, FILE_CODE39_RATIO_25);
        generator25.save(p25, BarCodeImageFormat.PNG);
        ExampleAssist.assertFileCreated(p25);
        assertImageHasBarcodes(p25, 1, List.of(expected(DecodeType.CODE_39, codeText)));

        // --- Ratio = 3.0 ---
        BarcodeGenerator generator30 = new BarcodeGenerator(EncodeTypes.CODE_39, codeText);
        generator30.getParameters().getBarcode().setWideNarrowRatio(3.0f);
        generator30.getParameters().getBarcode().getXDimension().setPixels(2.0f);
        generator30.getParameters().getBarcode().getBarHeight().setPixels(100);
        generator30.getParameters().getImageWidth().setPixels(520);
        generator30.getParameters().getImageHeight().setPixels(220);
        String p30 = ExampleAssist.pathCombine(FOLDER, FILE_CODE39_RATIO_30);
        generator30.save(p30, BarCodeImageFormat.PNG);
        ExampleAssist.assertFileCreated(p30);
        assertImageHasBarcodes(p30, 1, List.of(expected(DecodeType.CODE_39, codeText)));

        // Smoke check: both decode to the same payload.
        BarCodeReader r25 = new BarCodeReader(p25, DecodeType.CODE_39);
        BarCodeResult[] res25 = r25.readBarCodes();
        Assert.assertTrue(res25.length >= 1 && codeText.equals(res25[0].getCodeText()));

        BarCodeReader r30 = new BarCodeReader(p30, DecodeType.CODE_39);
        BarCodeResult[] res30 = r30.readBarCodes();
        Assert.assertTrue(res30.length >= 1 && codeText.equals(res30[0].getCodeText()));
    }

    /**
     * # Anti-aliasing ON vs OFF (visual smoothing vs barcode crispness)
     *
     * <b>Shows:</b>
     * <ul>
     *   <li>How to toggle anti-aliasing during rendering.</li>
     *   <li>Anti-aliasing may smooth edges (visually nicer) but for small modules it is often better OFF for crisp bars.</li>
     * </ul>
     *
     * <b>Notes:</b>
     * <ul>
     *   <li>Property name can differ; commonly {@code generator.getParameters().setUseAntiAlias(true/false)}.</li>
     *   <li>Both outputs should still be decodable.</li>
     * </ul>
     */
    @Test
    public void qr_useAntiAlias_on_vs_off() throws Exception {
        final String payload = "AA-TOGGLE";

        // --- Anti-alias OFF (crisp modules) ---
        BarcodeGenerator generatorOff = new BarcodeGenerator(EncodeTypes.QR, payload);
        generatorOff.getParameters().setUseAntiAlias(false);
        generatorOff.getParameters().getBarcode().getQR().setQrVersion(QRVersion.VERSION_02);
        generatorOff.getParameters().getBarcode().getXDimension().setPixels(3.0f);
        generatorOff.getParameters().getImageWidth().setPixels(220);
        generatorOff.getParameters().getImageHeight().setPixels(220);
        String pOff = ExampleAssist.pathCombine(FOLDER, FILE_QR_AA_OFF);
        generatorOff.save(pOff, BarCodeImageFormat.PNG);
        ExampleAssist.assertFileCreated(pOff);
        assertImageHasBarcodes(pOff, 1, List.of(expected(DecodeType.QR, payload)));

        // --- Anti-alias ON (smoother visuals) ---
        BarcodeGenerator generatorOn = new BarcodeGenerator(EncodeTypes.QR, payload);
        generatorOn.getParameters().setUseAntiAlias(true);
        generatorOn.getParameters().getBarcode().getQR().setQrVersion(QRVersion.VERSION_02);
        generatorOn.getParameters().getBarcode().getXDimension().setPixels(3.0f);
        generatorOn.getParameters().getImageWidth().setPixels(220);
        generatorOn.getParameters().getImageHeight().setPixels(220);
        String pOn = ExampleAssist.pathCombine(FOLDER, FILE_QR_AA_ON);
        generatorOn.save(pOn, BarCodeImageFormat.PNG);
        ExampleAssist.assertFileCreated(pOn);
        assertImageHasBarcodes(pOn, 1, List.of(expected(DecodeType.QR, payload)));
    }
}
