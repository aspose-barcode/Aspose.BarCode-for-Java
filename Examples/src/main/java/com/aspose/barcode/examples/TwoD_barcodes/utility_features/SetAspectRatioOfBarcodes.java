package com.aspose.barcode.examples.TwoD_barcodes.utility_features;

import java.io.IOException;

import com.aspose.barcode.BarCodeBuilder;
import com.aspose.barcode.examples.Utils;
import com.aspose.barcode.generation.BarCodeGenerator;

public class SetAspectRatioOfBarcodes 
{
    public static void main(String[] args) throws IOException 
    {
    	//ExStart: SetAspectRatioOfBarcodes
        String dataDir = Utils.getDataDir(SetAspectRatioOfBarcodes.class) + "2DBarcode/UtilityFeatures/";

        // Create instance of BarCodeBuilder class
        BarCodeGenerator generator = new BarCodeGenerator(com.aspose.barcode.EncodeTypes.PDF_417, "12345678");

        // Set Aspect Ratio to 3:2 or 1.5
        generator.getD2().setAspectRatio(1.5f);

        // Save the barcode image to disk in PNG format
        generator.save(dataDir + "barcode_aspect_ratio.png");
        //ExEnd: SetAspectRatioOfBarcodes
    }

}
