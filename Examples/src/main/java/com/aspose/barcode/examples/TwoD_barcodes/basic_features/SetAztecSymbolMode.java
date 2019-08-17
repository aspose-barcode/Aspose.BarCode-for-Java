package com.aspose.barcode.examples.TwoD_barcodes.basic_features;

import java.io.IOException;

import com.aspose.barcode.AztecSymbolMode;
import com.aspose.barcode.examples.Utils;
import com.aspose.barcode.generation.BarcodeGenerator;

public class SetAztecSymbolMode {
	public static void main(String[] args) throws IOException {
		// ExStart: SetAztecSymbolMode
		// The path to the resource directory.
		String dataDir = Utils.getDataDir(SetAztecSymbolMode.class) + "TwoD_barcodes/BasicFeatures/";

		// Create an instance of BarCodeGenerator class.
		BarcodeGenerator generator = new BarcodeGenerator(com.aspose.barcode.EncodeTypes.AZTEC, "25");

		// set the AztecSymbolMode property.
		generator.getParameters().getBarcode().getAztec().setAztecSymbolMode(AztecSymbolMode.RUNE);

		// Save the barcode as PNG image.
		generator.save(dataDir + "testRune25.png");
		// ExEnd: SetAztecSymbolMode
	}
}
