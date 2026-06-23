package com.aspose.barcode.guide.generation.overview;

import com.aspose.barcode.barcoderecognition.DecodeType;
import com.aspose.barcode.generation.BarCodeImageFormat;
import com.aspose.barcode.generation.BarcodeGenerator;
import com.aspose.barcode.generation.ECIEncodings;
import com.aspose.barcode.generation.EncodeTypes;
import com.aspose.barcode.generation.QREncodeMode;
import com.aspose.barcode.generation.QRErrorLevel;
import com.aspose.barcode.guide.common.ExampleAssist;
import com.aspose.barcode.guide.common.LicenseAssist;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.awt.Color;
import java.util.List;

import static com.aspose.barcode.guide.common.ExampleAssist.expected;

public class GenerateQRCode
{
    private static final String FOLDER = ExampleAssist.getOrCreateResourceFolderPath(
            "generation",
            "generate-qr-code"
    );

    @BeforeClass
    public void setUp()
    {
        LicenseAssist.setupLicense();
    }

    /**
     * Generates a basic QR Code using the default generation parameters.
     *
     * @throws Exception if generation, file validation, or recognition fails
     */
    @Test
    public void generateBasicQRCode() throws Exception
    {
        String codeText = "https://www.aspose.com";
        BarcodeGenerator generator = new BarcodeGenerator(EncodeTypes.QR, codeText);

        saveAndAssert(
                generator,
                "qrcode.png",
                DecodeType.QR,
                codeText
        );
    }

    /**
     * Generates a QR Code with the highest standard error correction level.
     *
     * @throws Exception if generation, file validation, or recognition fails
     */
    @Test
    public void defineErrorCorrectionLevel() throws Exception
    {
        String codeText = "Data with error correction";
        BarcodeGenerator generator = new BarcodeGenerator(EncodeTypes.QR, codeText);

        // LEVEL_H allows approximately 30% of codewords to be restored.
        generator.getParameters()
                .getBarcode()
                .getQR()
                .setErrorLevel(QRErrorLevel.LEVEL_H);

        saveAndAssert(
                generator,
                "qrcode_error_correction.png",
                DecodeType.QR,
                codeText
        );
    }

    /**
     * Sets the QR module size through X-Dimension.
     *
     * @throws Exception if generation, file validation, or recognition fails
     */
    @Test
    public void defineQRCodeSize() throws Exception
    {
        String codeText = "Set QR size";
        BarcodeGenerator generator = new BarcodeGenerator(EncodeTypes.QR, codeText);

        // X-Dimension controls the width and height of one QR module.
        generator.getParameters().getBarcode().getXDimension().setPixels(4);

        // Resolution is mainly relevant when physical measurement units are used.
        generator.getParameters().setResolution(300.0f);

        saveAndAssert(
                generator,
                "qrcode_sized.png",
                DecodeType.QR,
                codeText
        );
    }

    /**
     * Encodes Japanese text in a standard QR Code by using UTF-8 ECI mode.
     *
     * @throws Exception if generation, file validation, or recognition fails
     */
    @Test
    public void defineEncodingMode() throws Exception
    {
        String codeText = "データ";
        BarcodeGenerator generator = new BarcodeGenerator(EncodeTypes.QR, codeText);

        generator.getParameters().getBarcode().getQR().setEncodeMode(QREncodeMode.ECI);
        generator.getParameters().getBarcode().getQR().setECIEncoding(ECIEncodings.UTF8);

        saveAndAssert(
                generator,
                "qrcode_utf8.png",
                DecodeType.QR,
                codeText
        );
    }

    /**
     * Combines module size, error correction, UTF-8 ECI, colors, and a four-module margin.
     *
     * @throws Exception if generation, file validation, or recognition fails
     */
    @Test
    public void completeExample() throws Exception
    {
        String codeText = "Aspose QR — データ";
        BarcodeGenerator generator = new BarcodeGenerator(EncodeTypes.QR, codeText);

        generator.getParameters().getBarcode().getXDimension().setPixels(4);
        generator.getParameters().setResolution(300.0f);

        generator.getParameters().getBarcode().getQR().setErrorLevel(QRErrorLevel.LEVEL_M);
        generator.getParameters().getBarcode().getQR().setEncodeMode(QREncodeMode.ECI);
        generator.getParameters().getBarcode().getQR().setECIEncoding(ECIEncodings.UTF8);

        generator.getParameters().setBackColor(Color.WHITE);
        generator.getParameters().getBarcode().setBarColor(Color.BLACK);

        // X-Dimension is 4 px, so 16 px reserves a four-module margin.
        generator.getParameters().getBarcode().getPadding().getLeft().setPixels(16);
        generator.getParameters().getBarcode().getPadding().getRight().setPixels(16);
        generator.getParameters().getBarcode().getPadding().getTop().setPixels(16);
        generator.getParameters().getBarcode().getPadding().getBottom().setPixels(16);

        saveAndAssert(
                generator,
                "qrcode_complete.png",
                DecodeType.QR,
                codeText
        );
    }

    /**
     * Generates a compact Micro QR Code for a short payload.
     *
     * @throws Exception if generation, file validation, or recognition fails
     */
    @Test
    public void generateMicroQR() throws Exception
    {
        String codeText = "MicroQR1234567";
        BarcodeGenerator generator = new BarcodeGenerator(EncodeTypes.MICRO_QR, codeText);

        generator.getParameters().getBarcode().getXDimension().setPixels(4);

        saveAndAssert(
                generator,
                "micro_qr_basic.png",
                DecodeType.MICRO_QR,
                codeText
        );
    }

    /**
     * Encodes a URL as plain text in a QR Code.
     *
     * @throws Exception if generation, file validation, or recognition fails
     */
    @Test
    public void encodeURL() throws Exception
    {
        String codeText = "https://docs.aspose.com/";
        BarcodeGenerator generator = new BarcodeGenerator(EncodeTypes.QR, codeText);

        saveAndAssert(
                generator,
                "url_qrcode.png",
                DecodeType.QR,
                codeText
        );
    }

    /**
     * Generates standard QR and Micro QR images to demonstrate their different use cases.
     *
     * @throws Exception if generation, file validation, or recognition fails
     */
    @Test
    public void compareQRandMicroQR() throws Exception
    {
        String standardQrText = "https://example.com/product/12345";
        BarcodeGenerator standardQrGenerator = new BarcodeGenerator(
                EncodeTypes.QR,
                standardQrText
        );

        saveAndAssert(
                standardQrGenerator,
                "standard_qr.png",
                DecodeType.QR,
                standardQrText
        );

        String microQrText = "ID-12345";
        BarcodeGenerator microQrGenerator = new BarcodeGenerator(
                EncodeTypes.MICRO_QR,
                microQrText
        );

        saveAndAssert(
                microQrGenerator,
                "micro_qr_comparison.png",
                DecodeType.MICRO_QR,
                microQrText
        );
    }

    /**
     * Encodes a Unicode vCard by using UTF-8 ECI mode.
     *
     * @throws Exception if generation, file validation, or recognition fails
     */
    @Test
    public void encodeVCard() throws Exception
    {
        String vcard = "BEGIN:VCARD\n"
                + "VERSION:3.0\n"
                + "FN:山田 太郎\n"
                + "TEL:+81-3-1234-5678\n"
                + "EMAIL:taro@example.com\n"
                + "END:VCARD";

        BarcodeGenerator generator = new BarcodeGenerator(EncodeTypes.QR, vcard);
        generator.getParameters().getBarcode().getQR().setEncodeMode(QREncodeMode.ECI);
        generator.getParameters().getBarcode().getQR().setECIEncoding(ECIEncodings.UTF8);

        saveAndAssert(
                generator,
                "vcard_qrcode.png",
                DecodeType.QR,
                vcard
        );
    }

    /**
     * Encodes Wi-Fi configuration data as a QR Code payload.
     *
     * @throws Exception if generation, file validation, or recognition fails
     */
    @Test
    public void encodeWiFi() throws Exception
    {
        String wifi = "WIFI:T:WPA;S:MyNetwork;P:MyPassword;;";
        BarcodeGenerator generator = new BarcodeGenerator(EncodeTypes.QR, wifi);

        saveAndAssert(
                generator,
                "wifi_qrcode.png",
                DecodeType.QR,
                wifi
        );
    }

    /**
     * Saves a barcode as PNG, verifies the output file, and reads it back.
     *
     * @param generator        configured barcode generator
     * @param fileName         output file name inside the example resource folder
     * @param expectedType     expected barcode type during recognition
     * @param expectedCodeText expected decoded text
     * @throws Exception if generation, file validation, or recognition fails
     */
    private static void saveAndAssert(BarcodeGenerator generator,
                                      String fileName,
                                      com.aspose.barcode.barcoderecognition.BaseDecodeType expectedType,
                                      String expectedCodeText) throws Exception
    {
        String outputPath = ExampleAssist.pathCombine(FOLDER, fileName);

        generator.save(outputPath, BarCodeImageFormat.PNG);
        ExampleAssist.assertFileCreated(outputPath);
        ExampleAssist.assertImageHasBarcodes(
                outputPath,
                1,
                List.of(expected(expectedType, expectedCodeText))
        );
    }
}
