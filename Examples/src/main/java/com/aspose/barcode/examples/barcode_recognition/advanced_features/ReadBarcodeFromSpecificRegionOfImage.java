package com.aspose.barcode.examples.barcode_recognition.advanced_features;

import java.awt.Rectangle;
import java.io.FileInputStream;
import java.io.InputStream;

import com.aspose.barcode.barcoderecognition.BarCodeReadType;
import com.aspose.barcode.barcoderecognition.BarCodeReader;
import com.aspose.barcode.examples.ApplyALicense;
import com.aspose.barcode.examples.Utils;
import com.aspose.barcode.examples.barcode.basic_features.SpecifySymbology;

public class ReadBarcodeFromSpecificRegionOfImage {

	public static void main(String[] args) throws Exception {
		ApplyALicense.applyALicense();
		
		// The path to the resource directory.
    	String dataDir = Utils.getDataDir(SpecifySymbology.class) + "BarcodeReader/advanced_features/";
    	
		// Open the stream. Read only access is enough for Aspose.BarCode to load an image.
		InputStream stream = new FileInputStream(dataDir + "specificRegion.png");

		// Create an instance of BarCodeReader class
		// and specify an area to look for the barcode
		BarCodeReader reader = new BarCodeReader(stream, new Rectangle(0, 0, 100, 50), BarCodeReadType.Pdf417);

		// Read all barcodes in the provided area
		while (reader.read()) {
		    // Display the codetext and symbology type of the barcode found
		    System.out.println("Codetext: " + reader.getCodeText() + " Symbology: " + reader.getReadTypeName());
		}

		// Close the reader
		reader.close();
	}

}
