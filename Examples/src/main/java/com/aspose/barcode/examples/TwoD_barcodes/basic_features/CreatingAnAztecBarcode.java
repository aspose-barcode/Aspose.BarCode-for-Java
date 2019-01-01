package com.aspose.barcode.examples.TwoD_barcodes.basic_features;

import java.io.IOException;

import com.aspose.barcode.BarCodeBuilder;
import com.aspose.barcode.BarCodeImageFormat;
import com.aspose.barcode.examples.Utils;

public class CreatingAnAztecBarcode {

	public static void main(String[] args) throws IOException {
		// The path to the resource directory.
		String dataDir = Utils.getDataDir(CreatingAnAztecBarcode.class) + "2DBarcode/BasicFeatures/";
		createAnAztecBarcode(dataDir);
		errorCorrection(dataDir);
	}

	public static void createAnAztecBarcode(String dataDir) throws IOException {
		
		BarCodeBuilder b = new BarCodeBuilder();
                b.setEncodeType(com.aspose.barcode.EncodeTypes.AZTEC);
		b.setCodeText("1234567890");
		
		b.save(dataDir + "aztec.bmp", BarCodeImageFormat.BMP);
	}
	
	public static void errorCorrection(String dataDir) throws IOException {
		
		BarCodeBuilder b = new BarCodeBuilder();
                b.setEncodeType(com.aspose.barcode.EncodeTypes.AZTEC);
		b.setAztectErrorLevel(50);
		b.setCodeText("1234567890");
		
		b.save(dataDir + "error_correction_aztec.bmp", BarCodeImageFormat.BMP);
	}

}
