package com.aspose.barcode.guide.generation;

import com.aspose.barcode.generation.*;
import com.aspose.barcode.guide.common.ExampleAssist;
import com.aspose.barcode.guide.common.LicenseAssist;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;

public class GenerateCode128
{
    private static final String folder = ExampleAssist.getResourceFolderPath("generation");
    @BeforeClass
    public void setUp()
    {
        LicenseAssist.setupLicense();
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
