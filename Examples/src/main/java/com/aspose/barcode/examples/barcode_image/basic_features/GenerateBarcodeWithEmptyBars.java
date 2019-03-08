package com.aspose.barcode.examples.barcode_image.basic_features;

import java.io.IOException;

import com.aspose.barcode.BarCodeImageFormat;
import com.aspose.barcode.examples.Utils;
import com.aspose.barcode.generation.BarCodeGenerator;

public class GenerateBarcodeWithEmptyBars {

	public static void main(String[] args) throws IOException {
		//ExStart: GenerateBarcodeWithEmptyBars
		// The path to the resource directory.
		String dataDir = Utils.getDataDir(GenerateBarcodeWithEmptyBars.class) + "BarcodeImage/BasicFeatures/";

		//Create an instance of BarCodeGenerator and initialize it with CodeText and Symbology
		BarCodeGenerator generator = new BarCodeGenerator(com.aspose.barcode.EncodeTypes.CODE_128, "TEXT");

		//Set the FilledBars property to false
		generator.setFilledBars(false);

		//Save the resultant barcode image on disk
		generator.save(dataDir + "barcodeWithEmptyBars.png", BarCodeImageFormat.PNG);
		//ExEnd: GenerateBarcodeWithEmptyBars
	}

}
