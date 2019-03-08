package com.aspose.barcode.examples.barcode.basic_features;

import java.io.IOException;

import com.aspose.barcode.CodeLocation;
import com.aspose.barcode.examples.Utils;
import com.aspose.barcode.generation.BarCodeGenerator;

public class CodeTextLocation {

	public static void main(String[] args) throws IOException {
		
		// The path to the resource directory.
    	String dataDir = Utils.getDataDir(CodeTextLocation.class) + "Barcode/BasicFeatures/";
    	
    	// Create instance of BarCodeGenerator, specify codetext and symbology in the constructor
    	BarCodeGenerator generator = new BarCodeGenerator(com.aspose.barcode.EncodeTypes.DATA_MATRIX, "GTIN:898978777776665655 " + "UID: 121212121212121212 " + "Batch:GH768 " + "Exp.Date:150923");
		
    	//Set up code text color
    	generator.getCodeTextStyle().setLocation(CodeLocation.ABOVE);
    	
    	generator.save(dataDir + "codetextAbove.png");
	}

}
