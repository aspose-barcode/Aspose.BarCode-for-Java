package com.aspose.barcode.guide.recognition.performance;

import com.aspose.barcode.barcoderecognition.BarCodeReader;
import com.aspose.barcode.barcoderecognition.DecodeType;
import com.aspose.barcode.barcoderecognition.QualitySettings;
import com.aspose.barcode.barcoderecognition.BarcodeQualityMode;
import com.aspose.barcode.barcoderecognition.XDimensionMode;
import com.aspose.barcode.generation.BarCodeImageFormat;
import com.aspose.barcode.generation.BarcodeGenerator;
import com.aspose.barcode.generation.EncodeTypes;
import com.aspose.barcode.guide.common.ExampleAssist;
import com.aspose.barcode.guide.common.ImageSupplier;
import com.aspose.barcode.guide.common.LicenseAssist;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * Focus: BarcodeQualityMode (HIGH / NORMAL / LOW)
 *
 * What it shows:
 *  - How different quality profiles impact recognition on clean inputs.
 *  - How LOW profile helps on noisy/low-quality printing (simulated with Gaussian noise).
 *  - A "preset + overrides" example for very small modules with low quality.
 */
public class BarcodeQualityModeExamples {

    private static final String FOLDER =
            ExampleAssist.getOrCreateResourceFolderPath("recognition", "quality", "barcode_quality");

    @BeforeClass
    public void setUp() throws Exception {
        LicenseAssist.setupLicense();
        generateCode128Base();
        generateCode128Noisy();
    }

    /**
     * Generates a base Code 128 image used by the clean tests.
     * Idempotent: image is created only if missing.
     */
    private void generateCode128Base() throws Exception {
        String file = "code128_clean.png";
        ExampleAssist.checkOrCreateImage(FOLDER, file, path -> {
            BarcodeGenerator gen = new BarcodeGenerator(EncodeTypes.CODE_128, "QualitySettings:BarcodeQuality");
            gen.save(path, BarCodeImageFormat.PNG);
        });
    }

    /**
     * Generates a noisy version of the Code 128 image to emulate low print quality:
     * - Gaussian noise is commonly seen in poor scans/photographs.
     */
    private void generateCode128Noisy() throws Exception {
        String src = "code128_clean.png";
        String noisy = "code128_noisy.png";
        ExampleAssist.checkOrCreateImage(FOLDER, noisy, outPath -> {
            String inPath = ExampleAssist.pathCombine(FOLDER, src);
            ImageSupplier.addGaussianNoise(inPath, outPath, 12.0); // std-dev ~12 (tune if needed)
        });
    }

    // -------------------- CLEAN image tests --------------------

    /**
     * Clean input + HIGH:
     * - Starts from HighPerformance preset and forces BarcodeQuality=HIGH.
     * - Demonstrates a fast path suitable for high-quality barcodes.
     * - Expected: quick and stable recognition on a clean Code 128.
     */
    @Test
    public void read_Code128_Clean_BarcodeQuality_HIGH() throws Exception {
        String file = "code128_clean.png";
        BarCodeReader reader = new BarCodeReader(ExampleAssist.pathCombine(FOLDER, file), DecodeType.CODE_128);

        QualitySettings qs = QualitySettings.getHighPerformance();
        qs.setBarcodeQuality(BarcodeQualityMode.HIGH);
        reader.setQualitySettings(qs);

        ExampleAssist.assertRecognized(reader, file, 1, DecodeType.CODE_128);
    }

    /**
     * Clean input + NORMAL:
     * - Balanced preset (NormalQuality) with BarcodeQuality=NORMAL.
     * - Demonstrates a good default when inputs are generally fine.
     */
    @Test
    public void read_Code128_Clean_BarcodeQuality_NORMAL() throws Exception {
        String file = "code128_clean.png";
        BarCodeReader reader = new BarCodeReader(ExampleAssist.pathCombine(FOLDER, file), DecodeType.CODE_128);

        QualitySettings qs = QualitySettings.getNormalQuality();
        qs.setBarcodeQuality(BarcodeQualityMode.NORMAL);
        reader.setQualitySettings(qs);

        ExampleAssist.assertRecognized(reader, file, 1, DecodeType.CODE_128);
    }

    /**
     * Clean input + LOW:
     * - Heaviest recognition methods are enabled.
     * - Demonstrates the most tolerant (but slower) path even when the image is already clean.
     */
    @Test
    public void read_Code128_Clean_BarcodeQuality_LOW() throws Exception {
        String file = "code128_clean.png";
        BarCodeReader reader = new BarCodeReader(ExampleAssist.pathCombine(FOLDER, file), DecodeType.CODE_128);

        QualitySettings qs = QualitySettings.getHighQuality();
        qs.setBarcodeQuality(BarcodeQualityMode.LOW);
        reader.setQualitySettings(qs);

        ExampleAssist.assertRecognized(reader, file, 1, DecodeType.CODE_128);
    }

    // -------------------- NOISY image tests --------------------

    /**
     * Noisy input + HIGH:
     * - Shows that HIGH may still recognize, but it's tuned for good inputs.
     * - Useful as a baseline to compare with LOW on the same noisy image.
     */
    @Test
    public void read_Code128_Noisy_BarcodeQuality_HIGH() throws Exception {
        String file = "code128_noisy.png";
        BarCodeReader reader = new BarCodeReader(ExampleAssist.pathCombine(FOLDER, file), DecodeType.CODE_128);

        QualitySettings qs = QualitySettings.getHighPerformance();
        qs.setBarcodeQuality(BarcodeQualityMode.HIGH);
        reader.setQualitySettings(qs);

        ExampleAssist.assertRecognized(reader, file, 1, DecodeType.CODE_128);
    }

    /**
     * Noisy input + LOW:
     * - Enables heavy/robust methods designed for damaged/low-contrast elements.
     * - Demonstrates why LOW is the go-to for poor printing or degraded symbols.
     */
    @Test
    public void read_Code128_Noisy_BarcodeQuality_LOW() throws Exception {
        String file = "code128_noisy.png";
        BarCodeReader reader = new BarCodeReader(ExampleAssist.pathCombine(FOLDER, file), DecodeType.CODE_128);

        QualitySettings qs = QualitySettings.getHighQuality();
        qs.setBarcodeQuality(BarcodeQualityMode.LOW);
        reader.setQualitySettings(qs);

        ExampleAssist.assertRecognized(reader, file, 1, DecodeType.CODE_128);
    }

    // -------------------- Preset + targeted overrides --------------------

    /**
     * Preset + overrides for tiny and low-quality bars:
     * - Base: HighPerformance (fast).
     * - Overrides:
     *     XDimension=SMALL           -> Expect tiny modules
     *     MinimalXDimension=1.0 px   -> Minimal module size hint
     *     BarcodeQuality=LOW         -> Heavy methods for low-quality elements
     * - Demonstrates a practical tuning recipe for small/weak 1D barcodes.
     */
    @Test
    public void read_Code128_Clean_PresetWithOverrides_SmallLowQuality() throws Exception {
        String file = "code128_clean.png";
        BarCodeReader reader = new BarCodeReader(ExampleAssist.pathCombine(FOLDER, file), DecodeType.CODE_128);

        QualitySettings qs = QualitySettings.getHighPerformance();
        qs.setXDimension(XDimensionMode.SMALL);
        qs.setMinimalXDimension(1.0f);
        qs.setBarcodeQuality(BarcodeQualityMode.LOW);
        reader.setQualitySettings(qs);

        ExampleAssist.assertRecognized(reader, file, 1, DecodeType.CODE_128);
    }
}
