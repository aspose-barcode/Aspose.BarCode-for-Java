package com.aspose.barcode.guide.recognition.barcode_properties;

import com.aspose.barcode.barcoderecognition.*;
import com.aspose.barcode.generation.BarCodeImageFormat;
import com.aspose.barcode.generation.BarcodeGenerator;
import com.aspose.barcode.generation.EncodeTypes;
import com.aspose.barcode.guide.common.ExampleAssist;
import com.aspose.barcode.guide.common.LicenseAssist;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import javax.imageio.ImageIO;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.stream.IntStream;

/**
 * Demonstrates how to retrieve region geometry from recognition results:
 *  - axis-aligned Rectangle (bounding box);
 *  - oriented Quadrangle (four corner points) that follows rotation/skew.
 * Also shows how to render a simple debug overlay with these shapes.
 */
public class CoordinatesExample {

    private static final String FOLDER =
            ExampleAssist.getOrCreateResourceFolderPath("recognition", "barcode_properties", "coordinates");
    private static final String FILE_C128 = "coords_c128.png";
    private static final String FILE_QR   = "coords_qr.png";
    private static final String FILE_DEBUG = "coords_overlay.png";

    @BeforeClass
    public void setUp() throws Exception {
        LicenseAssist.setupLicense();
        generateFixtures();
    }

    /**
     * Test: Read Code128 and print its region geometry.
     *
     * What this test demonstrates:
     * 1) How to access an axis-aligned bounding Rectangle via {@link BarCodeRegionParameters#getRectangle()}.
     * 2) How to access an oriented Quadrangle via {@link BarCodeRegionParameters#getQuadrangle()}.
     * 3) How to draw both shapes as a quick visual overlay for debugging.
     *
     * Notes:
     * - Rectangle is always axis-aligned (no rotation).
     * - Quadrangle follows true corners and therefore reflects any rotation/skew.
     */
    @Test
    public void read_Code128_RegionGeometry() throws Exception {
        String path = ExampleAssist.pathCombine(FOLDER, FILE_C128);
        BarCodeReader rd = new BarCodeReader(path, DecodeType.CODE_128);
        BarCodeResult[] results = rd.readBarCodes();
        Assert.assertTrue(results.length >= 1, "Expected at least 1 result");

        BarCodeResult r = results[0];
        BarCodeRegionParameters barCodeRegionParameters = r.getRegion();

        // Axis-aligned rectangle
        Rectangle rect = barCodeRegionParameters.getRectangle();
        System.out.println("Rect: x=" + rect.x + " y=" + rect.y + " w=" + rect.width + " h=" + rect.height);

        // Oriented quadrangle (java.awt.Point)
        Quadrangle quad = barCodeRegionParameters.getQuadrangle();
        Point lt = quad.getLeftTop();
        Point rt = quad.getRightTop();
        Point rb = quad.getRightBottom();
        Point lb = quad.getLeftBottom();
        System.out.println("LT=" + lt + " RT=" + rt + " RB=" + rb + " LB=" + lb);

        // Draw debug overlay
        drawOverlay(path, ExampleAssist.pathCombine(FOLDER, FILE_DEBUG), rect, quad);

        // Raw corner points as an array
        Point[] points = barCodeRegionParameters.getPoints();
        if (points != null) {
            IntStream.range(0, points.length).forEach(i -> {
                Point point = points[i];
                System.out.println("Point " + i + ": x=" + point.x + " y=" + point.y);
            });
        }
    }

    /**
     * Test: Read QR and print its region geometry.
     *
     * What this test demonstrates:
     * - The same Rectangle/Quadrangle API works uniformly for 2D symbologies.
     * - You can rely on Quadrangle to reflect the true outline of the detected code.
     */
    @Test
    public void read_QR_RegionGeometry() throws Exception {
        String path = ExampleAssist.pathCombine(FOLDER, FILE_QR);
        BarCodeReader rd = new BarCodeReader(path, DecodeType.QR);
        BarCodeResult[] results = rd.readBarCodes();
        Assert.assertTrue(results.length >= 1, "Expected at least 1 result");

        BarCodeRegionParameters region = results[0].getRegion();
        Rectangle rect = region.getRectangle();
        Quadrangle quad = region.getQuadrangle();

        System.out.println("QR Rect: " + rect);
        System.out.println("QR Quad: LT=" + quad.getLeftTop() + " RT=" + quad.getRightTop()
                + " RB=" + quad.getRightBottom() + " LB=" + quad.getLeftBottom());
    }

    @Test
    public void draw_DebugOverlay_For_Code128() throws Exception {
        String sourceImagePath = ExampleAssist.pathCombine(FOLDER, FILE_C128);
        String debugImagePath = ExampleAssist.pathCombine(FOLDER, FILE_DEBUG);

        // Recognize barcode
        BarCodeReader barCodeReader = new BarCodeReader(sourceImagePath, DecodeType.CODE_128);
        BarCodeResult[] barCodeResults = barCodeReader.readBarCodes();
        Assert.assertTrue(barCodeResults.length > 0, "Expected at least one result");

        // Extract region geometry
        BarCodeRegionParameters barCodeRegionParameters = barCodeResults[0].getRegion();
        Rectangle rectangle = barCodeRegionParameters.getRectangle();
        Quadrangle quadrangle = barCodeRegionParameters.getQuadrangle();

        // Draw overlay and save result
        drawOverlay(sourceImagePath, debugImagePath, rectangle, quadrangle);

        File debugFile = new File(debugImagePath);
        Assert.assertTrue(debugFile.exists(), "Overlay image was not created");
    }


    // --- helper to paint overlay ---
    private static void drawOverlay(String srcPath, String outPath,
                                    Rectangle rect,
                                    Quadrangle quad) throws Exception {
        BufferedImage img = ImageIO.read(new File(srcPath));
        Graphics2D g = img.createGraphics();
        try {
            g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g.setColor(new Color(0, 180, 0));
            g.setStroke(new BasicStroke(2f));

            // Rectangle (axis-aligned bounding box)
            if (rect != null) {
                g.drawRect(rect.x, rect.y, rect.width, rect.height);
            }

            // Quadrangle polygon: LT -> RT -> RB -> LB -> LT (oriented corners)
            if (quad != null) {
                Point lt = quad.getLeftTop();
                Point rt = quad.getRightTop();
                Point rb = quad.getRightBottom();
                Point lb = quad.getLeftBottom();

                g.drawLine(lt.x, lt.y, rt.x, rt.y);
                g.drawLine(rt.x, rt.y, rb.x, rb.y);
                g.drawLine(rb.x, rb.y, lb.x, lb.y);
                g.drawLine(lb.x, lb.y, lt.x, lt.y);
            }
        } finally {
            g.dispose();
        }
        ImageIO.write(img, "PNG", new File(outPath));
        System.out.println("[INFO] Overlay saved: " + outPath);
    }

    /**
     * Generates two small fixtures: a Code128 and a QR image.
     * The goal is to have stable, clean inputs for geometry extraction examples.
     */
    private void generateFixtures() throws Exception {
        ExampleAssist.checkOrCreateImage(FOLDER, FILE_C128, (String full) -> {
            BarcodeGenerator g = new BarcodeGenerator(EncodeTypes.CODE_128, "COORDS-128");
            g.save(full, BarCodeImageFormat.PNG);
        });
        ExampleAssist.checkOrCreateImage(FOLDER, FILE_QR, (String full) -> {
            BarcodeGenerator g = new BarcodeGenerator(EncodeTypes.QR, "COORDS-QR");
            g.save(full, BarCodeImageFormat.PNG);
        });
    }
}
