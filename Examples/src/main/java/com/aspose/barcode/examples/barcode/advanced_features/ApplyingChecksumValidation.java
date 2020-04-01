package com.aspose.barcode.examples.barcode.advanced_features;

import com.aspose.barcode.barcoderecognition.BarCodeReader;
import com.aspose.barcode.barcoderecognition.BarCodeResult;
import com.aspose.barcode.barcoderecognition.ChecksumValidation;
import com.aspose.barcode.barcoderecognition.DecodeType;
import com.aspose.barcode.examples.Utils;

public class ApplyingChecksumValidation {
	public static void main(String[] args) {
		// ExStart: ApplyingChecksumValidation
		// The path to the resource directory.
		String dataDir = Utils.getDataDir(ApplyingChecksumValidation.class) + "Barcode/AdvancedFeatures/";
		
		// Create an instance of BarCodeReader class and load an existing
		// oncecode barcode.
		BarCodeReader reader = new BarCodeReader(dataDir + "onecode.png", DecodeType.ONE_CODE);

		// Set the ChecksumValidation property to Off.
		reader.setChecksumValidation(ChecksumValidation.OFF);

		for (BarCodeResult result : reader.readBarCodes()) {
			System.out.println("CodeText: " + result.getCodeText());
			System.out.println("Symbology type: " + result.getCodeType());
		}
		// ExEnd: ApplyingChecksumValidation
	}
}
