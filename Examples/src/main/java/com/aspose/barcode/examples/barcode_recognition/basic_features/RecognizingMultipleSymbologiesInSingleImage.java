package com.aspose.barcode.examples.barcode_recognition.basic_features;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import com.aspose.barcode.barcoderecognition.BarCodeReader;
import com.aspose.barcode.barcoderecognition.BarCodeResult;
import com.aspose.barcode.barcoderecognition.DecodeType;
import com.aspose.barcode.examples.ApplyALicense;
import com.aspose.barcode.examples.Utils;

public class RecognizingMultipleSymbologiesInSingleImage {

	public static void main(String[] args) throws Exception {
		ApplyALicense.applyALicense();

		// ExStart: RecognizingMultipleSymbologiesInSingleImage
		// The path to the resource directory.
		String dataDir = Utils.getDataDir(RecognizingMultipleSymbologiesInSingleImage.class)
				+ "BarcodeReader/basic_features/";
		BufferedImage img = ImageIO.read(new File(dataDir + "MultipleBarcodes.png"));

		// Initialize barcode reader
		BarCodeReader reader = new BarCodeReader(img, DecodeType.ALL_SUPPORTED_TYPES);
		
		// Read all types of barcode
		for (BarCodeResult result : reader.readBarCodes()) {
			System.out.println("CodeText: " + result.getCodeText());
			System.out.println("Symbology type: " + result.getCodeType());
		}
		// ExEnd: RecognizingMultipleSymbologiesInSingleImage
	}
}
