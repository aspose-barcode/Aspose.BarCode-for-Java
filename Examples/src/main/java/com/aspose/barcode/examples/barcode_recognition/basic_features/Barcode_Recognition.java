package com.aspose.barcode.examples.barcode_recognition.basic_features;

import com.aspose.barcode.barcoderecognition.BarCodeReader;
import com.aspose.barcode.barcoderecognition.BarCodeResult;
import com.aspose.barcode.examples.ApplyALicense;
import com.aspose.barcode.examples.Utils;

public class Barcode_Recognition {

	public static void main(String[] args) throws Exception {

		ApplyALicense.applyALicense();

		// ExStart: Barcode_Recognition
		// The path to the resource directory.
		String dataDir = Utils.getDataDir(Barcode_Recognition.class) + "BarcodeReader/basic_features/";

		// Initialize barcode reader
		BarCodeReader reader = new BarCodeReader(dataDir + "CodeText.jpg");
		
		// read barcode of type Code39Extended
		for (BarCodeResult result : reader.readBarCodes()) {
			System.out.println("CodeText: " + result.getCodeText());
			System.out.println("Symbology type: " + result.getCodeType());
		}
		// ExEnd: Barcode_Recognition
	}
}
