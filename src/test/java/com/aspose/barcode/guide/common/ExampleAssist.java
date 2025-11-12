package com.aspose.barcode.guide.common;

import com.aspose.barcode.barcoderecognition.*;
import com.aspose.barcode.generation.BarCodeImageFormat;
import com.aspose.barcode.generation.BarcodeGenerator;
import com.aspose.barcode.generation.BaseEncodeType;
import org.testng.Assert;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import java.util.*;
import java.util.stream.Collectors;
import java.util.AbstractMap.SimpleEntry;

/**
 * Utility helper for example tests and resource management.
 */
public class ExampleAssist {

    /**
     * Gets the path to a resource folder with a trailing separator.
     */
    public static String getResourceFolderPath(String... pathParts) {
        Path basePath = Paths.get("src", "test", "resources");

        for (String part : pathParts)
        {
            basePath = basePath.resolve(part);
        }

        return basePath + File.separator;
    }

    /**
     * Gets or creates a resource folder path and prints created structure.
     */
    public static String getOrCreateResourceFolderPath(String... pathParts) {
        Path basePath;

        // Detect if the first part already includes src/test/resources to avoid duplication
        if (pathParts.length > 0 && pathParts[0].contains("src" + File.separator + "test" + File.separator + "resources"))
        {
            basePath = Paths.get(pathParts[0]);
            for (int i = 1; i < pathParts.length; i++)
            {
                basePath = basePath.resolve(pathParts[i]);
            }
        }
        else
        {
            basePath = Paths.get("src", "test", "resources");
            for (String part : pathParts)
            {
                basePath = basePath.resolve(part);
            }
        }

        try
        {
            Files.createDirectories(basePath);

            System.out.println("[ExampleAssist] Created or verified folder structure:");
            Path current = Paths.get("src", "test", "resources");
            for (String part : pathParts)
            {
                current = current.resolve(part);
                if (Files.exists(current))
                {
                    System.out.println("  - " + current.toAbsolutePath());
                }
            }

            System.out.println("[ExampleAssist] Final resource path: " + basePath.toAbsolutePath());

        }
        catch (FileAlreadyExistsException e)
        {
            throw new IllegalStateException("Path exists but is not a directory: " + basePath, e);
        }
        catch (IOException e)
        {
            throw new UncheckedIOException("Failed to create directory: " + basePath, e);
        }

        return basePath + File.separator;
    }

    /**
     * Gets path to a resource file or nested folders.
     */
    public static String getResourceFilePath(String... pathParts) {
        Path basePath = Paths.get("src", "test", "resources");

        for (String part : pathParts)
        {
            basePath = basePath.resolve(part);
        }

        return basePath.toString();
    }

    /**
     * Gets input stream for reading resource files from classpath.
     */
    public static InputStream getResourceAsStream(String resourcePath) {
        InputStream stream = ExampleAssist.class.getClassLoader()
                .getResourceAsStream(resourcePath);

        if (stream == null)
        {
            throw new IllegalArgumentException("Resource not found: " + resourcePath);
        }

        return stream;
    }

    /**
     * Checks if the given image exists, or creates it using the provided generator.
     */
    public static void checkOrCreateImage(String imagesFolder, String fileName, ImageSupplier generator) throws IOException {
        Path path = Paths.get(imagesFolder, fileName);
        Files.createDirectories(path.getParent());

        if (!Files.exists(path))
        {
            String fullPath = path.toString();
            generator.supply(fullPath);
            Assert.assertTrue(Files.exists(path), "Failed to create fixture: " + path);
            Assert.assertTrue(Files.size(path) > 0, "Fixture is empty: " + path);
        }
    }

    public static void checkOrCreateImage(String imagesFolder, String fileName, BarcodeGenerator barcodeGenerator) throws IOException {
        Path path = Paths.get(imagesFolder, fileName);
        Files.createDirectories(path.getParent());

        if (!Files.exists(path))
        {
            barcodeGenerator.save(path.toString());
            Assert.assertTrue(Files.exists(path), "Failed to create fixture: " + path);
            Assert.assertTrue(Files.size(path) > 0, "Fixture is empty: " + path);
        }
    }

    public static void assertRecognized(BarCodeReader reader, String tag, int minCount, BaseDecodeType expectedType) throws Exception {

        // Auto-detect test name if tag not provided
        if (tag == null || tag.isEmpty())
        {
            tag = Thread.currentThread().getStackTrace()[2].getMethodName();
        }

        BarCodeResult[] results = reader.readBarCodes();

        System.out.println("=== [" + tag + "] ===");
        for (BarCodeResult result : results)
        {
            System.out.println(" Code Type: " + result.getCodeTypeName() + " - Code Text: " + result.getCodeText());
        }

        Assert.assertTrue(results.length >= minCount, "Expected at least " + minCount + " result(s) in test '" + tag + "', but got " + results.length);

        if (expectedType != null && results.length > 0)
        {
            boolean hasExpectedType = false;
            for (BarCodeResult result : results)
            {
                if (result.getCodeType().equals(expectedType))
                {
                    hasExpectedType = true;
                    break;
                }
            }

            Assert.assertTrue(
                    hasExpectedType,
                    "Expected to find type " + expectedType.toString() + " in test '" + tag + "'"
            );
        }
    }

    public static void assertRecognized(BarCodeReader reader, String tag, int expectedCount, BaseDecodeType expectedType, String expectedCodeText) throws Exception {
        if (tag == null || tag.isEmpty())
        {
            tag = Thread.currentThread().getStackTrace()[2].getMethodName();
        }

        BarCodeResult[] results = reader.readBarCodes();

        System.out.println("=== [" + tag + "] ===");
        for (BarCodeResult r : results)
        {
            System.out.println(" Code Type: " + r.getCodeTypeName() + " - Code Text: " + r.getCodeText());
        }

        Assert.assertEquals(results.length, expectedCount,
                "Expected " + expectedCount + " result(s) in '" + tag + "', but got " + results.length);

        if (results.length == 0)
        {
            return;
        }

        java.util.List<String> foundTypes = new java.util.ArrayList<>();
        java.util.List<String> foundTexts = new java.util.ArrayList<>();
        boolean hasExpectedType = false;
        boolean hasExpectedText = false;
        boolean hasSameResultPair = false;

        for (BarCodeResult barCodeResult : results)
        {
            foundTypes.add(barCodeResult.getCodeTypeName());
            foundTexts.add(barCodeResult.getCodeText());

            if (barCodeResult.getCodeType().equals(expectedType))
            {
                hasExpectedType = true;
            }
            if (java.util.Objects.equals(barCodeResult.getCodeText(), expectedCodeText))
            {
                hasExpectedText = true;
            }
            if (barCodeResult.getCodeType().equals(expectedType)
                    && java.util.Objects.equals(barCodeResult.getCodeText(), expectedCodeText))
            {
                hasSameResultPair = true;
            }
        }

        Assert.assertTrue(hasExpectedType,
                "Expected type " + expectedType + " in '" + tag + "'. Found: " + foundTypes);
        Assert.assertTrue(hasExpectedText,
                "Expected text \"" + expectedCodeText + "\" in '" + tag + "'. Found: " + foundTexts);
        Assert.assertTrue(hasSameResultPair,
                "Found type and text, but not in the same result. Expected pair: (" +
                        expectedType + ", \"" + expectedCodeText + "\")");
    }


    public static void assertRecognizedSilent(BarCodeReader reader, int minCount, BaseDecodeType expected) throws Exception {
        BarCodeResult[] results = reader.readBarCodes();
        Assert.assertTrue(results.length >= minCount);
        if (expected != null)
        {
            boolean ok = java.util.Arrays.stream(results).anyMatch(r -> r.getCodeType().equals(expected));
            Assert.assertTrue(ok);
        }
    }

    public static void assertNotRecognized(BarCodeReader reader, String tag) throws Exception {
        // Auto-detect test name if tag not provided
        if (tag == null || tag.isEmpty())
        {
            tag = Thread.currentThread().getStackTrace()[2].getMethodName();
        }

        BarCodeResult[] results = reader.readBarCodes();

        System.out.println("=== [" + tag + "] ===");
        if (results.length == 0)
        {
            System.out.println(" No barcodes recognized.");
        }
        else
        {
            for (BarCodeResult result : results)
            {
                System.out.println(" Code Type: " + result.getCodeTypeName()
                        + " - Code Text: " + result.getCodeText());
            }
        }

        // Expect exactly zero results
        Assert.assertTrue(
                results.length == 0,
                "Expected no barcodes in test '" + tag + "', but got " + results.length
        );
    }


    public static String generateTestBarcode(String data, String imagesFolder, String fileName, BaseEncodeType type) throws IOException {
        BarcodeGenerator gen = new BarcodeGenerator(type, data);
        gen.getParameters().setResolution(300f);
        gen.getParameters().getBarcode().getXDimension().setMillimeters(0.3f);
        gen.getParameters().getBarcode().getBarHeight().setMillimeters(25f);

        String fullPath = imagesFolder + File.separator + fileName;
        gen.save(fullPath, BarCodeImageFormat.PNG);
        return fullPath;
    }

    public static void generateAndRead(String folder, String fileName, String codeText, BaseEncodeType encodeType, BaseDecodeType decodeType) throws Exception {
        BarcodeGenerator g = new BarcodeGenerator(encodeType, codeText);
        String path = folder + "/" + fileName;
        g.save(path, BarCodeImageFormat.PNG);
        BarCodeReader reader = new BarCodeReader(path, decodeType);
        reader.setQualitySettings(QualitySettings.getHighPerformance());
        BarCodeResult[] results = reader.readBarCodes();
        for (BarCodeResult result : results)
        {
            System.out.println(" Code Type: " + result.getCodeTypeName() + " - Code Text: " + result.getCodeText());
        }
    }


/**
 @Test public void read_QR_HighPerformance1() throws Exception
 {
 // Purpose: Recognize a clean QR using High Performance preset.
 String fileName = "qr_hp123.png";
 BarcodeGenerator g = new BarcodeGenerator(EncodeTypes.QR, "HP-QR");
 String path = FOLDER + "/" +  fileName;
 g.save(path, BarCodeImageFormat.PNG);
 BarCodeReader reader = new BarCodeReader(ExampleAssist.pathCombine(FOLDER, fileName), DecodeType.QR);
 BarCodeResult[] results = reader.readBarCodes();
 for (BarCodeResult result : results)
 {
 System.out.println(" Code Type: " + result.getCodeTypeName() + " - Code Text: " + result.getCodeText());
 }
 }
 **/
    /**
     * Private constructor to prevent instantiation.
     */
    private ExampleAssist() {
        throw new UnsupportedOperationException("Utility class");
    }

    public static String pathCombine(String folder, String image) {
        return folder + "/" + image;
    }

    public static String getCurrentMethodName() {
        StackTraceElement[] st = Thread.currentThread().getStackTrace();
        for (int i = 0; i < st.length; i++)
        {
            if (ExampleAssist.class.getName().equals(st[i].getClassName()) &&
                    "getCurrentMethodName".equals(st[i].getMethodName()))
            {
                return (i + 1 < st.length) ? st[i + 1].getMethodName() : "unknown";
            }
        }
        return "unknown";
    }

    // Returns the first frame outside ExampleAssist (robust against wrappers)
    public static String currentMethodName() {
        String helperClass = ExampleAssist.class.getName();
        return StackWalker.getInstance(StackWalker.Option.RETAIN_CLASS_REFERENCE)
                .walk(stream -> stream
                        .dropWhile(f -> f.getClassName().equals(helperClass))      // skip helper
                        .filter(f -> !f.getClassName().startsWith("org.testng."))  // skip TestNG internals
                        .findFirst()
                        .map(StackWalker.StackFrame::getMethodName)
                        .orElse("unknown"));
    }

    /**
     * Adds zero-mean Gaussian noise to an image and writes the result.
     *
     * @param inputFullPath  full path to source image (PNG/JPG)
     * @param outputFullPath full path to output image (PNG)
     * @param stdDev         standard deviation of noise (e.g., 8..16). Higher → stronger noise.
     */
    public static void addGaussianNoise(String inputFullPath, String outputFullPath, double stdDev) throws IOException {
        BufferedImage src = ImageIO.read(new File(inputFullPath));
        if (src == null)
        {
            throw new IOException("Cannot read image: " + inputFullPath);
        }

        BufferedImage dst = new BufferedImage(src.getWidth(), src.getHeight(), BufferedImage.TYPE_INT_ARGB);
        Random rnd = new Random(12345L); // deterministic for tests

        for (int y = 0; y < src.getHeight(); y++)
        {
            for (int x = 0; x < src.getWidth(); x++)
            {
                int argb = src.getRGB(x, y);
                int a = (argb >>> 24) & 0xFF;
                int r = (argb >>> 16) & 0xFF;
                int g = (argb >>> 8) & 0xFF;
                int b = (argb) & 0xFF;

                // Gaussian noise per channel
                int nr = clampToByte(r + (int) Math.round(rnd.nextGaussian() * stdDev));
                int ng = clampToByte(g + (int) Math.round(rnd.nextGaussian() * stdDev));
                int nb = clampToByte(b + (int) Math.round(rnd.nextGaussian() * stdDev));

                dst.setRGB(x, y, (a << 24) | (nr << 16) | (ng << 8) | nb);
            }
        }
        ensureParentDirs(outputFullPath);
        ImageIO.write(dst, "png", new File(outputFullPath));
    }

    /**
     * Applies a small Gaussian-like blur via separable convolution and writes the result.
     *
     * @param inputFullPath  full path to source image
     * @param outputFullPath full path to output image (PNG)
     */
    public static void blur(String inputFullPath, String outputFullPath, float sigma) throws IOException {
        // Separable Gaussian blur: one horizontal pass followed by one vertical pass.
        // Use sigma ≈ 0.9–1.2 to simulate a degraded but still recognizable image.

        // Clamp sigma to a sensible range: too small -> no visible effect; too large -> overly "soapy" blur.
        if (sigma < 0.6f)
        {
            sigma = 0.6f;
        }
        if (sigma > 2.0f)
        {
            sigma = 2.0f;
        }

        BufferedImage src = ImageIO.read(new File(inputFullPath));
        if (src == null)
        {
            throw new IOException("Cannot read image: " + inputFullPath);
        }

        // Kernel size from the 3σ rule: cover ±3σ and keep it odd (≈ 6σ + 1). Result is typically 3..13 here.
        int size = Math.max(3, 2 * (int) Math.ceil(3 * sigma) + 1);
        float[] k1d = gaussianKernel1D(size, sigma); // normalized, symmetric 1D kernel

        Kernel hKernel = new Kernel(size, 1, k1d);
        Kernel vKernel = new Kernel(1, size, k1d);

        // Use EDGE_NO_OP to avoid padding artifacts; borders will remain less blurred.
        ConvolveOp hOp = new ConvolveOp(hKernel, ConvolveOp.EDGE_NO_OP, null);
        ConvolveOp vOp = new ConvolveOp(vKernel, ConvolveOp.EDGE_NO_OP, null);

        BufferedImage tmp = hOp.filter(src, null);
        BufferedImage dst = vOp.filter(tmp, null); // IMPORTANT: exactly one H pass + one V pass (no repeated passes)

        ensureParentDirs(outputFullPath);
        ImageIO.write(dst, "png", new File(outputFullPath));
    }


    private static float[] gaussianKernel1D(int size, float sigma) {
        float[] k = new float[size];
        int r = size / 2;
        float twoSigma2 = 2.0f * sigma * sigma;
        float sum = 0f;
        for (int i = -r, j = 0; i <= r; i++, j++)
        {
            float v = (float) Math.exp(-(i * i) / twoSigma2);
            k[j] = v;
            sum += v;
        }
        // normalize
        for (int i = 0; i < size; i++)
        {
            k[i] /= sum;
        }
        return k;
    }

// ---- helpers (put them as private static inside ExampleAssist) ----

    private static int clampToByte(int v) {
        return (v < 0) ? 0 : (v > 255 ? 255 : v);
    }

    private static Kernel gaussian3x3() {
        // Simple normalized 3x3 Gaussian approximation
        float[] m = {
                1f / 16f, 2f / 16f, 1f / 16f,
                2f / 16f, 4f / 16f, 2f / 16f,
                1f / 16f, 2f / 16f, 1f / 16f
        };
        return new Kernel(3, 3, m);
    }

    private static Kernel gaussian5x5() {
        // Simple normalized 5x5 Gaussian approximation
        float[] row = {1, 4, 6, 4, 1};
        float sum = 0;
        float[] m = new float[25];
        int k = 0;
        for (float v1 : row)
        {
            for (float v2 : row)
            {
                float v = v1 * v2;
                m[k++] = v;
                sum += v;
            }
        }
        for (int i = 0; i < m.length; i++)
        {
            m[i] /= sum;
        }
        return new Kernel(5, 5, m);
    }

    /**
     * Ensure that parent directories exist for the given file path.
     * Safe to call for already-existing directories.
     */
    private static void ensureParentDirs(String fullPath) throws IOException {
        File f = new File(fullPath);
        File p = f.getAbsoluteFile().getParentFile();
        if (p != null && !p.exists() && !p.mkdirs())
        {
            throw new IOException("Cannot create directories for: " + fullPath);
        }
    }


    public static void upscaleBicubic(String inputFullPath, String outputFullPath, double scale) throws IOException {
        BufferedImage src = ImageIO.read(new File(inputFullPath));
        if (src == null)
        {
            throw new IOException("Cannot read image: " + inputFullPath);
        }

        int w = (int) Math.round(src.getWidth() * scale);
        int h = (int) Math.round(src.getHeight() * scale);
        BufferedImage dst = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g = dst.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
        g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.drawImage(src, 0, 0, w, h, null);
        g.dispose();

        ensureParentDirs(outputFullPath);
        ImageIO.write(dst, "png", new File(outputFullPath));
    }

    /**
     * Inverts pixel colors (RGB) of the source image and writes to outPath.
     * Alpha channel is preserved.
     */
    public static void invertColors(String srcPath, String outPath) {
        try
        {
            BufferedImage src = ImageIO.read(new File(srcPath));
            if (src == null)
            {
                throw new IOException("Unsupported image format: " + srcPath);
            }

            int w = src.getWidth();
            int h = src.getHeight();
            BufferedImage dst = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);

            for (int y = 0; y < h; y++)
            {
                for (int x = 0; x < w; x++)
                {
                    int argb = src.getRGB(x, y);
                    int a = (argb >> 24) & 0xFF;
                    int r = (argb >> 16) & 0xFF;
                    int g = (argb >> 8) & 0xFF;
                    int b = argb & 0xFF;
                    // invert RGB, keep alpha
                    int inv = (a << 24) | ((255 - r) << 16) | ((255 - g) << 8) | (255 - b);
                    dst.setRGB(x, y, inv);
                }
            }

            String fmt = outPath.toLowerCase().endsWith(".jpg") || outPath.toLowerCase().endsWith(".jpeg") ? "jpg" : "png";
            // Ensure parent dir exists
            File outFile = new File(outPath);
            File parent = outFile.getParentFile();
            if (parent != null && !parent.exists())
            {
                parent.mkdirs();
            }
            ImageIO.write(dst, fmt, outFile);
        }
        catch (IOException e)
        {
            throw new RuntimeException("invertColors failed: " + e.getMessage(), e);
        }
    }

    /**
     * Downscale an image using nearest-neighbor interpolation to a target width
     * while preserving aspect ratio. Output is always written as PNG.
     * <p>
     * Why nearest:
     * - It preserves "pixelation" and avoids blur, which better simulates tiny barcodes
     * captured with limited sensor resolution (important for low-res tests).
     *
     * @param inPath        absolute or relative path to the input image
     * @param outPath       target path for the output image (PNG will be written)
     * @param targetWidthPx desired width in pixels (>= 1)
     * @throws IOException if read/write fails
     */
    public static void downscaleNearest(String inPath, String outPath, int targetWidthPx) throws IOException {
        if (targetWidthPx < 1)
        {
            throw new IllegalArgumentException("targetWidthPx must be >= 1");
        }

        BufferedImage src = ImageIO.read(new File(inPath));
        if (src == null)
        {
            throw new IOException("Cannot read image: " + inPath);
        }

        int srcW = Math.max(1, src.getWidth());
        int srcH = Math.max(1, src.getHeight());

        double scale = (double) targetWidthPx / (double) srcW;
        int targetHeightPx = Math.max(1, (int) Math.round(srcH * scale));

        BufferedImage dst = new BufferedImage(targetWidthPx, targetHeightPx, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g = dst.createGraphics();
        try
        {
            // Nearest-neighbor to keep sharp, blocky pixels (no smoothing).
            g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR);
            g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);
            g.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_SPEED);
            g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_SPEED);

            g.drawImage(src, 0, 0, targetWidthPx, targetHeightPx, null);
        }
        finally
        {
            g.dispose();
        }

        ensureParentDirs(outPath);
        ImageIO.write(dst, "png", new File(outPath));
    }

    /**
     * Downscale an image using nearest-neighbor and then apply Otsu binarization
     * to keep barcode edges crisp at low resolutions. Output is written as PNG.
     * <p>
     * Why this helps:
     * - Nearest-neighbor preserves module boundaries compared to smoothing resamplers.
     * - Otsu threshold restores hard black/white edges after resampling and reduces gray bleeding,
     * which improves decode reliability on tiny barcodes.
     *
     * @param inPath        input image path
     * @param outPath       output image path (PNG)
     * @param targetWidthPx target width in pixels (>= 1)
     */
    public static void downscaleNearestCrisp(String inPath, String outPath, int targetWidthPx) throws IOException {
        if (targetWidthPx < 1)
        {
            throw new IllegalArgumentException("targetWidthPx must be >= 1");
        }

        BufferedImage src = ImageIO.read(new File(inPath));
        if (src == null)
        {
            throw new IOException("Cannot read image: " + inPath);
        }

        // 1) Nearest-neighbor downscale with aspect ratio preserved.
        int srcW = Math.max(1, src.getWidth());
        int srcH = Math.max(1, src.getHeight());
        double scale = (double) targetWidthPx / (double) srcW;
        int targetHeightPx = Math.max(1, (int) Math.round(srcH * scale));

        BufferedImage scaled = new BufferedImage(targetWidthPx, targetHeightPx, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = scaled.createGraphics();
        try
        {
            g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR);
            g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);
            g.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_SPEED);
            g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_SPEED);
            g.drawImage(src, 0, 0, targetWidthPx, targetHeightPx, null);
        }
        finally
        {
            g.dispose();
        }

        // 2) Convert to grayscale (luminance).
        BufferedImage gray = new BufferedImage(targetWidthPx, targetHeightPx, BufferedImage.TYPE_BYTE_GRAY);
        Graphics2D gg = gray.createGraphics();
        try
        {
            gg.drawImage(scaled, 0, 0, null);
        }
        finally
        {
            gg.dispose();
        }

        // 3) Otsu threshold -> black/white image (crisp modules).
        int[] hist = new int[256];
        for (int y = 0; y < gray.getHeight(); y++)
        {
            for (int x = 0; x < gray.getWidth(); x++)
            {
                int v = gray.getRaster().getSample(x, y, 0);
                hist[v]++;
            }
        }

        int total = gray.getWidth() * gray.getHeight();
        long sum = 0;
        for (int t = 0; t < 256; t++)
        {
            sum += (long) t * hist[t];
        }

        long sumB = 0;
        int wB = 0;
        double varMax = -1.0;
        int threshold = 127;
        for (int t = 0; t < 256; t++)
        {
            wB += hist[t];
            if (wB == 0)
            {
                continue;
            }
            int wF = total - wB;
            if (wF == 0)
            {
                break;
            }

            sumB += (long) t * hist[t];

            double mB = (double) sumB / wB;
            double mF = (double) (sum - sumB) / wF;
            double varBetween = (double) wB * (double) wF * (mB - mF) * (mB - mF);

            if (varBetween > varMax)
            {
                varMax = varBetween;
                threshold = t;
            }
        }

        BufferedImage bw = new BufferedImage(targetWidthPx, targetHeightPx, BufferedImage.TYPE_BYTE_BINARY);
        for (int y = 0; y < gray.getHeight(); y++)
        {
            for (int x = 0; x < gray.getWidth(); x++)
            {
                int v = gray.getRaster().getSample(x, y, 0);
                int rgb = (v > threshold ? 0xFFFFFFFF : 0xFF000000);
                bw.setRGB(x, y, rgb);
            }
        }

        ensureParentDirs(outPath);
        ImageIO.write(bw, "png", new File(outPath));
    }

    /**
     * Render a barcode directly at the target pixel size (no resampling).
     * Ensures white background, black bars, and explicit quiet zones in pixels.
     *
     * @param type     symbology (e.g., EncodeTypes.CODE_128)
     * @param text     payload
     * @param widthPx  total image width in pixels
     * @param heightPx total image height in pixels
     * @param xDimPx   X-dimension in pixels (module width); use ~2.0f for ~150px wide, ~1.0–1.2f for ~80px
     * @param quietPx  quiet zone size (left/right) in pixels
     * @param outPath  output PNG path
     */
    public static void renderBarcodeFixedSizePNG(BaseEncodeType type, String text,
                                                 int widthPx, int heightPx,
                                                 float xDimPx, int quietPx,
                                                 String outPath) throws IOException {
        if (widthPx < 20 || heightPx < 20)
        {
            throw new IllegalArgumentException("Image too small for rendering: " + widthPx + "x" + heightPx);
        }
        if (xDimPx < 0.5f)
        {
            xDimPx = 0.5f;
        }

        // 1) Render barcode onto an ARGB canvas with exact pixel size.
        // Aspose.BarCode can render by pixels using parameters..
        BarcodeGenerator gen = new BarcodeGenerator(type, text);

        // Colors / background
        gen.getParameters().getBarcode().setBarColor(java.awt.Color.BLACK);
        gen.getParameters().setBackColor(java.awt.Color.WHITE);

        // Quiet zones (left/right/top/bottom) in pixels
        gen.getParameters().getBarcode().getPadding().getLeft().setPixels(quietPx);
        gen.getParameters().getBarcode().getPadding().getRight().setPixels(quietPx);
        gen.getParameters().getBarcode().getPadding().getTop().setPixels(quietPx / 2.0f);
        gen.getParameters().getBarcode().getPadding().getBottom().setPixels(quietPx / 2.0f);

        // X-dimension in pixels
        gen.getParameters().getBarcode().getXDimension().setPixels(xDimPx);

        // Fix overall image size in pixels (если у вашей версии свойства называются иначе — подставьте эквиваленты)
        gen.getParameters().getImageWidth().setPixels(widthPx);
        gen.getParameters().getImageHeight().setPixels(heightPx);

        // 2) save to PNG
        ensureParentDirs(outPath);
        gen.save(outPath, BarCodeImageFormat.PNG);

        // 3) Safety: Let's make sure the background is actually white (in case of transparency in the engine version)
        BufferedImage img = ImageIO.read(new File(outPath));
        if (img.getType() != BufferedImage.TYPE_INT_RGB)
        {
            BufferedImage rgb = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_RGB);
            Graphics2D g = rgb.createGraphics();
            try
            {
                g.setColor(Color.WHITE);
                g.fillRect(0, 0, rgb.getWidth(), rgb.getHeight());
                g.drawImage(img, 0, 0, null);
            }
            finally
            {
                g.dispose();
            }
            ImageIO.write(rgb, "png", new File(outPath));
        }
    }

    /**
     * Returns true if file exists.
     */
    public static boolean fileExists(String fullPath) {
        return fullPath != null && Files.exists(Paths.get(fullPath));
    }

    /**
     * Simple filename extraction (no directories).
     */
    public static String getFileName(String fullPath) {
        if (fullPath == null)
        {
            return "";
        }
        Path p = Paths.get(fullPath);
        Path name = p.getFileName();
        return name == null ? "" : name.toString();
    }

    /**
     * Logs an informational message to stdout.
     */
    public static void logInfo(String msg) {
        System.out.println("[INFO] " + msg);
    }

    /**
     * Logs a warning message to stdout.
     */
    public static void logWarn(String msg) {
        System.out.println("[WARN] " + msg);
    }

    /**
     * Lists files in a directory by a glob like "*.png".
     * Returns absolute paths. If folder does not exist, returns empty array.
     */
    public static String[] listFilesByGlob(String folder, String globPattern) {
        java.util.List<String> out = new ArrayList<>();
        if (folder == null || globPattern == null)
        {
            return new String[0];
        }

        Path dir = Paths.get(folder);
        if (!Files.isDirectory(dir))
        {
            return new String[0];
        }

        try (DirectoryStream<Path> stream = Files.newDirectoryStream(dir, globPattern))
        {
            for (Path p : stream)
            {
                if (Files.isRegularFile(p))
                {
                    out.add(p.toAbsolutePath().toString());
                }
            }
        }
        catch (IOException e)
        {
            logWarn("listFilesByGlob failed for " + folder + " pattern " + globPattern + ": " + e.getMessage());
        }
        return out.toArray(new String[0]);
    }

    /**
     * Asserts there is at least one result after recognition.
     * Reads barcodes internally if needed.
     */
    public static void assertHasAnyResult(BarCodeReader reader, String labelForError) {
        reader.readBarCodes();
        Assert.assertTrue(reader.getFoundCount() > 0,
                "Expected at least 1 result for: " + labelForError);
    }

    /**
     * Asserts count==expected and text of the first result == expectedText.
     */
    public static void assertRecognizedWithText(BarCodeReader reader, String labelForError, int expectedCount, String expectedText) {
        BarCodeResult[] results = reader.readBarCodes();
        Assert.assertEquals(results.length, expectedCount, "Unexpected count for: " + labelForError);
        Assert.assertEquals(results[0].getCodeText(), expectedText, "Unexpected text for: " + labelForError);
    }

    public static int getCpuCount() {
        try
        {
            return Runtime.getRuntime().availableProcessors();
        }
        catch (Throwable t)
        {
            return 1;
        }
    }

    public static boolean hasDecodeType(BarCodeResult[] results, BaseDecodeType type) {
        for (BarCodeResult codeResult : results)
        {
            if (codeResult.getCodeType().equals(type))
            {
                return true;
            }
        }
        return false;
    }

    public static void assertAngleClose(double actual, double expected, double tol, String msg) {
        Assert.assertTrue(Math.abs(actual - expected) <= tol,
                msg + " (actual=" + actual + ", expected=" + expected + "±" + tol + ")");
    }

    public static boolean containsPoint(Point[] arr, Point p) {
        for (Point a : arr)
        {
            if (a.equals(p))
            {
                return true;
            }
        }
        return false;
    }

    /**
     * Rotates an image around its center and expands the canvas to fit the result.
     * Uses NEAREST-NEIGHBOR interpolation and NO anti-aliasing to keep barcode modules
     * crisp (no blur). Prefer this for generating test fixtures intended for recognition,
     * especially 1D bars and small 2D modules.
     * <p>
     * Pros: preserves hard edges and module boundaries; avoids “soapy” blur.
     * Cons: visually more jagged on diagonals (which is fine for tests).
     *
     * @param src     source image
     * @param degrees rotation angle in degrees (clockwise)
     * @return rotated image with a white background; canvas is expanded to fit
     */
    public static BufferedImage rotateCenterCrispNN(BufferedImage src, double degrees) {
        double radians = Math.toRadians(degrees);
        double sin = Math.abs(Math.sin(radians));
        double cos = Math.abs(Math.cos(radians));
        int w = src.getWidth();
        int h = src.getHeight();
        int newW = (int) Math.floor(w * cos + h * sin);
        int newH = (int) Math.floor(h * cos + w * sin);

        BufferedImage dst = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2 = dst.createGraphics();
        try
        {
            // Preserve barcode sharpness: no AA, nearest-neighbor, fast rendering.
            g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR);
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);
            g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_SPEED);
            g2.setColor(Color.WHITE);
            g2.fillRect(0, 0, newW, newH);

            AffineTransform at = new AffineTransform();
            at.translate(newW / 2.0, newH / 2.0);
            at.rotate(radians);
            at.translate(-w / 2.0, -h / 2.0);

            g2.drawImage(src, at, null);
        }
        finally
        {
            g2.dispose();
        }
        return dst;
    }

    /**
     * Rotates an image around its center and expands the canvas to fit the result.
     * Uses BILINEAR interpolation and ANTI-ALIASING for visually smooth output.
     * Suitable for demos, UI previews, or overlays where aesthetics matter more
     * than pixel-perfect barcode modules.
     * <p>
     * Pros: smoother diagonals and text; nicer visuals.
     * Cons: can blur barcode edges, which may slightly reduce recognition robustness.
     *
     * @param src     source image
     * @param degrees rotation angle in degrees (clockwise)
     * @return rotated image with a white background; canvas is expanded to fit
     */
    public static BufferedImage rotateCenterSmoothBilinear(BufferedImage src, double degrees) {
        double radians = Math.toRadians(degrees);

        double sin = Math.abs(Math.sin(radians));
        double cos = Math.abs(Math.cos(radians));
        int w = src.getWidth();
        int h = src.getHeight();
        int newW = (int) Math.floor(w * cos + h * sin);
        int newH = (int) Math.floor(h * cos + w * sin);

        BufferedImage dst = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2 = dst.createGraphics();
        try
        {
            // Smooth, visual-friendly rotation (may soften barcode edges).
            g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
            g2.setColor(Color.WHITE);
            g2.fillRect(0, 0, newW, newH);

            AffineTransform at = new AffineTransform();
            at.translate(newW / 2.0, newH / 2.0);
            at.rotate(radians);
            at.translate(-w / 2.0, -h / 2.0);

            g2.drawImage(src, at, null);
        }
        finally
        {
            g2.dispose();
        }
        return dst;
    }

    // Prints recognition metadata for a single result, using only actual getters present in SDK.
    public static void printResultMetadata(BarCodeResult result, String prefix) {
        String p = (prefix == null || prefix.isEmpty()) ? "" : ("[" + prefix + "] ");

        // Generic fields
        System.out.println(p + "Type=" + result.getCodeTypeName() + " Text=" + result.getCodeText());
        System.out.println(p + "Confidence=" + result.getConfidence());
        BarCodeRegionParameters region = result.getRegion();
        if (region != null)
        {
            System.out.println(p + "Rect=" + region.getRectangle());
            Quadrangle quadrangle = region.getQuadrangle();
            if (quadrangle != null)
            {
                System.out.println(p + "Quad LT=" + quadrangle.getLeftTop()
                        + " RT=" + quadrangle.getRightTop()
                        + " RB=" + quadrangle.getRightBottom()
                        + " LB=" + quadrangle.getLeftBottom());
            }
        }

        // Extended (QR)
        BarCodeExtendedParameters extended = result.getExtended();
        if (extended != null)
        {
            QRExtendedParameters qrExtendedParameters = extended.getQR();
            if (qrExtendedParameters != null)
            {
                System.out.println(p + "QR: Version=" + qrExtendedParameters.getQRVersion()
                        + " MicroQR=" + qrExtendedParameters.getMicroQRVersion()
                        + " RectMicroQR=" + qrExtendedParameters.getRectMicroQRVersion()
                        + " ErrorLevel=" + qrExtendedParameters.getQRErrorLevel()
                        + " SA.Quantity=" + qrExtendedParameters.getQRStructuredAppendModeBarCodesQuantity()
                        + " SA.Index=" + qrExtendedParameters.getQRStructuredAppendModeBarCodeIndex()
                        + " SA.Parity=" + qrExtendedParameters.getQRStructuredAppendModeParityData());
            }

            // Extended (DataMatrix)
            DataMatrixExtendedParameters dataMatrixExtendedParameters = extended.getDataMatrix();
            if (dataMatrixExtendedParameters != null)
            {
                System.out.println(p + "DataMatrix: SA.BarcodeId=" + dataMatrixExtendedParameters.getStructuredAppendBarcodeId()
                        + " SA.Count=" + dataMatrixExtendedParameters.getStructuredAppendBarcodesCount()
                        + " SA.FileId=" + dataMatrixExtendedParameters.getStructuredAppendFileId()
                        + " ReaderProgramming=" + dataMatrixExtendedParameters.isReaderProgramming());
            }

            // Extended (Pdf417 / MacroPdf417)
            Pdf417ExtendedParameters pdf417ExtendedParameters = extended.getPdf417();
            if (pdf417ExtendedParameters != null)
            {
                System.out.println(p + "PDF417: FileId=\"" + pdf417ExtendedParameters.getMacroPdf417FileID()
                        + "\" SegmentId=" + pdf417ExtendedParameters.getMacroPdf417SegmentID()
                        + " SegmentsCount=" + pdf417ExtendedParameters.getMacroPdf417SegmentsCount()
                        + " FileName=" + pdf417ExtendedParameters.getMacroPdf417FileName()
                        + " FileSize=" + pdf417ExtendedParameters.getMacroPdf417FileSize()
                        + " Sender=" + pdf417ExtendedParameters.getMacroPdf417Sender()
                        + " Addressee=" + pdf417ExtendedParameters.getMacroPdf417Addressee()
                        + " TimeStamp=" + pdf417ExtendedParameters.getMacroPdf417TimeStamp()
                        + " Checksum=" + pdf417ExtendedParameters.getMacroPdf417Checksum()
                        + " Terminator=" + pdf417ExtendedParameters.getMacroPdf417Terminator()
                        + " IsReaderInit=" + pdf417ExtendedParameters.isReaderInitialization()
                        + " IsLinked=" + pdf417ExtendedParameters.isLinked()
                        + " IsCode128Emu=" + pdf417ExtendedParameters.isCode128Emulation());
            }
        }
    }

    /**
     * Factory for text-based expectation.
     */
    public static Expected expected(BaseDecodeType type, String expectedText) {
        Objects.requireNonNull(expectedText, "expectedText");
        return new Expected(type, CompareMode.TEXT, expectedText, null);
    }

    /**
     * Factory for byte-based expectation.
     */
    public static Expected expected(BaseDecodeType type, byte[] expectedBytes) {
        Objects.requireNonNull(expectedBytes, "expectedBytes");
        return new Expected(type, CompareMode.BYTES, null, expectedBytes.clone());
    }

    public static Expected expectedPrefix(BaseDecodeType type, String requiredPrefix) {
        Objects.requireNonNull(requiredPrefix, "requiredPrefix");
        return new Expected(type, CompareMode.PREFIX, requiredPrefix, null);
    }

    /**
     * Verifies that an image contains exactly expectedCount barcodes and
     * at least the expected (type,text) pairs. Also softly checks Confidence ∈ [0..100].
     * Decode types are inferred from the expected pairs.
     */
    /**
     * Assert that an image contains exactly {@code expectedCount} barcodes and that each {@link Expected}
     * is matched by a decoded barcode with the same type and either the same text (TEXT mode)
     * or identical bytes (BYTES mode). Order-independent; каждый найденный код используется один раз.
     * <p>
     * Notes:
     * - No try-with-resources and no explicit close/dispose on BarCodeReader (project policy).
     */
    public static void assertImageHasBarcodes(String imagePath,
                                              int expectedCount,
                                              List<Expected> expectedList) throws Exception {

        BaseDecodeType[] hints = (expectedList != null && !expectedList.isEmpty())
                ? expectedList.stream().map(e -> e.type).distinct().toArray(BaseDecodeType[]::new)
                : new BaseDecodeType[]{DecodeType.ALL_SUPPORTED_TYPES};

        BarCodeReader reader = new BarCodeReader(imagePath, hints);
        BarCodeResult[] results = reader.readBarCodes();

        // Debug print
        System.out.println("[assertImageHasBarcodes] file=" + imagePath);
        for (BarCodeResult r : results)
        {
            System.out.println("  -> " + r.getCodeTypeName()
                    + " | text=\"" + r.getCodeText() + "\""
                    + " | bytes=0x" + hexPreview(r.getCodeBytes(), 32)
                    + " | confidence=" + r.getConfidence());
        }

        // 2) Exact count
        Assert.assertEquals(results.length, expectedCount,
                "Unexpected number of barcodes in: " + imagePath);

        // 3) Confidence bounds [0..100]
        for (BarCodeResult r : results)
        {
            int conf = r.getConfidence();
            Assert.assertTrue(conf >= 0 && conf <= 100,
                    "Confidence out of bounds [0..100]: " + conf + " for " + r.getCodeTypeName());
        }

        // 4) Greedy matching Expected -> Results (order-independent; each result used once)
        boolean[] used = new boolean[results.length];
        for (Expected e : (expectedList == null ? java.util.List.<Expected>of() : expectedList))
        {
            boolean matched = false;

            for (int i = 0; i < results.length; i++)
            {
                if (used[i]) continue;
                BarCodeResult r = results[i];
                if (!r.getCodeType().equals(e.type))
                {
                    continue;
                }

                if (e.mode == CompareMode.TEXT)
                {
                    if (java.util.Objects.equals(r.getCodeText(), e.text))
                    {
                        used[i] = true;
                        matched = true;
                        break;
                    }
                } else
                    if (e.mode == CompareMode.PREFIX)
                    {
                        String t = r.getCodeText();
                        if (t != null && t.startsWith(e.text))
                        {
                            used[i] = true;
                            matched = true;
                            break;
                        }
                    } else
                        if (e.mode == CompareMode.BYTES)
                        {
                            if (java.util.Arrays.equals(r.getCodeBytes(), e.bytes))
                            {
                                used[i] = true;
                                matched = true;
                                break;
                            }
                        } else
                        {
                            org.testng.Assert.fail("Unknown CompareMode: " + e.mode);
                        }
            }

            if (!matched)
            {
                // collect some diagnostic information for a clear message
                java.util.List<String> seenTexts = new java.util.ArrayList<>();
                for (BarCodeResult r : results)
                    if (r.getCodeType().equals(e.type)) seenTexts.add(String.valueOf(r.getCodeText()));

                if (e.mode == CompareMode.TEXT)
                {
                    org.testng.Assert.fail("Expected pair not found (by text): type=" + e.type +
                            " text=\"" + e.text + "\". Seen texts: " + seenTexts);
                } else
                    if (e.mode == CompareMode.PREFIX)
                    {
                        org.testng.Assert.fail("Expected pair not found (by prefix): type=" + e.type +
                                " prefix=\"" + e.text + "\". Seen texts: " + seenTexts);
                    } else
                        if (e.mode == CompareMode.BYTES)
                        {
                            // show the hex of expected and first found bytes of the same type
                            String expectedHex = hexPreview(e.bytes, 32);
                            java.util.List<String> seenHex = new java.util.ArrayList<>();
                            for (BarCodeResult r : results)
                                if (r.getCodeType().equals(e.type))
                                    seenHex.add(hexPreview(r.getCodeBytes(), 32));
                            org.testng.Assert.fail("Expected pair not found (by bytes): type=" + e.type +
                                    " bytes=0x" + expectedHex + ". Seen bytes: " + seenHex);
                        }
            }
        }
    }



        // --- helper: file must exist and be non-empty ---
    public static void assertFileCreated(String fullPath) throws Exception {
        Path p = Paths.get(fullPath);
        Assert.assertTrue(Files.exists(p), "Output not created: " + fullPath);
        Assert.assertTrue(Files.size(p) > 0, "Output is empty: " + fullPath);
    }

    // Pretty-print the first N bytes of a byte[] as hex; append "…" if truncated.
    private static String hexPreview(byte[] bytes, int maxBytes) {
        if (bytes == null) return "null";
        int n = Math.min(bytes.length, Math.max(0, maxBytes)); // clamp to [0..len]
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append(String.format("%02X", bytes[i])); // two hex digits per byte
        }
        if (bytes.length > n) sb.append("…"); // indicate truncation
        return sb.toString();
    }

}
