package com.aspose.barcode.examples.barcode.advanced_features;

import com.aspose.barcode.BarCodeBuilder;
import com.aspose.barcode.EnableChecksum;
import com.aspose.barcode.examples.Utils;

public class Checksum {

	public static void main(String[] args) {
		// The path to the resource directory.
    	String dataDir = Utils.getDataDir(Checksum.class) + "Barcode/AdvancedFeatures/";
    	
		BarCodeBuilder bb = new BarCodeBuilder();
		bb.setCodeText("1234567");
                bb.setEncodeType(com.aspose.barcode.EncodeTypes.CODE_39_STANDARD);

		//Make the checksum to be visible on the barcode
		bb.setEnableChecksum(EnableChecksum.Default);

		//Save the Barcode image to file
    	bb.save(dataDir + "checksum.jpg");
	}

}
