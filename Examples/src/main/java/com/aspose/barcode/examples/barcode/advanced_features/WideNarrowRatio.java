package com.aspose.barcode.examples.barcode.advanced_features;

import com.aspose.barcode.BarCodeBuilder;
import com.aspose.barcode.Symbology;
import com.aspose.barcode.examples.Utils;

public class WideNarrowRatio {

	public static void main(String[] args) {
		// The path to the resource directory.
    	String dataDir = Utils.getDataDir(WideNarrowRatio.class) + "Barcode/AdvancedFeatures/";
    	
		//Instantiate barcode object
		BarCodeBuilder bb = new BarCodeBuilder();
		//Set the code text of the barcode
		bb.setCodeText("12345678");
		//Set the symbology type to code39
		bb.setSymbologyType(Symbology.Code39Extended);
		//Set the wide to narrow ratio for the barcode
		bb.setWideNarrowRatio(3.0f);
		
		// Save the image to disk in PNG format
		bb.save(dataDir + "wideNarrowRatio.png");
	}

}
