package com.aspose.barcode.guide.generation.symbology_codetext;

import com.aspose.barcode.barcoderecognition.DecodeType;
import com.aspose.barcode.generation.BarCodeImageFormat;
import com.aspose.barcode.generation.BarcodeGenerator;
import com.aspose.barcode.generation.ECIEncodings;
import com.aspose.barcode.generation.EncodeTypes;
import com.aspose.barcode.generation.QREncodeMode;
import com.aspose.barcode.guide.common.ExampleAssist;
import com.aspose.barcode.guide.common.LicenseAssist;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.nio.charset.StandardCharsets;
import java.util.List;

import static com.aspose.barcode.guide.common.ExampleAssist.assertImageHasBarcodes;
import static com.aspose.barcode.guide.common.ExampleAssist.expected;

/**
 * Demonstrates how to select a symbology and provide text or binary payloads.
 */
public class SetBarcodeSymbologyAndText {

    private static final String FOLDER =
            ExampleAssist.getOrCreateResourceFolderPath("generation", "symbology-and-text");

    @BeforeClass
    public void setUp() {
        LicenseAssist.setupLicense();
    }

    /**
     * Generates a Code 128 barcode from a Java string.
     */
    @Test
    public void generateCode128FromText() throws Exception {
        String codeText = "PRODUCT-2026";
        String outputPath = ExampleAssist.pathCombine(FOLDER, "code128_text.png");

        BarcodeGenerator generator = new BarcodeGenerator(EncodeTypes.CODE_128, codeText);
        generator.save(outputPath, BarCodeImageFormat.PNG);

        ExampleAssist.assertFileCreated(outputPath);
        assertImageHasBarcodes(outputPath, 1, List.of(expected(DecodeType.CODE_128, codeText)));
    }

    /**
     * Generates a QR Code from Unicode text using explicit UTF-8 ECI encoding.
     */
    @Test
    public void generateQrFromUnicodeText() throws Exception {
        String codeText = "Aspose — データ";
        String outputPath = ExampleAssist.pathCombine(FOLDER, "qr_unicode.png");

        BarcodeGenerator generator = new BarcodeGenerator(EncodeTypes.QR, codeText);
        generator.getParameters().getBarcode().getQR().setQrEncodeMode(QREncodeMode.ECI);
        generator.getParameters().getBarcode().getQR().setQrECIEncoding(ECIEncodings.UTF8);
        generator.save(outputPath, BarCodeImageFormat.PNG);

        ExampleAssist.assertFileCreated(outputPath);
        assertImageHasBarcodes(outputPath, 1, List.of(expected(DecodeType.QR, codeText)));
    }

    /**
     * Generates a QR Code from a raw byte sequence and verifies that the
     * decoded binary payload is identical to the source bytes.
     */
    @Test
    public void generateQrFromBytes() throws Exception {
        byte[] payload = {
                0x42, 0x49, 0x4E, 0x41, 0x52, 0x59,
                0x2D,
                0x01, 0x02, 0x03,
                0x7F
        };

        String outputPath = ExampleAssist.pathCombine(
                FOLDER,
                "qr_bytes.png"
        );

        BarcodeGenerator generator = new BarcodeGenerator(EncodeTypes.QR);
        generator.setCodeText(payload);

        // Encode the supplied byte array directly without text transcoding.
        generator.getParameters()
                .getBarcode()
                .getQR()
                .setQrEncodeMode(QREncodeMode.BYTES);

        generator.save(outputPath, BarCodeImageFormat.PNG);

        ExampleAssist.assertFileCreated(outputPath);

        assertImageHasBarcodes(
                outputPath,
                1,
                List.of(expected(DecodeType.QR, payload))
        );
    }
}
