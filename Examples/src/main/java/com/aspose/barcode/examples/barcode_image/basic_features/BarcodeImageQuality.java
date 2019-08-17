package com.aspose.barcode.examples.barcode_image.basic_features;

import java.io.IOException;

import com.aspose.barcode.examples.Utils;
import com.aspose.barcode.generation.BarcodeGenerator;

public class BarcodeImageQuality {
    public static void main(String[] args) throws IOException {
		//ExStart: BarcodeImageQuality
		// The path to the resource directory.
		String dataDir = Utils.getDataDir(BarcodeImageQuality.class) + "BarcodeImage/BasicFeatures/";
		
		BarcodeGenerator bb = new BarcodeGenerator(com.aspose.barcode.EncodeTypes.CODE_128,"1234567");
		
		bb.getParameters().setResolution(400);
		
		// Save the image
		bb.save(dataDir + "barcode-image-quality.jpg");
		//ExEnd: BarcodeImageQuality
    }
}
