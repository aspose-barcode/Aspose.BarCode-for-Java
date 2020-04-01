package com.aspose.barcode.examples.barcode_recognition.advanced_features;

import com.aspose.barcode.barcoderecognition.BarCodeReader;
import com.aspose.barcode.barcoderecognition.BarCodeResult;
import com.aspose.barcode.barcoderecognition.QualitySettings;
import com.aspose.barcode.examples.ApplyALicense;
import com.aspose.barcode.examples.Utils;

public class FasterImageProcessingForBarcodeRecognition {

	public static void main(String[] args) throws Exception {
		ApplyALicense.applyALicense();

		// ExStart: FasterImageProcessingForBarcodeRecognition
		// The path to the resource directory.
		String dataDir = Utils.getDataDir(FasterImageProcessingForBarcodeRecognition.class)
				+ "BarcodeReader/advanced_features/";

		// Read code39 barcode from image
		String imageFilePath = dataDir + "datamatrix.bmp";

		// Create an instance of BarCodeReader and set image and symbology type to
		// recognize
		BarCodeReader reader = new BarCodeReader(imageFilePath,
				com.aspose.barcode.barcoderecognition.DecodeType.DATA_MATRIX);

		// set high performance mode
		reader.setQualitySettings(QualitySettings.getHighPerformance());

		// set separate options
		reader.getQualitySettings().setAllowMedianSmoothing(true);
		reader.getQualitySettings().setMedianSmoothingWindowSize(4);

		// Try to recognize the barcode from the image
		for (BarCodeResult result : reader.readBarCodes()) {
			System.out.println("BarCode CodeText: " + result.getCodeText());
			System.out.println("BarCode CodeType: " + result.getCodeTypeName());
		}
		// ExEnd: FasterImageProcessingForBarcodeRecognition
	}

}
