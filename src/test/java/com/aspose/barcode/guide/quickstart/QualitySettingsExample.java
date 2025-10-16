package com.aspose.barcode.guide.quickstart;

import com.aspose.barcode.barcoderecognition.*;
import com.aspose.barcode.guide.common.ExampleAssist;
import com.aspose.barcode.guide.common.LicenseAssist;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.nio.file.Paths;

public class QualitySettingsExample
{

    private static final String IMAGES_FOLDER =
            Paths.get(ExampleAssist.getOrCreateResourceFolderPath("quick_start", "recognition", "prepared_images")).toString();

    @BeforeClass
    public void setUp() {
        LicenseAssist.setupLicense();
    }

    // --- Code 128 (NormalQuality) ---
    @Test
    public void read_Code128_NormalQuality() throws Exception {
        QualitySettings qs = QualitySettings.getNormalQuality();
        BarCodeReader barCodeReader = new BarCodeReader(IMAGES_FOLDER + "code128.png", DecodeType.CODE_128);
        barCodeReader.setQualitySettings(qs);
        assertRecognized(barCodeReader, "code128.png", 1);
    }

    // --- QR (HighPerformance) ---
    @Test
    public void read_QR_HighPerformance() throws Exception {
        QualitySettings qs = QualitySettings.getHighPerformance();
        BarCodeReader barCodeReader = new BarCodeReader(IMAGES_FOLDER + "qrcode.png", DecodeType.QR);
        barCodeReader.setQualitySettings(qs);
        assertRecognized(barCodeReader, "qrcode.png", 1);
    }

    // --- DataMatrix (HighQuality) ---
    @Test
    public void read_DataMatrix_HighQuality() throws Exception {
        QualitySettings qs = QualitySettings.getHighQuality();
        BarCodeReader barCodeReader = new BarCodeReader(IMAGES_FOLDER + "datamatrix.png", DecodeType.DATA_MATRIX);
        barCodeReader.setQualitySettings(qs);
        assertRecognized(barCodeReader, "datamatrix.png", 1);
    }

    // --- PDF417 (MaxQuality) ---
    @Test
    public void read_PDF417_MaxQuality() throws Exception {
        QualitySettings qs = QualitySettings.getMaxQuality();
        BarCodeReader barCodeReader = new BarCodeReader(IMAGES_FOLDER + "pdf417.png", DecodeType.PDF_417);
        barCodeReader.setQualitySettings(qs);
        assertRecognized(barCodeReader, "pdf417.png", 1);
    }

    // --- EAN-13 via TYPES_1D ---
    @Test
    public void read_EAN13_All1D_NormalQuality() throws Exception {
        QualitySettings qs = QualitySettings.getNormalQuality();
        BarCodeReader barCodeReader = new BarCodeReader(IMAGES_FOLDER + "ean13.png", DecodeType.TYPES_1D);
        barCodeReader.setQualitySettings(qs);
        assertRecognized(barCodeReader, "ean13.png", 1);
    }

    // --- Aztec via TYPES_2D ---
    @Test
    public void read_Aztec_All2D_NormalQuality() throws Exception {
        QualitySettings qs = QualitySettings.getNormalQuality();
        BarCodeReader barCodeReader = new BarCodeReader(IMAGES_FOLDER + "aztec.png", DecodeType.TYPES_2D);
        barCodeReader.setQualitySettings(qs);
        assertRecognized(barCodeReader, "aztec.png", 1);
    }

    // --- Custom: NormalQuality + InverseImage for inverted colors (if applicable image exists) ---
    @Test
    public void read_QR_NormalQuality_InverseEnabled() throws Exception {
        QualitySettings qs = QualitySettings.getNormalQuality();
        qs.setInverseImage(InverseImageMode.ENABLED);
        BarCodeReader barCodeReader = new BarCodeReader(IMAGES_FOLDER + "qrcode_inverted.png", DecodeType.QR);
        barCodeReader.setQualitySettings(qs);
        // If there is no inverted sample, lower the expected count to 0 or remove this test
        assertRecognized(barCodeReader, "qrcode_inverted.png", 1);
    }

    // --- Custom: HighPerformance + SMALL XDimension for tiny barcodes (if applicable image exists) ---
    @Test
    public void read_Code128_HighPerformance_TinyX() throws Exception {
        QualitySettings qs = QualitySettings.getHighPerformance();
        qs.setXDimension(XDimensionMode.SMALL);
        qs.setMinimalXDimension(1);
        BarCodeReader barCodeReader = new BarCodeReader(IMAGES_FOLDER + "code128_small.png", DecodeType.CODE_128);
        barCodeReader.setQualitySettings(qs);
        // If there is no tiny sample, lower the expected count to 0 or remove this test
        assertRecognized(barCodeReader, "code128_small.png", 1);
    }

    // --- Helper assertion ---
    private void assertRecognized(BarCodeReader barCodeReader, String imageName, int count) throws Exception {
        BarCodeResult[] results = barCodeReader.readBarCodes();
        for (BarCodeResult result : results) {
            System.out.println("Code Type : " + result.getCodeTypeName() + " Code Text : " + result.getCodeText());
        }
        Assert.assertTrue(
                results.length >= count,
                "Expected at least " + count + " result(s) in " + imageName + ", but got " + results.length
        );
    }
}
