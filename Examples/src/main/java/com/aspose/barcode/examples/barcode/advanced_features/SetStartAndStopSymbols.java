package com.aspose.barcode.examples.barcode.advanced_features;

import com.aspose.barcode.BarCodeBuilder;
import com.aspose.barcode.CodabarSymbol;
import com.aspose.barcode.Symbology;
import com.aspose.barcode.examples.Utils;
import com.aspose.barcode.examples.barcode.basic_features.SpecifySymbology;

public class SetStartAndStopSymbols {

	public static void main(String[] args) {
		// The path to the resource directory.
    	String dataDir = Utils.getDataDir(SpecifySymbology.class) + "Barcode/AdvancedFeatures/";
    	
		// Create instance of BarCodeBuilder, specify codetext and symbology in the constructor
		BarCodeBuilder builder = new BarCodeBuilder("12345678", Symbology.Codabar);

		// Set the codabar start symbol to A
		builder.setCodabarStartSymbol(CodabarSymbol.A);

		// Set the codabar stop symbol to D
		builder.setCodabarStopSymbol(CodabarSymbol.D);

		// Save the image to disk in PNG format
		builder.save(dataDir + "startAndStopSymbols.png");
	}

}
