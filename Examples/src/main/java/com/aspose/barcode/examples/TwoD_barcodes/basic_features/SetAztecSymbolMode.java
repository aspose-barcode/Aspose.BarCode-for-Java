package com.aspose.barcode.examples.TwoD_barcodes.basic_features;

import com.aspose.barcode.AztecSymbolMode;
import com.aspose.barcode.BarCodeBuilder;
import com.aspose.barcode.Symbology;
import com.aspose.barcode.examples.Utils;

public class SetAztecSymbolMode {
	public static void main(String[] args) {
		// The path to the resource directory.
		String dataDir = Utils.getDataDir(SetAztecSymbolMode.class) + "TwoD_barcodes/BasicFeatures/";
		// Create an instance of BarCodeBuilder class.
		BarCodeBuilder b = new BarCodeBuilder();

		// Set the Code text.
		b.setCodeText("25");

		// Set the barcode type.
		b.setSymbologyType(Symbology.Aztec);

		// set the AztecSymbolMode property.
		b.setAztecSymbolMode(AztecSymbolMode.Rune);

		// Save the barcode as PNG image.
		b.save(dataDir + "testRune25.png");
	}
}
