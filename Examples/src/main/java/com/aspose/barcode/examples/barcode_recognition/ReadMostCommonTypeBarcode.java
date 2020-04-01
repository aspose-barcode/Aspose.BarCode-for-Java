package com.aspose.barcode.examples.barcode_recognition;

import com.aspose.barcode.barcoderecognition.BarCodeReader;
import com.aspose.barcode.barcoderecognition.BarCodeResult;
import com.aspose.barcode.barcoderecognition.DecodeType;
import com.aspose.barcode.examples.Utils;
import com.aspose.barcode.examples.barcode_image.utility_features.BarcodeCustomSize;

public class ReadMostCommonTypeBarcode {

	public static void main(String[] args) throws Exception {
		// The path to the resource directory.
		String dataDir = Utils.getDataDir(BarcodeCustomSize.class) + "BarcodeImage/UtilityFeatures/";

		// ExStart: ReadMostCommonTypeBarcode
		BarCodeReader reader = new BarCodeReader(dataDir + "test.png", DecodeType.MOST_COMMON_TYPES);

		for (BarCodeResult result : reader.readBarCodes()) {
			System.out.println("BarCode CodeText: " + result.getCodeText());
			System.out.println("BarCode CodeType: " + result.getCodeTypeName());
		}
		// ExEnd:ReadMostCommonTypeBarcode
	}
}
