package com.aspose.barcode.examples.barcode.advanced_features;

import java.io.IOException;

import com.aspose.barcode.EnableChecksum;
import com.aspose.barcode.examples.Utils;
import com.aspose.barcode.generation.BarCodeGenerator;

public class Checksum {

	public static void main(String[] args) throws IOException {
		// The path to the resource directory.
    	String dataDir = Utils.getDataDir(Checksum.class) + "Barcode/AdvancedFeatures/";
    	
		BarCodeGenerator bb = new BarCodeGenerator(com.aspose.barcode.EncodeTypes.CODE_39_STANDARD, "1234567");

		//Make the checksum to be visible on the barcode
		bb.getChecksum().setEnable(EnableChecksum.DEFAULT);

		//Save the Barcode image to file
    	bb.save(dataDir + "checksum.jpg");
	}

}
