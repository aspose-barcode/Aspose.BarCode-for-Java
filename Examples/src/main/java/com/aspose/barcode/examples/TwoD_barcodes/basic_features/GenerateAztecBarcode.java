package com.aspose.barcode.examples.TwoD_barcodes.basic_features;

import com.aspose.barcode.BarCodeBuilder;
import com.aspose.barcode.Symbology;
import com.aspose.barcode.examples.Utils;

public class GenerateAztecBarcode {
	public static void main(String[] args) {
		// The path to the resource directory.
		String dataDir = Utils.getDataDir(GenerateAztecBarcode.class) + "TwoD_barcodes/BasicFeatures/";

		// Initialize BarCode builder class object
		BarCodeBuilder builder = new BarCodeBuilder();

		// Set the barcode text
		builder.setCodeText("25");

		// Set symbology type as Aztec
		builder.setSymbologyType(Symbology.Aztec);

		// save barcode
		builder.save(dataDir + "Aztec.png");
	}
}
