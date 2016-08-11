package com.aspose.barcode.examples.barcode.advanced_features;

import com.aspose.barcode.BarCodeBuilder;
import com.aspose.barcode.Symbology;
import com.aspose.barcode.examples.Utils;
import com.aspose.barcode.examples.barcode.basic_features.SpecifySymbology;

public class SetBarsHeight {

	public static void main(String[] args) {
		// The path to the resource directory.
    	String dataDir = Utils.getDataDir(SpecifySymbology.class) + "Barcode/AdvancedFeatures/";
    	
		//Instantiate barcode object
		BarCodeBuilder bb = new BarCodeBuilder();
		bb.setCodeText("12345678");
		bb.setSymbologyType(Symbology.Code128);
		//Set the bar height to be 3 milimeter
		bb.setBarHeight(3.0f);
		
		//Save the Barcode image to file
    	bb.save(dataDir + "barsHeight.jpg");
	}

}
