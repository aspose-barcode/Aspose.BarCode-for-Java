package com.aspose.barcode.guide.recognition.performance;

import com.aspose.barcode.barcoderecognition.BarCodeReader;
import com.aspose.barcode.barcoderecognition.DecodeType;
import com.aspose.barcode.barcoderecognition.QualitySettings;
import com.aspose.barcode.barcoderecognition.DeconvolutionMode;
import com.aspose.barcode.generation.BarCodeImageFormat;
import com.aspose.barcode.generation.BarcodeGenerator;
import com.aspose.barcode.generation.EncodeTypes;
import com.aspose.barcode.guide.common.ExampleAssist;
import com.aspose.barcode.guide.common.LicenseAssist;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * Demonstrates how to control recognition performance and robustness using:
 *  - BarcodeQualityMode (HIGH / NORMAL / LOW)
 *  - DeconvolutionMode (FAST / NORMAL / SLOW)
 *  - Preset + targeted overrides (XDimensionMode, MinimalXDimension, etc.)
 *
 * Test data (Code 128 and QR) is generated once in @BeforeClass.
 */
public class DeconvolutionModeExample {

    private static final String FOLDER =
            ExampleAssist.getOrCreateResourceFolderPath("recognition", "quality", "deconvolution_mode");

    @BeforeClass
    public void setUp() throws Exception {
        LicenseAssist.setupLicense();
        generateQRBase();
        generateQRBlurred();
    }

    private void generateQRBase() throws Exception {
        String file = "qr_clean.png";
        ExampleAssist.checkOrCreateImage(FOLDER, file, path -> {
            BarcodeGenerator gen = new BarcodeGenerator(EncodeTypes.QR, "QualitySettings:Deconvolution");
            gen.getParameters().getBarcode().getXDimension().setPixels(4);
            gen.save(path, BarCodeImageFormat.PNG);
        });
    }

    private void generateQRBlurred() throws Exception {
        String src = "qr_clean.png";
        String blurred = "qr_blurred.png";
        ExampleAssist.checkOrCreateImage(FOLDER, blurred, outPath -> {
            String inPath = ExampleAssist.pathCombine(FOLDER, src);
            // Gaussian-like blur (radius ~2.0). Increase for heavier blur.
            ExampleAssist.blur(inPath, outPath, 1.5f);
        });
    }



    // --- CLEAN image tests ---

    @Test
    public void read_QR_Clean_Deconvolution_FAST() throws Exception {
        String file = "qr_clean.png";
        BarCodeReader reader = new BarCodeReader(ExampleAssist.pathCombine(FOLDER, file), DecodeType.QR);

        QualitySettings qs = QualitySettings.getHighQuality();
        qs.setDeconvolution(DeconvolutionMode.FAST);
        reader.setQualitySettings(qs);

        ExampleAssist.assertRecognized(reader, file, 1, DecodeType.QR);
    }

    @Test
    public void read_QR_Clean_Deconvolution_NORMAL() throws Exception {
        String file = "qr_clean.png";
        BarCodeReader reader = new BarCodeReader(ExampleAssist.pathCombine(FOLDER, file), DecodeType.QR);

        QualitySettings qs = QualitySettings.getNormalQuality();
        qs.setDeconvolution(DeconvolutionMode.NORMAL);
        reader.setQualitySettings(qs);

        ExampleAssist.assertRecognized(reader, file, 1, DecodeType.QR);
    }

    @Test
    public void read_QR_Clean_Deconvolution_SLOW() throws Exception {
        String file = "qr_clean.png";
        BarCodeReader reader = new BarCodeReader(ExampleAssist.pathCombine(FOLDER, file), DecodeType.QR);

        QualitySettings qs = QualitySettings.getHighQuality();
        qs.setDeconvolution(DeconvolutionMode.SLOW);
        reader.setQualitySettings(qs);

        ExampleAssist.assertRecognized(reader, file, 1, DecodeType.QR);
    }

    // --- BLURRED image tests ---

    /**
     * Blurred input + Deconvolution=FAST (negative test)
     *
     * Purpose:
     *  - Show that the light-weight restoration (FAST) is not sufficient for motion/out-of-focus blur
     *    of this strength (produced by ExampleAssist.blur(...)).
     *  - This test intentionally expects NO recognition to emphasize the difference to SLOW mode.
     * Expectations:
     *  - Reader should fail to detect a QR on this blurred sample with FAST deconvolution.
     * Notes:
     *  - If you reduce blur radius in ExampleAssist.blur(...), FAST may start passing; in that case
     *    either weaken the blur again (to keep this test negative) or switch this check back to
     *    assertRecognized and move the negative case to a stronger blur sample.
     */
    @Test(enabled = false) //TODO: Find input parameters that cause the image to be unrecognized
    public void read_QR_Blurred_Deconvolution_FAST() throws Exception {
        String file = "qr_blurred.png";
        BarCodeReader reader = new BarCodeReader(ExampleAssist.pathCombine(FOLDER, file), DecodeType.QR);

        QualitySettings qs = QualitySettings.getHighQuality();
        qs.setDeconvolution(DeconvolutionMode.FAST); // light, speed-first restoration
        reader.setQualitySettings(qs);

        ExampleAssist.assertNotRecognized(reader, file);
    }

    /**
     * Blurred input + Deconvolution=SLOW (positive test)
     *
     * Purpose:
     *  - Demonstrate the strongest restoration path that is designed for heavy blur.
     *  - Complements the FAST negative test to show practical tuning: if FAST fails, try SLOW.
     * Expectations:
     *  - Reader should recognize the QR code on the same blurred image when SLOW is used.
     * Trade-off:
     *  - SLOW is slower than FAST but significantly more tolerant to blur.
     */
    @Test
    public void read_QR_Blurred_Deconvolution_SLOW() throws Exception {
        String file = "qr_blurred.png";
        BarCodeReader reader = new BarCodeReader(ExampleAssist.pathCombine(FOLDER, file), DecodeType.QR);

        QualitySettings qs = QualitySettings.getHighQuality();
        qs.setDeconvolution(DeconvolutionMode.SLOW); // strongest restoration
        reader.setQualitySettings(qs);

        ExampleAssist.assertRecognized(reader, file, 1, DecodeType.QR);
    }


}
