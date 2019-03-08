package com.aspose.barcode.examples.TwoD_barcodes.basic_features;

import java.io.IOException;

import com.aspose.barcode.BarCodeBuilder;
import com.aspose.barcode.examples.Utils;
import com.aspose.barcode.generation.BarCodeGenerator;

public class GenerateAztecBarcode 
{
    public static void main(String[] args) throws IOException 
    {
            // The path to the resource directory.
            String dataDir = Utils.getDataDir(GenerateAztecBarcode.class) + "TwoD_barcodes/BasicFeatures/";

            // Initialize BarCode builder class object
            BarCodeGenerator generator = new BarCodeGenerator(com.aspose.barcode.EncodeTypes.AZTEC, "25");

            // save barcode
            generator.save(dataDir + "Aztec.png");
    }
}
