package com.aspose.barcode.guide.recognition.special_parameters;

import com.aspose.barcode.barcoderecognition.BarCodeReader;
import com.aspose.barcode.barcoderecognition.BarCodeResult;
import com.aspose.barcode.barcoderecognition.ChecksumValidation;
import com.aspose.barcode.barcoderecognition.DecodeType;
import com.aspose.barcode.generation.BarCodeImageFormat;
import com.aspose.barcode.generation.BarcodeGenerator;
import com.aspose.barcode.generation.BaseEncodeType;
import com.aspose.barcode.generation.EncodeTypes;
import com.aspose.barcode.guide.common.ExampleAssist;
import com.aspose.barcode.guide.common.Generator;
import com.aspose.barcode.guide.common.LicenseAssist;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Demonstrates how {@link ChecksumValidation} affects recognition results.
 * <p>
 * We use EAN-13 because it has a mandatory check digit. The test creates:
 * <ul>
 *   <li>a valid EAN-13 image (checksum OK);</li>
 *   <li>a "damaged" EAN-13 image (partially overpainted) to emulate a bad checksum.</li>
 * </ul>
 * Then each image is read with {@code ChecksumValidation = ON, OFF, AUTO} to show
 * how the checksum gate filters results.
 */
public class ChecksumValidationExample
{

    private static final String FOLDER =
            ExampleAssist.getOrCreateResourceFolderPath("recognition", "special_parameters", "checksum_validation");

    @BeforeClass
    public void setUp()
    {
        LicenseAssist.setupLicense();
    }

    /**
     * Valid EAN-13 is recognized regardless of the checksum mode.
     * Here we assert the strictest mode: {@code ON}.
     */
    @Test
    public void read_Ean13_Valid_ChecksumOn() throws Exception
    {
        final String fileName = "ean13_valid.png";

        ExampleAssist.checkOrCreateImage(FOLDER, fileName, fullPath -> {
            BarcodeGenerator barcodeGenerator = new BarcodeGenerator(EncodeTypes.EAN_13, "5901234123457");
            barcodeGenerator.save(fullPath);
    });

        BarCodeReader reader = new BarCodeReader(ExampleAssist.pathCombine(FOLDER, fileName), DecodeType.EAN_13);
        reader.getBarcodeSettings().setChecksumValidation(ChecksumValidation.ON);
        ExampleAssist.assertRecognized(reader, fileName, 1, DecodeType.EAN_13);
    }


    /**
     * Damaged EAN-13 should be rejected when {@code ChecksumValidation = ON}.
     */
    @Test
    public void read_Ean13_Damaged_ChecksumOn() throws Exception
    {
        final String fileName = "ean13_damaged_on.png";
        generateDamagedEan13("5901234123457", fileName, EncodeTypes.EAN_13);
        BarCodeReader reader = new BarCodeReader(ExampleAssist.pathCombine(FOLDER, fileName), DecodeType.EAN_13);
        reader.getBarcodeSettings().setChecksumValidation(ChecksumValidation.ON);
        BarCodeResult[] results = reader.readBarCodes();
        System.out.println("Checksum=ON, detected: " + results.length);
        ExampleAssist.assertRecognized(reader, fileName, 0, DecodeType.EAN_13);
    }

    /**
     * The same damaged EAN-13 can still be returned when {@code ChecksumValidation = OFF}
     * because the engine skips the check digit filter.
     */
    @Test
    public void read_Ean13_Damaged_ChecksumOff() throws Exception
    {
        final String fileName = "ean13_damaged_off.png";
        generateDamagedEan13("5901234123457", fileName, EncodeTypes.EAN_13);
        BarCodeReader reader = new BarCodeReader(ExampleAssist.pathCombine(FOLDER, fileName), DecodeType.EAN_13);
        reader.getBarcodeSettings().setChecksumValidation(ChecksumValidation.OFF);
        BarCodeResult[] results = reader.readBarCodes();
        System.out.println("Checksum=OFF, detected: " + results.length);
        for (BarCodeResult r : results)
        {
            System.out.println("  -> " + r.getCodeTypeName() + " | " + r.getCodeText());
        }
        // Expect at least one "possibly wrong" result to pass through.
        ExampleAssist.assertRecognized(reader, fileName, 1, DecodeType.EAN_13);
    }

    /**
     * ChecksumValidation.DEFAULT.
     * For EAN/UPC it usually validates checksums,
     * so damaged code will be filtered out similarly to {@code ON}.
     */
    @Test
    public void read_Ean13_Damaged_ChecksumAuto() throws Exception
    {
        final String fileName = "ean13_damaged_auto.png";
        generateDamagedEan13("5901234123457", fileName, EncodeTypes.EAN_13);

        BarCodeReader reader = new BarCodeReader(ExampleAssist.pathCombine(FOLDER, fileName), DecodeType.EAN_13);
        reader.getBarcodeSettings().setChecksumValidation(ChecksumValidation.DEFAULT);

        BarCodeResult[] results = reader.readBarCodes();
        System.out.println("Checksum=AUTO, detected: " + results.length);

        ExampleAssist.assertRecognized(reader, fileName, 0, DecodeType.EAN_13);
    }

    // =====================================================================
    // Image generation helpers
    // =====================================================================

    /**
     * Generates an EAN-13 image and then draws a black rectangle over bars
     * to emulate checksum corruption. We do not try to craft a specific wrong
     * check digit because the generator always produces a correct symbol;
     * instead, partial occlusion leads the decoder to fail checksum validation.
     */
    private void generateDamagedEan13(String data, String fileName, BaseEncodeType type) throws IOException
    {
//        BarcodeGenerator barcodeGenerator = new BarcodeGenerator(type,data);
//        barcodeGenerator.save(FOLDER + fileName);
        String testImagePath = ExampleAssist.generateTestBarcode(data, FOLDER, fileName, type);
        File testFile = new File(testImagePath);
        // 2) Overpaint a chunk to make the symbol inconsistent
        BufferedImage img = ImageIO.read(testFile);
        Graphics2D g = img.createGraphics();
        try
        {
            g.setColor(Color.BLACK);
            int w = img.getWidth();
            int h = img.getHeight();
            // cover a vertical stripe across bars (keeps finder quiet zone mostly intact)
            int stripeW = Math.max(6, w / 12);
            g.fillRect(w / 2 - stripeW / 2, h / 5, stripeW, (int) (h * 0.6));
        }
        finally
        {
            g.dispose();
        }

        // 3) Save the damaged image and clean up
        ImageIO.write(img, "PNG", Path.of(testImagePath).toFile());
//        Files.deleteIfExists(tmp);
    }
    private String getFullPath(String fileName)
    {
        return FOLDER + File.separator + fileName;
    }

}
