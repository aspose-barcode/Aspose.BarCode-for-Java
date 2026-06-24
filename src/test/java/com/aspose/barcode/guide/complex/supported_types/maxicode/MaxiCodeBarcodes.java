package com.aspose.barcode.guide.complex.supported_types.maxicode;

import com.aspose.barcode.barcoderecognition.BarCodeReader;
import com.aspose.barcode.barcoderecognition.BarCodeResult;
import com.aspose.barcode.barcoderecognition.DecodeType;
import com.aspose.barcode.complexbarcode.ComplexBarcodeGenerator;
import com.aspose.barcode.complexbarcode.ComplexCodetextReader;
import com.aspose.barcode.complexbarcode.MaxiCodeCodetext;
import com.aspose.barcode.complexbarcode.MaxiCodeCodetextMode2;
import com.aspose.barcode.complexbarcode.MaxiCodeCodetextMode3;
import com.aspose.barcode.complexbarcode.MaxiCodeStructuredSecondMessage;
import com.aspose.barcode.generation.BarCodeImageFormat;
import com.aspose.barcode.guide.common.ExampleAssist;
import com.aspose.barcode.guide.common.LicenseAssist;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * Demonstrates structured MaxiCode mode 2 and mode 3 workflows with
 * Aspose.BarCode for Java.
 *
 * <p>The mode 2 example uses a numeric postal code and a structured second message.
 * The mode 3 example uses an alphanumeric postal code and a structured second
 * message. Both examples obtain the recognized MaxiCode mode from extended
 * recognition parameters before decoding the complex payload.</p>
 */
public class MaxiCodeBarcodes {

    private static final String FOLDER =
            ExampleAssist.getOrCreateResourceFolderPath("complex", "maxicode");

    /**
     * Initializes the Aspose.BarCode license before running the MaxiCode examples.
     */
    @BeforeClass
    public void setUp() {
        LicenseAssist.setupLicense();
    }

    /**
     * Demonstrates how to generate and read MaxiCode mode 2 with a numeric postal
     * code and a structured second message.
     *
     * <p>The recognized mode is taken from MaxiCode extended parameters and passed
     * to the complex codetext reader so that the correct structured type is
     * restored.</p>
     */
    @Test
    public void generateAndReadMaxiCodeMode2() throws Exception {
        MaxiCodeCodetextMode2 sourceCodetext = createMaxiCodeMode2();
        String outputPath = ExampleAssist.pathCombine(FOLDER, "maxicode_mode_2.png");

        ComplexBarcodeGenerator generator = new ComplexBarcodeGenerator(sourceCodetext);
        generator.save(outputPath, BarCodeImageFormat.PNG);

        ExampleAssist.assertFileCreated(outputPath);
        BarCodeReader reader = new BarCodeReader(outputPath, DecodeType.MAXI_CODE);
        BarCodeResult[] results = reader.readBarCodes();
        Assert.assertEquals(results.length, 1);

        MaxiCodeCodetext decodedBase = ComplexCodetextReader.tryDecodeMaxiCode(
                results[0].getExtended().getMaxiCode().getMode(),
                results[0].getCodeText());

        Assert.assertTrue(decodedBase instanceof MaxiCodeCodetextMode2);
        MaxiCodeCodetextMode2 decodedCodetext = (MaxiCodeCodetextMode2) decodedBase;
        Assert.assertEquals(decodedCodetext.getPostalCode(), "524032140");
        Assert.assertEquals(decodedCodetext.getCountryCode(), 56);
        Assert.assertEquals(decodedCodetext.getServiceCategory(), 999);
        Assert.assertTrue(decodedCodetext.getSecondMessage() instanceof MaxiCodeStructuredSecondMessage);
        MaxiCodeStructuredSecondMessage decodedMessage =
                (MaxiCodeStructuredSecondMessage) decodedCodetext.getSecondMessage();
        Assert.assertEquals(decodedMessage.getYear(), 26);
        Assert.assertEquals(decodedMessage.getIdentifiers().size(), 2);
        Assert.assertEquals(decodedMessage.getIdentifiers().get(0), "MODE 2 MESSAGE");
    }

    /**
     * Demonstrates how to generate and read MaxiCode mode 3 with an alphanumeric
     * postal code and a structured second message.
     *
     * <p>The structured message contains address-related identifiers and a year,
     * which are restored as a typed {@link MaxiCodeStructuredSecondMessage} after
     * the image is recognized.</p>
     */
    @Test
    public void generateAndReadMaxiCodeMode3() throws Exception {
        MaxiCodeCodetextMode3 sourceCodetext = createMaxiCodeMode3();
        String outputPath = ExampleAssist.pathCombine(FOLDER, "maxicode_mode_3.png");

        ComplexBarcodeGenerator generator = new ComplexBarcodeGenerator(sourceCodetext);
        generator.save(outputPath, BarCodeImageFormat.PNG);

        ExampleAssist.assertFileCreated(outputPath);
        BarCodeReader reader = new BarCodeReader(outputPath, DecodeType.MAXI_CODE);
        BarCodeResult[] results = reader.readBarCodes();
        Assert.assertEquals(results.length, 1);

        MaxiCodeCodetext decodedBase = ComplexCodetextReader.tryDecodeMaxiCode(
                results[0].getExtended().getMaxiCode().getMode(),
                results[0].getCodeText());

        Assert.assertTrue(decodedBase instanceof MaxiCodeCodetextMode3);
        MaxiCodeCodetextMode3 decodedCodetext = (MaxiCodeCodetextMode3) decodedBase;
        Assert.assertEquals(decodedCodetext.getPostalCode().trim(), "B1050");
        Assert.assertEquals(decodedCodetext.getCountryCode(), 56);
        Assert.assertEquals(decodedCodetext.getServiceCategory(), 999);
        Assert.assertTrue(decodedCodetext.getSecondMessage() instanceof MaxiCodeStructuredSecondMessage);

        MaxiCodeStructuredSecondMessage decodedMessage =
                (MaxiCodeStructuredSecondMessage) decodedCodetext.getSecondMessage();
        Assert.assertEquals(decodedMessage.getYear(), 99);
        Assert.assertEquals(decodedMessage.getIdentifiers().size(), 3);
        Assert.assertEquals(decodedMessage.getIdentifiers().get(0), "634 ALPHA DRIVE");
    }

    /**
     * Creates a MaxiCode mode 2 payload with a structured second message.
     */
    private MaxiCodeCodetextMode2 createMaxiCodeMode2() {
        MaxiCodeCodetextMode2 codetext = new MaxiCodeCodetextMode2();
        codetext.setPostalCode("524032140");
        codetext.setCountryCode(56);
        codetext.setServiceCategory(999);

        MaxiCodeStructuredSecondMessage secondMessage = new MaxiCodeStructuredSecondMessage();
        secondMessage.add("MODE 2 MESSAGE");
        secondMessage.add("WAREHOUSE 12");
        secondMessage.setYear(26);
        codetext.setSecondMessage(secondMessage);
        return codetext;
    }

    /**
     * Creates a MaxiCode mode 3 payload with a structured second message.
     */
    private MaxiCodeCodetextMode3 createMaxiCodeMode3() {
        MaxiCodeCodetextMode3 codetext = new MaxiCodeCodetextMode3();
        codetext.setPostalCode("B1050");
        codetext.setCountryCode(56);
        codetext.setServiceCategory(999);

        MaxiCodeStructuredSecondMessage secondMessage = new MaxiCodeStructuredSecondMessage();
        secondMessage.add("634 ALPHA DRIVE");
        secondMessage.add("PITTSBURGH");
        secondMessage.add("PA");
        secondMessage.setYear(99);
        codetext.setSecondMessage(secondMessage);
        return codetext;
    }
}
