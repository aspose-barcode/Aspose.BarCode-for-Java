package com.aspose.barcode.guide.recognition.special_parameters;

import com.aspose.barcode.barcoderecognition.BarCodeReader;
import com.aspose.barcode.barcoderecognition.BarCodeResult;
import com.aspose.barcode.barcoderecognition.DecodeType;
import com.aspose.barcode.generation.BarCodeImageFormat;
import com.aspose.barcode.generation.BarcodeGenerator;
import com.aspose.barcode.generation.EncodeTypes;
import com.aspose.barcode.guide.common.ExampleAssist;
import com.aspose.barcode.guide.common.LicenseAssist;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.*;
import java.io.IOException;
import java.nio.file.Paths;

/**
 * Demonstrates how the {@code AllowIncorrectBarcodes} parameter affects recognition results.
 * <p>
 * Some barcodes can be partially damaged, have an invalid checksum, or contain incorrect characters.
 * Normally, such barcodes are filtered out by checksum validation and not returned.
 * <p>
 * Setting {@code AllowIncorrectBarcodes = true} forces the engine to return
 * even potentially invalid or partially corrupted barcodes — useful for forensics or debugging.
 */
public class AllowIncorrectBarcodesExample {

    private static final String FOLDER =
            ExampleAssist.getOrCreateResourceFolderPath("recognition", "special_parameters", "allow_incorrect");

    @BeforeClass
    public void setUp() {
        LicenseAssist.setupLicense();
    }

    /**
     * Demonstrates standard behavior (default: {@code AllowIncorrectBarcodes = false}).
     * <p>
     * A valid barcode is recognized normally. If the image is intact, disabling this flag has no effect.
     */
    @Test
    public void read_Code39_Valid_DisallowIncorrect() throws Exception {
        String fileName = "code39_valid.png";

        ExampleAssist.checkOrCreateImage(FOLDER, fileName, path -> {
            BarcodeGenerator generator = new BarcodeGenerator(EncodeTypes.CODE_39, "VALID123");
            generator.save(path, BarCodeImageFormat.PNG);
        });

        BarCodeReader reader = new BarCodeReader(ExampleAssist.pathCombine(FOLDER, fileName), DecodeType.CODE_39);
        reader.getQualitySettings().setAllowIncorrectBarcodes(false);

        ExampleAssist.assertRecognized(reader, fileName, 1, DecodeType.CODE_39);
    }

    /**
     * Demonstrates that a corrupted barcode is ignored when {@code AllowIncorrectBarcodes = false}.
     * <p>
     * The barcode image is intentionally damaged (painted over), so checksum verification fails.
     * In this mode, the engine discards the result as invalid.
     */
    @Test
    public void read_Code39_Damaged_DisallowIncorrect() throws Exception {
        String fileName = "code39_damaged_disallowed.png";

        ExampleAssist.checkOrCreateImage(FOLDER, fileName, this::generateDamagedCode39);

        BarCodeReader reader = new BarCodeReader(ExampleAssist.pathCombine(FOLDER, fileName), DecodeType.CODE_39);
        reader.getQualitySettings().setAllowIncorrectBarcodes(false);

        BarCodeResult[] results = reader.readBarCodes();
        System.out.println("Detected count (AllowIncorrect=false): " + results.length);

        // Expect 0 or fewer results because invalid codes are filtered
        if (results.length > 0) {
            System.out.println("Code Text: " + results[0].getCodeText());
        }

        ExampleAssist.assertRecognized(reader, fileName, 0, DecodeType.CODE_39);
    }

    /**
     * Demonstrates that the same damaged barcode can still be detected
     * when {@code AllowIncorrectBarcodes = true}.
     * <p>
     * The engine attempts to return even corrupted results, skipping checksum validation.
     */
    @Test
    public void read_Code39_Damaged_AllowIncorrect() throws Exception {
        String fileName = "code39_damaged_allowed.png";

        ExampleAssist.checkOrCreateImage(FOLDER, fileName, this::generateDamagedCode39);

        BarCodeReader reader = new BarCodeReader(ExampleAssist.pathCombine(FOLDER, fileName), DecodeType.CODE_39);
        reader.getQualitySettings().setAllowIncorrectBarcodes(true);

        BarCodeResult[] results = reader.readBarCodes();
        System.out.println("Detected count (AllowIncorrect=true): " + results.length);
        for (BarCodeResult r : results) {
            System.out.println("Code Text (possibly invalid): " + r.getCodeText());
        }

        // Now expect recognition even for damaged input
        ExampleAssist.assertRecognized(reader, fileName, 1, DecodeType.CODE_39);
    }

    /**
     * Compares recognition results with {@code AllowIncorrectBarcodes = false} and {@code true}.
     * <p>
     * This clearly shows that the damaged image is only recognized when the flag is enabled.
     */
    @Test
    public void compare_AllowIncorrect_Toggle() throws Exception {
        String fileName = "code39_compare.png";
        ExampleAssist.checkOrCreateImage(FOLDER, fileName, path -> generateDamagedCode39(path));

        // Without allowance
        BarCodeReader reader1 = new BarCodeReader(ExampleAssist.pathCombine(FOLDER, fileName), DecodeType.CODE_39);
        reader1.getQualitySettings().setAllowIncorrectBarcodes(false);
        var results1 = reader1.readBarCodes();

        // With allowance
        BarCodeReader reader2 = new BarCodeReader(ExampleAssist.pathCombine(FOLDER, fileName), DecodeType.CODE_39);
        reader2.getQualitySettings().setAllowIncorrectBarcodes(true);
        var results2 = reader2.readBarCodes();

        System.out.println("AllowIncorrect(false): " + results1.length + " result(s)");
        System.out.println("AllowIncorrect(true):  " + results2.length + " result(s)");

        if (results2.length > 0) {
            System.out.println("Recovered text: " + results2[0].getCodeText());
        }

        ExampleAssist.assertRecognized(reader2, fileName, 1, DecodeType.CODE_39);
    }

    // ====================== Image generator ======================

    private void generateDamagedCode39(String path) throws IOException
    {
        // Generate valid Code39 first
        String tempFile = path + "_temp.png";
        BarcodeGenerator generator = new BarcodeGenerator(EncodeTypes.CODE_39, "BROKEN999");
        generator.save(tempFile, BarCodeImageFormat.PNG);

        // Simulate damage: cover part of the barcode with a black rectangle
        BufferedImage img = ImageIO.read(Paths.get(tempFile).toFile());
        Graphics2D g = img.createGraphics();
        g.setColor(Color.BLACK);
        int coverWidth = img.getWidth() / 4;
        int coverHeight = img.getHeight() / 2;
        g.fillRect(img.getWidth() / 3, img.getHeight() / 3, coverWidth, coverHeight);
        g.dispose();

        ImageIO.write(img, "PNG", Paths.get(path).toFile());
        java.nio.file.Files.deleteIfExists(Paths.get(tempFile));
    }
}
