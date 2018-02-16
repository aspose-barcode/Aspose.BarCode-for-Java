package com.aspose.barcode.examples.barcode.advanced_features;
import com.aspose.barcode.BarCodeBuilder;
import com.aspose.barcode.examples.Utils;

public class GenerateAustraliaPostBarcode {

	public static void main(String[] args) {
		// The path to the resource directory.
    	String dataDir = Utils.getDataDir(GenerateAustraliaPostBarcode.class) + "Barcode/AdvancedFeatures/";
    	
		// Create instance of BarCodeBuilder, specify code-text and Symbology in the constructor
		BarCodeBuilder builder = new BarCodeBuilder("1234567890", com.aspose.barcode.EncodeTypes.AUSTRALIA_POST);
		
		// Save the image to disk in PNG format
		builder.save(dataDir + "australiaPostBarcode.png");
	}

}
