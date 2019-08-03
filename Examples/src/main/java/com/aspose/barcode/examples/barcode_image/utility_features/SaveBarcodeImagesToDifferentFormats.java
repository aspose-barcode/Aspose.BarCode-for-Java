package com.aspose.barcode.examples.barcode_image.utility_features;

import java.io.IOException;

import com.aspose.barcode.*;
import com.aspose.barcode.examples.Utils;
import com.aspose.barcode.generation.BarcodeGenerator;

public class SaveBarcodeImagesToDifferentFormats {
    public static void main(String[] args) throws IOException {
    	//ExStart: SaveBarcodeImagesToDifferentFormats
    	// The path to the resource directory.
    	String dataDir = Utils.getDataDir(SaveBarcodeImagesToDifferentFormats.class) + "BarcodeImage/UtilityFeatures/";

    	//Instantiate barcode object, Set the symbology type to code128 and Set the Code text for the barcode
        BarcodeGenerator bb = new BarcodeGenerator(com.aspose.barcode.EncodeTypes.CODE_128, "1234567");
        

        //Save the image to your system and set its image format to Jpeg
        bb.save(dataDir + "barcode-image-format.jpg", BarCodeImageFormat.JPEG);
        //ExEnd: SaveBarcodeImagesToDifferentFormats
    }
}
