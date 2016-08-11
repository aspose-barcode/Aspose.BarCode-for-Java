package com.aspose.barcode.examples.barcode_recognition.basic_features;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import com.aspose.barcode.barcoderecognition.BarCodeReadType;
import com.aspose.barcode.barcoderecognition.BarCodeReader;
import com.aspose.barcode.examples.ApplyALicense;
import com.aspose.barcode.examples.Utils;

public class SpecificBarcodeSymbology {

	public static void main(String[] args) throws Exception {
		ApplyALicense.applyALicense();
		
		// The path to the resource directory.
    	String dataDir = Utils.getDataDir(SpecificBarcodeSymbology.class) + "BarcodeReader/basic_features/";
		BufferedImage img = ImageIO.read(new File(dataDir + "CodeText.jpg"));
		
		// Initialize barcode reader
		BarCodeReader rd = new BarCodeReader(img, BarCodeReadType.Code128);
		// Read barcode of type Code39Extended
		while (rd.read()) {
			// Print the code text, if barcode found
			System.out.println("CodeText: " + rd.getCodeText().toString());
			// Print the symbology type
			System.out.println("CodeText: " + rd.getReadTypeName());
		}
	}
}
