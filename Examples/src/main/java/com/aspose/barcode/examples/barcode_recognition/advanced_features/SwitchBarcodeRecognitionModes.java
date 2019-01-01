package com.aspose.barcode.examples.barcode_recognition.advanced_features;

import com.aspose.barcode.barcoderecognition.BarCodeReader;
import com.aspose.barcode.barcoderecognition.ManualHint;
import com.aspose.barcode.barcoderecognition.RecognitionMode;
import com.aspose.barcode.examples.ApplyALicense;
import com.aspose.barcode.examples.Utils;

public class SwitchBarcodeRecognitionModes {

	public static void main(String[] args) throws Exception {
		ApplyALicense.applyALicense();

		// The path to the resource directory.
		String dataDir = Utils.getDataDir(SwitchBarcodeRecognitionModes.class) + "BarcodeReader/advanced_features/";
		
		// Create an instance of BarCodeReader and set image and symbology type to recognize
		BarCodeReader reader = new BarCodeReader(dataDir + "code39Extended.jpg", com.aspose.barcode.barcoderecognition.DecodeType.DATA_MATRIX);

		// Set recognition mode
		reader.setRecognitionMode(RecognitionMode.ManualHints);

		// Set manual hints
		reader.setManualHints(ManualHint.INVERT_IMAGE | ManualHint.INCORRECT_BARCODES);

		// Try to recognize the barcode from the image
		while (reader.read()) {
			// Display the CodeText
			System.out.println("Codetext: " + reader.getCodeText());
		}

		// Close the reader
		reader.close();
	}

}
