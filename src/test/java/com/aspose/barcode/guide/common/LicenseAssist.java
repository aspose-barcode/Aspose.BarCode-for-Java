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
     * Tries to set up license from classpath resource.
     * If file is missing or invalid, prints a message and continues in evaluation mode.
     *
     * @return true if license was applied, false otherwise
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
                in = ExampleAssist.getResourceAsStream(LICENSE_PATH);
                if (in == null) {
                    System.out.println("[Aspose.BarCode] License file not found: " + LICENSE_PATH);
                    System.out.println("[Aspose.BarCode] Running in evaluation mode.");
                    return false;
                }

                License license = new License();
                license.setLicense(in); // Applies license globally inside Aspose
                licenseInstance = license;

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
                        // Ignore
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
