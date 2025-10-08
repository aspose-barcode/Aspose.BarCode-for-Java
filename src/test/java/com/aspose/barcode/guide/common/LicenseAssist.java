package com.aspose.barcode.guide.common;

import com.aspose.barcode.License;

import java.io.InputStream;

/**
 * Utility to initialize Aspose.BarCode license once per JVM.
 * Looks for the license file in the test/main resources.
 */
public final class LicenseAssist
{

    // Resource path relative to classpath root: src/test/resources or src/main/resources
    private static final String LICENSE_PATH = "license/Aspose.BarCode.Java.lic";

    // Volatile for safe double-checked locking
    private static volatile License licenseInstance;

    private LicenseAssist()
    {
        throw new UnsupportedOperationException("Utility class");
    }

    /**
     * Sets up license from classpath resource.
     * Throws RuntimeException on failure.
     */
    public static License setupLicense()
    {
        if (licenseInstance == null)
        {
            synchronized (LicenseAssist.class)
            {
                if (licenseInstance == null)
                {
                    try (InputStream in = ExampleAssist.getResourceAsStream(LICENSE_PATH))
                    {
                        if (in == null)
                        {
                            throw new IllegalStateException(
                                    "License resource not found on classpath: " + LICENSE_PATH
                            );
                        }
                        License lic = new License();
                        lic.setLicense(in); // Applies license globally inside Aspose
                        licenseInstance = lic;
                    }
                    catch (Exception e)
                    {
                        throw new RuntimeException(
                                "Failed to set up Aspose.BarCode license from: " + LICENSE_PATH, e
                        );
                    }
                }
            }
        }
        return licenseInstance;
    }

    /**
     * Sets up license, but if not found or invalid, falls back to evaluation mode (no exception).
     * Useful for CI or public example repo so tests still run in trial mode.
     */
    public static boolean setupLicenseQuietly()
    {
        try
        {
            setupLicense();
            return true;
        }
        catch (RuntimeException ex)
        {
            // Optionally log here
            // System.err.println("[WARN] " + ex.getMessage());
            return false;
        }
    }

    /**
     * Returns the current license instance, initializing if necessary.
     */
    public static License getLicense()
    {
        return (licenseInstance != null) ? licenseInstance : setupLicense();
    }

    /**
     * Resets the cached license reference (does NOT “unlicense” Aspose within the JVM).
     * Mainly for tests to re-run setup in isolated scenarios.
     */
    public static void resetLicense()
    {
        licenseInstance = null;
    }
}
