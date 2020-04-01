package com.aspose.barcode.examples.barcode_recognition.advanced_features;

import com.aspose.barcode.barcoderecognition.BarCodeReader;
import com.aspose.barcode.barcoderecognition.BarCodeResult;
import com.aspose.barcode.examples.ApplyALicense;
import com.aspose.barcode.examples.Utils;

public class GetBarCodeRecognitionQualityInPercent {

	public static void main(String[] args) throws Exception {
		ApplyALicense.applyALicense();

		// ExStart: GetBarCodeRecognitionQualityInPercent
		// The path to the resource directory.
		String dataDir = Utils.getDataDir(GetBarCodeRecognitionQualityInPercent.class)
				+ "BarcodeReader/advanced_features/";

		// Initialize the BarCodeReader object
		BarCodeReader reader = new BarCodeReader(dataDir + "code39Extended.jpg",
				com.aspose.barcode.barcoderecognition.DecodeType.ALL_SUPPORTED_TYPES);

		// Call read method
		for (BarCodeResult result : reader.readBarCodes()) {
			System.out.println(result.getCodeText() + " Type: " + result.getCodeType());
			double percent = result.getReadingQuality();
			System.out.println("Percent: " + percent);
		}
		// ExEnd: GetBarCodeRecognitionQualityInPercent
	}

}
