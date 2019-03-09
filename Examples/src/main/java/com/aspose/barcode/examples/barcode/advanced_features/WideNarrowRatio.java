package com.aspose.barcode.examples.barcode.advanced_features;

import java.io.IOException;

import com.aspose.barcode.BarCodeBuilder;
import com.aspose.barcode.examples.Utils;
import com.aspose.barcode.generation.BarCodeGenerator;

public class WideNarrowRatio {

	public static void main(String[] args) throws IOException {
		//ExStart: WideNarrowRatio
		// The path to the resource directory.
    	String dataDir = Utils.getDataDir(WideNarrowRatio.class) + "Barcode/AdvancedFeatures/";
    	
		//Instantiate barcode object
    	// Create instance of BarCodeGenerator, specify codetext and symbology in the constructor
    	BarCodeGenerator generator = new BarCodeGenerator(com.aspose.barcode.EncodeTypes.CODE_39_EXTENDED, "12345678");

		//Set the wide to narrow ratio for the barcode
    	generator.setWideNarrowRatio(3.0f);
		
		// Save the image to disk in PNG format
    	generator.save(dataDir + "wideNarrowRatio.png");
    	//ExEnd: WideNarrowRatio
	}

}
