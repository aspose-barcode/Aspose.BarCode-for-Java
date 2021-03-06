package com.aspose.barcode.examples.barcode.advanced_features;

import java.io.IOException;

import com.aspose.barcode.EncodeTypes;
import com.aspose.barcode.examples.Utils;
import com.aspose.barcode.generation.BarcodeGenerator;

public class AlwaysShowChecksum {
	public static void main(String[] args) throws IOException {
		// ExStart: AlwaysShowChecksum
		// The path to the resource directory.
		String dataDir = Utils.getDataDir(Checksum.class) + "Barcode/AdvancedFeatures/";

		BarcodeGenerator generator = new BarcodeGenerator(EncodeTypes.CODE_128, "12345");
		generator.getParameters().getBarcode().setChecksumAlwaysShow(true);

		// Save the Barcode image to file
		generator.save(dataDir + "checksum.jpg");
		// ExEnd: AlwaysShowChecksum
	}

}
