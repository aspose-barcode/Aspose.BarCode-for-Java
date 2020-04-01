package com.aspose.barcode.examples.barcode_recognition.advanced_features;

import com.aspose.barcode.barcoderecognition.BarCodeReader;
import com.aspose.barcode.barcoderecognition.BarCodeResult;
import com.aspose.barcode.barcoderecognition.QualitySettings;
import com.aspose.barcode.examples.ApplyALicense;
import com.aspose.barcode.examples.Utils;

public class SwitchBarcodeRecognitionModes {

	public static void main(String[] args) throws Exception {
		ApplyALicense.applyALicense();

		// ExStart: SwitchBarcodeRecognitionModes
		// The path to the resource directory.
		String dataDir = Utils.getDataDir(SwitchBarcodeRecognitionModes.class) + "BarcodeReader/advanced_features/";

		// Create an instance of BarCodeReader and set image and symbology type to
		// recognize
		BarCodeReader reader = new BarCodeReader(dataDir + "code39Extended.jpg",
				com.aspose.barcode.barcoderecognition.DecodeType.DATA_MATRIX);

		// Set recognition mode
		reader.setQualitySettings(QualitySettings.getHighPerformance());

		// Try to recognize the barcode from the image
		for (BarCodeResult result : reader.readBarCodes()) {
			System.out.println("CodeText: " + result.getCodeText());
			System.out.println("Symbology type: " + result.getCodeType());
		}
		// ExEnd: SwitchBarcodeRecognitionModes
	}

}
