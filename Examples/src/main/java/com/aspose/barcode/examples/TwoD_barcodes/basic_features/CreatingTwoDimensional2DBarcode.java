package com.aspose.barcode.examples.TwoD_barcodes.basic_features;

import java.io.IOException;

import com.aspose.barcode.examples.Utils;
import com.aspose.barcode.generation.BarcodeGenerator;

public class CreatingTwoDimensional2DBarcode {

	public static void main(String[] args) throws IOException {
		// The path to the resource directory.
    	String dataDir = Utils.getDataDir(CreatingTwoDimensional2DBarcode.class) + "2DBarcode/BasicFeatures/";
		creatingAPdf417Barcode(dataDir);
		creatingADataMatrixBarcode(dataDir);
	}

	public static void creatingAPdf417Barcode(String dataDir) throws IOException {
		//ExStart: creatingAPdf417Barcode
		BarcodeGenerator bb = new BarcodeGenerator(com.aspose.barcode.EncodeTypes.PDF_417, "this is some test code text. \\n Second line \\n third line.");

		// Width of each module
		bb.getParameters().getBarcode().getXDimension().setMillimeters(0.6f);

		bb.save(dataDir + "pdf417.bmp");
		//ExEnd: creatingAPdf417Barcode
	}
	
	public static void creatingADataMatrixBarcode(String dataDir) throws IOException {
		//ExStart: creatingADataMatrixBarcode
		BarcodeGenerator bb = new BarcodeGenerator(com.aspose.barcode.EncodeTypes.DATA_MATRIX, "123456789");

		// Width of each module
		bb.getParameters().getBarcode().getXDimension().setMillimeters(0.6f);
		
		bb.save(dataDir + "datamatrix.bmp");
		//ExEnd: creatingADataMatrixBarcode
	}

}
