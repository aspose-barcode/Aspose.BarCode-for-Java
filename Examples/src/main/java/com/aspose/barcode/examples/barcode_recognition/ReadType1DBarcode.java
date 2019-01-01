package com.aspose.barcode.examples.barcode_recognition;

import com.aspose.barcode.barcoderecognition.BarCodeReader;
import com.aspose.barcode.barcoderecognition.DecodeType;

public class ReadType1DBarcode
{

	public static void main(String[] args) throws Exception {
		//ExStart: ReadMostCommonTypeBarcode
		BarCodeReader reader = new BarCodeReader("test.png", DecodeType.TYPES_1D);
		while (reader.read())
		{
			String codeType = reader.getCodeType().toString();
			String codeText = reader.getCodeText();
			System.out.println(codeType + ", " + codeText);
		}
		//ExEnd:ReadMostCommonTypeBarcode
	}
}
