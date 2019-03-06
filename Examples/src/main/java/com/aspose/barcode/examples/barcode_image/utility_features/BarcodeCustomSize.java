package com.aspose.barcode.examples.barcode_image.utility_features;

import com.aspose.barcode.generation.AutoSizeMode;
import com.aspose.barcode.generation.BarCodeGenerator;
import com.aspose.barcode.examples.Utils;

import java.io.IOException;

public class BarcodeCustomSize {
    public static void main(String[] args) throws IOException {
    	
    	// The path to the resource directory.
    	String dataDir = Utils.getDataDir(BarcodeCustomSize.class) + "BarcodeImage/UtilityFeatures/";

        //Instantiate barcode object, Set the Code text for the barcode and the symbology type to Code39Standard
        BarCodeGenerator generator = new BarCodeGenerator(com.aspose.barcode.EncodeTypes.CODE_39_STANDARD, "1234567890");

        // Set auto size false
        generator.setAutoSizeMode(AutoSizeMode.NEAREST);

        // Set height
        generator.getBarCodeHeight().setMillimeters(50);

        // Set width
        generator.getBarCodeWidth().setMillimeters(120);

        // Save the image
        generator.save(dataDir + "barcode-custom-size.jpg");
        
    }
}
