package com.aspose.barcode.examples.TwoD_barcodes.basic_features;

import com.aspose.barcode.BarCodeBuilder;
import com.aspose.barcode.examples.Utils;

public class CreatingTwoDimensional2DBarcode {

	public static void main(String[] args) {
		// The path to the resource directory.
    	String dataDir = Utils.getDataDir(CreatingTwoDimensional2DBarcode.class) + "2DBarcode/BasicFeatures/";
		creatingAPdf417Barcode(dataDir);
		creatingADataMatrixBarcode(dataDir);
	}

	public static void creatingAPdf417Barcode(String dataDir) {
		
		BarCodeBuilder b= new BarCodeBuilder();
                b.setEncodeType(com.aspose.barcode.EncodeTypes.PDF_417);

		// Width of each module
		b.setxDimension(0.6f);

		// Height of each module
		b.setyDimension(1.2f);
		b.setCodeText("this is some test code text. \n Second line \n third line.");
		b.save(dataDir + "pdf417.bmp");
	}
	
	public static void creatingADataMatrixBarcode(String dataDir) {
		
		BarCodeBuilder b = new BarCodeBuilder();
                b.setEncodeType(com.aspose.barcode.EncodeTypes.DATA_MATRIX);

		// Width of each module
		b.setxDimension(0.6f);
		b.setCodeText("123456789");
		b.save(dataDir + "datamatrix.bmp");

	}

}
