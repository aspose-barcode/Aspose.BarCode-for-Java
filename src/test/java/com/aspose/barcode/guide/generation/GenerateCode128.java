package com.aspose.barcode.guide.generation;

import com.aspose.barcode.LicenseAssistant;
import com.aspose.barcode.auxiliary.Global;
import com.aspose.barcode.generation.BarcodeGenerator;
import com.aspose.barcode.generation.EncodeTypes;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;

public class GenerateCode128
{
    private static final String folder = Global.getTestDataFolder("developer-guide\\barcode-generation");

    @BeforeClass
    public void setUp()
    {
        LicenseAssistant.setupLicense();
    }

    @Test
    public void basicCode128Generation() throws IOException
    {
        // Create barcode generator instance
        BarcodeGenerator generator = new BarcodeGenerator(EncodeTypes.CODE_128, "BasicCode128");
        // Save barcode image
        generator.save(folder + "code128-basic.png");
    }


}
