package com.aspose.barcode.examples.barcode_recognition.advanced_features;

import java.awt.Point;

import com.aspose.barcode.barcoderecognition.BarCodeReader;
import com.aspose.barcode.barcoderecognition.BarCodeResult;
import com.aspose.barcode.examples.ApplyALicense;
import com.aspose.barcode.examples.Utils;

public class BarcodeRegionInformationFromTheImage {

	public static void main(String[] args) throws Exception {
		ApplyALicense.applyALicense();

		// ExStart: BarcodeRegionInformationFromTheImage
		// The path to the resource directory.
		String dataDir = Utils.getDataDir(BarcodeRegionInformationFromTheImage.class)
				+ "BarcodeReader/advanced_features/";
		// Read code39 barcode from image
		String imageFilePath = dataDir + "code39Extended.jpg";

		BarCodeReader reader = new BarCodeReader(imageFilePath,
				com.aspose.barcode.barcoderecognition.DecodeType.CODE_39_STANDARD);

		// Try to recognize all possible barcodes in the image
		reader.readBarCodes();
		for (BarCodeResult result : reader.readBarCodes()) {
			if (result.getRegion() != null) {
				// Display x and y coordinates of barcode detected
				Point[] point = result.getRegion().getPoints();
				System.out.println("Top left coordinates: X = " + point[0].x + ", Y = " + point[0].y);
				System.out.println("Bottom left coordinates: X = " + point[1].x + ", Y = " + point[1].y);
				System.out.println("Bottom right coordinates: X = " + point[2].x + ", Y = " + point[2].y);
				System.out.println("Top right coordinates: X = " + point[3].x + ", Y = " + point[3].y);
			}
		}
		// ExEnd: BarcodeRegionInformationFromTheImage
	}

}
