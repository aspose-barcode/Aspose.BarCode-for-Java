package com.aspose.barcode.guide.recognition.performance;

import com.aspose.barcode.barcoderecognition.BarCodeReader;
import com.aspose.barcode.barcoderecognition.DecodeType;
import com.aspose.barcode.barcoderecognition.ProcessorSettings;
import com.aspose.barcode.generation.BarCodeImageFormat;
import com.aspose.barcode.generation.BarcodeGenerator;
import com.aspose.barcode.generation.EncodeTypes;
import com.aspose.barcode.guide.common.ExampleAssist;
import com.aspose.barcode.guide.common.LicenseAssist;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static com.aspose.barcode.guide.common.ExampleAssist.getCpuCount;

/**
 * MultithreadReadingExample
 *
 * Goal:
 *  - Demonstrate how ProcessorSettings (UseAllCores / UseOnlyThisCoresCount / MaxAdditionalAllowedThreads)
 *    affect recognition speed on a dataset.
 *
 * Notes:
 *  - We keep assertions only for correctness (barcodes must be recognized).
 *  - Performance is reported to console via ExampleAssist (no hard time assertions to avoid flakiness in CI).
 */
public class MultithreadReadingExample {

    private static final String FOLDER =
            ExampleAssist.getOrCreateResourceFolderPath("recognition", "performance", "multithread");

    // Size of synthetic dataset. Increase if you want more pronounced differences.
    private static final int DATASET_SIZE = 36;

    // File names of generated dataset
    private static final List<String> FILES = new ArrayList<>(DATASET_SIZE);

    @BeforeClass
    public void setUp() throws Exception {
        LicenseAssist.setupLicense();
        generateDataset();
        // Optional: small warm-up to JIT the code path and avoid first-run skew in timings
        warmUpOnce();
    }

    /**
     * Generates a synthetic dataset of CODE_128 and QR images.
     * The mix of symbologies simulates a generic workload.
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
        }
    }


    /**
     * Do a quick warm-up run with default ProcessorSettings to stabilize JIT/IO caches.
     */
    private void warmUpOnce() throws Exception {
        setSingleCore(); // minimal threading for warm-up
        consumeDataset(false); // no printing
    }

    // ----------------------- Tests -----------------------

    /**
     * Single core baseline:
     * - UseAllCores = false
     * - UseOnlyThisCoresCount = 1
     * - MaxAdditionalAllowedThreads = 0
     *
     * Purpose:
     *  - Establish a single-threaded baseline to compare speedups from multi-core modes.
     * Expectation:
     *  - Slowest wall time on most machines, but useful as a reference.
     */
    @Test
    public void readDataset_SingleCore_Baseline() throws Exception {
        setSingleCore();
        long ms = consumeDataset(false);
        ExampleAssist.logInfo(String.format("[SingleCore] total: %d ms, images: %d", ms, DATASET_SIZE));
    }

    /**
     * Half of available cores:
     * - UseAllCores = false
     * - UseOnlyThisCoresCount = max(1, processors / 2)
     * - MaxAdditionalAllowedThreads = same as used cores (heuristic)
     *
     * Purpose:
     *  - Show that using a portion of cores gives better throughput than single core,
     *    while keeping some CPU headroom for the system/CI runner.
     * Expectation:
     *  - Faster than SingleCore; slower than AllCores on CPU-bound workloads.
     */
    @Test
    public void readDataset_HalfCores() throws Exception {
        setHalfCores();
        long ms = consumeDataset(false);
        ExampleAssist.logInfo(String.format("[HalfCores] total: %d ms, images: %d", ms, DATASET_SIZE));
    }

    /**
     * All available cores (recommended starting point for max throughput on a single reader):
     * - UseAllCores = true
     * - (UseOnlyThisCoresCount is ignored)
     * - MaxAdditionalAllowedThreads = processors (default good heuristic)
     *
     * Purpose:
     *  - Demonstrate maximum parallelism controlled by the engine for one BarCodeReader call.
     * Expectation:
     *  - Best or near-best wall time versus SingleCore/HalfCores.
     */
    @Test
    public void readDataset_AllCores() throws Exception {
        setAllCores();
        long ms = consumeDataset(false);
        ExampleAssist.logInfo(String.format("[AllCores] total: %d ms, images: %d", ms, DATASET_SIZE));
    }

    /**
     * All cores + increased MaxAdditionalAllowedThreads:
     * - UseAllCores = true
     * - MaxAdditionalAllowedThreads = processors * 2
     *
     * Purpose:
     *  - Show how raising the cap of additional worker threads may help IO-heavy or mixed workloads.
     * Caveat:
     *  - On pure CPU-bound workloads, too many threads can lead to contention and even regressions.
     * Expectation:
     *  - On some machines this equals AllCores; on others can be slightly better or slightly worse.
     */
    @Test
    public void readDataset_AllCores_MaxThreadsX2() throws Exception {
        setAllCoresMaxThreadsX2();
        long ms = consumeDataset(false);
        ExampleAssist.logInfo(String.format("[AllCores+MaxThreads*2] total: %d ms, images: %d", ms, DATASET_SIZE));
    }

    // ----------------------- Core logic -----------------------

    /**
     * Reads the whole dataset with current ProcessorSettings and returns wall time in ms.
     * Also verifies that each image is recognized at least once.
     */
    private long consumeDataset(boolean printPerFile) throws Exception {
        long t0 = System.nanoTime();
        for (String name : FILES) {
            String full = ExampleAssist.pathCombine(FOLDER, name);

            // Choose expected decode type by file name prefix for correctness check
            final boolean isQR = name.startsWith("qr_");
            BarCodeReader reader = new BarCodeReader(full,
                    isQR ? DecodeType.QR : DecodeType.CODE_128);

            // recognize & check
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
        ps.setMaxAdditionalAllowedThreads(half); // simple heuristics
    }

    private static void setAllCores() {
        ProcessorSettings ps = BarCodeReader.getProcessorSettings();
        int cpu = getCpuCount();
        ps.setUseAllCores(true);
        // This field is ignored when UseAllCores=true, but we will leave it consistent
        ps.setUseOnlyThisCoresCount(cpu);
        ps.setMaxAdditionalAllowedThreads(cpu); // basic security heuristics
    }

    private static void setAllCoresMaxThreadsX2() {
        ProcessorSettings ps = BarCodeReader.getProcessorSettings();
        int cpu = getCpuCount();
        ps.setUseAllCores(true);
        ps.setUseOnlyThisCoresCount(cpu);
        ps.setMaxAdditionalAllowedThreads(cpu * 2);
    }
}
