package com.aspose.barcode.examples.barcode_image.basic_features;

import java.io.IOException;

import com.aspose.barcode.BarCodeBuilder;
import com.aspose.barcode.examples.Utils;
import com.aspose.barcode.generation.BarCodeGenerator;

public class BarcodeImageMargins {
    
	public static void main(String[] args) throws IOException {
		//ExStart: BarcodeImageMargins
    	// The path to the resource directory.
    	String dataDir = Utils.getDataDir(BarcodeImageMargins.class) + "BarcodeImage/BasicFeatures/";

        BarCodeGenerator bb = new BarCodeGenerator(com.aspose.barcode.EncodeTypes.CODE_128, "1234567");

        //Set border margins for Top, Right, Left and Bottom
        bb.getMargins().getTop().setPixels(2f);
        bb.getMargins().getRight().setPixels(2f);
        bb.getMargins().getLeft().setPixels(2f);
        bb.getMargins().getBottom().setPixels(2f);

        // Save the image
        bb.save(dataDir + "barcode-image-margins.jpg");
        //ExEnd: BarcodeImageMargins
    }
}
