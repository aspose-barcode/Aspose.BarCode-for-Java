package com.aspose.barcode.guide.recognition.barcode_properties;

import com.aspose.barcode.barcoderecognition.*;
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

import static com.aspose.barcode.guide.common.ExampleAssist.*;

/**
 * Demonstrates how to read and interpret the rotation angle reported by the engine.
 * Focus points:
 *  - region.getAngle(): approximate rotation of the detected barcode (in degrees, double).
 *  - Rectangle vs Quadrangle for rotated codes.
 *  - Accessing raw corner Points via getPoints().
 *  - Simple visual overlay for debugging angles and geometry.
 */
public class AnglesExample {

    private static final String FOLDER =
            ExampleAssist.getOrCreateResourceFolderPath("recognition", "barcode_properties", "angles");
    private static final String FILE_C128_30 = "angle_c128_30.png";
    private static final String FILE_QR_45   = "angle_qr_45.png";
    private static final String FILE_DEBUG   = "angles_overlay.png";

    @BeforeClass
    public void setUp() throws Exception {
        LicenseAssist.setupLicense();
        generateFixtures();
    }

    /**
     * Test: Detect angle for a rotated Code128 (≈ 30 degrees).
     *
     * What this test demonstrates:
     * - How to retrieve angle via {@link BarCodeRegionParameters#getAngle()} (double).
     * - Why to compare with a tolerance: angle is estimated and may vary slightly.
     * - How to draw a quick overlay with Rectangle and Quadrangle.
     */
    @Test
    public void readCode128Angle_Approx30deg() throws Exception {
        String path = ExampleAssist.pathCombine(FOLDER, FILE_C128_30);
        BarCodeReader rd = new BarCodeReader(path, DecodeType.CODE_128);
        BarCodeResult[] results = rd.readBarCodes();
        Assert.assertTrue(results.length >= 1, "Expected at least 1 result");

        BarCodeRegionParameters region = results[0].getRegion();
        double angle = region.getAngle(); // degrees (double)
        System.out.println("Detected angle (Code128 ~30°): " + angle);

        // Use a reasonable tolerance; exact equality is not expected.
        assertAngleClose(angle, 30.0, 7.5, "Angle should be close to 30° within tolerance");

        // Optional: overlay to see oriented quadrangle vs. axis-aligned rectangle
        drawOverlay(path, ExampleAssist.pathCombine(FOLDER, FILE_DEBUG), region);
    }

    /**
     * Test: Detect angle for a rotated QR (≈ 45 degrees).
     *
     * What this test demonstrates:
     * - Angle retrieval works identically for 2D symbologies.
     * - Quadrangle corners outline the rotated square (visually a diamond at ~45°).
     */
    @Test
    public void readQRAngleApprox45deg() throws Exception {
        String path = ExampleAssist.pathCombine(FOLDER, FILE_QR_45);
        BarCodeReader barCodeReader = new BarCodeReader(path, DecodeType.QR);
        barCodeReader.setQualitySettings(QualitySettings.getHighQuality());
        BarCodeResult[] results = barCodeReader.readBarCodes();
        Assert.assertTrue(results.length >= 1, "Expected at least 1 result");

        BarCodeRegionParameters region = results[0].getRegion();
        double angle = region.getAngle();
        System.out.println("Detected angle (QR ~45°): " + angle);

        assertAngleClose(angle, 45.0, 7.5, "Angle should be close to 45° within tolerance");
    }

    /**
     * Test: Compare Rectangle vs Quadrangle for a rotated barcode.
     *
     * What this test demonstrates:
     * - Rectangle remains axis-aligned and grows to bound the rotated shape.
     * - Quadrangle tracks true corners and preserves rotation/skew visually.
     */
    @Test
    public void compare_Rectangle_VS_Quadrangle_OnRotated() throws Exception {
        String path = ExampleAssist.pathCombine(FOLDER, FILE_C128_30);
        BarCodeReader rd = new BarCodeReader(path, DecodeType.CODE_128);
        BarCodeResult[] results = rd.readBarCodes();
        Assert.assertTrue(results.length >= 1, "Expected at least 1 result");

        BarCodeRegionParameters region = results[0].getRegion();
        Rectangle rect = region.getRectangle();
        Quadrangle quad = region.getQuadrangle();

        System.out.println("Rect: " + rect);
        System.out.println("Quad: LT=" + quad.getLeftTop() + " RT=" + quad.getRightTop()
                + " RB=" + quad.getRightBottom() + " LB=" + quad.getLeftBottom());

        // Sanity: for a rotated code, the top edge shouldn't be perfectly horizontal.
        Point lt = quad.getLeftTop();
        Point rt = quad.getRightTop();
        Assert.assertTrue(Math.abs(rt.y - lt.y) > 0, "Top edge should not be perfectly horizontal on a rotated code");

        // The axis-aligned Rectangle should match Quadrangle's bounding rectangle.
        Rectangle quadBounds = quad.getBoundingRectangle();
        Assert.assertEquals(rect, quadBounds, "Rectangle must equal Quadrangle's bounding rectangle");
    }

    /**
     * Test: Using getPoints() to read raw corner points.
     *
     * What this test demonstrates:
     * - {@link BarCodeRegionParameters#getPoints()} exposes an array of four {@link java.awt.Point}.
     * - These points should correspond to the quadrangle's corners in consistent order.
     */
    @Test
    public void readCornersViaGetPoints() throws Exception {
        String path = ExampleAssist.pathCombine(FOLDER, FILE_QR_45);
        BarCodeReader barCodeReader = new BarCodeReader(path, DecodeType.QR);
        barCodeReader.setQualitySettings(QualitySettings.getHighQuality());
        BarCodeResult[] results = barCodeReader.readBarCodes();
        Assert.assertTrue(results.length >= 1, "Expected at least 1 result");

        BarCodeRegionParameters region = results[0].getRegion();
        Point[] pts = region.getPoints();
        Assert.assertNotNull(pts, "Points array must not be null");
        Assert.assertEquals(pts.length, 4, "Expected 4 corner points");

        Quadrangle q = region.getQuadrangle();
        // The set of points must contain all quadrangle corners (order may be engine-specific).
        boolean containsAll =
                containsPoint(pts, q.getLeftTop()) &&
                        containsPoint(pts, q.getRightTop()) &&
                        containsPoint(pts, q.getRightBottom()) &&
                        containsPoint(pts, q.getLeftBottom());

        Assert.assertTrue(containsAll, "Points[] must contain all 4 quadrangle corners");
    }

    // ---------- overlay helper ----------
    private static void drawOverlay(String srcPath, String outPath, BarCodeRegionParameters region) throws Exception {
        BufferedImage img = ImageIO.read(new File(srcPath));
        Graphics2D g = img.createGraphics();
        try {
            g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            // Draw Rectangle in green
            Rectangle rect = region.getRectangle();
            g.setColor(new Color(0, 160, 0));
            g.setStroke(new BasicStroke(2f));
            g.drawRect(rect.x, rect.y, rect.width, rect.height);

            // Draw Quadrangle in blue
            Quadrangle q = region.getQuadrangle();
            Point lt = q.getLeftTop();
            Point rt = q.getRightTop();
            Point rb = q.getRightBottom();
            Point lb = q.getLeftBottom();

            g.setColor(new Color(0, 90, 220));
            g.drawLine(lt.x, lt.y, rt.x, rt.y);
            g.drawLine(rt.x, rt.y, rb.x, rb.y);
            g.drawLine(rb.x, rb.y, lb.x, lb.y);
            g.drawLine(lb.x, lb.y, lt.x, lt.y);

            // Angle annotation (double with single decimal)
            String txt = String.format("angle: %.1f°", region.getAngle());
            g.setColor(Color.BLACK);
            g.drawString(txt, Math.max(0, rect.x), Math.max(12, rect.y - 6));
        } finally {
            g.dispose();
        }
        ImageIO.write(img, "PNG", new File(outPath));
        System.out.println("[INFO] Overlay saved: " + outPath);
    }

    // ---------- fixtures ----------
    private void generateFixtures() throws Exception {
        // 1) Render a large Code128 with quiet zones, then rotate it ~30°
        ExampleAssist.checkOrCreateImage(FOLDER, FILE_C128_30, (String full) -> {
            // target size and geometry of modules
            String tmp = full + ".tmp.png";
            ExampleAssist.renderBarcodeFixedSizePNG(
                    EncodeTypes.CODE_128, "ANGLE-128",
                    /*widthPx*/ 420, /*heightPx*/ 180,
                    /*xDimPx*/ 2.0f, /*quietPx*/ 24,
                    tmp);

            BufferedImage base = ImageIO.read(new File(tmp));
            BufferedImage rotated = rotateCenterCrispNN(base, 30.0);
            ImageIO.write(rotated, "PNG", new File(full));
            // cleanup
            new File(tmp).delete();
        });

        // 2) Render a large QR, then rotate it ~45°
        ExampleAssist.checkOrCreateImage(FOLDER, FILE_QR_45, (String full) -> {
            String tmp = full + ".tmp.png";
            ExampleAssist.renderBarcodeFixedSizePNG(
                    EncodeTypes.QR, "ANGLE-QR",
                    /*widthPx*/ 260, /*heightPx*/ 260,
                    /*xDimPx*/ 4.0f, /*quietPx*/ 24,
                    tmp);

            BufferedImage base = ImageIO.read(new File(tmp));
            BufferedImage rotated = rotateCenterCrispNN(base, 45.0);
            ImageIO.write(rotated, "PNG", new File(full));
            new File(tmp).delete();
        });
    }
}
