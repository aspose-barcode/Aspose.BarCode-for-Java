package com.aspose.barcode.examples.barcode_recognition;

import com.aspose.barcode.barcoderecognition.BarCodeReader;
import com.aspose.barcode.barcoderecognition.DecodeType;

public class ReadPostalTypesBarcode {

	public static void main(String[] args) throws Exception {
		// ExStart: ReadPostalTypesBarcode
		BarCodeReader reader = new BarCodeReader("test.png", DecodeType.POSTAL_TYPES);
		while (reader.read()) {
			String codeType = reader.getCodeType().toString();
			String codeText = reader.getCodeText();
			System.out.println(codeType + ", " + codeText);
		}
		// ExEnd: ReadPostalTypesBarcode
	}
}
