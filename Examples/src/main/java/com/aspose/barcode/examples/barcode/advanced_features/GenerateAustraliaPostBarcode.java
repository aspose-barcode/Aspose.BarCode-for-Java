package com.aspose.barcode.examples.barcode.advanced_features;

import com.aspose.barcode.AustraliaPostFormatControlCode;
import com.aspose.barcode.BarCodeBuilder;
import com.aspose.barcode.Symbology;
import com.aspose.barcode.examples.Utils;
import com.aspose.barcode.examples.barcode.basic_features.SpecifySymbology;

public class GenerateAustraliaPostBarcode {

	public static void main(String[] args) {
		// The path to the resource directory.
    	String dataDir = Utils.getDataDir(SpecifySymbology.class) + "Barcode/AdvancedFeatures/";
    	
		// Create instance of BarCodeBuilder, specify code-text and Symbology in the constructor
		BarCodeBuilder builder = new BarCodeBuilder("1234567890", Symbology.AustraliaPost);
		// Set format control code to standard
		builder.setAustraliaPostFormatControlCode(AustraliaPostFormatControlCode.Standard);

		// Save the image to disk in PNG format
		builder.save(dataDir + "australiaPostBarcode.png");
	}

}
