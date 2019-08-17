package com.aspose.barcode.examples.barcode.advanced_features;

import com.aspose.barcode.barcoderecognition.BarCodeReader;
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
		BarCodeReader r = new BarCodeReader(dataDir + "onecode.png", DecodeType.ONE_CODE);

		// Set the ChecksumValidation property to Off.
		r.setChecksumValidation(ChecksumValidation.OFF);

		while (r.read()) {
			System.out.println(r.getCodeType() + ": " + r.getCodeText());
			System.out.println("CheckSum: " + r.getCheckSum());
		}
		// ExEnd: ApplyingChecksumValidation
	}
}
