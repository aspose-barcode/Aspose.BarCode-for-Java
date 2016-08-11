package com.aspose.barcode.examples.barcode_recognition.advanced_features;

import com.aspose.barcode.barcoderecognition.BarCodeReadType;
import com.aspose.barcode.barcoderecognition.BarCodeReader;
import com.aspose.barcode.barcoderecognition.RecognitionHints.Orientation;
import com.aspose.barcode.examples.ApplyALicense;
import com.aspose.barcode.examples.Utils;
import com.aspose.barcode.examples.barcode.basic_features.SpecifySymbology;

public class BarcodeOrientation {

	public static void main(String[] args) throws Exception {
		ApplyALicense.applyALicense();
		
		// The path to the resource directory.
    	String dataDir = Utils.getDataDir(SpecifySymbology.class) + "BarcodeReader/advanced_features/";
    	
		// Read code39 barcode from image
		String image = dataDir + "code39Extended.jpg";
		BarCodeReader reader = new BarCodeReader(image, BarCodeReadType.Code39Standard);
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
