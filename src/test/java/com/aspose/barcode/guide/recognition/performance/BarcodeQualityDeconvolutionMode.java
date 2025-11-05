package com.aspose.barcode.guide.recognition.performance;

import com.aspose.barcode.barcoderecognition.BarCodeReader;
import com.aspose.barcode.barcoderecognition.BarCodeResult;
import com.aspose.barcode.barcoderecognition.DecodeType;
import com.aspose.barcode.barcoderecognition.QualitySettings;
import com.aspose.barcode.barcoderecognition.BarcodeQualityMode;
import com.aspose.barcode.barcoderecognition.DeconvolutionMode;
import com.aspose.barcode.barcoderecognition.XDimensionMode;
import com.aspose.barcode.generation.BarCodeImageFormat;
import com.aspose.barcode.generation.BarcodeGenerator;
import com.aspose.barcode.generation.EncodeTypes;
import com.aspose.barcode.guide.common.ExampleAssist;
import com.aspose.barcode.guide.common.LicenseAssist;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class BarcodeQualityDeconvolutionMode
{
    private static final String FOLDER =
            ExampleAssist.getOrCreateResourceFolderPath("recognition", "quality", "barcode_quality_deconvolution");

    @BeforeClass
    public void setUp() throws Exception
    {
        LicenseAssist.setupLicense();
        generateCode128AndQR();
    }

    // ==================== Test data generation ====================

    private void generateCode128AndQR() throws Exception
    {
        // Code128 for BarcodeQuality tests
        String code128 = "qset_code128.png";
        ExampleAssist.checkOrCreateImage(FOLDER, code128, path -> {
            BarcodeGenerator gen = new BarcodeGenerator(EncodeTypes.CODE_128, "QualitySettings:BarcodeQuality");
            gen.save(path, BarCodeImageFormat.PNG);
        });

        // QR for Deconvolution tests
        String qr = "qset_qr.png";
        ExampleAssist.checkOrCreateImage(FOLDER, qr, path -> {
            BarcodeGenerator gen = new BarcodeGenerator(EncodeTypes.QR, "QualitySettings:Deconvolution");
            gen.save(path, BarCodeImageFormat.PNG);
        });
    }

    // ==================== BarcodeQualityMode ====================

    @Test
    public void read_Code128_BarcodeQuality_HIGH() throws Exception
    {
        String fileName = "qset_code128.png";
        BarCodeReader reader = new BarCodeReader(ExampleAssist.pathCombine(FOLDER, fileName), DecodeType.CODE_128);

        QualitySettings qs = QualitySettings.getHighPerformance();
        qs.setBarcodeQuality(BarcodeQualityMode.HIGH);
        reader.setQualitySettings(qs);

        ExampleAssist.assertRecognized(reader, fileName, 1, DecodeType.CODE_128);
    }

    @Test
    public void read_Code128_BarcodeQuality_NORMAL() throws Exception
    {
        String fileName = "qset_code128.png";
        BarCodeReader reader = new BarCodeReader(ExampleAssist.pathCombine(FOLDER, fileName), DecodeType.CODE_128);

        QualitySettings qs = QualitySettings.getNormalQuality();
        qs.setBarcodeQuality(BarcodeQualityMode.NORMAL);
        reader.setQualitySettings(qs);

        ExampleAssist.assertRecognized(reader, fileName, 1, DecodeType.CODE_128);
    }

    @Test
    public void read_Code128_BarcodeQuality_LOW() throws Exception
    {
        String fileName = "qset_code128.png";
        BarCodeReader reader = new BarCodeReader(ExampleAssist.pathCombine(FOLDER, fileName), DecodeType.CODE_128);

        QualitySettings qs = QualitySettings.getHighQuality();
        qs.setBarcodeQuality(BarcodeQualityMode.LOW);
        reader.setQualitySettings(qs);

        ExampleAssist.assertRecognized(reader, fileName, 1, DecodeType.CODE_128);
    }

    // ==================== DeconvolutionMode ====================

    @Test
    public void read_QR_Deconvolution_FAST() throws Exception
    {
        String fileName = "qset_qr.png";
        BarCodeReader reader = new BarCodeReader(ExampleAssist.pathCombine(FOLDER, fileName), DecodeType.QR);

        QualitySettings qs = QualitySettings.getHighQuality();
        qs.setDeconvolution(DeconvolutionMode.FAST);
        reader.setQualitySettings(qs);

        ExampleAssist.assertRecognized(reader, fileName, 1, DecodeType.QR);
    }

    @Test
    public void read_QR_Deconvolution_NORMAL() throws Exception
    {
        String fileName = "qset_qr.png";
        BarCodeReader reader = new BarCodeReader(ExampleAssist.pathCombine(FOLDER, fileName), DecodeType.QR);

        QualitySettings qs = QualitySettings.getNormalQuality();
        qs.setDeconvolution(DeconvolutionMode.NORMAL);
        reader.setQualitySettings(qs);

        ExampleAssist.assertRecognized(reader, fileName, 1, DecodeType.QR);
    }

    @Test
    public void read_QR_Deconvolution_SLOW() throws Exception
    {
        String fileName = "qset_qr.png";
        BarCodeReader reader = new BarCodeReader(ExampleAssist.pathCombine(FOLDER, fileName), DecodeType.QR);

        QualitySettings qs = QualitySettings.getHighQuality();
        qs.setDeconvolution(DeconvolutionMode.SLOW);
        reader.setQualitySettings(qs);

        ExampleAssist.assertRecognized(reader, fileName, 1, DecodeType.QR);
    }

    // ==================== Preset + targeted overrides ====================

    @Test
    public void read_Code128_PresetWithOverrides_forSmallAndLowQuality() throws Exception
    {
        // Start from HighPerformance but enable heavier methods for tiny/low-quality bars
        String fileName = "qset_code128.png";
        BarCodeReader reader = new BarCodeReader(ExampleAssist.pathCombine(FOLDER, fileName), DecodeType.CODE_128);

        QualitySettings qs = QualitySettings.getHighPerformance();
        qs.setXDimension(XDimensionMode.SMALL);
        qs.setMinimalXDimension(1.0f);
        qs.setBarcodeQuality(BarcodeQualityMode.LOW);
        qs.setDeconvolution(DeconvolutionMode.SLOW);
        reader.setQualitySettings(qs);

        ExampleAssist.assertRecognized(reader, fileName, 1, DecodeType.CODE_128);
    }
}
