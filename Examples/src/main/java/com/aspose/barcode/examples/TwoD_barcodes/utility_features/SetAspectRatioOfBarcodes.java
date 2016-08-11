package com.aspose.barcode.examples.TwoD_barcodes.utility_features;

import com.aspose.barcode.BarCodeBuilder;
import com.aspose.barcode.Symbology;
import com.aspose.barcode.examples.Utils;

public class SetAspectRatioOfBarcodes {

	public static void main(String[] args) {
		String dataDir = Utils.getDataDir(SetAspectRatioOfBarcodes.class) + "2DBarcode/UtilityFeatures/";
		
		// Create instance of BarCodeBuilder class
		BarCodeBuilder builder = new BarCodeBuilder("1234567890", Symbology.Pdf417);
		
		// Set Aspect Ratio to 3:2 or 1.5
		builder.setAspectRatio(1.5f);
		
		// Save the barcode image to disk in PNG format
		builder.save(dataDir + "barcode_aspect_ratio.png");

	}

}
