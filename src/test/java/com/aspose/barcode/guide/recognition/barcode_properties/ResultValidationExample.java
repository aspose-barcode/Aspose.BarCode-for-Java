package com.aspose.barcode.guide.recognition.barcode_properties;

import com.aspose.barcode.barcoderecognition.*;
import com.aspose.barcode.generation.BarCodeImageFormat;
import com.aspose.barcode.generation.BarcodeGenerator;
import com.aspose.barcode.generation.EncodeTypes;
import com.aspose.barcode.generation.QRErrorLevel;
import com.aspose.barcode.guide.common.ExampleAssist;
import com.aspose.barcode.guide.common.ImageSupplier;
import com.aspose.barcode.guide.common.LicenseAssist;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

import static com.aspose.barcode.guide.common.ExampleAssist.*;

/**
 * Result Validation:
 * - Checksum validation (via engine behavior and OneD checksum field)
 * - Confidence comparison on clean vs degraded images
 * - QualitySettings influence on difficult inputs
 */
public class ResultValidationExample {

    private static final String FOLDER =
            ExampleAssist.getOrCreateResourceFolderPath("recognition", "barcode_properties", "result_validation");


    private static final String FILE_EAN13_VALID        = "rv_ean13_valid.png";
    private static final String FILE_EAN13_DAMAGED      = "rv_ean13_damaged.png";
    private static final String FILE_QR_CLEAN           = "rv_qr_clean.png";
    private static final String FILE_QR_NOISY           = "rv_qr_noisy.png";
    private static final String FILE_C128_TINY          = "rv_c128_tiny.png";
    private static final String FILE_CODE39_DAMAGED           = "rv_code39_damaged.png";

    @BeforeClass
    public void setUp() throws Exception {
        LicenseAssist.setupLicense();
        generateFixtures();
    }

    /**
     * Checksum validation:
     * - Valid EAN-13 should be recognized with AllowIncorrectBarcodes=false.
     * - Damaged EAN-13 is filtered out when disallowed, but may appear if allowed.
     * - OneD checksum value can be inspected via Extended parameters.
     */
    @Test
    public void checksumValidation_EAN13() throws Exception {
        String validPath = ExampleAssist.pathCombine(FOLDER, FILE_EAN13_VALID);
        String damagedPath = ExampleAssist.pathCombine(FOLDER, FILE_EAN13_DAMAGED);

        // Valid sample, disallow incorrect barcodes
        BarCodeReader validReader = new BarCodeReader(validPath, DecodeType.EAN_13);
        validReader.getQualitySettings().setAllowIncorrectBarcodes(false);
        BarCodeResult[] validResults = validReader.readBarCodes();
        Assert.assertTrue(validResults.length >= 1, "Expected valid EAN-13 to be recognized");
        BarCodeResult validResult = validResults[0];
        System.out.println("[EAN13 valid] Text=" + validResult.getCodeText()
                + " Confidence=" + validResult.getConfidence());
        ExampleAssist.assertRecognized(validReader, "", 1, validResult.getCodeType(), validResult.getCodeText());
        if (validResult.getExtended() != null && validResult.getExtended().getOneD() != null) {
            System.out.println("[EAN13 valid] OneD checksum=" + validResult.getExtended().getOneD().getCheckSum());
        }

        // Damaged sample, disallow incorrect barcodes
        BarCodeReader readerDisallow = new BarCodeReader(damagedPath, DecodeType.EAN_13);
        readerDisallow.getQualitySettings().setAllowIncorrectBarcodes(false);
        BarCodeResult[] disallowResults = readerDisallow.readBarCodes();
        int countDisallow = disallowResults.length;

        // Damaged sample, allow incorrect barcodes
        BarCodeReader readerAllow = new BarCodeReader(damagedPath, DecodeType.EAN_13);
        readerAllow.getQualitySettings().setAllowIncorrectBarcodes(true);
        BarCodeResult[] allowResults = readerAllow.readBarCodes();
        int countAllow = allowResults.length;

        System.out.println("[EAN13 damaged] disallow=" + countDisallow + " | allow=" + countAllow);
        //TODO create issue
        if (countDisallow > 0) {
            System.out.println("  disallow first: text=" + disallowResults[0].getCodeText()
                    + " conf=" + disallowResults[0].getConfidence());
        }
        if (countAllow > 0) {
            System.out.println("  allow   first: text=" + allowResults[0].getCodeText()
                    + " conf=" + allowResults[0].getConfidence());
        }

        // Core validation idea: enabling incorrect results should not yield fewer candidates
        Assert.assertTrue(countAllow >= countDisallow,
                "With allowIncorrect=true we expect >= results than with disallow");

        // At least one mode should return something for the damaged input
        Assert.assertTrue(countAllow > 0 || countDisallow > 0,
                "Expected at least one result on damaged input with either setting");

        // If both modes returned results, the 'allow' confidence should not exceed 'disallow' (soft heuristic)
        if (countAllow > 0 && countDisallow > 0) {
            Assert.assertTrue(allowResults[0].getConfidence() <= disallowResults[0].getConfidence() + 1e-6,
                    "Heuristic: allowIncorrect result should not be more confident than disallow");
        }
    }

    @Test
    public void checksumValidation_Code39() {
        String path = ExampleAssist.pathCombine(FOLDER, FILE_CODE39_DAMAGED);

        BarCodeReader disallow = new BarCodeReader(path, DecodeType.CODE_39);
        disallow.getQualitySettings().setAllowIncorrectBarcodes(false);
        int c0 = disallow.readBarCodes().length;

        BarCodeReader allow = new BarCodeReader(path, DecodeType.CODE_39);
        allow.getQualitySettings().setAllowIncorrectBarcodes(true);
        int c1 = allow.readBarCodes().length;

        System.out.println("[Code39 damaged] disallow=" + c0 + " | allow=" + c1);
        Assert.assertTrue(c1 >= c0, "allowIncorrect should not yield fewer results");
        Assert.assertTrue(c1 > 0 || c0 > 0, "At least one setting should return a result");
    }


    /**
     * Confidence comparison:
     * Clean QR should have confidence >= noisy QR (heuristic expectation).
     * We do soft assertion to avoid overfitting engine internals.
     */
    @Test
    public void confidence_CleanVsNoisy_QR() throws Exception {
        String cleanPath = ExampleAssist.pathCombine(FOLDER, FILE_QR_CLEAN);
        String noisyPath = ExampleAssist.pathCombine(FOLDER, FILE_QR_NOISY);

        BarCodeReader cleanReader = new BarCodeReader(cleanPath, DecodeType.QR);
        cleanReader.setQualitySettings(QualitySettings.getHighQuality());
        BarCodeResult[] cleanResults = cleanReader.readBarCodes();
        Assert.assertTrue(cleanResults.length >= 1, "Expected clean QR to be recognized");
        double cleanConfidence = cleanResults[0].getConfidence();

        BarCodeReader noisyReader = new BarCodeReader(noisyPath, DecodeType.QR);
        noisyReader.setQualitySettings(QualitySettings.getHighQuality());
        BarCodeResult[] noisyResults = noisyReader.readBarCodes();
        Assert.assertTrue(noisyResults.length >= 1, "Expected noisy QR to be recognized");
        double noisyConfidence = noisyResults[0].getConfidence();

        System.out.println("[QR] clean confidence=" + cleanConfidence + " vs noisy=" + noisyConfidence);
        // Heuristic: clean should not be worse than noisy
        Assert.assertTrue(cleanConfidence >= noisyConfidence,
                "Expected clean QR confidence >= noisy QR confidence");
    }

    /**
     * QualitySettings effect on hard input (tiny Code128):
     * Compare HighPerformance vs HighQuality — at least one of them must read the tiny code.
     * If both read, print confidences for reference.
     */
    @Test
    public void qualitySettings_TinyCode128() {
        String path = ExampleAssist.pathCombine(FOLDER, FILE_C128_TINY);

        BarCodeReader hpReader = new BarCodeReader(path, DecodeType.CODE_128);
        hpReader.setQualitySettings(QualitySettings.getHighPerformance());
        BarCodeResult[] hpResults = hpReader.readBarCodes();

        BarCodeReader hqReader = new BarCodeReader(path, DecodeType.CODE_128);
        hqReader.setQualitySettings(QualitySettings.getHighQuality());
        BarCodeResult[] hqResults = hqReader.readBarCodes();

        System.out.println("[Tiny C128] HP count=" + hpResults.length
                + (hpResults.length > 0 ? (" conf=" + hpResults[0].getConfidence()) : "")
                + " | HQ count=" + hqResults.length
                + (hqResults.length > 0 ? (" conf=" + hqResults[0].getConfidence()) : ""));

        Assert.assertTrue(hpResults.length > 0 || hqResults.length > 0,
                "Expected at least one preset to recognize the tiny Code128");
    }

    // ---------------- fixtures ----------------
    private void generateFixtures() throws Exception {
        // 1) EAN-13 valid (generator enforces correct checksum)
        ExampleAssist.checkOrCreateImage(FOLDER, FILE_EAN13_VALID, (ImageSupplier) (String full) -> {
            BarcodeGenerator generator = new BarcodeGenerator(EncodeTypes.EAN_13, "5901234123457");
            generator.save(full, BarCodeImageFormat.PNG);
        });

        // 2) EAN-13 damaged — keep bars crisp, apply a thin occluder + mild noise (no blur!)
        ExampleAssist.checkOrCreateImage(FOLDER, FILE_EAN13_DAMAGED, (ImageSupplier) (String full) -> {
            String tmp = full + ".tmp.png";
            BarcodeGenerator generator = new BarcodeGenerator(EncodeTypes.EAN_13, "5901234123457");
            generator.save(tmp, BarCodeImageFormat.PNG);

            BufferedImage img = javax.imageio.ImageIO.read(new File(tmp));
            Graphics2D g = img.createGraphics();
            try {
                g.setColor(Color.BLACK);

                // Thin vertical occluder in the center (doesn't touch left/right guard bars)
                int occW = Math.max(3, img.getWidth() / 50);
                int occH = (int) (img.getHeight() * 0.60);
                int occX = img.getWidth() / 2 - occW / 2;
                int occY = (img.getHeight() - occH) / 2;
                g.fillRect(occX, occY, occW, occH);
            } finally {
                g.dispose();
            }

            // Add mild Gaussian noise to degrade edges a bit, but keep bars readable
            ExampleAssist.addGaussianNoise(tmp, full, /*stdDev=*/6.0);
            new File(tmp).delete();
        });

        // 3) QR clean
        ExampleAssist.checkOrCreateImage(FOLDER, FILE_QR_CLEAN, (ImageSupplier) (String full) -> {
            BarcodeGenerator generator = new BarcodeGenerator(EncodeTypes.QR, "RESULT-VALIDATION-QR");
            generator.getParameters().getBarcode().getQR().setQrErrorLevel(QRErrorLevel.LEVEL_M);
            generator.save(full, BarCodeImageFormat.PNG);
        });

        // 4) QR noisy (add Gaussian noise to clean)
        ExampleAssist.checkOrCreateImage(FOLDER, FILE_QR_NOISY, (ImageSupplier) (String full) -> {
            String clean = ExampleAssist.pathCombine(FOLDER, FILE_QR_CLEAN);
            ExampleAssist.addGaussianNoise(clean, full, 12.0);
        });

        // 5) Tiny Code128 (hard case): render small and downscale to be challenging
        ExampleAssist.checkOrCreateImage(FOLDER, FILE_C128_TINY, (ImageSupplier) (String full) -> {
            String tmp = full + ".tmp.png";
            BarcodeGenerator generator = new BarcodeGenerator(EncodeTypes.CODE_128, "TINY-128-RESULT");
            generator.save(tmp, BarCodeImageFormat.PNG);
            // downscale aggressively with crisp binarization to simulate tiny print
            ExampleAssist.downscaleNearestCrisp(tmp, full, 120);
            new File(tmp).delete();
        });

        ExampleAssist.checkOrCreateImage(FOLDER, FILE_CODE39_DAMAGED, (String full) -> {
            String tmp = full + ".tmp.png";
            BarcodeGenerator gen = new BarcodeGenerator(EncodeTypes.CODE_39, "RESULT-VALIDATION-39");
            gen.save(tmp, BarCodeImageFormat.PNG);

            BufferedImage img = javax.imageio.ImageIO.read(new File(tmp));
            Graphics2D g = img.createGraphics();
            g.setColor(Color.BLACK);
            // Thin central blocker
            int w = Math.max(3, img.getWidth() / 50);
            int h = (int)(img.getHeight() * 0.6);
            g.fillRect(img.getWidth()/2 - w/2, (img.getHeight()-h)/2, w, h);
            g.dispose();
            javax.imageio.ImageIO.write(img, "PNG", new File(full));
            new File(tmp).delete();
        });

    }
}
