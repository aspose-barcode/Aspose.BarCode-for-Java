package com.aspose.barcode.examples.barcode_image.basic_features;

import java.io.IOException;

import com.aspose.barcode.BarCodeBuilder;
import com.aspose.barcode.BarCodeImageFormat;
import com.aspose.barcode.examples.Utils;

public class GenerateBarcodeWithEmptyBars {

	public static void main(String[] args) throws IOException {
		// The path to the resource directory.
		String dataDir = Utils.getDataDir(GenerateBarcodeWithEmptyBars.class) + "BarcodeImage/BasicFeatures/";

		//Create an instance of BarCodeBuilder and initialize it with CodeText and Symbology
		BarCodeBuilder builder = new BarCodeBuilder("TEXT", com.aspose.barcode.EncodeTypes.CODE_128);

		//Set the FilledBars property to false
		builder.setFilledBars(false);

		//Save the resultant barcode image on disk
		builder.save(dataDir + "barcodeWithEmptyBars.png", BarCodeImageFormat.Png);
	}

}
