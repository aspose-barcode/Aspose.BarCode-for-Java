package com.aspose.barcode.examples.barcode_recognition.basic_features;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import com.aspose.barcode.barcoderecognition.BarCodeReader;
import com.aspose.barcode.barcoderecognition.DecodeType;
import com.aspose.barcode.examples.ApplyALicense;
import com.aspose.barcode.examples.Utils;

public class RecognizingMultipleSymbologiesInSingleImage {

	public static void main(String[] args) throws Exception {
		ApplyALicense.applyALicense();
		
		//ExStart: RecognizingMultipleSymbologiesInSingleImage
		// The path to the resource directory.
    	String dataDir = Utils.getDataDir(RecognizingMultipleSymbologiesInSingleImage.class) + "BarcodeReader/basic_features/";
		BufferedImage img = ImageIO.read(new File(dataDir + "MultipleBarcodes.png"));
		
		// Initialize barcode reader
		BarCodeReader rd = new BarCodeReader(img, DecodeType.ALL_SUPPORTED_TYPES);
		// Read all types of barcode
		while (rd.read()) {
			// Print the code text, if barcode found
			System.out.println("CodeText: " + rd.getCodeText().toString());
			// Print the symbology type
			System.out.println("CodeText: " + rd.getCodeType());
		}
		//ExEnd: RecognizingMultipleSymbologiesInSingleImage
	}
}
