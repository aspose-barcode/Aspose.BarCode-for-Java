package com.aspose.barcode.examples.barcode.advanced_features;

import java.io.IOException;
import com.aspose.barcode.examples.Utils;
import com.aspose.barcode.generation.BarCodeGenerator;

public class BarcodeWithSegments 
{

	public static void main(String[] args) throws IOException 
    {
		// ExStart:BarcodeWithSegments

		// The path to the resource directory.
		String dataDir = Utils.getDataDir(ApplyingChecksumValidation.class) + "Barcode/AdvancedFeatures/";

		//Instantiate BarCodeBuilder object
		BarCodeGenerator generator = new BarCodeGenerator(com.aspose.barcode.EncodeTypes.DATABAR_EXPANDED_STACKED, "(01)98898765432106(3202)012345(15)991231"); 

		//Set the column property to define segments per row
		generator.getD2().setColumns(6);
		
		//Save the image
		generator.save(dataDir + "6segmets.png");
		// ExEnd:BarcodeWithSegments
	}
}
