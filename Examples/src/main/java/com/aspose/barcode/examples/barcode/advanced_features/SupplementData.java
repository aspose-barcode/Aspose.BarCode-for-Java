package com.aspose.barcode.examples.barcode.advanced_features;

import java.io.IOException;

import com.aspose.barcode.BarCodeBuilder;
import com.aspose.barcode.examples.Utils;
import com.aspose.barcode.generation.BarCodeGenerator;

public class SupplementData {

	public static void main(String[] args) throws IOException {
		
		// The path to the resource directory.
    	String dataDir = Utils.getDataDir(SupplementData.class) + "Barcode/AdvancedFeatures/";
    	
    	// Create instance of BarCodeGenerator, specify codetext and symbology in the constructor
    	BarCodeGenerator generator = new BarCodeGenerator(com.aspose.barcode.EncodeTypes.EAN_13, "123456789123");
		
		//Set the supplement data (5 Digit)
		generator.getSupplement().setData("12345");
		
		//Set space between the supplemental barcode and main barcode
		generator.getSupplement().getSpace().setPoint(2.0f);

		//Save the Barcode image to file
		generator.save(dataDir + "supplementData.jpg");
	}

}
