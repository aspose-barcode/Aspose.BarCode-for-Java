package com.aspose.barcode.examples.barcode_recognition.advanced_features;

import java.awt.Rectangle;
import java.io.FileInputStream;
import java.io.InputStream;

import com.aspose.barcode.barcoderecognition.BarCodeReader;
import com.aspose.barcode.examples.ApplyALicense;
import com.aspose.barcode.examples.Utils;

public class ReadBarcodeFromSpecificRegionOfImage {

	public static void main(String[] args) throws Exception {
		ApplyALicense.applyALicense();

		// ExStart: ReadBarcodeFromSpecificRegionOfImage
		// The path to the resource directory.
		String dataDir = Utils.getDataDir(ReadBarcodeFromSpecificRegionOfImage.class)
				+ "BarcodeReader/advanced_features/";

		// Open the stream. Read only access is enough for Aspose.BarCode to load an
		// image.
		InputStream stream = new FileInputStream(dataDir + "specificRegion.png");

		// Get BufferedImage with exact bar code only
		java.awt.image.BufferedImage img = javax.imageio.ImageIO.read(new java.io.File(dataDir));

		// Create an instance of BarCodeReader class
		// and specify an area to look for the barcode
		BarCodeReader reader = new BarCodeReader(img, new Rectangle(0, 0, 100, 50),
				com.aspose.barcode.barcoderecognition.DecodeType.PDF_417);

		// Read all barcodes in the provided area
		while (reader.read()) {
			// Display the codetext and symbology type of the barcode found
			System.out.println("Codetext: " + reader.getCodeText() + " Symbology: " + reader.getCodeType());
		}

		// Close the reader
		reader.close();
		// ExEnd: ReadBarcodeFromSpecificRegionOfImage
	}

}
