package com.aspose.barcode.examples.barcode_recognition;

import com.aspose.barcode.barcoderecognition.BarCodeReader;
import com.aspose.barcode.barcoderecognition.DecodeType;

public class ReadMostCommonTypeBarcode {

	public static void main(String[] args) throws Exception {
		// ExStart: ReadMostCommonTypeBarcode
		BarCodeReader reader = new BarCodeReader("GS1Code128", DecodeType.MOST_COMMON_TYPES);
		while (reader.read()) {
			String codeType = reader.getCodeType().toString();
			String codeText = reader.getCodeText();
			System.out.println(codeType + ", " + codeText);
		}
		// ExEnd:ReadMostCommonTypeBarcode
	}
}
