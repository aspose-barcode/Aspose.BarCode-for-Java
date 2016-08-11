package com.aspose.barcode.examples.barcode_image.basic_features;

import com.aspose.barcode.BarCodeBuilder;
import com.aspose.barcode.ImageQualityMode;
import com.aspose.barcode.Symbology;
import com.aspose.barcode.examples.Utils;
import com.aspose.barcode.examples.barcode.basic_features.SpecifySymbology;

public class BarcodeImageQuality {
    public static void main(String[] args) {
    	
    	// The path to the resource directory.
    	String dataDir = Utils.getDataDir(SpecifySymbology.class) + "BarcodeImage/BasicFeatures/";

        BarCodeBuilder bb = new BarCodeBuilder();
        bb.setCodeText("1234567");
        bb.setSymbologyType(Symbology.Code128);

        //Set the graphics drawing hint to be Anti Alias
        bb.setImageQuality(ImageQualityMode.AntiAlias);

        // Save the image
        bb.save(dataDir + "barcode-image-quality.jpg");
        
    }
}
