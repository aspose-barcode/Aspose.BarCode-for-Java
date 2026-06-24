package com.aspose.barcode.guide.complex.troubleshooting;

import com.aspose.barcode.BarCodeException;
import com.aspose.barcode.barcoderecognition.BarCodeReader;
import com.aspose.barcode.barcoderecognition.BarCodeResult;
import com.aspose.barcode.barcoderecognition.DecodeType;
import com.aspose.barcode.complexbarcode.ComplexCodetextReader;
import com.aspose.barcode.complexbarcode.MailmarkCodetext;
import com.aspose.barcode.complexbarcode.SwissQRCodetext;
import com.aspose.barcode.guide.common.ExampleAssist;
import com.aspose.barcode.guide.common.LicenseAssist;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * Demonstrates common troubleshooting scenarios for complex barcodes with
 * Aspose.BarCode for Java.
 *
 * <p>The examples distinguish image-recognition failures from codetext-decoding
 * failures, show how missing required fields are reported during payload
 * construction, and demonstrate how invalid standard-specific values can be
 * detected before a barcode is generated.</p>
 */
public class ComplexBarcodeTroubleshooting {

    private static final String FOLDER =
            ExampleAssist.getOrCreateResourceFolderPath(
                    "complex", "troubleshooting");

    /**
     * Initializes the Aspose.BarCode license before running the troubleshooting
     * examples.
     */
    @BeforeClass
    public void setUp() {
        LicenseAssist.setupLicense();
    }

    /**
     * Demonstrates how to detect that an image does not contain a recognizable
     * barcode.
     *
     * <p>The test creates a blank image, scans it for all supported symbologies,
     * and verifies that the reader returns an empty result array. This indicates
     * an image-recognition problem rather than a complex codetext parsing problem.</p>
     */
    @Test
    public void handleBarcodeNotRecognized() throws Exception {
        BufferedImage blankImage = new BufferedImage(320, 180, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics = blankImage.createGraphics();
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, blankImage.getWidth(), blankImage.getHeight());

        String outputPath = ExampleAssist.pathCombine(FOLDER, "blank_image.png");
        ImageIO.write(blankImage, "png", new File(outputPath));

        BarCodeReader reader = new BarCodeReader(outputPath, DecodeType.ALL_SUPPORTED_TYPES);
        BarCodeResult[] results = reader.readBarCodes();

        Assert.assertEquals(results.length, 0);
    }

    /**
     * Demonstrates how to handle recognized text that cannot be decoded as the
     * requested complex barcode standard.
     *
     * <p>The try-decode methods return {@code null} for unsupported content, so
     * application code can try a different decoder or report that the payload is
     * not a supported complex barcode.</p>
     */
    @Test
    public void handleCodetextCannotBeDecoded() {
        String invalidCodetext = "NOT-A-COMPLEX-BARCODE";

        Assert.assertNull(ComplexCodetextReader.tryDecodeSwissQR(invalidCodetext));
        Assert.assertNull(ComplexCodetextReader.tryDecodeHIBCLIC(invalidCodetext));
        Assert.assertNull(ComplexCodetextReader.tryDecodeHIBCPAS(invalidCodetext));
        Assert.assertNull(ComplexCodetextReader.tryDecodeMailmark(invalidCodetext));
        Assert.assertNull(ComplexCodetextReader.tryDecodeMailmark2D(invalidCodetext));
    }

    /**
     * Demonstrates how missing required business fields are reported before image
     * generation.
     *
     * <p>An empty Swiss QR object does not contain the required valid IBAN. Calling
     * {@code getConstructedCodetext()} therefore raises {@link BarCodeException},
     * allowing the application to correct the business data before rendering.</p>
     */
    @Test
    public void handleRequiredFieldsMissing() {
        SwissQRCodetext incompleteCodetext = new SwissQRCodetext();
        boolean exceptionThrown = false;

        try {
            incompleteCodetext.getConstructedCodetext();
        } catch (BarCodeException exception) {
            exceptionThrown = true;
            Assert.assertTrue(exception.getMessage().contains("IBAN"));
        }

        Assert.assertTrue(exceptionThrown);
    }

    /**
     * Demonstrates how invalid values that do not conform to a barcode standard
     * are rejected during codetext construction.
     *
     * <p>The example supplies an incorrectly formatted Mailmark destination code.
     * The API raises {@link BarCodeException} instead of generating a payload that
     * does not comply with the Royal Mail Mailmark specification.</p>
     */
    @Test
    public void handleGeneratedDataDoesNotMatchStandard() {
        MailmarkCodetext invalidCodetext = new MailmarkCodetext();
        invalidCodetext.setFormat(1);
        invalidCodetext.setVersionID(1);
        invalidCodetext.setClass("1");
        invalidCodetext.setSupplychainID(99);
        invalidCodetext.setItemID(12345678);
        invalidCodetext.setDestinationPostCodePlusDPS("INVALID");
        boolean exceptionThrown = false;

        try {
            invalidCodetext.getConstructedCodetext();
        } catch (Exception exception) {
            exceptionThrown = true;
            Assert.assertTrue(exception.getMessage().contains("Destination Post Code"));
        }

        Assert.assertTrue(exceptionThrown);
    }
}
