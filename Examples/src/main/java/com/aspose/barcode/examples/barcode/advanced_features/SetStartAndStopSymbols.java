package com.aspose.barcode.examples.barcode.advanced_features;

import java.io.IOException;

import com.aspose.barcode.CodabarSymbol;
import com.aspose.barcode.examples.Utils;
import com.aspose.barcode.generation.BarCodeGenerator;

public class SetStartAndStopSymbols {

	public static void main(String[] args) throws IOException {
		//ExStart: SetStartAndStopSymbols
		// The path to the resource directory.
    	String dataDir = Utils.getDataDir(SetStartAndStopSymbols.class) + "Barcode/AdvancedFeatures/";
    	
		// Create instance of BarCodeGenerator, specify codetext and symbology in the constructor
		BarCodeGenerator generator = new BarCodeGenerator(com.aspose.barcode.EncodeTypes.CODABAR, "12345678");

		// Set the codabar start symbol to A
		generator.getCodabar().setStartSymbol(CodabarSymbol.A);

		// Set the codabar stop symbol to D
		generator.getCodabar().setStopSymbol(CodabarSymbol.D);

		// Save the image to disk in PNG format
		generator.save(dataDir + "startAndStopSymbols.png");
		//ExEnd: SetStartAndStopSymbols
	}
}
