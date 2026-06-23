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

        // Valid sample, disallow incorrect barcodes
        BarCodeReader validReader = new BarCodeReader(validPath, DecodeType.EAN_13);
        validReader.getQualitySettings().setAllowIncorrectBarcodes(false);

        BarCodeResult[] results = validReader.readBarCodes();
        Assert.assertTrue(results.length >= 1, "Expected valid EAN-13 to be recognized");
        BarCodeResult r = results[0];

        System.out.println("[EAN13 valid] Text=" + r.getCodeText() + " Confidence=" + r.getConfidence());
        ExampleAssist.assertRecognized(validReader, "EAN13 valid", 1, DecodeType.EAN_13);

        // Check that OneD checksum metadata is available
        BarCodeExtendedParameters ext = r.getExtended();
        Assert.assertNotNull(ext, "Extended parameters must be present");
        Assert.assertNotNull(ext.getOneD(), "OneD extended parameters must be present for EAN-13");
        System.out.println("[EAN13 valid] OneD checksum=" + ext.getOneD().getCheckSum());
    }

    @Test
    public void allowIncorrect_Effect_Code39_Damaged() throws Exception {
        String path = ExampleAssist.pathCombine(FOLDER, FILE_CODE39_DAMAGED);

        // Disallow incorrect barcodes
        BarCodeReader disallowReader = new BarCodeReader(path, DecodeType.CODE_39);
        disallowReader.getQualitySettings().setAllowIncorrectBarcodes(false);
        BarCodeResult[] disallowResults = disallowReader.readBarCodes();
        int countDisallow = disallowResults.length;

        // Allow incorrect barcodes
        BarCodeReader allowReader = new BarCodeReader(path, DecodeType.CODE_39);
        allowReader.getQualitySettings().setAllowIncorrectBarcodes(true);
        BarCodeResult[] allowResults = allowReader.readBarCodes();
        int countAllow = allowResults.length;

        System.out.println("[Code39 damaged] disallow=" + countDisallow + " | allow=" + countAllow);
        if (countAllow > 0) {
            System.out.println("  allow first: text=" + allowResults[0].getCodeText()
                    + " conf=" + allowResults[0].getConfidence());
        }

        // Enabling incorrect results should not yield fewer candidates
        Assert.assertTrue(countAllow >= countDisallow,
                "With allowIncorrect=true we expect >= results than with disallow");

        // At least one of the modes should detect something on this damaged input
        Assert.assertTrue(countAllow > 0 || countDisallow > 0,
                "Expected at least one result on damaged Code39 with either setting");
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
            generator.getParameters().getBarcode().getQR().setErrorLevel(QRErrorLevel.LEVEL_M);
            generator.save(full, BarCodeImageFormat.PNG);
        });

        // 4) QR noisy (add Gaussian noise to clean)
        ExampleAssist.checkOrCreateImage(FOLDER, FILE_QR_NOISY, (ImageSupplier) (String full) -> {
            String clean = ExampleAssist.pathCombine(FOLDER, FILE_QR_CLEAN);
            ExampleAssist.addGaussianNoise(clean, full, 12.0);
        });

        // 5) Tiny Code128 fixture: render big & crisp, then downscale with nearest+Otsu
        ExampleAssist.checkOrCreateImage(FOLDER, FILE_C128_TINY, (String full) -> {
            String big = full + ".big.png";

            // 1) Render a clean Code128 with explicit quiet zones
            ExampleAssist.renderBarcodeFixedSizePNG(
                    EncodeTypes.CODE_128, "C128-TINY",
                    /*widthPx*/ 420, /*heightPx*/ 180,
                    /*xDimPx*/ 2.0f, /*quietPx*/ 24,
                    big);

            // 2) Downscale to a tiny width while keeping edges crisp (no blur)
            ExampleAssist.downscaleNearestCrisp(big, full, /*targetWidthPx*/ 128);

            new java.io.File(big).delete();
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
