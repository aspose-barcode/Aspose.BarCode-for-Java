package com.aspose.barcode.examples.barcode_recognition;

import com.aspose.barcode.barcoderecognition.BarCodeReader;
import com.aspose.barcode.barcoderecognition.DecodeType;

public class ReadPostalTypesBarcode {

	public static void main(String[] args) throws Exception {
		// ExStart: ReadPostalTypesBarcode
		BarCodeReader reader = new BarCodeReader("test.png", DecodeType.POSTAL_TYPES);

		reader.readBarCodes();
		for (int i = 0; reader.getFoundCount() > i; ++i) {
			System.out.println("BarCode CodeText: " + reader.getFoundBarCodes()[i].getCodeText());
			System.out.println("BarCode CodeType: " + reader.getFoundBarCodes()[i].getCodeTypeName());
		}
		// ExEnd: ReadPostalTypesBarcode
	}
}
