package com.aspose.barcode.examples.barcode_recognition.loading_barcode_images;

import com.aspose.barcode.barcoderecognition.BarCodeReader;
import com.aspose.barcode.examples.Utils;

public class LoadingFromAFile {

	public static void main(String[] args) {
		//ExStart: LoadingFromAFile
		// The path to the resource directory.
		String dataDir = Utils.getDataDir(LoadingFromAFile.class) + "BarcodeReader/loading_images/";
				
		// Initialize bar code reader
		BarCodeReader reader = new BarCodeReader(dataDir + "CodeText.jpg");

		//OR

		// Initialize bar code reader
		BarCodeReader barcodeReader = new BarCodeReader(dataDir + "CodeText.jpg", com.aspose.barcode.barcoderecognition.DecodeType.CODE_39_EXTENDED);
		//ExEnd: LoadingFromAFile
	}

}
