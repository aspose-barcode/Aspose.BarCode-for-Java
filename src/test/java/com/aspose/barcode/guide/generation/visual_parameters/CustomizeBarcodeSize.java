package com.aspose.barcode.guide.generation.visual_parameters;

import com.aspose.barcode.barcoderecognition.DecodeType;
import com.aspose.barcode.generation.AutoSizeMode;
import com.aspose.barcode.generation.BarCodeImageFormat;
import com.aspose.barcode.generation.BarcodeGenerator;
import com.aspose.barcode.generation.CodeLocation;
import com.aspose.barcode.generation.DataMatrixEccType;
import com.aspose.barcode.generation.DataMatrixVersion;
import com.aspose.barcode.generation.EncodeTypes;
import com.aspose.barcode.generation.ITF14BorderType;
import com.aspose.barcode.generation.ITFParameters;
import com.aspose.barcode.generation.QRVersion;
import com.aspose.barcode.generation.Unit;
import com.aspose.barcode.guide.common.ExampleAssist;
import com.aspose.barcode.guide.common.LicenseAssist;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

import static com.aspose.barcode.guide.common.ExampleAssist.assertImageHasBarcodes;
import static com.aspose.barcode.guide.common.ExampleAssist.expected;

/**
 * Demonstrates how to configure barcode dimensions and layout parameters.
 *
 * <p>The examples cover X-dimension, bar height, physical units, resolution,
 * auto-size modes, image bounds, quiet zones, and size parameters specific
 * to Australia Post, ITF-14, QR, Data Matrix, and PDF417.</p>
 */
public class CustomizeBarcodeSize {

    private static final String FOLDER =
            ExampleAssist.getOrCreateResourceFolderPath(
                    "generation",
                    "visual_parameters",
                    "customize_barcode_size"
            );

    private static final String FILE_CODE128_XDIMENSION =
            "code128_xdimension_pixels.png";
    private static final String FILE_CODE128_BAR_HEIGHT =
            "code128_bar_height_300dpi.png";
    private static final String FILE_EAN13_AUTOSIZE_NONE =
            "ean13_autosize_none.png";
    private static final String FILE_EAN13_AUTOSIZE_NEAREST =
            "ean13_autosize_nearest.png";
    private static final String FILE_AUSTRALIA_POST_SHORT_BAR =
            "australia_post_short_bar.png";
    private static final String FILE_QR_XDIMENSION =
            "qr_xdimension_203dpi.png";
    private static final String FILE_EAN13_QUIET_ZONE =
            "ean13_quiet_zone_300dpi.png";
    private static final String FILE_UPCA_AUTOSIZE_NONE =
            "upca_autosize_none.png";
    private static final String FILE_UPCA_AUTOSIZE_INTERPOLATION =
            "upca_autosize_interpolation.png";
    private static final String FILE_ITF14_BEARER_BAR =
            "itf14_bearer_bar_300dpi.png";
    private static final String FILE_PDF417_GRID =
            "pdf417_rows_columns.png";
    private static final String FILE_DATA_MATRIX_SIZE =
            "data_matrix_fixed_size.png";
    private static final String FILE_QR_QUIET_ZONE =
            "qr_quiet_zone_points.png";

    /**
     * Applies the Aspose.BarCode license before running the examples.
     */
    @BeforeClass
    public void setUp() {
        LicenseAssist.setupLicense();
    }

    /**
     * Configures Code 128 X-dimension and bar height in pixels.
     *
     * <p>The example uses {@link AutoSizeMode#NONE} so the explicitly configured
     * X-dimension and bar height remain the controlling dimensions.</p>
     */
    @Test
    public void configureCode128XDimensionInPixels() throws Exception {
        String codeText = "XDIM-PX";

        BarcodeGenerator generator = new BarcodeGenerator(
                EncodeTypes.CODE_128,
                codeText
        );

        generator.getParameters().setAutoSizeMode(AutoSizeMode.NONE);
        generator.getParameters().getImageWidth().setPixels(500);
        generator.getParameters().getImageHeight().setPixels(180);

        Unit xDimension =
                generator.getParameters().getBarcode().getXDimension();

        xDimension.setPixels(3.0f);

        generator.getParameters()
                .getBarcode()
                .getBarHeight()
                .setPixels(100);

        generator.getParameters()
                .getBarcode()
                .getPadding()
                .getLeft()
                .setPixels(12);

        generator.getParameters()
                .getBarcode()
                .getPadding()
                .getRight()
                .setPixels(12);

        generator.getParameters()
                .getBarcode()
                .getPadding()
                .getTop()
                .setPixels(8);

        generator.getParameters()
                .getBarcode()
                .getPadding()
                .getBottom()
                .setPixels(8);

        Assert.assertEquals(
                (int) xDimension.getPixels(),
                3,
                "X-dimension must be exactly 3 pixels"
        );

        String outputPath = ExampleAssist.pathCombine(
                FOLDER,
                FILE_CODE128_XDIMENSION
        );

        generator.save(outputPath, BarCodeImageFormat.PNG);
        ExampleAssist.assertFileCreated(outputPath);

        assertImageHasBarcodes(
                outputPath,
                1,
                List.of(expected(DecodeType.CODE_128, codeText))
        );
    }

    /**
     * Configures Code 128 bar height and X-dimension in millimeters at 300 DPI.
     *
     * <p>The example updates the resolution of each {@link Unit} before assigning
     * a physical value so that the corresponding pixel size can be verified.</p>
     */
    @Test
    public void configureCode128PhysicalDimensionsAt300Dpi() throws Exception {
        String codeText = "BAR-MM";

        BarcodeGenerator generator = new BarcodeGenerator(
                EncodeTypes.CODE_128,
                codeText
        );

        Unit barHeight =
                generator.getParameters().getBarcode().getBarHeight();
        Unit xDimension =
                generator.getParameters().getBarcode().getXDimension();

        barHeight.updateResolution(300f);
        xDimension.updateResolution(300f);

        barHeight.setMillimeters(12.0f);
        xDimension.setMillimeters(0.5f);

        Assert.assertTrue(
                Math.abs(barHeight.getPixels() - 142f) <= 3f,
                "Bar height must be approximately 142 pixels"
        );

        Assert.assertTrue(
                Math.abs(xDimension.getPixels() - 6f) <= 1f,
                "X-dimension must be approximately 6 pixels"
        );

        generator.getParameters().getImageWidth().setPixels(500);
        generator.getParameters().getImageHeight().setPixels(220);

        generator.getParameters()
                .getBarcode()
                .getPadding()
                .getLeft()
                .setPixels(16);

        generator.getParameters()
                .getBarcode()
                .getPadding()
                .getRight()
                .setPixels(16);

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

        String outputPath = ExampleAssist.pathCombine(
                FOLDER,
                FILE_CODE128_BAR_HEIGHT
        );

        generator.save(outputPath, BarCodeImageFormat.PNG);
        ExampleAssist.assertFileCreated(outputPath);

        assertImageHasBarcodes(
                outputPath,
                1,
                List.of(expected(DecodeType.CODE_128, codeText))
        );
    }

    /**
     * Compares {@link AutoSizeMode#NONE} and {@link AutoSizeMode#NEAREST}
     * for the same EAN-13 data and image bounds.
     */
    @Test
    public void compareAutoSizeNoneAndNearest() throws Exception {
        String codeText = "5901234123457";

        BarcodeGenerator noneGenerator = new BarcodeGenerator(
                EncodeTypes.EAN_13,
                codeText
        );

        noneGenerator.getParameters().setAutoSizeMode(AutoSizeMode.NONE);
        noneGenerator.getParameters().getImageWidth().setPixels(220);
        noneGenerator.getParameters().getImageHeight().setPixels(120);
        noneGenerator.getParameters().getBarcode().getXDimension().setPixels(2.0f);
        noneGenerator.getParameters().getBarcode().getPadding().getLeft().setPixels(8);
        noneGenerator.getParameters().getBarcode().getPadding().getRight().setPixels(8);
        noneGenerator.getParameters().getBarcode().getPadding().getTop().setPixels(6);
        noneGenerator.getParameters().getBarcode().getPadding().getBottom().setPixels(6);

        String nonePath = ExampleAssist.pathCombine(
                FOLDER,
                FILE_EAN13_AUTOSIZE_NONE
        );

        noneGenerator.save(nonePath, BarCodeImageFormat.PNG);
        ExampleAssist.assertFileCreated(nonePath);

        assertImageHasBarcodes(
                nonePath,
                1,
                List.of(expected(DecodeType.EAN_13, codeText))
        );

        BarcodeGenerator nearestGenerator = new BarcodeGenerator(
                EncodeTypes.EAN_13,
                codeText
        );

        nearestGenerator.getParameters().setAutoSizeMode(AutoSizeMode.NEAREST);
        nearestGenerator.getParameters().getImageWidth().setPixels(220);
        nearestGenerator.getParameters().getImageHeight().setPixels(120);
        nearestGenerator.getParameters().getBarcode().getXDimension().setPixels(2.0f);
        nearestGenerator.getParameters().getBarcode().getPadding().getLeft().setPixels(8);
        nearestGenerator.getParameters().getBarcode().getPadding().getRight().setPixels(8);
        nearestGenerator.getParameters().getBarcode().getPadding().getTop().setPixels(6);
        nearestGenerator.getParameters().getBarcode().getPadding().getBottom().setPixels(6);

        String nearestPath = ExampleAssist.pathCombine(
                FOLDER,
                FILE_EAN13_AUTOSIZE_NEAREST
        );

        nearestGenerator.save(nearestPath, BarCodeImageFormat.PNG);
        ExampleAssist.assertFileCreated(nearestPath);

        assertImageHasBarcodes(
                nearestPath,
                1,
                List.of(expected(DecodeType.EAN_13, codeText))
        );
    }

    /**
     * Configures the short-bar height for an Australia Post barcode.
     *
     * <p>The recognized value is checked by prefix because Australia Post
     * recognition can append generated machine data to the original FCC and DPID.</p>
     */
    @Test
    public void configureAustraliaPostShortBarHeight() throws Exception {
        String codeText = "5912345678";

        BarcodeGenerator generator = new BarcodeGenerator(
                EncodeTypes.AUSTRALIA_POST,
                codeText
        );

        generator.getParameters()
                .getBarcode()
                .getCodeTextParameters()
                .setLocation(CodeLocation.NONE);

        generator.getParameters()
                .setAutoSizeMode(AutoSizeMode.NONE);

        generator.getParameters()
                .getBarcode()
                .getXDimension()
                .setPixels(3.0f);

        generator.getParameters()
                .getBarcode()
                .getBarHeight()
                .setPixels(100);

        generator.getParameters()
                .getBarcode()
                .getAustralianPost()
                .getAustralianPostShortBarHeight()
                .setPixels(12);

        generator.getParameters()
                .getBarcode()
                .getPadding()
                .getLeft()
                .setPixels(24);

        generator.getParameters()
                .getBarcode()
                .getPadding()
                .getRight()
                .setPixels(24);

        generator.getParameters()
                .getBarcode()
                .getPadding()
                .getTop()
                .setPixels(16);

        generator.getParameters()
                .getBarcode()
                .getPadding()
                .getBottom()
                .setPixels(16);

        String outputPath = ExampleAssist.pathCombine(
                FOLDER,
                FILE_AUSTRALIA_POST_SHORT_BAR
        );

        generator.save(
                outputPath,
                BarCodeImageFormat.PNG
        );

        ExampleAssist.assertFileCreated(outputPath);

        assertImageHasBarcodes(
                outputPath,
                1,
                List.of(
                        ExampleAssist.expectedPrefix(
                                DecodeType.AUSTRALIA_POST,
                                codeText
                        )
                )
        );
    }

    /**
     * Configures QR X-dimension in millimeters for a 203 DPI device.
     */
    @Test
    public void configureQrXDimensionAt203Dpi() throws Exception {
        String codeText = "QR-203DPI";

        BarcodeGenerator generator = new BarcodeGenerator(
                EncodeTypes.QR,
                codeText
        );

        Unit xDimension =
                generator.getParameters().getBarcode().getXDimension();

        xDimension.updateResolution(203f);
        xDimension.setMillimeters(0.50f);

        String outputPath = ExampleAssist.pathCombine(
                FOLDER,
                FILE_QR_XDIMENSION
        );

        generator.save(outputPath, BarCodeImageFormat.PNG);
        ExampleAssist.assertFileCreated(outputPath);

        assertImageHasBarcodes(
                outputPath,
                1,
                List.of(expected(DecodeType.QR, codeText))
        );
    }

    /**
     * Configures EAN-13 quiet zones in millimeters at 300 DPI.
     */
    @Test
    public void configureEan13QuietZonesAt300Dpi() throws Exception {
        String codeText = "5901234123457";

        BarcodeGenerator generator = new BarcodeGenerator(
                EncodeTypes.EAN_13,
                codeText
        );

        Unit leftPadding = generator.getParameters()
                .getBarcode()
                .getPadding()
                .getLeft();

        Unit rightPadding = generator.getParameters()
                .getBarcode()
                .getPadding()
                .getRight();

        leftPadding.updateResolution(300f);
        rightPadding.updateResolution(300f);
        leftPadding.setMillimeters(3.7f);
        rightPadding.setMillimeters(3.7f);

        generator.getParameters().getImageWidth().setPixels(320);
        generator.getParameters().getImageHeight().setPixels(160);

        String outputPath = ExampleAssist.pathCombine(
                FOLDER,
                FILE_EAN13_QUIET_ZONE
        );

        generator.save(outputPath, BarCodeImageFormat.PNG);
        ExampleAssist.assertFileCreated(outputPath);

        assertImageHasBarcodes(
                outputPath,
                1,
                List.of(expected(DecodeType.EAN_13, codeText))
        );
    }

    /**
     * Compares {@link AutoSizeMode#NONE} and
     * {@link AutoSizeMode#INTERPOLATION} for UPC-A.
     */
    @Test
    public void compareUpcaAutoSizeNoneAndInterpolation() throws Exception {
        String codeText = "042100005264";

        BarcodeGenerator noneGenerator = new BarcodeGenerator(
                EncodeTypes.UPCA,
                codeText
        );

        noneGenerator.getParameters().setAutoSizeMode(AutoSizeMode.NONE);
        noneGenerator.getParameters().getImageWidth().setPixels(260);
        noneGenerator.getParameters().getImageHeight().setPixels(140);
        noneGenerator.getParameters().getBarcode().getXDimension().setPixels(2.0f);

        String nonePath = ExampleAssist.pathCombine(
                FOLDER,
                FILE_UPCA_AUTOSIZE_NONE
        );

        noneGenerator.save(nonePath, BarCodeImageFormat.PNG);
        ExampleAssist.assertFileCreated(nonePath);

        assertImageHasBarcodes(
                nonePath,
                1,
                List.of(expected(DecodeType.UPCA, codeText))
        );

        BarcodeGenerator interpolationGenerator = new BarcodeGenerator(
                EncodeTypes.UPCA,
                codeText
        );

        interpolationGenerator.getParameters()
                .setAutoSizeMode(AutoSizeMode.INTERPOLATION);

        interpolationGenerator.getParameters().getImageWidth().setPixels(260);
        interpolationGenerator.getParameters().getImageHeight().setPixels(140);
        interpolationGenerator.getParameters().getBarcode().getXDimension().setPixels(2.0f);

        String interpolationPath = ExampleAssist.pathCombine(
                FOLDER,
                FILE_UPCA_AUTOSIZE_INTERPOLATION
        );

        interpolationGenerator.save(
                interpolationPath,
                BarCodeImageFormat.PNG
        );

        ExampleAssist.assertFileCreated(interpolationPath);

        assertImageHasBarcodes(
                interpolationPath,
                1,
                List.of(expected(DecodeType.UPCA, codeText))
        );
    }

    /**
     * Configures ITF-14 bearer-bar thickness in millimeters at 300 DPI.
     */
    @Test
    public void configureItf14BearerBarAt300Dpi() throws Exception {
        String codeText = "10012345000017";

        BarcodeGenerator generator = new BarcodeGenerator(
                EncodeTypes.ITF_14,
                codeText
        );

        ITFParameters itfParameters =
                generator.getParameters().getBarcode().getITF();

        itfParameters.setItfBorderType(ITF14BorderType.FRAME);

        Unit borderThickness =
                itfParameters.getItfBorderThickness();

        borderThickness.updateResolution(300f);
        borderThickness.setMillimeters(2.5f);

        itfParameters.setQuietZoneCoef(12);

        generator.getParameters().getBarcode().getXDimension().setPixels(2.0f);
        generator.getParameters().getBarcode().getBarHeight().setPixels(100);
        generator.getParameters().getImageWidth().setPixels(520);
        generator.getParameters().getImageHeight().setPixels(260);

        String outputPath = ExampleAssist.pathCombine(
                FOLDER,
                FILE_ITF14_BEARER_BAR
        );

        generator.save(outputPath, BarCodeImageFormat.PNG);
        ExampleAssist.assertFileCreated(outputPath);

        assertImageHasBarcodes(
                outputPath,
                1,
                List.of(expected(DecodeType.ITF_14, codeText))
        );
    }

    /**
     * Configures the number of rows and columns in a PDF417 barcode.
     */
    @Test
    public void configurePdf417RowsAndColumns() throws Exception {
        String codeText = "PDF417-SIZE";

        BarcodeGenerator generator = new BarcodeGenerator(
                EncodeTypes.PDF_417,
                codeText
        );

        generator.getParameters()
                .getBarcode()
                .getPdf417()
                .setRows(8);

        generator.getParameters()
                .getBarcode()
                .getPdf417()
                .setColumns(5);

        generator.getParameters().getImageWidth().setPixels(480);
        generator.getParameters().getImageHeight().setPixels(240);

        String outputPath = ExampleAssist.pathCombine(
                FOLDER,
                FILE_PDF417_GRID
        );

        generator.save(outputPath, BarCodeImageFormat.PNG);
        ExampleAssist.assertFileCreated(outputPath);

        assertImageHasBarcodes(
                outputPath,
                1,
                List.of(expected(DecodeType.PDF_417, codeText))
        );
    }

    /**
     * Configures a fixed Data Matrix version and X-dimension in pixels.
     */
    @Test
    public void configureDataMatrixVersionAndXDimension() throws Exception {
        String codeText = "DM-SIZE";

        BarcodeGenerator generator = new BarcodeGenerator(
                EncodeTypes.DATA_MATRIX,
                codeText
        );

        generator.getParameters()
                .getBarcode()
                .getDataMatrix()
                .setDataMatrixEcc(DataMatrixEccType.ECC_200);

        generator.getParameters()
                .getBarcode()
                .getDataMatrix()
                .setDataMatrixVersion(
                        DataMatrixVersion.ECC200_24x24
                );

        generator.getParameters()
                .getBarcode()
                .getXDimension()
                .setPixels(3.0f);

        generator.getParameters().getBarcode().getPadding().getLeft().setPixels(12);
        generator.getParameters().getBarcode().getPadding().getRight().setPixels(12);
        generator.getParameters().getBarcode().getPadding().getTop().setPixels(12);
        generator.getParameters().getBarcode().getPadding().getBottom().setPixels(12);
        generator.getParameters().getImageWidth().setPixels(220);
        generator.getParameters().getImageHeight().setPixels(220);

        String outputPath = ExampleAssist.pathCombine(
                FOLDER,
                FILE_DATA_MATRIX_SIZE
        );

        generator.save(outputPath, BarCodeImageFormat.PNG);
        ExampleAssist.assertFileCreated(outputPath);

        assertImageHasBarcodes(
                outputPath,
                1,
                List.of(expected(DecodeType.DATA_MATRIX, codeText))
        );
    }

    /**
     * Configures a fixed QR version and quiet zones in typographic points.
     */
    @Test
    public void configureQrVersionAndQuietZonesInPoints() throws Exception {
        String codeText = "QR-PT";

        BarcodeGenerator generator = new BarcodeGenerator(
                EncodeTypes.QR,
                codeText
        );

        generator.getParameters()
                .getBarcode()
                .getQR()
                .setVersion(QRVersion.VERSION_02);

        generator.getParameters()
                .getBarcode()
                .getPadding()
                .getLeft()
                .setPoint(12f);

        generator.getParameters()
                .getBarcode()
                .getPadding()
                .getRight()
                .setPoint(12f);

        generator.getParameters()
                .getBarcode()
                .getPadding()
                .getTop()
                .setPoint(12f);

        generator.getParameters()
                .getBarcode()
                .getPadding()
                .getBottom()
                .setPoint(12f);

        generator.getParameters().getImageWidth().setPixels(220);
        generator.getParameters().getImageHeight().setPixels(220);

        String outputPath = ExampleAssist.pathCombine(
                FOLDER,
                FILE_QR_QUIET_ZONE
        );

        generator.save(outputPath, BarCodeImageFormat.PNG);
        ExampleAssist.assertFileCreated(outputPath);

        assertImageHasBarcodes(
                outputPath,
                1,
                List.of(expected(DecodeType.QR, codeText))
        );
    }
}
