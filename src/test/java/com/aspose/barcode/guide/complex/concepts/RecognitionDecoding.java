package com.aspose.barcode.guide.complex.concepts;

import com.aspose.barcode.barcoderecognition.BarCodeReader;
import com.aspose.barcode.barcoderecognition.BarCodeResult;
import com.aspose.barcode.barcoderecognition.DecodeType;
import com.aspose.barcode.complexbarcode.Address;
import com.aspose.barcode.complexbarcode.ComplexBarcodeGenerator;
import com.aspose.barcode.complexbarcode.ComplexCodetextReader;
import com.aspose.barcode.complexbarcode.QrBillStandardVersion;
import com.aspose.barcode.complexbarcode.SwissQRCodetext;
import com.aspose.barcode.generation.BarCodeImageFormat;
import com.aspose.barcode.generation.BarcodeGenerator;
import com.aspose.barcode.generation.EncodeTypes;
import com.aspose.barcode.guide.common.ExampleAssist;
import com.aspose.barcode.guide.common.LicenseAssist;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * Demonstrates the difference between barcode recognition and complex barcode
 * decoding in Aspose.BarCode for Java.
 *
 * <p>Recognition reads the physical barcode symbol and returns {@link BarCodeResult}.
 * Decoding interprets the recognized text as a specific complex barcode standard
 * and restores a typed Java object, such as {@link SwissQRCodetext}.</p>
 */
public class RecognitionDecoding {

    private static final String FOLDER =
            ExampleAssist.getOrCreateResourceFolderPath(
                    "complex", "recognition-vs-decoding");

    /**
     * Initializes the Aspose.BarCode license before running the examples in this
     * class so that generation and recognition are performed without evaluation
     * limitations.
     */
    @BeforeClass
    public void setUp() {
        LicenseAssist.setupLicense();
    }

    /**
     * Demonstrates that recognition returns the carrier barcode type and raw code text,
     * not a typed complex barcode object.
     *
     * <p>The example generates a Swiss QR barcode. {@link BarCodeReader} recognizes
     * it as {@link DecodeType#QR} and returns {@link BarCodeResult}. The result text
     * contains the standardized Swiss QR payload, but it is still plain text at this
     * stage.</p>
     */
    @Test
    public void recognizeCarrierBarcodeOnly() throws Exception {
        SwissQRCodetext sourceCodetext = createSwissQRCodetext();
        String outputPath = ExampleAssist.pathCombine(FOLDER, "recognized_swiss_qr.png");

        ComplexBarcodeGenerator generator = new ComplexBarcodeGenerator(sourceCodetext);
        generator.save(outputPath, BarCodeImageFormat.PNG);

        BarCodeReader reader = new BarCodeReader(outputPath, DecodeType.QR);
        BarCodeResult[] results = reader.readBarCodes();

        Assert.assertEquals(results.length, 1);
        Assert.assertEquals(results[0].getCodeType(), DecodeType.QR);
        Assert.assertEquals(results[0].getCodeText(), sourceCodetext.getConstructedCodetext());
        Assert.assertTrue(results[0].getCodeText().startsWith("SPC"));
    }

    /**
     * Demonstrates the second step: decoding recognized text into a typed complex
     * barcode object.
     *
     * <p>After recognition, the application passes {@link BarCodeResult#getCodeText()}
     * to {@link ComplexCodetextReader#tryDecodeSwissQR(String)}. The decoder restores
     * a {@link SwissQRCodetext} object, so application code can access business fields
     * such as account, amount, currency, creditor, and debtor.</p>
     */
    @Test
    public void decodeRecognizedTextAsSwissQR() throws Exception {
        SwissQRCodetext sourceCodetext = createSwissQRCodetext();
        String outputPath = ExampleAssist.pathCombine(FOLDER, "decoded_swiss_qr.png");

        ComplexBarcodeGenerator generator = new ComplexBarcodeGenerator(sourceCodetext);
        generator.save(outputPath, BarCodeImageFormat.PNG);

        BarCodeReader reader = new BarCodeReader(outputPath, DecodeType.QR);
        BarCodeResult[] results = reader.readBarCodes();
        Assert.assertEquals(results.length, 1);

        SwissQRCodetext decodedCodetext =
                ComplexCodetextReader.tryDecodeSwissQR(results[0].getCodeText());

        Assert.assertNotNull(decodedCodetext);
        Assert.assertEquals(decodedCodetext.getBill().getAccount(), sourceCodetext.getBill().getAccount());
        Assert.assertEquals(decodedCodetext.getBill().getAmount(), sourceCodetext.getBill().getAmount());
        Assert.assertEquals(decodedCodetext.getBill().getCreditor().getName(), "Robert Schneider AG");
    }

    /**
     * Demonstrates that a carrier barcode can be recognized successfully even when
     * its text is not a supported complex barcode payload.
     *
     * <p>The QR Code is recognized because it is a valid QR symbol. However, Swiss QR
     * decoding returns {@code null} because the recognized text does not follow the
     * Swiss QR payment format.</p>
     */
    @Test
    public void recognitionCanSucceedWhenComplexDecodingFails() throws Exception {
        String plainText = "This is a valid QR Code, but not a Swiss QR payment.";
        String outputPath = ExampleAssist.pathCombine(FOLDER, "plain_qr_not_swiss_qr.png");

        BarcodeGenerator generator = new BarcodeGenerator(EncodeTypes.QR, plainText);
        generator.save(outputPath, BarCodeImageFormat.PNG);

        BarCodeReader reader = new BarCodeReader(outputPath, DecodeType.QR);
        BarCodeResult[] results = reader.readBarCodes();

        Assert.assertEquals(results.length, 1);
        Assert.assertEquals(results[0].getCodeType(), DecodeType.QR);
        Assert.assertEquals(results[0].getCodeText(), plainText);
        Assert.assertNull(ComplexCodetextReader.tryDecodeSwissQR(results[0].getCodeText()));
    }

    /**
     * Creates a valid Swiss QR payment model used by the examples in this class.
     */
    private SwissQRCodetext createSwissQRCodetext() {
        SwissQRCodetext codetext = new SwissQRCodetext();
        codetext.getBill().setVersion(QrBillStandardVersion.V2_0);
        codetext.getBill().setAccount("CH4431999123000889012");
        codetext.getBill().setAmount(100.25);
        codetext.getBill().setCurrency("CHF");
        codetext.getBill().setCreditor(createAddress(
                "Robert Schneider AG", "Rue du Lac", "1268", "2501", "Biel"));
        codetext.getBill().setDebtor(createAddress(
                "Max Mustermann", "Musterstrasse", "1", "8000", "Zurich"));
        codetext.getBill().setReference("210000000003139471430009017");
        codetext.getBill().setUnstructuredMessage("Invoice 2026-001");
        return codetext;
    }

    /**
     * Creates a structured Swiss address for a Swiss QR payment model.
     */
    private Address createAddress(
            String name,
            String street,
            String houseNumber,
            String postalCode,
            String town) {
        Address address = new Address();
        address.setName(name);
        address.setStreet(street);
        address.setHouseNo(houseNumber);
        address.setPostalCode(postalCode);
        address.setTown(town);
        address.setCountryCode("CH");
        return address;
    }
}
