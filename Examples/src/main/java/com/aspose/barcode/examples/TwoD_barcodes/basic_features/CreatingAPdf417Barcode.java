package com.aspose.barcode.examples.TwoD_barcodes.basic_features;

import java.io.IOException;

import com.aspose.barcode.Pdf417ErrorLevel;
import com.aspose.barcode.examples.Utils;
import com.aspose.barcode.generation.BarcodeGenerator;

public class CreatingAPdf417Barcode {

	public static void main(String[] args) throws IOException {
		// The path to the resource directory.
		String dataDir = Utils.getDataDir(CreatingAPdf417Barcode.class) + "2DBarcode/BasicFeatures/";

		createAPdf417Barcode(dataDir);
		pdf417ErrorCorrectionLevel(dataDir);
		pdf417CompactionMode(dataDir);
	}

	public static void createAPdf417Barcode(String dataDir) throws IOException {
		// ExStart: createAPdf417Barcode
		BarcodeGenerator generator = new BarcodeGenerator(com.aspose.barcode.EncodeTypes.PDF_417, "1234567890");
		generator.save(dataDir + "pdf417.bmp");
		// ExEnd: createAPdf417Barcode
	}

	public static void pdf417ErrorCorrectionLevel(String dataDir) throws IOException {
		// ExStart: pdf417ErrorCorrectionLevel
		BarcodeGenerator generator = new BarcodeGenerator(com.aspose.barcode.EncodeTypes.PDF_417, "1234567890");
		generator.getParameters().getBarcode().getPdf417().setPdf417ErrorLevel(Pdf417ErrorLevel.LEVEL_8);

		generator.save(dataDir + "pdf417ErrorCorrectionLevel.bmp");
		// ExEnd: pdf417ErrorCorrectionLevel
	}

	public static void pdf417CompactionMode(String dataDir) throws IOException {
		// ExStart: pdf417CompactionMode
		BarcodeGenerator generator = new BarcodeGenerator(com.aspose.barcode.EncodeTypes.PDF_417, "This is text data.");
		// Set Pdf417 Compaction Mode to Text
		generator.getParameters().getBarcode().getPdf417().setPdf417ErrorLevel(Pdf417ErrorLevel.LEVEL_0);

		// Save the image to disk in PNG format
		generator.save(dataDir + "pdf417CompactionMode.png");
		// ExEnd: pdf417CompactionMode
	}
}
