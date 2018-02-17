package com.aspose.barcode.examples.barcode.advanced_features;

import com.aspose.barcode.BarCodeBuilder;
import com.aspose.barcode.examples.Utils;

public class SetBarsHeight {

	public static void main(String[] args) {
		// The path to the resource directory.
    	String dataDir = Utils.getDataDir(SetBarsHeight.class) + "Barcode/AdvancedFeatures/";
    	
		//Instantiate barcode object
		BarCodeBuilder bb = new BarCodeBuilder();
		bb.setCodeText("12345678");
                bb.setEncodeType(com.aspose.barcode.EncodeTypes.CODE_128);
                
		//Set the bar height to be 3 milimeter
		bb.setBarHeight(3.0f);
		
		//Save the Barcode image to file
    	bb.save(dataDir + "barsHeight.jpg");
	}

}
