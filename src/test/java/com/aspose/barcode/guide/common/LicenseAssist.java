package com.aspose.barcode.guide.common;

import com.aspose.barcode.License;

import java.io.InputStream;

/**
 * Utility to initialize Aspose.BarCode license once per JVM.
 * Looks for the license file in the test/main resources.
 */
public final class LicenseAssist {

    // Resource path relative to classpath root: src/test/resources or src/main/resources
    private static final String LICENSE_PATH = "license/Aspose.BarCode.Java.lic";

    // Volatile for safe double-checked locking
    private static volatile License licenseInstance;

    private LicenseAssist() {
        throw new UnsupportedOperationException("Utility class");
    }

    /**
     * Attempts to initialize the Aspose.BarCode license from a classpath resource.
     * <p>
     * This method implements a lazy, thread-safe initialization pattern for the {@link License} instance:
     * </p>
     *
     * <ul>
     *     <li>If the license has already been initialized ({@code licenseInstance != null}),
     *         the method returns immediately with {@code true}.</li>
     *     <li>If the license has not been initialized yet, the method enters a synchronized block
     *         on {@link LicenseAssist} to ensure that only one thread performs the initialization.</li>
     *     <li>Inside the synchronized block, the method performs a second check of {@code licenseInstance}
     *         (double-checked locking). This prevents multiple threads from initializing the license more than once.</li>
     *     <li>The method then tries to locate the license file on the classpath using
     *         {@link ClassLoader#getResourceAsStream(String)} with the path defined in {@link #LICENSE_PATH}.</li>
     *     <li>If the resource is not found ({@code InputStream} is {@code null}), the method prints a message
     *         to {@code System.out} indicating that the license file is missing and that Aspose.BarCode will run
     *         in evaluation mode, then returns {@code false} without throwing an exception.</li>
     *     <li>If the resource is found, a new {@link License} object is created, {@link License#setLicense(InputStream)}
     *         is called to apply the license globally within the Aspose.BarCode library, and the resulting instance
     *         is stored in the {@code volatile} field {@link #licenseInstance}.</li>
     *     <li>On successful initialization, a confirmation message is printed and the method returns {@code true}.</li>
     *     <li>If any exception occurs while reading the resource or applying the license, the method logs a warning
     *         and the stack trace to {@code System.out}, then returns {@code false}, keeping the application running
     *         in evaluation mode.</li>
     * </ul>
     *
     * <p>
     * Concurrency notes:
     * </p>
     * <ul>
     *     <li>The {@code licenseInstance} field is declared {@code volatile} to guarantee visibility of the
     *         initialized {@link License} object across threads. Once one thread successfully assigns the field,
     *         other threads will see the updated value without needing to re-enter the synchronized block.</li>
     *     <li>The combination of {@code volatile} and the synchronized block with a second null-check implements
     *         a standard double-checked locking pattern, ensuring that license initialization happens at most once
     *         while minimizing synchronization overhead for subsequent calls.</li>
     * </ul>
     *
     * @return {@code true} if the license was found on the classpath and successfully applied;
     *         {@code false} if the license file is missing or an error occurred and the library
     *         continues to run in evaluation mode.
     */
    public static boolean setupLicense() {
        if (licenseInstance != null) {
            return true;
        }

        synchronized (LicenseAssist.class) {
            if (licenseInstance != null) {
                return true;
            }

            InputStream in = null;
            try {
                ClassLoader cl = Thread.currentThread().getContextClassLoader();
                if (cl == null) {
                    cl = LicenseAssist.class.getClassLoader();
                }

                in = cl.getResourceAsStream(LICENSE_PATH);
                if (in == null) {
                    System.out.println("[Aspose.BarCode] License file not found: " + LICENSE_PATH);
                    System.out.println("[Aspose.BarCode] Running in evaluation mode.");
                    return false;
                }

                License lic = new License();
                lic.setLicense(in);
                licenseInstance = lic;

                System.out.println("[Aspose.BarCode] License successfully applied from: " + LICENSE_PATH);
                return true;
            } catch (Exception e) {
                System.out.println("[Aspose.BarCode] Failed to apply license, running in evaluation mode.");
                e.printStackTrace(System.out);
                return false;
            } finally {
                if (in != null) {
                    try {
                        in.close();
                    } catch (Exception ignored) {
                        // Ignore closing exception
                    }
                }
            }
        }
    }


    /**
     * Returns the current license instance or null if license was not applied.
     */
    public static License getLicense() {
        return licenseInstance;
    }

    /**
     * Resets the cached license reference (does NOT “unlicense” Aspose within the JVM).
     */
    public static void resetLicense() {
        licenseInstance = null;
    }
}
