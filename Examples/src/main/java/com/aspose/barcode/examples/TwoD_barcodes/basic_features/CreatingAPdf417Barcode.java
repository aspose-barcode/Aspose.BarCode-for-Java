package com.aspose.barcode.examples.TwoD_barcodes.basic_features;

import com.aspose.barcode.BarCodeBuilder;
import com.aspose.barcode.Pdf417CompactionMode;
import com.aspose.barcode.Pdf417ErrorLevel;
import com.aspose.barcode.Symbology;
import com.aspose.barcode.examples.Utils;
import com.aspose.barcode.examples.barcode.basic_features.SpecifySymbology;

public class CreatingAPdf417Barcode {

	public static void main(String[] args) {
		createAPdf417Barcode();
		pdf417ErrorCorrectionLevel();
		pdf417CompactionMode();
	}
	
	public static void createAPdf417Barcode() {
		// The path to the resource directory.
    	String dataDir = Utils.getDataDir(SpecifySymbology.class) + "2DBarcode/BasicFeatures/";
    	
		BarCodeBuilder b = new BarCodeBuilder();
		b.setSymbologyType(Symbology.Pdf417);
		b.setCodeText("1234567890");
		
		b.save(dataDir + "pdf417.bmp");
	}
	
	public static void pdf417ErrorCorrectionLevel() {
		// The path to the resource directory.
    	String dataDir = Utils.getDataDir(SpecifySymbology.class) + "2DBarcode/BasicFeatures/";
    	
		BarCodeBuilder b = new BarCodeBuilder();
		b.setSymbologyType(Symbology.Pdf417);
		b.setPdf417ErrorLevel(Pdf417ErrorLevel.Level8);
		b.setCodeText("12345");
		
		b.save(dataDir + "pdf417ErrorCorrectionLevel.bmp");
	}
	
	public static void pdf417CompactionMode() {
		// The path to the resource directory.
    	String dataDir = Utils.getDataDir(SpecifySymbology.class) + "2DBarcode/BasicFeatures/";
    	
    	BarCodeBuilder builder = new BarCodeBuilder("This is text data", Symbology.Pdf417);
    	// Set Pdf417 Compaction Mode to Text
    	builder.setPdf417CompactionMode(Pdf417CompactionMode.Text);

    	// Save the image to disk in PNG format
    	builder.save(dataDir + "pdf417CompactionMode.png");
	}
}
