package com.aspose.barcode.guide.recognition.barcode_properties;

import com.aspose.barcode.barcoderecognition.BarCodeReader;
import com.aspose.barcode.barcoderecognition.BarCodeResult;
import com.aspose.barcode.barcoderecognition.DecodeType;
import com.aspose.barcode.barcoderecognition.Quadrangle;
import com.aspose.barcode.barcoderecognition.BarCodeExtendedParameters;
import com.aspose.barcode.generation.BarcodeGenerator;
import com.aspose.barcode.generation.BarCodeImageFormat;
import com.aspose.barcode.generation.EncodeTypes;
import com.aspose.barcode.guide.common.ExampleAssist;
import com.aspose.barcode.guide.common.ImageSupplier;
import com.aspose.barcode.guide.common.LicenseAssist;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Locale;

/**
 * Demonstrates how to read and inspect barcode recognition metadata such as:
 * - Decode type / type name
 * - Confidence score
 * - Region (quadrangle/rectangle)
 * - 1D checksum (when available)
 * - Symbology-specific extended parameters (QR/DataMatrix/PDF417, etc.)
 *
 * Helper utilities:
 *  - LicenseAssist.setupLicense()      — sets license once per class
 *  - ExampleAssist.checkOrCreateImage  — generates test image if missing
 *  - ImageSupplier.*                   — ready-to-use image suppliers for generators
 */
public class ReadingMetadataExample {

    private static final String FOLDER =
            ExampleAssist.getOrCreateResourceFolderPath("recognition", "barcode_properties","reading_metadata");

    @BeforeClass
    public void setUp() {
        LicenseAssist.setupLicense();
        Locale.setDefault(Locale.ROOT);
    }

    /**
     * Reads a Code128 barcode and prints generic metadata:
     * decode type, code text, confidence, region, and 1D checksum (if present).
     */
    @Test
    public void read_Code128_Metadata_Basic() throws Exception {
        String fileName = "code128_meta_basic.png";

        // Create the image once using ImageSupplier (consistent with project helpers)
        ExampleAssist.checkOrCreateImage(FOLDER, fileName,
                ImageSupplier.code128("INV-2025-001"));

        try (BarCodeReader reader = new BarCodeReader(
                ExampleAssist.pathCombine(FOLDER, fileName),
                DecodeType.CODE_128)) {

            BarCodeResult[] results = reader.readBarCodes();
            ExampleAssist.assertRecognized(reader, fileName, 1, DecodeType.CODE_128);

            BarCodeResult r = results[0];

            // Generic metadata
            System.out.println("[Code128] CodeText: " + r.getCodeText());
            System.out.println("[Code128] Type:     " + r.getBarCodeTypeName());
            System.out.println("[Code128] Conf:     " + r.getConfidence());

            // Region metadata
            Quadrangle q = r.getRegion().getQuadrangle();
            System.out.println("[Code128] Region quad: "
                    + q.getLeftTop() + " " + q.getRightTop() + " "
                    + q.getRightBottom() + " " + q.getLeftBottom());

            // Extended metadata (1D checksum when available)
            BarCodeExtendedParameters ext = r.getExtended();
            if (ext != null && ext.getOneD() != null) {
                System.out.println("[Code128] OneD checksum: " + ext.getOneD().getCheckSum());
            } else {
                System.out.println("[Code128] OneD checksum: <n/a>");
            }
        }
    }

    /**
     * Reads a QR code and prints symbology-specific metadata like ErrorCorrectionLevel,
     * version/mask (when available), alongside generic metadata.
     */
    @Test
    public void read_QR_Metadata_Extended() throws Exception {
        String fileName = "qr_meta_ext.png";

        // Use ImageSupplier to create a QR image with a defined payload
        ExampleAssist.checkOrCreateImage(FOLDER, fileName,
                ImageSupplier.qr("META-QR:REV=1;EC=H;TS=2025-11-11"));

        try (BarCodeReader reader = new BarCodeReader(
                ExampleAssist.pathCombine(FOLDER, fileName),
                DecodeType.QR)) {

            BarCodeResult[] results = reader.readBarCodes();
            ExampleAssist.assertRecognized(reader, fileName, 1, DecodeType.QR);

            BarCodeResult r = results[0];

            // Generic metadata
            System.out.println("[QR] CodeText: " + r.getCodeText());
            System.out.println("[QR] Type:     " + r.getBarCodeTypeName());
            System.out.println("[QR] Conf:     " + r.getConfidence());

            Quadrangle q = r.getRegion().getQuadrangle();
            System.out.println("[QR] Region quad: "
                    + q.getLeftTop() + " " + q.getRightTop() + " "
                    + q.getRightBottom() + " " + q.getLeftBottom());

            // QR extended metadata
            BarCodeExtendedParameters ext = r.getExtended();
            if (ext != null && ext.getQR() != null) {
                var qr = ext.getQR();
                System.out.println("[QR] ErrorLevel: " + qr.getErrorLevel());
                System.out.println("[QR] Version:    " + qr.getVersion());
                System.out.println("[QR] Mask:       " + qr.getMask());
                System.out.println("[QR] MicroQR:    " + qr.isMicroQR());
            } else {
                System.out.println("[QR] Extended params: <n/a>");
            }
        }
    }

    /**
     * Reads a DataMatrix code and prints DataMatrix-specific extended parameters,
     * such as DataMatrix version and structured append flags (if provided by engine).
     */
    @Test
    public void read_DataMatrix_Metadata_Extended() throws Exception {
        String fileName = "dm_meta_ext.png";

        ExampleAssist.checkOrCreateImage(FOLDER, fileName,
                ImageSupplier.dataMatrix("DM:PART=1/1;REF=ABC-42"));

        try (BarCodeReader reader = new BarCodeReader(
                ExampleAssist.pathCombine(FOLDER, fileName),
                DecodeType.DATA_MATRIX)) {

            BarCodeResult[] results = reader.readBarCodes();
            ExampleAssist.assertRecognized(reader, fileName, 1, DecodeType.DATA_MATRIX);

            BarCodeResult r = results[0];

            // Generic metadata
            System.out.println("[DM] CodeText: " + r.getCodeText());
            System.out.println("[DM] Type:     " + r.getBarCodeTypeName());
            System.out.println("[DM] Conf:     " + r.getConfidence());

            Quadrangle q = r.getRegion().getQuadrangle();
            System.out.println("[DM] Region quad: "
                    + q.getLeftTop() + " " + q.getRightTop() + " "
                    + q.getRightBottom() + " " + q.getLeftBottom());

            // DataMatrix extended
            BarCodeExtendedParameters ext = r.getExtended();
            if (ext != null && ext.getDataMatrix() != null) {
                var dm = ext.getDataMatrix();
                System.out.println("[DM] DM version: " + dm.getDataMatrixVersion());
                System.out.println("[DM] Rectangular: " + dm.isReaderProgramming());
                System.out.println("[DM] StructuredAppend: " + dm.isStructuredAppend());
            } else {
                System.out.println("[DM] Extended params: <n/a>");
            }
        }
    }

    /**
     * Compares confidence and region size for the same image under different quality settings.
     * This shows how quality presets may affect confidence scores and geometry.
     */
    @Test
    public void compare_Metadata_AcrossQualitySettings() throws Exception {
        String fileName = "code128_quality_compare.png";

        ExampleAssist.checkOrCreateImage(FOLDER, fileName,
                ImageSupplier.code128("META-QUALITY-TEST"));

        // Fast (speed-oriented)
        try (BarCodeReader fast = new BarCodeReader(
                ExampleAssist.pathCombine(FOLDER, fileName),
                DecodeType.CODE_128)) {

            fast.setQualitySettings(com.aspose.barcode.barcoderecognition.QualitySettings.getHighSpeed());
            BarCodeResult[] resFast = fast.readBarCodes();

            System.out.println("[Fast] count=" + resFast.length);
            if (resFast.length > 0) {
                BarCodeResult r = resFast[0];
                System.out.println("[Fast] Conf=" + r.getConfidence()
                        + " RegionW=" + r.getRegion().getRectangle().getWidth()
                        + " RegionH=" + r.getRegion().getRectangle().getHeight());
            }
        }

        // High quality (accuracy-oriented)
        try (BarCodeReader high = new BarCodeReader(
                ExampleAssist.pathCombine(FOLDER, fileName),
                DecodeType.CODE_128)) {

            high.setQualitySettings(com.aspose.barcode.barcoderecognition.QualitySettings.getHighQuality());
            BarCodeResult[] resHigh = high.readBarCodes();

            System.out.println("[High] count=" + resHigh.length);
            if (resHigh.length > 0) {
                BarCodeResult r = resHigh[0];
                System.out.println("[High] Conf=" + r.getConfidence()
                        + " RegionW=" + r.getRegion().getRectangle().getWidth()
                        + " RegionH=" + r.getRegion().getRectangle().getHeight());
            }

            ExampleAssist.assertRecognized(high, fileName, 1, DecodeType.CODE_128);
        }
    }

    /**
     * Demonstrates how to generate and read multiple barcodes on a single image,
     * then print per-result metadata to illustrate differences across symbologies.
     */
    @Test
    public void read_Multiple_Symbologies_Metadata() throws Exception {
        String fileName = "multi_meta.png";

        // Generate a composite image via helper (Code128 + QR + DataMatrix)
        ExampleAssist.checkOrCreateImage(FOLDER, fileName,
                ImageSupplier.multiple(
                        ImageSupplier.code128("BATCH-128"),
                        ImageSupplier.qr("BATCH-QR"),
                        ImageSupplier.dataMatrix("BATCH-DM")));

        try (BarCodeReader reader = new BarCodeReader(
                ExampleAssist.pathCombine(FOLDER, fileName),
                DecodeType.CODE_128, DecodeType.QR, DecodeType.DATA_MATRIX)) {

            BarCodeResult[] results = reader.readBarCodes();
            System.out.println("[Multi] Detected: " + results.length);

            for (BarCodeResult r : results) {
                System.out.println("----");
                System.out.println("Type: " + r.getBarCodeTypeName());
                System.out.println("Text: " + r.getCodeText());
                System.out.println("Conf: " + r.getConfidence());

                Quadrangle q = r.getRegion().getQuadrangle();
                System.out.println("Region quad: "
                        + q.getLeftTop() + " " + q.getRightTop() + " "
                        + q.getRightBottom() + " " + q.getLeftBottom());

                BarCodeExtendedParameters ext = r.getExtended();
                if (ext != null) {
                    if (ext.getOneD() != null) {
                        System.out.println("OneD checksum: " + ext.getOneD().getCheckSum());
                    }
                    if (ext.getQR() != null) {
                        System.out.println("QR EC: " + ext.getQR().getErrorLevel()
                                + " Ver: " + ext.getQR().getVersion());
                    }
                    if (ext.getDataMatrix() != null) {
                        System.out.println("DM Ver: " + ext.getDataMatrix().getDataMatrixVersion()
                                + " SA: " + ext.getDataMatrix().isStructuredAppend());
                    }
                }
            }

            // Expect exactly 3 results (one per symbology)
            ExampleAssist.assertRecognized(reader, fileName, 3,
                    DecodeType.CODE_128, DecodeType.QR, DecodeType.DATA_MATRIX);
        }
    }

    // ---------- Optional: local image generator (fallback pattern like in the template) ----------
    // Kept for parity with the template and to show how you could plug a custom generator
    // if ImageSupplier does not cover some special case.
    @SuppressWarnings("unused")
    private void generateCode128(String path, String text) throws Exception {
        BarcodeGenerator gen = new BarcodeGenerator(EncodeTypes.CODE_128, text);
        gen.save(path, BarCodeImageFormat.PNG);
    }
}
