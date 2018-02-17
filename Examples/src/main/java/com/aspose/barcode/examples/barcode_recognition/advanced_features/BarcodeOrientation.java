package com.aspose.barcode.examples.barcode_recognition.advanced_features;

import com.aspose.barcode.barcoderecognition.BarCodeReader;
import com.aspose.barcode.barcoderecognition.RecognitionHints.Orientation;
import com.aspose.barcode.examples.ApplyALicense;
import com.aspose.barcode.examples.Utils;

public class BarcodeOrientation {

	public static void main(String[] args) throws Exception {
		ApplyALicense.applyALicense();
		
		// The path to the resource directory.
    	String dataDir = Utils.getDataDir(BarcodeOrientation.class) + "BarcodeReader/advanced_features/";
    	
		// Read code39 barcode from image
		String image = dataDir + "code39Extended.jpg";
		BarCodeReader reader = new BarCodeReader(image, com.aspose.barcode.barcoderecognition.DecodeType.CODE_39_STANDARD);
		// Set orientation
		reader.setOrientationHints(Orientation.Rotate90);
		// Try to recognize all possible barcodes in the image
		while (reader.read()) {
			System.err.println("Codetext: " + reader.getCodeText());
		}
		// Close reader
		reader.close();
	}

}
