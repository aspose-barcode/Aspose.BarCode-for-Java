package com.aspose.barcode.guide.generation.visual_parameters;

import com.aspose.barcode.barcoderecognition.DecodeType;
import com.aspose.barcode.generation.BarCodeImageFormat;
import com.aspose.barcode.generation.BarcodeGenerator;
import com.aspose.barcode.generation.EncodeTypes;
import com.aspose.barcode.guide.common.ExampleAssist;
import com.aspose.barcode.guide.common.LicenseAssist;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.List;

import static com.aspose.barcode.guide.common.ExampleAssist.assertImageHasBarcodes;
import static com.aspose.barcode.guide.common.ExampleAssist.expected;

/**
 * Demonstrates how to configure barcode image backgrounds, transparency,
 * padding, and foreground-to-background contrast.
 */
public class BackgroundsExample {

    private static final String FOLDER =
            ExampleAssist.getOrCreateResourceFolderPath(
                    "generation",
                    "visual_parameters",
                    "backgrounds"
            );

    @BeforeClass
    public void setUp() {
        LicenseAssist.setupLicense();
    }

    /**
     * Generates a QR Code with a light custom background and dark modules.
     *
     * The test verifies that the generated image exists and remains recognizable.
     */
    @Test
    public void qrWithSolidBackground() throws Exception {
        String codeText = "BACKGROUND-EXAMPLE";
        String outputPath = ExampleAssist.pathCombine(
                FOLDER,
                "qr_custom_background.png"
        );

        BarcodeGenerator generator = new BarcodeGenerator(
                EncodeTypes.QR,
                codeText
        );

        generator.getParameters().setBackColor(
                new Color(245, 248, 252)
        );

        generator.getParameters()
                .getBarcode()
                .setBarColor(new Color(20, 45, 90));

        generator.save(outputPath, BarCodeImageFormat.PNG);

        ExampleAssist.assertFileCreated(outputPath);
        assertImageHasBarcodes(
                outputPath,
                1,
                List.of(expected(DecodeType.QR, codeText))
        );
    }

    /**
     * Generates a QR Code with light modules on a dark background.
     *
     * This layout can be less compatible with some scanners, so the example
     * verifies file generation without treating inverted recognition as universal.
     */
    @Test
    public void qrWithDarkBackground() throws Exception {
        String outputPath = ExampleAssist.pathCombine(
                FOLDER,
                "qr_dark_background.png"
        );

        BarcodeGenerator generator = new BarcodeGenerator(
                EncodeTypes.QR,
                "DARK-BACKGROUND"
        );

        generator.getParameters().setBackColor(Color.BLACK);
        generator.getParameters()
                .getBarcode()
                .setBarColor(Color.WHITE);

        generator.save(outputPath, BarCodeImageFormat.PNG);

        ExampleAssist.assertFileCreated(outputPath);
        assertCornerColor(outputPath, Color.BLACK);
    }

    /**
     * Generates a Code 128 barcode with explicit padding around the symbol.
     *
     * Left and right padding reserve blank image space that can be used when
     * planning required quiet zones. The exact quiet-zone requirement depends
     * on the symbology and intended application.
     */
    @Test
    public void code128WithPadding() throws Exception {
        String codeText = "PADDING-EXAMPLE";
        String outputPath = ExampleAssist.pathCombine(
                FOLDER,
                "code128_with_padding.png"
        );

        BarcodeGenerator generator = new BarcodeGenerator(
                EncodeTypes.CODE_128,
                codeText
        );

        generator.getParameters()
                .getBarcode()
                .getPadding()
                .getLeft()
                .setPixels(20);

        generator.getParameters()
                .getBarcode()
                .getPadding()
                .getRight()
                .setPixels(20);

        generator.getParameters()
                .getBarcode()
                .getPadding()
                .getTop()
                .setPixels(10);

        generator.getParameters()
                .getBarcode()
                .getPadding()
                .getBottom()
                .setPixels(10);

        generator.save(outputPath, BarCodeImageFormat.PNG);

        ExampleAssist.assertFileCreated(outputPath);
        assertImageHasBarcodes(
                outputPath,
                1,
                List.of(expected(DecodeType.CODE_128, codeText))
        );
    }

    /**
     * Verifies that the top-left pixel matches the expected RGB color.
     */
    private static void assertCornerColor(
            String imagePath,
            Color expectedColor
    ) throws Exception {
        BufferedImage image = ImageIO.read(new File(imagePath));

        Assert.assertNotNull(
                image,
                "Generated image must be readable: " + imagePath
        );

        Color actualColor = new Color(image.getRGB(0, 0), true);

        Assert.assertEquals(
                actualColor.getRed(),
                expectedColor.getRed(),
                "Unexpected red channel at the image corner"
        );

        Assert.assertEquals(
                actualColor.getGreen(),
                expectedColor.getGreen(),
                "Unexpected green channel at the image corner"
        );

        Assert.assertEquals(
                actualColor.getBlue(),
                expectedColor.getBlue(),
                "Unexpected blue channel at the image corner"
        );
    }

    /**
     * Generates a QR Code with a fully transparent PNG background.
     *
     * The test reads the generated PNG through ImageIO and verifies that:
     * - the image color model contains an alpha channel;
     * - the background pixel in the top-left corner is fully transparent.
     */
    @Test
    public void qrWithTransparentBackground() throws Exception {
        String outputPath = ExampleAssist.pathCombine(
                FOLDER,
                "qr_transparent.png"
        );

        BarcodeGenerator generator = new BarcodeGenerator(
                EncodeTypes.QR,
                "TRANSPARENT-QR"
        );

        generator.getParameters().setBackColor(
                new Color(255, 255, 255, 0)
        );

        generator.save(outputPath, BarCodeImageFormat.PNG);

        ExampleAssist.assertFileCreated(outputPath);
        assertPngBackgroundAlpha(outputPath, 0);
    }

    /**
     * Generates a QR Code with a semi-transparent PNG background.
     *
     * The test reads the generated PNG through ImageIO and verifies that:
     * - the image color model contains an alpha channel;
     * - the background pixel in the top-left corner has the configured alpha value.
     */
    @Test
    public void qrWithSemiTransparentBackground() throws Exception {
        int expectedAlpha = 128;

        String outputPath = ExampleAssist.pathCombine(
                FOLDER,
                "qr_semi_transparent.png"
        );

        BarcodeGenerator generator = new BarcodeGenerator(
                EncodeTypes.QR,
                "SEMI-TRANSPARENT-QR"
        );

        generator.getParameters().setBackColor(
                new Color(255, 255, 255, expectedAlpha)
        );

        generator.save(outputPath, BarCodeImageFormat.PNG);

        ExampleAssist.assertFileCreated(outputPath);
        assertPngBackgroundAlpha(outputPath, expectedAlpha);
    }



    /**
     * Verifies that a generated PNG preserves an alpha channel and that its
     * top-left background pixel has the expected alpha value.
     *
     * <p>The method also prints image diagnostics to make behavior differences
     * between Aspose.BarCode versions visible in the test output.</p>
     *
     * @param imagePath    path to the generated PNG image
     * @param expectedAlpha expected alpha value in the range {@code 0..255}
     * @throws Exception if the image cannot be read or an assertion fails
     */
    private static void assertPngBackgroundAlpha(
            String imagePath,
            int expectedAlpha
    ) throws Exception {
        BufferedImage image = ImageIO.read(new File(imagePath));

        Assert.assertNotNull(
                image,
                "Generated PNG must be readable: " + imagePath
        );

        boolean hasAlpha = image.getColorModel().hasAlpha();

        int cornerArgb = image.getRGB(0, 0);
        Color cornerColor = new Color(cornerArgb, true);
        int actualAlpha = cornerColor.getAlpha();

        System.out.println("[PNG alpha diagnostics]");
        System.out.println("  File: " + imagePath);
        System.out.println("  BufferedImage type: " + image.getType());
        System.out.println("  Color model: " + image.getColorModel());
        System.out.println("  Has alpha: " + hasAlpha);
        System.out.println("  Corner RGBA: "
                + cornerColor.getRed() + ", "
                + cornerColor.getGreen() + ", "
                + cornerColor.getBlue() + ", "
                + actualAlpha);

        Assert.assertTrue(
                hasAlpha,
                "PNG color model must contain an alpha channel: " + imagePath
        );

        Assert.assertEquals(
                actualAlpha,
                expectedAlpha,
                "Unexpected alpha value in the top-left background pixel"
        );
    }
}
