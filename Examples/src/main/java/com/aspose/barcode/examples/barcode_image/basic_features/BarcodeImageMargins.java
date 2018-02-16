package com.aspose.barcode.examples.barcode_image.basic_features;

import com.aspose.barcode.BarCodeBuilder;
import com.aspose.barcode.examples.Utils;

public class BarcodeImageMargins {
    
	public static void main(String[] args) {
    	// The path to the resource directory.
    	String dataDir = Utils.getDataDir(BarcodeImageMargins.class) + "BarcodeImage/BasicFeatures/";

        BarCodeBuilder bb = new BarCodeBuilder();
        bb.setCodeText("1234567");
        bb.setEncodeType(com.aspose.barcode.EncodeTypes.CODE_128);

        bb.getMargins().setLeft(2f);
        bb.getMargins().setRight(2f);
        bb.getMargins().setTop(2f);
        bb.getMargins().setBottom(2f);

        // Save the image
        bb.save(dataDir + "barcode-image-margins.jpg");
    }
}
