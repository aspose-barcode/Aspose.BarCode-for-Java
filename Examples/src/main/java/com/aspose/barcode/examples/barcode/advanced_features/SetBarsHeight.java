package com.aspose.barcode.examples.barcode.advanced_features;

import java.io.IOException;

import com.aspose.barcode.examples.Utils;
import com.aspose.barcode.generation.BarcodeGenerator;

public class SetBarsHeight {

	public static void main(String[] args) throws IOException {
		// ExStart: SetBarsHeight
		// The path to the resource directory.
		String dataDir = Utils.getDataDir(SetBarsHeight.class) + "Barcode/AdvancedFeatures/";

		// Instantiate barcode object
		BarcodeGenerator generator = new BarcodeGenerator(com.aspose.barcode.EncodeTypes.CODE_128, "12345678");

		// Set the bar height to be 3 milimeter
		generator.getParameters().getBarcode().getBarHeight().setMillimeters(3.0f);

		// Save the Barcode image to file
		generator.save(dataDir + "barsHeight.jpg");
		// ExEnd: SetBarsHeight
	}

}
