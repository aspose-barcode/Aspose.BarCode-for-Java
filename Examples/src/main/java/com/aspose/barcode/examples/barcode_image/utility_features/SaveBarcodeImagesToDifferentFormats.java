package com.aspose.barcode.examples.barcode_image.utility_features;

import java.io.IOException;

import com.aspose.barcode.*;
import com.aspose.barcode.examples.Utils;

public class SaveBarcodeImagesToDifferentFormats {
    public static void main(String[] args) throws IOException {
    	
    	// The path to the resource directory.
    	String dataDir = Utils.getDataDir(SaveBarcodeImagesToDifferentFormats.class) + "BarcodeImage/UtilityFeatures/";

        //Instantiate barcode object
        BarCodeBuilder bb = new BarCodeBuilder();

        //Set the Code text for the barcode
        bb.setCodeText("1234567");

        //Set the symbology type to code128
        bb.setSymbologyType(Symbology.Code128);

        //Save the image to your system and set its image format to Jpeg
        bb.save(dataDir + "barcode-image-format.jpg", ImageFormat.getJpeg());
      
    }
}
