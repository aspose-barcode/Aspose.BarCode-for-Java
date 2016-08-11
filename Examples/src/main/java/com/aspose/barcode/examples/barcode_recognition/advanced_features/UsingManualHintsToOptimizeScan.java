package com.aspose.barcode.examples.barcode_recognition.advanced_features;

import com.aspose.barcode.barcoderecognition.BarCodeReadType;
import com.aspose.barcode.barcoderecognition.BarCodeReader;
import com.aspose.barcode.barcoderecognition.ManualHint;
import com.aspose.barcode.barcoderecognition.RecognitionMode;
import com.aspose.barcode.examples.ApplyALicense;
import com.aspose.barcode.examples.Utils;

public class UsingManualHintsToOptimizeScan {

	public static void main(String[] args) throws Exception {
		ApplyALicense.applyALicense();

		// The path to the resource directory.
		String dataDir = Utils.getDataDir(UsingManualHintsToOptimizeScan.class) + "BarcodeReader/advanced_features/";
		
		long s = System.currentTimeMillis();
		String filename = dataDir + "datamatrix.bmp";

		BarCodeReader reader = new BarCodeReader(filename, BarCodeReadType.GS1DataMatrix);
		System.out.println("Skip rotated barcodes");
		reader.setRecognitionMode(RecognitionMode.ManualHints);
		reader.setManualHints(ManualHint.SkipRotatedBarcodes);

		while (reader.read()) {
			System.out.println(reader.getReadTypeName() + ": " + reader.getCodeText() + " QA:" + reader.getRecognitionQuality());
		}
		long res1 = System.currentTimeMillis() - s;
		System.out.println(res1);
		System.out.println();

		s = System.currentTimeMillis();
		reader = new BarCodeReader(filename, BarCodeReadType.GS1DataMatrix);
		System.out.println("Not skip rotated barcodes");

		while (reader.read()) {
			System.out.println(reader.getReadTypeName() + ": " + reader.getCodeText() + " QA:" + reader.getRecognitionQuality());
		}
		long res2 = System.currentTimeMillis() - s;
		System.out.println(res2);
	}

}
