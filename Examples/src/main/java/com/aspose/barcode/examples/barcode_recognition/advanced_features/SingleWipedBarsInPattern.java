package com.aspose.barcode.examples.barcode_recognition.advanced_features;

import com.aspose.barcode.barcoderecognition.BarCodeReader;
import com.aspose.barcode.barcoderecognition.DecodeType;
import com.aspose.barcode.examples.Utils;

public class SingleWipedBarsInPattern {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// ExStart:SingleWipedBarsInPattern
		// The path to the documents directory.
		String dataDir = Utils.getDataDir(SingleWipedBarsInPattern.class) + "BarcodeReader/advanced_features/";

		// Create an instance of BarCodeReader class
		// Set file path
		// Set the recognition type
		BarCodeReader reader = new BarCodeReader(dataDir + "file.tiff", DecodeType.CODE_128);

		// Perform read operation
		reader.getQualitySettings().setAllowOneDWipedBarsRestoration(true);

		while (reader.read()) {
			System.out.println("codetext: " + reader.getCodeText());
			System.out.println("Symbology type: " + reader.getCodeType());
		}

		reader.close();
		// ExEnd:SingleWipedBarsInPattern
	}

}
