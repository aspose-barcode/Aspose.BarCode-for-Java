package com.aspose.barcode.guide.common;

import com.aspose.barcode.barcoderecognition.*;
import com.aspose.barcode.generation.BarCodeImageFormat;
import com.aspose.barcode.generation.BarcodeGenerator;
import com.aspose.barcode.generation.BaseEncodeType;
import com.aspose.barcode.generation.EncodeTypes;
import org.testng.Assert;
import org.testng.annotations.Test;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.Random;

/**
 * Utility helper for example tests and resource management.
 */
public class ExampleAssist
{

    /**
     * Gets the path to a resource folder with a trailing separator.
     */
    public static String getResourceFolderPath(String... pathParts)
    {
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
    public static String getOrCreateResourceFolderPath(String... pathParts)
    {
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
    public static String getResourceFilePath(String... pathParts)
    {
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
    public static InputStream getResourceAsStream(String resourcePath)
    {
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
    public static void checkOrCreateImage(String imagesFolder, String fileName, ImageSupplier generator) throws IOException
    {
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

    public static void checkOrCreateImage(String imagesFolder, String fileName, BarcodeGenerator barcodeGenerator) throws IOException
    {
        Path path = Paths.get(imagesFolder, fileName);
        Files.createDirectories(path.getParent());

        if (!Files.exists(path))
        {
            barcodeGenerator.save(path.toString());
            Assert.assertTrue(Files.exists(path), "Failed to create fixture: " + path);
            Assert.assertTrue(Files.size(path) > 0, "Fixture is empty: " + path);
        }
    }

    public static void assertRecognized(BarCodeReader reader, String tag, int minCount, BaseDecodeType expectedType) throws Exception
    {

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

    public static void assertNotRecognized(BarCodeReader reader, String tag) throws Exception
    {
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


    public static String generateTestBarcode(String data, String imagesFolder, String fileName, BaseEncodeType type) throws IOException
    {
        BarcodeGenerator gen = new BarcodeGenerator(type, data);
        gen.getParameters().setResolution(300f);
        gen.getParameters().getBarcode().getXDimension().setMillimeters(0.3f);
        gen.getParameters().getBarcode().getBarHeight().setMillimeters(25f);

        String fullPath = imagesFolder + File.separator + fileName;
        gen.save(fullPath, BarCodeImageFormat.PNG);
        return fullPath;
    }

    public static void generateAndRead(String folder, String fileName, String codeText, BaseEncodeType encodeType, BaseDecodeType decodeType) throws Exception
    {
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
    private ExampleAssist()
    {
        throw new UnsupportedOperationException("Utility class");
    }

    public static String pathCombine(String folder, String image)
    {
        return folder + "/" + image;
    }

    public static String getCurrentMethodName()
    {
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
    public static String currentMethodName()
    {
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
    public static void addGaussianNoise(String inputFullPath, String outputFullPath, double stdDev) throws IOException
    {
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
    public static void blur(String inputFullPath, String outputFullPath, float sigma) throws IOException
    {
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


    private static float[] gaussianKernel1D(int size, float sigma)
    {
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

    private static int clampToByte(int v)
    {
        return (v < 0) ? 0 : (v > 255 ? 255 : v);
    }

    private static Kernel gaussian3x3()
    {
        // Simple normalized 3x3 Gaussian approximation
        float[] m = {
                1f / 16f, 2f / 16f, 1f / 16f,
                2f / 16f, 4f / 16f, 2f / 16f,
                1f / 16f, 2f / 16f, 1f / 16f
        };
        return new Kernel(3, 3, m);
    }

    private static Kernel gaussian5x5()
    {
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
    private static void ensureParentDirs(String fullPath) throws IOException
    {
        File f = new File(fullPath);
        File p = f.getAbsoluteFile().getParentFile();
        if (p != null && !p.exists() && !p.mkdirs())
        {
            throw new IOException("Cannot create directories for: " + fullPath);
        }
    }


    public static void upscaleBicubic(String inputFullPath, String outputFullPath, double scale) throws IOException
    {
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
    public static void invertColors(String srcPath, String outPath)
    {
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
    public static void downscaleNearest(String inPath, String outPath, int targetWidthPx) throws IOException
    {
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
     *
     * Why this helps:
     * - Nearest-neighbor preserves module boundaries compared to smoothing resamplers.
     * - Otsu threshold restores hard black/white edges after resampling and reduces gray bleeding,
     *   which improves decode reliability on tiny barcodes.
     *
     * @param inPath        input image path
     * @param outPath       output image path (PNG)
     * @param targetWidthPx target width in pixels (>= 1)
     */
    public static void downscaleNearestCrisp(String inPath, String outPath, int targetWidthPx) throws IOException {
        if (targetWidthPx < 1) throw new IllegalArgumentException("targetWidthPx must be >= 1");

        BufferedImage src = ImageIO.read(new File(inPath));
        if (src == null) throw new IOException("Cannot read image: " + inPath);

        // 1) Nearest-neighbor downscale with aspect ratio preserved.
        int srcW = Math.max(1, src.getWidth());
        int srcH = Math.max(1, src.getHeight());
        double scale = (double) targetWidthPx / (double) srcW;
        int targetHeightPx = Math.max(1, (int) Math.round(srcH * scale));

        BufferedImage scaled = new BufferedImage(targetWidthPx, targetHeightPx, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = scaled.createGraphics();
        try {
            g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR);
            g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);
            g.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_SPEED);
            g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_SPEED);
            g.drawImage(src, 0, 0, targetWidthPx, targetHeightPx, null);
        } finally {
            g.dispose();
        }

        // 2) Convert to grayscale (luminance).
        BufferedImage gray = new BufferedImage(targetWidthPx, targetHeightPx, BufferedImage.TYPE_BYTE_GRAY);
        Graphics2D gg = gray.createGraphics();
        try {
            gg.drawImage(scaled, 0, 0, null);
        } finally {
            gg.dispose();
        }

        // 3) Otsu threshold -> black/white image (crisp modules).
        int[] hist = new int[256];
        for (int y = 0; y < gray.getHeight(); y++) {
            for (int x = 0; x < gray.getWidth(); x++) {
                int v = gray.getRaster().getSample(x, y, 0);
                hist[v]++;
            }
        }

        int total = gray.getWidth() * gray.getHeight();
        long sum = 0;
        for (int t = 0; t < 256; t++) sum += (long) t * hist[t];

        long sumB = 0;
        int wB = 0;
        double varMax = -1.0;
        int threshold = 127;
        for (int t = 0; t < 256; t++) {
            wB += hist[t];
            if (wB == 0) continue;
            int wF = total - wB;
            if (wF == 0) break;

            sumB += (long) t * hist[t];

            double mB = (double) sumB / wB;
            double mF = (double) (sum - sumB) / wF;
            double varBetween = (double) wB * (double) wF * (mB - mF) * (mB - mF);

            if (varBetween > varMax) {
                varMax = varBetween;
                threshold = t;
            }
        }

        BufferedImage bw = new BufferedImage(targetWidthPx, targetHeightPx, BufferedImage.TYPE_BYTE_BINARY);
        for (int y = 0; y < gray.getHeight(); y++) {
            for (int x = 0; x < gray.getWidth(); x++) {
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
     * @param type      symbology (e.g., EncodeTypes.CODE_128)
     * @param text      payload
     * @param widthPx   total image width in pixels
     * @param heightPx  total image height in pixels
     * @param xDimPx    X-dimension in pixels (module width); use ~2.0f for ~150px wide, ~1.0–1.2f for ~80px
     * @param quietPx   quiet zone size (left/right) in pixels
     * @param outPath   output PNG path
     */
    public static void renderBarcodeFixedSizePNG(BaseEncodeType type, String text,
                                                 int widthPx, int heightPx,
                                                 float xDimPx, int quietPx,
                                                 String outPath) throws IOException {
        if (widthPx < 20 || heightPx < 20) {
            throw new IllegalArgumentException("Image too small for rendering: " + widthPx + "x" + heightPx);
        }
        if (xDimPx < 0.5f) xDimPx = 0.5f;

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
        if (img.getType() != BufferedImage.TYPE_INT_RGB) {
            BufferedImage rgb = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_RGB);
            Graphics2D g = rgb.createGraphics();
            try {
                g.setColor(Color.WHITE);
                g.fillRect(0, 0, rgb.getWidth(), rgb.getHeight());
                g.drawImage(img, 0, 0, null);
            } finally {
                g.dispose();
            }
            ImageIO.write(rgb, "png", new File(outPath));
        }
    }
    /** Returns true if file exists. */
    public static boolean fileExists(String fullPath) {
        return fullPath != null && Files.exists(Paths.get(fullPath));
    }

    /** Simple filename extraction (no directories). */
    public static String getFileName(String fullPath) {
        if (fullPath == null) return "";
        Path p = Paths.get(fullPath);
        Path name = p.getFileName();
        return name == null ? "" : name.toString();
    }

    /** Logs an informational message to stdout. */
    public static void logInfo(String msg) {
        System.out.println("[INFO] " + msg);
    }

    /** Logs a warning message to stdout. */
    public static void logWarn(String msg) {
        System.out.println("[WARN] " + msg);
    }

    /**
     * Lists files in a directory by a glob like "*.png".
     * Returns absolute paths. If folder does not exist, returns empty array.
     */
    public static String[] listFilesByGlob(String folder, String globPattern) {
        java.util.List<String> out = new ArrayList<>();
        if (folder == null || globPattern == null) return new String[0];

        Path dir = Paths.get(folder);
        if (!Files.isDirectory(dir)) return new String[0];

        try (DirectoryStream<Path> stream = Files.newDirectoryStream(dir, globPattern)) {
            for (Path p : stream) {
                if (Files.isRegularFile(p)) out.add(p.toAbsolutePath().toString());
            }
        } catch (IOException e) {
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
}
