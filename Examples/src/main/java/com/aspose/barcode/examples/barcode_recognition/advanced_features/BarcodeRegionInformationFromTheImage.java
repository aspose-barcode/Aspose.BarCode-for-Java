package com.aspose.barcode.examples.barcode_recognition.advanced_features;

import java.awt.Point;

import com.aspose.barcode.barcoderecognition.BarCodeReadType;
import com.aspose.barcode.barcoderecognition.BarCodeReader;
import com.aspose.barcode.barcoderecognition.BarCodeRegion;
import com.aspose.barcode.examples.ApplyALicense;
import com.aspose.barcode.examples.Utils;

public class BarcodeRegionInformationFromTheImage {

	public static void main(String[] args) throws Exception {
		ApplyALicense.applyALicense();
		
		// The path to the resource directory.
    	String dataDir = Utils.getDataDir(BarcodeRegionInformationFromTheImage.class) + "BarcodeReader/advanced_features/";
		// Read code39 barcode from image
		String imageFilePath = dataDir + "code39Extended.jpg";
		
		BarCodeReader reader = new BarCodeReader(imageFilePath, BarCodeReadType.Code39Standard);

		// Try to recognize all possible barcodes in the image
		while (reader.read()) {

			// Get the region information
			BarCodeRegion region = reader.getRegion();
			if (region != null) {
				// Display x and y coordinates of barcode detected
				Point[] point = region.getPoints();
				System.out.println("Top left coordinates: X = " + point[0].x + ", Y = " + point[0].y);
				System.out.println("Bottom left coordinates: X = " + point[1].x + ", Y = " + point[1].y);
				System.out.println("Bottom right coordinates: X = " + point[2].x + ", Y = " + point[2].y);
				System.out.println("Top right coordinates: X = " + point[3].x + ", Y = " + point[3].y);
			}
			System.out.println("Codetext: " + reader.getCodeText());
		}

		// Close the reader
		reader.close();
	}

}
