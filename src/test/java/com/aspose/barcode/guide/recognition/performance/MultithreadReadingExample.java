package com.aspose.barcode.guide.recognition.performance;

import com.aspose.barcode.barcoderecognition.BarCodeReader;
import com.aspose.barcode.barcoderecognition.BarcodeQualityMode;
import com.aspose.barcode.barcoderecognition.DecodeType;
import com.aspose.barcode.barcoderecognition.DeconvolutionMode;
import com.aspose.barcode.barcoderecognition.ProcessorSettings;
import com.aspose.barcode.barcoderecognition.QualitySettings;
import com.aspose.barcode.barcoderecognition.XDimensionMode;
import com.aspose.barcode.generation.BarCodeImageFormat;
import com.aspose.barcode.generation.BarcodeGenerator;
import com.aspose.barcode.generation.EncodeTypes;
import com.aspose.barcode.guide.common.ExampleAssist;
import com.aspose.barcode.guide.common.LicenseAssist;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.aspose.barcode.guide.common.ExampleAssist.getCpuCount;

/**
 * MultithreadReadingExample
 *
 * Goal:
 *  - Demonstrate how ProcessorSettings (UseAllCores / UseOnlyThisCoresCount / MaxAdditionalAllowedThreads)
 *    affect recognition speed on a dataset.
 *
 * Methodology:
 *  - Images are preloaded into memory to avoid I/O noise.
 *  - Each mode has its own warm-up run.
 *  - Measure 3 times per mode and take the median.
 *  - Optional "heavy" QualitySettings profile to make the workload CPU-bound.
 */
public class MultithreadReadingExample {

    private static final String FOLDER =
            ExampleAssist.getOrCreateResourceFolderPath("recognition", "performance", "multithread");

    /** Increase for clearer differences on multi-core machines. */
    private static final int DATASET_SIZE = 72;

    /** Enable heavier recognition to make CPU the bottleneck. */
    private static final boolean USE_HEAVY_PROFILE = true;

    // File names of generated dataset (order matters for label/expected type)
    private static final List<String> FILES = new ArrayList<>(DATASET_SIZE);

    // Preloaded images and names to eliminate file I/O during benchmarks
    private static final List<BufferedImage> IMAGES = new ArrayList<>(DATASET_SIZE);
    private static final List<String> NAMES = new ArrayList<>(DATASET_SIZE);

    @BeforeClass
    public void setUp() throws Exception {
        LicenseAssist.setupLicense();
        generateDataset();     // creates images (if missing) and preloads them into memory
        warmUpOnce();          // global warm-up to stabilize JIT
    }

    /**
     * Generates a synthetic dataset of CODE_128 and QR images and preloads them into memory.
     */
    private void generateDataset() throws Exception {
        for (int i = 0; i < DATASET_SIZE; i++) {
            final boolean isQR = (i % 3 == 0); // ~1/3 QR, ~2/3 Code128
            final String name = isQR ? String.format("qr_%02d.png", i) : String.format("code128_%02d.png", i);
            FILES.add(name);

            final int idx = i;
            ExampleAssist.checkOrCreateImage(FOLDER, name, path -> {
                if (isQR) {
                    BarcodeGenerator g = new BarcodeGenerator(EncodeTypes.QR, "QR#" + idx);
                    g.save(path, BarCodeImageFormat.PNG);
                } else {
                    BarcodeGenerator g = new BarcodeGenerator(EncodeTypes.CODE_128, "C128#" + idx);
                    g.save(path, BarCodeImageFormat.PNG);
                }
            });

            // Preload into memory
            String full = ExampleAssist.pathCombine(FOLDER, name);
            BufferedImage img = ImageIO.read(new File(full));
            IMAGES.add(img);
            NAMES.add(name);
        }
    }

    /** Quick global warm-up with a light preset to remove first-run skew. */
    private void warmUpOnce() throws Exception {
        setSingleCore();
        consumeDatasetInMemory(false);
    }

    // ----------------------- Tests -----------------------

    /**
     * Single core baseline:
     * Purpose: establish a single-threaded baseline for later speedup comparisons.
     * Expectation: usually the slowest wall time on CPU-bound workloads.
     */
    @Test
    public void readDataset_SingleCore_Baseline() throws Exception {
        long ms = runWithWarmupAndMedian(MultithreadReadingExample::setSingleCore);
        ExampleAssist.logInfo(String.format("[SingleCore] median(3): %d ms, images: %d", ms, DATASET_SIZE));
    }

    /**
     * Half of available cores:
     * Purpose: show improvement vs single core while keeping system headroom.
     */
    @Test
    public void readDataset_HalfCores() throws Exception {
        long ms = runWithWarmupAndMedian(MultithreadReadingExample::setHalfCores);
        ExampleAssist.logInfo(String.format("[HalfCores] median(3): %d ms, images: %d", ms, DATASET_SIZE));
    }

    /**
     * All available cores:
     * Purpose: demonstrate maximum parallelism controlled by the engine.
     */
    @Test
    public void readDataset_AllCores() throws Exception {
        long ms = runWithWarmupAndMedian(MultithreadReadingExample::setAllCores);
        ExampleAssist.logInfo(String.format("[AllCores] median(3): %d ms, images: %d", ms, DATASET_SIZE));
    }

    /**
     * All cores + increased MaxAdditionalAllowedThreads:
     * Purpose: experiment with a higher worker-thread cap (can help mixed or I/O-heavy tasks).
     */
    @Test
    public void readDataset_AllCores_MaxThreadsX2() throws Exception {
        long ms = runWithWarmupAndMedian(MultithreadReadingExample::setAllCoresMaxThreadsX2);
        ExampleAssist.logInfo(String.format("[AllCores+MaxThreads*2] median(3): %d ms, images: %d", ms, DATASET_SIZE));
    }

    /**
     * All cores : leave 1 core for OS, but allow ~1.5× CPU additional threads.
     * Purpose: try a more aggressive pool on top of full-core usage.
     * Note: may be better or worse depending on CPU and workload characteristics.
     */
    @Test
    public void readDataset_AllCores_Tuned() throws Exception {
        long ms = runWithWarmupAndMedian(MultithreadReadingExample::setAllCores_Tuned);
        ExampleAssist.logInfo(String.format("[AllCores/Tuned] median(3): %d ms, images: %d", ms, DATASET_SIZE));
    }

    /**
     * Half of CPU (manual cap without UseAllCores).
     * Purpose: reduce scheduler overhead of UseAllCores and test a "sweet spot" with fewer threads.
     */
    @Test
    public void readDataset_Fixed_Half() throws Exception {
        long ms = runWithWarmupAndMedian(() -> setFixedCores(Math.max(1, getCpuCount() / 2)));
        ExampleAssist.logInfo(String.format("[FixedCores/half] median(3): %d ms, images: %d", ms, DATASET_SIZE));
    }

    /**
     * One core (manual cap).
     * Purpose: keep one core for the OS/runner and push near-max parallelism with a stable thread count.
     */
    @Test
    public void readDataset_Fixed_CpuMinusOne() throws Exception {
        long ms = runWithWarmupAndMedian(() -> setFixedCores(Math.max(1, getCpuCount() - 1)));
        ExampleAssist.logInfo(String.format("[FixedCores/cpu-1] median(3): %d ms, images: %d", ms, DATASET_SIZE));
    }

    // ----------------------- Measurement harness -----------------------

    /** Run setup, warm up once for that mode, then measure 3 times and return median in ms. */
    private static long runWithWarmupAndMedian(Runnable setup) throws Exception {
        setup.run();
        consumeDatasetInMemory(false); // warm-up for this exact mode

        long a = consumeDatasetInMemory(false);
        long b = consumeDatasetInMemory(false);
        long c = consumeDatasetInMemory(false);
        long[] arr = new long[]{a, b, c};
        Arrays.sort(arr);
        return arr[1];
    }

    /**
     * Reads the whole in-memory dataset with current ProcessorSettings and returns wall time in ms.
     * Uses a heavier QualitySettings profile when USE_HEAVY_PROFILE is enabled.
     * Asserts via ExampleAssist.assertRecognizedSilent (no per-file console noise).
     */
    private static long consumeDatasetInMemory(boolean printPerFile) throws Exception {
        long t0 = System.nanoTime();

        for (int i = 0; i < IMAGES.size(); i++) {
            String name = NAMES.get(i);
            boolean isQR = name.startsWith("qr_");

            BarCodeReader reader = new BarCodeReader(IMAGES.get(i), isQR ? DecodeType.QR : DecodeType.CODE_128);

            if (USE_HEAVY_PROFILE) {
                QualitySettings qs = QualitySettings.getHighQuality();
                qs.setDeconvolution(DeconvolutionMode.SLOW);
                qs.setBarcodeQuality(BarcodeQualityMode.LOW);
                qs.setXDimension(XDimensionMode.SMALL);
                reader.setQualitySettings(qs);
            }

            if (isQR) {
                ExampleAssist.assertRecognizedSilent(reader, 1, DecodeType.QR);
            } else {
                ExampleAssist.assertRecognizedSilent(reader, 1, DecodeType.CODE_128);
            }

            if (printPerFile) {
                ExampleAssist.logInfo("processed: " + name);
            }
        }

        long t1 = System.nanoTime();
        return (t1 - t0) / 1_000_000L;
    }

    // ----------------------- ProcessorSettings presets -----------------------

    private static void setSingleCore() {
        ProcessorSettings ps = BarCodeReader.getProcessorSettings();
        ps.setUseAllCores(false);
        ps.setUseOnlyThisCoresCount(1);
        ps.setMaxAdditionalAllowedThreads(0);
    }

    private static void setHalfCores() {
        ProcessorSettings ps = BarCodeReader.getProcessorSettings();
        int cpu = getCpuCount();
        int half = Math.max(1, cpu / 2);
        ps.setUseAllCores(false);
        ps.setUseOnlyThisCoresCount(half);
        ps.setMaxAdditionalAllowedThreads(half); // simple heuristic
    }

    private static void setAllCores() {
        ProcessorSettings ps = BarCodeReader.getProcessorSettings();
        int cpu = getCpuCount();
        ps.setUseAllCores(true);
        // This value is ignored when UseAllCores=true, but keep state consistent:
        ps.setUseOnlyThisCoresCount(cpu);
        ps.setMaxAdditionalAllowedThreads(Math.max(1, cpu - 1)); // leave 1 core for OS/runner
    }

    private static void setAllCoresMaxThreadsX2() {
        ProcessorSettings ps = BarCodeReader.getProcessorSettings();
        int cpu = getCpuCount();
        ps.setUseAllCores(true);
        ps.setUseOnlyThisCoresCount(cpu);
        ps.setMaxAdditionalAllowedThreads(cpu * 2);
    }

    /** Tuned "all cores": allow ~1.5×CPU additional threads. */
    private static void setAllCores_Tuned() {
        ProcessorSettings ps = BarCodeReader.getProcessorSettings();
        int cpu = getCpuCount();
        ps.setUseAllCores(true);
        ps.setUseOnlyThisCoresCount(cpu);
        ps.setMaxAdditionalAllowedThreads(Math.max(1, cpu + cpu / 2)); // ~1.5x CPU
    }

    /** Manual cap: fixed number of cores without UseAllCores. */
    private static void setFixedCores(int n) {
        ProcessorSettings ps = BarCodeReader.getProcessorSettings();
        int cpu = getCpuCount();
        int use = Math.max(1, Math.min(n, cpu));
        ps.setUseAllCores(false);
        ps.setUseOnlyThisCoresCount(use);
        ps.setMaxAdditionalAllowedThreads(use);
    }
}
