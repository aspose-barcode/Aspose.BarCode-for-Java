package com.aspose.barcode.guide.recognition.barcode_properties;

import com.aspose.barcode.barcoderecognition.BarCodeExtendedParameters;
import com.aspose.barcode.barcoderecognition.BarCodeReader;
import com.aspose.barcode.barcoderecognition.BarCodeResult;
import com.aspose.barcode.barcoderecognition.DecodeType;
import com.aspose.barcode.barcoderecognition.QualitySettings;
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

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;

import static com.aspose.barcode.guide.common.ExampleAssist.assertRecognized;

/**
 * Demonstrates result validation scenarios:
 * <ul>
 *   <li>checksum validation through recognition settings and extended parameters;</li>
 *   <li>confidence comparison for clean and degraded images;</li>
 *   <li>the effect of quality presets on difficult input images.</li>
 * </ul>
 */
public class ResultValidationExample {

    private static final String FOLDER =
            ExampleAssist.getOrCreateResourceFolderPath(
                    "recognition",
                    "barcode_properties",
                    "result_validation"
            );

    private static final String FILE_EAN13_VALID =
            "rv_ean13_valid.png";
    private static final String FILE_EAN13_DAMAGED =
            "rv_ean13_damaged.png";
    private static final String FILE_QR_CLEAN =
            "rv_qr_clean.png";
    private static final String FILE_QR_NOISY =
            "rv_qr_noisy.png";
    private static final String FILE_CODE128_SMALL =
            "rv_c128_small.png";
    private static final String FILE_CODE39_DAMAGED =
            "rv_code39_damaged.png";

    /**
     * Applies the license and creates deterministic test fixtures.
     */
    @BeforeClass
    public void setUp() throws Exception {
        LicenseAssist.setupLicense();
        generateFixtures();
    }

    /**
     * Verifies checksum-related recognition behavior for a valid EAN-13 barcode.
     *
     * <p>The example disables incorrect barcode results, checks that the valid
     * barcode is recognized, and inspects the one-dimensional checksum metadata.</p>
     */
    @Test
    public void checksumValidationEan13() throws Exception {
        String validPath = ExampleAssist.pathCombine(
                FOLDER,
                FILE_EAN13_VALID
        );

        BarCodeReader reader = new BarCodeReader(
                validPath,
                DecodeType.EAN_13
        );

        reader.getQualitySettings()
                .setAllowIncorrectBarcodes(false);

        BarCodeResult[] results = reader.readBarCodes();

        Assert.assertTrue(
                results.length >= 1,
                "Expected valid EAN-13 to be recognized"
        );

        BarCodeResult result = results[0];

        System.out.println(
                "[EAN13 valid] Text="
                        + result.getCodeText()
                        + " Confidence="
                        + result.getConfidence()
        );

        assertRecognized(
                reader,
                "EAN13 valid",
                1,
                DecodeType.EAN_13
        );

        BarCodeExtendedParameters extended =
                result.getExtended();

        Assert.assertNotNull(
                extended,
                "Extended parameters must be present"
        );

        Assert.assertNotNull(
                extended.getOneD(),
                "One-dimensional extended parameters must be present for EAN-13"
        );

        System.out.println(
                "[EAN13 valid] OneD checksum="
                        + extended.getOneD().getCheckSum()
        );
    }

    /**
     * Compares recognition results for a damaged Code 39 barcode when incorrect
     * barcode results are disabled and enabled.
     */
    @Test
    public void compareAllowIncorrectForDamagedCode39() throws Exception {
        String path = ExampleAssist.pathCombine(
                FOLDER,
                FILE_CODE39_DAMAGED
        );

        BarCodeReader disallowReader = new BarCodeReader(
                path,
                DecodeType.CODE_39
        );

        disallowReader.getQualitySettings()
                .setAllowIncorrectBarcodes(false);

        BarCodeResult[] disallowResults =
                disallowReader.readBarCodes();

        BarCodeReader allowReader = new BarCodeReader(
                path,
                DecodeType.CODE_39
        );

        allowReader.getQualitySettings()
                .setAllowIncorrectBarcodes(true);

        BarCodeResult[] allowResults =
                allowReader.readBarCodes();

        int disallowCount = disallowResults.length;
        int allowCount = allowResults.length;

        System.out.println(
                "[Code39 damaged] disallow="
                        + disallowCount
                        + " | allow="
                        + allowCount
        );

        if (allowCount > 0) {
            System.out.println(
                    "  allow first: text="
                            + allowResults[0].getCodeText()
                            + " conf="
                            + allowResults[0].getConfidence()
            );
        }

        Assert.assertTrue(
                allowCount >= disallowCount,
                "Allowing incorrect barcodes must not produce fewer candidates"
        );

        Assert.assertTrue(
                allowCount > 0 || disallowCount > 0,
                "Expected at least one result for the damaged Code 39 image"
        );
    }

    /**
     * Compares recognition confidence for clean and noisy QR images.
     *
     * <p>The clean image is expected to have confidence that is not lower than
     * the confidence of the noisy image.</p>
     */
    @Test
    public void compareConfidenceForCleanAndNoisyQr() throws Exception {
        String cleanPath = ExampleAssist.pathCombine(
                FOLDER,
                FILE_QR_CLEAN
        );

        String noisyPath = ExampleAssist.pathCombine(
                FOLDER,
                FILE_QR_NOISY
        );

        BarCodeReader cleanReader = new BarCodeReader(
                cleanPath,
                DecodeType.QR
        );

        cleanReader.setQualitySettings(
                QualitySettings.getHighQuality()
        );

        BarCodeResult[] cleanResults =
                cleanReader.readBarCodes();

        Assert.assertTrue(
                cleanResults.length >= 1,
                "Expected clean QR to be recognized"
        );

        double cleanConfidence =
                cleanResults[0].getConfidence();

        BarCodeReader noisyReader = new BarCodeReader(
                noisyPath,
                DecodeType.QR
        );

        noisyReader.setQualitySettings(
                QualitySettings.getHighQuality()
        );

        BarCodeResult[] noisyResults =
                noisyReader.readBarCodes();

        Assert.assertTrue(
                noisyResults.length >= 1,
                "Expected noisy QR to be recognized"
        );

        double noisyConfidence =
                noisyResults[0].getConfidence();

        System.out.println(
                "[QR] clean confidence="
                        + cleanConfidence
                        + " vs noisy="
                        + noisyConfidence
        );

        Assert.assertTrue(
                cleanConfidence >= noisyConfidence,
                "Expected clean QR confidence to be greater than or equal to noisy QR confidence"
        );
    }

    /**
     * Compares HighPerformance and HighQuality presets on a small Code 128 image.
     *
     * <p>The example demonstrates that different quality presets can produce
     * different recognition results for the same difficult image.</p>
     */
    @Test
    public void compareQualitySettingsForSmallCode128() {
        String path = ExampleAssist.pathCombine(
                FOLDER,
                FILE_CODE128_SMALL
        );

        BarCodeReader highPerformanceReader = new BarCodeReader(
                path,
                DecodeType.CODE_128
        );

        highPerformanceReader.setQualitySettings(
                QualitySettings.getHighPerformance()
        );

        BarCodeResult[] highPerformanceResults =
                highPerformanceReader.readBarCodes();

        BarCodeReader highQualityReader = new BarCodeReader(
                path,
                DecodeType.CODE_128
        );

        highQualityReader.setQualitySettings(
                QualitySettings.getHighQuality()
        );

        BarCodeResult[] highQualityResults =
                highQualityReader.readBarCodes();

        System.out.println(
                "[Small Code128] HighPerformance count="
                        + highPerformanceResults.length
                        + formatResult(highPerformanceResults)
                        + " | HighQuality count="
                        + highQualityResults.length
                        + formatResult(highQualityResults)
        );

        Assert.assertTrue(
                highPerformanceResults.length > 0
                        || highQualityResults.length > 0,
                "Expected at least one quality preset to detect the small Code 128 barcode"
        );
    }

    /**
     * Creates all recognition fixtures used by the tests.
     */
    private void generateFixtures() throws Exception {
        createValidEan13Fixture();
        createDamagedEan13Fixture();
        createCleanQrFixture();
        createNoisyQrFixture();
        createSmallCode128Fixture();
        createDamagedCode39Fixture();
    }

    /**
     * Creates a valid EAN-13 image.
     */
    private void createValidEan13Fixture() throws Exception {
        ExampleAssist.checkOrCreateImage(
                FOLDER,
                FILE_EAN13_VALID,
                (ImageSupplier) (String fullPath) -> {
                    BarcodeGenerator generator = new BarcodeGenerator(
                            EncodeTypes.EAN_13,
                            "5901234123457"
                    );

                    generator.save(
                            fullPath,
                            BarCodeImageFormat.PNG
                    );
                }
        );
    }

    /**
     * Creates a mildly damaged EAN-13 image.
     */
    private void createDamagedEan13Fixture() throws Exception {
        ExampleAssist.checkOrCreateImage(
                FOLDER,
                FILE_EAN13_DAMAGED,
                (ImageSupplier) (String fullPath) -> {
                    String temporaryPath =
                            fullPath + ".tmp.png";

                    BarcodeGenerator generator = new BarcodeGenerator(
                            EncodeTypes.EAN_13,
                            "5901234123457"
                    );

                    generator.save(
                            temporaryPath,
                            BarCodeImageFormat.PNG
                    );

                    BufferedImage image =
                            javax.imageio.ImageIO.read(
                                    new File(temporaryPath)
                            );

                    Graphics2D graphics =
                            image.createGraphics();

                    try {
                        graphics.setColor(Color.BLACK);

                        int occluderWidth =
                                Math.max(
                                        3,
                                        image.getWidth() / 50
                                );

                        int occluderHeight =
                                (int) (image.getHeight() * 0.60);

                        int occluderX =
                                image.getWidth() / 2
                                        - occluderWidth / 2;

                        int occluderY =
                                (image.getHeight()
                                        - occluderHeight) / 2;

                        graphics.fillRect(
                                occluderX,
                                occluderY,
                                occluderWidth,
                                occluderHeight
                        );
                    } finally {
                        graphics.dispose();
                    }

                    javax.imageio.ImageIO.write(
                            image,
                            "PNG",
                            new File(temporaryPath)
                    );

                    ExampleAssist.addGaussianNoise(
                            temporaryPath,
                            fullPath,
                            6.0
                    );

                    new File(temporaryPath).delete();
                }
        );
    }

    /**
     * Creates a clean QR image.
     */
    private void createCleanQrFixture() throws Exception {
        ExampleAssist.checkOrCreateImage(
                FOLDER,
                FILE_QR_CLEAN,
                (ImageSupplier) (String fullPath) -> {
                    BarcodeGenerator generator = new BarcodeGenerator(
                            EncodeTypes.QR,
                            "RESULT-VALIDATION-QR"
                    );

                    generator.getParameters()
                            .getBarcode()
                            .getQR()
                            .setErrorLevel(
                                    QRErrorLevel.LEVEL_M
                            );

                    generator.save(
                            fullPath,
                            BarCodeImageFormat.PNG
                    );
                }
        );
    }

    /**
     * Creates a noisy version of the clean QR image.
     */
    private void createNoisyQrFixture() throws Exception {
        ExampleAssist.checkOrCreateImage(
                FOLDER,
                FILE_QR_NOISY,
                (ImageSupplier) (String fullPath) -> {
                    String cleanPath =
                            ExampleAssist.pathCombine(
                                    FOLDER,
                                    FILE_QR_CLEAN
                            );

                    ExampleAssist.addGaussianNoise(
                            cleanPath,
                            fullPath,
                            12.0
                    );
                }
        );
    }

    /**
     * Creates a small but still recognizable Code 128 image.
     *
     * <p>The fixture is recreated on every test run so that an older cached image
     * with a smaller width cannot affect the result.</p>
     */
    private void createSmallCode128Fixture() throws Exception {
        String fullPath = ExampleAssist.pathCombine(
                FOLDER,
                FILE_CODE128_SMALL
        );

        String largePath =
                fullPath + ".large.png";

        new File(fullPath).delete();
        new File(largePath).delete();

        ExampleAssist.renderBarcodeFixedSizePNG(
                EncodeTypes.CODE_128,
                "C128-SMALL",
                420,
                180,
                2.0f,
                24,
                largePath
        );

        ExampleAssist.downscaleNearestCrisp(
                largePath,
                fullPath,
                200
        );

        new File(largePath).delete();

        ExampleAssist.assertFileCreated(fullPath);
    }

    /**
     * Creates a damaged Code 39 image with a thin central blocker.
     */
    private void createDamagedCode39Fixture() throws Exception {
        ExampleAssist.checkOrCreateImage(
                FOLDER,
                FILE_CODE39_DAMAGED,
                (String fullPath) -> {
                    String temporaryPath =
                            fullPath + ".tmp.png";

                    BarcodeGenerator generator = new BarcodeGenerator(
                            EncodeTypes.CODE_39,
                            "RESULT-VALIDATION-39"
                    );

                    generator.save(
                            temporaryPath,
                            BarCodeImageFormat.PNG
                    );

                    BufferedImage image =
                            javax.imageio.ImageIO.read(
                                    new File(temporaryPath)
                            );

                    Graphics2D graphics =
                            image.createGraphics();

                    try {
                        graphics.setColor(Color.BLACK);

                        int blockerWidth =
                                Math.max(
                                        3,
                                        image.getWidth() / 50
                                );

                        int blockerHeight =
                                (int) (image.getHeight() * 0.60);

                        graphics.fillRect(
                                image.getWidth() / 2
                                        - blockerWidth / 2,
                                (image.getHeight()
                                        - blockerHeight) / 2,
                                blockerWidth,
                                blockerHeight
                        );
                    } finally {
                        graphics.dispose();
                    }

                    javax.imageio.ImageIO.write(
                            image,
                            "PNG",
                            new File(fullPath)
                    );

                    new File(temporaryPath).delete();
                }
        );
    }

    /**
     * Checks whether the recognition results contain the expected code text.
     */
    private static boolean containsCodeText(
            BarCodeResult[] results,
            String expectedCodeText
    ) {
        for (BarCodeResult result : results) {
            if (expectedCodeText.equals(result.getCodeText())) {
                return true;
            }
        }

        return false;
    }

    /**
     * Formats the first recognition result for diagnostics.
     */
    private static String formatResult(
            BarCodeResult[] results
    ) {
        if (results.length == 0) {
            return "";
        }

        return " text=\""
                + results[0].getCodeText()
                + "\" confidence="
                + results[0].getConfidence();
    }
}
