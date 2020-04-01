package com.aspose.barcode.examples.barcode_recognition.advanced_features;

import com.aspose.barcode.barcoderecognition.BarCodeReader;
import com.aspose.barcode.barcoderecognition.BarCodeResult;
import com.aspose.barcode.barcoderecognition.QualitySettings;
import com.aspose.barcode.examples.ApplyALicense;
import com.aspose.barcode.examples.Utils;

public class UsingManualHintsToOptimizeScan {

	public static void main(String[] args) throws Exception {
		ApplyALicense.applyALicense();

		// ExStart: UsingManualHintsToOptimizeScan
		// The path to the resource directory.
		String dataDir = Utils.getDataDir(UsingManualHintsToOptimizeScan.class) + "BarcodeReader/advanced_features/";

		long s = System.currentTimeMillis();
		String filename = dataDir + "datamatrix.bmp";

		BarCodeReader reader = new BarCodeReader(filename,
				com.aspose.barcode.barcoderecognition.DecodeType.GS_1_DATA_MATRIX);
		System.out.println("Skip rotated barcodes");
		reader.setQualitySettings(QualitySettings.getHighQuality());

		for (BarCodeResult result : reader.readBarCodes()) {
			System.out.println(result.getCodeType() + ": " + result.getCodeText() + " QA:" + result.getReadingQuality());
		}
		
		long res1 = System.currentTimeMillis() - s;
		System.out.println(res1);
		System.out.println();

		s = System.currentTimeMillis();
		reader = new BarCodeReader(filename, com.aspose.barcode.barcoderecognition.DecodeType.GS_1_DATA_MATRIX);
		System.out.println("Not skip rotated barcodes");

		for (BarCodeResult result : reader.readBarCodes()) {
			System.out.println(result.getCodeType() + ": " + result.getCodeText() + " QA:" + result.getReadingQuality());
		}
		
		long res2 = System.currentTimeMillis() - s;
		System.out.println(res2);
		// ExEnd: UsingManualHintsToOptimizeScan
	}

}
