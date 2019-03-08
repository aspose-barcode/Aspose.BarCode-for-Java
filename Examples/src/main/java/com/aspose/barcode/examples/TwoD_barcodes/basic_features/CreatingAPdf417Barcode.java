package com.aspose.barcode.examples.TwoD_barcodes.basic_features;

import java.io.IOException;

import com.aspose.barcode.BarCodeBuilder;
import com.aspose.barcode.Pdf417CompactionMode;
import com.aspose.barcode.Pdf417ErrorLevel;
import com.aspose.barcode.examples.Utils;
import com.aspose.barcode.generation.BarCodeGenerator;

public class CreatingAPdf417Barcode {

	public static void main(String[] args) throws IOException {
		// The path to the resource directory.
    	String dataDir = Utils.getDataDir(CreatingAPdf417Barcode.class) + "2DBarcode/BasicFeatures/";
    	
		createAPdf417Barcode(dataDir);
		pdf417ErrorCorrectionLevel(dataDir);
		pdf417CompactionMode(dataDir);
	}
	
	public static void createAPdf417Barcode(String dataDir) throws IOException 
    {
		BarCodeGenerator generator = new BarCodeGenerator(com.aspose.barcode.EncodeTypes.PDF_417, "1234567890");
		generator.save(dataDir + "pdf417.bmp");
	}
	
	public static void pdf417ErrorCorrectionLevel(String dataDir) throws IOException {
    	
		BarCodeGenerator generator = new BarCodeGenerator(com.aspose.barcode.EncodeTypes.PDF_417, "1234567890");
		generator.getPdf417().setErrorLevel(Pdf417ErrorLevel.LEVEL_8);
		
		generator.save(dataDir + "pdf417ErrorCorrectionLevel.bmp");
	}
	
	public static void pdf417CompactionMode(String dataDir) throws IOException {
    	
		BarCodeGenerator generator = new BarCodeGenerator(com.aspose.barcode.EncodeTypes.PDF_417, "This is text data.");
    	// Set Pdf417 Compaction Mode to Text
		generator.getPdf417().setErrorLevel(Pdf417ErrorLevel.LEVEL_0);

		// Save the image to disk in PNG format
		generator.save(dataDir + "pdf417CompactionMode.png");
	}
}
