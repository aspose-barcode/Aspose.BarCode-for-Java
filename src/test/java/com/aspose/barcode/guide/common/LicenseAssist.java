package com.aspose.barcode.guide.common;

import com.aspose.barcode.License;

import java.io.InputStream;

public class LicenseAssist
{
    private static final String LICENSE_PATH = "license/Aspose.BarCode.Java.lic";
    private static License licenseInstance = null;

    /**
     * Sets up license from resources
     */
    public static License setupLicense()
    {
        if (licenseInstance != null)
        {
            return licenseInstance;
        }

        try
        {
            InputStream licenseStream = ExampleAssist.getResourceAsStream(LICENSE_PATH);
            licenseInstance = new License();
            licenseInstance.setLicense(licenseStream);
            licenseStream.close();
            return licenseInstance;
        }
        catch (Exception e)
        {
            throw new RuntimeException("Failed to set up license from: " + LICENSE_PATH, e);
        }
    }

    /**
     * Resets the license
     */
    public static void resetLicense()
    {
        licenseInstance = null;
    }

    /**
     * Returns current license instance
     */
    public static License getLicense()
    {
        if (licenseInstance == null)
        {
            return setupLicense();
        }
        return licenseInstance;
    }

    private LicenseAssist()
    {
        // Utility class
        throw new UnsupportedOperationException("Utility class");
    }
}