package com.aspose.barcode.examples.barcode_image.basic_features;

import com.aspose.barcode.BarCodeBuilder;
import com.aspose.barcode.Symbology;
import com.aspose.barcode.examples.Utils;
import com.aspose.barcode.examples.barcode.basic_features.SpecifySymbology;

public class BarcodeImageMargins {
    
	public static void main(String[] args) {
    	// The path to the resource directory.
    	String dataDir = Utils.getDataDir(SpecifySymbology.class) + "BarcodeImage/BasicFeatures/";

        BarCodeBuilder bb = new BarCodeBuilder();
        bb.setCodeText("1234567");
        bb.setSymbologyType(Symbology.Code128);

        bb.getMargins().setLeft(2f);
        bb.getMargins().setRight(2f);
        bb.getMargins().setTop(2f);
        bb.getMargins().setBottom(2f);

        // Save the image
        bb.save(dataDir + "barcode-image-margins.jpg");
    }
}
