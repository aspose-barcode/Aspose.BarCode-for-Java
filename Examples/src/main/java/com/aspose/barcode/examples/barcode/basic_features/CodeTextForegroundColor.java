package com.aspose.barcode.examples.barcode.basic_features;

import java.awt.Color;

import com.aspose.barcode.BarCodeBuilder;
import com.aspose.barcode.examples.Utils;

public class CodeTextForegroundColor {

	public static void main(String[] args) {
		// The path to the resource directory.
    	String dataDir = Utils.getDataDir(SpecifySymbology.class) + "Barcode/BasicFeatures/";
    	
		//Instantiate Barcode object
		BarCodeBuilder bb = new BarCodeBuilder();
		//Set up code text (data to be encoded)
		bb.setCodeText("1234567");
		//Set up code text color
		bb.setCodeTextColor(Color.RED);
		
		//Save the Barcode image to file
    	bb.save(dataDir + "codeTextForegroundColor.jpg");
	}

}
