package com.aspose.barcode.guide.quickstart;

import com.aspose.barcode.barcoderecognition.*;
import com.aspose.barcode.guide.common.ExampleAssist;
import com.aspose.barcode.guide.common.LicenseAssist;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.nio.file.Paths;

/**
 * QuickRecognitionExamples
 *
 * TestNG suite focusing on recognition settings only (no generation here).
 * Covers QualitySettings presets and manual tuning (XDimension, BarcodeQuality, Deconvolution, etc.).
 */
public class QuickRecognitionExamples {

    private static final String folder =
            Paths.get(ExampleAssist.getOrCreateResourceFolderPath("quick_start"), "recognition").toString();

    @BeforeClass
    public void setUp() {
        LicenseAssist.setupLicense();
    }

    // --- Presets ---

    @Test
    public void read_Code128_NormalQuality() throws Exception {
        QualitySettings qs = QualitySettings.getNormalQuality();
        assertRecognized("code128.png", 1, qs, DecodeType.CODE_128);
    }

    @Test
    public void read_QR_HighPerformance() throws Exception {
        QualitySettings qs = QualitySettings.getHighPerformance();
        assertRecognized("qrcode_utf8.png", 1, qs, DecodeType.QR);
    }

    @Test
    public void read_DataMatrix_HighQuality() throws Exception {
        QualitySettings qs = QualitySettings.getHighQuality();
        assertRecognized("datamatrix.png", 1, qs, DecodeType.DATA_MATRIX);
    }

    @Test
    public void read_Mixed_MaxQuality_AllSupported() throws Exception {
        QualitySettings qs = QualitySettings.getMaxQuality();
        assertRecognized("mixed.png", 1, qs, DecodeType.ALL_SUPPORTED_TYPES);
    }

    // --- X-Dimension tuning ---

    @Test
    public void read_SmallModules_XDimensionSmall() throws Exception {
        QualitySettings qs = QualitySettings.getHighPerformance();
        qs.setXDimension(XDimensionMode.SMALL);
        assertRecognized("small_modules.png", 1, qs, DecodeType.CODE_128);
    }

    @Test
    public void read_LargeModules_XDimensionLarge() throws Exception {
        QualitySettings qs = QualitySettings.getNormalQuality();
        qs.setXDimension(XDimensionMode.LARGE);
        assertRecognized("large_modules.png", 1, qs, DecodeType.CODE_128);
    }

    @Test
    public void read_CustomMinimalXDimension_UseMinimalMode() throws Exception {
        QualitySettings qs = QualitySettings.getHighQuality();
        qs.setXDimension(XDimensionMode.USE_MINIMAL_X_DIMENSION);
        qs.setMinimalXDimension(2.0f); // pixels
        assertRecognized("custom_min_xdim.png", 1, qs, DecodeType.DATA_MATRIX);
    }

    // --- Image quality & restoration ---

    @Test
    public void read_Blurred_SlowDeconvolution() throws Exception {
        QualitySettings qs = QualitySettings.getHighQuality();
        qs.setDeconvolution(DeconvolutionMode.SLOW);
        assertRecognized("blurred.png", 1, qs, DecodeType.PDF_417);
    }

    @Test
    public void read_LowQualityElements_BarcodeQualityLow() throws Exception {
        QualitySettings qs = QualitySettings.getHighPerformance();
        qs.setBarcodeQuality(BarcodeQualityMode.LOW);
        assertRecognized("low_quality.png", 1, qs, DecodeType.CODE_128);
    }

    // --- Polarity & background ---

    @Test
    public void read_InvertedColors_EnableInverse() throws Exception {
        QualitySettings qs = QualitySettings.getHighQuality();
        // If your build includes InverseImageMode, enable it:
        // qs.setInverseImage(InverseImageMode.ENABLED);
        assertRecognized("inverted.png", 1, qs, DecodeType.CODE_128);
    }

    @Test
    public void read_ComplexBackground_EnableMode() throws Exception {
        QualitySettings qs = QualitySettings.getMaxQuality();
        // If your build includes ComplexBackgroundMode, enable it (MaxQuality does by default):
        // qs.setComplexBackground(ComplexBackgroundMode.ENABLED);
        assertRecognized("complex_background.png", 1, qs, DecodeType.ALL_SUPPORTED_TYPES);
    }

    // --- Damaged / partially invalid ---

    @Test
    public void read_Damaged_AllowIncorrectBarcodes() throws Exception {
        QualitySettings qs = QualitySettings.getMaxQuality();
        qs.setAllowIncorrectBarcodes(true);
        assertRecognized("damaged.png", 1, qs, DecodeType.CODE_128);
    }

    // --- Utility assertion ---

    /**
     * Reads an image with the specified DecodeType set and QualitySettings,
     * and asserts that at least the expected number of barcodes is found.
     *
     * @param imageName file name under the recognition folder
     * @param minCount  minimum expected recognized barcodes
     * @param qs        quality settings preset or tuned settings (nullable)
     */
    private void assertRecognized(String imageName, int minCount, QualitySettings qs, BaseDecodeType decodeType) throws Exception {
        String img = Paths.get(folder, imageName).toString();
        BarCodeReader reader = new BarCodeReader(img, decodeType);

        if (qs != null) {
            reader.setQualitySettings(qs);
        }

        BarCodeResult[] results = reader.readBarCodes();
        Assert.assertTrue(
                results.length >= minCount,
                "Expected at least " + minCount + " result(s) in " + imageName + ", but got " + results.length
        );
    }
}
