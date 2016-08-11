package com.aspose.barcode.examples.barcode.advanced_features;

import com.aspose.barcode.BarCodeBuilder;
import com.aspose.barcode.EnableChecksum;
import com.aspose.barcode.Symbology;
import com.aspose.barcode.examples.Utils;
import com.aspose.barcode.examples.barcode.basic_features.SpecifySymbology;

public class Checksum {

	public static void main(String[] args) {
		// The path to the resource directory.
    	String dataDir = Utils.getDataDir(SpecifySymbology.class) + "Barcode/AdvancedFeatures/";
    	
		BarCodeBuilder bb = new BarCodeBuilder();
		bb.setCodeText("1234567");
		bb.setSymbologyType(Symbology.Code39Standard);

		//Make the checksum to be visible on the barcode
		bb.setEnableChecksum(EnableChecksum.Default);

		//Save the Barcode image to file
    	bb.save(dataDir + "checksum.jpg");
	}

}
