package com.aspose.barcode.guide.generation.save_options;

import com.aspose.barcode.barcoderecognition.BarCodeReader;
import com.aspose.barcode.barcoderecognition.BarCodeResult;
import com.aspose.barcode.barcoderecognition.DecodeType;
import com.aspose.barcode.generation.BarCodeImageFormat;
import com.aspose.barcode.generation.BarcodeGenerator;
import com.aspose.barcode.generation.EncodeTypes;
import com.aspose.barcode.guide.common.ExampleAssist;
import com.aspose.barcode.guide.common.LicenseAssist;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;

import static com.aspose.barcode.guide.common.ExampleAssist.*;

/**
 * Examples of saving generated barcodes:
 * - Different raster output formats (PNG, JPEG, TIFF).
 * - Vector format (SVG).
 * - Saving to an in-memory stream and recognizing from that stream.
 *
 * Conventions:
 * - No try-with-resources; do not close/Dispose readers/generators explicitly.
 * - Deterministic output paths under src/test/resources.
 * - Uses ExampleAssist helpers (path combining, assertions).
 */
public class SaveGeneratedImageExample {

    private static final String FOLDER =
            ExampleAssist.getOrCreateResourceFolderPath("generation", "save_options", "save_generated_image");

    // Raster output files
    private static final String FILE_CODE128_PNG  = "code128_output_png.png";
    private static final String FILE_CODE128_JPEG = "code128_output_jpeg.jpg";
    private static final String FILE_CODE128_TIFF = "code128_output_tiff.tif";

    // Vector output file
    private static final String FILE_QR_SVG          = "qr_output_svg.svg";
    private static final String FILE_QR_SVG_EXPECTED = "qr_output_svg_expected.svg";

    @BeforeClass
    public void setUp() throws Exception {
        LicenseAssist.setupLicense();
    }

    /**
     * Saving the same CODE_128 barcode to common raster formats.
     *
     * Shows:
     * - Saving to PNG, JPEG and TIFF using BarCodeImageFormat.
     * - All outputs remain recognizable by BarCodeReader.
     */
    @Test
    public void code128_save_to_raster_formats() throws Exception {
        final String payload = "SAVE-RASTER";

        BarcodeGenerator generator = new BarcodeGenerator(EncodeTypes.CODE_128, payload);
        generator.getParameters().getImageWidth().setPixels(300);
        generator.getParameters().getImageHeight().setPixels(150);

        // PNG
        String pngPath = ExampleAssist.pathCombine(FOLDER, FILE_CODE128_PNG);
        generator.save(pngPath, BarCodeImageFormat.PNG);
        ExampleAssist.assertFileCreated(pngPath);

        assertImageHasBarcodes(
                pngPath,
                1,
                List.of(expected(DecodeType.CODE_128, payload))
        );

        // JPEG
        String jpegPath = ExampleAssist.pathCombine(FOLDER, FILE_CODE128_JPEG);
        generator.save(jpegPath, BarCodeImageFormat.JPEG);
        ExampleAssist.assertFileCreated(jpegPath);

        assertImageHasBarcodes(
                jpegPath,
                1,
                List.of(expected(DecodeType.CODE_128, payload))
        );

        // TIFF
        String tiffPath = ExampleAssist.pathCombine(FOLDER, FILE_CODE128_TIFF);
        generator.save(tiffPath, BarCodeImageFormat.TIFF);
        ExampleAssist.assertFileCreated(tiffPath);

        assertImageHasBarcodes(
                tiffPath,
                1,
                List.of(expected(DecodeType.CODE_128, payload))
        );
    }

    /**
     * Saving QR barcode as a vector image (SVG).
     *
     * Shows:
     * - Vector output via BarCodeImageFormat.SVG for scalable rendering/printing.
     */
    @Test
    public void qr_save_to_vector_svg() throws Exception {
        final String payload = "SAVE-SVG";

        BarcodeGenerator generator = new BarcodeGenerator(EncodeTypes.QR, payload);
        generator.getParameters().getImageWidth().setPixels(220);
        generator.getParameters().getImageHeight().setPixels(220);

        String svgPath = ExampleAssist.pathCombine(FOLDER, FILE_QR_SVG);
        generator.save(svgPath, BarCodeImageFormat.SVG);
        ExampleAssist.assertFileCreated(svgPath);

        // Compare generated SVG with baseline template
        String expectedSvgPath = ExampleAssist.pathCombine(FOLDER, FILE_QR_SVG_EXPECTED);
        assertVectorImageEqualsExpected(expectedSvgPath, svgPath);
    }

    /**
     * Saving a generated barcode to an in-memory stream and recognizing it back from that stream.
     *
     * Shows:
     * - generator.save(OutputStream, BarCodeImageFormat).
     * - Creating BarCodeReader from an InputStream.
     */
    @Test
    public void code128_save_to_stream_and_recognize() throws Exception {
        final String payload = "SAVE-STREAM";

        BarcodeGenerator generator = new BarcodeGenerator(EncodeTypes.CODE_128, payload);
        generator.getParameters().getImageWidth().setPixels(300);
        generator.getParameters().getImageHeight().setPixels(150);

        // Save to in-memory stream (PNG)
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        generator.save(outputStream, BarCodeImageFormat.PNG);

        byte[] imageBytes = outputStream.toByteArray();
        Assert.assertTrue(imageBytes.length > 0, "Stream must contain image data");

        // Recognize directly from the in-memory bytes
        ByteArrayInputStream inputStream = new ByteArrayInputStream(imageBytes);
        BarCodeReader reader = new BarCodeReader(inputStream, DecodeType.CODE_128);
        BarCodeResult[] results = reader.readBarCodes();

        Assert.assertNotNull(results, "Results array must not be null");
        Assert.assertTrue(results.length > 0, "At least one barcode is expected in the stream image");
        Assert.assertEquals(results[0].getCodeText(), payload, "Recognized CodeText must match the payload");
        Assert.assertEquals(results[0].getCodeType(), DecodeType.CODE_128, "Recognized type must be CODE_128");
    }
}
