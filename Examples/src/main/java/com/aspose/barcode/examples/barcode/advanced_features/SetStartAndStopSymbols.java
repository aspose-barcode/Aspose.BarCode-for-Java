package com.aspose.barcode.examples.barcode.advanced_features;

import java.io.IOException;

import com.aspose.barcode.CodabarSymbol;
import com.aspose.barcode.examples.Utils;
import com.aspose.barcode.generation.BarcodeGenerator;

public class SetStartAndStopSymbols {

	public static void main(String[] args) throws IOException {
		// ExStart: SetStartAndStopSymbols
		// The path to the resource directory.
		String dataDir = Utils.getDataDir(SetStartAndStopSymbols.class) + "Barcode/AdvancedFeatures/";

		// Create instance of BarcodeGenerator, specify codetext and symbology in the
		// constructor
		BarcodeGenerator generator = new BarcodeGenerator(com.aspose.barcode.EncodeTypes.CODABAR, "12345678");

		// Set the Codabar start symbol to A
		generator.getParameters().getBarcode().getCodabar().setCodabarStartSymbol(CodabarSymbol.A);

		// Set the Codabar stop symbol to D
		generator.getParameters().getBarcode().getCodabar().setCodabarStopSymbol(CodabarSymbol.D);

		// Save the image to disk in PNG format
		generator.save(dataDir + "startAndStopSymbols.png");
		// ExEnd: SetStartAndStopSymbols
	}
}
