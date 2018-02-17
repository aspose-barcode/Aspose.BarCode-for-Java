package com.aspose.barcode.examples.barcode.advanced_features;

import com.aspose.barcode.BarCodeBuilder;
import com.aspose.barcode.examples.Utils;

public class SupplementData {

	public static void main(String[] args) {
		
		// The path to the resource directory.
    	String dataDir = Utils.getDataDir(SupplementData.class) + "Barcode/AdvancedFeatures/";
    	
		BarCodeBuilder bb = new BarCodeBuilder();
		bb.setCodeText("123456789123");
                bb.setEncodeType(com.aspose.barcode.EncodeTypes.EAN_13);
		
		//Set the supplement data (5 Digit)
		bb.setSupplementData("12345");
		//Set space between the supplemental barcode and main barcode
		bb.setSupplementSpace(2.0f);

		//Save the Barcode image to file
    	bb.save(dataDir + "supplementData.jpg");
	}

}
