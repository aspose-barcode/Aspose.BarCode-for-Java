package com.aspose.barcode.guide.generation.visual_parameters;

import com.aspose.barcode.barcoderecognition.BarCodeReader;
import com.aspose.barcode.barcoderecognition.BarCodeResult;
import com.aspose.barcode.barcoderecognition.DecodeType;
import com.aspose.barcode.generation.*;
import com.aspose.barcode.guide.common.ExampleAssist;
import com.aspose.barcode.guide.common.LicenseAssist;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

import static com.aspose.barcode.guide.common.ExampleAssist.assertImageHasBarcodes;
import static com.aspose.barcode.guide.common.ExampleAssist.expected;

/**
 * Rotation examples for generated barcodes.
 *
 * Shows:
 * - BaseGenerationParameters.setRotationAngle(float) for 1D and 2D barcodes.
 * - Orthogonal rotations (0/90/180/270) vs. arbitrary angles (e.g. ±45).
 * - Interaction between RotationAngle and AutoSizeMode (NEAREST vs fixed size).
 * Conventions:
 * - No try-with-resources; do not close/Dispose readers/generators explicitly.
 * - Deterministic output paths under src/test/resources.
 * - Uses ExampleAssist helpers (path combining, assertions).
 */
public class RotationExample {

    private static final String FOLDER =
            ExampleAssist.getOrCreateResourceFolderPath("generation", "visual_parameters", "rotation");

    // File names (CODE_128)
    private static final String FILE_CODE128_ROT_0       = "code128_rotation_0deg.png";
    private static final String FILE_CODE128_ROT_90      = "code128_rotation_90deg.png";
    private static final String FILE_CODE128_ROT_180     = "code128_rotation_180deg.png";
    private static final String FILE_CODE128_ROT_270     = "code128_rotation_270deg.png";
    private static final String FILE_CODE128_ROT_45      = "code128_rotation_45deg.png";
    private static final String FILE_CODE128_ROT_M45     = "code128_rotation_minus45deg.png";
    private static final String FILE_CODE128_ROT_90_AUTOSIZE_NEAREST =
            "code128_rotation_90deg_autosize_nearest.png";
    private static final String FILE_CODE128_ROT_90_FIXED_SIZE =
            "code128_rotation_90deg_fixed_size.png";


    // File names (QR)
    private static final String FILE_QR_ROT_90           = "qr_rotation_90deg.png";
    private static final String FILE_QR_ROT_360          = "qr_rotation_360deg.png";


    @BeforeClass
    public void setUp() throws Exception {
        LicenseAssist.setupLicense();
    }

    /**
     * CODE_128 rotated by orthogonal angles (0, 90, 180, 270).
     *
     * Shows:
     * - RotationAngle set to multiples of 90°, which are scanner-friendly.
     * Expected: each image contains exactly one CODE_128 "ROT-ORTHO".
     */
    @Test
    public void code128_rotation_orthogonal_angles() throws Exception {
        final String payload = "ROT-ORTHO";
        float[] angles = new float[] { 0f, 90f, 180f, 270f };
        String[] fileNames = new String[] {
                FILE_CODE128_ROT_0,
                FILE_CODE128_ROT_90,
                FILE_CODE128_ROT_180,
                FILE_CODE128_ROT_270
        };

        for (int i = 0; i < angles.length; i++) {
            BarcodeGenerator generator = new BarcodeGenerator(EncodeTypes.CODE_128, payload);
            generator.getParameters().setRotationAngle(angles[i]);

            generator.getParameters().getImageWidth().setPixels(420);
            generator.getParameters().getImageHeight().setPixels(220);

            String fullPath = ExampleAssist.pathCombine(FOLDER, fileNames[i]);
            generator.save(fullPath, BarCodeImageFormat.PNG);
            ExampleAssist.assertFileCreated(fullPath);

            assertImageHasBarcodes(
                    fullPath,
                    1,
                    List.of(expected(DecodeType.CODE_128, payload))
            );
        }
    }

    /**
     * CODE_128 rotated by arbitrary angles (+45 and -45 degrees).
     *
     * Shows:
     * - Using non-orthogonal angles which may be harder for some scanners,
     *   but are useful in design/layout scenarios.
     * Expected: each image contains exactly one CODE_128 "ROT-ARBITRARY".
     */
    @Test
    public void code128_rotation_arbitrary_angles() throws Exception {
        final String payload = "ROT-ARBITRARY";
        float[] angles = new float[] { 45f, -45f };
        String[] fileNames = new String[] {
                FILE_CODE128_ROT_45,
                FILE_CODE128_ROT_M45
        };

        for (int i = 0; i < angles.length; i++) {
            BarcodeGenerator generator = new BarcodeGenerator(EncodeTypes.CODE_128, payload);
            generator.getParameters().setRotationAngle(angles[i]);

            generator.getParameters().getImageWidth().setPixels(420);
            generator.getParameters().getImageHeight().setPixels(220);

            String fullPath = ExampleAssist.pathCombine(FOLDER, fileNames[i]);
            generator.save(fullPath, BarCodeImageFormat.PNG);
            ExampleAssist.assertFileCreated(fullPath);

            assertImageHasBarcodes(
                    fullPath,
                    1,
                    List.of(expected(DecodeType.CODE_128, payload))
            );
        }
    }

    /**
     * CODE_128 rotated by 45 degrees: AutoSizeMode.NEAREST vs fixed image size.
     *
     * Shows:
     * - AutoSizeMode.NEAREST: engine chooses the nearest “nice” raster size after rotation.
     * - AutoSizeMode.NONE with explicit ImageWidth/ImageHeight: fixed canvas, which may clip margins
     *   if chosen too small (here we deliberately pick a generous size to avoid errors).
     * Expected: both images contain one CODE_128 "ROT-AUTOSIZE" and remain readable.
     */
    @Test
    public void code128_rotation_autosize_vs_fixed() throws Exception {
        final String payload = "ROT-AUTOSIZE";

        // Variant 1: 90° + AutoSizeMode.NEAREST
        // Engine picks the nearest “nice” raster size after rotation.
        BarcodeGenerator generatorAuto = new BarcodeGenerator(EncodeTypes.CODE_128, payload);
        generatorAuto.getParameters().setRotationAngle(90f);
        generatorAuto.getParameters().setAutoSizeMode(AutoSizeMode.NEAREST);

        String autoPath = ExampleAssist.pathCombine(FOLDER, FILE_CODE128_ROT_90_AUTOSIZE_NEAREST);
        generatorAuto.save(autoPath, BarCodeImageFormat.PNG);
        ExampleAssist.assertFileCreated(autoPath);

        assertImageHasBarcodes(
                autoPath,
                1,
                List.of(expected(DecodeType.CODE_128, payload))
        );

        // Variant 2: 90° + AutoSizeMode.NONE with fixed canvas size.
        // Here we control the canvas explicitly.
        BarcodeGenerator generatorFixed = new BarcodeGenerator(EncodeTypes.CODE_128, payload);
        generatorFixed.getParameters().setRotationAngle(90f);
        generatorFixed.getParameters().setAutoSizeMode(AutoSizeMode.NONE);

        generatorFixed.getParameters().getImageWidth().setPixels(420);
        generatorFixed.getParameters().getImageHeight().setPixels(220);

        String fixedPath = ExampleAssist.pathCombine(FOLDER, FILE_CODE128_ROT_90_FIXED_SIZE);
        generatorFixed.save(fixedPath, BarCodeImageFormat.PNG);
        ExampleAssist.assertFileCreated(fixedPath);

        assertImageHasBarcodes(
                fixedPath,
                1,
                List.of(expected(DecodeType.CODE_128, payload))
        );
    }


    /**
     * QR rotation: square symbology rotated by 90 degrees.
     *
     * Shows:
     * - RotationAngle applied to a 2D (square) symbol.
     * Expected: one QR "QR-ROTATE" rotated by 90°.
     */
    @Test
    public void qr_rotation_90_degrees() throws Exception {
        final String payload = "QR-ROTATE";
        BarcodeGenerator generator = new BarcodeGenerator(EncodeTypes.QR, payload);
        generator.getParameters().setRotationAngle(90f);

        generator.getParameters().getImageWidth().setPixels(220);
        generator.getParameters().getImageHeight().setPixels(220);

        String fullPath = ExampleAssist.pathCombine(FOLDER, FILE_QR_ROT_90);
        generator.save(fullPath, BarCodeImageFormat.PNG);
        ExampleAssist.assertFileCreated(fullPath);

        assertImageHasBarcodes(
                fullPath,
                1,
                List.of(expected(DecodeType.QR, payload))
        );
    }

    /**
     * QR rotation: 360 degrees equals no rotation (same as 0°).
     *
     * Shows:
     * - RotationAngle = 360f is treated as "no rotation".
     * Expected: one QR "QR-FULL-TURN" with visual orientation equivalent to 0°.
     */
    @Test
    public void qr_rotation_full_turn_equals_zero() throws Exception {
        final String payload = "QR-FULL-TURN";
        BarcodeGenerator generator = new BarcodeGenerator(EncodeTypes.QR, payload);
        generator.getParameters().setRotationAngle(360f);

        generator.getParameters().getImageWidth().setPixels(220);
        generator.getParameters().getImageHeight().setPixels(220);

        String fullPath = ExampleAssist.pathCombine(FOLDER, FILE_QR_ROT_360);
        generator.save(fullPath, BarCodeImageFormat.PNG);
        ExampleAssist.assertFileCreated(fullPath);

        assertImageHasBarcodes(
                fullPath,
                1,
                List.of(expected(DecodeType.QR, payload))
        );
    }
}
