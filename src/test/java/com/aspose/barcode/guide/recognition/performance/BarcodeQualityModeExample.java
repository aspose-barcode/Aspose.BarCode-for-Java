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
import com.aspose.barcode.guide.common.LicenseAssist;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * Focus: BarcodeQualityMode (HIGH / NORMAL / LOW).
 * Includes clean vs noisy image scenarios. Noise is produced by ExampleAssist.addGaussianNoise(...).
 */
public class BarcodeQualityModeExample {

    private static final String FOLDER =
            ExampleAssist.getOrCreateResourceFolderPath("recognition", "quality", "barcode_quality_mode");

    @BeforeClass
    public void setUp() throws Exception {
        LicenseAssist.setupLicense();
        generateCode128Base();
        generateCode128Noisy();
    }

    private void generateCode128Base() throws Exception {
        String file = "code128_clean.png";
        ExampleAssist.checkOrCreateImage(FOLDER, file, path -> {
            BarcodeGenerator gen = new BarcodeGenerator(EncodeTypes.CODE_128, "QualitySettings:BarcodeQuality");
            gen.save(path, BarCodeImageFormat.PNG);
        });
    }

    private void generateCode128Noisy() throws Exception {
        String src = "code128_clean.png";
        String noisy = "code128_noisy.png";
        ExampleAssist.checkOrCreateImage(FOLDER, noisy, outPath -> {
            String inPath = ExampleAssist.pathCombine(FOLDER, src);
            // Adds Gaussian noise (stdDev ~12). Tune if your CI needs milder noise.
            ExampleAssist.addGaussianNoise(inPath, outPath, 12.0);
        });
    }

    // --- CLEAN image tests ---

    @Test
    public void read_Code128_Clean_BarcodeQuality_HIGH() throws Exception {
        String file = "code128_clean.png";
        BarCodeReader reader = new BarCodeReader(ExampleAssist.pathCombine(FOLDER, file), DecodeType.CODE_128);

        QualitySettings qs = QualitySettings.getHighPerformance();
        qs.setBarcodeQuality(BarcodeQualityMode.HIGH);
        reader.setQualitySettings(qs);

        ExampleAssist.assertRecognized(reader, file, 1, DecodeType.CODE_128);
    }

    @Test
    public void read_Code128_Clean_BarcodeQuality_NORMAL() throws Exception {
        String file = "code128_clean.png";
        BarCodeReader reader = new BarCodeReader(ExampleAssist.pathCombine(FOLDER, file), DecodeType.CODE_128);

        QualitySettings qs = QualitySettings.getNormalQuality();
        qs.setBarcodeQuality(BarcodeQualityMode.NORMAL);
        reader.setQualitySettings(qs);

        ExampleAssist.assertRecognized(reader, file, 1, DecodeType.CODE_128);
    }

    @Test
    public void read_Code128_Clean_BarcodeQuality_LOW() throws Exception {
        String file = "code128_clean.png";
        BarCodeReader reader = new BarCodeReader(ExampleAssist.pathCombine(FOLDER, file), DecodeType.CODE_128);

        QualitySettings qs = QualitySettings.getHighQuality();
        qs.setBarcodeQuality(BarcodeQualityMode.LOW);
        reader.setQualitySettings(qs);

        ExampleAssist.assertRecognized(reader, file, 1, DecodeType.CODE_128);
    }

    // --- NOISY image tests ---

    @Test
    public void read_Code128_Noisy_BarcodeQuality_HIGH() throws Exception {
        String file = "code128_noisy.png";
        BarCodeReader reader = new BarCodeReader(ExampleAssist.pathCombine(FOLDER, file), DecodeType.CODE_128);

        QualitySettings qs = QualitySettings.getHighPerformance();
        qs.setBarcodeQuality(BarcodeQualityMode.HIGH);
        reader.setQualitySettings(qs);

        ExampleAssist.assertRecognized(reader, file, 1, DecodeType.CODE_128);
    }

    @Test
    public void read_Code128_Noisy_BarcodeQuality_LOW() throws Exception {
        String file = "code128_noisy.png";
        BarCodeReader reader = new BarCodeReader(ExampleAssist.pathCombine(FOLDER, file), DecodeType.CODE_128);

        QualitySettings qs = QualitySettings.getHighQuality();
        qs.setBarcodeQuality(BarcodeQualityMode.LOW);
        reader.setQualitySettings(qs);

        ExampleAssist.assertRecognized(reader, file, 1, DecodeType.CODE_128);
    }

    // --- Preset + targeted overrides ---

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
