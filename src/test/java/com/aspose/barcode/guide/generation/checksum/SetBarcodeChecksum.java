package com.aspose.barcode.guide.generation.checksum;

import com.aspose.barcode.barcoderecognition.ChecksumValidation;
import com.aspose.barcode.barcoderecognition.DecodeType;
import com.aspose.barcode.generation.BarCodeImageFormat;
import com.aspose.barcode.generation.BarcodeGenerator;
import com.aspose.barcode.generation.EnableChecksum;
import com.aspose.barcode.generation.EncodeTypes;
import com.aspose.barcode.guide.common.ExampleAssist;
import com.aspose.barcode.guide.common.LicenseAssist;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

import static com.aspose.barcode.guide.common.ExampleAssist.assertImageHasBarcodes;
import static com.aspose.barcode.guide.common.ExampleAssist.expected;

/**
 * Demonstrates optional and mandatory checksum behavior.
 */
public class SetBarcodeChecksum {

    private static final String FOLDER =
            ExampleAssist.getOrCreateResourceFolderPath("generation", "set-barcode-checksum");

    @BeforeClass
    public void setUp() {
        LicenseAssist.setupLicense();
    }

    /**
     * Enables the optional Mod10 checksum for Interleaved 2 of 5.
     */
    @Test
    public void enableOptionalChecksum() throws Exception {
        String payload = "123456";
        String expectedText = ExampleAssist.expectedI25WithChecksum(payload);
        String outputPath = ExampleAssist.pathCombine(FOLDER, "interleaved2of5_checksum.png");

        BarcodeGenerator generator = new BarcodeGenerator(EncodeTypes.INTERLEAVED_2_OF_5, payload);
        generator.getParameters().getBarcode().setChecksumEnabled(EnableChecksum.YES);
        generator.save(outputPath, BarCodeImageFormat.PNG);

        ExampleAssist.assertFileCreated(outputPath);
        assertImageHasBarcodes(
                outputPath,
                1,
                List.of(expected(DecodeType.INTERLEAVED_2_OF_5, expectedText)),
                ChecksumValidation.ON
        );
    }

    /**
     * Generates EAN-13, whose check digit is required by the symbology.
     */
    @Test
    public void generateMandatoryChecksum() throws Exception {
        String dataWithoutCheckDigit = "590123412345";
        String expectedText = "5901234123457";
        String outputPath = ExampleAssist.pathCombine(FOLDER, "ean13_checksum.png");

        BarcodeGenerator generator = new BarcodeGenerator(EncodeTypes.EAN_13, dataWithoutCheckDigit);
        generator.save(outputPath, BarCodeImageFormat.PNG);

        ExampleAssist.assertFileCreated(outputPath);
        assertImageHasBarcodes(
                outputPath,
                1,
                List.of(expected(DecodeType.EAN_13, expectedText)),
                ChecksumValidation.ON
        );
    }
}
