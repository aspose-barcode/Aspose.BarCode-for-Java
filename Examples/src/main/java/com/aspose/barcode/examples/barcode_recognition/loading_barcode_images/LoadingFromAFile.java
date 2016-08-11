package com.aspose.barcode.examples.barcode_recognition.loading_barcode_images;

import com.aspose.barcode.barcoderecognition.BarCodeReadType;
import com.aspose.barcode.barcoderecognition.BarCodeReader;
import com.aspose.barcode.examples.Utils;
import com.aspose.barcode.examples.barcode.basic_features.SpecifySymbology;

public class LoadingFromAFile {

	public static void main(String[] args) {
		// The path to the resource directory.
		String dataDir = Utils.getDataDir(SpecifySymbology.class) + "BarcodeReader/loading_images/";
				
		// Initialize bar code reader
		BarCodeReader reader = new BarCodeReader(dataDir + "CodeText.jpg");

		//OR

		// Initialize bar code reader
		BarCodeReader barcodeReader = new BarCodeReader(dataDir + "CodeText.jpg", BarCodeReadType.Code39Extended);
	}

}
