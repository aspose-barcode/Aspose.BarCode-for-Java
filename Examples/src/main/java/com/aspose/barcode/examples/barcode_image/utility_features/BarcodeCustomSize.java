package com.aspose.barcode.examples.barcode_image.utility_features;

import com.aspose.barcode.BarCodeBuilder;
import com.aspose.barcode.examples.Utils;

public class BarcodeCustomSize {
    public static void main(String[] args) {
    	
    	// The path to the resource directory.
    	String dataDir = Utils.getDataDir(BarcodeCustomSize.class) + "BarcodeImage/UtilityFeatures/";

        //Instantiate barcode object
        BarCodeBuilder builder = new BarCodeBuilder();

        //Set the Code text for the barcode
        builder.setCodeText("1234567890");

        //Set the symbology type to Code39Standard
        builder.setEncodeType(com.aspose.barcode.EncodeTypes.CODE_39_STANDARD);

        // Set auto size false
        builder.setAutoSize(false);

        // Set height
        builder.setImageHeight(50);

        // Set width
        builder.setImageWidth(120);

        // Save the image
        builder.save(dataDir + "barcode-custom-size.jpg");
        
    }
}
