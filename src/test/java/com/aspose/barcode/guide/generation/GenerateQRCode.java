package com.aspose.barcode.guide.generation;

import com.aspose.barcode.generation.*;
import com.aspose.barcode.guide.common.ExampleAssist;
import com.aspose.barcode.guide.common.LicenseAssist;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;

public class GenerateQRCode
{
    private static final String folder = ExampleAssist.getResourceFolderPath("generation", "generate-qr-code");
    @BeforeClass
    public void setUp()
    {
        LicenseAssist.setupLicense();
    }

    /**
     * QR codes support different error correction levels that allow the barcode to be read even if it's partially damaged
     * Error correction levels explained:
     * Level L: ~7% of codewords can be restored
     * Level M: ~15% of codewords can be restored (default)
     * Level Q: ~25% of codewords can be restored
     * Level H: ~30% of codewords can be restored
     */
    @Test
    public void defineErrorCorrectionLevel() throws IOException
    {
        BarcodeGenerator generator = new BarcodeGenerator(EncodeTypes.QR, "Data with error correction");
         // Set error correction level
        // Options: LEVEL_L (7%), LEVEL_M (15%), LEVEL_Q (25%), LEVEL_H (30%)
        generator.getParameters().getBarcode().getQR().setQrErrorLevel(QRErrorLevel.LEVEL_H);
        generator.save(folder + "qrcode_error_correction.png");
    }

    /**
     * Setting QR Code Size
     */
    @Test
    public void defineQRCodeSize() throws IOException
    {
        BarcodeGenerator generator = new BarcodeGenerator(EncodeTypes.QR, "Set QR size");
        // Set barcode dimensions in pixels
        generator.getParameters().getBarcode().getXDimension().setPixels(4);
        // Set image resolution
        generator.getParameters().setResolution(300);
        generator.save(folder + "qrcode_sized.png");
    }

    @Test
    public void defineEncodingMode() throws IOException
    {
        BarcodeGenerator generator = new BarcodeGenerator(EncodeTypes.QR, "データ");
        generator.getParameters().getBarcode().getQR().setQrEncodeMode(QREncodeMode.ECI);
        generator.getParameters().getBarcode().getQR().setQrECIEncoding(ECIEncodings.UTF8);
        generator.save(folder + "qrcode_utf8.png");
    }

    @Test
    public void completeExample() throws IOException
    {
        try {
            // Initialize barcode generator
            BarcodeGenerator generator = new BarcodeGenerator(EncodeTypes.QR,"https://www.aspose.com"
            );

            // Configure appearance
            generator.getParameters().getBarcode().getXDimension().setPixels(4);
            generator.getParameters().setResolution(300);

            // Configure QR-specific settings
            generator.getParameters().getBarcode().getQR()
                    .setQrErrorLevel(QRErrorLevel.LEVEL_M);
            generator.getParameters().getBarcode().getQR()
                    .setQrECIEncoding(ECIEncodings.UTF8);
            generator.getParameters().getBarcode().getQR()
                    .setQrEncodeMode(QREncodeMode.ECI);

            // Set colors
            generator.getParameters().setBackColor(java.awt.Color.WHITE);
            generator.getParameters().getBarcode().setBarColor(java.awt.Color.BLACK);

            // Save to file
            generator.save(folder + "qrcode_complete.png");

            System.out.println("QR Code generated successfully!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * For smaller data amounts, you can use Micro QR Code which is a more compact version
     * @throws IOException
     */
    @Test
    public void generateMicroQR() throws IOException
    {
        BarcodeGenerator generator = new BarcodeGenerator(EncodeTypes.MICRO_QR, "MicroQR1234567");

        // Micro QR specific settings
        generator.getParameters().getBarcode().getXDimension().setPixels(4);

        generator.save(folder + "micro_qr.png");
    }

    @Test
    public void encodeURL() throws IOException
    {
        BarcodeGenerator generator = new BarcodeGenerator(EncodeTypes.QR,"https://docs.aspose.com/");
        generator.save("url_qrcode.png");
    }

    @Test
    public void compareQRandMicroQR() throws IOException
    {
        // Standard QR - for URL or longer data
        BarcodeGenerator standardQR = new BarcodeGenerator(EncodeTypes.QR,"https://example.com/product/12345"
        );
        standardQR.save(folder + "standard_qr.png");

        // Micro QR - for short ID or serial number
        BarcodeGenerator microQR = new BarcodeGenerator(EncodeTypes.MICRO_QR,"ID-12345");
        microQR.save(folder + "micro_qr.png");
    }

    @Test
    public void encodeVCard() throws IOException
    {
        String vcard = "BEGIN:VCARD\n" +
                "VERSION:3.0\n" +
                "FN:John Doe\n" +
                "TEL:+1-555-1234\n" +
                "EMAIL:john@example.com\n" +
                "END:VCARD";

        BarcodeGenerator generator = new BarcodeGenerator(EncodeTypes.QR, vcard);

        // Use ECI mode for proper UTF-8 encoding
        generator.getParameters().getBarcode().getQR().setQrEncodeMode(QREncodeMode.ECI);
        generator.getParameters().getBarcode().getQR().setQrECIEncoding(ECIEncodings.UTF8);

        generator.save(folder + "vcard_qrcode.png");
    }

    @Test
    public void encodeWiFi() throws IOException
    {
        String wifi = "WIFI:T:WPA;S:MyNetwork;P:MyPassword;;";

        BarcodeGenerator generator = new BarcodeGenerator(EncodeTypes.QR, wifi);
        generator.save(folder + "wifi_qrcode.png");
    }



}
