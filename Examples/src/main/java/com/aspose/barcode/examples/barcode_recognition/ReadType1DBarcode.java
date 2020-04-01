package com.aspose.barcode.examples.barcode_recognition;

import com.aspose.barcode.barcoderecognition.BarCodeReader;
import com.aspose.barcode.barcoderecognition.BarCodeResult;
import com.aspose.barcode.barcoderecognition.DecodeType;

public class ReadType1DBarcode {
	public static void main(String[] args) throws Exception {
		// ExStart: ReadType1DBarcode
		BarCodeReader reader = new BarCodeReader("test.png", DecodeType.TYPES_1D);
		for (BarCodeResult result : reader.readBarCodes()) {
			System.out.println("BarCode CodeText: " + result.getCodeText());
			System.out.println("BarCode CodeType: " + result.getCodeTypeName());
		}
		// ExEnd: ReadType1DBarcode
	}
}
