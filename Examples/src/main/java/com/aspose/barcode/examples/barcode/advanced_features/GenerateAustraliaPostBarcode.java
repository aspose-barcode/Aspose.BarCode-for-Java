package com.aspose.barcode.examples.barcode.advanced_features;

import java.io.IOException;

import com.aspose.barcode.EncodeTypes;
import com.aspose.barcode.examples.Utils;
import com.aspose.barcode.generation.BarcodeGenerator;

public class GenerateAustraliaPostBarcode {

	public static void main(String[] args) throws IOException {
		// ExStart: GenerateAustraliaPostBarcode
		// The path to the resource directory.
		String dataDir = Utils.getDataDir(GenerateAustraliaPostBarcode.class) + "Barcode/AdvancedFeatures/";

		// Create instance of BarCodeBuilder, specify code-text and Symbology in the
		// constructor
		BarcodeGenerator generator = new BarcodeGenerator(EncodeTypes.AUSTRALIA_POST, "1234567890");

		// Save the image to disk in PNG format
		generator.save(dataDir + "australiaPostBarcode.png");
		// ExEnd: GenerateAustraliaPostBarcode
	}

}
